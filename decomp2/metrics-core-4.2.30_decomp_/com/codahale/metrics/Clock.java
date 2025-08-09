package com.codahale.metrics;

public abstract class Clock {
   public abstract long getTick();

   public long getTime() {
      return System.currentTimeMillis();
   }

   public static Clock defaultClock() {
      return Clock.UserTimeClockHolder.DEFAULT;
   }

   public static class UserTimeClock extends Clock {
      public long getTick() {
         return System.nanoTime();
      }
   }

   private static class UserTimeClockHolder {
      private static final Clock DEFAULT = new UserTimeClock();
   }
}
