package cs5004.animator.view;

import java.io.IOException;
import java.io.PrintStream;

import cs5004.animator.model.Animation;

/**
 * A class to capture parsed data from a file and render it in a text description out to a file.
 */
public class TextView implements Views {
  private Animation model;
  private Appendable textData;
  private PrintStream doc;

  /**
   * Constructs a textual description of the animation built in the model.
   *
   * @param model AnimationImpl model that contains all shapes and transformations to be drawn
   * @param out   file name for text to be exported to
   * @throws IOException if the file does not exist
   */
  public TextView(Animation model, String out) throws IOException {
    this.model = model;
    this.doc = null;
    if (out.equals("") || out.equalsIgnoreCase("out")) {
      this.textData = new PrintStream(System.out);
    } else {
      doc = new PrintStream("./" + out);
    }
  }

  @Override
  public void render() {
    String helper = model.getTransformationState();
    if (doc != null) {
      doc.append(helper);
    } else {
      try {
        this.textData.append(helper);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void setCurrentFrame(int newTick) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This operation is not valid for this class.");
  }

}
