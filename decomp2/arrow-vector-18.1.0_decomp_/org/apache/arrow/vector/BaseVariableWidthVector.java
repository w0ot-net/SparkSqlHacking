package org.apache.arrow.vector;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.ArrowBufPointer;
import org.apache.arrow.memory.util.ByteFunctionHelpers;
import org.apache.arrow.memory.util.CommonUtil;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.TransferPair;

public abstract class BaseVariableWidthVector extends BaseValueVector implements VariableWidthFieldVector {
   private static final int DEFAULT_RECORD_BYTE_COUNT = 8;
   private static final int INITIAL_BYTE_COUNT = 31760;
   private static final int MAX_BUFFER_SIZE;
   private int lastValueCapacity;
   private long lastValueAllocationSizeInBytes;
   public static final int OFFSET_WIDTH = 4;
   protected static final byte[] emptyByteArray;
   protected ArrowBuf validityBuffer;
   protected ArrowBuf valueBuffer;
   protected ArrowBuf offsetBuffer;
   protected int valueCount;
   protected int lastSet;
   protected final Field field;

   public BaseVariableWidthVector(Field field, BufferAllocator allocator) {
      super(allocator);
      this.field = field;
      this.lastValueAllocationSizeInBytes = 31760L;
      this.lastValueCapacity = 3969;
      this.valueCount = 0;
      this.lastSet = -1;
      this.offsetBuffer = allocator.getEmpty();
      this.validityBuffer = allocator.getEmpty();
      this.valueBuffer = allocator.getEmpty();
   }

   public String getName() {
      return this.field.getName();
   }

   public ArrowBuf getValidityBuffer() {
      return this.validityBuffer;
   }

   public ArrowBuf getDataBuffer() {
      return this.valueBuffer;
   }

   public ArrowBuf getOffsetBuffer() {
      return this.offsetBuffer;
   }

   public long getOffsetBufferAddress() {
      return this.offsetBuffer.memoryAddress();
   }

   public long getValidityBufferAddress() {
      return this.validityBuffer.memoryAddress();
   }

   public long getDataBufferAddress() {
      return this.valueBuffer.memoryAddress();
   }

   public void setInitialCapacity(int valueCount) {
      long size = (long)valueCount * 8L;
      this.checkDataBufferSize(size);
      this.computeAndCheckOffsetsBufferSize(valueCount);
      this.lastValueAllocationSizeInBytes = (long)((int)size);
      this.lastValueCapacity = valueCount;
   }

   public void setInitialCapacity(int valueCount, double density) {
      long size = Math.max((long)((double)valueCount * density), 1L);
      this.checkDataBufferSize(size);
      this.computeAndCheckOffsetsBufferSize(valueCount);
      this.lastValueAllocationSizeInBytes = (long)((int)size);
      this.lastValueCapacity = valueCount;
   }

   public double getDensity() {
      if (this.valueCount == 0) {
         return (double)0.0F;
      } else {
         int startOffset = this.getStartOffset(0);
         int endOffset = this.getStartOffset(this.valueCount);
         double totalListSize = (double)(endOffset - startOffset);
         return totalListSize / (double)this.valueCount;
      }
   }

   public int getValueCapacity() {
      int offsetValueCapacity = Math.max(this.getOffsetBufferValueCapacity() - 1, 0);
      return Math.min(offsetValueCapacity, this.getValidityBufferValueCapacity());
   }

   private int getValidityBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.validityBuffer.capacity() * 8L);
   }

   private int getOffsetBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.offsetBuffer.capacity() / 4L);
   }

   public void zeroVector() {
      this.initValidityBuffer();
      this.initOffsetBuffer();
      this.valueBuffer.setZero(0L, this.valueBuffer.capacity());
   }

   private void initValidityBuffer() {
      this.validityBuffer.setZero(0L, this.validityBuffer.capacity());
   }

   private void initOffsetBuffer() {
      this.offsetBuffer.setZero(0L, this.offsetBuffer.capacity());
   }

   public void reset() {
      this.zeroVector();
      this.lastSet = -1;
      this.valueCount = 0;
   }

   public void close() {
      this.clear();
   }

   public void clear() {
      this.validityBuffer = this.releaseBuffer(this.validityBuffer);
      this.valueBuffer = this.releaseBuffer(this.valueBuffer);
      this.offsetBuffer = this.releaseBuffer(this.offsetBuffer);
      this.lastSet = -1;
      this.valueCount = 0;
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
      ArrowBuf bitBuffer = (ArrowBuf)ownBuffers.get(0);
      ArrowBuf offBuffer = (ArrowBuf)ownBuffers.get(1);
      ArrowBuf dataBuffer = (ArrowBuf)ownBuffers.get(2);
      this.validityBuffer.getReferenceManager().release();
      this.validityBuffer = BitVectorHelper.loadValidityBuffer(fieldNode, bitBuffer, this.allocator);
      this.offsetBuffer.getReferenceManager().release();
      this.offsetBuffer = offBuffer.getReferenceManager().retain(offBuffer, this.allocator);
      this.valueBuffer.getReferenceManager().release();
      this.valueBuffer = dataBuffer.getReferenceManager().retain(dataBuffer, this.allocator);
      this.lastSet = fieldNode.getLength() - 1;
      this.valueCount = fieldNode.getLength();
   }

   public List getFieldBuffers() {
      this.fillHoles(this.valueCount);
      List<ArrowBuf> result = new ArrayList(3);
      this.setReaderAndWriterIndex();
      result.add(this.validityBuffer);
      result.add(this.offsetBuffer);
      result.add(this.valueBuffer);
      return result;
   }

   public void exportCDataBuffers(List buffers, ArrowBuf buffersPtr, long nullValue) {
      this.fillHoles(this.valueCount);
      this.exportBuffer(this.validityBuffer, buffers, buffersPtr, nullValue, true);
      if (this.offsetBuffer.capacity() == 0L) {
         this.exportBuffer(this.allocateOffsetBuffer(4L), buffers, buffersPtr, nullValue, false);
      } else {
         this.exportBuffer(this.offsetBuffer, buffers, buffersPtr, nullValue, true);
      }

      this.exportBuffer(this.valueBuffer, buffers, buffersPtr, nullValue, true);
   }

   private void setReaderAndWriterIndex() {
      this.validityBuffer.readerIndex(0L);
      this.offsetBuffer.readerIndex(0L);
      this.valueBuffer.readerIndex(0L);
      if (this.valueCount == 0) {
         this.validityBuffer.writerIndex(0L);
         this.offsetBuffer.writerIndex(0L);
         this.valueBuffer.writerIndex(0L);
      } else {
         int lastDataOffset = this.getStartOffset(this.valueCount);
         this.validityBuffer.writerIndex((long)getValidityBufferSizeFromCount(this.valueCount));
         this.offsetBuffer.writerIndex((long)(this.valueCount + 1) * 4L);
         this.valueBuffer.writerIndex((long)lastDataOffset);
      }

   }

   public void allocateNew() {
      this.allocateNew(this.lastValueAllocationSizeInBytes, this.lastValueCapacity);
   }

   public boolean allocateNewSafe() {
      try {
         this.allocateNew(this.lastValueAllocationSizeInBytes, this.lastValueCapacity);
         return true;
      } catch (Exception var2) {
         return false;
      }
   }

   public void allocateNew(long totalBytes, int valueCount) {
      assert totalBytes >= 0L;

      this.checkDataBufferSize(totalBytes);
      this.computeAndCheckOffsetsBufferSize(valueCount);
      this.clear();

      try {
         this.allocateBytes(totalBytes, valueCount);
      } catch (Exception e) {
         this.clear();
         throw e;
      }
   }

   public void allocateNew(int valueCount) {
      this.allocateNew(this.lastValueAllocationSizeInBytes, valueCount);
   }

   private void checkDataBufferSize(long size) {
      if (size > (long)MAX_BUFFER_SIZE || size < 0L) {
         throw new OversizedAllocationException("Memory required for vector is (" + size + "), which is overflow or more than max allowed (" + MAX_BUFFER_SIZE + "). You could consider using LargeVarCharVector/LargeVarBinaryVector for large strings/large bytes types");
      }
   }

   private long computeAndCheckOffsetsBufferSize(int valueCount) {
      long size = this.computeCombinedBufferSize(valueCount + 1, 4);
      if (size > (long)MAX_BUFFER_SIZE) {
         throw new OversizedAllocationException("Memory required for vector capacity " + valueCount + " is (" + size + "), which is more than max allowed (" + MAX_BUFFER_SIZE + ")");
      } else {
         return size;
      }
   }

   private void allocateBytes(long valueBufferSize, int valueCount) {
      this.valueBuffer = this.allocator.buffer(valueBufferSize);
      this.valueBuffer.readerIndex(0L);
      BaseValueVector.DataAndValidityBuffers buffers = this.allocFixedDataAndValidityBufs(valueCount + 1, 4);
      this.offsetBuffer = buffers.getDataBuf();
      this.validityBuffer = buffers.getValidityBuf();
      this.initOffsetBuffer();
      this.initValidityBuffer();
      this.lastValueCapacity = this.getValueCapacity();
      this.lastValueAllocationSizeInBytes = (long)LargeMemoryUtil.capAtMaxInt(this.valueBuffer.capacity());
   }

   private ArrowBuf allocateOffsetBuffer(long size) {
      int curSize = (int)size;
      ArrowBuf offsetBuffer = this.allocator.buffer((long)curSize);
      offsetBuffer.readerIndex(0L);
      this.initOffsetBuffer();
      return offsetBuffer;
   }

   private void allocateValidityBuffer(long size) {
      int curSize = (int)size;
      this.validityBuffer = this.allocator.buffer((long)curSize);
      this.validityBuffer.readerIndex(0L);
      this.initValidityBuffer();
   }

   public void reAlloc() {
      this.reallocDataBuffer();
      this.reallocValidityAndOffsetBuffers();
   }

   public void reallocDataBuffer() {
      long currentBufferCapacity = this.valueBuffer.capacity();
      long newAllocationSize = currentBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.lastValueAllocationSizeInBytes > 0L) {
            newAllocationSize = this.lastValueAllocationSizeInBytes;
         } else {
            newAllocationSize = 63520L;
         }
      }

      this.reallocDataBuffer(newAllocationSize);
   }

   public void reallocDataBuffer(long desiredAllocSize) {
      if (desiredAllocSize != 0L) {
         long newAllocationSize = CommonUtil.nextPowerOfTwo(desiredAllocSize);

         assert newAllocationSize >= 1L;

         this.checkDataBufferSize(newAllocationSize);
         ArrowBuf newBuf = this.allocator.buffer(newAllocationSize);
         newBuf.setBytes(0L, this.valueBuffer, 0L, this.valueBuffer.capacity());
         this.valueBuffer.getReferenceManager().release();
         this.valueBuffer = newBuf;
         this.lastValueAllocationSizeInBytes = this.valueBuffer.capacity();
      }
   }

   public void reallocValidityAndOffsetBuffers() {
      int targetOffsetCount = LargeMemoryUtil.capAtMaxInt(this.offsetBuffer.capacity() / 4L * 2L);
      if (targetOffsetCount == 0) {
         if (this.lastValueCapacity > 0) {
            targetOffsetCount = this.lastValueCapacity + 1;
         } else {
            targetOffsetCount = 7942;
         }
      }

      this.computeAndCheckOffsetsBufferSize(targetOffsetCount);
      BaseValueVector.DataAndValidityBuffers buffers = this.allocFixedDataAndValidityBufs(targetOffsetCount, 4);
      ArrowBuf newOffsetBuffer = buffers.getDataBuf();
      newOffsetBuffer.setBytes(0L, this.offsetBuffer, 0L, this.offsetBuffer.capacity());
      newOffsetBuffer.setZero(this.offsetBuffer.capacity(), newOffsetBuffer.capacity() - this.offsetBuffer.capacity());
      this.offsetBuffer.getReferenceManager().release();
      this.offsetBuffer = newOffsetBuffer;
      ArrowBuf newValidityBuffer = buffers.getValidityBuf();
      newValidityBuffer.setBytes(0L, this.validityBuffer, 0L, this.validityBuffer.capacity());
      newValidityBuffer.setZero(this.validityBuffer.capacity(), newValidityBuffer.capacity() - this.validityBuffer.capacity());
      this.validityBuffer.getReferenceManager().release();
      this.validityBuffer = newValidityBuffer;
      this.lastValueCapacity = this.getValueCapacity();
   }

   public int getByteCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.valueBuffer.capacity());
   }

   public int sizeOfValueBuffer() {
      return this.valueCount == 0 ? 0 : this.offsetBuffer.getInt((long)this.valueCount * 4L);
   }

   public int getBufferSize() {
      return this.getBufferSizeFor(this.valueCount);
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         int validityBufferSize = getValidityBufferSizeFromCount(valueCount);
         int offsetBufferSize = (valueCount + 1) * 4;
         int dataBufferSize = this.offsetBuffer.getInt((long)valueCount * 4L);
         return validityBufferSize + offsetBufferSize + dataBufferSize;
      }
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
         buffers = new ArrowBuf[3];
         buffers[0] = this.validityBuffer;
         buffers[1] = this.offsetBuffer;
         buffers[2] = this.valueBuffer;
      }

      if (clear) {
         for(ArrowBuf buffer : buffers) {
            buffer.getReferenceManager().retain();
         }

         this.clear();
      }

      return buffers;
   }

   public void validateScalars() {
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return this.getTransferPair(field, allocator);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return this.getTransferPair(ref, allocator);
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return this.getTransferPair(this.getName(), allocator);
   }

   public abstract TransferPair getTransferPair(String var1, BufferAllocator var2);

   public abstract TransferPair getTransferPair(Field var1, BufferAllocator var2);

   public void transferTo(BaseVariableWidthVector target) {
      this.compareTypes(target, "transferTo");
      target.clear();
      target.validityBuffer = transferBuffer(this.validityBuffer, target.allocator);
      target.valueBuffer = transferBuffer(this.valueBuffer, target.allocator);
      target.offsetBuffer = transferBuffer(this.offsetBuffer, target.allocator);
      target.setLastSet(this.lastSet);
      if (this.valueCount > 0) {
         target.setValueCount(this.valueCount);
      }

      this.clear();
   }

   public void splitAndTransferTo(int startIndex, int length, BaseVariableWidthVector target) {
      Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, this.valueCount);
      this.compareTypes(target, "splitAndTransferTo");
      target.clear();
      if (length > 0) {
         this.splitAndTransferValidityBuffer(startIndex, length, target);
         this.splitAndTransferOffsetBuffer(startIndex, length, target);
         target.setLastSet(length - 1);
         target.setValueCount(length);
      }

   }

   private void splitAndTransferOffsetBuffer(int startIndex, int length, BaseVariableWidthVector target) {
      int start = this.getStartOffset(startIndex);
      int end = this.getStartOffset(startIndex + length);
      int dataLength = end - start;
      if (start == 0) {
         ArrowBuf slicedOffsetBuffer = this.offsetBuffer.slice((long)startIndex * 4L, (long)(1 + length) * 4L);
         target.offsetBuffer = transferBuffer(slicedOffsetBuffer, target.allocator);
      } else {
         target.offsetBuffer = target.allocateOffsetBuffer((long)(length + 1) * 4L);

         for(int i = 0; i < length + 1; ++i) {
            int relativeSourceOffset = this.getStartOffset(startIndex + i) - start;
            target.offsetBuffer.setInt((long)i * 4L, relativeSourceOffset);
         }
      }

      ArrowBuf slicedBuffer = this.valueBuffer.slice((long)start, (long)dataLength);
      target.valueBuffer = transferBuffer(slicedBuffer, target.allocator);
   }

   private void splitAndTransferValidityBuffer(int startIndex, int length, BaseVariableWidthVector target) {
      if (length > 0) {
         int firstByteSource = BitVectorHelper.byteIndex(startIndex);
         int lastByteSource = BitVectorHelper.byteIndex(this.valueCount - 1);
         int byteSizeTarget = getValidityBufferSizeFromCount(length);
         int offset = startIndex % 8;
         if (offset == 0) {
            if (target.validityBuffer != null) {
               target.validityBuffer.getReferenceManager().release();
            }

            ArrowBuf slicedValidityBuffer = this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
            target.validityBuffer = transferBuffer(slicedValidityBuffer, target.allocator);
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

   public int getNullCount() {
      return BitVectorHelper.getNullCount(this.validityBuffer, this.valueCount);
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

   public int getValueCount() {
      return this.valueCount;
   }

   public void setValueCount(int valueCount) {
      assert valueCount >= 0;

      this.valueCount = valueCount;

      while(valueCount > this.getValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      this.fillHoles(valueCount);
      this.lastSet = valueCount - 1;
      this.setReaderAndWriterIndex();
   }

   public void fillEmpties(int index) {
      this.handleSafe(index, emptyByteArray.length);
      this.fillHoles(index);
      this.lastSet = index - 1;
   }

   public void setLastSet(int value) {
      this.lastSet = value;
   }

   public int getLastSet() {
      return this.lastSet;
   }

   public long getStartEnd(int index) {
      return this.offsetBuffer.getLong((long)index * 4L);
   }

   public void setIndexDefined(int index) {
      while(index >= this.getValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
   }

   public void setValueLengthSafe(int index, int length) {
      assert index >= 0;

      this.handleSafe(index, length);
      this.fillHoles(index);
      int startOffset = this.getStartOffset(index);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + length);
      this.lastSet = index;
   }

   public int getValueLength(int index) {
      assert index >= 0;

      if (this.isSet(index) == 0) {
         return 0;
      } else {
         int startOffset = this.getStartOffset(index);
         int dataLength = this.getEndOffset(index) - startOffset;
         return dataLength;
      }
   }

   public void set(int index, byte[] value) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, value, 0, value.length);
      this.lastSet = index;
   }

   public void setSafe(int index, byte[] value) {
      assert index >= 0;

      this.handleSafe(index, value.length);
      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, value, 0, value.length);
      this.lastSet = index;
   }

   public void set(int index, byte[] value, int start, int length) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, value, start, length);
      this.lastSet = index;
   }

   public void setSafe(int index, byte[] value, int start, int length) {
      assert index >= 0;

      this.handleSafe(index, length);
      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, value, start, length);
      this.lastSet = index;
   }

   public void set(int index, ByteBuffer value, int start, int length) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int startOffset = this.getStartOffset(index);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + length);
      this.valueBuffer.setBytes((long)startOffset, value, start, length);
      this.lastSet = index;
   }

   public void setSafe(int index, ByteBuffer value, int start, int length) {
      assert index >= 0;

      this.handleSafe(index, length);
      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int startOffset = this.getStartOffset(index);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + length);
      this.valueBuffer.setBytes((long)startOffset, value, start, length);
      this.lastSet = index;
   }

   public void setNull(int index) {
      while(index >= this.getValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      BitVectorHelper.unsetBit(this.validityBuffer, index);
   }

   public void set(int index, int isSet, int start, int end, ArrowBuf buffer) {
      assert index >= 0;

      int dataLength = end - start;
      this.fillHoles(index);
      BitVectorHelper.setValidityBit(this.validityBuffer, index, isSet);
      int startOffset = this.offsetBuffer.getInt((long)index * 4L);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + dataLength);
      this.valueBuffer.setBytes((long)startOffset, buffer, (long)start, (long)dataLength);
      this.lastSet = index;
   }

   public void setSafe(int index, int isSet, int start, int end, ArrowBuf buffer) {
      assert index >= 0;

      int dataLength = end - start;
      this.handleSafe(index, dataLength);
      this.fillHoles(index);
      BitVectorHelper.setValidityBit(this.validityBuffer, index, isSet);
      int startOffset = this.offsetBuffer.getInt((long)index * 4L);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + dataLength);
      this.valueBuffer.setBytes((long)startOffset, buffer, (long)start, (long)dataLength);
      this.lastSet = index;
   }

   public void set(int index, int start, int length, ArrowBuf buffer) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int startOffset = this.offsetBuffer.getInt((long)index * 4L);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + length);
      ArrowBuf bb = buffer.slice((long)start, (long)length);
      this.valueBuffer.setBytes((long)startOffset, bb);
      this.lastSet = index;
   }

   public void setSafe(int index, int start, int length, ArrowBuf buffer) {
      assert index >= 0;

      this.handleSafe(index, length);
      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      int startOffset = this.getStartOffset(index);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + length);
      ArrowBuf bb = buffer.slice((long)start, (long)length);
      this.valueBuffer.setBytes((long)startOffset, bb);
      this.lastSet = index;
   }

   protected final void fillHoles(int index) {
      for(int i = this.lastSet + 1; i < index; ++i) {
         this.setBytes(i, emptyByteArray, 0, emptyByteArray.length);
      }

      this.lastSet = index - 1;
   }

   protected final void setBytes(int index, byte[] value, int start, int length) {
      int startOffset = this.getStartOffset(index);
      this.offsetBuffer.setInt((long)(index + 1) * 4L, startOffset + length);
      this.valueBuffer.setBytes((long)startOffset, value, start, (long)length);
   }

   public final int getStartOffset(int index) {
      return this.offsetBuffer.getInt((long)index * 4L);
   }

   protected final void handleSafe(int index, int dataLength) {
      while(index >= this.getValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      long startOffset = this.lastSet < 0 ? 0L : (long)this.getStartOffset(this.lastSet + 1);
      long targetCapacity = startOffset + (long)dataLength;
      if (this.valueBuffer.capacity() < targetCapacity) {
         this.reallocDataBuffer(targetCapacity);
      }

   }

   public static byte[] get(ArrowBuf data, ArrowBuf offset, int index) {
      int currentStartOffset = offset.getInt((long)index * 4L);
      int dataLength = offset.getInt((long)(index + 1) * 4L) - currentStartOffset;
      byte[] result = new byte[dataLength];
      data.getBytes((long)currentStartOffset, result, 0, dataLength);
      return result;
   }

   public static ArrowBuf set(ArrowBuf buffer, BufferAllocator allocator, int valueCount, int index, int value) {
      if (buffer == null) {
         buffer = allocator.buffer((long)valueCount * 4L);
      }

      buffer.setInt((long)index * 4L, value);
      if (index == valueCount - 1) {
         buffer.writerIndex((long)valueCount * 4L);
      }

      return buffer;
   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      if (from.isNull(fromIndex)) {
         this.fillHoles(thisIndex);
         BitVectorHelper.unsetBit(this.validityBuffer, thisIndex);
         int copyStart = this.offsetBuffer.getInt((long)thisIndex * 4L);
         this.offsetBuffer.setInt((long)(thisIndex + 1) * 4L, copyStart);
      } else {
         int start = from.getOffsetBuffer().getInt((long)fromIndex * 4L);
         int end = from.getOffsetBuffer().getInt((long)(fromIndex + 1) * 4L);
         int length = end - start;
         this.fillHoles(thisIndex);
         BitVectorHelper.setBit(this.validityBuffer, (long)thisIndex);
         int copyStart = this.getStartOffset(thisIndex);
         from.getDataBuffer().getBytes((long)start, this.valueBuffer, (long)copyStart, length);
         this.offsetBuffer.setInt((long)(thisIndex + 1) * 4L, copyStart + length);
      }

      this.lastSet = thisIndex;
   }

   public void copyFromSafe(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      if (from.isNull(fromIndex)) {
         this.handleSafe(thisIndex, 0);
         this.fillHoles(thisIndex);
         BitVectorHelper.unsetBit(this.validityBuffer, thisIndex);
         int copyStart = this.getStartOffset(thisIndex);
         this.offsetBuffer.setInt((long)(thisIndex + 1) * 4L, copyStart);
      } else {
         int start = from.getOffsetBuffer().getInt((long)fromIndex * 4L);
         int end = from.getOffsetBuffer().getInt((long)(fromIndex + 1) * 4L);
         int length = end - start;
         this.handleSafe(thisIndex, length);
         this.fillHoles(thisIndex);
         BitVectorHelper.setBit(this.validityBuffer, (long)thisIndex);
         int copyStart = this.getStartOffset(thisIndex);
         from.getDataBuffer().getBytes((long)start, this.valueBuffer, (long)copyStart, length);
         this.offsetBuffer.setInt((long)(thisIndex + 1) * 4L, copyStart + length);
      }

      this.lastSet = thisIndex;
   }

   public ArrowBufPointer getDataPointer(int index) {
      return this.getDataPointer(index, new ArrowBufPointer());
   }

   public ArrowBufPointer getDataPointer(int index, ArrowBufPointer reuse) {
      if (this.isNull(index)) {
         reuse.set((ArrowBuf)null, 0L, 0L);
      } else {
         int offset = this.getStartOffset(index);
         int length = this.getEndOffset(index) - offset;
         reuse.set(this.valueBuffer, (long)offset, (long)length);
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
         int start = this.getStartOffset(index);
         int end = this.getEndOffset(index);
         return ByteFunctionHelpers.hash(hasher, this.getDataBuffer(), (long)start, (long)end);
      }
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public final int getEndOffset(int index) {
      return this.offsetBuffer.getInt((long)(index + 1) * 4L);
   }

   static {
      MAX_BUFFER_SIZE = (int)Math.min(MAX_ALLOCATION_SIZE, 2147483647L);
      emptyByteArray = new byte[0];
   }
}
