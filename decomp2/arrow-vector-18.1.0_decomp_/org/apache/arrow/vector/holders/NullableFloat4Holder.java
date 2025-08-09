package org.apache.arrow.vector.holders;

public final class NullableFloat4Holder implements ValueHolder {
   public static final int WIDTH = 4;
   public int isSet;
   public float value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
