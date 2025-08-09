package org.apache.parquet.column.values.bitpacking;

import org.apache.parquet.bytes.BytesUtils;

abstract class BaseBitPackingReader extends BitPacking.BitPackingReader {
   int alignToBytes(int bitsCount) {
      return BytesUtils.paddedByteCountFromBits(bitsCount);
   }
}
