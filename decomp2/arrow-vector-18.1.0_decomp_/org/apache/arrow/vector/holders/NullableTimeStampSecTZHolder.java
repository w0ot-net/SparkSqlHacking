package org.apache.arrow.vector.holders;

public final class NullableTimeStampSecTZHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public int isSet;
   public long value;
   public String timezone;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
