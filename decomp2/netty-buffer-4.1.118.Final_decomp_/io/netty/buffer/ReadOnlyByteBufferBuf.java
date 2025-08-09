package io.netty.buffer;

import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

class ReadOnlyByteBufferBuf extends AbstractReferenceCountedByteBuf {
   protected final ByteBuffer buffer;
   private final ByteBufAllocator allocator;
   private ByteBuffer tmpNioBuf;

   ReadOnlyByteBufferBuf(ByteBufAllocator allocator, ByteBuffer buffer) {
      super(buffer.remaining());
      if (!buffer.isReadOnly()) {
         throw new IllegalArgumentException("must be a readonly buffer: " + StringUtil.simpleClassName(buffer));
      } else {
         this.allocator = allocator;
         this.buffer = buffer.slice().order(ByteOrder.BIG_ENDIAN);
         this.writerIndex(this.buffer.limit());
      }
   }

   protected void deallocate() {
   }

   public boolean isWritable() {
      return false;
   }

   public boolean isWritable(int numBytes) {
      return false;
   }

   public ByteBuf ensureWritable(int minWritableBytes) {
      throw new ReadOnlyBufferException();
   }

   public int ensureWritable(int minWritableBytes, boolean force) {
      return 1;
   }

   public byte getByte(int index) {
      this.ensureAccessible();
      return this._getByte(index);
   }

   protected byte _getByte(int index) {
      return this.buffer.get(index);
   }

   public short getShort(int index) {
      this.ensureAccessible();
      return this._getShort(index);
   }

   protected short _getShort(int index) {
      return this.buffer.getShort(index);
   }

   public short getShortLE(int index) {
      this.ensureAccessible();
      return this._getShortLE(index);
   }

   protected short _getShortLE(int index) {
      return ByteBufUtil.swapShort(this.buffer.getShort(index));
   }

   public int getUnsignedMedium(int index) {
      this.ensureAccessible();
      return this._getUnsignedMedium(index);
   }

   protected int _getUnsignedMedium(int index) {
      return (this.getByte(index) & 255) << 16 | (this.getByte(index + 1) & 255) << 8 | this.getByte(index + 2) & 255;
   }

   public int getUnsignedMediumLE(int index) {
      this.ensureAccessible();
      return this._getUnsignedMediumLE(index);
   }

   protected int _getUnsignedMediumLE(int index) {
      return this.getByte(index) & 255 | (this.getByte(index + 1) & 255) << 8 | (this.getByte(index + 2) & 255) << 16;
   }

   public int getInt(int index) {
      this.ensureAccessible();
      return this._getInt(index);
   }

   protected int _getInt(int index) {
      return this.buffer.getInt(index);
   }

   public int getIntLE(int index) {
      this.ensureAccessible();
      return this._getIntLE(index);
   }

   protected int _getIntLE(int index) {
      return ByteBufUtil.swapInt(this.buffer.getInt(index));
   }

   public long getLong(int index) {
      this.ensureAccessible();
      return this._getLong(index);
   }

   protected long _getLong(int index) {
      return this.buffer.getLong(index);
   }

   public long getLongLE(int index) {
      this.ensureAccessible();
      return this._getLongLE(index);
   }

   protected long _getLongLE(int index) {
      return ByteBufUtil.swapLong(this.buffer.getLong(index));
   }

   public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
      return this.getBytes(index, dst, dstIndex, length, false);
   }

   public ByteBuf readBytes(ByteBuf dst, int dstIndex, int length) {
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, dst, dstIndex, length, true);
      this.readerIndex += length;
      return this;
   }

   protected ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length, boolean internal) {
      this.checkDstIndex(index, length, dstIndex, dst.capacity());
      if (dst.hasArray()) {
         this.getBytes(index, dst.array(), dst.arrayOffset() + dstIndex, length);
      } else if (dst.nioBufferCount() > 0) {
         for(ByteBuffer bb : dst.nioBuffers(dstIndex, length)) {
            int bbLen = bb.remaining();
            this.getBytes(index, bb, internal);
            index += bbLen;
         }
      } else {
         dst.setBytes(dstIndex, (ByteBuf)this, index, length);
      }

      return this;
   }

   public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
      return this.getBytes(index, dst, dstIndex, length, false);
   }

   public ByteBuf readBytes(byte[] dst, int dstIndex, int length) {
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, dst, dstIndex, length, true);
      this.readerIndex += length;
      return this;
   }

   protected ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length, boolean internal) {
      this.checkDstIndex(index, length, dstIndex, dst.length);
      ByteBuffer tmpBuf = this.nioBuffer(internal);
      tmpBuf.clear().position(index).limit(index + length);
      tmpBuf.get(dst, dstIndex, length);
      return this;
   }

   public ByteBuf getBytes(int index, ByteBuffer dst) {
      return this.getBytes(index, dst, false);
   }

   public ByteBuf readBytes(ByteBuffer dst) {
      int length = dst.remaining();
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, dst, true);
      this.readerIndex += length;
      return this;
   }

   private ByteBuf getBytes(int index, ByteBuffer dst, boolean internal) {
      this.checkIndex(index, dst.remaining());
      ByteBuffer tmpBuf = this.nioBuffer(internal);
      tmpBuf.clear().position(index).limit(index + dst.remaining());
      dst.put(tmpBuf);
      return this;
   }

   public ByteBuf setByte(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setByte(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setShort(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setShort(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setShortLE(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setShortLE(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setMedium(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setMedium(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setMediumLE(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setMediumLE(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setInt(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setInt(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setIntLE(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setIntLE(int index, int value) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setLong(int index, long value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setLong(int index, long value) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setLongLE(int index, long value) {
      throw new ReadOnlyBufferException();
   }

   protected void _setLongLE(int index, long value) {
      throw new ReadOnlyBufferException();
   }

   public int capacity() {
      return this.maxCapacity();
   }

   public ByteBuf capacity(int newCapacity) {
      throw new ReadOnlyBufferException();
   }

   public ByteBufAllocator alloc() {
      return this.allocator;
   }

   public ByteOrder order() {
      return ByteOrder.BIG_ENDIAN;
   }

   public ByteBuf unwrap() {
      return null;
   }

   public boolean isReadOnly() {
      return this.buffer.isReadOnly();
   }

   public boolean isDirect() {
      return this.buffer.isDirect();
   }

   public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
      return this.getBytes(index, out, length, false);
   }

   public ByteBuf readBytes(OutputStream out, int length) throws IOException {
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, out, length, true);
      this.readerIndex += length;
      return this;
   }

   private ByteBuf getBytes(int index, OutputStream out, int length, boolean internal) throws IOException {
      this.ensureAccessible();
      if (length == 0) {
         return this;
      } else {
         if (this.buffer.hasArray()) {
            out.write(this.buffer.array(), index + this.buffer.arrayOffset(), length);
         } else {
            byte[] tmp = ByteBufUtil.threadLocalTempArray(length);
            ByteBuffer tmpBuf = this.nioBuffer(internal);
            tmpBuf.clear().position(index);
            tmpBuf.get(tmp, 0, length);
            out.write(tmp, 0, length);
         }

         return this;
      }
   }

   public int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
      return this.getBytes(index, out, length, false);
   }

   public int readBytes(GatheringByteChannel out, int length) throws IOException {
      this.checkReadableBytes(length);
      int readBytes = this.getBytes(this.readerIndex, out, length, true);
      this.readerIndex += readBytes;
      return readBytes;
   }

   private int getBytes(int index, GatheringByteChannel out, int length, boolean internal) throws IOException {
      this.ensureAccessible();
      if (length == 0) {
         return 0;
      } else {
         ByteBuffer tmpBuf = this.nioBuffer(internal);
         tmpBuf.clear().position(index).limit(index + length);
         return out.write(tmpBuf);
      }
   }

   public int getBytes(int index, FileChannel out, long position, int length) throws IOException {
      return this.getBytes(index, out, position, length, false);
   }

   public int readBytes(FileChannel out, long position, int length) throws IOException {
      this.checkReadableBytes(length);
      int readBytes = this.getBytes(this.readerIndex, out, position, length, true);
      this.readerIndex += readBytes;
      return readBytes;
   }

   private int getBytes(int index, FileChannel out, long position, int length, boolean internal) throws IOException {
      this.ensureAccessible();
      if (length == 0) {
         return 0;
      } else {
         ByteBuffer tmpBuf = this.nioBuffer(internal);
         tmpBuf.clear().position(index).limit(index + length);
         return out.write(tmpBuf, position);
      }
   }

   public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuf setBytes(int index, ByteBuffer src) {
      throw new ReadOnlyBufferException();
   }

   public int setBytes(int index, InputStream in, int length) throws IOException {
      throw new ReadOnlyBufferException();
   }

   public int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
      throw new ReadOnlyBufferException();
   }

   public int setBytes(int index, FileChannel in, long position, int length) throws IOException {
      throw new ReadOnlyBufferException();
   }

   protected final ByteBuffer internalNioBuffer() {
      ByteBuffer tmpNioBuf = this.tmpNioBuf;
      if (tmpNioBuf == null) {
         this.tmpNioBuf = tmpNioBuf = this.buffer.duplicate();
      }

      return tmpNioBuf;
   }

   public ByteBuf copy(int index, int length) {
      this.ensureAccessible();

      ByteBuffer src;
      try {
         src = (ByteBuffer)this.buffer.duplicate().clear().position(index).limit(index + length);
      } catch (IllegalArgumentException var5) {
         throw new IndexOutOfBoundsException("Too many bytes to read - Need " + (index + length));
      }

      ByteBuf dst = src.isDirect() ? this.alloc().directBuffer(length) : this.alloc().heapBuffer(length);
      dst.writeBytes(src);
      return dst;
   }

   public int nioBufferCount() {
      return 1;
   }

   public ByteBuffer[] nioBuffers(int index, int length) {
      return new ByteBuffer[]{this.nioBuffer(index, length)};
   }

   public ByteBuffer nioBuffer(int index, int length) {
      this.checkIndex(index, length);
      return (ByteBuffer)this.buffer.duplicate().position(index).limit(index + length);
   }

   public ByteBuffer internalNioBuffer(int index, int length) {
      this.ensureAccessible();
      return (ByteBuffer)this.internalNioBuffer().clear().position(index).limit(index + length);
   }

   public final boolean isContiguous() {
      return true;
   }

   public boolean hasArray() {
      return this.buffer.hasArray();
   }

   public byte[] array() {
      return this.buffer.array();
   }

   public int arrayOffset() {
      return this.buffer.arrayOffset();
   }

   public boolean hasMemoryAddress() {
      return false;
   }

   public long memoryAddress() {
      throw new UnsupportedOperationException();
   }

   private ByteBuffer nioBuffer(boolean internal) {
      return internal ? this.internalNioBuffer() : this.buffer.duplicate();
   }

   public ByteBuf duplicate() {
      return new ReadOnlyDuplicatedByteBuf(this);
   }

   public ByteBuf slice(int index, int length) {
      return new ReadOnlySlicedByteBuf(this, index, length);
   }

   public ByteBuf asReadOnly() {
      return this;
   }

   private static final class ReadOnlySlicedByteBuf extends SlicedByteBuf {
      ReadOnlySlicedByteBuf(ByteBuf buffer, int index, int length) {
         super(buffer, index, length);
      }

      public ByteBuf asReadOnly() {
         return this;
      }

      public ByteBuf slice(int index, int length) {
         return new ReadOnlySlicedByteBuf(this, index, length);
      }

      public ByteBuf duplicate() {
         return this.slice(0, this.capacity()).setIndex(this.readerIndex(), this.writerIndex());
      }

      public boolean isWritable() {
         return false;
      }

      public boolean isWritable(int numBytes) {
         return false;
      }

      public int ensureWritable(int minWritableBytes, boolean force) {
         return 1;
      }
   }

   private static final class ReadOnlyDuplicatedByteBuf extends DuplicatedByteBuf {
      ReadOnlyDuplicatedByteBuf(ByteBuf buffer) {
         super(buffer);
      }

      public ByteBuf asReadOnly() {
         return this;
      }

      public ByteBuf slice(int index, int length) {
         return new ReadOnlySlicedByteBuf(this, index, length);
      }

      public ByteBuf duplicate() {
         return new ReadOnlyDuplicatedByteBuf(this);
      }

      public boolean isWritable() {
         return false;
      }

      public boolean isWritable(int numBytes) {
         return false;
      }

      public int ensureWritable(int minWritableBytes, boolean force) {
         return 1;
      }
   }
}
