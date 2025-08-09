package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.ReusableBuffer;
import org.apache.arrow.vector.complex.impl.VarCharReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.NullableVarCharHolder;
import org.apache.arrow.vector.holders.VarCharHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.Text;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.validate.ValidateUtil;

public final class VarCharVector extends BaseVariableWidthVector implements ValueIterableVector {
   public VarCharVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.VARCHAR.getType()), allocator);
   }

   public VarCharVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public VarCharVector(Field field, BufferAllocator allocator) {
      super(field, allocator);
   }

   protected FieldReader getReaderImpl() {
      return new VarCharReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.VARCHAR;
   }

   public byte[] get(int index) {
      assert index >= 0;

      if (NullCheckingForGet.NULL_CHECKING_ENABLED && this.isSet(index) == 0) {
         return null;
      } else {
         int startOffset = this.getStartOffset(index);
         int dataLength = this.getEndOffset(index) - startOffset;
         byte[] result = new byte[dataLength];
         this.valueBuffer.getBytes((long)startOffset, result, 0, dataLength);
         return result;
      }
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
      int startOffset = this.getStartOffset(index);
      int dataLength = this.getEndOffset(index) - startOffset;
      buffer.set(this.valueBuffer, (long)startOffset, (long)dataLength);
   }

   public void get(int index, NullableVarCharHolder holder) {
      assert index >= 0;

      if (this.isSet(index) == 0) {
         holder.isSet = 0;
      } else {
         holder.isSet = 1;
         holder.start = this.getStartOffset(index);
         holder.end = this.getEndOffset(index);
         holder.buffer = this.valueBuffer;
      }
   }

   public void set(int index, VarCharHolder holder) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int dataLength = holder.end - holder.start;
      int startOffset = this.getStartOffset(index);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + dataLength);
      this.valueBuffer.setBytes((long)startOffset, holder.buffer, (long)holder.start, (long)dataLength);
      this.lastSet = index;
   }

   public void setSafe(int index, VarCharHolder holder) {
      assert index >= 0;

      int dataLength = holder.end - holder.start;
      this.handleSafe(index, dataLength);
      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int startOffset = this.getStartOffset(index);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + dataLength);
      this.valueBuffer.setBytes((long)startOffset, holder.buffer, (long)holder.start, (long)dataLength);
      this.lastSet = index;
   }

   public void set(int index, NullableVarCharHolder holder) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setValidityBit(this.validityBuffer, index, holder.isSet);
      int startOffset = this.getStartOffset(index);
      if (holder.isSet != 0) {
         int dataLength = holder.end - holder.start;
         this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + dataLength);
         this.valueBuffer.setBytes((long)startOffset, holder.buffer, (long)holder.start, (long)dataLength);
      } else {
         this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset);
      }

      this.lastSet = index;
   }

   public void setSafe(int index, NullableVarCharHolder holder) {
      assert index >= 0;

      if (holder.isSet != 0) {
         int dataLength = holder.end - holder.start;
         this.handleSafe(index, dataLength);
         this.fillHoles(index);
         int startOffset = this.getStartOffset(index);
         this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + dataLength);
         this.valueBuffer.setBytes((long)startOffset, holder.buffer, (long)holder.start, (long)dataLength);
      } else {
         this.fillEmpties(index + 1);
      }

      BitVectorHelper.setValidityBit(this.validityBuffer, index, holder.isSet);
      this.lastSet = index;
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
      return new TransferImpl((VarCharVector)to);
   }

   private class TransferImpl implements TransferPair {
      VarCharVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new VarCharVector(ref, VarCharVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new VarCharVector(field, allocator);
      }

      public TransferImpl(VarCharVector to) {
         this.to = to;
      }

      public VarCharVector getTo() {
         return this.to;
      }

      public void transfer() {
         VarCharVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         VarCharVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, VarCharVector.this);
      }
   }
}
