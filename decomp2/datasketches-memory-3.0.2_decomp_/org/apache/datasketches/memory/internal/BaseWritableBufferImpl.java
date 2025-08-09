package org.apache.datasketches.memory.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;
import org.apache.datasketches.memory.Buffer;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.ReadOnlyException;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;

public abstract class BaseWritableBufferImpl extends PositionalImpl implements WritableBuffer {
   BaseWritableBufferImpl(long capacityBytes) {
      super(capacityBytes);
   }

   public static WritableBuffer wrapByteBuffer(ByteBuffer byteBuffer, boolean localReadOnly, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(byteBuffer, "byteBuffer must not be null");
      Objects.requireNonNull(byteOrder, "byteOrder must not be null");
      AccessByteBuffer abb = new AccessByteBuffer(byteBuffer);
      int typeId = !abb.resourceReadOnly && !localReadOnly ? 0 : 1;
      long cumOffsetBytes = abb.initialCumOffset;
      BaseWritableBufferImpl bwbi = (BaseWritableBufferImpl)(Util.isNativeByteOrder(byteOrder) ? new BBWritableBufferImpl(abb.unsafeObj, abb.nativeBaseOffset, abb.offsetBytes, abb.capacityBytes, typeId, cumOffsetBytes, memReqSvr, byteBuffer) : new BBNonNativeWritableBufferImpl(abb.unsafeObj, abb.nativeBaseOffset, abb.offsetBytes, abb.capacityBytes, typeId, cumOffsetBytes, memReqSvr, byteBuffer));
      bwbi.setStartPositionEnd(0L, (long)byteBuffer.position(), (long)byteBuffer.limit());
      return bwbi;
   }

   public Buffer region() {
      return this.writableRegionImpl(this.getPosition(), this.getEnd() - this.getPosition(), true, this.getTypeByteOrder());
   }

   public Buffer region(long offsetBytes, long capacityBytes, ByteOrder byteOrder) {
      WritableBuffer buf = this.writableRegionImpl(offsetBytes, capacityBytes, true, byteOrder);
      buf.setStartPositionEnd(0L, 0L, capacityBytes);
      return buf;
   }

   public WritableBuffer writableRegion() {
      return this.writableRegionImpl(this.getPosition(), this.getEnd() - this.getPosition(), false, this.getTypeByteOrder());
   }

   public WritableBuffer writableRegion(long offsetBytes, long capacityBytes, ByteOrder byteOrder) {
      WritableBuffer wbuf = this.writableRegionImpl(offsetBytes, capacityBytes, false, byteOrder);
      wbuf.setStartPositionEnd(0L, 0L, capacityBytes);
      return wbuf;
   }

   WritableBuffer writableRegionImpl(long offsetBytes, long capacityBytes, boolean localReadOnly, ByteOrder byteOrder) {
      if (this.isReadOnly() && !localReadOnly) {
         throw new ReadOnlyException("Writable region of a read-only Buffer is not allowed.");
      } else {
         this.checkValidAndBounds(offsetBytes, capacityBytes);
         boolean readOnly = this.isReadOnly() || localReadOnly;
         WritableBuffer wbuf = this.toWritableRegion(offsetBytes, capacityBytes, readOnly, byteOrder);
         wbuf.setStartPositionEnd(0L, 0L, capacityBytes);
         return wbuf;
      }
   }

   abstract WritableBuffer toWritableRegion(long var1, long var3, boolean var5, ByteOrder var6);

   public Buffer duplicate() {
      return this.writableDuplicateImpl(true, this.getTypeByteOrder());
   }

   public Buffer duplicate(ByteOrder byteOrder) {
      return this.writableDuplicateImpl(true, byteOrder);
   }

   public WritableBuffer writableDuplicate() {
      return this.writableDuplicateImpl(false, this.getTypeByteOrder());
   }

   public WritableBuffer writableDuplicate(ByteOrder byteOrder) {
      return this.writableDuplicateImpl(false, byteOrder);
   }

   WritableBuffer writableDuplicateImpl(boolean localReadOnly, ByteOrder byteOrder) {
      if (this.isReadOnly() && !localReadOnly) {
         throw new ReadOnlyException("Writable duplicate of a read-only Buffer is not allowed.");
      } else {
         boolean finalReadOnly = this.isReadOnly() || localReadOnly;
         WritableBuffer wbuf = this.toDuplicate(finalReadOnly, byteOrder);
         wbuf.setStartPositionEnd(this.getStart(), this.getPosition(), this.getEnd());
         return wbuf;
      }
   }

   abstract BaseWritableBufferImpl toDuplicate(boolean var1, ByteOrder var2);

   public Memory asMemory(ByteOrder byteOrder) {
      return this.asWritableMemory(true, byteOrder);
   }

   public WritableMemory asWritableMemory(ByteOrder byteOrder) {
      return this.asWritableMemory(false, byteOrder);
   }

   WritableMemory asWritableMemory(boolean localReadOnly, ByteOrder byteOrder) {
      Objects.requireNonNull(byteOrder, "byteOrder must be non-null");
      if (this.isReadOnly() && !localReadOnly) {
         throw new ReadOnlyException("Converting a read-only Buffer to a writable Memory is not allowed.");
      } else {
         boolean finalReadOnly = this.isReadOnly() || localReadOnly;
         WritableMemory wmem = this.toWritableMemory(finalReadOnly, byteOrder);
         return wmem;
      }
   }

   abstract WritableMemory toWritableMemory(boolean var1, ByteOrder var2);

   public final boolean getBoolean() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 1L);
      return UnsafeUtil.unsafe.getBoolean(this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public final boolean getBoolean(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 1L);
      return UnsafeUtil.unsafe.getBoolean(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public final byte getByte() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 1L);
      return UnsafeUtil.unsafe.getByte(this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public final byte getByte(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 1L);
      return UnsafeUtil.unsafe.getByte(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public final void getByteArray(byte[] dstArray, int dstOffsetBytes, int lengthBytes) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthBytes;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetBytes, (long)lengthBytes, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(pos), dstArray, UnsafeUtil.ARRAY_BYTE_BASE_OFFSET + (long)dstOffsetBytes, copyBytes);
   }

   final char getNativeOrderedChar() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 2L);
      return UnsafeUtil.unsafe.getChar(this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   final char getNativeOrderedChar(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 2L);
      return UnsafeUtil.unsafe.getChar(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   final int getNativeOrderedInt() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 4L);
      return UnsafeUtil.unsafe.getInt(this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   final int getNativeOrderedInt(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 4L);
      return UnsafeUtil.unsafe.getInt(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   final long getNativeOrderedLong() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 8L);
      return UnsafeUtil.unsafe.getLong(this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   final long getNativeOrderedLong(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 8L);
      return UnsafeUtil.unsafe.getLong(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   final short getNativeOrderedShort() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 2L);
      return UnsafeUtil.unsafe.getShort(this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   final short getNativeOrderedShort(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 2L);
      return UnsafeUtil.unsafe.getShort(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public final int compareTo(long thisOffsetBytes, long thisLengthBytes, Buffer thatBuf, long thatOffsetBytes, long thatLengthBytes) {
      return CompareAndCopy.compare(this, thisOffsetBytes, thisLengthBytes, (ResourceImpl)thatBuf, thatOffsetBytes, thatLengthBytes);
   }

   public final void putBoolean(boolean value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 1L);
      UnsafeUtil.unsafe.putBoolean(this.getUnsafeObject(), this.getCumulativeOffset(pos), value);
   }

   public final void putBoolean(long offsetBytes, boolean value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 1L);
      UnsafeUtil.unsafe.putBoolean(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public final void putByte(byte value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 1L);
      UnsafeUtil.unsafe.putByte(this.getUnsafeObject(), this.getCumulativeOffset(pos), value);
   }

   public final void putByte(long offsetBytes, byte value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 1L);
      UnsafeUtil.unsafe.putByte(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public final void putByteArray(byte[] srcArray, int srcOffsetBytes, int lengthBytes) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthBytes;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetBytes, (long)lengthBytes, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_BYTE_BASE_OFFSET + (long)srcOffsetBytes, this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes);
   }

   final void putNativeOrderedChar(char value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 2L);
      UnsafeUtil.unsafe.putChar(this.getUnsafeObject(), this.getCumulativeOffset(pos), value);
   }

   final void putNativeOrderedChar(long offsetBytes, char value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 2L);
      UnsafeUtil.unsafe.putChar(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   final void putNativeOrderedInt(int value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 4L);
      UnsafeUtil.unsafe.putInt(this.getUnsafeObject(), this.getCumulativeOffset(pos), value);
   }

   final void putNativeOrderedInt(long offsetBytes, int value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 4L);
      UnsafeUtil.unsafe.putInt(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   final void putNativeOrderedLong(long value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 8L);
      UnsafeUtil.unsafe.putLong(this.getUnsafeObject(), this.getCumulativeOffset(pos), value);
   }

   final void putNativeOrderedLong(long offsetBytes, long value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 8L);
      UnsafeUtil.unsafe.putLong(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   final void putNativeOrderedShort(short value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 2L);
      UnsafeUtil.unsafe.putShort(this.getUnsafeObject(), this.getCumulativeOffset(pos), value);
   }

   final void putNativeOrderedShort(long offsetBytes, short value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 2L);
      UnsafeUtil.unsafe.putShort(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public final Object getArray() {
      this.checkValid();
      return this.getUnsafeObject();
   }

   public final void clear() {
      this.fill((byte)0);
   }

   public final void fill(byte value) {
      this.checkNotReadOnly();
      long pos = this.getPosition();
      long len = this.getEnd() - pos;
      checkInvariants(this.getStart(), pos + len, this.getEnd(), this.getCapacity());

      while(len > 0L) {
         long chunk = Math.min(len, 1048576L);
         UnsafeUtil.unsafe.setMemory(this.getUnsafeObject(), this.getCumulativeOffset(pos), chunk, value);
         pos += chunk;
         len -= chunk;
      }

   }
}
