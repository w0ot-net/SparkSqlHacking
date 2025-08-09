package org.apache.arrow.vector;

import java.math.BigInteger;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.UInt8ReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableUInt8Holder;
import org.apache.arrow.vector.holders.UInt8Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.util.ValueVectorUtility;

public final class UInt8Vector extends BaseFixedWidthVector implements BaseIntVector, ValueIterableVector {
   public static final long MAX_UINT8 = -1L;
   public static final byte TYPE_WIDTH = 8;
   private static final BigInteger SAFE_CONVERSION_MASK = new BigInteger("ffffffffffffffff", 16);

   public UInt8Vector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.UINT8.getType()), allocator);
   }

   public UInt8Vector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public UInt8Vector(Field field, BufferAllocator allocator) {
      super(field, allocator, 8);
   }

   protected FieldReader getReaderImpl() {
      return new UInt8ReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.UINT8;
   }

   public static BigInteger getNoOverflow(ArrowBuf buffer, int index) {
      BigInteger l = BigInteger.valueOf(buffer.getLong((long)index * 8L));
      return SAFE_CONVERSION_MASK.and(l);
   }

   public long get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getLong((long)index * 8L);
      }
   }

   public void get(int index, NullableUInt8Holder holder) {
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

   public BigInteger getObjectNoOverflow(int index) {
      return this.isSet(index) == 0 ? null : getNoOverflow(this.valueBuffer, index);
   }

   private void setValue(int index, long value) {
      this.valueBuffer.setLong((long)index * 8L, value);
   }

   public void set(int index, long value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableUInt8Holder holder) throws IllegalArgumentException {
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

   public void set(int index, UInt8Holder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, long value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableUInt8Holder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, UInt8Holder holder) {
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
      return new TransferImpl((UInt8Vector)to);
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

   public String toString() {
      return ValueVectorUtility.getToString(this, 0, this.getValueCount(), (v, i) -> v.getObjectNoOverflow(i));
   }

   private class TransferImpl implements TransferPair {
      UInt8Vector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new UInt8Vector(ref, UInt8Vector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new UInt8Vector(field, allocator);
      }

      public TransferImpl(UInt8Vector to) {
         this.to = to;
      }

      public UInt8Vector getTo() {
         return this.to;
      }

      public void transfer() {
         UInt8Vector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         UInt8Vector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, UInt8Vector.this);
      }
   }
}
