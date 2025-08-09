package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.ReusableBuffer;
import org.apache.arrow.vector.complex.impl.ViewVarBinaryReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableViewVarBinaryHolder;
import org.apache.arrow.vector.holders.ViewVarBinaryHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class ViewVarBinaryVector extends BaseVariableWidthViewVector implements ValueIterableVector {
   public ViewVarBinaryVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.VIEWVARBINARY.getType()), allocator);
   }

   public ViewVarBinaryVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public ViewVarBinaryVector(Field field, BufferAllocator allocator) {
      super(field, allocator);
   }

   protected FieldReader getReaderImpl() {
      return new ViewVarBinaryReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.VIEWVARBINARY;
   }

   public byte[] get(int index) {
      assert index >= 0;

      return NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0 ? null : this.getData(index);
   }

   public void read(int index, ReusableBuffer buffer) {
      this.getData(index, buffer);
   }

   public byte[] getObject(int index) {
      return this.get(index);
   }

   public void get(int index, NullableViewVarBinaryHolder holder) {
      int dataLength = this.getValueLength(index);
      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         if (dataLength > 12) {
            int bufferIndex = this.viewBuffer.getInt((long)index * 16L + 4L + 4L);
            int dataOffset = this.viewBuffer.getInt((long)index * 16L + 4L + 4L + 4L);
            holder.buffer = (ArrowBuf)this.dataBuffers.get(bufferIndex);
            holder.start = dataOffset;
            holder.end = dataOffset + dataLength;
         } else {
            long dataOffset = (long)index * 16L + 4L;
            holder.buffer = this.viewBuffer;
            holder.start = (int)dataOffset;
            holder.end = (int)dataOffset + dataLength;
         }

      }
   }

   public void set(int index, ViewVarBinaryHolder holder) {
      int start = holder.start;
      int length = holder.end - start;
      this.setBytes(index, holder.buffer, start, length);
      this.lastSet = index;
   }

   public void setSafe(int index, ViewVarBinaryHolder holder) {
      int length = holder.end - holder.start;
      this.handleSafe(index, length);
      this.set(index, holder);
   }

   public void set(int index, NullableViewVarBinaryHolder holder) {
      if (holder.isSet == 0) {
         this.setNull(index);
      } else {
         BitVectorHelper.setBit(this.validityBuffer, (long)index);
         int start = holder.start;
         int length = holder.end - start;
         this.setBytes(index, holder.buffer, start, length);
      }

      this.lastSet = index;
   }

   public void setSafe(int index, NullableViewVarBinaryHolder holder) {
      int length = holder.end - holder.start;
      this.handleSafe(index, length);
      this.set(index, holder);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((ViewVarBinaryVector)to);
   }

   private class TransferImpl implements TransferPair {
      ViewVarBinaryVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new ViewVarBinaryVector(ref, ViewVarBinaryVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new ViewVarBinaryVector(field, allocator);
      }

      public TransferImpl(ViewVarBinaryVector to) {
         this.to = to;
      }

      public ViewVarBinaryVector getTo() {
         return this.to;
      }

      public void transfer() {
         ViewVarBinaryVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         ViewVarBinaryVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, ViewVarBinaryVector.this);
      }
   }
}
