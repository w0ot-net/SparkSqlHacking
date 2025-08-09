package org.apache.datasketches.memory.internal;

abstract class NativeWritableBufferImpl extends BaseWritableBufferImpl {
   NativeWritableBufferImpl(long capacityBytes) {
      super(capacityBytes);
   }

   public char getChar() {
      return this.getNativeOrderedChar();
   }

   public char getChar(long offsetBytes) {
      return this.getNativeOrderedChar(offsetBytes);
   }

   public void getCharArray(char[] dstArray, int dstOffsetChars, int lengthChars) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthChars << 1;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetChars, (long)lengthChars, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(pos), dstArray, UnsafeUtil.ARRAY_CHAR_BASE_OFFSET + ((long)dstOffsetChars << 1), copyBytes);
   }

   public double getDouble() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 8L);
      return UnsafeUtil.unsafe.getDouble(this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public double getDouble(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 8L);
      return UnsafeUtil.unsafe.getDouble(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void getDoubleArray(double[] dstArray, int dstOffsetDoubles, int lengthDoubles) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthDoubles << 3;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetDoubles, (long)lengthDoubles, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(pos), dstArray, UnsafeUtil.ARRAY_DOUBLE_BASE_OFFSET + ((long)dstOffsetDoubles << 3), copyBytes);
   }

   public float getFloat() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 4L);
      return UnsafeUtil.unsafe.getFloat(this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public float getFloat(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 4L);
      return UnsafeUtil.unsafe.getFloat(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes));
   }

   public void getFloatArray(float[] dstArray, int dstOffsetFloats, int lengthFloats) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthFloats << 2;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetFloats, (long)lengthFloats, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(pos), dstArray, UnsafeUtil.ARRAY_FLOAT_BASE_OFFSET + ((long)dstOffsetFloats << 2), copyBytes);
   }

   public int getInt() {
      return this.getNativeOrderedInt();
   }

   public int getInt(long offsetBytes) {
      return this.getNativeOrderedInt(offsetBytes);
   }

   public void getIntArray(int[] dstArray, int dstOffsetInts, int lengthInts) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthInts << 2;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetInts, (long)lengthInts, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(pos), dstArray, UnsafeUtil.ARRAY_INT_BASE_OFFSET + ((long)dstOffsetInts << 2), copyBytes);
   }

   public long getLong() {
      return this.getNativeOrderedLong();
   }

   public long getLong(long offsetBytes) {
      return this.getNativeOrderedLong(offsetBytes);
   }

   public void getLongArray(long[] dstArray, int dstOffsetLongs, int lengthLongs) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthLongs << 3;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetLongs, (long)lengthLongs, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(pos), dstArray, UnsafeUtil.ARRAY_LONG_BASE_OFFSET + ((long)dstOffsetLongs << 3), copyBytes);
   }

   public short getShort() {
      return this.getNativeOrderedShort();
   }

   public short getShort(long offsetBytes) {
      return this.getNativeOrderedShort(offsetBytes);
   }

   public void getShortArray(short[] dstArray, int dstOffsetShorts, int lengthShorts) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthShorts << 1;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      ResourceImpl.checkBounds((long)dstOffsetShorts, (long)lengthShorts, (long)dstArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(this.getUnsafeObject(), this.getCumulativeOffset(pos), dstArray, UnsafeUtil.ARRAY_SHORT_BASE_OFFSET + ((long)dstOffsetShorts << 1), copyBytes);
   }

   public void putChar(char value) {
      this.putNativeOrderedChar(value);
   }

   public void putChar(long offsetBytes, char value) {
      this.putNativeOrderedChar(offsetBytes, value);
   }

   public void putCharArray(char[] srcArray, int srcOffsetChars, int lengthChars) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthChars << 1;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetChars, (long)lengthChars, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_CHAR_BASE_OFFSET + ((long)srcOffsetChars << 1), this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes);
   }

   public void putDouble(double value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 8L);
      UnsafeUtil.unsafe.putDouble(this.getUnsafeObject(), this.getCumulativeOffset(pos), value);
   }

   public void putDouble(long offsetBytes, double value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 8L);
      UnsafeUtil.unsafe.putDouble(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public void putDoubleArray(double[] srcArray, int srcOffsetDoubles, int lengthDoubles) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthDoubles << 3;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetDoubles, (long)lengthDoubles, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_DOUBLE_BASE_OFFSET + ((long)srcOffsetDoubles << 3), this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes);
   }

   public void putFloat(float value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 4L);
      UnsafeUtil.unsafe.putFloat(this.getUnsafeObject(), this.getCumulativeOffset(pos), value);
   }

   public void putFloat(long offsetBytes, float value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 4L);
      UnsafeUtil.unsafe.putFloat(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), value);
   }

   public void putFloatArray(float[] srcArray, int srcOffsetFloats, int lengthFloats) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthFloats << 2;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetFloats, (long)lengthFloats, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_FLOAT_BASE_OFFSET + ((long)srcOffsetFloats << 2), this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes);
   }

   public void putInt(int value) {
      this.putNativeOrderedInt(value);
   }

   public void putInt(long offsetBytes, int value) {
      this.putNativeOrderedInt(offsetBytes, value);
   }

   public void putIntArray(int[] srcArray, int srcOffsetInts, int lengthInts) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthInts << 2;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetInts, (long)lengthInts, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_INT_BASE_OFFSET + ((long)srcOffsetInts << 2), this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes);
   }

   public void putLong(long value) {
      this.putNativeOrderedLong(value);
   }

   public void putLong(long offsetBytes, long value) {
      this.putNativeOrderedLong(offsetBytes, value);
   }

   public void putLongArray(long[] srcArray, int srcOffsetLongs, int lengthLongs) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthLongs << 3;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetLongs, (long)lengthLongs, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_LONG_BASE_OFFSET + ((long)srcOffsetLongs << 3), this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes);
   }

   public void putShort(short value) {
      this.putNativeOrderedShort(value);
   }

   public void putShort(long offsetBytes, short value) {
      this.putNativeOrderedShort(offsetBytes, value);
   }

   public void putShortArray(short[] srcArray, int srcOffsetShorts, int lengthShorts) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthShorts << 1;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      ResourceImpl.checkBounds((long)srcOffsetShorts, (long)lengthShorts, (long)srcArray.length);
      CompareAndCopy.copyMemoryCheckingDifferentObject(srcArray, UnsafeUtil.ARRAY_SHORT_BASE_OFFSET + ((long)srcOffsetShorts << 1), this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes);
   }
}
