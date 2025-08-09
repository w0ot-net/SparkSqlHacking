package org.apache.arrow.memory.patch;

import io.netty.buffer.AbstractByteBufAllocator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.ExpandableByteBuf;
import io.netty.buffer.NettyArrowBuf;
import org.apache.arrow.memory.BufferAllocator;

/** @deprecated */
@Deprecated
public class ArrowByteBufAllocator extends AbstractByteBufAllocator {
   private static final int DEFAULT_BUFFER_SIZE = 4096;
   private static final int DEFAULT_MAX_COMPOSITE_COMPONENTS = 16;
   private final BufferAllocator allocator;

   public ArrowByteBufAllocator(BufferAllocator allocator) {
      this.allocator = allocator;
   }

   public BufferAllocator unwrap() {
      return this.allocator;
   }

   public ByteBuf buffer() {
      return this.buffer(4096);
   }

   public ByteBuf buffer(int initialCapacity) {
      return new ExpandableByteBuf(NettyArrowBuf.unwrapBuffer(this.allocator.buffer((long)initialCapacity)), this.allocator);
   }

   public ByteBuf buffer(int initialCapacity, int maxCapacity) {
      return this.buffer(initialCapacity);
   }

   public ByteBuf ioBuffer() {
      return this.buffer();
   }

   public ByteBuf ioBuffer(int initialCapacity) {
      return this.buffer(initialCapacity);
   }

   public ByteBuf ioBuffer(int initialCapacity, int maxCapacity) {
      return this.buffer(initialCapacity);
   }

   public ByteBuf directBuffer() {
      return this.buffer();
   }

   public ByteBuf directBuffer(int initialCapacity) {
      return NettyArrowBuf.unwrapBuffer(this.allocator.buffer((long)initialCapacity));
   }

   public ByteBuf directBuffer(int initialCapacity, int maxCapacity) {
      return this.buffer(initialCapacity, maxCapacity);
   }

   public CompositeByteBuf compositeBuffer() {
      return this.compositeBuffer(16);
   }

   public CompositeByteBuf compositeBuffer(int maxNumComponents) {
      return new CompositeByteBuf(this, true, maxNumComponents);
   }

   public CompositeByteBuf compositeDirectBuffer() {
      return this.compositeBuffer();
   }

   public CompositeByteBuf compositeDirectBuffer(int maxNumComponents) {
      return this.compositeBuffer(maxNumComponents);
   }

   public boolean isDirectBufferPooled() {
      return false;
   }

   public ByteBuf heapBuffer() {
      throw this.fail();
   }

   public ByteBuf heapBuffer(int initialCapacity) {
      throw this.fail();
   }

   public ByteBuf heapBuffer(int initialCapacity, int maxCapacity) {
      throw this.fail();
   }

   public CompositeByteBuf compositeHeapBuffer() {
      throw this.fail();
   }

   public CompositeByteBuf compositeHeapBuffer(int maxNumComponents) {
      throw this.fail();
   }

   protected ByteBuf newHeapBuffer(int initialCapacity, int maxCapacity) {
      throw this.fail();
   }

   protected ByteBuf newDirectBuffer(int initialCapacity, int maxCapacity) {
      return this.buffer(initialCapacity, maxCapacity);
   }

   private RuntimeException fail() {
      throw new UnsupportedOperationException("Allocator doesn't support heap-based memory.");
   }
}
