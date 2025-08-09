package org.apache.parquet.bytes;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ByteBufferReleaser implements AutoCloseable {
   final ByteBufferAllocator allocator;
   private final List toRelease = new ArrayList();

   public ByteBufferReleaser(ByteBufferAllocator allocator) {
      this.allocator = allocator;
   }

   public void releaseLater(ByteBuffer buffer) {
      this.toRelease.add(buffer);
   }

   public void close() {
      for(ByteBuffer buf : this.toRelease) {
         this.allocator.release(buf);
      }

      this.toRelease.clear();
   }
}
