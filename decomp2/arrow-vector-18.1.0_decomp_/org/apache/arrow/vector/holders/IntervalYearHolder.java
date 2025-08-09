package org.apache.arrow.vector.holders;

public final class IntervalYearHolder implements ValueHolder {
   public static final int WIDTH = 4;
   public final int isSet = 1;
   public int value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
