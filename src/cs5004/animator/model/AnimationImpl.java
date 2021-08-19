package cs5004.animator.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.util.AnimationBuilder;

/**
 * Class the employs a hash map to house Shape objects and an array list to house Transformations
 * objects associates with the shapes. Included in cs5004.animator.model so that as the Shape
 * objects change, there will be a record of what transformations they underwent which can be
 * printed in a string description and animated visually.
 */
public class AnimationImpl implements Animation {
  private Canvas canvas;
  private Map<String, Shape> shapeMap;
  private List<Transformation> transformationList;

  /**
   * Constructs beginning data structures and their operations for new Animation Implementation to
   * house Shape/Transformation objects and the Canvas object that will define the space on which
   * they will be rendered.
   */
  public AnimationImpl() {
    canvas = null;
    shapeMap = new LinkedHashMap<>();
    transformationList = new ArrayList<>();
  }

  @Override
  public void addAllTransformations() {
    for (Map.Entry<String, Shape> each : shapeMap.entrySet()) {
      this.transformationList.addAll(each.getValue().getChanges());
    }
    transformationList.sort(new TimeComparatorStart());
  }

  @Override
  public void addCanvas(int x, int y, int width, int height) {
    this.canvas = new Canvas(x, y, width, height);
  }

  @Override
  public void addShape(String id, Shape shape) {
    this.shapeMap.put(id, shape);
  }

  @Override
  public Canvas retCanvas() {
    return this.canvas;
  }

  @Override
  public List<Transformation> retList() {
    return transformationList;
  }

  @Override
  public List<Shape> getShapesInFrame(int frame) {
    List<Shape> frameList = new ArrayList<>();
    for (Map.Entry<String, Shape> each : this.shapeMap.entrySet()) {
      frameList.add(each.getValue().createFrame(frame));
    }
    return frameList;
  }

  public Map<String, Shape> retHashMap() {
    return this.shapeMap;
  }

  @Override
  public String getTransformationState() {
    String state = "";
    String initialState = "";
    String currentState = "";

    for (Map.Entry<String, Shape> one : shapeMap.entrySet()) {
      initialState += one.getValue().getAttributes();
    }

    addAllTransformations();

    for (Transformation each : transformationList) {
      currentState += each.getCurrentState();
    }

    state += initialState;
    state += currentState;

    return state.stripTrailing();
  }

  /**
   * Nested class used to parse data from input files in order to be fed into the Animation
   * implementation methods with the end result of producing an animation.
   */
  public static final class Builder implements AnimationBuilder<Animation> {

    Animation animation = new AnimationImpl();

    @Override
    public Animation build() {
      return animation;
    }

    @Override
    public AnimationBuilder<Animation> setBounds(int x, int y, int width, int height) {
      animation.addCanvas(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<Animation> declareShape(String name, String type) {
      if (type.equals("rectangle")) {
        animation.addShape(name, new Rectangle(0, 0, 0, 0, 0, 0,
                0, name, "rectangle", 0));
      }
      if (type.equals("ellipse")) {
        animation.addShape(name, new Oval(0, 0, 0, 0, 0, 0, 0,
                name, "ellipse", 0));
      }
      return this;
    }

    @Override
    public AnimationBuilder<Animation> addMotion(String name, int t1, int x1, int y1, int w1,
                                                 int h1, int r1, int g1, int b1, int t2, int x2,
                                                 int y2, int w2, int h2, int r2, int g2, int b2) {

      if (w1 == 0 || w2 == 0 || h1 == 0 || h2 == 0) {
        throw new IllegalArgumentException("Shape cannot be dimensionless.");
      }

      Map<String, Shape> shapeMap = animation.retHashMap();
      Shape shape = shapeMap.get(name);

      boolean rectangle = shape.retType().equals("rectangle");

      boolean oval = shape.retType().equals("ellipse");

      boolean shapeMoves = (x1 != x2)
              || (y1 != y2);

      boolean shapeResizes = (w1 != w2)
              || (h1 != h2);

      boolean shapeRecolors = (r1 != r2)
              || (g1 != g2)
              || (b1 != b2);

      boolean shapePersists = (x1 == x2)
              && (y1 == y2)
              && (w1 == w2)
              && (h1 == h2)
              && (r1 == r2)
              && (g1 == g2)
              && (b1 == b2);

      if (rectangle) {
        if (shape.retHeight() == 0 || shape.retWidth() == 0) {
          shapeMap.replace(name, new Rectangle(h1, w1, r1, g1, b1, x1, y1, name,
                  "rectangle", t1));
        }
      } else if (oval) {
        if (shape.retHeight() == 0 || shape.retWidth() == 0) {
          shapeMap.replace(name, new Oval(h1, w1, r1, g1, b1, x1, y1, name,
                  "ellipse", t1));
        }
      }

      if (rectangle) {
        if (shapeMoves || shapePersists) {
          Transformation newMotion = new Move(name, Type.MOVE, Type.RECTANGLE,
                  t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
          shape.addTransformation(newMotion);
        }
        if (shapeResizes) {
          Transformation newMotion = new Resize(name, Type.RESIZE, Type.RECTANGLE, t1, x1, y1, w1,
                  h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
          shape.addTransformation(newMotion);
        }
        if (shapeRecolors) {
          Transformation newMotion = new ChangeColor(name, Type.CHANGECOLOR, Type.RECTANGLE, t1,
                  x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
          shape.addTransformation(newMotion);
        }
      }

      if (oval) {
        if (shapeMoves || shapePersists) {
          Transformation newMotion = new Move(name, Type.MOVE, Type.OVAL, t1, x1, y1, w1, h1, r1,
                  g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
          shape.addTransformation(newMotion);
        }
        if (shapeResizes) {
          Transformation newMotion = new Resize(name, Type.RESIZE, Type.OVAL, t1, x1, y1, w1, h1,
                  r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
          shape.addTransformation(newMotion);
        }
        if (shapeRecolors) {
          Transformation newMotion = new ChangeColor(name, Type.CHANGECOLOR, Type.OVAL, t1, x1,
                  y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
          shape.addTransformation(newMotion);
        }
      }
      return this;
    }
  }
}