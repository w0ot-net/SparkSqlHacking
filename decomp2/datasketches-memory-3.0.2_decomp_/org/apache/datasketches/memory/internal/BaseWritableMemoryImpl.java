package org.apache.datasketches.memory.internal;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.WritableByteChannel;
import java.util.Objects;
import org.apache.datasketches.memory.Buffer;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.ReadOnlyException;
import org.apache.datasketches.memory.WritableBuffer;
import org.apache.datasketches.memory.WritableMemory;

public abstract class BaseWritableMemoryImpl extends ResourceImpl implements WritableMemory {
   private static final byte[] EMPTY_BYTES = new byte[1024];

   BaseWritableMemoryImpl() {
   }

   public static WritableMemory wrapHeapArray(Object array, long offsetBytes, long lengthBytes, boolean localReadOnly, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(array, "array must be non-null");
      Util.negativeCheck(offsetBytes, "offsetBytes");
      Util.negativeCheck(lengthBytes, "lengthBytes");
      Objects.requireNonNull(byteOrder, "byteOrder must be non-null");
      if (array instanceof byte[]) {
         ResourceImpl.checkBounds(offsetBytes, lengthBytes, (long)((byte[])((byte[])array)).length);
      }

      long cumOffsetBytes = UnsafeUtil.getArrayBaseOffset(array.getClass()) + offsetBytes;
      int typeId = localReadOnly ? 1 : 0;
      return (WritableMemory)(Util.isNativeByteOrder(byteOrder) ? new HeapWritableMemoryImpl(array, offsetBytes, lengthBytes, typeId, cumOffsetBytes, memReqSvr) : new HeapNonNativeWritableMemoryImpl(array, offsetBytes, lengthBytes, typeId, cumOffsetBytes, memReqSvr));
   }

   public static WritableMemory wrapByteBuffer(ByteBuffer byteBuffer, boolean localReadOnly, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      Objects.requireNonNull(byteBuffer, "byteBuf must be non-null");
      Objects.requireNonNull(byteOrder, "byteOrder must be non-null");
      AccessByteBuffer abb = new AccessByteBuffer(byteBuffer);
      int typeId = !abb.resourceReadOnly && !localReadOnly ? 0 : 1;
      long cumOffsetBytes = abb.offsetBytes + (abb.unsafeObj == null ? abb.nativeBaseOffset : UnsafeUtil.getArrayBaseOffset(abb.unsafeObj.getClass()));
      return (WritableMemory)(Util.isNativeByteOrder(byteOrder) ? new BBWritableMemoryImpl(abb.unsafeObj, abb.nativeBaseOffset, abb.offsetBytes, abb.capacityBytes, typeId, cumOffsetBytes, memReqSvr, byteBuffer) : new BBNonNativeWritableMemoryImpl(abb.unsafeObj, abb.nativeBaseOffset, abb.offsetBytes, abb.capacityBytes, typeId, cumOffsetBytes, memReqSvr, byteBuffer));
   }

   public static WritableMemory wrapMap(File file, long fileOffsetBytes, long capacityBytes, boolean localReadOnly, ByteOrder byteOrder) {
      Objects.requireNonNull(file, "File must be non-null.");
      Util.negativeCheck(fileOffsetBytes, "fileOffsetBytes");
      Util.negativeCheck(capacityBytes, "capacityBytes");
      Objects.requireNonNull(byteOrder, "ByteOrder must be non-null.");
      AllocateDirectWritableMap dirWMap = new AllocateDirectWritableMap(file, fileOffsetBytes, capacityBytes, localReadOnly);
      int typeId = !dirWMap.resourceReadOnly && !localReadOnly ? 0 : 1;
      long cumOffsetBytes = dirWMap.nativeBaseOffset;
      BaseWritableMemoryImpl wmem = (BaseWritableMemoryImpl)(Util.isNativeByteOrder(byteOrder) ? new MapWritableMemoryImpl(dirWMap, 0L, capacityBytes, typeId, cumOffsetBytes) : new MapNonNativeWritableMemoryImpl(dirWMap, 0L, capacityBytes, typeId, cumOffsetBytes));
      return wmem;
   }

   public static WritableMemory wrapDirect(long capacityBytes, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      Util.negativeCheck(capacityBytes, "capacityBytes");
      Objects.requireNonNull(byteOrder, "byteOrder must be non-null.");
      AllocateDirect direct = new AllocateDirect(capacityBytes);
      int typeId = 0;
      long nativeBaseOffset = direct.getNativeBaseOffset();
      BaseWritableMemoryImpl wmem = (BaseWritableMemoryImpl)(Util.isNativeByteOrder(byteOrder) ? new DirectWritableMemoryImpl(direct, 0L, capacityBytes, 0, nativeBaseOffset, memReqSvr) : new DirectNonNativeWritableMemoryImpl(direct, 0L, capacityBytes, 0, nativeBaseOffset, memReqSvr));
      return wmem;
   }

   public Memory region(long regionOffsetBytes, long capacityBytes, ByteOrder byteOrder) {
      return this.writableRegionImpl(regionOffsetBytes, capacityBytes, true, byteOrder);
   }

   public WritableMemory writableRegion(long regionOffsetBytes, long capacityBytes, ByteOrder byteOrder) {
      return this.writableRegionImpl(regionOffsetBytes, capacityBytes, false, byteOrder);
   }

   private WritableMemory writableRegionImpl(long regionOffsetBytes, long capacityBytes, boolean localReadOnly, ByteOrder byteOrder) {
      if (this.isReadOnly() && !localReadOnly) {
         throw new ReadOnlyException("Writable region of a read-only Memory is not allowed.");
      } else {
         Util.negativeCheck(regionOffsetBytes, "offsetBytes must be >= 0");
         Util.negativeCheck(capacityBytes, "capacityBytes must be >= 0");
         Objects.requireNonNull(byteOrder, "byteOrder must be non-null.");
         this.checkValidAndBounds(regionOffsetBytes, capacityBytes);
         boolean finalReadOnly = this.isReadOnly() || localReadOnly;
         return this.toWritableRegion(regionOffsetBytes, capacityBytes, finalReadOnly, byteOrder);
      }
   }

   abstract WritableMemory toWritableRegion(long var1, long var3, boolean var5, ByteOrder var6);

   public Buffer asBuffer(ByteOrder byteOrder) {
      return this.asWritableBuffer(true, byteOrder);
   }

   public WritableBuffer asWritableBuffer(ByteOrder byteOrder) {
      return this.asWritableBuffer(false, byteOrder);
   }

   private WritableBuffer asWritableBuffer(boolean localReadOnly, ByteOrder byteOrder) {
      Objects.requireNonNull(byteOrder, "byteOrder must be non-null");
      if (this.isReadOnly() && !localReadOnly) {
         throw new ReadOnlyException("Converting a read-only Memory to a writable Buffer is not allowed.");
      } else {
         boolean finalReadOnly = this.isReadOnly() || localReadOnly;
         WritableBuffer wbuf = this.toWritableBuffer(finalReadOnly, byteOrder);
         wbuf.setStartPositionEnd(0L, 0L, this.getCapacity());
         return wbuf;
      }
   }

   abstract WritableBuffer toWritableBuffer(boolean var1, ByteOrder var2);

   public final boolean getBoolean(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 1L);
      return UnsafeUtil.unsafe.getBoolean(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public final byte getByte(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 1L);
      return UnsafeUtil.unsafe.getByte(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public final void getByteArray(long offsetBytes, byte[] dstArray, int dstOffsetBytes, int lengthBytes) {
      long copyBytes = (long)lengthBytes;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetBytes, (long)lengthBytes, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), dstArray, UnsafeUtil.ARRAY_BYTE_BASE_OFFSET + (long)dstOffsetBytes, copyBytes);
   }

   final char getNativeOrderedChar(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 2L);
      return UnsafeUtil.unsafe.getChar(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   final int getNativeOrderedInt(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 4L);
      return UnsafeUtil.unsafe.getInt(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   final long getNativeOrderedLong(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 8L);
      return UnsafeUtil.unsafe.getLong(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   final short getNativeOrderedShort(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 2L);
      return UnsafeUtil.unsafe.getShort(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public final int compareTo(long thisOffsetBytes, long thisLengthBytes, Memory thatMem, long thatOffsetBytes, long thatLengthBytes) {
      return CompareAndCopy.compare(this, thisOffsetBytes, thisLengthBytes, (ResourceImpl)thatMem, thatOffsetBytes, thatLengthBytes);
   }

   public final void copyTo(long srcOffsetBytes, WritableMemory destination, long dstOffsetBytes, long lengthBytes) {
      CompareAndCopy.copy(this, srcOffsetBytes, (ResourceImpl)destination, dstOffsetBytes, lengthBytes);
   }

   public final void writeTo(long offsetBytes, long lengthBytes, WritableByteChannel out) throws IOException {
      this.checkValidAndBounds(offsetBytes, lengthBytes);
      if (this.getUnsafeObject() instanceof byte[]) {
         this.writeByteArrayTo((byte[])this.getUnsafeObject(), offsetBytes, lengthBytes, out);
      } else if (this.getUnsafeObject() == null) {
         this.writeDirectMemoryTo(offsetBytes, lengthBytes, out);
      } else {
         this.writeToWithExtraCopy(offsetBytes, lengthBytes, out);
      }

   }

   public final void putBoolean(long offsetBytes, boolean value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 1L);
      UnsafeUtil.unsafe.putBoolean(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public final void putByte(long offsetBytes, byte value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 1L);
      UnsafeUtil.unsafe.putByte(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public final void putByteArray(long offsetBytes, byte[] srcArray, int srcOffsetBytes, int lengthBytes) {
      long copyBytes = (long)lengthBytes;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetBytes, (long)lengthBytes, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_BYTE_BASE_OFFSET + (long)srcOffsetBytes, this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes);
   }

   final void putNativeOrderedChar(long offsetBytes, char value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 2L);
      UnsafeUtil.unsafe.putChar(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   final void putNativeOrderedInt(long offsetBytes, int value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 4L);
      UnsafeUtil.unsafe.putInt(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   final void putNativeOrderedLong(long offsetBytes, long value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 8L);
      UnsafeUtil.unsafe.putLong(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
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
      this.clear(0L, this.getCapacity());
   }

   public final void clear(long offsetBytes, long lengthBytes) {
      long endBytes = offsetBytes + lengthBytes;

      for(long i = offsetBytes; i < endBytes; i += (long)EMPTY_BYTES.length) {
         this.putByteArray(i, EMPTY_BYTES, 0, (int)Math.min((long)EMPTY_BYTES.length, endBytes - i));
      }

   }

   public final void clearBits(long offsetBytes, byte bitMask) {
      this.checkValidAndBoundsForWrite(offsetBytes, 1L);
      long cumBaseOff = this.getCumulativeOffset(offsetBytes);
      int value = UnsafeUtil.unsafe.getByte(this.getUnsafeObject(), cumBaseOff) & 255;
      value &= ~bitMask;
      UnsafeUtil.unsafe.putByte(this.getUnsafeObject(), cumBaseOff, (byte)value);
   }

   public final void fill(byte value) {
      this.fill(0L, this.getCapacity(), value);
   }

   public final void fill(long offsetBytes, long lengthBytes, byte value) {
      this.checkValidAndBoundsForWrite(offsetBytes, lengthBytes);

      while(lengthBytes > 0L) {
         long chunk = Math.min(lengthBytes, 1048576L);
         UnsafeUtil.unsafe.setMemory(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), chunk, value);
         offsetBytes += chunk;
         lengthBytes -= chunk;
      }

   }

   public final void setBits(long offsetBytes, byte bitMask) {
      this.checkValidAndBoundsForWrite(offsetBytes, 1L);
      long myOffset = this.getCumulativeOffset(offsetBytes);
      byte value = UnsafeUtil.unsafe.getByte(this.getUnsafeObject(), myOffset);
      UnsafeUtil.unsafe.putByte(this.getUnsafeObject(), myOffset, (byte)(value | bitMask));
   }

   private void writeByteArrayTo(byte[] unsafeObj, long offsetBytes, long lengthBytes, WritableByteChannel out) throws IOException {
      int off = Ints.checkedCast(this.getCumulativeOffset(offsetBytes) - UnsafeUtil.ARRAY_BYTE_BASE_OFFSET);
      int len = Ints.checkedCast(lengthBytes);
      ByteBuffer bufToWrite = ByteBuffer.wrap(unsafeObj, off, len);
      writeFully(bufToWrite, out);
   }

   private void writeDirectMemoryTo(long offsetBytes, long lengthBytes, WritableByteChannel out) throws IOException {
      int chunk;
      for(long addr = this.getCumulativeOffset(offsetBytes); lengthBytes > 0L; lengthBytes -= (long)chunk) {
         chunk = (int)Math.min(1048576L, lengthBytes);
         ByteBuffer bufToWrite = AccessByteBuffer.getDummyReadOnlyDirectByteBuffer(addr, chunk);
         writeFully(bufToWrite, out);
         addr += (long)chunk;
      }

   }

   private void writeToWithExtraCopy(long offsetBytes, long lengthBytes, WritableByteChannel out) throws IOException {
      int bufLen = Ints.checkedCast(Math.max(8L, Math.min(this.getCapacity() / 1024L & -8L, 4096L)));
      byte[] buf = new byte[bufLen];

      int chunk;
      for(ByteBuffer bufToWrite = ByteBuffer.wrap(buf); lengthBytes > 0L; lengthBytes -= (long)chunk) {
         chunk = (int)Math.min((long)buf.length, lengthBytes);
         this.getByteArray(offsetBytes, buf, 0, chunk);
         bufToWrite.clear().limit(chunk);
         writeFully(bufToWrite, out);
         offsetBytes += (long)chunk;
      }

   }

   private static void writeFully(ByteBuffer bufToWrite, WritableByteChannel out) throws IOException {
      while(bufToWrite.remaining() > 0) {
         out.write(bufToWrite);
      }

   }
}
