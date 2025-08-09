package org.apache.parquet.column.values.bitpacking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteBasedBitPackingEncoder {
   private static final Logger LOG = LoggerFactory.getLogger(ByteBasedBitPackingEncoder.class);
   private static final int VALUES_WRITTEN_AT_A_TIME = 8;
   private static final int MAX_SLAB_SIZE_MULT = 65536;
   private static final int INITIAL_SLAB_SIZE_MULT = 1024;
   private final int bitWidth;
   private final BytePacker packer;
   private final int[] input = new int[8];
   private int slabSize;
   private long totalFullSlabSize;
   private int inputSize;
   private byte[] packed;
   private int packedPosition;
   private final List slabs = new ArrayList();
   private int totalValues;

   public ByteBasedBitPackingEncoder(int bitWidth, Packer packer) {
      this.bitWidth = bitWidth;
      this.inputSize = 0;
      this.totalFullSlabSize = 0L;
      this.slabSize = bitWidth == 0 ? 1 : bitWidth * 1024;
      this.initPackedSlab();
      this.packer = packer.newBytePacker(bitWidth);
   }

   public void writeInt(int value) throws IOException {
      this.input[this.inputSize] = value;
      ++this.inputSize;
      if (this.inputSize == 8) {
         this.pack();
         if (this.packedPosition == this.slabSize) {
            this.slabs.add(BytesInput.from(this.packed));
            this.totalFullSlabSize += (long)this.slabSize;
            if (this.slabSize < this.bitWidth * 65536) {
               this.slabSize *= 2;
            }

            this.initPackedSlab();
         }
      }

   }

   private void pack() {
      this.packer.pack8Values(this.input, 0, this.packed, this.packedPosition);
      this.packedPosition += this.bitWidth;
      this.totalValues += this.inputSize;
      this.inputSize = 0;
   }

   private void initPackedSlab() {
      this.packed = new byte[this.slabSize];
      this.packedPosition = 0;
   }

   public BytesInput toBytes() throws IOException {
      int packedByteLength = this.packedPosition + BytesUtils.paddedByteCountFromBits(this.inputSize * this.bitWidth);
      LOG.debug("writing {} bytes", this.totalFullSlabSize + (long)packedByteLength);
      if (this.inputSize > 0) {
         for(int i = this.inputSize; i < this.input.length; ++i) {
            this.input[i] = 0;
         }

         this.pack();
      }

      return BytesInput.concat(new BytesInput[]{BytesInput.concat(this.slabs), BytesInput.from(this.packed, 0, packedByteLength)});
   }

   public long getBufferSize() {
      return (long)BytesUtils.paddedByteCountFromBits((this.totalValues + this.inputSize) * this.bitWidth);
   }

   public long getAllocatedSize() {
      return this.totalFullSlabSize + (long)this.packed.length + (long)(this.input.length * 4);
   }

   public String memUsageString(String prefix) {
      return String.format("%s ByteBitPacking %d slabs, %d bytes", prefix, this.slabs.size(), this.getAllocatedSize());
   }

   int getNumSlabs() {
      return this.slabs.size() + 1;
   }
}
