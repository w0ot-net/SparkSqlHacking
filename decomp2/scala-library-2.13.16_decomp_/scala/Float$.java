package scala;

public final class Float$ implements AnyValCompanion {
   public static final Float$ MODULE$ = new Float$();

   public final float MinPositiveValue() {
      return java.lang.Float.MIN_VALUE;
   }

   public final float NaN() {
      return java.lang.Float.NaN;
   }

   public final float PositiveInfinity() {
      return java.lang.Float.POSITIVE_INFINITY;
   }

   public final float NegativeInfinity() {
      return java.lang.Float.NEGATIVE_INFINITY;
   }

   public final float MinValue() {
      return -java.lang.Float.MAX_VALUE;
   }

   public final float MaxValue() {
      return java.lang.Float.MAX_VALUE;
   }

   public java.lang.Float box(final float x) {
      throw new NotImplementedError();
   }

   public float unbox(final Object x) {
      throw new NotImplementedError();
   }

   public String toString() {
      return "object scala.Float";
   }

   public double float2double(final float x) {
      return (double)x;
   }

   private Float$() {
   }
}
