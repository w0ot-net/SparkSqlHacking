package org.roaringbitmap;

public interface RelativeRangeConsumer {
   void acceptPresent(int var1);

   void acceptAbsent(int var1);

   void acceptAllPresent(int var1, int var2);

   void acceptAllAbsent(int var1, int var2);
}
