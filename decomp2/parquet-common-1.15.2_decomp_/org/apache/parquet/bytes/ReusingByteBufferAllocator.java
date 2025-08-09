package org.apache.parquet.bytes;

import java.nio.ByteBuffer;

public abstract class ReusingByteBufferAllocator implements ByteBufferAllocator, AutoCloseable {
   private final ByteBufferAllocator allocator;
   private final ByteBufferReleaser releaser;
   private ByteBuffer buffer;
   private ByteBuffer bufferOut;

   public static ReusingByteBufferAllocator strict(ByteBufferAllocator allocator) {
      return new ReusingByteBufferAllocator(allocator) {
         void allocateCheck(ByteBuffer bufferOut) {
            if (bufferOut != null) {
               throw new IllegalStateException("The single buffer is not yet released");
            }
         }
      };
   }

   public static ReusingByteBufferAllocator unsafe(ByteBufferAllocator allocator) {
      return new ReusingByteBufferAllocator(allocator) {
         void allocateCheck(ByteBuffer bufferOut) {
         }
      };
   }

   private ReusingByteBufferAllocator(ByteBufferAllocator allocator) {
      this.releaser = new ByteBufferReleaser(this);
      this.allocator = allocator;
   }

   public ByteBufferReleaser getReleaser() {
      return this.releaser;
   }

   public ByteBuffer allocate(int size) {
      this.allocateCheck(this.bufferOut);
      if (this.buffer == null) {
         this.bufferOut = this.buffer = this.allocator.allocate(size);
      } else if (this.buffer.capacity() < size) {
         this.allocator.release(this.buffer);
         this.bufferOut = this.buffer = this.allocator.allocate(size);
      } else {
         this.buffer.clear();
         this.buffer.limit(size);
         this.bufferOut = this.buffer.slice();
      }

      return this.bufferOut;
   }

   abstract void allocateCheck(ByteBuffer var1);

   public void release(ByteBuffer b) {
      if (this.bufferOut == null) {
         throw new IllegalStateException("The single buffer has already been released or never allocated");
      } else if (b != this.bufferOut) {
         throw new IllegalArgumentException("The buffer to be released is not the one allocated by this allocator");
      } else {
         this.bufferOut = null;
      }
   }

   public boolean isDirect() {
      return this.allocator.isDirect();
   }

   public void close() {
      if (this.buffer != null) {
         this.allocator.release(this.buffer);
         this.buffer = null;
         this.bufferOut = null;
      }

   }
}
