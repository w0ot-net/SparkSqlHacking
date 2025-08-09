package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.OutputStream;

class SixBitPackingWriter extends BaseBitPackingWriter {
   private OutputStream out;
   private int buffer = 0;
   private int count = 0;

   public SixBitPackingWriter(OutputStream out) {
      this.out = out;
   }

   public void write(int val) throws IOException {
      this.buffer <<= 6;
      this.buffer |= val;
      ++this.count;
      if (this.count == 4) {
         this.out.write(this.buffer >>> 16 & 255);
         this.out.write(this.buffer >>> 8 & 255);
         this.out.write(this.buffer >>> 0 & 255);
         this.buffer = 0;
         this.count = 0;
      }

   }

   public void finish() throws IOException {
      if (this.count != 0) {
         int numberOfBits = this.count * 6;
         this.finish(numberOfBits, this.buffer, this.out);
         this.buffer = 0;
         this.count = 0;
      }

      this.out = null;
   }
}
