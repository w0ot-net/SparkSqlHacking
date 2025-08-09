package org.apache.orc.util;

import java.util.Random;
import org.apache.hadoop.hive.ql.exec.vector.expressions.StringExpr;

public class CuckooSetBytes {
   private byte[][] t1;
   private byte[][] t2;
   private byte[][] prev1 = null;
   private byte[][] prev2 = null;
   private int n;
   private static final double PADDING_FACTOR = (double)2.5F;
   private int salt = 0;
   private final Random gen = new Random(676983475L);
   private int rehashCount = 0;
   private static final long INT_MASK = 4294967295L;
   private static final long BYTE_MASK = 255L;
   private int maxLen;
   private int minLen = Integer.MAX_VALUE;
   static final int[] primes = new int[]{7, 13, 17, 23, 31, 53, 67, 89, 127, 269, 571, 1019, 2089, 4507, 8263, 16361, 32327, 65437, 131111, 258887, 525961, 999983, 2158909, 4074073, 8321801, 15485863, 32452867, 67867967, 122949829, 256203221, 553105253, 982451653, 1645333507, Integer.MAX_VALUE};

   public CuckooSetBytes(int expectedSize) {
      this.n = (int)((double)expectedSize * (double)2.5F / (double)2.0F);

      for(int i = 0; i != primes.length; ++i) {
         if (this.n <= primes[i]) {
            this.n = primes[i];
            break;
         }
      }

      this.t1 = new byte[this.n][];
      this.t2 = new byte[this.n][];
      this.updateHashSalt();
   }

   public boolean lookup(byte[] b, int start, int len) {
      if (len >= this.minLen && len <= this.maxLen) {
         return entryEqual(this.t1, this.h1(b, start, len), b, start, len) || entryEqual(this.t2, this.h2(b, start, len), b, start, len);
      } else {
         return false;
      }
   }

   private static boolean entryEqual(byte[][] t, int hash, byte[] b, int start, int len) {
      return t[hash] != null && StringExpr.equal(t[hash], 0, t[hash].length, b, start, len);
   }

   public void insert(byte[] x) {
      if (!this.lookup(x, 0, x.length)) {
         this.minLen = Math.min(this.minLen, x.length);
         this.maxLen = Math.max(this.maxLen, x.length);

         for(int i = 0; i != this.n; ++i) {
            int hash1 = this.h1(x, 0, x.length);
            if (this.t1[hash1] == null) {
               this.t1[hash1] = x;
               return;
            }

            byte[] temp = this.t1[hash1];
            this.t1[hash1] = x;
            int hash2 = this.h2(temp, 0, temp.length);
            if (this.t2[hash2] == null) {
               this.t2[hash2] = temp;
               return;
            }

            byte[] var7 = this.t2[hash2];
            this.t2[hash2] = temp;
            x = var7;
         }

         this.rehash();
         this.insert(x);
      }
   }

   public void load(byte[][] a) {
      for(byte[] x : a) {
         this.insert(x);
      }

   }

   private byte[] tryInsert(byte[] x) {
      int i = 0;

      while(true) {
         if (i != this.n) {
            int hash1 = this.h1(x, 0, x.length);
            if (this.t1[hash1] == null) {
               this.t1[hash1] = x;
               return null;
            }

            byte[] temp = this.t1[hash1];
            this.t1[hash1] = x;
            int hash2 = this.h2(temp, 0, temp.length);
            if (this.t2[hash2] == null) {
               this.t2[hash2] = temp;
               return null;
            }

            byte[] var7 = this.t2[hash2];
            this.t2[hash2] = temp;
            x = var7;
            if (var7 != null) {
               ++i;
               continue;
            }
         }

         return x;
      }
   }

   private int h1(byte[] b, int start, int len) {
      return (this.hash(b, start, len, 0) & Integer.MAX_VALUE) % this.n;
   }

   private int h2(byte[] b, int start, int len) {
      return (this.hash(b, start, len, this.salt) & Integer.MAX_VALUE) % this.n;
   }

   private void updateHashSalt() {
      this.salt = this.gen.nextInt(Integer.MAX_VALUE);
   }

   private void rehash() {
      ++this.rehashCount;
      if (this.rehashCount > 20) {
         throw new RuntimeException("Too many rehashes");
      } else {
         this.updateHashSalt();
         if (this.prev1 == null) {
            this.prev1 = this.t1;
            this.prev2 = this.t2;
         }

         this.t1 = new byte[this.n][];
         this.t2 = new byte[this.n][];

         for(byte[] v : this.prev1) {
            if (v != null) {
               byte[] x = this.tryInsert(v);
               if (x != null) {
                  this.rehash();
                  return;
               }
            }
         }

         for(byte[] v : this.prev2) {
            if (v != null) {
               byte[] x = this.tryInsert(v);
               if (x != null) {
                  this.rehash();
                  return;
               }
            }
         }

         this.prev1 = null;
         this.prev2 = null;
      }
   }

   private int hash(byte[] key, int start, int nbytes, int initval) {
      int length = nbytes;
      long b;
      long c;
      long a = b = c = 3735928559L + (long)nbytes + (long)initval & 4294967295L;

      int offset;
      for(offset = start; length > 12; length -= 12) {
         long var13 = a + ((long)key[offset] & 255L) & 4294967295L;
         long var14 = var13 + (((long)key[offset + 1] & 255L) << 8 & 4294967295L) & 4294967295L;
         long var15 = var14 + (((long)key[offset + 2] & 255L) << 16 & 4294967295L) & 4294967295L;
         long var16 = var15 + (((long)key[offset + 3] & 255L) << 24 & 4294967295L) & 4294967295L;
         b = b + ((long)key[offset + 4] & 255L) & 4294967295L;
         b = b + (((long)key[offset + 5] & 255L) << 8 & 4294967295L) & 4294967295L;
         b = b + (((long)key[offset + 6] & 255L) << 16 & 4294967295L) & 4294967295L;
         b = b + (((long)key[offset + 7] & 255L) << 24 & 4294967295L) & 4294967295L;
         c = c + ((long)key[offset + 8] & 255L) & 4294967295L;
         c = c + (((long)key[offset + 9] & 255L) << 8 & 4294967295L) & 4294967295L;
         c = c + (((long)key[offset + 10] & 255L) << 16 & 4294967295L) & 4294967295L;
         c = c + (((long)key[offset + 11] & 255L) << 24 & 4294967295L) & 4294967295L;
         long var17 = var16 - c & 4294967295L;
         long var18 = var17 ^ rot(c, 4);
         c = c + b & 4294967295L;
         b = b - var18 & 4294967295L;
         b ^= rot(var18, 6);
         long var19 = var18 + c & 4294967295L;
         c = c - b & 4294967295L;
         c ^= rot(b, 8);
         b = b + var19 & 4294967295L;
         long var20 = var19 - c & 4294967295L;
         long var21 = var20 ^ rot(c, 16);
         c = c + b & 4294967295L;
         b = b - var21 & 4294967295L;
         b ^= rot(var21, 19);
         a = var21 + c & 4294967295L;
         c = c - b & 4294967295L;
         c ^= rot(b, 4);
         b = b + a & 4294967295L;
         offset += 12;
      }

      switch (length) {
         case 0:
            return (int)(c & 4294967295L);
         case 12:
            c = c + (((long)key[offset + 11] & 255L) << 24 & 4294967295L) & 4294967295L;
         case 11:
            c = c + (((long)key[offset + 10] & 255L) << 16 & 4294967295L) & 4294967295L;
         case 10:
            c = c + (((long)key[offset + 9] & 255L) << 8 & 4294967295L) & 4294967295L;
         case 9:
            c = c + ((long)key[offset + 8] & 255L) & 4294967295L;
         case 8:
            b = b + (((long)key[offset + 7] & 255L) << 24 & 4294967295L) & 4294967295L;
         case 7:
            b = b + (((long)key[offset + 6] & 255L) << 16 & 4294967295L) & 4294967295L;
         case 6:
            b = b + (((long)key[offset + 5] & 255L) << 8 & 4294967295L) & 4294967295L;
         case 5:
            b = b + ((long)key[offset + 4] & 255L) & 4294967295L;
         case 4:
            a = a + (((long)key[offset + 3] & 255L) << 24 & 4294967295L) & 4294967295L;
         case 3:
            a = a + (((long)key[offset + 2] & 255L) << 16 & 4294967295L) & 4294967295L;
         case 2:
            a = a + (((long)key[offset + 1] & 255L) << 8 & 4294967295L) & 4294967295L;
         case 1:
            a = a + ((long)key[offset] & 255L) & 4294967295L;
         default:
            c ^= b;
            c = c - rot(b, 14) & 4294967295L;
            a ^= c;
            a = a - rot(c, 11) & 4294967295L;
            b ^= a;
            b = b - rot(a, 25) & 4294967295L;
            c ^= b;
            c = c - rot(b, 16) & 4294967295L;
            a ^= c;
            a = a - rot(c, 4) & 4294967295L;
            b ^= a;
            b = b - rot(a, 14) & 4294967295L;
            c ^= b;
            c = c - rot(b, 24) & 4294967295L;
            return (int)(c & 4294967295L);
      }
   }

   private static long rot(long val, int pos) {
      return (long)Integer.rotateLeft((int)(val & 4294967295L), pos) & 4294967295L;
   }
}
