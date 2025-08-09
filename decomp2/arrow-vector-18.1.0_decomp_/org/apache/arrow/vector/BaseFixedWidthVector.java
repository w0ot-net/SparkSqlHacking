package org.apache.arrow.vector;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.ArrowBufPointer;
import org.apache.arrow.memory.util.ByteFunctionHelpers;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.MemoryUtil;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.TransferPair;

public abstract class BaseFixedWidthVector extends BaseValueVector implements FixedWidthVector, FieldVector, VectorDefinitionSetter {
   private final int typeWidth;
   protected int lastValueCapacity;
   protected int actualValueCapacity;
   protected final Field field;
   private int allocationMonitor;
   protected ArrowBuf validityBuffer;
   protected ArrowBuf valueBuffer;
   protected int valueCount;

   public BaseFixedWidthVector(Field field, BufferAllocator allocator, int typeWidth) {
      super(allocator);
      this.typeWidth = typeWidth;
      this.field = field;
      this.valueCount = 0;
      this.allocationMonitor = 0;
      this.validityBuffer = allocator.getEmpty();
      this.valueBuffer = allocator.getEmpty();
      this.lastValueCapacity = 3970;
      this.refreshValueCapacity();
   }

   public int getTypeWidth() {
      return this.typeWidth;
   }

   public String getName() {
      return this.field.getName();
   }

   public long getValidityBufferAddress() {
      return this.validityBuffer.memoryAddress();
   }

   public long getDataBufferAddress() {
      return this.valueBuffer.memoryAddress();
   }

   public long getOffsetBufferAddress() {
      throw new UnsupportedOperationException("not supported for fixed-width vectors");
   }

   public ArrowBuf getValidityBuffer() {
      return this.validityBuffer;
   }

   public ArrowBuf getDataBuffer() {
      return this.valueBuffer;
   }

   public ArrowBuf getOffsetBuffer() {
      throw new UnsupportedOperationException("not supported for fixed-width vectors");
   }

   public void setInitialCapacity(int valueCount) {
      this.computeAndCheckBufferSize(valueCount);
      this.lastValueCapacity = valueCount;
   }

   public int getValueCapacity() {
      return this.actualValueCapacity;
   }

   protected void refreshValueCapacity() {
      this.actualValueCapacity = Math.min(this.getValueBufferValueCapacity(), this.getValidityBufferValueCapacity());
   }

   protected int getValueBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.valueBuffer.capacity() / (long)this.typeWidth);
   }

   protected int getValidityBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.validityBuffer.capacity() * 8L);
   }

   public void zeroVector() {
      this.initValidityBuffer();
      this.initValueBuffer();
   }

   private void initValidityBuffer() {
      this.validityBuffer.setZero(0L, this.validityBuffer.capacity());
   }

   private void initValueBuffer() {
      this.valueBuffer.setZero(0L, this.valueBuffer.capacity());
   }

   public void reset() {
      this.valueCount = 0;
      this.zeroVector();
   }

   public void close() {
      this.clear();
   }

   public void clear() {
      this.valueCount = 0;
      this.validityBuffer = this.releaseBuffer(this.validityBuffer);
      this.valueBuffer = this.releaseBuffer(this.valueBuffer);
      this.refreshValueCapacity();
   }

   protected void incrementAllocationMonitor() {
      if (this.allocationMonitor < 0) {
         this.allocationMonitor = 0;
      }

      ++this.allocationMonitor;
   }

   protected void decrementAllocationMonitor() {
      if (this.allocationMonitor > 0) {
         this.allocationMonitor = 0;
      }

      --this.allocationMonitor;
   }

   public void allocateNew() {
      this.allocateNew(this.lastValueCapacity);
   }

   public boolean allocateNewSafe() {
      try {
         this.allocateNew(this.lastValueCapacity);
         return true;
      } catch (Exception var2) {
         return false;
      }
   }

   public void allocateNew(int valueCount) {
      this.computeAndCheckBufferSize(valueCount);
      this.clear();

      try {
         this.allocateBytes(valueCount);
      } catch (Exception e) {
         this.clear();
         throw e;
      }
   }

   private long computeAndCheckBufferSize(int valueCount) {
      long size = this.computeCombinedBufferSize(valueCount, this.typeWidth);
      if (size > MAX_ALLOCATION_SIZE) {
         throw new OversizedAllocationException("Memory required for vector capacity " + valueCount + " is (" + size + "), which is more than max allowed (" + MAX_ALLOCATION_SIZE + ")");
      } else {
         return size;
      }
   }

   private void allocateBytes(int valueCount) {
      BaseValueVector.DataAndValidityBuffers buffers = this.allocFixedDataAndValidityBufs(valueCount, this.typeWidth);
      this.valueBuffer = buffers.getDataBuf();
      this.validityBuffer = buffers.getValidityBuf();
      this.zeroVector();
      this.refreshValueCapacity();
      this.lastValueCapacity = this.getValueCapacity();
   }

   private void allocateValidityBuffer(int validityBufferSize) {
      this.validityBuffer = this.allocator.buffer((long)validityBufferSize);
      this.validityBuffer.readerIndex(0L);
      this.refreshValueCapacity();
   }

   public int getBufferSizeFor(int count) {
      return count == 0 ? 0 : count * this.typeWidth + getValidityBufferSizeFromCount(count);
   }

   public int getBufferSize() {
      return this.valueCount == 0 ? 0 : this.valueCount * this.typeWidth + getValidityBufferSizeFromCount(this.valueCount);
   }

   public Field getField() {
      return this.field;
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      this.setReaderAndWriterIndex();
      ArrowBuf[] buffers;
      if (this.getBufferSize() == 0) {
         buffers = new ArrowBuf[0];
      } else {
         buffers = new ArrowBuf[2];
         buffers[0] = this.validityBuffer;
         buffers[1] = this.valueBuffer;
      }

      if (clear) {
         for(ArrowBuf buffer : buffers) {
            buffer.getReferenceManager().retain(1);
         }

         this.clear();
      }

      return buffers;
   }

   public void reAlloc() {
      int targetValueCount = this.getValueCapacity() * 2;
      if (targetValueCount == 0) {
         if (this.lastValueCapacity > 0) {
            targetValueCount = this.lastValueCapacity;
         } else {
            targetValueCount = 7940;
         }
      }

      this.computeAndCheckBufferSize(targetValueCount);
      BaseValueVector.DataAndValidityBuffers buffers = this.allocFixedDataAndValidityBufs(targetValueCount, this.typeWidth);
      ArrowBuf newValueBuffer = buffers.getDataBuf();
      newValueBuffer.setBytes(0L, this.valueBuffer, 0L, this.valueBuffer.capacity());
      newValueBuffer.setZero(this.valueBuffer.capacity(), newValueBuffer.capacity() - this.valueBuffer.capacity());
      this.valueBuffer.getReferenceManager().release();
      this.valueBuffer = newValueBuffer;
      ArrowBuf newValidityBuffer = buffers.getValidityBuf();
      newValidityBuffer.setBytes(0L, this.validityBuffer, 0L, this.validityBuffer.capacity());
      newValidityBuffer.setZero(this.validityBuffer.capacity(), newValidityBuffer.capacity() - this.validityBuffer.capacity());
      this.validityBuffer.getReferenceManager().release();
      this.validityBuffer = newValidityBuffer;
      this.refreshValueCapacity();
      this.lastValueCapacity = this.getValueCapacity();
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use getFieldBuffers");
   }

   public void initializeChildrenFromFields(List children) {
      if (!children.isEmpty()) {
         throw new IllegalArgumentException("primitive type vector cannot have children");
      }
   }

   public List getChildrenFromFields() {
      return Collections.emptyList();
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      if (ownBuffers.size() != 2) {
         throw new IllegalArgumentException("Illegal buffer count, expected 2, got: " + ownBuffers.size());
      } else {
         ArrowBuf bitBuffer = (ArrowBuf)ownBuffers.get(0);
         ArrowBuf dataBuffer = (ArrowBuf)ownBuffers.get(1);
         this.validityBuffer.getReferenceManager().release();
         this.validityBuffer = BitVectorHelper.loadValidityBuffer(fieldNode, bitBuffer, this.allocator);
         this.valueBuffer.getReferenceManager().release();
         this.valueBuffer = dataBuffer.getReferenceManager().retain(dataBuffer, this.allocator);
         this.refreshValueCapacity();
         this.valueCount = fieldNode.getLength();
      }
   }

   public List getFieldBuffers() {
      List<ArrowBuf> result = new ArrayList(2);
      this.setReaderAndWriterIndex();
      result.add(this.validityBuffer);
      result.add(this.valueBuffer);
      return result;
   }

   private void setReaderAndWriterIndex() {
      this.validityBuffer.readerIndex(0L);
      this.valueBuffer.readerIndex(0L);
      if (this.valueCount == 0) {
         this.validityBuffer.writerIndex(0L);
         this.valueBuffer.writerIndex(0L);
      } else {
         this.validityBuffer.writerIndex((long)getValidityBufferSizeFromCount(this.valueCount));
         if (this.typeWidth == 0) {
            this.valueBuffer.writerIndex((long)getValidityBufferSizeFromCount(this.valueCount));
         } else {
            this.valueBuffer.writerIndex((long)this.valueCount * (long)this.typeWidth);
         }
      }

   }

   public void validateScalars() {
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return this.getTransferPair(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return this.getTransferPair(field, allocator);
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return this.getTransferPair(this.getName(), allocator);
   }

   public abstract TransferPair getTransferPair(String var1, BufferAllocator var2);

   public abstract TransferPair getTransferPair(Field var1, BufferAllocator var2);

   public void transferTo(BaseFixedWidthVector target) {
      this.compareTypes(target, "transferTo");
      target.clear();
      target.validityBuffer = transferBuffer(this.validityBuffer, target.allocator);
      target.valueBuffer = transferBuffer(this.valueBuffer, target.allocator);
      target.valueCount = this.valueCount;
      target.refreshValueCapacity();
      this.clear();
   }

   public void splitAndTransferTo(int startIndex, int length, BaseFixedWidthVector target) {
      Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, this.valueCount);
      this.compareTypes(target, "splitAndTransferTo");
      target.clear();
      this.splitAndTransferValidityBuffer(startIndex, length, target);
      this.splitAndTransferValueBuffer(startIndex, length, target);
      target.setValueCount(length);
   }

   private void splitAndTransferValueBuffer(int startIndex, int length, BaseFixedWidthVector target) {
      int startPoint = startIndex * this.typeWidth;
      int sliceLength = length * this.typeWidth;
      ArrowBuf slicedBuffer = this.valueBuffer.slice((long)startPoint, (long)sliceLength);
      target.valueBuffer = transferBuffer(slicedBuffer, target.allocator);
      target.refreshValueCapacity();
   }

   private void splitAndTransferValidityBuffer(int startIndex, int length, BaseFixedWidthVector target) {
      int firstByteSource = BitVectorHelper.byteIndex(startIndex);
      int lastByteSource = BitVectorHelper.byteIndex(this.valueCount - 1);
      int byteSizeTarget = getValidityBufferSizeFromCount(length);
      int offset = startIndex % 8;
      if (length > 0) {
         if (offset == 0) {
            if (target.validityBuffer != null) {
               target.validityBuffer.getReferenceManager().release();
            }

            ArrowBuf slicedValidityBuffer = this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
            target.validityBuffer = transferBuffer(slicedValidityBuffer, target.allocator);
            target.refreshValueCapacity();
         } else {
            target.allocateValidityBuffer(byteSizeTarget);

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

   public int getNullCount() {
      return BitVectorHelper.getNullCount(this.validityBuffer, this.valueCount);
   }

   public int getValueCount() {
      return this.valueCount;
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;
      int currentValueCapacity = this.getValueCapacity();

      while(valueCount > this.getValueCapacity()) {
         this.reAlloc();
      }

      if (valueCount > 0) {
         if (currentValueCapacity >= valueCount * 2) {
            this.incrementAllocationMonitor();
         } else if (currentValueCapacity <= valueCount / 2) {
            this.decrementAllocationMonitor();
         }
      }

      this.setReaderAndWriterIndex();
   }

   public boolean isSafe(int index) {
      return index < this.getValueCapacity();
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
      this.handleSafe(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
   }

   public void set(int index, byte[] value, int start, int length) {
      throw new UnsupportedOperationException();
   }

   public void setSafe(int index, byte[] value, int start, int length) {
      throw new UnsupportedOperationException();
   }

   public void set(int index, ByteBuffer value, int start, int length) {
      throw new UnsupportedOperationException();
   }

   public void setSafe(int index, ByteBuffer value, int start, int length) {
      throw new UnsupportedOperationException();
   }

   protected void handleSafe(int index) {
      while(index >= this.getValueCapacity()) {
         this.decrementAllocationMonitor();
         this.reAlloc();
      }

   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      if (from.isNull(fromIndex)) {
         BitVectorHelper.unsetBit(this.getValidityBuffer(), thisIndex);
      } else {
         BitVectorHelper.setBit(this.getValidityBuffer(), (long)thisIndex);
         MemoryUtil.copyMemory(from.getDataBuffer().memoryAddress() + (long)fromIndex * (long)this.typeWidth, this.getDataBuffer().memoryAddress() + (long)thisIndex * (long)this.typeWidth, (long)this.typeWidth);
      }

   }

   public void copyFromSafe(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      this.handleSafe(thisIndex);
      this.copyFrom(fromIndex, thisIndex, from);
   }

   public void setNull(int index) {
      this.handleSafe(index);
      BitVectorHelper.unsetBit(this.validityBuffer, index);
   }

   public ArrowBufPointer getDataPointer(int index) {
      return this.getDataPointer(index, new ArrowBufPointer());
   }

   public ArrowBufPointer getDataPointer(int index, ArrowBufPointer reuse) {
      if (this.isNull(index)) {
         reuse.set((ArrowBuf)null, 0L, 0L);
      } else {
         reuse.set(this.valueBuffer, (long)index * (long)this.typeWidth, (long)this.typeWidth);
      }

      return reuse;
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      if (this.isNull(index)) {
         return 0;
      } else {
         long start = (long)this.typeWidth * (long)index;
         long end = (long)this.typeWidth * (long)(index + 1);
         return ByteFunctionHelpers.hash(hasher, this.getDataBuffer(), start, end);
      }
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }
}
