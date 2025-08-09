package org.xerial.snappy;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.zip.Checksum;
import org.xerial.snappy.pool.BufferPool;
import org.xerial.snappy.pool.DefaultPoolFactory;

public final class SnappyFramedInputStream extends InputStream implements ReadableByteChannel {
   private final Checksum crc32;
   private final ReadableByteChannel rbc;
   private final ByteBuffer frameHeader;
   private final boolean verifyChecksums;
   private final BufferPool bufferPool;
   private ByteBuffer input;
   private ByteBuffer uncompressedDirect;
   private boolean closed;
   private boolean eof;
   private int valid;
   private int position;
   private byte[] buffer;

   public SnappyFramedInputStream(InputStream var1) throws IOException {
      this(var1, true, DefaultPoolFactory.getDefaultPool());
   }

   public SnappyFramedInputStream(InputStream var1, BufferPool var2) throws IOException {
      this(var1, true, var2);
   }

   public SnappyFramedInputStream(InputStream var1, boolean var2) throws IOException {
      this(var1, var2, DefaultPoolFactory.getDefaultPool());
   }

   public SnappyFramedInputStream(InputStream var1, boolean var2, BufferPool var3) throws IOException {
      this(Channels.newChannel(var1), var2, var3);
   }

   public SnappyFramedInputStream(ReadableByteChannel var1, BufferPool var2) throws IOException {
      this(var1, true, var2);
   }

   public SnappyFramedInputStream(ReadableByteChannel var1) throws IOException {
      this(var1, true);
   }

   public SnappyFramedInputStream(ReadableByteChannel var1, boolean var2) throws IOException {
      this(var1, var2, DefaultPoolFactory.getDefaultPool());
   }

   public SnappyFramedInputStream(ReadableByteChannel var1, boolean var2, BufferPool var3) throws IOException {
      this.crc32 = SnappyFramed.getCRC32C();
      if (var1 == null) {
         throw new NullPointerException("in is null");
      } else if (var3 == null) {
         throw new NullPointerException("bufferPool is null");
      } else {
         this.bufferPool = var3;
         this.rbc = var1;
         this.verifyChecksums = var2;
         this.allocateBuffersBasedOnSize(65541);
         this.frameHeader = ByteBuffer.allocate(4);
         byte[] var4 = SnappyFramed.HEADER_BYTES;
         byte[] var5 = new byte[var4.length];
         ByteBuffer var6 = ByteBuffer.wrap(var5);
         int var7 = SnappyFramed.readBytes(var1, var6);
         if (var7 < var4.length) {
            throw new EOFException("encountered EOF while reading stream header");
         } else if (!Arrays.equals(var4, var5)) {
            throw new IOException("invalid stream header");
         }
      }
   }

   private void allocateBuffersBasedOnSize(int var1) {
      if (this.input != null) {
         this.bufferPool.releaseDirect(this.input);
      }

      if (this.uncompressedDirect != null) {
         this.bufferPool.releaseDirect(this.uncompressedDirect);
      }

      if (this.buffer != null) {
         this.bufferPool.releaseArray(this.buffer);
      }

      this.input = this.bufferPool.allocateDirect(var1);
      int var2 = Snappy.maxCompressedLength(var1);
      this.uncompressedDirect = this.bufferPool.allocateDirect(var2);
      this.buffer = this.bufferPool.allocateArray(var2);
   }

   public int read() throws IOException {
      if (this.closed) {
         return -1;
      } else {
         return !this.ensureBuffer() ? -1 : this.buffer[this.position++] & 255;
      }
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("output is null");
      } else if (var2 >= 0 && var3 >= 0 && var2 + var3 <= var1.length) {
         if (this.closed) {
            throw new ClosedChannelException();
         } else if (var3 == 0) {
            return 0;
         } else if (!this.ensureBuffer()) {
            return -1;
         } else {
            int var4 = Math.min(var3, this.available());
            System.arraycopy(this.buffer, this.position, var1, var2, var4);
            this.position += var4;
            return var4;
         }
      } else {
         throw new IllegalArgumentException("invalid offset [" + var2 + "] and length [" + var3 + ']');
      }
   }

   public int available() throws IOException {
      return this.closed ? 0 : this.valid - this.position;
   }

   public boolean isOpen() {
      return !this.closed;
   }

   public int read(ByteBuffer var1) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("dst is null");
      } else if (this.closed) {
         throw new ClosedChannelException();
      } else if (var1.remaining() == 0) {
         return 0;
      } else if (!this.ensureBuffer()) {
         return -1;
      } else {
         int var2 = Math.min(var1.remaining(), this.available());
         var1.put(this.buffer, this.position, var2);
         this.position += var2;
         return var2;
      }
   }

   public long transferTo(OutputStream var1) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("os is null");
      } else if (this.closed) {
         throw new ClosedChannelException();
      } else {
         long var2;
         int var4;
         for(var2 = 0L; this.ensureBuffer(); var2 += (long)var4) {
            var4 = this.available();
            var1.write(this.buffer, this.position, var4);
            this.position += var4;
         }

         return var2;
      }
   }

   public long transferTo(WritableByteChannel var1) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException("wbc is null");
      } else if (this.closed) {
         throw new ClosedChannelException();
      } else {
         ByteBuffer var2 = ByteBuffer.wrap(this.buffer);

         long var3;
         int var5;
         for(var3 = 0L; this.ensureBuffer(); var3 += (long)var5) {
            var2.clear();
            var2.position(this.position);
            var2.limit(this.position + this.available());
            var1.write(var2);
            var5 = var2.position() - this.position;
            this.position += var5;
         }

         return var3;
      }
   }

   public void close() throws IOException {
      try {
         this.rbc.close();
      } finally {
         if (!this.closed) {
            this.closed = true;
            if (this.input != null) {
               this.bufferPool.releaseDirect(this.input);
               this.input = null;
            }

            if (this.uncompressedDirect != null) {
               this.bufferPool.releaseDirect(this.uncompressedDirect);
               this.uncompressedDirect = null;
            }

            if (this.buffer != null) {
               this.bufferPool.releaseArray(this.buffer);
               this.buffer = null;
            }
         }

      }

   }

   private boolean ensureBuffer() throws IOException {
      if (this.available() > 0) {
         return true;
      } else if (this.eof) {
         return false;
      } else if (!this.readBlockHeader()) {
         this.eof = true;
         return false;
      } else {
         FrameMetaData var1 = this.getFrameMetaData(this.frameHeader);
         if (SnappyFramedInputStream.FrameAction.SKIP == var1.frameAction) {
            SnappyFramed.skip(this.rbc, var1.length, ByteBuffer.wrap(this.buffer));
            return this.ensureBuffer();
         } else {
            if (var1.length > this.input.capacity()) {
               this.allocateBuffersBasedOnSize(var1.length);
            }

            this.input.clear();
            this.input.limit(var1.length);
            int var2 = SnappyFramed.readBytes(this.rbc, this.input);
            if (var2 != var1.length) {
               throw new EOFException("unexpectd EOF when reading frame");
            } else {
               this.input.flip();
               FrameData var3 = this.getFrameData(this.input);
               if (SnappyFramedInputStream.FrameAction.UNCOMPRESS == var1.frameAction) {
                  this.input.position(var3.offset);
                  int var4 = Snappy.uncompressedLength(this.input);
                  if (var4 > this.uncompressedDirect.capacity()) {
                     this.bufferPool.releaseDirect(this.uncompressedDirect);
                     this.bufferPool.releaseArray(this.buffer);
                     this.uncompressedDirect = this.bufferPool.allocateDirect(var4);
                     this.buffer = this.bufferPool.allocateArray(var4);
                  }

                  this.uncompressedDirect.clear();
                  this.valid = Snappy.uncompress(this.input, this.uncompressedDirect);
                  this.uncompressedDirect.get(this.buffer, 0, this.valid);
                  this.position = 0;
               } else {
                  this.input.position(var3.offset);
                  this.position = 0;
                  this.valid = this.input.remaining();
                  this.input.get(this.buffer, 0, this.input.remaining());
               }

               if (this.verifyChecksums) {
                  int var5 = SnappyFramed.maskedCrc32c(this.crc32, this.buffer, this.position, this.valid - this.position);
                  if (var3.checkSum != var5) {
                     throw new IOException("Corrupt input: invalid checksum");
                  }
               }

               return true;
            }
         }
      }
   }

   private boolean readBlockHeader() throws IOException {
      this.frameHeader.clear();
      int var1 = SnappyFramed.readBytes(this.rbc, this.frameHeader);
      if (var1 == -1) {
         return false;
      } else if (var1 < this.frameHeader.capacity()) {
         throw new EOFException("encountered EOF while reading block header");
      } else {
         this.frameHeader.flip();
         return true;
      }
   }

   private FrameMetaData getFrameMetaData(ByteBuffer var1) throws IOException {
      assert var1.hasArray();

      byte[] var2 = var1.array();
      int var3 = var2[1] & 255;
      var3 |= (var2[2] & 255) << 8;
      var3 |= (var2[3] & 255) << 16;
      byte var4 = 0;
      int var6 = var2[0] & 255;
      FrameAction var5;
      switch (var6) {
         case 0:
            var5 = SnappyFramedInputStream.FrameAction.UNCOMPRESS;
            var4 = 5;
            break;
         case 1:
            var5 = SnappyFramedInputStream.FrameAction.RAW;
            var4 = 5;
            break;
         case 255:
            if (var3 != 6) {
               throw new IOException("stream identifier chunk with invalid length: " + var3);
            }

            var5 = SnappyFramedInputStream.FrameAction.SKIP;
            var4 = 6;
            break;
         default:
            if (var6 <= 127) {
               throw new IOException("unsupported unskippable chunk: " + Integer.toHexString(var6));
            }

            var5 = SnappyFramedInputStream.FrameAction.SKIP;
            var4 = 0;
      }

      if (var3 < var4) {
         throw new IOException("invalid length: " + var3 + " for chunk flag: " + Integer.toHexString(var6));
      } else {
         return new FrameMetaData(var5, var3);
      }
   }

   private FrameData getFrameData(ByteBuffer var1) throws IOException {
      var1.order(ByteOrder.LITTLE_ENDIAN);
      int var2 = var1.getInt(var1.position());
      return new FrameData(var2, 4);
   }

   static enum FrameAction {
      RAW,
      SKIP,
      UNCOMPRESS;
   }

   public static final class FrameMetaData {
      final int length;
      final FrameAction frameAction;

      public FrameMetaData(FrameAction var1, int var2) {
         this.frameAction = var1;
         this.length = var2;
      }
   }

   public static final class FrameData {
      final int checkSum;
      final int offset;

      public FrameData(int var1, int var2) {
         this.checkSum = var1;
         this.offset = var2;
      }
   }
}
