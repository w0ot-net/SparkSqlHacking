package org.apache.curator.framework.recipes.cache;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

class OutstandingOps {
   private final AtomicReference completionProc;
   private final AtomicLong count = new AtomicLong(0L);
   private volatile boolean active = true;

   OutstandingOps(Runnable completionProc) {
      this.completionProc = new AtomicReference(completionProc);
   }

   void increment() {
      if (this.active) {
         this.count.incrementAndGet();
      }

   }

   void decrement() {
      if (this.active && this.count.decrementAndGet() == 0L) {
         Runnable proc = (Runnable)this.completionProc.getAndSet((Object)null);
         if (proc != null) {
            this.active = false;
            proc.run();
         }
      }

   }
}
