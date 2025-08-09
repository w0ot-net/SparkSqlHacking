package io.netty.buffer;

import io.netty.util.internal.ObjectUtil;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class ByteBufInputStream extends InputStream implements DataInput {
   private final ByteBuf buffer;
   private final int startIndex;
   private final int endIndex;
   private boolean closed;
   private final boolean releaseOnClose;
   private StringBuilder lineBuf;

   public ByteBufInputStream(ByteBuf buffer) {
      this(buffer, buffer.readableBytes());
   }

   public ByteBufInputStream(ByteBuf buffer, int length) {
      this(buffer, length, false);
   }

   public ByteBufInputStream(ByteBuf buffer, boolean releaseOnClose) {
      this(buffer, buffer.readableBytes(), releaseOnClose);
   }

   public ByteBufInputStream(ByteBuf buffer, int length, boolean releaseOnClose) {
      ObjectUtil.checkNotNull(buffer, "buffer");
      if (length < 0) {
         if (releaseOnClose) {
            buffer.release();
         }

         ObjectUtil.checkPositiveOrZero(length, "length");
      }

      if (length > buffer.readableBytes()) {
         if (releaseOnClose) {
            buffer.release();
         }

         throw new IndexOutOfBoundsException("Too many bytes to be read - Needs " + length + ", maximum is " + buffer.readableBytes());
      } else {
         this.releaseOnClose = releaseOnClose;
         this.buffer = buffer;
         this.startIndex = buffer.readerIndex();
         this.endIndex = this.startIndex + length;
         buffer.markReaderIndex();
      }
   }

   public int readBytes() {
      return this.buffer.readerIndex() - this.startIndex;
   }

   public void close() throws IOException {
      try {
         super.close();
      } finally {
         if (this.releaseOnClose && !this.closed) {
            this.closed = true;
            this.buffer.release();
         }

      }

   }

   public int available() throws IOException {
      return this.endIndex - this.buffer.readerIndex();
   }

   public void mark(int readlimit) {
      this.buffer.markReaderIndex();
   }

   public boolean markSupported() {
      return true;
   }

   public int read() throws IOException {
      int available = this.available();
      return available == 0 ? -1 : this.buffer.readByte() & 255;
   }

   public int read(byte[] b, int off, int len) throws IOException {
      int available = this.available();
      if (available == 0) {
         return -1;
      } else {
         len = Math.min(available, len);
         this.buffer.readBytes(b, off, len);
         return len;
      }
   }

   public void reset() throws IOException {
      this.buffer.resetReaderIndex();
   }

   public long skip(long n) throws IOException {
      return n > 2147483647L ? (long)this.skipBytes(Integer.MAX_VALUE) : (long)this.skipBytes((int)n);
   }

   public boolean readBoolean() throws IOException {
      this.checkAvailable(1);
      return this.read() != 0;
   }

   public byte readByte() throws IOException {
      int available = this.available();
      if (available == 0) {
         throw new EOFException();
      } else {
         return this.buffer.readByte();
      }
   }

   public char readChar() throws IOException {
      return (char)this.readShort();
   }

   public double readDouble() throws IOException {
      return Double.longBitsToDouble(this.readLong());
   }

   public float readFloat() throws IOException {
      return Float.intBitsToFloat(this.readInt());
   }

   public void readFully(byte[] b) throws IOException {
      this.readFully(b, 0, b.length);
   }

   public void readFully(byte[] b, int off, int len) throws IOException {
      this.checkAvailable(len);
      this.buffer.readBytes(b, off, len);
   }

   public int readInt() throws IOException {
      this.checkAvailable(4);
      return this.buffer.readInt();
   }

   public String readLine() throws IOException {
      int available = this.available();
      if (available == 0) {
         return null;
      } else {
         if (this.lineBuf != null) {
            this.lineBuf.setLength(0);
         }

         while(true) {
            int c = this.buffer.readUnsignedByte();
            --available;
            switch (c) {
               case 10:
                  return this.lineBuf != null && this.lineBuf.length() > 0 ? this.lineBuf.toString() : "";
               case 13:
                  if (available > 0 && (char)this.buffer.getUnsignedByte(this.buffer.readerIndex()) == '\n') {
                     this.buffer.skipBytes(1);
                     --available;
                  }

                  return this.lineBuf != null && this.lineBuf.length() > 0 ? this.lineBuf.toString() : "";
               default:
                  if (this.lineBuf == null) {
                     this.lineBuf = new StringBuilder();
                  }

                  this.lineBuf.append((char)c);
                  if (available <= 0) {
                     return this.lineBuf != null && this.lineBuf.length() > 0 ? this.lineBuf.toString() : "";
                  }
            }
         }
      }
   }

   public long readLong() throws IOException {
      this.checkAvailable(8);
      return this.buffer.readLong();
   }

   public short readShort() throws IOException {
      this.checkAvailable(2);
      return this.buffer.readShort();
   }

   public String readUTF() throws IOException {
      return DataInputStream.readUTF(this);
   }

   public int readUnsignedByte() throws IOException {
      return this.readByte() & 255;
   }

   public int readUnsignedShort() throws IOException {
      return this.readShort() & '\uffff';
   }

   public int skipBytes(int n) throws IOException {
      int nBytes = Math.min(this.available(), n);
      this.buffer.skipBytes(nBytes);
      return nBytes;
   }

   private void checkAvailable(int fieldSize) throws IOException {
      if (fieldSize < 0) {
         throw new IndexOutOfBoundsException("fieldSize cannot be a negative number");
      } else if (fieldSize > this.available()) {
         throw new EOFException("fieldSize is too long! Length is " + fieldSize + ", but maximum is " + this.available());
      }
   }
}
