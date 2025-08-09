package io.netty.util.internal.shaded.org.jctools.util;

public final class UnsafeRefArrayAccess {
   public static final long REF_ARRAY_BASE;
   public static final int REF_ELEMENT_SHIFT;

   public static void spRefElement(Object[] buffer, long offset, Object e) {
      UnsafeAccess.UNSAFE.putObject(buffer, offset, e);
   }

   public static void soRefElement(Object[] buffer, long offset, Object e) {
      UnsafeAccess.UNSAFE.putOrderedObject(buffer, offset, e);
   }

   public static Object lpRefElement(Object[] buffer, long offset) {
      return UnsafeAccess.UNSAFE.getObject(buffer, offset);
   }

   public static Object lvRefElement(Object[] buffer, long offset) {
      return UnsafeAccess.UNSAFE.getObjectVolatile(buffer, offset);
   }

   public static long calcRefElementOffset(long index) {
      return REF_ARRAY_BASE + (index << REF_ELEMENT_SHIFT);
   }

   public static long calcCircularRefElementOffset(long index, long mask) {
      return REF_ARRAY_BASE + ((index & mask) << REF_ELEMENT_SHIFT);
   }

   public static Object[] allocateRefArray(int capacity) {
      return new Object[capacity];
   }

   static {
      int scale = UnsafeAccess.UNSAFE.arrayIndexScale(Object[].class);
      if (4 == scale) {
         REF_ELEMENT_SHIFT = 2;
      } else {
         if (8 != scale) {
            throw new IllegalStateException("Unknown pointer size: " + scale);
         }

         REF_ELEMENT_SHIFT = 3;
      }

      REF_ARRAY_BASE = (long)UnsafeAccess.UNSAFE.arrayBaseOffset(Object[].class);
   }
}
