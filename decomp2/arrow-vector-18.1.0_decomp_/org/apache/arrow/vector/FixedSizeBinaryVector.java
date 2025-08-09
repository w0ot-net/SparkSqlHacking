package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.ReusableBuffer;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.complex.impl.FixedSizeBinaryReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.FixedSizeBinaryHolder;
import org.apache.arrow.vector.holders.NullableFixedSizeBinaryHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.validate.ValidateUtil;

public class FixedSizeBinaryVector extends BaseFixedWidthVector implements ValueIterableVector {
   private final int byteWidth;

   public FixedSizeBinaryVector(String name, BufferAllocator allocator, int byteWidth) {
      this(name, FieldType.nullable(new ArrowType.FixedSizeBinary(byteWidth)), allocator);
   }

   public FixedSizeBinaryVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public FixedSizeBinaryVector(Field field, BufferAllocator allocator) {
      super(field, allocator, ((ArrowType.FixedSizeBinary)field.getFieldType().getType()).getByteWidth());
      this.byteWidth = ((ArrowType.FixedSizeBinary)field.getFieldType().getType()).getByteWidth();
   }

   protected FieldReader getReaderImpl() {
      return new FixedSizeBinaryReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.FIXEDSIZEBINARY;
   }

   public byte[] get(int index) {
      assert index >= 0;

      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         return null;
      } else {
         byte[] dst = new byte[this.byteWidth];
         this.valueBuffer.getBytes((long)index * (long)this.byteWidth, dst, 0, this.byteWidth);
         return dst;
      }
   }

   public void read(int index, ReusableBuffer buffer) {
      int startOffset = index * this.byteWidth;
      buffer.set(this.valueBuffer, (long)startOffset, (long)this.byteWidth);
   }

   public void get(int index, NullableFixedSizeBinaryHolder holder) {
      assert index >= 0;

      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.buffer = this.valueBuffer.slice((long)index * (long)this.byteWidth, (long)this.byteWidth);
         holder.byteWidth = this.byteWidth;
      }
   }

   public byte[] getObject(int index) {
      return this.get(index);
   }

   public int getByteWidth() {
      return this.byteWidth;
   }

   public void set(int index, byte[] value) {
      assert index >= 0;

      Preconditions.checkNotNull(value, "expecting a valid byte array");

      assert this.byteWidth <= value.length;

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * (long)this.byteWidth, value, 0, (long)this.byteWidth);
   }

   public void setSafe(int index, byte[] value) {
      this.handleSafe(index);
      this.set(index, value);
   }

   public void set(int index, int isSet, byte[] value) {
      if (isSet > 0) {
         this.set(index, value);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, byte[] value) {
      this.handleSafe(index);
      this.set(index, isSet, value);
   }

   public void set(int index, ArrowBuf buffer) {
      assert index >= 0;

      assert (long)this.byteWidth <= buffer.capacity();

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.valueBuffer.setBytes((long)index * (long)this.byteWidth, buffer, 0L, (long)this.byteWidth);
   }

   public void setSafe(int index, ArrowBuf buffer) {
      this.handleSafe(index);
      this.set(index, buffer);
   }

   public void set(int index, int isSet, ArrowBuf buffer) {
      if (isSet > 0) {
         this.set(index, buffer);
      } else {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      }

   }

   public void setSafe(int index, int isSet, ArrowBuf buffer) {
      this.handleSafe(index);
      this.set(index, isSet, buffer);
   }

   public void set(int index, FixedSizeBinaryHolder holder) {
      if (this.byteWidth != holder.byteWidth) {
         throw new IllegalArgumentException(String.format("holder.byteWidth: %d not equal to vector byteWidth: %d", holder.byteWidth, this.byteWidth));
      } else {
         this.set(index, holder.buffer);
      }
   }

   public void setSafe(int index, FixedSizeBinaryHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public void set(int index, NullableFixedSizeBinaryHolder holder) {
      if (holder.isSet < 0) {
         throw new IllegalArgumentException("holder has a negative isSet value");
      } else if (this.byteWidth != holder.byteWidth) {
         throw new IllegalArgumentException(String.format("holder.byteWidth: %d not equal to vector byteWidth: %d", holder.byteWidth, this.byteWidth));
      } else {
         if (holder.isSet > 0) {
            this.set(index, holder.buffer);
         } else {
            BitVectorHelper.unsetBit(this.validityBuffer, index);
         }

      }
   }

   public void setSafe(int index, NullableFixedSizeBinaryHolder holder) {
      this.handleSafe(index);
      this.set(index, holder);
   }

   public static byte[] get(ArrowBuf buffer, int index, int byteWidth) {
      byte[] dst = new byte[byteWidth];
      buffer.getBytes((long)index * (long)byteWidth, dst, 0, byteWidth);
      return dst;
   }

   public void validateScalars() {
      for(int i = 0; i < this.getValueCount(); ++i) {
         byte[] value = this.get(i);
         if (value != null) {
            ValidateUtil.validateOrThrow(value.length == this.byteWidth, "Invalid value for FixedSizeBinaryVector at position " + i + ". The length was " + value.length + " but the length of each element should be " + this.byteWidth + ".");
         }
      }

   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((FixedSizeBinaryVector)to);
   }

   private class TransferImpl implements TransferPair {
      FixedSizeBinaryVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new FixedSizeBinaryVector(ref, allocator, FixedSizeBinaryVector.this.byteWidth);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new FixedSizeBinaryVector(field, allocator);
      }

      public TransferImpl(FixedSizeBinaryVector to) {
         this.to = to;
      }

      public FixedSizeBinaryVector getTo() {
         return this.to;
      }

      public void transfer() {
         FixedSizeBinaryVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         FixedSizeBinaryVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, FixedSizeBinaryVector.this);
      }
   }
}
