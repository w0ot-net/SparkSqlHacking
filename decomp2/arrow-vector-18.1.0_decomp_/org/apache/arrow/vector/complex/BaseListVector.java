package org.apache.arrow.vector.complex;

import org.apache.arrow.vector.FieldVector;

public interface BaseListVector extends FieldVector {
   int getElementStartIndex(int var1);

   int getElementEndIndex(int var1);
}
