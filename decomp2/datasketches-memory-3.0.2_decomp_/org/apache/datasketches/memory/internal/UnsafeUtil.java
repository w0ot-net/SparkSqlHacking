package org.apache.datasketches.memory.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import sun.misc.Unsafe;

public final class UnsafeUtil {
   public static final Unsafe unsafe;
   public static final int ADDRESS_SIZE;
   public static final long ARRAY_BOOLEAN_BASE_OFFSET;
   public static final long ARRAY_BYTE_BASE_OFFSET;
   public static final long ARRAY_SHORT_BASE_OFFSET;
   public static final long ARRAY_CHAR_BASE_OFFSET;
   public static final long ARRAY_INT_BASE_OFFSET;
   public static final long ARRAY_LONG_BASE_OFFSET;
   public static final long ARRAY_FLOAT_BASE_OFFSET;
   public static final long ARRAY_DOUBLE_BASE_OFFSET;
   public static final long ARRAY_OBJECT_BASE_OFFSET;
   public static final int ARRAY_BOOLEAN_INDEX_SCALE = 1;
   public static final int ARRAY_BYTE_INDEX_SCALE = 1;
   public static final long ARRAY_SHORT_INDEX_SCALE = 2L;
   public static final long ARRAY_CHAR_INDEX_SCALE = 2L;
   public static final long ARRAY_INT_INDEX_SCALE = 4L;
   public static final long ARRAY_LONG_INDEX_SCALE = 8L;
   public static final long ARRAY_FLOAT_INDEX_SCALE = 4L;
   public static final long ARRAY_DOUBLE_INDEX_SCALE = 8L;
   public static final long ARRAY_OBJECT_INDEX_SCALE;
   public static final int BOOLEAN_SHIFT = 0;
   public static final int BYTE_SHIFT = 0;
   public static final long SHORT_SHIFT = 1L;
   public static final long CHAR_SHIFT = 1L;
   public static final long INT_SHIFT = 2L;
   public static final long LONG_SHIFT = 3L;
   public static final long FLOAT_SHIFT = 2L;
   public static final long DOUBLE_SHIFT = 3L;
   public static final long OBJECT_SHIFT;
   public static final String LS = System.getProperty("line.separator");

   private UnsafeUtil() {
   }

   public static long getFieldOffset(Class c, String fieldName) {
      try {
         return unsafe.objectFieldOffset(c.getDeclaredField(fieldName));
      } catch (NoSuchFieldException e) {
         throw new IllegalStateException(e + ": " + fieldName);
      }
   }

   public static long getArrayBaseOffset(Class c) {
      if (c == byte[].class) {
         return ARRAY_BYTE_BASE_OFFSET;
      } else if (c == int[].class) {
         return ARRAY_INT_BASE_OFFSET;
      } else if (c == long[].class) {
         return ARRAY_LONG_BASE_OFFSET;
      } else if (c == float[].class) {
         return ARRAY_FLOAT_BASE_OFFSET;
      } else if (c == double[].class) {
         return ARRAY_DOUBLE_BASE_OFFSET;
      } else if (c == boolean[].class) {
         return ARRAY_BOOLEAN_BASE_OFFSET;
      } else if (c == short[].class) {
         return ARRAY_SHORT_BASE_OFFSET;
      } else if (c == char[].class) {
         return ARRAY_CHAR_BASE_OFFSET;
      } else {
         return c == Object[].class ? ARRAY_OBJECT_BASE_OFFSET : (long)unsafe.arrayBaseOffset(c);
      }
   }

   static {
      try {
         Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
         unsafeConstructor.setAccessible(true);
         unsafe = (Unsafe)unsafeConstructor.newInstance();
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
         ((Exception)e).printStackTrace();
         throw new RuntimeException("Unable to acquire Unsafe. " + e);
      }

      ADDRESS_SIZE = unsafe.addressSize();
      ARRAY_BOOLEAN_BASE_OFFSET = (long)unsafe.arrayBaseOffset(boolean[].class);
      ARRAY_BYTE_BASE_OFFSET = (long)unsafe.arrayBaseOffset(byte[].class);
      ARRAY_SHORT_BASE_OFFSET = (long)unsafe.arrayBaseOffset(short[].class);
      ARRAY_CHAR_BASE_OFFSET = (long)unsafe.arrayBaseOffset(char[].class);
      ARRAY_INT_BASE_OFFSET = (long)unsafe.arrayBaseOffset(int[].class);
      ARRAY_LONG_BASE_OFFSET = (long)unsafe.arrayBaseOffset(long[].class);
      ARRAY_FLOAT_BASE_OFFSET = (long)unsafe.arrayBaseOffset(float[].class);
      ARRAY_DOUBLE_BASE_OFFSET = (long)unsafe.arrayBaseOffset(double[].class);
      ARRAY_OBJECT_BASE_OFFSET = (long)unsafe.arrayBaseOffset(Object[].class);
      ARRAY_OBJECT_INDEX_SCALE = (long)unsafe.arrayIndexScale(Object[].class);
      OBJECT_SHIFT = ARRAY_OBJECT_INDEX_SCALE == 4L ? 2L : 3L;
   }
}
