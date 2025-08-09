package org.apache.arrow.vector.complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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

public abstract class BaseRepeatedValueVector extends BaseValueVector implements RepeatedValueVector, BaseListVector {
   public static final FieldVector DEFAULT_DATA_VECTOR;
   public static final String DATA_VECTOR_NAME = "$data$";
   public static final byte OFFSET_WIDTH = 4;
   protected ArrowBuf offsetBuffer;
   protected FieldVector vector;
   protected final CallBack repeatedCallBack;
   protected int valueCount;
   protected long offsetAllocationSizeInBytes;
   private final String name;
   protected String defaultDataVectorName;

   protected BaseRepeatedValueVector(String name, BufferAllocator allocator, CallBack callBack) {
      this(name, allocator, DEFAULT_DATA_VECTOR, callBack);
   }

   protected BaseRepeatedValueVector(String name, BufferAllocator allocator, FieldVector vector, CallBack callBack) {
      super(allocator);
      this.offsetAllocationSizeInBytes = 15880L;
      this.defaultDataVectorName = "$data$";
      this.name = name;
      this.offsetBuffer = allocator.getEmpty();
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
         this.offsetBuffer = this.allocateOffsetBuffer(this.offsetAllocationSizeInBytes);
         dataAlloc = this.vector.allocateNewSafe();
         return dataAlloc;
      } catch (Exception e) {
         e.printStackTrace();
         this.clear();
         var3 = false;
      } finally {
         if (!dataAlloc) {
            this.clear();
         }

      }

      return var3;
   }

   protected ArrowBuf allocateOffsetBuffer(long size) {
      int curSize = (int)size;
      ArrowBuf offsetBuffer = this.allocator.buffer((long)curSize);
      offsetBuffer.readerIndex(0L);
      this.offsetAllocationSizeInBytes = (long)curSize;
      offsetBuffer.setZero(0L, offsetBuffer.capacity());
      return offsetBuffer;
   }

   public void reAlloc() {
      this.reallocOffsetBuffer();
      this.vector.reAlloc();
   }

   protected void reallocOffsetBuffer() {
      long currentBufferCapacity = this.offsetBuffer.capacity();
      long newAllocationSize = currentBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.offsetAllocationSizeInBytes > 0L) {
            newAllocationSize = this.offsetAllocationSizeInBytes;
         } else {
            newAllocationSize = 31760L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);
      newAllocationSize = Math.min(newAllocationSize, 8589934588L);

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

   /** @deprecated */
   @Deprecated
   public UInt4Vector getOffsetVector() {
      throw new UnsupportedOperationException("There is no inner offset vector");
   }

   public FieldVector getDataVector() {
      return this.vector;
   }

   public void setInitialCapacity(int numRecords) {
      this.offsetAllocationSizeInBytes = ((long)numRecords + 1L) * 4L;
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
         this.offsetAllocationSizeInBytes = ((long)numRecords + 1L) * 4L;
         int innerValueCapacity = Math.max((int)((double)numRecords * density), 1);
         if (this.vector instanceof DensityAwareVector) {
            ((DensityAwareVector)this.vector).setInitialCapacity(innerValueCapacity, density);
         } else {
            this.vector.setInitialCapacity(innerValueCapacity);
         }

      }
   }

   public void setInitialTotalCapacity(int numRecords, int totalNumberOfElements) {
      this.offsetAllocationSizeInBytes = ((long)numRecords + 1L) * 4L;
      this.vector.setInitialCapacity(totalNumberOfElements);
   }

   public int getValueCapacity() {
      int offsetValueCapacity = Math.max(this.getOffsetBufferValueCapacity() - 1, 0);
      return this.vector == DEFAULT_DATA_VECTOR ? offsetValueCapacity : Math.min(this.vector.getValueCapacity(), offsetValueCapacity);
   }

   protected int getOffsetBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.offsetBuffer.capacity() / 4L);
   }

   public int getBufferSize() {
      return this.valueCount == 0 ? 0 : (this.valueCount + 1) * 4 + this.vector.getBufferSize();
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         int innerVectorValueCount = this.offsetBuffer.getInt((long)(valueCount * 4));
         return (valueCount + 1) * 4 + this.vector.getBufferSizeFor(innerVectorValueCount);
      }
   }

   public Iterator iterator() {
      return Collections.singleton(this.getDataVector()).iterator();
   }

   public void clear() {
      this.offsetBuffer = this.releaseBuffer(this.offsetBuffer);
      this.vector.clear();
      this.valueCount = 0;
      super.clear();
   }

   public void reset() {
      this.offsetBuffer.setZero(0L, this.offsetBuffer.capacity());
      this.vector.reset();
      this.valueCount = 0;
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      ArrowBuf[] buffers;
      if (this.getBufferSize() == 0) {
         buffers = new ArrowBuf[0];
      } else {
         List<ArrowBuf> list = new ArrayList();
         list.add(this.offsetBuffer);
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
      return this.vector == DEFAULT_DATA_VECTOR ? 0 : 1;
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

   public int getValueCount() {
      return this.valueCount;
   }

   public int getInnerValueCount() {
      return this.vector.getValueCount();
   }

   public int getInnerValueCountAt(int index) {
      return this.offsetBuffer.getInt((long)((index + 1) * 4)) - this.offsetBuffer.getInt((long)(index * 4));
   }

   public abstract boolean isEmpty(int var1);

   public int startNewValue(int index) {
      while(index >= this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      int offset = this.offsetBuffer.getInt((long)(index * 4));
      this.offsetBuffer.setInt((long)((index + 1) * 4), offset);
      this.setValueCount(index + 1);
      return offset;
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;

      while(valueCount > this.getOffsetBufferValueCapacity()) {
         this.reallocOffsetBuffer();
      }

      int childValueCount = valueCount == 0 ? 0 : this.offsetBuffer.getInt((long)(valueCount * 4));
      this.vector.setValueCount(childValueCount);
   }

   static {
      DEFAULT_DATA_VECTOR = ZeroVector.INSTANCE;
   }
}
