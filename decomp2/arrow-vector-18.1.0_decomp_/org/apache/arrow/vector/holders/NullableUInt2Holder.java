package org.apache.arrow.vector.holders;

public final class NullableUInt2Holder implements ValueHolder {
   public static final int WIDTH = 2;
   public int isSet;
   public char value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
