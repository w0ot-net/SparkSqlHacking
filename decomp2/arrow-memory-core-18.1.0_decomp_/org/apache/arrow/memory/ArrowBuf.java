package org.apache.arrow.memory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.arrow.memory.util.CommonUtil;
import org.apache.arrow.memory.util.HistoricalLog;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.MemoryUtil;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.util.VisibleForTesting;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ArrowBuf implements AutoCloseable {
   private static final Logger logger = LoggerFactory.getLogger(ArrowBuf.class);
   private static final int SHORT_SIZE = 2;
   private static final int INT_SIZE = 4;
   private static final int FLOAT_SIZE = 4;
   private static final int DOUBLE_SIZE = 8;
   private static final int LONG_SIZE = 8;
   private static final AtomicLong idGenerator = new AtomicLong(0L);
   private static final int LOG_BYTES_PER_ROW = 10;
   private final long id;
   private final ReferenceManager referenceManager;
   private final @Nullable BufferManager bufferManager;
   private final long addr;
   private long readerIndex;
   private long writerIndex;
   private final @Nullable HistoricalLog historicalLog;
   private volatile long capacity;

   public ArrowBuf(ReferenceManager referenceManager, @Nullable BufferManager bufferManager, long capacity, long memoryAddress) {
      this.id = idGenerator.incrementAndGet();
      this.historicalLog = BaseAllocator.DEBUG ? new HistoricalLog(6, "ArrowBuf[%d]", new Object[]{this.id}) : null;
      this.referenceManager = referenceManager;
      this.bufferManager = bufferManager;
      this.addr = memoryAddress;
      this.capacity = capacity;
      this.readerIndex = 0L;
      this.writerIndex = 0L;
      if (this.historicalLog != null) {
         this.historicalLog.recordEvent("create()");
      }

   }

   public int refCnt() {
      return this.referenceManager.getRefCount();
   }

   public void checkBytes(long start, long end) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         this.checkIndexD(start, end - start);
      }

   }

   private void ensureAccessible() {
      if (this.refCnt() == 0) {
         throw new IllegalStateException("Ref count should be >= 1 for accessing the ArrowBuf");
      }
   }

   public ReferenceManager getReferenceManager() {
      return this.referenceManager;
   }

   public long capacity() {
      return this.capacity;
   }

   public synchronized ArrowBuf capacity(long newCapacity) {
      if (newCapacity == this.capacity) {
         return this;
      } else {
         Preconditions.checkArgument(newCapacity >= 0L);
         if (newCapacity < this.capacity) {
            this.capacity = newCapacity;
            return this;
         } else {
            throw new UnsupportedOperationException("Buffers don't support resizing that increases the size.");
         }
      }
   }

   public ByteOrder order() {
      return ByteOrder.nativeOrder();
   }

   public long readableBytes() {
      Preconditions.checkState(this.writerIndex >= this.readerIndex, "Writer index cannot be less than reader index");
      return this.writerIndex - this.readerIndex;
   }

   public long writableBytes() {
      return this.capacity() - this.writerIndex;
   }

   public ArrowBuf slice() {
      return this.slice(this.readerIndex, this.readableBytes());
   }

   public ArrowBuf slice(long index, long length) {
      Preconditions.checkPositionIndex(index, this.capacity);
      Preconditions.checkPositionIndex(index + length, this.capacity);
      ArrowBuf newBuf = this.referenceManager.deriveBuffer(this, index, length);
      newBuf.writerIndex(length);
      return newBuf;
   }

   public ByteBuffer nioBuffer() {
      return this.nioBuffer(this.readerIndex, LargeMemoryUtil.checkedCastToInt(this.readableBytes()));
   }

   public ByteBuffer nioBuffer(long index, int length) {
      this.chk(index, (long)length);
      return this.getDirectBuffer(index, length);
   }

   private ByteBuffer getDirectBuffer(long index, int length) {
      long address = this.addr(index);
      return MemoryUtil.directBuffer(address, length);
   }

   public long memoryAddress() {
      return this.addr;
   }

   public String toString() {
      return String.format("ArrowBuf[%d], address:%d, capacity:%d", this.id, this.memoryAddress(), this.capacity);
   }

   public int hashCode() {
      return System.identityHashCode(this);
   }

   public boolean equals(@Nullable Object obj) {
      return this == obj;
   }

   private long addr(long index) {
      return this.addr + index;
   }

   private void chk(long index, long length) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         this.checkIndexD(index, length);
      }

   }

   private void checkIndexD(long index, long fieldLength) {
      this.ensureAccessible();
      Preconditions.checkArgument(fieldLength >= 0L, "expecting non-negative data length");
      if (index < 0L || index > this.capacity() - fieldLength) {
         if (this.historicalLog != null) {
            this.historicalLog.logHistory(logger);
         }

         throw new IndexOutOfBoundsException(String.format("index: %d, length: %d (expected: range(0, %d))", index, fieldLength, this.capacity()));
      }
   }

   public long getLong(long index) {
      this.chk(index, 8L);
      return MemoryUtil.getLong(this.addr(index));
   }

   public void setLong(long index, long value) {
      this.chk(index, 8L);
      MemoryUtil.putLong(this.addr(index), value);
   }

   public float getFloat(long index) {
      return Float.intBitsToFloat(this.getInt(index));
   }

   public void setFloat(long index, float value) {
      this.chk(index, 4L);
      MemoryUtil.putInt(this.addr(index), Float.floatToRawIntBits(value));
   }

   public double getDouble(long index) {
      return Double.longBitsToDouble(this.getLong(index));
   }

   public void setDouble(long index, double value) {
      this.chk(index, 8L);
      MemoryUtil.putLong(this.addr(index), Double.doubleToRawLongBits(value));
   }

   public char getChar(long index) {
      return (char)this.getShort(index);
   }

   public void setChar(long index, int value) {
      this.chk(index, 2L);
      MemoryUtil.putShort(this.addr(index), (short)value);
   }

   public int getInt(long index) {
      this.chk(index, 4L);
      return MemoryUtil.getInt(this.addr(index));
   }

   public void setInt(long index, int value) {
      this.chk(index, 4L);
      MemoryUtil.putInt(this.addr(index), value);
   }

   public short getShort(long index) {
      this.chk(index, 2L);
      return MemoryUtil.getShort(this.addr(index));
   }

   public void setShort(long index, int value) {
      this.setShort(index, (short)value);
   }

   public void setShort(long index, short value) {
      this.chk(index, 2L);
      MemoryUtil.putShort(this.addr(index), value);
   }

   public void setByte(long index, int value) {
      this.chk(index, 1L);
      MemoryUtil.putByte(this.addr(index), (byte)value);
   }

   public void setByte(long index, byte value) {
      this.chk(index, 1L);
      MemoryUtil.putByte(this.addr(index), value);
   }

   public byte getByte(long index) {
      this.chk(index, 1L);
      return MemoryUtil.getByte(this.addr(index));
   }

   private void ensureWritable(int length) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         Preconditions.checkArgument(length >= 0, "expecting non-negative length");
         this.ensureAccessible();
         if ((long)length > this.writableBytes()) {
            throw new IndexOutOfBoundsException(String.format("writerIndex(%d) + length(%d) exceeds capacity(%d)", this.writerIndex, length, this.capacity()));
         }
      }

   }

   private void ensureReadable(int length) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         Preconditions.checkArgument(length >= 0, "expecting non-negative length");
         this.ensureAccessible();
         if ((long)length > this.readableBytes()) {
            throw new IndexOutOfBoundsException(String.format("readerIndex(%d) + length(%d) exceeds writerIndex(%d)", this.readerIndex, length, this.writerIndex));
         }
      }

   }

   public byte readByte() {
      this.ensureReadable(1);
      byte b = this.getByte(this.readerIndex);
      ++this.readerIndex;
      return b;
   }

   public void readBytes(byte[] dst) {
      Preconditions.checkArgument(dst != null, "expecting valid dst bytearray");
      this.ensureReadable(dst.length);
      this.getBytes(this.readerIndex, dst, 0, LargeMemoryUtil.checkedCastToInt((long)dst.length));
   }

   public void writeByte(byte value) {
      this.ensureWritable(1);
      MemoryUtil.putByte(this.addr(this.writerIndex), value);
      ++this.writerIndex;
   }

   public void writeByte(int value) {
      this.ensureWritable(1);
      MemoryUtil.putByte(this.addr(this.writerIndex), (byte)value);
      ++this.writerIndex;
   }

   public void writeBytes(byte[] src) {
      Preconditions.checkArgument(src != null, "expecting valid src array");
      this.writeBytes(src, 0, src.length);
   }

   public void writeBytes(byte[] src, int srcIndex, int length) {
      this.ensureWritable(length);
      this.setBytes(this.writerIndex, src, srcIndex, (long)length);
      this.writerIndex += (long)length;
   }

   public void writeShort(int value) {
      this.ensureWritable(2);
      MemoryUtil.putShort(this.addr(this.writerIndex), (short)value);
      this.writerIndex += 2L;
   }

   public void writeInt(int value) {
      this.ensureWritable(4);
      MemoryUtil.putInt(this.addr(this.writerIndex), value);
      this.writerIndex += 4L;
   }

   public void writeLong(long value) {
      this.ensureWritable(8);
      MemoryUtil.putLong(this.addr(this.writerIndex), value);
      this.writerIndex += 8L;
   }

   public void writeFloat(float value) {
      this.ensureWritable(4);
      MemoryUtil.putInt(this.addr(this.writerIndex), Float.floatToRawIntBits(value));
      this.writerIndex += 4L;
   }

   public void writeDouble(double value) {
      this.ensureWritable(8);
      MemoryUtil.putLong(this.addr(this.writerIndex), Double.doubleToRawLongBits(value));
      this.writerIndex += 8L;
   }

   private static boolean isOutOfBounds(long index, long length, long capacity) {
      return (index | length | index + length | capacity - (index + length)) < 0L;
   }

   private void checkIndex(long index, long fieldLength) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         this.ensureAccessible();
         if (isOutOfBounds(index, fieldLength, this.capacity())) {
            throw new IndexOutOfBoundsException(String.format("index: %d, length: %d (expected: range(0, %d))", index, fieldLength, this.capacity()));
         }
      }

   }

   public void getBytes(long index, byte[] dst) {
      this.getBytes(index, dst, 0, dst.length);
   }

   public void getBytes(long index, byte[] dst, int dstIndex, int length) {
      this.checkIndex(index, (long)length);
      Preconditions.checkArgument(dst != null, "expecting a valid dst byte array");
      if (isOutOfBounds((long)dstIndex, (long)length, (long)dst.length)) {
         throw new IndexOutOfBoundsException("Not enough space to copy data into destination" + dstIndex);
      } else {
         if (length != 0) {
            MemoryUtil.copyFromMemory(this.addr(index), dst, (long)dstIndex, (long)length);
         }

      }
   }

   public void setBytes(long index, byte[] src) {
      this.setBytes(index, src, 0, (long)src.length);
   }

   public void setBytes(long index, byte[] src, int srcIndex, long length) {
      this.checkIndex(index, length);
      Preconditions.checkArgument(src != null, "expecting a valid src byte array");
      if (isOutOfBounds((long)srcIndex, length, (long)src.length)) {
         throw new IndexOutOfBoundsException("Not enough space to copy data from byte array" + srcIndex);
      } else {
         if (length > 0L) {
            MemoryUtil.copyToMemory(src, (long)srcIndex, this.addr(index), length);
         }

      }
   }

   public void getBytes(long index, ByteBuffer dst) {
      this.checkIndex(index, (long)dst.remaining());
      if (dst.remaining() != 0) {
         long srcAddress = this.addr(index);
         if (dst.isDirect()) {
            if (dst.isReadOnly()) {
               throw new ReadOnlyBufferException();
            }

            long dstAddress = MemoryUtil.getByteBufferAddress(dst) + (long)dst.position();
            MemoryUtil.copyMemory(srcAddress, dstAddress, (long)dst.remaining());
            dst.position(dst.position() + dst.remaining());
         } else {
            if (!dst.hasArray()) {
               throw new UnsupportedOperationException("Copy from this ArrowBuf to ByteBuffer is not supported");
            }

            int dstIndex = dst.arrayOffset() + dst.position();
            MemoryUtil.copyFromMemory(srcAddress, dst.array(), (long)dstIndex, (long)dst.remaining());
            dst.position(dst.position() + dst.remaining());
         }
      }

   }

   public void setBytes(long index, ByteBuffer src) {
      this.checkIndex(index, (long)src.remaining());
      int length = src.remaining();
      long dstAddress = this.addr(index);
      if (length != 0) {
         if (src.isDirect()) {
            long srcAddress = MemoryUtil.getByteBufferAddress(src) + (long)src.position();
            MemoryUtil.copyMemory(srcAddress, dstAddress, (long)length);
            src.position(src.position() + length);
         } else if (src.hasArray()) {
            int srcIndex = src.arrayOffset() + src.position();
            MemoryUtil.copyToMemory(src.array(), (long)srcIndex, dstAddress, (long)length);
            src.position(src.position() + length);
         } else {
            ByteOrder originalByteOrder = src.order();
            src.order(this.order());

            try {
               while(length - 128 >= 8) {
                  for(int x = 0; x < 16; ++x) {
                     MemoryUtil.putLong(dstAddress, src.getLong());
                     length -= 8;
                     dstAddress += 8L;
                  }
               }

               while(length >= 8) {
                  MemoryUtil.putLong(dstAddress, src.getLong());
                  length -= 8;
                  dstAddress += 8L;
               }

               while(length > 0) {
                  MemoryUtil.putByte(dstAddress, src.get());
                  --length;
                  ++dstAddress;
               }

               return;
            } finally {
               src.order(originalByteOrder);
            }
         }
      }

   }

   public void setBytes(long index, ByteBuffer src, int srcIndex, int length) {
      this.checkIndex(index, (long)length);
      if (src.isDirect()) {
         long srcAddress = MemoryUtil.getByteBufferAddress(src) + (long)srcIndex;
         long dstAddress = this.addr(index);
         MemoryUtil.copyMemory(srcAddress, dstAddress, (long)length);
      } else if (srcIndex == 0 && src.capacity() == length) {
         this.setBytes(index, src);
      } else {
         ByteBuffer newBuf = src.duplicate();
         newBuf.position(srcIndex);
         newBuf.limit(srcIndex + length);
         this.setBytes(index, newBuf);
      }

   }

   public void getBytes(long index, ArrowBuf dst, long dstIndex, int length) {
      this.checkIndex(index, (long)length);
      Preconditions.checkArgument(dst != null, "expecting a valid ArrowBuf");
      if (isOutOfBounds(dstIndex, (long)length, dst.capacity())) {
         throw new IndexOutOfBoundsException(String.format("index: %d, length: %d (expected: range(0, %d))", dstIndex, length, dst.capacity()));
      } else {
         if (length != 0) {
            long srcAddress = this.addr(index);
            long dstAddress = dst.memoryAddress() + dstIndex;
            MemoryUtil.copyMemory(srcAddress, dstAddress, (long)length);
         }

      }
   }

   public void setBytes(long index, ArrowBuf src, long srcIndex, long length) {
      this.checkIndex(index, length);
      Preconditions.checkArgument(src != null, "expecting a valid ArrowBuf");
      if (isOutOfBounds(srcIndex, length, src.capacity())) {
         throw new IndexOutOfBoundsException(String.format("index: %d, length: %d (expected: range(0, %d))", srcIndex, length, src.capacity()));
      } else {
         if (length != 0L) {
            long srcAddress = src.memoryAddress() + srcIndex;
            long dstAddress = this.addr(index);
            MemoryUtil.copyMemory(srcAddress, dstAddress, length);
         }

      }
   }

   public void setBytes(long index, ArrowBuf src) {
      Preconditions.checkArgument(src != null, "expecting valid ArrowBuf");
      long length = src.readableBytes();
      this.checkIndex(index, length);
      long srcAddress = src.memoryAddress() + src.readerIndex;
      long dstAddress = this.addr(index);
      MemoryUtil.copyMemory(srcAddress, dstAddress, length);
      src.readerIndex(src.readerIndex + length);
   }

   public int setBytes(long index, InputStream in, int length) throws IOException {
      Preconditions.checkArgument(in != null, "expecting valid input stream");
      this.checkIndex(index, (long)length);
      int readBytes = 0;
      if (length > 0) {
         byte[] tmp = new byte[length];
         readBytes = in.read(tmp);
         if (readBytes > 0) {
            MemoryUtil.copyToMemory(tmp, 0L, this.addr(index), (long)readBytes);
         }
      }

      return readBytes;
   }

   public void getBytes(long index, OutputStream out, int length) throws IOException {
      Preconditions.checkArgument(out != null, "expecting valid output stream");
      this.checkIndex(index, (long)length);
      if (length > 0) {
         byte[] tmp = new byte[length];
         MemoryUtil.copyFromMemory(this.addr(index), tmp, 0L, (long)length);
         out.write(tmp);
      }

   }

   public void close() {
      this.referenceManager.release();
   }

   public long getPossibleMemoryConsumed() {
      return this.referenceManager.getSize();
   }

   public long getActualMemoryConsumed() {
      return this.referenceManager.getAccountedSize();
   }

   public String toHexString(long start, int length) {
      long roundedStart = start / 10L * 10L;
      StringBuilder sb = new StringBuilder("buffer byte dump\n");
      long index = roundedStart;

      for(long nLogged = 0L; nLogged < (long)length; nLogged += 10L) {
         sb.append(String.format(" [%05d-%05d]", index, index + 10L - 1L));

         for(int i = 0; i < 10; ++i) {
            try {
               byte b = this.getByte(index++);
               sb.append(String.format(" 0x%02x", b));
            } catch (IndexOutOfBoundsException var13) {
               sb.append(" <ioob>");
            }
         }

         sb.append('\n');
      }

      return sb.toString();
   }

   public long getId() {
      return this.id;
   }

   @VisibleForTesting
   public void print(StringBuilder sb, int indent, BaseAllocator.Verbosity verbosity) {
      CommonUtil.indent(sb, indent).append(this.toString());
      if (this.historicalLog != null && verbosity.includeHistoricalLog) {
         sb.append("\n");
         this.historicalLog.buildHistory(sb, indent + 1, verbosity.includeStackTraces);
      }

   }

   public void print(StringBuilder sb, int indent) {
      this.print(sb, indent, BaseAllocator.Verbosity.LOG_WITH_STACKTRACE);
   }

   public long readerIndex() {
      return this.readerIndex;
   }

   public long writerIndex() {
      return this.writerIndex;
   }

   public ArrowBuf readerIndex(long readerIndex) {
      this.readerIndex = readerIndex;
      return this;
   }

   public ArrowBuf writerIndex(long writerIndex) {
      this.writerIndex = writerIndex;
      return this;
   }

   public ArrowBuf setZero(long index, long length) {
      if (length != 0L) {
         this.checkIndex(index, length);
         MemoryUtil.setMemory(this.addr + index, length, (byte)0);
      }

      return this;
   }

   /** @deprecated */
   @Deprecated
   public ArrowBuf setOne(int index, int length) {
      if (length != 0) {
         this.checkIndex((long)index, (long)length);
         MemoryUtil.setMemory(this.addr + (long)index, (long)length, (byte)-1);
      }

      return this;
   }

   public ArrowBuf setOne(long index, long length) {
      if (length != 0L) {
         this.checkIndex(index, length);
         MemoryUtil.setMemory(this.addr + index, length, (byte)-1);
      }

      return this;
   }

   public ArrowBuf reallocIfNeeded(long size) {
      Preconditions.checkArgument(size >= 0L, "reallocation size must be non-negative");
      if (this.capacity() >= size) {
         return this;
      } else if (this.bufferManager != null) {
         return this.bufferManager.replace(this, size);
      } else {
         throw new UnsupportedOperationException("Realloc is only available in the context of operator's UDFs");
      }
   }

   public ArrowBuf clear() {
      this.readerIndex = this.writerIndex = 0L;
      return this;
   }
}
