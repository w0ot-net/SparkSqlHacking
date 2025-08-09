package io.vertx.core.buffer.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

public final class PartialPooledByteBufAllocator implements ByteBufAllocator {
   public static final PartialPooledByteBufAllocator INSTANCE = new PartialPooledByteBufAllocator();

   private PartialPooledByteBufAllocator() {
   }

   public ByteBuf buffer() {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.heapBuffer();
   }

   public ByteBuf buffer(int initialCapacity) {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.heapBuffer(initialCapacity);
   }

   public ByteBuf buffer(int initialCapacity, int maxCapacity) {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.heapBuffer(initialCapacity, maxCapacity);
   }

   public ByteBuf ioBuffer() {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.directBuffer();
   }

   public ByteBuf ioBuffer(int initialCapacity) {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.directBuffer(initialCapacity);
   }

   public ByteBuf ioBuffer(int initialCapacity, int maxCapacity) {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.directBuffer(initialCapacity, maxCapacity);
   }

   public ByteBuf heapBuffer() {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.heapBuffer();
   }

   public ByteBuf heapBuffer(int initialCapacity) {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.heapBuffer(initialCapacity);
   }

   public ByteBuf heapBuffer(int initialCapacity, int maxCapacity) {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.heapBuffer(initialCapacity, maxCapacity);
   }

   public ByteBuf directBuffer() {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.directBuffer();
   }

   public ByteBuf directBuffer(int initialCapacity) {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.directBuffer(initialCapacity);
   }

   public ByteBuf directBuffer(int initialCapacity, int maxCapacity) {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.directBuffer(initialCapacity, maxCapacity);
   }

   public CompositeByteBuf compositeBuffer() {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.compositeHeapBuffer();
   }

   public CompositeByteBuf compositeBuffer(int maxNumComponents) {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.compositeHeapBuffer(maxNumComponents);
   }

   public CompositeByteBuf compositeHeapBuffer() {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.compositeHeapBuffer();
   }

   public CompositeByteBuf compositeHeapBuffer(int maxNumComponents) {
      return VertxByteBufAllocator.UNPOOLED_ALLOCATOR.compositeHeapBuffer(maxNumComponents);
   }

   public CompositeByteBuf compositeDirectBuffer() {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.compositeDirectBuffer();
   }

   public CompositeByteBuf compositeDirectBuffer(int maxNumComponents) {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.compositeDirectBuffer();
   }

   public boolean isDirectBufferPooled() {
      return true;
   }

   public int calculateNewCapacity(int minNewCapacity, int maxCapacity) {
      return VertxByteBufAllocator.POOLED_ALLOCATOR.calculateNewCapacity(minNewCapacity, maxCapacity);
   }
}
