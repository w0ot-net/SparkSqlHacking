package org.apache.ivy.util;

public class HexEncoder {
   private static final char[] ALPHABET = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

   public static String encode(byte[] packet) {
      StringBuilder chars = new StringBuilder(16);

      for(byte bt : packet) {
         int highBits = (bt & 240) >> 4;
         int lowBits = bt & 15;
         chars.append(ALPHABET[highBits]).append(ALPHABET[lowBits]);
      }

      return chars.toString();
   }
}
