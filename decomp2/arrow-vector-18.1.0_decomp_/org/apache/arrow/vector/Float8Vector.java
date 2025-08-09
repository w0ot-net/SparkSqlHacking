package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.Float8ReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.Float8Holder;
import org.apache.arrow.vector.holders.NullableFloat8Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class Float8Vector extends BaseFixedWidthVector implements FloatingPointVector, ValueIterableVector {
   public static final byte TYPE_WIDTH = 8;

   public Float8Vector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.FLOAT8.getType()), allocator);
   }

   public Float8Vector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public Float8Vector(Field field, BufferAllocator allocator) {
      super(field, allocator, 8);
   }

   protected FieldReader getReaderImpl() {
      return new Float8ReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.FLOAT8;
   }

   public double get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getDouble((long)index * 8L);
      }
   }

   public void get(int index, NullableFloat8Holder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getDouble((long)index * 8L);
      }
   }

   public Double getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getDouble((long)index * 8L);
   }

   private void setValue(int index, double value) {
      this.valueBuffer.setDouble((long)index * 8L, value);
   }

   public void set(int index, double value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableFloat8Holder holder) throws IllegalArgumentException {
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

   public void set(int index, Float8Holder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, double value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableFloat8Holder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, Float8Holder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, double value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, double value) {
      this.handleSafe(index);
      this.set(index, isSet, value);
   }

   public static double get(ArrowBuf buffer, int index) {
      return buffer.getDouble((long)index * 8L);
   }

   public void setWithPossibleTruncate(int index, double value) {
      this.set(index, value);
   }

   public void setSafeWithPossibleTruncate(int index, double value) {
      this.setSafe(index, value);
   }

   public double getValueAsDouble(int index) {
      return this.get(index);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((Float8Vector)to);
   }

   private class TransferImpl implements TransferPair {
      Float8Vector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new Float8Vector(ref, Float8Vector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new Float8Vector(field, allocator);
      }

      public TransferImpl(Float8Vector to) {
         this.to = to;
      }

      public Float8Vector getTo() {
         return this.to;
      }

      public void transfer() {
         Float8Vector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         Float8Vector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, Float8Vector.this);
      }
   }
}
