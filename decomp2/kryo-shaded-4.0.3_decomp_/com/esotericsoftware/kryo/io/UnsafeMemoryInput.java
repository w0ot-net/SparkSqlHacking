package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import java.io.InputStream;
import java.nio.ByteBuffer;
import sun.nio.ch.DirectBuffer;

public final class UnsafeMemoryInput extends ByteBufferInput {
   private long bufaddress;

   public UnsafeMemoryInput() {
      this.varIntsEnabled = false;
   }

   public UnsafeMemoryInput(int bufferSize) {
      super(bufferSize);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public UnsafeMemoryInput(byte[] buffer) {
      super(buffer);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public UnsafeMemoryInput(ByteBuffer buffer) {
      super(buffer);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public UnsafeMemoryInput(long address, int maxBufferSize) {
      super(address, maxBufferSize);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public UnsafeMemoryInput(InputStream inputStream) {
      super(inputStream);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public UnsafeMemoryInput(InputStream inputStream, int bufferSize) {
      super(inputStream, bufferSize);
      this.varIntsEnabled = false;
      this.updateBufferAddress();
   }

   public void setBuffer(ByteBuffer buffer) {
      super.setBuffer(buffer);
      this.updateBufferAddress();
   }

   private void updateBufferAddress() {
      this.bufaddress = ((DirectBuffer)super.niobuffer).address();
   }

   public int readInt() throws KryoException {
      this.require(4);
      int result = UnsafeUtil.unsafe().getInt(this.bufaddress + (long)this.position);
      this.position += 4;
      return result;
   }

   public float readFloat() throws KryoException {
      this.require(4);
      float result = UnsafeUtil.unsafe().getFloat(this.bufaddress + (long)this.position);
      this.position += 4;
      return result;
   }

   public short readShort() throws KryoException {
      this.require(2);
      short result = UnsafeUtil.unsafe().getShort(this.bufaddress + (long)this.position);
      this.position += 2;
      return result;
   }

   public long readLong() throws KryoException {
      this.require(8);
      long result = UnsafeUtil.unsafe().getLong(this.bufaddress + (long)this.position);
      this.position += 8;
      return result;
   }

   public boolean readBoolean() throws KryoException {
      super.niobuffer.position(this.position);
      return super.readBoolean();
   }

   public byte readByte() throws KryoException {
      super.niobuffer.position(this.position);
      return super.readByte();
   }

   public char readChar() throws KryoException {
      this.require(2);
      char result = UnsafeUtil.unsafe().getChar(this.bufaddress + (long)this.position);
      this.position += 2;
      return result;
   }

   public double readDouble() throws KryoException {
      this.require(8);
      double result = UnsafeUtil.unsafe().getDouble(this.bufaddress + (long)this.position);
      this.position += 8;
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

   public byte[] readBytes(int length) throws KryoException {
      byte[] bytes = new byte[length];
      this.readBytes(bytes, 0L, (long)bytes.length);
      return bytes;
   }

   public final void readBytes(Object dstObj, long offset, long count) throws KryoException {
      if (dstObj.getClass().isArray()) {
         this.readBytes(dstObj, UnsafeUtil.byteArrayBaseOffset, offset, (int)count);
      } else {
         throw new KryoException("Only bulk reads of arrays is supported");
      }
   }

   private final void readBytes(Object dstObj, long dstArrayTypeOffset, long offset, int count) throws KryoException {
      int copyCount = Math.min(this.limit - this.position, count);

      while(true) {
         UnsafeUtil.unsafe().copyMemory((Object)null, this.bufaddress + (long)this.position, dstObj, dstArrayTypeOffset + offset, (long)copyCount);
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
}
