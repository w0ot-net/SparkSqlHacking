package org.apache.arrow.memory;

import org.apache.arrow.memory.rounding.DefaultRoundingPolicy;
import org.apache.arrow.memory.rounding.RoundingPolicy;
import org.apache.arrow.util.VisibleForTesting;

public class RootAllocator extends BaseAllocator {
   public RootAllocator() {
      this(AllocationListener.NOOP, Long.MAX_VALUE);
   }

   public RootAllocator(long limit) {
      this(AllocationListener.NOOP, limit);
   }

   public RootAllocator(AllocationListener listener, long limit) {
      this(listener, limit, DefaultRoundingPolicy.DEFAULT_ROUNDING_POLICY);
   }

   public RootAllocator(AllocationListener listener, long limit, RoundingPolicy roundingPolicy) {
      this(configBuilder().listener(listener).maxAllocation(limit).roundingPolicy(roundingPolicy).build());
   }

   public RootAllocator(BaseAllocator.Config config) {
      super((BaseAllocator)null, "ROOT", config);
   }

   @VisibleForTesting
   public void verify() {
      this.verifyAllocator();
   }
}
