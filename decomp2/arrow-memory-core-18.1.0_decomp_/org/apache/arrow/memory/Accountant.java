package org.apache.arrow.memory;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.arrow.util.Preconditions;
import org.checkerframework.checker.nullness.qual.Nullable;

class Accountant implements AutoCloseable {
   protected final @Nullable Accountant parent;
   private final String name;
   protected final long reservation;
   private final AtomicLong peakAllocation = new AtomicLong();
   private final AtomicLong allocationLimit = new AtomicLong();
   private final AtomicLong locallyHeldMemory = new AtomicLong();

   public Accountant(@Nullable Accountant parent, String name, long reservation, long maxAllocation) {
      Preconditions.checkNotNull(name, "name must not be null");
      Preconditions.checkArgument(reservation >= 0L, "The initial reservation size must be non-negative.");
      Preconditions.checkArgument(maxAllocation >= 0L, "The maximum allocation limit must be non-negative.");
      Preconditions.checkArgument(reservation <= maxAllocation, "The initial reservation size must be <= the maximum allocation.");
      Preconditions.checkArgument(reservation == 0L || parent != null, "The root accountant can't reserve memory.");
      this.parent = parent;
      this.name = name;
      this.reservation = reservation;
      this.allocationLimit.set(maxAllocation);
      if (reservation != 0L) {
         Preconditions.checkArgument(parent != null, "parent must not be null");
         AllocationOutcome outcome = parent.allocateBytes(reservation);
         if (!outcome.isOk()) {
            throw new OutOfMemoryException(String.format("Failure trying to allocate initial reservation for Allocator. Attempted to allocate %d bytes.", reservation), outcome.getDetails());
         }
      }

   }

   AllocationOutcome allocateBytes(long size) {
      AllocationOutcome.Status status = this.allocateBytesInternal(size);
      if (status.isOk()) {
         return AllocationOutcome.SUCCESS_INSTANCE;
      } else {
         AllocationOutcomeDetails details = new AllocationOutcomeDetails();
         status = this.allocateBytesInternal(size, details);
         return new AllocationOutcome(status, details);
      }
   }

   private AllocationOutcome.Status allocateBytesInternal(long size, @Nullable AllocationOutcomeDetails details) {
      AllocationOutcome.Status status = this.allocate(size, true, false, details);
      if (!status.isOk()) {
         this.releaseBytes(size);
      }

      return status;
   }

   private AllocationOutcome.Status allocateBytesInternal(long size) {
      return this.allocateBytesInternal(size, (AllocationOutcomeDetails)null);
   }

   private void updatePeak() {
      long currentMemory = this.locallyHeldMemory.get();

      long previousPeak;
      do {
         previousPeak = this.peakAllocation.get();
      } while(currentMemory > previousPeak && !this.peakAllocation.compareAndSet(previousPeak, currentMemory));

   }

   public boolean forceAllocate(long size) {
      AllocationOutcome.Status outcome = this.allocate(size, true, true, (AllocationOutcomeDetails)null);
      return outcome.isOk();
   }

   private AllocationOutcome.Status allocate(long size, boolean incomingUpdatePeak, boolean forceAllocation, @Nullable AllocationOutcomeDetails details) {
      long oldLocal = this.locallyHeldMemory.getAndAdd(size);
      long newLocal = oldLocal + size;
      boolean overflow = ((oldLocal ^ newLocal) & (size ^ newLocal)) < 0L;
      long beyondReservation = newLocal - this.reservation;
      boolean beyondLimit = overflow || newLocal > this.allocationLimit.get();
      boolean updatePeak = forceAllocation || incomingUpdatePeak && !beyondLimit;
      if (details != null) {
         boolean allocationFailed = true;
         long allocatedLocal = 0L;
         if (!beyondLimit) {
            allocatedLocal = size - Math.min(beyondReservation, size);
            allocationFailed = false;
         }

         details.pushEntry(this, newLocal - size, size, allocatedLocal, allocationFailed);
      }

      AllocationOutcome.Status parentOutcome = AllocationOutcome.Status.SUCCESS;
      if (beyondReservation > 0L && this.parent != null) {
         long parentRequest = Math.min(beyondReservation, size);
         parentOutcome = this.parent.allocate(parentRequest, updatePeak, forceAllocation, details);
      }

      AllocationOutcome.Status finalOutcome;
      if (beyondLimit) {
         finalOutcome = AllocationOutcome.Status.FAILED_LOCAL;
      } else {
         finalOutcome = parentOutcome.isOk() ? AllocationOutcome.Status.SUCCESS : AllocationOutcome.Status.FAILED_PARENT;
      }

      if (updatePeak) {
         this.updatePeak();
      }

      return finalOutcome;
   }

   public void releaseBytes(long size) {
      long newSize = this.locallyHeldMemory.addAndGet(-size);
      Preconditions.checkArgument(newSize >= 0L, "Accounted size went negative.");
      long originalSize = newSize + size;
      if (originalSize > this.reservation && this.parent != null) {
         long possibleAmountToReleaseToParent = originalSize - this.reservation;
         long actualToReleaseToParent = Math.min(size, possibleAmountToReleaseToParent);
         this.parent.releaseBytes(actualToReleaseToParent);
      }

   }

   public boolean isOverLimit() {
      return this.getAllocatedMemory() > this.getLimit() || this.parent != null && this.parent.isOverLimit();
   }

   public void close() {
      if (this.parent != null) {
         this.parent.releaseBytes(this.reservation);
      }

   }

   public String getName() {
      return this.name;
   }

   public long getLimit() {
      return this.allocationLimit.get();
   }

   public long getInitReservation() {
      return this.reservation;
   }

   public void setLimit(long newLimit) {
      this.allocationLimit.set(newLimit);
   }

   public long getAllocatedMemory() {
      return this.locallyHeldMemory.get();
   }

   public long getPeakMemoryAllocation() {
      return this.peakAllocation.get();
   }

   public long getHeadroom() {
      long localHeadroom = this.allocationLimit.get() - this.locallyHeldMemory.get();
      if (this.parent == null) {
         return localHeadroom;
      } else {
         long reservedHeadroom = Math.max(0L, this.reservation - this.locallyHeldMemory.get());
         return Math.min(localHeadroom, this.parent.getHeadroom() + reservedHeadroom);
      }
   }
}
