package org.apache.arrow.vector.holders;

public final class IntervalDayHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public final int isSet = 1;
   public int days;
   public int milliseconds;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
