package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.UInt4ReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableUInt4Holder;
import org.apache.arrow.vector.holders.UInt4Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.util.ValueVectorUtility;

public final class UInt4Vector extends BaseFixedWidthVector implements BaseIntVector, ValueIterableVector {
   public static final long PROMOTION_MASK = 4294967295L;
   public static final int MAX_UINT4 = -1;
   public static final byte TYPE_WIDTH = 4;

   public UInt4Vector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.UINT4.getType()), allocator);
   }

   public UInt4Vector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public UInt4Vector(Field field, BufferAllocator allocator) {
      super(field, allocator, 4);
   }

   protected FieldReader getReaderImpl() {
      return new UInt4ReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.UINT4;
   }

   public static long getNoOverflow(ArrowBuf buffer, int index) {
      long l = (long)buffer.getInt((long)index * 4L);
      return 4294967295L & l;
   }

   public int get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getInt((long)index * 4L);
      }
   }

   public void get(int index, NullableUInt4Holder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getInt((long)index * 4L);
      }
   }

   public Integer getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getInt((long)index * 4L);
   }

   public Long getObjectNoOverflow(int index) {
      return this.isSet(index) == 0 ? null : getNoOverflow(this.valueBuffer, index);
   }

   private void setValue(int index, int value) {
      this.valueBuffer.setInt((long)index * 4L, value);
   }

   public void set(int index, int value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableUInt4Holder holder) throws IllegalArgumentException {
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

   public void set(int index, UInt4Holder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, int value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableUInt4Holder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, UInt4Holder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, int value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, int value) {
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
      return new TransferImpl((UInt4Vector)to);
   }

   public void setWithPossibleTruncate(int index, long value) {
      this.setSafe(index, (int)value);
   }

   public void setUnsafeWithPossibleTruncate(int index, long value) {
      this.set(index, (int)value);
   }

   public long getValueAsLong(int index) {
      return (long)this.get(index) & 4294967295L;
   }

   public String toString() {
      return ValueVectorUtility.getToString(this, 0, this.getValueCount(), (v, i) -> v.getObjectNoOverflow(i));
   }

   private class TransferImpl implements TransferPair {
      UInt4Vector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new UInt4Vector(ref, UInt4Vector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new UInt4Vector(field, allocator);
      }

      public TransferImpl(UInt4Vector to) {
         this.to = to;
      }

      public UInt4Vector getTo() {
         return this.to;
      }

      public void transfer() {
         UInt4Vector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         UInt4Vector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, UInt4Vector.this);
      }
   }
}
