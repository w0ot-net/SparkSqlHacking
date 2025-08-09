package org.apache.datasketches.memory.internal;

abstract class NonNativeWritableBufferImpl extends BaseWritableBufferImpl {
   NonNativeWritableBufferImpl(long capacityBytes) {
      super(capacityBytes);
   }

   public char getChar() {
      return Character.reverseBytes(this.getNativeOrderedChar());
   }

   public char getChar(long offsetBytes) {
      return Character.reverseBytes(this.getNativeOrderedChar(offsetBytes));
   }

   public void getCharArray(char[] dstArray, int dstOffsetChars, int lengthChars) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthChars << 1;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      CompareAndCopy.getNonNativeChars(this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes, dstArray, dstOffsetChars, lengthChars);
   }

   public double getDouble() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 8L);
      return Double.longBitsToDouble(Long.reverseBytes(UnsafeUtil.unsafe.getLong(this.getUnsafeObject(), this.getCumulativeOffset(pos))));
   }

   public double getDouble(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 8L);
      return Double.longBitsToDouble(Long.reverseBytes(UnsafeUtil.unsafe.getLong(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes))));
   }

   public void getDoubleArray(double[] dstArray, int dstOffsetDoubles, int lengthDoubles) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthDoubles << 3;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      CompareAndCopy.getNonNativeDoubles(this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes, dstArray, dstOffsetDoubles, lengthDoubles);
   }

   public float getFloat() {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForRead(pos, 4L);
      return Float.intBitsToFloat(Integer.reverseBytes(UnsafeUtil.unsafe.getInt(this.getUnsafeObject(), this.getCumulativeOffset(pos))));
   }

   public float getFloat(long offsetBytes) {
      this.checkValidAndBounds(offsetBytes, 4L);
      return Float.intBitsToFloat(Integer.reverseBytes(UnsafeUtil.unsafe.getInt(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes))));
   }

   public void getFloatArray(float[] dstArray, int dstOffsetFloats, int lengthFloats) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthFloats << 2;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      CompareAndCopy.getNonNativeFloats(this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes, dstArray, dstOffsetFloats, lengthFloats);
   }

   public int getInt() {
      return Integer.reverseBytes(this.getNativeOrderedInt());
   }

   public int getInt(long offsetBytes) {
      return Integer.reverseBytes(this.getNativeOrderedInt(offsetBytes));
   }

   public void getIntArray(int[] dstArray, int dstOffsetInts, int lengthInts) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthInts << 2;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      CompareAndCopy.getNonNativeInts(this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes, dstArray, dstOffsetInts, lengthInts);
   }

   public long getLong() {
      return Long.reverseBytes(this.getNativeOrderedLong());
   }

   public long getLong(long offsetBytes) {
      return Long.reverseBytes(this.getNativeOrderedLong(offsetBytes));
   }

   public void getLongArray(long[] dstArray, int dstOffsetLongs, int lengthLongs) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthLongs << 3;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      CompareAndCopy.getNonNativeLongs(this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes, dstArray, dstOffsetLongs, lengthLongs);
   }

   public short getShort() {
      return Short.reverseBytes(this.getNativeOrderedShort());
   }

   public short getShort(long offsetBytes) {
      return Short.reverseBytes(this.getNativeOrderedShort(offsetBytes));
   }

   public void getShortArray(short[] dstArray, int dstOffsetShorts, int lengthShorts) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthShorts << 1;
      this.incrementAndCheckPositionForRead(pos, copyBytes);
      CompareAndCopy.getNonNativeShorts(this.getUnsafeObject(), this.getCumulativeOffset(pos), copyBytes, dstArray, dstOffsetShorts, lengthShorts);
   }

   public void putChar(char value) {
      this.putNativeOrderedChar(Character.reverseBytes(value));
   }

   public void putChar(long offsetBytes, char value) {
      this.putNativeOrderedChar(offsetBytes, Character.reverseBytes(value));
   }

   public void putCharArray(char[] srcArray, int srcOffsetChars, int lengthChars) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthChars << 1;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      CompareAndCopy.putNonNativeChars(srcArray, srcOffsetChars, lengthChars, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public void putDouble(double value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 8L);
      UnsafeUtil.unsafe.putLong(this.getUnsafeObject(), this.getCumulativeOffset(pos), Long.reverseBytes(Double.doubleToRawLongBits(value)));
   }

   public void putDouble(long offsetBytes, double value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 8L);
      UnsafeUtil.unsafe.putLong(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), Long.reverseBytes(Double.doubleToRawLongBits(value)));
   }

   public void putDoubleArray(double[] srcArray, int srcOffsetDoubles, int lengthDoubles) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthDoubles << 3;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      CompareAndCopy.putNonNativeDoubles(srcArray, srcOffsetDoubles, lengthDoubles, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public void putFloat(float value) {
      long pos = this.getPosition();
      this.incrementAndCheckPositionForWrite(pos, 4L);
      UnsafeUtil.unsafe.putInt(this.getUnsafeObject(), this.getCumulativeOffset(pos), Integer.reverseBytes(Float.floatToRawIntBits(value)));
   }

   public void putFloat(long offsetBytes, float value) {
      this.checkValidAndBoundsForWrite(offsetBytes, 4L);
      UnsafeUtil.unsafe.putInt(this.getUnsafeObject(), this.getCumulativeOffset(offsetBytes), Integer.reverseBytes(Float.floatToRawIntBits(value)));
   }

   public void putFloatArray(float[] srcArray, int srcOffsetFloats, int lengthFloats) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthFloats << 2;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      CompareAndCopy.putNonNativeFloats(srcArray, srcOffsetFloats, lengthFloats, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public void putInt(int value) {
      this.putNativeOrderedInt(Integer.reverseBytes(value));
   }

   public void putInt(long offsetBytes, int value) {
      this.putNativeOrderedInt(offsetBytes, Integer.reverseBytes(value));
   }

   public void putIntArray(int[] srcArray, int srcOffsetInts, int lengthInts) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthInts << 2;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      CompareAndCopy.putNonNativeInts(srcArray, srcOffsetInts, lengthInts, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public void putLong(long value) {
      this.putNativeOrderedLong(Long.reverseBytes(value));
   }

   public void putLong(long offsetBytes, long value) {
      this.putNativeOrderedLong(offsetBytes, Long.reverseBytes(value));
   }

   public void putLongArray(long[] srcArray, int srcOffsetLongs, int lengthLongs) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthLongs << 3;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      CompareAndCopy.putNonNativeLongs(srcArray, srcOffsetLongs, lengthLongs, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }

   public void putShort(short value) {
      this.putNativeOrderedShort(Short.reverseBytes(value));
   }

   public void putShort(long offsetBytes, short value) {
      this.putNativeOrderedShort(offsetBytes, Short.reverseBytes(value));
   }

   public void putShortArray(short[] srcArray, int srcOffsetShorts, int lengthShorts) {
      long pos = this.getPosition();
      long copyBytes = (long)lengthShorts << 1;
      this.incrementAndCheckPositionForWrite(pos, copyBytes);
      CompareAndCopy.putNonNativeShorts(srcArray, srcOffsetShorts, lengthShorts, copyBytes, this.getUnsafeObject(), this.getCumulativeOffset(pos));
   }
}
