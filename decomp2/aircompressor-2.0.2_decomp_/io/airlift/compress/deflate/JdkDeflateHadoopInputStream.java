package io.airlift.compress.deflate;

import io.airlift.compress.hadoop.HadoopInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

class JdkDeflateHadoopInputStream extends HadoopInputStream {
   private final byte[] oneByte = new byte[1];
   private final InputStream input;
   private final Inflater inflater;
   private final byte[] inputBuffer;
   private int inputBufferEnd;
   private boolean closed;

   public JdkDeflateHadoopInputStream(InputStream input, int bufferSize) {
      this.input = (InputStream)Objects.requireNonNull(input, "input is null");
      this.inflater = new Inflater();
      this.inputBuffer = new byte[bufferSize];
   }

   public int read() throws IOException {
      int length = this.read(this.oneByte, 0, 1);
      return length < 0 ? length : this.oneByte[0] & 255;
   }

   public int read(byte[] output, int offset, int length) throws IOException {
      if (this.closed) {
         throw new IOException("Closed");
      } else {
         while(true) {
            try {
               int outputSize = this.inflater.inflate(output, offset, length);
               if (outputSize > 0) {
                  return outputSize;
               }
            } catch (DataFormatException e) {
               throw new IOException(e);
            }

            if (this.inflater.needsDictionary()) {
               return -1;
            }

            if (this.inflater.finished()) {
               int remainingBytes = this.inflater.getRemaining();
               if (remainingBytes > 0) {
                  this.inflater.reset();
                  this.inflater.setInput(this.inputBuffer, this.inputBufferEnd - remainingBytes, remainingBytes);
               } else {
                  int bufferedBytes = this.input.read(this.inputBuffer, 0, this.inputBuffer.length);
                  if (bufferedBytes < 0) {
                     return -1;
                  }

                  this.inflater.reset();
                  this.inflater.setInput(this.inputBuffer, 0, bufferedBytes);
                  this.inputBufferEnd = bufferedBytes;
               }
            } else if (this.inflater.needsInput()) {
               int bufferedBytes = this.input.read(this.inputBuffer, 0, this.inputBuffer.length);
               if (bufferedBytes < 0) {
                  throw new EOFException("Unexpected end of input stream");
               }

               this.inflater.setInput(this.inputBuffer, 0, bufferedBytes);
               this.inputBufferEnd = bufferedBytes;
            }
         }
      }
   }

   public void resetState() {
      this.inflater.reset();
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.closed = true;
         this.inflater.end();
         this.input.close();
      }

   }
}
