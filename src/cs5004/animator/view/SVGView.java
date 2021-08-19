package cs5004.animator.view;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.Canvas;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Transformation;

/**
 * Class to create an SVG animation.
 */
public class SVGView implements Views {

  StringBuilder svgBuilder = new StringBuilder();
  private Appendable svgData;
  private PrintStream doc;
  private String svgCode;

  /**
   * Constructor for the SVG animation.
   *
   * @param model AnimationImpl model that contains the shapes and transformations to be rendered
   * @param out   name of the out file
   * @throws IOException if the out file does not exist
   */
  public SVGView(AnimationImpl model, String out) throws IOException {
    Canvas canvasHelper = model.retCanvas();

    this.doc = null;
    if (out.equals("") || out.equalsIgnoreCase("out")) {
      this.svgData = new PrintStream(System.out);
    } else {
      doc = new PrintStream("./" + out);
    }
    this.svgCode = "";

    svgBuilder.append("<svg width=\"").append(canvasHelper.retWidth() + 500).append("\" height=\"")
            .append(canvasHelper.retHeight() + 500).append("\" xmlns=\"http://www.w3"
            + ".org/2000/svg\"")
            .append(">\n");


    svgBuilder.append("<rect>\n").append("<animate id=\"base\" begin=\"0;base.end\" dur=\"10000"
            + ".0ms\" "
            + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n").append("</rect>\n\n");

    Map<String, Shape> helper = model.retHashMap();

    for (Shape v : helper.values()) {
      int duration = v.getDisappearanceTime() - v.getAppearanceTime();
      if (v.retType().equals("rectangle")) {
        svgBuilder.append("<rect ").append("id=\"").append(v.retID()).append("\" x=\"")
                .append(v.retX()).append("\" y=\"").append(v.retY())
                .append("\" width=\"").append(v.retWidth()).append("\" height=\"")
                .append(v.retHeight()).append("\" fill= \"rgb(").append(v.retColor().getRed())
                .append(",").append(v.retColor().getGreen()).append(",")
                .append(v.retColor().getBlue()).append(")\" visibility=\"visible\">\n");

        List<Transformation> helper2 = v.getChanges();
        String changes = addChanges(helper2);
        svgBuilder.append(changes);
        svgBuilder.append("</rect>\n");

      } else {
        svgBuilder.append("<ellipse ").append("id=\"").append(v.retID()).append("\" cx=\"")
                .append(v.retX()).append("\" cy=\"").append(v.retY())
                .append("\" rx=\"").append(v.retWidth()).append("\" ry=\"").append(v.retHeight())
                .append("\" fill= \"rgb(").append(v.retColor().getRed()).append(",")
                .append(v.retColor().getGreen()).append(",").append(v.retColor().getBlue())
                .append(")\" visibility=\"visible\">\n");

        List<Transformation> helper2 = v.getChanges();
        String changes = addChanges(helper2);
        svgBuilder.append(changes);
        svgBuilder.append("</ellipse>\n");
      }
      svgBuilder.append("\n");
    }
    svgBuilder.append("</svg>");
    svgCode = svgBuilder.toString();
  }

  /**
   * Adds any type of transformation to any of the Shape objects.
   *
   * @param items list of transformation objects
   * @return string detailing of overall changes of each of the Shape objects
   */
  public String addChanges(List<Transformation> items) {
    StringBuilder changes = new StringBuilder();
    for (Transformation item : items) {

      int dur = (item.getEndTime() - item.getStartTime()) * 100;

      if (item.getType().toString().equalsIgnoreCase("recolor")) {
        changes.append("<animate id=\"").append(item.getName())
                .append("\" attributeType=\"xml\" begin=\"base.begin+").append(item.getStartTime())
                .append("ms\" dur=\"").append(dur).append("ms\" attributeName=Fill").append(" "
                + "from=rgb(")
                .append(item.getEndRed()).append(",").append(item.getEndGreen()).append(",")
                .append(item.getEndBlue()).append(")").append(" to=").append(item.getEndRed())
                .append(",")
                .append(item.getEndGreen()).append(",").append(item.getEndBlue()).append(")")
                .append(" fill=\"freeze\" \n");

      } else if (item.getType().toString().equalsIgnoreCase("move")) {
        if (item.getShapeType().toString().equalsIgnoreCase("rectangle")) {
          changes.append("<animate id=\"").append(item.getName())
                  .append("\" attributeType=\"xml\" begin=\"base.begin+").append(item.getStartTime()
                  * 1000)
                  .append("ms\" dur=\"").append(dur).append("ms\" attributeName=\"x\" ").append(
                  "from=\"")
                  .append(item.getStartX()).append("\" to=\"").append(item.getEndX())
                  .append("\" fill=\"freeze\"").append(" />\n");
          changes.append("<animate id=\"").append(item.getName())
                  .append("\" attributeType=\"xml\" begin=\"base.begin+").append(item.getStartTime()
                  * 1000)
                  .append("ms\" dur=\"").append(dur).append("ms\" attributeName=\"y\" ").append(
                  "from=\"")
                  .append(item.getStartY()).append("\" to=\"").append(item.getEndY())
                  .append("\" fill=\"freeze\"").append(" />\n");
        } else if (item.getShapeType().toString().equalsIgnoreCase("oval")) {
          changes.append("<animate id=\"").append(item.getName())
                  .append("\" attributeType=\"xml\" begin=\"base.begin+").append(item.getStartTime()
                  * 1000)
                  .append("ms\" dur=\"").append(dur).append("ms\" attributeName=\"cx\" ").append(
                  "from"
                          + "=\"")
                  .append(item.getStartX()).append("\" to=\"").append(item.getEndX())
                  .append("\" fill=\"freeze\"").append(" />\n");
          changes.append("<animate id=\"").append(item.getName())
                  .append("\" attributeType=\"xml\" begin=\"base.begin+").append(item.getStartTime()
                  * 1000)
                  .append("ms\" dur=\"").append(dur).append("ms\" attributeName=\"cy\" ").append(
                  "from"
                          + "=\"")
                  .append(item.getStartY()).append("\" to=\"").append(item.getEndY())
                  .append("\" fill=\"freeze\"").append(" />\n");
        }
      } else if (item.getType().toString().equalsIgnoreCase("resize")) {
        if (item.getShapeType().toString().equalsIgnoreCase("rectangle")) {
          changes.append("<animate id=\"").append(item.getName())
                  .append("\" attributeType=\"xml\"begin=\"base.begin+")
                  .append(item.getStartTime() * 1000).append("ms\" dur=\"").append(dur)
                  .append("ms\" attributeName=width from=\"").append(item.getStartWidth())
                  .append("\" to=\"").append(item.getEndWidth()).append("\" fill=\"freeze\" />\n");
          changes.append("<animate id=\"").append(item.getName())
                  .append("\" attributeType=\"xml\"begin=\"base.begin+")
                  .append(item.getStartTime() * 1000).append("ms\" dur=\"").append(dur)
                  .append("ms\" attributeName=width from=\"").append(item.getStartHeight())
                  .append("\" to=\"").append(item.getEndHeight()).append("\" fill=\"freeze\" />\n");
        } else if (item.getShapeType().toString().equalsIgnoreCase("oval")) {
          changes.append("<animate id=\"").append(item.getName())
                  .append("\" attributeType=\"xml\"begin=\"base.begin+")
                  .append(item.getStartTime() * 1000).append("ms\" dur=\"").append(dur)
                  .append("ms\" attributeName=rx from=\"").append(item.getStartWidth())
                  .append("\" to=\"").append(item.getEndWidth()).append("\" fill=\"freeze\" />\n");
          changes.append("<animate id=\"").append(item.getName())
                  .append("\" attributeType=\"xml\"begin=\"base.begin+")
                  .append(item.getStartTime() * 1000).append("ms\" dur=\"").append(dur)
                  .append("ms\" attributeName=\"ry\" from=\"").append(item.getStartHeight())
                  .append("\" to=\"").append(item.getEndHeight()).append("\" fill=\"freeze\" />\n");
        }
      }
    }
    return changes.toString();
  }

  @Override
  public void setCurrentFrame(int newTick) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation is not valid for this class.");
  }

  @Override
  public void render() {
    if (doc != null) {
      doc.append(svgCode);
      System.out.println("Export Successful");
    } else {
      try {
        this.svgData.append(svgCode);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
