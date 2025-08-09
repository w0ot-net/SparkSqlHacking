package org.apache.arrow.vector.holders;

public final class NullableTimeStampMilliHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public int isSet;
   public long value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
