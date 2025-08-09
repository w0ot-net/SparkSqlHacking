package org.sparkproject.guava.hash;

@ElementTypesAreNonnullByDefault
interface LongAddable {
   void increment();

   void add(long x);

   long sum();
}
