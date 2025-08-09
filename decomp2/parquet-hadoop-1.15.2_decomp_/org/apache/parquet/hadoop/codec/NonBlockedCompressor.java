package org.apache.parquet.hadoop.codec;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.parquet.Preconditions;

public abstract class NonBlockedCompressor implements Compressor {
   private static final int INITIAL_INPUT_BUFFER_SIZE = 4096;
   private static final double INPUT_BUFFER_GROWTH_FACTOR = 1.2;
   private ByteBuffer outputBuffer = ByteBuffer.allocateDirect(0);
   private ByteBuffer inputBuffer = ByteBuffer.allocateDirect(0);
   private long bytesRead = 0L;
   private long bytesWritten = 0L;
   private boolean finishCalled = false;

   public synchronized int compress(byte[] buffer, int off, int len) throws IOException {
      SnappyUtil.validateBuffer(buffer, off, len);
      if (this.needsInput()) {
         return 0;
      } else {
         if (!this.outputBuffer.hasRemaining()) {
            int maxOutputSize = this.maxCompressedLength(this.inputBuffer.position());
            if (maxOutputSize > this.outputBuffer.capacity()) {
               ByteBuffer oldBuffer = this.outputBuffer;
               this.outputBuffer = ByteBuffer.allocateDirect(maxOutputSize);
               CleanUtil.cleanDirectBuffer(oldBuffer);
            }

            this.outputBuffer.clear();
            this.inputBuffer.limit(this.inputBuffer.position());
            this.inputBuffer.position(0);
            int size = this.compress(this.inputBuffer, this.outputBuffer);
            this.outputBuffer.limit(size);
            this.inputBuffer.limit(0);
            this.inputBuffer.rewind();
         }

         int numBytes = Math.min(len, this.outputBuffer.remaining());
         this.outputBuffer.get(buffer, off, numBytes);
         this.bytesWritten += (long)numBytes;
         return numBytes;
      }
   }

   public synchronized void setInput(byte[] buffer, int off, int len) {
      SnappyUtil.validateBuffer(buffer, off, len);
      Preconditions.checkArgument(!this.outputBuffer.hasRemaining(), "Output buffer should be empty. Caller must call compress()");
      if (this.inputBuffer.capacity() - this.inputBuffer.position() < len) {
         int newBufferSize;
         if (this.inputBuffer.capacity() == 0) {
            newBufferSize = Math.max(4096, len);
         } else {
            newBufferSize = Math.max(this.inputBuffer.position() + len, (int)((double)this.inputBuffer.capacity() * 1.2));
         }

         ByteBuffer tmp = ByteBuffer.allocateDirect(newBufferSize);
         tmp.limit(this.inputBuffer.position() + len);
         this.inputBuffer.rewind();
         tmp.put(this.inputBuffer);
         ByteBuffer oldBuffer = this.inputBuffer;
         this.inputBuffer = tmp;
         CleanUtil.cleanDirectBuffer(oldBuffer);
      } else {
         this.inputBuffer.limit(this.inputBuffer.position() + len);
      }

      this.inputBuffer.put(buffer, off, len);
      this.bytesRead += (long)len;
   }

   public void end() {
      CleanUtil.cleanDirectBuffer(this.inputBuffer);
      CleanUtil.cleanDirectBuffer(this.outputBuffer);
   }

   public synchronized void finish() {
      this.finishCalled = true;
   }

   public synchronized boolean finished() {
      return this.finishCalled && this.inputBuffer.position() == 0 && !this.outputBuffer.hasRemaining();
   }

   public long getBytesRead() {
      return this.bytesRead;
   }

   public long getBytesWritten() {
      return this.bytesWritten;
   }

   public synchronized boolean needsInput() {
      return !this.finishCalled;
   }

   public void reinit(Configuration c) {
      this.reset();
   }

   public synchronized void reset() {
      this.finishCalled = false;
      this.bytesRead = this.bytesWritten = 0L;
      this.inputBuffer.rewind();
      this.outputBuffer.rewind();
      this.inputBuffer.limit(0);
      this.outputBuffer.limit(0);
   }

   public void setDictionary(byte[] dictionary, int off, int len) {
   }

   protected abstract int maxCompressedLength(int var1);

   protected abstract int compress(ByteBuffer var1, ByteBuffer var2) throws IOException;
}
