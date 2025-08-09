package org.apache.datasketches.memory.internal;

abstract class NonNativeWritableMemoryImpl extends BaseWritableMemoryImpl {
   public char getChar(long offsetBytes) {
      return Character.reverseBytes(this.getNativeOrderedChar(offsetBytes));
   }

   public void getCharArray(long offsetBytes, char[] dstArray, int dstOffsetChars, int lengthChars) {
      long copyBytes = (long)lengthChars << 1;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      CompareAndCopy.getNonNativeChars(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes, dstArray, dstOffsetChars, lengthChars);
   }

   public double getDouble(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 8L);
      return Double.longBitsToDouble(Long.reverseBytes(UnsafeUtil.unsafe.getLong(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes))));
   }

   public void getDoubleArray(long offsetBytes, double[] dstArray, int dstOffsetDoubles, int lengthDoubles) {
      long copyBytes = (long)lengthDoubles << 3;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      CompareAndCopy.getNonNativeDoubles(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes, dstArray, dstOffsetDoubles, lengthDoubles);
   }

   public float getFloat(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 4L);
      return Float.intBitsToFloat(Integer.reverseBytes(UnsafeUtil.unsafe.getInt(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes))));
   }

   public void getFloatArray(long offsetBytes, float[] dstArray, int dstOffsetFloats, int lengthFloats) {
      long copyBytes = (long)lengthFloats << 2;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      CompareAndCopy.getNonNativeFloats(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes, dstArray, dstOffsetFloats, lengthFloats);
   }

   public int getInt(long offsetBytes) {
      return Integer.reverseBytes(this.getNativeOrderedInt(offsetBytes));
   }

   public void getIntArray(long offsetBytes, int[] dstArray, int dstOffsetInts, int lengthInts) {
      long copyBytes = (long)lengthInts << 2;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      CompareAndCopy.getNonNativeInts(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes, dstArray, dstOffsetInts, lengthInts);
   }

   public long getLong(long offsetBytes) {
      return Long.reverseBytes(this.getNativeOrderedLong(offsetBytes));
   }

   public void getLongArray(long offsetBytes, long[] dstArray, int dstOffsetLongs, int lengthLongs) {
      long copyBytes = (long)lengthLongs << 3;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      CompareAndCopy.getNonNativeLongs(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes, dstArray, dstOffsetLongs, lengthLongs);
   }

   public short getShort(long offsetBytes) {
      return Short.reverseBytes(this.getNativeOrderedShort(offsetBytes));
   }

   public void getShortArray(long offsetBytes, short[] dstArray, int dstOffsetShorts, int lengthShorts) {
      long copyBytes = (long)lengthShorts << 1;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      CompareAndCopy.getNonNativeShorts(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes, dstArray, dstOffsetShorts, lengthShorts);
   }

   public void putChar(long offsetBytes, char value) {
      this.putNativeOrderedChar(offsetBytes, Character.reverseBytes(value));
   }

   public void putCharArray(long offsetBytes, char[] srcArray, int srcOffsetChars, int lengthChars) {
      long copyBytes = (long)lengthChars << 1;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      CompareAndCopy.putNonNativeChars(srcArray, srcOffsetChars, lengthChars, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void putDouble(long offsetBytes, double value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 8L);
      UnsafeUtil.unsafe.putLong(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), Long.reverseBytes(Double.doubleToRawLongBits(value)));
   }

   public void putDoubleArray(long offsetBytes, double[] srcArray, int srcOffsetDoubles, int lengthDoubles) {
      long copyBytes = (long)lengthDoubles << 3;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      CompareAndCopy.putNonNativeDoubles(srcArray, srcOffsetDoubles, lengthDoubles, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void putFloat(long offsetBytes, float value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 4L);
      UnsafeUtil.unsafe.putInt(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), Integer.reverseBytes(Float.floatToRawIntBits(value)));
   }

   public void putFloatArray(long offsetBytes, float[] srcArray, int srcOffsetFloats, int lengthFloats) {
      long copyBytes = (long)lengthFloats << 2;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      CompareAndCopy.putNonNativeFloats(srcArray, srcOffsetFloats, lengthFloats, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void putInt(long offsetBytes, int value) {
      this.putNativeOrderedInt(offsetBytes, Integer.reverseBytes(value));
   }

   public void putIntArray(long offsetBytes, int[] srcArray, int srcOffsetInts, int lengthInts) {
      long copyBytes = (long)lengthInts << 2;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      CompareAndCopy.putNonNativeInts(srcArray, srcOffsetInts, lengthInts, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void putLong(long offsetBytes, long value) {
      this.putNativeOrderedLong(offsetBytes, Long.reverseBytes(value));
   }

   public void putLongArray(long offsetBytes, long[] srcArray, int srcOffsetLongs, int lengthLongs) {
      long copyBytes = (long)lengthLongs << 3;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      CompareAndCopy.putNonNativeLongs(srcArray, srcOffsetLongs, lengthLongs, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void putShort(long offsetBytes, short value) {
      this.putNativeOrderedShort(offsetBytes, Short.reverseBytes(value));
   }

   public void putShortArray(long offsetBytes, short[] srcArray, int srcOffsetShorts, int lengthShorts) {
      long copyBytes = (long)lengthShorts << 1;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      CompareAndCopy.putNonNativeShorts(srcArray, srcOffsetShorts, lengthShorts, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }
}
