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

public abstract class BaseLargeVariableWidthVector extends BaseValueVector implements VariableWidthFieldVector {
   private static final int DEFAULT_RECORD_BYTE_COUNT = 12;
   private static final int INITIAL_BYTE_COUNT = 47640;
   private int lastValueCapacity;
   private long lastValueAllocationSizeInBytes;
   public static final int OFFSET_WIDTH = 8;
   protected static final byte[] emptyByteArray = new byte[0];
   protected ArrowBuf validityBuffer;
   protected ArrowBuf valueBuffer;
   protected ArrowBuf offsetBuffer;
   protected int valueCount;
   protected int lastSet;
   protected final Field field;

   public BaseLargeVariableWidthVector(Field field, BufferAllocator allocator) {
      super(allocator);
      this.field = field;
      this.lastValueAllocationSizeInBytes = 47640L;
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
      long size = (long)valueCount * 12L;
      this.checkDataBufferSize(size);
      this.computeAndCheckOffsetsBufferSize(valueCount);
      this.lastValueAllocationSizeInBytes = size;
      this.lastValueCapacity = valueCount;
   }

   public void setInitialCapacity(int valueCount, double density) {
      long size = Math.max((long)((double)valueCount * density), 1L);
      this.checkDataBufferSize(size);
      this.computeAndCheckOffsetsBufferSize(valueCount);
      this.lastValueAllocationSizeInBytes = size;
      this.lastValueCapacity = valueCount;
   }

   public double getDensity() {
      if (this.valueCount == 0) {
         return (double)0.0F;
      } else {
         long startOffset = this.getStartOffset(0);
         long endOffset = this.getStartOffset(this.valueCount);
         double totalListSize = (double)(endOffset - startOffset);
         return totalListSize / (double)this.valueCount;
      }
   }

   public int getValueCapacity() {
      long offsetValueCapacity = Math.max(this.getOffsetBufferValueCapacity() - 1L, 0L);
      return LargeMemoryUtil.capAtMaxInt(Math.min(offsetValueCapacity, this.getValidityBufferValueCapacity()));
   }

   private long getValidityBufferValueCapacity() {
      return this.validityBuffer.capacity() * 8L;
   }

   private long getOffsetBufferValueCapacity() {
      return this.offsetBuffer.capacity() / 8L;
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
         this.exportBuffer(this.allocateOffsetBuffer(8L), buffers, buffersPtr, nullValue, false);
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
         long lastDataOffset = this.getStartOffset(this.valueCount);
         this.validityBuffer.writerIndex((long)getValidityBufferSizeFromCount(this.valueCount));
         this.offsetBuffer.writerIndex((long)(this.valueCount + 1) * 8L);
         this.valueBuffer.writerIndex(lastDataOffset);
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
      if (size > MAX_ALLOCATION_SIZE || size < 0L) {
         throw new OversizedAllocationException("Memory required for vector  is (" + size + "), which is more than max allowed (" + MAX_ALLOCATION_SIZE + ")");
      }
   }

   private long computeAndCheckOffsetsBufferSize(int valueCount) {
      long size = this.computeCombinedBufferSize(valueCount + 1, 8);
      if (size > MAX_ALLOCATION_SIZE) {
         throw new OversizedAllocationException("Memory required for vector capacity " + valueCount + " is (" + size + "), which is more than max allowed (" + MAX_ALLOCATION_SIZE + ")");
      } else {
         return size;
      }
   }

   private void allocateBytes(long valueBufferSize, int valueCount) {
      this.valueBuffer = this.allocator.buffer(valueBufferSize);
      this.valueBuffer.readerIndex(0L);
      BaseValueVector.DataAndValidityBuffers buffers = this.allocFixedDataAndValidityBufs(valueCount + 1, 8);
      this.offsetBuffer = buffers.getDataBuf();
      this.validityBuffer = buffers.getValidityBuf();
      this.initOffsetBuffer();
      this.initValidityBuffer();
      this.lastValueCapacity = this.getValueCapacity();
      this.lastValueAllocationSizeInBytes = (long)LargeMemoryUtil.capAtMaxInt(this.valueBuffer.capacity());
   }

   private ArrowBuf allocateOffsetBuffer(long size) {
      ArrowBuf offsetBuffer = this.allocator.buffer(size);
      offsetBuffer.readerIndex(0L);
      this.initOffsetBuffer();
      return offsetBuffer;
   }

   private void allocateValidityBuffer(long size) {
      this.validityBuffer = this.allocator.buffer(size);
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
            newAllocationSize = 95280L;
         }
      }

      newAllocationSize = CommonUtil.nextPowerOfTwo(newAllocationSize);

      assert newAllocationSize >= 1L;

      this.checkDataBufferSize(newAllocationSize);
      ArrowBuf newBuf = this.allocator.buffer(newAllocationSize);
      newBuf.setBytes(0L, this.valueBuffer, 0L, currentBufferCapacity);
      this.valueBuffer.getReferenceManager().release();
      this.valueBuffer = newBuf;
      this.lastValueAllocationSizeInBytes = this.valueBuffer.capacity();
   }

   public void reallocValidityAndOffsetBuffers() {
      int targetOffsetCount = LargeMemoryUtil.capAtMaxInt(this.offsetBuffer.capacity() / 8L * 2L);
      if (targetOffsetCount == 0) {
         if (this.lastValueCapacity > 0) {
            targetOffsetCount = this.lastValueCapacity + 1;
         } else {
            targetOffsetCount = 7942;
         }
      }

      this.computeAndCheckOffsetsBufferSize(targetOffsetCount);
      BaseValueVector.DataAndValidityBuffers buffers = this.allocFixedDataAndValidityBufs(targetOffsetCount, 8);
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
      return this.valueCount == 0 ? 0 : LargeMemoryUtil.capAtMaxInt(this.getStartOffset(this.valueCount));
   }

   public int getBufferSize() {
      return this.getBufferSizeFor(this.valueCount);
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         long validityBufferSize = (long)getValidityBufferSizeFromCount(valueCount);
         long offsetBufferSize = (long)(valueCount + 1) * 8L;
         long dataBufferSize = this.getStartOffset(valueCount);
         return LargeMemoryUtil.capAtMaxInt(validityBufferSize + offsetBufferSize + dataBufferSize);
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

   public void transferTo(BaseLargeVariableWidthVector target) {
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

   public void splitAndTransferTo(int startIndex, int length, BaseLargeVariableWidthVector target) {
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

   private void splitAndTransferOffsetBuffer(int startIndex, int length, BaseLargeVariableWidthVector target) {
      long start = this.getStartOffset(startIndex);
      long end = this.getStartOffset(startIndex + length);
      long dataLength = end - start;
      target.offsetBuffer = target.allocateOffsetBuffer((long)(length + 1) * 8L);

      for(int i = 0; i < length + 1; ++i) {
         long relativeSourceOffset = this.getStartOffset(startIndex + i) - start;
         target.offsetBuffer.setLong((long)i * 8L, relativeSourceOffset);
      }

      ArrowBuf slicedBuffer = this.valueBuffer.slice(start, dataLength);
      target.valueBuffer = transferBuffer(slicedBuffer, target.allocator);
   }

   private void splitAndTransferValidityBuffer(int startIndex, int length, BaseLargeVariableWidthVector target) {
      int firstByteSource = BitVectorHelper.byteIndex(startIndex);
      int lastByteSource = BitVectorHelper.byteIndex(this.valueCount - 1);
      int byteSizeTarget = getValidityBufferSizeFromCount(length);
      int offset = startIndex % 8;
      if (length > 0) {
         if (offset == 0) {
            if (target.validityBuffer != null) {
               target.validityBuffer.getReferenceManager().release();
            }

            target.validityBuffer = this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
            target.validityBuffer.getReferenceManager().retain();
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
      long startOffset = this.getStartOffset(index);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)length);
      this.lastSet = index;
   }

   public int getValueLength(int index) {
      assert index >= 0;

      if (this.isSet(index) == 0) {
         return 0;
      } else {
         long startOffset = this.getStartOffset(index);
         int dataLength = (int)(this.getEndOffset(index) - startOffset);
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
      long startOffset = this.getStartOffset(index);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)length);
      this.valueBuffer.setBytes(startOffset, value, start, length);
      this.lastSet = index;
   }

   public void setSafe(int index, ByteBuffer value, int start, int length) {
      assert index >= 0;

      this.handleSafe(index, length);
      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      long startOffset = this.getStartOffset(index);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)length);
      this.valueBuffer.setBytes(startOffset, value, start, length);
      this.lastSet = index;
   }

   public void setNull(int index) {
      while(index >= this.getValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      BitVectorHelper.unsetBit(this.validityBuffer, index);
   }

   public void set(int index, int isSet, long start, long end, ArrowBuf buffer) {
      assert index >= 0;

      long dataLength = end - start;
      this.fillHoles(index);
      BitVectorHelper.setValidityBit(this.validityBuffer, index, isSet);
      long startOffset = this.offsetBuffer.getLong((long)index * 8L);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, end);
      this.valueBuffer.setBytes(startOffset, buffer, start, dataLength);
      this.lastSet = index;
   }

   public void setSafe(int index, int isSet, long start, long end, ArrowBuf buffer) {
      assert index >= 0;

      long dataLength = end - start;
      this.handleSafe(index, (int)dataLength);
      this.fillHoles(index);
      BitVectorHelper.setValidityBit(this.validityBuffer, index, isSet);
      long startOffset = this.offsetBuffer.getLong((long)index * 8L);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + dataLength);
      this.valueBuffer.setBytes(startOffset, buffer, start, dataLength);
      this.lastSet = index;
   }

   public void set(int index, long start, int length, ArrowBuf buffer) {
      assert index >= 0;

      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      long startOffset = this.offsetBuffer.getLong((long)index * 8L);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)length);
      ArrowBuf bb = buffer.slice(start, (long)length);
      this.valueBuffer.setBytes(startOffset, bb);
      this.lastSet = index;
   }

   public void setSafe(int index, long start, int length, ArrowBuf buffer) {
      assert index >= 0;

      this.handleSafe(index, length);
      this.fillHoles(index);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      long startOffset = this.offsetBuffer.getLong((long)index * 8L);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)length);
      ArrowBuf bb = buffer.slice(start, (long)length);
      this.valueBuffer.setBytes(startOffset, bb);
      this.lastSet = index;
   }

   protected final void fillHoles(int index) {
      for(int i = this.lastSet + 1; i < index; ++i) {
         this.setBytes(i, emptyByteArray, 0, emptyByteArray.length);
      }

      this.lastSet = index - 1;
   }

   protected final void setBytes(int index, byte[] value, int start, int length) {
      long startOffset = this.getStartOffset(index);
      this.offsetBuffer.setLong((long)(index + 1) * 8L, startOffset + (long)length);
      this.valueBuffer.setBytes(startOffset, value, start, (long)length);
   }

   protected final long getStartOffset(int index) {
      return this.offsetBuffer.getLong((long)index * 8L);
   }

   protected final void handleSafe(int index, int dataLength) {
      while(index >= this.getValueCapacity()) {
         this.reallocValidityAndOffsetBuffers();
      }

      long startOffset = this.lastSet < 0 ? 0L : this.getStartOffset(this.lastSet + 1);

      while(this.valueBuffer.capacity() < startOffset + (long)dataLength) {
         this.reallocDataBuffer();
      }

   }

   public static byte[] get(ArrowBuf data, ArrowBuf offset, int index) {
      long currentStartOffset = offset.getLong((long)index * 8L);
      int dataLength = (int)(offset.getLong((long)(index + 1) * 8L) - currentStartOffset);
      byte[] result = new byte[dataLength];
      data.getBytes(currentStartOffset, result, 0, dataLength);
      return result;
   }

   public static ArrowBuf set(ArrowBuf buffer, BufferAllocator allocator, int valueCount, int index, long value) {
      if (buffer == null) {
         buffer = allocator.buffer((long)valueCount * 8L);
      }

      buffer.setLong((long)index * 8L, value);
      if (index == valueCount - 1) {
         buffer.writerIndex((long)valueCount * 8L);
      }

      return buffer;
   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      if (from.isNull(fromIndex)) {
         this.fillHoles(thisIndex);
         BitVectorHelper.unsetBit(this.validityBuffer, thisIndex);
         long copyStart = this.offsetBuffer.getLong((long)thisIndex * 8L);
         this.offsetBuffer.setLong((long)(thisIndex + 1) * 8L, copyStart);
      } else {
         long start = from.getOffsetBuffer().getLong((long)fromIndex * 8L);
         long end = from.getOffsetBuffer().getLong((long)(fromIndex + 1) * 8L);
         long length = end - start;
         this.fillHoles(thisIndex);
         BitVectorHelper.setBit(this.validityBuffer, (long)thisIndex);
         long copyStart = this.getStartOffset(thisIndex);
         from.getDataBuffer().getBytes(start, this.valueBuffer, copyStart, (int)length);
         this.offsetBuffer.setLong((long)(thisIndex + 1) * 8L, copyStart + length);
      }

      this.lastSet = thisIndex;
   }

   public void copyFromSafe(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      if (from.isNull(fromIndex)) {
         this.handleSafe(thisIndex, 0);
         this.fillHoles(thisIndex);
         BitVectorHelper.unsetBit(this.validityBuffer, thisIndex);
         long copyStart = this.offsetBuffer.getLong((long)thisIndex * 8L);
         this.offsetBuffer.setLong((long)(thisIndex + 1) * 8L, copyStart);
      } else {
         long start = from.getOffsetBuffer().getLong((long)fromIndex * 8L);
         long end = from.getOffsetBuffer().getLong((long)(fromIndex + 1) * 8L);
         int length = (int)(end - start);
         this.handleSafe(thisIndex, length);
         this.fillHoles(thisIndex);
         BitVectorHelper.setBit(this.validityBuffer, (long)thisIndex);
         long copyStart = this.getStartOffset(thisIndex);
         from.getDataBuffer().getBytes(start, this.valueBuffer, copyStart, length);
         this.offsetBuffer.setLong((long)(thisIndex + 1) * 8L, copyStart + (long)length);
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
         long offset = this.getStartOffset(index);
         int length = (int)(this.getEndOffset(index) - offset);
         reuse.set(this.valueBuffer, offset, (long)length);
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
         long start = this.getStartOffset(index);
         long end = this.getEndOffset(index);
         return ByteFunctionHelpers.hash(hasher, this.getDataBuffer(), start, end);
      }
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   protected final long getEndOffset(int index) {
      return this.offsetBuffer.getLong((long)(index + 1) * 8L);
   }
}
