package org.apache.arrow.vector.holders;

public final class SmallIntHolder implements ValueHolder {
   public static final int WIDTH = 2;
   public final int isSet = 1;
   public short value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
