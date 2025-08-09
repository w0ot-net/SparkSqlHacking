package org.apache.arrow.vector.holders;

public final class TimeStampMilliHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public final int isSet = 1;
   public long value;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
