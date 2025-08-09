package org.apache.arrow.vector.complex;

public interface RepeatedVariableWidthVectorLike {
   void allocateNew(int var1, int var2, int var3);

   int getByteCapacity();
}
