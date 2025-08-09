package org.apache.arrow.vector.holders;

public final class NullableUInt1Holder implements ValueHolder {
   public static final int WIDTH = 1;
   public int isSet;
   public byte value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
