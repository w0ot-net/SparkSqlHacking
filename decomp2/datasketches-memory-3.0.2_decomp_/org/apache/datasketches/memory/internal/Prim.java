package org.apache.datasketches.memory.internal;

public enum Prim {
   BOOLEAN(UnsafeUtil.ARRAY_BOOLEAN_BASE_OFFSET, 1L, 0L),
   BYTE(UnsafeUtil.ARRAY_BYTE_BASE_OFFSET, 1L, 0L),
   CHAR(UnsafeUtil.ARRAY_CHAR_BASE_OFFSET, 2L, 1L),
   SHORT(UnsafeUtil.ARRAY_SHORT_BASE_OFFSET, 2L, 1L),
   INT(UnsafeUtil.ARRAY_INT_BASE_OFFSET, 4L, 2L),
   LONG(UnsafeUtil.ARRAY_LONG_BASE_OFFSET, 8L, 3L),
   FLOAT(UnsafeUtil.ARRAY_FLOAT_BASE_OFFSET, 4L, 2L),
   DOUBLE(UnsafeUtil.ARRAY_DOUBLE_BASE_OFFSET, 8L, 3L),
   OBJECT(UnsafeUtil.ARRAY_OBJECT_BASE_OFFSET, UnsafeUtil.ARRAY_OBJECT_INDEX_SCALE, UnsafeUtil.OBJECT_SHIFT);

   private final long arrBaseOff_;
   private final long arrIdxScale_;
   private final long sizeShift_;

   private Prim(long arrBaseOff, long arrIdxScale, long sizeShift) {
      this.arrBaseOff_ = arrBaseOff;
      this.arrIdxScale_ = arrIdxScale;
      this.sizeShift_ = sizeShift;
   }

   public long off() {
      return this.arrBaseOff_;
   }

   public long scale() {
      return this.arrIdxScale_;
   }

   public long shift() {
      return this.sizeShift_;
   }
}
