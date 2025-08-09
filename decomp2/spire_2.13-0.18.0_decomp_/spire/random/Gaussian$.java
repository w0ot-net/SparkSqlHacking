package spire.random;

import java.math.MathContext;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class Gaussian$ implements GaussianInstances {
   public static final Gaussian$ MODULE$ = new Gaussian$();
   private static Gaussian float;
   private static Gaussian double;

   static {
      GaussianInstances.$init$(MODULE$);
   }

   public Gaussian bigDecimal(final MathContext mc) {
      return GaussianInstances.bigDecimal$(this, mc);
   }

   public MathContext bigDecimal$default$1() {
      return GaussianInstances.bigDecimal$default$1$(this);
   }

   public Gaussian float() {
      return float;
   }

   public Gaussian double() {
      return double;
   }

   public void spire$random$GaussianInstances$_setter_$float_$eq(final Gaussian x$1) {
      float = x$1;
   }

   public void spire$random$GaussianInstances$_setter_$double_$eq(final Gaussian x$1) {
      double = x$1;
   }

   public final Gaussian apply(final Gaussian g) {
      return g;
   }

   public Dist apply(final Object mean, final Object stdDev, final Gaussian g) {
      return g.apply(mean, stdDev);
   }

   public final Gaussian apply$mDc$sp(final Gaussian g) {
      return g;
   }

   public final Gaussian apply$mFc$sp(final Gaussian g) {
      return g;
   }

   public Dist apply$mZc$sp(final boolean mean, final boolean stdDev, final Gaussian g) {
      return g.apply(BoxesRunTime.boxToBoolean(mean), BoxesRunTime.boxToBoolean(stdDev));
   }

   public Dist apply$mBc$sp(final byte mean, final byte stdDev, final Gaussian g) {
      return g.apply(BoxesRunTime.boxToByte(mean), BoxesRunTime.boxToByte(stdDev));
   }

   public Dist apply$mCc$sp(final char mean, final char stdDev, final Gaussian g) {
      return g.apply(BoxesRunTime.boxToCharacter(mean), BoxesRunTime.boxToCharacter(stdDev));
   }

   public Dist apply$mDc$sp(final double mean, final double stdDev, final Gaussian g) {
      return g.apply$mcD$sp(mean, stdDev);
   }

   public Dist apply$mFc$sp(final float mean, final float stdDev, final Gaussian g) {
      return g.apply$mcF$sp(mean, stdDev);
   }

   public Dist apply$mIc$sp(final int mean, final int stdDev, final Gaussian g) {
      return g.apply(BoxesRunTime.boxToInteger(mean), BoxesRunTime.boxToInteger(stdDev));
   }

   public Dist apply$mJc$sp(final long mean, final long stdDev, final Gaussian g) {
      return g.apply(BoxesRunTime.boxToLong(mean), BoxesRunTime.boxToLong(stdDev));
   }

   public Dist apply$mSc$sp(final short mean, final short stdDev, final Gaussian g) {
      return g.apply(BoxesRunTime.boxToShort(mean), BoxesRunTime.boxToShort(stdDev));
   }

   public Dist apply$mVc$sp(final BoxedUnit mean, final BoxedUnit stdDev, final Gaussian g) {
      return g.apply(mean, stdDev);
   }

   private Gaussian$() {
   }
}
