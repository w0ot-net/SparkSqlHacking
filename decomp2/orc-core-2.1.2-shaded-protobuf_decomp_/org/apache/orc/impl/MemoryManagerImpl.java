package org.apache.orc.impl;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.orc.OrcConf;

public class MemoryManagerImpl implements org.apache.orc.MemoryManager {
   private final long totalMemoryPool;
   private final Map writerList;
   private final AtomicLong totalAllocation;

   public MemoryManagerImpl(Configuration conf) {
      this(Math.round((double)ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() * OrcConf.MEMORY_POOL.getDouble(conf)));
   }

   public MemoryManagerImpl(long poolSize) {
      this.writerList = new HashMap();
      this.totalAllocation = new AtomicLong(0L);
      this.totalMemoryPool = poolSize;
   }

   public synchronized void addWriter(Path path, long requestedAllocation, org.apache.orc.MemoryManager.Callback callback) throws IOException {
      WriterInfo oldVal = (WriterInfo)this.writerList.get(path);
      if (oldVal == null) {
         oldVal = new WriterInfo(requestedAllocation);
         this.writerList.put(path, oldVal);
         this.totalAllocation.addAndGet(requestedAllocation);
      } else {
         this.totalAllocation.addAndGet(requestedAllocation - oldVal.allocation);
         oldVal.allocation = requestedAllocation;
      }

   }

   public synchronized void removeWriter(Path path) throws IOException {
      WriterInfo val = (WriterInfo)this.writerList.remove(path);
      if (val != null) {
         this.totalAllocation.addAndGet(-val.allocation);
      }

   }

   public long getTotalMemoryPool() {
      return this.totalMemoryPool;
   }

   public double getAllocationScale() {
      long alloc = this.totalAllocation.get();
      return alloc <= this.totalMemoryPool ? (double)1.0F : (double)this.totalMemoryPool / (double)alloc;
   }

   public void addedRow(int rows) throws IOException {
   }

   /** @deprecated */
   public void notifyWriters() throws IOException {
   }

   public long checkMemory(long previous, org.apache.orc.MemoryManager.Callback writer) throws IOException {
      long current = this.totalAllocation.get();
      if (current != previous) {
         writer.checkMemory(this.getAllocationScale());
      }

      return current;
   }

   private static class WriterInfo {
      long allocation;

      WriterInfo(long allocation) {
         this.allocation = allocation;
      }
   }
}
