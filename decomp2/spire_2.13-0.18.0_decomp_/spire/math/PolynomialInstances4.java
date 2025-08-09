package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
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
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.CModule$mcD$sp;
import spire.algebra.LeftModule;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005U3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\u000bQ_2Lhn\\7jC2Len\u001d;b]\u000e,7\u000f\u000e\u0006\u0003\u000b\u0019\tA!\\1uQ*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001Q\u0001\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003#Ii\u0011\u0001B\u0005\u0003'\u0011\u0011A\u0003U8ms:|W.[1m\u0013:\u001cH/\u00198dKN\u001c\u0014A\u0002\u0013j]&$H\u0005F\u0001\u0017!\tYq#\u0003\u0002\u0019\u0019\t!QK\\5u\u0003%yg/\u001a:GS\u0016dG-\u0006\u0002\u001cCQ!A\u0004\u000f\"Q!\r\tRdH\u0005\u0003=\u0011\u00111\u0003U8ms:|W.[1m\u001fZ,'OR5fY\u0012\u0004\"\u0001I\u0011\r\u0001\u0011I!E\u0001Q\u0001\u0002\u0003\u0015\ra\t\u0002\u0002\u0007F\u0011Ae\n\t\u0003\u0017\u0015J!A\n\u0007\u0003\u000f9{G\u000f[5oOB\u00111\u0002K\u0005\u0003S1\u00111!\u00118zQ\r\t3F\f\t\u0003\u00171J!!\f\u0007\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G=\u0002$'\r\b\u0003\u0017AJ!!\r\u0007\u0002\r\u0011{WO\u00197fc\u0011!3gN\u0007\u000f\u0005Q:T\"A\u001b\u000b\u0005YB\u0011A\u0002\u001fs_>$h(C\u0001\u000e\u0011\u001dI$!!AA\u0004i\n1\"\u001a<jI\u0016t7-\u001a\u00139gA\u00191hP\u0010\u000f\u0005qjT\"\u0001\u0004\n\u0005y2\u0011a\u00029bG.\fw-Z\u0005\u0003\u0001\u0006\u0013\u0001b\u00117bgN$\u0016m\u001a\u0006\u0003}\u0019Aqa\u0011\u0002\u0002\u0002\u0003\u000fA)A\u0006fm&$WM\\2fIa\"\u0004cA#N?9\u0011ai\u0013\b\u0003\u000f&s!\u0001\u000e%\n\u0003\u001dI!A\u0013\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011a\b\u0014\u0006\u0003\u0015\u001aI!AT(\u0003\u000b\u0019KW\r\u001c3\u000b\u0005yb\u0005bB)\u0003\u0003\u0003\u0005\u001dAU\u0001\fKZLG-\u001a8dK\u0012BT\u0007E\u0002F'~I!\u0001V(\u0003\u0005\u0015\u000b\b"
)
public interface PolynomialInstances4 extends PolynomialInstances3 {
   // $FF: synthetic method
   static PolynomialOverField overField$(final PolynomialInstances4 $this, final ClassTag evidence$83, final Field evidence$84, final Eq evidence$85) {
      return $this.overField(evidence$83, evidence$84, evidence$85);
   }

   default PolynomialOverField overField(final ClassTag evidence$83, final Field evidence$84, final Eq evidence$85) {
      return new PolynomialOverField(evidence$84, evidence$85, evidence$83) {
         private final Field scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial divr(final Polynomial x, final Object k) {
            return PolynomialOverField.divr$(this, x, k);
         }

         public Polynomial divr$mcD$sp(final Polynomial x, final double k) {
            return PolynomialOverField.divr$mcD$sp$(this, x, k);
         }

         public BigInt euclideanFunction(final Polynomial x) {
            return PolynomialOverField.euclideanFunction$(this, x);
         }

         public BigInt euclideanFunction$mcD$sp(final Polynomial x) {
            return PolynomialOverField.euclideanFunction$mcD$sp$(this, x);
         }

         public Polynomial equot(final Polynomial x, final Polynomial y) {
            return PolynomialOverField.equot$(this, x, y);
         }

         public Polynomial equot$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverField.equot$mcD$sp$(this, x, y);
         }

         public Polynomial emod(final Polynomial x, final Polynomial y) {
            return PolynomialOverField.emod$(this, x, y);
         }

         public Polynomial emod$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverField.emod$mcD$sp$(this, x, y);
         }

         public Tuple2 equotmod(final Polynomial x, final Polynomial y) {
            return PolynomialOverField.equotmod$(this, x, y);
         }

         public Tuple2 equotmod$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverField.equotmod$mcD$sp$(this, x, y);
         }

         public boolean specInstance$() {
            return PolynomialOverField.specInstance$$(this);
         }

         public Field scalar$mcF$sp() {
            return VectorSpace.scalar$mcF$sp$(this);
         }

         public Field scalar$mcI$sp() {
            return VectorSpace.scalar$mcI$sp$(this);
         }

         public Field scalar$mcJ$sp() {
            return VectorSpace.scalar$mcJ$sp$(this);
         }

         public Object divr$mcD$sp(final Object v, final double f) {
            return VectorSpace.divr$mcD$sp$(this, v, f);
         }

         public Object divr$mcF$sp(final Object v, final float f) {
            return VectorSpace.divr$mcF$sp$(this, v, f);
         }

         public Object divr$mcI$sp(final Object v, final int f) {
            return VectorSpace.divr$mcI$sp$(this, v, f);
         }

         public Object divr$mcJ$sp(final Object v, final long f) {
            return VectorSpace.divr$mcJ$sp$(this, v, f);
         }

         public Object timesr(final Object v, final Object r) {
            return CModule.timesr$(this, v, r);
         }

         public Object timesr$mcD$sp(final Object v, final double r) {
            return CModule.timesr$mcD$sp$(this, v, r);
         }

         public Object timesr$mcF$sp(final Object v, final float r) {
            return CModule.timesr$mcF$sp$(this, v, r);
         }

         public Object timesr$mcI$sp(final Object v, final int r) {
            return CModule.timesr$mcI$sp$(this, v, r);
         }

         public Object timesr$mcJ$sp(final Object v, final long r) {
            return CModule.timesr$mcJ$sp$(this, v, r);
         }

         public Object timesl$mcD$sp(final double r, final Object v) {
            return LeftModule.timesl$mcD$sp$(this, r, v);
         }

         public Object timesl$mcF$sp(final float r, final Object v) {
            return LeftModule.timesl$mcF$sp$(this, r, v);
         }

         public Object timesl$mcI$sp(final int r, final Object v) {
            return LeftModule.timesl$mcI$sp$(this, r, v);
         }

         public Object timesl$mcJ$sp(final long r, final Object v) {
            return LeftModule.timesl$mcJ$sp$(this, r, v);
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

         public Object gcd(final Object a, final Object b, final Eq ev) {
            return EuclideanRing.gcd$(this, a, b, ev);
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

         public Object lcm(final Object a, final Object b, final Eq ev) {
            return EuclideanRing.lcm$(this, a, b, ev);
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

         public Polynomial one() {
            return PolynomialOverRing.one$(this);
         }

         public Polynomial one$mcD$sp() {
            return PolynomialOverRing.one$mcD$sp$(this);
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

         public Field scalar() {
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
            MultiplicativeCommutativeSemigroup.$init$(this);
            MultiplicativeCommutativeMonoid.$init$(this);
            EuclideanRing.$init$(this);
            CModule.$init$(this);
            VectorSpace.$init$(this);
            PolynomialOverField.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Field().apply(evidence$84$1);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$85$1);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$83$1);
         }
      };
   }

   // $FF: synthetic method
   static PolynomialOverField overField$mDc$sp$(final PolynomialInstances4 $this, final ClassTag evidence$83, final Field evidence$84, final Eq evidence$85) {
      return $this.overField$mDc$sp(evidence$83, evidence$84, evidence$85);
   }

   default PolynomialOverField overField$mDc$sp(final ClassTag evidence$83, final Field evidence$84, final Eq evidence$85) {
      return new PolynomialOverField$mcD$sp(evidence$84, evidence$85, evidence$83) {
         private final Field scalar;
         private final Eq eq;
         private final ClassTag ct;

         public Polynomial divr(final Polynomial x, final double k) {
            return PolynomialOverField$mcD$sp.divr$(this, x, k);
         }

         public Polynomial divr$mcD$sp(final Polynomial x, final double k) {
            return PolynomialOverField$mcD$sp.divr$mcD$sp$(this, x, k);
         }

         public BigInt euclideanFunction(final Polynomial x) {
            return PolynomialOverField$mcD$sp.euclideanFunction$(this, x);
         }

         public BigInt euclideanFunction$mcD$sp(final Polynomial x) {
            return PolynomialOverField$mcD$sp.euclideanFunction$mcD$sp$(this, x);
         }

         public Polynomial equot(final Polynomial x, final Polynomial y) {
            return PolynomialOverField$mcD$sp.equot$(this, x, y);
         }

         public Polynomial equot$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverField$mcD$sp.equot$mcD$sp$(this, x, y);
         }

         public Polynomial emod(final Polynomial x, final Polynomial y) {
            return PolynomialOverField$mcD$sp.emod$(this, x, y);
         }

         public Polynomial emod$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverField$mcD$sp.emod$mcD$sp$(this, x, y);
         }

         public Tuple2 equotmod(final Polynomial x, final Polynomial y) {
            return PolynomialOverField$mcD$sp.equotmod$(this, x, y);
         }

         public Tuple2 equotmod$mcD$sp(final Polynomial x, final Polynomial y) {
            return PolynomialOverField$mcD$sp.equotmod$mcD$sp$(this, x, y);
         }

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

         public Object timesr(final Object v, final double r) {
            return CModule$mcD$sp.timesr$(this, v, r);
         }

         public Object timesr$mcD$sp(final Object v, final double r) {
            return CModule$mcD$sp.timesr$mcD$sp$(this, v, r);
         }

         public Field scalar$mcF$sp() {
            return VectorSpace.scalar$mcF$sp$(this);
         }

         public Field scalar$mcI$sp() {
            return VectorSpace.scalar$mcI$sp$(this);
         }

         public Field scalar$mcJ$sp() {
            return VectorSpace.scalar$mcJ$sp$(this);
         }

         public Object divr$mcF$sp(final Object v, final float f) {
            return VectorSpace.divr$mcF$sp$(this, v, f);
         }

         public Object divr$mcI$sp(final Object v, final int f) {
            return VectorSpace.divr$mcI$sp$(this, v, f);
         }

         public Object divr$mcJ$sp(final Object v, final long f) {
            return VectorSpace.divr$mcJ$sp$(this, v, f);
         }

         public Object timesr$mcF$sp(final Object v, final float r) {
            return CModule.timesr$mcF$sp$(this, v, r);
         }

         public Object timesr$mcI$sp(final Object v, final int r) {
            return CModule.timesr$mcI$sp$(this, v, r);
         }

         public Object timesr$mcJ$sp(final Object v, final long r) {
            return CModule.timesr$mcJ$sp$(this, v, r);
         }

         public Object timesl$mcF$sp(final float r, final Object v) {
            return LeftModule.timesl$mcF$sp$(this, r, v);
         }

         public Object timesl$mcI$sp(final int r, final Object v) {
            return LeftModule.timesl$mcI$sp$(this, r, v);
         }

         public Object timesl$mcJ$sp(final long r, final Object v) {
            return LeftModule.timesl$mcJ$sp$(this, r, v);
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

         public Object gcd(final Object a, final Object b, final Eq ev) {
            return EuclideanRing.gcd$(this, a, b, ev);
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

         public Object lcm(final Object a, final Object b, final Eq ev) {
            return EuclideanRing.lcm$(this, a, b, ev);
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

         public Field scalar() {
            return this.scalar$mcD$sp();
         }

         public Eq eq() {
            return this.eq$mcD$sp();
         }

         public ClassTag ct() {
            return this.ct;
         }

         public Field scalar$mcD$sp() {
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
            MultiplicativeCommutativeSemigroup.$init$(this);
            MultiplicativeCommutativeMonoid.$init$(this);
            EuclideanRing.$init$(this);
            CModule.$init$(this);
            VectorSpace.$init$(this);
            PolynomialOverField.$init$(this);
            this.scalar = spire.algebra.package$.MODULE$.Field().apply(evidence$84$2);
            this.eq = spire.algebra.package$.MODULE$.Eq().apply(evidence$85$2);
            this.ct = (ClassTag).MODULE$.implicitly(evidence$83$2);
         }
      };
   }

   static void $init$(final PolynomialInstances4 $this) {
   }
}
