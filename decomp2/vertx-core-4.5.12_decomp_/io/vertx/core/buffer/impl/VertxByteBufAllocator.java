package io.vertx.core.buffer.impl;

import io.netty.buffer.AbstractByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.util.internal.PlatformDependent;

public abstract class VertxByteBufAllocator extends AbstractByteBufAllocator {
   private static final boolean REUSE_NETTY_ALLOCATOR = Boolean.getBoolean("vertx.reuseNettyAllocators");
   public static final ByteBufAllocator POOLED_ALLOCATOR;
   public static final ByteBufAllocator UNPOOLED_ALLOCATOR;
   private static final VertxByteBufAllocator UNSAFE_IMPL;
   private static final VertxByteBufAllocator IMPL;
   public static final VertxByteBufAllocator DEFAULT;

   protected ByteBuf newDirectBuffer(int initialCapacity, int maxCapacity) {
      return UNPOOLED_ALLOCATOR.directBuffer(initialCapacity, maxCapacity);
   }

   public boolean isDirectBufferPooled() {
      return false;
   }

   static {
      POOLED_ALLOCATOR = REUSE_NETTY_ALLOCATOR && PooledByteBufAllocator.defaultPreferDirect() ? PooledByteBufAllocator.DEFAULT : new PooledByteBufAllocator(true);
      UNPOOLED_ALLOCATOR = new UnpooledByteBufAllocator(false);
      UNSAFE_IMPL = new VertxByteBufAllocator() {
         protected ByteBuf newHeapBuffer(int initialCapacity, int maxCapacity) {
            return new VertxUnsafeHeapByteBuf(this, initialCapacity, maxCapacity);
         }
      };
      IMPL = new VertxByteBufAllocator() {
         protected ByteBuf newHeapBuffer(int initialCapacity, int maxCapacity) {
            return new VertxHeapByteBuf(this, initialCapacity, maxCapacity);
         }
      };
      DEFAULT = PlatformDependent.hasUnsafe() ? UNSAFE_IMPL : IMPL;
   }
}
