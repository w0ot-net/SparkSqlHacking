package org.apache.arrow.memory;

import com.google.errorprone.annotations.FormatMethod;
import com.google.errorprone.annotations.FormatString;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.arrow.memory.rounding.DefaultRoundingPolicy;
import org.apache.arrow.memory.rounding.RoundingPolicy;
import org.apache.arrow.memory.util.AssertionUtil;
import org.apache.arrow.memory.util.CommonUtil;
import org.apache.arrow.memory.util.HistoricalLog;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.util.Preconditions;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class BaseAllocator extends Accountant implements BufferAllocator {
   public static final String DEBUG_ALLOCATOR = "arrow.memory.debug.allocator";
   public static final int DEBUG_LOG_LENGTH = 6;
   public static final boolean DEBUG;
   private static final Logger logger = LoggerFactory.getLogger(BaseAllocator.class);
   public static final Config DEFAULT_CONFIG;
   private final String name;
   private final RootAllocator root;
   private final Object DEBUG_LOCK = new Object();
   private final AllocationListener listener;
   private final @Nullable BaseAllocator parentAllocator;
   private final Map childAllocators;
   private final ArrowBuf empty;
   private final @Nullable IdentityHashMap childLedgers;
   private final @Nullable IdentityHashMap reservations;
   private final @Nullable HistoricalLog historicalLog;
   private final RoundingPolicy roundingPolicy;
   private final AllocationManager.@NonNull Factory allocationManagerFactory;
   private volatile boolean isClosed = false;

   protected BaseAllocator(BaseAllocator parentAllocator, String name, Config config) throws OutOfMemoryException {
      super(parentAllocator, name, config.getInitReservation(), config.getMaxAllocation());
      this.listener = config.getListener();
      this.allocationManagerFactory = config.getAllocationManagerFactory();
      if (parentAllocator != null) {
         this.root = parentAllocator.root;
         this.empty = parentAllocator.empty;
      } else {
         if (!(this instanceof RootAllocator)) {
            throw new IllegalStateException("An parent allocator must either carry a root or be the root.");
         }

         this.root = (RootAllocator)this;
         this.empty = this.createEmpty();
      }

      this.parentAllocator = parentAllocator;
      this.name = name;
      this.childAllocators = Collections.synchronizedMap(new IdentityHashMap());
      if (DEBUG) {
         this.reservations = new IdentityHashMap();
         this.childLedgers = new IdentityHashMap();
         this.historicalLog = new HistoricalLog(6, "allocator[%s]", new Object[]{name});
         this.hist("created by \"%s\", owned = %d", name, this.getAllocatedMemory());
      } else {
         this.reservations = null;
         this.historicalLog = null;
         this.childLedgers = null;
      }

      this.roundingPolicy = config.getRoundingPolicy();
   }

   public AllocationListener getListener() {
      return this.listener;
   }

   public @Nullable BaseAllocator getParentAllocator() {
      return this.parentAllocator;
   }

   public Collection getChildAllocators() {
      synchronized(this.childAllocators) {
         return new HashSet(this.childAllocators.keySet());
      }
   }

   private static String createErrorMsg(BufferAllocator allocator, long rounded, long requested) {
      return rounded != requested ? String.format("Unable to allocate buffer of size %d (rounded from %d) due to memory limit. Current allocation: %d", rounded, requested, allocator.getAllocatedMemory()) : String.format("Unable to allocate buffer of size %d due to memory limit. Current allocation: %d", rounded, allocator.getAllocatedMemory());
   }

   public static boolean isDebug() {
      return DEBUG;
   }

   public void assertOpen() {
      if (AssertionUtil.ASSERT_ENABLED && this.isClosed) {
         throw new IllegalStateException("Attempting operation on allocator when allocator is closed.\n" + this.toVerboseString());
      }
   }

   public String getName() {
      return this.name;
   }

   public ArrowBuf getEmpty() {
      return this.empty;
   }

   void associateLedger(BufferLedger ledger) {
      this.assertOpen();
      if (DEBUG) {
         synchronized(this.DEBUG_LOCK) {
            if (this.childLedgers != null) {
               this.childLedgers.put(ledger, (Object)null);
            }
         }
      }

   }

   void dissociateLedger(BufferLedger ledger) {
      this.assertOpen();
      if (DEBUG) {
         synchronized(this.DEBUG_LOCK) {
            Preconditions.checkState(this.childLedgers != null, "childLedgers must not be null");
            if (!this.childLedgers.containsKey(ledger)) {
               throw new IllegalStateException("Trying to remove a child ledger that doesn't exist.");
            }

            this.childLedgers.remove(ledger);
         }
      }

   }

   private void childClosed(BaseAllocator childAllocator) {
      this.assertOpen();
      if (DEBUG) {
         Preconditions.checkArgument(childAllocator != null, "child allocator can't be null");
         synchronized(this.DEBUG_LOCK) {
            Object object = this.childAllocators.remove(childAllocator);
            if (object == null) {
               if (childAllocator.historicalLog != null) {
                  childAllocator.historicalLog.logHistory(logger);
               }

               throw new IllegalStateException("Child allocator[" + childAllocator.name + "] not found in parent allocator[" + this.name + "]'s childAllocators");
            }
         }
      } else {
         this.childAllocators.remove(childAllocator);
      }

      this.listener.onChildRemoved(this, childAllocator);
   }

   public ArrowBuf wrapForeignAllocation(ForeignAllocation allocation) {
      this.assertOpen();
      long size = allocation.getSize();
      this.listener.onPreAllocation(size);
      AllocationOutcome outcome = this.allocateBytes(size);
      if (!outcome.isOk()) {
         if (this.listener.onFailedAllocation(size, outcome)) {
            outcome = this.allocateBytes(size);
         }

         if (!outcome.isOk()) {
            throw new OutOfMemoryException(createErrorMsg(this, size, size), outcome.getDetails());
         }
      }

      try {
         AllocationManager manager = new ForeignAllocationManager(this, allocation);
         BufferLedger ledger = manager.associate(this);
         ArrowBuf buf = new ArrowBuf(ledger, (BufferManager)null, size, allocation.memoryAddress());
         buf.writerIndex(size);
         this.listener.onAllocation(size);
         return buf;
      } catch (Throwable var10) {
         try {
            this.releaseBytes(size);
         } catch (Throwable e) {
            var10.addSuppressed(e);
         }

         try {
            allocation.release0();
         } catch (Throwable e) {
            var10.addSuppressed(e);
         }

         throw var10;
      }
   }

   public ArrowBuf buffer(long initialRequestSize) {
      this.assertOpen();
      return this.buffer(initialRequestSize, (BufferManager)null);
   }

   private ArrowBuf createEmpty() {
      return this.allocationManagerFactory.empty();
   }

   public ArrowBuf buffer(long initialRequestSize, @Nullable BufferManager manager) {
      this.assertOpen();
      Preconditions.checkArgument(initialRequestSize >= 0L, "the requested size must be non-negative");
      if (initialRequestSize == 0L) {
         return this.getEmpty();
      } else {
         long actualRequestSize = this.roundingPolicy.getRoundedSize(initialRequestSize);
         this.listener.onPreAllocation(actualRequestSize);
         AllocationOutcome outcome = this.allocateBytes(actualRequestSize);
         if (!outcome.isOk()) {
            if (this.listener.onFailedAllocation(actualRequestSize, outcome)) {
               outcome = this.allocateBytes(actualRequestSize);
            }

            if (!outcome.isOk()) {
               throw new OutOfMemoryException(createErrorMsg(this, actualRequestSize, initialRequestSize), outcome.getDetails());
            }
         }

         boolean success = false;

         ArrowBuf var9;
         try {
            ArrowBuf buffer = this.bufferWithoutReservation(actualRequestSize, manager);
            success = true;
            this.listener.onAllocation(actualRequestSize);
            var9 = buffer;
         } catch (OutOfMemoryError e) {
            throw e;
         } finally {
            if (!success) {
               this.releaseBytes(actualRequestSize);
            }

         }

         return var9;
      }
   }

   private ArrowBuf bufferWithoutReservation(long size, @Nullable BufferManager bufferManager) throws OutOfMemoryException {
      this.assertOpen();
      AllocationManager manager = this.newAllocationManager(size);
      BufferLedger ledger = manager.associate(this);
      ArrowBuf buffer = ledger.newArrowBuf(size, bufferManager);
      Preconditions.checkArgument(buffer.capacity() == size, "Allocated capacity %d was not equal to requested capacity %d.", buffer.capacity(), size);
      return buffer;
   }

   private AllocationManager newAllocationManager(long size) {
      return this.newAllocationManager(this, size);
   }

   private AllocationManager newAllocationManager(BaseAllocator accountingAllocator, long size) {
      return this.allocationManagerFactory.create(accountingAllocator, size);
   }

   public BufferAllocator getRoot() {
      return this.root;
   }

   public BufferAllocator newChildAllocator(String name, long initReservation, long maxAllocation) {
      return this.newChildAllocator(name, this.listener, initReservation, maxAllocation);
   }

   public BufferAllocator newChildAllocator(String name, AllocationListener listener, long initReservation, long maxAllocation) {
      this.assertOpen();
      ChildAllocator childAllocator = new ChildAllocator(this, name, configBuilder().listener(listener).initReservation(initReservation).maxAllocation(maxAllocation).roundingPolicy(this.roundingPolicy).allocationManagerFactory(this.allocationManagerFactory).build());
      if (DEBUG) {
         synchronized(this.DEBUG_LOCK) {
            this.childAllocators.put(childAllocator, childAllocator);
            if (this.historicalLog != null) {
               this.historicalLog.recordEvent("allocator[%s] created new child allocator[%s]", name, childAllocator.getName());
            }
         }
      } else {
         this.childAllocators.put(childAllocator, childAllocator);
      }

      this.listener.onChildAdded(this, childAllocator);
      return childAllocator;
   }

   public AllocationReservation newReservation() {
      this.assertOpen();
      return new Reservation();
   }

   public synchronized void close() {
      if (!this.isClosed) {
         this.isClosed = true;
         StringBuilder outstandingChildAllocators = new StringBuilder();
         if (DEBUG) {
            synchronized(this.DEBUG_LOCK) {
               this.verifyAllocator();
               if (!this.childAllocators.isEmpty()) {
                  for(BaseAllocator childAllocator : this.childAllocators.keySet()) {
                     if (childAllocator.isClosed) {
                        logger.warn(String.format("Closed child allocator[%s] on parent allocator[%s]'s child list.\n%s", childAllocator.name, this.name, this.toString()));
                     }
                  }

                  throw new IllegalStateException(String.format("Allocator[%s] closed with outstanding child allocators.\n%s", this.name, this.toString()));
               }

               int allocatedCount = this.childLedgers != null ? this.childLedgers.size() : 0;
               if (allocatedCount > 0) {
                  throw new IllegalStateException(String.format("Allocator[%s] closed with outstanding buffers allocated (%d).\n%s", this.name, allocatedCount, this.toString()));
               }

               if (this.reservations != null && this.reservations.size() != 0) {
                  throw new IllegalStateException(String.format("Allocator[%s] closed with outstanding reservations (%d).\n%s", this.name, this.reservations.size(), this.toString()));
               }
            }
         } else if (!this.childAllocators.isEmpty()) {
            outstandingChildAllocators.append("Outstanding child allocators : \n");
            synchronized(this.childAllocators) {
               for(BaseAllocator childAllocator : this.childAllocators.keySet()) {
                  outstandingChildAllocators.append(String.format("  %s", childAllocator.toString()));
               }
            }
         }

         long allocated = this.getAllocatedMemory();
         if (allocated > 0L) {
            if (this.parent != null && this.reservation > allocated) {
               this.parent.releaseBytes(this.reservation - allocated);
            }

            String msg = String.format("Memory was leaked by query. Memory leaked: (%d)\n%s%s", allocated, outstandingChildAllocators.toString(), this.toString());
            logger.error(msg);
            throw new IllegalStateException(msg);
         } else {
            super.close();
            if (this.parentAllocator != null) {
               this.parentAllocator.childClosed(this);
            }

            if (DEBUG) {
               if (this.historicalLog != null) {
                  this.historicalLog.recordEvent("closed");
               }

               logger.debug(String.format("closed allocator[%s].", this.name));
            }

         }
      }
   }

   public String toString() {
      Verbosity verbosity = logger.isTraceEnabled() ? BaseAllocator.Verbosity.LOG_WITH_STACKTRACE : BaseAllocator.Verbosity.BASIC;
      StringBuilder sb = new StringBuilder();
      this.print(sb, 0, verbosity);
      return sb.toString();
   }

   public String toVerboseString() {
      StringBuilder sb = new StringBuilder();
      this.print(sb, 0, BaseAllocator.Verbosity.LOG_WITH_STACKTRACE);
      return sb.toString();
   }

   @FormatMethod
   private void hist(@FormatString String noteFormat, Object... args) {
      if (this.historicalLog != null) {
         this.historicalLog.recordEvent(noteFormat, args);
      }

   }

   void verifyAllocator() {
      IdentityHashMap<AllocationManager, BaseAllocator> seen = new IdentityHashMap();
      this.verifyAllocator(seen);
   }

   private void verifyAllocator(IdentityHashMap buffersSeen) {
      if (DEBUG) {
         synchronized(this.DEBUG_LOCK) {
            long allocated = this.getAllocatedMemory();
            Set<BaseAllocator> childSet = this.childAllocators.keySet();

            for(BaseAllocator childAllocator : childSet) {
               childAllocator.verifyAllocator(buffersSeen);
            }

            long childTotal = 0L;

            for(BaseAllocator childAllocator : childSet) {
               childTotal += Math.max(childAllocator.getAllocatedMemory(), childAllocator.reservation);
            }

            if (childTotal > this.getAllocatedMemory()) {
               if (this.historicalLog != null) {
                  this.historicalLog.logHistory(logger);
               }

               logger.debug("allocator[" + this.name + "] child event logs BEGIN");

               for(BaseAllocator childAllocator : childSet) {
                  if (childAllocator.historicalLog != null) {
                     childAllocator.historicalLog.logHistory(logger);
                  }
               }

               logger.debug("allocator[" + this.name + "] child event logs END");
               throw new IllegalStateException("Child allocators own more memory (" + childTotal + ") than their parent (name = " + this.name + " ) has allocated (" + this.getAllocatedMemory() + ")");
            } else {
               long bufferTotal = 0L;
               Set<BufferLedger> ledgerSet = this.childLedgers != null ? this.childLedgers.keySet() : null;
               if (ledgerSet != null) {
                  for(BufferLedger ledger : ledgerSet) {
                     if (ledger.isOwningLedger()) {
                        AllocationManager am = ledger.getAllocationManager();
                        BaseAllocator otherOwner = (BaseAllocator)buffersSeen.get(am);
                        if (otherOwner != null) {
                           throw new IllegalStateException("This allocator's ArrowBuf already owned by another allocator");
                        }

                        buffersSeen.put(am, this);
                        bufferTotal += am.getSize();
                     }
                  }
               }

               Set<Reservation> reservationSet = this.reservations != null ? this.reservations.keySet() : null;
               long reservedTotal = 0L;
               if (reservationSet != null) {
                  for(Reservation reservation : reservationSet) {
                     if (!reservation.isUsed()) {
                        reservedTotal += (long)reservation.getSize();
                     }
                  }
               }

               if (bufferTotal + reservedTotal + childTotal != this.getAllocatedMemory()) {
                  StringBuilder sb = new StringBuilder();
                  sb.append("allocator[");
                  sb.append(this.name);
                  sb.append("]\nallocated: ");
                  sb.append(Long.toString(allocated));
                  sb.append(" allocated - (bufferTotal + reservedTotal + childTotal): ");
                  sb.append(Long.toString(allocated - (bufferTotal + reservedTotal + childTotal)));
                  sb.append('\n');
                  if (bufferTotal != 0L) {
                     sb.append("buffer total: ");
                     sb.append(Long.toString(bufferTotal));
                     sb.append('\n');
                     this.dumpBuffers(sb, ledgerSet);
                  }

                  if (childTotal != 0L) {
                     sb.append("child total: ");
                     sb.append(Long.toString(childTotal));
                     sb.append('\n');

                     for(BaseAllocator childAllocator : childSet) {
                        sb.append("child allocator[");
                        sb.append(childAllocator.name);
                        sb.append("] owned ");
                        sb.append(Long.toString(childAllocator.getAllocatedMemory()));
                        sb.append('\n');
                     }
                  }

                  if (reservedTotal != 0L) {
                     sb.append(String.format("reserved total : %d bytes.", reservedTotal));
                     if (reservationSet != null) {
                        for(Reservation reservation : reservationSet) {
                           if (reservation.historicalLog != null) {
                              reservation.historicalLog.buildHistory(sb, 0, true);
                           }

                           sb.append('\n');
                        }
                     }
                  }

                  logger.debug(sb.toString());
                  long allocated2 = this.getAllocatedMemory();
                  if (allocated2 != allocated) {
                     throw new IllegalStateException(String.format("allocator[%s]: allocated t1 (%d) + allocated t2 (%d). Someone released memory while in verification.", this.name, allocated, allocated2));
                  } else {
                     throw new IllegalStateException(String.format("allocator[%s]: buffer space (%d) + prealloc space (%d) + child space (%d) != allocated (%d)", this.name, bufferTotal, reservedTotal, childTotal, allocated));
                  }
               }
            }
         }
      }
   }

   void print(StringBuilder sb, int level, Verbosity verbosity) {
      CommonUtil.indent(sb, level).append("Allocator(").append(this.name).append(") ").append(this.reservation).append('/').append(this.getAllocatedMemory()).append('/').append(this.getPeakMemoryAllocation()).append('/').append(this.getLimit()).append(" (res/actual/peak/limit)").append('\n');
      if (DEBUG) {
         CommonUtil.indent(sb, level + 1).append(String.format("child allocators: %d\n", this.childAllocators.size()));

         for(BaseAllocator child : this.childAllocators.keySet()) {
            child.print(sb, level + 2, verbosity);
         }

         CommonUtil.indent(sb, level + 1).append(String.format("ledgers: %d\n", this.childLedgers != null ? this.childLedgers.size() : 0));
         if (this.childLedgers != null) {
            for(BufferLedger ledger : this.childLedgers.keySet()) {
               ledger.print(sb, level + 2, verbosity);
            }
         }

         Set<Reservation> reservations = this.reservations != null ? this.reservations.keySet() : null;
         CommonUtil.indent(sb, level + 1).append(String.format("reservations: %d\n", reservations != null ? reservations.size() : 0));
         if (reservations != null) {
            for(Reservation reservation : reservations) {
               if (verbosity.includeHistoricalLog && reservation.historicalLog != null) {
                  reservation.historicalLog.buildHistory(sb, level + 3, true);
               }
            }
         }
      }

   }

   private void dumpBuffers(StringBuilder sb, @Nullable Set ledgerSet) {
      if (ledgerSet != null) {
         for(BufferLedger ledger : ledgerSet) {
            if (ledger.isOwningLedger()) {
               AllocationManager am = ledger.getAllocationManager();
               sb.append("UnsafeDirectLittleEndian[identityHashCode == ");
               sb.append(Integer.toString(System.identityHashCode(am)));
               sb.append("] size ");
               sb.append(Long.toString(am.getSize()));
               sb.append('\n');
            }
         }
      }

   }

   public static Config defaultConfig() {
      return DEFAULT_CONFIG;
   }

   public static ImmutableConfig.Builder configBuilder() {
      return ImmutableConfig.builder();
   }

   public RoundingPolicy getRoundingPolicy() {
      return this.roundingPolicy;
   }

   static {
      String propValue = System.getProperty("arrow.memory.debug.allocator");
      if (propValue != null) {
         DEBUG = Boolean.parseBoolean(propValue);
      } else {
         DEBUG = false;
      }

      logger.info("Debug mode " + (DEBUG ? "enabled." : "disabled. Enable with the VM option -Darrow.memory.debug.allocator=true."));
      DEFAULT_CONFIG = ImmutableConfig.builder().build();
   }

   public static enum Verbosity {
      BASIC(false, false),
      LOG(true, false),
      LOG_WITH_STACKTRACE(true, true);

      public final boolean includeHistoricalLog;
      public final boolean includeStackTraces;

      private Verbosity(boolean includeHistoricalLog, boolean includeStackTraces) {
         this.includeHistoricalLog = includeHistoricalLog;
         this.includeStackTraces = includeStackTraces;
      }

      // $FF: synthetic method
      private static Verbosity[] $values() {
         return new Verbosity[]{BASIC, LOG, LOG_WITH_STACKTRACE};
      }
   }

   @Immutable
   abstract static class Config {
      @Default
      AllocationManager.Factory getAllocationManagerFactory() {
         return DefaultAllocationManagerOption.getDefaultAllocationManagerFactory();
      }

      @Default
      AllocationListener getListener() {
         return AllocationListener.NOOP;
      }

      @Default
      long getInitReservation() {
         return 0L;
      }

      @Default
      long getMaxAllocation() {
         return Long.MAX_VALUE;
      }

      @Default
      RoundingPolicy getRoundingPolicy() {
         return DefaultRoundingPolicy.DEFAULT_ROUNDING_POLICY;
      }
   }

   public class Reservation implements AllocationReservation {
      private final @Nullable HistoricalLog historicalLog;
      private long nBytes = 0L;
      private boolean used = false;
      private boolean closed = false;

      public Reservation() {
         if (BaseAllocator.DEBUG) {
            this.historicalLog = new HistoricalLog("Reservation[allocator[%s], %d]", new Object[]{BaseAllocator.this.name, System.identityHashCode(this)});
            this.historicalLog.recordEvent("created");
            synchronized(BaseAllocator.this.DEBUG_LOCK) {
               if (BaseAllocator.this.reservations != null) {
                  BaseAllocator.this.reservations.put(this, this);
               }
            }
         } else {
            this.historicalLog = null;
         }

      }

      /** @deprecated */
      @Deprecated(
         forRemoval = true
      )
      public boolean add(int nBytes) {
         return this.add((long)nBytes);
      }

      public boolean add(long nBytes) {
         BaseAllocator.this.assertOpen();
         Preconditions.checkArgument(nBytes >= 0L, "nBytes(%d) < 0", nBytes);
         Preconditions.checkState(!this.closed, "Attempt to increase reservation after reservation has been closed");
         Preconditions.checkState(!this.used, "Attempt to increase reservation after reservation has been used");
         long nBytesTwo = CommonUtil.nextPowerOfTwo(nBytes);
         if (!this.reserve(nBytesTwo)) {
            return false;
         } else {
            this.nBytes += nBytesTwo;
            return true;
         }
      }

      public ArrowBuf allocateBuffer() {
         BaseAllocator.this.assertOpen();
         Preconditions.checkState(!this.closed, "Attempt to allocate after closed");
         Preconditions.checkState(!this.used, "Attempt to allocate more than once");
         ArrowBuf arrowBuf = this.allocate(this.nBytes);
         this.used = true;
         return arrowBuf;
      }

      public int getSize() {
         return LargeMemoryUtil.checkedCastToInt(this.nBytes);
      }

      public long getSizeLong() {
         return this.nBytes;
      }

      public boolean isUsed() {
         return this.used;
      }

      public boolean isClosed() {
         return this.closed;
      }

      public void close() {
         BaseAllocator.this.assertOpen();
         if (!this.closed) {
            if (BaseAllocator.DEBUG && !this.isClosed()) {
               Object object;
               synchronized(BaseAllocator.this.DEBUG_LOCK) {
                  object = BaseAllocator.this.reservations != null ? BaseAllocator.this.reservations.remove(this) : null;
               }

               if (object == null) {
                  StringBuilder sb = new StringBuilder();
                  BaseAllocator.this.print(sb, 0, BaseAllocator.Verbosity.LOG_WITH_STACKTRACE);
                  BaseAllocator.logger.debug(sb.toString());
                  throw new IllegalStateException(String.format("Didn't find closing reservation[%d]", System.identityHashCode(this)));
               }

               if (this.historicalLog != null) {
                  this.historicalLog.recordEvent("closed");
               }
            }

            if (!this.used) {
               this.releaseReservation(this.nBytes);
            }

            this.closed = true;
         }
      }

      /** @deprecated */
      @Deprecated(
         forRemoval = true
      )
      public boolean reserve(int nBytes) {
         return this.reserve((long)nBytes);
      }

      public boolean reserve(long nBytes) {
         BaseAllocator.this.assertOpen();
         AllocationOutcome outcome = BaseAllocator.this.allocateBytes(nBytes);
         if (this.historicalLog != null) {
            this.historicalLog.recordEvent("reserve(%d) => %s", nBytes, Boolean.toString(outcome.isOk()));
         }

         return outcome.isOk();
      }

      private ArrowBuf allocate(long nBytes) {
         BaseAllocator.this.assertOpen();
         boolean success = false;

         ArrowBuf var5;
         try {
            ArrowBuf arrowBuf = BaseAllocator.this.bufferWithoutReservation(nBytes, (BufferManager)null);
            BaseAllocator.this.listener.onAllocation(nBytes);
            if (this.historicalLog != null) {
               this.historicalLog.recordEvent("allocate() => %s", String.format("ArrowBuf[%d]", arrowBuf.getId()));
            }

            success = true;
            var5 = arrowBuf;
         } finally {
            if (!success) {
               BaseAllocator.this.releaseBytes(nBytes);
            }

         }

         return var5;
      }

      private void releaseReservation(long nBytes) {
         BaseAllocator.this.assertOpen();
         BaseAllocator.this.releaseBytes(nBytes);
         if (this.historicalLog != null) {
            this.historicalLog.recordEvent("releaseReservation(%d)", nBytes);
         }

      }
   }
}
