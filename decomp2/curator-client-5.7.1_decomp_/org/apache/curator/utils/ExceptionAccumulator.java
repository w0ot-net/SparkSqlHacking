package org.apache.curator.utils;

import org.apache.curator.shaded.com.google.common.base.Throwables;

public class ExceptionAccumulator {
   private volatile Throwable mainEx = null;

   public void propagate() {
      if (this.mainEx != null) {
         Throwables.propagate(this.mainEx);
      }

   }

   public void add(Throwable e) {
      if (e instanceof InterruptedException) {
         if (this.mainEx != null) {
            e.addSuppressed(this.mainEx);
         }

         Thread.currentThread().interrupt();
      }

      if (this.mainEx == null) {
         this.mainEx = e;
      } else {
         this.mainEx.addSuppressed(e);
      }

   }
}
