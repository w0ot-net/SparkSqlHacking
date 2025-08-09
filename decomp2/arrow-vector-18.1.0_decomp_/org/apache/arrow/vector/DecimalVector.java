package org.apache.arrow.vector;

import java.math.BigDecimal;
import java.nio.ByteOrder;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.MemoryUtil;
import org.apache.arrow.vector.complex.impl.DecimalReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.DecimalHolder;
import org.apache.arrow.vector.holders.NullableDecimalHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.DecimalUtility;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.validate.ValidateUtil;

public final class DecimalVector extends BaseFixedWidthVector implements ValueIterableVector {
   public static final int MAX_PRECISION = 38;
   public static final byte TYPE_WIDTH = 16;
   private static final boolean LITTLE_ENDIAN;
   private final int precision;
   private final int scale;

   public DecimalVector(String name, BufferAllocator allocator, int precision, int scale) {
      this(name, FieldType.nullable(new ArrowType.Decimal(precision, scale, 128)), allocator);
   }

   public DecimalVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public DecimalVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 16);
      ArrowType.Decimal arrowType = (ArrowType.Decimal)field.getFieldType().getType();
      this.precision = arrowType.getPrecision();
      this.scale = arrowType.getScale();
   }

   protected FieldReader getReaderImpl() {
      return new DecimalReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.DECIMAL;
   }

   public ArrowBuf get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.slice((long)index * 16L, 16L);
      }
   }

   public void get(int index, NullableDecimalHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.buffer = this.valueBuffer;
         holder.precision = this.precision;
         holder.scale = this.scale;
         holder.start = (long)index * 16L;
      }
   }

   public BigDecimal getObject(int index) {
      return this.isSet(index) == 0 ? null : this.getObjectNotNull(index);
   }

   public BigDecimal getObjectNotNull(int index) {
      return DecimalUtility.getBigDecimalFromArrowBuf(this.valueBuffer, index, this.scale, 16);
   }

   public int getPrecision() {
      return this.precision;
   }

   public int getScale() {
      return this.scale;
   }

   public void set(int index, ArrowBuf buffer) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 16L, buffer, 0L, 16L);
   }

   public void setBigEndian(int index, byte[] value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int length = value.length;
      this.valueBuffer.checkBytes((long)index * 16L, (long)(index + 1) * 16L);
      long outAddress = this.valueBuffer.memoryAddress() + (long)index * 16L;
      if (length == 0) {
         MemoryUtil.setMemory(outAddress, 16L, (byte)0);
      } else {
         if (LITTLE_ENDIAN) {
            for(int byteIdx = 0; byteIdx < length; ++byteIdx) {
               MemoryUtil.putByte(outAddress + (long)byteIdx, value[length - 1 - byteIdx]);
            }

            if (length == 16) {
               return;
            }

            if (length < 16) {
               byte pad = (byte)(value[0] < 0 ? 255 : 0);
               MemoryUtil.setMemory(outAddress + (long)length, (long)(16 - length), pad);
               return;
            }
         } else if (length <= 16) {
            MemoryUtil.copyToMemory(value, 0L, outAddress + 16L - (long)length, (long)length);
            byte pad = (byte)(value[0] < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress, (long)(16 - length), pad);
            return;
         }

         throw new IllegalArgumentException("Invalid decimal value length. Valid length in [1 - 16], got " + length);
      }
   }

   public void set(int index, long start, ArrowBuf buffer) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 16L, buffer, start, 16L);
   }

   public void setSafe(int index, long start, ArrowBuf buffer, int length) {
      this.handleSafe(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      buffer.checkBytes(start, start + (long)length);
      this.valueBuffer.checkBytes((long)index * 16L, (long)(index + 1) * 16L);
      long inAddress = buffer.memoryAddress() + start;
      long outAddress = this.valueBuffer.memoryAddress() + (long)index * 16L;
      if (LITTLE_ENDIAN) {
         MemoryUtil.copyMemory(inAddress, outAddress, (long)length);
         if (length < 16) {
            byte msb = MemoryUtil.getByte(inAddress + (long)length - 1L);
            byte pad = (byte)(msb < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress + (long)length, (long)(16 - length), pad);
         }
      } else {
         MemoryUtil.copyMemory(inAddress, outAddress + 16L - (long)length, (long)length);
         if (length < 16) {
            byte msb = MemoryUtil.getByte(inAddress);
            byte pad = (byte)(msb < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress, (long)(16 - length), pad);
         }
      }

   }

   public void setBigEndianSafe(int index, long start, ArrowBuf buffer, int length) {
      this.handleSafe(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      buffer.checkBytes(start, start + (long)length);
      this.valueBuffer.checkBytes((long)index * 16L, (long)(index + 1) * 16L);
      long inAddress = buffer.memoryAddress() + start;
      long outAddress = this.valueBuffer.memoryAddress() + (long)index * 16L;
      if (LITTLE_ENDIAN) {
         for(int byteIdx = 0; byteIdx < length; ++byteIdx) {
            byte val = MemoryUtil.getByte(inAddress + (long)length - 1L - (long)byteIdx);
            MemoryUtil.putByte(outAddress + (long)byteIdx, val);
         }

         if (length < 16) {
            byte msb = MemoryUtil.getByte(inAddress);
            byte pad = (byte)(msb < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress + (long)length, (long)(16 - length), pad);
         }
      } else {
         MemoryUtil.copyMemory(inAddress, outAddress + 16L - (long)length, (long)length);
         if (length < 16) {
            byte msb = MemoryUtil.getByte(inAddress);
            byte pad = (byte)(msb < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress, (long)(16 - length), pad);
         }
      }

   }

   public void set(int index, BigDecimal value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      DecimalUtility.checkPrecisionAndScale(value, this.precision, this.scale);
      DecimalUtility.writeBigDecimalToArrowBuf(value, this.valueBuffer, index, 16);
   }

   public void set(int index, long value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      DecimalUtility.writeLongToArrowBuf(value, this.valueBuffer, index, 16);
   }

   public void set(int index, NullableDecimalHolder holder) throws IllegalArgumentException {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException();
      } else {
         if (holder.isSet > 0) {
            BitVectorHelper.setBit(this.validityBuffer, (long)index);
            this.valueBuffer.setBytes((long)index * 16L, holder.buffer, holder.start, 16L);
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void set(int index, DecimalHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 16L, holder.buffer, holder.start, 16L);
   }

   public void setSafe(int index, ArrowBuf buffer) {
      this.handleSafe(index);
      this.set(index, buffer);
   }

   public void setBigEndianSafe(int index, byte[] value) {
      this.handleSafe(index);
      this.setBigEndian(index, value);
   }

   public void setSafe(int index, long start, ArrowBuf buffer) {
      this.handleSafe(index);
      this.set(index, start, buffer);
   }

   public void setSafe(int index, BigDecimal value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, long value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableDecimalHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, DecimalHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, long start, ArrowBuf buffer) {
      if (isSet > 0) {
         this.set(index, start, buffer);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, long start, ArrowBuf buffer) {
      this.handleSafe(index);
      this.set(index, isSet, start, buffer);
   }

   public void validateScalars() {
      for(int i = 0; i < this.getValueCount(); ++i) {
         BigDecimal value = this.getObject(i);
         if (value != null) {
            ValidateUtil.validateOrThrow(DecimalUtility.checkPrecisionAndScaleNoThrow(value, this.getPrecision(), this.getScale()), "Invalid value for DecimalVector at position " + i + ". Value does not fit in precision " + this.getPrecision() + " and scale " + this.getScale() + ".");
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
      return new TransferImpl((DecimalVector)to);
   }

   static {
      LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
   }

   private class TransferImpl implements TransferPair {
      DecimalVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new DecimalVector(ref, allocator, DecimalVector.this.precision, DecimalVector.this.scale);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new DecimalVector(field, allocator);
      }

      public TransferImpl(DecimalVector to) {
         this.to = to;
      }

      public DecimalVector getTo() {
         return this.to;
      }

      public void transfer() {
         DecimalVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         DecimalVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, DecimalVector.this);
      }
   }
}
