package io.netty.util.internal;

import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.shaded.org.jctools.queues.MpmcArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.MpscChunkedArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.MpscUnboundedArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.SpscLinkedQueue;
import io.netty.util.internal.shaded.org.jctools.queues.atomic.MpmcAtomicArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscAtomicArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscChunkedAtomicArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscUnboundedAtomicArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.atomic.SpscLinkedAtomicQueue;
import io.netty.util.internal.shaded.org.jctools.queues.atomic.unpadded.MpscAtomicUnpaddedArrayQueue;
import io.netty.util.internal.shaded.org.jctools.queues.unpadded.MpscUnpaddedArrayQueue;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PlatformDependent {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(PlatformDependent.class);
   private static Pattern MAX_DIRECT_MEMORY_SIZE_ARG_PATTERN;
   private static final boolean MAYBE_SUPER_USER;
   private static final boolean CAN_ENABLE_TCP_NODELAY_BY_DEFAULT = !isAndroid();
   private static final Throwable UNSAFE_UNAVAILABILITY_CAUSE = unsafeUnavailabilityCause0();
   private static final boolean DIRECT_BUFFER_PREFERRED;
   private static final long MAX_DIRECT_MEMORY = estimateMaxDirectMemory();
   private static final int MPSC_CHUNK_SIZE = 1024;
   private static final int MIN_MAX_MPSC_CAPACITY = 2048;
   private static final int MAX_ALLOWED_MPSC_CAPACITY = 1073741824;
   private static final long BYTE_ARRAY_BASE_OFFSET = byteArrayBaseOffset0();
   private static final File TMPDIR = tmpdir0();
   private static final int BIT_MODE = bitMode0();
   private static final String NORMALIZED_ARCH = normalizeArch(SystemPropertyUtil.get("os.arch", ""));
   private static final String NORMALIZED_OS = normalizeOs(SystemPropertyUtil.get("os.name", ""));
   private static final String[] ALLOWED_LINUX_OS_CLASSIFIERS = new String[]{"fedora", "suse", "arch"};
   private static final Set LINUX_OS_CLASSIFIERS;
   private static final boolean IS_WINDOWS = isWindows0();
   private static final boolean IS_OSX = isOsx0();
   private static final boolean IS_J9_JVM = isJ9Jvm0();
   private static final boolean IS_IVKVM_DOT_NET = isIkvmDotNet0();
   private static final int ADDRESS_SIZE = addressSize0();
   private static final boolean USE_DIRECT_BUFFER_NO_CLEANER;
   private static final AtomicLong DIRECT_MEMORY_COUNTER;
   private static final long DIRECT_MEMORY_LIMIT;
   private static final ThreadLocalRandomProvider RANDOM_PROVIDER;
   private static final Cleaner CLEANER;
   private static final int UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD;
   private static final String[] OS_RELEASE_FILES = new String[]{"/etc/os-release", "/usr/lib/os-release"};
   private static final String LINUX_ID_PREFIX = "ID=";
   private static final String LINUX_ID_LIKE_PREFIX = "ID_LIKE=";
   public static final boolean BIG_ENDIAN_NATIVE_ORDER;
   private static final Cleaner NOOP;

   static void addFilesystemOsClassifiers(final Set allowedClassifiers, final Set availableClassifiers) {
      for(final String osReleaseFileName : OS_RELEASE_FILES) {
         final File file = new File(osReleaseFileName);
         boolean found = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
            public Boolean run() {
               try {
                  if (file.exists()) {
                     BufferedReader reader = null;

                     try {
                        reader = new BufferedReader(new InputStreamReader(new BoundedInputStream(new FileInputStream(file)), CharsetUtil.UTF_8));

                        String line;
                        while((line = reader.readLine()) != null) {
                           if (line.startsWith("ID=")) {
                              String id = PlatformDependent.normalizeOsReleaseVariableValue(line.substring("ID=".length()));
                              PlatformDependent.addClassifier(allowedClassifiers, availableClassifiers, id);
                           } else if (line.startsWith("ID_LIKE=")) {
                              line = PlatformDependent.normalizeOsReleaseVariableValue(line.substring("ID_LIKE=".length()));
                              PlatformDependent.addClassifier(allowedClassifiers, availableClassifiers, line.split("[ ]+"));
                           }
                        }
                     } catch (SecurityException e) {
                        PlatformDependent.logger.debug("Unable to read {}", osReleaseFileName, e);
                     } catch (IOException e) {
                        PlatformDependent.logger.debug("Error while reading content of {}", osReleaseFileName, e);
                     } finally {
                        if (reader != null) {
                           try {
                              reader.close();
                           } catch (IOException var14) {
                           }
                        }

                     }

                     return true;
                  }
               } catch (SecurityException e) {
                  PlatformDependent.logger.debug("Unable to check if {} exists", osReleaseFileName, e);
               }

               return false;
            }
         });
         if (found) {
            break;
         }
      }

   }

   static boolean addPropertyOsClassifiers(Set allowedClassifiers, Set availableClassifiers) {
      String osClassifiersPropertyName = "io.netty.osClassifiers";
      String osClassifiers = SystemPropertyUtil.get(osClassifiersPropertyName);
      if (osClassifiers == null) {
         return false;
      } else if (osClassifiers.isEmpty()) {
         return true;
      } else {
         String[] classifiers = osClassifiers.split(",");
         if (classifiers.length == 0) {
            throw new IllegalArgumentException(osClassifiersPropertyName + " property is not empty, but contains no classifiers: " + osClassifiers);
         } else if (classifiers.length > 2) {
            throw new IllegalArgumentException(osClassifiersPropertyName + " property contains more than 2 classifiers: " + osClassifiers);
         } else {
            for(String classifier : classifiers) {
               addClassifier(allowedClassifiers, availableClassifiers, classifier);
            }

            return true;
         }
      }
   }

   public static long byteArrayBaseOffset() {
      return BYTE_ARRAY_BASE_OFFSET;
   }

   public static boolean hasDirectBufferNoCleanerConstructor() {
      return PlatformDependent0.hasDirectBufferNoCleanerConstructor();
   }

   public static byte[] allocateUninitializedArray(int size) {
      return UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD >= 0 && UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD <= size ? PlatformDependent0.allocateUninitializedArray(size) : new byte[size];
   }

   public static boolean isAndroid() {
      return PlatformDependent0.isAndroid();
   }

   public static boolean isWindows() {
      return IS_WINDOWS;
   }

   public static boolean isOsx() {
      return IS_OSX;
   }

   public static boolean maybeSuperUser() {
      return MAYBE_SUPER_USER;
   }

   public static int javaVersion() {
      return PlatformDependent0.javaVersion();
   }

   public static boolean canEnableTcpNoDelayByDefault() {
      return CAN_ENABLE_TCP_NODELAY_BY_DEFAULT;
   }

   public static boolean hasUnsafe() {
      return UNSAFE_UNAVAILABILITY_CAUSE == null;
   }

   public static Throwable getUnsafeUnavailabilityCause() {
      return UNSAFE_UNAVAILABILITY_CAUSE;
   }

   public static boolean isUnaligned() {
      return PlatformDependent0.isUnaligned();
   }

   public static boolean directBufferPreferred() {
      return DIRECT_BUFFER_PREFERRED;
   }

   public static long maxDirectMemory() {
      return DIRECT_MEMORY_LIMIT;
   }

   public static long usedDirectMemory() {
      return DIRECT_MEMORY_COUNTER != null ? DIRECT_MEMORY_COUNTER.get() : -1L;
   }

   public static File tmpdir() {
      return TMPDIR;
   }

   public static int bitMode() {
      return BIT_MODE;
   }

   public static int addressSize() {
      return ADDRESS_SIZE;
   }

   public static long allocateMemory(long size) {
      return PlatformDependent0.allocateMemory(size);
   }

   public static void freeMemory(long address) {
      PlatformDependent0.freeMemory(address);
   }

   public static long reallocateMemory(long address, long newSize) {
      return PlatformDependent0.reallocateMemory(address, newSize);
   }

   public static void throwException(Throwable t) {
      if (hasUnsafe()) {
         PlatformDependent0.throwException(t);
      } else {
         throwException0(t);
      }

   }

   private static void throwException0(Throwable t) throws Throwable {
      throw t;
   }

   public static ConcurrentMap newConcurrentHashMap() {
      return new ConcurrentHashMap();
   }

   public static LongCounter newLongCounter() {
      return (LongCounter)(javaVersion() >= 8 ? new LongAdderCounter() : new AtomicLongCounter());
   }

   public static ConcurrentMap newConcurrentHashMap(int initialCapacity) {
      return new ConcurrentHashMap(initialCapacity);
   }

   public static ConcurrentMap newConcurrentHashMap(int initialCapacity, float loadFactor) {
      return new ConcurrentHashMap(initialCapacity, loadFactor);
   }

   public static ConcurrentMap newConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
      return new ConcurrentHashMap(initialCapacity, loadFactor, concurrencyLevel);
   }

   public static ConcurrentMap newConcurrentHashMap(Map map) {
      return new ConcurrentHashMap(map);
   }

   public static void freeDirectBuffer(ByteBuffer buffer) {
      CLEANER.freeDirectBuffer(buffer);
   }

   public static long directBufferAddress(ByteBuffer buffer) {
      return PlatformDependent0.directBufferAddress(buffer);
   }

   public static ByteBuffer directBuffer(long memoryAddress, int size) {
      if (PlatformDependent0.hasDirectBufferNoCleanerConstructor()) {
         return PlatformDependent0.newDirectBuffer(memoryAddress, size);
      } else {
         throw new UnsupportedOperationException("sun.misc.Unsafe or java.nio.DirectByteBuffer.<init>(long, int) not available");
      }
   }

   public static Object getObject(Object object, long fieldOffset) {
      return PlatformDependent0.getObject(object, fieldOffset);
   }

   public static int getInt(Object object, long fieldOffset) {
      return PlatformDependent0.getInt(object, fieldOffset);
   }

   static void safeConstructPutInt(Object object, long fieldOffset, int value) {
      PlatformDependent0.safeConstructPutInt(object, fieldOffset, value);
   }

   public static int getIntVolatile(long address) {
      return PlatformDependent0.getIntVolatile(address);
   }

   public static void putIntOrdered(long adddress, int newValue) {
      PlatformDependent0.putIntOrdered(adddress, newValue);
   }

   public static byte getByte(long address) {
      return PlatformDependent0.getByte(address);
   }

   public static short getShort(long address) {
      return PlatformDependent0.getShort(address);
   }

   public static int getInt(long address) {
      return PlatformDependent0.getInt(address);
   }

   public static long getLong(long address) {
      return PlatformDependent0.getLong(address);
   }

   public static byte getByte(byte[] data, int index) {
      return hasUnsafe() ? PlatformDependent0.getByte(data, index) : data[index];
   }

   public static byte getByte(byte[] data, long index) {
      return hasUnsafe() ? PlatformDependent0.getByte(data, index) : data[toIntExact(index)];
   }

   public static short getShort(byte[] data, int index) {
      return hasUnsafe() ? PlatformDependent0.getShort(data, index) : (short)data[index];
   }

   public static int getInt(byte[] data, int index) {
      return hasUnsafe() ? PlatformDependent0.getInt(data, index) : data[index];
   }

   public static int getInt(int[] data, long index) {
      return hasUnsafe() ? PlatformDependent0.getInt(data, index) : data[toIntExact(index)];
   }

   public static long getLong(byte[] data, int index) {
      return hasUnsafe() ? PlatformDependent0.getLong(data, index) : (long)data[index];
   }

   public static long getLong(long[] data, long index) {
      return hasUnsafe() ? PlatformDependent0.getLong(data, index) : data[toIntExact(index)];
   }

   private static int toIntExact(long value) {
      if (javaVersion() >= 8) {
         return toIntExact8(value);
      } else {
         int result = (int)value;
         if ((long)result != value) {
            throw new ArithmeticException("Cannot convert to exact int: " + value);
         } else {
            return result;
         }
      }
   }

   @SuppressJava6Requirement(
      reason = "version checked"
   )
   private static int toIntExact8(long value) {
      return Math.toIntExact(value);
   }

   private static long getLongSafe(byte[] bytes, int offset) {
      return BIG_ENDIAN_NATIVE_ORDER ? (long)bytes[offset] << 56 | ((long)bytes[offset + 1] & 255L) << 48 | ((long)bytes[offset + 2] & 255L) << 40 | ((long)bytes[offset + 3] & 255L) << 32 | ((long)bytes[offset + 4] & 255L) << 24 | ((long)bytes[offset + 5] & 255L) << 16 | ((long)bytes[offset + 6] & 255L) << 8 | (long)bytes[offset + 7] & 255L : (long)bytes[offset] & 255L | ((long)bytes[offset + 1] & 255L) << 8 | ((long)bytes[offset + 2] & 255L) << 16 | ((long)bytes[offset + 3] & 255L) << 24 | ((long)bytes[offset + 4] & 255L) << 32 | ((long)bytes[offset + 5] & 255L) << 40 | ((long)bytes[offset + 6] & 255L) << 48 | (long)bytes[offset + 7] << 56;
   }

   private static int getIntSafe(byte[] bytes, int offset) {
      return BIG_ENDIAN_NATIVE_ORDER ? bytes[offset] << 24 | (bytes[offset + 1] & 255) << 16 | (bytes[offset + 2] & 255) << 8 | bytes[offset + 3] & 255 : bytes[offset] & 255 | (bytes[offset + 1] & 255) << 8 | (bytes[offset + 2] & 255) << 16 | bytes[offset + 3] << 24;
   }

   private static short getShortSafe(byte[] bytes, int offset) {
      return BIG_ENDIAN_NATIVE_ORDER ? (short)(bytes[offset] << 8 | bytes[offset + 1] & 255) : (short)(bytes[offset] & 255 | bytes[offset + 1] << 8);
   }

   private static int hashCodeAsciiCompute(CharSequence value, int offset, int hash) {
      return BIG_ENDIAN_NATIVE_ORDER ? hash * -862048943 + hashCodeAsciiSanitizeInt(value, offset + 4) * 461845907 + hashCodeAsciiSanitizeInt(value, offset) : hash * -862048943 + hashCodeAsciiSanitizeInt(value, offset) * 461845907 + hashCodeAsciiSanitizeInt(value, offset + 4);
   }

   private static int hashCodeAsciiSanitizeInt(CharSequence value, int offset) {
      return BIG_ENDIAN_NATIVE_ORDER ? value.charAt(offset + 3) & 31 | (value.charAt(offset + 2) & 31) << 8 | (value.charAt(offset + 1) & 31) << 16 | (value.charAt(offset) & 31) << 24 : (value.charAt(offset + 3) & 31) << 24 | (value.charAt(offset + 2) & 31) << 16 | (value.charAt(offset + 1) & 31) << 8 | value.charAt(offset) & 31;
   }

   private static int hashCodeAsciiSanitizeShort(CharSequence value, int offset) {
      return BIG_ENDIAN_NATIVE_ORDER ? value.charAt(offset + 1) & 31 | (value.charAt(offset) & 31) << 8 : (value.charAt(offset + 1) & 31) << 8 | value.charAt(offset) & 31;
   }

   private static int hashCodeAsciiSanitizeByte(char value) {
      return value & 31;
   }

   public static void putByte(long address, byte value) {
      PlatformDependent0.putByte(address, value);
   }

   public static void putShort(long address, short value) {
      PlatformDependent0.putShort(address, value);
   }

   public static void putInt(long address, int value) {
      PlatformDependent0.putInt(address, value);
   }

   public static void putLong(long address, long value) {
      PlatformDependent0.putLong(address, value);
   }

   public static void putByte(byte[] data, int index, byte value) {
      PlatformDependent0.putByte(data, index, value);
   }

   public static void putByte(Object data, long offset, byte value) {
      PlatformDependent0.putByte(data, offset, value);
   }

   public static void putShort(byte[] data, int index, short value) {
      PlatformDependent0.putShort(data, index, value);
   }

   public static void putInt(byte[] data, int index, int value) {
      PlatformDependent0.putInt(data, index, value);
   }

   public static void putLong(byte[] data, int index, long value) {
      PlatformDependent0.putLong(data, index, value);
   }

   public static void putObject(Object o, long offset, Object x) {
      PlatformDependent0.putObject(o, offset, x);
   }

   public static long objectFieldOffset(Field field) {
      return PlatformDependent0.objectFieldOffset(field);
   }

   public static void copyMemory(long srcAddr, long dstAddr, long length) {
      PlatformDependent0.copyMemory(srcAddr, dstAddr, length);
   }

   public static void copyMemory(byte[] src, int srcIndex, long dstAddr, long length) {
      PlatformDependent0.copyMemory(src, BYTE_ARRAY_BASE_OFFSET + (long)srcIndex, (Object)null, dstAddr, length);
   }

   public static void copyMemory(byte[] src, int srcIndex, byte[] dst, int dstIndex, long length) {
      PlatformDependent0.copyMemory(src, BYTE_ARRAY_BASE_OFFSET + (long)srcIndex, dst, BYTE_ARRAY_BASE_OFFSET + (long)dstIndex, length);
   }

   public static void copyMemory(long srcAddr, byte[] dst, int dstIndex, long length) {
      PlatformDependent0.copyMemory((Object)null, srcAddr, dst, BYTE_ARRAY_BASE_OFFSET + (long)dstIndex, length);
   }

   public static void setMemory(byte[] dst, int dstIndex, long bytes, byte value) {
      PlatformDependent0.setMemory(dst, BYTE_ARRAY_BASE_OFFSET + (long)dstIndex, bytes, value);
   }

   public static void setMemory(long address, long bytes, byte value) {
      PlatformDependent0.setMemory(address, bytes, value);
   }

   public static ByteBuffer allocateDirectNoCleaner(int capacity) {
      assert USE_DIRECT_BUFFER_NO_CLEANER;

      incrementMemoryCounter(capacity);

      try {
         return PlatformDependent0.allocateDirectNoCleaner(capacity);
      } catch (Throwable e) {
         decrementMemoryCounter(capacity);
         throwException(e);
         return null;
      }
   }

   public static ByteBuffer reallocateDirectNoCleaner(ByteBuffer buffer, int capacity) {
      assert USE_DIRECT_BUFFER_NO_CLEANER;

      int len = capacity - buffer.capacity();
      incrementMemoryCounter(len);

      try {
         return PlatformDependent0.reallocateDirectNoCleaner(buffer, capacity);
      } catch (Throwable e) {
         decrementMemoryCounter(len);
         throwException(e);
         return null;
      }
   }

   public static void freeDirectNoCleaner(ByteBuffer buffer) {
      assert USE_DIRECT_BUFFER_NO_CLEANER;

      int capacity = buffer.capacity();
      PlatformDependent0.freeMemory(PlatformDependent0.directBufferAddress(buffer));
      decrementMemoryCounter(capacity);
   }

   public static boolean hasAlignDirectByteBuffer() {
      return hasUnsafe() || PlatformDependent0.hasAlignSliceMethod();
   }

   public static ByteBuffer alignDirectBuffer(ByteBuffer buffer, int alignment) {
      if (!buffer.isDirect()) {
         throw new IllegalArgumentException("Cannot get aligned slice of non-direct byte buffer.");
      } else if (PlatformDependent0.hasAlignSliceMethod()) {
         return PlatformDependent0.alignSlice(buffer, alignment);
      } else if (hasUnsafe()) {
         long address = directBufferAddress(buffer);
         long aligned = align(address, alignment);
         buffer.position((int)(aligned - address));
         return buffer.slice();
      } else {
         throw new UnsupportedOperationException("Cannot align direct buffer. Needs either Unsafe or ByteBuffer.alignSlice method available.");
      }
   }

   public static long align(long value, int alignment) {
      return Pow2.align(value, alignment);
   }

   private static void incrementMemoryCounter(int capacity) {
      if (DIRECT_MEMORY_COUNTER != null) {
         long newUsedMemory = DIRECT_MEMORY_COUNTER.addAndGet((long)capacity);
         if (newUsedMemory > DIRECT_MEMORY_LIMIT) {
            DIRECT_MEMORY_COUNTER.addAndGet((long)(-capacity));
            throw new OutOfDirectMemoryError("failed to allocate " + capacity + " byte(s) of direct memory (used: " + (newUsedMemory - (long)capacity) + ", max: " + DIRECT_MEMORY_LIMIT + ')');
         }
      }

   }

   private static void decrementMemoryCounter(int capacity) {
      if (DIRECT_MEMORY_COUNTER != null) {
         long usedMemory = DIRECT_MEMORY_COUNTER.addAndGet((long)(-capacity));

         assert usedMemory >= 0L;
      }

   }

   public static boolean useDirectBufferNoCleaner() {
      return USE_DIRECT_BUFFER_NO_CLEANER;
   }

   public static boolean equals(byte[] bytes1, int startPos1, byte[] bytes2, int startPos2, int length) {
      if (javaVersion() > 8 && (startPos2 | startPos1 | bytes1.length - length | bytes2.length - length) == 0) {
         return Arrays.equals(bytes1, bytes2);
      } else {
         return hasUnsafe() && PlatformDependent0.unalignedAccess() ? PlatformDependent0.equals(bytes1, startPos1, bytes2, startPos2, length) : equalsSafe(bytes1, startPos1, bytes2, startPos2, length);
      }
   }

   public static boolean isZero(byte[] bytes, int startPos, int length) {
      return hasUnsafe() && PlatformDependent0.unalignedAccess() ? PlatformDependent0.isZero(bytes, startPos, length) : isZeroSafe(bytes, startPos, length);
   }

   public static int equalsConstantTime(byte[] bytes1, int startPos1, byte[] bytes2, int startPos2, int length) {
      return hasUnsafe() && PlatformDependent0.unalignedAccess() ? PlatformDependent0.equalsConstantTime(bytes1, startPos1, bytes2, startPos2, length) : ConstantTimeUtils.equalsConstantTime(bytes1, startPos1, bytes2, startPos2, length);
   }

   public static int hashCodeAscii(byte[] bytes, int startPos, int length) {
      return hasUnsafe() && PlatformDependent0.unalignedAccess() ? PlatformDependent0.hashCodeAscii(bytes, startPos, length) : hashCodeAsciiSafe(bytes, startPos, length);
   }

   public static int hashCodeAscii(CharSequence bytes) {
      int length = bytes.length();
      int remainingBytes = length & 7;
      int hash = -1028477387;
      if (length >= 32) {
         for(int i = length - 8; i >= remainingBytes; i -= 8) {
            hash = hashCodeAsciiCompute(bytes, i, hash);
         }
      } else if (length >= 8) {
         hash = hashCodeAsciiCompute(bytes, length - 8, hash);
         if (length >= 16) {
            hash = hashCodeAsciiCompute(bytes, length - 16, hash);
            if (length >= 24) {
               hash = hashCodeAsciiCompute(bytes, length - 24, hash);
            }
         }
      }

      if (remainingBytes == 0) {
         return hash;
      } else {
         int offset = 0;
         if (remainingBytes != 2 & remainingBytes != 4 & remainingBytes != 6) {
            hash = hash * -862048943 + hashCodeAsciiSanitizeByte(bytes.charAt(0));
            offset = 1;
         }

         if (remainingBytes != 1 & remainingBytes != 4 & remainingBytes != 5) {
            hash = hash * (offset == 0 ? -862048943 : 461845907) + PlatformDependent0.hashCodeAsciiSanitize(hashCodeAsciiSanitizeShort(bytes, offset));
            offset += 2;
         }

         return remainingBytes >= 4 ? hash * (offset == 0 | offset == 3 ? -862048943 : 461845907) + hashCodeAsciiSanitizeInt(bytes, offset) : hash;
      }
   }

   public static Queue newMpscQueue() {
      return PlatformDependent.Mpsc.newMpscQueue();
   }

   public static Queue newMpscQueue(int maxCapacity) {
      return PlatformDependent.Mpsc.newMpscQueue(maxCapacity);
   }

   public static Queue newMpscQueue(int chunkSize, int maxCapacity) {
      return PlatformDependent.Mpsc.newChunkedMpscQueue(chunkSize, maxCapacity);
   }

   public static Queue newSpscQueue() {
      return (Queue)(hasUnsafe() ? new SpscLinkedQueue() : new SpscLinkedAtomicQueue());
   }

   public static Queue newFixedMpscQueue(int capacity) {
      return (Queue)(hasUnsafe() ? new MpscArrayQueue(capacity) : new MpscAtomicArrayQueue(capacity));
   }

   public static Queue newFixedMpscUnpaddedQueue(int capacity) {
      return (Queue)(hasUnsafe() ? new MpscUnpaddedArrayQueue(capacity) : new MpscAtomicUnpaddedArrayQueue(capacity));
   }

   public static Queue newFixedMpmcQueue(int capacity) {
      return (Queue)(hasUnsafe() ? new MpmcArrayQueue(capacity) : new MpmcAtomicArrayQueue(capacity));
   }

   public static ClassLoader getClassLoader(Class clazz) {
      return PlatformDependent0.getClassLoader(clazz);
   }

   public static ClassLoader getContextClassLoader() {
      return PlatformDependent0.getContextClassLoader();
   }

   public static ClassLoader getSystemClassLoader() {
      return PlatformDependent0.getSystemClassLoader();
   }

   @SuppressJava6Requirement(
      reason = "Usage guarded by java version check"
   )
   public static Deque newConcurrentDeque() {
      return (Deque)(javaVersion() < 7 ? new LinkedBlockingDeque() : new ConcurrentLinkedDeque());
   }

   public static Random threadLocalRandom() {
      return RANDOM_PROVIDER.current();
   }

   private static boolean isWindows0() {
      boolean windows = "windows".equals(NORMALIZED_OS);
      if (windows) {
         logger.debug("Platform: Windows");
      }

      return windows;
   }

   private static boolean isOsx0() {
      boolean osx = "osx".equals(NORMALIZED_OS);
      if (osx) {
         logger.debug("Platform: MacOS");
      }

      return osx;
   }

   private static boolean maybeSuperUser0() {
      String username = SystemPropertyUtil.get("user.name");
      if (isWindows()) {
         return "Administrator".equals(username);
      } else {
         return "root".equals(username) || "toor".equals(username);
      }
   }

   private static Throwable unsafeUnavailabilityCause0() {
      if (isAndroid()) {
         logger.debug("sun.misc.Unsafe: unavailable (Android)");
         return new UnsupportedOperationException("sun.misc.Unsafe: unavailable (Android)");
      } else if (isIkvmDotNet()) {
         logger.debug("sun.misc.Unsafe: unavailable (IKVM.NET)");
         return new UnsupportedOperationException("sun.misc.Unsafe: unavailable (IKVM.NET)");
      } else {
         Throwable cause = PlatformDependent0.getUnsafeUnavailabilityCause();
         if (cause != null) {
            return cause;
         } else {
            try {
               boolean hasUnsafe = PlatformDependent0.hasUnsafe();
               logger.debug("sun.misc.Unsafe: {}", (Object)(hasUnsafe ? "available" : "unavailable"));
               return hasUnsafe ? null : PlatformDependent0.getUnsafeUnavailabilityCause();
            } catch (Throwable t) {
               logger.trace("Could not determine if Unsafe is available", t);
               return new UnsupportedOperationException("Could not determine if Unsafe is available", t);
            }
         }
      }
   }

   public static boolean isJ9Jvm() {
      return IS_J9_JVM;
   }

   private static boolean isJ9Jvm0() {
      String vmName = SystemPropertyUtil.get("java.vm.name", "").toLowerCase();
      return vmName.startsWith("ibm j9") || vmName.startsWith("eclipse openj9");
   }

   public static boolean isIkvmDotNet() {
      return IS_IVKVM_DOT_NET;
   }

   private static boolean isIkvmDotNet0() {
      String vmName = SystemPropertyUtil.get("java.vm.name", "").toUpperCase(Locale.US);
      return vmName.equals("IKVM.NET");
   }

   private static Pattern getMaxDirectMemorySizeArgPattern() {
      Pattern pattern = MAX_DIRECT_MEMORY_SIZE_ARG_PATTERN;
      if (pattern == null) {
         pattern = Pattern.compile("\\s*-XX:MaxDirectMemorySize\\s*=\\s*([0-9]+)\\s*([kKmMgG]?)\\s*$");
         MAX_DIRECT_MEMORY_SIZE_ARG_PATTERN = pattern;
      }

      return pattern;
   }

   public static long estimateMaxDirectMemory() {
      long maxDirectMemory = PlatformDependent0.bitsMaxDirectMemory();
      if (maxDirectMemory > 0L) {
         return maxDirectMemory;
      } else {
         ClassLoader systemClassLoader = null;

         try {
            systemClassLoader = getSystemClassLoader();
            String vmName = SystemPropertyUtil.get("java.vm.name", "").toLowerCase();
            if (!vmName.startsWith("ibm j9") && !vmName.startsWith("eclipse openj9")) {
               Class<?> vmClass = Class.forName("sun.misc.VM", true, systemClassLoader);
               Method m = vmClass.getDeclaredMethod("maxDirectMemory");
               maxDirectMemory = ((Number)m.invoke((Object)null)).longValue();
            }
         } catch (Throwable var10) {
         }

         if (maxDirectMemory > 0L) {
            return maxDirectMemory;
         } else {
            try {
               Class<?> mgmtFactoryClass = Class.forName("java.lang.management.ManagementFactory", true, systemClassLoader);
               Class<?> runtimeClass = Class.forName("java.lang.management.RuntimeMXBean", true, systemClassLoader);
               Object runtime = mgmtFactoryClass.getDeclaredMethod("getRuntimeMXBean").invoke((Object)null);
               List<String> vmArgs = (List)runtimeClass.getDeclaredMethod("getInputArguments").invoke(runtime);
               Pattern maxDirectMemorySizeArgPattern = getMaxDirectMemorySizeArgPattern();

               label47:
               for(int i = vmArgs.size() - 1; i >= 0; --i) {
                  Matcher m = maxDirectMemorySizeArgPattern.matcher((CharSequence)vmArgs.get(i));
                  if (m.matches()) {
                     maxDirectMemory = Long.parseLong(m.group(1));
                     switch (m.group(2).charAt(0)) {
                        case 'G':
                        case 'g':
                           maxDirectMemory *= 1073741824L;
                           break label47;
                        case 'K':
                        case 'k':
                           maxDirectMemory *= 1024L;
                           break label47;
                        case 'M':
                        case 'm':
                           maxDirectMemory *= 1048576L;
                        default:
                           break label47;
                     }
                  }
               }
            } catch (Throwable var11) {
            }

            if (maxDirectMemory <= 0L) {
               maxDirectMemory = Runtime.getRuntime().maxMemory();
               logger.debug("maxDirectMemory: {} bytes (maybe)", (Object)maxDirectMemory);
            } else {
               logger.debug("maxDirectMemory: {} bytes", (Object)maxDirectMemory);
            }

            return maxDirectMemory;
         }
      }
   }

   private static File tmpdir0() {
      try {
         File f = toDirectory(SystemPropertyUtil.get("io.netty.tmpdir"));
         if (f != null) {
            logger.debug("-Dio.netty.tmpdir: {}", (Object)f);
            return f;
         }

         f = toDirectory(SystemPropertyUtil.get("java.io.tmpdir"));
         if (f != null) {
            logger.debug("-Dio.netty.tmpdir: {} (java.io.tmpdir)", (Object)f);
            return f;
         }

         if (isWindows()) {
            f = toDirectory(System.getenv("TEMP"));
            if (f != null) {
               logger.debug("-Dio.netty.tmpdir: {} (%TEMP%)", (Object)f);
               return f;
            }

            String userprofile = System.getenv("USERPROFILE");
            if (userprofile != null) {
               f = toDirectory(userprofile + "\\AppData\\Local\\Temp");
               if (f != null) {
                  logger.debug("-Dio.netty.tmpdir: {} (%USERPROFILE%\\AppData\\Local\\Temp)", (Object)f);
                  return f;
               }

               f = toDirectory(userprofile + "\\Local Settings\\Temp");
               if (f != null) {
                  logger.debug("-Dio.netty.tmpdir: {} (%USERPROFILE%\\Local Settings\\Temp)", (Object)f);
                  return f;
               }
            }
         } else {
            f = toDirectory(System.getenv("TMPDIR"));
            if (f != null) {
               logger.debug("-Dio.netty.tmpdir: {} ($TMPDIR)", (Object)f);
               return f;
            }
         }
      } catch (Throwable var2) {
      }

      File f;
      if (isWindows()) {
         f = new File("C:\\Windows\\Temp");
      } else {
         f = new File("/tmp");
      }

      logger.warn("Failed to get the temporary directory; falling back to: {}", (Object)f);
      return f;
   }

   private static File toDirectory(String path) {
      if (path == null) {
         return null;
      } else {
         File f = new File(path);
         f.mkdirs();
         if (!f.isDirectory()) {
            return null;
         } else {
            try {
               return f.getAbsoluteFile();
            } catch (Exception var3) {
               return f;
            }
         }
      }
   }

   private static int bitMode0() {
      int bitMode = SystemPropertyUtil.getInt("io.netty.bitMode", 0);
      if (bitMode > 0) {
         logger.debug("-Dio.netty.bitMode: {}", (Object)bitMode);
         return bitMode;
      } else {
         bitMode = SystemPropertyUtil.getInt("sun.arch.data.model", 0);
         if (bitMode > 0) {
            logger.debug("-Dio.netty.bitMode: {} (sun.arch.data.model)", (Object)bitMode);
            return bitMode;
         } else {
            bitMode = SystemPropertyUtil.getInt("com.ibm.vm.bitmode", 0);
            if (bitMode > 0) {
               logger.debug("-Dio.netty.bitMode: {} (com.ibm.vm.bitmode)", (Object)bitMode);
               return bitMode;
            } else {
               String arch = SystemPropertyUtil.get("os.arch", "").toLowerCase(Locale.US).trim();
               if (!"amd64".equals(arch) && !"x86_64".equals(arch)) {
                  if ("i386".equals(arch) || "i486".equals(arch) || "i586".equals(arch) || "i686".equals(arch)) {
                     bitMode = 32;
                  }
               } else {
                  bitMode = 64;
               }

               if (bitMode > 0) {
                  logger.debug("-Dio.netty.bitMode: {} (os.arch: {})", bitMode, arch);
               }

               String vm = SystemPropertyUtil.get("java.vm.name", "").toLowerCase(Locale.US);
               Pattern bitPattern = Pattern.compile("([1-9][0-9]+)-?bit");
               Matcher m = bitPattern.matcher(vm);
               return m.find() ? Integer.parseInt(m.group(1)) : 64;
            }
         }
      }
   }

   private static int addressSize0() {
      return !hasUnsafe() ? -1 : PlatformDependent0.addressSize();
   }

   private static long byteArrayBaseOffset0() {
      return !hasUnsafe() ? -1L : PlatformDependent0.byteArrayBaseOffset();
   }

   private static boolean equalsSafe(byte[] bytes1, int startPos1, byte[] bytes2, int startPos2, int length) {
      for(int end = startPos1 + length; startPos1 < end; ++startPos2) {
         if (bytes1[startPos1] != bytes2[startPos2]) {
            return false;
         }

         ++startPos1;
      }

      return true;
   }

   private static boolean isZeroSafe(byte[] bytes, int startPos, int length) {
      for(int end = startPos + length; startPos < end; ++startPos) {
         if (bytes[startPos] != 0) {
            return false;
         }
      }

      return true;
   }

   static int hashCodeAsciiSafe(byte[] bytes, int startPos, int length) {
      int hash = -1028477387;
      int remainingBytes = length & 7;
      int end = startPos + remainingBytes;

      for(int i = startPos - 8 + length; i >= end; i -= 8) {
         hash = PlatformDependent0.hashCodeAsciiCompute(getLongSafe(bytes, i), hash);
      }

      switch (remainingBytes) {
         case 1:
            return hash * -862048943 + PlatformDependent0.hashCodeAsciiSanitize(bytes[startPos]);
         case 2:
            return hash * -862048943 + PlatformDependent0.hashCodeAsciiSanitize(getShortSafe(bytes, startPos));
         case 3:
            return (hash * -862048943 + PlatformDependent0.hashCodeAsciiSanitize(bytes[startPos])) * 461845907 + PlatformDependent0.hashCodeAsciiSanitize(getShortSafe(bytes, startPos + 1));
         case 4:
            return hash * -862048943 + PlatformDependent0.hashCodeAsciiSanitize(getIntSafe(bytes, startPos));
         case 5:
            return (hash * -862048943 + PlatformDependent0.hashCodeAsciiSanitize(bytes[startPos])) * 461845907 + PlatformDependent0.hashCodeAsciiSanitize(getIntSafe(bytes, startPos + 1));
         case 6:
            return (hash * -862048943 + PlatformDependent0.hashCodeAsciiSanitize(getShortSafe(bytes, startPos))) * 461845907 + PlatformDependent0.hashCodeAsciiSanitize(getIntSafe(bytes, startPos + 2));
         case 7:
            return ((hash * -862048943 + PlatformDependent0.hashCodeAsciiSanitize(bytes[startPos])) * 461845907 + PlatformDependent0.hashCodeAsciiSanitize(getShortSafe(bytes, startPos + 1))) * -862048943 + PlatformDependent0.hashCodeAsciiSanitize(getIntSafe(bytes, startPos + 3));
         default:
            return hash;
      }
   }

   public static String normalizedArch() {
      return NORMALIZED_ARCH;
   }

   public static String normalizedOs() {
      return NORMALIZED_OS;
   }

   public static Set normalizedLinuxClassifiers() {
      return LINUX_OS_CLASSIFIERS;
   }

   @SuppressJava6Requirement(
      reason = "Guarded by version check"
   )
   public static File createTempFile(String prefix, String suffix, File directory) throws IOException {
      if (javaVersion() >= 7) {
         return directory == null ? Files.createTempFile(prefix, suffix).toFile() : Files.createTempFile(directory.toPath(), prefix, suffix).toFile();
      } else {
         File file;
         if (directory == null) {
            file = File.createTempFile(prefix, suffix);
         } else {
            file = File.createTempFile(prefix, suffix, directory);
         }

         if (!file.setReadable(false, false)) {
            throw new IOException("Failed to set permissions on temporary file " + file);
         } else if (!file.setReadable(true, true)) {
            throw new IOException("Failed to set permissions on temporary file " + file);
         } else {
            return file;
         }
      }
   }

   private static void addClassifier(Set allowed, Set dest, String... maybeClassifiers) {
      for(String id : maybeClassifiers) {
         if (allowed.contains(id)) {
            dest.add(id);
         }
      }

   }

   private static String normalizeOsReleaseVariableValue(String value) {
      return value.trim().replaceAll("[\"']", "");
   }

   private static String normalize(String value) {
      return value.toLowerCase(Locale.US).replaceAll("[^a-z0-9]+", "");
   }

   private static String normalizeArch(String value) {
      value = normalize(value);
      if (value.matches("^(x8664|amd64|ia32e|em64t|x64)$")) {
         return "x86_64";
      } else if (value.matches("^(x8632|x86|i[3-6]86|ia32|x32)$")) {
         return "x86_32";
      } else if (value.matches("^(ia64|itanium64)$")) {
         return "itanium_64";
      } else if (value.matches("^(sparc|sparc32)$")) {
         return "sparc_32";
      } else if (value.matches("^(sparcv9|sparc64)$")) {
         return "sparc_64";
      } else if (value.matches("^(arm|arm32)$")) {
         return "arm_32";
      } else if ("aarch64".equals(value)) {
         return "aarch_64";
      } else if ("riscv64".equals(value)) {
         return "riscv64";
      } else if (value.matches("^(ppc|ppc32)$")) {
         return "ppc_32";
      } else if ("ppc64".equals(value)) {
         return "ppc_64";
      } else if ("ppc64le".equals(value)) {
         return "ppcle_64";
      } else if ("s390".equals(value)) {
         return "s390_32";
      } else if ("s390x".equals(value)) {
         return "s390_64";
      } else {
         return "loongarch64".equals(value) ? "loongarch_64" : "unknown";
      }
   }

   private static String normalizeOs(String value) {
      value = normalize(value);
      if (value.startsWith("aix")) {
         return "aix";
      } else if (value.startsWith("hpux")) {
         return "hpux";
      } else if (!value.startsWith("os400") || value.length() > 5 && Character.isDigit(value.charAt(5))) {
         if (value.startsWith("linux")) {
            return "linux";
         } else if (!value.startsWith("macosx") && !value.startsWith("osx") && !value.startsWith("darwin")) {
            if (value.startsWith("freebsd")) {
               return "freebsd";
            } else if (value.startsWith("openbsd")) {
               return "openbsd";
            } else if (value.startsWith("netbsd")) {
               return "netbsd";
            } else if (!value.startsWith("solaris") && !value.startsWith("sunos")) {
               return value.startsWith("windows") ? "windows" : "unknown";
            } else {
               return "sunos";
            }
         } else {
            return "osx";
         }
      } else {
         return "os400";
      }
   }

   private PlatformDependent() {
   }

   static {
      BIG_ENDIAN_NATIVE_ORDER = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
      NOOP = new Cleaner() {
         public void freeDirectBuffer(ByteBuffer buffer) {
         }
      };
      if (javaVersion() >= 7) {
         RANDOM_PROVIDER = new ThreadLocalRandomProvider() {
            @SuppressJava6Requirement(
               reason = "Usage guarded by java version check"
            )
            public Random current() {
               return java.util.concurrent.ThreadLocalRandom.current();
            }
         };
      } else {
         RANDOM_PROVIDER = new ThreadLocalRandomProvider() {
            public Random current() {
               return ThreadLocalRandom.current();
            }
         };
      }

      long maxDirectMemory = SystemPropertyUtil.getLong("io.netty.maxDirectMemory", -1L);
      if (maxDirectMemory != 0L && hasUnsafe() && PlatformDependent0.hasDirectBufferNoCleanerConstructor()) {
         USE_DIRECT_BUFFER_NO_CLEANER = true;
         if (maxDirectMemory < 0L) {
            maxDirectMemory = MAX_DIRECT_MEMORY;
            if (maxDirectMemory <= 0L) {
               DIRECT_MEMORY_COUNTER = null;
            } else {
               DIRECT_MEMORY_COUNTER = new AtomicLong();
            }
         } else {
            DIRECT_MEMORY_COUNTER = new AtomicLong();
         }
      } else {
         USE_DIRECT_BUFFER_NO_CLEANER = false;
         DIRECT_MEMORY_COUNTER = null;
      }

      logger.debug("-Dio.netty.maxDirectMemory: {} bytes", (Object)maxDirectMemory);
      DIRECT_MEMORY_LIMIT = maxDirectMemory >= 1L ? maxDirectMemory : MAX_DIRECT_MEMORY;
      int tryAllocateUninitializedArray = SystemPropertyUtil.getInt("io.netty.uninitializedArrayAllocationThreshold", 1024);
      UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD = javaVersion() >= 9 && PlatformDependent0.hasAllocateArrayMethod() ? tryAllocateUninitializedArray : -1;
      logger.debug("-Dio.netty.uninitializedArrayAllocationThreshold: {}", (Object)UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD);
      MAYBE_SUPER_USER = maybeSuperUser0();
      if (!isAndroid()) {
         if (javaVersion() >= 9) {
            CLEANER = (Cleaner)(CleanerJava9.isSupported() ? new CleanerJava9() : NOOP);
         } else {
            CLEANER = (Cleaner)(CleanerJava6.isSupported() ? new CleanerJava6() : NOOP);
         }
      } else {
         CLEANER = NOOP;
      }

      DIRECT_BUFFER_PREFERRED = CLEANER != NOOP && !SystemPropertyUtil.getBoolean("io.netty.noPreferDirect", false);
      if (logger.isDebugEnabled()) {
         logger.debug("-Dio.netty.noPreferDirect: {}", (Object)(!DIRECT_BUFFER_PREFERRED));
      }

      if (CLEANER == NOOP && !PlatformDependent0.isExplicitNoUnsafe()) {
         logger.info("Your platform does not provide complete low-level API for accessing direct buffers reliably. Unless explicitly requested, heap buffer will always be preferred to avoid potential system instability.");
      }

      Set<String> allowedClassifiers = Collections.unmodifiableSet(new HashSet(Arrays.asList(ALLOWED_LINUX_OS_CLASSIFIERS)));
      Set<String> availableClassifiers = new LinkedHashSet();
      if (!addPropertyOsClassifiers(allowedClassifiers, availableClassifiers)) {
         addFilesystemOsClassifiers(allowedClassifiers, availableClassifiers);
      }

      LINUX_OS_CLASSIFIERS = Collections.unmodifiableSet(availableClassifiers);
   }

   private static final class Mpsc {
      private static final boolean USE_MPSC_CHUNKED_ARRAY_QUEUE;

      static Queue newMpscQueue(int maxCapacity) {
         int capacity = Math.max(Math.min(maxCapacity, 1073741824), 2048);
         return newChunkedMpscQueue(1024, capacity);
      }

      static Queue newChunkedMpscQueue(int chunkSize, int capacity) {
         return (Queue)(USE_MPSC_CHUNKED_ARRAY_QUEUE ? new MpscChunkedArrayQueue(chunkSize, capacity) : new MpscChunkedAtomicArrayQueue(chunkSize, capacity));
      }

      static Queue newMpscQueue() {
         return (Queue)(USE_MPSC_CHUNKED_ARRAY_QUEUE ? new MpscUnboundedArrayQueue(1024) : new MpscUnboundedAtomicArrayQueue(1024));
      }

      static {
         Object unsafe = null;
         if (PlatformDependent.hasUnsafe()) {
            unsafe = AccessController.doPrivileged(new PrivilegedAction() {
               public Object run() {
                  return UnsafeAccess.UNSAFE;
               }
            });
         }

         if (unsafe == null) {
            PlatformDependent.logger.debug("org.jctools-core.MpscChunkedArrayQueue: unavailable");
            USE_MPSC_CHUNKED_ARRAY_QUEUE = false;
         } else {
            PlatformDependent.logger.debug("org.jctools-core.MpscChunkedArrayQueue: available");
            USE_MPSC_CHUNKED_ARRAY_QUEUE = true;
         }

      }
   }

   private static final class AtomicLongCounter extends AtomicLong implements LongCounter {
      private static final long serialVersionUID = 4074772784610639305L;

      private AtomicLongCounter() {
      }

      public void add(long delta) {
         this.addAndGet(delta);
      }

      public void increment() {
         this.incrementAndGet();
      }

      public void decrement() {
         this.decrementAndGet();
      }

      public long value() {
         return this.get();
      }
   }

   private interface ThreadLocalRandomProvider {
      Random current();
   }
}
