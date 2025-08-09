package org.sparkproject.jetty.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.sparkproject.jetty.util.BufferUtil;

public class ByteBufferAccumulator implements AutoCloseable {
   private final List _buffers;
   private final ByteBufferPool _bufferPool;
   private final boolean _direct;

   public ByteBufferAccumulator() {
      this((ByteBufferPool)null, false);
   }

   public ByteBufferAccumulator(ByteBufferPool bufferPool, boolean direct) {
      this._buffers = new ArrayList();
      this._bufferPool = (ByteBufferPool)(bufferPool == null ? new NullByteBufferPool() : bufferPool);
      this._direct = direct;
   }

   public int getLength() {
      int length = 0;

      for(ByteBuffer buffer : this._buffers) {
         length = Math.addExact(length, buffer.remaining());
      }

      return length;
   }

   public ByteBufferPool getByteBufferPool() {
      return this._bufferPool;
   }

   public ByteBuffer ensureBuffer(int minAllocationSize) {
      return this.ensureBuffer(1, minAllocationSize);
   }

   public ByteBuffer ensureBuffer(int minSize, int minAllocationSize) {
      ByteBuffer buffer = this._buffers.isEmpty() ? BufferUtil.EMPTY_BUFFER : (ByteBuffer)this._buffers.get(this._buffers.size() - 1);
      if (BufferUtil.space(buffer) < minSize) {
         buffer = this._bufferPool.acquire(minAllocationSize, this._direct);
         this._buffers.add(buffer);
      }

      return buffer;
   }

   public void copyBytes(byte[] buf, int offset, int length) {
      this.copyBuffer(BufferUtil.toBuffer(buf, offset, length));
   }

   public void copyBuffer(ByteBuffer buffer) {
      while(buffer.hasRemaining()) {
         ByteBuffer b = this.ensureBuffer(buffer.remaining());
         int pos = BufferUtil.flipToFill(b);
         BufferUtil.put(buffer, b);
         BufferUtil.flipToFlush(b, pos);
      }

   }

   public ByteBuffer takeByteBuffer() {
      if (this._buffers.size() == 1) {
         ByteBuffer combinedBuffer = (ByteBuffer)this._buffers.get(0);
         this._buffers.clear();
         return combinedBuffer;
      } else {
         int length = this.getLength();
         ByteBuffer combinedBuffer = this._bufferPool.acquire(length, this._direct);
         BufferUtil.clearToFill(combinedBuffer);

         for(ByteBuffer buffer : this._buffers) {
            combinedBuffer.put(buffer);
            this._bufferPool.release(buffer);
         }

         BufferUtil.flipToFlush(combinedBuffer, 0);
         this._buffers.clear();
         return combinedBuffer;
      }
   }

   public ByteBuffer toByteBuffer() {
      ByteBuffer combinedBuffer = this.takeByteBuffer();
      this._buffers.add(combinedBuffer);
      return combinedBuffer;
   }

   public byte[] toByteArray() {
      int length = this.getLength();
      if (length == 0) {
         return new byte[0];
      } else {
         byte[] bytes = new byte[length];
         ByteBuffer buffer = BufferUtil.toBuffer(bytes);
         BufferUtil.clear(buffer);
         this.writeTo(buffer);
         return bytes;
      }
   }

   public void writeTo(ByteBuffer buffer) {
      int pos = BufferUtil.flipToFill(buffer);

      for(ByteBuffer bb : this._buffers) {
         buffer.put(bb.slice());
      }

      BufferUtil.flipToFlush(buffer, pos);
   }

   public void writeTo(OutputStream out) throws IOException {
      for(ByteBuffer bb : this._buffers) {
         BufferUtil.writeTo(bb.slice(), out);
      }

   }

   public void close() {
      List var10000 = this._buffers;
      ByteBufferPool var10001 = this._bufferPool;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::release);
      this._buffers.clear();
   }
}
