package org.apache.curator.shaded.com.google.common.base;

import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class Ticker {
   private static final Ticker SYSTEM_TICKER = new Ticker() {
      public long read() {
         return System.nanoTime();
      }
   };

   protected Ticker() {
   }

   public abstract long read();

   public static Ticker systemTicker() {
      return SYSTEM_TICKER;
   }
}
