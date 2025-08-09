package org.apache.arrow.vector.holders;

import org.apache.arrow.memory.ArrowBuf;

public final class FixedSizeBinaryHolder implements ValueHolder {
   public static final int WIDTH = -1;
   public final int isSet = 1;
   public ArrowBuf buffer;
   public int byteWidth;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
