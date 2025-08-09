package org.apache.arrow.vector;

import java.time.Duration;
import java.time.Period;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.IntervalMonthDayNanoReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.IntervalMonthDayNanoHolder;
import org.apache.arrow.vector.holders.NullableIntervalMonthDayNanoHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class IntervalMonthDayNanoVector extends BaseFixedWidthVector implements ValueIterableVector {
   public static final byte TYPE_WIDTH = 16;
   private static final byte DAY_OFFSET = 4;
   private static final byte NANOSECOND_OFFSET = 8;

   public IntervalMonthDayNanoVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.INTERVALMONTHDAYNANO.getType()), allocator);
   }

   public IntervalMonthDayNanoVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public IntervalMonthDayNanoVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 16);
   }

   protected FieldReader getReaderImpl() {
      return new IntervalMonthDayNanoReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.INTERVALMONTHDAYNANO;
   }

   public static int getMonths(ArrowBuf buffer, int index) {
      return buffer.getInt((long)index * 16L);
   }

   public static int getDays(ArrowBuf buffer, int index) {
      return buffer.getInt((long)index * 16L + 4L);
   }

   public static long getNanoseconds(ArrowBuf buffer, int index) {
      return buffer.getLong((long)index * 16L + 8L);
   }

   public ArrowBuf get(int index) throws IllegalStateException {
      return NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0 ? null : this.valueBuffer.slice((long)index * 16L, 16L);
   }

   public void get(int index, NullableIntervalMonthDayNanoHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         long startIndex = (long)index * 16L;
         holder.isSet = 1;
         holder.months = this.valueBuffer.getInt(startIndex);
         holder.days = this.valueBuffer.getInt(startIndex + 4L);
         holder.nanoseconds = this.valueBuffer.getLong(startIndex + 8L);
      }
   }

   public PeriodDuration getObject(int index) {
      if (this.isSet(index) == 0) {
         return null;
      } else {
         long startIndex = (long)index * 16L;
         int months = this.valueBuffer.getInt(startIndex);
         int days = this.valueBuffer.getInt(startIndex + 4L);
         long nanoseconds = this.valueBuffer.getLong(startIndex + 8L);
         return new PeriodDuration(Period.ofMonths(months).plusDays((long)days), Duration.ofNanos(nanoseconds));
      }
   }

   public StringBuilder getAsStringBuilder(int index) {
      return this.isSet(index) == 0 ? null : this.getAsStringBuilderHelper(index);
   }

   private StringBuilder getAsStringBuilderHelper(int index) {
      return (new StringBuilder()).append(this.getObject(index).toString()).append(" ");
   }

   public void set(int index, ArrowBuf value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 16L, value, 0L, 16L);
   }

   public void set(int index, int months, int days, long nanoseconds) {
      long offsetIndex = (long)index * 16L;
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setInt(offsetIndex, months);
      this.valueBuffer.setInt(offsetIndex + 4L, days);
      this.valueBuffer.setLong(offsetIndex + 8L, nanoseconds);
   }

   public void set(int index, NullableIntervalMonthDayNanoHolder holder) throws IllegalArgumentException {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException();
      } else {
         if (holder.isSet > 0) {
            this.set(index, holder.months, holder.days, holder.nanoseconds);
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void set(int index, IntervalMonthDayNanoHolder holder) {
      this.set(index, holder.months, holder.days, holder.nanoseconds);
   }

   public void setSafe(int index, ArrowBuf value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, int months, int days, long nanoseconds) {
      this.handleSafe(index);
      this.set(index, months, days, nanoseconds);
   }

   public void setSafe(int index, NullableIntervalMonthDayNanoHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, IntervalMonthDayNanoHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, int months, int days, long nanoseconds) {
      if (isSet > 0) {
         this.set(index, months, days, nanoseconds);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, int months, int days, long nanoseconds) {
      this.handleSafe(index);
      this.set(index, isSet, months, days, nanoseconds);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((IntervalMonthDayNanoVector)to);
   }

   private class TransferImpl implements TransferPair {
      IntervalMonthDayNanoVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new IntervalMonthDayNanoVector(ref, IntervalMonthDayNanoVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new IntervalMonthDayNanoVector(field, allocator);
      }

      public TransferImpl(IntervalMonthDayNanoVector to) {
         this.to = to;
      }

      public IntervalMonthDayNanoVector getTo() {
         return this.to;
      }

      public void transfer() {
         IntervalMonthDayNanoVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         IntervalMonthDayNanoVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, IntervalMonthDayNanoVector.this);
      }
   }
}
