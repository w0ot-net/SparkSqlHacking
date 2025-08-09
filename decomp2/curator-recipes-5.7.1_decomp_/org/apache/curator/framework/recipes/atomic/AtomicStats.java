package org.apache.curator.framework.recipes.atomic;

public class AtomicStats {
   private int optimisticTries = 0;
   private int promotedLockTries = 0;
   private long optimisticTimeMs = 0L;
   private long promotedTimeMs = 0L;

   public int getOptimisticTries() {
      return this.optimisticTries;
   }

   public int getPromotedLockTries() {
      return this.promotedLockTries;
   }

   public long getOptimisticTimeMs() {
      return this.optimisticTimeMs;
   }

   public long getPromotedTimeMs() {
      return this.promotedTimeMs;
   }

   void incrementOptimisticTries() {
      ++this.optimisticTries;
   }

   void incrementPromotedTries() {
      ++this.promotedLockTries;
   }

   void setOptimisticTimeMs(long optimisticTimeMs) {
      this.optimisticTimeMs = optimisticTimeMs;
   }

   void setPromotedTimeMs(long promotedTimeMs) {
      this.promotedTimeMs = promotedTimeMs;
   }
}
