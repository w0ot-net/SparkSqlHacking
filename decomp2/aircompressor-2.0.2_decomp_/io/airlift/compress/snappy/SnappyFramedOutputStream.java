package io.airlift.compress.snappy;

import java.io.IOException;
import java.io.OutputStream;

public final class SnappyFramedOutputStream extends OutputStream {
   public static final int MAX_BLOCK_SIZE = 65536;
   public static final int DEFAULT_BLOCK_SIZE = 65536;
   public static final double DEFAULT_MIN_COMPRESSION_RATIO = 0.85;
   private final SnappyCompressor compressor;
   private final int blockSize;
   private final byte[] buffer;
   private final byte[] outputBuffer;
   private final double minCompressionRatio;
   private final OutputStream out;
   private final boolean writeChecksums;
   private int position;
   private boolean closed;

   public SnappyFramedOutputStream(OutputStream out) throws IOException {
      this(out, true);
   }

   public static SnappyFramedOutputStream newChecksumFreeBenchmarkOutputStream(OutputStream out) throws IOException {
      return new SnappyFramedOutputStream(out, false);
   }

   private SnappyFramedOutputStream(OutputStream out, boolean writeChecksums) throws IOException {
      this(out, writeChecksums, 65536, 0.85);
   }

   public SnappyFramedOutputStream(OutputStream out, boolean writeChecksums, int blockSize, double minCompressionRatio) throws IOException {
      this.compressor = new SnappyCompressor();
      this.out = (OutputStream)SnappyInternalUtils.checkNotNull(out, "out is null");
      this.writeChecksums = writeChecksums;
      SnappyInternalUtils.checkArgument(minCompressionRatio > (double)0.0F && minCompressionRatio <= (double)1.0F, "minCompressionRatio %1s must be between (0,1.0].", minCompressionRatio);
      this.minCompressionRatio = minCompressionRatio;
      this.blockSize = blockSize;
      this.buffer = new byte[blockSize];
      this.outputBuffer = new byte[this.compressor.maxCompressedLength(blockSize)];
      out.write(SnappyFramed.HEADER_BYTES);
      SnappyInternalUtils.checkArgument(blockSize > 0 && blockSize <= 65536, "blockSize must be in (0, 65536]", blockSize);
   }

   public void write(int b) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         if (this.position >= this.blockSize) {
            this.flushBuffer();
         }

         this.buffer[this.position++] = (byte)b;
      }
   }

   public void write(byte[] input, int offset, int length) throws IOException {
      SnappyInternalUtils.checkNotNull(input, "input is null");
      SnappyInternalUtils.checkPositionIndexes(offset, offset + length, input.length);
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         int free = this.blockSize - this.position;
         if (free >= length) {
            this.copyToBuffer(input, offset, length);
         } else {
            if (this.position > 0) {
               this.copyToBuffer(input, offset, free);
               this.flushBuffer();
               offset += free;
               length -= free;
            }

            while(length >= this.blockSize) {
               this.writeCompressed(input, offset, this.blockSize);
               offset += this.blockSize;
               length -= this.blockSize;
            }

            this.copyToBuffer(input, offset, length);
         }
      }
   }

   public void flush() throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         this.flushBuffer();
         this.out.flush();
      }
   }

   public void close() throws IOException {
      if (!this.closed) {
         try {
            this.flush();
            this.out.close();
         } finally {
            this.closed = true;
         }

      }
   }

   private void copyToBuffer(byte[] input, int offset, int length) {
      System.arraycopy(input, offset, this.buffer, this.position, length);
      this.position += length;
   }

   private void flushBuffer() throws IOException {
      if (this.position > 0) {
         this.writeCompressed(this.buffer, 0, this.position);
         this.position = 0;
      }

   }

   private void writeCompressed(byte[] input, int offset, int length) throws IOException {
      int crc32c = this.writeChecksums ? Crc32C.maskedCrc32c(input, offset, length) : 0;
      int compressed = this.compressor.compress(input, offset, length, this.outputBuffer, 0, this.outputBuffer.length);
      if ((double)compressed / (double)length <= this.minCompressionRatio) {
         this.writeBlock(this.out, this.outputBuffer, 0, compressed, true, crc32c);
      } else {
         this.writeBlock(this.out, input, offset, length, false, crc32c);
      }

   }

   private void writeBlock(OutputStream out, byte[] data, int offset, int length, boolean compressed, int crc32c) throws IOException {
      out.write(compressed ? 0 : 1);
      int headerLength = length + 4;
      out.write(headerLength);
      out.write(headerLength >>> 8);
      out.write(headerLength >>> 16);
      out.write(crc32c);
      out.write(crc32c >>> 8);
      out.write(crc32c >>> 16);
      out.write(crc32c >>> 24);
      out.write(data, offset, length);
   }
}
