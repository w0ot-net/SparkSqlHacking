package spire.random;

public final class Exponential$ implements ExponentialInstances {
   public static final Exponential$ MODULE$ = new Exponential$();
   private static Exponential float;
   private static Exponential double;

   static {
      ExponentialInstances.$init$(MODULE$);
   }

   public Exponential float() {
      return float;
   }

   public Exponential double() {
      return double;
   }

   public void spire$random$ExponentialInstances$_setter_$float_$eq(final Exponential x$1) {
      float = x$1;
   }

   public void spire$random$ExponentialInstances$_setter_$double_$eq(final Exponential x$1) {
      double = x$1;
   }

   public final Exponential apply(final Exponential e) {
      return e;
   }

   public Dist apply(final Object rate, final Exponential e) {
      return e.apply(rate);
   }

   public final Exponential apply$mDc$sp(final Exponential e) {
      return e;
   }

   public final Exponential apply$mFc$sp(final Exponential e) {
      return e;
   }

   public Dist apply$mDc$sp(final double rate, final Exponential e) {
      return e.apply$mcD$sp(rate);
   }

   public Dist apply$mFc$sp(final float rate, final Exponential e) {
      return e.apply$mcF$sp(rate);
   }

   private Exponential$() {
   }
}
