package org.sparkproject.guava.util.concurrent;

import org.sparkproject.guava.annotations.GwtCompatible;

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
