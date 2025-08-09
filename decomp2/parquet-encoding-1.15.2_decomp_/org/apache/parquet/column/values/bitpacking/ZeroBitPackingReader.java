package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;

class ZeroBitPackingReader extends BitPacking.BitPackingReader {
   public int read() throws IOException {
      return 0;
   }
}
