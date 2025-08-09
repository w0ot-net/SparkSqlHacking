package org.apache.arrow.vector.complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.CommonUtil;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseValueVector;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueIterableVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.impl.NullableStructReaderImpl;
import org.apache.arrow.vector.complex.impl.NullableStructWriter;
import org.apache.arrow.vector.holders.ComplexHolder;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.TransferPair;

public class StructVector extends NonNullableStructVector implements FieldVector, ValueIterableVector {
   private final NullableStructReaderImpl reader = new NullableStructReaderImpl(this);
   private final NullableStructWriter writer = new NullableStructWriter(this);
   protected ArrowBuf validityBuffer;
   private int validityAllocationSizeInBytes;

   public static StructVector empty(String name, BufferAllocator allocator) {
      FieldType fieldType = FieldType.nullable(ArrowType.Struct.INSTANCE);
      return new StructVector(name, allocator, fieldType, (CallBack)null, AbstractStructVector.ConflictPolicy.CONFLICT_REPLACE, false);
   }

   public static StructVector emptyWithDuplicates(String name, BufferAllocator allocator) {
      FieldType fieldType = new FieldType(false, ArrowType.Struct.INSTANCE, (DictionaryEncoding)null, (Map)null);
      return new StructVector(name, allocator, fieldType, (CallBack)null, AbstractStructVector.ConflictPolicy.CONFLICT_APPEND, true);
   }

   public StructVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      super(name, (BufferAllocator)Preconditions.checkNotNull(allocator), fieldType, callBack);
      this.validityBuffer = allocator.getEmpty();
      this.validityAllocationSizeInBytes = BitVectorHelper.getValidityBufferSize(3970);
   }

   public StructVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack, AbstractStructVector.ConflictPolicy conflictPolicy, boolean allowConflictPolicyChanges) {
      super(name, (BufferAllocator)Preconditions.checkNotNull(allocator), fieldType, callBack, conflictPolicy, allowConflictPolicyChanges);
      this.validityBuffer = allocator.getEmpty();
      this.validityAllocationSizeInBytes = BitVectorHelper.getValidityBufferSize(3970);
   }

   public StructVector(Field field, BufferAllocator allocator, CallBack callBack) {
      super(field, (BufferAllocator)Preconditions.checkNotNull(allocator), callBack);
      this.validityBuffer = allocator.getEmpty();
      this.validityAllocationSizeInBytes = BitVectorHelper.getValidityBufferSize(3970);
   }

   public StructVector(Field field, BufferAllocator allocator, CallBack callBack, AbstractStructVector.ConflictPolicy conflictPolicy, boolean allowConflictPolicyChanges) {
      super(field, (BufferAllocator)Preconditions.checkNotNull(allocator), callBack, conflictPolicy, allowConflictPolicyChanges);
      this.validityBuffer = allocator.getEmpty();
      this.validityAllocationSizeInBytes = BitVectorHelper.getValidityBufferSize(3970);
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      if (ownBuffers.size() != 1) {
         throw new IllegalArgumentException("Illegal buffer count, expected 1, got: " + ownBuffers.size());
      } else {
         ArrowBuf bitBuffer = (ArrowBuf)ownBuffers.get(0);
         this.validityBuffer.getReferenceManager().release();
         this.validityBuffer = BitVectorHelper.loadValidityBuffer(fieldNode, bitBuffer, this.allocator);
         this.valueCount = fieldNode.getLength();
         this.validityAllocationSizeInBytes = LargeMemoryUtil.checkedCastToInt(this.validityBuffer.capacity());
      }
   }

   public List getFieldBuffers() {
      List<ArrowBuf> result = new ArrayList(1);
      this.setReaderAndWriterIndex();
      result.add(this.validityBuffer);
      return result;
   }

   private void setReaderAndWriterIndex() {
      this.validityBuffer.readerIndex(0L);
      this.validityBuffer.writerIndex((long)BitVectorHelper.getValidityBufferSize(this.valueCount));
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use getFieldBuffers");
   }

   public NullableStructReaderImpl getReader() {
      return this.reader;
   }

   public NullableStructWriter getWriter() {
      return this.writer;
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return new NullableStructTransferPair(this, new StructVector(this.name, allocator, this.field.getFieldType(), (CallBack)null, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new NullableStructTransferPair(this, (StructVector)to, false);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new NullableStructTransferPair(this, new StructVector(ref, allocator, this.field.getFieldType(), (CallBack)null, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return new NullableStructTransferPair(this, new StructVector(ref, allocator, this.field.getFieldType(), callBack, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new NullableStructTransferPair(this, new StructVector(field, allocator, (CallBack)null, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return new NullableStructTransferPair(this, new StructVector(field, allocator, callBack, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   private void splitAndTransferValidityBuffer(int startIndex, int length, StructVector target) {
      int firstByteSource = BitVectorHelper.byteIndex(startIndex);
      int lastByteSource = BitVectorHelper.byteIndex(this.valueCount - 1);
      int byteSizeTarget = BitVectorHelper.getValidityBufferSize(length);
      int offset = startIndex % 8;
      if (length > 0) {
         if (offset == 0) {
            if (target.validityBuffer != null) {
               target.validityBuffer.getReferenceManager().release();
            }

            target.validityBuffer = this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
            target.validityBuffer.getReferenceManager().retain(1);
         } else {
            target.allocateValidityBuffer((long)byteSizeTarget);

            for(int i = 0; i < byteSizeTarget - 1; ++i) {
               byte b1 = BitVectorHelper.getBitsFromCurrentByte(this.validityBuffer, firstByteSource + i, offset);
               byte b2 = BitVectorHelper.getBitsFromNextByte(this.validityBuffer, firstByteSource + i + 1, offset);
               target.validityBuffer.setByte((long)i, b1 + b2);
            }

            if (firstByteSource + byteSizeTarget - 1 < lastByteSource) {
               byte b1 = BitVectorHelper.getBitsFromCurrentByte(this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
               byte b2 = BitVectorHelper.getBitsFromNextByte(this.validityBuffer, firstByteSource + byteSizeTarget, offset);
               target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1 + b2);
            } else {
               byte b1 = BitVectorHelper.getBitsFromCurrentByte(this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
               target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1);
            }
         }
      }

   }

   private int getValidityBufferValueCapacity() {
      return LargeMemoryUtil.checkedCastToInt(this.validityBuffer.capacity() * 8L);
   }

   public int getValueCapacity() {
      return Math.min(this.getValidityBufferValueCapacity(), super.getValueCapacity());
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      this.setReaderAndWriterIndex();
      ArrowBuf[] buffers;
      if (this.getBufferSize() == 0) {
         buffers = new ArrowBuf[0];
      } else {
         List<ArrowBuf> list = new ArrayList();
         list.add(this.validityBuffer);
         list.addAll(Arrays.asList(super.getBuffers(false)));
         buffers = (ArrowBuf[])list.toArray(new ArrowBuf[list.size()]);
      }

      if (clear) {
         for(ArrowBuf buffer : buffers) {
            buffer.getReferenceManager().retain();
         }

         this.clear();
      }

      return buffers;
   }

   public void close() {
      this.clearValidityBuffer();
      super.close();
   }

   public void clear() {
      this.clearValidityBuffer();
      super.clear();
   }

   public void reset() {
      super.reset();
      this.validityBuffer.setZero(0L, this.validityBuffer.capacity());
   }

   private void clearValidityBuffer() {
      this.validityBuffer.getReferenceManager().release();
      this.validityBuffer = this.allocator.getEmpty();
   }

   public int getBufferSize() {
      return this.valueCount == 0 ? 0 : super.getBufferSize() + BitVectorHelper.getValidityBufferSize(this.valueCount);
   }

   public int getBufferSizeFor(int valueCount) {
      return valueCount == 0 ? 0 : super.getBufferSizeFor(valueCount) + BitVectorHelper.getValidityBufferSize(valueCount);
   }

   public void setInitialCapacity(int numRecords) {
      this.validityAllocationSizeInBytes = BitVectorHelper.getValidityBufferSize(numRecords);
      super.setInitialCapacity(numRecords);
   }

   public void setInitialCapacity(int numRecords, double density) {
      this.validityAllocationSizeInBytes = BitVectorHelper.getValidityBufferSize(numRecords);
      super.setInitialCapacity(numRecords, density);
   }

   public boolean allocateNewSafe() {
      boolean success = false;

      try {
         this.clear();
         this.allocateValidityBuffer((long)this.validityAllocationSizeInBytes);
         success = super.allocateNewSafe();
      } finally {
         if (!success) {
            this.clear();
         }

      }

      return success;
   }

   private void allocateValidityBuffer(long size) {
      int curSize = (int)size;
      this.validityBuffer = this.allocator.buffer((long)curSize);
      this.validityBuffer.readerIndex(0L);
      this.validityAllocationSizeInBytes = curSize;
      this.validityBuffer.setZero(0L, this.validityBuffer.capacity());
   }

   public void reAlloc() {
      this.reallocValidityBuffer();
      super.reAlloc();
   }

   private void reallocValidityBuffer() {
      int currentBufferCapacity = LargeMemoryUtil.checkedCastToInt(this.validityBuffer.capacity());
      long newAllocationSize = this.getNewAllocationSize(currentBufferCapacity);
      ArrowBuf newBuf = this.allocator.buffer(newAllocationSize);
      newBuf.setBytes(0L, this.validityBuffer, 0L, (long)currentBufferCapacity);
      newBuf.setZero((long)currentBufferCapacity, newBuf.capacity() - (long)currentBufferCapacity);
      this.validityBuffer.getReferenceManager().release(1);
      this.validityBuffer = newBuf;
      this.validityAllocationSizeInBytes = (int)newAllocationSize;
   }

   private long getNewAllocationSize(int currentBufferCapacity) {
      long newAllocationSize = (long)currentBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.validityAllocationSizeInBytes > 0) {
            newAllocationSize = (long)this.validityAllocationSizeInBytes;
         } else {
            newAllocationSize = (long)BitVectorHelper.getValidityBufferSize(3970) * 2L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);

      assert newAllocationSize >= 1L;

      if (newAllocationSize > BaseValueVector.MAX_ALLOCATION_SIZE) {
         throw new OversizedAllocationException("Unable to expand the buffer");
      } else {
         return newAllocationSize;
      }
   }

   public long getValidityBufferAddress() {
      return this.validityBuffer.memoryAddress();
   }

   public long getDataBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public long getOffsetBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getValidityBuffer() {
      return this.validityBuffer;
   }

   public ArrowBuf getDataBuffer() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getOffsetBuffer() {
      throw new UnsupportedOperationException();
   }

   public Map getObject(int index) {
      return this.isSet(index) == 0 ? null : super.getObject(index);
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      return this.isSet(index) == 0 ? 0 : super.hashCode(index, hasher);
   }

   public void get(int index, ComplexHolder holder) {
      holder.isSet = this.isSet(index);
      if (holder.isSet == 0) {
         holder.reader = null;
      } else {
         super.get(index, holder);
      }
   }

   public int getNullCount() {
      return BitVectorHelper.getNullCount(this.validityBuffer, this.valueCount);
   }

   public boolean isNull(int index) {
      return this.isSet(index) == 0;
   }

   public int isSet(int index) {
      int byteIndex = index >> 3;
      byte b = this.validityBuffer.getByte((long)byteIndex);
      int bitIndex = index & 7;
      return b >> bitIndex & 1;
   }

   public void setIndexDefined(int index) {
      while(index >= this.getValidityBufferValueCapacity()) {
         this.reallocValidityBuffer();
      }

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
   }

   public void setNull(int index) {
      while(index >= this.getValidityBufferValueCapacity()) {
         this.reallocValidityBuffer();
      }

      BitVectorHelper.unsetBit(this.validityBuffer, index);
   }

   public void setValueCount(int valueCount) {
      Preconditions.checkArgument(valueCount >= 0);

      while(valueCount > this.getValidityBufferValueCapacity()) {
         this.reallocValidityBuffer();
      }

      super.setValueCount(valueCount);
      this.valueCount = valueCount;
   }

   protected class NullableStructTransferPair extends NonNullableStructVector.StructTransferPair {
      private StructVector target;

      protected NullableStructTransferPair(StructVector from, StructVector to, boolean allocate) {
         super(from, to, allocate);
         this.target = to;
      }

      public void transfer() {
         this.target.clear();
         this.target.validityBuffer = BaseValueVector.transferBuffer(StructVector.this.validityBuffer, this.target.allocator);
         super.transfer();
         StructVector.this.clear();
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         while(toIndex >= this.target.getValidityBufferValueCapacity()) {
            this.target.reallocValidityBuffer();
         }

         BitVectorHelper.setValidityBit(this.target.validityBuffer, toIndex, StructVector.this.isSet(fromIndex));
         super.copyValueSafe(fromIndex, toIndex);
      }

      public void splitAndTransfer(int startIndex, int length) {
         Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= StructVector.this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, StructVector.this.valueCount);
         this.target.clear();
         StructVector.this.splitAndTransferValidityBuffer(startIndex, length, this.target);
         super.splitAndTransfer(startIndex, length);
      }
   }
}
