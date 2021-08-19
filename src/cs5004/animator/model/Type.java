package cs5004.animator.model;

/**
 * Enum class to specify the types of Transformation objects that are allowed.
 */
public enum Type {
  RESIZE("resize"),
  CHANGECOLOR("recolor"),
  MOVE("move"),
  RECTANGLE("rectangle"),
  OVAL("ellipse");

  private final String type;

  Type(String type) {
    this.type = type;
  }

  /**
   * Getter for the type of Transformation object in String form.
   *
   * @return string name of Type of Transformation object.
   */
  protected String getType() {
    return this.type;
  }
}
