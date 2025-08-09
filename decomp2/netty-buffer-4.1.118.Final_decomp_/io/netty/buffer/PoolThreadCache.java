package io.netty.buffer;

import io.netty.util.Recycler;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectPool;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

final class PoolThreadCache {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(PoolThreadCache.class);
   private static final int INTEGER_SIZE_MINUS_ONE = 31;
   final PoolArena heapArena;
   final PoolArena directArena;
   private final MemoryRegionCache[] smallSubPageHeapCaches;
   private final MemoryRegionCache[] smallSubPageDirectCaches;
   private final MemoryRegionCache[] normalHeapCaches;
   private final MemoryRegionCache[] normalDirectCaches;
   private final int freeSweepAllocationThreshold;
   private final AtomicBoolean freed = new AtomicBoolean();
   private final FreeOnFinalize freeOnFinalize;
   private int allocations;

   PoolThreadCache(PoolArena heapArena, PoolArena directArena, int smallCacheSize, int normalCacheSize, int maxCachedBufferCapacity, int freeSweepAllocationThreshold, boolean useFinalizer) {
      ObjectUtil.checkPositiveOrZero(maxCachedBufferCapacity, "maxCachedBufferCapacity");
      this.freeSweepAllocationThreshold = freeSweepAllocationThreshold;
      this.heapArena = heapArena;
      this.directArena = directArena;
      if (directArena != null) {
         this.smallSubPageDirectCaches = createSubPageCaches(smallCacheSize, directArena.sizeClass.nSubpages);
         this.normalDirectCaches = createNormalCaches(normalCacheSize, maxCachedBufferCapacity, directArena);
         directArena.numThreadCaches.getAndIncrement();
      } else {
         this.smallSubPageDirectCaches = null;
         this.normalDirectCaches = null;
      }

      if (heapArena != null) {
         this.smallSubPageHeapCaches = createSubPageCaches(smallCacheSize, heapArena.sizeClass.nSubpages);
         this.normalHeapCaches = createNormalCaches(normalCacheSize, maxCachedBufferCapacity, heapArena);
         heapArena.numThreadCaches.getAndIncrement();
      } else {
         this.smallSubPageHeapCaches = null;
         this.normalHeapCaches = null;
      }

      if ((this.smallSubPageDirectCaches != null || this.normalDirectCaches != null || this.smallSubPageHeapCaches != null || this.normalHeapCaches != null) && freeSweepAllocationThreshold < 1) {
         throw new IllegalArgumentException("freeSweepAllocationThreshold: " + freeSweepAllocationThreshold + " (expected: > 0)");
      } else {
         this.freeOnFinalize = useFinalizer ? new FreeOnFinalize(this) : null;
      }
   }

   private static MemoryRegionCache[] createSubPageCaches(int cacheSize, int numCaches) {
      if (cacheSize > 0 && numCaches > 0) {
         MemoryRegionCache<T>[] cache = new MemoryRegionCache[numCaches];

         for(int i = 0; i < cache.length; ++i) {
            cache[i] = new SubPageMemoryRegionCache(cacheSize);
         }

         return cache;
      } else {
         return null;
      }
   }

   private static MemoryRegionCache[] createNormalCaches(int cacheSize, int maxCachedBufferCapacity, PoolArena area) {
      if (cacheSize > 0 && maxCachedBufferCapacity > 0) {
         int max = Math.min(area.sizeClass.chunkSize, maxCachedBufferCapacity);
         List<MemoryRegionCache<T>> cache = new ArrayList();

         for(int idx = area.sizeClass.nSubpages; idx < area.sizeClass.nSizes && area.sizeClass.sizeIdx2size(idx) <= max; ++idx) {
            cache.add(new NormalMemoryRegionCache(cacheSize));
         }

         return (MemoryRegionCache[])cache.toArray(new MemoryRegionCache[0]);
      } else {
         return null;
      }
   }

   static int log2(int val) {
      return 31 - Integer.numberOfLeadingZeros(val);
   }

   boolean allocateSmall(PoolArena area, PooledByteBuf buf, int reqCapacity, int sizeIdx) {
      return this.allocate(this.cacheForSmall(area, sizeIdx), buf, reqCapacity);
   }

   boolean allocateNormal(PoolArena area, PooledByteBuf buf, int reqCapacity, int sizeIdx) {
      return this.allocate(this.cacheForNormal(area, sizeIdx), buf, reqCapacity);
   }

   private boolean allocate(MemoryRegionCache cache, PooledByteBuf buf, int reqCapacity) {
      if (cache == null) {
         return false;
      } else {
         boolean allocated = cache.allocate(buf, reqCapacity, this);
         if (++this.allocations >= this.freeSweepAllocationThreshold) {
            this.allocations = 0;
            this.trim();
         }

         return allocated;
      }
   }

   boolean add(PoolArena area, PoolChunk chunk, ByteBuffer nioBuffer, long handle, int normCapacity, PoolArena.SizeClass sizeClass) {
      int sizeIdx = area.sizeClass.size2SizeIdx(normCapacity);
      MemoryRegionCache<?> cache = this.cache(area, sizeIdx, sizeClass);
      if (cache == null) {
         return false;
      } else {
         return this.freed.get() ? false : cache.add(chunk, nioBuffer, handle, normCapacity);
      }
   }

   private MemoryRegionCache cache(PoolArena area, int sizeIdx, PoolArena.SizeClass sizeClass) {
      switch (sizeClass) {
         case Normal:
            return this.cacheForNormal(area, sizeIdx);
         case Small:
            return this.cacheForSmall(area, sizeIdx);
         default:
            throw new Error();
      }
   }

   void free(boolean finalizer) {
      if (this.freed.compareAndSet(false, true)) {
         if (this.freeOnFinalize != null) {
            this.freeOnFinalize.cache = null;
         }

         int numFreed = free(this.smallSubPageDirectCaches, finalizer) + free(this.normalDirectCaches, finalizer) + free(this.smallSubPageHeapCaches, finalizer) + free(this.normalHeapCaches, finalizer);
         if (numFreed > 0 && logger.isDebugEnabled()) {
            logger.debug("Freed {} thread-local buffer(s) from thread: {}", numFreed, Thread.currentThread().getName());
         }

         if (this.directArena != null) {
            this.directArena.numThreadCaches.getAndDecrement();
         }

         if (this.heapArena != null) {
            this.heapArena.numThreadCaches.getAndDecrement();
         }
      }

   }

   private static int free(MemoryRegionCache[] caches, boolean finalizer) {
      if (caches == null) {
         return 0;
      } else {
         int numFreed = 0;

         for(MemoryRegionCache c : caches) {
            numFreed += free(c, finalizer);
         }

         return numFreed;
      }
   }

   private static int free(MemoryRegionCache cache, boolean finalizer) {
      return cache == null ? 0 : cache.free(finalizer);
   }

   void trim() {
      trim(this.smallSubPageDirectCaches);
      trim(this.normalDirectCaches);
      trim(this.smallSubPageHeapCaches);
      trim(this.normalHeapCaches);
   }

   private static void trim(MemoryRegionCache[] caches) {
      if (caches != null) {
         for(MemoryRegionCache c : caches) {
            trim(c);
         }

      }
   }

   private static void trim(MemoryRegionCache cache) {
      if (cache != null) {
         cache.trim();
      }
   }

   private MemoryRegionCache cacheForSmall(PoolArena area, int sizeIdx) {
      return area.isDirect() ? cache(this.smallSubPageDirectCaches, sizeIdx) : cache(this.smallSubPageHeapCaches, sizeIdx);
   }

   private MemoryRegionCache cacheForNormal(PoolArena area, int sizeIdx) {
      int idx = sizeIdx - area.sizeClass.nSubpages;
      return area.isDirect() ? cache(this.normalDirectCaches, idx) : cache(this.normalHeapCaches, idx);
   }

   private static MemoryRegionCache cache(MemoryRegionCache[] cache, int sizeIdx) {
      return cache != null && sizeIdx <= cache.length - 1 ? cache[sizeIdx] : null;
   }

   private static final class SubPageMemoryRegionCache extends MemoryRegionCache {
      SubPageMemoryRegionCache(int size) {
         super(size, PoolArena.SizeClass.Small);
      }

      protected void initBuf(PoolChunk chunk, ByteBuffer nioBuffer, long handle, PooledByteBuf buf, int reqCapacity, PoolThreadCache threadCache) {
         chunk.initBufWithSubpage(buf, nioBuffer, handle, reqCapacity, threadCache);
      }
   }

   private static final class NormalMemoryRegionCache extends MemoryRegionCache {
      NormalMemoryRegionCache(int size) {
         super(size, PoolArena.SizeClass.Normal);
      }

      protected void initBuf(PoolChunk chunk, ByteBuffer nioBuffer, long handle, PooledByteBuf buf, int reqCapacity, PoolThreadCache threadCache) {
         chunk.initBuf(buf, nioBuffer, handle, reqCapacity, threadCache);
      }
   }

   private abstract static class MemoryRegionCache {
      private final int size;
      private final Queue queue;
      private final PoolArena.SizeClass sizeClass;
      private int allocations;
      private static final ObjectPool RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator() {
         public Entry newObject(ObjectPool.Handle handle) {
            return new Entry(handle);
         }
      });

      MemoryRegionCache(int size, PoolArena.SizeClass sizeClass) {
         this.size = MathUtil.safeFindNextPositivePowerOfTwo(size);
         this.queue = PlatformDependent.newFixedMpscUnpaddedQueue(this.size);
         this.sizeClass = sizeClass;
      }

      protected abstract void initBuf(PoolChunk var1, ByteBuffer var2, long var3, PooledByteBuf var5, int var6, PoolThreadCache var7);

      public final boolean add(PoolChunk chunk, ByteBuffer nioBuffer, long handle, int normCapacity) {
         Entry<T> entry = newEntry(chunk, nioBuffer, handle, normCapacity);
         boolean queued = this.queue.offer(entry);
         if (!queued) {
            entry.unguardedRecycle();
         }

         return queued;
      }

      public final boolean allocate(PooledByteBuf buf, int reqCapacity, PoolThreadCache threadCache) {
         Entry<T> entry = (Entry)this.queue.poll();
         if (entry == null) {
            return false;
         } else {
            this.initBuf(entry.chunk, entry.nioBuffer, entry.handle, buf, reqCapacity, threadCache);
            entry.unguardedRecycle();
            ++this.allocations;
            return true;
         }
      }

      public final int free(boolean finalizer) {
         return this.free(Integer.MAX_VALUE, finalizer);
      }

      private int free(int max, boolean finalizer) {
         int numFreed;
         for(numFreed = 0; numFreed < max; ++numFreed) {
            Entry<T> entry = (Entry)this.queue.poll();
            if (entry == null) {
               return numFreed;
            }

            this.freeEntry(entry, finalizer);
         }

         return numFreed;
      }

      public final void trim() {
         int free = this.size - this.allocations;
         this.allocations = 0;
         if (free > 0) {
            this.free(free, false);
         }

      }

      private void freeEntry(Entry entry, boolean finalizer) {
         PoolChunk chunk = entry.chunk;
         long handle = entry.handle;
         ByteBuffer nioBuffer = entry.nioBuffer;
         int normCapacity = entry.normCapacity;
         if (!finalizer) {
            entry.recycle();
         }

         chunk.arena.freeChunk(chunk, handle, normCapacity, this.sizeClass, nioBuffer, finalizer);
      }

      private static Entry newEntry(PoolChunk chunk, ByteBuffer nioBuffer, long handle, int normCapacity) {
         Entry entry = (Entry)RECYCLER.get();
         entry.chunk = chunk;
         entry.nioBuffer = nioBuffer;
         entry.handle = handle;
         entry.normCapacity = normCapacity;
         return entry;
      }

      static final class Entry {
         final Recycler.EnhancedHandle recyclerHandle;
         PoolChunk chunk;
         ByteBuffer nioBuffer;
         long handle = -1L;
         int normCapacity;

         Entry(ObjectPool.Handle recyclerHandle) {
            this.recyclerHandle = (Recycler.EnhancedHandle)recyclerHandle;
         }

         void recycle() {
            this.chunk = null;
            this.nioBuffer = null;
            this.handle = -1L;
            this.recyclerHandle.recycle(this);
         }

         void unguardedRecycle() {
            this.chunk = null;
            this.nioBuffer = null;
            this.handle = -1L;
            this.recyclerHandle.unguardedRecycle(this);
         }
      }
   }

   private static final class FreeOnFinalize {
      private volatile PoolThreadCache cache;

      private FreeOnFinalize(PoolThreadCache cache) {
         this.cache = cache;
      }

      protected void finalize() throws Throwable {
         try {
            super.finalize();
         } finally {
            PoolThreadCache cache = this.cache;
            this.cache = null;
            if (cache != null) {
               cache.free(true);
            }

         }

      }
   }
}
