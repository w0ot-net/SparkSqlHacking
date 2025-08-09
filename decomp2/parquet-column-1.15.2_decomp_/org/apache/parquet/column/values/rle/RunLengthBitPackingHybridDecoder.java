package org.apache.parquet.column.values.rle;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.values.bitpacking.BytePacker;
import org.apache.parquet.column.values.bitpacking.Packer;
import org.apache.parquet.io.ParquetDecodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunLengthBitPackingHybridDecoder {
   private static final Logger LOG = LoggerFactory.getLogger(RunLengthBitPackingHybridDecoder.class);
   private final int bitWidth;
   private final BytePacker packer;
   private final InputStream in;
   private MODE mode;
   private int currentCount;
   private int currentValue;
   private int[] currentBuffer;

   public RunLengthBitPackingHybridDecoder(int bitWidth, InputStream in) {
      LOG.debug("decoding bitWidth {}", bitWidth);
      Preconditions.checkArgument(bitWidth >= 0 && bitWidth <= 32, "bitWidth must be >= 0 and <= 32");
      this.bitWidth = bitWidth;
      this.packer = Packer.LITTLE_ENDIAN.newBytePacker(bitWidth);
      this.in = in;
   }

   public int readInt() throws IOException {
      if (this.currentCount == 0) {
         this.readNext();
      }

      --this.currentCount;
      int result;
      switch (this.mode) {
         case RLE:
            result = this.currentValue;
            break;
         case PACKED:
            result = this.currentBuffer[this.currentBuffer.length - 1 - this.currentCount];
            break;
         default:
            throw new ParquetDecodingException("not a valid mode " + this.mode);
      }

      return result;
   }

   private void readNext() throws IOException {
      Preconditions.checkArgument(this.in.available() > 0, "Reading past RLE/BitPacking stream.");
      int header = BytesUtils.readUnsignedVarInt(this.in);
      this.mode = (header & 1) == 0 ? RunLengthBitPackingHybridDecoder.MODE.RLE : RunLengthBitPackingHybridDecoder.MODE.PACKED;
      switch (this.mode) {
         case RLE:
            this.currentCount = header >>> 1;
            LOG.debug("reading {} values RLE", this.currentCount);
            this.currentValue = BytesUtils.readIntLittleEndianPaddedOnBitWidth(this.in, this.bitWidth);
            break;
         case PACKED:
            int numGroups = header >>> 1;
            this.currentCount = numGroups * 8;
            LOG.debug("reading {} values BIT PACKED", this.currentCount);
            this.currentBuffer = new int[this.currentCount];
            byte[] bytes = new byte[numGroups * this.bitWidth];
            int bytesToRead = (int)Math.ceil((double)(this.currentCount * this.bitWidth) / (double)8.0F);
            bytesToRead = Math.min(bytesToRead, this.in.available());
            (new DataInputStream(this.in)).readFully(bytes, 0, bytesToRead);
            int valueIndex = 0;

            for(int byteIndex = 0; valueIndex < this.currentCount; byteIndex += this.bitWidth) {
               this.packer.unpack8Values(bytes, byteIndex, this.currentBuffer, valueIndex);
               valueIndex += 8;
            }
            break;
         default:
            throw new ParquetDecodingException("not a valid mode " + this.mode);
      }

   }

   private static enum MODE {
      RLE,
      PACKED;
   }
}
