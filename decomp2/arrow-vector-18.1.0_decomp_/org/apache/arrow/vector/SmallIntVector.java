package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.SmallIntReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableSmallIntHolder;
import org.apache.arrow.vector.holders.SmallIntHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class SmallIntVector extends BaseFixedWidthVector implements BaseIntVector, ValueIterableVector {
   public static final byte TYPE_WIDTH = 2;

   public SmallIntVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.SMALLINT.getType()), allocator);
   }

   public SmallIntVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public SmallIntVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 2);
   }

   protected FieldReader getReaderImpl() {
      return new SmallIntReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.SMALLINT;
   }

   public short get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getShort((long)index * 2L);
      }
   }

   public void get(int index, NullableSmallIntHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getShort((long)index * 2L);
      }
   }

   public Short getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getShort((long)index * 2L);
   }

   private void setValue(int index, int value) {
      this.valueBuffer.setShort((long)index * 2L, value);
   }

   private void setValue(int index, short value) {
      this.valueBuffer.setShort((long)index * 2L, value);
   }

   public void set(int index, int value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, short value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableSmallIntHolder holder) throws IllegalArgumentException {
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

   public void set(int index, SmallIntHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, int value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, short value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableSmallIntHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, SmallIntHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, short value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, short value) {
      this.handleSafe(index);
      this.set(index, isSet, value);
   }

   public static short get(ArrowBuf buffer, int index) {
      return buffer.getShort((long)index * 2L);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((SmallIntVector)to);
   }

   public void setWithPossibleTruncate(int index, long value) {
      this.setSafe(index, (int)value);
   }

   public void setUnsafeWithPossibleTruncate(int index, long value) {
      this.set(index, (int)value);
   }

   public long getValueAsLong(int index) {
      return (long)this.get(index);
   }

   private class TransferImpl implements TransferPair {
      SmallIntVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new SmallIntVector(ref, SmallIntVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new SmallIntVector(field, allocator);
      }

      public TransferImpl(SmallIntVector to) {
         this.to = to;
      }

      public SmallIntVector getTo() {
         return this.to;
      }

      public void transfer() {
         SmallIntVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         SmallIntVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, SmallIntVector.this);
      }
   }
}
