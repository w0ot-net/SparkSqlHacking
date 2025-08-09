package org.apache.arrow.memory.util;

import java.nio.ByteOrder;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BoundsChecking;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.memory.util.hash.SimpleHasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteFunctionHelpers {
   static final Logger logger = LoggerFactory.getLogger(ByteFunctionHelpers.class);
   private static final boolean LITTLE_ENDIAN;

   private ByteFunctionHelpers() {
   }

   public static int equal(ArrowBuf left, long lStart, long lEnd, ArrowBuf right, long rStart, long rEnd) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         left.checkBytes(lStart, lEnd);
         right.checkBytes(rStart, rEnd);
      }

      return memEqual(left.memoryAddress(), lStart, lEnd, right.memoryAddress(), rStart, rEnd);
   }

   private static int memEqual(long laddr, long lStart, long lEnd, long raddr, long rStart, long rEnd) {
      long n = lEnd - lStart;
      if (n != rEnd - rStart) {
         return 0;
      } else {
         long lPos = laddr + lStart;

         long rPos;
         for(rPos = raddr + rStart; n > 63L; n -= 64L) {
            for(int x = 0; x < 8; ++x) {
               long leftLong = MemoryUtil.getLong(lPos);
               long rightLong = MemoryUtil.getLong(rPos);
               if (leftLong != rightLong) {
                  return 0;
               }

               lPos += 8L;
               rPos += 8L;
            }
         }

         while(n > 7L) {
            long leftLong = MemoryUtil.getLong(lPos);
            long rightLong = MemoryUtil.getLong(rPos);
            if (leftLong != rightLong) {
               return 0;
            }

            lPos += 8L;
            rPos += 8L;
            n -= 8L;
         }

         if (n > 3L) {
            int leftInt = MemoryUtil.getInt(lPos);
            int rightInt = MemoryUtil.getInt(rPos);
            if (leftInt != rightInt) {
               return 0;
            }

            lPos += 4L;
            rPos += 4L;
            n -= 4L;
         }

         while(n-- != 0L) {
            byte leftByte = MemoryUtil.getByte(lPos);
            byte rightByte = MemoryUtil.getByte(rPos);
            if (leftByte != rightByte) {
               return 0;
            }

            ++lPos;
            ++rPos;
         }

         return 1;
      }
   }

   public static int compare(ArrowBuf left, long lStart, long lEnd, ArrowBuf right, long rStart, long rEnd) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         left.checkBytes(lStart, lEnd);
         right.checkBytes(rStart, rEnd);
      }

      return memcmp(left.memoryAddress(), lStart, lEnd, right.memoryAddress(), rStart, rEnd);
   }

   private static int memcmp(long laddr, long lStart, long lEnd, long raddr, long rStart, long rEnd) {
      long lLen = lEnd - lStart;
      long rLen = rEnd - rStart;
      long n = Math.min(rLen, lLen);
      long lPos = laddr + lStart;

      long rPos;
      for(rPos = raddr + rStart; n > 63L; n -= 64L) {
         for(int x = 0; x < 8; ++x) {
            long leftLong = MemoryUtil.getLong(lPos);
            long rightLong = MemoryUtil.getLong(rPos);
            if (leftLong != rightLong) {
               if (LITTLE_ENDIAN) {
                  return unsignedLongCompare(Long.reverseBytes(leftLong), Long.reverseBytes(rightLong));
               }

               return unsignedLongCompare(leftLong, rightLong);
            }

            lPos += 8L;
            rPos += 8L;
         }
      }

      while(n > 7L) {
         long leftLong = MemoryUtil.getLong(lPos);
         long rightLong = MemoryUtil.getLong(rPos);
         if (leftLong != rightLong) {
            if (LITTLE_ENDIAN) {
               return unsignedLongCompare(Long.reverseBytes(leftLong), Long.reverseBytes(rightLong));
            }

            return unsignedLongCompare(leftLong, rightLong);
         }

         lPos += 8L;
         rPos += 8L;
         n -= 8L;
      }

      if (n > 3L) {
         int leftInt = MemoryUtil.getInt(lPos);
         int rightInt = MemoryUtil.getInt(rPos);
         if (leftInt != rightInt) {
            if (LITTLE_ENDIAN) {
               return unsignedIntCompare(Integer.reverseBytes(leftInt), Integer.reverseBytes(rightInt));
            }

            return unsignedIntCompare(leftInt, rightInt);
         }

         lPos += 4L;
         rPos += 4L;
         n -= 4L;
      }

      while(n-- != 0L) {
         byte leftByte = MemoryUtil.getByte(lPos);
         byte rightByte = MemoryUtil.getByte(rPos);
         if (leftByte != rightByte) {
            return (leftByte & 255) - (rightByte & 255) > 0 ? 1 : -1;
         }

         ++lPos;
         ++rPos;
      }

      if (lLen == rLen) {
         return 0;
      } else {
         return lLen > rLen ? 1 : -1;
      }
   }

   public static int compare(ArrowBuf left, int lStart, int lEnd, byte[] right, int rStart, int rEnd) {
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         left.checkBytes((long)lStart, (long)lEnd);
      }

      return memcmp(left.memoryAddress(), lStart, lEnd, right, rStart, rEnd);
   }

   public static int unsignedLongCompare(long a, long b) {
      return Long.compare(a ^ Long.MIN_VALUE, b ^ Long.MIN_VALUE);
   }

   public static int unsignedIntCompare(int a, int b) {
      return Integer.compare(a ^ Integer.MIN_VALUE, b ^ Integer.MIN_VALUE);
   }

   private static int memcmp(long laddr, int lStart, int lEnd, byte[] right, int rStart, int rEnd) {
      int lLen = lEnd - lStart;
      int rLen = rEnd - rStart;
      int n = Math.min(rLen, lLen);
      long lPos = laddr + (long)lStart;

      int rPos;
      for(rPos = rStart; n > 7; n -= 8) {
         long leftLong = MemoryUtil.getLong(lPos);
         long rightLong = MemoryUtil.getLong(right, rPos);
         if (leftLong != rightLong) {
            if (LITTLE_ENDIAN) {
               return unsignedLongCompare(Long.reverseBytes(leftLong), Long.reverseBytes(rightLong));
            }

            return unsignedLongCompare(leftLong, rightLong);
         }

         lPos += 8L;
         rPos += 8;
      }

      if (n > 3) {
         int leftInt = MemoryUtil.getInt(lPos);
         int rightInt = MemoryUtil.getInt(right, rPos);
         if (leftInt != rightInt) {
            if (LITTLE_ENDIAN) {
               return unsignedIntCompare(Integer.reverseBytes(leftInt), Integer.reverseBytes(rightInt));
            }

            return unsignedIntCompare(leftInt, rightInt);
         }

         lPos += 4L;
         rPos += 4;
         n -= 4;
      }

      while(n-- != 0) {
         byte leftByte = MemoryUtil.getByte(lPos);
         byte rightByte = right[rPos];
         if (leftByte != rightByte) {
            return (leftByte & 255) - (rightByte & 255) > 0 ? 1 : -1;
         }

         ++lPos;
         ++rPos;
      }

      if (lLen == rLen) {
         return 0;
      } else {
         return lLen > rLen ? 1 : -1;
      }
   }

   public static int hash(ArrowBuf buf, long start, long end) {
      return hash(SimpleHasher.INSTANCE, buf, start, end);
   }

   public static final int hash(ArrowBufHasher hasher, ArrowBuf buf, long start, long end) {
      if (hasher == null) {
         hasher = SimpleHasher.INSTANCE;
      }

      return hasher.hashCode(buf, start, end - start);
   }

   public static int combineHash(int currentHash, int newHash) {
      return currentHash * 31 + newHash;
   }

   static {
      LITTLE_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
   }
}
