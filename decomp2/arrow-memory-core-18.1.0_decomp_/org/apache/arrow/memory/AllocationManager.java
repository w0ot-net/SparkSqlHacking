package org.apache.arrow.memory;

import org.apache.arrow.util.Preconditions;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract class AllocationManager {
   private final BufferAllocator root;
   private final LowCostIdentityHashMap map = new LowCostIdentityHashMap();
   private volatile @Nullable BufferLedger owningLedger;

   protected AllocationManager(BufferAllocator accountingAllocator) {
      Preconditions.checkNotNull(accountingAllocator);
      accountingAllocator.assertOpen();
      this.root = accountingAllocator.getRoot();
      this.owningLedger = this.associate(accountingAllocator, false);
   }

   @Nullable BufferLedger getOwningLedger() {
      return this.owningLedger;
   }

   void setOwningLedger(BufferLedger ledger) {
      this.owningLedger = ledger;
   }

   BufferLedger associate(BufferAllocator allocator) {
      return this.associate(allocator, true);
   }

   private BufferLedger associate(BufferAllocator allocator, boolean retain) {
      allocator.assertOpen();
      Preconditions.checkState(this.root == allocator.getRoot(), "A buffer can only be associated between two allocators that share the same root");
      synchronized(this) {
         BufferLedger ledger = (BufferLedger)this.map.get(allocator);
         if (ledger != null) {
            if (retain) {
               ledger.increment();
            }

            return ledger;
         } else {
            ledger = new BufferLedger(allocator, this);
            if (retain) {
               ledger.increment();
            }

            BufferLedger oldLedger = (BufferLedger)this.map.put(ledger);
            Preconditions.checkState(oldLedger == null, "Detected inconsistent state: A reference manager already exists for this allocator");
            if (allocator instanceof BaseAllocator) {
               ((BaseAllocator)allocator).associateLedger(ledger);
            }

            return ledger;
         }
      }
   }

   void release(BufferLedger ledger) {
      BufferAllocator allocator = ledger.getAllocator();
      allocator.assertOpen();
      Preconditions.checkState(this.map.containsKey(allocator), "Expecting a mapping for allocator and reference manager");
      BufferLedger oldLedger = (BufferLedger)this.map.remove(allocator);
      Preconditions.checkState(oldLedger != null, "Expecting a mapping for allocator and reference manager");
      BufferAllocator oldAllocator = oldLedger.getAllocator();
      if (oldAllocator instanceof BaseAllocator) {
         ((BaseAllocator)oldAllocator).dissociateLedger(oldLedger);
      }

      if (oldLedger == this.owningLedger) {
         if (this.map.isEmpty()) {
            oldAllocator.releaseBytes(this.getSize());
            this.release0();
            oldAllocator.getListener().onRelease(this.getSize());
            this.owningLedger = null;
         } else {
            BufferLedger newOwningLedger = (BufferLedger)this.map.getNextValue();
            oldLedger.transferBalance(newOwningLedger);
         }
      } else {
         Preconditions.checkState(this.map.size() > 0, "The final removal of reference manager should be connected to owning reference manager");
      }

   }

   public abstract long getSize();

   protected abstract long memoryAddress();

   protected abstract void release0();

   public interface Factory {
      AllocationManager create(BufferAllocator var1, long var2);

      ArrowBuf empty();
   }
}
