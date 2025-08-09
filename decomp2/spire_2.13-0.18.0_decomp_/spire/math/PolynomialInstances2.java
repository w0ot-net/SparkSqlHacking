package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\u000bQ_2Lhn\\7jC2Len\u001d;b]\u000e,7O\r\u0006\u0003\u000b\u0019\tA!\\1uQ*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001Q\u0001\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003#Ii\u0011\u0001B\u0005\u0003'\u0011\u0011A\u0003U8ms:|W.[1m\u0013:\u001cH/\u00198dKN\f\u0014A\u0002\u0013j]&$H\u0005F\u0001\u0017!\tYq#\u0003\u0002\u0019\u0019\t!QK\\5u\u0003!yg/\u001a:SS:<WCA\u000e\")\u0011a\u0002H\u0011)\u0011\u0007Eir$\u0003\u0002\u001f\t\t\u0011\u0002k\u001c7z]>l\u0017.\u00197Pm\u0016\u0014(+\u001b8h!\t\u0001\u0013\u0005\u0004\u0001\u0005\u0013\t\u0012\u0001\u0015!A\u0001\u0006\u0004\u0019#!A\"\u0012\u0005\u0011:\u0003CA\u0006&\u0013\t1CBA\u0004O_RD\u0017N\\4\u0011\u0005-A\u0013BA\u0015\r\u0005\r\te.\u001f\u0015\u0004C-r\u0003CA\u0006-\u0013\tiCBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u00120aI\ndBA\u00061\u0013\t\tD\"\u0001\u0004E_V\u0014G.Z\u0019\u0005IM:TB\u0004\u00025o5\tQG\u0003\u00027\u0011\u00051AH]8pizJ\u0011!\u0004\u0005\bs\t\t\t\u0011q\u0001;\u0003-)g/\u001b3f]\u000e,GeN\u001c\u0011\u0007mztD\u0004\u0002={5\ta!\u0003\u0002?\r\u00059\u0001/Y2lC\u001e,\u0017B\u0001!B\u0005!\u0019E.Y:t)\u0006<'B\u0001 \u0007\u0011\u001d\u0019%!!AA\u0004\u0011\u000b1\"\u001a<jI\u0016t7-\u001a\u00138qA\u0019Q)T\u0010\u000f\u0005\u0019[eBA$J\u001d\t!\u0004*C\u0001\b\u0013\tQe!A\u0004bY\u001e,'M]1\n\u0005yb%B\u0001&\u0007\u0013\tquJ\u0001\u0003SS:<'B\u0001 M\u0011\u001d\t&!!AA\u0004I\u000b1\"\u001a<jI\u0016t7-\u001a\u00138sA\u0019QiU\u0010\n\u0005Q{%AA#r\u0001"
)
public interface PolynomialInstances2 extends PolynomialInstances1 {
   // $FF: synthetic method
   static PolynomialOverRing overRing$(final PolynomialInstances2 $this, final ClassTag evidence$77, final Ring evidence$78, final Eq evidence$79) {
      return $this.overRing(evidence$77, evidence$78, evidence$79);
   }

   default PolynomialOverRing overRing(final ClassTag evidence$77, final Ring evidence$78, final Eq evidence$79) {
      return new PolynomialOverRing(evidence$78, evidence$79, evidence$77) {
         private final Ring scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial one() {
            return PolynomialOverRing.one$(this);
         }

         public Polynomial one$mcD$sp() {
            return PolynomialOverRing.one$mcD$sp$(this);
         }

         public boolean specInstance$() {
            return PolynomialOverRing.specInstance$$(this);
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

         public Monoid multiplicative() {
            return MultiplicativeMonoid.multiplicative$(this);
         }

         public Monoid multiplicative$mcD$sp() {
            return MultiplicativeMonoid.multiplicative$mcD$sp$(this);
         }

         public Monoid multiplicative$mcF$sp() {
            return MultiplicativeMonoid.multiplicative$mcF$sp$(this);
         }

         public Monoid multiplicative$mcI$sp() {
            return MultiplicativeMonoid.multiplicative$mcI$sp$(this);
         }

         public Monoid multiplicative$mcJ$sp() {
            return MultiplicativeMonoid.multiplicative$mcJ$sp$(this);
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

         public Polynomial timesl(final Object r, final Polynomial v) {
            return PolynomialOverRng.timesl$(this, r, v);
         }

         public Polynomial timesl$mcD$sp(final double r, final Polynomial v) {
            return PolynomialOverRng.timesl$mcD$sp$(this, r, v);
         }

         public Polynomial negate(final Polynomial x) {
            return PolynomialOverRng.negate$(this, x);
         }

         public Polynomial negate$mcD$sp(final Polynomial x) {
            return PolynomialOverRng.negate$mcD$sp$(this, x);
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

         public Eq eq$mcD$sp() {
            return PolynomialOverSemiring.eq$mcD$sp$(this);
         }

         public Polynomial zero() {
            return PolynomialOverSemiring.zero$(this);
         }

         public Polynomial zero$mcD$sp() {
            return PolynomialOverSemiring.zero$mcD$sp$(this);
         }

         public Polynomial plus(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.plus$(this, x, y);
         }

         public Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.plus$mcD$sp$(this, x, y);
         }

         public Polynomial times(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.times$(this, x, y);
         }

         public Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring.times$mcD$sp$(this, x, y);
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

         public Ring scalar() {
            return this.scalar;
         }

         public Eq eq() {
            return this.eq;
         }

         public ClassTag ct() {
            return this.ct;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            PolynomialOverSemiring.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            PolynomialOverRng.$init$(this);
            MultiplicativeMonoid.$init$(this);
            Ring.$init$(this);
            PolynomialOverRing.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Ring().apply(evidence$78$1);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$79$1);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$77$1);
         }
      };
   }

   // $FF: synthetic method
   static PolynomialOverRing overRing$mDc$sp$(final PolynomialInstances2 $this, final ClassTag evidence$77, final Ring evidence$78, final Eq evidence$79) {
      return $this.overRing$mDc$sp(evidence$77, evidence$78, evidence$79);
   }

   default PolynomialOverRing overRing$mDc$sp(final ClassTag evidence$77, final Ring evidence$78, final Eq evidence$79) {
      return new PolynomialOverRing$mcD$sp(evidence$78, evidence$79, evidence$77) {
         private final Ring scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial one() {
            return PolynomialOverRing$mcD$sp.one$(this);
         }

         public Polynomial one$mcD$sp() {
            return PolynomialOverRing$mcD$sp.one$mcD$sp$(this);
         }

         public Polynomial timesl(final double r, final Polynomial v) {
            return PolynomialOverRng$mcD$sp.timesl$(this, r, v);
         }

         public Polynomial timesl$mcD$sp(final double r, final Polynomial v) {
            return PolynomialOverRng$mcD$sp.timesl$mcD$sp$(this, r, v);
         }

         public Polynomial negate(final Polynomial x) {
            return PolynomialOverRng$mcD$sp.negate$(this, x);
         }

         public Polynomial negate$mcD$sp(final Polynomial x) {
            return PolynomialOverRng$mcD$sp.negate$mcD$sp$(this, x);
         }

         public Polynomial zero() {
            return PolynomialOverSemiring$mcD$sp.zero$(this);
         }

         public Polynomial zero$mcD$sp() {
            return PolynomialOverSemiring$mcD$sp.zero$mcD$sp$(this);
         }

         public Polynomial plus(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.plus$(this, x, y);
         }

         public Polynomial plus$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.plus$mcD$sp$(this, x, y);
         }

         public Polynomial times(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.times$(this, x, y);
         }

         public Polynomial times$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverSemiring$mcD$sp.times$mcD$sp$(this, x, y);
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

         public Monoid multiplicative() {
            return MultiplicativeMonoid.multiplicative$(this);
         }

         public Monoid multiplicative$mcD$sp() {
            return MultiplicativeMonoid.multiplicative$mcD$sp$(this);
         }

         public Monoid multiplicative$mcF$sp() {
            return MultiplicativeMonoid.multiplicative$mcF$sp$(this);
         }

         public Monoid multiplicative$mcI$sp() {
            return MultiplicativeMonoid.multiplicative$mcI$sp$(this);
         }

         public Monoid multiplicative$mcJ$sp() {
            return MultiplicativeMonoid.multiplicative$mcJ$sp$(this);
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

         public Ring scalar() {
            return this.scalar$mcD$sp();
         }

         public Eq eq() {
            return this.eq$mcD$sp();
         }

         public ClassTag ct() {
            return this.ct;
         }

         public Ring scalar$mcD$sp() {
            return this.scalar;
         }

         public Eq eq$mcD$sp() {
            return this.eq;
         }

         public boolean specInstance$() {
            return true;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            PolynomialOverSemiring.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            PolynomialOverRng.$init$(this);
            MultiplicativeMonoid.$init$(this);
            Ring.$init$(this);
            PolynomialOverRing.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Ring().apply(evidence$78$2);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$79$2);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$77$2);
         }
      };
   }

   static void $init$(final PolynomialInstances2 $this) {
   }
}
