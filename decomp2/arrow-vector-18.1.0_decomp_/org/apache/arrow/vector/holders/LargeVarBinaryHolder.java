package org.apache.arrow.vector.holders;

import org.apache.arrow.memory.ArrowBuf;

public final class LargeVarBinaryHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public final int isSet = 1;
   public long start;
   public long end;
   public ArrowBuf buffer;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
