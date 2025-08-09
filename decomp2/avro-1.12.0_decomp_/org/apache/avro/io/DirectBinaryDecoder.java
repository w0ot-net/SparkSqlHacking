package org.apache.avro.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import org.apache.avro.InvalidNumberEncodingException;
import org.apache.avro.SystemLimitException;
import org.apache.avro.util.ByteBufferInputStream;

class DirectBinaryDecoder extends BinaryDecoder {
   private InputStream in;
   private ByteReader byteReader;
   private final byte[] buf = new byte[8];

   DirectBinaryDecoder(InputStream in) {
      this.configure(in);
   }

   DirectBinaryDecoder configure(InputStream in) {
      this.in = in;
      this.byteReader = (ByteReader)(in instanceof ByteBufferInputStream ? new ReuseByteReader((ByteBufferInputStream)in) : new ByteReader());
      return this;
   }

   public boolean readBoolean() throws IOException {
      int n = this.in.read();
      if (n < 0) {
         throw new EOFException();
      } else {
         return n == 1;
      }
   }

   public int readInt() throws IOException {
      int n = 0;
      int shift = 0;

      do {
         int b = this.in.read();
         if (b < 0) {
            throw new EOFException();
         }

         n |= (b & 127) << shift;
         if ((b & 128) == 0) {
            return n >>> 1 ^ -(n & 1);
         }

         shift += 7;
      } while(shift < 32);

      throw new InvalidNumberEncodingException("Invalid int encoding");
   }

   public long readLong() throws IOException {
      long n = 0L;
      int shift = 0;

      do {
         int b = this.in.read();
         if (b < 0) {
            throw new EOFException();
         }

         n |= ((long)b & 127L) << shift;
         if ((b & 128) == 0) {
            return n >>> 1 ^ -(n & 1L);
         }

         shift += 7;
      } while(shift < 64);

      throw new InvalidNumberEncodingException("Invalid long encoding");
   }

   public float readFloat() throws IOException {
      this.doReadBytes(this.buf, 0, 4);
      int n = this.buf[0] & 255 | (this.buf[1] & 255) << 8 | (this.buf[2] & 255) << 16 | (this.buf[3] & 255) << 24;
      return Float.intBitsToFloat(n);
   }

   public double readDouble() throws IOException {
      this.doReadBytes(this.buf, 0, 8);
      long n = (long)this.buf[0] & 255L | ((long)this.buf[1] & 255L) << 8 | ((long)this.buf[2] & 255L) << 16 | ((long)this.buf[3] & 255L) << 24 | ((long)this.buf[4] & 255L) << 32 | ((long)this.buf[5] & 255L) << 40 | ((long)this.buf[6] & 255L) << 48 | ((long)this.buf[7] & 255L) << 56;
      return Double.longBitsToDouble(n);
   }

   public ByteBuffer readBytes(ByteBuffer old) throws IOException {
      long length = this.readLong();
      return this.byteReader.read(old, SystemLimitException.checkMaxBytesLength(length));
   }

   protected void doSkipBytes(long length) throws IOException {
      while(length > 0L) {
         long n = this.in.skip(length);
         if (n <= 0L) {
            throw new EOFException();
         }

         length -= n;
      }

   }

   protected void doReadBytes(byte[] bytes, int start, int length) throws IOException {
      while(true) {
         int n = this.in.read(bytes, start, length);
         if (n == length || length == 0) {
            return;
         }

         if (n < 0) {
            throw new EOFException();
         }

         start += n;
         length -= n;
      }
   }

   public InputStream inputStream() {
      return this.in;
   }

   public boolean isEnd() throws IOException {
      throw new UnsupportedOperationException();
   }

   private class ByteReader {
      public ByteBuffer read(ByteBuffer old, int length) throws IOException {
         ByteBuffer result;
         if (old != null && length <= old.capacity()) {
            result = old;
            old.clear();
         } else {
            result = ByteBuffer.allocate(length);
         }

         DirectBinaryDecoder.this.doReadBytes(result.array(), result.position(), length);
         result.limit(length);
         return result;
      }
   }

   private class ReuseByteReader extends ByteReader {
      private final ByteBufferInputStream bbi;

      public ReuseByteReader(ByteBufferInputStream bbi) {
         this.bbi = bbi;
      }

      public ByteBuffer read(ByteBuffer old, int length) throws IOException {
         return old != null ? super.read(old, length) : this.bbi.readBuffer(length);
      }
   }
}
