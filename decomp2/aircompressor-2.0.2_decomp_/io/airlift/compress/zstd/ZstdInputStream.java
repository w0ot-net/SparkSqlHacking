package io.airlift.compress.zstd;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import sun.misc.Unsafe;

public class ZstdInputStream extends InputStream {
   private static final int MIN_BUFFER_SIZE = 4096;
   private final InputStream inputStream;
   private final ZstdIncrementalFrameDecompressor decompressor = new ZstdIncrementalFrameDecompressor();
   private byte[] inputBuffer;
   private int inputBufferOffset;
   private int inputBufferLimit;
   private byte[] singleByteOutputBuffer;
   private boolean closed;

   public ZstdInputStream(InputStream inputStream) {
      this.inputBuffer = new byte[this.decompressor.getInputRequired()];
      this.inputStream = (InputStream)Objects.requireNonNull(inputStream, "inputStream is null");
   }

   public int read() throws IOException {
      if (this.singleByteOutputBuffer == null) {
         this.singleByteOutputBuffer = new byte[1];
      }

      int readSize = this.read(this.singleByteOutputBuffer, 0, 1);
      Util.checkState(readSize != 0, "A zero read size should never be returned");
      return readSize != 1 ? -1 : this.singleByteOutputBuffer[0] & 255;
   }

   public int read(final byte[] outputBuffer, final int outputOffset, final int outputLength) throws IOException {
      if (this.closed) {
         throw new IOException("Stream is closed");
      } else if (outputBuffer == null) {
         throw new NullPointerException();
      } else {
         Util.checkPositionIndexes(outputOffset, outputOffset + outputLength, outputBuffer.length);
         if (outputLength == 0) {
            return 0;
         } else {
            int outputLimit = outputOffset + outputLength;

            int outputUsed;
            for(outputUsed = 0; outputUsed < outputLength; outputUsed += this.decompressor.getOutputBufferUsed()) {
               boolean enoughInput = this.fillInputBufferIfNecessary(this.decompressor.getInputRequired());
               if (!enoughInput) {
                  if (this.decompressor.isAtStoppingPoint()) {
                     return outputUsed > 0 ? outputUsed : -1;
                  }

                  throw new IOException("Not enough input bytes");
               }

               this.decompressor.partialDecompress(this.inputBuffer, (long)(this.inputBufferOffset + Unsafe.ARRAY_BYTE_BASE_OFFSET), (long)(this.inputBufferLimit + Unsafe.ARRAY_BYTE_BASE_OFFSET), outputBuffer, outputOffset + outputUsed, outputLimit);
               this.inputBufferOffset += this.decompressor.getInputConsumed();
            }

            return outputUsed;
         }
      }
   }

   private boolean fillInputBufferIfNecessary(int requiredSize) throws IOException {
      if (this.inputBufferLimit - this.inputBufferOffset >= requiredSize) {
         return true;
      } else {
         if (this.inputBufferOffset > 0) {
            int copySize = this.inputBufferLimit - this.inputBufferOffset;
            System.arraycopy(this.inputBuffer, this.inputBufferOffset, this.inputBuffer, 0, copySize);
            this.inputBufferOffset = 0;
            this.inputBufferLimit = copySize;
         }

         if (this.inputBuffer.length < requiredSize) {
            this.inputBuffer = Arrays.copyOf(this.inputBuffer, Math.max(requiredSize, 4096));
         }

         while(this.inputBufferLimit < this.inputBuffer.length) {
            int readSize = this.inputStream.read(this.inputBuffer, this.inputBufferLimit, this.inputBuffer.length - this.inputBufferLimit);
            if (readSize < 0) {
               break;
            }

            this.inputBufferLimit += readSize;
         }

         return this.inputBufferLimit >= requiredSize;
      }
   }

   public int available() throws IOException {
      return this.closed ? 0 : this.decompressor.getRequestedOutputSize();
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.closed = true;
         this.inputStream.close();
      }

   }
}
