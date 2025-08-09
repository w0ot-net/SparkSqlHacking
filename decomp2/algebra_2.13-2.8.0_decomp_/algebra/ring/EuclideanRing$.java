package algebra.ring;

import cats.kernel.Eq;
import java.io.Serializable;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.runtime.ModuleSerializationProxy;

public final class EuclideanRing$ implements EuclideanRingFunctions, Serializable {
   public static final EuclideanRing$ MODULE$ = new EuclideanRing$();

   static {
      AdditiveSemigroupFunctions.$init$(MODULE$);
      AdditiveMonoidFunctions.$init$(MODULE$);
      AdditiveGroupFunctions.$init$(MODULE$);
      MultiplicativeSemigroupFunctions.$init$(MODULE$);
      MultiplicativeMonoidFunctions.$init$(MODULE$);
      RingFunctions.$init$(MODULE$);
      GCDRingFunctions.$init$(MODULE$);
      EuclideanRingFunctions.$init$(MODULE$);
   }

   public BigInt euclideanFunction(final Object a, final EuclideanRing ev) {
      return EuclideanRingFunctions.euclideanFunction$(this, a, ev);
   }

   public BigInt euclideanFunction$mDc$sp(final double a, final EuclideanRing ev) {
      return EuclideanRingFunctions.euclideanFunction$mDc$sp$(this, a, ev);
   }

   public BigInt euclideanFunction$mFc$sp(final float a, final EuclideanRing ev) {
      return EuclideanRingFunctions.euclideanFunction$mFc$sp$(this, a, ev);
   }

   public BigInt euclideanFunction$mIc$sp(final int a, final EuclideanRing ev) {
      return EuclideanRingFunctions.euclideanFunction$mIc$sp$(this, a, ev);
   }

   public BigInt euclideanFunction$mJc$sp(final long a, final EuclideanRing ev) {
      return EuclideanRingFunctions.euclideanFunction$mJc$sp$(this, a, ev);
   }

   public Object equot(final Object a, final Object b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equot$(this, a, b, ev);
   }

   public double equot$mDc$sp(final double a, final double b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equot$mDc$sp$(this, a, b, ev);
   }

   public float equot$mFc$sp(final float a, final float b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equot$mFc$sp$(this, a, b, ev);
   }

   public int equot$mIc$sp(final int a, final int b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equot$mIc$sp$(this, a, b, ev);
   }

   public long equot$mJc$sp(final long a, final long b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equot$mJc$sp$(this, a, b, ev);
   }

   public Object emod(final Object a, final Object b, final EuclideanRing ev) {
      return EuclideanRingFunctions.emod$(this, a, b, ev);
   }

   public double emod$mDc$sp(final double a, final double b, final EuclideanRing ev) {
      return EuclideanRingFunctions.emod$mDc$sp$(this, a, b, ev);
   }

   public float emod$mFc$sp(final float a, final float b, final EuclideanRing ev) {
      return EuclideanRingFunctions.emod$mFc$sp$(this, a, b, ev);
   }

   public int emod$mIc$sp(final int a, final int b, final EuclideanRing ev) {
      return EuclideanRingFunctions.emod$mIc$sp$(this, a, b, ev);
   }

   public long emod$mJc$sp(final long a, final long b, final EuclideanRing ev) {
      return EuclideanRingFunctions.emod$mJc$sp$(this, a, b, ev);
   }

   public Tuple2 equotmod(final Object a, final Object b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equotmod$(this, a, b, ev);
   }

   public Tuple2 equotmod$mDc$sp(final double a, final double b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equotmod$mDc$sp$(this, a, b, ev);
   }

   public Tuple2 equotmod$mFc$sp(final float a, final float b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equotmod$mFc$sp$(this, a, b, ev);
   }

   public Tuple2 equotmod$mIc$sp(final int a, final int b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equotmod$mIc$sp$(this, a, b, ev);
   }

   public Tuple2 equotmod$mJc$sp(final long a, final long b, final EuclideanRing ev) {
      return EuclideanRingFunctions.equotmod$mJc$sp$(this, a, b, ev);
   }

   public Object gcd(final Object a, final Object b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.gcd$(this, a, b, ev, eqA);
   }

   public double gcd$mDc$sp(final double a, final double b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.gcd$mDc$sp$(this, a, b, ev, eqA);
   }

   public float gcd$mFc$sp(final float a, final float b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.gcd$mFc$sp$(this, a, b, ev, eqA);
   }

   public int gcd$mIc$sp(final int a, final int b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.gcd$mIc$sp$(this, a, b, ev, eqA);
   }

   public long gcd$mJc$sp(final long a, final long b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.gcd$mJc$sp$(this, a, b, ev, eqA);
   }

   public Object lcm(final Object a, final Object b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.lcm$(this, a, b, ev, eqA);
   }

   public double lcm$mDc$sp(final double a, final double b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.lcm$mDc$sp$(this, a, b, ev, eqA);
   }

   public float lcm$mFc$sp(final float a, final float b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.lcm$mFc$sp$(this, a, b, ev, eqA);
   }

   public int lcm$mIc$sp(final int a, final int b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.lcm$mIc$sp$(this, a, b, ev, eqA);
   }

   public long lcm$mJc$sp(final long a, final long b, final GCDRing ev, final Eq eqA) {
      return GCDRingFunctions.lcm$mJc$sp$(this, a, b, ev, eqA);
   }

   public Object fromInt(final int n, final Ring ev) {
      return RingFunctions.fromInt$(this, n, ev);
   }

   public double fromInt$mDc$sp(final int n, final Ring ev) {
      return RingFunctions.fromInt$mDc$sp$(this, n, ev);
   }

   public float fromInt$mFc$sp(final int n, final Ring ev) {
      return RingFunctions.fromInt$mFc$sp$(this, n, ev);
   }

   public int fromInt$mIc$sp(final int n, final Ring ev) {
      return RingFunctions.fromInt$mIc$sp$(this, n, ev);
   }

   public long fromInt$mJc$sp(final int n, final Ring ev) {
      return RingFunctions.fromInt$mJc$sp$(this, n, ev);
   }

   public Object fromBigInt(final BigInt n, final Ring ev) {
      return RingFunctions.fromBigInt$(this, n, ev);
   }

   public double fromBigInt$mDc$sp(final BigInt n, final Ring ev) {
      return RingFunctions.fromBigInt$mDc$sp$(this, n, ev);
   }

   public float fromBigInt$mFc$sp(final BigInt n, final Ring ev) {
      return RingFunctions.fromBigInt$mFc$sp$(this, n, ev);
   }

   public int fromBigInt$mIc$sp(final BigInt n, final Ring ev) {
      return RingFunctions.fromBigInt$mIc$sp$(this, n, ev);
   }

   public long fromBigInt$mJc$sp(final BigInt n, final Ring ev) {
      return RingFunctions.fromBigInt$mJc$sp$(this, n, ev);
   }

   public final Object defaultFromBigInt(final BigInt n, final Ring ev) {
      return RingFunctions.defaultFromBigInt$(this, n, ev);
   }

   public final double defaultFromBigInt$mDc$sp(final BigInt n, final Ring ev) {
      return RingFunctions.defaultFromBigInt$mDc$sp$(this, n, ev);
   }

   public final float defaultFromBigInt$mFc$sp(final BigInt n, final Ring ev) {
      return RingFunctions.defaultFromBigInt$mFc$sp$(this, n, ev);
   }

   public final int defaultFromBigInt$mIc$sp(final BigInt n, final Ring ev) {
      return RingFunctions.defaultFromBigInt$mIc$sp$(this, n, ev);
   }

   public final long defaultFromBigInt$mJc$sp(final BigInt n, final Ring ev) {
      return RingFunctions.defaultFromBigInt$mJc$sp$(this, n, ev);
   }

   public final Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return RingFunctions.defaultFromDouble$(this, a, ringA, mgA);
   }

   public Object one(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$(this, ev);
   }

   public double one$mDc$sp(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$mDc$sp$(this, ev);
   }

   public float one$mFc$sp(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$mFc$sp$(this, ev);
   }

   public int one$mIc$sp(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$mIc$sp$(this, ev);
   }

   public long one$mJc$sp(final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.one$mJc$sp$(this, ev);
   }

   public boolean isOne(final Object a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$(this, a, ev0, ev1);
   }

   public boolean isOne$mDc$sp(final double a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$mDc$sp$(this, a, ev0, ev1);
   }

   public boolean isOne$mFc$sp(final float a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$mFc$sp$(this, a, ev0, ev1);
   }

   public boolean isOne$mIc$sp(final int a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$mIc$sp$(this, a, ev0, ev1);
   }

   public boolean isOne$mJc$sp(final long a, final MultiplicativeMonoid ev0, final Eq ev1) {
      return MultiplicativeMonoidFunctions.isOne$mJc$sp$(this, a, ev0, ev1);
   }

   public Object product(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$(this, as, ev);
   }

   public double product$mDc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$mDc$sp$(this, as, ev);
   }

   public float product$mFc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$mFc$sp$(this, as, ev);
   }

   public int product$mIc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$mIc$sp$(this, as, ev);
   }

   public long product$mJc$sp(final IterableOnce as, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidFunctions.product$mJc$sp$(this, as, ev);
   }

   public boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.isMultiplicativeCommutative$(this, ev);
   }

   public Object times(final Object x, final Object y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$(this, x, y, ev);
   }

   public double times$mDc$sp(final double x, final double y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$mDc$sp$(this, x, y, ev);
   }

   public float times$mFc$sp(final float x, final float y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$mFc$sp$(this, x, y, ev);
   }

   public int times$mIc$sp(final int x, final int y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$mIc$sp$(this, x, y, ev);
   }

   public long times$mJc$sp(final long x, final long y, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.times$mJc$sp$(this, x, y, ev);
   }

   public Object pow(final Object a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$(this, a, n, ev);
   }

   public double pow$mDc$sp(final double a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$mDc$sp$(this, a, n, ev);
   }

   public float pow$mFc$sp(final float a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$mFc$sp$(this, a, n, ev);
   }

   public int pow$mIc$sp(final int a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$mIc$sp$(this, a, n, ev);
   }

   public long pow$mJc$sp(final long a, final int n, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.pow$mJc$sp$(this, a, n, ev);
   }

   public Option tryProduct(final IterableOnce as, final MultiplicativeSemigroup ev) {
      return MultiplicativeSemigroupFunctions.tryProduct$(this, as, ev);
   }

   public Object negate(final Object x, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.negate$(this, x, ev);
   }

   public double negate$mDc$sp(final double x, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.negate$mDc$sp$(this, x, ev);
   }

   public float negate$mFc$sp(final float x, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.negate$mFc$sp$(this, x, ev);
   }

   public int negate$mIc$sp(final int x, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.negate$mIc$sp$(this, x, ev);
   }

   public long negate$mJc$sp(final long x, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.negate$mJc$sp$(this, x, ev);
   }

   public Object minus(final Object x, final Object y, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.minus$(this, x, y, ev);
   }

   public double minus$mDc$sp(final double x, final double y, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.minus$mDc$sp$(this, x, y, ev);
   }

   public float minus$mFc$sp(final float x, final float y, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.minus$mFc$sp$(this, x, y, ev);
   }

   public int minus$mIc$sp(final int x, final int y, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.minus$mIc$sp$(this, x, y, ev);
   }

   public long minus$mJc$sp(final long x, final long y, final AdditiveGroup ev) {
      return AdditiveGroupFunctions.minus$mJc$sp$(this, x, y, ev);
   }

   public Object zero(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$(this, ev);
   }

   public double zero$mDc$sp(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$mDc$sp$(this, ev);
   }

   public float zero$mFc$sp(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$mFc$sp$(this, ev);
   }

   public int zero$mIc$sp(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$mIc$sp$(this, ev);
   }

   public long zero$mJc$sp(final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.zero$mJc$sp$(this, ev);
   }

   public boolean isZero(final Object a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$(this, a, ev0, ev1);
   }

   public boolean isZero$mDc$sp(final double a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$mDc$sp$(this, a, ev0, ev1);
   }

   public boolean isZero$mFc$sp(final float a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$mFc$sp$(this, a, ev0, ev1);
   }

   public boolean isZero$mIc$sp(final int a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$mIc$sp$(this, a, ev0, ev1);
   }

   public boolean isZero$mJc$sp(final long a, final AdditiveMonoid ev0, final Eq ev1) {
      return AdditiveMonoidFunctions.isZero$mJc$sp$(this, a, ev0, ev1);
   }

   public Object sum(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$(this, as, ev);
   }

   public double sum$mDc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$mDc$sp$(this, as, ev);
   }

   public float sum$mFc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$mFc$sp$(this, as, ev);
   }

   public int sum$mIc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$mIc$sp$(this, as, ev);
   }

   public long sum$mJc$sp(final IterableOnce as, final AdditiveMonoid ev) {
      return AdditiveMonoidFunctions.sum$mJc$sp$(this, as, ev);
   }

   public boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.isAdditiveCommutative$(this, ev);
   }

   public Object plus(final Object x, final Object y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$(this, x, y, ev);
   }

   public double plus$mDc$sp(final double x, final double y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$mDc$sp$(this, x, y, ev);
   }

   public float plus$mFc$sp(final float x, final float y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$mFc$sp$(this, x, y, ev);
   }

   public int plus$mIc$sp(final int x, final int y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$mIc$sp$(this, x, y, ev);
   }

   public long plus$mJc$sp(final long x, final long y, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.plus$mJc$sp$(this, x, y, ev);
   }

   public Object sumN(final Object a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$(this, a, n, ev);
   }

   public double sumN$mDc$sp(final double a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$mDc$sp$(this, a, n, ev);
   }

   public float sumN$mFc$sp(final float a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$mFc$sp$(this, a, n, ev);
   }

   public int sumN$mIc$sp(final int a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$mIc$sp$(this, a, n, ev);
   }

   public long sumN$mJc$sp(final long a, final int n, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.sumN$mJc$sp$(this, a, n, ev);
   }

   public Option trySum(final IterableOnce as, final AdditiveSemigroup ev) {
      return AdditiveSemigroupFunctions.trySum$(this, as, ev);
   }

   public final EuclideanRing apply(final EuclideanRing e) {
      return e;
   }

   public final Object euclid(final Object a, final Object b, final Eq evidence$1, final EuclideanRing evidence$2) {
      while(!this.apply(evidence$2).isZero(b, evidence$1)) {
         Object var10000 = b;
         Object var10001 = this.apply(evidence$2).emod(a, b);
         evidence$2 = evidence$2;
         evidence$1 = evidence$1;
         b = var10001;
         a = var10000;
      }

      return a;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EuclideanRing$.class);
   }

   public final double euclid$mDc$sp(final double a, final double b, final Eq evidence$1, final EuclideanRing evidence$2) {
      while(!this.apply(evidence$2).isZero$mcD$sp(b, evidence$1)) {
         double var10000 = b;
         double var10001 = this.apply(evidence$2).emod$mcD$sp(a, b);
         evidence$2 = evidence$2;
         evidence$1 = evidence$1;
         b = var10001;
         a = var10000;
      }

      return a;
   }

   public final float euclid$mFc$sp(final float a, final float b, final Eq evidence$1, final EuclideanRing evidence$2) {
      while(!this.apply(evidence$2).isZero$mcF$sp(b, evidence$1)) {
         float var10000 = b;
         float var10001 = this.apply(evidence$2).emod$mcF$sp(a, b);
         evidence$2 = evidence$2;
         evidence$1 = evidence$1;
         b = var10001;
         a = var10000;
      }

      return a;
   }

   public final int euclid$mIc$sp(final int a, final int b, final Eq evidence$1, final EuclideanRing evidence$2) {
      while(!this.apply(evidence$2).isZero$mcI$sp(b, evidence$1)) {
         int var10000 = b;
         int var10001 = this.apply(evidence$2).emod$mcI$sp(a, b);
         evidence$2 = evidence$2;
         evidence$1 = evidence$1;
         b = var10001;
         a = var10000;
      }

      return a;
   }

   public final long euclid$mJc$sp(final long a, final long b, final Eq evidence$1, final EuclideanRing evidence$2) {
      while(!this.apply(evidence$2).isZero$mcJ$sp(b, evidence$1)) {
         long var10000 = b;
         long var10001 = this.apply(evidence$2).emod$mcJ$sp(a, b);
         evidence$2 = evidence$2;
         evidence$1 = evidence$1;
         b = var10001;
         a = var10000;
      }

      return a;
   }

   private EuclideanRing$() {
   }
}
