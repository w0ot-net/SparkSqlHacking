package org.apache.arrow.vector;

import java.time.Period;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.IntervalYearReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.IntervalYearHolder;
import org.apache.arrow.vector.holders.NullableIntervalYearHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class IntervalYearVector extends BaseFixedWidthVector implements ValueIterableVector {
   public static final byte TYPE_WIDTH = 4;

   public IntervalYearVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.INTERVALYEAR.getType()), allocator);
   }

   public IntervalYearVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public IntervalYearVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 4);
   }

   protected FieldReader getReaderImpl() {
      return new IntervalYearReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.INTERVALYEAR;
   }

   public static int getTotalMonths(ArrowBuf buffer, int index) {
      return buffer.getInt((long)index * 4L);
   }

   public int get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getInt((long)index * 4L);
      }
   }

   public void get(int index, NullableIntervalYearHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getInt((long)index * 4L);
      }
   }

   public Period getObject(int index) {
      if (this.isSet(index) == 0) {
         return null;
      } else {
         int interval = this.valueBuffer.getInt((long)index * 4L);
         return Period.ofMonths(interval);
      }
   }

   public StringBuilder getAsStringBuilder(int index) {
      return this.isSet(index) == 0 ? null : this.getAsStringBuilderHelper(index);
   }

   private StringBuilder getAsStringBuilderHelper(int index) {
      int value = this.valueBuffer.getInt((long)index * 4L);
      int years = value / 12;
      int months = value % 12;
      String yearString = Math.abs(years) == 1 ? " year " : " years ";
      String monthString = Math.abs(months) == 1 ? " month " : " months ";
      return (new StringBuilder()).append(years).append(yearString).append(months).append(monthString);
   }

   private void setValue(int index, int value) {
      this.valueBuffer.setInt((long)index * 4L, value);
   }

   public void set(int index, int value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableIntervalYearHolder holder) throws IllegalArgumentException {
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

   public void set(int index, IntervalYearHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, int value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableIntervalYearHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, IntervalYearHolder holder) {
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

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((IntervalYearVector)to);
   }

   private class TransferImpl implements TransferPair {
      IntervalYearVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new IntervalYearVector(ref, IntervalYearVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new IntervalYearVector(field, allocator);
      }

      public TransferImpl(IntervalYearVector to) {
         this.to = to;
      }

      public IntervalYearVector getTo() {
         return this.to;
      }

      public void transfer() {
         IntervalYearVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         IntervalYearVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, IntervalYearVector.this);
      }
   }
}
