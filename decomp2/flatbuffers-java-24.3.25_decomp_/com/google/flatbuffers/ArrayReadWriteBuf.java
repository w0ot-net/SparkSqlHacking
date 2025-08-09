package com.google.flatbuffers;

import java.util.Arrays;

public class ArrayReadWriteBuf implements ReadWriteBuf {
   private byte[] buffer;
   private int writePos;

   public ArrayReadWriteBuf() {
      this(10);
   }

   public ArrayReadWriteBuf(int initialCapacity) {
      this(new byte[initialCapacity]);
   }

   public ArrayReadWriteBuf(byte[] buffer) {
      this.buffer = buffer;
      this.writePos = 0;
   }

   public ArrayReadWriteBuf(byte[] buffer, int startPos) {
      this.buffer = buffer;
      this.writePos = startPos;
   }

   public void clear() {
      this.writePos = 0;
   }

   public boolean getBoolean(int index) {
      return this.buffer[index] != 0;
   }

   public byte get(int index) {
      return this.buffer[index];
   }

   public short getShort(int index) {
      return (short)(this.buffer[index + 1] << 8 | this.buffer[index] & 255);
   }

   public int getInt(int index) {
      return this.buffer[index + 3] << 24 | (this.buffer[index + 2] & 255) << 16 | (this.buffer[index + 1] & 255) << 8 | this.buffer[index] & 255;
   }

   public long getLong(int index) {
      return (long)this.buffer[index++] & 255L | ((long)this.buffer[index++] & 255L) << 8 | ((long)this.buffer[index++] & 255L) << 16 | ((long)this.buffer[index++] & 255L) << 24 | ((long)this.buffer[index++] & 255L) << 32 | ((long)this.buffer[index++] & 255L) << 40 | ((long)this.buffer[index++] & 255L) << 48 | (long)this.buffer[index] << 56;
   }

   public float getFloat(int index) {
      return Float.intBitsToFloat(this.getInt(index));
   }

   public double getDouble(int index) {
      return Double.longBitsToDouble(this.getLong(index));
   }

   public String getString(int start, int size) {
      return Utf8Safe.decodeUtf8Array(this.buffer, start, size);
   }

   public byte[] data() {
      return this.buffer;
   }

   public void putBoolean(boolean value) {
      this.setBoolean(this.writePos, value);
      ++this.writePos;
   }

   public void put(byte[] value, int start, int length) {
      this.set(this.writePos, value, start, length);
      this.writePos += length;
   }

   public void put(byte value) {
      this.set(this.writePos, value);
      ++this.writePos;
   }

   public void putShort(short value) {
      this.setShort(this.writePos, value);
      this.writePos += 2;
   }

   public void putInt(int value) {
      this.setInt(this.writePos, value);
      this.writePos += 4;
   }

   public void putLong(long value) {
      this.setLong(this.writePos, value);
      this.writePos += 8;
   }

   public void putFloat(float value) {
      this.setFloat(this.writePos, value);
      this.writePos += 4;
   }

   public void putDouble(double value) {
      this.setDouble(this.writePos, value);
      this.writePos += 8;
   }

   public void setBoolean(int index, boolean value) {
      this.set(index, (byte)(value ? 1 : 0));
   }

   public void set(int index, byte value) {
      this.requestCapacity(index + 1);
      this.buffer[index] = value;
   }

   public void set(int index, byte[] toCopy, int start, int length) {
      this.requestCapacity(index + (length - start));
      System.arraycopy(toCopy, start, this.buffer, index, length);
   }

   public void setShort(int index, short value) {
      this.requestCapacity(index + 2);
      this.buffer[index++] = (byte)(value & 255);
      this.buffer[index] = (byte)(value >> 8 & 255);
   }

   public void setInt(int index, int value) {
      this.requestCapacity(index + 4);
      this.buffer[index++] = (byte)(value & 255);
      this.buffer[index++] = (byte)(value >> 8 & 255);
      this.buffer[index++] = (byte)(value >> 16 & 255);
      this.buffer[index] = (byte)(value >> 24 & 255);
   }

   public void setLong(int index, long value) {
      this.requestCapacity(index + 8);
      int i = (int)value;
      this.buffer[index++] = (byte)(i & 255);
      this.buffer[index++] = (byte)(i >> 8 & 255);
      this.buffer[index++] = (byte)(i >> 16 & 255);
      this.buffer[index++] = (byte)(i >> 24 & 255);
      i = (int)(value >> 32);
      this.buffer[index++] = (byte)(i & 255);
      this.buffer[index++] = (byte)(i >> 8 & 255);
      this.buffer[index++] = (byte)(i >> 16 & 255);
      this.buffer[index] = (byte)(i >> 24 & 255);
   }

   public void setFloat(int index, float value) {
      this.requestCapacity(index + 4);
      int iValue = Float.floatToRawIntBits(value);
      this.buffer[index++] = (byte)(iValue & 255);
      this.buffer[index++] = (byte)(iValue >> 8 & 255);
      this.buffer[index++] = (byte)(iValue >> 16 & 255);
      this.buffer[index] = (byte)(iValue >> 24 & 255);
   }

   public void setDouble(int index, double value) {
      this.requestCapacity(index + 8);
      long lValue = Double.doubleToRawLongBits(value);
      int i = (int)lValue;
      this.buffer[index++] = (byte)(i & 255);
      this.buffer[index++] = (byte)(i >> 8 & 255);
      this.buffer[index++] = (byte)(i >> 16 & 255);
      this.buffer[index++] = (byte)(i >> 24 & 255);
      i = (int)(lValue >> 32);
      this.buffer[index++] = (byte)(i & 255);
      this.buffer[index++] = (byte)(i >> 8 & 255);
      this.buffer[index++] = (byte)(i >> 16 & 255);
      this.buffer[index] = (byte)(i >> 24 & 255);
   }

   public int limit() {
      return this.writePos;
   }

   public int writePosition() {
      return this.writePos;
   }

   public boolean requestCapacity(int capacity) {
      if (capacity < 0) {
         throw new IllegalArgumentException("Capacity may not be negative (likely a previous int overflow)");
      } else if (this.buffer.length >= capacity) {
         return true;
      } else {
         int oldCapacity = this.buffer.length;
         int newCapacity = oldCapacity + (oldCapacity >> 1);
         if (newCapacity < capacity) {
            newCapacity = capacity;
         }

         this.buffer = Arrays.copyOf(this.buffer, newCapacity);
         return true;
      }
   }
}
