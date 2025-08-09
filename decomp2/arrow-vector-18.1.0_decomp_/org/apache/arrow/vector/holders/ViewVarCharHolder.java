package org.apache.arrow.vector.holders;

import org.apache.arrow.memory.ArrowBuf;

public final class ViewVarCharHolder implements ValueHolder {
   public static final int WIDTH = 4;
   public final int isSet = 1;
   public int start;
   public int end;
   public ArrowBuf buffer;

   public int hashCode() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      throw new UnsupportedOperationException();
   }
}
