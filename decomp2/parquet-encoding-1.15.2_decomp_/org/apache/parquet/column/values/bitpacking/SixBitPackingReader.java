package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.InputStream;

class SixBitPackingReader extends BaseBitPackingReader {
   private final InputStream in;
   private final long valueCount;
   private int buffer = 0;
   private int count = 0;
   private long totalRead = 0L;

   public SixBitPackingReader(InputStream in, long valueCount) {
      this.in = in;
      this.valueCount = valueCount;
   }

   public int read() throws IOException {
      if (this.count == 0) {
         if (this.valueCount - this.totalRead < 4L) {
            this.buffer = 0;
            int bitsToRead = 6 * (int)(this.valueCount - this.totalRead);
            int bytesToRead = this.alignToBytes(bitsToRead);

            for(int i = 2; i >= 3 - bytesToRead; --i) {
               this.buffer |= this.in.read() << i * 8;
            }

            this.count = 4;
            this.totalRead = this.valueCount;
         } else {
            this.buffer = (this.in.read() << 16) + (this.in.read() << 8) + this.in.read();
            this.count = 4;
            this.totalRead += 4L;
         }
      }

      int result = this.buffer >> (this.count - 1) * 6 & 63;
      --this.count;
      return result;
   }
}
