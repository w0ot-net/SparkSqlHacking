package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BoundsChecking;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.patch.ArrowByteBufAllocator;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.util.VisibleForTesting;

public class NettyArrowBuf extends AbstractByteBuf implements AutoCloseable {
   private final ArrowBuf arrowBuf;
   private final ArrowByteBufAllocator arrowByteBufAllocator;
   private long length;
   private final long address;

   /** @deprecated */
   @Deprecated(
      forRemoval = true
   )
   public NettyArrowBuf(ArrowBuf arrowBuf, BufferAllocator bufferAllocator, int length) {
      this(arrowBuf, bufferAllocator, (long)length);
   }

   public NettyArrowBuf(ArrowBuf arrowBuf, BufferAllocator bufferAllocator, long length) {
      super((int)length);
      this.arrowBuf = arrowBuf;
      this.arrowByteBufAllocator = new ArrowByteBufAllocator(bufferAllocator);
      this.length = length;
      this.address = arrowBuf.memoryAddress();
   }

   public ByteBuf copy() {
      throw new UnsupportedOperationException();
   }

   public ByteBuf copy(int index, int length) {
      throw new UnsupportedOperationException();
   }

   public ByteBuf retain() {
      this.arrowBuf.getReferenceManager().retain();
      return this;
   }

   public ArrowBuf arrowBuf() {
      return this.arrowBuf;
   }

   public ByteBuf retain(int increment) {
      this.arrowBuf.getReferenceManager().retain(increment);
      return this;
   }

   public boolean isDirect() {
      return true;
   }

   public synchronized ByteBuf capacity(int newCapacity) {
      if ((long)newCapacity == this.length) {
         return this;
      } else {
         Preconditions.checkArgument(newCapacity >= 0);
         if ((long)newCapacity < this.length) {
            this.length = (long)newCapacity;
            return this;
         } else {
            throw new UnsupportedOperationException("Buffers don't support resizing that increases the size.");
         }
      }
   }

   public ByteBuf unwrap() {
      return null;
   }

   public int refCnt() {
      return this.arrowBuf.getReferenceManager().getRefCount();
   }

   public ArrowByteBufAllocator alloc() {
      return this.arrowByteBufAllocator;
   }

   public boolean hasArray() {
      return false;
   }

   public byte[] array() {
      throw new UnsupportedOperationException("Operation not supported on direct buffer");
   }

   public int arrayOffset() {
      throw new UnsupportedOperationException("Operation not supported on direct buffer");
   }

   public boolean hasMemoryAddress() {
      return true;
   }

   public long memoryAddress() {
      return this.address;
   }

   public ByteBuf touch() {
      return this;
   }

   public ByteBuf touch(Object hint) {
      return this;
   }

   public int capacity() {
      return (int)Math.min(2147483647L, this.arrowBuf.capacity());
   }

   public NettyArrowBuf slice() {
      return unwrapBuffer(this.arrowBuf.slice((long)this.readerIndex, (long)(this.writerIndex - this.readerIndex)));
   }

   public NettyArrowBuf slice(int index, int length) {
      return unwrapBuffer(this.arrowBuf.slice((long)index, (long)length));
   }

   public void close() {
      this.arrowBuf.close();
   }

   public boolean release() {
      return this.arrowBuf.getReferenceManager().release();
   }

   public boolean release(int decrement) {
      return this.arrowBuf.getReferenceManager().release(decrement);
   }

   public NettyArrowBuf readerIndex(int readerIndex) {
      super.readerIndex(readerIndex);
      return this;
   }

   public NettyArrowBuf writerIndex(int writerIndex) {
      super.writerIndex(writerIndex);
      return this;
   }

   public int nioBufferCount() {
      return 1;
   }

   public ByteBuffer internalNioBuffer(int index, int length) {
      ByteBuffer nioBuf = this.getDirectBuffer((long)index);
      return nioBuf.clear().limit(length);
   }

   public ByteBuffer[] nioBuffers() {
      return new ByteBuffer[]{this.nioBuffer()};
   }

   public ByteBuffer[] nioBuffers(int index, int length) {
      return new ByteBuffer[]{this.nioBuffer(index, length)};
   }

   public ByteBuffer nioBuffer() {
      return this.nioBuffer(this.readerIndex(), this.readableBytes());
   }

   public ByteBuffer nioBuffer(int index, int length) {
      this.chk((long)index, (long)length);
      ByteBuffer buffer = this.getDirectBuffer((long)index);
      buffer.limit(length);
      return buffer;
   }

   public ByteBuffer nioBuffer(long index, int length) {
      this.chk(index, (long)length);
      ByteBuffer buffer = this.getDirectBuffer(index);
      buffer.limit(length);
      return buffer;
   }

   private ByteBuffer getDirectBuffer(long index) {
      return PlatformDependent.directBuffer(this.addr(index), LargeMemoryUtil.checkedCastToInt(this.length - index));
   }

   public ByteBuf getBytes(int index, ByteBuffer dst) {
      this.arrowBuf.getBytes((long)index, dst);
      return this;
   }

   public ByteBuf setBytes(int index, ByteBuffer src) {
      this.arrowBuf.setBytes((long)index, src);
      return this;
   }

   public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
      this.arrowBuf.getBytes((long)index, dst, dstIndex, length);
      return this;
   }

   public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
      this.arrowBuf.setBytes((long)index, src, srcIndex, (long)length);
      return this;
   }

   private static boolean isOutOfBounds(int index, int length, int capacity) {
      return (index | length | index + length | capacity - (index + length)) < 0;
   }

   public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
      this.chk((long)index, (long)length);
      Preconditions.checkArgument(dst != null, "Expecting valid dst ByteBuffer");
      if (isOutOfBounds(dstIndex, length, dst.capacity())) {
         throw new IndexOutOfBoundsException("dstIndex: " + dstIndex + " length: " + length);
      } else {
         long srcAddress = this.addr((long)index);
         if (dst.hasMemoryAddress()) {
            long dstAddress = dst.memoryAddress() + (long)dstIndex;
            PlatformDependent.copyMemory(srcAddress, dstAddress, (long)length);
         } else if (dst.hasArray()) {
            dstIndex += dst.arrayOffset();
            PlatformDependent.copyMemory(srcAddress, dst.array(), dstIndex, (long)length);
         } else {
            dst.setBytes(dstIndex, this, index, length);
         }

         return this;
      }
   }

   public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
      this.chk((long)index, (long)length);
      Preconditions.checkArgument(src != null, "Expecting valid src ByteBuffer");
      if (isOutOfBounds(srcIndex, length, src.capacity())) {
         throw new IndexOutOfBoundsException("srcIndex: " + srcIndex + " length: " + length);
      } else {
         if (length != 0) {
            long dstAddress = this.addr((long)index);
            if (src.hasMemoryAddress()) {
               long srcAddress = src.memoryAddress() + (long)srcIndex;
               PlatformDependent.copyMemory(srcAddress, dstAddress, (long)length);
            } else if (src.hasArray()) {
               srcIndex += src.arrayOffset();
               PlatformDependent.copyMemory(src.array(), srcIndex, dstAddress, (long)length);
            } else {
               src.getBytes(srcIndex, this, index, length);
            }
         }

         return this;
      }
   }

   public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
      this.arrowBuf.getBytes((long)index, out, length);
      return this;
   }

   public int setBytes(int index, InputStream in, int length) throws IOException {
      return this.arrowBuf.setBytes((long)index, in, length);
   }

   public int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
      Preconditions.checkArgument(out != null, "expecting valid gathering byte channel");
      this.chk((long)index, (long)length);
      if (length == 0) {
         return 0;
      } else {
         ByteBuffer tmpBuf = this.getDirectBuffer((long)index);
         tmpBuf.clear().limit(length);
         return out.write(tmpBuf);
      }
   }

   public int getBytes(int index, FileChannel out, long position, int length) throws IOException {
      this.chk((long)index, (long)length);
      if (length == 0) {
         return 0;
      } else {
         ByteBuffer tmpBuf = this.getDirectBuffer((long)index);
         tmpBuf.clear().limit(length);
         return out.write(tmpBuf, position);
      }
   }

   public int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
      return (int)in.read(this.nioBuffers(index, length));
   }

   public int setBytes(int index, FileChannel in, long position, int length) throws IOException {
      return (int)in.read(this.nioBuffers(index, length));
   }

   public ByteOrder order() {
      return ByteOrder.LITTLE_ENDIAN;
   }

   public ByteBuf order(ByteOrder endianness) {
      return this;
   }

   protected int _getUnsignedMedium(int index) {
      return this.getUnsignedMedium(index);
   }

   protected int _getUnsignedMediumLE(int index) {
      this.chk((long)index, 3L);
      long addr = this.addr((long)index);
      return PlatformDependent.getByte(addr) & 255 | (Short.reverseBytes(PlatformDependent.getShort(addr + 1L)) & '\uffff') << 8;
   }

   protected byte _getByte(int index) {
      return this.getByte(index);
   }

   public byte getByte(int index) {
      return this.arrowBuf.getByte((long)index);
   }

   protected short _getShortLE(int index) {
      short s = this.getShort(index);
      return Short.reverseBytes(s);
   }

   protected short _getShort(int index) {
      return this.getShort(index);
   }

   public short getShort(int index) {
      return this.arrowBuf.getShort((long)index);
   }

   protected int _getIntLE(int index) {
      int value = this.getInt(index);
      return Integer.reverseBytes(value);
   }

   protected int _getInt(int index) {
      return this.getInt(index);
   }

   public int getInt(int index) {
      return this.arrowBuf.getInt((long)index);
   }

   protected long _getLongLE(int index) {
      long value = this.getLong(index);
      return Long.reverseBytes(value);
   }

   protected long _getLong(int index) {
      return this.getLong(index);
   }

   public long getLong(int index) {
      return this.arrowBuf.getLong((long)index);
   }

   protected void _setByte(int index, int value) {
      this.setByte(index, value);
   }

   public NettyArrowBuf setByte(int index, int value) {
      this.arrowBuf.setByte((long)index, value);
      return this;
   }

   protected void _setShortLE(int index, int value) {
      this.chk((long)index, 2L);
      PlatformDependent.putShort(this.addr((long)index), Short.reverseBytes((short)value));
   }

   protected void _setShort(int index, int value) {
      this.setShort(index, value);
   }

   public NettyArrowBuf setShort(int index, int value) {
      this.arrowBuf.setShort((long)index, value);
      return this;
   }

   private long addr(long index) {
      return this.address + index;
   }

   private void chk(long index, long fieldLength) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         this.ensureAccessible();
         if (fieldLength < 0L) {
            throw new IllegalArgumentException("length: " + fieldLength + " (expected: >= 0)");
         }

         if (index < 0L || index > (long)this.capacity() - fieldLength) {
            throw new IndexOutOfBoundsException(String.format("index: %d, length: %d (expected: range(0, %d))", index, fieldLength, this.capacity()));
         }
      }

   }

   protected void _setMedium(int index, int value) {
      this.setMedium(index, value);
   }

   protected void _setMediumLE(int index, int value) {
      this.chk((long)index, 3L);
      long addr = this.addr((long)index);
      PlatformDependent.putByte(addr, (byte)value);
      PlatformDependent.putShort(addr + 1L, Short.reverseBytes((short)(value >>> 8)));
   }

   public NettyArrowBuf setMedium(int index, int value) {
      this.chk((long)index, 3L);
      long addr = this.addr((long)index);
      PlatformDependent.putShort(addr, (short)value);
      PlatformDependent.putByte(addr + 2L, (byte)(value >>> 16));
      return this;
   }

   @VisibleForTesting
   protected void _setInt(int index, int value) {
      this.setInt(index, value);
   }

   @VisibleForTesting
   protected void _setIntLE(int index, int value) {
      this.chk((long)index, 4L);
      PlatformDependent.putInt(this.addr((long)index), Integer.reverseBytes(value));
   }

   public NettyArrowBuf setInt(int index, int value) {
      this.arrowBuf.setInt((long)index, value);
      return this;
   }

   protected void _setLong(int index, long value) {
      this.setLong(index, value);
   }

   public void _setLongLE(int index, long value) {
      this.chk((long)index, 8L);
      PlatformDependent.putLong(this.addr((long)index), Long.reverseBytes(value));
   }

   public NettyArrowBuf setLong(int index, long value) {
      this.arrowBuf.setLong((long)index, value);
      return this;
   }

   public static NettyArrowBuf unwrapBuffer(ArrowBuf buf) {
      NettyArrowBuf nettyArrowBuf = new NettyArrowBuf(buf, buf.getReferenceManager().getAllocator(), LargeMemoryUtil.checkedCastToInt(buf.capacity()));
      nettyArrowBuf.readerIndex(LargeMemoryUtil.checkedCastToInt(buf.readerIndex()));
      nettyArrowBuf.writerIndex(LargeMemoryUtil.checkedCastToInt(buf.writerIndex()));
      return nettyArrowBuf;
   }
}
