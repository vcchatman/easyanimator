package cs5004.animator.model;

/**
 * Abstract Transformation class representing the commonalities amongst the three Transformation
 * classes: Resize, Move, and ChangeColor.
 */
public abstract class AbstractTransformation implements Transformation {
  private String name;
  private Type type;
  private Type shapeType;
  private int w1;
  private int h1;
  private int t1;
  private int r1;
  private int g1;
  private int b1;
  private int x1;
  private int y1;
  private int w2;
  private int h2;
  private int t2;
  private int r2;
  private int g2;
  private int b2;
  private int x2;
  private int y2;

  /**
   * Constructor for the Transformation object.
   *
   * @param name      name of the Shape being transformed
   * @param type      type of transformation
   * @param shapeType type of shape being transformed
   * @param t1        start time of transformation
   * @param x1        start x-value of transformation
   * @param y1        start y-value of transformation
   * @param w1        start width of transformation
   * @param h1        start height of transformation
   * @param r1        start red value of transformation
   * @param g1        start green value of transformation
   * @param b1        start blue value of transformation
   * @param t2        end time of transformation
   * @param x2        end x-value of transformation
   * @param y2        end y-value of transformation
   * @param w2        end width of transformation
   * @param h2        end height of transformation
   * @param r2        end red value of transformation
   * @param g2        end green value of transformation
   * @param b2        end blue value of transformation
   */
  public AbstractTransformation(String name,
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
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("Time cannot be negative!");
    }
    if (t1 > t2) {
      throw new IllegalArgumentException("Start time cannot be after end time!");
    }
    if (w1 < 0 || h1 < 0 || w2 < 0 || h2 < 0) {
      throw new IllegalArgumentException("Shape can't have a negative height or width!");
    }
    if (g2 > 255 || g2 < 0 || r2 > 255 || r2 < 0 || b2 > 255 || b2 < 0) {
      throw new IllegalArgumentException("Red, blue, or green color index must be between 0 and "
              + "255!");
    }
    this.name = name;
    this.type = type;
    this.shapeType = shapeType;
    this.t1 = t1;
    this.t2 = t2;
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.w1 = w1;
    this.w2 = w2;
    this.h1 = h1;
    this.h2 = h2;
    this.r1 = r1;
    this.r2 = r2;
    this.g1 = g1;
    this.g2 = g2;
    this.b1 = b1;
    this.b2 = b2;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Type getType() {
    return this.type;
  }

  @Override
  public Type getShapeType() {
    return this.shapeType;
  }

  @Override
  public int getStartTime() {
    return this.t1;
  }

  @Override
  public int getEndTime() {
    return this.t2;
  }

  @Override
  public int getStartWidth() {
    return this.w1;
  }

  @Override
  public int getStartHeight() {
    return this.h1;
  }

  @Override
  public int getStartRed() {
    return this.r1;
  }

  @Override
  public int getStartGreen() {
    return this.g1;
  }

  @Override
  public int getStartBlue() {
    return this.b1;
  }

  @Override
  public int getStartX() {
    return this.x1;
  }

  @Override
  public int getStartY() {
    return this.y1;
  }

  @Override
  public int getEndWidth() {
    return this.w2;
  }

  @Override
  public int getEndHeight() {
    return this.h2;
  }

  @Override
  public int getEndRed() {
    return this.r2;
  }

  @Override
  public int getEndGreen() {
    return this.g2;
  }

  @Override
  public int getEndBlue() {
    return this.b2;
  }

  @Override
  public int getEndX() {
    return this.x2;
  }

  @Override
  public int getEndY() {
    return this.y2;
  }

  @Override
  public int pullFrame(int p1, int p2, int t1, int t2, int frame) {
    float pf;
    pf = p1 * ((float) (t2 - frame) / (float) (t2 - t1)) + p2 * ((float) (frame - t1) / (float)
            (t2 - t1));
    return (int) pf;
  }

  @Override
  public String getCurrentState() {
    if (this instanceof Move) {
      if (x1 == x2 && y1 == y2) {
        return "";
      } else {
        return String.format("%s moves from (%d, %d) to (%d, %d) from time %d to %d.\n",
                this.name,
                this.x1,
                this.y1,
                this.x2,
                this.y2,
                this.t1,
                this.t2);
      }
    } else if (this instanceof Resize) {
      return String.format("%s scales from width %d and height %d to width %d and "
                      + "height %d from time %d to %d.\n",
              this.name,
              this.w1,
              this.h1,
              this.w2,
              this.h2,
              this.t1,
              this.t2);
    } else if (this instanceof ChangeColor) {
      return String.format("%s changes color from (%d, %d, %d) to (%d, %d, %d) from time %d "
                      + "to %d.\n",
              this.name,
              this.r1,
              this.g1,
              this.b1,
              this.r2,
              this.g2,
              this.b2,
              this.t1,
              this.t2);
    } else {
      return "";
    }
  }
}
