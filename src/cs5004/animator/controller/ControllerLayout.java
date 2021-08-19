package cs5004.animator.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.view.EditorView;

/**
 * Interface to tie all controllers under a common inheritance.
 */
public interface ControllerLayout {
  /**
   * Instantiate the model object that each of the View classes will use to render an animation.
   *
   * @return model object
   * @throws FileNotFoundException if in file is not found
   */
  AnimationImpl getModel() throws FileNotFoundException;

  /**
   * Triggers the rendering of type of view received from the command line.
   *
   * @throws IOException the out file cannot be found
   */
  void runAnimation() throws IOException;

  /**
   * Initiates the animation for the visual view classes if either is provided from the command
   * line.
   *
   * @param v visual view to be rendered
   */
  void generateVisual(EditorView v);
}
