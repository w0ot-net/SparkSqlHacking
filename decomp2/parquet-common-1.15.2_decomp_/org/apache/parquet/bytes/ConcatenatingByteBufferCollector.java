package org.apache.parquet.bytes;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class ConcatenatingByteBufferCollector extends BytesInput implements AutoCloseable {
   private final ByteBufferAllocator allocator;
   private final List slabs = new ArrayList();
   private long size = 0L;

   public ConcatenatingByteBufferCollector(ByteBufferAllocator allocator) {
      this.allocator = allocator;
   }

   public void collect(BytesInput bytesInput) {
      int inputSize = Math.toIntExact(bytesInput.size());
      ByteBuffer slab = this.allocator.allocate(inputSize);
      bytesInput.writeInto(slab);
      slab.flip();
      this.slabs.add(slab);
      this.size += (long)inputSize;
   }

   public void close() {
      for(ByteBuffer slab : this.slabs) {
         this.allocator.release(slab);
      }

      this.slabs.clear();
   }

   public void writeAllTo(OutputStream out) throws IOException {
      WritableByteChannel channel = Channels.newChannel(out);

      for(ByteBuffer buffer : this.slabs) {
         channel.write(buffer.duplicate());
      }

   }

   public void writeInto(ByteBuffer buffer) {
      for(ByteBuffer slab : this.slabs) {
         buffer.put(slab.duplicate());
      }

   }

   ByteBuffer getInternalByteBuffer() {
      return this.slabs.size() == 1 ? ((ByteBuffer)this.slabs.get(0)).duplicate() : null;
   }

   public long size() {
      return this.size;
   }

   public String memUsageString(String prefix) {
      return String.format("%s %s %d slabs, %,d bytes", prefix, this.getClass().getSimpleName(), this.slabs.size(), this.size);
   }
}
