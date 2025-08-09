package org.apache.curator.framework.recipes.queue;

import java.util.concurrent.ThreadFactory;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

public class QueueSharderPolicies {
   private int newQueueThreshold;
   private int thresholdCheckMs;
   private int maxQueues;
   private ThreadFactory threadFactory;
   private static final int DEFAULT_QUEUE_THRESHOLD = 10000;
   private static final int DEFAULT_THRESHOLD_CHECK_MS = 30000;
   private static final int DEFAULT_MAX_QUEUES = 10;

   public static Builder builder() {
      return new Builder();
   }

   int getNewQueueThreshold() {
      return this.newQueueThreshold;
   }

   int getThresholdCheckMs() {
      return this.thresholdCheckMs;
   }

   int getMaxQueues() {
      return this.maxQueues;
   }

   ThreadFactory getThreadFactory() {
      return this.threadFactory;
   }

   private QueueSharderPolicies() {
      this.newQueueThreshold = 10000;
      this.thresholdCheckMs = 30000;
      this.maxQueues = 10;
      this.threadFactory = QueueBuilder.defaultThreadFactory;
   }

   public static class Builder {
      private QueueSharderPolicies policies;

      public Builder newQueueThreshold(int newQueueThreshold) {
         Preconditions.checkArgument(newQueueThreshold > 0, "newQueueThreshold must be a positive number");
         this.policies.newQueueThreshold = newQueueThreshold;
         return this;
      }

      public Builder thresholdCheckMs(int thresholdCheckMs) {
         Preconditions.checkArgument(thresholdCheckMs > 0, "thresholdCheckMs must be a positive number");
         this.policies.thresholdCheckMs = thresholdCheckMs;
         return this;
      }

      public Builder maxQueues(int maxQueues) {
         Preconditions.checkArgument(maxQueues > 0, "thresholdCheckMs must be a positive number");
         this.policies.maxQueues = maxQueues;
         return this;
      }

      public Builder threadFactory(ThreadFactory threadFactory) {
         this.policies.threadFactory = (ThreadFactory)Preconditions.checkNotNull(threadFactory, "threadFactory cannot be null");
         return this;
      }

      public QueueSharderPolicies build() {
         QueueSharderPolicies var1;
         try {
            var1 = this.policies;
         } finally {
            this.policies = new QueueSharderPolicies();
         }

         return var1;
      }

      private Builder() {
         this.policies = new QueueSharderPolicies();
      }
   }
}
