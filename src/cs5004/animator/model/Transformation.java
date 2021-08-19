package cs5004.animator.model;


/**
 * Interface for the abstract transformation class and concrete transformation classes: Resize,
 * Move, and Change Color. Included in cs5004.animator.model so that there will be a record of each
 * change that was made to an object that can be compared against the concrete Shape objects which
 * hold data of their original state.
 */
public interface Transformation {

  /**
   * Returns a statement of the shape object's initial properties relative to its current
   * properties.
   *
   * @return String statement showing the initial state and present state.
   */
  String getCurrentState();

  /**
   * Returns the Shape name of the Transformation object.
   *
   * @return name of shape
   */
  String getName();

  /**
   * Retrieves type of Transformation object.
   *
   * @return type of the transformation object
   */
  Type getType();

  /**
   * Retrieves the type of Shape object.
   *
   * @return the type of Shape object
   */
  Type getShapeType();

  /**
   * Returns start time of Transformation object.
   *
   * @return Transformation start time
   */
  int getStartTime();

  /**
   * Returns end time of Transformation object.
   *
   * @return Transformation end time
   */
  int getEndTime();

  /**
   * Gets destination width of transformation object.
   *
   * @return destination width of transformation object
   */
  int getStartWidth();

  /**
   * Gets destination height of transformation object.
   *
   * @return destination height of transformation object
   */
  int getStartHeight();

  /**
   * Gets destination red hue of transformation object.
   *
   * @return destination red hue of transformation object
   */
  int getStartRed();

  /**
   * Gets destination green hue of transformation object.
   *
   * @return destination green hye of transformation object
   */
  int getStartGreen();

  /**
   * Gets destination blue hue of transformation object.
   *
   * @return destination blue hue of transformation object
   */
  int getStartBlue();

  /**
   * Gets destination x-value of transformation object.
   *
   * @return destination width of transformation object
   */
  int getStartX();

  /**
   * Gets destination y-value of transformation object.
   *
   * @return destination y-value of transformation object
   */
  int getStartY();

  /**
   * Gets source width of transformation object.
   *
   * @return source width of transformation object
   */
  int getEndWidth();

  /**
   * Gets source height of transformation object.
   *
   * @return source height of transformation object
   */
  int getEndHeight();

  /**
   * Gets source red hue of transformation object.
   *
   * @return source red hue of transformation object
   */
  int getEndRed();

  /**
   * Gets source green hue of transformation object.
   *
   * @return source green hue of transformation object
   */
  int getEndGreen();

  /**
   * Gets source blue hue of transformation object.
   *
   * @return source blue hue of transformation object
   */
  int getEndBlue();

  /**
   * Gets source x-value of transformation object.
   *
   * @return source x-value of transformation object
   */
  int getEndX();

  /**
   * Gets source y-value of transformation object.
   *
   * @return source y-value of transformation object
   */
  int getEndY();

  /**
   * Takes a given property of a shape (e.g. x-value, y-value, red, green, width, etc.) and
   * calculates the level of that property that should exist at an exact frame within the shape's
   * overall animation.
   *
   * @param p1    starting value of the property selected
   * @param p2    ending value of the property selected
   * @param t1    start time of the transformation
   * @param t2    end time of the transformation
   * @param frame requested frame
   * @return tweened integer that represents current frame's numerical value of property selected
   */
  int pullFrame(int p1, int p2, int t1, int t2, int frame);
}
