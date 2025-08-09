package org.apache.arrow.vector;

public interface VariableWidthVector extends ElementAddressableVector, DensityAwareVector {
   void allocateNew(long var1, int var3);

   void allocateNew(int var1);

   int getByteCapacity();

   int sizeOfValueBuffer();
}
