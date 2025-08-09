package org.apache.parquet.column.values.rle;

import java.io.IOException;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.bytes.CapacityByteArrayOutputStream;
import org.apache.parquet.column.values.bitpacking.BytePacker;
import org.apache.parquet.column.values.bitpacking.Packer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunLengthBitPackingHybridEncoder implements AutoCloseable {
   private static final Logger LOG = LoggerFactory.getLogger(RunLengthBitPackingHybridEncoder.class);
   private final BytePacker packer;
   private final CapacityByteArrayOutputStream baos;
   private final int bitWidth;
   private final byte[] packBuffer;
   private int previousValue;
   private final int[] bufferedValues;
   private int numBufferedValues;
   private int repeatCount;
   private int bitPackedGroupCount;
   private long bitPackedRunHeaderPointer;
   private boolean toBytesCalled;

   public RunLengthBitPackingHybridEncoder(int bitWidth, int initialCapacity, int pageSize, ByteBufferAllocator allocator) {
      LOG.debug("Encoding: RunLengthBitPackingHybridEncoder with bithWidth: {} initialCapacity {}", bitWidth, initialCapacity);
      Preconditions.checkArgument(bitWidth >= 0 && bitWidth <= 32, "bitWidth must be >= 0 and <= 32");
      this.bitWidth = bitWidth;
      this.baos = new CapacityByteArrayOutputStream(initialCapacity, pageSize, allocator);
      this.packBuffer = new byte[bitWidth];
      this.bufferedValues = new int[8];
      this.packer = Packer.LITTLE_ENDIAN.newBytePacker(bitWidth);
      this.reset(false);
   }

   private void reset(boolean resetBaos) {
      if (resetBaos) {
         this.baos.reset();
      }

      this.previousValue = 0;
      this.numBufferedValues = 0;
      this.repeatCount = 0;
      this.bitPackedGroupCount = 0;
      this.bitPackedRunHeaderPointer = -1L;
      this.toBytesCalled = false;
   }

   public void writeInt(int value) throws IOException {
      if (value == this.previousValue) {
         ++this.repeatCount;
         if (this.repeatCount >= 8) {
            return;
         }
      } else {
         if (this.repeatCount >= 8) {
            this.writeRleRun();
         }

         this.repeatCount = 1;
         this.previousValue = value;
      }

      this.bufferedValues[this.numBufferedValues] = value;
      ++this.numBufferedValues;
      if (this.numBufferedValues == 8) {
         this.writeOrAppendBitPackedRun();
      }

   }

   private void writeOrAppendBitPackedRun() throws IOException {
      if (this.bitPackedGroupCount >= 63) {
         this.endPreviousBitPackedRun();
      }

      if (this.bitPackedRunHeaderPointer == -1L) {
         this.baos.write(0);
         this.bitPackedRunHeaderPointer = this.baos.getCurrentIndex();
      }

      this.packer.pack8Values(this.bufferedValues, 0, this.packBuffer, 0);
      this.baos.write(this.packBuffer);
      this.numBufferedValues = 0;
      this.repeatCount = 0;
      ++this.bitPackedGroupCount;
   }

   private void endPreviousBitPackedRun() {
      if (this.bitPackedRunHeaderPointer != -1L) {
         byte bitPackHeader = (byte)(this.bitPackedGroupCount << 1 | 1);
         this.baos.setByte(this.bitPackedRunHeaderPointer, bitPackHeader);
         this.bitPackedRunHeaderPointer = -1L;
         this.bitPackedGroupCount = 0;
      }
   }

   private void writeRleRun() throws IOException {
      this.endPreviousBitPackedRun();
      BytesUtils.writeUnsignedVarInt(this.repeatCount << 1, this.baos);
      BytesUtils.writeIntLittleEndianPaddedOnBitWidth(this.baos, this.previousValue, this.bitWidth);
      this.repeatCount = 0;
      this.numBufferedValues = 0;
   }

   public BytesInput toBytes() throws IOException {
      Preconditions.checkArgument(!this.toBytesCalled, "You cannot call toBytes() more than once without calling reset()");
      if (this.repeatCount >= 8) {
         this.writeRleRun();
      } else if (this.numBufferedValues > 0) {
         for(int i = this.numBufferedValues; i < 8; ++i) {
            this.bufferedValues[i] = 0;
         }

         this.writeOrAppendBitPackedRun();
         this.endPreviousBitPackedRun();
      } else {
         this.endPreviousBitPackedRun();
      }

      this.toBytesCalled = true;
      return BytesInput.from(this.baos);
   }

   public void reset() {
      this.reset(true);
   }

   public void close() {
      this.reset(false);
      this.baos.close();
   }

   public long getBufferedSize() {
      return this.baos.size();
   }

   public long getAllocatedSize() {
      return (long)this.baos.getCapacity();
   }
}
