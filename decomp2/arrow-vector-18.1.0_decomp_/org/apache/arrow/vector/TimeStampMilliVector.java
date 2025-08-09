package org.apache.arrow.vector;

import java.time.LocalDateTime;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.TimeStampMilliReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableTimeStampMilliHolder;
import org.apache.arrow.vector.holders.TimeStampMilliHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.DateUtility;
import org.apache.arrow.vector.util.TransferPair;

public final class TimeStampMilliVector extends TimeStampVector implements ValueIterableVector {
   public TimeStampMilliVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.TIMESTAMPMILLI.getType()), allocator);
   }

   public TimeStampMilliVector(String name, FieldType fieldType, BufferAllocator allocator) {
      super(name, fieldType, allocator);
   }

   public TimeStampMilliVector(Field field, BufferAllocator allocator) {
      super(field, allocator);
   }

   protected FieldReader getReaderImpl() {
      return new TimeStampMilliReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.TIMESTAMPMILLI;
   }

   public void get(int index, NullableTimeStampMilliHolder holder) {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
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

   public void set(int index, NullableTimeStampMilliHolder holder) throws IllegalArgumentException {
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

   public void set(int index, TimeStampMilliHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, NullableTimeStampMilliHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, TimeStampMilliHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      TimeStampMilliVector to = new TimeStampMilliVector(ref, this.field.getFieldType(), allocator);
      return new TimeStampVector.TransferImpl(to);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      TimeStampMilliVector to = new TimeStampMilliVector(field, allocator);
      return new TimeStampVector.TransferImpl(to);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TimeStampVector.TransferImpl((TimeStampMilliVector)to);
   }
}
