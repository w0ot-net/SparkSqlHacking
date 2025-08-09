package org.apache.parquet.bytes;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class LittleEndianDataInputStream extends InputStream {
   private final InputStream in;
   private byte[] readBuffer = new byte[8];

   public LittleEndianDataInputStream(InputStream in) {
      this.in = in;
   }

   public final void readFully(byte[] b) throws IOException {
      this.readFully(b, 0, b.length);
   }

   public final void readFully(byte[] b, int off, int len) throws IOException {
      if (len < 0) {
         throw new IndexOutOfBoundsException();
      } else {
         int count;
         for(int n = 0; n < len; n += count) {
            count = this.in.read(b, off + n, len - n);
            if (count < 0) {
               throw new EOFException();
            }
         }

      }
   }

   public final int skipBytes(int n) throws IOException {
      int total = 0;

      int cur;
      for(cur = 0; total < n && (cur = (int)this.in.skip((long)(n - total))) > 0; total += cur) {
      }

      return total;
   }

   public int read() throws IOException {
      return this.in.read();
   }

   public int hashCode() {
      return this.in.hashCode();
   }

   public int read(byte[] b) throws IOException {
      return this.in.read(b);
   }

   public boolean equals(Object obj) {
      return this.in.equals(obj);
   }

   public int read(byte[] b, int off, int len) throws IOException {
      return this.in.read(b, off, len);
   }

   public long skip(long n) throws IOException {
      return this.in.skip(n);
   }

   public int available() throws IOException {
      return this.in.available();
   }

   public void close() throws IOException {
      this.in.close();
   }

   public void mark(int readlimit) {
      this.in.mark(readlimit);
   }

   public void reset() throws IOException {
      this.in.reset();
   }

   public boolean markSupported() {
      return this.in.markSupported();
   }

   public final boolean readBoolean() throws IOException {
      int ch = this.in.read();
      if (ch < 0) {
         throw new EOFException();
      } else {
         return ch != 0;
      }
   }

   public final byte readByte() throws IOException {
      int ch = this.in.read();
      if (ch < 0) {
         throw new EOFException();
      } else {
         return (byte)ch;
      }
   }

   public final int readUnsignedByte() throws IOException {
      int ch = this.in.read();
      if (ch < 0) {
         throw new EOFException();
      } else {
         return ch;
      }
   }

   public final short readShort() throws IOException {
      int ch2 = this.in.read();
      int ch1 = this.in.read();
      if ((ch1 | ch2) < 0) {
         throw new EOFException();
      } else {
         return (short)((ch1 << 8) + (ch2 << 0));
      }
   }

   public final int readUnsignedShort() throws IOException {
      int ch2 = this.in.read();
      int ch1 = this.in.read();
      if ((ch1 | ch2) < 0) {
         throw new EOFException();
      } else {
         return (ch1 << 8) + (ch2 << 0);
      }
   }

   public final int readInt() throws IOException {
      int ch4 = this.in.read();
      int ch3 = this.in.read();
      int ch2 = this.in.read();
      int ch1 = this.in.read();
      if ((ch1 | ch2 | ch3 | ch4) < 0) {
         throw new EOFException();
      } else {
         return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
      }
   }

   public final long readLong() throws IOException {
      this.readFully(this.readBuffer, 0, 8);
      return ((long)this.readBuffer[7] << 56) + ((long)(this.readBuffer[6] & 255) << 48) + ((long)(this.readBuffer[5] & 255) << 40) + ((long)(this.readBuffer[4] & 255) << 32) + ((long)(this.readBuffer[3] & 255) << 24) + (long)((this.readBuffer[2] & 255) << 16) + (long)((this.readBuffer[1] & 255) << 8) + (long)((this.readBuffer[0] & 255) << 0);
   }

   public final float readFloat() throws IOException {
      return Float.intBitsToFloat(this.readInt());
   }

   public final double readDouble() throws IOException {
      return Double.longBitsToDouble(this.readLong());
   }
}
