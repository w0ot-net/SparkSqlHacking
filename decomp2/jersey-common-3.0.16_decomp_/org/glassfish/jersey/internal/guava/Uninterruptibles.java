package org.glassfish.jersey.internal.guava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

final class Uninterruptibles {
   private Uninterruptibles() {
   }

   public static Object getUninterruptibly(Future future) throws ExecutionException {
      boolean interrupted = false;

      try {
         while(true) {
            try {
               Object var2 = future.get();
               return var2;
            } catch (InterruptedException var6) {
               interrupted = true;
            }
         }
      } finally {
         if (interrupted) {
            Thread.currentThread().interrupt();
         }

      }
   }
}
