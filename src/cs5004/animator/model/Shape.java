package cs5004.animator.model;

import java.awt.Color;
import java.util.List;

/**
 * Interface for Shape objects.
 */
public interface Shape {

  /**
   * Method to return the Shape personal id.
   *
   * @return String of object's id.
   */
  String retID();

  /**
   * Method to return shape's height.
   *
   * @return int of the Shape current height.
   */
  int retHeight();

  /**
   * Method to return the shape's width.
   *
   * @return int of the shape's current width.
   */
  int retWidth();

  /**
   * Method to return the shape's x coordinate.
   *
   * @return int of the shape's current x coordinate.
   */
  int retX();

  /**
   * Method to return the shape's y coordinate.
   *
   * @return int of the shape's current y coordinate.
   */
  int retY();

  /**
   * Method to return a shape's color.
   *
   * @return the actually color of the shape. Use built in get method to retrieve rgb values.
   */
  Color retColor();

  /**
   * Gets type of the shape.
   *
   * @return type of the shape.
   */
  String retType();

  /**
   * Gets appearance time of the shape.
   *
   * @return appearance time of the shape.
   */
  int getAppearanceTime();

  /**
   * Sets disappearance time of the shape.
   */
  void setDisappearanceTime();

  /**
   * Gets disappearance time of the shape.
   *
   * @return disappearance time of the shape.
   */
  int getDisappearanceTime();

  /**
   * Gets string value representation of the original shape and its properties.
   *
   * @return string description
   */
  String getAttributes();

  /**
   * Gets a list of changes the original shape object has undergone.
   *
   * @return list of shape objects
   */
  List<Transformation> getChanges();

  /**
   * Adds a transformation object to the Shape object's changes array.
   *
   * @param motion transformation object
   */
  void addTransformation(Transformation motion);

  /**
   * Creates a Shape object based off of the tweened properties of itself extracted from its
   * transformations at frame time.
   *
   * @param frame the specific frame of the animation in play
   * @return a tweened Shape
   */
  Shape createFrame(int frame);

}
