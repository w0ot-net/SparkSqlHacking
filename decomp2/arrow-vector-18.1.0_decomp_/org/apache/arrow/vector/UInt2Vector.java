package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.complex.impl.UInt2ReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableUInt2Holder;
import org.apache.arrow.vector.holders.UInt2Holder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.util.ValueVectorUtility;

public final class UInt2Vector extends BaseFixedWidthVector implements BaseIntVector, ValueIterableVector {
   public static final char MAX_UINT2 = '\uffff';
   public static final byte TYPE_WIDTH = 2;

   public UInt2Vector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.UINT2.getType()), allocator);
   }

   public UInt2Vector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public UInt2Vector(Field field, BufferAllocator allocator) {
      super(field, allocator, 2);
   }

   protected FieldReader getReaderImpl() {
      return new UInt2ReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.UINT2;
   }

   public static char get(ArrowBuf buffer, int index) {
      return buffer.getChar((long)index * 2L);
   }

   public char get(int index) throws IllegalStateException {
      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         throw new IllegalStateException("Value at index is null");
      } else {
         return this.valueBuffer.getChar((long)index * 2L);
      }
   }

   public void get(int index, NullableUInt2Holder holder) {
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.value = this.valueBuffer.getChar((long)index * 2L);
      }
   }

   public Character getObject(int index) {
      return this.isSet(index) == 0 ? null : this.valueBuffer.getChar((long)index * 2L);
   }

   private void setValue(int index, int value) {
      this.valueBuffer.setChar((long)index * 2L, value);
   }

   private void setValue(int index, char value) {
      this.valueBuffer.setChar((long)index * 2L, value);
   }

   public void set(int index, int value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, char value) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, value);
   }

   public void set(int index, NullableUInt2Holder holder) throws IllegalArgumentException {
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

   public void set(int index, UInt2Holder holder) {
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setValue(index, holder.value);
   }

   public void setSafe(int index, int value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, char value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void setSafe(int index, NullableUInt2Holder holder) throws IllegalArgumentException {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void setSafe(int index, UInt2Holder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, int isSet, char value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, char value) {
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
      return new TransferImpl((UInt2Vector)to);
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

   public String toString() {
      return ValueVectorUtility.getToString(this, 0, this.getValueCount(), (v, i) -> v.isNull(i) ? "null" : Integer.toString(v.get(i) & '\uffff'));
   }

   private class TransferImpl implements TransferPair {
      UInt2Vector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new UInt2Vector(ref, UInt2Vector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new UInt2Vector(field, allocator);
      }

      public TransferImpl(UInt2Vector to) {
         this.to = to;
      }

      public UInt2Vector getTo() {
         return this.to;
      }

      public void transfer() {
         UInt2Vector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         UInt2Vector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, UInt2Vector.this);
      }
   }
}
