package jodd.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import jodd.JoddCore;

public class Base64 {
   private static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
   private static final int[] INV = new int[256];

   public static char[] encodeToChar(byte[] arr, boolean lineSeparator) {
      int len = arr != null ? arr.length : 0;
      if (len == 0) {
         return new char[0];
      } else {
         int evenlen = len / 3 * 3;
         int cnt = (len - 1) / 3 + 1 << 2;
         int destLen = cnt + (lineSeparator ? (cnt - 1) / 76 << 1 : 0);
         char[] dest = new char[destLen];
         int s = 0;
         int d = 0;
         int cc = 0;

         while(s < evenlen) {
            int i = (arr[s++] & 255) << 16 | (arr[s++] & 255) << 8 | arr[s++] & 255;
            dest[d++] = CHARS[i >>> 18 & 63];
            dest[d++] = CHARS[i >>> 12 & 63];
            dest[d++] = CHARS[i >>> 6 & 63];
            dest[d++] = CHARS[i & 63];
            if (lineSeparator) {
               ++cc;
               if (cc == 19 && d < destLen - 2) {
                  dest[d++] = '\r';
                  dest[d++] = '\n';
                  cc = 0;
               }
            }
         }

         s = len - evenlen;
         if (s > 0) {
            d = (arr[evenlen] & 255) << 10 | (s == 2 ? (arr[len - 1] & 255) << 2 : 0);
            dest[destLen - 4] = CHARS[d >> 12];
            dest[destLen - 3] = CHARS[d >>> 6 & 63];
            dest[destLen - 2] = s == 2 ? CHARS[d & 63] : 61;
            dest[destLen - 1] = '=';
         }

         return dest;
      }
   }

   public byte[] decode(char[] arr) {
      int length = arr.length;
      if (length == 0) {
         return new byte[0];
      } else {
         int sndx = 0;
         int endx = length - 1;
         int pad = arr[endx] == '=' ? (arr[endx - 1] == '=' ? 2 : 1) : 0;
         int cnt = endx - sndx + 1;
         int sepCnt = length > 76 ? (arr[76] == '\r' ? cnt / 78 : 0) << 1 : 0;
         int len = ((cnt - sepCnt) * 6 >> 3) - pad;
         byte[] dest = new byte[len];
         int d = 0;
         int cc = 0;
         int eLen = len / 3 * 3;

         while(d < eLen) {
            int i = INV[arr[sndx++]] << 18 | INV[arr[sndx++]] << 12 | INV[arr[sndx++]] << 6 | INV[arr[sndx++]];
            dest[d++] = (byte)(i >> 16);
            dest[d++] = (byte)(i >> 8);
            dest[d++] = (byte)i;
            if (sepCnt > 0) {
               ++cc;
               if (cc == 19) {
                  sndx += 2;
                  cc = 0;
               }
            }
         }

         if (d < len) {
            cc = 0;

            for(int j = 0; sndx <= endx - pad; ++j) {
               cc |= INV[arr[sndx++]] << 18 - j * 6;
            }

            for(int r = 16; d < len; r -= 8) {
               dest[d++] = (byte)(cc >> r);
            }
         }

         return dest;
      }
   }

   public static byte[] encodeToByte(String s) {
      try {
         return encodeToByte(s.getBytes(JoddCore.encoding), false);
      } catch (UnsupportedEncodingException var2) {
         return null;
      }
   }

   public static byte[] encodeToByte(String s, boolean lineSep) {
      try {
         return encodeToByte(s.getBytes(JoddCore.encoding), lineSep);
      } catch (UnsupportedEncodingException var3) {
         return null;
      }
   }

   public static byte[] encodeToByte(byte[] arr) {
      return encodeToByte(arr, false);
   }

   public static byte[] encodeToByte(byte[] arr, boolean lineSep) {
      int len = arr != null ? arr.length : 0;
      if (len == 0) {
         return new byte[0];
      } else {
         int evenlen = len / 3 * 3;
         int cnt = (len - 1) / 3 + 1 << 2;
         int destlen = cnt + (lineSep ? (cnt - 1) / 76 << 1 : 0);
         byte[] dest = new byte[destlen];
         int s = 0;
         int d = 0;
         int cc = 0;

         while(s < evenlen) {
            int i = (arr[s++] & 255) << 16 | (arr[s++] & 255) << 8 | arr[s++] & 255;
            dest[d++] = (byte)CHARS[i >>> 18 & 63];
            dest[d++] = (byte)CHARS[i >>> 12 & 63];
            dest[d++] = (byte)CHARS[i >>> 6 & 63];
            dest[d++] = (byte)CHARS[i & 63];
            if (lineSep) {
               ++cc;
               if (cc == 19 && d < destlen - 2) {
                  dest[d++] = 13;
                  dest[d++] = 10;
                  cc = 0;
               }
            }
         }

         s = len - evenlen;
         if (s > 0) {
            d = (arr[evenlen] & 255) << 10 | (s == 2 ? (arr[len - 1] & 255) << 2 : 0);
            dest[destlen - 4] = (byte)CHARS[d >> 12];
            dest[destlen - 3] = (byte)CHARS[d >>> 6 & 63];
            dest[destlen - 2] = s == 2 ? (byte)CHARS[d & 63] : 61;
            dest[destlen - 1] = 61;
         }

         return dest;
      }
   }

   public static String decodeToString(byte[] arr) {
      try {
         return new String(decode(arr), JoddCore.encoding);
      } catch (UnsupportedEncodingException var2) {
         return null;
      }
   }

   public static byte[] decode(byte[] arr) {
      int length = arr.length;
      if (length == 0) {
         return new byte[0];
      } else {
         int sndx = 0;
         int endx = length - 1;
         int pad = arr[endx] == 61 ? (arr[endx - 1] == 61 ? 2 : 1) : 0;
         int cnt = endx - sndx + 1;
         int sepCnt = length > 76 ? (arr[76] == 13 ? cnt / 78 : 0) << 1 : 0;
         int len = ((cnt - sepCnt) * 6 >> 3) - pad;
         byte[] dest = new byte[len];
         int d = 0;
         int cc = 0;
         int eLen = len / 3 * 3;

         while(d < eLen) {
            int i = INV[arr[sndx++]] << 18 | INV[arr[sndx++]] << 12 | INV[arr[sndx++]] << 6 | INV[arr[sndx++]];
            dest[d++] = (byte)(i >> 16);
            dest[d++] = (byte)(i >> 8);
            dest[d++] = (byte)i;
            if (sepCnt > 0) {
               ++cc;
               if (cc == 19) {
                  sndx += 2;
                  cc = 0;
               }
            }
         }

         if (d < len) {
            cc = 0;

            for(int j = 0; sndx <= endx - pad; ++j) {
               cc |= INV[arr[sndx++]] << 18 - j * 6;
            }

            for(int r = 16; d < len; r -= 8) {
               dest[d++] = (byte)(cc >> r);
            }
         }

         return dest;
      }
   }

   public static String encodeToString(String s) {
      try {
         return new String(encodeToChar(s.getBytes(JoddCore.encoding), false));
      } catch (UnsupportedEncodingException var2) {
         return null;
      }
   }

   public static String encodeToString(String s, boolean lineSep) {
      try {
         return new String(encodeToChar(s.getBytes(JoddCore.encoding), lineSep));
      } catch (UnsupportedEncodingException var3) {
         return null;
      }
   }

   public static String encodeToString(byte[] arr) {
      return new String(encodeToChar(arr, false));
   }

   public static String encodeToString(byte[] arr, boolean lineSep) {
      return new String(encodeToChar(arr, lineSep));
   }

   public static String decodeToString(String s) {
      try {
         return new String(decode(s), JoddCore.encoding);
      } catch (UnsupportedEncodingException var2) {
         return null;
      }
   }

   public static byte[] decode(String s) {
      int length = s.length();
      if (length == 0) {
         return new byte[0];
      } else {
         int sndx = 0;
         int endx = length - 1;
         int pad = s.charAt(endx) == '=' ? (s.charAt(endx - 1) == '=' ? 2 : 1) : 0;
         int cnt = endx - sndx + 1;
         int sepCnt = length > 76 ? (s.charAt(76) == '\r' ? cnt / 78 : 0) << 1 : 0;
         int len = ((cnt - sepCnt) * 6 >> 3) - pad;
         byte[] dest = new byte[len];
         int d = 0;
         int cc = 0;
         int eLen = len / 3 * 3;

         while(d < eLen) {
            int i = INV[s.charAt(sndx++)] << 18 | INV[s.charAt(sndx++)] << 12 | INV[s.charAt(sndx++)] << 6 | INV[s.charAt(sndx++)];
            dest[d++] = (byte)(i >> 16);
            dest[d++] = (byte)(i >> 8);
            dest[d++] = (byte)i;
            if (sepCnt > 0) {
               ++cc;
               if (cc == 19) {
                  sndx += 2;
                  cc = 0;
               }
            }
         }

         if (d < len) {
            cc = 0;

            for(int j = 0; sndx <= endx - pad; ++j) {
               cc |= INV[s.charAt(sndx++)] << 18 - j * 6;
            }

            for(int r = 16; d < len; r -= 8) {
               dest[d++] = (byte)(cc >> r);
            }
         }

         return dest;
      }
   }

   static {
      Arrays.fill(INV, -1);
      int i = 0;

      for(int iS = CHARS.length; i < iS; INV[CHARS[i]] = i++) {
      }

      INV[61] = 0;
   }
}
