package org.apache.arrow.vector;

import java.util.Collections;
import java.util.Iterator;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.ReferenceManager;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.util.DataSizeRoundingUtil;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.util.ValueVectorUtility;

public abstract class BaseValueVector implements ValueVector {
   public static final String MAX_ALLOCATION_SIZE_PROPERTY = "arrow.vector.max_allocation_bytes";
   public static final long MAX_ALLOCATION_SIZE = Long.getLong("arrow.vector.max_allocation_bytes", Long.MAX_VALUE);
   public static final int INITIAL_VALUE_ALLOCATION = 3970;
   protected final BufferAllocator allocator;
   protected volatile FieldReader fieldReader;

   protected BaseValueVector(BufferAllocator allocator) {
      this.allocator = (BufferAllocator)Preconditions.checkNotNull(allocator, "allocator cannot be null");
   }

   public abstract String getName();

   public String toString() {
      return ValueVectorUtility.getToString(this, 0, this.getValueCount());
   }

   public void clear() {
   }

   public void close() {
      this.clear();
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return this.getTransferPair(this.getName(), allocator);
   }

   public Iterator iterator() {
      return Collections.emptyIterator();
   }

   public static boolean checkBufRefs(ValueVector vv) {
      for(ArrowBuf buffer : vv.getBuffers(false)) {
         if (buffer.refCnt() <= 0) {
            throw new IllegalStateException("zero refcount");
         }
      }

      return true;
   }

   public BufferAllocator getAllocator() {
      return this.allocator;
   }

   void compareTypes(BaseValueVector target, String caller) {
      if (this.getMinorType() != target.getMinorType()) {
         throw new UnsupportedOperationException(caller + " should have vectors of exact same type");
      }
   }

   protected ArrowBuf releaseBuffer(ArrowBuf buffer) {
      buffer.getReferenceManager().release();
      buffer = this.allocator.getEmpty();
      return buffer;
   }

   protected static int getValidityBufferSizeFromCount(int valueCount) {
      return DataSizeRoundingUtil.divideBy8Ceil(valueCount);
   }

   private static long roundUp8ForValidityBuffer(long valueCount) {
      return valueCount + 63L >> 6 << 3;
   }

   long computeCombinedBufferSize(int valueCount, int typeWidth) {
      Preconditions.checkArgument(valueCount >= 0, "valueCount must be >= 0");
      Preconditions.checkArgument(typeWidth >= 0, "typeWidth must be >= 0");
      long bufferSize = roundUp8ForValidityBuffer((long)valueCount);
      if (typeWidth == 0) {
         bufferSize *= 2L;
      } else {
         bufferSize += DataSizeRoundingUtil.roundUpTo8Multiple((long)valueCount * (long)typeWidth);
      }

      return this.allocator.getRoundingPolicy().getRoundedSize(bufferSize);
   }

   protected abstract FieldReader getReaderImpl();

   public FieldReader getReader() {
      FieldReader reader = this.fieldReader;
      if (reader != null) {
         return reader;
      } else {
         synchronized(this) {
            if (this.fieldReader == null) {
               this.fieldReader = this.getReaderImpl();
            }

            return this.fieldReader;
         }
      }
   }

   DataAndValidityBuffers allocFixedDataAndValidityBufs(int valueCount, int typeWidth) {
      long bufferSize = this.computeCombinedBufferSize(valueCount, typeWidth);

      assert bufferSize <= MAX_ALLOCATION_SIZE;

      long validityBufferSize;
      long dataBufferSize;
      if (typeWidth == 0) {
         validityBufferSize = dataBufferSize = bufferSize / 2L;
      } else {
         long actualCount = (long)((double)bufferSize * (double)8.0F / (double)(8 * typeWidth + 1));

         while(true) {
            validityBufferSize = roundUp8ForValidityBuffer(actualCount);
            dataBufferSize = DataSizeRoundingUtil.roundUpTo8Multiple(actualCount * (long)typeWidth);
            if (validityBufferSize + dataBufferSize <= bufferSize) {
               break;
            }

            --actualCount;
         }
      }

      ArrowBuf combinedBuffer = this.allocator.buffer(bufferSize);
      ArrowBuf dataBuf = null;
      ArrowBuf validityBuf = null;
      long bufferOffset = 0L;

      for(int numBuffers = 0; numBuffers < 2; ++numBuffers) {
         long len = numBuffers == 0 ? dataBufferSize : validityBufferSize;
         ArrowBuf buf = combinedBuffer.slice(bufferOffset, len);
         buf.getReferenceManager().retain();
         buf.readerIndex(0L);
         buf.writerIndex(0L);
         bufferOffset += len;
         if (numBuffers == 0) {
            dataBuf = buf;
         } else {
            validityBuf = buf;
         }
      }

      combinedBuffer.getReferenceManager().release();
      return new DataAndValidityBuffers(dataBuf, validityBuf);
   }

   public static ArrowBuf transferBuffer(ArrowBuf srcBuffer, BufferAllocator targetAllocator) {
      ReferenceManager referenceManager = srcBuffer.getReferenceManager();
      return referenceManager.transferOwnership(srcBuffer, targetAllocator).getTransferredBuffer();
   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      throw new UnsupportedOperationException();
   }

   public void copyFromSafe(int fromIndex, int thisIndex, ValueVector from) {
      throw new UnsupportedOperationException();
   }

   static class DataAndValidityBuffers {
      private ArrowBuf dataBuf;
      private ArrowBuf validityBuf;

      DataAndValidityBuffers(ArrowBuf dataBuf, ArrowBuf validityBuf) {
         this.dataBuf = dataBuf;
         this.validityBuf = validityBuf;
      }

      ArrowBuf getDataBuf() {
         return this.dataBuf;
      }

      ArrowBuf getValidityBuf() {
         return this.validityBuf;
      }
   }
}
