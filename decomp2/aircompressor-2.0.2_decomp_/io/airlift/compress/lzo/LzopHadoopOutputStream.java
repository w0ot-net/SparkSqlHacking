package io.airlift.compress.lzo;

import io.airlift.compress.hadoop.HadoopOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.Adler32;

class LzopHadoopOutputStream extends HadoopOutputStream {
   private static final int LZOP_FILE_VERSION = 4112;
   private static final int LZOP_FORMAT_VERSION = 2368;
   private static final int LZO_FORMAT_VERSION = 8272;
   private static final int LEVEL = 5;
   private final LzoCompressor compressor = new LzoCompressor();
   private final OutputStream out;
   private final byte[] inputBuffer;
   private final int inputMaxSize;
   private int inputOffset;
   private final byte[] outputBuffer;

   public LzopHadoopOutputStream(OutputStream out, int bufferSize) throws IOException {
      this.out = (OutputStream)Objects.requireNonNull(out, "out is null");
      this.inputBuffer = new byte[bufferSize];
      this.inputMaxSize = this.inputBuffer.length - compressionOverhead(bufferSize);
      this.outputBuffer = new byte[this.compressor.maxCompressedLength(this.inputMaxSize) + 8];
      out.write(LzoConstants.LZOP_MAGIC);
      ByteArrayOutputStream headerOut = new ByteArrayOutputStream(25);
      DataOutputStream headerDataOut = new DataOutputStream(headerOut);
      headerDataOut.writeShort(4112);
      headerDataOut.writeShort(8272);
      headerDataOut.writeShort(2368);
      headerDataOut.writeByte(1);
      headerDataOut.writeByte(5);
      headerDataOut.writeInt(0);
      headerDataOut.writeInt(33188);
      headerDataOut.writeInt((int)(System.currentTimeMillis() / 1000L));
      headerDataOut.writeInt(0);
      headerDataOut.writeByte(0);
      byte[] header = headerOut.toByteArray();
      out.write(header);
      Adler32 headerChecksum = new Adler32();
      headerChecksum.update(header);
      this.writeBigEndianInt((int)headerChecksum.getValue());
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
         this.writeBigEndianInt(0);
      } finally {
         this.out.close();
      }

   }

   private void writeNextChunk(byte[] input, int inputOffset, int inputLength) throws IOException {
      int compressedSize = this.compressor.compress(input, inputOffset, inputLength, this.outputBuffer, 0, this.outputBuffer.length);
      this.writeBigEndianInt(inputLength);
      if (compressedSize < inputLength) {
         this.writeBigEndianInt(compressedSize);
         this.out.write(this.outputBuffer, 0, compressedSize);
      } else {
         this.writeBigEndianInt(inputLength);
         this.out.write(input, inputOffset, inputLength);
      }

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
