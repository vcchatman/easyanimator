package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


import javax.swing.Timer;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.Shape;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.EditorView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.Views;
import cs5004.animator.view.VisualView;


/**
 * Controller class to intake parsed tokens from the command line and use them to begin the begin
 * and control the animation renderings.
 */
public class Controller implements ControllerLayout {
  private String in;
  private String out;
  private String view;
  private int speedTicks;
  private AnimationImpl model;
  private Views newView;
  private int originalSpeed;

  /**
   * Constructor for controller object.
   *
   * @param in    file to be read
   * @param out   file to be exported to, if necessary
   * @param view  type of view to be rendered
   * @param speed speed of the view, if necessary
   * @throws IOException if in file is not found.
   */
  public Controller(String in, String out, String view, String speed) throws IOException {
    this.in = in;
    this.out = out;
    this.view = view;
    this.model = getModel();

    if (speed.equals("")) {
      this.speedTicks = 1;
    } else {
      this.speedTicks = Integer.parseInt(speed);
    }

    originalSpeed = Integer.parseInt(speed);
  }

  @Override
  public AnimationImpl getModel() throws FileNotFoundException {
    FileReader reader = new FileReader(in);
    AnimationImpl.Builder build = new AnimationImpl.Builder();
    AnimationImpl model;
    model = (AnimationImpl) AnimationReader.parseFile(reader, build);
    return model;
  }

  @Override
  public void runAnimation() throws IOException {
    switch (view) {

      case "svg":
        SVGView svg = new SVGView(model, out);
        svg.render();
        break;

      case "text":
        TextView text = new TextView(model, out);
        text.render();
        break;

      case "visual":
        VisualView visual = new VisualView(model);
        Timer timer = new Timer(1000 / speedTicks, new AnimationListener(visual));
        timer.start();
        break;

      case "playback":
        EditorView playback = new EditorView(model);
        generateVisual(playback);
        break;

      default:
        break;
    }
  }

  @Override
  public void generateVisual(EditorView v) {
    AnimationListener framePlay = new AnimationListener(v);
    Timer timer = new Timer(1000 / speedTicks, framePlay);

    int endAnimation = 0;
    Map<String, Shape> shapes = model.retHashMap();
    for (Shape shape : shapes.values()) {
      if (shape.getDisappearanceTime() > endAnimation) {
        endAnimation = shape.getDisappearanceTime();
      }
    }

    while (true) {
      if (this.speedTicks != originalSpeed * v.getRate()) {
        setSpeedTicks(v.getRate());
        timer.setDelay(1000 / speedTicks);
        timer.restart();
      }

      int loopFlag = v.retLoopStatus();
      String action = v.getActionState();
      switch (action) {
        case "started":
          timer.start();
          break;
        case "paused":
          timer.stop();
          break;
        case "reset":
          framePlay.currentTick = 0;
          v.setActionState("started");
          break;
        default:
          break;
      }
      v.render();
      if (loopFlag == 1 && framePlay.currentTick == endAnimation) {
        framePlay.currentTick = 0;
        v.setActionState("started");
      }
    }
  }

  /**
   * ActionListener class to set and render the current animation frame.
   */
  public static class AnimationListener implements ActionListener {

    private int currentTick;
    private Views v;

    AnimationListener(Views v) {
      this.v = v;
      this.currentTick = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      v.setCurrentFrame(currentTick);
      v.render();
      currentTick++;
      v.setCurrentFrame(currentTick);
    }
  }

  protected void setSpeedTicks(int rate) {
    this.speedTicks = originalSpeed * rate;
  }
}