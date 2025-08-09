package org.apache.logging.log4j.util;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Timer implements Serializable, StringBuilderFormattable {
   private static final long serialVersionUID = 9175191792439630013L;
   private final String name;
   private Status status;
   private long elapsedTime;
   private final int iterations;
   private static long NANO_PER_SECOND = 1000000000L;
   private static long NANO_PER_MINUTE;
   private static long NANO_PER_HOUR;
   private ThreadLocal startTime;

   public Timer(final String name) {
      this(name, 0);
   }

   public Timer(final String name, final int iterations) {
      this.startTime = new ThreadLocal() {
         protected Long initialValue() {
            return 0L;
         }
      };
      this.name = name;
      this.status = Timer.Status.Stopped;
      this.iterations = iterations > 0 ? iterations : 0;
   }

   public synchronized void start() {
      this.startTime.set(System.nanoTime());
      this.elapsedTime = 0L;
      this.status = Timer.Status.Started;
   }

   public synchronized void startOrResume() {
      if (this.status == Timer.Status.Stopped) {
         this.start();
      } else {
         this.resume();
      }

   }

   public synchronized String stop() {
      this.elapsedTime += System.nanoTime() - (Long)this.startTime.get();
      this.startTime.set(0L);
      this.status = Timer.Status.Stopped;
      return this.toString();
   }

   public synchronized void pause() {
      this.elapsedTime += System.nanoTime() - (Long)this.startTime.get();
      this.startTime.set(0L);
      this.status = Timer.Status.Paused;
   }

   public synchronized void resume() {
      this.startTime.set(System.nanoTime());
      this.status = Timer.Status.Started;
   }

   public String getName() {
      return this.name;
   }

   public long getElapsedTime() {
      return this.elapsedTime / 1000000L;
   }

   public long getElapsedNanoTime() {
      return this.elapsedTime;
   }

   public Status getStatus() {
      return this.status;
   }

   public String toString() {
      StringBuilder result = new StringBuilder();
      this.formatTo(result);
      return result.toString();
   }

   public void formatTo(final StringBuilder buffer) {
      buffer.append("Timer ").append(this.name);
      switch (this.status) {
         case Started:
            buffer.append(" started");
            break;
         case Paused:
            buffer.append(" paused");
            break;
         case Stopped:
            long nanoseconds = this.elapsedTime;
            long hours = nanoseconds / NANO_PER_HOUR;
            nanoseconds %= NANO_PER_HOUR;
            long minutes = nanoseconds / NANO_PER_MINUTE;
            nanoseconds %= NANO_PER_MINUTE;
            long seconds = nanoseconds / NANO_PER_SECOND;
            nanoseconds %= NANO_PER_SECOND;
            String elapsed = "";
            if (hours > 0L) {
               elapsed = elapsed + hours + " hours ";
            }

            if (minutes > 0L || hours > 0L) {
               elapsed = elapsed + minutes + " minutes ";
            }

            DecimalFormat numFormat = new DecimalFormat("#0");
            elapsed = elapsed + numFormat.format(seconds) + '.';
            numFormat = new DecimalFormat("000000000");
            elapsed = elapsed + numFormat.format(nanoseconds) + " seconds";
            buffer.append(" stopped. Elapsed time: ").append(elapsed);
            if (this.iterations > 0) {
               nanoseconds = this.elapsedTime / (long)this.iterations;
               hours = nanoseconds / NANO_PER_HOUR;
               nanoseconds %= NANO_PER_HOUR;
               minutes = nanoseconds / NANO_PER_MINUTE;
               nanoseconds %= NANO_PER_MINUTE;
               seconds = nanoseconds / NANO_PER_SECOND;
               nanoseconds %= NANO_PER_SECOND;
               elapsed = "";
               if (hours > 0L) {
                  elapsed = elapsed + hours + " hours ";
               }

               if (minutes > 0L || hours > 0L) {
                  elapsed = elapsed + minutes + " minutes ";
               }

               numFormat = new DecimalFormat("#0");
               elapsed = elapsed + numFormat.format(seconds) + '.';
               numFormat = new DecimalFormat("000000000");
               elapsed = elapsed + numFormat.format(nanoseconds) + " seconds";
               buffer.append(" Average per iteration: ").append(elapsed);
            }
            break;
         default:
            buffer.append(' ').append(this.status);
      }

   }

   public boolean equals(final Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Timer)) {
         return false;
      } else {
         Timer timer = (Timer)o;
         if (this.elapsedTime != timer.elapsedTime) {
            return false;
         } else if (this.startTime != timer.startTime) {
            return false;
         } else {
            if (this.name != null) {
               if (!this.name.equals(timer.name)) {
                  return false;
               }
            } else if (timer.name != null) {
               return false;
            }

            if (this.status != null) {
               if (!this.status.equals(timer.status)) {
                  return false;
               }
            } else if (timer.status != null) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int result = this.name != null ? this.name.hashCode() : 0;
      result = 29 * result + (this.status != null ? this.status.hashCode() : 0);
      long time = (Long)this.startTime.get();
      result = 29 * result + (int)(time ^ time >>> 32);
      result = 29 * result + (int)(this.elapsedTime ^ this.elapsedTime >>> 32);
      return result;
   }

   static {
      NANO_PER_MINUTE = NANO_PER_SECOND * 60L;
      NANO_PER_HOUR = NANO_PER_MINUTE * 60L;
   }

   public static enum Status {
      Started,
      Stopped,
      Paused;

      // $FF: synthetic method
      private static Status[] $values() {
         return new Status[]{Started, Stopped, Paused};
      }
   }
}
