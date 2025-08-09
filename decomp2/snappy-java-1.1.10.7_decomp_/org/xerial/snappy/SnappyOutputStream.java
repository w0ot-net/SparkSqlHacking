package org.xerial.snappy;

import java.io.IOException;
import java.io.OutputStream;
import org.xerial.snappy.buffer.BufferAllocator;
import org.xerial.snappy.buffer.BufferAllocatorFactory;
import org.xerial.snappy.buffer.CachedBufferAllocator;

public class SnappyOutputStream extends OutputStream {
   public static final int MAX_BLOCK_SIZE = 536870912;
   static final int MIN_BLOCK_SIZE = 1024;
   static final int DEFAULT_BLOCK_SIZE = 32768;
   protected final OutputStream out;
   private final int blockSize;
   private final BufferAllocator inputBufferAllocator;
   private final BufferAllocator outputBufferAllocator;
   protected byte[] inputBuffer;
   protected byte[] outputBuffer;
   private int inputCursor;
   private int outputCursor;
   private boolean headerWritten;
   private boolean closed;

   public SnappyOutputStream(OutputStream var1) {
      this(var1, 32768);
   }

   public SnappyOutputStream(OutputStream var1, int var2) {
      this(var1, var2, CachedBufferAllocator.getBufferAllocatorFactory());
   }

   public SnappyOutputStream(OutputStream var1, int var2, BufferAllocatorFactory var3) {
      this.inputCursor = 0;
      this.outputCursor = 0;
      this.out = var1;
      this.blockSize = Math.max(1024, var2);
      if (this.blockSize > 536870912) {
         throw new IllegalArgumentException(String.format("Provided chunk size %,d larger than max %,d", this.blockSize, 536870912));
      } else {
         int var5 = SnappyCodec.HEADER_SIZE + 4 + Snappy.maxCompressedLength(var2);
         this.inputBufferAllocator = var3.getBufferAllocator(var2);
         this.outputBufferAllocator = var3.getBufferAllocator(var5);
         this.inputBuffer = this.inputBufferAllocator.allocate(var2);
         this.outputBuffer = this.outputBufferAllocator.allocate(var5);
      }
   }

   public void write(byte[] var1, int var2, int var3) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         int var5;
         for(int var4 = 0; var4 < var3; var4 += var5) {
            var5 = Math.min(var3 - var4, this.blockSize - this.inputCursor);
            if (var5 > 0) {
               System.arraycopy(var1, var2 + var4, this.inputBuffer, this.inputCursor, var5);
               this.inputCursor += var5;
            }

            if (this.inputCursor < this.blockSize) {
               return;
            }

            this.compressInput();
         }

      }
   }

   public void write(long[] var1, int var2, int var3) throws IOException {
      this.rawWrite(var1, var2 * 8, var3 * 8);
   }

   public void write(double[] var1, int var2, int var3) throws IOException {
      this.rawWrite(var1, var2 * 8, var3 * 8);
   }

   public void write(float[] var1, int var2, int var3) throws IOException {
      this.rawWrite(var1, var2 * 4, var3 * 4);
   }

   public void write(int[] var1, int var2, int var3) throws IOException {
      this.rawWrite(var1, var2 * 4, var3 * 4);
   }

   public void write(short[] var1, int var2, int var3) throws IOException {
      this.rawWrite(var1, var2 * 2, var3 * 2);
   }

   public void write(long[] var1) throws IOException {
      this.write((long[])var1, 0, var1.length);
   }

   public void write(double[] var1) throws IOException {
      this.write((double[])var1, 0, var1.length);
   }

   public void write(float[] var1) throws IOException {
      this.write((float[])var1, 0, var1.length);
   }

   public void write(int[] var1) throws IOException {
      this.write((int[])var1, 0, var1.length);
   }

   public void write(short[] var1) throws IOException {
      this.write((short[])var1, 0, var1.length);
   }

   private boolean hasSufficientOutputBufferFor(int var1) {
      int var2 = Snappy.maxCompressedLength(var1);
      return var2 < this.outputBuffer.length - this.outputCursor - 4;
   }

   public void rawWrite(Object var1, int var2, int var3) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         int var5;
         for(int var4 = 0; var4 < var3; var4 += var5) {
            var5 = Math.min(var3 - var4, this.blockSize - this.inputCursor);
            if (var5 > 0) {
               Snappy.arrayCopy(var1, var2 + var4, var5, this.inputBuffer, this.inputCursor);
               this.inputCursor += var5;
            }

            if (this.inputCursor < this.blockSize) {
               return;
            }

            this.compressInput();
         }

      }
   }

   public void write(int var1) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         if (this.inputCursor >= this.inputBuffer.length) {
            this.compressInput();
         }

         this.inputBuffer[this.inputCursor++] = (byte)var1;
      }
   }

   public void flush() throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         this.compressInput();
         this.dumpOutput();
         this.out.flush();
      }
   }

   static void writeInt(byte[] var0, int var1, int var2) {
      var0[var1] = (byte)(var2 >> 24 & 255);
      var0[var1 + 1] = (byte)(var2 >> 16 & 255);
      var0[var1 + 2] = (byte)(var2 >> 8 & 255);
      var0[var1 + 3] = (byte)(var2 >> 0 & 255);
   }

   static int readInt(byte[] var0, int var1) {
      int var2 = (var0[var1] & 255) << 24;
      int var3 = (var0[var1 + 1] & 255) << 16;
      int var4 = (var0[var1 + 2] & 255) << 8;
      int var5 = var0[var1 + 3] & 255;
      return var2 | var3 | var4 | var5;
   }

   protected void dumpOutput() throws IOException {
      if (this.outputCursor > 0) {
         this.out.write(this.outputBuffer, 0, this.outputCursor);
         this.outputCursor = 0;
      }

   }

   protected void compressInput() throws IOException {
      if (!this.headerWritten) {
         this.outputCursor = this.writeHeader();
         this.headerWritten = true;
      }

      if (this.inputCursor > 0) {
         if (!this.hasSufficientOutputBufferFor(this.inputCursor)) {
            this.dumpOutput();
         }

         this.writeBlockPreemble();
         int var1 = Snappy.compress(this.inputBuffer, 0, this.inputCursor, this.outputBuffer, this.outputCursor + 4);
         writeInt(this.outputBuffer, this.outputCursor, var1);
         this.outputCursor += 4 + var1;
         this.inputCursor = 0;
      }
   }

   protected int writeHeader() {
      return SnappyCodec.currentHeader.writeHeader(this.outputBuffer, 0);
   }

   protected void writeBlockPreemble() {
   }

   protected void writeCurrentDataSize() {
      writeInt(this.outputBuffer, this.outputCursor, this.inputCursor);
      this.outputCursor += 4;
   }

   public void close() throws IOException {
      if (!this.closed) {
         try {
            this.flush();
            this.out.close();
         } finally {
            this.closed = true;
            this.inputBufferAllocator.release(this.inputBuffer);
            this.outputBufferAllocator.release(this.outputBuffer);
            this.inputBuffer = null;
            this.outputBuffer = null;
         }

      }
   }
}
