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
import org.apache.arrow.vector.complex.impl.UnionListViewReader;
import org.apache.arrow.vector.complex.impl.UnionListViewWriter;
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

public class ListViewVector extends BaseRepeatedValueViewVector implements PromotableVector, ValueIterableVector {
   protected ArrowBuf validityBuffer;
   protected UnionListViewReader reader;
   private CallBack callBack;
   protected Field field;
   protected int validityAllocationSizeInBytes;

   public static ListViewVector empty(String name, BufferAllocator allocator) {
      return new ListViewVector(name, allocator, FieldType.nullable(ArrowType.ListView.INSTANCE), (CallBack)null);
   }

   public ListViewVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      this(new Field(name, fieldType, (List)null), allocator, callBack);
   }

   public ListViewVector(Field field, BufferAllocator allocator, CallBack callBack) {
      super(field.getName(), allocator, callBack);
      this.validityBuffer = allocator.getEmpty();
      this.field = field;
      this.callBack = callBack;
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(3970);
   }

   public void initializeChildrenFromFields(List children) {
      Preconditions.checkArgument(children.size() == 1, "ListViews have one child Field. Found: %s", children.isEmpty() ? "none" : children);
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

   public List getChildrenFromFields() {
      return Collections.singletonList(this.getDataVector());
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      if (ownBuffers.size() != 3) {
         throw new IllegalArgumentException("Illegal buffer count, expected 3, got: " + ownBuffers.size());
      } else {
         ArrowBuf bitBuffer = (ArrowBuf)ownBuffers.get(0);
         ArrowBuf offBuffer = (ArrowBuf)ownBuffers.get(1);
         ArrowBuf szBuffer = (ArrowBuf)ownBuffers.get(2);
         this.validityBuffer.getReferenceManager().release();
         this.validityBuffer = BitVectorHelper.loadValidityBuffer(fieldNode, bitBuffer, this.allocator);
         this.offsetBuffer.getReferenceManager().release();
         this.offsetBuffer = offBuffer.getReferenceManager().retain(offBuffer, this.allocator);
         this.sizeBuffer.getReferenceManager().release();
         this.sizeBuffer = szBuffer.getReferenceManager().retain(szBuffer, this.allocator);
         this.validityAllocationSizeInBytes = LargeMemoryUtil.checkedCastToInt(this.validityBuffer.capacity());
         this.offsetAllocationSizeInBytes = this.offsetBuffer.capacity();
         this.sizeAllocationSizeInBytes = this.sizeBuffer.capacity();
         this.valueCount = fieldNode.getLength();
      }
   }

   private void setReaderAndWriterIndex() {
      this.validityBuffer.readerIndex(0L);
      this.offsetBuffer.readerIndex(0L);
      this.sizeBuffer.readerIndex(0L);
      if (this.valueCount == 0) {
         this.validityBuffer.writerIndex(0L);
         this.offsetBuffer.writerIndex(0L);
         this.sizeBuffer.writerIndex(0L);
      } else {
         this.validityBuffer.writerIndex((long)getValidityBufferSizeFromCount(this.valueCount));
         this.offsetBuffer.writerIndex((long)(this.valueCount * 4));
         this.sizeBuffer.writerIndex((long)(this.valueCount * 4));
      }

   }

   public List getFieldBuffers() {
      List<ArrowBuf> result = new ArrayList(2);
      this.setReaderAndWriterIndex();
      result.add(this.validityBuffer);
      result.add(this.offsetBuffer);
      result.add(this.sizeBuffer);
      return result;
   }

   public void exportCDataBuffers(List buffers, ArrowBuf buffersPtr, long nullValue) {
      this.exportBuffer(this.validityBuffer, buffers, buffersPtr, nullValue, true);
      this.exportBuffer(this.offsetBuffer, buffers, buffersPtr, nullValue, true);
      this.exportBuffer(this.sizeBuffer, buffers, buffersPtr, nullValue, true);
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

   protected void reallocValidityAndSizeAndOffsetBuffers() {
      this.reallocateBuffers();
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

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
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
      return new TransferImpl((ListViewVector)target);
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

   public ArrowBuf getSizeBuffer() {
      return this.sizeBuffer;
   }

   public long getSizeBufferAddress() {
      return this.sizeBuffer.memoryAddress();
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
         int end = this.sizeBuffer.getInt((long)(index * 4));

         for(int i = start; i < end; ++i) {
            hash = ByteFunctionHelpers.combineHash(hash, this.vector.hashCode(i, hasher));
         }

         return hash;
      }
   }

   protected FieldReader getReaderImpl() {
      return new UnionListViewReader(this);
   }

   public UnionListViewReader getReader() {
      this.reader = (UnionListViewReader)super.getReader();
      return this.reader;
   }

   public int getBufferSize() {
      if (this.valueCount == 0) {
         return 0;
      } else {
         int offsetBufferSize = this.valueCount * 4;
         int sizeBufferSize = this.valueCount * 4;
         int validityBufferSize = getValidityBufferSizeFromCount(this.valueCount);
         return offsetBufferSize + sizeBufferSize + validityBufferSize + this.vector.getBufferSize();
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
      return Types.MinorType.LISTVIEW;
   }

   public void clear() {
      super.clear();
      this.validityBuffer = this.releaseBuffer(this.validityBuffer);
   }

   public void reset() {
      super.reset();
      this.validityBuffer.setZero(0L, this.validityBuffer.capacity());
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      this.setReaderAndWriterIndex();
      ArrowBuf[] buffers;
      if (this.getBufferSize() == 0) {
         buffers = new ArrowBuf[0];
      } else {
         List<ArrowBuf> list = new ArrayList();
         list.add(this.validityBuffer);
         list.add(this.offsetBuffer);
         list.add(this.sizeBuffer);
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

   public List getObject(int index) {
      if (this.isSet(index) == 0) {
         return null;
      } else {
         List<Object> vals = new JsonStringArrayList();
         int start = this.offsetBuffer.getInt((long)(index * 4));
         int end = start + this.sizeBuffer.getInt((long)(index * 4));
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
         return this.sizeBuffer.getInt((long)(index * 4)) == 0;
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

   private int getValidityAndSizeValueCapacity() {
      int offsetValueCapacity = Math.max(this.getOffsetBufferValueCapacity(), 0);
      int sizeValueCapacity = Math.max(this.getSizeBufferValueCapacity(), 0);
      return Math.min(offsetValueCapacity, sizeValueCapacity);
   }

   private int getValidityAndOffsetValueCapacity() {
      int offsetValueCapacity = Math.max(this.getOffsetBufferValueCapacity(), 0);
      return Math.min(offsetValueCapacity, this.getValidityBufferValueCapacity());
   }

   private int getValidityBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.validityBuffer.capacity() * 8L);
   }

   public void setNull(int index) {
      while(index >= this.getValidityAndSizeValueCapacity()) {
         this.reallocValidityAndSizeAndOffsetBuffers();
      }

      this.offsetBuffer.setInt((long)(index * 4), 0);
      this.sizeBuffer.setInt((long)(index * 4), 0);
      BitVectorHelper.unsetBit(this.validityBuffer, index);
   }

   public int startNewValue(int index) {
      while(index >= this.getValidityAndSizeValueCapacity()) {
         this.reallocValidityAndSizeAndOffsetBuffers();
      }

      if (index > 0) {
         int prevOffset = this.getMaxViewEndChildVectorByIndex(index);
         this.offsetBuffer.setInt((long)(index * 4), prevOffset);
      }

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      return this.offsetBuffer.getInt((long)(index * 4));
   }

   private void validateInvariants(int offset, int size) {
      if (offset < 0) {
         throw new IllegalArgumentException("Offset cannot be negative");
      } else if (size < 0) {
         throw new IllegalArgumentException("Size cannot be negative");
      } else if (offset > this.vector.getValueCount()) {
         throw new IllegalArgumentException("Offset is out of bounds.");
      } else if (offset + size > this.vector.getValueCount()) {
         throw new IllegalArgumentException("Offset + size <= length of the child array.");
      }
   }

   public void setOffset(int index, int value) {
      this.validateInvariants(value, this.sizeBuffer.getInt((long)(index * 4)));
      this.offsetBuffer.setInt((long)(index * 4), value);
   }

   public void setSize(int index, int value) {
      this.validateInvariants(this.offsetBuffer.getInt((long)(index * 4)), value);
      this.sizeBuffer.setInt((long)(index * 4), value);
   }

   public void setValidity(int index, int value) {
      if (value == 0) {
         BitVectorHelper.unsetBit(this.validityBuffer, index);
      } else {
         BitVectorHelper.setBit(this.validityBuffer, (long)index);
      }

   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;
      if (valueCount > 0) {
         while(valueCount > this.getValidityAndSizeValueCapacity()) {
            this.reallocValidityAndSizeAndOffsetBuffers();
         }
      }

      int childValueCount = valueCount == 0 ? 0 : this.getMaxViewEndChildVector();
      this.vector.setValueCount(childValueCount);
   }

   public int getElementStartIndex(int index) {
      return this.offsetBuffer.getInt((long)(index * 4));
   }

   public int getElementEndIndex(int index) {
      return this.sizeBuffer.getInt((long)(index * 4));
   }

   public AddOrGetResult addOrGetVector(FieldType fieldType) {
      AddOrGetResult<T> result = super.addOrGetVector(fieldType);
      this.invalidateReader();
      return result;
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

   private void invalidateReader() {
      this.reader = null;
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use getFieldBuffers");
   }

   public UnionListViewWriter getWriter() {
      return new UnionListViewWriter(this);
   }

   public int getValueCount() {
      return this.valueCount;
   }

   public double getDensity() {
      if (this.valueCount == 0) {
         return (double)0.0F;
      } else {
         double totalListSize = (double)this.getMaxViewEndChildVector();
         return totalListSize / (double)this.valueCount;
      }
   }

   public void validate() {
      for(int i = 0; i < this.valueCount; ++i) {
         int offset = this.offsetBuffer.getInt((long)(i * 4));
         int size = this.sizeBuffer.getInt((long)(i * 4));
         this.validateInvariants(offset, size);
      }

   }

   public void endValue(int index, int size) {
      this.sizeBuffer.setInt((long)(index * 4), size);
   }

   private class TransferImpl implements TransferPair {
      ListViewVector to;
      TransferPair dataTransferPair;

      public TransferImpl(String name, BufferAllocator allocator, CallBack callBack) {
         this(new ListViewVector(name, allocator, ListViewVector.this.field.getFieldType(), callBack));
      }

      public TransferImpl(Field field, BufferAllocator allocator, CallBack callBack) {
         this(new ListViewVector(field, allocator, callBack));
      }

      public TransferImpl(ListViewVector to) {
         this.to = to;
         to.addOrGetVector(ListViewVector.this.vector.getField().getFieldType());
         if (to.getDataVector() instanceof ZeroVector) {
            to.addOrGetVector(ListViewVector.this.vector.getField().getFieldType());
         }

         this.dataTransferPair = ListViewVector.this.getDataVector().makeTransferPair(to.getDataVector());
      }

      public void transfer() {
         this.to.clear();
         this.dataTransferPair.transfer();
         this.to.validityBuffer = BaseValueVector.transferBuffer(ListViewVector.this.validityBuffer, this.to.allocator);
         this.to.offsetBuffer = BaseValueVector.transferBuffer(ListViewVector.this.offsetBuffer, this.to.allocator);
         this.to.sizeBuffer = BaseValueVector.transferBuffer(ListViewVector.this.sizeBuffer, this.to.allocator);
         if (ListViewVector.this.valueCount > 0) {
            this.to.setValueCount(ListViewVector.this.valueCount);
         }

         ListViewVector.this.clear();
      }

      public void splitAndTransfer(int startIndex, int length) {
         Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= ListViewVector.this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, ListViewVector.this.valueCount);
         this.to.clear();
         if (length > 0) {
            ListViewVector.this.offsetBuffer.getInt((long)startIndex * 4L);
            this.to.offsetBuffer = this.to.allocateBuffers((long)length * 4L);
            this.to.sizeBuffer = this.to.allocateBuffers((long)length * 4L);
            int maxOffsetAndSizeSum = -1;
            int minOffsetValue = -1;

            for(int i = 0; i < length; ++i) {
               int offsetValue = ListViewVector.this.offsetBuffer.getInt((long)(startIndex + i) * 4L);
               int sizeValue = ListViewVector.this.sizeBuffer.getInt((long)(startIndex + i) * 4L);
               this.to.sizeBuffer.setInt((long)i * 4L, sizeValue);
               if (maxOffsetAndSizeSum < offsetValue + sizeValue) {
                  maxOffsetAndSizeSum = offsetValue + sizeValue;
               }

               if (minOffsetValue == -1 || minOffsetValue > offsetValue) {
                  minOffsetValue = offsetValue;
               }
            }

            for(int i = 0; i < length; ++i) {
               int offsetValue = ListViewVector.this.offsetBuffer.getInt((long)(startIndex + i) * 4L);
               int relativeOffset = offsetValue - minOffsetValue;
               this.to.offsetBuffer.setInt((long)i * 4L, relativeOffset);
            }

            this.splitAndTransferValidityBuffer(startIndex, length, this.to);
            int childSliceLength = maxOffsetAndSizeSum - minOffsetValue;
            this.dataTransferPair.splitAndTransfer(minOffsetValue, childSliceLength);
            this.to.setValueCount(length);
         }

      }

      private void splitAndTransferValidityBuffer(int startIndex, int length, ListViewVector target) {
         int firstByteSource = BitVectorHelper.byteIndex(startIndex);
         int lastByteSource = BitVectorHelper.byteIndex(ListViewVector.this.valueCount - 1);
         int byteSizeTarget = ListViewVector.getValidityBufferSizeFromCount(length);
         int offset = startIndex % 8;
         if (length > 0) {
            if (offset == 0) {
               if (target.validityBuffer != null) {
                  target.validityBuffer.getReferenceManager().release();
               }

               target.validityBuffer = ListViewVector.this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
               target.validityBuffer.getReferenceManager().retain(1);
            } else {
               target.allocateValidityBuffer((long)byteSizeTarget);

               for(int i = 0; i < byteSizeTarget - 1; ++i) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(ListViewVector.this.validityBuffer, firstByteSource + i, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(ListViewVector.this.validityBuffer, firstByteSource + i + 1, offset);
                  target.validityBuffer.setByte((long)i, b1 + b2);
               }

               if (firstByteSource + byteSizeTarget - 1 < lastByteSource) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(ListViewVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(ListViewVector.this.validityBuffer, firstByteSource + byteSizeTarget, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1 + b2);
               } else {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(ListViewVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1);
               }
            }
         }

      }

      public ValueVector getTo() {
         return this.to;
      }

      public void copyValueSafe(int from, int to) {
         this.to.copyFrom(from, to, ListViewVector.this);
      }
   }
}
