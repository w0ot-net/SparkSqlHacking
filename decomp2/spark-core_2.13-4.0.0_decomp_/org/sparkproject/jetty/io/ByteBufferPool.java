package org.sparkproject.jetty.io;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.sparkproject.jetty.util.BufferUtil;

public interface ByteBufferPool {
   ByteBuffer acquire(int var1, boolean var2);

   void release(ByteBuffer var1);

   default void remove(ByteBuffer buffer) {
   }

   default ByteBuffer newByteBuffer(int capacity, boolean direct) {
      return direct ? BufferUtil.allocateDirect(capacity) : BufferUtil.allocate(capacity);
   }

   RetainableByteBufferPool asRetainableByteBufferPool();

   public static class Lease {
      private final ByteBufferPool byteBufferPool;
      private final List buffers;
      private final List recycles;

      public Lease(ByteBufferPool byteBufferPool) {
         this.byteBufferPool = byteBufferPool;
         this.buffers = new ArrayList();
         this.recycles = new ArrayList();
      }

      public ByteBuffer acquire(int capacity, boolean direct) {
         ByteBuffer buffer = this.byteBufferPool.acquire(capacity, direct);
         BufferUtil.clearToFill(buffer);
         return buffer;
      }

      public void append(ByteBuffer buffer, boolean recycle) {
         this.buffers.add(buffer);
         this.recycles.add(recycle);
      }

      public void insert(int index, ByteBuffer buffer, boolean recycle) {
         this.buffers.add(index, buffer);
         this.recycles.add(index, recycle);
      }

      public List getByteBuffers() {
         return this.buffers;
      }

      public long getTotalLength() {
         long length = 0L;

         for(ByteBuffer buffer : this.buffers) {
            length += (long)buffer.remaining();
         }

         return length;
      }

      public int getSize() {
         return this.buffers.size();
      }

      public void recycle() {
         for(int i = 0; i < this.buffers.size(); ++i) {
            ByteBuffer buffer = (ByteBuffer)this.buffers.get(i);
            if ((Boolean)this.recycles.get(i)) {
               this.release(buffer);
            }
         }

         this.buffers.clear();
         this.recycles.clear();
      }

      public void release(ByteBuffer buffer) {
         this.byteBufferPool.release(buffer);
      }
   }
}
