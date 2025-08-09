package org.apache.hadoop.hive.serde2;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.apache.hadoop.hive.serde2.lazybinary.LazyBinaryUtils;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hive.common.util.HashCodeUtil;

public final class WriteBuffers implements ByteStream.RandomAccessOutput {
   private final ArrayList writeBuffers = new ArrayList(1);
   private final int wbSize;
   private final int wbSizeLog2;
   private final long offsetMask;
   private final long maxSize;
   Position writePos = new Position();
   Position unsafeReadPos = new Position();

   public WriteBuffers(int wbSize, long maxSize) {
      this.wbSize = Integer.bitCount(wbSize) == 1 ? wbSize : Integer.highestOneBit(wbSize);
      this.wbSizeLog2 = 31 - Integer.numberOfLeadingZeros(this.wbSize);
      this.offsetMask = (long)(this.wbSize - 1);
      this.maxSize = maxSize;
      this.writePos.bufferIndex = -1;
   }

   public int unsafeReadVInt() {
      return (int)this.readVLong(this.unsafeReadPos);
   }

   public int readVInt(Position readPos) {
      return (int)this.readVLong(readPos);
   }

   public long unsafeReadVLong() {
      return this.readVLong(this.unsafeReadPos);
   }

   public long readVLong(Position readPos) {
      this.ponderNextBufferToRead(readPos);
      byte firstByte = readPos.buffer[readPos.offset++];
      int length = (byte)WritableUtils.decodeVIntSize(firstByte) - 1;
      if (length == 0) {
         return (long)firstByte;
      } else {
         long i = 0L;
         if (this.isAllInOneReadBuffer(length, readPos)) {
            for(int idx = 0; idx < length; ++idx) {
               i = i << 8 | (long)(readPos.buffer[readPos.offset + idx] & 255);
            }

            readPos.offset = readPos.offset + length;
         } else {
            for(int idx = 0; idx < length; ++idx) {
               i = i << 8 | (long)(this.readNextByte(readPos) & 255);
            }
         }

         return WritableUtils.isNegativeVInt(firstByte) ? ~i : i;
      }
   }

   public void unsafeSkipVLong() {
      this.skipVLong(this.unsafeReadPos);
   }

   public void skipVLong(Position readPos) {
      this.ponderNextBufferToRead(readPos);
      byte firstByte = readPos.buffer[readPos.offset++];
      int length = (byte)WritableUtils.decodeVIntSize(firstByte);
      if (length > 1) {
         readPos.offset = readPos.offset + (length - 1);
      }

      for(int diff = readPos.offset - this.wbSize; diff >= 0; diff = readPos.offset - this.wbSize) {
         ++readPos.bufferIndex;
         readPos.buffer = (byte[])this.writeBuffers.get(readPos.bufferIndex);
         readPos.offset = diff;
      }

   }

   public void setUnsafeReadPoint(long offset) {
      this.setReadPoint(offset, this.unsafeReadPos);
   }

   public void setReadPoint(long offset, Position readPos) {
      readPos.bufferIndex = this.getBufferIndex(offset);
      readPos.buffer = (byte[])this.writeBuffers.get(readPos.bufferIndex);
      readPos.offset = this.getOffset(offset);
   }

   public int unsafeHashCode(long offset, int length) {
      return this.hashCode(offset, length, this.unsafeReadPos);
   }

   public int hashCode(long offset, int length, Position readPos) {
      this.setReadPoint(offset, readPos);
      if (this.isAllInOneReadBuffer(length, readPos)) {
         int result = HashCodeUtil.murmurHash(readPos.buffer, readPos.offset, length);
         readPos.offset = readPos.offset + length;
         return result;
      } else {
         byte[] bytes = new byte[length];

         int toRead;
         for(int destOffset = 0; destOffset < length; destOffset += toRead) {
            this.ponderNextBufferToRead(readPos);
            toRead = Math.min(length - destOffset, this.wbSize - readPos.offset);
            System.arraycopy(readPos.buffer, readPos.offset, bytes, destOffset, toRead);
            readPos.offset = readPos.offset + toRead;
         }

         return HashCodeUtil.murmurHash(bytes, 0, bytes.length);
      }
   }

   private byte readNextByte(Position readPos) {
      this.ponderNextBufferToRead(readPos);
      return readPos.buffer[readPos.offset++];
   }

   private void ponderNextBufferToRead(Position readPos) {
      if (readPos.offset >= this.wbSize) {
         ++readPos.bufferIndex;
         readPos.buffer = (byte[])this.writeBuffers.get(readPos.bufferIndex);
         readPos.offset = 0;
      }

   }

   public int hashCode(byte[] key, int offset, int length) {
      return HashCodeUtil.murmurHash(key, offset, length);
   }

   private void setByte(long offset, byte value) {
      ((byte[])this.writeBuffers.get(this.getBufferIndex(offset)))[this.getOffset(offset)] = value;
   }

   public void reserve(int byteCount) {
      if (byteCount < 0) {
         throw new AssertionError("byteCount must be non-negative");
      } else {
         int currentWriteOffset;
         for(currentWriteOffset = this.writePos.offset + byteCount; currentWriteOffset > this.wbSize; currentWriteOffset -= this.wbSize) {
            this.nextBufferToWrite();
         }

         this.writePos.offset = currentWriteOffset;
      }
   }

   public void setWritePoint(long offset) {
      this.writePos.bufferIndex = this.getBufferIndex(offset);
      this.writePos.buffer = (byte[])this.writeBuffers.get(this.writePos.bufferIndex);
      this.writePos.offset = this.getOffset(offset);
   }

   public void write(int b) {
      if (this.writePos.offset == this.wbSize) {
         this.nextBufferToWrite();
      }

      this.writePos.buffer[this.writePos.offset++] = (byte)b;
   }

   public void write(byte[] b) {
      this.write(b, 0, b.length);
   }

   public void write(byte[] b, int off, int len) {
      if (this.writePos.bufferIndex == -1) {
         this.nextBufferToWrite();
      }

      int srcOffset = 0;

      while(srcOffset < len) {
         int toWrite = Math.min(len - srcOffset, this.wbSize - this.writePos.offset);
         System.arraycopy(b, srcOffset + off, this.writePos.buffer, this.writePos.offset, toWrite);
         Position var6 = this.writePos;
         var6.offset = var6.offset + toWrite;
         srcOffset += toWrite;
         if (this.writePos.offset == this.wbSize) {
            this.nextBufferToWrite();
         }
      }

   }

   public int getLength() {
      return (int)this.getWritePoint();
   }

   private int getOffset(long offset) {
      return (int)(offset & this.offsetMask);
   }

   private int getBufferIndex(long offset) {
      return (int)(offset >>> this.wbSizeLog2);
   }

   private void nextBufferToWrite() {
      if (this.writePos.bufferIndex == this.writeBuffers.size() - 1) {
         if ((long)(1 + this.writeBuffers.size()) * (long)this.wbSize > this.maxSize) {
            throw new RuntimeException("Too much memory used by write buffers");
         }

         this.writeBuffers.add(new byte[this.wbSize]);
      }

      ++this.writePos.bufferIndex;
      this.writePos.buffer = (byte[])this.writeBuffers.get(this.writePos.bufferIndex);
      this.writePos.offset = 0;
   }

   public boolean isEqual(long leftOffset, int leftLength, long rightOffset, int rightLength) {
      if (rightLength != leftLength) {
         return false;
      } else {
         int leftIndex = this.getBufferIndex(leftOffset);
         int rightIndex = this.getBufferIndex(rightOffset);
         int leftFrom = this.getOffset(leftOffset);
         int rightFrom = this.getOffset(rightOffset);
         byte[] leftBuffer = (byte[])this.writeBuffers.get(leftIndex);
         byte[] rightBuffer = (byte[])this.writeBuffers.get(rightIndex);
         if (leftFrom + leftLength <= this.wbSize && rightFrom + rightLength <= this.wbSize) {
            for(int i = 0; i < leftLength; ++i) {
               if (leftBuffer[leftFrom + i] != rightBuffer[rightFrom + i]) {
                  return false;
               }
            }

            return true;
         } else {
            for(int i = 0; i < leftLength; ++i) {
               if (leftFrom == this.wbSize) {
                  ++leftIndex;
                  leftBuffer = (byte[])this.writeBuffers.get(leftIndex);
                  leftFrom = 0;
               }

               if (rightFrom == this.wbSize) {
                  ++rightIndex;
                  rightBuffer = (byte[])this.writeBuffers.get(rightIndex);
                  rightFrom = 0;
               }

               if (leftBuffer[leftFrom++] != rightBuffer[rightFrom++]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private final boolean isEqual(byte[] left, int leftOffset, int rightIndex, int rightFrom, int length) {
      if (length == 0) {
         return true;
      } else {
         byte[] rightBuffer = (byte[])this.writeBuffers.get(rightIndex);
         if (rightFrom + length <= this.wbSize) {
            if (left[leftOffset + length - 1] != rightBuffer[rightFrom + length - 1]) {
               return false;
            } else {
               for(int i = 0; i < length; ++i) {
                  if (left[leftOffset + i] != rightBuffer[rightFrom + i]) {
                     return false;
                  }
               }

               return true;
            }
         } else {
            for(int i = 0; i < length; ++i) {
               if (rightFrom == this.wbSize) {
                  ++rightIndex;
                  rightBuffer = (byte[])this.writeBuffers.get(rightIndex);
                  rightFrom = 0;
               }

               if (left[leftOffset + i] != rightBuffer[rightFrom++]) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public boolean isEqual(byte[] left, int leftLength, long rightOffset, int rightLength) {
      return rightLength != leftLength ? false : this.isEqual(left, 0, this.getBufferIndex(rightOffset), this.getOffset(rightOffset), leftLength);
   }

   public boolean isEqual(byte[] left, int leftOffset, int leftLength, long rightOffset, int rightLength) {
      return rightLength != leftLength ? false : this.isEqual(left, leftOffset, this.getBufferIndex(rightOffset), this.getOffset(rightOffset), leftLength);
   }

   public boolean isEqual(byte[] left, int leftOffset, Position readPos, int length) {
      return this.isEqual(left, leftOffset, readPos.bufferIndex, readPos.offset, length);
   }

   public void clear() {
      this.writeBuffers.clear();
      this.clearState();
   }

   private void clearState() {
      this.writePos.clear();
      this.unsafeReadPos.clear();
   }

   public long getWritePoint() {
      if (this.writePos.bufferIndex == -1) {
         this.nextBufferToWrite();
      }

      return ((long)this.writePos.bufferIndex << this.wbSizeLog2) + (long)this.writePos.offset;
   }

   public long getUnsafeReadPoint() {
      return this.getReadPoint(this.unsafeReadPos);
   }

   public long getReadPoint(Position readPos) {
      return (long)readPos.bufferIndex * (long)this.wbSize + (long)readPos.offset;
   }

   public void getByteSegmentRefToCurrent(ByteSegmentRef byteSegmentRef, int length, Position readPos) {
      byteSegmentRef.reset((long)readPos.bufferIndex * (long)this.wbSize + (long)readPos.offset, length);
      if (length > 0) {
         this.populateValue(byteSegmentRef);
      }

   }

   public void writeVInt(int value) {
      LazyBinaryUtils.writeVInt(this, value);
   }

   public void writeVLong(long value) {
      LazyBinaryUtils.writeVLong(this, value);
   }

   public void writeBytes(long offset, int length) {
      int readBufIndex = this.getBufferIndex(offset);
      byte[] readBuffer = (byte[])this.writeBuffers.get(readBufIndex);
      int readBufOffset = this.getOffset(offset);
      int srcOffset = 0;

      while(srcOffset < length) {
         if (readBufOffset == this.wbSize) {
            ++readBufIndex;
            readBuffer = (byte[])this.writeBuffers.get(readBufIndex);
            readBufOffset = 0;
         }

         if (this.writePos.offset == this.wbSize) {
            this.nextBufferToWrite();
         }

         int toRead = Math.min(length - srcOffset, this.wbSize - readBufOffset);
         int toWrite = Math.min(toRead, this.wbSize - this.writePos.offset);
         System.arraycopy(readBuffer, readBufOffset, this.writePos.buffer, this.writePos.offset, toWrite);
         Position var10 = this.writePos;
         var10.offset = var10.offset + toWrite;
         readBufOffset += toWrite;
         srcOffset += toWrite;
         if (toRead > toWrite) {
            this.nextBufferToWrite();
            toRead -= toWrite;
            System.arraycopy(readBuffer, readBufOffset, this.writePos.buffer, this.writePos.offset, toRead);
            var10 = this.writePos;
            var10.offset = var10.offset + toRead;
            readBufOffset += toRead;
            srcOffset += toRead;
         }
      }

   }

   public void populateValue(ByteSegmentRef value) {
      int index = this.getBufferIndex(value.getOffset());
      byte[] buffer = (byte[])this.writeBuffers.get(index);
      int bufferOffset = this.getOffset(value.getOffset());
      int length = value.getLength();
      if (bufferOffset + length <= this.wbSize) {
         value.bytes = buffer;
         value.offset = (long)bufferOffset;
      } else {
         value.bytes = new byte[length];
         value.offset = 0L;

         int toCopy;
         for(int destOffset = 0; destOffset < length; destOffset += toCopy) {
            if (destOffset > 0) {
               ++index;
               buffer = (byte[])this.writeBuffers.get(index);
               bufferOffset = 0;
            }

            toCopy = Math.min(length - destOffset, this.wbSize - bufferOffset);
            System.arraycopy(buffer, bufferOffset, value.bytes, destOffset, toCopy);
         }

      }
   }

   private boolean isAllInOneReadBuffer(int length, Position readPos) {
      return readPos.offset + length <= this.wbSize;
   }

   private boolean isAllInOneWriteBuffer(int length) {
      return this.writePos.offset + length <= this.wbSize;
   }

   public void seal() {
      if (this.writePos.bufferIndex != -1) {
         if ((double)this.writePos.offset < (double)this.wbSize * 0.8) {
            byte[] smallerBuffer = new byte[this.writePos.offset];
            System.arraycopy(this.writePos.buffer, 0, smallerBuffer, 0, this.writePos.offset);
            this.writeBuffers.set(this.writePos.bufferIndex, smallerBuffer);
         }

         if (this.writePos.bufferIndex + 1 < this.writeBuffers.size()) {
            this.writeBuffers.subList(this.writePos.bufferIndex + 1, this.writeBuffers.size()).clear();
         }

         this.clearState();
      }
   }

   public long unsafeReadNByteLong(long offset, int bytes) {
      return this.readNByteLong(offset, bytes, this.unsafeReadPos);
   }

   public long readNByteLong(long offset, int bytes, Position readPos) {
      this.setReadPoint(offset, readPos);
      long v = 0L;
      if (this.isAllInOneReadBuffer(bytes, readPos)) {
         for(int i = 0; i < bytes; ++i) {
            v = (v << 8) + (long)(readPos.buffer[readPos.offset + i] & 255);
         }

         readPos.offset = readPos.offset + bytes;
      } else {
         for(int i = 0; i < bytes; ++i) {
            v = (v << 8) + (long)(this.readNextByte(readPos) & 255);
         }
      }

      return v;
   }

   public void writeFiveByteULong(long offset, long v) {
      int prevIndex = this.writePos.bufferIndex;
      int prevOffset = this.writePos.offset;
      this.setWritePoint(offset);
      if (this.isAllInOneWriteBuffer(5)) {
         this.writePos.buffer[this.writePos.offset] = (byte)((int)(v >>> 32));
         this.writePos.buffer[this.writePos.offset + 1] = (byte)((int)(v >>> 24));
         this.writePos.buffer[this.writePos.offset + 2] = (byte)((int)(v >>> 16));
         this.writePos.buffer[this.writePos.offset + 3] = (byte)((int)(v >>> 8));
         this.writePos.buffer[this.writePos.offset + 4] = (byte)((int)v);
         Position var7 = this.writePos;
         var7.offset = var7.offset + 5;
      } else {
         this.setByte(offset++, (byte)((int)(v >>> 32)));
         this.setByte(offset++, (byte)((int)(v >>> 24)));
         this.setByte(offset++, (byte)((int)(v >>> 16)));
         this.setByte(offset++, (byte)((int)(v >>> 8)));
         this.setByte(offset, (byte)((int)v));
      }

      this.writePos.bufferIndex = prevIndex;
      this.writePos.buffer = (byte[])this.writeBuffers.get(this.writePos.bufferIndex);
      this.writePos.offset = prevOffset;
   }

   public int readInt(long offset) {
      return (int)this.unsafeReadNByteLong(offset, 4);
   }

   public void writeInt(long offset, int v) {
      int prevIndex = this.writePos.bufferIndex;
      int prevOffset = this.writePos.offset;
      this.setWritePoint(offset);
      if (this.isAllInOneWriteBuffer(4)) {
         this.writePos.buffer[this.writePos.offset] = (byte)(v >> 24);
         this.writePos.buffer[this.writePos.offset + 1] = (byte)(v >> 16);
         this.writePos.buffer[this.writePos.offset + 2] = (byte)(v >> 8);
         this.writePos.buffer[this.writePos.offset + 3] = (byte)v;
         Position var6 = this.writePos;
         var6.offset = var6.offset + 4;
      } else {
         this.setByte(offset++, (byte)(v >>> 24));
         this.setByte(offset++, (byte)(v >>> 16));
         this.setByte(offset++, (byte)(v >>> 8));
         this.setByte(offset, (byte)v);
      }

      this.writePos.bufferIndex = prevIndex;
      this.writePos.buffer = (byte[])this.writeBuffers.get(this.writePos.bufferIndex);
      this.writePos.offset = prevOffset;
   }

   public void writeByte(long offset, byte value) {
      int prevIndex = this.writePos.bufferIndex;
      int prevOffset = this.writePos.offset;
      this.setWritePoint(offset);
      this.writePos.buffer[this.writePos.offset] = value;
      this.writePos.bufferIndex = prevIndex;
      this.writePos.buffer = (byte[])this.writeBuffers.get(this.writePos.bufferIndex);
      this.writePos.offset = prevOffset;
   }

   public long size() {
      return (long)this.writeBuffers.size() * (long)this.wbSize;
   }

   public Position getUnsafeReadPosition() {
      return this.unsafeReadPos;
   }

   public static class Position {
      private byte[] buffer = null;
      private int bufferIndex = 0;
      private int offset = 0;

      public void clear() {
         this.buffer = null;
         this.bufferIndex = this.offset = -1;
      }
   }

   public static class ByteSegmentRef {
      private byte[] bytes = null;
      private long offset;
      private int length;

      public ByteSegmentRef(long offset, int length) {
         this.reset(offset, length);
      }

      public void reset(long offset, int length) {
         if (length < 0) {
            throw new AssertionError("Length is negative: " + length);
         } else {
            this.offset = offset;
            this.length = length;
         }
      }

      public ByteSegmentRef() {
      }

      public byte[] getBytes() {
         return this.bytes;
      }

      public long getOffset() {
         return this.offset;
      }

      public int getLength() {
         return this.length;
      }

      public ByteBuffer copy() {
         byte[] copy = new byte[this.length];
         if (this.length > 0) {
            System.arraycopy(this.bytes, (int)this.offset, copy, 0, this.length);
         }

         return ByteBuffer.wrap(copy);
      }
   }
}
