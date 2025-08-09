package spire.random.rng;

import spire.math.package$;

public final class Utils$ {
   public static final Utils$ MODULE$ = new Utils$();
   private static volatile long seedUniquifier = 8682522807148012L;

   private long seedUniquifier() {
      return seedUniquifier;
   }

   private void seedUniquifier_$eq(final long x$1) {
      seedUniquifier = x$1;
   }

   public int intFromTime(final long time) {
      return (int)this.longFromTime(time);
   }

   public long intFromTime$default$1() {
      return System.nanoTime();
   }

   public long longFromTime(final long time) {
      this.seedUniquifier_$eq(this.seedUniquifier() + 1L);
      return this.seedUniquifier() + time;
   }

   public long longFromTime$default$1() {
      return System.nanoTime();
   }

   public int[] seedFromInt(final int length, final int seed) {
      int[] a = new int[length];
      a[0] = seed;

      for(int index$macro$1 = 1; index$macro$1 < length; ++index$macro$1) {
         int x = a[index$macro$1 - 1];
         a[index$macro$1] = 1812433253 * (x ^ x >>> 30) + index$macro$1;
      }

      return a;
   }

   public int seedFromInt$default$2() {
      return 5489;
   }

   public long[] seedFromLong(final int length, final long seed) {
      long[] a = new long[length];
      a[0] = seed;

      for(int index$macro$1 = 1; index$macro$1 < length; ++index$macro$1) {
         long x = a[index$macro$1 - 1];
         a[index$macro$1] = 6364136223846793005L * (x ^ x >>> 62) + (long)index$macro$1;
      }

      return a;
   }

   public long seedFromLong$default$2() {
      return 5489L;
   }

   public int[] seedFromArray(final int length, final int[] seed) {
      int[] a = this.seedFromInt(length, 19650218);
      int length_1 = length - 1;
      int i = 1;
      int j = 0;

      for(int k = package$.MODULE$.max(length, seed.length); k != 0; --k) {
         int x = a[i - 1];
         a[i] ^= (x ^ x >>> 30) * 1664525 + seed[j] + j;
         ++i;
         ++j;
         if (i >= length) {
            a[0] = a[length_1];
            i = 1;
         }

         if (j >= seed.length) {
            j = 0;
         }
      }

      for(int var10 = length_1; var10 != 0; --var10) {
         int x = a[i - 1];
         a[i] ^= (x ^ x >>> 30) * 1566083941 - i;
         ++i;
         if (i >= length) {
            a[0] = a[length_1];
            i = 1;
         }
      }

      a[0] = Integer.MIN_VALUE;
      return a;
   }

   public long[] seedFromArray(final int length, final long[] seed) {
      long[] a = this.seedFromLong(length, 19650218L);
      int length_1 = length - 1;
      int i = 1;
      int j = 0;

      for(int k = package$.MODULE$.max(length, seed.length); k != 0; --k) {
         long x = a[i - 1];
         a[i] ^= (x ^ x >>> 62) * 3935559000370003845L + seed[j] + (long)j;
         ++i;
         ++j;
         if (i >= length) {
            a[0] = a[length_1];
            i = 1;
         }

         if (j >= seed.length) {
            j = 0;
         }
      }

      for(int var12 = length - 1; var12 != 0; --var12) {
         long x = a[i - 1];
         a[i] ^= (x ^ x >>> 62) * 2862933555777941757L - (long)i;
         ++i;
         if (i >= length) {
            a[0] = a[length_1];
            i = 1;
         }
      }

      a[0] = Long.MIN_VALUE;
      return a;
   }

   private Utils$() {
   }
}
