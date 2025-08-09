package org.apache.spark.memory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.MEMORY_CONSUMER.;
import org.apache.spark.unsafe.memory.MemoryBlock;
import org.apache.spark.util.Utils;
import org.sparkproject.guava.annotations.VisibleForTesting;

public class TaskMemoryManager {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(TaskMemoryManager.class);
   private static final int PAGE_NUMBER_BITS = 13;
   @VisibleForTesting
   static final int OFFSET_BITS = 51;
   private static final int PAGE_TABLE_SIZE = 8192;
   public static final long MAXIMUM_PAGE_SIZE_BYTES = 17179869176L;
   private static final long MASK_LONG_LOWER_51_BITS = 2251799813685247L;
   private final MemoryBlock[] pageTable = new MemoryBlock[8192];
   private final BitSet allocatedPages = new BitSet(8192);
   private final MemoryManager memoryManager;
   private final long taskAttemptId;
   final MemoryMode tungstenMemoryMode;
   @GuardedBy("this")
   private final HashSet consumers;
   private volatile long acquiredButNotUsed = 0L;
   private long currentOffHeapMemory = 0L;
   private final Object offHeapMemoryLock = new Object();
   private long currentOnHeapMemory = 0L;
   private final Object onHeapMemoryLock = new Object();
   private volatile long peakOffHeapMemory = 0L;
   private volatile long peakOnHeapMemory = 0L;

   public TaskMemoryManager(MemoryManager memoryManager, long taskAttemptId) {
      this.tungstenMemoryMode = memoryManager.tungstenMemoryMode();
      this.memoryManager = memoryManager;
      this.taskAttemptId = taskAttemptId;
      this.consumers = new HashSet();
   }

   public long acquireExecutionMemory(long required, MemoryConsumer requestingConsumer) {
      assert required >= 0L;

      assert requestingConsumer != null;

      MemoryMode mode = requestingConsumer.getMode();
      synchronized(this) {
         long got = this.memoryManager.acquireExecutionMemory(required, this.taskAttemptId, mode);
         if (got < required) {
            if (logger.isDebugEnabled()) {
               logger.debug("Task {} need to spill {} for {}", new Object[]{this.taskAttemptId, Utils.bytesToString(required - got), requestingConsumer});
            }

            TreeMap<Long, List<MemoryConsumer>> sortedConsumers = new TreeMap();

            for(MemoryConsumer c : this.consumers) {
               if (c.getUsed() > 0L && c.getMode() == mode) {
                  long key = c == requestingConsumer ? 0L : c.getUsed();
                  List<MemoryConsumer> list = (List)sortedConsumers.computeIfAbsent(key, (k) -> new ArrayList(1));
                  list.add(c);
               }
            }

            while(got < required && !sortedConsumers.isEmpty()) {
               Map.Entry<Long, List<MemoryConsumer>> currentEntry = sortedConsumers.ceilingEntry(required - got);
               if (currentEntry == null) {
                  currentEntry = sortedConsumers.lastEntry();
               }

               List<MemoryConsumer> cList = (List)currentEntry.getValue();
               got += this.trySpillAndAcquire(requestingConsumer, required - got, cList, cList.size() - 1);
               if (cList.isEmpty()) {
                  sortedConsumers.remove(currentEntry.getKey());
               }
            }
         }

         this.consumers.add(requestingConsumer);
         if (logger.isDebugEnabled()) {
            logger.debug("Task {} acquired {} for {}", new Object[]{this.taskAttemptId, Utils.bytesToString(got), requestingConsumer});
         }

         if (mode == MemoryMode.OFF_HEAP) {
            synchronized(this.offHeapMemoryLock) {
               this.currentOffHeapMemory += got;
               this.peakOffHeapMemory = Math.max(this.peakOffHeapMemory, this.currentOffHeapMemory);
            }
         } else {
            synchronized(this.onHeapMemoryLock) {
               this.currentOnHeapMemory += got;
               this.peakOnHeapMemory = Math.max(this.peakOnHeapMemory, this.currentOnHeapMemory);
            }
         }

         return got;
      }
   }

   private long trySpillAndAcquire(MemoryConsumer requestingConsumer, long requested, List cList, int idx) {
      MemoryMode mode = requestingConsumer.getMode();
      final MemoryConsumer consumerToSpill = (MemoryConsumer)cList.get(idx);
      if (logger.isDebugEnabled()) {
         logger.debug("Task {} try to spill {} from {} for {}", new Object[]{this.taskAttemptId, Utils.bytesToString(requested), consumerToSpill, requestingConsumer});
      }

      try {
         long released = consumerToSpill.spill(requested, requestingConsumer);
         if (released > 0L) {
            if (logger.isDebugEnabled()) {
               logger.debug("Task {} spilled {} of requested {} from {} for {}", new Object[]{this.taskAttemptId, Utils.bytesToString(released), Utils.bytesToString(requested), consumerToSpill, requestingConsumer});
            }

            return this.memoryManager.acquireExecutionMemory(requested, this.taskAttemptId, mode);
         } else {
            cList.remove(idx);
            return 0L;
         }
      } catch (InterruptedIOException | ClosedByInterruptException e) {
         logger.error("error while calling spill() on {}", e, new MDC[]{MDC.of(.MODULE$, consumerToSpill)});
         throw new RuntimeException(((IOException)e).getMessage());
      } catch (final IOException e) {
         logger.error("error while calling spill() on {}", e, new MDC[]{MDC.of(.MODULE$, consumerToSpill)});
         throw new SparkOutOfMemoryError("_LEGACY_ERROR_TEMP_3300", new HashMap() {
            {
               this.put("consumerToSpill", consumerToSpill.toString());
               this.put("message", e.getMessage());
            }
         });
      }
   }

   public void releaseExecutionMemory(long size, MemoryConsumer consumer) {
      if (logger.isDebugEnabled()) {
         logger.debug("Task {} release {} from {}", new Object[]{this.taskAttemptId, Utils.bytesToString(size), consumer});
      }

      this.memoryManager.releaseExecutionMemory(size, this.taskAttemptId, consumer.getMode());
      if (consumer.getMode() == MemoryMode.OFF_HEAP) {
         synchronized(this.offHeapMemoryLock) {
            this.currentOffHeapMemory -= size;
         }
      } else {
         synchronized(this.onHeapMemoryLock) {
            this.currentOnHeapMemory -= size;
         }
      }

   }

   public void showMemoryUsage() {
      logger.info("Memory used in task {}", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, this.taskAttemptId)});
      synchronized(this) {
         long memoryAccountedForByConsumers = 0L;

         for(MemoryConsumer c : this.consumers) {
            long totalMemUsage = c.getUsed();
            memoryAccountedForByConsumers += totalMemUsage;
            if (totalMemUsage > 0L) {
               logger.info("Acquired by {}: {}", new MDC[]{MDC.of(.MODULE$, c), MDC.of(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, Utils.bytesToString(totalMemUsage))});
            }
         }

         long memoryNotAccountedFor = this.memoryManager.getExecutionMemoryUsageForTask(this.taskAttemptId) - memoryAccountedForByConsumers;
         logger.info("{} bytes of memory were used by task {} but are not associated with specific consumers", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.MEMORY_SIZE..MODULE$, memoryNotAccountedFor), MDC.of(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, this.taskAttemptId)});
         logger.info("{} bytes of memory are used for execution and {} bytes of memory are used for storage", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.EXECUTION_MEMORY_SIZE..MODULE$, this.memoryManager.executionMemoryUsed()), MDC.of(org.apache.spark.internal.LogKeys.STORAGE_MEMORY_SIZE..MODULE$, this.memoryManager.storageMemoryUsed())});
      }
   }

   public long pageSizeBytes() {
      return this.memoryManager.pageSizeBytes();
   }

   public MemoryBlock allocatePage(long size, MemoryConsumer consumer) {
      assert consumer != null;

      assert consumer.getMode() == this.tungstenMemoryMode;

      if (size > 17179869176L) {
         throw new TooLargePageException(size);
      } else {
         long acquired = this.acquireExecutionMemory(size, consumer);
         if (acquired <= 0L) {
            return null;
         } else {
            int pageNumber;
            synchronized(this) {
               pageNumber = this.allocatedPages.nextClearBit(0);
               if (pageNumber >= 8192) {
                  this.releaseExecutionMemory(acquired, consumer);
                  throw new IllegalStateException("Have already allocated a maximum of 8192 pages");
               }

               this.allocatedPages.set(pageNumber);
            }

            MemoryBlock page = null;

            try {
               page = this.memoryManager.tungstenMemoryAllocator().allocate(acquired);
            } catch (OutOfMemoryError var12) {
               logger.warn("Failed to allocate a page ({} bytes), try again.", new MDC[]{MDC.of(org.apache.spark.internal.LogKeys.PAGE_SIZE..MODULE$, acquired)});
               synchronized(this) {
                  this.acquiredButNotUsed += acquired;
                  this.allocatedPages.clear(pageNumber);
               }

               return this.allocatePage(size, consumer);
            }

            page.pageNumber = pageNumber;
            this.pageTable[pageNumber] = page;
            if (logger.isTraceEnabled()) {
               logger.trace("Allocate page number {} ({} bytes)", pageNumber, acquired);
            }

            return page;
         }
      }
   }

   public void freePage(MemoryBlock page, MemoryConsumer consumer) {
      assert page.pageNumber != -1 : "Called freePage() on memory that wasn't allocated with allocatePage()";

      assert page.pageNumber != -3 : "Called freePage() on a memory block that has already been freed";

      assert page.pageNumber != -2 : "Called freePage() on a memory block that has already been freed";

      assert this.allocatedPages.get(page.pageNumber);

      this.pageTable[page.pageNumber] = null;
      synchronized(this) {
         this.allocatedPages.clear(page.pageNumber);
      }

      if (logger.isTraceEnabled()) {
         logger.trace("Freed page number {} ({} bytes)", page.pageNumber, page.size());
      }

      long pageSize = page.size();
      page.pageNumber = -2;
      this.memoryManager.tungstenMemoryAllocator().free(page);
      this.releaseExecutionMemory(pageSize, consumer);
   }

   public long encodePageNumberAndOffset(MemoryBlock page, long offsetInPage) {
      if (this.tungstenMemoryMode == MemoryMode.OFF_HEAP) {
         offsetInPage -= page.getBaseOffset();
      }

      return encodePageNumberAndOffset(page.pageNumber, offsetInPage);
   }

   @VisibleForTesting
   public static long encodePageNumberAndOffset(int pageNumber, long offsetInPage) {
      assert pageNumber >= 0 : "encodePageNumberAndOffset called with invalid page";

      return (long)pageNumber << 51 | offsetInPage & 2251799813685247L;
   }

   @VisibleForTesting
   public static int decodePageNumber(long pagePlusOffsetAddress) {
      return (int)(pagePlusOffsetAddress >>> 51);
   }

   private static long decodeOffset(long pagePlusOffsetAddress) {
      return pagePlusOffsetAddress & 2251799813685247L;
   }

   public Object getPage(long pagePlusOffsetAddress) {
      if (this.tungstenMemoryMode != MemoryMode.ON_HEAP) {
         return null;
      } else {
         int pageNumber = decodePageNumber(pagePlusOffsetAddress);

         assert pageNumber >= 0 && pageNumber < 8192;

         MemoryBlock page = this.pageTable[pageNumber];

         assert page != null;

         assert page.getBaseObject() != null;

         return page.getBaseObject();
      }
   }

   public long getOffsetInPage(long pagePlusOffsetAddress) {
      long offsetInPage = decodeOffset(pagePlusOffsetAddress);
      if (this.tungstenMemoryMode == MemoryMode.ON_HEAP) {
         return offsetInPage;
      } else {
         int pageNumber = decodePageNumber(pagePlusOffsetAddress);

         assert pageNumber >= 0 && pageNumber < 8192;

         MemoryBlock page = this.pageTable[pageNumber];

         assert page != null;

         return page.getBaseOffset() + offsetInPage;
      }
   }

   public long cleanUpAllAllocatedMemory() {
      synchronized(this) {
         for(MemoryConsumer c : this.consumers) {
            if (c != null && c.getUsed() > 0L && logger.isDebugEnabled()) {
               logger.debug("unreleased {} memory from {}", Utils.bytesToString(c.getUsed()), c);
            }
         }

         this.consumers.clear();

         for(MemoryBlock page : this.pageTable) {
            if (page != null) {
               if (logger.isDebugEnabled()) {
                  logger.debug("unreleased page: {} in task {}", page, this.taskAttemptId);
               }

               page.pageNumber = -2;
               this.memoryManager.tungstenMemoryAllocator().free(page);
            }
         }

         Arrays.fill(this.pageTable, (Object)null);
      }

      this.memoryManager.releaseExecutionMemory(this.acquiredButNotUsed, this.taskAttemptId, this.tungstenMemoryMode);
      return this.memoryManager.releaseAllExecutionMemoryForTask(this.taskAttemptId);
   }

   public long getMemoryConsumptionForThisTask() {
      return this.memoryManager.getExecutionMemoryUsageForTask(this.taskAttemptId);
   }

   public MemoryMode getTungstenMemoryMode() {
      return this.tungstenMemoryMode;
   }

   public long getPeakOnHeapExecutionMemory() {
      return this.peakOnHeapMemory;
   }

   public long getPeakOffHeapExecutionMemory() {
      return this.peakOffHeapMemory;
   }
}
