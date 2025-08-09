package io.airlift.compress.lzo;

import io.airlift.compress.hadoop.HadoopOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

class LzoHadoopOutputStream extends HadoopOutputStream {
   private final LzoCompressor compressor = new LzoCompressor();
   private final OutputStream out;
   private final byte[] inputBuffer;
   private final int inputMaxSize;
   private int inputOffset;
   private final byte[] outputBuffer;

   public LzoHadoopOutputStream(OutputStream out, int bufferSize) {
      this.out = (OutputStream)Objects.requireNonNull(out, "out is null");
      this.inputBuffer = new byte[bufferSize];
      this.inputMaxSize = this.inputBuffer.length - compressionOverhead(bufferSize);
      this.outputBuffer = new byte[this.compressor.maxCompressedLength(this.inputMaxSize) + 8];
   }

   public void write(int b) throws IOException {
      this.inputBuffer[this.inputOffset++] = (byte)b;
      if (this.inputOffset >= this.inputMaxSize) {
         this.writeNextChunk(this.inputBuffer, 0, this.inputOffset);
      }

   }

   public void write(byte[] buffer, int offset, int length) throws IOException {
      while(length > 0) {
         int chunkSize = Math.min(length, this.inputMaxSize - this.inputOffset);
         if (this.inputOffset == 0 && length > this.inputMaxSize) {
            this.writeNextChunk(buffer, offset, chunkSize);
         } else {
            System.arraycopy(buffer, offset, this.inputBuffer, this.inputOffset, chunkSize);
            this.inputOffset += chunkSize;
            if (this.inputOffset >= this.inputMaxSize) {
               this.writeNextChunk(this.inputBuffer, 0, this.inputOffset);
            }
         }

         length -= chunkSize;
         offset += chunkSize;
      }

   }

   public void finish() throws IOException {
      if (this.inputOffset > 0) {
         this.writeNextChunk(this.inputBuffer, 0, this.inputOffset);
      }

   }

   public void flush() throws IOException {
      this.out.flush();
   }

   public void close() throws IOException {
      try {
         this.finish();
      } finally {
         this.out.close();
      }

   }

   private void writeNextChunk(byte[] input, int inputOffset, int inputLength) throws IOException {
      int compressedSize = this.compressor.compress(input, inputOffset, inputLength, this.outputBuffer, 0, this.outputBuffer.length);
      this.writeBigEndianInt(inputLength);
      this.writeBigEndianInt(compressedSize);
      this.out.write(this.outputBuffer, 0, compressedSize);
      this.inputOffset = 0;
   }

   private void writeBigEndianInt(int value) throws IOException {
      this.out.write(value >>> 24);
      this.out.write(value >>> 16);
      this.out.write(value >>> 8);
      this.out.write(value);
   }

   private static int compressionOverhead(int size) {
      return size / 16 + 64 + 3;
   }
}
