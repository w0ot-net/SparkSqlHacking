package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.InputStream;

class FiveBitPackingReader extends BaseBitPackingReader {
   private final InputStream in;
   private final long valueCount;
   private long buffer = 0L;
   private int count = 0;
   private long totalRead = 0L;

   public FiveBitPackingReader(InputStream in, long valueCount) {
      this.in = in;
      this.valueCount = valueCount;
   }

   public int read() throws IOException {
      if (this.count == 0) {
         if (this.valueCount - this.totalRead < 8L) {
            this.buffer = 0L;
            int bitsToRead = 5 * (int)(this.valueCount - this.totalRead);
            int bytesToRead = this.alignToBytes(bitsToRead);

            for(int i = 4; i >= 5 - bytesToRead; --i) {
               this.buffer |= ((long)this.in.read() & 255L) << i * 8;
            }

            this.count = 8;
            this.totalRead = this.valueCount;
         } else {
            this.buffer = (((long)this.in.read() & 255L) << 32) + (((long)this.in.read() & 255L) << 24) + (long)(this.in.read() << 16) + (long)(this.in.read() << 8) + (long)this.in.read();
            this.count = 8;
            this.totalRead += 8L;
         }
      }

      int result = (int)(this.buffer >> (this.count - 1) * 5) & 31;
      --this.count;
      return result;
   }
}
