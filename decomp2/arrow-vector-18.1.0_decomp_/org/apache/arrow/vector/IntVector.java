package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.IntReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.IntHolder;
import org.apache.arrow.vector.holders.NullableIntHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class IntVector extends BaseFixedWidthVector implements BaseIntVector, ValueIterableVector {
   public static final byte TYPE_WIDTH = 4;

   public IntVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.INT.getType()), allocator);
   }

   public IntVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public IntVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 4);
   }

   protected FieldReader getReaderImpl() {
      return new IntReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.INT;
   }

   public int get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getInt((long)index * 4L);
      }
   }

   public void get(int index, NullableIntHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getInt((long)index * 4L);
      }
   }

   public Integer getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getInt((long)index * 4L);
   }

   private void setValue(int index, int value) {
      this.valueBuffer.setInt((long)index * 4L, value);
   }

   public void set(int index, int value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableIntHolder holder) throws IllegalArgumentException {
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

   public void set(int index, IntHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, int value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableIntHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, IntHolder holder) {
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

   public static int get(ArrowBuf buffer, int index) {
      return buffer.getInt((long)index * 4L);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((IntVector)to);
   }

   public void setWithPossibleTruncate(int index, long value) {
      this.setSafe(index, (int)value);
   }

   public void setUnsafeWithPossibleTruncate(int index, long value) {
      this.set(index, (int)value);
   }

   public long getValueAsLong(int index) {
      return (long)this.get(index);
   }

   private class TransferImpl implements TransferPair {
      IntVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new IntVector(ref, IntVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new IntVector(field, allocator);
      }

      public TransferImpl(IntVector to) {
         this.to = to;
      }

      public IntVector getTo() {
         return this.to;
      }

      public void transfer() {
         IntVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         IntVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, IntVector.this);
      }
   }
}
