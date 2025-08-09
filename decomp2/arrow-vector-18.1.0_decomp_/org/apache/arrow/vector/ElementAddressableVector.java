package org.apache.arrow.vector;

import org.apache.arrow.memory.util.ArrowBufPointer;

public interface ElementAddressableVector extends ValueVector {
   ArrowBufPointer getDataPointer(int var1);

   ArrowBufPointer getDataPointer(int var1, ArrowBufPointer var2);
}
