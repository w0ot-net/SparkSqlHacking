package org.apache.arrow.vector.holders;

import org.apache.arrow.memory.ArrowBuf;

public final class NullableLargeVarCharHolder implements ValueHolder {
   public static final int WIDTH = 8;
   public int isSet;
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
