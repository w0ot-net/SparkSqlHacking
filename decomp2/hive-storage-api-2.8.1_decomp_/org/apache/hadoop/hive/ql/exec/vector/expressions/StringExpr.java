package org.apache.hadoop.hive.ql.exec.vector.expressions;

import java.util.Arrays;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;

public class StringExpr {
   public static int compare(byte[] arg1, int start1, int len1, byte[] arg2, int start2, int len2) {
      for(int i = 0; i < len1 && i < len2; ++i) {
         int b1 = arg1[i + start1] & 255;
         int b2 = arg2[i + start2] & 255;
         if (b1 != b2) {
            return b1 - b2;
         }
      }

      return len1 - len2;
   }

   public static boolean equal(byte[] arg1, int start1, int len1, byte[] arg2, int start2, int len2) {
      if (len1 != len2) {
         return false;
      } else if (len1 == 0) {
         return true;
      } else if (arg1[start1] == arg2[start2] && arg1[start1 + len1 - 1] == arg2[start2 + len2 - 1]) {
         if (len1 == len2) {
            int step = 8;
            int remainder = len1 % 8;
            int wlen = len1 - remainder;

            for(int i = wlen; i < len1; ++i) {
               if (arg1[start1 + i] != arg2[start2 + i]) {
                  return false;
               }
            }

            for(int i = 0; i < wlen; i += 8) {
               int s1 = start1 + i;
               int s2 = start2 + i;
               boolean neq = false;

               for(int j = 0; j < 8; ++j) {
                  neq = arg1[s1 + j] != arg2[s2 + j] || neq;
               }

               if (neq) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static int characterCount(byte[] bytes) {
      int end = bytes.length;
      int j = 0;

      int charCount;
      for(charCount = 0; j < end; ++j) {
         if ((bytes[j] & 192) != 128) {
            ++charCount;
         }
      }

      return charCount;
   }

   public static int characterCount(byte[] bytes, int start, int length) {
      int end = start + length;
      int j = start;

      int charCount;
      for(charCount = 0; j < end; ++j) {
         if ((bytes[j] & 192) != 128) {
            ++charCount;
         }
      }

      return charCount;
   }

   public static void padRight(BytesColumnVector outV, int i, byte[] bytes, int start, int length, int maxCharacterLength) {
      int characterLength = characterCount(bytes, start, length);
      int blankPadLength = Math.max(maxCharacterLength - characterLength, 0);
      int resultLength = length + blankPadLength;
      outV.ensureValPreallocated(resultLength);
      byte[] resultBytes = outV.getValPreallocatedBytes();
      int resultStart = outV.getValPreallocatedStart();
      System.arraycopy(bytes, start, resultBytes, resultStart, length);
      int padEnd = resultStart + resultLength;

      for(int p = resultStart + length; p < padEnd; ++p) {
         resultBytes[p] = 32;
      }

      outV.setValPreallocated(i, resultLength);
   }

   public static byte[] padRight(byte[] bytes, int start, int length, int maxCharacterLength) {
      int characterLength = characterCount(bytes, start, length);
      int blankPadLength = Math.max(maxCharacterLength - characterLength, 0);
      int resultLength = length + blankPadLength;
      byte[] resultBytes = new byte[resultLength];
      int resultStart = 0;
      System.arraycopy(bytes, start, resultBytes, 0, length);
      int padEnd = 0 + resultLength;

      for(int p = 0 + length; p < padEnd; ++p) {
         resultBytes[p] = 32;
      }

      return resultBytes;
   }

   public static void assign(BytesColumnVector outV, int i, byte[] bytes, int start, int length) {
      outV.setVal(i, bytes, start, length);
   }

   public static int rightTrim(byte[] bytes, int start, int length) {
      int j;
      for(j = start + length - 1; j >= start && bytes[j] == 32; --j) {
      }

      return j - start + 1;
   }

   public static void rightTrim(BytesColumnVector outV, int i, byte[] bytes, int start, int length) {
      int j;
      for(j = start + length - 1; j >= start && bytes[j] == 32; --j) {
      }

      outV.setVal(i, bytes, start, j - start + 1);
   }

   public static int truncate(byte[] bytes, int start, int length, int maxLength) {
      if (length <= maxLength) {
         return length;
      } else {
         int end = start + length;
         int j = start;

         for(int charCount = 0; j < end; ++j) {
            if ((bytes[j] & 192) != 128) {
               if (charCount == maxLength) {
                  break;
               }

               ++charCount;
            }
         }

         return j - start;
      }
   }

   public static void truncate(BytesColumnVector outV, int i, byte[] bytes, int start, int length, int maxLength) {
      int newLength = truncate(bytes, start, length, maxLength);
      outV.setVal(i, bytes, start, newLength);
   }

   public static byte[] truncateScalar(byte[] bytes, int maxLength) {
      int newLength = truncate(bytes, 0, bytes.length, maxLength);
      return newLength == bytes.length ? bytes : Arrays.copyOf(bytes, newLength);
   }

   public static int rightTrimAndTruncate(byte[] bytes, int start, int length, int maxLength) {
      int newLength = truncate(bytes, start, length, maxLength);

      for(int i = start + newLength - 1; i >= start; --i) {
         if (bytes[i] != 32) {
            return i - start + 1;
         }
      }

      return 0;
   }

   public static void rightTrimAndTruncate(BytesColumnVector outV, int i, byte[] bytes, int start, int length, int maxLength) {
      int newLength = rightTrimAndTruncate(bytes, start, length, maxLength);
      outV.setVal(i, bytes, start, newLength);
   }

   public static byte[] rightTrimAndTruncateScalar(byte[] bytes, int maxLength) {
      int newLength = rightTrimAndTruncate(bytes, 0, bytes.length, maxLength);
      return newLength == bytes.length ? bytes : Arrays.copyOf(bytes, newLength);
   }

   public static Finder compile(byte[] pattern) {
      return new BoyerMooreHorspool(pattern);
   }

   private static class BoyerMooreHorspool implements Finder {
      private static final int MAX_BYTE = 255;
      private final long[] shift = new long[255];
      private final byte[] pattern;
      private final int plen;

      public BoyerMooreHorspool(byte[] pattern) {
         this.pattern = pattern;
         this.plen = pattern.length;
         Arrays.fill(this.shift, (long)this.plen);

         for(int i = 0; i < this.plen - 1; ++i) {
            this.shift[pattern[i] & 255] = (long)(this.plen - i - 1);
         }

      }

      public int find(byte[] input, int start, int len) {
         if (this.pattern.length == 0) {
            return 0;
         } else {
            int end = start + len;
            int next = start + this.plen - 1;
            int plen = this.plen;

            for(byte[] pattern = this.pattern; next < end; next = (int)((long)next + this.shift[input[next] & 255])) {
               int s_tmp = next;

               for(int p_tmp = plen - 1; input[s_tmp] == pattern[p_tmp]; --s_tmp) {
                  --p_tmp;
                  if (p_tmp < 0) {
                     return s_tmp;
                  }
               }
            }

            return -1;
         }
      }
   }

   public interface Finder {
      int find(byte[] var1, int var2, int var3);
   }
}
