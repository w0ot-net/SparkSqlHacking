package org.sparkproject.jetty.io;

import java.nio.ByteBuffer;

public interface RetainableByteBufferPool {
   RetainableByteBuffer acquire(int var1, boolean var2);

   void clear();

   static RetainableByteBufferPool from(ByteBufferPool byteBufferPool) {
      return new NotRetainedByteBufferPool(byteBufferPool);
   }

   public static class NotRetainedByteBufferPool implements RetainableByteBufferPool {
      private final ByteBufferPool _byteBufferPool;

      public NotRetainedByteBufferPool(ByteBufferPool byteBufferPool) {
         this._byteBufferPool = byteBufferPool;
      }

      public RetainableByteBuffer acquire(int size, boolean direct) {
         ByteBuffer byteBuffer = this._byteBufferPool.acquire(size, direct);
         RetainableByteBuffer retainableByteBuffer = new RetainableByteBuffer(byteBuffer, this::release);
         retainableByteBuffer.acquire();
         return retainableByteBuffer;
      }

      private void release(RetainableByteBuffer retainedBuffer) {
         this._byteBufferPool.release(retainedBuffer.getBuffer());
      }

      public void clear() {
      }

      public String toString() {
         return String.format("NonRetainableByteBufferPool@%x{%s}", this.hashCode(), this._byteBufferPool.toString());
      }
   }
}
