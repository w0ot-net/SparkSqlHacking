package org.apache.arrow.vector;

import java.time.LocalDateTime;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.TimeStampNanoReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableTimeStampNanoHolder;
import org.apache.arrow.vector.holders.TimeStampNanoHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.DateUtility;
import org.apache.arrow.vector.util.TransferPair;

public final class TimeStampNanoVector extends TimeStampVector implements ValueIterableVector {
   public TimeStampNanoVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.TIMESTAMPNANO.getType()), allocator);
   }

   public TimeStampNanoVector(String name, FieldType fieldType, BufferAllocator allocator) {
      super(name, fieldType, allocator);
   }

   public TimeStampNanoVector(Field field, BufferAllocator allocator) {
      super(field, allocator);
   }

   protected FieldReader getReaderImpl() {
      return new TimeStampNanoReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.TIMESTAMPNANO;
   }

   public void get(int index, NullableTimeStampNanoHolder holder) {
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
         long nanos = this.valueBuffer.getLong((long)index * 8L);
         return DateUtility.getLocalDateTimeFromEpochNano(nanos);
      }
   }

   public void set(int index, NullableTimeStampNanoHolder holder) throws IllegalArgumentException {
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

   public void set(int index, TimeStampNanoHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, NullableTimeStampNanoHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, TimeStampNanoHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      TimeStampNanoVector to = new TimeStampNanoVector(ref, this.field.getFieldType(), allocator);
      return new TimeStampVector.TransferImpl(to);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      TimeStampNanoVector to = new TimeStampNanoVector(field, allocator);
      return new TimeStampVector.TransferImpl(to);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TimeStampVector.TransferImpl((TimeStampNanoVector)to);
   }
}
