package io.jsonwebtoken.io;

import java.util.Arrays;

final class Base64 {
   private static final char[] BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
   private static final char[] BASE64URL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
   private static final int[] BASE64_IALPHABET = new int[256];
   private static final int[] BASE64URL_IALPHABET = new int[256];
   private static final int IALPHABET_MAX_INDEX;
   static final Base64 DEFAULT;
   static final Base64 URL_SAFE;
   private final boolean urlsafe;
   private final char[] ALPHABET;
   private final int[] IALPHABET;

   private Base64(boolean urlsafe) {
      this.urlsafe = urlsafe;
      this.ALPHABET = urlsafe ? BASE64URL_ALPHABET : BASE64_ALPHABET;
      this.IALPHABET = urlsafe ? BASE64URL_IALPHABET : BASE64_IALPHABET;
   }

   private String getName() {
      return this.urlsafe ? "base64url" : "base64";
   }

   private char[] encodeToChar(byte[] sArr, boolean lineSep) {
      int sLen = sArr != null ? sArr.length : 0;
      if (sLen == 0) {
         return new char[0];
      } else {
         int eLen = sLen / 3 * 3;
         int left = sLen - eLen;
         int cCnt = (sLen - 1) / 3 + 1 << 2;
         int dLen = cCnt + (lineSep ? (cCnt - 1) / 76 << 1 : 0);
         int padCount = 0;
         if (left == 2) {
            padCount = 1;
         } else if (left == 1) {
            padCount = 2;
         }

         char[] dArr = new char[this.urlsafe ? dLen - padCount : dLen];
         int s = 0;
         int d = 0;
         int cc = 0;

         while(s < eLen) {
            int i = (sArr[s++] & 255) << 16 | (sArr[s++] & 255) << 8 | sArr[s++] & 255;
            dArr[d++] = this.ALPHABET[i >>> 18 & 63];
            dArr[d++] = this.ALPHABET[i >>> 12 & 63];
            dArr[d++] = this.ALPHABET[i >>> 6 & 63];
            dArr[d++] = this.ALPHABET[i & 63];
            if (lineSep) {
               ++cc;
               if (cc == 19 && d < dLen - 2) {
                  dArr[d++] = '\r';
                  dArr[d++] = '\n';
                  cc = 0;
               }
            }
         }

         if (left > 0) {
            s = (sArr[eLen] & 255) << 10 | (left == 2 ? (sArr[sLen - 1] & 255) << 2 : 0);
            dArr[dLen - 4] = this.ALPHABET[s >> 12];
            dArr[dLen - 3] = this.ALPHABET[s >>> 6 & 63];
            if (left == 2) {
               dArr[dLen - 2] = this.ALPHABET[s & 63];
            } else if (!this.urlsafe) {
               dArr[dLen - 2] = '=';
            }

            if (!this.urlsafe) {
               dArr[dLen - 1] = '=';
            }
         }

         return dArr;
      }
   }

   private int ctoi(char c) {
      int i = c > IALPHABET_MAX_INDEX ? -1 : this.IALPHABET[c];
      if (i < 0) {
         String msg = "Illegal " + this.getName() + " character: '" + c + "'";
         throw new DecodingException(msg);
      } else {
         return i;
      }
   }

   byte[] decodeFast(CharSequence seq) throws DecodingException {
      int sLen = seq != null ? seq.length() : 0;
      if (sLen == 0) {
         return new byte[0];
      } else {
         int sIx = 0;

         int eIx;
         for(eIx = sLen - 1; sIx < eIx && this.IALPHABET[seq.charAt(sIx)] < 0; ++sIx) {
         }

         while(eIx > 0 && this.IALPHABET[seq.charAt(eIx)] < 0) {
            --eIx;
         }

         int pad = seq.charAt(eIx) == '=' ? (seq.charAt(eIx - 1) == '=' ? 2 : 1) : 0;
         int cCnt = eIx - sIx + 1;
         int sepCnt = sLen > 76 ? (seq.charAt(76) == '\r' ? cCnt / 78 : 0) << 1 : 0;
         int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
         byte[] dArr = new byte[len];
         int d = 0;
         int cc = 0;
         int eLen = len / 3 * 3;

         while(d < eLen) {
            int i = this.ctoi(seq.charAt(sIx++)) << 18 | this.ctoi(seq.charAt(sIx++)) << 12 | this.ctoi(seq.charAt(sIx++)) << 6 | this.ctoi(seq.charAt(sIx++));
            dArr[d++] = (byte)(i >> 16);
            dArr[d++] = (byte)(i >> 8);
            dArr[d++] = (byte)i;
            if (sepCnt > 0) {
               ++cc;
               if (cc == 19) {
                  sIx += 2;
                  cc = 0;
               }
            }
         }

         if (d < len) {
            cc = 0;

            for(int j = 0; sIx <= eIx - pad; ++j) {
               cc |= this.ctoi(seq.charAt(sIx++)) << 18 - j * 6;
            }

            for(int r = 16; d < len; r -= 8) {
               dArr[d++] = (byte)(cc >> r);
            }
         }

         return dArr;
      }
   }

   String encodeToString(byte[] sArr, boolean lineSep) {
      return new String(this.encodeToChar(sArr, lineSep));
   }

   static {
      IALPHABET_MAX_INDEX = BASE64_IALPHABET.length - 1;
      Arrays.fill(BASE64_IALPHABET, -1);
      System.arraycopy(BASE64_IALPHABET, 0, BASE64URL_IALPHABET, 0, BASE64_IALPHABET.length);
      int i = 0;

      for(int iS = BASE64_ALPHABET.length; i < iS; BASE64URL_IALPHABET[BASE64URL_ALPHABET[i]] = i++) {
         BASE64_IALPHABET[BASE64_ALPHABET[i]] = i;
      }

      BASE64_IALPHABET[61] = 0;
      BASE64URL_IALPHABET[61] = 0;
      DEFAULT = new Base64(false);
      URL_SAFE = new Base64(true);
   }
}
