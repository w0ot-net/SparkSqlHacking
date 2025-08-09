package org.apache.arrow.vector.holders;

public final class TinyIntHolder implements ValueHolder {
   public static final int WIDTH = 1;
   public final int isSet = 1;
   public byte value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
