package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.BigIntReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.BigIntHolder;
import org.apache.arrow.vector.holders.NullableBigIntHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class BigIntVector extends BaseFixedWidthVector implements BaseIntVector, ValueIterableVector {
   public static final byte TYPE_WIDTH = 8;

   public BigIntVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.BIGINT.getType()), allocator);
   }

   public BigIntVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public BigIntVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 8);
   }

   protected FieldReader getReaderImpl() {
      return new BigIntReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.BIGINT;
   }

   public long get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getLong((long)index * 8L);
      }
   }

   public void get(int index, NullableBigIntHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getLong((long)index * 8L);
      }
   }

   public Long getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getLong((long)index * 8L);
   }

   private void setValue(int index, long value) {
      this.valueBuffer.setLong((long)index * 8L, value);
   }

   public void set(int index, long value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableBigIntHolder holder) throws IllegalArgumentException {
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

   public void set(int index, BigIntHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, long value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableBigIntHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, BigIntHolder holder) {
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
      return new TransferImpl((BigIntVector)to);
   }

   public void setWithPossibleTruncate(int index, long value) {
      this.setSafe(index, value);
   }

   public void setUnsafeWithPossibleTruncate(int index, long value) {
      this.set(index, value);
   }

   public long getValueAsLong(int index) {
      return this.get(index);
   }

   private class TransferImpl implements TransferPair {
      BigIntVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new BigIntVector(ref, BigIntVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new BigIntVector(field, allocator);
      }

      public TransferImpl(BigIntVector to) {
         this.to = to;
      }

      public BigIntVector getTo() {
         return this.to;
      }

      public void transfer() {
         BigIntVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         BigIntVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, BigIntVector.this);
      }
   }
}
