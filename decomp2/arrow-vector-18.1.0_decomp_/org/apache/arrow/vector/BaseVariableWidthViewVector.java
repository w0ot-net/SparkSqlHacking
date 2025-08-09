package org.apache.arrow.vector;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.ReusableBuffer;
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
import org.apache.arrow.vector.util.DataSizeRoundingUtil;
import org.apache.arrow.vector.util.OversizedAllocationException;
import org.apache.arrow.vector.util.TransferPair;

public abstract class BaseVariableWidthViewVector extends BaseValueVector implements VariableWidthFieldVector {
   public static final int ELEMENT_SIZE = 16;
   public static final int INITIAL_VIEW_VALUE_ALLOCATION = 4096;
   private static final int INITIAL_BYTE_COUNT = 65536;
   private static final int MAX_BUFFER_SIZE;
   private int lastValueCapacity;
   private long lastValueAllocationSizeInBytes;
   public static final int INLINE_SIZE = 12;
   public static final int LENGTH_WIDTH = 4;
   public static final int PREFIX_WIDTH = 4;
   public static final int BUF_INDEX_WIDTH = 4;
   public static final byte[] EMPTY_BYTE_ARRAY;
   protected ArrowBuf validityBuffer;
   protected ArrowBuf viewBuffer;
   protected List dataBuffers;
   protected int initialDataBufferSize;
   protected int valueCount;
   protected int lastSet;
   protected final Field field;

   public BaseVariableWidthViewVector(Field field, BufferAllocator allocator) {
      super(allocator);
      this.field = field;
      this.lastValueAllocationSizeInBytes = 65536L;
      this.lastValueCapacity = 4096;
      this.valueCount = 0;
      this.lastSet = -1;
      this.validityBuffer = allocator.getEmpty();
      this.viewBuffer = allocator.getEmpty();
      this.dataBuffers = new ArrayList();
   }

   public String getName() {
      return this.field.getName();
   }

   public ArrowBuf getValidityBuffer() {
      return this.validityBuffer;
   }

   public ArrowBuf getDataBuffer() {
      return this.viewBuffer;
   }

   public List getDataBuffers() {
      return this.dataBuffers;
   }

   public ArrowBuf getOffsetBuffer() {
      throw new UnsupportedOperationException("Offset buffer is not supported in BaseVariableWidthViewVector");
   }

   public long getOffsetBufferAddress() {
      throw new UnsupportedOperationException("Offset buffer is not supported in BaseVariableWidthViewVector");
   }

   public long getValidityBufferAddress() {
      return this.validityBuffer.memoryAddress();
   }

   public long getDataBufferAddress() {
      return this.viewBuffer.memoryAddress();
   }

   public void setInitialCapacity(int valueCount) {
      long size = (long)valueCount * 16L;
      this.checkDataBufferSize(size);
      this.lastValueAllocationSizeInBytes = (long)((int)size);
      this.lastValueCapacity = valueCount;
   }

   public void setInitialCapacity(int valueCount, double density) {
      long size = (long)valueCount * 16L;
      this.initialDataBufferSize = (int)((double)valueCount * density);
      this.checkDataBufferSize(size);
      this.lastValueAllocationSizeInBytes = (long)((int)size);
      this.lastValueCapacity = valueCount;
   }

   public double getDensity() {
      if (this.valueCount == 0) {
         return (double)0.0F;
      } else {
         double totalListSize = (double)this.getTotalValueLengthUpToIndex(this.valueCount);
         return totalListSize / (double)this.valueCount;
      }
   }

   public int getValueCapacity() {
      int validityCapacity = this.getValidityBufferValueCapacity();
      int valueBufferCapacity = Math.max(LargeMemoryUtil.capAtMaxInt(this.viewBuffer.capacity() / 16L), 0);
      return Math.min(valueBufferCapacity, validityCapacity);
   }

   private int getValidityBufferValueCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.validityBuffer.capacity() * 8L);
   }

   public void zeroVector() {
      this.initValidityBuffer();
      this.viewBuffer.setZero(0L, this.viewBuffer.capacity());
      this.clearDataBuffers();
   }

   private void initValidityBuffer() {
      this.validityBuffer.setZero(0L, this.validityBuffer.capacity());
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
      this.viewBuffer = this.releaseBuffer(this.viewBuffer);
      this.clearDataBuffers();
      this.lastSet = -1;
      this.valueCount = 0;
   }

   public void clearDataBuffers() {
      for(ArrowBuf buffer : this.dataBuffers) {
         this.releaseBuffer(buffer);
      }

      this.dataBuffers.clear();
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
      ArrowBuf bitBuf = (ArrowBuf)ownBuffers.get(0);
      ArrowBuf viewBuf = (ArrowBuf)ownBuffers.get(1);
      List<ArrowBuf> dataBufs = ownBuffers.subList(2, ownBuffers.size());
      this.clear();
      this.viewBuffer = viewBuf.getReferenceManager().retain(viewBuf, this.allocator);
      this.validityBuffer = BitVectorHelper.loadValidityBuffer(fieldNode, bitBuf, this.allocator);

      for(ArrowBuf dataBuf : dataBufs) {
         this.dataBuffers.add(dataBuf.getReferenceManager().retain(dataBuf, this.allocator));
      }

      this.lastSet = fieldNode.getLength() - 1;
      this.valueCount = fieldNode.getLength();
   }

   public List getFieldBuffers() {
      List<ArrowBuf> result = new ArrayList(2 + this.dataBuffers.size());
      this.setReaderAndWriterIndex();
      result.add(this.validityBuffer);
      result.add(this.viewBuffer);
      result.addAll(this.dataBuffers);
      return result;
   }

   private void setReaderAndWriterIndex() {
      this.validityBuffer.readerIndex(0L);
      this.viewBuffer.readerIndex(0L);
      if (this.valueCount == 0) {
         this.validityBuffer.writerIndex(0L);
         this.viewBuffer.writerIndex(0L);
      } else {
         this.validityBuffer.writerIndex((long)getValidityBufferSizeFromCount(this.valueCount));
         this.viewBuffer.writerIndex((long)(this.valueCount * 16));
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

   private void allocateBytes(long valueBufferSize, int valueCount) {
      this.viewBuffer = this.allocator.buffer(valueBufferSize);
      this.viewBuffer.readerIndex(0L);
      this.validityBuffer = this.allocator.buffer((long)((valueCount + 7) / 8));
      this.initValidityBuffer();
      this.lastValueCapacity = this.getValueCapacity();
      this.lastValueAllocationSizeInBytes = (long)LargeMemoryUtil.capAtMaxInt(this.viewBuffer.capacity());
   }

   public void reAlloc() {
      this.reallocViewBuffer();
      this.reallocViewDataBuffer();
      this.reallocValidityBuffer();
   }

   public void reallocViewBuffer() {
      long currentViewBufferCapacity = this.viewBuffer.capacity();
      long newAllocationSize = currentViewBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.lastValueAllocationSizeInBytes > 0L) {
            newAllocationSize = this.lastValueAllocationSizeInBytes;
         } else {
            newAllocationSize = 131072L;
         }
      }

      this.reallocViewBuffer(newAllocationSize);
   }

   public void reallocViewDataBuffer() {
      long currentDataBufferCapacity = 0L;
      if (!this.dataBuffers.isEmpty()) {
         currentDataBufferCapacity = ((ArrowBuf)this.dataBuffers.get(this.dataBuffers.size() - 1)).capacity();
      }

      long newAllocationSize = currentDataBufferCapacity * 2L;
      if (newAllocationSize == 0L) {
         if (this.lastValueAllocationSizeInBytes > 0L) {
            newAllocationSize = this.lastValueAllocationSizeInBytes;
         } else {
            newAllocationSize = 131072L;
         }
      }

      this.reallocViewDataBuffer(newAllocationSize);
   }

   public void reallocViewBuffer(long desiredAllocSize) {
      if (desiredAllocSize != 0L) {
         long newAllocationSize = CommonUtil.nextPowerOfTwo(desiredAllocSize);

         assert newAllocationSize >= 1L;

         this.checkDataBufferSize(newAllocationSize);
         newAllocationSize = DataSizeRoundingUtil.roundUpToMultipleOf16(newAllocationSize);
         ArrowBuf newBuf = this.allocator.buffer(newAllocationSize);
         newBuf.setBytes(0L, this.viewBuffer, 0L, this.viewBuffer.capacity());
         this.viewBuffer.getReferenceManager().release();
         this.viewBuffer = newBuf;
         this.lastValueAllocationSizeInBytes = this.viewBuffer.capacity();
         this.lastValueCapacity = this.getValueCapacity();
      }
   }

   public void reallocViewDataBuffer(long desiredAllocSize) {
      if (desiredAllocSize != 0L) {
         if (!this.dataBuffers.isEmpty()) {
            ArrowBuf currentBuf = (ArrowBuf)this.dataBuffers.get(this.dataBuffers.size() - 1);
            if (currentBuf.capacity() - currentBuf.writerIndex() < desiredAllocSize) {
               long newAllocationSize = CommonUtil.nextPowerOfTwo(desiredAllocSize);

               assert newAllocationSize >= 1L;

               this.checkDataBufferSize(newAllocationSize);
               ArrowBuf newBuf = this.allocator.buffer(newAllocationSize);
               this.dataBuffers.add(newBuf);
            }
         }
      }
   }

   public void reallocValidityBuffer() {
      int targetValidityCount = LargeMemoryUtil.capAtMaxInt(this.validityBuffer.capacity() * 8L * 2L);
      if (targetValidityCount == 0) {
         if (this.lastValueCapacity > 0) {
            targetValidityCount = this.lastValueCapacity;
         } else {
            targetValidityCount = 7940;
         }
      }

      long validityBufferSize = this.computeValidityBufferSize(targetValidityCount);
      ArrowBuf newValidityBuffer = this.allocator.buffer(validityBufferSize);
      newValidityBuffer.setBytes(0L, this.validityBuffer, 0L, this.validityBuffer.capacity());
      newValidityBuffer.setZero(this.validityBuffer.capacity(), newValidityBuffer.capacity() - this.validityBuffer.capacity());
      this.validityBuffer.getReferenceManager().release();
      this.validityBuffer = newValidityBuffer;
      this.lastValueCapacity = this.getValueCapacity();
   }

   private long computeValidityBufferSize(int valueCount) {
      return (long)((valueCount + 7) / 8);
   }

   public int getByteCapacity() {
      return LargeMemoryUtil.capAtMaxInt(this.viewBuffer.capacity());
   }

   public int sizeOfValueBuffer() {
      throw new UnsupportedOperationException("sizeOfValueBuffer is not supported for BaseVariableWidthViewVector");
   }

   public int sizeOfViewBufferElements() {
      if (this.valueCount == 0) {
         return 0;
      } else {
         int totalSize = 0;

         for(int i = 0; i < this.valueCount; ++i) {
            totalSize += this.getValueLength(i);
         }

         return totalSize;
      }
   }

   public int getBufferSize() {
      return this.getBufferSizeFor(this.valueCount);
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         int validityBufferSize = getValidityBufferSizeFromCount(valueCount);
         int viewBufferSize = valueCount * 16;
         int dataBufferSize = this.getDataBufferSize();
         return validityBufferSize + viewBufferSize + dataBufferSize;
      }
   }

   private int getDataBufferSize() {
      int dataBufferSize = 0;

      for(ArrowBuf buf : this.dataBuffers) {
         dataBufferSize += (int)buf.writerIndex();
      }

      return dataBufferSize;
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
         int dataBufferSize = this.dataBuffers.size();
         int fixedBufferSize = 2;
         buffers = new ArrowBuf[2 + dataBufferSize];
         buffers[0] = this.validityBuffer;
         buffers[1] = this.viewBuffer;

         for(int i = 2; i < 2 + dataBufferSize; ++i) {
            buffers[i] = (ArrowBuf)this.dataBuffers.get(i - 2);
         }
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

   public void transferTo(BaseVariableWidthViewVector target) {
      this.compareTypes(target, "transferTo");
      target.clear();
      target.validityBuffer = transferBuffer(this.validityBuffer, target.allocator);
      target.viewBuffer = transferBuffer(this.viewBuffer, target.allocator);
      target.dataBuffers = new ArrayList(this.dataBuffers.size());

      for(int i = 0; i < this.dataBuffers.size(); ++i) {
         target.dataBuffers.add(transferBuffer((ArrowBuf)this.dataBuffers.get(i), target.allocator));
      }

      target.setLastSet(this.lastSet);
      if (this.valueCount > 0) {
         target.setValueCount(this.valueCount);
      }

      this.clear();
   }

   public void splitAndTransferTo(int startIndex, int length, BaseVariableWidthViewVector target) {
      Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, this.valueCount);
      this.compareTypes(target, "splitAndTransferTo");
      target.clear();
      if (length > 0) {
         this.splitAndTransferValidityBuffer(startIndex, length, target);
         this.splitAndTransferViewBufferAndDataBuffer(startIndex, length, target);
         target.setLastSet(length - 1);
         target.setValueCount(length);
      }

   }

   private void allocateValidityBuffer(long size) {
      int curSize = (int)size;
      this.validityBuffer = this.allocator.buffer((long)curSize);
      this.validityBuffer.readerIndex(0L);
      this.initValidityBuffer();
   }

   private void splitAndTransferValidityBuffer(int startIndex, int length, BaseVariableWidthViewVector target) {
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

   private void splitAndTransferViewBufferAndDataBuffer(int startIndex, int length, BaseVariableWidthViewVector target) {
      if (length != 0) {
         if (target.viewBuffer != null) {
            target.viewBuffer.getReferenceManager().release();
         }

         target.viewBuffer = target.allocator.buffer((long)(length * 16));

         for(int i = startIndex; i < startIndex + length; ++i) {
            int stringLength = this.getValueLength(i);
            int writePosition = (i - startIndex) * 16;
            int readPosition = i * 16;
            target.viewBuffer.setInt((long)writePosition, stringLength);
            if (stringLength <= 12) {
               writePosition += 4;
               readPosition += 4;
               target.viewBuffer.setBytes((long)writePosition, this.viewBuffer, (long)readPosition, (long)stringLength);
            } else {
               int readBufIndex = this.viewBuffer.getInt((long)i * 16L + 4L + 4L);
               int readBufOffset = this.viewBuffer.getInt((long)i * 16L + 4L + 4L + 4L);
               ArrowBuf dataBuf = (ArrowBuf)this.dataBuffers.get(readBufIndex);
               ArrowBuf currentDataBuf = target.allocateOrGetLastDataBuffer(stringLength);
               long currentOffSet = currentDataBuf.writerIndex();
               writePosition += 4;
               readPosition += 4;
               target.viewBuffer.setBytes((long)writePosition, this.viewBuffer, (long)readPosition, 4L);
               writePosition += 4;
               target.viewBuffer.setInt((long)writePosition, target.dataBuffers.size() - 1);
               writePosition += 4;
               target.viewBuffer.setInt((long)writePosition, (int)currentOffSet);
               currentDataBuf.setBytes(currentOffSet, dataBuf, (long)readBufOffset, (long)stringLength);
               currentDataBuf.writerIndex(currentOffSet + (long)stringLength);
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
         this.reallocViewBuffer();
         this.reallocValidityBuffer();
      }

      this.lastSet = valueCount - 1;
      this.setReaderAndWriterIndex();
   }

   public void fillEmpties(int index) {
      this.handleSafe(index, EMPTY_BYTE_ARRAY.length);
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
         this.reallocValidityBuffer();
      }

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
   }

   public void setValueLengthSafe(int index, int length) {
      assert index >= 0;

      this.handleSafe(index, length);
      this.lastSet = index;
   }

   public int getValueLength(int index) {
      assert index >= 0;

      if (index >= 0 && (long)index < this.viewBuffer.capacity() / 16L) {
         return this.isSet(index) == 0 ? 0 : this.viewBuffer.getInt((long)index * 16L);
      } else {
         throw new IndexOutOfBoundsException("Index out of bounds: " + index);
      }
   }

   public void set(int index, byte[] value) {
      assert index >= 0;

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, (byte[])value, 0, value.length);
      this.lastSet = index;
   }

   public void setSafe(int index, byte[] value) {
      assert index >= 0;

      this.handleSafe(index, value.length);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, (byte[])value, 0, value.length);
      this.lastSet = index;
   }

   public void set(int index, byte[] value, int start, int length) {
      assert index >= 0;

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, value, start, length);
      this.lastSet = index;
   }

   public void setSafe(int index, byte[] value, int start, int length) {
      assert index >= 0;

      this.handleSafe(index, length);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, value, start, length);
      this.lastSet = index;
   }

   public void set(int index, ByteBuffer value, int start, int length) {
      assert index >= 0;

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, value.array(), start, length);
      this.lastSet = index;
   }

   public void setSafe(int index, ByteBuffer value, int start, int length) {
      assert index >= 0;

      this.handleSafe(index, length);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, value.array(), start, length);
      this.lastSet = index;
   }

   public void setNull(int index) {
      this.handleSafe(index, 0);
      BitVectorHelper.unsetBit(this.validityBuffer, index);
   }

   public void set(int index, int isSet, int start, int end, ArrowBuf buffer) {
      assert index >= 0;

      int dataLength = end - start;
      BitVectorHelper.setValidityBit(this.validityBuffer, index, isSet);
      this.setBytes(index, buffer, start, dataLength);
      this.lastSet = index;
   }

   public void setSafe(int index, int isSet, int start, int end, ArrowBuf buffer) {
      assert index >= 0;

      int dataLength = end - start;
      this.handleSafe(index, dataLength);
      BitVectorHelper.setValidityBit(this.validityBuffer, index, isSet);
      this.setBytes(index, buffer, start, dataLength);
      this.lastSet = index;
   }

   public void set(int index, int start, int length, ArrowBuf buffer) {
      assert index >= 0;

      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, buffer, start, length);
      this.lastSet = index;
   }

   public void setSafe(int index, int start, int length, ArrowBuf buffer) {
      assert index >= 0;

      this.handleSafe(index, length);
      BitVectorHelper.setBit(this.validityBuffer, (long)index);
      this.setBytes(index, buffer, start, length);
      this.lastSet = index;
   }

   protected ArrowBuf allocateOrGetLastDataBuffer(int length) {
      long dataBufferSize;
      if (this.initialDataBufferSize > 0) {
         dataBufferSize = (long)Math.max(this.initialDataBufferSize, length);
      } else {
         dataBufferSize = Math.max(this.lastValueAllocationSizeInBytes, (long)length);
      }

      if (this.dataBuffers.isEmpty() || ((ArrowBuf)this.dataBuffers.get(this.dataBuffers.size() - 1)).capacity() - ((ArrowBuf)this.dataBuffers.get(this.dataBuffers.size() - 1)).writerIndex() < (long)length) {
         ArrowBuf newBuf = this.allocator.buffer(dataBufferSize);
         this.dataBuffers.add(newBuf);
      }

      return (ArrowBuf)this.dataBuffers.get(this.dataBuffers.size() - 1);
   }

   protected final void setBytes(int index, byte[] value, int start, int length) {
      int writePosition = index * 16;
      this.viewBuffer.setZero((long)writePosition, 16L);
      if (length <= 12) {
         this.viewBuffer.setInt((long)writePosition, length);
         writePosition += 4;
         this.viewBuffer.setBytes((long)writePosition, value, start, (long)length);
      } else {
         ArrowBuf currentBuf = this.allocateOrGetLastDataBuffer(length);
         this.viewBuffer.setInt((long)writePosition, length);
         writePosition += 4;
         this.viewBuffer.setBytes((long)writePosition, value, start, 4L);
         writePosition += 4;
         this.viewBuffer.setInt((long)writePosition, this.dataBuffers.size() - 1);
         writePosition += 4;
         this.viewBuffer.setInt((long)writePosition, (int)currentBuf.writerIndex());
         currentBuf.setBytes(currentBuf.writerIndex(), value, start, (long)length);
         currentBuf.writerIndex(currentBuf.writerIndex() + (long)length);
      }

   }

   protected final void setBytes(int index, ArrowBuf valueBuf, int start, int length) {
      int writePosition = index * 16;
      this.viewBuffer.setZero((long)writePosition, 16L);
      if (length <= 12) {
         this.viewBuffer.setInt((long)writePosition, length);
         writePosition += 4;
         this.viewBuffer.setBytes((long)writePosition, valueBuf, (long)start, (long)length);
      } else {
         ArrowBuf currentBuf = this.allocateOrGetLastDataBuffer(length);
         this.viewBuffer.setInt((long)writePosition, length);
         writePosition += 4;
         this.viewBuffer.setBytes((long)writePosition, valueBuf, (long)start, 4L);
         writePosition += 4;
         this.viewBuffer.setInt((long)writePosition, this.dataBuffers.size() - 1);
         writePosition += 4;
         this.viewBuffer.setInt((long)writePosition, (int)currentBuf.writerIndex());
         currentBuf.setBytes(currentBuf.writerIndex(), valueBuf, (long)start, (long)length);
         currentBuf.writerIndex(currentBuf.writerIndex() + (long)length);
      }

   }

   public final int getTotalValueLengthUpToIndex(int index) {
      int totalLength = 0;

      for(int i = 0; i < index - 1; ++i) {
         totalLength += this.getValueLength(i);
      }

      return totalLength;
   }

   protected final void handleSafe(int index, int dataLength) {
      long targetCapacity = DataSizeRoundingUtil.roundUpToMultipleOf16((long)index * 16L + (long)dataLength);
      if (this.viewBuffer.capacity() < targetCapacity) {
         this.reallocViewBuffer(targetCapacity);
      }

      while(index >= this.getValidityBufferValueCapacity()) {
         this.reallocValidityBuffer();
      }

   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      if (from.isNull(fromIndex)) {
         BitVectorHelper.unsetBit(this.validityBuffer, thisIndex);
      } else {
         int viewLength = from.getDataBuffer().getInt((long)fromIndex * 16L);
         this.copyFromNotNull(fromIndex, thisIndex, from, viewLength);
      }

      this.lastSet = thisIndex;
   }

   public void copyFromSafe(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      if (from.isNull(fromIndex)) {
         this.handleSafe(thisIndex, 0);
         BitVectorHelper.unsetBit(this.validityBuffer, thisIndex);
      } else {
         int viewLength = from.getDataBuffer().getInt((long)fromIndex * 16L);
         this.handleSafe(thisIndex, viewLength);
         this.copyFromNotNull(fromIndex, thisIndex, from, viewLength);
      }

      this.lastSet = thisIndex;
   }

   private void copyFromNotNull(int fromIndex, int thisIndex, ValueVector from, int viewLength) {
      BitVectorHelper.setBit(this.validityBuffer, (long)thisIndex);
      int start = thisIndex * 16;
      int copyStart = fromIndex * 16;
      if (viewLength > 12) {
         int bufIndex = from.getDataBuffer().getInt((long)fromIndex * 16L + 4L + 4L);
         int dataOffset = from.getDataBuffer().getInt((long)fromIndex * 16L + 4L + 4L + 4L);
         ArrowBuf dataBuf = (ArrowBuf)((BaseVariableWidthViewVector)from).dataBuffers.get(bufIndex);
         ArrowBuf thisDataBuf = this.allocateOrGetLastDataBuffer(viewLength);
         this.viewBuffer.setBytes((long)start, from.getDataBuffer(), (long)copyStart, 8L);
         int writePosition = start + 4 + 4;
         this.viewBuffer.setInt((long)writePosition, this.dataBuffers.size() - 1);
         writePosition += 4;
         this.viewBuffer.setInt((long)writePosition, (int)thisDataBuf.writerIndex());
         thisDataBuf.setBytes(thisDataBuf.writerIndex(), dataBuf, (long)dataOffset, (long)viewLength);
         thisDataBuf.writerIndex(thisDataBuf.writerIndex() + (long)viewLength);
      } else {
         from.getDataBuffer().getBytes((long)copyStart, this.viewBuffer, (long)start, 16);
      }

   }

   public ArrowBufPointer getDataPointer(int index) {
      return this.getDataPointer(index, new ArrowBufPointer());
   }

   public ArrowBufPointer getDataPointer(int index, ArrowBufPointer reuse) {
      if (this.isNull(index)) {
         reuse.set((ArrowBuf)null, 0L, 0L);
      } else {
         int length = this.getValueLength(index);
         if (length < 12) {
            int start = index * 16 + 4;
            reuse.set(this.viewBuffer, (long)start, (long)length);
         } else {
            int bufIndex = this.viewBuffer.getInt((long)index * 16L + 4L + 4L);
            ArrowBuf dataBuf = (ArrowBuf)this.dataBuffers.get(bufIndex);
            reuse.set(dataBuf, 0L, (long)length);
         }
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
         int length = this.getValueLength(index);
         if (length < 12) {
            int start = index * 16 + 4;
            return ByteFunctionHelpers.hash(hasher, this.getDataBuffer(), (long)start, (long)(start + length));
         } else {
            int bufIndex = this.viewBuffer.getInt((long)index * 16L + 4L + 4L);
            int dataOffset = this.viewBuffer.getInt((long)index * 16L + 4L + 4L + 4L);
            ArrowBuf dataBuf = (ArrowBuf)this.dataBuffers.get(bufIndex);
            return ByteFunctionHelpers.hash(hasher, dataBuf, (long)dataOffset, (long)(dataOffset + length));
         }
      }
   }

   protected byte[] getData(int index) {
      int dataLength = this.getValueLength(index);
      byte[] result = new byte[dataLength];
      if (dataLength > 12) {
         int bufferIndex = this.viewBuffer.getInt((long)index * 16L + 4L + 4L);
         int dataOffset = this.viewBuffer.getInt((long)index * 16L + 4L + 4L + 4L);
         ((ArrowBuf)this.dataBuffers.get(bufferIndex)).getBytes((long)dataOffset, result, 0, dataLength);
      } else {
         this.viewBuffer.getBytes((long)index * 16L + 4L, result, 0, dataLength);
      }

      return result;
   }

   protected void getData(int index, ReusableBuffer buffer) {
      int dataLength = this.getValueLength(index);
      if (dataLength > 12) {
         int bufferIndex = this.viewBuffer.getInt((long)index * 16L + 4L + 4L);
         int dataOffset = this.viewBuffer.getInt((long)index * 16L + 4L + 4L + 4L);
         ArrowBuf dataBuf = (ArrowBuf)this.dataBuffers.get(bufferIndex);
         buffer.set(dataBuf, (long)dataOffset, (long)dataLength);
      } else {
         buffer.set(this.viewBuffer, (long)index * 16L + 4L, (long)dataLength);
      }

   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public int getExportedCDataBufferCount() {
      return 3 + this.dataBuffers.size();
   }

   public void exportCDataBuffers(List buffers, ArrowBuf buffersPtr, long nullValue) {
      this.exportBuffer(this.validityBuffer, buffers, buffersPtr, nullValue, true);
      this.exportBuffer(this.viewBuffer, buffers, buffersPtr, nullValue, true);
      ArrowBuf variadicSizeBuffer = this.allocator.buffer(8L * (long)this.dataBuffers.size());

      for(int i = 0; i < this.dataBuffers.size(); ++i) {
         ArrowBuf dataBuf = (ArrowBuf)this.dataBuffers.get(i);
         variadicSizeBuffer.setLong((long)i * 8L, dataBuf.capacity());
         this.exportBuffer(dataBuf, buffers, buffersPtr, nullValue, true);
      }

      this.exportBuffer(variadicSizeBuffer, buffers, buffersPtr, nullValue, false);
   }

   static {
      MAX_BUFFER_SIZE = (int)Math.min(MAX_ALLOCATION_SIZE, 2147483647L);
      EMPTY_BYTE_ARRAY = new byte[0];
   }
}
