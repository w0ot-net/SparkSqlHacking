package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public final class ThrottledInputStream extends CountingInputStream {
   private final long maxBytesPerSecond;
   private final long startTime;
   private Duration totalSleepDuration;

   public static Builder builder() {
      return new Builder();
   }

   static long toSleepMillis(long bytesRead, long maxBytesPerSec, long elapsedMillis) {
      if (elapsedMillis < 0L) {
         throw new IllegalArgumentException("The elapsed time should be greater or equal to zero");
      } else if (bytesRead > 0L && maxBytesPerSec > 0L && elapsedMillis != 0L) {
         long millis = (long)((double)bytesRead / (double)maxBytesPerSec * (double)1000.0F - (double)elapsedMillis);
         return millis <= 0L ? 0L : millis;
      } else {
         return 0L;
      }
   }

   private ThrottledInputStream(Builder builder) throws IOException {
      super((ProxyInputStream.AbstractBuilder)builder);
      this.startTime = System.currentTimeMillis();
      this.totalSleepDuration = Duration.ZERO;
      if (builder.maxBytesPerSecond <= 0L) {
         throw new IllegalArgumentException("Bandwidth " + builder.maxBytesPerSecond + " is invalid.");
      } else {
         this.maxBytesPerSecond = builder.maxBytesPerSecond;
      }
   }

   protected void beforeRead(int n) throws IOException {
      this.throttle();
   }

   private long getBytesPerSecond() {
      long elapsedSeconds = (System.currentTimeMillis() - this.startTime) / 1000L;
      return elapsedSeconds == 0L ? this.getByteCount() : this.getByteCount() / elapsedSeconds;
   }

   private long getSleepMillis() {
      return toSleepMillis(this.getByteCount(), this.maxBytesPerSecond, System.currentTimeMillis() - this.startTime);
   }

   Duration getTotalSleepDuration() {
      return this.totalSleepDuration;
   }

   private void throttle() throws InterruptedIOException {
      long sleepMillis = this.getSleepMillis();
      if (sleepMillis > 0L) {
         this.totalSleepDuration = this.totalSleepDuration.plus(sleepMillis, ChronoUnit.MILLIS);

         try {
            TimeUnit.MILLISECONDS.sleep(sleepMillis);
         } catch (InterruptedException var4) {
            throw new InterruptedIOException("Thread aborted");
         }
      }

   }

   public String toString() {
      return "ThrottledInputStream[bytesRead=" + this.getByteCount() + ", maxBytesPerSec=" + this.maxBytesPerSecond + ", bytesPerSec=" + this.getBytesPerSecond() + ", totalSleepDuration=" + this.totalSleepDuration + ']';
   }

   public static class Builder extends ProxyInputStream.AbstractBuilder {
      private long maxBytesPerSecond = Long.MAX_VALUE;

      public ThrottledInputStream get() throws IOException {
         return new ThrottledInputStream(this);
      }

      public void setMaxBytesPerSecond(long maxBytesPerSecond) {
         this.maxBytesPerSecond = maxBytesPerSecond;
      }
   }
}
