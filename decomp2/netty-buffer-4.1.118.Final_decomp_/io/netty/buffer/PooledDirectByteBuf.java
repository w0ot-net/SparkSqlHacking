package io.netty.buffer;

import io.netty.util.internal.ObjectPool;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

final class PooledDirectByteBuf extends PooledByteBuf {
   private static final ObjectPool RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator() {
      public PooledDirectByteBuf newObject(ObjectPool.Handle handle) {
         return new PooledDirectByteBuf(handle, 0);
      }
   });

   static PooledDirectByteBuf newInstance(int maxCapacity) {
      PooledDirectByteBuf buf = (PooledDirectByteBuf)RECYCLER.get();
      buf.reuse(maxCapacity);
      return buf;
   }

   private PooledDirectByteBuf(ObjectPool.Handle recyclerHandle, int maxCapacity) {
      super(recyclerHandle, maxCapacity);
   }

   protected ByteBuffer newInternalNioBuffer(ByteBuffer memory) {
      return memory.duplicate();
   }

   public boolean isDirect() {
      return true;
   }

   protected byte _getByte(int index) {
      return ((ByteBuffer)this.memory).get(this.idx(index));
   }

   protected short _getShort(int index) {
      return ((ByteBuffer)this.memory).getShort(this.idx(index));
   }

   protected short _getShortLE(int index) {
      return ByteBufUtil.swapShort(this._getShort(index));
   }

   protected int _getUnsignedMedium(int index) {
      index = this.idx(index);
      return (((ByteBuffer)this.memory).get(index) & 255) << 16 | (((ByteBuffer)this.memory).get(index + 1) & 255) << 8 | ((ByteBuffer)this.memory).get(index + 2) & 255;
   }

   protected int _getUnsignedMediumLE(int index) {
      index = this.idx(index);
      return ((ByteBuffer)this.memory).get(index) & 255 | (((ByteBuffer)this.memory).get(index + 1) & 255) << 8 | (((ByteBuffer)this.memory).get(index + 2) & 255) << 16;
   }

   protected int _getInt(int index) {
      return ((ByteBuffer)this.memory).getInt(this.idx(index));
   }

   protected int _getIntLE(int index) {
      return ByteBufUtil.swapInt(this._getInt(index));
   }

   protected long _getLong(int index) {
      return ((ByteBuffer)this.memory).getLong(this.idx(index));
   }

   protected long _getLongLE(int index) {
      return ByteBufUtil.swapLong(this._getLong(index));
   }

   public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
      this.checkDstIndex(index, length, dstIndex, dst.capacity());
      if (dst.hasArray()) {
         this.getBytes(index, dst.array(), dst.arrayOffset() + dstIndex, length);
      } else if (dst.nioBufferCount() > 0) {
         for(ByteBuffer bb : dst.nioBuffers(dstIndex, length)) {
            int bbLen = bb.remaining();
            this.getBytes(index, bb);
            index += bbLen;
         }
      } else {
         dst.setBytes(dstIndex, (ByteBuf)this, index, length);
      }

      return this;
   }

   public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
      this.checkDstIndex(index, length, dstIndex, dst.length);
      this._internalNioBuffer(index, length, true).get(dst, dstIndex, length);
      return this;
   }

   public ByteBuf readBytes(byte[] dst, int dstIndex, int length) {
      this.checkDstIndex(length, dstIndex, dst.length);
      this._internalNioBuffer(this.readerIndex, length, false).get(dst, dstIndex, length);
      this.readerIndex += length;
      return this;
   }

   public ByteBuf getBytes(int index, ByteBuffer dst) {
      dst.put(this.duplicateInternalNioBuffer(index, dst.remaining()));
      return this;
   }

   public ByteBuf readBytes(ByteBuffer dst) {
      int length = dst.remaining();
      this.checkReadableBytes(length);
      dst.put(this._internalNioBuffer(this.readerIndex, length, false));
      this.readerIndex += length;
      return this;
   }

   public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
      this.getBytes(index, out, length, false);
      return this;
   }

   private void getBytes(int index, OutputStream out, int length, boolean internal) throws IOException {
      this.checkIndex(index, length);
      if (length != 0) {
         ByteBufUtil.readBytes(this.alloc(), internal ? this.internalNioBuffer() : ((ByteBuffer)this.memory).duplicate(), this.idx(index), length, out);
      }
   }

   public ByteBuf readBytes(OutputStream out, int length) throws IOException {
      this.checkReadableBytes(length);
      this.getBytes(this.readerIndex, out, length, true);
      this.readerIndex += length;
      return this;
   }

   protected void _setByte(int index, int value) {
      ((ByteBuffer)this.memory).put(this.idx(index), (byte)value);
   }

   protected void _setShort(int index, int value) {
      ((ByteBuffer)this.memory).putShort(this.idx(index), (short)value);
   }

   protected void _setShortLE(int index, int value) {
      this._setShort(index, ByteBufUtil.swapShort((short)value));
   }

   protected void _setMedium(int index, int value) {
      index = this.idx(index);
      ((ByteBuffer)this.memory).put(index, (byte)(value >>> 16));
      ((ByteBuffer)this.memory).put(index + 1, (byte)(value >>> 8));
      ((ByteBuffer)this.memory).put(index + 2, (byte)value);
   }

   protected void _setMediumLE(int index, int value) {
      index = this.idx(index);
      ((ByteBuffer)this.memory).put(index, (byte)value);
      ((ByteBuffer)this.memory).put(index + 1, (byte)(value >>> 8));
      ((ByteBuffer)this.memory).put(index + 2, (byte)(value >>> 16));
   }

   protected void _setInt(int index, int value) {
      ((ByteBuffer)this.memory).putInt(this.idx(index), value);
   }

   protected void _setIntLE(int index, int value) {
      this._setInt(index, ByteBufUtil.swapInt(value));
   }

   protected void _setLong(int index, long value) {
      ((ByteBuffer)this.memory).putLong(this.idx(index), value);
   }

   protected void _setLongLE(int index, long value) {
      this._setLong(index, ByteBufUtil.swapLong(value));
   }

   public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
      this.checkSrcIndex(index, length, srcIndex, src.capacity());
      if (src.hasArray()) {
         this.setBytes(index, src.array(), src.arrayOffset() + srcIndex, length);
      } else if (src.nioBufferCount() > 0) {
         for(ByteBuffer bb : src.nioBuffers(srcIndex, length)) {
            int bbLen = bb.remaining();
            this.setBytes(index, bb);
            index += bbLen;
         }
      } else {
         src.getBytes(srcIndex, (ByteBuf)this, index, length);
      }

      return this;
   }

   public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
      this.checkSrcIndex(index, length, srcIndex, src.length);
      this._internalNioBuffer(index, length, false).put(src, srcIndex, length);
      return this;
   }

   public ByteBuf setBytes(int index, ByteBuffer src) {
      int length = src.remaining();
      this.checkIndex(index, length);
      ByteBuffer tmpBuf = this.internalNioBuffer();
      if (src == tmpBuf) {
         src = src.duplicate();
      }

      index = this.idx(index);
      tmpBuf.limit(index + length).position(index);
      tmpBuf.put(src);
      return this;
   }

   public int setBytes(int index, InputStream in, int length) throws IOException {
      this.checkIndex(index, length);
      byte[] tmp = ByteBufUtil.threadLocalTempArray(length);
      int readBytes = in.read(tmp, 0, length);
      if (readBytes <= 0) {
         return readBytes;
      } else {
         ByteBuffer tmpBuf = this.internalNioBuffer();
         tmpBuf.position(this.idx(index));
         tmpBuf.put(tmp, 0, readBytes);
         return readBytes;
      }
   }

   public ByteBuf copy(int index, int length) {
      this.checkIndex(index, length);
      ByteBuf copy = this.alloc().directBuffer(length, this.maxCapacity());
      return copy.writeBytes((ByteBuf)this, index, length);
   }

   public boolean hasArray() {
      return false;
   }

   public byte[] array() {
      throw new UnsupportedOperationException("direct buffer");
   }

   public int arrayOffset() {
      throw new UnsupportedOperationException("direct buffer");
   }

   public boolean hasMemoryAddress() {
      return false;
   }

   public long memoryAddress() {
      throw new UnsupportedOperationException();
   }
}
