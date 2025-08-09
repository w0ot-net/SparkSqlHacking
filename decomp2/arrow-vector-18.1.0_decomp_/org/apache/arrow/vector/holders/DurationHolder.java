package org.apache.arrow.vector.holders;

import org.apache.arrow.vector.types.TimeUnit;

public final class DurationHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public final int isSet = 1;
   public long value;
   public TimeUnit unit;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
