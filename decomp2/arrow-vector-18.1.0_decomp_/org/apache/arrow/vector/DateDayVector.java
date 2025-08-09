package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.DateDayReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.DateDayHolder;
import org.apache.arrow.vector.holders.NullableDateDayHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class DateDayVector extends BaseFixedWidthVector implements ValueIterableVector {
   public static final byte TYPE_WIDTH = 4;

   public DateDayVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.DATEDAY.getType()), allocator);
   }

   public DateDayVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public DateDayVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 4);
   }

   protected FieldReader getReaderImpl() {
      return new DateDayReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.DATEDAY;
   }

   public int get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getInt((long)index * 4L);
      }
   }

   public void get(int index, NullableDateDayHolder holder) {
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

   public void set(int index, NullableDateDayHolder holder) throws IllegalArgumentException {
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

   public void set(int index, DateDayHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, int value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableDateDayHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, DateDayHolder holder) {
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
      return new TransferImpl((DateDayVector)to);
   }

   private class TransferImpl implements TransferPair {
      DateDayVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new DateDayVector(ref, DateDayVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new DateDayVector(field, allocator);
      }

      public TransferImpl(DateDayVector to) {
         this.to = to;
      }

      public DateDayVector getTo() {
         return this.to;
      }

      public void transfer() {
         DateDayVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         DateDayVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, DateDayVector.this);
      }
   }
}
