package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.Float4ReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.Float4Holder;
import org.apache.arrow.vector.holders.NullableFloat4Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class Float4Vector extends BaseFixedWidthVector implements FloatingPointVector, ValueIterableVector {
   public static final byte TYPE_WIDTH = 4;

   public Float4Vector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.FLOAT4.getType()), allocator);
   }

   public Float4Vector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public Float4Vector(Field field, BufferAllocator allocator) {
      super(field, allocator, 4);
   }

   protected FieldReader getReaderImpl() {
      return new Float4ReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.FLOAT4;
   }

   public float get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getFloat((long)index * 4L);
      }
   }

   public void get(int index, NullableFloat4Holder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getFloat((long)index * 4L);
      }
   }

   public Float getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getFloat((long)index * 4L);
   }

   private void setValue(int index, float value) {
      this.valueBuffer.setFloat((long)index * 4L, value);
   }

   public void set(int index, float value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableFloat4Holder holder) throws IllegalArgumentException {
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

   public void set(int index, Float4Holder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, float value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableFloat4Holder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, Float4Holder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, float value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, float value) {
      this.handleSafe(index);
      this.set(index, isSet, value);
   }

   public static float get(ArrowBuf buffer, int index) {
      return buffer.getFloat((long)index * 4L);
   }

   public void setWithPossibleTruncate(int index, double value) {
      this.set(index, (float)value);
   }

   public void setSafeWithPossibleTruncate(int index, double value) {
      this.setSafe(index, (float)value);
   }

   public double getValueAsDouble(int index) {
      return (double)this.get(index);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((Float4Vector)to);
   }

   private class TransferImpl implements TransferPair {
      Float4Vector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new Float4Vector(ref, Float4Vector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new Float4Vector(field, allocator);
      }

      public TransferImpl(Float4Vector to) {
         this.to = to;
      }

      public Float4Vector getTo() {
         return this.to;
      }

      public void transfer() {
         Float4Vector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         Float4Vector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, Float4Vector.this);
      }
   }
}
