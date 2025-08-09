package org.apache.datasketches.memory.internal;

abstract class NativeWritableMemoryImpl extends BaseWritableMemoryImpl {
   public char getChar(long offsetBytes) {
      return this.getNativeOrderedChar(offsetBytes);
   }

   public void getCharArray(long offsetBytes, char[] dstArray, int dstOffsetChars, int lengthChars) {
      long copyBytes = (long)lengthChars << 1;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetChars, (long)lengthChars, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), dstArray, UnsafeUtil.ARRAY_CHAR_BASE_OFFSET + ((long)dstOffsetChars << 1), copyBytes);
   }

   public double getDouble(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 8L);
      return UnsafeUtil.unsafe.getDouble(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void getDoubleArray(long offsetBytes, double[] dstArray, int dstOffsetDoubles, int lengthDoubles) {
      long copyBytes = (long)lengthDoubles << 3;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetDoubles, (long)lengthDoubles, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), dstArray, UnsafeUtil.ARRAY_DOUBLE_BASE_OFFSET + ((long)dstOffsetDoubles << 3), copyBytes);
   }

   public float getFloat(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 4L);
      return UnsafeUtil.unsafe.getFloat(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void getFloatArray(long offsetBytes, float[] dstArray, int dstOffsetFloats, int lengthFloats) {
      long copyBytes = (long)lengthFloats << 2;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetFloats, (long)lengthFloats, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), dstArray, UnsafeUtil.ARRAY_FLOAT_BASE_OFFSET + ((long)dstOffsetFloats << 2), copyBytes);
   }

   public int getInt(long offsetBytes) {
      return this.getNativeOrderedInt(offsetBytes);
   }

   public void getIntArray(long offsetBytes, int[] dstArray, int dstOffsetInts, int lengthInts) {
      long copyBytes = (long)lengthInts << 2;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetInts, (long)lengthInts, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), dstArray, UnsafeUtil.ARRAY_INT_BASE_OFFSET + ((long)dstOffsetInts << 2), copyBytes);
   }

   public long getLong(long offsetBytes) {
      return this.getNativeOrderedLong(offsetBytes);
   }

   public void getLongArray(long offsetBytes, long[] dstArray, int dstOffsetLongs, int lengthLongs) {
      long copyBytes = (long)lengthLongs << 3;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetLongs, (long)lengthLongs, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), dstArray, UnsafeUtil.ARRAY_LONG_BASE_OFFSET + ((long)dstOffsetLongs << 3), copyBytes);
   }

   public short getShort(long offsetBytes) {
      return this.getNativeOrderedShort(offsetBytes);
   }

   public void getShortArray(long offsetBytes, short[] dstArray, int dstOffsetShorts, int lengthShorts) {
      long copyBytes = (long)lengthShorts << 1;
      this.checkValidAndBounds(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetShorts, (long)lengthShorts, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), dstArray, UnsafeUtil.ARRAY_SHORT_BASE_OFFSET + ((long)dstOffsetShorts << 1), copyBytes);
   }

   public void putChar(long offsetBytes, char value) {
      this.putNativeOrderedChar(offsetBytes, value);
   }

   public void putCharArray(long offsetBytes, char[] srcArray, int srcOffsetChars, int lengthChars) {
      long copyBytes = (long)lengthChars << 1;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetChars, (long)lengthChars, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_CHAR_BASE_OFFSET + ((long)srcOffsetChars << 1), this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes);
   }

   public void putDouble(long offsetBytes, double value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 8L);
      UnsafeUtil.unsafe.putDouble(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public void putDoubleArray(long offsetBytes, double[] srcArray, int srcOffsetDoubles, int lengthDoubles) {
      long copyBytes = (long)lengthDoubles << 3;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetDoubles, (long)lengthDoubles, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_DOUBLE_BASE_OFFSET + ((long)srcOffsetDoubles << 3), this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes);
   }

   public void putFloat(long offsetBytes, float value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 4L);
      UnsafeUtil.unsafe.putFloat(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public void putFloatArray(long offsetBytes, float[] srcArray, int srcOffsetFloats, int lengthFloats) {
      long copyBytes = (long)lengthFloats << 2;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetFloats, (long)lengthFloats, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_FLOAT_BASE_OFFSET + ((long)srcOffsetFloats << 2), this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes);
   }

   public void putInt(long offsetBytes, int value) {
      this.putNativeOrderedInt(offsetBytes, value);
   }

   public void putIntArray(long offsetBytes, int[] srcArray, int srcOffsetInts, int lengthInts) {
      long copyBytes = (long)lengthInts << 2;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetInts, (long)lengthInts, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_INT_BASE_OFFSET + ((long)srcOffsetInts << 2), this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes);
   }

   public void putLong(long offsetBytes, long value) {
      this.putNativeOrderedLong(offsetBytes, value);
   }

   public void putLongArray(long offsetBytes, long[] srcArray, int srcOffsetLongs, int lengthLongs) {
      long copyBytes = (long)lengthLongs << 3;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetLongs, (long)lengthLongs, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_LONG_BASE_OFFSET + ((long)srcOffsetLongs << 3), this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes);
   }

   public void putShort(long offsetBytes, short value) {
      this.putNativeOrderedShort(offsetBytes, value);
   }

   public void putShortArray(long offsetBytes, short[] srcArray, int srcOffsetShorts, int lengthShorts) {
      long copyBytes = (long)lengthShorts << 1;
      this.checkValidAndBoundsForWrite(offsetBytes, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetShorts, (long)lengthShorts, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_SHORT_BASE_OFFSET + ((long)srcOffsetShorts << 1), this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), copyBytes);
   }
}
