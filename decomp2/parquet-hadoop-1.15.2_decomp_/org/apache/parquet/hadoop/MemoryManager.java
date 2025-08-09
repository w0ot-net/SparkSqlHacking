package org.apache.parquet.hadoop;

import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryManager {
   private static final Logger LOG = LoggerFactory.getLogger(MemoryManager.class);
   static final float DEFAULT_MEMORY_POOL_RATIO = 0.95F;
   static final long DEFAULT_MIN_MEMORY_ALLOCATION = 1048576L;
   private final float memoryPoolRatio;
   private final long totalMemoryPool;
   private final long minMemoryAllocation;
   private final Map writerList = new HashMap();
   private final Map callBacks = new HashMap();
   private double scale = (double)1.0F;

   public MemoryManager(float ratio, long minAllocation) {
      this.checkRatio(ratio);
      this.memoryPoolRatio = ratio;
      this.minMemoryAllocation = minAllocation;
      this.totalMemoryPool = Math.round((double)ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() * (double)ratio);
      LOG.debug("Allocated total memory pool is: {}", this.totalMemoryPool);
   }

   private void checkRatio(float ratio) {
      if (ratio <= 0.0F || ratio > 1.0F) {
         throw new IllegalArgumentException("The configured memory pool ratio " + ratio + " is not between 0 and 1.");
      }
   }

   synchronized void addWriter(InternalParquetRecordWriter writer, Long allocation) {
      Long oldValue = (Long)this.writerList.get(writer);
      if (oldValue == null) {
         this.writerList.put(writer, allocation);
         this.updateAllocation();
      } else {
         throw new IllegalArgumentException("[BUG] The Parquet Memory Manager should not add an instance of InternalParquetRecordWriter more than once. The Manager already contains the writer: " + writer);
      }
   }

   synchronized void removeWriter(InternalParquetRecordWriter writer) {
      this.writerList.remove(writer);
      if (!this.writerList.isEmpty()) {
         this.updateAllocation();
      }

   }

   private void updateAllocation() {
      long totalAllocations = 0L;

      for(Long allocation : this.writerList.values()) {
         totalAllocations += allocation;
      }

      if (totalAllocations <= this.totalMemoryPool) {
         this.scale = (double)1.0F;
      } else {
         this.scale = (double)this.totalMemoryPool / (double)totalAllocations;
         LOG.warn(String.format("Total allocation exceeds %.2f%% (%,d bytes) of heap memory\nScaling row group sizes to %.2f%% for %d writers", 100.0F * this.memoryPoolRatio, this.totalMemoryPool, (double)100.0F * this.scale, this.writerList.size()));

         for(Runnable callBack : this.callBacks.values()) {
            callBack.run();
         }
      }

      for(Map.Entry entry : this.writerList.entrySet()) {
         long newSize = (long)Math.floor((double)(Long)entry.getValue() * this.scale);
         if (this.scale < (double)1.0F && this.minMemoryAllocation > 0L && newSize < this.minMemoryAllocation) {
            throw new ParquetMemoryManagerRuntimeException(String.format("New Memory allocation %d bytes is smaller than the minimum allocation size of %d bytes.", newSize, this.minMemoryAllocation));
         }

         ((InternalParquetRecordWriter)entry.getKey()).setRowGroupSizeThreshold(newSize);
         LOG.debug(String.format("Adjust block size from %,d to %,d for writer: %s", entry.getValue(), newSize, entry.getKey()));
      }

   }

   long getTotalMemoryPool() {
      return this.totalMemoryPool;
   }

   Map getWriterList() {
      return this.writerList;
   }

   float getMemoryPoolRatio() {
      return this.memoryPoolRatio;
   }

   public void registerScaleCallBack(String callBackName, Runnable callBack) {
      Objects.requireNonNull(callBackName, "callBackName cannot be null");
      Objects.requireNonNull(callBack, "callBack cannot be null");
      if (this.callBacks.containsKey(callBackName)) {
         throw new IllegalArgumentException("The callBackName " + callBackName + " is duplicated and has been registered already.");
      } else {
         this.callBacks.put(callBackName, callBack);
      }
   }

   Map getScaleCallBacks() {
      return Collections.unmodifiableMap(this.callBacks);
   }

   double getScale() {
      return this.scale;
   }
}
