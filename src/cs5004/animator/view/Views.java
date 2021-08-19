package cs5004.animator.view;

/**
 * Interface for all types of views that will be displayed.
 */
public interface Views {

  /**
   * Renders the animation, whether visual or text.
   */
  void render();

  /**
   * Sets the current frame of the animation to the one indicated.
   *
   * @param newTick frame within the animation
   */
  void setCurrentFrame(int newTick);
}