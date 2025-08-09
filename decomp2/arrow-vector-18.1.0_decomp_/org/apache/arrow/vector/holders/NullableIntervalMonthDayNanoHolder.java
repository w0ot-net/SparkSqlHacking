package org.apache.arrow.vector.holders;

public final class NullableIntervalMonthDayNanoHolder implements ValueHolder {
   public static final int WIDTH = 16;
   public int isSet;
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
