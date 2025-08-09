package com.clearspring.analytics.hash;

public class Lookup3Hash {
   public static int lookup3(int[] k, int offset, int length, int initval) {
      int b;
      int c;
      int a = b = c = -559038737 + (length << 2) + initval;

      int i;
      for(i = offset; length > 3; i += 3) {
         int var8 = a + k[i];
         b += k[i + 1];
         c += k[i + 2];
         int var9 = var8 - c;
         int var10 = var9 ^ (c << 4 | c >>> -4);
         c += b;
         b -= var10;
         b ^= var10 << 6 | var10 >>> -6;
         int var11 = var10 + c;
         c -= b;
         c ^= b << 8 | b >>> -8;
         b += var11;
         int var12 = var11 - c;
         int var13 = var12 ^ (c << 16 | c >>> -16);
         c += b;
         b -= var13;
         b ^= var13 << 19 | var13 >>> -19;
         a = var13 + c;
         c -= b;
         c ^= b << 4 | b >>> -4;
         b += a;
         length -= 3;
      }

      switch (length) {
         case 3:
            c += k[i + 2];
         case 2:
            b += k[i + 1];
         case 1:
            a += k[i + 0];
            c ^= b;
            c -= b << 14 | b >>> -14;
            a ^= c;
            a -= c << 11 | c >>> -11;
            b ^= a;
            b -= a << 25 | a >>> -25;
            c ^= b;
            c -= b << 16 | b >>> -16;
            a ^= c;
            a -= c << 4 | c >>> -4;
            b ^= a;
            b -= a << 14 | a >>> -14;
            c ^= b;
            c -= b << 24 | b >>> -24;
         case 0:
         default:
            return c;
      }
   }

   public static int lookup3ycs(int[] k, int offset, int length, int initval) {
      return lookup3(k, offset, length, initval - (length << 2));
   }

   public static int lookup3ycs(CharSequence s, int start, int end, int initval) {
      int b;
      int c;
      int a = b = c = -559038737 + initval;
      int i = start;

      boolean mixed;
      for(mixed = true; i < end; mixed = true) {
         mixed = false;
         char ch = s.charAt(i++);
         a += Character.isHighSurrogate(ch) && i < end ? Character.toCodePoint(ch, s.charAt(i++)) : ch;
         if (i >= end) {
            break;
         }

         ch = s.charAt(i++);
         b += Character.isHighSurrogate(ch) && i < end ? Character.toCodePoint(ch, s.charAt(i++)) : ch;
         if (i >= end) {
            break;
         }

         ch = s.charAt(i++);
         c += Character.isHighSurrogate(ch) && i < end ? Character.toCodePoint(ch, s.charAt(i++)) : ch;
         if (i >= end) {
            break;
         }

         int var10 = a - c;
         int var11 = var10 ^ (c << 4 | c >>> -4);
         c += b;
         b -= var11;
         b ^= var11 << 6 | var11 >>> -6;
         int var12 = var11 + c;
         c -= b;
         c ^= b << 8 | b >>> -8;
         b += var12;
         int var13 = var12 - c;
         int var14 = var13 ^ (c << 16 | c >>> -16);
         c += b;
         b -= var14;
         b ^= var14 << 19 | var14 >>> -19;
         a = var14 + c;
         c -= b;
         c ^= b << 4 | b >>> -4;
         b += a;
      }

      if (!mixed) {
         c ^= b;
         c -= b << 14 | b >>> -14;
         a ^= c;
         a -= c << 11 | c >>> -11;
         b ^= a;
         b -= a << 25 | a >>> -25;
         c ^= b;
         c -= b << 16 | b >>> -16;
         a ^= c;
         a -= c << 4 | c >>> -4;
         b ^= a;
         b -= a << 14 | a >>> -14;
         c ^= b;
         c -= b << 24 | b >>> -24;
      }

      return c;
   }

   public static long lookup3ycs64(CharSequence s, int start, int end, long initval) {
      int b;
      int c;
      int a = b = c = -559038737 + (int)initval;
      c += (int)(initval >>> 32);
      int i = start;

      boolean mixed;
      for(mixed = true; i < end; mixed = true) {
         mixed = false;
         char ch = s.charAt(i++);
         a += Character.isHighSurrogate(ch) && i < end ? Character.toCodePoint(ch, s.charAt(i++)) : ch;
         if (i >= end) {
            break;
         }

         ch = s.charAt(i++);
         b += Character.isHighSurrogate(ch) && i < end ? Character.toCodePoint(ch, s.charAt(i++)) : ch;
         if (i >= end) {
            break;
         }

         ch = s.charAt(i++);
         c += Character.isHighSurrogate(ch) && i < end ? Character.toCodePoint(ch, s.charAt(i++)) : ch;
         if (i >= end) {
            break;
         }

         int var11 = a - c;
         int var12 = var11 ^ (c << 4 | c >>> -4);
         c += b;
         b -= var12;
         b ^= var12 << 6 | var12 >>> -6;
         int var13 = var12 + c;
         c -= b;
         c ^= b << 8 | b >>> -8;
         b += var13;
         int var14 = var13 - c;
         int var15 = var14 ^ (c << 16 | c >>> -16);
         c += b;
         b -= var15;
         b ^= var15 << 19 | var15 >>> -19;
         a = var15 + c;
         c -= b;
         c ^= b << 4 | b >>> -4;
         b += a;
      }

      if (!mixed) {
         c ^= b;
         c -= b << 14 | b >>> -14;
         a ^= c;
         a -= c << 11 | c >>> -11;
         int var25 = b ^ a;
         int var26 = var25 - (a << 25 | a >>> -25);
         c ^= var26;
         c -= var26 << 16 | var26 >>> -16;
         a ^= c;
         a -= c << 4 | c >>> -4;
         int var27 = var26 ^ a;
         b = var27 - (a << 14 | a >>> -14);
         c ^= b;
         c -= b << 24 | b >>> -24;
      }

      return (long)c + ((long)b << 32);
   }

   public static long lookup3ycs64(CharSequence s) {
      return lookup3ycs64(s, 0, s.length(), -1L);
   }
}
