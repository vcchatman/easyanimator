package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import javax.swing.JPanel;

import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.Shape;

/**
 * Main panel class where the animation happens.
 */
public class MainPanel extends JPanel {
  private AnimationImpl model;
  private Map<String, Shape> helper;

  /**
   * Constructor for the main panel where everything is painted.
   */
  public MainPanel(AnimationImpl model) {
    this.helper = new LinkedHashMap<>();
    this.model = model;

    this.setPreferredSize(new Dimension(this.model.retCanvas().retWidth(),
            this.model.retCanvas().retHeight()));
  }

  /**
   * Adds all shapes in given array list to a hash map which is then used in the repaint method
   * called so that all shapes in the hash map are drawn.
   *
   * @param shapeList array list of shapes that exist during the current frame tick
   */
  void animate(List<Shape> shapeList) {
    for (Shape each : shapeList) {
      this.helper.put(each.retID(), each);
    }
    repaint();
  }

  /**
   * Method to paint the current panel.
   *
   * @param g the graphics to be painted.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (Map.Entry<String, Shape> entry : helper.entrySet()) {
      Shape v = entry.getValue();
      if (v.retType().equals("rectangle")) {
        g2d.setColor(new Color(v.retColor().getRed(),
                v.retColor().getGreen(),
                v.retColor().getBlue()));
        g2d.fillRect(v.retX() - this.model.retCanvas().retX(),
                v.retY() - this.model.retCanvas().retY(),
                v.retWidth(),
                v.retHeight());
      } else {
        g2d.setColor(new Color(v.retColor().getRed(),
                v.retColor().getGreen(),
                v.retColor().getBlue()));
        g2d.fillOval(v.retX() - this.model.retCanvas().retX(),
                v.retY() - this.model.retCanvas().retY(),
                v.retWidth(),
                v.retHeight());
      }
    }
  }
}