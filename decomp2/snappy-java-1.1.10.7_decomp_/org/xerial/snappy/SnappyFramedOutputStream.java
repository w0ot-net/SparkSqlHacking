package org.xerial.snappy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.Checksum;
import org.xerial.snappy.pool.BufferPool;
import org.xerial.snappy.pool.DefaultPoolFactory;

public final class SnappyFramedOutputStream extends OutputStream implements WritableByteChannel {
   public static final int MAX_BLOCK_SIZE = 65536;
   public static final int DEFAULT_BLOCK_SIZE = 65536;
   public static final double DEFAULT_MIN_COMPRESSION_RATIO = 0.85;
   private final Checksum crc32;
   private final ByteBuffer headerBuffer;
   private final BufferPool bufferPool;
   private final int blockSize;
   private final ByteBuffer buffer;
   private final ByteBuffer directInputBuffer;
   private final ByteBuffer outputBuffer;
   private final double minCompressionRatio;
   private final WritableByteChannel out;
   private boolean closed;

   public SnappyFramedOutputStream(OutputStream var1) throws IOException {
      this(var1, 65536, 0.85, DefaultPoolFactory.getDefaultPool());
   }

   public SnappyFramedOutputStream(OutputStream var1, BufferPool var2) throws IOException {
      this(var1, 65536, 0.85, var2);
   }

   public SnappyFramedOutputStream(OutputStream var1, int var2, double var3) throws IOException {
      this(Channels.newChannel(var1), var2, var3, DefaultPoolFactory.getDefaultPool());
   }

   public SnappyFramedOutputStream(OutputStream var1, int var2, double var3, BufferPool var5) throws IOException {
      this(Channels.newChannel(var1), var2, var3, var5);
   }

   public SnappyFramedOutputStream(WritableByteChannel var1) throws IOException {
      this(var1, 65536, 0.85, DefaultPoolFactory.getDefaultPool());
   }

   public SnappyFramedOutputStream(WritableByteChannel var1, BufferPool var2) throws IOException {
      this(var1, 65536, 0.85, var2);
   }

   public SnappyFramedOutputStream(WritableByteChannel var1, int var2, double var3) throws IOException {
      this(var1, var2, var3, DefaultPoolFactory.getDefaultPool());
   }

   public SnappyFramedOutputStream(WritableByteChannel var1, int var2, double var3, BufferPool var5) throws IOException {
      this.crc32 = SnappyFramed.getCRC32C();
      this.headerBuffer = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
      if (var1 == null) {
         throw new NullPointerException("out is null");
      } else if (var5 == null) {
         throw new NullPointerException("buffer pool is null");
      } else if (!(var3 <= (double)0.0F) && !(var3 > (double)1.0F)) {
         if (var2 > 0 && var2 <= 65536) {
            this.blockSize = var2;
            this.out = var1;
            this.minCompressionRatio = var3;
            this.bufferPool = var5;
            this.buffer = ByteBuffer.wrap(var5.allocateArray(var2), 0, var2);
            this.directInputBuffer = var5.allocateDirect(var2);
            this.outputBuffer = var5.allocateDirect(Snappy.maxCompressedLength(var2));
            this.writeHeader(var1);
         } else {
            throw new IllegalArgumentException("block size " + var2 + " must be in (0, 65536]");
         }
      } else {
         throw new IllegalArgumentException("minCompressionRatio " + var3 + " must be in (0,1.0]");
      }
   }

   private void writeHeader(WritableByteChannel var1) throws IOException {
      var1.write(ByteBuffer.wrap(SnappyFramed.HEADER_BYTES));
   }

   public boolean isOpen() {
      return !this.closed;
   }

   public void write(int var1) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         if (this.buffer.remaining() <= 0) {
            this.flushBuffer();
         }

         this.buffer.put((byte)var1);
      }
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else if (var1 == null) {
         throw new NullPointerException();
      } else if (var2 >= 0 && var2 <= var1.length && var3 >= 0 && var2 + var3 <= var1.length && var2 + var3 >= 0) {
         while(var3 > 0) {
            if (this.buffer.remaining() <= 0) {
               this.flushBuffer();
            }

            int var4 = Math.min(var3, this.buffer.remaining());
            this.buffer.put(var1, var2, var4);
            var2 += var4;
            var3 -= var4;
         }

      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public int write(ByteBuffer var1) throws IOException {
      if (this.closed) {
         throw new ClosedChannelException();
      } else {
         if (this.buffer.remaining() <= 0) {
            this.flushBuffer();
         }

         int var2 = var1.remaining();
         if (this.buffer.remaining() >= var1.remaining()) {
            this.buffer.put(var1);
            return var2;
         } else {
            int var3 = var1.position() + var1.remaining();

            while(var1.position() + this.buffer.remaining() <= var3) {
               var1.limit(var1.position() + this.buffer.remaining());
               this.buffer.put(var1);
               this.flushBuffer();
            }

            var1.limit(var3);
            this.buffer.put(var1);
            return var2;
         }
      }
   }

   public long transferFrom(InputStream var1) throws IOException {
      if (this.closed) {
         throw new ClosedChannelException();
      } else if (var1 == null) {
         throw new NullPointerException();
      } else {
         if (this.buffer.remaining() == 0) {
            this.flushBuffer();
         }

         assert this.buffer.hasArray();

         byte[] var2 = this.buffer.array();
         int var3 = this.buffer.arrayOffset();

         long var4;
         int var6;
         for(var4 = 0L; (var6 = var1.read(var2, var3 + this.buffer.position(), this.buffer.remaining())) != -1; var4 += (long)var6) {
            this.buffer.position(this.buffer.position() + var6);
            if (this.buffer.remaining() == 0) {
               this.flushBuffer();
            }
         }

         return var4;
      }
   }

   public long transferFrom(ReadableByteChannel var1) throws IOException {
      if (this.closed) {
         throw new ClosedChannelException();
      } else if (var1 == null) {
         throw new NullPointerException();
      } else {
         if (this.buffer.remaining() == 0) {
            this.flushBuffer();
         }

         long var2;
         int var4;
         for(var2 = 0L; (var4 = var1.read(this.buffer)) != -1; var2 += (long)var4) {
            if (this.buffer.remaining() == 0) {
               this.flushBuffer();
            }
         }

         return var2;
      }
   }

   public final void flush() throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         this.flushBuffer();
      }
   }

   public final void close() throws IOException {
      if (!this.closed) {
         try {
            this.flush();
            this.out.close();
         } finally {
            this.closed = true;
            this.bufferPool.releaseArray(this.buffer.array());
            this.bufferPool.releaseDirect(this.directInputBuffer);
            this.bufferPool.releaseDirect(this.outputBuffer);
         }

      }
   }

   private void flushBuffer() throws IOException {
      if (this.buffer.position() > 0) {
         this.buffer.flip();
         this.writeCompressed(this.buffer);
         this.buffer.clear();
         this.buffer.limit(this.blockSize);
      }

   }

   private void writeCompressed(ByteBuffer var1) throws IOException {
      byte[] var2 = var1.array();
      int var3 = var1.remaining();
      int var4 = SnappyFramed.maskedCrc32c(this.crc32, var2, 0, var3);
      this.directInputBuffer.clear();
      this.directInputBuffer.put(var1);
      this.directInputBuffer.flip();
      this.outputBuffer.clear();
      Snappy.compress(this.directInputBuffer, this.outputBuffer);
      int var5 = this.outputBuffer.remaining();
      if ((double)var5 / (double)var3 <= this.minCompressionRatio) {
         this.writeBlock(this.out, this.outputBuffer, true, var4);
      } else {
         var1.flip();
         this.writeBlock(this.out, var1, false, var4);
      }

   }

   private void writeBlock(WritableByteChannel var1, ByteBuffer var2, boolean var3, int var4) throws IOException {
      this.headerBuffer.clear();
      this.headerBuffer.put((byte)(var3 ? 0 : 1));
      int var5 = var2.remaining() + 4;
      this.headerBuffer.put((byte)var5);
      this.headerBuffer.put((byte)(var5 >>> 8));
      this.headerBuffer.put((byte)(var5 >>> 16));
      this.headerBuffer.putInt(var4);
      this.headerBuffer.flip();
      var1.write(this.headerBuffer);
      var1.write(var2);
   }
}
