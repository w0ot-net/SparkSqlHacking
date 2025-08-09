package org.apache.curator.shaded.com.google.common.hash;

@ElementTypesAreNonnullByDefault
interface LongAddable {
   void increment();

   void add(long x);

   long sum();
}
