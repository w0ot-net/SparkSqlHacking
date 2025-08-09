package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public final class AdaptiveByteBufAllocator extends AbstractByteBufAllocator implements ByteBufAllocatorMetricProvider, ByteBufAllocatorMetric {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(AdaptiveByteBufAllocator.class);
   private static final boolean DEFAULT_USE_CACHED_MAGAZINES_FOR_NON_EVENT_LOOP_THREADS = SystemPropertyUtil.getBoolean("io.netty.allocator.useCachedMagazinesForNonEventLoopThreads", false);
   private final AdaptiveAllocatorApi direct;
   private final AdaptiveAllocatorApi heap;

   public AdaptiveByteBufAllocator() {
      this(PlatformDependent.directBufferPreferred());
   }

   public AdaptiveByteBufAllocator(boolean preferDirect) {
      this(preferDirect, DEFAULT_USE_CACHED_MAGAZINES_FOR_NON_EVENT_LOOP_THREADS);
   }

   public AdaptiveByteBufAllocator(boolean preferDirect, boolean useCacheForNonEventLoopThreads) {
      super(preferDirect);
      if (PlatformDependent.javaVersion() < 8) {
         throw new IllegalStateException("This allocator require Java 8 or newer.");
      } else {
         AdaptivePoolingAllocator.MagazineCaching magazineCaching = useCacheForNonEventLoopThreads ? AdaptivePoolingAllocator.MagazineCaching.FastThreadLocalThreads : AdaptivePoolingAllocator.MagazineCaching.EventLoopThreads;
         this.direct = new AdaptivePoolingAllocator(new DirectChunkAllocator(this), magazineCaching);
         this.heap = new AdaptivePoolingAllocator(new HeapChunkAllocator(this), magazineCaching);
      }
   }

   protected ByteBuf newHeapBuffer(int initialCapacity, int maxCapacity) {
      return this.heap.allocate(initialCapacity, maxCapacity);
   }

   protected ByteBuf newDirectBuffer(int initialCapacity, int maxCapacity) {
      return this.direct.allocate(initialCapacity, maxCapacity);
   }

   public boolean isDirectBufferPooled() {
      return true;
   }

   public long usedHeapMemory() {
      return this.heap.usedMemory();
   }

   public long usedDirectMemory() {
      return this.direct.usedMemory();
   }

   public ByteBufAllocatorMetric metric() {
      return this;
   }

   static {
      logger.debug("-Dio.netty.allocator.useCachedMagazinesForNonEventLoopThreads: {}", DEFAULT_USE_CACHED_MAGAZINES_FOR_NON_EVENT_LOOP_THREADS);
   }

   private static final class HeapChunkAllocator implements AdaptivePoolingAllocator.ChunkAllocator {
      private final ByteBufAllocator allocator;

      private HeapChunkAllocator(ByteBufAllocator allocator) {
         this.allocator = allocator;
      }

      public AbstractByteBuf allocate(int initialCapacity, int maxCapacity) {
         return (AbstractByteBuf)(PlatformDependent.hasUnsafe() ? new UnpooledUnsafeHeapByteBuf(this.allocator, initialCapacity, maxCapacity) : new UnpooledHeapByteBuf(this.allocator, initialCapacity, maxCapacity));
      }
   }

   private static final class DirectChunkAllocator implements AdaptivePoolingAllocator.ChunkAllocator {
      private final ByteBufAllocator allocator;

      private DirectChunkAllocator(ByteBufAllocator allocator) {
         this.allocator = allocator;
      }

      public AbstractByteBuf allocate(int initialCapacity, int maxCapacity) {
         return (AbstractByteBuf)(PlatformDependent.hasUnsafe() ? UnsafeByteBufUtil.newUnsafeDirectByteBuf(this.allocator, initialCapacity, maxCapacity) : new UnpooledDirectByteBuf(this.allocator, initialCapacity, maxCapacity));
      }
   }

   interface AdaptiveAllocatorApi {
      ByteBuf allocate(int var1, int var2);

      long usedMemory();
   }
}
