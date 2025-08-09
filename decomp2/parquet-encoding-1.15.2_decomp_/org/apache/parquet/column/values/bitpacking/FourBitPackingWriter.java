package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.OutputStream;

class FourBitPackingWriter extends BitPacking.BitPackingWriter {
   private OutputStream out;
   private int buffer = 0;
   private int count = 0;

   public FourBitPackingWriter(OutputStream out) {
      this.out = out;
   }

   public void write(int val) throws IOException {
      this.buffer <<= 4;
      this.buffer |= val;
      ++this.count;
      if (this.count == 2) {
         this.out.write(this.buffer);
         this.buffer = 0;
         this.count = 0;
      }

   }

   public void finish() throws IOException {
      while(this.count != 0) {
         this.write(0);
      }

      this.out = null;
   }
}
