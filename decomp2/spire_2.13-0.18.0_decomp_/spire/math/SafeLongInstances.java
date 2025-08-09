package spire.math;

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
import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;
import spire.algebra.IsAlgebraic;
import spire.algebra.IsIntegral;
import spire.algebra.IsRational;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.UniqueFactorizationDomain;
import spire.math.prime.Factors;

@ScalaSignature(
   bytes = "\u0006\u0005A3q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003\u0017\u0001\u0011\u0005qcB\u0003\u001c\u0001!\rADB\u0003\u001f\u0001!\u0005q\u0004C\u00037\u0007\u0011\u0005qgB\u0003@\u0001!\r\u0001IB\u0003B\u0001!\u0005!\tC\u00037\r\u0011\u0005Q\tC\u0004I\u0001\t\u0007IqA%\u0003#M\u000bg-\u001a'p]\u001eLen\u001d;b]\u000e,7O\u0003\u0002\f\u0019\u0005!Q.\u0019;i\u0015\u0005i\u0011!B:qSJ,7\u0001A\n\u0003\u0001A\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0019!\t\t\u0012$\u0003\u0002\u001b%\t!QK\\5u\u0003=\u0019\u0016MZ3M_:<\u0017\t\\4fEJ\f\u0007CA\u000f\u0004\u001b\u0005\u0001!aD*bM\u0016duN\\4BY\u001e,'M]1\u0014\r\r\u0001\u0002\u0005J\u0014+!\t\t#%D\u0001\u000b\u0013\t\u0019#BA\fTC\u001a,Gj\u001c8h\u0013N,Uo\u00197jI\u0016\fgNU5oOB\u0011\u0011%J\u0005\u0003M)\u00111eU1gK2{gnZ%t+:L\u0017/^3GC\u000e$xN]5{CRLwN\u001c#p[\u0006Lg\u000e\u0005\u0002\"Q%\u0011\u0011F\u0003\u0002\u0010'\u00064W\rT8oO&\u001bhJU8piB\u00111f\r\b\u0003YEr!!\f\u0019\u000e\u00039R!a\f\b\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0012B\u0001\u001a\u0013\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001N\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005I\u0012\u0012A\u0002\u001fj]&$h\bF\u0001\u001dQ\u0011\u0019\u0011\bP\u001f\u0011\u0005EQ\u0014BA\u001e\u0013\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002Q\u0011\u0011\u0011\bP\u001f\u0002\u001dM\u000bg-\u001a'p]\u001eL5OU3bYB\u0011QD\u0002\u0002\u000f'\u00064W\rT8oO&\u001b(+Z1m'\u00111\u0001c\u0011\u0016\u0011\u0005\u0005\"\u0015BA!\u000b)\u0005\u0001\u0005\u0006\u0002\u0004:yuBC!B\u001d={\u0005Y1+\u00194f\u0019>tw\rV1h+\u0005Q\u0005cA\u0011L\u001b&\u0011AJ\u0003\u0002\n\u001dVl'-\u001a:UC\u001e\u0004\"!\t(\n\u0005=S!\u0001C*bM\u0016duN\\4"
)
public interface SafeLongInstances {
   SafeLongAlgebra$ SafeLongAlgebra();

   SafeLongIsReal$ SafeLongIsReal();

   void spire$math$SafeLongInstances$_setter_$SafeLongTag_$eq(final NumberTag x$1);

   NumberTag SafeLongTag();

   static void $init$(final SafeLongInstances $this) {
      $this.spire$math$SafeLongInstances$_setter_$SafeLongTag_$eq(new NumberTag.LargeTag(NumberTag.Integral$.MODULE$, SafeLong$.MODULE$.zero()));
   }

   public class SafeLongAlgebra$ implements SafeLongIsEuclideanRing, SafeLongIsUniqueFactorizationDomain, SafeLongIsNRoot {
      private static final long serialVersionUID = 1L;
      private SafeLong one;
      private SafeLong zero;

      public SafeLong nroot(final SafeLong a, final int k) {
         return SafeLongIsNRoot.nroot$(this, a, k);
      }

      public SafeLong fpow(final SafeLong a, final SafeLong b) {
         return SafeLongIsNRoot.fpow$(this, a, b);
      }

      public double nroot$mcD$sp(final double a, final int n) {
         return NRoot.nroot$mcD$sp$(this, a, n);
      }

      public float nroot$mcF$sp(final float a, final int n) {
         return NRoot.nroot$mcF$sp$(this, a, n);
      }

      public int nroot$mcI$sp(final int a, final int n) {
         return NRoot.nroot$mcI$sp$(this, a, n);
      }

      public long nroot$mcJ$sp(final long a, final int n) {
         return NRoot.nroot$mcJ$sp$(this, a, n);
      }

      public Object sqrt(final Object a) {
         return NRoot.sqrt$(this, a);
      }

      public double sqrt$mcD$sp(final double a) {
         return NRoot.sqrt$mcD$sp$(this, a);
      }

      public float sqrt$mcF$sp(final float a) {
         return NRoot.sqrt$mcF$sp$(this, a);
      }

      public int sqrt$mcI$sp(final int a) {
         return NRoot.sqrt$mcI$sp$(this, a);
      }

      public long sqrt$mcJ$sp(final long a) {
         return NRoot.sqrt$mcJ$sp$(this, a);
      }

      public double fpow$mcD$sp(final double a, final double b) {
         return NRoot.fpow$mcD$sp$(this, a, b);
      }

      public float fpow$mcF$sp(final float a, final float b) {
         return NRoot.fpow$mcF$sp$(this, a, b);
      }

      public int fpow$mcI$sp(final int a, final int b) {
         return NRoot.fpow$mcI$sp$(this, a, b);
      }

      public long fpow$mcJ$sp(final long a, final long b) {
         return NRoot.fpow$mcJ$sp$(this, a, b);
      }

      public boolean isPrime(final SafeLong a) {
         return SafeLongIsUniqueFactorizationDomain.isPrime$(this, a);
      }

      public Factors factor(final SafeLong a) {
         return SafeLongIsUniqueFactorizationDomain.factor$(this, a);
      }

      public boolean isPrime$mcB$sp(final byte a) {
         return UniqueFactorizationDomain.isPrime$mcB$sp$(this, a);
      }

      public boolean isPrime$mcI$sp(final int a) {
         return UniqueFactorizationDomain.isPrime$mcI$sp$(this, a);
      }

      public boolean isPrime$mcJ$sp(final long a) {
         return UniqueFactorizationDomain.isPrime$mcJ$sp$(this, a);
      }

      public boolean isPrime$mcS$sp(final short a) {
         return UniqueFactorizationDomain.isPrime$mcS$sp$(this, a);
      }

      public UniqueFactorizationDomain.Decomposition factor$mcB$sp(final byte a) {
         return UniqueFactorizationDomain.factor$mcB$sp$(this, a);
      }

      public UniqueFactorizationDomain.Decomposition factor$mcI$sp(final int a) {
         return UniqueFactorizationDomain.factor$mcI$sp$(this, a);
      }

      public UniqueFactorizationDomain.Decomposition factor$mcJ$sp(final long a) {
         return UniqueFactorizationDomain.factor$mcJ$sp$(this, a);
      }

      public UniqueFactorizationDomain.Decomposition factor$mcS$sp(final short a) {
         return UniqueFactorizationDomain.factor$mcS$sp$(this, a);
      }

      public BigInt euclideanFunction(final SafeLong a) {
         return SafeLongIsEuclideanRing.euclideanFunction$(this, a);
      }

      public SafeLong equot(final SafeLong a, final SafeLong b) {
         return SafeLongIsEuclideanRing.equot$(this, a, b);
      }

      public SafeLong emod(final SafeLong a, final SafeLong b) {
         return SafeLongIsEuclideanRing.emod$(this, a, b);
      }

      public Tuple2 equotmod(final SafeLong a, final SafeLong b) {
         return SafeLongIsEuclideanRing.equotmod$(this, a, b);
      }

      public SafeLong lcm(final SafeLong a, final SafeLong b, final Eq ev) {
         return SafeLongIsEuclideanRing.lcm$(this, a, b, ev);
      }

      public SafeLong gcd(final SafeLong a, final SafeLong b, final Eq ev) {
         return SafeLongIsEuclideanRing.gcd$(this, a, b, ev);
      }

      public SafeLong minus(final SafeLong a, final SafeLong b) {
         return SafeLongIsCRing.minus$(this, a, b);
      }

      public SafeLong negate(final SafeLong a) {
         return SafeLongIsCRing.negate$(this, a);
      }

      public SafeLong plus(final SafeLong a, final SafeLong b) {
         return SafeLongIsCRing.plus$(this, a, b);
      }

      public SafeLong pow(final SafeLong a, final int b) {
         return SafeLongIsCRing.pow$(this, a, b);
      }

      public SafeLong times(final SafeLong a, final SafeLong b) {
         return SafeLongIsCRing.times$(this, a, b);
      }

      public SafeLong fromInt(final int n) {
         return SafeLongIsCRing.fromInt$(this, n);
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

      public SafeLong one() {
         return this.one;
      }

      public SafeLong zero() {
         return this.zero;
      }

      public void spire$math$SafeLongIsCRing$_setter_$one_$eq(final SafeLong x$1) {
         this.one = x$1;
      }

      public void spire$math$SafeLongIsCRing$_setter_$zero_$eq(final SafeLong x$1) {
         this.zero = x$1;
      }

      public SafeLongAlgebra$() {
         AdditiveSemigroup.$init$(this);
         AdditiveMonoid.$init$(this);
         AdditiveCommutativeSemigroup.$init$(this);
         AdditiveCommutativeMonoid.$init$(this);
         MultiplicativeSemigroup.$init$(this);
         MultiplicativeMonoid.$init$(this);
         AdditiveGroup.$init$(this);
         AdditiveCommutativeGroup.$init$(this);
         Ring.$init$(this);
         MultiplicativeCommutativeSemigroup.$init$(this);
         MultiplicativeCommutativeMonoid.$init$(this);
         EuclideanRing.$init$(this);
         SafeLongIsCRing.$init$(this);
         SafeLongIsGCDRing.$init$(this);
         SafeLongIsEuclideanRing.$init$(this);
         SafeLongIsUniqueFactorizationDomain.$init$(this);
         NRoot.$init$(this);
         SafeLongIsNRoot.$init$(this);
         Statics.releaseFence();
      }
   }

   public class SafeLongIsReal$ implements SafeLongIsReal {
      private static final long serialVersionUID = 1L;

      public double toDouble(final SafeLong n) {
         return SafeLongIsReal.toDouble$(this, n);
      }

      public BigInt toBigInt(final SafeLong n) {
         return SafeLongIsReal.toBigInt$(this, n);
      }

      public BigInt toBigIntOpt(final SafeLong n) {
         return SafeLongTruncatedDivision.toBigIntOpt$(this, n);
      }

      public SafeLong tquot(final SafeLong x, final SafeLong y) {
         return SafeLongTruncatedDivision.tquot$(this, x, y);
      }

      public SafeLong tmod(final SafeLong x, final SafeLong y) {
         return SafeLongTruncatedDivision.tmod$(this, x, y);
      }

      public Tuple2 tquotmod(final SafeLong x, final SafeLong y) {
         return SafeLongTruncatedDivision.tquotmod$(this, x, y);
      }

      public Tuple2 fquotmod(final SafeLong lhs, final SafeLong rhs) {
         return SafeLongTruncatedDivision.fquotmod$(this, lhs, rhs);
      }

      public SafeLong fquot(final SafeLong lhs, final SafeLong rhs) {
         return SafeLongTruncatedDivision.fquot$(this, lhs, rhs);
      }

      public SafeLong fmod(final SafeLong lhs, final SafeLong rhs) {
         return SafeLongTruncatedDivision.fmod$(this, lhs, rhs);
      }

      public SafeLongSigned order() {
         return SafeLongSigned.order$(this);
      }

      public SafeLongAlgebra$ additiveCommutativeMonoid() {
         return SafeLongSigned.additiveCommutativeMonoid$(this);
      }

      public int signum(final SafeLong a) {
         return SafeLongSigned.signum$(this, a);
      }

      public SafeLong abs(final SafeLong a) {
         return SafeLongSigned.abs$(this, a);
      }

      public boolean eqv(final SafeLong x, final SafeLong y) {
         return SafeLongOrder.eqv$(this, x, y);
      }

      public boolean neqv(final SafeLong x, final SafeLong y) {
         return SafeLongOrder.neqv$(this, x, y);
      }

      public boolean gt(final SafeLong x, final SafeLong y) {
         return SafeLongOrder.gt$(this, x, y);
      }

      public boolean gteqv(final SafeLong x, final SafeLong y) {
         return SafeLongOrder.gteqv$(this, x, y);
      }

      public boolean lt(final SafeLong x, final SafeLong y) {
         return SafeLongOrder.lt$(this, x, y);
      }

      public boolean lteqv(final SafeLong x, final SafeLong y) {
         return SafeLongOrder.lteqv$(this, x, y);
      }

      public int compare(final SafeLong x, final SafeLong y) {
         return SafeLongOrder.compare$(this, x, y);
      }

      public byte tquot$mcB$sp(final byte x, final byte y) {
         return TruncatedDivision.tquot$mcB$sp$(this, x, y);
      }

      public double tquot$mcD$sp(final double x, final double y) {
         return TruncatedDivision.tquot$mcD$sp$(this, x, y);
      }

      public float tquot$mcF$sp(final float x, final float y) {
         return TruncatedDivision.tquot$mcF$sp$(this, x, y);
      }

      public int tquot$mcI$sp(final int x, final int y) {
         return TruncatedDivision.tquot$mcI$sp$(this, x, y);
      }

      public long tquot$mcJ$sp(final long x, final long y) {
         return TruncatedDivision.tquot$mcJ$sp$(this, x, y);
      }

      public short tquot$mcS$sp(final short x, final short y) {
         return TruncatedDivision.tquot$mcS$sp$(this, x, y);
      }

      public byte tmod$mcB$sp(final byte x, final byte y) {
         return TruncatedDivision.tmod$mcB$sp$(this, x, y);
      }

      public double tmod$mcD$sp(final double x, final double y) {
         return TruncatedDivision.tmod$mcD$sp$(this, x, y);
      }

      public float tmod$mcF$sp(final float x, final float y) {
         return TruncatedDivision.tmod$mcF$sp$(this, x, y);
      }

      public int tmod$mcI$sp(final int x, final int y) {
         return TruncatedDivision.tmod$mcI$sp$(this, x, y);
      }

      public long tmod$mcJ$sp(final long x, final long y) {
         return TruncatedDivision.tmod$mcJ$sp$(this, x, y);
      }

      public short tmod$mcS$sp(final short x, final short y) {
         return TruncatedDivision.tmod$mcS$sp$(this, x, y);
      }

      public Tuple2 tquotmod$mcB$sp(final byte x, final byte y) {
         return TruncatedDivision.tquotmod$mcB$sp$(this, x, y);
      }

      public Tuple2 tquotmod$mcD$sp(final double x, final double y) {
         return TruncatedDivision.tquotmod$mcD$sp$(this, x, y);
      }

      public Tuple2 tquotmod$mcF$sp(final float x, final float y) {
         return TruncatedDivision.tquotmod$mcF$sp$(this, x, y);
      }

      public Tuple2 tquotmod$mcI$sp(final int x, final int y) {
         return TruncatedDivision.tquotmod$mcI$sp$(this, x, y);
      }

      public Tuple2 tquotmod$mcJ$sp(final long x, final long y) {
         return TruncatedDivision.tquotmod$mcJ$sp$(this, x, y);
      }

      public Tuple2 tquotmod$mcS$sp(final short x, final short y) {
         return TruncatedDivision.tquotmod$mcS$sp$(this, x, y);
      }

      public byte fquot$mcB$sp(final byte x, final byte y) {
         return TruncatedDivision.fquot$mcB$sp$(this, x, y);
      }

      public double fquot$mcD$sp(final double x, final double y) {
         return TruncatedDivision.fquot$mcD$sp$(this, x, y);
      }

      public float fquot$mcF$sp(final float x, final float y) {
         return TruncatedDivision.fquot$mcF$sp$(this, x, y);
      }

      public int fquot$mcI$sp(final int x, final int y) {
         return TruncatedDivision.fquot$mcI$sp$(this, x, y);
      }

      public long fquot$mcJ$sp(final long x, final long y) {
         return TruncatedDivision.fquot$mcJ$sp$(this, x, y);
      }

      public short fquot$mcS$sp(final short x, final short y) {
         return TruncatedDivision.fquot$mcS$sp$(this, x, y);
      }

      public byte fmod$mcB$sp(final byte x, final byte y) {
         return TruncatedDivision.fmod$mcB$sp$(this, x, y);
      }

      public double fmod$mcD$sp(final double x, final double y) {
         return TruncatedDivision.fmod$mcD$sp$(this, x, y);
      }

      public float fmod$mcF$sp(final float x, final float y) {
         return TruncatedDivision.fmod$mcF$sp$(this, x, y);
      }

      public int fmod$mcI$sp(final int x, final int y) {
         return TruncatedDivision.fmod$mcI$sp$(this, x, y);
      }

      public long fmod$mcJ$sp(final long x, final long y) {
         return TruncatedDivision.fmod$mcJ$sp$(this, x, y);
      }

      public short fmod$mcS$sp(final short x, final short y) {
         return TruncatedDivision.fmod$mcS$sp$(this, x, y);
      }

      public Tuple2 fquotmod$mcB$sp(final byte x, final byte y) {
         return TruncatedDivision.fquotmod$mcB$sp$(this, x, y);
      }

      public Tuple2 fquotmod$mcD$sp(final double x, final double y) {
         return TruncatedDivision.fquotmod$mcD$sp$(this, x, y);
      }

      public Tuple2 fquotmod$mcF$sp(final float x, final float y) {
         return TruncatedDivision.fquotmod$mcF$sp$(this, x, y);
      }

      public Tuple2 fquotmod$mcI$sp(final int x, final int y) {
         return TruncatedDivision.fquotmod$mcI$sp$(this, x, y);
      }

      public Tuple2 fquotmod$mcJ$sp(final long x, final long y) {
         return TruncatedDivision.fquotmod$mcJ$sp$(this, x, y);
      }

      public Tuple2 fquotmod$mcS$sp(final short x, final short y) {
         return TruncatedDivision.fquotmod$mcS$sp$(this, x, y);
      }

      public Object ceil(final Object a) {
         return IsIntegral.ceil$(this, a);
      }

      public Object floor(final Object a) {
         return IsIntegral.floor$(this, a);
      }

      public Object round(final Object a) {
         return IsIntegral.round$(this, a);
      }

      public boolean isWhole(final Object a) {
         return IsIntegral.isWhole$(this, a);
      }

      public Rational toRational(final Object a) {
         return IsIntegral.toRational$(this, a);
      }

      public Algebraic toAlgebraic(final Object a) {
         return IsRational.toAlgebraic$(this, a);
      }

      public Algebraic toAlgebraic$mcZ$sp(final boolean a) {
         return IsAlgebraic.toAlgebraic$mcZ$sp$(this, a);
      }

      public Algebraic toAlgebraic$mcB$sp(final byte a) {
         return IsAlgebraic.toAlgebraic$mcB$sp$(this, a);
      }

      public Algebraic toAlgebraic$mcC$sp(final char a) {
         return IsAlgebraic.toAlgebraic$mcC$sp$(this, a);
      }

      public Algebraic toAlgebraic$mcD$sp(final double a) {
         return IsAlgebraic.toAlgebraic$mcD$sp$(this, a);
      }

      public Algebraic toAlgebraic$mcF$sp(final float a) {
         return IsAlgebraic.toAlgebraic$mcF$sp$(this, a);
      }

      public Algebraic toAlgebraic$mcI$sp(final int a) {
         return IsAlgebraic.toAlgebraic$mcI$sp$(this, a);
      }

      public Algebraic toAlgebraic$mcJ$sp(final long a) {
         return IsAlgebraic.toAlgebraic$mcJ$sp$(this, a);
      }

      public Algebraic toAlgebraic$mcS$sp(final short a) {
         return IsAlgebraic.toAlgebraic$mcS$sp$(this, a);
      }

      public Algebraic toAlgebraic$mcV$sp(final BoxedUnit a) {
         return IsAlgebraic.toAlgebraic$mcV$sp$(this, a);
      }

      public Real toReal(final Object a) {
         return IsAlgebraic.toReal$(this, a);
      }

      public Real toReal$mcZ$sp(final boolean a) {
         return IsAlgebraic.toReal$mcZ$sp$(this, a);
      }

      public Real toReal$mcB$sp(final byte a) {
         return IsAlgebraic.toReal$mcB$sp$(this, a);
      }

      public Real toReal$mcC$sp(final char a) {
         return IsAlgebraic.toReal$mcC$sp$(this, a);
      }

      public Real toReal$mcD$sp(final double a) {
         return IsAlgebraic.toReal$mcD$sp$(this, a);
      }

      public Real toReal$mcF$sp(final float a) {
         return IsAlgebraic.toReal$mcF$sp$(this, a);
      }

      public Real toReal$mcI$sp(final int a) {
         return IsAlgebraic.toReal$mcI$sp$(this, a);
      }

      public Real toReal$mcJ$sp(final long a) {
         return IsAlgebraic.toReal$mcJ$sp$(this, a);
      }

      public Real toReal$mcS$sp(final short a) {
         return IsAlgebraic.toReal$mcS$sp$(this, a);
      }

      public Real toReal$mcV$sp(final BoxedUnit a) {
         return IsAlgebraic.toReal$mcV$sp$(this, a);
      }

      public boolean ceil$mcZ$sp(final boolean a) {
         return IsReal.ceil$mcZ$sp$(this, a);
      }

      public byte ceil$mcB$sp(final byte a) {
         return IsReal.ceil$mcB$sp$(this, a);
      }

      public char ceil$mcC$sp(final char a) {
         return IsReal.ceil$mcC$sp$(this, a);
      }

      public double ceil$mcD$sp(final double a) {
         return IsReal.ceil$mcD$sp$(this, a);
      }

      public float ceil$mcF$sp(final float a) {
         return IsReal.ceil$mcF$sp$(this, a);
      }

      public int ceil$mcI$sp(final int a) {
         return IsReal.ceil$mcI$sp$(this, a);
      }

      public long ceil$mcJ$sp(final long a) {
         return IsReal.ceil$mcJ$sp$(this, a);
      }

      public short ceil$mcS$sp(final short a) {
         return IsReal.ceil$mcS$sp$(this, a);
      }

      public void ceil$mcV$sp(final BoxedUnit a) {
         IsReal.ceil$mcV$sp$(this, a);
      }

      public boolean floor$mcZ$sp(final boolean a) {
         return IsReal.floor$mcZ$sp$(this, a);
      }

      public byte floor$mcB$sp(final byte a) {
         return IsReal.floor$mcB$sp$(this, a);
      }

      public char floor$mcC$sp(final char a) {
         return IsReal.floor$mcC$sp$(this, a);
      }

      public double floor$mcD$sp(final double a) {
         return IsReal.floor$mcD$sp$(this, a);
      }

      public float floor$mcF$sp(final float a) {
         return IsReal.floor$mcF$sp$(this, a);
      }

      public int floor$mcI$sp(final int a) {
         return IsReal.floor$mcI$sp$(this, a);
      }

      public long floor$mcJ$sp(final long a) {
         return IsReal.floor$mcJ$sp$(this, a);
      }

      public short floor$mcS$sp(final short a) {
         return IsReal.floor$mcS$sp$(this, a);
      }

      public void floor$mcV$sp(final BoxedUnit a) {
         IsReal.floor$mcV$sp$(this, a);
      }

      public boolean round$mcZ$sp(final boolean a) {
         return IsReal.round$mcZ$sp$(this, a);
      }

      public byte round$mcB$sp(final byte a) {
         return IsReal.round$mcB$sp$(this, a);
      }

      public char round$mcC$sp(final char a) {
         return IsReal.round$mcC$sp$(this, a);
      }

      public double round$mcD$sp(final double a) {
         return IsReal.round$mcD$sp$(this, a);
      }

      public float round$mcF$sp(final float a) {
         return IsReal.round$mcF$sp$(this, a);
      }

      public int round$mcI$sp(final int a) {
         return IsReal.round$mcI$sp$(this, a);
      }

      public long round$mcJ$sp(final long a) {
         return IsReal.round$mcJ$sp$(this, a);
      }

      public short round$mcS$sp(final short a) {
         return IsReal.round$mcS$sp$(this, a);
      }

      public void round$mcV$sp(final BoxedUnit a) {
         IsReal.round$mcV$sp$(this, a);
      }

      public boolean isWhole$mcZ$sp(final boolean a) {
         return IsReal.isWhole$mcZ$sp$(this, a);
      }

      public boolean isWhole$mcB$sp(final byte a) {
         return IsReal.isWhole$mcB$sp$(this, a);
      }

      public boolean isWhole$mcC$sp(final char a) {
         return IsReal.isWhole$mcC$sp$(this, a);
      }

      public boolean isWhole$mcD$sp(final double a) {
         return IsReal.isWhole$mcD$sp$(this, a);
      }

      public boolean isWhole$mcF$sp(final float a) {
         return IsReal.isWhole$mcF$sp$(this, a);
      }

      public boolean isWhole$mcI$sp(final int a) {
         return IsReal.isWhole$mcI$sp$(this, a);
      }

      public boolean isWhole$mcJ$sp(final long a) {
         return IsReal.isWhole$mcJ$sp$(this, a);
      }

      public boolean isWhole$mcS$sp(final short a) {
         return IsReal.isWhole$mcS$sp$(this, a);
      }

      public boolean isWhole$mcV$sp(final BoxedUnit a) {
         return IsReal.isWhole$mcV$sp$(this, a);
      }

      public double toDouble$mcZ$sp(final boolean a) {
         return IsReal.toDouble$mcZ$sp$(this, a);
      }

      public double toDouble$mcB$sp(final byte a) {
         return IsReal.toDouble$mcB$sp$(this, a);
      }

      public double toDouble$mcC$sp(final char a) {
         return IsReal.toDouble$mcC$sp$(this, a);
      }

      public double toDouble$mcD$sp(final double a) {
         return IsReal.toDouble$mcD$sp$(this, a);
      }

      public double toDouble$mcF$sp(final float a) {
         return IsReal.toDouble$mcF$sp$(this, a);
      }

      public double toDouble$mcI$sp(final int a) {
         return IsReal.toDouble$mcI$sp$(this, a);
      }

      public double toDouble$mcJ$sp(final long a) {
         return IsReal.toDouble$mcJ$sp$(this, a);
      }

      public double toDouble$mcS$sp(final short a) {
         return IsReal.toDouble$mcS$sp$(this, a);
      }

      public double toDouble$mcV$sp(final BoxedUnit a) {
         return IsReal.toDouble$mcV$sp$(this, a);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcB$sp() {
         return Signed.additiveCommutativeMonoid$mcB$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcD$sp() {
         return Signed.additiveCommutativeMonoid$mcD$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcF$sp() {
         return Signed.additiveCommutativeMonoid$mcF$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcI$sp() {
         return Signed.additiveCommutativeMonoid$mcI$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcJ$sp() {
         return Signed.additiveCommutativeMonoid$mcJ$sp$(this);
      }

      public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcS$sp() {
         return Signed.additiveCommutativeMonoid$mcS$sp$(this);
      }

      public Order order$mcB$sp() {
         return Signed.order$mcB$sp$(this);
      }

      public Order order$mcD$sp() {
         return Signed.order$mcD$sp$(this);
      }

      public Order order$mcF$sp() {
         return Signed.order$mcF$sp$(this);
      }

      public Order order$mcI$sp() {
         return Signed.order$mcI$sp$(this);
      }

      public Order order$mcJ$sp() {
         return Signed.order$mcJ$sp$(this);
      }

      public Order order$mcS$sp() {
         return Signed.order$mcS$sp$(this);
      }

      public Signed.Sign sign(final Object a) {
         return Signed.sign$(this, a);
      }

      public Signed.Sign sign$mcB$sp(final byte a) {
         return Signed.sign$mcB$sp$(this, a);
      }

      public Signed.Sign sign$mcD$sp(final double a) {
         return Signed.sign$mcD$sp$(this, a);
      }

      public Signed.Sign sign$mcF$sp(final float a) {
         return Signed.sign$mcF$sp$(this, a);
      }

      public Signed.Sign sign$mcI$sp(final int a) {
         return Signed.sign$mcI$sp$(this, a);
      }

      public Signed.Sign sign$mcJ$sp(final long a) {
         return Signed.sign$mcJ$sp$(this, a);
      }

      public Signed.Sign sign$mcS$sp(final short a) {
         return Signed.sign$mcS$sp$(this, a);
      }

      public int signum$mcB$sp(final byte a) {
         return Signed.signum$mcB$sp$(this, a);
      }

      public int signum$mcD$sp(final double a) {
         return Signed.signum$mcD$sp$(this, a);
      }

      public int signum$mcF$sp(final float a) {
         return Signed.signum$mcF$sp$(this, a);
      }

      public int signum$mcI$sp(final int a) {
         return Signed.signum$mcI$sp$(this, a);
      }

      public int signum$mcJ$sp(final long a) {
         return Signed.signum$mcJ$sp$(this, a);
      }

      public int signum$mcS$sp(final short a) {
         return Signed.signum$mcS$sp$(this, a);
      }

      public byte abs$mcB$sp(final byte a) {
         return Signed.abs$mcB$sp$(this, a);
      }

      public double abs$mcD$sp(final double a) {
         return Signed.abs$mcD$sp$(this, a);
      }

      public float abs$mcF$sp(final float a) {
         return Signed.abs$mcF$sp$(this, a);
      }

      public int abs$mcI$sp(final int a) {
         return Signed.abs$mcI$sp$(this, a);
      }

      public long abs$mcJ$sp(final long a) {
         return Signed.abs$mcJ$sp$(this, a);
      }

      public short abs$mcS$sp(final short a) {
         return Signed.abs$mcS$sp$(this, a);
      }

      public boolean isSignZero(final Object a) {
         return Signed.isSignZero$(this, a);
      }

      public boolean isSignZero$mcB$sp(final byte a) {
         return Signed.isSignZero$mcB$sp$(this, a);
      }

      public boolean isSignZero$mcD$sp(final double a) {
         return Signed.isSignZero$mcD$sp$(this, a);
      }

      public boolean isSignZero$mcF$sp(final float a) {
         return Signed.isSignZero$mcF$sp$(this, a);
      }

      public boolean isSignZero$mcI$sp(final int a) {
         return Signed.isSignZero$mcI$sp$(this, a);
      }

      public boolean isSignZero$mcJ$sp(final long a) {
         return Signed.isSignZero$mcJ$sp$(this, a);
      }

      public boolean isSignZero$mcS$sp(final short a) {
         return Signed.isSignZero$mcS$sp$(this, a);
      }

      public boolean isSignPositive(final Object a) {
         return Signed.isSignPositive$(this, a);
      }

      public boolean isSignPositive$mcB$sp(final byte a) {
         return Signed.isSignPositive$mcB$sp$(this, a);
      }

      public boolean isSignPositive$mcD$sp(final double a) {
         return Signed.isSignPositive$mcD$sp$(this, a);
      }

      public boolean isSignPositive$mcF$sp(final float a) {
         return Signed.isSignPositive$mcF$sp$(this, a);
      }

      public boolean isSignPositive$mcI$sp(final int a) {
         return Signed.isSignPositive$mcI$sp$(this, a);
      }

      public boolean isSignPositive$mcJ$sp(final long a) {
         return Signed.isSignPositive$mcJ$sp$(this, a);
      }

      public boolean isSignPositive$mcS$sp(final short a) {
         return Signed.isSignPositive$mcS$sp$(this, a);
      }

      public boolean isSignNegative(final Object a) {
         return Signed.isSignNegative$(this, a);
      }

      public boolean isSignNegative$mcB$sp(final byte a) {
         return Signed.isSignNegative$mcB$sp$(this, a);
      }

      public boolean isSignNegative$mcD$sp(final double a) {
         return Signed.isSignNegative$mcD$sp$(this, a);
      }

      public boolean isSignNegative$mcF$sp(final float a) {
         return Signed.isSignNegative$mcF$sp$(this, a);
      }

      public boolean isSignNegative$mcI$sp(final int a) {
         return Signed.isSignNegative$mcI$sp$(this, a);
      }

      public boolean isSignNegative$mcJ$sp(final long a) {
         return Signed.isSignNegative$mcJ$sp$(this, a);
      }

      public boolean isSignNegative$mcS$sp(final short a) {
         return Signed.isSignNegative$mcS$sp$(this, a);
      }

      public boolean isSignNonZero(final Object a) {
         return Signed.isSignNonZero$(this, a);
      }

      public boolean isSignNonZero$mcB$sp(final byte a) {
         return Signed.isSignNonZero$mcB$sp$(this, a);
      }

      public boolean isSignNonZero$mcD$sp(final double a) {
         return Signed.isSignNonZero$mcD$sp$(this, a);
      }

      public boolean isSignNonZero$mcF$sp(final float a) {
         return Signed.isSignNonZero$mcF$sp$(this, a);
      }

      public boolean isSignNonZero$mcI$sp(final int a) {
         return Signed.isSignNonZero$mcI$sp$(this, a);
      }

      public boolean isSignNonZero$mcJ$sp(final long a) {
         return Signed.isSignNonZero$mcJ$sp$(this, a);
      }

      public boolean isSignNonZero$mcS$sp(final short a) {
         return Signed.isSignNonZero$mcS$sp$(this, a);
      }

      public boolean isSignNonPositive(final Object a) {
         return Signed.isSignNonPositive$(this, a);
      }

      public boolean isSignNonPositive$mcB$sp(final byte a) {
         return Signed.isSignNonPositive$mcB$sp$(this, a);
      }

      public boolean isSignNonPositive$mcD$sp(final double a) {
         return Signed.isSignNonPositive$mcD$sp$(this, a);
      }

      public boolean isSignNonPositive$mcF$sp(final float a) {
         return Signed.isSignNonPositive$mcF$sp$(this, a);
      }

      public boolean isSignNonPositive$mcI$sp(final int a) {
         return Signed.isSignNonPositive$mcI$sp$(this, a);
      }

      public boolean isSignNonPositive$mcJ$sp(final long a) {
         return Signed.isSignNonPositive$mcJ$sp$(this, a);
      }

      public boolean isSignNonPositive$mcS$sp(final short a) {
         return Signed.isSignNonPositive$mcS$sp$(this, a);
      }

      public boolean isSignNonNegative(final Object a) {
         return Signed.isSignNonNegative$(this, a);
      }

      public boolean isSignNonNegative$mcB$sp(final byte a) {
         return Signed.isSignNonNegative$mcB$sp$(this, a);
      }

      public boolean isSignNonNegative$mcD$sp(final double a) {
         return Signed.isSignNonNegative$mcD$sp$(this, a);
      }

      public boolean isSignNonNegative$mcF$sp(final float a) {
         return Signed.isSignNonNegative$mcF$sp$(this, a);
      }

      public boolean isSignNonNegative$mcI$sp(final int a) {
         return Signed.isSignNonNegative$mcI$sp$(this, a);
      }

      public boolean isSignNonNegative$mcJ$sp(final long a) {
         return Signed.isSignNonNegative$mcJ$sp$(this, a);
      }

      public boolean isSignNonNegative$mcS$sp(final short a) {
         return Signed.isSignNonNegative$mcS$sp$(this, a);
      }

      public int compare$mcZ$sp(final boolean x, final boolean y) {
         return Order.compare$mcZ$sp$(this, x, y);
      }

      public int compare$mcB$sp(final byte x, final byte y) {
         return Order.compare$mcB$sp$(this, x, y);
      }

      public int compare$mcC$sp(final char x, final char y) {
         return Order.compare$mcC$sp$(this, x, y);
      }

      public int compare$mcD$sp(final double x, final double y) {
         return Order.compare$mcD$sp$(this, x, y);
      }

      public int compare$mcF$sp(final float x, final float y) {
         return Order.compare$mcF$sp$(this, x, y);
      }

      public int compare$mcI$sp(final int x, final int y) {
         return Order.compare$mcI$sp$(this, x, y);
      }

      public int compare$mcJ$sp(final long x, final long y) {
         return Order.compare$mcJ$sp$(this, x, y);
      }

      public int compare$mcS$sp(final short x, final short y) {
         return Order.compare$mcS$sp$(this, x, y);
      }

      public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.compare$mcV$sp$(this, x, y);
      }

      public Comparison comparison(final Object x, final Object y) {
         return Order.comparison$(this, x, y);
      }

      public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
         return Order.comparison$mcZ$sp$(this, x, y);
      }

      public Comparison comparison$mcB$sp(final byte x, final byte y) {
         return Order.comparison$mcB$sp$(this, x, y);
      }

      public Comparison comparison$mcC$sp(final char x, final char y) {
         return Order.comparison$mcC$sp$(this, x, y);
      }

      public Comparison comparison$mcD$sp(final double x, final double y) {
         return Order.comparison$mcD$sp$(this, x, y);
      }

      public Comparison comparison$mcF$sp(final float x, final float y) {
         return Order.comparison$mcF$sp$(this, x, y);
      }

      public Comparison comparison$mcI$sp(final int x, final int y) {
         return Order.comparison$mcI$sp$(this, x, y);
      }

      public Comparison comparison$mcJ$sp(final long x, final long y) {
         return Order.comparison$mcJ$sp$(this, x, y);
      }

      public Comparison comparison$mcS$sp(final short x, final short y) {
         return Order.comparison$mcS$sp$(this, x, y);
      }

      public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.comparison$mcV$sp$(this, x, y);
      }

      public double partialCompare(final Object x, final Object y) {
         return Order.partialCompare$(this, x, y);
      }

      public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
         return Order.partialCompare$mcZ$sp$(this, x, y);
      }

      public double partialCompare$mcB$sp(final byte x, final byte y) {
         return Order.partialCompare$mcB$sp$(this, x, y);
      }

      public double partialCompare$mcC$sp(final char x, final char y) {
         return Order.partialCompare$mcC$sp$(this, x, y);
      }

      public double partialCompare$mcD$sp(final double x, final double y) {
         return Order.partialCompare$mcD$sp$(this, x, y);
      }

      public double partialCompare$mcF$sp(final float x, final float y) {
         return Order.partialCompare$mcF$sp$(this, x, y);
      }

      public double partialCompare$mcI$sp(final int x, final int y) {
         return Order.partialCompare$mcI$sp$(this, x, y);
      }

      public double partialCompare$mcJ$sp(final long x, final long y) {
         return Order.partialCompare$mcJ$sp$(this, x, y);
      }

      public double partialCompare$mcS$sp(final short x, final short y) {
         return Order.partialCompare$mcS$sp$(this, x, y);
      }

      public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.partialCompare$mcV$sp$(this, x, y);
      }

      public Object min(final Object x, final Object y) {
         return Order.min$(this, x, y);
      }

      public boolean min$mcZ$sp(final boolean x, final boolean y) {
         return Order.min$mcZ$sp$(this, x, y);
      }

      public byte min$mcB$sp(final byte x, final byte y) {
         return Order.min$mcB$sp$(this, x, y);
      }

      public char min$mcC$sp(final char x, final char y) {
         return Order.min$mcC$sp$(this, x, y);
      }

      public double min$mcD$sp(final double x, final double y) {
         return Order.min$mcD$sp$(this, x, y);
      }

      public float min$mcF$sp(final float x, final float y) {
         return Order.min$mcF$sp$(this, x, y);
      }

      public int min$mcI$sp(final int x, final int y) {
         return Order.min$mcI$sp$(this, x, y);
      }

      public long min$mcJ$sp(final long x, final long y) {
         return Order.min$mcJ$sp$(this, x, y);
      }

      public short min$mcS$sp(final short x, final short y) {
         return Order.min$mcS$sp$(this, x, y);
      }

      public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         Order.min$mcV$sp$(this, x, y);
      }

      public Object max(final Object x, final Object y) {
         return Order.max$(this, x, y);
      }

      public boolean max$mcZ$sp(final boolean x, final boolean y) {
         return Order.max$mcZ$sp$(this, x, y);
      }

      public byte max$mcB$sp(final byte x, final byte y) {
         return Order.max$mcB$sp$(this, x, y);
      }

      public char max$mcC$sp(final char x, final char y) {
         return Order.max$mcC$sp$(this, x, y);
      }

      public double max$mcD$sp(final double x, final double y) {
         return Order.max$mcD$sp$(this, x, y);
      }

      public float max$mcF$sp(final float x, final float y) {
         return Order.max$mcF$sp$(this, x, y);
      }

      public int max$mcI$sp(final int x, final int y) {
         return Order.max$mcI$sp$(this, x, y);
      }

      public long max$mcJ$sp(final long x, final long y) {
         return Order.max$mcJ$sp$(this, x, y);
      }

      public short max$mcS$sp(final short x, final short y) {
         return Order.max$mcS$sp$(this, x, y);
      }

      public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         Order.max$mcV$sp$(this, x, y);
      }

      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.eqv$mcZ$sp$(this, x, y);
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return Order.eqv$mcB$sp$(this, x, y);
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return Order.eqv$mcC$sp$(this, x, y);
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return Order.eqv$mcD$sp$(this, x, y);
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return Order.eqv$mcF$sp$(this, x, y);
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return Order.eqv$mcI$sp$(this, x, y);
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return Order.eqv$mcJ$sp$(this, x, y);
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return Order.eqv$mcS$sp$(this, x, y);
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.eqv$mcV$sp$(this, x, y);
      }

      public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.neqv$mcZ$sp$(this, x, y);
      }

      public boolean neqv$mcB$sp(final byte x, final byte y) {
         return Order.neqv$mcB$sp$(this, x, y);
      }

      public boolean neqv$mcC$sp(final char x, final char y) {
         return Order.neqv$mcC$sp$(this, x, y);
      }

      public boolean neqv$mcD$sp(final double x, final double y) {
         return Order.neqv$mcD$sp$(this, x, y);
      }

      public boolean neqv$mcF$sp(final float x, final float y) {
         return Order.neqv$mcF$sp$(this, x, y);
      }

      public boolean neqv$mcI$sp(final int x, final int y) {
         return Order.neqv$mcI$sp$(this, x, y);
      }

      public boolean neqv$mcJ$sp(final long x, final long y) {
         return Order.neqv$mcJ$sp$(this, x, y);
      }

      public boolean neqv$mcS$sp(final short x, final short y) {
         return Order.neqv$mcS$sp$(this, x, y);
      }

      public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.neqv$mcV$sp$(this, x, y);
      }

      public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.lteqv$mcZ$sp$(this, x, y);
      }

      public boolean lteqv$mcB$sp(final byte x, final byte y) {
         return Order.lteqv$mcB$sp$(this, x, y);
      }

      public boolean lteqv$mcC$sp(final char x, final char y) {
         return Order.lteqv$mcC$sp$(this, x, y);
      }

      public boolean lteqv$mcD$sp(final double x, final double y) {
         return Order.lteqv$mcD$sp$(this, x, y);
      }

      public boolean lteqv$mcF$sp(final float x, final float y) {
         return Order.lteqv$mcF$sp$(this, x, y);
      }

      public boolean lteqv$mcI$sp(final int x, final int y) {
         return Order.lteqv$mcI$sp$(this, x, y);
      }

      public boolean lteqv$mcJ$sp(final long x, final long y) {
         return Order.lteqv$mcJ$sp$(this, x, y);
      }

      public boolean lteqv$mcS$sp(final short x, final short y) {
         return Order.lteqv$mcS$sp$(this, x, y);
      }

      public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.lteqv$mcV$sp$(this, x, y);
      }

      public boolean lt$mcZ$sp(final boolean x, final boolean y) {
         return Order.lt$mcZ$sp$(this, x, y);
      }

      public boolean lt$mcB$sp(final byte x, final byte y) {
         return Order.lt$mcB$sp$(this, x, y);
      }

      public boolean lt$mcC$sp(final char x, final char y) {
         return Order.lt$mcC$sp$(this, x, y);
      }

      public boolean lt$mcD$sp(final double x, final double y) {
         return Order.lt$mcD$sp$(this, x, y);
      }

      public boolean lt$mcF$sp(final float x, final float y) {
         return Order.lt$mcF$sp$(this, x, y);
      }

      public boolean lt$mcI$sp(final int x, final int y) {
         return Order.lt$mcI$sp$(this, x, y);
      }

      public boolean lt$mcJ$sp(final long x, final long y) {
         return Order.lt$mcJ$sp$(this, x, y);
      }

      public boolean lt$mcS$sp(final short x, final short y) {
         return Order.lt$mcS$sp$(this, x, y);
      }

      public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.lt$mcV$sp$(this, x, y);
      }

      public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
         return Order.gteqv$mcZ$sp$(this, x, y);
      }

      public boolean gteqv$mcB$sp(final byte x, final byte y) {
         return Order.gteqv$mcB$sp$(this, x, y);
      }

      public boolean gteqv$mcC$sp(final char x, final char y) {
         return Order.gteqv$mcC$sp$(this, x, y);
      }

      public boolean gteqv$mcD$sp(final double x, final double y) {
         return Order.gteqv$mcD$sp$(this, x, y);
      }

      public boolean gteqv$mcF$sp(final float x, final float y) {
         return Order.gteqv$mcF$sp$(this, x, y);
      }

      public boolean gteqv$mcI$sp(final int x, final int y) {
         return Order.gteqv$mcI$sp$(this, x, y);
      }

      public boolean gteqv$mcJ$sp(final long x, final long y) {
         return Order.gteqv$mcJ$sp$(this, x, y);
      }

      public boolean gteqv$mcS$sp(final short x, final short y) {
         return Order.gteqv$mcS$sp$(this, x, y);
      }

      public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.gteqv$mcV$sp$(this, x, y);
      }

      public boolean gt$mcZ$sp(final boolean x, final boolean y) {
         return Order.gt$mcZ$sp$(this, x, y);
      }

      public boolean gt$mcB$sp(final byte x, final byte y) {
         return Order.gt$mcB$sp$(this, x, y);
      }

      public boolean gt$mcC$sp(final char x, final char y) {
         return Order.gt$mcC$sp$(this, x, y);
      }

      public boolean gt$mcD$sp(final double x, final double y) {
         return Order.gt$mcD$sp$(this, x, y);
      }

      public boolean gt$mcF$sp(final float x, final float y) {
         return Order.gt$mcF$sp$(this, x, y);
      }

      public boolean gt$mcI$sp(final int x, final int y) {
         return Order.gt$mcI$sp$(this, x, y);
      }

      public boolean gt$mcJ$sp(final long x, final long y) {
         return Order.gt$mcJ$sp$(this, x, y);
      }

      public boolean gt$mcS$sp(final short x, final short y) {
         return Order.gt$mcS$sp$(this, x, y);
      }

      public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return Order.gt$mcV$sp$(this, x, y);
      }

      public Ordering toOrdering() {
         return Order.toOrdering$(this);
      }

      public Option partialComparison(final Object x, final Object y) {
         return PartialOrder.partialComparison$(this, x, y);
      }

      public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
      }

      public Option partialComparison$mcB$sp(final byte x, final byte y) {
         return PartialOrder.partialComparison$mcB$sp$(this, x, y);
      }

      public Option partialComparison$mcC$sp(final char x, final char y) {
         return PartialOrder.partialComparison$mcC$sp$(this, x, y);
      }

      public Option partialComparison$mcD$sp(final double x, final double y) {
         return PartialOrder.partialComparison$mcD$sp$(this, x, y);
      }

      public Option partialComparison$mcF$sp(final float x, final float y) {
         return PartialOrder.partialComparison$mcF$sp$(this, x, y);
      }

      public Option partialComparison$mcI$sp(final int x, final int y) {
         return PartialOrder.partialComparison$mcI$sp$(this, x, y);
      }

      public Option partialComparison$mcJ$sp(final long x, final long y) {
         return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
      }

      public Option partialComparison$mcS$sp(final short x, final short y) {
         return PartialOrder.partialComparison$mcS$sp$(this, x, y);
      }

      public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.partialComparison$mcV$sp$(this, x, y);
      }

      public Option tryCompare(final Object x, final Object y) {
         return PartialOrder.tryCompare$(this, x, y);
      }

      public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
      }

      public Option tryCompare$mcB$sp(final byte x, final byte y) {
         return PartialOrder.tryCompare$mcB$sp$(this, x, y);
      }

      public Option tryCompare$mcC$sp(final char x, final char y) {
         return PartialOrder.tryCompare$mcC$sp$(this, x, y);
      }

      public Option tryCompare$mcD$sp(final double x, final double y) {
         return PartialOrder.tryCompare$mcD$sp$(this, x, y);
      }

      public Option tryCompare$mcF$sp(final float x, final float y) {
         return PartialOrder.tryCompare$mcF$sp$(this, x, y);
      }

      public Option tryCompare$mcI$sp(final int x, final int y) {
         return PartialOrder.tryCompare$mcI$sp$(this, x, y);
      }

      public Option tryCompare$mcJ$sp(final long x, final long y) {
         return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
      }

      public Option tryCompare$mcS$sp(final short x, final short y) {
         return PartialOrder.tryCompare$mcS$sp$(this, x, y);
      }

      public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.tryCompare$mcV$sp$(this, x, y);
      }

      public Option pmin(final Object x, final Object y) {
         return PartialOrder.pmin$(this, x, y);
      }

      public Option pmin$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.pmin$mcZ$sp$(this, x, y);
      }

      public Option pmin$mcB$sp(final byte x, final byte y) {
         return PartialOrder.pmin$mcB$sp$(this, x, y);
      }

      public Option pmin$mcC$sp(final char x, final char y) {
         return PartialOrder.pmin$mcC$sp$(this, x, y);
      }

      public Option pmin$mcD$sp(final double x, final double y) {
         return PartialOrder.pmin$mcD$sp$(this, x, y);
      }

      public Option pmin$mcF$sp(final float x, final float y) {
         return PartialOrder.pmin$mcF$sp$(this, x, y);
      }

      public Option pmin$mcI$sp(final int x, final int y) {
         return PartialOrder.pmin$mcI$sp$(this, x, y);
      }

      public Option pmin$mcJ$sp(final long x, final long y) {
         return PartialOrder.pmin$mcJ$sp$(this, x, y);
      }

      public Option pmin$mcS$sp(final short x, final short y) {
         return PartialOrder.pmin$mcS$sp$(this, x, y);
      }

      public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.pmin$mcV$sp$(this, x, y);
      }

      public Option pmax(final Object x, final Object y) {
         return PartialOrder.pmax$(this, x, y);
      }

      public Option pmax$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.pmax$mcZ$sp$(this, x, y);
      }

      public Option pmax$mcB$sp(final byte x, final byte y) {
         return PartialOrder.pmax$mcB$sp$(this, x, y);
      }

      public Option pmax$mcC$sp(final char x, final char y) {
         return PartialOrder.pmax$mcC$sp$(this, x, y);
      }

      public Option pmax$mcD$sp(final double x, final double y) {
         return PartialOrder.pmax$mcD$sp$(this, x, y);
      }

      public Option pmax$mcF$sp(final float x, final float y) {
         return PartialOrder.pmax$mcF$sp$(this, x, y);
      }

      public Option pmax$mcI$sp(final int x, final int y) {
         return PartialOrder.pmax$mcI$sp$(this, x, y);
      }

      public Option pmax$mcJ$sp(final long x, final long y) {
         return PartialOrder.pmax$mcJ$sp$(this, x, y);
      }

      public Option pmax$mcS$sp(final short x, final short y) {
         return PartialOrder.pmax$mcS$sp$(this, x, y);
      }

      public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.pmax$mcV$sp$(this, x, y);
      }

      public SafeLongIsReal$() {
         Eq.$init$(this);
         PartialOrder.$init$(this);
         Order.$init$(this);
         Signed.$init$(this);
         IsAlgebraic.$init$(this);
         IsRational.$init$(this);
         IsIntegral.$init$(this);
         TruncatedDivision.$init$(this);
         SafeLongOrder.$init$(this);
         SafeLongSigned.$init$(this);
         SafeLongTruncatedDivision.$init$(this);
         SafeLongIsReal.$init$(this);
      }
   }
}
