package scala;

public final class Long$ implements AnyValCompanion {
   public static final Long$ MODULE$ = new Long$();

   public final long MinValue() {
      return java.lang.Long.MIN_VALUE;
   }

   public final long MaxValue() {
      return java.lang.Long.MAX_VALUE;
   }

   public java.lang.Long box(final long x) {
      throw new NotImplementedError();
   }

   public long unbox(final Object x) {
      throw new NotImplementedError();
   }

   public String toString() {
      return "object scala.Long";
   }

   /** @deprecated */
   public float long2float(final long x) {
      return (float)x;
   }

   /** @deprecated */
   public double long2double(final long x) {
      return (double)x;
   }

   private Long$() {
   }
}
