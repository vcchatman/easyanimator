package cs5004.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for all concrete Shape objects.
 */
public abstract class AbstractShape implements Shape {

  private int height;
  private int width;
  private Color color;
  private int x;
  private int y;
  private String id;
  private String type;
  private int tAppear;
  private int tDisappear;
  private List<Transformation> changes;

  /**
   * Constructor for the Shape using width and height.
   *
   * @param hei   int for the height.
   * @param wid   int for the width.
   * @param red   int between 0 and 255 for red color.
   * @param green int between 0 and 255 for green color.
   * @param blue  int between 0 and 255 for blue color.
   * @param x     int for x position of shape.
   * @param y     int for y position of shape.
   */
  public AbstractShape(int hei,
                       int wid,
                       int red,
                       int green,
                       int blue,
                       int x,
                       int y,
                       String id,
                       String type,
                       int tAppear) {
    if (green > 255 || green < 0
            || red > 255 || red < 0
            || blue > 255 || blue < 0) {
      throw new IllegalArgumentException("Red, blue, or green color index must be between 0 and "
              + "255!");
    } else if (hei < 0 || wid < 0) {
      throw new IllegalArgumentException("Shape can't have a negative height or width!");
    }
    this.width = wid;
    this.height = hei;
    this.color = new Color(red, green, blue);
    this.x = x;
    this.y = y;
    this.id = id;
    this.type = type;
    this.tAppear = tAppear;
    this.tDisappear = 0;
    this.changes = new ArrayList<>();
  }

  @Override
  public String retID() {
    return this.id;
  }

  @Override
  public int retHeight() {
    return this.height;
  }

  @Override
  public int retWidth() {
    return this.width;
  }

  @Override
  public int retX() {
    return this.x;
  }

  @Override
  public int retY() {
    return this.y;
  }

  @Override
  public Color retColor() {
    return this.color;
  }

  @Override
  public String retType() {
    return this.type;
  }

  @Override
  public int getAppearanceTime() {
    return tAppear;
  }

  @Override
  public int getDisappearanceTime() {
    return tDisappear;
  }

  @Override
  public void setDisappearanceTime() {
    this.changes.sort(new TimeComparatorEnd());
    this.tDisappear = this.changes.get(0).getEndTime();
  }

  @Override
  public List<Transformation> getChanges() {
    return this.changes;
  }

  @Override
  public void addTransformation(Transformation motion) {
    if (!this.id.equals(motion.getName())) {
      throw new IllegalArgumentException("This transformation is for a different shape.");
    }
    for (Transformation each : this.changes) {
      if ((motion.getStartTime() > each.getStartTime()
              && motion.getStartTime() < each.getEndTime())
              || (motion.getEndTime() > each.getStartTime()
              && motion.getEndTime() < each.getEndTime())) {
        throw new IllegalArgumentException("There is already a transformation of this type at this "
                + "time.");
      }
    }
    this.changes.add(motion);
    this.setDisappearanceTime();
  }

  @Override
  public Shape createFrame(int frame) {

    Shape newFrame = this;
    int frameX = this.x;
    int frameY = this.y;
    int frameRed = this.color.getRed();
    int frameGreen = this.color.getGreen();
    int frameBlue = this.color.getBlue();
    int frameWidth = this.width;
    int frameHeight = this.height;

    for (Transformation each : this.changes) {

      if (frame >= each.getStartTime() && frame <= each.getEndTime()) {

        int tweenX = each.pullFrame(each.getStartX(),
                each.getEndX(),
                each.getStartTime(),
                each.getEndTime(), frame);
        if (tweenX != frameX) {
          frameX = tweenX;
        }

        int tweenY = each.pullFrame(
                each.getStartY(),
                each.getEndY(),
                each.getStartTime(),
                each.getEndTime(), frame);
        if (tweenY != frameY) {
          frameY = tweenY;
        }

        int tweenRed = each.pullFrame(each.getStartRed(),
                each.getEndRed(),
                each.getStartTime(),
                each.getEndTime(), frame);
        if (tweenRed != frameRed) {
          frameRed = tweenRed;
        }

        int tweenGreen = each.pullFrame(each.getStartGreen(),
                each.getEndGreen(),
                each.getStartTime(),
                each.getEndTime(), frame);
        if (tweenGreen != frameGreen) {
          frameGreen = tweenGreen;
        }

        int tweenBlue = each.pullFrame(
                each.getStartBlue(),
                each.getEndBlue(),
                each.getStartTime(),
                each.getEndTime(), frame);
        if (tweenBlue != frameBlue) {
          frameBlue = tweenBlue;
        }

        int tweenWidth = each.pullFrame(each.getStartWidth(),
                each.getEndWidth(),
                each.getStartTime(),
                each.getEndTime(), frame);
        if (tweenWidth != frameWidth) {
          frameWidth = tweenWidth;
        }

        int tweenHeight = each.pullFrame(each.getStartHeight(),
                each.getEndHeight(),
                each.getStartTime(),
                each.getEndTime(), frame);
        if (tweenHeight != frameHeight) {
          frameHeight = tweenHeight;
        }
      }
    }

    if (this.type.equals("rectangle")) {
      newFrame = new Rectangle(frameHeight,
              frameWidth,
              frameRed,
              frameGreen,
              frameBlue,
              frameX,
              frameY,
              this.id,
              this.type,
              frame);
    } else if (this.type.equals("ellipse")) {
      newFrame = new Oval(frameHeight,
              frameWidth,
              frameRed,
              frameGreen,
              frameBlue,
              frameX,
              frameY,
              this.id,
              this.type,
              frame);
    }
    return newFrame;
  }

  @Override
  public String getAttributes() {
    String s = "";
    s += String.format("Create shape %s with corner at (%d, %d), width=%d and height=%d\n",
            this.id,
            this.x,
            this.y,
            this.width,
            this.height);

    s += String.format("%s appears at time t=%d and disappears at time t=%d\n\n",
            this.id,
            this.tAppear,
            this.tDisappear);
    return s;
  }
}
