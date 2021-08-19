package cs5004.animator.model;

/**
 * A class of type Shape.Transformation to move a shape object to a new position.
 */
public class Move extends AbstractTransformation {

  public Move(String name,
              Type type,
              Type shapeType,
              int t1,
              int x1,
              int y1,
              int w1,
              int h1,
              int r1,
              int g1,
              int b1,
              int t2,
              int x2,
              int y2,
              int w2,
              int h2,
              int r2,
              int g2,
              int b2) {
    super(name, type, shapeType, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
  }
}
