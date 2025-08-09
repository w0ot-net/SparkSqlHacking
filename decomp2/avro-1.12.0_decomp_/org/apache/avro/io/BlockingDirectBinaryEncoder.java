package org.apache.avro.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class BlockingDirectBinaryEncoder extends DirectBinaryEncoder {
   private final ArrayList buffers = new ArrayList();
   private final ArrayDeque stashedBuffers = new ArrayDeque();
   private int depth = 0;
   private final ArrayDeque blockItemCounts = new ArrayDeque();

   public BlockingDirectBinaryEncoder(OutputStream out) {
      super(out);
   }

   private void startBlock() {
      this.stashedBuffers.push(this.out);
      if (this.buffers.size() <= this.depth) {
         this.buffers.add(new BufferOutputStream());
      }

      BufferOutputStream buf = (BufferOutputStream)this.buffers.get(this.depth);
      buf.reset();
      ++this.depth;
      this.out = buf;
   }

   private void endBlock() {
      if (this.depth == 0) {
         throw new RuntimeException("Called endBlock, while not buffering a block");
      } else {
         --this.depth;
         this.out = (OutputStream)this.stashedBuffers.pop();
         BufferOutputStream buffer = (BufferOutputStream)this.buffers.get(this.depth);
         long blockItemCount = (Long)this.blockItemCounts.pop();
         if (blockItemCount > 0L) {
            try {
               this.writeLong(-blockItemCount);
               this.writeLong((long)buffer.size());
               this.writeFixed(buffer.toBufferWithoutCopy());
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
         }

      }
   }

   public void setItemCount(long itemCount) throws IOException {
      this.blockItemCounts.push(itemCount);
   }

   public void writeArrayStart() throws IOException {
      this.startBlock();
   }

   public void writeArrayEnd() throws IOException {
      this.endBlock();
      super.writeArrayEnd();
   }

   public void writeMapStart() throws IOException {
      this.startBlock();
   }

   public void writeMapEnd() throws IOException {
      this.endBlock();
      super.writeMapEnd();
   }

   private static class BufferOutputStream extends ByteArrayOutputStream {
      BufferOutputStream() {
      }

      ByteBuffer toBufferWithoutCopy() {
         return ByteBuffer.wrap(this.buf, 0, this.count);
      }
   }
}
