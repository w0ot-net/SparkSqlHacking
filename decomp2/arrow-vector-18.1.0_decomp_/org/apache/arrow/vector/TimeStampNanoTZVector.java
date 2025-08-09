package org.apache.arrow.vector;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.TimeStampNanoTZReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableTimeStampNanoTZHolder;
import org.apache.arrow.vector.holders.TimeStampNanoTZHolder;
import org.apache.arrow.vector.types.TimeUnit;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class TimeStampNanoTZVector extends TimeStampVector implements ValueIterableVector {
   private final String timeZone;

   public TimeStampNanoTZVector(String name, BufferAllocator allocator, String timeZone) {
      this(name, FieldType.nullable(new ArrowType.Timestamp(TimeUnit.NANOSECOND, timeZone)), allocator);
   }

   public TimeStampNanoTZVector(String name, FieldType fieldType, BufferAllocator allocator) {
      super(name, fieldType, allocator);
      ArrowType.Timestamp arrowType = (ArrowType.Timestamp)fieldType.getType();
      this.timeZone = arrowType.getTimezone();
   }

   public TimeStampNanoTZVector(Field field, BufferAllocator allocator) {
      super(field, allocator);
      ArrowType.Timestamp arrowType = (ArrowType.Timestamp)field.getFieldType().getType();
      this.timeZone = arrowType.getTimezone();
   }

   protected FieldReader getReaderImpl() {
      return new TimeStampNanoTZReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.TIMESTAMPNANOTZ;
   }

   public String getTimeZone() {
      return this.timeZone;
   }

   public void get(int index, NullableTimeStampNanoTZHolder holder) {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getLong((long)index * 8L);
         holder.timezone = this.timeZone;
      }
   }

   public Long getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getLong((long)index * 8L);
   }

   public void set(int index, NullableTimeStampNanoTZHolder holder) throws IllegalArgumentException {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException();
      } else if (!this.timeZone.equals(holder.timezone)) {
         throw new IllegalArgumentException(String.format("holder.timezone: %s not equal to vector timezone: %s", holder.timezone, this.timeZone));
      } else {
         if (holder.isSet > 0) {
            BitVectorHelper.setBit(this.validityBuffer, (long)index);
            this.setValue(index, holder.value);
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void set(int index, TimeStampNanoTZHolder holder) {
      if (!this.timeZone.equals(holder.timezone)) {
         throw new IllegalArgumentException(String.format("holder.timezone: %s not equal to vector timezone: %s", holder.timezone, this.timeZone));
      } else {
         BitVectorHelper.setBit(this.validityBuffer, (long)index);
         this.setValue(index, holder.value);
      }
   }

   public void setSafe(int index, NullableTimeStampNanoTZHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, TimeStampNanoTZHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      TimeStampNanoTZVector to = new TimeStampNanoTZVector(ref, this.field.getFieldType(), allocator);
      return new TimeStampVector.TransferImpl(to);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      TimeStampNanoTZVector to = new TimeStampNanoTZVector(field, allocator);
      return new TimeStampVector.TransferImpl(to);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TimeStampVector.TransferImpl((TimeStampNanoTZVector)to);
   }
}
