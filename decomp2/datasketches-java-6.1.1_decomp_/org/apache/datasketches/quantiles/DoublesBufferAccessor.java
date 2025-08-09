package org.apache.datasketches.quantiles;

abstract class DoublesBufferAccessor {
   abstract double get(int var1);

   abstract double set(int var1, double var2);

   abstract int numItems();

   abstract double[] getArray(int var1, int var2);

   abstract void putArray(double[] var1, int var2, int var3, int var4);
}
