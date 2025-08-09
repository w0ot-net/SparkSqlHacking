package spire.random;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.DivisionRing;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\bESN$\u0018J\\:uC:\u001cWm]\u001b\u000b\u0005\u00151\u0011A\u0002:b]\u0012|WNC\u0001\b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u00192\u0001\u0001\u0006\u0011!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fMB\u0011\u0011CE\u0007\u0002\t%\u00111\u0003\u0002\u0002\u000f\t&\u001cH/\u00138ti\u0006t7-Z:5\u0003\u0019!\u0013N\\5uIQ\ta\u0003\u0005\u0002\f/%\u0011\u0001\u0004\u0004\u0002\u0005+:LG/A\u0003gS\u0016dG-\u0006\u0002\u001ccQ\u0019ADO \u0011\u0007uICF\u0004\u0002\u001fM9\u0011q\u0004\n\b\u0003A\rj\u0011!\t\u0006\u0003E!\ta\u0001\u0010:p_Rt\u0014\"A\u0004\n\u0005\u00152\u0011aB1mO\u0016\u0014'/Y\u0005\u0003O!\nq\u0001]1dW\u0006<WM\u0003\u0002&\r%\u0011!f\u000b\u0002\u0006\r&,G\u000e\u001a\u0006\u0003O!\u00022!E\u00170\u0013\tqCA\u0001\u0003ESN$\bC\u0001\u00192\u0019\u0001!QA\r\u0002C\u0002M\u0012\u0011!Q\t\u0003i]\u0002\"aC\u001b\n\u0005Yb!a\u0002(pi\"Lgn\u001a\t\u0003\u0017aJ!!\u000f\u0007\u0003\u0007\u0005s\u0017\u0010C\u0003<\u0005\u0001\u000fA(A\u0002fmF\u00022!H\u001f0\u0013\tq4F\u0001\u0002Fc\")\u0001I\u0001a\u0002\u0003\u0006\u0019QM\u001e\u001a\u0011\u0007uIs\u0006"
)
public interface DistInstances5 extends DistInstances4 {
   // $FF: synthetic method
   static Field field$(final DistInstances5 $this, final Eq ev1, final Field ev2) {
      return $this.field(ev1, ev2);
   }

   default Field field(final Eq ev1, final Field ev2) {
      return new DistField(ev2, ev1) {
         private final Field ev2$3;
         private final Eq ev1$3;

         public Dist div(final Dist x, final Dist y) {
            return DistField.div$(this, x, y);
         }

         public Dist equot(final Dist x, final Dist y) {
            return DistField.equot$(this, x, y);
         }

         public Dist emod(final Dist x, final Dist y) {
            return DistField.emod$(this, x, y);
         }

         public Tuple2 equotmod(final Dist x, final Dist y) {
            return DistField.equotmod$(this, x, y);
         }

         public Dist reciprocal(final Dist x) {
            return DistField.reciprocal$(this, x);
         }

         public BigInt euclideanFunction(final Dist x) {
            return DistField.euclideanFunction$(this, x);
         }

         public Object gcd(final Object a, final Object b, final Eq eqA) {
            return Field.gcd$(this, a, b, eqA);
         }

         public double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
            return Field.gcd$mcD$sp$(this, a, b, eqA);
         }

         public float gcd$mcF$sp(final float a, final float b, final Eq eqA) {
            return Field.gcd$mcF$sp$(this, a, b, eqA);
         }

         public int gcd$mcI$sp(final int a, final int b, final Eq eqA) {
            return Field.gcd$mcI$sp$(this, a, b, eqA);
         }

         public long gcd$mcJ$sp(final long a, final long b, final Eq eqA) {
            return Field.gcd$mcJ$sp$(this, a, b, eqA);
         }

         public Object lcm(final Object a, final Object b, final Eq eqA) {
            return Field.lcm$(this, a, b, eqA);
         }

         public double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
            return Field.lcm$mcD$sp$(this, a, b, eqA);
         }

         public float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
            return Field.lcm$mcF$sp$(this, a, b, eqA);
         }

         public int lcm$mcI$sp(final int a, final int b, final Eq eqA) {
            return Field.lcm$mcI$sp$(this, a, b, eqA);
         }

         public long lcm$mcJ$sp(final long a, final long b, final Eq eqA) {
            return Field.lcm$mcJ$sp$(this, a, b, eqA);
         }

         public BigInt euclideanFunction$mcD$sp(final double a) {
            return Field.euclideanFunction$mcD$sp$(this, a);
         }

         public BigInt euclideanFunction$mcF$sp(final float a) {
            return Field.euclideanFunction$mcF$sp$(this, a);
         }

         public BigInt euclideanFunction$mcI$sp(final int a) {
            return Field.euclideanFunction$mcI$sp$(this, a);
         }

         public BigInt euclideanFunction$mcJ$sp(final long a) {
            return Field.euclideanFunction$mcJ$sp$(this, a);
         }

         public double equot$mcD$sp(final double a, final double b) {
            return Field.equot$mcD$sp$(this, a, b);
         }

         public float equot$mcF$sp(final float a, final float b) {
            return Field.equot$mcF$sp$(this, a, b);
         }

         public int equot$mcI$sp(final int a, final int b) {
            return Field.equot$mcI$sp$(this, a, b);
         }

         public long equot$mcJ$sp(final long a, final long b) {
            return Field.equot$mcJ$sp$(this, a, b);
         }

         public double emod$mcD$sp(final double a, final double b) {
            return Field.emod$mcD$sp$(this, a, b);
         }

         public float emod$mcF$sp(final float a, final float b) {
            return Field.emod$mcF$sp$(this, a, b);
         }

         public int emod$mcI$sp(final int a, final int b) {
            return Field.emod$mcI$sp$(this, a, b);
         }

         public long emod$mcJ$sp(final long a, final long b) {
            return Field.emod$mcJ$sp$(this, a, b);
         }

         public Tuple2 equotmod$mcD$sp(final double a, final double b) {
            return Field.equotmod$mcD$sp$(this, a, b);
         }

         public Tuple2 equotmod$mcF$sp(final float a, final float b) {
            return Field.equotmod$mcF$sp$(this, a, b);
         }

         public Tuple2 equotmod$mcI$sp(final int a, final int b) {
            return Field.equotmod$mcI$sp$(this, a, b);
         }

         public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
            return Field.equotmod$mcJ$sp$(this, a, b);
         }

         public Object fromDouble(final double a) {
            return Field.fromDouble$(this, a);
         }

         public double fromDouble$mcD$sp(final double a) {
            return Field.fromDouble$mcD$sp$(this, a);
         }

         public float fromDouble$mcF$sp(final double a) {
            return Field.fromDouble$mcF$sp$(this, a);
         }

         public int fromDouble$mcI$sp(final double a) {
            return Field.fromDouble$mcI$sp$(this, a);
         }

         public long fromDouble$mcJ$sp(final double a) {
            return Field.fromDouble$mcJ$sp$(this, a);
         }

         public CommutativeGroup multiplicative() {
            return MultiplicativeCommutativeGroup.multiplicative$(this);
         }

         public CommutativeGroup multiplicative$mcD$sp() {
            return MultiplicativeCommutativeGroup.multiplicative$mcD$sp$(this);
         }

         public CommutativeGroup multiplicative$mcF$sp() {
            return MultiplicativeCommutativeGroup.multiplicative$mcF$sp$(this);
         }

         public CommutativeGroup multiplicative$mcI$sp() {
            return MultiplicativeCommutativeGroup.multiplicative$mcI$sp$(this);
         }

         public CommutativeGroup multiplicative$mcJ$sp() {
            return MultiplicativeCommutativeGroup.multiplicative$mcJ$sp$(this);
         }

         public byte fromDouble$mcB$sp(final double a) {
            return DivisionRing.fromDouble$mcB$sp$(this, a);
         }

         public short fromDouble$mcS$sp(final double a) {
            return DivisionRing.fromDouble$mcS$sp$(this, a);
         }

         public double reciprocal$mcD$sp(final double x) {
            return MultiplicativeGroup.reciprocal$mcD$sp$(this, x);
         }

         public float reciprocal$mcF$sp(final float x) {
            return MultiplicativeGroup.reciprocal$mcF$sp$(this, x);
         }

         public int reciprocal$mcI$sp(final int x) {
            return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
         }

         public long reciprocal$mcJ$sp(final long x) {
            return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
         }

         public double div$mcD$sp(final double x, final double y) {
            return MultiplicativeGroup.div$mcD$sp$(this, x, y);
         }

         public float div$mcF$sp(final float x, final float y) {
            return MultiplicativeGroup.div$mcF$sp$(this, x, y);
         }

         public int div$mcI$sp(final int x, final int y) {
            return MultiplicativeGroup.div$mcI$sp$(this, x, y);
         }

         public long div$mcJ$sp(final long x, final long y) {
            return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
         }

         public Object pow(final Object a, final int n) {
            return MultiplicativeGroup.pow$(this, a, n);
         }

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeGroup.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
         }

         public Dist one() {
            return DistCRing.one$(this);
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

         public Field alg() {
            return this.ev2$3;
         }

         public Eq eqA() {
            return this.ev1$3;
         }

         public {
            this.ev2$3 = ev2$3;
            this.ev1$3 = ev1$3;
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
            MultiplicativeGroup.$init$(this);
            DivisionRing.$init$(this);
            MultiplicativeCommutativeGroup.$init$(this);
            Field.$init$(this);
            DistField.$init$(this);
         }
      };
   }

   static void $init$(final DistInstances5 $this) {
   }
}
