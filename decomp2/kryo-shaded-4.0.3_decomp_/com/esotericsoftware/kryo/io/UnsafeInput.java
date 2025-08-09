package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.InputStream;

public final class UnsafeInput extends Input {
   private boolean varIntsEnabled = false;

   public UnsafeInput() {
   }

   public UnsafeInput(int bufferSize) {
      super(bufferSize);
   }

   public UnsafeInput(byte[] buffer) {
      super(buffer);
   }

   public UnsafeInput(byte[] buffer, int offset, int count) {
      super(buffer, offset, count);
   }

   public UnsafeInput(InputStream inputStream) {
      super(inputStream);
   }

   public UnsafeInput(InputStream inputStream, int bufferSize) {
      super(inputStream, bufferSize);
   }

   public int readInt() throws KryoException {
      this.require(4);
      int result = UnsafeUtil.unsafe().getInt(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position);
      this.position += 4;
      return result;
   }

   public float readFloat() throws KryoException {
      this.require(4);
      float result = UnsafeUtil.unsafe().getFloat(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position);
      this.position += 4;
      return result;
   }

   public short readShort() throws KryoException {
      this.require(2);
      short result = UnsafeUtil.unsafe().getShort(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position);
      this.position += 2;
      return result;
   }

   public long readLong() throws KryoException {
      this.require(8);
      long result = UnsafeUtil.unsafe().getLong(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position);
      this.position += 8;
      return result;
   }

   public double readDouble() throws KryoException {
      this.require(8);
      double result = UnsafeUtil.unsafe().getDouble(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position);
      this.position += 8;
      return result;
   }

   public char readChar() throws KryoException {
      this.require(2);
      char result = UnsafeUtil.unsafe().getChar(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position);
      this.position += 2;
      return result;
   }

   public int readInt(boolean optimizePositive) throws KryoException {
      return !this.varIntsEnabled ? this.readInt() : super.readInt(optimizePositive);
   }

   public long readLong(boolean optimizePositive) throws KryoException {
      return !this.varIntsEnabled ? this.readLong() : super.readLong(optimizePositive);
   }

   public final int[] readInts(int length, boolean optimizePositive) throws KryoException {
      if (!this.varIntsEnabled) {
         int bytesToCopy = length << 2;
         int[] array = new int[length];
         this.readBytes(array, UnsafeUtil.intArrayBaseOffset, 0L, bytesToCopy);
         return array;
      } else {
         return super.readInts(length, optimizePositive);
      }
   }

   public final long[] readLongs(int length, boolean optimizePositive) throws KryoException {
      if (!this.varIntsEnabled) {
         int bytesToCopy = length << 3;
         long[] array = new long[length];
         this.readBytes(array, UnsafeUtil.longArrayBaseOffset, 0L, bytesToCopy);
         return array;
      } else {
         return super.readLongs(length, optimizePositive);
      }
   }

   public final int[] readInts(int length) throws KryoException {
      int bytesToCopy = length << 2;
      int[] array = new int[length];
      this.readBytes(array, UnsafeUtil.intArrayBaseOffset, 0L, bytesToCopy);
      return array;
   }

   public final long[] readLongs(int length) throws KryoException {
      int bytesToCopy = length << 3;
      long[] array = new long[length];
      this.readBytes(array, UnsafeUtil.longArrayBaseOffset, 0L, bytesToCopy);
      return array;
   }

   public final float[] readFloats(int length) throws KryoException {
      int bytesToCopy = length << 2;
      float[] array = new float[length];
      this.readBytes(array, UnsafeUtil.floatArrayBaseOffset, 0L, bytesToCopy);
      return array;
   }

   public final short[] readShorts(int length) throws KryoException {
      int bytesToCopy = length << 1;
      short[] array = new short[length];
      this.readBytes(array, UnsafeUtil.shortArrayBaseOffset, 0L, bytesToCopy);
      return array;
   }

   public final char[] readChars(int length) throws KryoException {
      int bytesToCopy = length << 1;
      char[] array = new char[length];
      this.readBytes(array, UnsafeUtil.charArrayBaseOffset, 0L, bytesToCopy);
      return array;
   }

   public final double[] readDoubles(int length) throws KryoException {
      int bytesToCopy = length << 3;
      double[] array = new double[length];
      this.readBytes(array, UnsafeUtil.doubleArrayBaseOffset, 0L, bytesToCopy);
      return array;
   }

   public final void readBytes(Object dstObj, long offset, long count) throws KryoException {
      if (dstObj.getClass().isArray()) {
         this.readBytes(dstObj, 0L, offset, (int)count);
      } else {
         throw new KryoException("Only bulk reads of arrays is supported");
      }
   }

   private final void readBytes(Object dstArray, long dstArrayTypeOffset, long offset, int count) throws KryoException {
      int copyCount = Math.min(this.limit - this.position, count);

      while(true) {
         UnsafeUtil.unsafe().copyMemory(this.buffer, UnsafeUtil.byteArrayBaseOffset + (long)this.position, dstArray, dstArrayTypeOffset + offset, (long)copyCount);
         this.position += copyCount;
         count -= copyCount;
         if (count == 0) {
            return;
         }

         offset += (long)copyCount;
         copyCount = Math.min(count, this.capacity);
         this.require(copyCount);
      }
   }

   public boolean getVarIntsEnabled() {
      return this.varIntsEnabled;
   }

   public void setVarIntsEnabled(boolean varIntsEnabled) {
      this.varIntsEnabled = varIntsEnabled;
   }
}
