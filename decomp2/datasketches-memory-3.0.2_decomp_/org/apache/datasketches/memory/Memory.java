package org.apache.datasketches.memory;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.WritableByteChannel;
import java.util.Objects;
import org.apache.datasketches.memory.internal.BaseWritableMemoryImpl;
import org.apache.datasketches.memory.internal.Prim;

public interface Memory extends Resource {
   static Memory wrap(ByteBuffer byteBuffer) {
      return wrap(byteBuffer, byteBuffer.order());
   }

   static Memory wrap(ByteBuffer byteBuffer, ByteOrder byteOrder) {
      return BaseWritableMemoryImpl.wrapByteBuffer(byteBuffer, true, byteOrder, (MemoryRequestServer)null);
   }

   static Memory map(File file) throws IOException {
      return map(file, 0L, file.length(), ByteOrder.nativeOrder());
   }

   static Memory map(File file, long fileOffsetBytes, long capacityBytes, ByteOrder byteOrder) throws IOException {
      return BaseWritableMemoryImpl.wrapMap(file, fileOffsetBytes, capacityBytes, true, byteOrder);
   }

   default Memory region(long offsetBytes, long capacityBytes) {
      return this.region(offsetBytes, capacityBytes, this.getTypeByteOrder());
   }

   Memory region(long var1, long var3, ByteOrder var5);

   default Buffer asBuffer() {
      return this.asBuffer(this.getTypeByteOrder());
   }

   Buffer asBuffer(ByteOrder var1);

   static Memory wrap(byte[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      return wrap(array, 0, array.length, ByteOrder.nativeOrder());
   }

   static Memory wrap(byte[] array, ByteOrder byteOrder) {
      return wrap(array, 0, array.length, byteOrder);
   }

   static Memory wrap(byte[] array, int offsetBytes, int lengthBytes, ByteOrder byteOrder) {
      return BaseWritableMemoryImpl.wrapHeapArray(array, (long)offsetBytes, (long)lengthBytes, true, byteOrder, (MemoryRequestServer)null);
   }

   static Memory wrap(char[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.CHAR.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, true, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static Memory wrap(short[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.SHORT.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, true, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static Memory wrap(int[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.INT.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, true, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static Memory wrap(long[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.LONG.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, true, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static Memory wrap(float[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.FLOAT.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, true, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   static Memory wrap(double[] array) {
      Objects.requireNonNull(array, "array must be non-null");
      long lengthBytes = (long)(array.length << (int)Prim.DOUBLE.shift());
      return BaseWritableMemoryImpl.wrapHeapArray(array, 0L, lengthBytes, true, ByteOrder.nativeOrder(), (MemoryRequestServer)null);
   }

   boolean getBoolean(long var1);

   byte getByte(long var1);

   void getByteArray(long var1, byte[] var3, int var4, int var5);

   char getChar(long var1);

   void getCharArray(long var1, char[] var3, int var4, int var5);

   double getDouble(long var1);

   void getDoubleArray(long var1, double[] var3, int var4, int var5);

   float getFloat(long var1);

   void getFloatArray(long var1, float[] var3, int var4, int var5);

   int getInt(long var1);

   void getIntArray(long var1, int[] var3, int var4, int var5);

   long getLong(long var1);

   void getLongArray(long var1, long[] var3, int var4, int var5);

   short getShort(long var1);

   void getShortArray(long var1, short[] var3, int var4, int var5);

   int compareTo(long var1, long var3, Memory var5, long var6, long var8);

   void copyTo(long var1, WritableMemory var3, long var4, long var6);

   void writeTo(long var1, long var3, WritableByteChannel var5) throws IOException;
}
