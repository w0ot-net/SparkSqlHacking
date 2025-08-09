package org.apache.arrow.vector.holders;

public final class NullableSmallIntHolder implements ValueHolder {
   public static final int WIDTH = 2;
   public int isSet;
   public short value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
