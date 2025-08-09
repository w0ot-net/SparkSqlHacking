package org.apache.arrow.vector.holders;

import org.apache.arrow.memory.ArrowBuf;

public final class Decimal256Holder implements ValueHolder {
   public static final int WIDTH = 32;
   public final int isSet = 1;
   public long start;
   public ArrowBuf buffer;
   public int scale;
   public int precision;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
