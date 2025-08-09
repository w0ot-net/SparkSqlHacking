package org.joda.time.tz;

public class ZoneInfoLogger {
   static ThreadLocal cVerbose = new ThreadLocal() {
      protected Boolean initialValue() {
         return Boolean.FALSE;
      }
   };

   public static boolean verbose() {
      return (Boolean)cVerbose.get();
   }

   public static void set(boolean var0) {
      cVerbose.set(var0);
   }
}
