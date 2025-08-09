package org.apache.datasketches.memory;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;
import org.apache.datasketches.memory.internal.BaseWritableMemoryImpl;
import org.apache.datasketches.memory.internal.Prim;
import org.apache.datasketches.memory.internal.Util;

public interface WritableMemory extends Memory {
   static WritableMemory writableWrap(ByteBuffer byteBuffer) {
      return writableWrap(byteBuffer, byteBuffer.order(), (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(ByteBuffer byteBuffer, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      if (byteBuffer.isReadOnly()) {
         throw new ReadOnlyException("byteBuffer must be writable.");
      } else {
         return BaseWritableMemoryImpl.wrapByteBuffer(byteBuffer, false, byteOrder, memReqSvr);
      }
   }

   static WritableMemory writableMap(File file) throws IOException {
      return writableMap(file, 0L, file.length(), ByteOrder.nativeOrder());
   }

   static WritableMemory writableMap(File file, long fileOffsetBytes, long capacityBytes, ByteOrder byteOrder) throws IOException {
      if (!file.canWrite()) {
         throw new ReadOnlyException("file must be writable.");
      } else {
         return BaseWritableMemoryImpl.wrapMap(file, fileOffsetBytes, capacityBytes, false, byteOrder);
      }
   }

   static WritableMemory allocateDirect(long capacityBytes) {
      return allocateDirect(capacityBytes, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static WritableMemory allocateDirect(long capacityBytes, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      return BaseWritableMemoryImpl.wrapDirect(capacityBytes, byteOrder, memReqSvr);
   }

   default WritableMemory writableRegion(long offsetBytes, long capacityBytes) {
      return this.writableRegion(offsetBytes, capacityBytes, this.getTypeByteOrder());
   }

   WritableMemory writableRegion(long var1, long var3, ByteOrder var5);

   default WritableBuffer asWritableBuffer() {
      return this.asWritableBuffer(this.getTypeByteOrder());
   }

   WritableBuffer asWritableBuffer(ByteOrder var1);

   static WritableMemory allocate(int capacityBytes) {
      return allocate(capacityBytes, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static WritableMemory allocate(int capacityBytes, ByteOrder byteOrder) {
      return allocate(capacityBytes, byteOrder, (MemoryRequestServer)null);
   }

   static WritableMemory allocate(int capacityBytes, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      byte[] arr = new byte[capacityBytes];
      Util.negativeCheck((long)capacityBytes, "capacityBytes");
      return writableWrap(arr, 0, capacityBytes, byteOrder, memReqSvr);
   }

   static WritableMemory writableWrap(byte[] array) {
      return writableWrap(array, 0, array.length, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(byte[] array, ByteOrder byteOrder) {
      return writableWrap(array, 0, array.length, byteOrder, (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(byte[] array, int offsetBytes, int lengthBytes, ByteOrder byteOrder) {
      return writableWrap(array, offsetBytes, lengthBytes, byteOrder, (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(byte[] array, int offsetBytes, int lengthBytes, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      return BaseWritableMemoryImpl.wrapHeapArray(array, (long)offsetBytes, (long)lengthBytes, false, byteOrder, memReqSvr);
   }

   static WritableMemory writableWrap(char[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.CHAR.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, false, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(short[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.SHORT.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, false, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(int[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.INT.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, false, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(long[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.LONG.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, false, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(float[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.FLOAT.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, false, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static WritableMemory writableWrap(double[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.DOUBLE.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, false, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   void putBoolean(long var1, boolean var3);

   void putByte(long var1, byte var3);

   void putByteArray(long var1, byte[] var3, int var4, int var5);

   void putChar(long var1, char var3);

   void putCharArray(long var1, char[] var3, int var4, int var5);

   void putDouble(long var1, double var3);

   void putDoubleArray(long var1, double[] var3, int var4, int var5);

   void putFloat(long var1, float var3);

   void putFloatArray(long var1, float[] var3, int var4, int var5);

   void putInt(long var1, int var3);

   void putIntArray(long var1, int[] var3, int var4, int var5);

   void putLong(long var1, long var3);

   void putLongArray(long var1, long[] var3, int var4, int var5);

   void putShort(long var1, short var3);

   void putShortArray(long var1, short[] var3, int var4, int var5);

   Object getArray();

   void clear();

   void clear(long var1, long var3);

   void clearBits(long var1, byte var3);

   void fill(byte var1);

   void fill(long var1, long var3, byte var5);

   void setBits(long var1, byte var3);
}
