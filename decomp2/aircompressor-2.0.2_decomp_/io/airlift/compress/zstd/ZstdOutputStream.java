package io.airlift.compress.zstd;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;
import sun.misc.Unsafe;

public class ZstdOutputStream extends OutputStream {
   private final OutputStream outputStream;
   private final CompressionContext context;
   private final int maxBufferSize;
   private XxHash64 partialHash;
   private byte[] uncompressed = new byte[0];
   private final byte[] compressed;
   private int uncompressedOffset;
   private int uncompressedPosition;
   private boolean closed;

   public ZstdOutputStream(OutputStream outputStream) throws IOException {
      this.outputStream = (OutputStream)Objects.requireNonNull(outputStream, "outputStream is null");
      this.context = new CompressionContext(CompressionParameters.compute(3, -1), (long)Unsafe.ARRAY_BYTE_BASE_OFFSET, Integer.MAX_VALUE);
      this.maxBufferSize = this.context.parameters.getWindowSize() * 4;
      int bufferSize = this.context.parameters.getBlockSize() + 3;
      this.compressed = new byte[bufferSize + (bufferSize >>> 8) + 8];
   }

   public void write(int b) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         this.growBufferIfNecessary(1);
         this.uncompressed[this.uncompressedPosition++] = (byte)b;
         this.compressIfNecessary();
      }
   }

   public void write(byte[] buffer) throws IOException {
      this.write(buffer, 0, buffer.length);
   }

   public void write(byte[] buffer, int offset, int length) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else {
         this.growBufferIfNecessary(length);

         while(length > 0) {
            int writeSize = Math.min(length, this.uncompressed.length - this.uncompressedPosition);
            System.arraycopy(buffer, offset, this.uncompressed, this.uncompressedPosition, writeSize);
            this.uncompressedPosition += writeSize;
            length -= writeSize;
            offset += writeSize;
            this.compressIfNecessary();
         }

      }
   }

   private void growBufferIfNecessary(int length) {
      if (this.uncompressedPosition + length > this.uncompressed.length && this.uncompressed.length < this.maxBufferSize) {
         int newSize = (this.uncompressed.length + length) * 2;
         newSize = Math.min(newSize, this.maxBufferSize);
         newSize = Math.max(newSize, this.context.parameters.getBlockSize());
         this.uncompressed = Arrays.copyOf(this.uncompressed, newSize);
      }
   }

   private void compressIfNecessary() throws IOException {
      if (this.uncompressed.length >= this.maxBufferSize && this.uncompressedPosition == this.uncompressed.length && this.uncompressed.length - this.context.parameters.getWindowSize() > this.context.parameters.getBlockSize()) {
         this.writeChunk(false);
      }

   }

   void finishWithoutClosingSource() throws IOException {
      if (!this.closed) {
         this.writeChunk(true);
         this.closed = true;
      }

   }

   public void close() throws IOException {
      if (!this.closed) {
         this.writeChunk(true);
         this.closed = true;
         this.outputStream.close();
      }

   }

   private void writeChunk(boolean lastChunk) throws IOException {
      int chunkSize;
      if (lastChunk) {
         chunkSize = this.uncompressedPosition - this.uncompressedOffset;
      } else {
         int blockSize = this.context.parameters.getBlockSize();
         chunkSize = this.uncompressedPosition - this.uncompressedOffset - this.context.parameters.getWindowSize() - blockSize;
         Util.checkState(chunkSize > blockSize, "Must write at least one full block");
         chunkSize = chunkSize / blockSize * blockSize;
      }

      if (this.partialHash == null) {
         this.partialHash = new XxHash64();
         int inputSize = lastChunk ? chunkSize : -1;
         int outputAddress = Unsafe.ARRAY_BYTE_BASE_OFFSET;
         outputAddress += ZstdFrameCompressor.writeMagic(this.compressed, (long)outputAddress, (long)(outputAddress + 4));
         outputAddress += ZstdFrameCompressor.writeFrameHeader(this.compressed, (long)outputAddress, (long)(outputAddress + 14), inputSize, this.context.parameters.getWindowSize());
         this.outputStream.write(this.compressed, 0, outputAddress - Unsafe.ARRAY_BYTE_BASE_OFFSET);
      }

      this.partialHash.update(this.uncompressed, this.uncompressedOffset, chunkSize);

      do {
         int blockSize = Math.min(chunkSize, this.context.parameters.getBlockSize());
         int compressedSize = ZstdFrameCompressor.writeCompressedBlock(this.uncompressed, (long)(Unsafe.ARRAY_BYTE_BASE_OFFSET + this.uncompressedOffset), blockSize, this.compressed, (long)Unsafe.ARRAY_BYTE_BASE_OFFSET, this.compressed.length, this.context, lastChunk && blockSize == chunkSize);
         this.outputStream.write(this.compressed, 0, compressedSize);
         this.uncompressedOffset += blockSize;
         chunkSize -= blockSize;
      } while(chunkSize > 0);

      if (lastChunk) {
         int hash = (int)this.partialHash.hash();
         this.outputStream.write(hash);
         this.outputStream.write(hash >> 8);
         this.outputStream.write(hash >> 16);
         this.outputStream.write(hash >> 24);
      } else {
         int slideWindowSize = this.uncompressedOffset - this.context.parameters.getWindowSize();
         this.context.slideWindow(slideWindowSize);
         System.arraycopy(this.uncompressed, slideWindowSize, this.uncompressed, 0, this.context.parameters.getWindowSize() + (this.uncompressedPosition - this.uncompressedOffset));
         this.uncompressedOffset -= slideWindowSize;
         this.uncompressedPosition -= slideWindowSize;
      }

   }
}
