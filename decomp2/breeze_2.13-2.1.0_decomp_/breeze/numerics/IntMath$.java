package breeze.numerics;

public final class IntMath$ {
   public static final IntMath$ MODULE$ = new IntMath$();

   public int ipow(final int base, final int exp) {
      int b = base;
      int var10000;
      if (exp < 0 && base != 1) {
         var10000 = 0;
      } else {
         int e = exp;

         int result;
         for(result = 1; e != 0; b *= b) {
            if ((e & 1) != 0) {
               result *= b;
            }

            e >>= 1;
         }

         var10000 = result;
      }

      return var10000;
   }

   public long ipow(final long base, final long exp) {
      long b = base;
      long var10000;
      if (exp < 0L && base != 1L) {
         var10000 = 0L;
      } else {
         long e = exp;

         long result;
         for(result = 1L; e != 0L; b *= b) {
            if ((e & 1L) != 0L) {
               result *= b;
            }

            e >>= 1;
         }

         var10000 = result;
      }

      return var10000;
   }

   private IntMath$() {
   }
}
