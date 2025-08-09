package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteBitPackingValuesReader extends ValuesReader {
   private static final int VALUES_AT_A_TIME = 8;
   private static final Logger LOG = LoggerFactory.getLogger(ByteBitPackingValuesReader.class);
   private final int bitWidth;
   private final BytePacker packer;
   private final int[] decoded = new int[8];
   private int decodedPosition = 7;
   private ByteBufferInputStream in;
   private final byte[] tempEncode;

   public ByteBitPackingValuesReader(int bound, Packer packer) {
      this.bitWidth = BytesUtils.getWidthFromMaxInt(bound);
      this.packer = packer.newBytePacker(this.bitWidth);
      this.tempEncode = new byte[this.bitWidth];
   }

   private void readMore() {
      try {
         int avail = this.in.available();
         if (avail < this.bitWidth) {
            this.in.read(this.tempEncode, 0, avail);

            for(int i = avail; i < this.bitWidth; ++i) {
               this.tempEncode[i] = 0;
            }
         } else {
            this.in.read(this.tempEncode, 0, this.bitWidth);
         }

         this.packer.unpack8Values(this.tempEncode, 0, this.decoded, 0);
      } catch (IOException e) {
         throw new ParquetDecodingException("Failed to read packed values", e);
      }

      this.decodedPosition = 0;
   }

   public int readInteger() {
      ++this.decodedPosition;
      if (this.decodedPosition == this.decoded.length) {
         this.readMore();
      }

      return this.decoded[this.decodedPosition];
   }

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      int effectiveBitLength = valueCount * this.bitWidth;
      int length = BytesUtils.paddedByteCountFromBits(effectiveBitLength);
      LOG.debug("reading {} bytes for {} values of size {} bits.", new Object[]{length, valueCount, this.bitWidth});
      length = Math.min(length, stream.available());
      this.in = stream.sliceStream((long)length);
      this.decodedPosition = 7;
      this.updateNextOffset(length);
   }

   public void skip() {
      this.readInteger();
   }
}
