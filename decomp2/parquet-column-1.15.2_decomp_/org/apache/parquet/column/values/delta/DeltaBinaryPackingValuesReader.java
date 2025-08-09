package org.apache.parquet.column.values.delta;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.column.values.bitpacking.BytePackerForLong;
import org.apache.parquet.column.values.bitpacking.Packer;
import org.apache.parquet.io.ParquetDecodingException;

public class DeltaBinaryPackingValuesReader extends ValuesReader {
   private int totalValueCount;
   private int valuesRead;
   private long minDeltaInCurrentBlock;
   private long[] valuesBuffer;
   private int valuesBuffered;
   private ByteBufferInputStream in;
   private DeltaBinaryPackingConfig config;
   private int[] bitWidths;

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      this.in = stream;
      long startPos = this.in.position();
      this.config = DeltaBinaryPackingConfig.readConfig(this.in);
      this.totalValueCount = BytesUtils.readUnsignedVarInt(this.in);
      this.allocateValuesBuffer();
      this.bitWidths = new int[this.config.miniBlockNumInABlock];
      this.valuesBuffer[this.valuesBuffered++] = BytesUtils.readZigZagVarLong(this.in);

      while(this.valuesBuffered < this.totalValueCount) {
         this.loadNewBlockToBuffer();
      }

      this.updateNextOffset((int)(this.in.position() - startPos));
   }

   private void allocateValuesBuffer() {
      int totalMiniBlockCount = (int)Math.ceil((double)this.totalValueCount / (double)this.config.miniBlockSizeInValues);
      this.valuesBuffer = new long[totalMiniBlockCount * this.config.miniBlockSizeInValues + 1];
   }

   public void skip() {
      this.checkRead();
      ++this.valuesRead;
   }

   public void skip(int n) {
      this.valuesRead += n - 1;
      this.checkRead();
      ++this.valuesRead;
   }

   public int readInteger() {
      return (int)this.readLong();
   }

   public long readLong() {
      this.checkRead();
      return this.valuesBuffer[this.valuesRead++];
   }

   private void checkRead() {
      if (this.valuesRead >= this.totalValueCount) {
         throw new ParquetDecodingException("no more value to read, total value count is " + this.totalValueCount);
      }
   }

   private void loadNewBlockToBuffer() throws IOException {
      try {
         this.minDeltaInCurrentBlock = BytesUtils.readZigZagVarLong(this.in);
      } catch (IOException e) {
         throw new ParquetDecodingException("can not read min delta in current block", e);
      }

      this.readBitWidthsForMiniBlocks();

      int i;
      for(i = 0; i < this.config.miniBlockNumInABlock && this.valuesBuffered < this.totalValueCount; ++i) {
         BytePackerForLong packer = Packer.LITTLE_ENDIAN.newBytePackerForLong(this.bitWidths[i]);
         this.unpackMiniBlock(packer);
      }

      int valueUnpacked = i * this.config.miniBlockSizeInValues;

      for(int j = this.valuesBuffered - valueUnpacked; j < this.valuesBuffered; ++j) {
         long[] var10000 = this.valuesBuffer;
         var10000[j] += this.minDeltaInCurrentBlock + this.valuesBuffer[j - 1];
      }

   }

   private void unpackMiniBlock(BytePackerForLong packer) throws IOException {
      for(int j = 0; j < this.config.miniBlockSizeInValues; j += 8) {
         this.unpack8Values(packer);
      }

   }

   private void unpack8Values(BytePackerForLong packer) throws IOException {
      ByteBuffer buffer = this.in.slice(packer.getBitWidth());
      packer.unpack8Values(buffer, buffer.position(), this.valuesBuffer, this.valuesBuffered);
      this.valuesBuffered += 8;
   }

   private void readBitWidthsForMiniBlocks() {
      for(int i = 0; i < this.config.miniBlockNumInABlock; ++i) {
         try {
            this.bitWidths[i] = BytesUtils.readIntLittleEndianOnOneByte(this.in);
         } catch (IOException e) {
            throw new ParquetDecodingException("Can not decode bitwidth in block header", e);
         }
      }

   }
}
