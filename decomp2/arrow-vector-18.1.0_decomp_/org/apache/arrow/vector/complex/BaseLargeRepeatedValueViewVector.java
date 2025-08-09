package org.apache.arrow.vector.complex;

import java.util.Collections;
import java.util.Iterator;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.CommonUtil;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.AddOrGetResult;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseValueVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.DensityAwareVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.ZeroVector;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.SchemaChangeRuntimeException;

public abstract class BaseLargeRepeatedValueViewVector extends BaseValueVector implements RepeatedValueVector, FieldVector {
   public static final FieldVector DEFAULT_DATA_VECTOR;
   public static final String DATA_VECTOR_NAME = "$data$";
   public static final byte OFFSET_WIDTH = 8;
   public static final byte SIZE_WIDTH = 8;
   protected ArrowBuf offsetBuffer;
   protected ArrowBuf sizeBuffer;
   protected FieldVector vector;
   protected final CallBack repeatedCallBack;
   protected int valueCount;
   protected long offsetAllocationSizeInBytes;
   protected long sizeAllocationSizeInBytes;
   private final String name;
   protected String defaultDataVectorName;

   protected BaseLargeRepeatedValueViewVector(String name, BufferAllocator allocator, CallBack callBack) {
      this(name, allocator, DEFAULT_DATA_VECTOR, callBack);
   }

   protected BaseLargeRepeatedValueViewVector(String name, BufferAllocator allocator, FieldVector vector, CallBack callBack) {
      super(allocator);
      this.offsetAllocationSizeInBytes = 31760L;
      this.sizeAllocationSizeInBytes = 31760L;
      this.defaultDataVectorName = "$data$";
      this.name = name;
      this.offsetBuffer = allocator.getEmpty();
      this.sizeBuffer = allocator.getEmpty();
      this.vector = (FieldVector)Preconditions.checkNotNull(vector, "data vector cannot be null");
      this.repeatedCallBack = callBack;
      this.valueCount = 0;
   }

   public String getName() {
      return this.name;
   }

   public boolean allocateNewSafe() {
      boolean dataAlloc = false;

      boolean var3;
      try {
         this.allocateBuffers();
         dataAlloc = this.vector.allocateNewSafe();
         return dataAlloc;
      } catch (Exception var7) {
         this.clear();
         var3 = false;
      } finally {
         if (!dataAlloc) {
            this.clear();
         }

      }

      return var3;
   }

   private void allocateBuffers() {
      this.offsetBuffer = this.allocateBuffers(this.offsetAllocationSizeInBytes);
      this.sizeBuffer = this.allocateBuffers(this.sizeAllocationSizeInBytes);
   }

   protected ArrowBuf allocateBuffers(long size) {
      int curSize = (int)size;
      ArrowBuf buffer = this.allocator.buffer((long)curSize);
      buffer.readerIndex(0L);
      buffer.setZero(0L, buffer.capacity());
      return buffer;
   }

   public void reAlloc() {
      this.reallocateBuffers();
      this.vector.reAlloc();
   }

   protected void reallocateBuffers() {
      this.reallocOffsetBuffer();
      this.reallocSizeBuffer();
   }

   private void reallocOffsetBuffer() {
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

   private void reallocSizeBuffer() {
      long currentBufferCapacity = this.sizeBuffer.capacity();
      long newAllocationSize = currentBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.sizeAllocationSizeInBytes > 0L) {
            newAllocationSize = this.sizeAllocationSizeInBytes;
         } else {
            newAllocationSize = 63520L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);
      newAllocationSize = Math.min(newAllocationSize, 17179869176L);

      assert newAllocationSize >= 1L;

      if (newAllocationSize <= MAX_ALLOCATION_SIZE && newAllocationSize > this.sizeBuffer.capacity()) {
         ArrowBuf newBuf = this.allocator.buffer(newAllocationSize);
         newBuf.setBytes(0L, this.sizeBuffer, 0L, currentBufferCapacity);
         newBuf.setZero(currentBufferCapacity, newBuf.capacity() - currentBufferCapacity);
         this.sizeBuffer.getReferenceManager().release(1);
         this.sizeBuffer = newBuf;
         this.sizeAllocationSizeInBytes = newAllocationSize;
      } else {
         throw new OversizedAllocationException("Unable to expand the buffer");
      }
   }

   public FieldVector getDataVector() {
      return this.vector;
   }

   public void setInitialCapacity(int numRecords) {
      this.offsetAllocationSizeInBytes = (long)numRecords * 8L;
      this.sizeAllocationSizeInBytes = (long)numRecords * 8L;
      if (!(this.vector instanceof BaseFixedWidthVector) && !(this.vector instanceof BaseVariableWidthVector)) {
         this.vector.setInitialCapacity(numRecords);
      } else {
         this.vector.setInitialCapacity(numRecords * 5);
      }

   }

   public void setInitialCapacity(int numRecords, double density) {
      if ((double)numRecords * density >= (double)Integer.MAX_VALUE) {
         throw new OversizedAllocationException("Requested amount of memory is more than max allowed");
      } else {
         this.offsetAllocationSizeInBytes = (long)numRecords * 8L;
         this.sizeAllocationSizeInBytes = (long)numRecords * 8L;
         int innerValueCapacity = Math.max((int)((double)numRecords * density), 1);
         if (this.vector instanceof DensityAwareVector) {
            ((DensityAwareVector)this.vector).setInitialCapacity(innerValueCapacity, density);
         } else {
            this.vector.setInitialCapacity(innerValueCapacity);
         }

      }
   }

   public void setInitialTotalCapacity(int numRecords, int totalNumberOfElements) {
      this.offsetAllocationSizeInBytes = (long)numRecords * 8L;
      this.sizeAllocationSizeInBytes = (long)numRecords * 8L;
      this.vector.setInitialCapacity(totalNumberOfElements);
   }

   public int getValueCapacity() {
      throw new UnsupportedOperationException("Get value capacity is not supported in RepeatedValueVector");
   }

   protected int getOffsetBufferValueCapacity() {
      return LargeMemoryUtil.checkedCastToInt(this.offsetBuffer.capacity() / 8L);
   }

   protected int getSizeBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.sizeBuffer.capacity() / 8L);
   }

   public int getBufferSize() {
      return this.valueCount == 0 ? 0 : this.valueCount * 8 + this.valueCount * 8 + this.vector.getBufferSize();
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         int innerVectorValueCount = 0;

         for(int i = 0; i < valueCount; ++i) {
            innerVectorValueCount += this.sizeBuffer.getInt((long)(i * 8));
         }

         return valueCount * 8 + valueCount * 8 + this.vector.getBufferSizeFor(LargeMemoryUtil.checkedCastToInt((long)innerVectorValueCount));
      }
   }

   public Iterator iterator() {
      return Collections.singleton(this.getDataVector()).iterator();
   }

   public void clear() {
      this.offsetBuffer = this.releaseBuffer(this.offsetBuffer);
      this.sizeBuffer = this.releaseBuffer(this.sizeBuffer);
      this.vector.clear();
      this.valueCount = 0;
      super.clear();
   }

   public void reset() {
      this.offsetBuffer.setZero(0L, this.offsetBuffer.capacity());
      this.sizeBuffer.setZero(0L, this.sizeBuffer.capacity());
      this.vector.reset();
      this.valueCount = 0;
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      return new ArrowBuf[0];
   }

   public int getValueCount() {
      return this.valueCount;
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;

      while(valueCount > this.getOffsetBufferValueCapacity()) {
         this.reallocateBuffers();
      }

      int childValueCount = valueCount == 0 ? 0 : this.getMaxViewEndChildVector();
      this.vector.setValueCount(childValueCount);
   }

   protected int getMaxViewEndChildVector() {
      int maxOffsetSizeSum = this.offsetBuffer.getInt(0L) + this.sizeBuffer.getInt(0L);

      for(int i = 0; i < this.valueCount; ++i) {
         int currentOffset = this.offsetBuffer.getInt((long)i * 8L);
         int currentSize = this.sizeBuffer.getInt((long)i * 8L);
         int currentSum = currentOffset + currentSize;
         maxOffsetSizeSum = Math.max(maxOffsetSizeSum, currentSum);
      }

      return maxOffsetSizeSum;
   }

   protected int getMaxViewEndChildVectorByIndex(int index) {
      int maxOffsetSizeSum = this.offsetBuffer.getInt(0L) + this.sizeBuffer.getInt(0L);

      for(int i = 0; i < index; ++i) {
         int currentOffset = this.offsetBuffer.getInt((long)i * 8L);
         int currentSize = this.sizeBuffer.getInt((long)i * 8L);
         int currentSum = currentOffset + currentSize;
         maxOffsetSizeSum = Math.max(maxOffsetSizeSum, currentSum);
      }

      return maxOffsetSizeSum;
   }

   public AddOrGetResult addOrGetVector(FieldType fieldType) {
      boolean created = false;
      if (this.vector instanceof NullVector) {
         this.vector = fieldType.createNewSingleVector(this.defaultDataVectorName, this.allocator, this.repeatedCallBack);
         created = true;
         if (this.repeatedCallBack != null && fieldType.getType().getTypeID() != ArrowType.ArrowTypeID.Null) {
            this.repeatedCallBack.doWork();
         }
      }

      if (this.vector.getField().getType().getTypeID() != fieldType.getType().getTypeID()) {
         String msg = String.format("Inner vector type mismatch. Requested type: [%s], actual type: [%s]", fieldType.getType().getTypeID(), this.vector.getField().getType().getTypeID());
         throw new SchemaChangeRuntimeException(msg);
      } else {
         return new AddOrGetResult(this.vector, created);
      }
   }

   protected void replaceDataVector(FieldVector v) {
      this.vector.clear();
      this.vector = v;
   }

   public abstract boolean isEmpty(int var1);

   public int startNewValue(int index) {
      while(index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      while(index >= this.getSizeBufferValueCapacity()) {
         this.reallocSizeBuffer();
      }

      if (index > 0) {
         int prevOffset = this.getMaxViewEndChildVectorByIndex(index);
         this.offsetBuffer.setInt((long)index * 8L, prevOffset);
      }

      this.setValueCount(index + 1);
      return this.offsetBuffer.getInt((long)index * 8L);
   }

   /** @deprecated */
   @Deprecated
   public UInt4Vector getOffsetVector() {
      throw new UnsupportedOperationException("There is no inner offset vector");
   }

   static {
      DEFAULT_DATA_VECTOR = ZeroVector.INSTANCE;
   }
}
