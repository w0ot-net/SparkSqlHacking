package com.zaxxer.hikari.util;

import java.util.concurrent.TimeUnit;

public interface ClockSource {
   ClockSource INSTANCE = ClockSource.Factory.create();
   TimeUnit[] TIMEUNITS_DESCENDING = new TimeUnit[]{TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES, TimeUnit.SECONDS, TimeUnit.MILLISECONDS, TimeUnit.MICROSECONDS, TimeUnit.NANOSECONDS};
   String[] TIMEUNIT_DISPLAY_VALUES = new String[]{"ns", "µs", "ms", "s", "m", "h", "d"};

   long currentTime();

   long toMillis(long var1);

   long toNanos(long var1);

   long elapsedMillis(long var1);

   long elapsedMillis(long var1, long var3);

   long elapsedNanos(long var1);

   long elapsedNanos(long var1, long var3);

   long plusMillis(long var1, long var3);

   TimeUnit getSourceTimeUnit();

   String elapsedDisplayString(long var1, long var3);

   public static class Factory {
      private static ClockSource create() {
         String os = System.getProperty("os.name");
         return (ClockSource)("Mac OS X".equals(os) ? new MillisecondClockSource() : new NanosecondClockSource());
      }
   }

   public static final class MillisecondClockSource extends NanosecondClockSource {
      public long currentTime() {
         return System.currentTimeMillis();
      }

      public long elapsedMillis(long startTime) {
         return System.currentTimeMillis() - startTime;
      }

      public long elapsedMillis(long startTime, long endTime) {
         return endTime - startTime;
      }

      public long elapsedNanos(long startTime) {
         return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis() - startTime);
      }

      public long elapsedNanos(long startTime, long endTime) {
         return TimeUnit.MILLISECONDS.toNanos(endTime - startTime);
      }

      public long toMillis(long time) {
         return time;
      }

      public long toNanos(long time) {
         return TimeUnit.MILLISECONDS.toNanos(time);
      }

      public long plusMillis(long time, long millis) {
         return time + millis;
      }

      public TimeUnit getSourceTimeUnit() {
         return TimeUnit.MILLISECONDS;
      }
   }

   public static class NanosecondClockSource implements ClockSource {
      public long currentTime() {
         return System.nanoTime();
      }

      public long toMillis(long time) {
         return TimeUnit.NANOSECONDS.toMillis(time);
      }

      public long toNanos(long time) {
         return time;
      }

      public long elapsedMillis(long startTime) {
         return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
      }

      public long elapsedMillis(long startTime, long endTime) {
         return TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
      }

      public long elapsedNanos(long startTime) {
         return System.nanoTime() - startTime;
      }

      public long elapsedNanos(long startTime, long endTime) {
         return endTime - startTime;
      }

      public long plusMillis(long time, long millis) {
         return time + TimeUnit.MILLISECONDS.toNanos(millis);
      }

      public TimeUnit getSourceTimeUnit() {
         return TimeUnit.NANOSECONDS;
      }

      public String elapsedDisplayString(long startTime, long endTime) {
         long elapsedNanos = this.elapsedNanos(startTime, endTime);
         StringBuilder sb = new StringBuilder(elapsedNanos < 0L ? "-" : "");
         elapsedNanos = Math.abs(elapsedNanos);

         for(TimeUnit unit : TIMEUNITS_DESCENDING) {
            long converted = unit.convert(elapsedNanos, TimeUnit.NANOSECONDS);
            if (converted > 0L) {
               sb.append(converted).append(TIMEUNIT_DISPLAY_VALUES[unit.ordinal()]);
               elapsedNanos -= TimeUnit.NANOSECONDS.convert(converted, unit);
            }
         }

         return sb.toString();
      }
   }
}
