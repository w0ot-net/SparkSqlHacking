package org.apache.arrow.memory;

import java.util.Collection;
import org.apache.arrow.memory.rounding.DefaultRoundingPolicy;
import org.apache.arrow.memory.rounding.RoundingPolicy;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface BufferAllocator extends AutoCloseable {
   ArrowBuf buffer(long var1);

   ArrowBuf buffer(long var1, BufferManager var3);

   BufferAllocator getRoot();

   BufferAllocator newChildAllocator(String var1, long var2, long var4);

   BufferAllocator newChildAllocator(String var1, AllocationListener var2, long var3, long var5);

   void close();

   long getAllocatedMemory();

   long getLimit();

   long getInitReservation();

   void setLimit(long var1);

   long getPeakMemoryAllocation();

   long getHeadroom();

   boolean forceAllocate(long var1);

   void releaseBytes(long var1);

   AllocationListener getListener();

   @Nullable BufferAllocator getParentAllocator();

   Collection getChildAllocators();

   AllocationReservation newReservation();

   ArrowBuf getEmpty();

   String getName();

   boolean isOverLimit();

   String toVerboseString();

   void assertOpen();

   default RoundingPolicy getRoundingPolicy() {
      return DefaultRoundingPolicy.DEFAULT_ROUNDING_POLICY;
   }

   default ArrowBuf wrapForeignAllocation(ForeignAllocation allocation) {
      throw new UnsupportedOperationException();
   }
}
