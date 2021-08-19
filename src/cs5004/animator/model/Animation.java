package cs5004.animator.model;

import java.util.List;
import java.util.Map;

/**
 * Implementation of animation class.
 */
public interface Animation {

  /**
   * Sets the canvas on in which animations will be bound.
   *
   * @param x      x coordinate of canvas
   * @param y      y coordinate of canvas
   * @param width  width of canvas
   * @param height height of canvas
   */
  void addCanvas(int x, int y, int width, int height);

  /**
   * Adds a Transformation object to the array list and sorts the list but the start times of
   * each transformation.
   */
  void addAllTransformations();

  /**
   * Add Shape object to the shape hash map with shape's id as the key and shape object as the
   * value.
   *
   * @param id    unique id of shape
   * @param shape shape object
   */
  void addShape(String id, Shape shape);

  /**
   * Retrieves the shape hash map that is used by the AnimationImpl class.
   *
   * @return shape hash map object
   */
  Map<String, Shape> retHashMap();

  /**
   * Collects the defining properties of each shape along with a string description of each
   * of the transformations it has under gone, sorted chronologically by start time.
   *
   * @return a string description of each shape created and all of its transformations
   */
  String getTransformationState();

  /**
   * Retrieves transformation list from AnimationImpl class.
   *
   * @return transformation list
   */
  List<Transformation> retList();

  /**
   * Gets canvas object.
   *
   * @return canvas object
   */
  Canvas retCanvas();

  /**
   * Retrieves all shape objects at the requested animation frame adds them to an array list.
   *
   * @param frame number of frame
   * @return an array list holding all shapes at that frame in animation
   */
  List<Shape> getShapesInFrame(int frame);
}
