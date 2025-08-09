package org.apache.commons.io.input;

import java.io.InputStream;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.output.QueueOutputStream;

public class QueueInputStream extends InputStream {
   private final BlockingQueue blockingQueue;
   private final long timeoutNanos;

   public static Builder builder() {
      return new Builder();
   }

   public QueueInputStream() {
      this(new LinkedBlockingQueue());
   }

   /** @deprecated */
   @Deprecated
   public QueueInputStream(BlockingQueue blockingQueue) {
      this(blockingQueue, Duration.ZERO);
   }

   private QueueInputStream(BlockingQueue blockingQueue, Duration timeout) {
      this.blockingQueue = (BlockingQueue)Objects.requireNonNull(blockingQueue, "blockingQueue");
      this.timeoutNanos = ((Duration)Objects.requireNonNull(timeout, "timeout")).toNanos();
   }

   BlockingQueue getBlockingQueue() {
      return this.blockingQueue;
   }

   Duration getTimeout() {
      return Duration.ofNanos(this.timeoutNanos);
   }

   public QueueOutputStream newQueueOutputStream() {
      return new QueueOutputStream(this.blockingQueue);
   }

   public int read() {
      try {
         Integer value = (Integer)this.blockingQueue.poll(this.timeoutNanos, TimeUnit.NANOSECONDS);
         return value == null ? -1 : 255 & value;
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         throw new IllegalStateException(e);
      }
   }

   public static class Builder extends AbstractStreamBuilder {
      private BlockingQueue blockingQueue = new LinkedBlockingQueue();
      private Duration timeout;

      public Builder() {
         this.timeout = Duration.ZERO;
      }

      public QueueInputStream get() {
         return new QueueInputStream(this.blockingQueue, this.timeout);
      }

      public Builder setBlockingQueue(BlockingQueue blockingQueue) {
         this.blockingQueue = (BlockingQueue)(blockingQueue != null ? blockingQueue : new LinkedBlockingQueue());
         return this;
      }

      public Builder setTimeout(Duration timeout) {
         if (timeout != null && timeout.toNanos() < 0L) {
            throw new IllegalArgumentException("timeout must not be negative");
         } else {
            this.timeout = timeout != null ? timeout : Duration.ZERO;
            return this;
         }
      }
   }
}
