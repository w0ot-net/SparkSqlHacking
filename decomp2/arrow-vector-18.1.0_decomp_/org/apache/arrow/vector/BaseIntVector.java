package org.apache.arrow.vector;

public interface BaseIntVector extends FieldVector {
   void setWithPossibleTruncate(int var1, long var2);

   void setUnsafeWithPossibleTruncate(int var1, long var2);

   long getValueAsLong(int var1);
}
