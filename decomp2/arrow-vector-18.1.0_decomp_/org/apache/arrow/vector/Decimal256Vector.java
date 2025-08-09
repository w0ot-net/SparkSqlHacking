package org.apache.arrow.vector;

import java.math.BigDecimal;
import java.nio.ByteOrder;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.MemoryUtil;
import org.apache.arrow.vector.complex.impl.Decimal256ReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.Decimal256Holder;
import org.apache.arrow.vector.holders.NullableDecimal256Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.DecimalUtility;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.validate.ValidateUtil;

public final class Decimal256Vector extends BaseFixedWidthVector implements ValueIterableVector {
   public static final int MAX_PRECISION = 76;
   public static final byte TYPE_WIDTH = 32;
   private static final boolean LITTLE_ENDIAN;
   private final int precision;
   private final int scale;

   public Decimal256Vector(String name, BufferAllocator allocator, int precision, int scale) {
      this(name, FieldType.nullable(new ArrowType.Decimal(precision, scale, 256)), allocator);
   }

   public Decimal256Vector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public Decimal256Vector(Field field, BufferAllocator allocator) {
      super(field, allocator, 32);
      ArrowType.Decimal arrowType = (ArrowType.Decimal)field.getFieldType().getType();
      this.precision = arrowType.getPrecision();
      this.scale = arrowType.getScale();
   }

   protected FieldReader getReaderImpl() {
      return new Decimal256ReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.DECIMAL256;
   }

   public ArrowBuf get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.slice((long)index * 32L, 32L);
      }
   }

   public void get(int index, NullableDecimal256Holder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.buffer = this.valueBuffer;
         holder.precision = this.precision;
         holder.scale = this.scale;
         holder.start = (long)index * 32L;
      }
   }

   public BigDecimal getObject(int index) {
      return this.isSet(index) == 0 ? null : this.getObjectNotNull(index);
   }

   public BigDecimal getObjectNotNull(int index) {
      return DecimalUtility.getBigDecimalFromArrowBuf(this.valueBuffer, index, this.scale, 32);
   }

   public int getPrecision() {
      return this.precision;
   }

   public int getScale() {
      return this.scale;
   }

   public void set(int index, ArrowBuf buffer) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 32L, buffer, 0L, 32L);
   }

   public void setBigEndian(int index, byte[] value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int length = value.length;
      this.valueBuffer.checkBytes((long)index * 32L, (long)(index + 1) * 32L);
      long outAddress = this.valueBuffer.memoryAddress() + (long)index * 32L;
      if (length == 0) {
         MemoryUtil.setMemory(outAddress, 32L, (byte)0);
      } else {
         if (LITTLE_ENDIAN) {
            for(int byteIdx = 0; byteIdx < length; ++byteIdx) {
               MemoryUtil.putByte(outAddress + (long)byteIdx, value[length - 1 - byteIdx]);
            }

            if (length == 32) {
               return;
            }

            if (length < 32) {
               byte pad = (byte)(value[0] < 0 ? 255 : 0);
               MemoryUtil.setMemory(outAddress + (long)length, (long)(32 - length), pad);
               return;
            }
         } else if (length <= 32) {
            MemoryUtil.copyToMemory(value, 0L, outAddress + 32L - (long)length, (long)length);
            byte pad = (byte)(value[0] < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress, (long)(32 - length), pad);
            return;
         }

         throw new IllegalArgumentException("Invalid decimal value length. Valid length in [1 - 32], got " + length);
      }
   }

   public void set(int index, long start, ArrowBuf buffer) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 32L, buffer, start, 32L);
   }

   public void setSafe(int index, long start, ArrowBuf buffer, int length) {
      this.handleSafe(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      buffer.checkBytes(start, start + (long)length);
      this.valueBuffer.checkBytes((long)index * 32L, (long)(index + 1) * 32L);
      long inAddress = buffer.memoryAddress() + start;
      long outAddress = this.valueBuffer.memoryAddress() + (long)index * 32L;
      if (LITTLE_ENDIAN) {
         MemoryUtil.copyMemory(inAddress, outAddress, (long)length);
         if (length < 32) {
            byte msb = MemoryUtil.getByte(inAddress + (long)length - 1L);
            byte pad = (byte)(msb < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress + (long)length, (long)(32 - length), pad);
         }
      } else {
         MemoryUtil.copyMemory(inAddress, outAddress + 32L - (long)length, (long)length);
         if (length < 32) {
            byte msb = MemoryUtil.getByte(inAddress);
            byte pad = (byte)(msb < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress, (long)(32 - length), pad);
         }
      }

   }

   public void setBigEndianSafe(int index, long start, ArrowBuf buffer, int length) {
      this.handleSafe(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      buffer.checkBytes(start, start + (long)length);
      this.valueBuffer.checkBytes((long)index * 32L, (long)(index + 1) * 32L);
      long inAddress = buffer.memoryAddress() + start;
      long outAddress = this.valueBuffer.memoryAddress() + (long)index * 32L;
      if (LITTLE_ENDIAN) {
         for(int byteIdx = 0; byteIdx < length; ++byteIdx) {
            byte val = MemoryUtil.getByte(inAddress + (long)length - 1L - (long)byteIdx);
            MemoryUtil.putByte(outAddress + (long)byteIdx, val);
         }

         if (length < 32) {
            byte msb = MemoryUtil.getByte(inAddress);
            byte pad = (byte)(msb < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress + (long)length, (long)(32 - length), pad);
         }
      } else {
         MemoryUtil.copyMemory(inAddress, outAddress + 32L - (long)length, (long)length);
         if (length < 32) {
            byte msb = MemoryUtil.getByte(inAddress);
            byte pad = (byte)(msb < 0 ? 255 : 0);
            MemoryUtil.setMemory(outAddress, (long)(32 - length), pad);
         }
      }

   }

   public void set(int index, BigDecimal value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      DecimalUtility.checkPrecisionAndScale(value, this.precision, this.scale);
      DecimalUtility.writeBigDecimalToArrowBuf(value, this.valueBuffer, index, 32);
   }

   public void set(int index, long value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      DecimalUtility.writeLongToArrowBuf(value, this.valueBuffer, index, 32);
   }

   public void set(int index, NullableDecimal256Holder holder) throws IllegalArgumentException {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException();
      } else {
         if (holder.isSet > 0) {
            BitVectorHelper.setBit(this.validityBuffer, (long)index);
            this.valueBuffer.setBytes((long)index * 32L, holder.buffer, holder.start, 32L);
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void set(int index, Decimal256Holder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 32L, holder.buffer, holder.start, 32L);
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

   public void setSafe(int index, NullableDecimal256Holder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, Decimal256Holder holder) {
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
            ValidateUtil.validateOrThrow(DecimalUtility.checkPrecisionAndScaleNoThrow(value, this.getPrecision(), this.getScale()), "Invalid value for Decimal256Vector at position " + i + ". Value does not fit in precision " + this.getPrecision() + " and scale " + this.getScale() + ".");
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
      return new TransferImpl((Decimal256Vector)to);
   }

   static {
      LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
   }

   private class TransferImpl implements TransferPair {
      Decimal256Vector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new Decimal256Vector(ref, allocator, Decimal256Vector.this.precision, Decimal256Vector.this.scale);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new Decimal256Vector(field, allocator);
      }

      public TransferImpl(Decimal256Vector to) {
         this.to = to;
      }

      public Decimal256Vector getTo() {
         return this.to;
      }

      public void transfer() {
         Decimal256Vector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         Decimal256Vector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, Decimal256Vector.this);
      }
   }
}
