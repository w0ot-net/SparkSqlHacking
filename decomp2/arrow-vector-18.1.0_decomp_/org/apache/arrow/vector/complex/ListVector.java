package org.apache.arrow.vector.complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.memory.util.ByteFunctionHelpers;
import org.apache.arrow.memory.util.CommonUtil;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.AddOrGetResult;
import org.apache.arrow.vector.BaseValueVector;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueIterableVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.ZeroVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.impl.ComplexCopier;
import org.apache.arrow.vector.complex.impl.UnionListReader;
import org.apache.arrow.vector.complex.impl.UnionListWriter;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.JsonStringArrayList;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.TransferPair;

public class ListVector extends BaseRepeatedValueVector implements PromotableVector, ValueIterableVector {
   protected ArrowBuf validityBuffer;
   protected UnionListReader reader;
   private CallBack callBack;
   protected Field field;
   protected int validityAllocationSizeInBytes;
   protected int lastSet;

   public static ListVector empty(String name, BufferAllocator allocator) {
      return new ListVector(name, allocator, FieldType.nullable(ArrowType.List.INSTANCE), (CallBack)null);
   }

   public ListVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      this(new Field(name, fieldType, (List)null), allocator, callBack);
   }

   public ListVector(Field field, BufferAllocator allocator, CallBack callBack) {
      super(field.getName(), allocator, callBack);
      this.validityBuffer = allocator.getEmpty();
      this.field = field;
      this.callBack = callBack;
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(3970);
      this.lastSet = -1;
   }

   public void initializeChildrenFromFields(List children) {
      Preconditions.checkArgument(children.size() == 1, "Lists have one child Field. Found: %s", children.isEmpty() ? "none" : children);
      Field field = (Field)children.get(0);
      AddOrGetResult<FieldVector> addOrGetVector = this.addOrGetVector(field.getFieldType());
      Preconditions.checkArgument(addOrGetVector.isCreated(), "Child vector already existed: %s", addOrGetVector.getVector());
      ((FieldVector)addOrGetVector.getVector()).initializeChildrenFromFields(field.getChildren());
      this.field = new Field(this.field.getName(), this.field.getFieldType(), children);
   }

   public void setInitialCapacity(int numRecords) {
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(numRecords);
      super.setInitialCapacity(numRecords);
   }

   public void setInitialCapacity(int numRecords, double density) {
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(numRecords);
      super.setInitialCapacity(numRecords, density);
   }

   public void setInitialTotalCapacity(int numRecords, int totalNumberOfElements) {
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(numRecords);
      super.setInitialTotalCapacity(numRecords, totalNumberOfElements);
   }

   public double getDensity() {
      if (this.valueCount == 0) {
         return (double)0.0F;
      } else {
         int startOffset = this.offsetBuffer.getInt(0L);
         int endOffset = this.offsetBuffer.getInt((long)(this.valueCount * 4));
         double totalListSize = (double)(endOffset - startOffset);
         return totalListSize / (double)this.valueCount;
      }
   }

   public List getChildrenFromFields() {
      return Collections.singletonList(this.getDataVector());
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      if (ownBuffers.size() != 2) {
         throw new IllegalArgumentException("Illegal buffer count, expected 2, got: " + ownBuffers.size());
      } else {
         ArrowBuf bitBuffer = (ArrowBuf)ownBuffers.get(0);
         ArrowBuf offBuffer = (ArrowBuf)ownBuffers.get(1);
         this.validityBuffer.getReferenceManager().release();
         this.validityBuffer = BitVectorHelper.loadValidityBuffer(fieldNode, bitBuffer, this.allocator);
         this.offsetBuffer.getReferenceManager().release();
         this.offsetBuffer = offBuffer.getReferenceManager().retain(offBuffer, this.allocator);
         this.validityAllocationSizeInBytes = LargeMemoryUtil.checkedCastToInt(this.validityBuffer.capacity());
         this.offsetAllocationSizeInBytes = this.offsetBuffer.capacity();
         this.lastSet = fieldNode.getLength() - 1;
         this.valueCount = fieldNode.getLength();
      }
   }

   public List getFieldBuffers() {
      List<ArrowBuf> result = new ArrayList(2);
      this.setReaderAndWriterIndex();
      result.add(this.validityBuffer);
      result.add(this.offsetBuffer);
      return result;
   }

   public void exportCDataBuffers(List buffers, ArrowBuf buffersPtr, long nullValue) {
      this.exportBuffer(this.validityBuffer, buffers, buffersPtr, nullValue, true);
      if (this.offsetBuffer.capacity() == 0L) {
         this.exportBuffer(this.allocateOffsetBuffer(4L), buffers, buffersPtr, nullValue, false);
      } else {
         this.exportBuffer(this.offsetBuffer, buffers, buffersPtr, nullValue, true);
      }

   }

   private void setReaderAndWriterIndex() {
      this.validityBuffer.readerIndex(0L);
      this.offsetBuffer.readerIndex(0L);
      if (this.valueCount == 0) {
         this.validityBuffer.writerIndex(0L);
         this.offsetBuffer.writerIndex(0L);
      } else {
         this.validityBuffer.writerIndex((long)getValidityBufferSizeFromCount(this.valueCount));
         this.offsetBuffer.writerIndex((long)((this.valueCount + 1) * 4));
      }

   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use getFieldBuffers");
   }

   public UnionListWriter getWriter() {
      return new UnionListWriter(this);
   }

   public void allocateNew() throws OutOfMemoryException {
      if (!this.allocateNewSafe()) {
         throw new OutOfMemoryException("Failure while allocating memory");
      }
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

   protected void allocateValidityBuffer(long size) {
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

   protected void reallocValidityAndOffsetBuffers() {
      this.reallocOffsetBuffer();
      this.reallocValidityBuffer();
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
            newAllocationSize = (long)getValidityBufferSizeFromCount(3970) * 2L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);

      assert newAllocationSize >= 1L;

      if (newAllocationSize > MAX_ALLOCATION_SIZE) {
         throw new OversizedAllocationException("Unable to expand the buffer");
      } else {
         return newAllocationSize;
      }
   }

   public void copyFromSafe(int inIndex, int outIndex, ValueVector from) {
      this.copyFrom(inIndex, outIndex, from);
   }

   public void copyFrom(int inIndex, int outIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      FieldReader in = from.getReader();
      in.setPosition(inIndex);
      FieldWriter out = this.getWriter();
      out.setPosition(outIndex);
      ComplexCopier.copy(in, out);
   }

   public FieldVector getDataVector() {
      return this.vector;
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return this.getTransferPair((String)ref, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return this.getTransferPair((Field)field, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return new TransferImpl(ref, allocator, callBack);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return new TransferImpl(field, allocator, callBack);
   }

   public TransferPair makeTransferPair(ValueVector target) {
      return new TransferImpl((ListVector)target);
   }

   public long getValidityBufferAddress() {
      return this.validityBuffer.memoryAddress();
   }

   public long getDataBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public long getOffsetBufferAddress() {
      return this.offsetBuffer.memoryAddress();
   }

   public ArrowBuf getValidityBuffer() {
      return this.validityBuffer;
   }

   public ArrowBuf getDataBuffer() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getOffsetBuffer() {
      return this.offsetBuffer;
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      if (this.isSet(index) == 0) {
         return 0;
      } else {
         int hash = 0;
         int start = this.offsetBuffer.getInt((long)(index * 4));
         int end = this.offsetBuffer.getInt((long)((index + 1) * 4));

         for(int i = start; i < end; ++i) {
            hash = ByteFunctionHelpers.combineHash(hash, this.vector.hashCode(i, hasher));
         }

         return hash;
      }
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   protected FieldReader getReaderImpl() {
      return new UnionListReader(this);
   }

   public UnionListReader getReader() {
      this.reader = (UnionListReader)super.getReader();
      return this.reader;
   }

   public AddOrGetResult addOrGetVector(FieldType fieldType) {
      AddOrGetResult<T> result = super.addOrGetVector(fieldType);
      this.invalidateReader();
      return result;
   }

   public int getBufferSize() {
      if (this.valueCount == 0) {
         return 0;
      } else {
         int offsetBufferSize = (this.valueCount + 1) * 4;
         int validityBufferSize = getValidityBufferSizeFromCount(this.valueCount);
         return offsetBufferSize + validityBufferSize + this.vector.getBufferSize();
      }
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         int validityBufferSize = getValidityBufferSizeFromCount(valueCount);
         return super.getBufferSizeFor(valueCount) + validityBufferSize;
      }
   }

   public Field getField() {
      if (this.field.getChildren().contains(this.getDataVector().getField())) {
         return this.field;
      } else {
         this.field = new Field(this.field.getName(), this.field.getFieldType(), Collections.singletonList(this.getDataVector().getField()));
         return this.field;
      }
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.LIST;
   }

   public void clear() {
      super.clear();
      this.validityBuffer = this.releaseBuffer(this.validityBuffer);
      this.lastSet = -1;
   }

   public void reset() {
      super.reset();
      this.validityBuffer.setZero(0L, this.validityBuffer.capacity());
      this.lastSet = -1;
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      this.setReaderAndWriterIndex();
      ArrowBuf[] buffers;
      if (this.getBufferSize() == 0) {
         buffers = new ArrowBuf[0];
      } else {
         List<ArrowBuf> list = new ArrayList();
         list.add(this.offsetBuffer);
         list.add(this.validityBuffer);
         list.addAll(Arrays.asList(this.vector.getBuffers(false)));
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

   public UnionVector promoteToUnion() {
      UnionVector vector = new UnionVector("$data$", this.allocator, (FieldType)null, this.callBack);
      this.replaceDataVector(vector);
      this.invalidateReader();
      if (this.callBack != null) {
         this.callBack.doWork();
      }

      return vector;
   }

   protected void invalidateReader() {
      this.reader = null;
   }

   public List getObject(int index) {
      if (this.isSet(index) == 0) {
         return null;
      } else {
         List<Object> vals = new JsonStringArrayList();
         int start = this.offsetBuffer.getInt((long)(index * 4));
         int end = this.offsetBuffer.getInt((long)((index + 1) * 4));
         ValueVector vv = this.getDataVector();

         for(int i = start; i < end; ++i) {
            vals.add(vv.getObject(i));
         }

         return vals;
      }
   }

   public boolean isNull(int index) {
      return this.isSet(index) == 0;
   }

   public boolean isEmpty(int index) {
      if (this.isNull(index)) {
         return true;
      } else {
         int start = this.offsetBuffer.getInt((long)(index * 4));
         int end = this.offsetBuffer.getInt((long)((index + 1) * 4));
         return start == end;
      }
   }

   public int isSet(int index) {
      int byteIndex = index >> 3;
      byte b = this.validityBuffer.getByte((long)byteIndex);
      int bitIndex = index & 7;
      return b >> bitIndex & 1;
   }

   public int getNullCount() {
      return BitVectorHelper.getNullCount(this.validityBuffer, this.valueCount);
   }

   public int getValueCapacity() {
      return this.getValidityAndOffsetValueCapacity();
   }

   private int getValidityAndOffsetValueCapacity() {
      int offsetValueCapacity = Math.max(this.getOffsetBufferValueCapacity() - 1, 0);
      return Math.min(offsetValueCapacity, this.getValidityBufferValueCapacity());
   }

   private int getValidityBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.validityBuffer.capacity() * 8L);
   }

   public void setNotNull(int index) {
      while(index >= this.getValidityAndOffsetValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.lastSet = index;
   }

   public void setNull(int index) {
      while(index >= this.getValidityAndOffsetValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      if (this.lastSet >= index) {
         this.lastSet = index - 1;
      }

      for(int i = this.lastSet + 1; i <= index; ++i) {
         int currentOffset = this.offsetBuffer.getInt((long)(i * 4));
         this.offsetBuffer.setInt((long)((i + 1) * 4), currentOffset);
      }

      BitVectorHelper.unsetBit(this.validityBuffer, index);
      this.lastSet = index;
   }

   public int startNewValue(int index) {
      while(index >= this.getValidityAndOffsetValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      if (this.lastSet >= index) {
         this.lastSet = index - 1;
      }

      for(int i = this.lastSet + 1; i <= index; ++i) {
         int currentOffset = this.offsetBuffer.getInt((long)(i * 4));
         this.offsetBuffer.setInt((long)((i + 1) * 4), currentOffset);
      }

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.lastSet = index;
      return this.offsetBuffer.getInt((long)((this.lastSet + 1) * 4));
   }

   public void endValue(int index, int size) {
      int currentOffset = this.offsetBuffer.getInt((long)((index + 1) * 4));
      this.offsetBuffer.setInt((long)((index + 1) * 4), currentOffset + size);
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;
      if (valueCount > 0) {
         while(valueCount > this.getValidityAndOffsetValueCapacity()) {
            this.reallocValidityAndOffsetBuffers();
         }

         for(int i = this.lastSet + 1; i < valueCount; ++i) {
            int currentOffset = this.offsetBuffer.getInt((long)(i * 4));
            this.offsetBuffer.setInt((long)((i + 1) * 4), currentOffset);
         }
      }

      int childValueCount = valueCount == 0 ? 0 : this.offsetBuffer.getInt((long)((this.lastSet + 1) * 4));
      this.vector.setValueCount(childValueCount);
   }

   public void setLastSet(int value) {
      this.lastSet = value;
   }

   public int getLastSet() {
      return this.lastSet;
   }

   public int getElementStartIndex(int index) {
      return this.offsetBuffer.getInt((long)(index * 4));
   }

   public int getElementEndIndex(int index) {
      return this.offsetBuffer.getInt((long)((index + 1) * 4));
   }

   private class TransferImpl implements TransferPair {
      ListVector to;
      TransferPair dataTransferPair;

      public TransferImpl(String name, BufferAllocator allocator, CallBack callBack) {
         this(new ListVector(name, allocator, ListVector.this.field.getFieldType(), callBack));
      }

      public TransferImpl(Field field, BufferAllocator allocator, CallBack callBack) {
         this(new ListVector(field, allocator, callBack));
      }

      public TransferImpl(ListVector to) {
         this.to = to;
         to.addOrGetVector(ListVector.this.vector.getField().getFieldType());
         if (to.getDataVector() instanceof ZeroVector) {
            to.addOrGetVector(ListVector.this.vector.getField().getFieldType());
         }

         this.dataTransferPair = ListVector.this.getDataVector().makeTransferPair(to.getDataVector());
      }

      public void transfer() {
         this.to.clear();
         this.dataTransferPair.transfer();
         this.to.validityBuffer = BaseValueVector.transferBuffer(ListVector.this.validityBuffer, this.to.allocator);
         this.to.offsetBuffer = BaseValueVector.transferBuffer(ListVector.this.offsetBuffer, this.to.allocator);
         this.to.lastSet = ListVector.this.lastSet;
         if (ListVector.this.valueCount > 0) {
            this.to.setValueCount(ListVector.this.valueCount);
         }

         ListVector.this.clear();
      }

      public void splitAndTransfer(int startIndex, int length) {
         Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= ListVector.this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, ListVector.this.valueCount);
         this.to.clear();
         if (length > 0) {
            int startPoint = ListVector.this.offsetBuffer.getInt((long)(startIndex * 4));
            int sliceLength = ListVector.this.offsetBuffer.getInt((long)((startIndex + length) * 4)) - startPoint;
            this.to.offsetBuffer = this.to.allocateOffsetBuffer((long)((length + 1) * 4));

            for(int i = 0; i < length + 1; ++i) {
               int relativeOffset = ListVector.this.offsetBuffer.getInt((long)((startIndex + i) * 4)) - startPoint;
               this.to.offsetBuffer.setInt((long)(i * 4), relativeOffset);
            }

            this.splitAndTransferValidityBuffer(startIndex, length, this.to);
            this.dataTransferPair.splitAndTransfer(startPoint, sliceLength);
            this.to.lastSet = length - 1;
            this.to.setValueCount(length);
         }

      }

      private void splitAndTransferValidityBuffer(int startIndex, int length, ListVector target) {
         int firstByteSource = BitVectorHelper.byteIndex(startIndex);
         int lastByteSource = BitVectorHelper.byteIndex(ListVector.this.valueCount - 1);
         int byteSizeTarget = ListVector.getValidityBufferSizeFromCount(length);
         int offset = startIndex % 8;
         if (length > 0) {
            if (offset == 0) {
               if (target.validityBuffer != null) {
                  target.validityBuffer.getReferenceManager().release();
               }

               target.validityBuffer = ListVector.this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
               target.validityBuffer.getReferenceManager().retain(1);
            } else {
               target.allocateValidityBuffer((long)byteSizeTarget);

               for(int i = 0; i < byteSizeTarget - 1; ++i) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(ListVector.this.validityBuffer, firstByteSource + i, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(ListVector.this.validityBuffer, firstByteSource + i + 1, offset);
                  target.validityBuffer.setByte((long)i, b1 + b2);
               }

               if (firstByteSource + byteSizeTarget - 1 < lastByteSource) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(ListVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(ListVector.this.validityBuffer, firstByteSource + byteSizeTarget, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1 + b2);
               } else {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(ListVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1);
               }
            }
         }

      }

      public ValueVector getTo() {
         return this.to;
      }

      public void copyValueSafe(int from, int to) {
         this.to.copyFrom(from, to, ListVector.this);
      }
   }
}
