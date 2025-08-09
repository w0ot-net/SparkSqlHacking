package org.apache.parquet.bytes;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/** @deprecated */
@Deprecated
public class ConcatenatingByteArrayCollector extends BytesInput {
   private final List slabs = new ArrayList();
   private long size = 0L;

   public void collect(BytesInput bytesInput) throws IOException {
      byte[] bytes = bytesInput.toByteArray();
      this.slabs.add(bytes);
      this.size += (long)bytes.length;
   }

   public void reset() {
      this.size = 0L;
      this.slabs.clear();
   }

   public void writeAllTo(OutputStream out) throws IOException {
      for(byte[] slab : this.slabs) {
         out.write(slab);
      }

   }

   public void writeInto(ByteBuffer buffer) {
      for(byte[] slab : this.slabs) {
         buffer.put(slab);
      }

   }

   public long size() {
      return this.size;
   }

   public String memUsageString(String prefix) {
      return String.format("%s %s %d slabs, %,d bytes", prefix, this.getClass().getSimpleName(), this.slabs.size(), this.size);
   }
}
