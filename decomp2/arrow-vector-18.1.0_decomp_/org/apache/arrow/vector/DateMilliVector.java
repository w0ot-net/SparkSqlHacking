package org.apache.arrow.vector;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.DateMilliReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.DateMilliHolder;
import org.apache.arrow.vector.holders.NullableDateMilliHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.DateUtility;
import org.apache.arrow.vector.util.TransferPair;

public final class DateMilliVector extends BaseFixedWidthVector implements ValueIterableVector {
   public static final byte TYPE_WIDTH = 8;

   public DateMilliVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.DATEMILLI.getType()), allocator);
   }

   public DateMilliVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public DateMilliVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 8);
   }

   protected FieldReader getReaderImpl() {
      return new DateMilliReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.DATEMILLI;
   }

   public long get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getLong((long)index * 8L);
      }
   }

   public void get(int index, NullableDateMilliHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getLong((long)index * 8L);
      }
   }

   public LocalDateTime getObject(int index) {
      if (this.isSet(index) == 0) {
         return null;
      } else {
         long millis = this.valueBuffer.getLong((long)index * 8L);
         return DateUtility.getLocalDateTimeFromEpochMilli(millis);
      }
   }

   private void setValue(int index, long value) {
      this.valueBuffer.setLong((long)index * 8L, value);
   }

   public void set(int index, long value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableDateMilliHolder holder) throws IllegalArgumentException {
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

   public void set(int index, DateMilliHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, long value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableDateMilliHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, DateMilliHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, long value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, long value) {
      this.handleSafe(index);
      this.set(index, isSet, value);
   }

   public static long get(ArrowBuf buffer, int index) {
      return buffer.getLong((long)index * 8L);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((DateMilliVector)to);
   }

   private class TransferImpl implements TransferPair {
      DateMilliVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new DateMilliVector(ref, DateMilliVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new DateMilliVector(field, allocator);
      }

      public TransferImpl(DateMilliVector to) {
         this.to = to;
      }

      public DateMilliVector getTo() {
         return this.to;
      }

      public void transfer() {
         DateMilliVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         DateMilliVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, DateMilliVector.this);
      }
   }
}
