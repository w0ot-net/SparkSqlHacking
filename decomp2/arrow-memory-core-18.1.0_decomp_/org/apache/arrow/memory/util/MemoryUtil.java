package org.apache.arrow.memory.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

public class MemoryUtil {
   private static final Logger logger = LoggerFactory.getLogger(MemoryUtil.class);
   private static final @Nullable Constructor DIRECT_BUFFER_CONSTRUCTOR;
   private static final Unsafe UNSAFE;
   private static final long BYTE_ARRAY_BASE_OFFSET;
   private static final long BYTE_BUFFER_ADDRESS_OFFSET;
   public static final boolean LITTLE_ENDIAN;
   private static final int majorVersion;

   public static long getByteBufferAddress(ByteBuffer buf) {
      return UNSAFE.getLong(buf, BYTE_BUFFER_ADDRESS_OFFSET);
   }

   private MemoryUtil() {
   }

   public static ByteBuffer directBuffer(long address, int capacity) {
      if (DIRECT_BUFFER_CONSTRUCTOR != null) {
         if (capacity < 0) {
            throw new IllegalArgumentException("Capacity is negative, has to be positive or 0");
         } else {
            try {
               return (ByteBuffer)DIRECT_BUFFER_CONSTRUCTOR.newInstance(address, capacity);
            } catch (Throwable cause) {
               throw new Error(cause);
            }
         }
      } else {
         throw new UnsupportedOperationException("sun.misc.Unsafe or java.nio.DirectByteBuffer.<init>(long, int) not available");
      }
   }

   private static void copyMemory(@Nullable Object srcBase, long srcOffset, @Nullable Object destBase, long destOffset, long bytes) {
      UNSAFE.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
   }

   public static void copyMemory(long srcAddress, long destAddress, long bytes) {
      UNSAFE.copyMemory(srcAddress, destAddress, bytes);
   }

   public static void copyToMemory(byte[] src, long srcIndex, long destAddress, long bytes) {
      copyMemory(src, BYTE_ARRAY_BASE_OFFSET + srcIndex, (Object)null, destAddress, bytes);
   }

   public static void copyFromMemory(long srcAddress, byte[] dest, long destIndex, long bytes) {
      copyMemory((Object)null, srcAddress, dest, BYTE_ARRAY_BASE_OFFSET + destIndex, bytes);
   }

   public static byte getByte(long address) {
      return UNSAFE.getByte(address);
   }

   public static void putByte(long address, byte value) {
      UNSAFE.putByte(address, value);
   }

   public static short getShort(long address) {
      return UNSAFE.getShort(address);
   }

   public static void putShort(long address, short value) {
      UNSAFE.putShort(address, value);
   }

   public static int getInt(long address) {
      return UNSAFE.getInt(address);
   }

   public static void putInt(long address, int value) {
      UNSAFE.putInt(address, value);
   }

   public static long getLong(long address) {
      return UNSAFE.getLong(address);
   }

   public static void putLong(long address, long value) {
      UNSAFE.putLong(address, value);
   }

   public static void setMemory(long address, long bytes, byte value) {
      UNSAFE.setMemory(address, bytes, value);
   }

   public static int getInt(byte[] bytes, int index) {
      return UNSAFE.getInt(bytes, BYTE_ARRAY_BASE_OFFSET + (long)index);
   }

   public static long getLong(byte[] bytes, int index) {
      return UNSAFE.getLong(bytes, BYTE_ARRAY_BASE_OFFSET + (long)index);
   }

   public static long allocateMemory(long bytes) {
      return UNSAFE.allocateMemory(bytes);
   }

   public static void freeMemory(long address) {
      UNSAFE.freeMemory(address);
   }

   static {
      LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
      majorVersion = Integer.parseInt(System.getProperty("java.specification.version").split("\\D+")[0]);

      try {
         Object maybeUnsafe = AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               try {
                  Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
                  unsafeField.setAccessible(true);
                  return unsafeField.get((Object)null);
               } catch (Throwable e) {
                  return e;
               }
            }
         });
         if (maybeUnsafe instanceof Throwable) {
            throw (Throwable)maybeUnsafe;
         } else {
            UNSAFE = (Unsafe)maybeUnsafe;
            BYTE_ARRAY_BASE_OFFSET = (long)UNSAFE.arrayBaseOffset(byte[].class);
            Field addressField = Buffer.class.getDeclaredField("address");
            addressField.setAccessible(true);
            BYTE_BUFFER_ADDRESS_OFFSET = UNSAFE.objectFieldOffset(addressField);
            long address = -1L;
            final ByteBuffer direct = ByteBuffer.allocateDirect(1);

            Constructor<?> directBufferConstructor;
            try {
               Object maybeDirectBufferConstructor = AccessController.doPrivileged(new PrivilegedAction() {
                  public Object run() {
                     try {
                        Constructor<?> constructor = MemoryUtil.majorVersion >= 21 ? direct.getClass().getDeclaredConstructor(Long.TYPE, Long.TYPE) : direct.getClass().getDeclaredConstructor(Long.TYPE, Integer.TYPE);
                        constructor.setAccessible(true);
                        MemoryUtil.logger.debug("Constructor for direct buffer found and made accessible");
                        return constructor;
                     } catch (NoSuchMethodException e) {
                        MemoryUtil.logger.debug("Cannot get constructor for direct buffer allocation", e);
                        return e;
                     } catch (SecurityException e) {
                        MemoryUtil.logger.debug("Cannot get constructor for direct buffer allocation", e);
                        return e;
                     }
                  }
               });
               if (maybeDirectBufferConstructor instanceof Constructor) {
                  address = UNSAFE.allocateMemory(1L);

                  try {
                     ((Constructor)maybeDirectBufferConstructor).newInstance(address, 1);
                     directBufferConstructor = (Constructor)maybeDirectBufferConstructor;
                     logger.debug("direct buffer constructor: available");
                  } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                     logger.warn("unable to instantiate a direct buffer via constructor", e);
                     directBufferConstructor = null;
                  }
               } else {
                  logger.debug("direct buffer constructor: unavailable", (Throwable)maybeDirectBufferConstructor);
                  directBufferConstructor = null;
               }
            } finally {
               if (address != -1L) {
                  UNSAFE.freeMemory(address);
               }

            }

            DIRECT_BUFFER_CONSTRUCTOR = directBufferConstructor;
         }
      } catch (Throwable e) {
         RuntimeException failure = new RuntimeException("Failed to initialize MemoryUtil. You must start Java with `--add-opens=java.base/java.nio=org.apache.arrow.memory.core,ALL-UNNAMED` (See https://arrow.apache.org/docs/java/install.html)", e);
         failure.printStackTrace();
         throw failure;
      }
   }
}
