package org.apache.parquet.column.values.delta;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.values.bitpacking.BytePackerForLong;
import org.apache.parquet.column.values.bitpacking.Packer;
import org.apache.parquet.io.ParquetEncodingException;

public class DeltaBinaryPackingValuesWriterForLong extends DeltaBinaryPackingValuesWriter {
   private static final int MAX_BITWIDTH = 64;
   private long[] deltaBlockBuffer;
   private long firstValue;
   private long previousValue;
   private long minDeltaInCurrentBlock;

   public DeltaBinaryPackingValuesWriterForLong(int slabSize, int pageSize, ByteBufferAllocator allocator) {
      this(128, 4, slabSize, pageSize, allocator);
   }

   public DeltaBinaryPackingValuesWriterForLong(int blockSizeInValues, int miniBlockNum, int slabSize, int pageSize, ByteBufferAllocator allocator) {
      super(blockSizeInValues, miniBlockNum, slabSize, pageSize, allocator);
      this.firstValue = 0L;
      this.previousValue = 0L;
      this.minDeltaInCurrentBlock = Long.MAX_VALUE;
      this.deltaBlockBuffer = new long[this.config.blockSizeInValues];
      this.miniBlockByteBuffer = new byte[this.config.miniBlockSizeInValues * 64];
   }

   public void writeLong(long v) {
      ++this.totalValueCount;
      if (this.totalValueCount == 1) {
         this.firstValue = v;
         this.previousValue = this.firstValue;
      } else {
         long delta = v - this.previousValue;
         this.previousValue = v;
         this.deltaBlockBuffer[this.deltaValuesToFlush++] = delta;
         if (delta < this.minDeltaInCurrentBlock) {
            this.minDeltaInCurrentBlock = delta;
         }

         if (this.config.blockSizeInValues == this.deltaValuesToFlush) {
            this.flushBlockBuffer();
         }

      }
   }

   private void flushBlockBuffer() {
      for(int i = 0; i < this.deltaValuesToFlush; ++i) {
         this.deltaBlockBuffer[i] -= this.minDeltaInCurrentBlock;
      }

      this.writeMinDelta();
      int miniBlocksToFlush = this.getMiniBlockCountToFlush((double)this.deltaValuesToFlush);
      this.calculateBitWidthsForDeltaBlockBuffer(miniBlocksToFlush);

      for(int i = 0; i < this.config.miniBlockNumInABlock; ++i) {
         this.writeBitWidthForMiniBlock(i);
      }

      for(int i = 0; i < miniBlocksToFlush; ++i) {
         int currentBitWidth = this.bitWidths[i];
         int blockOffset = 0;
         BytePackerForLong packer = Packer.LITTLE_ENDIAN.newBytePackerForLong(currentBitWidth);
         int miniBlockStart = i * this.config.miniBlockSizeInValues;

         for(int j = miniBlockStart; j < (i + 1) * this.config.miniBlockSizeInValues; j += 8) {
            packer.pack8Values(this.deltaBlockBuffer, j, this.miniBlockByteBuffer, blockOffset);
            blockOffset += currentBitWidth;
         }

         this.baos.write(this.miniBlockByteBuffer, 0, blockOffset);
      }

      this.minDeltaInCurrentBlock = Long.MAX_VALUE;
      this.deltaValuesToFlush = 0;
   }

   private void writeMinDelta() {
      try {
         BytesUtils.writeZigZagVarLong(this.minDeltaInCurrentBlock, this.baos);
      } catch (IOException e) {
         throw new ParquetEncodingException("can not write min delta for block", e);
      }
   }

   private void calculateBitWidthsForDeltaBlockBuffer(int miniBlocksToFlush) {
      for(int miniBlockIndex = 0; miniBlockIndex < miniBlocksToFlush; ++miniBlockIndex) {
         long mask = 0L;
         int miniStart = miniBlockIndex * this.config.miniBlockSizeInValues;
         int miniEnd = Math.min((miniBlockIndex + 1) * this.config.miniBlockSizeInValues, this.deltaValuesToFlush);

         for(int i = miniStart; i < miniEnd; ++i) {
            mask |= this.deltaBlockBuffer[i];
         }

         this.bitWidths[miniBlockIndex] = 64 - Long.numberOfLeadingZeros(mask);
      }

   }

   public BytesInput getBytes() {
      if (this.deltaValuesToFlush != 0) {
         this.flushBlockBuffer();
      }

      return BytesInput.concat(new BytesInput[]{this.config.toBytesInput(), BytesInput.fromUnsignedVarInt(this.totalValueCount), BytesInput.fromZigZagVarLong(this.firstValue), BytesInput.from(this.baos)});
   }

   public void reset() {
      super.reset();
      this.minDeltaInCurrentBlock = Long.MAX_VALUE;
   }

   public void close() {
      super.close();
      this.minDeltaInCurrentBlock = Long.MAX_VALUE;
   }
}
