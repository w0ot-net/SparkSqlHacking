package io.vertx.core.buffer.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledUnsafeHeapByteBuf;

final class VertxUnsafeHeapByteBuf extends UnpooledUnsafeHeapByteBuf {
   public VertxUnsafeHeapByteBuf(ByteBufAllocator alloc, int initialCapacity, int maxCapacity) {
      super(alloc, initialCapacity, maxCapacity);
   }

   public ByteBuf retain(int increment) {
      return this;
   }

   public ByteBuf retain() {
      return this;
   }

   public ByteBuf touch() {
      return this;
   }

   public ByteBuf touch(Object hint) {
      return this;
   }

   public boolean release() {
      return false;
   }

   public boolean release(int decrement) {
      return false;
   }
}
