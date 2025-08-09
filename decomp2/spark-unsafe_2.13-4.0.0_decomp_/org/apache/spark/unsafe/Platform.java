package org.apache.spark.unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import sun.misc.Unsafe;

public final class Platform {
   private static final Unsafe _UNSAFE;
   public static final int BOOLEAN_ARRAY_OFFSET;
   public static final int BYTE_ARRAY_OFFSET;
   public static final int SHORT_ARRAY_OFFSET;
   public static final int INT_ARRAY_OFFSET;
   public static final int LONG_ARRAY_OFFSET;
   public static final int FLOAT_ARRAY_OFFSET;
   public static final int DOUBLE_ARRAY_OFFSET;
   private static final boolean unaligned;
   private static final int majorVersion = Integer.parseInt(System.getProperty("java.version").split("\\D+")[0]);
   private static final Constructor DBB_CONSTRUCTOR;
   private static final Field DBB_CLEANER_FIELD;
   private static final Method CLEANER_CREATE_METHOD;
   private static final long UNSAFE_COPY_THRESHOLD = 1048576L;

   public static boolean cleanerCreateMethodIsDefined() {
      return CLEANER_CREATE_METHOD != null;
   }

   public static boolean unaligned() {
      return unaligned;
   }

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

   public static long reallocateMemory(long address, long oldSize, long newSize) {
      long newMemory = _UNSAFE.allocateMemory(newSize);
      copyMemory((Object)null, address, (Object)null, newMemory, oldSize);
      freeMemory(address);
      return newMemory;
   }

   public static ByteBuffer allocateDirectBuffer(int size) {
      try {
         if (CLEANER_CREATE_METHOD == null) {
            try {
               return ByteBuffer.allocateDirect(size);
            } catch (OutOfMemoryError oome) {
               throw new OutOfMemoryError("Failed to allocate direct buffer (" + oome.getMessage() + "); try increasing -XX:MaxDirectMemorySize=... to, for example, your heap size");
            }
         } else {
            long memory = allocateMemory((long)size);
            ByteBuffer buffer = (ByteBuffer)DBB_CONSTRUCTOR.newInstance(memory, size);

            try {
               DBB_CLEANER_FIELD.set(buffer, CLEANER_CREATE_METHOD.invoke((Object)null, buffer, (Runnable)() -> freeMemory(memory)));
            } catch (InvocationTargetException | IllegalAccessException e) {
               freeMemory(memory);
               throw new IllegalStateException(e);
            }

            return buffer;
         }
      } catch (Exception e) {
         throwException(e);
         throw new IllegalStateException("unreachable");
      }
   }

   public static void setMemory(Object object, long offset, long size, byte value) {
      _UNSAFE.setMemory(object, offset, size, value);
   }

   public static void setMemory(long address, byte value, long size) {
      _UNSAFE.setMemory(address, size, value);
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
      try {
         Class<?> cls = Class.forName("java.nio.DirectByteBuffer");
         Constructor<?> constructor = majorVersion < 21 ? cls.getDeclaredConstructor(Long.TYPE, Integer.TYPE) : cls.getDeclaredConstructor(Long.TYPE, Long.TYPE);
         Field cleanerField = cls.getDeclaredField("cleaner");
         if (!constructor.trySetAccessible()) {
            constructor = null;
         }

         if (!cleanerField.trySetAccessible()) {
            cleanerField = null;
         }

         DBB_CONSTRUCTOR = constructor;
         DBB_CLEANER_FIELD = cleanerField;
         if (DBB_CONSTRUCTOR != null && DBB_CLEANER_FIELD != null) {
            Class<?> cleanerClass = Class.forName("jdk.internal.ref.Cleaner");
            Method createMethod = cleanerClass.getMethod("create", Object.class, Runnable.class);

            try {
               createMethod.invoke((Object)null, null, null);
            } catch (IllegalAccessException var8) {
               createMethod = null;
            }

            CLEANER_CREATE_METHOD = createMethod;
         } else {
            CLEANER_CREATE_METHOD = null;
         }
      } catch (NoSuchMethodException | NoSuchFieldException | ClassNotFoundException e) {
         throw new IllegalStateException(e);
      } catch (InvocationTargetException ite) {
         throw new IllegalStateException(ite.getCause());
      }

      Unsafe unsafe;
      try {
         Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
         unsafeField.setAccessible(true);
         unsafe = (Unsafe)unsafeField.get((Object)null);
      } catch (Throwable var7) {
         unsafe = null;
      }

      _UNSAFE = unsafe;
      if (_UNSAFE != null) {
         BOOLEAN_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(boolean[].class);
         BYTE_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(byte[].class);
         SHORT_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(short[].class);
         INT_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(int[].class);
         LONG_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(long[].class);
         FLOAT_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(float[].class);
         DOUBLE_ARRAY_OFFSET = _UNSAFE.arrayBaseOffset(double[].class);
      } else {
         BOOLEAN_ARRAY_OFFSET = 0;
         BYTE_ARRAY_OFFSET = 0;
         SHORT_ARRAY_OFFSET = 0;
         INT_ARRAY_OFFSET = 0;
         LONG_ARRAY_OFFSET = 0;
         FLOAT_ARRAY_OFFSET = 0;
         DOUBLE_ARRAY_OFFSET = 0;
      }

      String arch = System.getProperty("os.arch", "");
      boolean _unaligned;
      if (!arch.equals("ppc64le") && !arch.equals("ppc64") && !arch.equals("s390x")) {
         try {
            Class<?> bitsClass = Class.forName("java.nio.Bits", false, ClassLoader.getSystemClassLoader());
            if (_UNSAFE != null) {
               Field unalignedField = bitsClass.getDeclaredField("UNALIGNED");
               _unaligned = _UNSAFE.getBoolean(_UNSAFE.staticFieldBase(unalignedField), _UNSAFE.staticFieldOffset(unalignedField));
            } else {
               Method unalignedMethod = bitsClass.getDeclaredMethod("unaligned");
               unalignedMethod.setAccessible(true);
               _unaligned = Boolean.TRUE.equals(unalignedMethod.invoke((Object)null));
            }
         } catch (Throwable var6) {
            _unaligned = arch.matches("^(i[3-6]86|x86(_64)?|x64|amd64|aarch64)$");
         }
      } else {
         _unaligned = true;
      }

      unaligned = _unaligned;
   }
}
