package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.ArrowBufPointer;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.complex.impl.BitReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.BitHolder;
import org.apache.arrow.vector.holders.NullableBitHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.TransferPair;

public final class BitVector extends BaseFixedWidthVector implements ValueIterableVector {
   private static final int HASH_CODE_FOR_ZERO = 17;
   private static final int HASH_CODE_FOR_ONE = 19;

   public BitVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.BIT.getType()), allocator);
   }

   public BitVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public BitVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 0);
   }

   protected FieldReader getReaderImpl() {
      return new BitReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.BIT;
   }

   public void setInitialCapacity(int valueCount) {
      int size = getValidityBufferSizeFromCount(valueCount);
      if ((long)size * 2L > MAX_ALLOCATION_SIZE) {
         throw new OversizedAllocationException("Requested amount of memory is more than max allowed");
      } else {
         this.lastValueCapacity = valueCount;
      }
   }

   protected int getValueBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.valueBuffer.capacity() * 8L);
   }

   public int getBufferSizeFor(int count) {
      return count == 0 ? 0 : 2 * getValidityBufferSizeFromCount(count);
   }

   public int getBufferSize() {
      return this.getBufferSizeFor(this.valueCount);
   }

   public void splitAndTransferTo(int startIndex, int length, BaseFixedWidthVector target) {
      Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, this.valueCount);
      this.compareTypes(target, "splitAndTransferTo");
      target.clear();
      target.validityBuffer = this.splitAndTransferBuffer(startIndex, length, this.validityBuffer, target.validityBuffer);
      target.valueBuffer = this.splitAndTransferBuffer(startIndex, length, this.valueBuffer, target.valueBuffer);
      target.refreshValueCapacity();
      target.setValueCount(length);
   }

   private ArrowBuf splitAndTransferBuffer(int startIndex, int length, ArrowBuf sourceBuffer, ArrowBuf destBuffer) {
      int firstByteSource = BitVectorHelper.byteIndex(startIndex);
      int lastByteSource = BitVectorHelper.byteIndex(this.valueCount - 1);
      int byteSizeTarget = getValidityBufferSizeFromCount(length);
      int offset = startIndex % 8;
      if (length > 0) {
         if (offset == 0) {
            if (destBuffer != null) {
               destBuffer.getReferenceManager().release();
            }

            destBuffer = sourceBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
            destBuffer.getReferenceManager().retain(1);
         } else {
            destBuffer = this.allocator.buffer((long)byteSizeTarget);
            destBuffer.readerIndex(0L);
            destBuffer.setZero(0L, destBuffer.capacity());

            for(int i = 0; i < byteSizeTarget - 1; ++i) {
               byte b1 = BitVectorHelper.getBitsFromCurrentByte(sourceBuffer, firstByteSource + i, offset);
               byte b2 = BitVectorHelper.getBitsFromNextByte(sourceBuffer, firstByteSource + i + 1, offset);
               destBuffer.setByte((long)i, b1 + b2);
            }

            if (firstByteSource + byteSizeTarget - 1 < lastByteSource) {
               byte b1 = BitVectorHelper.getBitsFromCurrentByte(sourceBuffer, firstByteSource + byteSizeTarget - 1, offset);
               byte b2 = BitVectorHelper.getBitsFromNextByte(sourceBuffer, firstByteSource + byteSizeTarget, offset);
               destBuffer.setByte((long)(byteSizeTarget - 1), b1 + b2);
            } else {
               byte b1 = BitVectorHelper.getBitsFromCurrentByte(sourceBuffer, firstByteSource + byteSizeTarget - 1, offset);
               destBuffer.setByte((long)(byteSizeTarget - 1), b1);
            }
         }
      }

      return destBuffer;
   }

   private int getBit(int index) {
      int byteIndex = index >> 3;
      byte b = this.valueBuffer.getByte((long)byteIndex);
      int bitIndex = index & 7;
      return b >> bitIndex & 1;
   }

   public int get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.getBit(index);
      }
   }

   public void get(int index, NullableBitHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.getBit(index);
      }
   }

   public Boolean getObject(int index) {
      return this.isSet(index) == 0 ? null : this.getBit(index) != 0;
   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      boolean fromIsSet = BitVectorHelper.get(from.getValidityBuffer(), fromIndex) != 0;
      if (fromIsSet) {
         BitVectorHelper.setBit(this.validityBuffer, (long)thisIndex);
         BitVectorHelper.setValidityBit(this.valueBuffer, thisIndex, ((BitVector)from).getBit(fromIndex));
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, thisIndex);
      }

   }

   public void set(int index, int value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      if (value != 0) {
         BitVectorHelper.setBit(this.valueBuffer, (long)index);
      } else {
         BitVectorHelper.unsetBit(this.valueBuffer, index);
      }

   }

   public void set(int index, NullableBitHolder holder) throws IllegalArgumentException {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException();
      } else {
         if (holder.isSet > 0) {
            BitVectorHelper.setBit(this.validityBuffer, (long)index);
            if (holder.value != 0) {
               BitVectorHelper.setBit(this.valueBuffer, (long)index);
            } else {
               BitVectorHelper.unsetBit(this.valueBuffer, index);
            }
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void set(int index, BitHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      if (holder.value != 0) {
         BitVectorHelper.setBit(this.valueBuffer, (long)index);
      } else {
         BitVectorHelper.unsetBit(this.valueBuffer, index);
      }

   }

   public void setSafe(int index, int value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableBitHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, BitHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, int value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, int value) {
      this.handleSafe(index);
      this.set(index, isSet, value);
   }

   public void setToOne(int index) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      BitVectorHelper.setBit(this.valueBuffer, (long)index);
   }

   public void setSafeToOne(int index) {
      this.handleSafe(index);
      this.setToOne(index);
   }

   public ArrowBufPointer getDataPointer(int index) {
      throw new UnsupportedOperationException();
   }

   public ArrowBufPointer getDataPointer(int index, ArrowBufPointer reuse) {
      throw new UnsupportedOperationException();
   }

   public int hashCode(int index) {
      if (this.isNull(index)) {
         return 0;
      } else {
         return this.get(index) == 0 ? 17 : 19;
      }
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      return this.hashCode(index);
   }

   public void setRangeToOne(int firstBitIndex, int count) {
      int startByteIndex = BitVectorHelper.byteIndex(firstBitIndex);
      int lastBitIndex = firstBitIndex + count;
      int endByteIndex = BitVectorHelper.byteIndex(lastBitIndex);
      int startByteBitIndex = BitVectorHelper.bitIndex(firstBitIndex);
      int endBytebitIndex = BitVectorHelper.bitIndex(lastBitIndex);
      if (count < 8 && startByteIndex == endByteIndex) {
         byte bitMask = 0;

         for(int i = startByteBitIndex; i < endBytebitIndex; ++i) {
            bitMask |= (byte)((int)(1L << i));
         }

         BitVectorHelper.setBitMaskedByte(this.validityBuffer, startByteIndex, bitMask);
         BitVectorHelper.setBitMaskedByte(this.valueBuffer, startByteIndex, bitMask);
      } else {
         if (startByteBitIndex != 0) {
            byte bitMask = (byte)((int)(255L << startByteBitIndex));
            BitVectorHelper.setBitMaskedByte(this.validityBuffer, startByteIndex, bitMask);
            BitVectorHelper.setBitMaskedByte(this.valueBuffer, startByteIndex, bitMask);
            ++startByteIndex;
         }

         this.validityBuffer.setOne(startByteIndex, endByteIndex - startByteIndex);
         this.valueBuffer.setOne(startByteIndex, endByteIndex - startByteIndex);
         if (endBytebitIndex != 0) {
            int byteIndex = BitVectorHelper.byteIndex(lastBitIndex - endBytebitIndex);
            byte bitMask = (byte)((int)(255L >>> (8 - endBytebitIndex & 7)));
            BitVectorHelper.setBitMaskedByte(this.validityBuffer, byteIndex, bitMask);
            BitVectorHelper.setBitMaskedByte(this.valueBuffer, byteIndex, bitMask);
         }
      }

   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((BitVector)to);
   }

   private class TransferImpl implements TransferPair {
      BitVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new BitVector(ref, BitVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new BitVector(field, allocator);
      }

      public TransferImpl(BitVector to) {
         this.to = to;
      }

      public BitVector getTo() {
         return this.to;
      }

      public void transfer() {
         BitVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         BitVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, BitVector.this);
      }
   }
}
