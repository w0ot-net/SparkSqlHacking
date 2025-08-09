package org.apache.arrow.vector;

import java.time.Duration;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.IntervalDayReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.IntervalDayHolder;
import org.apache.arrow.vector.holders.NullableIntervalDayHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class IntervalDayVector extends BaseFixedWidthVector implements ValueIterableVector {
   public static final byte TYPE_WIDTH = 8;
   private static final byte MILLISECOND_OFFSET = 4;

   public IntervalDayVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.INTERVALDAY.getType()), allocator);
   }

   public IntervalDayVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public IntervalDayVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 8);
   }

   protected FieldReader getReaderImpl() {
      return new IntervalDayReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.INTERVALDAY;
   }

   public static int getDays(ArrowBuf buffer, int index) {
      return buffer.getInt((long)index * 8L);
   }

   public static int getMilliseconds(ArrowBuf buffer, int index) {
      return buffer.getInt((long)index * 8L + 4L);
   }

   public ArrowBuf get(int index) throws IllegalStateException {
      return NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0 ? null : this.valueBuffer.slice((long)index * 8L, 8L);
   }

   public void get(int index, NullableIntervalDayHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         long startIndex = (long)index * 8L;
         holder.isSet = 1;
         holder.days = this.valueBuffer.getInt(startIndex);
         holder.milliseconds = this.valueBuffer.getInt(startIndex + 4L);
      }
   }

   public Duration getObject(int index) {
      return this.isSet(index) == 0 ? null : this.getObjectNotNull(index);
   }

   public Duration getObjectNotNull(int index) {
      long startIndex = (long)index * 8L;
      int days = this.valueBuffer.getInt(startIndex);
      int milliseconds = this.valueBuffer.getInt(startIndex + 4L);
      return Duration.ofDays((long)days).plusMillis((long)milliseconds);
   }

   public StringBuilder getAsStringBuilder(int index) {
      return this.isSet(index) == 0 ? null : this.getAsStringBuilderHelper(index);
   }

   private StringBuilder getAsStringBuilderHelper(int index) {
      long startIndex = (long)index * 8L;
      int days = this.valueBuffer.getInt(startIndex);
      int millis = this.valueBuffer.getInt(startIndex + 4L);
      int hours = millis / 3600000;
      millis %= 3600000;
      int minutes = millis / '\uea60';
      millis %= 60000;
      int seconds = millis / 1000;
      millis %= 1000;
      String dayString = Math.abs(days) == 1 ? " day " : " days ";
      return (new StringBuilder()).append(days).append(dayString).append(hours).append(":").append(minutes).append(":").append(seconds).append(".").append(millis);
   }

   public void set(int index, ArrowBuf value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * 8L, value, 0L, 8L);
   }

   public void set(int index, int days, int milliseconds) {
      long offsetIndex = (long)index * 8L;
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setInt(offsetIndex, days);
      this.valueBuffer.setInt(offsetIndex + 4L, milliseconds);
   }

   public void set(int index, NullableIntervalDayHolder holder) throws IllegalArgumentException {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException();
      } else {
         if (holder.isSet > 0) {
            this.set(index, holder.days, holder.milliseconds);
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void set(int index, IntervalDayHolder holder) {
      this.set(index, holder.days, holder.milliseconds);
   }

   public void setSafe(int index, ArrowBuf value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, int days, int milliseconds) {
      this.handleSafe(index);
      this.set(index, days, milliseconds);
   }

   public void setSafe(int index, NullableIntervalDayHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, IntervalDayHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, int days, int milliseconds) {
      if (isSet > 0) {
         this.set(index, days, milliseconds);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, int days, int milliseconds) {
      this.handleSafe(index);
      this.set(index, isSet, days, milliseconds);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((IntervalDayVector)to);
   }

   private class TransferImpl implements TransferPair {
      IntervalDayVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new IntervalDayVector(ref, IntervalDayVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new IntervalDayVector(field, allocator);
      }

      public TransferImpl(IntervalDayVector to) {
         this.to = to;
      }

      public IntervalDayVector getTo() {
         return this.to;
      }

      public void transfer() {
         IntervalDayVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         IntervalDayVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, IntervalDayVector.this);
      }
   }
}
