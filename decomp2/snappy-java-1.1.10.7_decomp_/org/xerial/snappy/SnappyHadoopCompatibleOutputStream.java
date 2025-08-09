package org.xerial.snappy;

import java.io.OutputStream;
import org.xerial.snappy.buffer.CachedBufferAllocator;

public class SnappyHadoopCompatibleOutputStream extends SnappyOutputStream {
   public SnappyHadoopCompatibleOutputStream(OutputStream var1) {
      this(var1, 32768);
   }

   public SnappyHadoopCompatibleOutputStream(OutputStream var1, int var2) {
      super(var1, var2, CachedBufferAllocator.getBufferAllocatorFactory());
   }

   protected int writeHeader() {
      return 0;
   }

   protected void writeBlockPreemble() {
      this.writeCurrentDataSize();
   }
}
