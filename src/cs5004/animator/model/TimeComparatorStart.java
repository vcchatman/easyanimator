package cs5004.animator.model;

import java.util.Comparator;

/**
 * Comparator to sort Transformation objects by their start times, order chronological.
 */
public class TimeComparatorStart implements Comparator<Transformation> {
  public int compare(Transformation motion1, Transformation motion2) {
    return Integer.compare(motion1.getStartTime(), motion2.getStartTime());
  }
}
