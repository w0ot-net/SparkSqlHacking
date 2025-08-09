package org.apache.arrow.vector.holders;

public final class IntervalMonthDayNanoHolder implements ValueHolder {
   public static final int WIDTH = 16;
   public final int isSet = 1;
   public int months;
   public int days;
   public long nanoseconds;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
