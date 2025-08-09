package org.sparkproject.guava.cache;

import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
interface LongAddable {
   void increment();

   void add(long x);

   long sum();
}
