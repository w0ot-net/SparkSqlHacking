package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.io.OutputStream;

abstract class BaseBitPackingWriter extends BitPacking.BitPackingWriter {
   void finish(int numberOfBits, int buffer, OutputStream out) throws IOException {
      int padding = numberOfBits % 8 == 0 ? 0 : 8 - numberOfBits % 8;
      buffer <<= padding;
      int numberOfBytes = (numberOfBits + padding) / 8;

      for(int i = (numberOfBytes - 1) * 8; i >= 0; i -= 8) {
         out.write(buffer >>> i & 255);
      }

   }

   void finish(int numberOfBits, long buffer, OutputStream out) throws IOException {
      int padding = numberOfBits % 8 == 0 ? 0 : 8 - numberOfBits % 8;
      buffer <<= padding;
      int numberOfBytes = (numberOfBits + padding) / 8;

      for(int i = (numberOfBytes - 1) * 8; i >= 0; i -= 8) {
         out.write((int)(buffer >>> i) & 255);
      }

   }
}
