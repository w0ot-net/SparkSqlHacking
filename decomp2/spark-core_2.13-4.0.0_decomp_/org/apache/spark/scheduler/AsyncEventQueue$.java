package org.apache.spark.scheduler;

public final class AsyncEventQueue$ {
   public static final AsyncEventQueue$ MODULE$ = new AsyncEventQueue$();
   private static final SparkListenerEvent POISON_PILL = new SparkListenerEvent() {
      public boolean logEvent() {
         return SparkListenerEvent.logEvent$(this);
      }

      public {
         SparkListenerEvent.$init$(this);
      }
   };
   private static final int LOGGING_INTERVAL = 60000;

   public SparkListenerEvent POISON_PILL() {
      return POISON_PILL;
   }

   public int LOGGING_INTERVAL() {
      return LOGGING_INTERVAL;
   }

   private AsyncEventQueue$() {
   }
}
