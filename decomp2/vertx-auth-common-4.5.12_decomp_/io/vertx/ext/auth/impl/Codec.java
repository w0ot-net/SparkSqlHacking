package io.vertx.ext.auth.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class Codec {
   private static final byte[] BASE16;
   private static final int[] BASE16_LOOKUP;
   private static final char[] BASE32;
   private static final int[] BASE32_LOOKUP;
   private static final Base64.Encoder BASE64URL;
   private static final Base64.Decoder BASE64URL_DECODER;
   private static final Base64.Encoder BASE64;
   private static final Base64.Encoder BASE64_NOPADDING;
   private static final Base64.Decoder BASE64_DECODER;
   private static final Base64.Encoder BASE64MIME;
   private static final Base64.Decoder BASE64MIME_DECODER;

   private Codec() {
   }

   public static String base16Encode(byte[] bytes) {
      byte[] base16 = new byte[bytes.length * 2];

      for(int i = 0; i < bytes.length; ++i) {
         int v = bytes[i] & 255;
         base16[i * 2] = BASE16[v >>> 4];
         base16[i * 2 + 1] = BASE16[v & 15];
      }

      return new String(base16, StandardCharsets.ISO_8859_1);
   }

   public static byte[] base16Decode(String base16) {
      byte[] bytes = new byte[base16.length() / 2];
      int i = 0;

      while(i < base16.length()) {
         int lookup = base16.charAt(i) - 48;
         if (lookup >= 0 && lookup < BASE16_LOOKUP.length) {
            int high = BASE16_LOOKUP[lookup];
            if (high == 255) {
               throw new IllegalArgumentException("Invalid char: " + base16.charAt(i));
            }

            lookup = base16.charAt(i + 1) - 48;
            if (lookup >= 0 && lookup < BASE16_LOOKUP.length) {
               int low = BASE16_LOOKUP[lookup];
               if (low == 255) {
                  throw new IllegalArgumentException("Invalid char: " + base16.charAt(i + 1));
               }

               bytes[i / 2] = (byte)((high << 4) + low);
               i += 2;
               continue;
            }

            throw new IllegalArgumentException("Invalid char: " + base16.charAt(i + 1));
         }

         throw new IllegalArgumentException("Invalid char: " + base16.charAt(i));
      }

      return bytes;
   }

   public static String base32Encode(byte[] bytes) {
      int i = 0;
      int index = 0;

      StringBuilder base32;
      int digit;
      for(base32 = new StringBuilder((bytes.length + 7) * 8 / 5); i < bytes.length; base32.append(BASE32[digit])) {
         int currByte = bytes[i] >= 0 ? bytes[i] : bytes[i] + 256;
         if (index > 3) {
            int nextByte;
            if (i + 1 < bytes.length) {
               nextByte = bytes[i + 1] >= 0 ? bytes[i + 1] : bytes[i + 1] + 256;
            } else {
               nextByte = 0;
            }

            digit = currByte & 255 >> index;
            index = (index + 5) % 8;
            digit <<= index;
            digit |= nextByte >> 8 - index;
            ++i;
         } else {
            digit = currByte >> 8 - (index + 5) & 31;
            index = (index + 5) % 8;
            if (index == 0) {
               ++i;
            }
         }
      }

      return base32.toString();
   }

   public static byte[] base32Decode(String base32) {
      byte[] bytes = new byte[base32.length() * 5 / 8];
      int i = 0;
      int index = 0;
      int offset = 0;

      while(true) {
         label36: {
            if (i < base32.length()) {
               int lookup = base32.charAt(i) - 48;
               if (lookup < 0 || lookup >= BASE32_LOOKUP.length) {
                  throw new IllegalArgumentException("Invalid char: " + base32.charAt(i));
               }

               int digit = BASE32_LOOKUP[lookup];
               if (digit == 255) {
                  throw new IllegalArgumentException("Invalid char: " + base32.charAt(i));
               }

               if (index <= 3) {
                  index = (index + 5) % 8;
                  if (index != 0) {
                     bytes[offset] = (byte)(bytes[offset] | digit << 8 - index);
                     break label36;
                  }

                  bytes[offset] = (byte)(bytes[offset] | digit);
                  ++offset;
                  if (offset < bytes.length) {
                     break label36;
                  }
               } else {
                  index = (index + 5) % 8;
                  bytes[offset] = (byte)(bytes[offset] | digit >>> index);
                  ++offset;
                  if (offset < bytes.length) {
                     bytes[offset] = (byte)(bytes[offset] | digit << 8 - index);
                     break label36;
                  }
               }
            }

            return bytes;
         }

         ++i;
      }
   }

   public static String base64UrlEncode(byte[] bytes) {
      return BASE64URL.encodeToString(bytes);
   }

   public static byte[] base64UrlDecode(String base64) {
      return BASE64URL_DECODER.decode(base64);
   }

   public static byte[] base64UrlDecode(byte[] base64) {
      return BASE64URL_DECODER.decode(base64);
   }

   public static String base64Encode(byte[] bytes) {
      return BASE64.encodeToString(bytes);
   }

   public static String base64EncodeWithoutPadding(byte[] bytes) {
      return BASE64_NOPADDING.encodeToString(bytes);
   }

   public static byte[] base64Decode(String base64) {
      return BASE64_DECODER.decode(base64);
   }

   public static byte[] base64Decode(byte[] base64) {
      return BASE64_DECODER.decode(base64);
   }

   public static String base64MimeEncode(byte[] bytes) {
      return BASE64MIME.encodeToString(bytes);
   }

   public static byte[] base64MimeDecode(String base64) {
      return BASE64MIME_DECODER.decode(base64);
   }

   public static byte[] base64MimeDecode(byte[] base64) {
      return BASE64MIME_DECODER.decode(base64);
   }

   static {
      BASE16 = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);
      BASE16_LOOKUP = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 255, 255, 255, 255, 255, 255, 255, 10, 11, 12, 13, 14, 15, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 10, 11, 12, 13, 14, 15, 255};
      BASE32 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();
      BASE32_LOOKUP = new int[]{255, 255, 26, 27, 28, 29, 30, 31, 255, 255, 255, 255, 255, 255, 255, 255, 255, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 255, 255, 255, 255, 255, 255, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 255, 255, 255, 255, 255};
      BASE64URL = Base64.getUrlEncoder().withoutPadding();
      BASE64URL_DECODER = Base64.getUrlDecoder();
      BASE64 = Base64.getEncoder();
      BASE64_NOPADDING = Base64.getEncoder().withoutPadding();
      BASE64_DECODER = Base64.getDecoder();
      BASE64MIME = Base64.getMimeEncoder();
      BASE64MIME_DECODER = Base64.getMimeDecoder();
   }
}
