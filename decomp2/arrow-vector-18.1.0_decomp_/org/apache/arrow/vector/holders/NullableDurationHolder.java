package org.apache.arrow.vector.holders;

import org.apache.arrow.vector.types.TimeUnit;

public final class NullableDurationHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public int isSet;
   public long value;
   public TimeUnit unit;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
