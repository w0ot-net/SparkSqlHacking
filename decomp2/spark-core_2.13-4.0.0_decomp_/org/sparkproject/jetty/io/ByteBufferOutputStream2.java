package org.sparkproject.jetty.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteBufferOutputStream2 extends OutputStream {
   private final ByteBufferAccumulator _accumulator;
   private int _size;

   public ByteBufferOutputStream2() {
      this((ByteBufferPool)null, false);
   }

   public ByteBufferOutputStream2(ByteBufferPool bufferPool, boolean direct) {
      this._size = 0;
      this._accumulator = new ByteBufferAccumulator((ByteBufferPool)(bufferPool == null ? new NullByteBufferPool() : bufferPool), direct);
   }

   public ByteBufferPool getByteBufferPool() {
      return this._accumulator.getByteBufferPool();
   }

   public ByteBuffer takeByteBuffer() {
      return this._accumulator.takeByteBuffer();
   }

   public ByteBuffer toByteBuffer() {
      return this._accumulator.toByteBuffer();
   }

   public byte[] toByteArray() {
      return this._accumulator.toByteArray();
   }

   public int size() {
      return this._size;
   }

   public void write(int b) {
      this.write(new byte[]{(byte)b}, 0, 1);
   }

   public void write(byte[] b, int off, int len) {
      this._size += len;
      this._accumulator.copyBytes(b, off, len);
   }

   public void write(ByteBuffer buffer) {
      this._size += buffer.remaining();
      this._accumulator.copyBuffer(buffer);
   }

   public void writeTo(ByteBuffer buffer) {
      this._accumulator.writeTo(buffer);
   }

   public void writeTo(OutputStream out) throws IOException {
      this._accumulator.writeTo(out);
   }

   public void close() {
      this._accumulator.close();
      this._size = 0;
   }

   public synchronized String toString() {
      return String.format("%s@%x{size=%d, byteAccumulator=%s}", this.getClass().getSimpleName(), this.hashCode(), this._size, this._accumulator);
   }
}
