package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.ReusableBuffer;
import org.apache.arrow.vector.complex.impl.LargeVarBinaryReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.LargeVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableLargeVarBinaryHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.TransferPair;

public final class LargeVarBinaryVector extends BaseLargeVariableWidthVector implements ValueIterableVector {
   public LargeVarBinaryVector(String name, BufferAllocator allocator) {
      this(name, FieldType.nullable(Types.MinorType.LARGEVARBINARY.getType()), allocator);
   }

   public LargeVarBinaryVector(String name, FieldType fieldType, BufferAllocator allocator) {
      this(new Field(name, fieldType, (List)null), allocator);
   }

   public LargeVarBinaryVector(Field field, BufferAllocator allocator) {
      super(field, allocator);
   }

   protected FieldReader getReaderImpl() {
      return new LargeVarBinaryReaderImpl(this);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.LARGEVARBINARY;
   }

   public byte[] get(int index) {
      assert index >= 0;

      if (this.isSet(index) == 0) {
         return null;
      } else {
         long startOffset = this.getStartOffset(index);
         long dataLength = this.getEndOffset(index) - startOffset;
         byte[] result = new byte[(int)dataLength];
         this.valueBuffer.getBytes(startOffset, result, 0, (int)dataLength);
         return result;
      }
   }

   public void read(int index, ReusableBuffer buffer) {
      long startOffset = this.getStartOffset(index);
      long dataLength = this.getEndOffset(index) - startOffset;
      buffer.set(this.valueBuffer, startOffset, dataLength);
   }

   public byte[] getObject(int index) {
      return this.get(index);
   }

   public void get(int index, NullableLargeVarBinaryHolder holder) {
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

   public void set(int index, LargeVarBinaryHolder holder) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int dataLength = (int)(holder.end - holder.start);
      long startOffset = this.getStartOffset(index);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)dataLength);
      this.valueBuffer.setBytes(startOffset, holder.buffer, holder.start, (long)dataLength);
      this.lastSet = index;
   }

   public void setSafe(int index, LargeVarBinaryHolder holder) {
      assert index >= 0;

      int dataLength = (int)(holder.end - holder.start);
      this.handleSafe(index, dataLength);
      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      long startOffset = this.getStartOffset(index);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)dataLength);
      this.valueBuffer.setBytes(startOffset, holder.buffer, holder.start, (long)dataLength);
      this.lastSet = index;
   }

   public void set(int index, NullableLargeVarBinaryHolder holder) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setValidityBit(this.validityBuffer, index, holder.isSet);
      long startOffset = this.getStartOffset(index);
      if (holder.isSet != 0) {
         int dataLength = (int)(holder.end - holder.start);
         this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)dataLength);
         this.valueBuffer.setBytes(startOffset, holder.buffer, holder.start, (long)dataLength);
      } else {
         this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset);
      }

      this.lastSet = index;
   }

   public void setSafe(int index, NullableLargeVarBinaryHolder holder) {
      assert index >= 0;

      if (holder.isSet != 0) {
         int dataLength = (int)(holder.end - holder.start);
         this.handleSafe(index, dataLength);
         this.fillHoles(index);
         long startOffset = this.getStartOffset(index);
         this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)dataLength);
         this.valueBuffer.setBytes(startOffset, holder.buffer, holder.start, (long)dataLength);
      } else {
         this.fillEmpties(index + 1);
      }

      BitVectorHelper.setValidityBit(this.validityBuffer, index, holder.isSet);
      this.lastSet = index;
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new TransferImpl((LargeVarBinaryVector)to);
   }

   private class TransferImpl implements TransferPair {
      LargeVarBinaryVector to;

      public TransferImpl(String ref, BufferAllocator allocator) {
         this.to = new LargeVarBinaryVector(ref, LargeVarBinaryVector.this.field.getFieldType(), allocator);
      }

      public TransferImpl(Field field, BufferAllocator allocator) {
         this.to = new LargeVarBinaryVector(field, allocator);
      }

      public TransferImpl(LargeVarBinaryVector to) {
         this.to = to;
      }

      public LargeVarBinaryVector getTo() {
         return this.to;
      }

      public void transfer() {
         LargeVarBinaryVector.this.transferTo(this.to);
      }

      public void splitAndTransfer(int startIndex, int length) {
         LargeVarBinaryVector.this.splitAndTransferTo(startIndex, length, this.to);
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         this.to.copyFromSafe(fromIndex, toIndex, LargeVarBinaryVector.this);
      }
   }
}
