package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.ReusableBuffer;
import org.apache.arrow.vector.complex.impl.ViewVarCharReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableViewVarCharHolder;
import org.apache.arrow.vector.holders.ViewVarCharHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.Text;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.validate.ValidateUtil;

public final class ViewVarCharVector extends BaseVariableWidthViewVector implements ValueIterableVector {
   public ViewVarCharVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.VIEWVARCHAR.getType()), allocator);
   }

   public ViewVarCharVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public ViewVarCharVector(Field field, BufferAllocator allocator) {
      super(field, allocator);
   }

   protected FieldReader getReaderImpl() {
      return new ViewVarCharReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.VIEWVARCHAR;
   }

   public byte[] get(int index) {
      assert index >= 0;

      return NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0 ? null : this.getData(index);
   }

   public Text getObject(int index) {
      assert index >= 0;

      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         return null;
      } else {
         Text result = new Text();
         this.read(index, result);
         return result;
      }
   }

   public void read(int index, ReusableBuffer buffer) {
      this.getData(index, buffer);
   }

   public void get(int index, NullableViewVarCharHolder holder) {
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

   public void set(int index, ViewVarCharHolder holder) {
      int start = holder.start;
      int length = holder.end - start;
      this.setBytes(index, holder.buffer, start, length);
      this.lastSet = index;
   }

   public void setSafe(int index, ViewVarCharHolder holder) {
      int length = holder.end - holder.start;
      this.handleSafe(index, length);
      this.set(index, holder);
   }

   public void set(int index, NullableViewVarCharHolder holder) {
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

   public void setSafe(int index, NullableViewVarCharHolder holder) {
      int length = holder.end - holder.start;
      this.handleSafe(index, length);
      this.set(index, holder);
   }

   public void set(int index, Text text) {
      this.set(index, text.getBytes(), 0, (int)text.getLength());
   }

   public void setSafe(int index, Text text) {
      this.setSafe(index, text.getBytes(), 0, (int)text.getLength());
   }

   public void validateScalars() {
      for(int i = 0; i < this.getValueCount(); ++i) {
         byte[] value = this.get(i);
         if (value != null) {
            ValidateUtil.validateOrThrow(Text.validateUTF8NoThrow(value), "Non-UTF-8 data in VarCharVector at position " + i + ".");
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
      return new TransferImpl((ViewVarCharVector)to);
   }

   private class TransferImpl implements TransferPair {
      ViewVarCharVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new ViewVarCharVector(ref, ViewVarCharVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new ViewVarCharVector(field, allocator);
      }

      public TransferImpl(ViewVarCharVector to) {
         this.to = to;
      }

      public ViewVarCharVector getTo() {
         return this.to;
      }

      public void transfer() {
         ViewVarCharVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         ViewVarCharVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, ViewVarCharVector.this);
      }
   }
}
