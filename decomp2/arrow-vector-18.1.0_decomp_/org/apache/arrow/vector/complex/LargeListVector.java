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
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseValueVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.DensityAwareVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.ValueIterableVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.ZeroVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.impl.ComplexCopier;
import org.apache.arrow.vector.complex.impl.UnionLargeListReader;
import org.apache.arrow.vector.complex.impl.UnionLargeListWriter;
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

public class LargeListVector extends BaseValueVector implements RepeatedValueVector, FieldVector, PromotableVector, ValueIterableVector {
   public static final FieldVector DEFAULT_DATA_VECTOR;
   public static final String DATA_VECTOR_NAME = "$data$";
   public static final byte OFFSET_WIDTH = 8;
   protected ArrowBuf offsetBuffer;
   protected FieldVector vector;
   protected final CallBack callBack;
   protected int valueCount;
   protected long offsetAllocationSizeInBytes;
   protected String defaultDataVectorName;
   protected ArrowBuf validityBuffer;
   protected UnionLargeListReader reader;
   private Field field;
   private int validityAllocationSizeInBytes;
   private int lastSet;

   public static LargeListVector empty(String name, BufferAllocator allocator) {
      return new LargeListVector(name, allocator, FieldType.nullable(ArrowType.LargeList.INSTANCE), (CallBack)null);
   }

   public LargeListVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      this(new Field(name, fieldType, (List)null), allocator, callBack);
   }

   public LargeListVector(Field field, BufferAllocator allocator, CallBack callBack) {
      super(allocator);
      this.offsetAllocationSizeInBytes = 31760L;
      this.defaultDataVectorName = "$data$";
      this.field = field;
      this.validityBuffer = allocator.getEmpty();
      this.callBack = callBack;
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(3970);
      this.lastSet = -1;
      this.offsetBuffer = allocator.getEmpty();
      this.vector = this.vector == null ? DEFAULT_DATA_VECTOR : this.vector;
      this.valueCount = 0;
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
      this.offsetAllocationSizeInBytes = (long)(numRecords + 1) * 8L;
      if (!(this.vector instanceof BaseFixedWidthVector) && !(this.vector instanceof BaseVariableWidthVector)) {
         this.vector.setInitialCapacity(numRecords);
      } else {
         this.vector.setInitialCapacity(numRecords * 5);
      }

   }

   public void setInitialCapacity(int numRecords, double density) {
      this.validityAllocationSizeInBytes = getValidityBufferSizeFromCount(numRecords);
      if ((double)numRecords * density >= (double)Integer.MAX_VALUE) {
         throw new OversizedAllocationException("Requested amount of memory is more than max allowed");
      } else {
         this.offsetAllocationSizeInBytes = ((long)numRecords + 1L) * 8L;
         int innerValueCapacity = Math.max((int)((double)numRecords * density), 1);
         if (this.vector instanceof DensityAwareVector) {
            ((DensityAwareVector)this.vector).setInitialCapacity(innerValueCapacity, density);
         } else {
            this.vector.setInitialCapacity(innerValueCapacity);
         }

      }
   }

   public void setInitialTotalCapacity(int numRecords, int totalNumberOfElements) {
      this.offsetAllocationSizeInBytes = ((long)numRecords + 1L) * 8L;
      this.vector.setInitialCapacity(totalNumberOfElements);
   }

   public double getDensity() {
      if (this.valueCount == 0) {
         return (double)0.0F;
      } else {
         long startOffset = this.offsetBuffer.getLong(0L);
         long endOffset = this.offsetBuffer.getLong((long)this.valueCount * 8L);
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
         this.exportBuffer(this.allocateOffsetBuffer(8L), buffers, buffersPtr, nullValue, false);
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
         this.offsetBuffer.writerIndex((long)((this.valueCount + 1) * 8));
      }

   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use getFieldBuffers");
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
         boolean dataAlloc = false;

         try {
            this.offsetBuffer = this.allocateOffsetBuffer(this.offsetAllocationSizeInBytes);
            dataAlloc = this.vector.allocateNewSafe();
         } catch (Exception e) {
            e.printStackTrace();
            this.clear();
            success = false;
         } finally {
            if (!dataAlloc) {
               this.clear();
            }

         }

         success = dataAlloc;
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

   protected ArrowBuf allocateOffsetBuffer(long size) {
      ArrowBuf offsetBuffer = this.allocator.buffer(size);
      offsetBuffer.readerIndex(0L);
      this.offsetAllocationSizeInBytes = size;
      offsetBuffer.setZero(0L, offsetBuffer.capacity());
      return offsetBuffer;
   }

   public void reAlloc() {
      this.reallocValidityBuffer();
      this.reallocOffsetBuffer();
      this.vector.reAlloc();
   }

   private void reallocValidityAndOffsetBuffers() {
      this.reallocOffsetBuffer();
      this.reallocValidityBuffer();
   }

   protected void reallocOffsetBuffer() {
      long currentBufferCapacity = this.offsetBuffer.capacity();
      long newAllocationSize = currentBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.offsetAllocationSizeInBytes > 0L) {
            newAllocationSize = this.offsetAllocationSizeInBytes;
         } else {
            newAllocationSize = 63520L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);
      newAllocationSize = Math.min(newAllocationSize, 17179869176L);

      assert newAllocationSize >= 1L;

      if (newAllocationSize <= MAX_ALLOCATION_SIZE && newAllocationSize > this.offsetBuffer.capacity()) {
         ArrowBuf newBuf = this.allocator.buffer(newAllocationSize);
         newBuf.setBytes(0L, this.offsetBuffer, 0L, currentBufferCapacity);
         newBuf.setZero(currentBufferCapacity, newBuf.capacity() - currentBufferCapacity);
         this.offsetBuffer.getReferenceManager().release(1);
         this.offsetBuffer = newBuf;
         this.offsetAllocationSizeInBytes = newAllocationSize;
      } else {
         throw new OversizedAllocationException("Unable to expand the buffer");
      }
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

   public void copyFromSafe(int inIndex, int outIndex, ValueVector from) {
      this.copyFrom(inIndex, outIndex, from);
   }

   public void copyFrom(int inIndex, int outIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      FieldReader in = from.getReader();
      in.setPosition(inIndex);
      UnionLargeListWriter out = this.getWriter();
      out.setPosition(outIndex);
      ComplexCopier.copy(in, out);
   }

   /** @deprecated */
   @Deprecated
   public UInt4Vector getOffsetVector() {
      throw new UnsupportedOperationException("There is no inner offset vector");
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
      return new TransferImpl((LargeListVector)target);
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

   public int getValueCount() {
      return this.valueCount;
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      if (this.isSet(index) == 0) {
         return 0;
      } else {
         int hash = 0;
         long start = this.offsetBuffer.getLong((long)index * 8L);
         long end = this.offsetBuffer.getLong(((long)index + 1L) * 8L);

         for(long i = start; i < end; ++i) {
            hash = ByteFunctionHelpers.combineHash(hash, this.vector.hashCode(LargeMemoryUtil.checkedCastToInt(i), hasher));
         }

         return hash;
      }
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public UnionLargeListWriter getWriter() {
      return new UnionLargeListWriter(this);
   }

   protected void replaceDataVector(FieldVector v) {
      this.vector.clear();
      this.vector = v;
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

   protected FieldReader getReaderImpl() {
      return new UnionLargeListReader(this);
   }

   public UnionLargeListReader getReader() {
      this.reader = (UnionLargeListReader)super.getReader();
      return this.reader;
   }

   public AddOrGetResult addOrGetVector(FieldType fieldType) {
      boolean created = false;
      if (this.vector instanceof NullVector) {
         this.vector = fieldType.createNewSingleVector(this.defaultDataVectorName, this.allocator, this.callBack);
         created = true;
         if (this.callBack != null && fieldType.getType().getTypeID() != ArrowType.ArrowTypeID.Null) {
            this.callBack.doWork();
         }
      }

      if (this.vector.getField().getType().getTypeID() != fieldType.getType().getTypeID()) {
         String msg = String.format("Inner vector type mismatch. Requested type: [%s], actual type: [%s]", fieldType.getType().getTypeID(), this.vector.getField().getType().getTypeID());
         throw new SchemaChangeRuntimeException(msg);
      } else {
         this.invalidateReader();
         return new AddOrGetResult(this.vector, created);
      }
   }

   public int getBufferSize() {
      if (this.valueCount == 0) {
         return 0;
      } else {
         int offsetBufferSize = (this.valueCount + 1) * 8;
         int validityBufferSize = getValidityBufferSizeFromCount(this.valueCount);
         return offsetBufferSize + validityBufferSize + this.vector.getBufferSize();
      }
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         int validityBufferSize = getValidityBufferSizeFromCount(valueCount);
         long innerVectorValueCount = this.offsetBuffer.getLong((long)valueCount * 8L);
         return (valueCount + 1) * 8 + this.vector.getBufferSizeFor(LargeMemoryUtil.checkedCastToInt(innerVectorValueCount)) + validityBufferSize;
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
      return Types.MinorType.LARGELIST;
   }

   public String getName() {
      return this.field.getName();
   }

   public void clear() {
      this.offsetBuffer = this.releaseBuffer(this.offsetBuffer);
      this.vector.clear();
      this.valueCount = 0;
      super.clear();
      this.validityBuffer = this.releaseBuffer(this.validityBuffer);
      this.lastSet = -1;
   }

   public void reset() {
      this.offsetBuffer.setZero(0L, this.offsetBuffer.capacity());
      this.vector.reset();
      this.valueCount = 0;
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

   protected void invalidateReader() {
      this.reader = null;
   }

   public List getObject(int index) {
      if (this.isSet(index) == 0) {
         return null;
      } else {
         List<Object> vals = new JsonStringArrayList();
         long start = this.offsetBuffer.getLong((long)index * 8L);
         long end = this.offsetBuffer.getLong(((long)index + 1L) * 8L);
         ValueVector vv = this.getDataVector();

         for(long i = start; i < end; ++i) {
            vals.add(vv.getObject(LargeMemoryUtil.checkedCastToInt(i)));
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
         long start = this.offsetBuffer.getLong((long)index * 8L);
         long end = this.offsetBuffer.getLong(((long)index + 1L) * 8L);
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

   protected int getOffsetBufferValueCapacity() {
      return LargeMemoryUtil.checkedCastToInt(this.offsetBuffer.capacity() / 8L);
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
         int currentOffset = this.offsetBuffer.getInt((long)(i * 8));
         this.offsetBuffer.setInt((long)((i + 1) * 8), currentOffset);
      }

      BitVectorHelper.unsetBit(this.validityBuffer, index);
   }

   public long startNewValue(long index) {
      while(index >= (long)this.getValidityAndOffsetValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      for(int i = this.lastSet + 1; (long)i <= index; ++i) {
         long currentOffset = this.offsetBuffer.getLong((long)i * 8L);
         this.offsetBuffer.setLong(((long)i + 1L) * 8L, currentOffset);
      }

      BitVectorHelper.setBit(this.validityBuffer, index);
      this.lastSet = LargeMemoryUtil.checkedCastToInt(index);
      return this.offsetBuffer.getLong(((long)this.lastSet + 1L) * 8L);
   }

   public void endValue(int index, long size) {
      long currentOffset = this.offsetBuffer.getLong(((long)index + 1L) * 8L);
      this.offsetBuffer.setLong(((long)index + 1L) * 8L, currentOffset + size);
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;
      if (valueCount > 0) {
         while(valueCount > this.getValidityAndOffsetValueCapacity()) {
            this.reallocValidityAndOffsetBuffers();
         }

         for(int i = this.lastSet + 1; i < valueCount; ++i) {
            long currentOffset = this.offsetBuffer.getLong((long)i * 8L);
            this.offsetBuffer.setLong(((long)i + 1L) * 8L, currentOffset);
         }
      }

      long childValueCount = valueCount == 0 ? 0L : this.offsetBuffer.getLong(((long)this.lastSet + 1L) * 8L);
      Preconditions.checkArgument(childValueCount <= 2147483647L || childValueCount >= -2147483648L, "LargeListVector doesn't yet support 64-bit allocations: %s", childValueCount);
      this.vector.setValueCount((int)childValueCount);
   }

   public void setLastSet(int value) {
      this.lastSet = value;
   }

   public int getLastSet() {
      return this.lastSet;
   }

   public long getElementStartIndex(int index) {
      return this.offsetBuffer.getLong((long)index * 8L);
   }

   public long getElementEndIndex(int index) {
      return this.offsetBuffer.getLong(((long)index + 1L) * 8L);
   }

   static {
      DEFAULT_DATA_VECTOR = ZeroVector.INSTANCE;
   }

   private class TransferImpl implements TransferPair {
      LargeListVector to;
      TransferPair dataTransferPair;

      public TransferImpl(String name, BufferAllocator allocator, CallBack callBack) {
         this(new LargeListVector(name, allocator, LargeListVector.this.field.getFieldType(), callBack));
      }

      public TransferImpl(Field field, BufferAllocator allocator, CallBack callBack) {
         this(new LargeListVector(field, allocator, callBack));
      }

      public TransferImpl(LargeListVector to) {
         this.to = to;
         to.addOrGetVector(LargeListVector.this.vector.getField().getFieldType());
         if (to.getDataVector() instanceof ZeroVector) {
            to.addOrGetVector(LargeListVector.this.vector.getField().getFieldType());
         }

         this.dataTransferPair = LargeListVector.this.getDataVector().makeTransferPair(to.getDataVector());
      }

      public void transfer() {
         this.to.clear();
         this.dataTransferPair.transfer();
         this.to.validityBuffer = BaseValueVector.transferBuffer(LargeListVector.this.validityBuffer, this.to.allocator);
         this.to.offsetBuffer = BaseValueVector.transferBuffer(LargeListVector.this.offsetBuffer, this.to.allocator);
         this.to.lastSet = LargeListVector.this.lastSet;
         if (LargeListVector.this.valueCount > 0) {
            this.to.setValueCount(LargeListVector.this.valueCount);
         }

         LargeListVector.this.clear();
      }

      public void splitAndTransfer(int startIndex, int length) {
         Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= LargeListVector.this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, LargeListVector.this.valueCount);
         long startPoint = LargeListVector.this.offsetBuffer.getLong((long)startIndex * 8L);
         long sliceLength = LargeListVector.this.offsetBuffer.getLong((long)(startIndex + length) * 8L) - startPoint;
         this.to.clear();
         this.to.offsetBuffer = this.to.allocateOffsetBuffer((long)((length + 1) * 8));

         for(int i = 0; i < length + 1; ++i) {
            long relativeOffset = LargeListVector.this.offsetBuffer.getLong((long)(startIndex + i) * 8L) - startPoint;
            this.to.offsetBuffer.setLong((long)i * 8L, relativeOffset);
         }

         this.splitAndTransferValidityBuffer(startIndex, length, this.to);
         this.dataTransferPair.splitAndTransfer(LargeMemoryUtil.checkedCastToInt(startPoint), LargeMemoryUtil.checkedCastToInt(sliceLength));
         this.to.lastSet = length - 1;
         this.to.setValueCount(length);
      }

      private void splitAndTransferValidityBuffer(int startIndex, int length, LargeListVector target) {
         int firstByteSource = BitVectorHelper.byteIndex(startIndex);
         int lastByteSource = BitVectorHelper.byteIndex(LargeListVector.this.valueCount - 1);
         int byteSizeTarget = LargeListVector.getValidityBufferSizeFromCount(length);
         int offset = startIndex % 8;
         if (length > 0) {
            if (offset == 0) {
               if (target.validityBuffer != null) {
                  target.validityBuffer.getReferenceManager().release();
               }

               target.validityBuffer = LargeListVector.this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
               target.validityBuffer.getReferenceManager().retain(1);
            } else {
               target.allocateValidityBuffer((long)byteSizeTarget);

               for(int i = 0; i < byteSizeTarget - 1; ++i) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(LargeListVector.this.validityBuffer, firstByteSource + i, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(LargeListVector.this.validityBuffer, firstByteSource + i + 1, offset);
                  target.validityBuffer.setByte((long)i, b1 + b2);
               }

               if (firstByteSource + byteSizeTarget - 1 < lastByteSource) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(LargeListVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(LargeListVector.this.validityBuffer, firstByteSource + byteSizeTarget, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1 + b2);
               } else {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(LargeListVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1);
               }
            }
         }

      }

      public ValueVector getTo() {
         return this.to;
      }

      public void copyValueSafe(int from, int to) {
         this.to.copyFrom(from, to, LargeListVector.this);
      }
   }
}
