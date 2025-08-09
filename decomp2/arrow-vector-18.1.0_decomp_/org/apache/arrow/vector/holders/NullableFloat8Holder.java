package org.apache.arrow.vector.holders;

public final class NullableFloat8Holder implements ValueHolder {
   public static final int WIDTH = 8;
   public int isSet;
   public double value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
