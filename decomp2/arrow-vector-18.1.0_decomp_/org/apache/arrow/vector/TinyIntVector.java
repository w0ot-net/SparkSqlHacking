package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.TinyIntReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableTinyIntHolder;
import org.apache.arrow.vector.holders.TinyIntHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class TinyIntVector extends BaseFixedWidthVector implements BaseIntVector, ValueIterableVector {
   public static final byte TYPE_WIDTH = 1;

   public TinyIntVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.TINYINT.getType()), allocator);
   }

   public TinyIntVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public TinyIntVector(Field field, BufferAllocator allocator) {
      super(field, allocator, 1);
   }

   protected FieldReader getReaderImpl() {
      return new TinyIntReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.TINYINT;
   }

   public byte get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getByte((long)(index * 1));
      }
   }

   public void get(int index, NullableTinyIntHolder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getByte((long)(index * 1));
      }
   }

   public Byte getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getByte((long)(index * 1));
   }

   private void setValue(int index, int value) {
      this.valueBuffer.setByte((long)(index * 1), value);
   }

   private void setValue(int index, byte value) {
      this.valueBuffer.setByte((long)(index * 1), value);
   }

   public void set(int index, int value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, byte value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableTinyIntHolder holder) throws IllegalArgumentException {
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

   public void set(int index, TinyIntHolder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, int value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, byte value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableTinyIntHolder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, TinyIntHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, byte value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, byte value) {
      this.handleSafe(index);
      this.set(index, isSet, value);
   }

   public static byte get(ArrowBuf buffer, int index) {
      return buffer.getByte((long)(index * 1));
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((TinyIntVector)to);
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
      TinyIntVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new TinyIntVector(ref, TinyIntVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new TinyIntVector(field, allocator);
      }

      public TransferImpl(TinyIntVector to) {
         this.to = to;
      }

      public TinyIntVector getTo() {
         return this.to;
      }

      public void transfer() {
         TinyIntVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         TinyIntVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, TinyIntVector.this);
      }
   }
}
