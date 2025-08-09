package org.apache.arrow.vector;

import java.time.Duration;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.DurationReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.DurationHolder;
import org.apache.arrow.vector.holders.NullableDurationHolder;
import org.apache.arrow.vector.types.TimeUnit;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class DurationVector extends BaseFixedWidthVector implements ValueIterableVector {
   public static final byte TYPE_WIDTH = 8;
   private final TimeUnit unit;

   public DurationVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public DurationVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 8);
      this.unit = ((ArrowType.Duration)field.getFieldType().getType()).getUnit();
   }

   protected FieldReader getReaderImpl() {
      return new DurationReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.DURATION;
   }

   public static long get(ArrowBuf buffer, int index) {
      return buffer.getLong((long)index * 8L);
   }

   public ArrowBuf get(int index) throws IllegalStateException {
      return NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0 ? null : this.valueBuffer.slice((long)index * 8L, 8L);
   }

   public void get(int index, NullableDurationHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = get(this.valueBuffer, index);
         holder.unit = this.unit;
      }
   }

   public Duration getObject(int index) {
      return this.isSet(index) == 0 ? null : this.getObjectNotNull(index);
   }

   public Duration getObjectNotNull(int index) {
      long value = get(this.valueBuffer, index);
      return toDuration(value, this.unit);
   }

   public static Duration toDuration(long value, TimeUnit unit) {
      switch (unit) {
         case SECOND:
            return Duration.ofSeconds(value);
         case MILLISECOND:
            return Duration.ofMillis(value);
         case NANOSECOND:
            return Duration.ofNanos(value);
         case MICROSECOND:
            return Duration.ofNanos(java.util.concurrent.TimeUnit.MICROSECONDS.toNanos(value));
         default:
            throw new IllegalArgumentException("Unknown timeunit: " + String.valueOf(unit));
      }
   }

   public StringBuilder getAsStringBuilder(int index) {
      return this.isSet(index) == 0 ? null : this.getAsStringBuilderHelper(index);
   }

   private StringBuilder getAsStringBuilderHelper(int index) {
      return new StringBuilder(this.getObject(index).toString());
   }

   public TimeUnit getUnit() {
      return this.unit;
   }

   public void set(int index, ArrowBuf value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 8L, value, 0L, 8L);
   }

   public void set(int index, long value) {
      long offsetIndex = (long)index * 8L;
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setLong(offsetIndex, value);
   }

   public void set(int index, NullableDurationHolder holder) throws IllegalArgumentException {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException();
      } else if (!this.unit.equals(holder.unit)) {
         throw new IllegalArgumentException(String.format("holder.unit: %s not equal to vector unit: %s", holder.unit, this.unit));
      } else {
         if (holder.isSet > 0) {
            this.set(index, holder.value);
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void set(int index, DurationHolder holder) {
      if (!this.unit.equals(holder.unit)) {
         throw new IllegalArgumentException(String.format("holder.unit: %s not equal to vector unit: %s", holder.unit, this.unit));
      } else {
         this.set(index, holder.value);
      }
   }

   public void setSafe(int index, ArrowBuf value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, long value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableDurationHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, DurationHolder holder) {
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

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((DurationVector)to);
   }

   private class TransferImpl implements TransferPair {
      DurationVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new DurationVector(ref, DurationVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new DurationVector(field, allocator);
      }

      public TransferImpl(DurationVector to) {
         this.to = to;
      }

      public DurationVector getTo() {
         return this.to;
      }

      public void transfer() {
         DurationVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         DurationVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, DurationVector.this);
      }
   }
}
