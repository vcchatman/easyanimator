package cs5004.animator.model;

import java.util.Comparator;

/**
 * Comparator to sort Transformation objects by end times, order reverse chronological.
 */
public class TimeComparatorEnd implements Comparator<Transformation> {
  public int compare(Transformation motion1, Transformation motion2) {
    return Integer.compare(motion2.getEndTime(), motion1.getEndTime());
  }
}
