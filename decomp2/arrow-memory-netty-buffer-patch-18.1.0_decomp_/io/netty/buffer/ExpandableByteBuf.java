package io.netty.buffer;

import org.apache.arrow.memory.BufferAllocator;

public class ExpandableByteBuf extends MutableWrappedByteBuf {
   private final BufferAllocator allocator;

   public ExpandableByteBuf(ByteBuf buffer, BufferAllocator allocator) {
      super(buffer);
      this.allocator = allocator;
   }

   public ByteBuf copy(int index, int length) {
      return new ExpandableByteBuf(this.buffer.copy(index, length), this.allocator);
   }

   public ByteBuf capacity(int newCapacity) {
      if (newCapacity > this.capacity()) {
         ByteBuf newBuf = NettyArrowBuf.unwrapBuffer(this.allocator.buffer((long)newCapacity));
         newBuf.writeBytes(this.buffer, 0, this.buffer.capacity());
         newBuf.readerIndex(this.buffer.readerIndex());
         newBuf.writerIndex(this.buffer.writerIndex());
         this.buffer.release();
         this.buffer = newBuf;
         return newBuf;
      } else {
         return super.capacity(newCapacity);
      }
   }
}
