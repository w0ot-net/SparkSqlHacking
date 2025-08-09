package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.OutputStream;

class SevenBitPackingWriter extends BaseBitPackingWriter {
   private OutputStream out;
   private long buffer = 0L;
   private int count = 0;

   public SevenBitPackingWriter(OutputStream out) {
      this.out = out;
   }

   public void write(int val) throws IOException {
      this.buffer <<= 7;
      this.buffer |= (long)val;
      ++this.count;
      if (this.count == 8) {
         this.out.write((int)(this.buffer >>> 48) & 255);
         this.out.write((int)(this.buffer >>> 40) & 255);
         this.out.write((int)(this.buffer >>> 32) & 255);
         this.out.write((int)(this.buffer >>> 24) & 255);
         this.out.write((int)(this.buffer >>> 16) & 255);
         this.out.write((int)(this.buffer >>> 8) & 255);
         this.out.write((int)(this.buffer >>> 0) & 255);
         this.buffer = 0L;
         this.count = 0;
      }

   }

   public void finish() throws IOException {
      if (this.count != 0) {
         int numberOfBits = this.count * 7;
         this.finish(numberOfBits, this.buffer, this.out);
         this.buffer = 0L;
         this.count = 0;
      }

      this.out = null;
   }
}
