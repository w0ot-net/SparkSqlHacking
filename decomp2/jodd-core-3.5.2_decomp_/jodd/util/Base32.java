package jodd.util;

public class Base32 {
   private static final String ERR_CANONICAL_LEN = "Invalid Base32 string length";
   private static final String ERR_CANONICAL_END = "Invalid end bits of Base32 string";
   private static final String ERR_INVALID_CHARS = "Invalid character in Base32 string";
   private static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();
   private static final byte[] LOOKUP = new byte[]{26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

   public static String encode(byte[] bytes) {
      StringBuilder base32 = new StringBuilder((bytes.length * 8 + 4) / 5);
      int i = 0;

      while(i < bytes.length) {
         int currByte = bytes[i++] & 255;
         base32.append(CHARS[currByte >> 3]);
         int digit = (currByte & 7) << 2;
         if (i >= bytes.length) {
            base32.append(CHARS[digit]);
            break;
         }

         currByte = bytes[i++] & 255;
         base32.append(CHARS[digit | currByte >> 6]);
         base32.append(CHARS[currByte >> 1 & 31]);
         digit = (currByte & 1) << 4;
         if (i >= bytes.length) {
            base32.append(CHARS[digit]);
            break;
         }

         currByte = bytes[i++] & 255;
         base32.append(CHARS[digit | currByte >> 4]);
         digit = (currByte & 15) << 1;
         if (i >= bytes.length) {
            base32.append(CHARS[digit]);
            break;
         }

         currByte = bytes[i++] & 255;
         base32.append(CHARS[digit | currByte >> 7]);
         base32.append(CHARS[currByte >> 2 & 31]);
         digit = (currByte & 3) << 3;
         if (i >= bytes.length) {
            base32.append(CHARS[digit]);
            break;
         }

         currByte = bytes[i++] & 255;
         base32.append(CHARS[digit | currByte >> 5]);
         base32.append(CHARS[currByte & 31]);
      }

      return base32.toString();
   }

   public static byte[] decode(String base32) throws IllegalArgumentException {
      switch (base32.length() % 8) {
         case 1:
         case 3:
         case 6:
            throw new IllegalArgumentException("Invalid Base32 string length");
         default:
            byte[] bytes = new byte[base32.length() * 5 / 8];
            int offset = 0;
            int i = 0;

            while(true) {
               if (i < base32.length()) {
                  int lookup = base32.charAt(i++) - 50;
                  if (lookup < 0 || lookup >= LOOKUP.length) {
                     throw new IllegalArgumentException("Invalid character in Base32 string");
                  }

                  byte digit = LOOKUP[lookup];
                  if (digit == -1) {
                     throw new IllegalArgumentException("Invalid character in Base32 string");
                  }

                  byte nextByte = (byte)(digit << 3);
                  lookup = base32.charAt(i++) - 50;
                  if (lookup < 0 || lookup >= LOOKUP.length) {
                     throw new IllegalArgumentException("Invalid character in Base32 string");
                  }

                  digit = LOOKUP[lookup];
                  if (digit == -1) {
                     throw new IllegalArgumentException("Invalid character in Base32 string");
                  }

                  bytes[offset++] = (byte)(nextByte | digit >> 2);
                  nextByte = (byte)((digit & 3) << 6);
                  if (i >= base32.length()) {
                     if (nextByte != 0) {
                        throw new IllegalArgumentException("Invalid end bits of Base32 string");
                     }
                  } else {
                     lookup = base32.charAt(i++) - 50;
                     if (lookup < 0 || lookup >= LOOKUP.length) {
                        throw new IllegalArgumentException("Invalid character in Base32 string");
                     }

                     digit = LOOKUP[lookup];
                     if (digit == -1) {
                        throw new IllegalArgumentException("Invalid character in Base32 string");
                     }

                     nextByte = (byte)(nextByte | (byte)(digit << 1));
                     lookup = base32.charAt(i++) - 50;
                     if (lookup < 0 || lookup >= LOOKUP.length) {
                        throw new IllegalArgumentException("Invalid character in Base32 string");
                     }

                     digit = LOOKUP[lookup];
                     if (digit == -1) {
                        throw new IllegalArgumentException("Invalid character in Base32 string");
                     }

                     bytes[offset++] = (byte)(nextByte | digit >> 4);
                     nextByte = (byte)((digit & 15) << 4);
                     if (i >= base32.length()) {
                        if (nextByte != 0) {
                           throw new IllegalArgumentException("Invalid end bits of Base32 string");
                        }
                     } else {
                        lookup = base32.charAt(i++) - 50;
                        if (lookup < 0 || lookup >= LOOKUP.length) {
                           throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        digit = LOOKUP[lookup];
                        if (digit == -1) {
                           throw new IllegalArgumentException("Invalid character in Base32 string");
                        }

                        bytes[offset++] = (byte)(nextByte | digit >> 1);
                        nextByte = (byte)((digit & 1) << 7);
                        if (i >= base32.length()) {
                           if (nextByte != 0) {
                              throw new IllegalArgumentException("Invalid end bits of Base32 string");
                           }
                        } else {
                           lookup = base32.charAt(i++) - 50;
                           if (lookup < 0 || lookup >= LOOKUP.length) {
                              throw new IllegalArgumentException("Invalid character in Base32 string");
                           }

                           digit = LOOKUP[lookup];
                           if (digit == -1) {
                              throw new IllegalArgumentException("Invalid character in Base32 string");
                           }

                           nextByte = (byte)(nextByte | (byte)(digit << 2));
                           lookup = base32.charAt(i++) - 50;
                           if (lookup < 0 || lookup >= LOOKUP.length) {
                              throw new IllegalArgumentException("Invalid character in Base32 string");
                           }

                           digit = LOOKUP[lookup];
                           if (digit == -1) {
                              throw new IllegalArgumentException("Invalid character in Base32 string");
                           }

                           bytes[offset++] = (byte)(nextByte | digit >> 3);
                           nextByte = (byte)((digit & 7) << 5);
                           if (i < base32.length()) {
                              lookup = base32.charAt(i++) - 50;
                              if (lookup >= 0 && lookup < LOOKUP.length) {
                                 digit = LOOKUP[lookup];
                                 if (digit == -1) {
                                    throw new IllegalArgumentException("Invalid character in Base32 string");
                                 }

                                 bytes[offset++] = (byte)(nextByte | digit);
                                 continue;
                              }

                              throw new IllegalArgumentException("Invalid character in Base32 string");
                           }

                           if (nextByte != 0) {
                              throw new IllegalArgumentException("Invalid end bits of Base32 string");
                           }
                        }
                     }
                  }
               }

               return bytes;
            }
      }
   }
}
