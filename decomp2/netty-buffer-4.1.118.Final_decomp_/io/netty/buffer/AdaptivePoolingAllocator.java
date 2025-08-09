package io.netty.buffer;

import io.netty.util.ByteProcessor;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.NettyRuntime;
import io.netty.util.Recycler;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import io.netty.util.internal.ObjectPool;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ReferenceCountUpdater;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThreadExecutorMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.StampedLock;

@SuppressJava6Requirement(
   reason = "Guarded by version check"
)
final class AdaptivePoolingAllocator implements AdaptiveByteBufAllocator.AdaptiveAllocatorApi {
   private static final int MIN_CHUNK_SIZE = 131072;
   private static final int EXPANSION_ATTEMPTS = 3;
   private static final int INITIAL_MAGAZINES = 4;
   private static final int RETIRE_CAPACITY = 4096;
   private static final int MAX_STRIPES = NettyRuntime.availableProcessors() * 2;
   private static final int BUFS_PER_CHUNK = 10;
   private static final int MAX_CHUNK_SIZE = 10485760;
   private static final int CENTRAL_QUEUE_CAPACITY = Math.max(2, SystemPropertyUtil.getInt("io.netty.allocator.centralQueueCapacity", NettyRuntime.availableProcessors()));
   private static final int MAGAZINE_BUFFER_QUEUE_CAPACITY = SystemPropertyUtil.getInt("io.netty.allocator.magazineBufferQueueCapacity", 1024);
   private static final Object NO_MAGAZINE;
   private final ChunkAllocator chunkAllocator;
   private final Queue centralQueue;
   private final StampedLock magazineExpandLock;
   private volatile Magazine[] magazines;
   private final FastThreadLocal threadLocalMagazine;
   private final Set liveCachedMagazines;
   private volatile boolean freed;

   AdaptivePoolingAllocator(ChunkAllocator chunkAllocator, MagazineCaching magazineCaching) {
      ObjectUtil.checkNotNull(chunkAllocator, "chunkAllocator");
      ObjectUtil.checkNotNull(magazineCaching, "magazineCaching");
      this.chunkAllocator = chunkAllocator;
      this.centralQueue = (Queue)ObjectUtil.checkNotNull(createSharedChunkQueue(), "centralQueue");
      this.magazineExpandLock = new StampedLock();
      if (magazineCaching != AdaptivePoolingAllocator.MagazineCaching.None) {
         assert magazineCaching == AdaptivePoolingAllocator.MagazineCaching.EventLoopThreads || magazineCaching == AdaptivePoolingAllocator.MagazineCaching.FastThreadLocalThreads;

         final boolean cachedMagazinesNonEventLoopThreads = magazineCaching == AdaptivePoolingAllocator.MagazineCaching.FastThreadLocalThreads;
         final Set<Magazine> liveMagazines = new CopyOnWriteArraySet();
         this.threadLocalMagazine = new FastThreadLocal() {
            protected Object initialValue() {
               if (!cachedMagazinesNonEventLoopThreads && ThreadExecutorMap.currentExecutor() == null) {
                  return AdaptivePoolingAllocator.NO_MAGAZINE;
               } else if (!FastThreadLocalThread.willCleanupFastThreadLocals(Thread.currentThread())) {
                  return AdaptivePoolingAllocator.NO_MAGAZINE;
               } else {
                  Magazine mag = new Magazine(AdaptivePoolingAllocator.this, false);
                  liveMagazines.add(mag);
                  return mag;
               }
            }

            protected void onRemoval(Object value) throws Exception {
               if (value != AdaptivePoolingAllocator.NO_MAGAZINE) {
                  liveMagazines.remove(value);
               }

            }
         };
         this.liveCachedMagazines = liveMagazines;
      } else {
         this.threadLocalMagazine = null;
         this.liveCachedMagazines = null;
      }

      Magazine[] mags = new Magazine[4];

      for(int i = 0; i < mags.length; ++i) {
         mags[i] = new Magazine(this);
      }

      this.magazines = mags;
   }

   private static Queue createSharedChunkQueue() {
      return PlatformDependent.newFixedMpmcQueue(CENTRAL_QUEUE_CAPACITY);
   }

   public ByteBuf allocate(int size, int maxCapacity) {
      return this.allocate(size, maxCapacity, Thread.currentThread(), (AdaptiveByteBuf)null);
   }

   private AdaptiveByteBuf allocate(int size, int maxCapacity, Thread currentThread, AdaptiveByteBuf buf) {
      if (size <= 10485760) {
         int sizeBucket = AdaptivePoolingAllocator.AllocationStatistics.sizeBucket(size);
         FastThreadLocal<Object> threadLocalMagazine = this.threadLocalMagazine;
         if (threadLocalMagazine != null && currentThread instanceof FastThreadLocalThread) {
            Object mag = threadLocalMagazine.get();
            if (mag != NO_MAGAZINE) {
               Magazine magazine = (Magazine)mag;
               if (buf == null) {
                  buf = magazine.newBuffer();
               }

               boolean allocated = magazine.tryAllocate(size, sizeBucket, maxCapacity, buf);

               assert allocated : "Allocation of threadLocalMagazine must always succeed";

               return buf;
            }
         }

         long threadId = currentThread.getId();
         int expansions = 0;

         Magazine[] mags;
         do {
            mags = this.magazines;
            int mask = mags.length - 1;
            int index = (int)(threadId & (long)mask);
            int i = 0;

            for(int m = Integer.numberOfTrailingZeros(~mask); i < m; ++i) {
               Magazine mag = mags[index + i & mask];
               if (buf == null) {
                  buf = mag.newBuffer();
               }

               if (mag.tryAllocate(size, sizeBucket, maxCapacity, buf)) {
                  return buf;
               }
            }

            ++expansions;
         } while(expansions <= 3 && this.tryExpandMagazines(mags.length));
      }

      return this.allocateFallback(size, maxCapacity, currentThread, buf);
   }

   private AdaptiveByteBuf allocateFallback(int size, int maxCapacity, Thread currentThread, AdaptiveByteBuf buf) {
      Magazine magazine;
      if (buf != null) {
         Chunk chunk = buf.chunk;
         if (chunk == null || chunk == AdaptivePoolingAllocator.Magazine.MAGAZINE_FREED || (magazine = chunk.currentMagazine()) == null) {
            magazine = this.getFallbackMagazine(currentThread);
         }
      } else {
         magazine = this.getFallbackMagazine(currentThread);
         buf = magazine.newBuffer();
      }

      AbstractByteBuf innerChunk = this.chunkAllocator.allocate(size, maxCapacity);
      Chunk chunk = new Chunk(innerChunk, magazine, false);

      try {
         chunk.readInitInto(buf, size, maxCapacity);
      } finally {
         chunk.release();
      }

      return buf;
   }

   private Magazine getFallbackMagazine(Thread currentThread) {
      FastThreadLocal<Object> threadLocalMagazine = this.threadLocalMagazine;
      Object tlMag;
      if (threadLocalMagazine != null && currentThread instanceof FastThreadLocalThread && (tlMag = threadLocalMagazine.get()) != NO_MAGAZINE) {
         return (Magazine)tlMag;
      } else {
         Magazine[] mags = this.magazines;
         return mags[(int)currentThread.getId() & mags.length - 1];
      }
   }

   void allocate(int size, int maxCapacity, AdaptiveByteBuf into) {
      AdaptiveByteBuf result = this.allocate(size, maxCapacity, Thread.currentThread(), into);

      assert result == into : "Re-allocation created separate buffer instance";

   }

   public long usedMemory() {
      long sum = 0L;

      for(Chunk chunk : this.centralQueue) {
         sum += (long)chunk.capacity();
      }

      for(Magazine magazine : this.magazines) {
         sum += magazine.usedMemory.get();
      }

      if (this.liveCachedMagazines != null) {
         for(Magazine magazine : this.liveCachedMagazines) {
            sum += magazine.usedMemory.get();
         }
      }

      return sum;
   }

   private boolean tryExpandMagazines(int currentLength) {
      if (currentLength >= MAX_STRIPES) {
         return true;
      } else {
         long writeLock = this.magazineExpandLock.tryWriteLock();
         if (writeLock != 0L) {
            Magazine[] mags;
            try {
               mags = this.magazines;
               if (mags.length >= MAX_STRIPES || mags.length > currentLength || this.freed) {
                  boolean var13 = true;
                  return var13;
               }

               int preferredChunkSize = mags[0].sharedPrefChunkSize;
               Magazine[] expanded = new Magazine[mags.length * 2];
               int i = 0;

               for(int l = expanded.length; i < l; ++i) {
                  Magazine m = new Magazine(this);
                  m.localPrefChunkSize = preferredChunkSize;
                  m.sharedPrefChunkSize = preferredChunkSize;
                  expanded[i] = m;
               }

               this.magazines = expanded;
            } finally {
               this.magazineExpandLock.unlockWrite(writeLock);
            }

            for(Magazine magazine : mags) {
               magazine.free();
            }

            return true;
         } else {
            return true;
         }
      }
   }

   private boolean offerToQueue(Chunk buffer) {
      if (this.freed) {
         return false;
      } else {
         assert buffer.allocatedBytes == 0;

         assert buffer.magazine == null;

         boolean isAdded = this.centralQueue.offer(buffer);
         if (this.freed && isAdded) {
            this.freeCentralQueue();
         }

         return isAdded;
      }
   }

   protected void finalize() throws Throwable {
      try {
         super.finalize();
      } finally {
         this.free();
      }

   }

   private void free() {
      this.freed = true;
      long stamp = this.magazineExpandLock.writeLock();

      try {
         Magazine[] mags = this.magazines;

         for(Magazine magazine : mags) {
            magazine.free();
         }
      } finally {
         this.magazineExpandLock.unlockWrite(stamp);
      }

      this.freeCentralQueue();
   }

   private void freeCentralQueue() {
      while(true) {
         Chunk chunk = (Chunk)this.centralQueue.poll();
         if (chunk == null) {
            return;
         }

         chunk.release();
      }
   }

   static int sizeBucket(int size) {
      return AdaptivePoolingAllocator.AllocationStatistics.sizeBucket(size);
   }

   static {
      NO_MAGAZINE = Boolean.TRUE;
      if (CENTRAL_QUEUE_CAPACITY < 2) {
         throw new IllegalArgumentException("CENTRAL_QUEUE_CAPACITY: " + CENTRAL_QUEUE_CAPACITY + " (expected: >= " + 2 + ')');
      } else if (MAGAZINE_BUFFER_QUEUE_CAPACITY < 2) {
         throw new IllegalArgumentException("MAGAZINE_BUFFER_QUEUE_CAPACITY: " + MAGAZINE_BUFFER_QUEUE_CAPACITY + " (expected: >= " + 2 + ')');
      }
   }

   static enum MagazineCaching {
      EventLoopThreads,
      FastThreadLocalThreads,
      None;
   }

   private static class AllocationStatistics {
      private static final int MIN_DATUM_TARGET = 1024;
      private static final int MAX_DATUM_TARGET = 65534;
      private static final int INIT_DATUM_TARGET = 9;
      private static final int HISTO_MIN_BUCKET_SHIFT = 13;
      private static final int HISTO_MAX_BUCKET_SHIFT = 20;
      private static final int HISTO_BUCKET_COUNT = 8;
      private static final int HISTO_MAX_BUCKET_MASK = 7;
      private static final int SIZE_MAX_MASK = 10485759;
      protected final AdaptivePoolingAllocator parent;
      private final boolean shareable;
      private final short[][] histos;
      private short[] histo;
      private final int[] sums;
      private int histoIndex;
      private int datumCount;
      private int datumTarget;
      protected volatile int sharedPrefChunkSize;
      protected volatile int localPrefChunkSize;

      private AllocationStatistics(AdaptivePoolingAllocator parent, boolean shareable) {
         this.histos = new short[][]{new short[8], new short[8], new short[8], new short[8]};
         this.histo = this.histos[0];
         this.sums = new int[8];
         this.datumTarget = 9;
         this.sharedPrefChunkSize = 131072;
         this.localPrefChunkSize = 131072;
         this.parent = parent;
         this.shareable = shareable;
      }

      protected void recordAllocationSize(int bucket) {
         ++this.histo[bucket];
         if (this.datumCount++ == this.datumTarget) {
            this.rotateHistograms();
         }

      }

      static int sizeBucket(int size) {
         if (size == 0) {
            return 0;
         } else {
            int normalizedSize = size - 1 >> 13 & 10485759;
            return Math.min(32 - Integer.numberOfLeadingZeros(normalizedSize), 7);
         }
      }

      private void rotateHistograms() {
         short[][] hs = this.histos;

         for(int i = 0; i < 8; ++i) {
            this.sums[i] = (hs[0][i] & '\uffff') + (hs[1][i] & '\uffff') + (hs[2][i] & '\uffff') + (hs[3][i] & '\uffff');
         }

         int sum = 0;

         for(int count : this.sums) {
            sum += count;
         }

         int targetPercentile = (int)((double)sum * 0.99);

         int sizeBucket;
         for(sizeBucket = 0; sizeBucket < this.sums.length && this.sums[sizeBucket] <= targetPercentile; ++sizeBucket) {
            targetPercentile -= this.sums[sizeBucket];
         }

         int percentileSize = 1 << sizeBucket + 13;
         int prefChunkSize = Math.max(percentileSize * 10, 131072);
         this.localPrefChunkSize = prefChunkSize;
         if (this.shareable) {
            for(Magazine mag : this.parent.magazines) {
               prefChunkSize = Math.max(prefChunkSize, mag.localPrefChunkSize);
            }
         }

         if (this.sharedPrefChunkSize != prefChunkSize) {
            this.datumTarget = Math.max(this.datumTarget >> 1, 1024);
            this.sharedPrefChunkSize = prefChunkSize;
         } else {
            this.datumTarget = Math.min(this.datumTarget << 1, 65534);
         }

         this.histoIndex = this.histoIndex + 1 & 3;
         this.histo = this.histos[this.histoIndex];
         this.datumCount = 0;
         Arrays.fill(this.histo, (short)0);
      }

      protected int preferredChunkSize() {
         return this.sharedPrefChunkSize;
      }
   }

   @SuppressJava6Requirement(
      reason = "Guarded by version check"
   )
   private static final class Magazine extends AllocationStatistics {
      private static final AtomicReferenceFieldUpdater NEXT_IN_LINE = AtomicReferenceFieldUpdater.newUpdater(Magazine.class, Chunk.class, "nextInLine");
      private static final Chunk MAGAZINE_FREED = new Chunk();
      private static final ObjectPool EVENT_LOOP_LOCAL_BUFFER_POOL = ObjectPool.newPool(new ObjectPool.ObjectCreator() {
         public AdaptiveByteBuf newObject(ObjectPool.Handle handle) {
            return new AdaptiveByteBuf(handle);
         }
      });
      private Chunk current;
      private volatile Chunk nextInLine;
      private final AtomicLong usedMemory;
      private final StampedLock allocationLock;
      private final Queue bufferQueue;
      private final ObjectPool.Handle handle;

      Magazine(AdaptivePoolingAllocator parent) {
         this(parent, true);
      }

      Magazine(AdaptivePoolingAllocator parent, boolean shareable) {
         super(parent, shareable, null);
         if (shareable) {
            this.allocationLock = new StampedLock();
            this.bufferQueue = PlatformDependent.newFixedMpmcQueue(AdaptivePoolingAllocator.MAGAZINE_BUFFER_QUEUE_CAPACITY);
            this.handle = new ObjectPool.Handle() {
               public void recycle(AdaptiveByteBuf self) {
                  Magazine.this.bufferQueue.offer(self);
               }
            };
         } else {
            this.allocationLock = null;
            this.bufferQueue = null;
            this.handle = null;
         }

         this.usedMemory = new AtomicLong();
      }

      public boolean tryAllocate(int size, int sizeBucket, int maxCapacity, AdaptiveByteBuf buf) {
         if (this.allocationLock == null) {
            return this.allocate(size, sizeBucket, maxCapacity, buf);
         } else {
            long writeLock = this.allocationLock.tryWriteLock();
            if (writeLock != 0L) {
               boolean var7;
               try {
                  var7 = this.allocate(size, sizeBucket, maxCapacity, buf);
               } finally {
                  this.allocationLock.unlockWrite(writeLock);
               }

               return var7;
            } else {
               return this.allocateWithoutLock(size, maxCapacity, buf);
            }
         }
      }

      private boolean allocateWithoutLock(int size, int maxCapacity, AdaptiveByteBuf buf) {
         Chunk curr = (Chunk)NEXT_IN_LINE.getAndSet(this, (Object)null);
         if (curr == MAGAZINE_FREED) {
            this.restoreMagazineFreed();
            return false;
         } else {
            if (curr == null) {
               curr = (Chunk)this.parent.centralQueue.poll();
               if (curr == null) {
                  return false;
               }

               curr.attachToMagazine(this);
            }

            if (curr.remainingCapacity() >= size) {
               curr.readInitInto(buf, size, maxCapacity);
            }

            try {
               if (curr.remainingCapacity() >= 4096) {
                  this.transferToNextInLineOrRelease(curr);
                  curr = null;
               }
            } finally {
               if (curr != null) {
                  curr.release();
               }

            }

            return true;
         }
      }

      private boolean allocate(int size, int sizeBucket, int maxCapacity, AdaptiveByteBuf buf) {
         this.recordAllocationSize(sizeBucket);
         Chunk curr = this.current;
         if (curr != null) {
            if (curr.remainingCapacity() > size) {
               curr.readInitInto(buf, size, maxCapacity);
               return true;
            }

            this.current = null;
            if (curr.remainingCapacity() == size) {
               boolean var24;
               try {
                  curr.readInitInto(buf, size, maxCapacity);
                  var24 = true;
               } finally {
                  curr.release();
               }

               return var24;
            }

            if (curr.remainingCapacity() < 4096) {
               curr.release();
            } else {
               this.transferToNextInLineOrRelease(curr);
            }
         }

         assert this.current == null;

         curr = (Chunk)NEXT_IN_LINE.getAndSet(this, (Object)null);
         if (curr != null) {
            if (curr == MAGAZINE_FREED) {
               this.restoreMagazineFreed();
               return false;
            }

            if (curr.remainingCapacity() > size) {
               curr.readInitInto(buf, size, maxCapacity);
               this.current = curr;
               return true;
            }

            if (curr.remainingCapacity() == size) {
               boolean var6;
               try {
                  curr.readInitInto(buf, size, maxCapacity);
                  var6 = true;
               } finally {
                  curr.release();
               }

               return var6;
            }

            curr.release();
         }

         curr = (Chunk)this.parent.centralQueue.poll();
         if (curr == null) {
            curr = this.newChunkAllocation(size);
         } else {
            curr.attachToMagazine(this);
            if (curr.remainingCapacity() < size) {
               if (curr.remainingCapacity() < 4096) {
                  curr.release();
               } else {
                  this.transferToNextInLineOrRelease(curr);
               }

               curr = this.newChunkAllocation(size);
            }
         }

         this.current = curr;

         try {
            assert this.current.remainingCapacity() >= size;

            if (curr.remainingCapacity() > size) {
               curr.readInitInto(buf, size, maxCapacity);
               curr = null;
            } else {
               curr.readInitInto(buf, size, maxCapacity);
            }
         } finally {
            if (curr != null) {
               curr.release();
               this.current = null;
            }

         }

         return true;
      }

      private void restoreMagazineFreed() {
         Chunk next = (Chunk)NEXT_IN_LINE.getAndSet(this, MAGAZINE_FREED);
         if (next != null && next != MAGAZINE_FREED) {
            next.release();
         }

      }

      private void transferToNextInLineOrRelease(Chunk chunk) {
         if (!NEXT_IN_LINE.compareAndSet(this, (Object)null, chunk)) {
            Chunk nextChunk = (Chunk)NEXT_IN_LINE.get(this);
            if (nextChunk != null && nextChunk != MAGAZINE_FREED && chunk.remainingCapacity() > nextChunk.remainingCapacity() && NEXT_IN_LINE.compareAndSet(this, nextChunk, chunk)) {
               nextChunk.release();
            } else {
               chunk.release();
            }
         }
      }

      private Chunk newChunkAllocation(int promptingSize) {
         int size = Math.max(promptingSize * 10, this.preferredChunkSize());
         int minChunks = size / 131072;
         if (131072 * minChunks < size) {
            size = 131072 * (1 + minChunks);
         }

         ChunkAllocator chunkAllocator = this.parent.chunkAllocator;
         return new Chunk(chunkAllocator.allocate(size, size), this, true);
      }

      boolean trySetNextInLine(Chunk chunk) {
         return NEXT_IN_LINE.compareAndSet(this, (Object)null, chunk);
      }

      void free() {
         this.restoreMagazineFreed();
         long stamp = this.allocationLock.writeLock();

         try {
            if (this.current != null) {
               this.current.release();
               this.current = null;
            }
         } finally {
            this.allocationLock.unlockWrite(stamp);
         }

      }

      public AdaptiveByteBuf newBuffer() {
         AdaptiveByteBuf buf;
         if (this.handle == null) {
            buf = (AdaptiveByteBuf)EVENT_LOOP_LOCAL_BUFFER_POOL.get();
         } else {
            buf = (AdaptiveByteBuf)this.bufferQueue.poll();
            if (buf == null) {
               buf = new AdaptiveByteBuf(this.handle);
            }
         }

         buf.resetRefCnt();
         buf.discardMarks();
         return buf;
      }
   }

   private static final class Chunk implements ReferenceCounted {
      private final AbstractByteBuf delegate;
      private Magazine magazine;
      private final AdaptivePoolingAllocator allocator;
      private final int capacity;
      private final boolean pooled;
      private int allocatedBytes;
      private static final long REFCNT_FIELD_OFFSET = ReferenceCountUpdater.getUnsafeOffset(Chunk.class, "refCnt");
      private static final AtomicIntegerFieldUpdater AIF_UPDATER = AtomicIntegerFieldUpdater.newUpdater(Chunk.class, "refCnt");
      private static final ReferenceCountUpdater updater = new ReferenceCountUpdater() {
         protected AtomicIntegerFieldUpdater updater() {
            return AdaptivePoolingAllocator.Chunk.AIF_UPDATER;
         }

         protected long unsafeOffset() {
            return AdaptivePoolingAllocator.Chunk.REFCNT_FIELD_OFFSET;
         }
      };
      private volatile int refCnt;

      Chunk() {
         this.delegate = null;
         this.magazine = null;
         this.allocator = null;
         this.capacity = 0;
         this.pooled = false;
      }

      Chunk(AbstractByteBuf delegate, Magazine magazine, boolean pooled) {
         this.delegate = delegate;
         this.pooled = pooled;
         this.capacity = delegate.capacity();
         updater.setInitialValue(this);
         this.allocator = magazine.parent;
         this.attachToMagazine(magazine);
      }

      Magazine currentMagazine() {
         return this.magazine;
      }

      void detachFromMagazine() {
         if (this.magazine != null) {
            this.magazine.usedMemory.getAndAdd((long)(-this.capacity));
            this.magazine = null;
         }

      }

      void attachToMagazine(Magazine magazine) {
         assert this.magazine == null;

         this.magazine = magazine;
         magazine.usedMemory.getAndAdd((long)this.capacity);
      }

      public Chunk touch(Object hint) {
         return this;
      }

      public int refCnt() {
         return updater.refCnt(this);
      }

      public Chunk retain() {
         return (Chunk)updater.retain(this);
      }

      public Chunk retain(int increment) {
         return (Chunk)updater.retain(this, increment);
      }

      public Chunk touch() {
         return this;
      }

      public boolean release() {
         if (updater.release(this)) {
            this.deallocate();
            return true;
         } else {
            return false;
         }
      }

      public boolean release(int decrement) {
         if (updater.release(this, decrement)) {
            this.deallocate();
            return true;
         } else {
            return false;
         }
      }

      private void deallocate() {
         Magazine mag = this.magazine;
         AdaptivePoolingAllocator parent = mag.parent;
         int chunkSize = mag.preferredChunkSize();
         int memSize = this.delegate.capacity();
         if (this.pooled && !shouldReleaseSuboptimalChunkSuze(memSize, chunkSize)) {
            updater.resetRefCnt(this);
            this.delegate.setIndex(0, 0);
            this.allocatedBytes = 0;
            if (!mag.trySetNextInLine(this)) {
               this.detachFromMagazine();
               if (!parent.offerToQueue(this)) {
                  boolean released = updater.release(this);
                  this.delegate.release();

                  assert released;
               }
            }
         } else {
            this.detachFromMagazine();
            this.delegate.release();
         }

      }

      private static boolean shouldReleaseSuboptimalChunkSuze(int givenSize, int preferredSize) {
         int givenChunks = givenSize / 131072;
         int preferredChunks = preferredSize / 131072;
         int deviation = Math.abs(givenChunks - preferredChunks);
         return deviation != 0 && PlatformDependent.threadLocalRandom().nextDouble() * (double)200.0F > (double)deviation;
      }

      public void readInitInto(AdaptiveByteBuf buf, int size, int maxCapacity) {
         int startIndex = this.allocatedBytes;
         this.allocatedBytes = startIndex + size;
         Chunk chunk = this;
         this.retain();

         try {
            buf.init(this.delegate, chunk, 0, 0, startIndex, size, maxCapacity);
            chunk = null;
         } finally {
            if (chunk != null) {
               this.allocatedBytes = startIndex;
               chunk.release();
            }

         }

      }

      public int remainingCapacity() {
         return this.capacity - this.allocatedBytes;
      }

      public int capacity() {
         return this.capacity;
      }
   }

   static final class AdaptiveByteBuf extends AbstractReferenceCountedByteBuf {
      private final ObjectPool.Handle handle;
      private int adjustment;
      private AbstractByteBuf rootParent;
      Chunk chunk;
      private int length;
      private ByteBuffer tmpNioBuf;
      private boolean hasArray;
      private boolean hasMemoryAddress;

      AdaptiveByteBuf(ObjectPool.Handle recyclerHandle) {
         super(0);
         this.handle = (ObjectPool.Handle)ObjectUtil.checkNotNull(recyclerHandle, "recyclerHandle");
      }

      void init(AbstractByteBuf unwrapped, Chunk wrapped, int readerIndex, int writerIndex, int adjustment, int capacity, int maxCapacity) {
         this.adjustment = adjustment;
         this.chunk = wrapped;
         this.length = capacity;
         this.maxCapacity(maxCapacity);
         this.setIndex0(readerIndex, writerIndex);
         this.hasArray = unwrapped.hasArray();
         this.hasMemoryAddress = unwrapped.hasMemoryAddress();
         this.rootParent = unwrapped;
         this.tmpNioBuf = unwrapped.internalNioBuffer(adjustment, capacity).slice();
      }

      private AbstractByteBuf rootParent() {
         AbstractByteBuf rootParent = this.rootParent;
         if (rootParent != null) {
            return rootParent;
         } else {
            throw new IllegalReferenceCountException();
         }
      }

      public int capacity() {
         return this.length;
      }

      public ByteBuf capacity(int newCapacity) {
         if (newCapacity == this.capacity()) {
            this.ensureAccessible();
            return this;
         } else {
            this.checkNewCapacity(newCapacity);
            if (newCapacity < this.capacity()) {
               this.length = newCapacity;
               this.setIndex0(Math.min(this.readerIndex(), newCapacity), Math.min(this.writerIndex(), newCapacity));
               return this;
            } else {
               ByteBuffer data = this.tmpNioBuf;
               data.clear();
               this.tmpNioBuf = null;
               Chunk chunk = this.chunk;
               AdaptivePoolingAllocator allocator = chunk.allocator;
               int readerIndex = this.readerIndex;
               int writerIndex = this.writerIndex;
               allocator.allocate(newCapacity, this.maxCapacity(), this);
               this.tmpNioBuf.put(data);
               this.tmpNioBuf.clear();
               chunk.release();
               this.readerIndex = readerIndex;
               this.writerIndex = writerIndex;
               return this;
            }
         }
      }

      public ByteBufAllocator alloc() {
         return this.rootParent().alloc();
      }

      public ByteOrder order() {
         return this.rootParent().order();
      }

      public ByteBuf unwrap() {
         return null;
      }

      public boolean isDirect() {
         return this.rootParent().isDirect();
      }

      public int arrayOffset() {
         return this.idx(this.rootParent().arrayOffset());
      }

      public boolean hasMemoryAddress() {
         return this.hasMemoryAddress;
      }

      public long memoryAddress() {
         this.ensureAccessible();
         return this.rootParent().memoryAddress() + (long)this.adjustment;
      }

      public ByteBuffer nioBuffer(int index, int length) {
         this.checkIndex(index, length);
         return this.rootParent().nioBuffer(this.idx(index), length);
      }

      public ByteBuffer internalNioBuffer(int index, int length) {
         this.checkIndex(index, length);
         return (ByteBuffer)this.internalNioBuffer().position(index).limit(index + length);
      }

      private ByteBuffer internalNioBuffer() {
         return (ByteBuffer)this.tmpNioBuf.clear();
      }

      public ByteBuffer[] nioBuffers(int index, int length) {
         this.checkIndex(index, length);
         return this.rootParent().nioBuffers(this.idx(index), length);
      }

      public boolean hasArray() {
         return this.hasArray;
      }

      public byte[] array() {
         this.ensureAccessible();
         return this.rootParent().array();
      }

      public ByteBuf copy(int index, int length) {
         this.checkIndex(index, length);
         return this.rootParent().copy(this.idx(index), length);
      }

      public int nioBufferCount() {
         return this.rootParent().nioBufferCount();
      }

      protected byte _getByte(int index) {
         return this.rootParent()._getByte(this.idx(index));
      }

      protected short _getShort(int index) {
         return this.rootParent()._getShort(this.idx(index));
      }

      protected short _getShortLE(int index) {
         return this.rootParent()._getShortLE(this.idx(index));
      }

      protected int _getUnsignedMedium(int index) {
         return this.rootParent()._getUnsignedMedium(this.idx(index));
      }

      protected int _getUnsignedMediumLE(int index) {
         return this.rootParent()._getUnsignedMediumLE(this.idx(index));
      }

      protected int _getInt(int index) {
         return this.rootParent()._getInt(this.idx(index));
      }

      protected int _getIntLE(int index) {
         return this.rootParent()._getIntLE(this.idx(index));
      }

      protected long _getLong(int index) {
         return this.rootParent()._getLong(this.idx(index));
      }

      protected long _getLongLE(int index) {
         return this.rootParent()._getLongLE(this.idx(index));
      }

      public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
         this.checkIndex(index, length);
         this.rootParent().getBytes(this.idx(index), dst, dstIndex, length);
         return this;
      }

      public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
         this.checkIndex(index, length);
         this.rootParent().getBytes(this.idx(index), dst, dstIndex, length);
         return this;
      }

      public ByteBuf getBytes(int index, ByteBuffer dst) {
         this.checkIndex(index, dst.remaining());
         this.rootParent().getBytes(this.idx(index), (ByteBuffer)dst);
         return this;
      }

      protected void _setByte(int index, int value) {
         this.rootParent()._setByte(this.idx(index), value);
      }

      protected void _setShort(int index, int value) {
         this.rootParent()._setShort(this.idx(index), value);
      }

      protected void _setShortLE(int index, int value) {
         this.rootParent()._setShortLE(this.idx(index), value);
      }

      protected void _setMedium(int index, int value) {
         this.rootParent()._setMedium(this.idx(index), value);
      }

      protected void _setMediumLE(int index, int value) {
         this.rootParent()._setMediumLE(this.idx(index), value);
      }

      protected void _setInt(int index, int value) {
         this.rootParent()._setInt(this.idx(index), value);
      }

      protected void _setIntLE(int index, int value) {
         this.rootParent()._setIntLE(this.idx(index), value);
      }

      protected void _setLong(int index, long value) {
         this.rootParent()._setLong(this.idx(index), value);
      }

      protected void _setLongLE(int index, long value) {
         this.rootParent().setLongLE(this.idx(index), value);
      }

      public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
         this.checkIndex(index, length);
         this.rootParent().setBytes(this.idx(index), src, srcIndex, length);
         return this;
      }

      public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
         this.checkIndex(index, length);
         this.rootParent().setBytes(this.idx(index), src, srcIndex, length);
         return this;
      }

      public ByteBuf setBytes(int index, ByteBuffer src) {
         this.checkIndex(index, src.remaining());
         this.rootParent().setBytes(this.idx(index), (ByteBuffer)src);
         return this;
      }

      public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
         this.checkIndex(index, length);
         if (length != 0) {
            ByteBufUtil.readBytes(this.alloc(), this.internalNioBuffer().duplicate(), index, length, out);
         }

         return this;
      }

      public int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
         return out.write(this.internalNioBuffer(index, length).duplicate());
      }

      public int getBytes(int index, FileChannel out, long position, int length) throws IOException {
         return out.write(this.internalNioBuffer(index, length).duplicate(), position);
      }

      public int setBytes(int index, InputStream in, int length) throws IOException {
         this.checkIndex(index, length);
         AbstractByteBuf rootParent = this.rootParent();
         if (rootParent.hasArray()) {
            return rootParent.setBytes(this.idx(index), in, length);
         } else {
            byte[] tmp = ByteBufUtil.threadLocalTempArray(length);
            int readBytes = in.read(tmp, 0, length);
            if (readBytes <= 0) {
               return readBytes;
            } else {
               this.setBytes(index, (byte[])tmp, 0, readBytes);
               return readBytes;
            }
         }
      }

      public int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
         try {
            return in.read(this.internalNioBuffer(index, length).duplicate());
         } catch (ClosedChannelException var5) {
            return -1;
         }
      }

      public int setBytes(int index, FileChannel in, long position, int length) throws IOException {
         try {
            return in.read(this.internalNioBuffer(index, length).duplicate(), position);
         } catch (ClosedChannelException var7) {
            return -1;
         }
      }

      public int forEachByte(int index, int length, ByteProcessor processor) {
         this.checkIndex(index, length);
         int ret = this.rootParent().forEachByte(this.idx(index), length, processor);
         return this.forEachResult(ret);
      }

      public int forEachByteDesc(int index, int length, ByteProcessor processor) {
         this.checkIndex(index, length);
         int ret = this.rootParent().forEachByteDesc(this.idx(index), length, processor);
         return this.forEachResult(ret);
      }

      private int forEachResult(int ret) {
         return ret < this.adjustment ? -1 : ret - this.adjustment;
      }

      public boolean isContiguous() {
         return this.rootParent().isContiguous();
      }

      private int idx(int index) {
         return index + this.adjustment;
      }

      protected void deallocate() {
         if (this.chunk != null) {
            this.chunk.release();
         }

         this.tmpNioBuf = null;
         this.chunk = null;
         this.rootParent = null;
         if (this.handle instanceof Recycler.EnhancedHandle) {
            Recycler.EnhancedHandle<AdaptiveByteBuf> enhancedHandle = (Recycler.EnhancedHandle)this.handle;
            enhancedHandle.unguardedRecycle(this);
         } else {
            this.handle.recycle(this);
         }

      }
   }

   interface ChunkAllocator {
      AbstractByteBuf allocate(int var1, int var2);
   }
}
