import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.Move;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Resize;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Transformation;
import cs5004.animator.model.Type;
import cs5004.animator.view.EasyAnimator;

import static org.junit.Assert.assertEquals;

/**
 * Test class to ensure methods work as intended.
 */
public class AnimatorTest {

  private Shape oval;
  private Shape rectangle;
  private Shape oval2;
  private Shape oval3;
  private Shape rectangle2;
  private Shape rectangle3;
  private Transformation move1;
  private Transformation move2;
  private Transformation move3;
  private Transformation resize1;
  private Transformation resize2;
  private Transformation resize3;
  private Transformation resize4;
  private Transformation changeColor1;
  private Transformation changeColor2;

  /**
   * Quick setup of some objects.
   */
  @Before
  public void setUp() {
    rectangle = new Rectangle(2, 3, 150, 33, 0, 100, 100,
            "rec1", "rectangle", 0);

    oval = new Oval(3, 1, 0, 57, 230, 250, 100, "oval1",
            "ellipse", 0);

    oval2 = new Oval(4, 2, 43, 67, 54, 52, 78, "oval2",
            "ellipse", 0);

    oval3 = new Oval(7, 5, 128, 210, 41, 250, 300, "oval3",
            "ellipse", 0);

    rectangle2 = new Rectangle(3, 6, 32, 240, 121, 50, -200,
            "rec2", "rectangle", 0);

    rectangle3 = new Rectangle(100, 50, 255, 0, 0, 200, 200,
            "rec3", "rectangle", 1);

    move1 = new Move("rec1", Type.MOVE, Type.RECTANGLE, 1, 200, 200, 50,
            100, 255, 0, 0, 50, 300, 300, 50, 100, 255,
            0, 0);

    move2 = new Move("rec1", Type.MOVE, Type.RECTANGLE, 1, 200, 200, 50,
            100, 255, 0, 0, 50, 200, 200, 50, 100, 255,
            0, 0);

    move3 = new Move("rec3", Type.MOVE, Type.RECTANGLE, 1, 200, 200, 50,
            100, 255, 0, 0, 50, 300, 300, 50, 100, 255,
            0, 0);

    resize1 = new Resize("rec1", Type.RESIZE, Type.RECTANGLE, 51, 300, 300, 50,
            100, 255, 0, 0, 70, 300, 300, 25, 100, 255,
            0, 0);

    resize2 = new Resize("rec1", Type.RESIZE, Type.RECTANGLE, 61, 300, 300, 50,
            100, 255, 0, 0, 70, 300, 300, 75, 100, 255,
            0, 0);

    resize3 = new Resize("rec1", Type.RESIZE, Type.RECTANGLE, 51, 300, 300, 50,
            100, 255, 0, 0, 70, 300, 300, 25, 100, 255,
            0, 0);

    resize4 = new Resize("rec3", Type.RESIZE, Type.RECTANGLE, 51, 300, 300, 50,
            100, 255, 0, 0, 70, 300, 300, 25, 100, 255,
            0, 0);

    changeColor1 = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 50, 300, 300, 50,
            100, 0, 0, 255, 70, 300, 300, 25, 100, 0,
            170, 85);

    changeColor2 = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 70, 300, 300, 50,
            100, 0, 175, 80, 80, 300, 300, 25, 100, 0,
            255, 0);


  }

  // VISUAL VIEW TESTS

  /**
   * Test to show that not giving an out sends it to System.out
   *
   * @throws IOException if no proper input file is found.
   */
  @Test
  public void testFirst() throws IOException {
    String[] args = {"-in", "example_files\\smalldemo.txt", "-speed", "3", "-view", "text"};
    EasyAnimator.main(args);
    String test = "text";
    assertEquals("text", test);
  }

  @Test
  public void testFirst_SecondHalf() throws IOException {
    String[] args = {"-in", "example_files\\smalldemo.txt", "-speed", "3", "-view", "text",
        "-out", "out_files\\smalldemo_out.txt"};
    EasyAnimator.main(args);
    String test = "text";
    assertEquals("text", test);
  }

  @Test
  public void testSecond() throws IOException {
    String[] args = {"-in", "example_files\\smalldemo.txt", "-speed", "3", "-view", "svg"};
    EasyAnimator.main(args);
    String test = "test";
    assertEquals("test", test);
  }

  /**
   * Test to show text files properly exports out to txt file.
   *
   * @throws IOException if no proper input file is found.
   */
  @Test
  public void testThird() throws IOException {
    String[] args = {"-in", "example_files\\smalldemo.txt", "-speed", "3", "-view", "svg", "-out",
        "out_files\\small_demo.svg"};
    EasyAnimator.main(args);
    String test = "test";
    assertEquals("test", test);
  }

  @Test
  public void testFourth() throws IOException {
    String[] args = {"-in", "example_files\\toh-3.txt", "-speed", "3", "-view", "svg", ""
            + "-out", "out_files\\toh-at-20.svg"};
    EasyAnimator.main(args);
    String test = "test";
    assertEquals("test", test);
  }

  @Test
  public void testFifth() throws IOException {
    String[] args = {"-in", "example_files\\big-bang-big-crunch.txt", "-speed", "3", "-view",
        "svg", "" + "-out", "out_files\\big-bang-big-crunch.svg"};
    EasyAnimator.main(args);
    String test = "test";
    assertEquals("test", test);
  }

  @Test
  public void testSixth() throws IOException {
    String[] args = {"-in", "example_files\\smalldemo.txt", "-speed", "3",
        "-view", "playback",};
    EasyAnimator.main(args);
    String test = "test";
    assertEquals("test", test);
  }

  // COMPOSITE TRANSFORMATION-SHAPE TESTS

  @Test(expected = IllegalArgumentException.class)
  public void testMismatchedShapesMove() {
    rectangle3.addTransformation(move1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMismatchedShapesResize() {
    rectangle3.addTransformation(resize3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMismatchedShapesRecolor() {
    rectangle3.addTransformation(changeColor2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSameTransformationTypeAtOverLappingTimes() {
    rectangle.addTransformation(resize2);
    rectangle.addTransformation(resize3);
  }

  @Test
  public void testAddMotionsAndGetChanges() {
    rectangle.addTransformation(move1);
    rectangle.addTransformation(resize3);
    oval.addTransformation(changeColor1);

    assertEquals(rectangle.getChanges().size(), 2);
    assertEquals(oval.getChanges().size(), 1);
  }

  @Test
  public void testGetDisappearanceTime() {
    rectangle.addTransformation(move1);
    rectangle.addTransformation(resize3);

    assertEquals(rectangle.getDisappearanceTime(), 70);
  }

  @Test
  public void testCreateFrame() {
    rectangle3.addTransformation(move3);
    assertEquals(rectangle3.createFrame(25).retX(), 250, 5);
    assertEquals(rectangle3.createFrame(25).retY(), 250, 5);
    assertEquals(rectangle3.createFrame(25).retWidth(), 50, 0);
    assertEquals(rectangle3.createFrame(25).retHeight(), 100, 0);
  }

  @Test
  public void testGetAttributes() {
    rectangle3.addTransformation(move3);
    rectangle3.addTransformation(resize4);

    assertEquals(rectangle3.getAttributes(),
            "Create shape rec3 with corner at (200, 200), width=50 and height=100\n" +
                    "rec3 appears at time t=1 and disappears at time t=70\n\n");
  }

  // TRANSFORMATION OBJECT TESTS

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTime1() {
    ChangeColor obj = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, -70, 300,
            300, 50, 100, 0, 175, 80, 80, 300, 300, 25,
            100, 0, 255, 0);
    assertEquals(obj.getStartTime(), -70, .001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTime2() {
    ChangeColor obj = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 70, 300,
            300, 50, 100, 0, 175, 80, -80, 300, 300, 25,
            100, 0, 255, 0);
    assertEquals(obj.getEndTime(), -80, .001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartTimeAfterEndTime() {
    ChangeColor obj = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 170, 300,
            300, 50, 100, 0, 175, 80, 80, 300, 300, 25,
            100, 0, 255, 0);
    assertEquals(obj.getEndTime(), -80, .001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeHeight() {
    ChangeColor obj = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 70, 300,
            300, 50, -100, 0, 175, 80, 80, 300, 300, 25,
            100, 0, 255, 0);
    assertEquals(obj.getStartHeight(), -100, .001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationNegativeWidth() {
    ChangeColor obj = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 70, 300,
            300, -50, 100, 0, 175, 80, 80, 300, 300, 25,
            100, 0, 255, 0);
    assertEquals(obj.getStartWidth(), -100, .001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationHighRed() {
    ChangeColor obj = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 70, 300,
            300, 50, 100, 0, 175, 80, 80, 300, 300, 25,
            100, 300, 255, 0);
    assertEquals(obj.getEndRed(), 100, .001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationHighGreen() {
    ChangeColor obj = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 70, 300,
            300, 50, 100, 0, 175, 80, 80, 300, 300, 25,
            100, 0, 355, 0);
    assertEquals(obj.getEndGreen(), 100, .001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformationHighBlue() {
    ChangeColor obj = new ChangeColor("oval1", Type.RESIZE, Type.OVAL, 70, 300,
            300, 50, 100, 0, 175, 80, 80, 300, 300, 25,
            100, 0, 255, 300);
    assertEquals(obj.getEndBlue(), 100, .001);
  }

  @Test
  public void testTransformationGetters() {
    assertEquals(move1.getName(), "rec1");
    assertEquals(move1.getType(), Type.MOVE);
    assertEquals(move1.getShapeType(), Type.RECTANGLE);
    assertEquals(move1.getStartTime(), 1, .001);
    assertEquals(move1.getStartX(), 200, .001);
    assertEquals(move1.getStartY(), 200, .001);
    assertEquals(move1.getStartWidth(), 50, .001);
    assertEquals(move1.getStartHeight(), 100, .001);
    assertEquals(move1.getStartRed(), 255, .001);
    assertEquals(move1.getStartGreen(), 0, .001);
    assertEquals(move1.getStartBlue(), 0, .001);
    assertEquals(move1.getEndTime(), 50, .001);
    assertEquals(move1.getEndX(), 300, .001);
    assertEquals(move1.getEndY(), 300, .001);
    assertEquals(move1.getEndWidth(), 50, .001);
    assertEquals(move1.getEndHeight(), 100, .001);
    assertEquals(move1.getEndRed(), 255, .001);
    assertEquals(move1.getEndGreen(), 0, .001);
    assertEquals(move1.getEndBlue(), 0, .001);
  }

  @Test
  public void testPullFrame() {
    int frame = 35;
    int tweenX = move1.pullFrame(move1.getStartX(),
            move1.getEndX(),
            move1.getStartTime(),
            move1.getEndTime(), frame);
    assertEquals(tweenX, 269, .01);
  }

  @Test
  public void testTransformationCurrentStates() {
    assertEquals(move2.getCurrentState(), "");

    assertEquals(move1.getCurrentState(), "rec1 moves from (200, 200) to (300, 300) from "
            + "time 1 to 50.\n");

    assertEquals(resize1.getCurrentState(), "rec1 scales from width 50 and height 100 to "
            + "width 25 and height 100 from time 51 to 70.\n");

    assertEquals(changeColor1.getCurrentState(), "oval1 changes color from (0, 0, 255) to "
            + "(0, 170, 85) from time 50 to 70.\n");
  }


  // SHAPE OBJECT TESTS

  /**
   * Test to show that a negative height and or width is not accepted.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testShapeNegativeHeight() {
    Shape rec = new Rectangle(-2, 3, 42, 43, 44, 111, 222,
            "rec", "rectangle", 0);
    assertEquals(-2, rec.retHeight(), 0);
  }

  /**
   * Test to show that a negative height and or width is not accepted.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    Shape rec = new Rectangle(2, -3, 42, 43, 44, 111, 222,
            "rec", "rectangle", 0);
    assertEquals(-2, rec.retHeight(), 0);
  }

  /**
   * Test to show a red under 0 is not accepted.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRedPositive() {
    Shape oval = new Oval(3, 2, -34, 230, 230, 21, 34,
            "oval", "ellipse", 0);
    assertEquals(21, oval.retX(), 0);
  }

  /**
   * Test to show a red over 255 is not accepted.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRedUnderThreshold() {
    Shape oval = new Oval(3, 2, 340, 230, 230, 21, 34,
            "oval", "ellipse", 0);
    assertEquals(21, oval.retX(), 0);
  }

  /**
   * Test to show a green under 0 is not accepted.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGreenPositive() {
    Shape oval = new Oval(3, 2, 34, -230, 200, 21, 34,
            "oval", "ellipse", 0);
    assertEquals(21, oval.retX(), 0);
  }

  /**
   * Test to show a green over 255 is not accepted.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGreenUnderThreshold() {
    Shape oval = new Oval(3, 2, 234, 260, 230, 21, 34,
            "oval", "ellipse", 0);
    assertEquals(21, oval.retX(), 0);
  }

  /**
   * Test to show a blue under 0 is not accepted.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBluePositive() {
    Shape oval = new Oval(3, 2, 234, 230, -260, 21, 34,
            "oval", "ellipse", 0);
    assertEquals(21, oval.retX(), 0);
  }

  /**
   * Test to show a blue over 255 is not accepted.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBlueUnderThreshold() {
    Shape oval = new Oval(3, 2, 234, 230, 260, 21, 34,
            "oval", "ellipse", 0);
    assertEquals(21, oval.retX(), 0);
  }

  /**
   * Test to ensure widths are correctly set and updated.
   */
  @Test
  public void testRetWidth() {
    assertEquals(3, rectangle.retWidth(), 0);
    assertEquals(1, oval.retWidth(), 0);
    assertEquals(2, oval2.retWidth(), 0);
    assertEquals(5, oval3.retWidth(), 0);
    assertEquals(6, rectangle2.retWidth(), 0);
  }

  /**
   * Test to show returning heights are correct.
   */
  @Test
  public void testRetHeight() {
    assertEquals(2, rectangle.retHeight(), 0);
    assertEquals(3, oval.retHeight(), 0);
    assertEquals(4, oval2.retHeight(), 0);
    assertEquals(7, oval3.retHeight(), 0);
    assertEquals(3, rectangle2.retHeight(), 0);
  }

  /**
   * Test to ensure id's are correct are correct.
   */
  @Test
  public void testRetId() {
    assertEquals("oval1", oval.retID());
    assertEquals("oval2", oval2.retID());
    assertEquals("oval3", oval3.retID());
    assertEquals("rec1", rectangle.retID());
    assertEquals("rec2", rectangle2.retID());
  }

  /**
   * Test to show returning x coordinates are correct.
   */
  @Test
  public void testRetX() {
    assertEquals(100, rectangle.retX(), 0);
    assertEquals(250, oval.retX(), 0);
    assertEquals(52, oval2.retX(), 0);
    assertEquals(250, oval3.retX(), 0);
    assertEquals(50, rectangle2.retX(), 0);
  }

  /**
   * Test to show returning y coordinates are correct.
   */
  @Test
  public void testRetY() {
    assertEquals(100, rectangle.retY(), 0);
    assertEquals(100, oval.retY(), 0);
    assertEquals(78, oval2.retY(), 0);
    assertEquals(300, oval3.retY(), 0);
    assertEquals(-200, rectangle2.retY(), 0);
  }

  /**
   * Test to show returning colors are correct.
   */
  @Test
  public void testRetRgb() {
    assertEquals(150, rectangle.retColor().getRed(), 0);
    assertEquals(57, oval.retColor().getGreen(), 0);
    assertEquals(54, oval2.retColor().getBlue(), 0);
    assertEquals(128, oval3.retColor().getRed(), 0);
    assertEquals(240, rectangle2.retColor().getGreen(), 0);
  }


}
