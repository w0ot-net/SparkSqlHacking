package org.apache.arrow.vector.holders;

public final class NullableBitHolder implements ValueHolder {
   public static final int WIDTH = 1;
   public int isSet;
   public int value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
