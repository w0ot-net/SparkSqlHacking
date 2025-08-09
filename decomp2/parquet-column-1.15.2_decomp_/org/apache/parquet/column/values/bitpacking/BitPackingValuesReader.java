package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitPackingValuesReader extends ValuesReader {
   private static final Logger LOG = LoggerFactory.getLogger(BitPackingValuesReader.class);
   private ByteBufferInputStream in;
   private BitPacking.BitPackingReader bitPackingReader;
   private final int bitsPerValue;

   public BitPackingValuesReader(int bound) {
      this.bitsPerValue = BytesUtils.getWidthFromMaxInt(bound);
   }

   public int readInteger() {
      try {
         return this.bitPackingReader.read();
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      int effectiveBitLength = valueCount * this.bitsPerValue;
      int length = BytesUtils.paddedByteCountFromBits(effectiveBitLength);
      LOG.debug("reading {} bytes for {} values of size {} bits.", new Object[]{length, valueCount, this.bitsPerValue});
      this.in = stream.sliceStream((long)length);
      this.bitPackingReader = BitPacking.createBitPackingReader(this.bitsPerValue, this.in, (long)valueCount);
      this.updateNextOffset(length);
   }

   public void skip() {
      this.readInteger();
   }
}
