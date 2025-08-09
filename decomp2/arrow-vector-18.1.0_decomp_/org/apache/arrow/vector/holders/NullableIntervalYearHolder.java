package org.apache.arrow.vector.holders;

public final class NullableIntervalYearHolder implements ValueHolder {
   public static final int WIDTH = 4;
   public int isSet;
   public int value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
