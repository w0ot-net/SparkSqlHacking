package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.OutputStream;

class EightBitPackingWriter extends BitPacking.BitPackingWriter {
   private OutputStream out;

   public EightBitPackingWriter(OutputStream out) {
      this.out = out;
   }

   public void write(int val) throws IOException {
      this.out.write(val);
   }

   public void finish() throws IOException {
      this.out = null;
   }
}
