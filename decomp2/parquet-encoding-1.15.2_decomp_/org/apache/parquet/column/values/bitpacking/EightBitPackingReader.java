package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.InputStream;

class EightBitPackingReader extends BitPacking.BitPackingReader {
   private final InputStream in;

   public EightBitPackingReader(InputStream in) {
      this.in = in;
   }

   public int read() throws IOException {
      return this.in.read();
   }
}
