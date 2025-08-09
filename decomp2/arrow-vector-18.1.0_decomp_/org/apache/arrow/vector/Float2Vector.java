package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.Float16;
import org.apache.arrow.vector.complex.impl.Float2ReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.Float2Holder;
import org.apache.arrow.vector.holders.NullableFloat2Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class Float2Vector extends BaseFixedWidthVector implements FloatingPointVector, ValueIterableVector {
   public static final byte TYPE_WIDTH = 2;

   public Float2Vector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.FLOAT2.getType()), allocator);
   }

   public Float2Vector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public Float2Vector(Field field, BufferAllocator allocator) {
      super(field, allocator, 2);
   }

   protected FieldReader getReaderImpl() {
      return new Float2ReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.FLOAT2;
   }

   public short get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getShort((long)index * 2L);
      }
   }

   public void get(int index, NullableFloat2Holder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getShort((long)index * 2L);
      }
   }

   public Short getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getShort((long)index * 2L);
   }

   static short get(ArrowBuf buffer, int index) {
      return buffer.getShort((long)index * 2L);
   }

   public double getValueAsDouble(int index) {
      return (double)this.getValueAsFloat(index);
   }

   public float getValueAsFloat(int index) {
      return Float16.toFloat(this.get(index));
   }

   private void setValue(int index, short value) {
      this.valueBuffer.setShort((long)index * 2L, value);
   }

   private void setValue(int index, float value) {
      this.valueBuffer.setShort((long)index * 2L, Float16.toFloat16(value));
   }

   public void set(int index, short value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void setWithPossibleTruncate(int index, float value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableFloat2Holder holder) throws IllegalArgumentException {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException();
      } else {
         if (holder.isSet > 0) {
            BitVectorHelper.setBit(this.validityBuffer, (long)index);
            this.setValue(index, holder.value);
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void set(int index, Float2Holder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, short value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafeWithPossibleTruncate(int index, float value) {
      this.handleSafe(index);
      this.setWithPossibleTruncate(index, value);
   }

   public void setSafe(int index, NullableFloat2Holder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, Float2Holder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, short value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setWithPossibleTruncate(int index, int isSet, float value) {
      if (isSet > 0) {
         this.setWithPossibleTruncate(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, short value) {
      this.handleSafe(index);
      this.set(index, isSet, value);
   }

   public void setSafeWithPossibleTruncate(int index, int isSet, float value) {
      this.handleSafe(index);
      this.setWithPossibleTruncate(index, isSet, value);
   }

   public void setWithPossibleTruncate(int index, double value) {
      throw new UnsupportedOperationException("The operation for double data types is not supported.");
   }

   public void setSafeWithPossibleTruncate(int index, double value) {
      throw new UnsupportedOperationException("The operation for double data types is not supported.");
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((Float2Vector)to);
   }

   private class TransferImpl implements TransferPair {
      Float2Vector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new Float2Vector(ref, Float2Vector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new Float2Vector(field, allocator);
      }

      public TransferImpl(Float2Vector to) {
         this.to = to;
      }

      public Float2Vector getTo() {
         return this.to;
      }

      public void transfer() {
         Float2Vector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         Float2Vector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, Float2Vector.this);
      }
   }
}
