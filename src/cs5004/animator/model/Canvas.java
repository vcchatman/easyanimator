package cs5004.animator.model;

/**
 * Class constructing the canvas within which all animations will be bound.
 */
public class Canvas extends AnimationImpl {

  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructor for canvas object.
   *
   * @param x      x-value of canvas
   * @param y      y-value of canvas
   * @param width  width of canvas
   * @param height height of canvas
   */
  public Canvas(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Getter for x-value.
   *
   * @return x-value
   */
  public int retX() {
    return this.x;
  }

  /**
   * Getter for y-value.
   *
   * @return y-value
   */
  public int retY() {
    return this.y;
  }

  /**
   * Getter for canvas width.
   *
   * @return canvas width
   */
  public int retWidth() {
    return this.width;
  }

  /**
   * Getter for canvas height.
   *
   * @return canvas height
   */
  public int retHeight() {
    return this.height;
  }
}
