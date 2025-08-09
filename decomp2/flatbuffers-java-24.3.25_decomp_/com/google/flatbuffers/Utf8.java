package com.google.flatbuffers;

import java.nio.ByteBuffer;

public abstract class Utf8 {
   private static Utf8 DEFAULT;

   public abstract int encodedLength(CharSequence var1);

   public abstract void encodeUtf8(CharSequence var1, ByteBuffer var2);

   public abstract String decodeUtf8(ByteBuffer var1, int var2, int var3);

   public static Utf8 getDefault() {
      if (DEFAULT == null) {
         DEFAULT = new Utf8Safe();
      }

      return DEFAULT;
   }

   public static void setDefault(Utf8 instance) {
      DEFAULT = instance;
   }

   public static int encodeUtf8CodePoint(CharSequence in, int start, byte[] out) {
      assert out.length >= 4;

      int inLength = in.length();
      if (start >= inLength) {
         return 0;
      } else {
         char c = in.charAt(start);
         if (c < 128) {
            out[0] = (byte)c;
            return 1;
         } else if (c < 2048) {
            out[0] = (byte)(192 | c >>> 6);
            out[1] = (byte)(128 | 63 & c);
            return 2;
         } else if (c >= '\ud800' && '\udfff' >= c) {
            char low;
            if (start + 1 != inLength && Character.isSurrogatePair(c, low = in.charAt(start + 1))) {
               int codePoint = Character.toCodePoint(c, low);
               out[0] = (byte)(240 | codePoint >>> 18);
               out[1] = (byte)(128 | 63 & codePoint >>> 12);
               out[2] = (byte)(128 | 63 & codePoint >>> 6);
               out[3] = (byte)(128 | 63 & codePoint);
               return 4;
            } else {
               throw new UnpairedSurrogateException(start, inLength);
            }
         } else {
            out[0] = (byte)(224 | c >>> 12);
            out[1] = (byte)(128 | 63 & c >>> 6);
            out[2] = (byte)(128 | 63 & c);
            return 3;
         }
      }
   }

   static class DecodeUtil {
      static boolean isOneByte(byte b) {
         return b >= 0;
      }

      static boolean isTwoBytes(byte b) {
         return b < -32;
      }

      static boolean isThreeBytes(byte b) {
         return b < -16;
      }

      static void handleOneByte(byte byte1, char[] resultArr, int resultPos) {
         resultArr[resultPos] = (char)byte1;
      }

      static void handleTwoBytes(byte byte1, byte byte2, char[] resultArr, int resultPos) throws IllegalArgumentException {
         if (byte1 < -62) {
            throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
         } else if (isNotTrailingByte(byte2)) {
            throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
         } else {
            resultArr[resultPos] = (char)((byte1 & 31) << 6 | trailingByteValue(byte2));
         }
      }

      static void handleThreeBytes(byte byte1, byte byte2, byte byte3, char[] resultArr, int resultPos) throws IllegalArgumentException {
         if (!isNotTrailingByte(byte2) && (byte1 != -32 || byte2 >= -96) && (byte1 != -19 || byte2 < -96) && !isNotTrailingByte(byte3)) {
            resultArr[resultPos] = (char)((byte1 & 15) << 12 | trailingByteValue(byte2) << 6 | trailingByteValue(byte3));
         } else {
            throw new IllegalArgumentException("Invalid UTF-8");
         }
      }

      static void handleFourBytes(byte byte1, byte byte2, byte byte3, byte byte4, char[] resultArr, int resultPos) throws IllegalArgumentException {
         if (!isNotTrailingByte(byte2) && (byte1 << 28) + (byte2 - -112) >> 30 == 0 && !isNotTrailingByte(byte3) && !isNotTrailingByte(byte4)) {
            int codepoint = (byte1 & 7) << 18 | trailingByteValue(byte2) << 12 | trailingByteValue(byte3) << 6 | trailingByteValue(byte4);
            resultArr[resultPos] = highSurrogate(codepoint);
            resultArr[resultPos + 1] = lowSurrogate(codepoint);
         } else {
            throw new IllegalArgumentException("Invalid UTF-8");
         }
      }

      private static boolean isNotTrailingByte(byte b) {
         return b > -65;
      }

      private static int trailingByteValue(byte b) {
         return b & 63;
      }

      private static char highSurrogate(int codePoint) {
         return (char)('ퟀ' + (codePoint >>> 10));
      }

      private static char lowSurrogate(int codePoint) {
         return (char)('\udc00' + (codePoint & 1023));
      }
   }

   static class UnpairedSurrogateException extends IllegalArgumentException {
      UnpairedSurrogateException(int index, int length) {
         super("Unpaired surrogate at index " + index + " of " + length);
      }
   }
}
