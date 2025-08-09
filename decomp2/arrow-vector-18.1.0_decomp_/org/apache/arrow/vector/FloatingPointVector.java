package org.apache.arrow.vector;

public interface FloatingPointVector extends ValueVector {
   void setWithPossibleTruncate(int var1, double var2);

   void setSafeWithPossibleTruncate(int var1, double var2);

   double getValueAsDouble(int var1);
}
