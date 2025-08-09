package scala;

public final class Double$ implements AnyValCompanion {
   public static final Double$ MODULE$ = new Double$();

   public final double MinPositiveValue() {
      return java.lang.Double.MIN_VALUE;
   }

   public final double NaN() {
      return java.lang.Double.NaN;
   }

   public final double PositiveInfinity() {
      return java.lang.Double.POSITIVE_INFINITY;
   }

   public final double NegativeInfinity() {
      return java.lang.Double.NEGATIVE_INFINITY;
   }

   public final double MinValue() {
      return -java.lang.Double.MAX_VALUE;
   }

   public final double MaxValue() {
      return java.lang.Double.MAX_VALUE;
   }

   public java.lang.Double box(final double x) {
      throw new NotImplementedError();
   }

   public double unbox(final Object x) {
      throw new NotImplementedError();
   }

   public String toString() {
      return "object scala.Double";
   }

   private Double$() {
   }
}
