package org.apache.arrow.memory;

import java.util.IdentityHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.arrow.memory.util.CommonUtil;
import org.apache.arrow.memory.util.HistoricalLog;
import org.apache.arrow.util.Preconditions;
import org.checkerframework.checker.nullness.qual.Nullable;

public class BufferLedger implements ValueWithKeyIncluded, ReferenceManager {
   private final @Nullable IdentityHashMap buffers;
   private static final AtomicLong LEDGER_ID_GENERATOR = new AtomicLong(0L);
   private final long ledgerId;
   private final AtomicInteger bufRefCnt;
   private final long lCreationTime;
   private final BufferAllocator allocator;
   private final AllocationManager allocationManager;
   private final @Nullable HistoricalLog historicalLog;
   private volatile long lDestructionTime;

   BufferLedger(BufferAllocator allocator, AllocationManager allocationManager) {
      this.buffers = BaseAllocator.DEBUG ? new IdentityHashMap() : null;
      this.ledgerId = LEDGER_ID_GENERATOR.incrementAndGet();
      this.bufRefCnt = new AtomicInteger(0);
      this.lCreationTime = System.nanoTime();
      this.historicalLog = BaseAllocator.DEBUG ? new HistoricalLog(6, "BufferLedger[%d]", new Object[]{1}) : null;
      this.lDestructionTime = 0L;
      this.allocator = allocator;
      this.allocationManager = allocationManager;
   }

   boolean isOwningLedger() {
      return this == this.allocationManager.getOwningLedger();
   }

   public BufferAllocator getKey() {
      return this.allocator;
   }

   public BufferAllocator getAllocator() {
      return this.allocator;
   }

   public int getRefCount() {
      return this.bufRefCnt.get();
   }

   void increment() {
      this.bufRefCnt.incrementAndGet();
   }

   public boolean release() {
      return this.release(1);
   }

   public boolean release(int decrement) {
      Preconditions.checkState(decrement >= 1, "ref count decrement should be greater than or equal to 1");
      int refCnt = this.decrement(decrement);
      if (this.historicalLog != null) {
         this.historicalLog.recordEvent("release(%d). original value: %d", decrement, refCnt + decrement);
      }

      Preconditions.checkState(refCnt >= 0, "RefCnt has gone negative");
      return refCnt == 0;
   }

   private int decrement(int decrement) {
      this.allocator.assertOpen();
      synchronized(this.allocationManager) {
         int outcome = this.bufRefCnt.addAndGet(-decrement);
         if (outcome == 0) {
            this.lDestructionTime = System.nanoTime();
            this.allocationManager.release(this);
         }

         return outcome;
      }
   }

   public void retain() {
      this.retain(1);
   }

   public void retain(int increment) {
      Preconditions.checkArgument(increment > 0, "retain(%s) argument is not positive", increment);
      if (this.historicalLog != null) {
         this.historicalLog.recordEvent("retain(%d)", increment);
      }

      int originalReferenceCount = this.bufRefCnt.getAndAdd(increment);
      Preconditions.checkArgument(originalReferenceCount > 0);
   }

   public ArrowBuf deriveBuffer(ArrowBuf sourceBuffer, long index, long length) {
      long derivedBufferAddress = sourceBuffer.memoryAddress() + index;
      ArrowBuf derivedBuf = new ArrowBuf(this, (BufferManager)null, length, derivedBufferAddress);
      return this.loggingArrowBufHistoricalLog(derivedBuf);
   }

   ArrowBuf newArrowBuf(long length, @Nullable BufferManager manager) {
      this.allocator.assertOpen();
      long startAddress = this.allocationManager.memoryAddress();
      ArrowBuf buf = new ArrowBuf(this, manager, length, startAddress);
      return this.loggingArrowBufHistoricalLog(buf);
   }

   private ArrowBuf loggingArrowBufHistoricalLog(ArrowBuf buf) {
      if (this.historicalLog != null) {
         this.historicalLog.recordEvent("ArrowBuf(BufferLedger, BufferAllocator[%s], UnsafeDirectLittleEndian[identityHashCode == %d](%s)) => ledger hc == %d", this.allocator.getName(), System.identityHashCode(buf), buf.toString(), System.identityHashCode(this));
         Preconditions.checkState(this.buffers != null, "IdentityHashMap of buffers must not be null");
         synchronized(this.buffers) {
            this.buffers.put(buf, (Object)null);
         }
      }

      return buf;
   }

   public ArrowBuf retain(ArrowBuf srcBuffer, BufferAllocator target) {
      if (this.historicalLog != null) {
         this.historicalLog.recordEvent("retain(%s)", target.getName());
      }

      BufferLedger targetRefManager = this.allocationManager.associate(target);
      long targetBufLength = srcBuffer.capacity();
      ArrowBuf targetArrowBuf = targetRefManager.deriveBuffer(srcBuffer, 0L, targetBufLength);
      targetArrowBuf.readerIndex(srcBuffer.readerIndex());
      targetArrowBuf.writerIndex(srcBuffer.writerIndex());
      return targetArrowBuf;
   }

   boolean transferBalance(@Nullable ReferenceManager targetReferenceManager) {
      Preconditions.checkArgument(targetReferenceManager != null, "Expecting valid target reference manager");
      boolean overlimit = false;
      if (targetReferenceManager != null) {
         BufferAllocator targetAllocator = targetReferenceManager.getAllocator();
         Preconditions.checkArgument(this.allocator.getRoot() == targetAllocator.getRoot(), "You can only transfer between two allocators that share the same root.");
         this.allocator.assertOpen();
         targetReferenceManager.getAllocator().assertOpen();
         if (targetReferenceManager == this) {
            return true;
         }

         synchronized(this.allocationManager) {
            if (this.allocationManager.getOwningLedger() != this) {
               return true;
            }

            if (BaseAllocator.DEBUG && this.historicalLog != null) {
               this.historicalLog.recordEvent("transferBalance(%s)", targetReferenceManager.getAllocator().getName());
            }

            overlimit = targetAllocator.forceAllocate(this.allocationManager.getSize());
            this.allocator.releaseBytes(this.allocationManager.getSize());
            this.allocationManager.setOwningLedger((BufferLedger)targetReferenceManager);
         }
      }

      return overlimit;
   }

   public TransferResult transferOwnership(ArrowBuf srcBuffer, BufferAllocator target) {
      BufferLedger targetRefManager = this.allocationManager.associate(target);
      long targetBufLength = srcBuffer.capacity();
      ArrowBuf targetArrowBuf = targetRefManager.deriveBuffer(srcBuffer, 0L, targetBufLength);
      targetArrowBuf.readerIndex(srcBuffer.readerIndex());
      targetArrowBuf.writerIndex(srcBuffer.writerIndex());
      boolean allocationFit = this.transferBalance(targetRefManager);
      return new TransferResult(allocationFit, targetArrowBuf);
   }

   public long getSize() {
      return this.allocationManager.getSize();
   }

   public long getAccountedSize() {
      synchronized(this.allocationManager) {
         return this.allocationManager.getOwningLedger() == this ? this.allocationManager.getSize() : 0L;
      }
   }

   void print(StringBuilder sb, int indent, BaseAllocator.Verbosity verbosity) {
      CommonUtil.indent(sb, indent).append("ledger[").append(this.ledgerId).append("] allocator: ").append(this.allocator.getName()).append("), isOwning: ").append(", size: ").append(", references: ").append(this.bufRefCnt.get()).append(", life: ").append(this.lCreationTime).append("..").append(this.lDestructionTime).append(", allocatorManager: [").append(", life: ");
      if (!BaseAllocator.DEBUG) {
         sb.append("]\n");
      } else {
         Preconditions.checkArgument(this.buffers != null, "IdentityHashMap of buffers must not be null");
         synchronized(this.buffers) {
            sb.append("] holds ").append(this.buffers.size()).append(" buffers. \n");

            for(ArrowBuf buf : this.buffers.keySet()) {
               buf.print(sb, indent + 2, verbosity);
               sb.append('\n');
            }
         }
      }

   }

   public AllocationManager getAllocationManager() {
      return this.allocationManager;
   }

   public static class TransferResult implements OwnershipTransferResult {
      final boolean allocationFit;
      public final ArrowBuf buffer;

      private TransferResult(boolean allocationFit, ArrowBuf buffer) {
         this.allocationFit = allocationFit;
         this.buffer = buffer;
      }

      public ArrowBuf getTransferredBuffer() {
         return this.buffer;
      }

      public boolean getAllocationFit() {
         return this.allocationFit;
      }
   }
}
