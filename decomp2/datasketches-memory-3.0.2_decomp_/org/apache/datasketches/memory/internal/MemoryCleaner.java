package org.apache.datasketches.memory.internal;

import sun.misc.Cleaner;

public class MemoryCleaner {
   private final Cleaner cleaner;

   public MemoryCleaner(Object referent, Runnable deallocator) {
      this.cleaner = Cleaner.create(referent, deallocator);
   }

   public void clean() {
      this.cleaner.clean();
   }
}
