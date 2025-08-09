package org.apache.avro.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.InvalidNumberEncodingException;
import org.apache.avro.SystemLimitException;
import org.apache.avro.util.Utf8;

public class BinaryDecoder extends Decoder {
   private long collectionCount;
   private ByteSource source;
   private byte[] buf;
   private int minPos;
   private int pos;
   private int limit;
   private final Utf8 scratchUtf8;

   byte[] getBuf() {
      return this.buf;
   }

   int getPos() {
      return this.pos;
   }

   int getLimit() {
      return this.limit;
   }

   void setBuf(byte[] buf, int pos, int len) {
      this.buf = buf;
      this.pos = pos;
      this.limit = pos + len;
   }

   void clearBuf() {
      this.buf = null;
   }

   protected BinaryDecoder() {
      this.collectionCount = 0L;
      this.source = null;
      this.buf = null;
      this.minPos = 0;
      this.pos = 0;
      this.limit = 0;
      this.scratchUtf8 = new Utf8();
   }

   BinaryDecoder(InputStream in, int bufferSize) {
      this();
      this.configure(in, bufferSize);
   }

   BinaryDecoder(byte[] data, int offset, int length) {
      this();
      this.configure(data, offset, length);
   }

   BinaryDecoder configure(InputStream in, int bufferSize) {
      this.configureSource(bufferSize, new InputStreamByteSource(in));
      return this;
   }

   BinaryDecoder configure(byte[] data, int offset, int length) {
      this.configureSource(8192, new ByteArrayByteSource(data, offset, length));
      return this;
   }

   private void configureSource(int bufferSize, ByteSource source) {
      if (null != this.source) {
         this.source.detach();
      }

      source.attach(bufferSize, this);
      this.source = source;
   }

   public void readNull() throws IOException {
   }

   public boolean readBoolean() throws IOException {
      if (this.limit == this.pos) {
         this.limit = this.source.tryReadRaw(this.buf, 0, this.buf.length);
         this.pos = 0;
         if (this.limit == 0) {
            throw new EOFException();
         }
      }

      int n = this.buf[this.pos++] & 255;
      return n == 1;
   }

   public int readInt() throws IOException {
      this.ensureBounds(5);
      int len = 1;
      int b = this.buf[this.pos] & 255;
      int n = b & 127;
      if (b > 127) {
         b = this.buf[this.pos + len++] & 255;
         n ^= (b & 127) << 7;
         if (b > 127) {
            b = this.buf[this.pos + len++] & 255;
            n ^= (b & 127) << 14;
            if (b > 127) {
               b = this.buf[this.pos + len++] & 255;
               n ^= (b & 127) << 21;
               if (b > 127) {
                  b = this.buf[this.pos + len++] & 255;
                  n ^= (b & 127) << 28;
                  if (b > 127) {
                     throw new InvalidNumberEncodingException("Invalid int encoding");
                  }
               }
            }
         }
      }

      this.pos += len;
      if (this.pos > this.limit) {
         throw new EOFException();
      } else {
         return n >>> 1 ^ -(n & 1);
      }
   }

   public long readLong() throws IOException {
      this.ensureBounds(10);
      int b = this.buf[this.pos++] & 255;
      int n = b & 127;
      long l;
      if (b > 127) {
         b = this.buf[this.pos++] & 255;
         n ^= (b & 127) << 7;
         if (b > 127) {
            b = this.buf[this.pos++] & 255;
            n ^= (b & 127) << 14;
            if (b > 127) {
               b = this.buf[this.pos++] & 255;
               n ^= (b & 127) << 21;
               if (b > 127) {
                  l = this.innerLongDecode((long)n);
               } else {
                  l = (long)n;
               }
            } else {
               l = (long)n;
            }
         } else {
            l = (long)n;
         }
      } else {
         l = (long)n;
      }

      if (this.pos > this.limit) {
         throw new EOFException();
      } else {
         return l >>> 1 ^ -(l & 1L);
      }
   }

   private long innerLongDecode(long l) throws IOException {
      int len = 1;
      int b = this.buf[this.pos] & 255;
      l ^= ((long)b & 127L) << 28;
      if (b > 127) {
         b = this.buf[this.pos + len++] & 255;
         l ^= ((long)b & 127L) << 35;
         if (b > 127) {
            b = this.buf[this.pos + len++] & 255;
            l ^= ((long)b & 127L) << 42;
            if (b > 127) {
               b = this.buf[this.pos + len++] & 255;
               l ^= ((long)b & 127L) << 49;
               if (b > 127) {
                  b = this.buf[this.pos + len++] & 255;
                  l ^= ((long)b & 127L) << 56;
                  if (b > 127) {
                     b = this.buf[this.pos + len++] & 255;
                     l ^= ((long)b & 127L) << 63;
                     if (b > 127) {
                        throw new InvalidNumberEncodingException("Invalid long encoding");
                     }
                  }
               }
            }
         }
      }

      this.pos += len;
      return l;
   }

   public float readFloat() throws IOException {
      this.ensureBounds(4);
      int len = 1;
      int n = this.buf[this.pos] & 255 | (this.buf[this.pos + len++] & 255) << 8 | (this.buf[this.pos + len++] & 255) << 16 | (this.buf[this.pos + len++] & 255) << 24;
      if (this.pos + 4 > this.limit) {
         throw new EOFException();
      } else {
         this.pos += 4;
         return Float.intBitsToFloat(n);
      }
   }

   public double readDouble() throws IOException {
      this.ensureBounds(8);
      int len = 1;
      int n1 = this.buf[this.pos] & 255 | (this.buf[this.pos + len++] & 255) << 8 | (this.buf[this.pos + len++] & 255) << 16 | (this.buf[this.pos + len++] & 255) << 24;
      int n2 = this.buf[this.pos + len++] & 255 | (this.buf[this.pos + len++] & 255) << 8 | (this.buf[this.pos + len++] & 255) << 16 | (this.buf[this.pos + len++] & 255) << 24;
      if (this.pos + 8 > this.limit) {
         throw new EOFException();
      } else {
         this.pos += 8;
         return Double.longBitsToDouble((long)n1 & 4294967295L | (long)n2 << 32);
      }
   }

   public Utf8 readString(Utf8 old) throws IOException {
      int length = SystemLimitException.checkMaxStringLength(this.readLong());
      Utf8 result = old != null ? old : new Utf8();
      result.setByteLength(length);
      if (0 != length) {
         this.doReadBytes(result.getBytes(), 0, length);
      }

      return result;
   }

   public String readString() throws IOException {
      return this.readString(this.scratchUtf8).toString();
   }

   public void skipString() throws IOException {
      this.doSkipBytes(this.readLong());
   }

   public ByteBuffer readBytes(ByteBuffer old) throws IOException {
      int length = SystemLimitException.checkMaxBytesLength(this.readLong());
      ByteBuffer result;
      if (old != null && length <= old.capacity()) {
         result = old;
         old.clear();
      } else {
         result = ByteBuffer.allocate(length);
      }

      this.doReadBytes(result.array(), result.position(), length);
      result.limit(length);
      return result;
   }

   public void skipBytes() throws IOException {
      this.doSkipBytes(this.readLong());
   }

   public void readFixed(byte[] bytes, int start, int length) throws IOException {
      this.doReadBytes(bytes, start, length);
   }

   public void skipFixed(int length) throws IOException {
      this.doSkipBytes((long)length);
   }

   public int readEnum() throws IOException {
      return this.readInt();
   }

   protected void doSkipBytes(long length) throws IOException {
      if (length > 0L) {
         int remaining = this.limit - this.pos;
         if (length <= (long)remaining) {
            this.pos = (int)((long)this.pos + length);
         } else {
            this.limit = this.pos = 0;
            length -= (long)remaining;
            this.source.skipSourceBytes(length);
         }

      }
   }

   protected void doReadBytes(byte[] bytes, int start, int length) throws IOException {
      if (length < 0) {
         throw new AvroRuntimeException("Malformed data. Length is negative: " + length);
      } else {
         int remaining = this.limit - this.pos;
         if (length <= remaining) {
            System.arraycopy(this.buf, this.pos, bytes, start, length);
            this.pos += length;
         } else {
            System.arraycopy(this.buf, this.pos, bytes, start, remaining);
            start += remaining;
            length -= remaining;
            this.pos = this.limit;
            this.source.readRaw(bytes, start, length);
         }

      }
   }

   protected long doReadItemCount() throws IOException {
      long result = this.readLong();
      if (result < 0L) {
         this.readLong();
         result = -result;
      }

      return result;
   }

   private long doSkipItems() throws IOException {
      long result;
      for(result = this.readLong(); result < 0L; result = this.readLong()) {
         long bytecount = this.readLong();
         this.doSkipBytes(bytecount);
      }

      return result;
   }

   public long readArrayStart() throws IOException {
      this.collectionCount = (long)SystemLimitException.checkMaxCollectionLength(0L, this.doReadItemCount());
      return this.collectionCount;
   }

   public long arrayNext() throws IOException {
      long length = this.doReadItemCount();
      this.collectionCount = (long)SystemLimitException.checkMaxCollectionLength(this.collectionCount, length);
      return length;
   }

   public long skipArray() throws IOException {
      return this.doSkipItems();
   }

   public long readMapStart() throws IOException {
      this.collectionCount = (long)SystemLimitException.checkMaxCollectionLength(0L, this.doReadItemCount());
      return this.collectionCount;
   }

   public long mapNext() throws IOException {
      long length = this.doReadItemCount();
      this.collectionCount = (long)SystemLimitException.checkMaxCollectionLength(this.collectionCount, length);
      return length;
   }

   public long skipMap() throws IOException {
      return this.doSkipItems();
   }

   public int readIndex() throws IOException {
      return this.readInt();
   }

   public boolean isEnd() throws IOException {
      if (this.pos < this.limit) {
         return false;
      } else if (this.source.isEof()) {
         return true;
      } else {
         int read = this.source.tryReadRaw(this.buf, 0, this.buf.length);
         this.pos = 0;
         this.limit = read;
         return 0 == read;
      }
   }

   private void ensureBounds(int num) throws IOException {
      int remaining = this.limit - this.pos;
      if (remaining < num) {
         this.source.compactAndFill(this.buf, this.pos, this.minPos, remaining);
         if (this.pos >= this.limit) {
            throw new EOFException();
         }
      }

   }

   public InputStream inputStream() {
      return this.source;
   }

   static class BufferAccessor {
      private final BinaryDecoder decoder;
      private byte[] buf;
      private int pos;
      private int limit;
      boolean detached = false;

      private BufferAccessor(BinaryDecoder decoder) {
         this.decoder = decoder;
      }

      void detach() {
         this.buf = this.decoder.buf;
         this.pos = this.decoder.pos;
         this.limit = this.decoder.limit;
         this.detached = true;
      }

      int getPos() {
         return this.detached ? this.pos : this.decoder.pos;
      }

      int getLim() {
         return this.detached ? this.limit : this.decoder.limit;
      }

      byte[] getBuf() {
         return this.detached ? this.buf : this.decoder.buf;
      }

      void setPos(int pos) {
         if (this.detached) {
            this.pos = pos;
         } else {
            this.decoder.pos = pos;
         }

      }

      void setLimit(int limit) {
         if (this.detached) {
            this.limit = limit;
         } else {
            this.decoder.limit = limit;
         }

      }

      void setBuf(byte[] buf, int offset, int length) {
         if (this.detached) {
            this.buf = buf;
            this.limit = offset + length;
            this.pos = offset;
         } else {
            this.decoder.buf = buf;
            this.decoder.limit = offset + length;
            this.decoder.pos = offset;
            this.decoder.minPos = offset;
         }

      }
   }

   abstract static class ByteSource extends InputStream {
      protected BufferAccessor ba;

      protected ByteSource() {
      }

      abstract boolean isEof();

      protected void attach(int bufferSize, BinaryDecoder decoder) {
         decoder.buf = new byte[bufferSize];
         decoder.pos = 0;
         decoder.minPos = 0;
         decoder.limit = 0;
         this.ba = new BufferAccessor(decoder);
      }

      protected void detach() {
         this.ba.detach();
      }

      protected abstract void skipSourceBytes(long length) throws IOException;

      protected abstract long trySkipBytes(long skipLength) throws IOException;

      protected abstract void readRaw(byte[] data, int off, int len) throws IOException;

      protected abstract int tryReadRaw(byte[] data, int off, int len) throws IOException;

      protected void compactAndFill(byte[] buf, int pos, int minPos, int remaining) throws IOException {
         System.arraycopy(buf, pos, buf, minPos, remaining);
         this.ba.setPos(minPos);
         int newLimit = remaining + this.tryReadRaw(buf, minPos + remaining, buf.length - remaining);
         this.ba.setLimit(newLimit);
      }

      public int read(byte[] b, int off, int len) throws IOException {
         int lim = this.ba.getLim();
         int pos = this.ba.getPos();
         byte[] buf = this.ba.getBuf();
         int remaining = lim - pos;
         if (remaining >= len) {
            System.arraycopy(buf, pos, b, off, len);
            pos += len;
            this.ba.setPos(pos);
            return len;
         } else {
            System.arraycopy(buf, pos, b, off, remaining);
            pos += remaining;
            this.ba.setPos(pos);
            int inputRead = remaining + this.tryReadRaw(b, off + remaining, len - remaining);
            return inputRead == 0 ? -1 : inputRead;
         }
      }

      public long skip(long n) throws IOException {
         int lim = this.ba.getLim();
         int pos = this.ba.getPos();
         int remaining = lim - pos;
         if ((long)remaining > n) {
            pos = (int)((long)pos + n);
            this.ba.setPos(pos);
            return n;
         } else {
            this.ba.setPos(lim);
            long isSkipCount = this.trySkipBytes(n - (long)remaining);
            return isSkipCount + (long)remaining;
         }
      }

      public int available() throws IOException {
         return this.ba.getLim() - this.ba.getPos();
      }
   }

   private static class InputStreamByteSource extends ByteSource {
      private InputStream in;
      protected boolean isEof = false;

      private InputStreamByteSource(InputStream in) {
         this.in = in;
      }

      protected void skipSourceBytes(long length) throws IOException {
         boolean readZero = false;

         while(length > 0L) {
            long n = this.in.skip(length);
            if (n > 0L) {
               length -= n;
            } else {
               if (n != 0L) {
                  this.isEof = true;
                  throw new EOFException();
               }

               if (readZero) {
                  this.isEof = true;
                  throw new EOFException();
               }

               readZero = true;
            }
         }

      }

      protected long trySkipBytes(long length) throws IOException {
         long leftToSkip = length;

         try {
            boolean readZero = false;

            while(leftToSkip > 0L) {
               long n = this.in.skip(length);
               if (n > 0L) {
                  leftToSkip -= n;
               } else {
                  if (n != 0L) {
                     this.isEof = true;
                     break;
                  }

                  if (readZero) {
                     this.isEof = true;
                     break;
                  }

                  readZero = true;
               }
            }
         } catch (EOFException var8) {
            this.isEof = true;
         }

         return length - leftToSkip;
      }

      protected void readRaw(byte[] data, int off, int len) throws IOException {
         while(len > 0) {
            int read = this.in.read(data, off, len);
            if (read < 0) {
               this.isEof = true;
               throw new EOFException();
            }

            len -= read;
            off += read;
         }

      }

      protected int tryReadRaw(byte[] data, int off, int len) throws IOException {
         int leftToCopy = len;

         try {
            while(leftToCopy > 0) {
               int read = this.in.read(data, off, leftToCopy);
               if (read < 0) {
                  this.isEof = true;
                  break;
               }

               leftToCopy -= read;
               off += read;
            }
         } catch (EOFException var6) {
            this.isEof = true;
         }

         return len - leftToCopy;
      }

      public int read() throws IOException {
         if (this.ba.getLim() - this.ba.getPos() == 0) {
            return this.in.read();
         } else {
            int position = this.ba.getPos();
            int result = this.ba.getBuf()[position] & 255;
            this.ba.setPos(position + 1);
            return result;
         }
      }

      public boolean isEof() {
         return this.isEof;
      }

      public void close() throws IOException {
         this.in.close();
      }
   }

   private static class ByteArrayByteSource extends ByteSource {
      private static final int MIN_SIZE = 16;
      private byte[] data;
      private int position;
      private int max;
      private boolean compacted = false;

      private ByteArrayByteSource(byte[] data, int start, int len) {
         if (len < 16) {
            this.data = Arrays.copyOfRange(data, start, start + 16);
            this.position = 0;
            this.max = len;
         } else {
            this.data = data;
            this.position = start;
            this.max = start + len;
         }

      }

      protected void attach(int bufferSize, BinaryDecoder decoder) {
         decoder.buf = this.data;
         decoder.pos = this.position;
         decoder.minPos = this.position;
         decoder.limit = this.max;
         this.ba = new BufferAccessor(decoder);
      }

      protected void skipSourceBytes(long length) throws IOException {
         long skipped = this.trySkipBytes(length);
         if (skipped < length) {
            throw new EOFException();
         }
      }

      protected long trySkipBytes(long length) throws IOException {
         this.max = this.ba.getLim();
         this.position = this.ba.getPos();
         long remaining = (long)this.max - (long)this.position;
         if (remaining >= length) {
            this.position = (int)((long)this.position + length);
            this.ba.setPos(this.position);
            return length;
         } else {
            this.position = (int)((long)this.position + remaining);
            this.ba.setPos(this.position);
            return remaining;
         }
      }

      protected void readRaw(byte[] data, int off, int len) throws IOException {
         int read = this.tryReadRaw(data, off, len);
         if (read < len) {
            throw new EOFException();
         }
      }

      protected int tryReadRaw(byte[] data, int off, int len) throws IOException {
         return 0;
      }

      protected void compactAndFill(byte[] buf, int pos, int minPos, int remaining) throws IOException {
         if (!this.compacted) {
            byte[] tinybuf = new byte[remaining + 16];
            System.arraycopy(buf, pos, tinybuf, 0, remaining);
            this.ba.setBuf(tinybuf, 0, remaining);
            this.compacted = true;
         }

      }

      public int read() throws IOException {
         this.max = this.ba.getLim();
         this.position = this.ba.getPos();
         if (this.position >= this.max) {
            return -1;
         } else {
            int result = this.ba.getBuf()[this.position++] & 255;
            this.ba.setPos(this.position);
            return result;
         }
      }

      public void close() throws IOException {
         this.ba.setPos(this.ba.getLim());
      }

      public boolean isEof() {
         int remaining = this.ba.getLim() - this.ba.getPos();
         return remaining == 0;
      }
   }
}
