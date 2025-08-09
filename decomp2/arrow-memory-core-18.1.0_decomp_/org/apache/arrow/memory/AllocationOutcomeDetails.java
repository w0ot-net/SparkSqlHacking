package org.apache.arrow.memory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.Nullable;

public class AllocationOutcomeDetails {
   Deque allocEntries = new ArrayDeque();

   AllocationOutcomeDetails() {
   }

   void pushEntry(Accountant accountant, long totalUsedBeforeAllocation, long requestedSize, long allocatedSize, boolean allocationFailed) {
      Entry top = (Entry)this.allocEntries.peekLast();
      if (top == null || !top.allocationFailed) {
         this.allocEntries.addLast(new Entry(accountant, totalUsedBeforeAllocation, requestedSize, allocatedSize, allocationFailed));
      }
   }

   public @Nullable BufferAllocator getFailedAllocator() {
      Entry top = (Entry)this.allocEntries.peekLast();
      return top != null && top.allocationFailed && top.accountant instanceof BufferAllocator ? (BufferAllocator)top.accountant : null;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Allocation outcome details:\n");
      Deque var10000 = this.allocEntries;
      Objects.requireNonNull(sb);
      var10000.forEach(sb::append);
      return sb.toString();
   }

   public static class Entry {
      private final Accountant accountant;
      private final long limit;
      private final long used;
      private final long requestedSize;
      private final long allocatedSize;
      private final boolean allocationFailed;

      Entry(Accountant accountant, long totalUsedBeforeAllocation, long requestedSize, long allocatedSize, boolean allocationFailed) {
         this.accountant = accountant;
         this.limit = accountant.getLimit();
         this.used = totalUsedBeforeAllocation;
         this.requestedSize = requestedSize;
         this.allocatedSize = allocatedSize;
         this.allocationFailed = allocationFailed;
      }

      public Accountant getAccountant() {
         return this.accountant;
      }

      public long getLimit() {
         return this.limit;
      }

      public long getUsed() {
         return this.used;
      }

      public long getRequestedSize() {
         return this.requestedSize;
      }

      public long getAllocatedSize() {
         return this.allocatedSize;
      }

      public boolean isAllocationFailed() {
         return this.allocationFailed;
      }

      public String toString() {
         String var10000 = this.accountant.getName();
         return "allocator[" + var10000 + "] reservation: " + this.accountant.getInitReservation() + " limit: " + this.limit + " used: " + this.used + " requestedSize: " + this.requestedSize + " allocatedSize: " + this.allocatedSize + " localAllocationStatus: " + (this.allocationFailed ? "fail" : "success") + "\n";
      }
   }
}
