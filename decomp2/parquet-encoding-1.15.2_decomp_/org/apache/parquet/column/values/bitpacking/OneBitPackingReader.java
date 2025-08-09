package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.InputStream;

class OneBitPackingReader extends BitPacking.BitPackingReader {
   private final InputStream in;
   private int buffer = 0;
   private int count = 0;

   public OneBitPackingReader(InputStream in) {
      this.in = in;
   }

   public int read() throws IOException {
      if (this.count == 0) {
         this.buffer = this.in.read();
         this.count = 8;
      }

      int result = this.buffer >> this.count - 1 & 1;
      --this.count;
      return result;
   }
}
