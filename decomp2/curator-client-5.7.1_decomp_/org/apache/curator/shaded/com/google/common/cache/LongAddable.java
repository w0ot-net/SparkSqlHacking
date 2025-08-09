package org.apache.curator.shaded.com.google.common.cache;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
interface LongAddable {
   void increment();

   void add(long x);

   long sum();
}
