package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.InputStream;

class TwoBitPackingReader extends BitPacking.BitPackingReader {
   private final InputStream in;
   private int buffer = 0;
   private int count = 0;

   public TwoBitPackingReader(InputStream in) {
      this.in = in;
   }

   public int read() throws IOException {
      if (this.count == 0) {
         this.buffer = this.in.read();
         this.count = 4;
      }

      int result = this.buffer >> (this.count - 1) * 2 & 3;
      --this.count;
      return result;
   }
}
