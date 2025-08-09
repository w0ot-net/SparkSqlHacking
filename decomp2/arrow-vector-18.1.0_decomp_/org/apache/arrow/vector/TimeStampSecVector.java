package org.apache.arrow.vector;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.TimeStampSecReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableTimeStampSecHolder;
import org.apache.arrow.vector.holders.TimeStampSecHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.DateUtility;
import org.apache.arrow.vector.util.TransferPair;

public final class TimeStampSecVector extends TimeStampVector implements ValueIterableVector {
   public TimeStampSecVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.TIMESTAMPSEC.getType()), allocator);
   }

   public TimeStampSecVector(String name, FieldType fieldType, BufferAllocator allocator) {
      super(name, fieldType, allocator);
   }

   public TimeStampSecVector(Field field, BufferAllocator allocator) {
      super(field, allocator);
   }

   protected FieldReader getReaderImpl() {
      return new TimeStampSecReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.TIMESTAMPSEC;
   }

   public void get(int index, NullableTimeStampSecHolder holder) {
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
         long secs = this.valueBuffer.getLong((long)index * 8L);
         long millis = TimeUnit.SECONDS.toMillis(secs);
         return DateUtility.getLocalDateTimeFromEpochMilli(millis);
      }
   }

   public void set(int index, NullableTimeStampSecHolder holder) throws IllegalArgumentException {
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

   public void set(int index, TimeStampSecHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, NullableTimeStampSecHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, TimeStampSecHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      TimeStampSecVector to = new TimeStampSecVector(ref, this.field.getFieldType(), allocator);
      return new TimeStampVector.TransferImpl(to);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      TimeStampSecVector to = new TimeStampSecVector(field, allocator);
      return new TimeStampVector.TransferImpl(to);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TimeStampVector.TransferImpl((TimeStampSecVector)to);
   }
}
