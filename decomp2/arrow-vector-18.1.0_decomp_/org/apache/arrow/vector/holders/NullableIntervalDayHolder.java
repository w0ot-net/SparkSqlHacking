package org.apache.arrow.vector.holders;

public final class NullableIntervalDayHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public int isSet;
   public int days;
   public int milliseconds;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
