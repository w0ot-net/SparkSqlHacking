package org.apache.spark.memory;

import java.io.IOException;
import org.apache.spark.errors.SparkCoreErrors;
import org.apache.spark.unsafe.array.LongArray;
import org.apache.spark.unsafe.memory.MemoryBlock;

public abstract class MemoryConsumer {
   protected final TaskMemoryManager taskMemoryManager;
   private final long pageSize;
   private final MemoryMode mode;
   protected long used;

   protected MemoryConsumer(TaskMemoryManager taskMemoryManager, long pageSize, MemoryMode mode) {
      this.taskMemoryManager = taskMemoryManager;
      this.pageSize = pageSize;
      this.mode = mode;
   }

   protected MemoryConsumer(TaskMemoryManager taskMemoryManager, MemoryMode mode) {
      this(taskMemoryManager, taskMemoryManager.pageSizeBytes(), mode);
   }

   public MemoryMode getMode() {
      return this.mode;
   }

   public long getUsed() {
      return this.used;
   }

   public void spill() throws IOException {
      this.spill(Long.MAX_VALUE, this);
   }

   public abstract long spill(long var1, MemoryConsumer var3) throws IOException;

   public LongArray allocateArray(long size) {
      long required = size * 8L;
      MemoryBlock page = this.taskMemoryManager.allocatePage(required, this);
      if (page == null || page.size() < required) {
         this.throwOom(page, required);
      }

      this.used += required;
      return new LongArray(page);
   }

   public void freeArray(LongArray array) {
      this.freePage(array.memoryBlock());
   }

   protected MemoryBlock allocatePage(long required) {
      MemoryBlock page = this.taskMemoryManager.allocatePage(Math.max(this.pageSize, required), this);
      if (page == null || page.size() < required) {
         this.throwOom(page, required);
      }

      this.used += page.size();
      return page;
   }

   protected void freePage(MemoryBlock page) {
      this.used -= page.size();
      this.taskMemoryManager.freePage(page, this);
   }

   public long acquireMemory(long size) {
      long granted = this.taskMemoryManager.acquireExecutionMemory(size, this);
      this.used += granted;
      return granted;
   }

   public void freeMemory(long size) {
      this.taskMemoryManager.releaseExecutionMemory(size, this);
      this.used -= size;
   }

   private void throwOom(MemoryBlock page, long required) {
      long got = 0L;
      if (page != null) {
         got = page.size();
         this.taskMemoryManager.freePage(page, this);
      }

      this.taskMemoryManager.showMemoryUsage();
      throw SparkCoreErrors.outOfMemoryError(required, got);
   }
}
