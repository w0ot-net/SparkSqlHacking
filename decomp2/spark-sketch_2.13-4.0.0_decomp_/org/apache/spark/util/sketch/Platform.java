package org.apache.spark.util.sketch;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

final class Platform {
   private static final Unsafe _UNSAFE;
   public static final int BYTE_ARRAY_OFFSET;
   public static final int INT_ARRAY_OFFSET;
   public static final int LONG_ARRAY_OFFSET;
   public static final int DOUBLE_ARRAY_OFFSET;
   private static final long UNSAFE_COPY_THRESHOLD = 1048576L;

   public static int getInt(Object object, long offset) {
      return _UNSAFE.getInt(object, offset);
   }

   public static void putInt(Object object, long offset, int value) {
      _UNSAFE.putInt(object, offset, value);
   }

   public static boolean getBoolean(Object object, long offset) {
      return _UNSAFE.getBoolean(object, offset);
   }

   public static void putBoolean(Object object, long offset, boolean value) {
      _UNSAFE.putBoolean(object, offset, value);
   }

   public static byte getByte(Object object, long offset) {
      return _UNSAFE.getByte(object, offset);
   }

   public static void putByte(Object object, long offset, byte value) {
      _UNSAFE.putByte(object, offset, value);
   }

   public static short getShort(Object object, long offset) {
      return _UNSAFE.getShort(object, offset);
   }

   public static void putShort(Object object, long offset, short value) {
      _UNSAFE.putShort(object, offset, value);
   }

   public static long getLong(Object object, long offset) {
      return _UNSAFE.getLong(object, offset);
   }

   public static void putLong(Object object, long offset, long value) {
      _UNSAFE.putLong(object, offset, value);
   }

   public static float getFloat(Object object, long offset) {
      return _UNSAFE.getFloat(object, offset);
   }

   public static void putFloat(Object object, long offset, float value) {
      _UNSAFE.putFloat(object, offset, value);
   }

   public static double getDouble(Object object, long offset) {
      return _UNSAFE.getDouble(object, offset);
   }

   public static void putDouble(Object object, long offset, double value) {
      _UNSAFE.putDouble(object, offset, value);
   }

   public static Object getObjectVolatile(Object object, long offset) {
      return _UNSAFE.getObjectVolatile(object, offset);
   }

   public static void putObjectVolatile(Object object, long offset, Object value) {
      _UNSAFE.putObjectVolatile(object, offset, value);
   }

   public static long allocateMemory(long size) {
      return _UNSAFE.allocateMemory(size);
   }

   public static void freeMemory(long address) {
      _UNSAFE.freeMemory(address);
   }

   public static void copyMemory(Object src, long srcOffset, Object dst, long dstOffset, long length) {
      if (dstOffset < srcOffset) {
         while(length > 0L) {
            long size = Math.min(length, 1048576L);
            _UNSAFE.copyMemory(src, srcOffset, dst, dstOffset, size);
            length -= size;
            srcOffset += size;
            dstOffset += size;
         }
      } else {
         srcOffset += length;

         long size;
         for(long var11 = dstOffset + length; length > 0L; length -= size) {
            size = Math.min(length, 1048576L);
            srcOffset -= size;
            var11 -= size;
            _UNSAFE.copyMemory(src, srcOffset, dst, var11, size);
         }
      }

   }

   public static void throwException(Throwable t) {
      _UNSAFE.throwException(t);
   }

   static {
      Unsafe unsafe;
      try {
         Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
         unsafeField.setAccessible(true);
         unsafe = (Unsafe)unsafeField.get((Object)null);
      } catch (Throwable var2) {
         unsafe = null;
      }

      _UNSAFE = unsafe;
      if (_UNSAFE != null) {
         BYTE_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(byte[].class);
         INT_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(int[].class);
         LONG_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(long[].class);
         DOUBLE_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(double[].class);
      } else {
         BYTE_ARRAY_OFFSET = 0;
         INT_ARRAY_OFFSET = 0;
         LONG_ARRAY_OFFSET = 0;
         DOUBLE_ARRAY_OFFSET = 0;
      }

   }
}
