package org.apache.arrow.vector;

public interface FixedWidthVector extends ElementAddressableVector {
   void allocateNew(int var1);

   void zeroVector();
}
