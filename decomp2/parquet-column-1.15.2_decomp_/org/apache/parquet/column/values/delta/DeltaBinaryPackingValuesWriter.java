package org.apache.parquet.column.values.delta;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.bytes.CapacityByteArrayOutputStream;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.io.ParquetEncodingException;

public abstract class DeltaBinaryPackingValuesWriter extends ValuesWriter {
   public static final int DEFAULT_NUM_BLOCK_VALUES = 128;
   public static final int DEFAULT_NUM_MINIBLOCKS = 4;
   protected final CapacityByteArrayOutputStream baos;
   protected final DeltaBinaryPackingConfig config;
   protected final int[] bitWidths;
   protected int totalValueCount;
   protected int deltaValuesToFlush;
   protected byte[] miniBlockByteBuffer;

   public DeltaBinaryPackingValuesWriter(int slabSize, int pageSize, ByteBufferAllocator allocator) {
      this(128, 4, slabSize, pageSize, allocator);
   }

   public DeltaBinaryPackingValuesWriter(int blockSizeInValues, int miniBlockNum, int slabSize, int pageSize, ByteBufferAllocator allocator) {
      this.totalValueCount = 0;
      this.deltaValuesToFlush = 0;
      this.config = new DeltaBinaryPackingConfig(blockSizeInValues, miniBlockNum);
      this.bitWidths = new int[this.config.miniBlockNumInABlock];
      this.baos = new CapacityByteArrayOutputStream(slabSize, pageSize, allocator);
   }

   public long getBufferedSize() {
      return this.baos.size();
   }

   protected void writeBitWidthForMiniBlock(int i) {
      try {
         BytesUtils.writeIntLittleEndianOnOneByte(this.baos, this.bitWidths[i]);
      } catch (IOException e) {
         throw new ParquetEncodingException("can not write bitwidth for miniblock", e);
      }
   }

   protected int getMiniBlockCountToFlush(double numberCount) {
      return (int)Math.ceil(numberCount / (double)this.config.miniBlockSizeInValues);
   }

   public Encoding getEncoding() {
      return Encoding.DELTA_BINARY_PACKED;
   }

   public void reset() {
      this.totalValueCount = 0;
      this.baos.reset();
      this.deltaValuesToFlush = 0;
   }

   public void close() {
      this.totalValueCount = 0;
      this.baos.close();
      this.deltaValuesToFlush = 0;
   }

   public long getAllocatedSize() {
      return (long)this.baos.getCapacity();
   }

   public String memUsageString(String prefix) {
      return String.format("%s DeltaBinaryPacking %d bytes", prefix, this.getAllocatedSize());
   }
}
