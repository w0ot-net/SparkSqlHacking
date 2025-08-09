package org.apache.arrow.vector.holders;

public final class TimeStampMicroTZHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public final int isSet = 1;
   public long value;
   public String timezone;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
