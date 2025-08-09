package org.apache.datasketches.memory.internal;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import org.apache.datasketches.memory.Memory;

public final class Util {
   public static final String LS = System.getProperty("line.separator");
   public static final ByteOrder NATIVE_BYTE_ORDER = ByteOrder.nativeOrder();
   public static final ByteOrder NON_NATIVE_BYTE_ORDER;
   public static final int UNSAFE_COPY_THRESHOLD_BYTES = 1048576;

   private Util() {
   }

   public static ByteOrder otherByteOrder(ByteOrder order) {
      return order == NATIVE_BYTE_ORDER ? NON_NATIVE_BYTE_ORDER : NATIVE_BYTE_ORDER;
   }

   public static boolean isNativeByteOrder(ByteOrder byteOrder) {
      if (byteOrder == null) {
         throw new IllegalArgumentException("ByteOrder parameter cannot be null.");
      } else {
         return ByteOrder.nativeOrder() == byteOrder;
      }
   }

   public static long binarySearchLongs(Memory mem, long fromLongIndex, long toLongIndex, long key) {
      ResourceImpl.checkBounds(fromLongIndex << 3, toLongIndex - fromLongIndex << 3, mem.getCapacity());
      long low = fromLongIndex;
      long high = toLongIndex - 1L;

      while(low <= high) {
         long mid = low + high >>> 1;
         long midVal = mem.getLong(mid << 3);
         if (midVal < key) {
            low = mid + 1L;
         } else {
            if (midVal <= key) {
               return mid;
            }

            high = mid - 1L;
         }
      }

      return -(low + 1L);
   }

   public static final String zeroPad(String s, int fieldLength) {
      return characterPad(s, fieldLength, '0', false);
   }

   public static final String characterPad(String s, int fieldLength, char padChar, boolean postpend) {
      int sLen = s.length();
      if (sLen < fieldLength) {
         char[] cArr = new char[fieldLength - sLen];
         Arrays.fill(cArr, padChar);
         String addstr = String.valueOf(cArr);
         return postpend ? s.concat(addstr) : addstr.concat(s);
      } else {
         return s;
      }
   }

   public static final boolean isAllBitsClear(long value, long bitMask) {
      return (~value & bitMask) == bitMask;
   }

   public static final boolean isAllBitsSet(long value, long bitMask) {
      return (value & bitMask) == bitMask;
   }

   public static final boolean isAnyBitsClear(long value, long bitMask) {
      return (~value & bitMask) != 0L;
   }

   public static final boolean isAnyBitsSet(long value, long bitMask) {
      return (value & bitMask) != 0L;
   }

   public static final void zeroCheck(long value, String arg) {
      if (value <= 0L) {
         throw new IllegalArgumentException("The argument '" + arg + "' may not be negative or zero.");
      }
   }

   public static final void negativeCheck(long value, String arg) {
      if (value < 0L) {
         throw new IllegalArgumentException("The argument '" + arg + "' may not be negative.");
      }
   }

   public static final void nullCheck(Object obj, String arg) {
      if (obj == null) {
         throw new IllegalArgumentException("The argument '" + arg + "' may not be null.");
      }
   }

   public static String getResourcePath(String shortFileName) {
      Objects.requireNonNull(shortFileName, "input parameter " + shortFileName + " cannot be null.");

      try {
         URL url = Util.class.getClassLoader().getResource(shortFileName);
         Objects.requireNonNull(url, "resource " + shortFileName + " could not be acquired.");
         URI uri = url.toURI();
         String path = uri.isAbsolute() ? Paths.get(uri).toAbsolutePath().toString() : uri.getPath();
         return path;
      } catch (URISyntaxException e) {
         throw new IllegalArgumentException("Cannot find resource: " + shortFileName + LS + e);
      }
   }

   public static File getResourceFile(String shortFileName) {
      return new File(getResourcePath(shortFileName));
   }

   public static byte[] getResourceBytes(String shortFileName) {
      try {
         return Files.readAllBytes(Paths.get(getResourcePath(shortFileName)));
      } catch (IOException e) {
         throw new IllegalArgumentException("Cannot read resource: " + shortFileName + LS + e);
      }
   }

   static {
      NON_NATIVE_BYTE_ORDER = NATIVE_BYTE_ORDER == ByteOrder.LITTLE_ENDIAN ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
   }

   public static class RandomCodePoints {
      private Random rand;
      private static final int ALL_CP = 1114112;
      private static final int MIN_SUR = 55296;
      private static final int MAX_SUR = 57343;

      public RandomCodePoints(boolean deterministic) {
         this.rand = deterministic ? new Random(0L) : new Random();
      }

      public final void fillCodePointArray(int[] cpArr) {
         this.fillCodePointArray(cpArr, 0, 1114112);
      }

      public final void fillCodePointArray(int[] cpArr, int startCP, int endCP) {
         int arrLen = cpArr.length;
         int numCP = Math.min(endCP, 1114112) - Math.min(0, startCP);
         int idx = 0;

         while(idx < arrLen) {
            int cp = startCP + this.rand.nextInt(numCP);
            if (cp < 55296 || cp > 57343) {
               cpArr[idx++] = cp;
            }
         }

      }

      public final int getCodePoint() {
         return this.getCodePoint(0, 1114112);
      }

      public final int getCodePoint(int startCP, int endCP) {
         int numCP = Math.min(endCP, 1114112) - Math.min(0, startCP);

         int cp;
         do {
            cp = startCP + this.rand.nextInt(numCP);
         } while(cp >= 55296 && cp <= 57343);

         return cp;
      }
   }
}
