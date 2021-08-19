package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import cs5004.animator.model.AnimationImpl;

/**
 * Class that opens the display for an enhanced visual view of the animation with playback
 * features.
 */
public class EditorView extends JFrame implements Views {
  private int currentTick;
  private AnimationImpl model;
  private MainPanel panel;
  private String actionState;
  private int rate;
  private int loopStatus;

  /**
   * Constructor for the editor view which builds the frames, panels, and playback buttons.
   *
   * @param model model received from Animation impl that contains what will be displayed
   */
  public EditorView(AnimationImpl model) {
    this.model = model;
    this.currentTick = 0;
    this.actionState = "";
    this.rate = 1;
    this.loopStatus = 0;

    JFrame frame = new JFrame("EasyAnimator");

    // Playback buttons in the Playback Panel
    JButton start = new JButton("Start");
    start.setActionCommand("StartAnimation");
    start.addActionListener(new ButtonListener());

    JButton pause = new JButton("Pause");
    pause.setActionCommand("PauseAnimation");
    pause.addActionListener(new ButtonListener());

    JButton replay = new JButton("Replay");
    replay.setActionCommand("ReplayAnimation");
    replay.addActionListener(new ButtonListener());

    // Speed buttons in the Playback Panel
    JButton originalSpeed = new JButton("1x");
    originalSpeed.setActionCommand("original");
    originalSpeed.addActionListener(new SpeedListener());

    JButton doubleSpeed = new JButton("2x");
    doubleSpeed.setActionCommand("double");
    doubleSpeed.addActionListener(new SpeedListener());

    JButton quadrupleSpeed = new JButton("4x");
    quadrupleSpeed.setActionCommand("quadruple");
    quadrupleSpeed.addActionListener(new SpeedListener());

    JButton octupleSpeed = new JButton("8x");
    octupleSpeed.setActionCommand("octuple");
    octupleSpeed.addActionListener(new SpeedListener());

    JButton sixteenSpeed = new JButton("16x");
    sixteenSpeed.setActionCommand("sixteen");
    sixteenSpeed.addActionListener(new SpeedListener());

    JButton thirtytwoSpeed = new JButton("32x");
    thirtytwoSpeed.setActionCommand("thirtytwo");
    thirtytwoSpeed.addActionListener(new SpeedListener());

    // Looping option for Playback Panel
    JCheckBox loop = new JCheckBox("Loop");
    loop.addActionListener(new LoopSetter());

    // Playback Panel
    JPanel playbackPanel = new JPanel();
    playbackPanel.setBorder(BorderFactory.createTitledBorder("Playback Options"));
    playbackPanel.setPreferredSize(new Dimension(150, 1));
    playbackPanel.setBackground(Color.WHITE);
    playbackPanel.add(start);
    playbackPanel.add(pause);
    playbackPanel.add(replay);
    playbackPanel.add(originalSpeed);
    playbackPanel.add(doubleSpeed);
    playbackPanel.add(quadrupleSpeed);
    playbackPanel.add(octupleSpeed);
    playbackPanel.add(sixteenSpeed);
    playbackPanel.add(thirtytwoSpeed);
    playbackPanel.add(loop);

    panel = new MainPanel(this.model);
    panel.setBackground(Color.BLACK);

    JScrollPane scrollPane = new JScrollPane(panel);

    frame.add(playbackPanel, BorderLayout.AFTER_LINE_ENDS);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Gets the state that the animation can be in (playing, paused, or reset).
   *
   * @return
   */
  public String getActionState() {
    return this.actionState;
  }

  @Override
  public void render() {
    panel.animate(model.getShapesInFrame(currentTick));
  }

  @Override
  public void setCurrentFrame(int newTick) {
    this.currentTick = newTick;
  }

  /**
   * Sets the action state of the animation.
   *
   * @param state state that the animation can have
   */
  public void setActionState(String state) {
    this.actionState = state;
  }

  /**
   * Gets the speed at which the original playback speed will be multiplied by.
   *
   * @return new playback speed
   */
  public int getRate() {
    return this.rate;
  }

  /**
   * Gets looping status, a numerical boolean reflecting 0 for not looping and 1 for looping.
   *
   * @return numerical boolean 0 or 1
   */
  public int retLoopStatus() {
    return this.loopStatus;
  }

  /**
   * ActionListener class to set the loop status based on a button press.
   */
  public class LoopSetter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (loopStatus == 0) {
        loopStatus = 1;
      } else {
        loopStatus = 0;
      }
    }
  }

  /**
   * ActionListener class that causes playback changes in animation.
   */
  public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "StartAnimation":
          actionState = "started";
          break;
        case "PauseAnimation":
          actionState = "paused";
          break;
        case "ReplayAnimation":
          actionState = "reset";
          break;
        default:
          break;
      }
    }
  }

  /**
   * ActionListener class that changes the changes the original speed by a preset multiple.
   */
  public class SpeedListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "original":
          rate = 1;
          break;
        case "double":
          rate = 2;
          break;
        case "quadruple":
          rate = 4;
          break;
        case "octuple":
          rate = 8;
          break;
        case "sixteen":
          rate = 16;
          break;
        case "thirtytwo":
          rate = 32;
          break;
        default:
          break;
      }
    }
  }
}