package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.InputStream;

class FourBitPackingReader extends BitPacking.BitPackingReader {
   private final InputStream in;
   private int buffer = 0;
   private int count = 0;

   public FourBitPackingReader(InputStream in) {
      this.in = in;
   }

   public int read() throws IOException {
      if (this.count == 0) {
         this.buffer = this.in.read();
         this.count = 2;
      }

      int result = this.buffer >> (this.count - 1) * 4 & 15;
      --this.count;
      return result;
   }
}
