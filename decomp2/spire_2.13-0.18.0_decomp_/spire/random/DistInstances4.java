package spire.random;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.EuclideanRing;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\bESN$\u0018J\\:uC:\u001cWm\u001d\u001b\u000b\u0005\u00151\u0011A\u0002:b]\u0012|WNC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001\u0001\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011\u0011CE\u0007\u0002\t%\u00111\u0003\u0002\u0002\u000f\t&\u001cH/\u00138ti\u0006t7-Z:4\u0003\u0019!\u0013N\\5uIQ\ta\u0003\u0005\u0002\f/%\u0011\u0001\u0004\u0004\u0002\u0005+:LG/A\u0007fk\u000ed\u0017\u000eZ3b]JKgnZ\u000b\u00037E\"2\u0001\b\u001e@!\ri\u0012\u0006\f\b\u0003=\u0019r!a\b\u0013\u000f\u0005\u0001\u001aS\"A\u0011\u000b\u0005\tB\u0011A\u0002\u001fs_>$h(C\u0001\b\u0013\t)c!A\u0004bY\u001e,'M]1\n\u0005\u001dB\u0013a\u00029bG.\fw-\u001a\u0006\u0003K\u0019I!AK\u0016\u0003\u001b\u0015+8\r\\5eK\u0006t'+\u001b8h\u0015\t9\u0003\u0006E\u0002\u0012[=J!A\f\u0003\u0003\t\u0011K7\u000f\u001e\t\u0003aEb\u0001\u0001B\u00033\u0005\t\u00071GA\u0001B#\t!t\u0007\u0005\u0002\fk%\u0011a\u0007\u0004\u0002\b\u001d>$\b.\u001b8h!\tY\u0001(\u0003\u0002:\u0019\t\u0019\u0011I\\=\t\u000bm\u0012\u00019\u0001\u001f\u0002\u0007\u00154\u0018\u0007E\u0002\u001e{=J!AP\u0016\u0003\u0005\u0015\u000b\b\"\u0002!\u0003\u0001\b\t\u0015aA3weA\u0019Q$K\u0018"
)
public interface DistInstances4 extends DistInstances3 {
   // $FF: synthetic method
   static EuclideanRing euclideanRing$(final DistInstances4 $this, final Eq ev1, final EuclideanRing ev2) {
      return $this.euclideanRing(ev1, ev2);
   }

   default EuclideanRing euclideanRing(final Eq ev1, final EuclideanRing ev2) {
      return new DistEuclideanRing(ev2, ev1) {
         private final EuclideanRing ev2$2;
         private final Eq ev1$2;

         public BigInt euclideanFunction(final Dist x) {
            return DistEuclideanRing.euclideanFunction$(this, x);
         }

         public Dist equot(final Dist x, final Dist y) {
            return DistEuclideanRing.equot$(this, x, y);
         }

         public Dist emod(final Dist x, final Dist y) {
            return DistEuclideanRing.emod$(this, x, y);
         }

         public Dist gcd(final Dist x, final Dist y, final Eq ev) {
            return DistEuclideanRing.gcd$(this, x, y, ev);
         }

         public Dist lcm(final Dist x, final Dist y, final Eq ev) {
            return DistEuclideanRing.lcm$(this, x, y, ev);
         }

         public BigInt euclideanFunction$mcD$sp(final double a) {
            return EuclideanRing.euclideanFunction$mcD$sp$(this, a);
         }

         public BigInt euclideanFunction$mcF$sp(final float a) {
            return EuclideanRing.euclideanFunction$mcF$sp$(this, a);
         }

         public BigInt euclideanFunction$mcI$sp(final int a) {
            return EuclideanRing.euclideanFunction$mcI$sp$(this, a);
         }

         public BigInt euclideanFunction$mcJ$sp(final long a) {
            return EuclideanRing.euclideanFunction$mcJ$sp$(this, a);
         }

         public double equot$mcD$sp(final double a, final double b) {
            return EuclideanRing.equot$mcD$sp$(this, a, b);
         }

         public float equot$mcF$sp(final float a, final float b) {
            return EuclideanRing.equot$mcF$sp$(this, a, b);
         }

         public int equot$mcI$sp(final int a, final int b) {
            return EuclideanRing.equot$mcI$sp$(this, a, b);
         }

         public long equot$mcJ$sp(final long a, final long b) {
            return EuclideanRing.equot$mcJ$sp$(this, a, b);
         }

         public double emod$mcD$sp(final double a, final double b) {
            return EuclideanRing.emod$mcD$sp$(this, a, b);
         }

         public float emod$mcF$sp(final float a, final float b) {
            return EuclideanRing.emod$mcF$sp$(this, a, b);
         }

         public int emod$mcI$sp(final int a, final int b) {
            return EuclideanRing.emod$mcI$sp$(this, a, b);
         }

         public long emod$mcJ$sp(final long a, final long b) {
            return EuclideanRing.emod$mcJ$sp$(this, a, b);
         }

         public Tuple2 equotmod(final Object a, final Object b) {
            return EuclideanRing.equotmod$(this, a, b);
         }

         public Tuple2 equotmod$mcD$sp(final double a, final double b) {
            return EuclideanRing.equotmod$mcD$sp$(this, a, b);
         }

         public Tuple2 equotmod$mcF$sp(final float a, final float b) {
            return EuclideanRing.equotmod$mcF$sp$(this, a, b);
         }

         public Tuple2 equotmod$mcI$sp(final int a, final int b) {
            return EuclideanRing.equotmod$mcI$sp$(this, a, b);
         }

         public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
            return EuclideanRing.equotmod$mcJ$sp$(this, a, b);
         }

         public double gcd$mcD$sp(final double a, final double b, final Eq ev) {
            return EuclideanRing.gcd$mcD$sp$(this, a, b, ev);
         }

         public float gcd$mcF$sp(final float a, final float b, final Eq ev) {
            return EuclideanRing.gcd$mcF$sp$(this, a, b, ev);
         }

         public int gcd$mcI$sp(final int a, final int b, final Eq ev) {
            return EuclideanRing.gcd$mcI$sp$(this, a, b, ev);
         }

         public long gcd$mcJ$sp(final long a, final long b, final Eq ev) {
            return EuclideanRing.gcd$mcJ$sp$(this, a, b, ev);
         }

         public double lcm$mcD$sp(final double a, final double b, final Eq ev) {
            return EuclideanRing.lcm$mcD$sp$(this, a, b, ev);
         }

         public float lcm$mcF$sp(final float a, final float b, final Eq ev) {
            return EuclideanRing.lcm$mcF$sp$(this, a, b, ev);
         }

         public int lcm$mcI$sp(final int a, final int b, final Eq ev) {
            return EuclideanRing.lcm$mcI$sp$(this, a, b, ev);
         }

         public long lcm$mcJ$sp(final long a, final long b, final Eq ev) {
            return EuclideanRing.lcm$mcJ$sp$(this, a, b, ev);
         }

         public Dist one() {
            return DistCRing.one$(this);
         }

         public CommutativeMonoid multiplicative() {
            return MultiplicativeCommutativeMonoid.multiplicative$(this);
         }

         public CommutativeMonoid multiplicative$mcD$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcD$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcF$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcF$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcI$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcI$sp$(this);
         }

         public CommutativeMonoid multiplicative$mcJ$sp() {
            return MultiplicativeCommutativeMonoid.multiplicative$mcJ$sp$(this);
         }

         public Object fromInt(final int n) {
            return Ring.fromInt$(this, n);
         }

         public double fromInt$mcD$sp(final int n) {
            return Ring.fromInt$mcD$sp$(this, n);
         }

         public float fromInt$mcF$sp(final int n) {
            return Ring.fromInt$mcF$sp$(this, n);
         }

         public int fromInt$mcI$sp(final int n) {
            return Ring.fromInt$mcI$sp$(this, n);
         }

         public long fromInt$mcJ$sp(final int n) {
            return Ring.fromInt$mcJ$sp$(this, n);
         }

         public Object fromBigInt(final BigInt n) {
            return Ring.fromBigInt$(this, n);
         }

         public double fromBigInt$mcD$sp(final BigInt n) {
            return Ring.fromBigInt$mcD$sp$(this, n);
         }

         public float fromBigInt$mcF$sp(final BigInt n) {
            return Ring.fromBigInt$mcF$sp$(this, n);
         }

         public int fromBigInt$mcI$sp(final BigInt n) {
            return Ring.fromBigInt$mcI$sp$(this, n);
         }

         public long fromBigInt$mcJ$sp(final BigInt n) {
            return Ring.fromBigInt$mcJ$sp$(this, n);
         }

         public double one$mcD$sp() {
            return MultiplicativeMonoid.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return MultiplicativeMonoid.one$mcF$sp$(this);
         }

         public int one$mcI$sp() {
            return MultiplicativeMonoid.one$mcI$sp$(this);
         }

         public long one$mcJ$sp() {
            return MultiplicativeMonoid.one$mcJ$sp$(this);
         }

         public boolean isOne(final Object a, final Eq ev) {
            return MultiplicativeMonoid.isOne$(this, a, ev);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeMonoid.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
         }

         public Object product(final IterableOnce as) {
            return MultiplicativeMonoid.product$(this, as);
         }

         public double product$mcD$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcD$sp$(this, as);
         }

         public float product$mcF$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcF$sp$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcI$sp$(this, as);
         }

         public long product$mcJ$sp(final IterableOnce as) {
            return MultiplicativeMonoid.product$mcJ$sp$(this, as);
         }

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeMonoid.tryProduct$(this, as);
         }

         public Dist negate(final Dist x) {
            return DistCRng.negate$(this, x);
         }

         public CommutativeGroup additive() {
            return AdditiveCommutativeGroup.additive$(this);
         }

         public CommutativeGroup additive$mcD$sp() {
            return AdditiveCommutativeGroup.additive$mcD$sp$(this);
         }

         public CommutativeGroup additive$mcF$sp() {
            return AdditiveCommutativeGroup.additive$mcF$sp$(this);
         }

         public CommutativeGroup additive$mcI$sp() {
            return AdditiveCommutativeGroup.additive$mcI$sp$(this);
         }

         public CommutativeGroup additive$mcJ$sp() {
            return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
         }

         public double negate$mcD$sp(final double x) {
            return AdditiveGroup.negate$mcD$sp$(this, x);
         }

         public float negate$mcF$sp(final float x) {
            return AdditiveGroup.negate$mcF$sp$(this, x);
         }

         public int negate$mcI$sp(final int x) {
            return AdditiveGroup.negate$mcI$sp$(this, x);
         }

         public long negate$mcJ$sp(final long x) {
            return AdditiveGroup.negate$mcJ$sp$(this, x);
         }

         public Object minus(final Object x, final Object y) {
            return AdditiveGroup.minus$(this, x, y);
         }

         public double minus$mcD$sp(final double x, final double y) {
            return AdditiveGroup.minus$mcD$sp$(this, x, y);
         }

         public float minus$mcF$sp(final float x, final float y) {
            return AdditiveGroup.minus$mcF$sp$(this, x, y);
         }

         public int minus$mcI$sp(final int x, final int y) {
            return AdditiveGroup.minus$mcI$sp$(this, x, y);
         }

         public long minus$mcJ$sp(final long x, final long y) {
            return AdditiveGroup.minus$mcJ$sp$(this, x, y);
         }

         public Object sumN(final Object a, final int n) {
            return AdditiveGroup.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveGroup.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveGroup.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveGroup.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
         }

         public Dist zero() {
            return DistCSemiring.zero$(this);
         }

         public Dist plus(final Dist x, final Dist y) {
            return DistCSemiring.plus$(this, x, y);
         }

         public Dist times(final Dist x, final Dist y) {
            return DistCSemiring.times$(this, x, y);
         }

         public double times$mcD$sp(final double x, final double y) {
            return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
         }

         public float times$mcF$sp(final float x, final float y) {
            return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
         }

         public long times$mcJ$sp(final long x, final long y) {
            return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
         }

         public Object positivePow(final Object a, final int n) {
            return MultiplicativeSemigroup.positivePow$(this, a, n);
         }

         public double positivePow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
         }

         public float positivePow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
         }

         public int positivePow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
         }

         public long positivePow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
         }

         public double zero$mcD$sp() {
            return AdditiveMonoid.zero$mcD$sp$(this);
         }

         public float zero$mcF$sp() {
            return AdditiveMonoid.zero$mcF$sp$(this);
         }

         public int zero$mcI$sp() {
            return AdditiveMonoid.zero$mcI$sp$(this);
         }

         public long zero$mcJ$sp() {
            return AdditiveMonoid.zero$mcJ$sp$(this);
         }

         public boolean isZero(final Object a, final Eq ev) {
            return AdditiveMonoid.isZero$(this, a, ev);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
         }

         public Object sum(final IterableOnce as) {
            return AdditiveMonoid.sum$(this, as);
         }

         public double sum$mcD$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcD$sp$(this, as);
         }

         public float sum$mcF$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcF$sp$(this, as);
         }

         public int sum$mcI$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcI$sp$(this, as);
         }

         public long sum$mcJ$sp(final IterableOnce as) {
            return AdditiveMonoid.sum$mcJ$sp$(this, as);
         }

         public Option trySum(final IterableOnce as) {
            return AdditiveMonoid.trySum$(this, as);
         }

         public double plus$mcD$sp(final double x, final double y) {
            return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
         }

         public float plus$mcF$sp(final float x, final float y) {
            return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
         }

         public int plus$mcI$sp(final int x, final int y) {
            return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
         }

         public long plus$mcJ$sp(final long x, final long y) {
            return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
         }

         public Object positiveSumN(final Object a, final int n) {
            return AdditiveSemigroup.positiveSumN$(this, a, n);
         }

         public double positiveSumN$mcD$sp(final double a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
         }

         public float positiveSumN$mcF$sp(final float a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
         }

         public int positiveSumN$mcI$sp(final int a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
         }

         public long positiveSumN$mcJ$sp(final long a, final int n) {
            return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
         }

         public EuclideanRing alg() {
            return this.ev2$2;
         }

         public Eq eqA() {
            return this.ev1$2;
         }

         public {
            this.ev2$2 = ev2$2;
            this.ev1$2 = ev1$2;
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            MultiplicativeCommutativeSemigroup.$init$(this);
            DistCSemiring.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            DistCRng.$init$(this);
            MultiplicativeMonoid.$init$(this);
            Ring.$init$(this);
            MultiplicativeCommutativeMonoid.$init$(this);
            DistCRing.$init$(this);
            DistGCDRing.$init$(this);
            EuclideanRing.$init$(this);
            DistEuclideanRing.$init$(this);
         }
      };
   }

   static void $init$(final DistInstances4 $this) {
   }
}
