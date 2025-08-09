package io.netty.buffer;

import io.netty.util.internal.LongCounter;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

abstract class PoolArena implements PoolArenaMetric {
   private static final boolean HAS_UNSAFE = PlatformDependent.hasUnsafe();
   final PooledByteBufAllocator parent;
   final PoolSubpage[] smallSubpagePools;
   private final PoolChunkList q050;
   private final PoolChunkList q025;
   private final PoolChunkList q000;
   private final PoolChunkList qInit;
   private final PoolChunkList q075;
   private final PoolChunkList q100;
   private final List chunkListMetrics;
   private long allocationsNormal;
   private final LongCounter allocationsSmall = PlatformDependent.newLongCounter();
   private final LongCounter allocationsHuge = PlatformDependent.newLongCounter();
   private final LongCounter activeBytesHuge = PlatformDependent.newLongCounter();
   private long deallocationsSmall;
   private long deallocationsNormal;
   private final LongCounter deallocationsHuge = PlatformDependent.newLongCounter();
   final AtomicInteger numThreadCaches = new AtomicInteger();
   private final ReentrantLock lock = new ReentrantLock();
   final SizeClasses sizeClass;

   protected PoolArena(PooledByteBufAllocator parent, SizeClasses sizeClass) {
      assert null != sizeClass;

      this.parent = parent;
      this.sizeClass = sizeClass;
      this.smallSubpagePools = this.newSubpagePoolArray(sizeClass.nSubpages);

      for(int i = 0; i < this.smallSubpagePools.length; ++i) {
         this.smallSubpagePools[i] = this.newSubpagePoolHead(i);
      }

      this.q100 = new PoolChunkList(this, (PoolChunkList)null, 100, Integer.MAX_VALUE, sizeClass.chunkSize);
      this.q075 = new PoolChunkList(this, this.q100, 75, 100, sizeClass.chunkSize);
      this.q050 = new PoolChunkList(this, this.q100, 50, 100, sizeClass.chunkSize);
      this.q025 = new PoolChunkList(this, this.q050, 25, 75, sizeClass.chunkSize);
      this.q000 = new PoolChunkList(this, this.q025, 1, 50, sizeClass.chunkSize);
      this.qInit = new PoolChunkList(this, this.q000, Integer.MIN_VALUE, 25, sizeClass.chunkSize);
      this.q100.prevList(this.q075);
      this.q075.prevList(this.q050);
      this.q050.prevList(this.q025);
      this.q025.prevList(this.q000);
      this.q000.prevList((PoolChunkList)null);
      this.qInit.prevList(this.qInit);
      List<PoolChunkListMetric> metrics = new ArrayList(6);
      metrics.add(this.qInit);
      metrics.add(this.q000);
      metrics.add(this.q025);
      metrics.add(this.q050);
      metrics.add(this.q075);
      metrics.add(this.q100);
      this.chunkListMetrics = Collections.unmodifiableList(metrics);
   }

   private PoolSubpage newSubpagePoolHead(int index) {
      PoolSubpage<T> head = new PoolSubpage(index);
      head.prev = head;
      head.next = head;
      return head;
   }

   private PoolSubpage[] newSubpagePoolArray(int size) {
      return new PoolSubpage[size];
   }

   abstract boolean isDirect();

   PooledByteBuf allocate(PoolThreadCache cache, int reqCapacity, int maxCapacity) {
      PooledByteBuf<T> buf = this.newByteBuf(maxCapacity);
      this.allocate(cache, buf, reqCapacity);
      return buf;
   }

   private void allocate(PoolThreadCache cache, PooledByteBuf buf, int reqCapacity) {
      int sizeIdx = this.sizeClass.size2SizeIdx(reqCapacity);
      if (sizeIdx <= this.sizeClass.smallMaxSizeIdx) {
         this.tcacheAllocateSmall(cache, buf, reqCapacity, sizeIdx);
      } else if (sizeIdx < this.sizeClass.nSizes) {
         this.tcacheAllocateNormal(cache, buf, reqCapacity, sizeIdx);
      } else {
         int normCapacity = this.sizeClass.directMemoryCacheAlignment > 0 ? this.sizeClass.normalizeSize(reqCapacity) : reqCapacity;
         this.allocateHuge(buf, normCapacity);
      }

   }

   private void tcacheAllocateSmall(PoolThreadCache cache, PooledByteBuf buf, int reqCapacity, int sizeIdx) {
      if (!cache.allocateSmall(this, buf, reqCapacity, sizeIdx)) {
         PoolSubpage<T> head = this.smallSubpagePools[sizeIdx];
         head.lock();

         boolean needsNormalAllocation;
         try {
            PoolSubpage<T> s = head.next;
            needsNormalAllocation = s == head;
            if (!needsNormalAllocation) {
               assert s.doNotDestroy && s.elemSize == this.sizeClass.sizeIdx2size(sizeIdx) : "doNotDestroy=" + s.doNotDestroy + ", elemSize=" + s.elemSize + ", sizeIdx=" + sizeIdx;

               long handle = s.allocate();

               assert handle >= 0L;

               s.chunk.initBufWithSubpage(buf, (ByteBuffer)null, handle, reqCapacity, cache);
            }
         } finally {
            head.unlock();
         }

         if (needsNormalAllocation) {
            this.lock();

            try {
               this.allocateNormal(buf, reqCapacity, sizeIdx, cache);
            } finally {
               this.unlock();
            }
         }

         this.incSmallAllocation();
      }
   }

   private void tcacheAllocateNormal(PoolThreadCache cache, PooledByteBuf buf, int reqCapacity, int sizeIdx) {
      if (!cache.allocateNormal(this, buf, reqCapacity, sizeIdx)) {
         this.lock();

         try {
            this.allocateNormal(buf, reqCapacity, sizeIdx, cache);
            ++this.allocationsNormal;
         } finally {
            this.unlock();
         }

      }
   }

   private void allocateNormal(PooledByteBuf buf, int reqCapacity, int sizeIdx, PoolThreadCache threadCache) {
      assert this.lock.isHeldByCurrentThread();

      if (!this.q050.allocate(buf, reqCapacity, sizeIdx, threadCache) && !this.q025.allocate(buf, reqCapacity, sizeIdx, threadCache) && !this.q000.allocate(buf, reqCapacity, sizeIdx, threadCache) && !this.qInit.allocate(buf, reqCapacity, sizeIdx, threadCache) && !this.q075.allocate(buf, reqCapacity, sizeIdx, threadCache)) {
         PoolChunk<T> c = this.newChunk(this.sizeClass.pageSize, this.sizeClass.nPSizes, this.sizeClass.pageShifts, this.sizeClass.chunkSize);
         boolean success = c.allocate(buf, reqCapacity, sizeIdx, threadCache);

         assert success;

         this.qInit.add(c);
      }
   }

   private void incSmallAllocation() {
      this.allocationsSmall.increment();
   }

   private void allocateHuge(PooledByteBuf buf, int reqCapacity) {
      PoolChunk<T> chunk = this.newUnpooledChunk(reqCapacity);
      this.activeBytesHuge.add((long)chunk.chunkSize());
      buf.initUnpooled(chunk, reqCapacity);
      this.allocationsHuge.increment();
   }

   void free(PoolChunk chunk, ByteBuffer nioBuffer, long handle, int normCapacity, PoolThreadCache cache) {
      chunk.decrementPinnedMemory(normCapacity);
      if (chunk.unpooled) {
         int size = chunk.chunkSize();
         this.destroyChunk(chunk);
         this.activeBytesHuge.add((long)(-size));
         this.deallocationsHuge.increment();
      } else {
         SizeClass sizeClass = sizeClass(handle);
         if (cache != null && cache.add(this, chunk, nioBuffer, handle, normCapacity, sizeClass)) {
            return;
         }

         this.freeChunk(chunk, handle, normCapacity, sizeClass, nioBuffer, false);
      }

   }

   private static SizeClass sizeClass(long handle) {
      return PoolChunk.isSubpage(handle) ? PoolArena.SizeClass.Small : PoolArena.SizeClass.Normal;
   }

   void freeChunk(PoolChunk chunk, long handle, int normCapacity, SizeClass sizeClass, ByteBuffer nioBuffer, boolean finalizer) {
      this.lock();

      boolean destroyChunk;
      try {
         if (!finalizer) {
            switch (sizeClass) {
               case Normal:
                  ++this.deallocationsNormal;
                  break;
               case Small:
                  ++this.deallocationsSmall;
                  break;
               default:
                  throw new Error();
            }
         }

         destroyChunk = !chunk.parent.free(chunk, handle, normCapacity, nioBuffer);
      } finally {
         this.unlock();
      }

      if (destroyChunk) {
         this.destroyChunk(chunk);
      }

   }

   void reallocate(PooledByteBuf buf, int newCapacity) {
      assert newCapacity >= 0 && newCapacity <= buf.maxCapacity();

      int oldCapacity;
      PoolChunk<T> oldChunk;
      ByteBuffer oldNioBuffer;
      long oldHandle;
      T oldMemory;
      int oldOffset;
      int oldMaxLength;
      PoolThreadCache oldCache;
      synchronized(buf) {
         oldCapacity = buf.length;
         if (oldCapacity == newCapacity) {
            return;
         }

         oldChunk = buf.chunk;
         oldNioBuffer = buf.tmpNioBuf;
         oldHandle = buf.handle;
         oldMemory = (T)buf.memory;
         oldOffset = buf.offset;
         oldMaxLength = buf.maxLength;
         oldCache = buf.cache;
         this.allocate(this.parent.threadCache(), buf, newCapacity);
      }

      int bytesToCopy;
      if (newCapacity > oldCapacity) {
         bytesToCopy = oldCapacity;
      } else {
         buf.trimIndicesToCapacity(newCapacity);
         bytesToCopy = newCapacity;
      }

      this.memoryCopy(oldMemory, oldOffset, buf, bytesToCopy);
      this.free(oldChunk, oldNioBuffer, oldHandle, oldMaxLength, oldCache);
   }

   public int numThreadCaches() {
      return this.numThreadCaches.get();
   }

   public int numTinySubpages() {
      return 0;
   }

   public int numSmallSubpages() {
      return this.smallSubpagePools.length;
   }

   public int numChunkLists() {
      return this.chunkListMetrics.size();
   }

   public List tinySubpages() {
      return Collections.emptyList();
   }

   public List smallSubpages() {
      return subPageMetricList(this.smallSubpagePools);
   }

   public List chunkLists() {
      return this.chunkListMetrics;
   }

   private static List subPageMetricList(PoolSubpage[] pages) {
      List<PoolSubpageMetric> metrics = new ArrayList();

      for(PoolSubpage head : pages) {
         if (head.next != head) {
            PoolSubpage<?> s = head.next;

            while(true) {
               metrics.add(s);
               s = s.next;
               if (s == head) {
                  break;
               }
            }
         }
      }

      return metrics;
   }

   public long numAllocations() {
      this.lock();

      long allocsNormal;
      try {
         allocsNormal = this.allocationsNormal;
      } finally {
         this.unlock();
      }

      return this.allocationsSmall.value() + allocsNormal + this.allocationsHuge.value();
   }

   public long numTinyAllocations() {
      return 0L;
   }

   public long numSmallAllocations() {
      return this.allocationsSmall.value();
   }

   public long numNormalAllocations() {
      this.lock();

      long var1;
      try {
         var1 = this.allocationsNormal;
      } finally {
         this.unlock();
      }

      return var1;
   }

   public long numDeallocations() {
      this.lock();

      long deallocs;
      try {
         deallocs = this.deallocationsSmall + this.deallocationsNormal;
      } finally {
         this.unlock();
      }

      return deallocs + this.deallocationsHuge.value();
   }

   public long numTinyDeallocations() {
      return 0L;
   }

   public long numSmallDeallocations() {
      this.lock();

      long var1;
      try {
         var1 = this.deallocationsSmall;
      } finally {
         this.unlock();
      }

      return var1;
   }

   public long numNormalDeallocations() {
      this.lock();

      long var1;
      try {
         var1 = this.deallocationsNormal;
      } finally {
         this.unlock();
      }

      return var1;
   }

   public long numHugeAllocations() {
      return this.allocationsHuge.value();
   }

   public long numHugeDeallocations() {
      return this.deallocationsHuge.value();
   }

   public long numActiveAllocations() {
      long val = this.allocationsSmall.value() + this.allocationsHuge.value() - this.deallocationsHuge.value();
      this.lock();

      try {
         val += this.allocationsNormal - (this.deallocationsSmall + this.deallocationsNormal);
      } finally {
         this.unlock();
      }

      return Math.max(val, 0L);
   }

   public long numActiveTinyAllocations() {
      return 0L;
   }

   public long numActiveSmallAllocations() {
      return Math.max(this.numSmallAllocations() - this.numSmallDeallocations(), 0L);
   }

   public long numActiveNormalAllocations() {
      this.lock();

      long val;
      try {
         val = this.allocationsNormal - this.deallocationsNormal;
      } finally {
         this.unlock();
      }

      return Math.max(val, 0L);
   }

   public long numActiveHugeAllocations() {
      return Math.max(this.numHugeAllocations() - this.numHugeDeallocations(), 0L);
   }

   public long numActiveBytes() {
      long val = this.activeBytesHuge.value();
      this.lock();

      try {
         for(int i = 0; i < this.chunkListMetrics.size(); ++i) {
            for(PoolChunkMetric m : (PoolChunkListMetric)this.chunkListMetrics.get(i)) {
               val += (long)m.chunkSize();
            }
         }
      } finally {
         this.unlock();
      }

      return Math.max(0L, val);
   }

   public long numPinnedBytes() {
      long val = this.activeBytesHuge.value();

      for(int i = 0; i < this.chunkListMetrics.size(); ++i) {
         for(PoolChunkMetric m : (PoolChunkListMetric)this.chunkListMetrics.get(i)) {
            val += (long)((PoolChunk)m).pinnedBytes();
         }
      }

      return Math.max(0L, val);
   }

   protected abstract PoolChunk newChunk(int var1, int var2, int var3, int var4);

   protected abstract PoolChunk newUnpooledChunk(int var1);

   protected abstract PooledByteBuf newByteBuf(int var1);

   protected abstract void memoryCopy(Object var1, int var2, PooledByteBuf var3, int var4);

   protected abstract void destroyChunk(PoolChunk var1);

   public String toString() {
      this.lock();

      String var2;
      try {
         StringBuilder buf = (new StringBuilder()).append("Chunk(s) at 0~25%:").append(StringUtil.NEWLINE).append(this.qInit).append(StringUtil.NEWLINE).append("Chunk(s) at 0~50%:").append(StringUtil.NEWLINE).append(this.q000).append(StringUtil.NEWLINE).append("Chunk(s) at 25~75%:").append(StringUtil.NEWLINE).append(this.q025).append(StringUtil.NEWLINE).append("Chunk(s) at 50~100%:").append(StringUtil.NEWLINE).append(this.q050).append(StringUtil.NEWLINE).append("Chunk(s) at 75~100%:").append(StringUtil.NEWLINE).append(this.q075).append(StringUtil.NEWLINE).append("Chunk(s) at 100%:").append(StringUtil.NEWLINE).append(this.q100).append(StringUtil.NEWLINE).append("small subpages:");
         appendPoolSubPages(buf, this.smallSubpagePools);
         buf.append(StringUtil.NEWLINE);
         var2 = buf.toString();
      } finally {
         this.unlock();
      }

      return var2;
   }

   private static void appendPoolSubPages(StringBuilder buf, PoolSubpage[] subpages) {
      for(int i = 0; i < subpages.length; ++i) {
         PoolSubpage<?> head = subpages[i];
         if (head.next != head && head.next != null) {
            buf.append(StringUtil.NEWLINE).append(i).append(": ");
            PoolSubpage<?> s = head.next;

            while(s != null) {
               buf.append(s);
               s = s.next;
               if (s == head) {
                  break;
               }
            }
         }
      }

   }

   protected final void finalize() throws Throwable {
      try {
         super.finalize();
      } finally {
         destroyPoolSubPages(this.smallSubpagePools);
         this.destroyPoolChunkLists(this.qInit, this.q000, this.q025, this.q050, this.q075, this.q100);
      }

   }

   private static void destroyPoolSubPages(PoolSubpage[] pages) {
      for(PoolSubpage page : pages) {
         page.destroy();
      }

   }

   private void destroyPoolChunkLists(PoolChunkList... chunkLists) {
      for(PoolChunkList chunkList : chunkLists) {
         chunkList.destroy(this);
      }

   }

   void lock() {
      this.lock.lock();
   }

   void unlock() {
      this.lock.unlock();
   }

   public int sizeIdx2size(int sizeIdx) {
      return this.sizeClass.sizeIdx2size(sizeIdx);
   }

   public int sizeIdx2sizeCompute(int sizeIdx) {
      return this.sizeClass.sizeIdx2sizeCompute(sizeIdx);
   }

   public long pageIdx2size(int pageIdx) {
      return this.sizeClass.pageIdx2size(pageIdx);
   }

   public long pageIdx2sizeCompute(int pageIdx) {
      return this.sizeClass.pageIdx2sizeCompute(pageIdx);
   }

   public int size2SizeIdx(int size) {
      return this.sizeClass.size2SizeIdx(size);
   }

   public int pages2pageIdx(int pages) {
      return this.sizeClass.pages2pageIdx(pages);
   }

   public int pages2pageIdxFloor(int pages) {
      return this.sizeClass.pages2pageIdxFloor(pages);
   }

   public int normalizeSize(int size) {
      return this.sizeClass.normalizeSize(size);
   }

   static enum SizeClass {
      Small,
      Normal;
   }

   static final class HeapArena extends PoolArena {
      private final AtomicReference lastDestroyedChunk = new AtomicReference();

      HeapArena(PooledByteBufAllocator parent, SizeClasses sizeClass) {
         super(parent, sizeClass);
      }

      private static byte[] newByteArray(int size) {
         return PlatformDependent.allocateUninitializedArray(size);
      }

      boolean isDirect() {
         return false;
      }

      protected PoolChunk newChunk(int pageSize, int maxPageIdx, int pageShifts, int chunkSize) {
         PoolChunk<byte[]> chunk = (PoolChunk)this.lastDestroyedChunk.getAndSet((Object)null);
         if (chunk == null) {
            return new PoolChunk(this, (Object)null, newByteArray(chunkSize), pageSize, pageShifts, chunkSize, maxPageIdx);
         } else {
            assert chunk.chunkSize == chunkSize && chunk.pageSize == pageSize && chunk.maxPageIdx == maxPageIdx && chunk.pageShifts == pageShifts;

            return chunk;
         }
      }

      protected PoolChunk newUnpooledChunk(int capacity) {
         return new PoolChunk(this, (Object)null, newByteArray(capacity), capacity);
      }

      protected void destroyChunk(PoolChunk chunk) {
         if (!chunk.unpooled && this.lastDestroyedChunk.get() == null) {
            this.lastDestroyedChunk.set(chunk);
         }

      }

      protected PooledByteBuf newByteBuf(int maxCapacity) {
         return (PooledByteBuf)(PoolArena.HAS_UNSAFE ? PooledUnsafeHeapByteBuf.newUnsafeInstance(maxCapacity) : PooledHeapByteBuf.newInstance(maxCapacity));
      }

      protected void memoryCopy(byte[] src, int srcOffset, PooledByteBuf dst, int length) {
         if (length != 0) {
            System.arraycopy(src, srcOffset, dst.memory, dst.offset, length);
         }
      }
   }

   static final class DirectArena extends PoolArena {
      DirectArena(PooledByteBufAllocator parent, SizeClasses sizeClass) {
         super(parent, sizeClass);
      }

      boolean isDirect() {
         return true;
      }

      protected PoolChunk newChunk(int pageSize, int maxPageIdx, int pageShifts, int chunkSize) {
         if (this.sizeClass.directMemoryCacheAlignment == 0) {
            ByteBuffer memory = allocateDirect(chunkSize);
            return new PoolChunk(this, memory, memory, pageSize, pageShifts, chunkSize, maxPageIdx);
         } else {
            ByteBuffer base = allocateDirect(chunkSize + this.sizeClass.directMemoryCacheAlignment);
            ByteBuffer memory = PlatformDependent.alignDirectBuffer(base, this.sizeClass.directMemoryCacheAlignment);
            return new PoolChunk(this, base, memory, pageSize, pageShifts, chunkSize, maxPageIdx);
         }
      }

      protected PoolChunk newUnpooledChunk(int capacity) {
         if (this.sizeClass.directMemoryCacheAlignment == 0) {
            ByteBuffer memory = allocateDirect(capacity);
            return new PoolChunk(this, memory, memory, capacity);
         } else {
            ByteBuffer base = allocateDirect(capacity + this.sizeClass.directMemoryCacheAlignment);
            ByteBuffer memory = PlatformDependent.alignDirectBuffer(base, this.sizeClass.directMemoryCacheAlignment);
            return new PoolChunk(this, base, memory, capacity);
         }
      }

      private static ByteBuffer allocateDirect(int capacity) {
         return PlatformDependent.useDirectBufferNoCleaner() ? PlatformDependent.allocateDirectNoCleaner(capacity) : ByteBuffer.allocateDirect(capacity);
      }

      protected void destroyChunk(PoolChunk chunk) {
         if (PlatformDependent.useDirectBufferNoCleaner()) {
            PlatformDependent.freeDirectNoCleaner((ByteBuffer)chunk.base);
         } else {
            PlatformDependent.freeDirectBuffer((ByteBuffer)chunk.base);
         }

      }

      protected PooledByteBuf newByteBuf(int maxCapacity) {
         return (PooledByteBuf)(PoolArena.HAS_UNSAFE ? PooledUnsafeDirectByteBuf.newInstance(maxCapacity) : PooledDirectByteBuf.newInstance(maxCapacity));
      }

      protected void memoryCopy(ByteBuffer src, int srcOffset, PooledByteBuf dstBuf, int length) {
         if (length != 0) {
            if (PoolArena.HAS_UNSAFE) {
               PlatformDependent.copyMemory(PlatformDependent.directBufferAddress(src) + (long)srcOffset, PlatformDependent.directBufferAddress((ByteBuffer)dstBuf.memory) + (long)dstBuf.offset, (long)length);
            } else {
               src = src.duplicate();
               ByteBuffer dst = dstBuf.internalNioBuffer();
               src.position(srcOffset).limit(srcOffset + length);
               dst.position(dstBuf.offset);
               dst.put(src);
            }

         }
      }
   }
}
