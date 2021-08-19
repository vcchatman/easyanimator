package cs5004.animator.view;

import java.io.IOException;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.ControllerLayout;

/**
 * Class that constructors command line argument parser to take input from user or file to render an
 * animation.
 */
public final class EasyAnimator {

  /**
   * Parses command line arguments from user or file.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) throws IOException {
    String in = "";
    String out = "";
    String view = "";
    String speed = "";
    int i;
    for (i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          in = args[i + 1];
          break;
        case "-out":
          if (i == args.length - 1) {
            out = "";
          } else if (args[i + 1].charAt(0) == '-') {
            out = "";
          } else {
            out = args[i + 1];
          }
          break;
        case "-view":
          view = args[i + 1];
          break;
        case "-speed":
          if (i == args.length - 1) {
            speed = "";
          } else if (args[i + 1].charAt(0) == '-') {
            speed = "";
          } else {
            speed = args[i + 1];
          }
          break;
        default:
          break;
      }
    }

    ControllerLayout c = new Controller(in, out, view, speed);
    c.runAnimation();
  }
}