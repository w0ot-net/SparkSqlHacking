package org.apache.parquet.hadoop.codec;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.hadoop.io.compress.Decompressor;
import org.apache.parquet.Preconditions;

public abstract class NonBlockedDecompressor implements Decompressor {
   private static final int INITIAL_INPUT_BUFFER_SIZE = 4096;
   private static final double INPUT_BUFFER_GROWTH_FACTOR = 1.2;
   private ByteBuffer outputBuffer = ByteBuffer.allocateDirect(0);
   private ByteBuffer inputBuffer = ByteBuffer.allocateDirect(0);
   private boolean finished;

   public synchronized int decompress(byte[] buffer, int off, int len) throws IOException {
      SnappyUtil.validateBuffer(buffer, off, len);
      if (this.inputBuffer.position() == 0 && !this.outputBuffer.hasRemaining()) {
         return 0;
      } else {
         if (!this.outputBuffer.hasRemaining()) {
            this.inputBuffer.rewind();
            Preconditions.checkArgument(this.inputBuffer.position() == 0, "Invalid position of 0.");
            Preconditions.checkArgument(this.outputBuffer.position() == 0, "Invalid position of 0.");
            int decompressedSize = this.maxUncompressedLength(this.inputBuffer, len);
            if (decompressedSize > this.outputBuffer.capacity()) {
               ByteBuffer oldBuffer = this.outputBuffer;
               this.outputBuffer = ByteBuffer.allocateDirect(decompressedSize);
               CleanUtil.cleanDirectBuffer(oldBuffer);
            }

            this.outputBuffer.clear();
            int size = this.uncompress(this.inputBuffer, this.outputBuffer);
            this.outputBuffer.limit(size);
            this.inputBuffer.clear();
            this.inputBuffer.limit(0);
            this.finished = true;
         }

         int numBytes = Math.min(len, this.outputBuffer.remaining());
         this.outputBuffer.get(buffer, off, numBytes);
         return numBytes;
      }
   }

   public synchronized void setInput(byte[] buffer, int off, int len) {
      SnappyUtil.validateBuffer(buffer, off, len);
      if (this.inputBuffer.capacity() - this.inputBuffer.position() < len) {
         int newBufferSize;
         if (this.inputBuffer.capacity() == 0) {
            newBufferSize = Math.max(4096, len);
         } else {
            newBufferSize = Math.max(this.inputBuffer.position() + len, (int)((double)this.inputBuffer.capacity() * 1.2));
         }

         ByteBuffer newBuffer = ByteBuffer.allocateDirect(newBufferSize);
         newBuffer.limit(this.inputBuffer.position() + len);
         this.inputBuffer.rewind();
         newBuffer.put(this.inputBuffer);
         ByteBuffer oldBuffer = this.inputBuffer;
         this.inputBuffer = newBuffer;
         CleanUtil.cleanDirectBuffer(oldBuffer);
      } else {
         this.inputBuffer.limit(this.inputBuffer.position() + len);
      }

      this.inputBuffer.put(buffer, off, len);
   }

   public void end() {
      CleanUtil.cleanDirectBuffer(this.inputBuffer);
      CleanUtil.cleanDirectBuffer(this.outputBuffer);
   }

   public synchronized boolean finished() {
      return this.finished && !this.outputBuffer.hasRemaining();
   }

   public int getRemaining() {
      return 0;
   }

   public synchronized boolean needsInput() {
      return !this.inputBuffer.hasRemaining() && !this.outputBuffer.hasRemaining();
   }

   public synchronized void reset() {
      this.finished = false;
      this.inputBuffer.rewind();
      this.outputBuffer.rewind();
      this.inputBuffer.limit(0);
      this.outputBuffer.limit(0);
   }

   public boolean needsDictionary() {
      return false;
   }

   public void setDictionary(byte[] b, int off, int len) {
   }

   protected abstract int maxUncompressedLength(ByteBuffer var1, int var2) throws IOException;

   protected abstract int uncompress(ByteBuffer var1, ByteBuffer var2) throws IOException;
}
