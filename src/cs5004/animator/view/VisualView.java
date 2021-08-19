package cs5004.animator.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.model.AnimationImpl;

/**
 * Class to create simple visual view of animation.
 */
public class VisualView extends JFrame implements Views {
  private int currentTick;
  private MainPanel panel;
  private AnimationImpl model;

  /**
   * Constructor for Visual View object.
   *
   * @param model AnimationImpl model that contains the shapes and transformations to be rendered
   */
  public VisualView(AnimationImpl model) {
    this.model = model;
    this.currentTick = 0;

    JFrame frame = new JFrame("2D Animator!!!");

    panel = new MainPanel(this.model);
    panel.setBackground(Color.WHITE);

    JScrollPane scrollPane = new JScrollPane(panel);

    frame.add(scrollPane);
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void render() {
    panel.animate(model.getShapesInFrame(currentTick));
  }

  @Override
  public void setCurrentFrame(int newTick) {
    this.currentTick = newTick;
  }
}