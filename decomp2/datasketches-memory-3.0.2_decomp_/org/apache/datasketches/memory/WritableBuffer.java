package org.apache.datasketches.memory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.datasketches.memory.internal.BaseWritableBufferImpl;

public interface WritableBuffer extends Buffer {
   static WritableBuffer writableWrap(ByteBuffer byteBuffer) {
      return writableWrap(byteBuffer, byteBuffer.order(), defaultMemReqSvr);
   }

   static WritableBuffer writableWrap(ByteBuffer byteBuffer, ByteOrder byteOrder, MemoryRequestServer memReqSvr) {
      if (byteBuffer.isReadOnly()) {
         throw new ReadOnlyException("Cannot create a WritableBuffer from a ReadOnly ByteBuffer.");
      } else {
         return BaseWritableBufferImpl.wrapByteBuffer(byteBuffer, false, byteOrder, memReqSvr);
      }
   }

   WritableBuffer writableDuplicate();

   WritableBuffer writableDuplicate(ByteOrder var1);

   WritableBuffer writableRegion();

   WritableBuffer writableRegion(long var1, long var3, ByteOrder var5);

   default WritableMemory asWritableMemory() {
      return this.asWritableMemory(ByteOrder.nativeOrder());
   }

   WritableMemory asWritableMemory(ByteOrder var1);

   void putBoolean(boolean var1);

   void putBoolean(long var1, boolean var3);

   void putByte(byte var1);

   void putByte(long var1, byte var3);

   void putByteArray(byte[] var1, int var2, int var3);

   void putChar(char var1);

   void putChar(long var1, char var3);

   void putCharArray(char[] var1, int var2, int var3);

   void putDouble(double var1);

   void putDouble(long var1, double var3);

   void putDoubleArray(double[] var1, int var2, int var3);

   void putFloat(float var1);

   void putFloat(long var1, float var3);

   void putFloatArray(float[] var1, int var2, int var3);

   void putInt(int var1);

   void putInt(long var1, int var3);

   void putIntArray(int[] var1, int var2, int var3);

   void putLong(long var1);

   void putLong(long var1, long var3);

   void putLongArray(long[] var1, int var2, int var3);

   void putShort(short var1);

   void putShort(long var1, short var3);

   void putShortArray(short[] var1, int var2, int var3);

   Object getArray();

   void clear();

   void fill(byte var1);
}
