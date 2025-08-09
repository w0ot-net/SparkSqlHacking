package org.apache.curator.shaded.com.google.common.util.concurrent;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Runnables {
   private static final Runnable EMPTY_RUNNABLE = new Runnable() {
      public void run() {
      }
   };

   public static Runnable doNothing() {
      return EMPTY_RUNNABLE;
   }

   private Runnables() {
   }
}
