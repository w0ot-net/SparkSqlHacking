package org.apache.arrow.vector.complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
import org.apache.arrow.vector.complex.impl.UnionFixedSizeListReader;
import org.apache.arrow.vector.complex.impl.UnionFixedSizeListWriter;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.JsonStringArrayList;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.SchemaChangeRuntimeException;
import org.apache.arrow.vector.util.TransferPair;

public class FixedSizeListVector extends BaseValueVector implements BaseListVector, PromotableVector, ValueIterableVector {
   private FieldVector vector;
   private ArrowBuf validityBuffer;
   private final int listSize;
   private Field field;
   private UnionFixedSizeListReader reader;
   private int valueCount;
   private int validityAllocationSizeInBytes;

   public static FixedSizeListVector empty(String name, int size, BufferAllocator allocator) {
      FieldType fieldType = FieldType.nullable(new ArrowType.FixedSizeList(size));
      return new FixedSizeListVector(name, allocator, fieldType, (CallBack)null);
   }

   public FixedSizeListVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack unusedSchemaChangeCallback) {
      this(new Field(name, fieldType, (List)null), allocator, unusedSchemaChangeCallback);
   }

   public FixedSizeListVector(Field field, BufferAllocator allocator, CallBack unusedSchemaChangeCallback) {
      super(allocator);
      this.field = field;
      this.validityBuffer = allocator.getEmpty();
      this.vector = ZeroVector.INSTANCE;
      this.listSize = ((ArrowType.FixedSizeList)field.getFieldType().getType()).getListSize();
      Preconditions.checkArgument(this.listSize >= 0, "list size must be non-negative");
      this.valueCount = 0;
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(3970);
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
      return Types.MinorType.FIXED_SIZE_LIST;
   }

   public String getName() {
      return this.field.getName();
   }

   public int getListSize() {
      return this.listSize;
   }

   public void initializeChildrenFromFields(List children) {
      Preconditions.checkArgument(children.size() == 1, "Lists have one child Field. Found: %s", children.isEmpty() ? "none" : children);
      Field field = (Field)children.get(0);
      AddOrGetResult<FieldVector> addOrGetVector = this.addOrGetVector(field.getFieldType());
      Preconditions.checkArgument(addOrGetVector.isCreated(), "Child vector already existed: %s", addOrGetVector.getVector());
      ((FieldVector)addOrGetVector.getVector()).initializeChildrenFromFields(field.getChildren());
      this.field = new Field(this.field.getName(), this.field.getFieldType(), children);
   }

   public List getChildrenFromFields() {
      return Collections.singletonList(this.vector);
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
      this.validityBuffer.writerIndex((long)getValidityBufferSizeFromCount(this.valueCount));
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use getFieldBuffers");
   }

   protected FieldReader getReaderImpl() {
      return new UnionFixedSizeListReader(this);
   }

   public UnionFixedSizeListReader getReader() {
      this.reader = (UnionFixedSizeListReader)super.getReader();
      return this.reader;
   }

   private void invalidateReader() {
      this.reader = null;
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
         success = this.vector.allocateNewSafe();
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
      this.vector.reAlloc();
   }

   private void reallocValidityBuffer() {
      int currentBufferCapacity = LargeMemoryUtil.checkedCastToInt(this.validityBuffer.capacity());
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
         ArrowBuf newBuf = this.allocator.buffer(newAllocationSize);
         newBuf.setBytes(0L, this.validityBuffer, 0L, (long)currentBufferCapacity);
         newBuf.setZero((long)currentBufferCapacity, newBuf.capacity() - (long)currentBufferCapacity);
         this.validityBuffer.getReferenceManager().release(1);
         this.validityBuffer = newBuf;
         this.validityAllocationSizeInBytes = (int)newAllocationSize;
      }
   }

   public FieldVector getDataVector() {
      return this.vector;
   }

   public int startNewValue(int index) {
      while(index >= this.getValidityBufferValueCapacity()) {
         this.reallocValidityBuffer();
      }

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      return index * this.listSize;
   }

   public UnionFixedSizeListWriter getWriter() {
      return new UnionFixedSizeListWriter(this);
   }

   public void setInitialCapacity(int numRecords) {
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(numRecords);
      this.vector.setInitialCapacity(numRecords * this.listSize);
   }

   public int getValueCapacity() {
      return this.vector != ZeroVector.INSTANCE && this.listSize != 0 ? Math.min(this.vector.getValueCapacity() / this.listSize, this.getValidityBufferValueCapacity()) : 0;
   }

   public int getBufferSize() {
      return this.getValueCount() == 0 ? 0 : getValidityBufferSizeFromCount(this.valueCount) + this.vector.getBufferSize();
   }

   public int getBufferSizeFor(int valueCount) {
      return valueCount == 0 ? 0 : getValidityBufferSizeFromCount(valueCount) + this.vector.getBufferSizeFor(valueCount * this.listSize);
   }

   public Iterator iterator() {
      return Collections.singleton(this.vector).iterator();
   }

   public void clear() {
      this.validityBuffer = this.releaseBuffer(this.validityBuffer);
      this.vector.clear();
      this.valueCount = 0;
      super.clear();
   }

   public void reset() {
      this.validityBuffer.setZero(0L, this.validityBuffer.capacity());
      this.vector.reset();
      this.valueCount = 0;
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      this.setReaderAndWriterIndex();
      ArrowBuf[] buffers;
      if (this.getBufferSize() == 0) {
         buffers = new ArrowBuf[0];
      } else {
         List<ArrowBuf> list = new ArrayList();
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

   public int size() {
      return this.vector == ZeroVector.INSTANCE ? 0 : 1;
   }

   public AddOrGetResult addOrGetVector(FieldType type) {
      boolean created = false;
      if (this.vector == ZeroVector.INSTANCE) {
         this.vector = type.createNewSingleVector((String)"$data$", this.allocator, (CallBack)null);
         this.invalidateReader();
         created = true;
      }

      if (!Objects.equals(this.vector.getField().getType(), type.getType())) {
         String msg = String.format("Inner vector type mismatch. Requested type: [%s], actual type: [%s]", type.getType(), this.vector.getField().getType());
         throw new SchemaChangeRuntimeException(msg);
      } else {
         return new AddOrGetResult(this.vector, created);
      }
   }

   public void copyFromSafe(int inIndex, int outIndex, ValueVector from) {
      this.copyFrom(inIndex, outIndex, from);
   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      TransferPair pair = from.makeTransferPair(this);
      pair.copyValueSafe(fromIndex, thisIndex);
   }

   public UnionVector promoteToUnion() {
      UnionVector vector = new UnionVector(this.getName(), this.allocator, (FieldType)null, (CallBack)null);
      this.vector.clear();
      this.vector = vector;
      this.invalidateReader();
      return vector;
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

   public List getObject(int index) {
      if (this.isSet(index) == 0) {
         return null;
      } else {
         List<Object> vals = new JsonStringArrayList(this.listSize);

         for(int i = 0; i < this.listSize; ++i) {
            vals.add(this.vector.getObject(index * this.listSize + i));
         }

         return vals;
      }
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

   public int getNullCount() {
      return BitVectorHelper.getNullCount(this.validityBuffer, this.valueCount);
   }

   public int getValueCount() {
      return this.valueCount;
   }

   private int getValidityBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.validityBuffer.capacity() * 8L);
   }

   public void setNull(int index) {
      while(index >= this.getValidityBufferValueCapacity()) {
         this.reallocValidityBuffer();
      }

      BitVectorHelper.unsetBit(this.validityBuffer, index);
   }

   public void setNotNull(int index) {
      while(index >= this.getValidityBufferValueCapacity()) {
         this.reallocValidityBuffer();
      }

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;

      while(valueCount > this.getValidityBufferValueCapacity()) {
         this.reallocValidityBuffer();
      }

      this.vector.setValueCount(valueCount * this.listSize);
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
      return new TransferImpl((FixedSizeListVector)target);
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      if (this.isSet(index) == 0) {
         return 0;
      } else {
         int hash = 0;

         for(int i = 0; i < this.listSize; ++i) {
            hash = ByteFunctionHelpers.combineHash(hash, this.vector.hashCode(index * this.listSize + i, hasher));
         }

         return hash;
      }
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public int getElementStartIndex(int index) {
      return this.listSize * index;
   }

   public int getElementEndIndex(int index) {
      return this.listSize * (index + 1);
   }

   private class TransferImpl implements TransferPair {
      FixedSizeListVector to;
      TransferPair dataPair;

      public TransferImpl(String name, BufferAllocator allocator, CallBack callBack) {
         this(new FixedSizeListVector(name, allocator, FixedSizeListVector.this.field.getFieldType(), callBack));
      }

      public TransferImpl(Field field, BufferAllocator allocator, CallBack callBack) {
         this(new FixedSizeListVector(field, allocator, callBack));
      }

      public TransferImpl(FixedSizeListVector to) {
         this.to = to;
         if (!(FixedSizeListVector.this.vector instanceof ZeroVector)) {
            to.addOrGetVector(FixedSizeListVector.this.vector.getField().getFieldType());
         }

         this.dataPair = FixedSizeListVector.this.vector.makeTransferPair(to.vector);
      }

      public void transfer() {
         this.to.clear();
         this.dataPair.transfer();
         this.to.validityBuffer = BaseValueVector.transferBuffer(FixedSizeListVector.this.validityBuffer, this.to.allocator);
         this.to.setValueCount(FixedSizeListVector.this.valueCount);
         FixedSizeListVector.this.clear();
      }

      public void splitAndTransfer(int startIndex, int length) {
         Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= FixedSizeListVector.this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, FixedSizeListVector.this.valueCount);
         int startPoint = FixedSizeListVector.this.listSize * startIndex;
         int sliceLength = FixedSizeListVector.this.listSize * length;
         this.to.clear();
         this.splitAndTransferValidityBuffer(startIndex, length, this.to);
         this.dataPair.splitAndTransfer(startPoint, sliceLength);
         this.to.setValueCount(length);
      }

      private void splitAndTransferValidityBuffer(int startIndex, int length, FixedSizeListVector target) {
         int firstByteSource = BitVectorHelper.byteIndex(startIndex);
         int lastByteSource = BitVectorHelper.byteIndex(FixedSizeListVector.this.valueCount - 1);
         int byteSizeTarget = FixedSizeListVector.getValidityBufferSizeFromCount(length);
         int offset = startIndex % 8;
         if (length > 0) {
            if (offset == 0) {
               if (target.validityBuffer != null) {
                  target.validityBuffer.getReferenceManager().release();
               }

               target.validityBuffer = FixedSizeListVector.this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
               target.validityBuffer.getReferenceManager().retain(1);
            } else {
               target.allocateValidityBuffer((long)byteSizeTarget);

               for(int i = 0; i < byteSizeTarget - 1; ++i) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(FixedSizeListVector.this.validityBuffer, firstByteSource + i, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(FixedSizeListVector.this.validityBuffer, firstByteSource + i + 1, offset);
                  target.validityBuffer.setByte((long)i, b1 + b2);
               }

               if (firstByteSource + byteSizeTarget - 1 < lastByteSource) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(FixedSizeListVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(FixedSizeListVector.this.validityBuffer, firstByteSource + byteSizeTarget, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1 + b2);
               } else {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(FixedSizeListVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1);
               }
            }
         }

      }

      public ValueVector getTo() {
         return this.to;
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         while(toIndex >= this.to.getValueCapacity()) {
            this.to.reAlloc();
         }

         BitVectorHelper.setValidityBit(this.to.validityBuffer, toIndex, FixedSizeListVector.this.isSet(fromIndex));
         int fromOffset = fromIndex * FixedSizeListVector.this.listSize;
         int toOffset = toIndex * FixedSizeListVector.this.listSize;

         for(int i = 0; i < FixedSizeListVector.this.listSize; ++i) {
            this.dataPair.copyValueSafe(fromOffset + i, toOffset + i);
         }

      }
   }
}
