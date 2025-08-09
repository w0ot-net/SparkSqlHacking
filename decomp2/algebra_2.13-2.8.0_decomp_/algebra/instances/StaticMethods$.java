package algebra.instances;

public final class StaticMethods$ {
   public static final StaticMethods$ MODULE$ = new StaticMethods$();

   public final long pow(final long base, final long exponent) {
      long var10000;
      if (exponent >= 0L) {
         var10000 = this.loop$1(1L, base, exponent);
      } else {
         if (base == 0L) {
            throw new ArithmeticException("zero can't be raised to negative power");
         }

         var10000 = base == 1L ? 1L : (base == -1L ? ((exponent & 1L) == 0L ? -1L : 1L) : 0L);
      }

      return var10000;
   }

   private final long loop$1(final long t, final long b, final long e) {
      while(e != 0L) {
         if ((e & 1L) == 1L) {
            long var10000 = t * b;
            long var7 = b * b;
            e >>>= (int)1L;
            b = var7;
            t = var10000;
         } else {
            long var10001 = b * b;
            e >>>= (int)1L;
            b = var10001;
            t = t;
         }
      }

      return t;
   }

   private StaticMethods$() {
   }
}
