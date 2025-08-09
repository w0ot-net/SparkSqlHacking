package io.netty.buffer;

import io.netty.util.internal.ObjectPool;
import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

class PooledHeapByteBuf extends PooledByteBuf {
   private static final ObjectPool RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator() {
      public PooledHeapByteBuf newObject(ObjectPool.Handle handle) {
         return new PooledHeapByteBuf(handle, 0);
      }
   });

   static PooledHeapByteBuf newInstance(int maxCapacity) {
      PooledHeapByteBuf buf = (PooledHeapByteBuf)RECYCLER.get();
      buf.reuse(maxCapacity);
      return buf;
   }

   PooledHeapByteBuf(ObjectPool.Handle recyclerHandle, int maxCapacity) {
      super(recyclerHandle, maxCapacity);
   }

   public final boolean isDirect() {
      return false;
   }

   protected byte _getByte(int index) {
      return HeapByteBufUtil.getByte((byte[])this.memory, this.idx(index));
   }

   protected short _getShort(int index) {
      return HeapByteBufUtil.getShort((byte[])this.memory, this.idx(index));
   }

   protected short _getShortLE(int index) {
      return HeapByteBufUtil.getShortLE((byte[])this.memory, this.idx(index));
   }

   protected int _getUnsignedMedium(int index) {
      return HeapByteBufUtil.getUnsignedMedium((byte[])this.memory, this.idx(index));
   }

   protected int _getUnsignedMediumLE(int index) {
      return HeapByteBufUtil.getUnsignedMediumLE((byte[])this.memory, this.idx(index));
   }

   protected int _getInt(int index) {
      return HeapByteBufUtil.getInt((byte[])this.memory, this.idx(index));
   }

   protected int _getIntLE(int index) {
      return HeapByteBufUtil.getIntLE((byte[])this.memory, this.idx(index));
   }

   protected long _getLong(int index) {
      return HeapByteBufUtil.getLong((byte[])this.memory, this.idx(index));
   }

   protected long _getLongLE(int index) {
      return HeapByteBufUtil.getLongLE((byte[])this.memory, this.idx(index));
   }

   public final ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
      this.checkDstIndex(index, length, dstIndex, dst.capacity());
      if (dst.hasMemoryAddress()) {
         PlatformDependent.copyMemory((byte[])this.memory, this.idx(index), dst.memoryAddress() + (long)dstIndex, (long)length);
      } else if (dst.hasArray()) {
         this.getBytes(index, dst.array(), dst.arrayOffset() + dstIndex, length);
      } else {
         dst.setBytes(dstIndex, (byte[])this.memory, this.idx(index), length);
      }

      return this;
   }

   public final ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
      this.checkDstIndex(index, length, dstIndex, dst.length);
      System.arraycopy(this.memory, this.idx(index), dst, dstIndex, length);
      return this;
   }

   public final ByteBuf getBytes(int index, ByteBuffer dst) {
      int length = dst.remaining();
      this.checkIndex(index, length);
      dst.put((byte[])this.memory, this.idx(index), length);
      return this;
   }

   public final ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
      this.checkIndex(index, length);
      out.write((byte[])this.memory, this.idx(index), length);
      return this;
   }

   protected void _setByte(int index, int value) {
      HeapByteBufUtil.setByte((byte[])this.memory, this.idx(index), value);
   }

   protected void _setShort(int index, int value) {
      HeapByteBufUtil.setShort((byte[])this.memory, this.idx(index), value);
   }

   protected void _setShortLE(int index, int value) {
      HeapByteBufUtil.setShortLE((byte[])this.memory, this.idx(index), value);
   }

   protected void _setMedium(int index, int value) {
      HeapByteBufUtil.setMedium((byte[])this.memory, this.idx(index), value);
   }

   protected void _setMediumLE(int index, int value) {
      HeapByteBufUtil.setMediumLE((byte[])this.memory, this.idx(index), value);
   }

   protected void _setInt(int index, int value) {
      HeapByteBufUtil.setInt((byte[])this.memory, this.idx(index), value);
   }

   protected void _setIntLE(int index, int value) {
      HeapByteBufUtil.setIntLE((byte[])this.memory, this.idx(index), value);
   }

   protected void _setLong(int index, long value) {
      HeapByteBufUtil.setLong((byte[])this.memory, this.idx(index), value);
   }

   protected void _setLongLE(int index, long value) {
      HeapByteBufUtil.setLongLE((byte[])this.memory, this.idx(index), value);
   }

   public final ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
      this.checkSrcIndex(index, length, srcIndex, src.capacity());
      if (src.hasMemoryAddress()) {
         PlatformDependent.copyMemory(src.memoryAddress() + (long)srcIndex, (byte[])this.memory, this.idx(index), (long)length);
      } else if (src.hasArray()) {
         this.setBytes(index, src.array(), src.arrayOffset() + srcIndex, length);
      } else {
         src.getBytes(srcIndex, (byte[])this.memory, this.idx(index), length);
      }

      return this;
   }

   public final ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
      this.checkSrcIndex(index, length, srcIndex, src.length);
      System.arraycopy(src, srcIndex, this.memory, this.idx(index), length);
      return this;
   }

   public final ByteBuf setBytes(int index, ByteBuffer src) {
      int length = src.remaining();
      this.checkIndex(index, length);
      src.get((byte[])this.memory, this.idx(index), length);
      return this;
   }

   public final int setBytes(int index, InputStream in, int length) throws IOException {
      this.checkIndex(index, length);
      return in.read((byte[])this.memory, this.idx(index), length);
   }

   public final ByteBuf copy(int index, int length) {
      this.checkIndex(index, length);
      ByteBuf copy = this.alloc().heapBuffer(length, this.maxCapacity());
      return copy.writeBytes((byte[])this.memory, this.idx(index), length);
   }

   final ByteBuffer duplicateInternalNioBuffer(int index, int length) {
      this.checkIndex(index, length);
      return ByteBuffer.wrap((byte[])this.memory, this.idx(index), length).slice();
   }

   public final boolean hasArray() {
      return true;
   }

   public final byte[] array() {
      this.ensureAccessible();
      return (byte[])this.memory;
   }

   public final int arrayOffset() {
      return this.offset;
   }

   public final boolean hasMemoryAddress() {
      return false;
   }

   public final long memoryAddress() {
      throw new UnsupportedOperationException();
   }

   protected final ByteBuffer newInternalNioBuffer(byte[] memory) {
      return ByteBuffer.wrap(memory);
   }
}
