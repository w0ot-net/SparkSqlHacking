package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.UInt1ReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.holders.UInt1Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.util.ValueVectorUtility;

public final class UInt1Vector extends BaseFixedWidthVector implements BaseIntVector, ValueIterableVector {
   public static final int PROMOTION_MASK = 255;
   public static final byte MAX_UINT1 = -1;
   public static final byte TYPE_WIDTH = 1;

   public UInt1Vector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.UINT1.getType()), allocator);
   }

   public UInt1Vector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public UInt1Vector(Field field, BufferAllocator allocator) {
      super(field, allocator, 1);
   }

   protected FieldReader getReaderImpl() {
      return new UInt1ReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.UINT1;
   }

   public static short getNoOverflow(ArrowBuf buffer, int index) {
      byte b = buffer.getByte((long)(index * 1));
      return (short)(255 & b);
   }

   public byte get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getByte((long)(index * 1));
      }
   }

   public void get(int index, NullableUInt1Holder holder) {
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

   public Short getObjectNoOverflow(int index) {
      return this.isSet(index) == 0 ? null : getNoOverflow(this.valueBuffer, index);
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

   public void set(int index, NullableUInt1Holder holder) throws IllegalArgumentException {
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

   public void set(int index, UInt1Holder holder) {
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

   public void setSafe(int index, NullableUInt1Holder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, UInt1Holder holder) {
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

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((UInt1Vector)to);
   }

   public void setWithPossibleTruncate(int index, long value) {
      this.setSafe(index, (int)value);
   }

   public void setUnsafeWithPossibleTruncate(int index, long value) {
      this.set(index, (int)value);
   }

   public long getValueAsLong(int index) {
      return (long)(this.get(index) & 255);
   }

   public String toString() {
      return ValueVectorUtility.getToString(this, 0, this.getValueCount(), (v, i) -> v.getObjectNoOverflow(i));
   }

   private class TransferImpl implements TransferPair {
      UInt1Vector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new UInt1Vector(ref, UInt1Vector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new UInt1Vector(field, allocator);
      }

      public TransferImpl(UInt1Vector to) {
         this.to = to;
      }

      public UInt1Vector getTo() {
         return this.to;
      }

      public void transfer() {
         UInt1Vector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         UInt1Vector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, UInt1Vector.this);
      }
   }
}
