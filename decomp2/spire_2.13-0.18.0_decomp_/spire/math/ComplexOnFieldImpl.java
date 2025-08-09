package spire.math;

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
import algebra.ring.Signed;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.Involution;
import spire.algebra.LeftModule;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4Q\u0001C\u0005\u0003\u00135A\u0001b\u0010\u0001\u0003\u0006\u0004%\u0019\u0001\u0011\u0005\t\u001b\u0002\u0011\t\u0011)A\u0005\u0003\"Aa\n\u0001BC\u0002\u0013\rq\n\u0003\u0005T\u0001\t\u0005\t\u0015!\u0003Q\u0011!!\u0006A!b\u0001\n\u0007)\u0006\u0002C-\u0001\u0005\u0003\u0005\u000b\u0011\u0002,\t\u000bi\u0003A\u0011A.\u0003%\r{W\u000e\u001d7fq>sg)[3mI&k\u0007\u000f\u001c\u0006\u0003\u0015-\tA!\\1uQ*\tA\"A\u0003ta&\u0014X-\u0006\u0002\u000f7M!\u0001aD\u000b9!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0019acF\r\u000e\u0003%I!\u0001G\u0005\u0003\u001d\r{W\u000e\u001d7fq>sg)[3mIB\u0011!d\u0007\u0007\u0001\t%a\u0002\u0001)A\u0001\u0002\u000b\u0007aDA\u0001B\u0007\u0001\t\"a\b\u0012\u0011\u0005A\u0001\u0013BA\u0011\u0012\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001E\u0012\n\u0005\u0011\n\"aA!os\"\"1DJ\u00154!\t\u0001r%\u0003\u0002)#\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019#fK\u0017-\u001d\t\u00012&\u0003\u0002-#\u0005)a\t\\8biF\"AE\f\u001a\u0013\u001d\ty#'D\u00011\u0015\t\tT$\u0001\u0004=e>|GOP\u0005\u0002%E*1\u0005N\u001b8m9\u0011\u0001#N\u0005\u0003mE\ta\u0001R8vE2,\u0017\u0007\u0002\u0013/eI\u0001\"!\u000f\u001f\u000f\u00059R\u0014BA\u001e\u0012\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005m\n\u0012AB:dC2\f'/F\u0001B!\r\u0011%*\u0007\b\u0003\u0007\"s!\u0001\u0012$\u000f\u0005=*\u0015\"\u0001\u0007\n\u0005\u001d[\u0011aB1mO\u0016\u0014'/Y\u0005\u0003w%S!aR\u0006\n\u0005-c%!\u0002$jK2$'BA\u001eJ\u0003\u001d\u00198-\u00197be\u0002\nQa\u001c:eKJ,\u0012\u0001\u0015\t\u0004\u0005FK\u0012B\u0001*M\u0005\u0015y%\u000fZ3s\u0003\u0019y'\u000fZ3sA\u000511/[4oK\u0012,\u0012A\u0016\t\u0004\u0005^K\u0012B\u0001-M\u0005\u0019\u0019\u0016n\u001a8fI\u000691/[4oK\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0001])\u0011ifl\u00181\u0011\u0007Y\u0001\u0011\u0004C\u0003@\u000f\u0001\u000f\u0011\tC\u0003O\u000f\u0001\u000f\u0001\u000bC\u0003U\u000f\u0001\u000fa\u000b\u000b\u0003\u0001E\u00164\u0007C\u0001\td\u0013\t!\u0017C\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0011\u0001"
)
public class ComplexOnFieldImpl implements ComplexOnField {
   private static final long serialVersionUID = 1L;
   public final Field scalar;
   public final Order order;
   public final Signed signed;

   public Complex fromDouble(final double n) {
      return ComplexOnField.fromDouble$(this, n);
   }

   public Complex fromDouble$mcD$sp(final double n) {
      return ComplexOnField.fromDouble$mcD$sp$(this, n);
   }

   public Complex fromDouble$mcF$sp(final double n) {
      return ComplexOnField.fromDouble$mcF$sp$(this, n);
   }

   public Complex div(final Complex a, final Complex b) {
      return ComplexOnField.div$(this, a, b);
   }

   public Complex div$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnField.div$mcD$sp$(this, a, b);
   }

   public Complex div$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnField.div$mcF$sp$(this, a, b);
   }

   public Field scalar$mcI$sp() {
      return VectorSpace.scalar$mcI$sp$(this);
   }

   public Field scalar$mcJ$sp() {
      return VectorSpace.scalar$mcJ$sp$(this);
   }

   public Object divr(final Object v, final Object f) {
      return VectorSpace.divr$(this, v, f);
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

   public BigInt euclideanFunction(final Object a) {
      return Field.euclideanFunction$(this, a);
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

   public Object equot(final Object a, final Object b) {
      return Field.equot$(this, a, b);
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

   public Object emod(final Object a, final Object b) {
      return Field.emod$(this, a, b);
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

   public Tuple2 equotmod(final Object a, final Object b) {
      return Field.equotmod$(this, a, b);
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

   public Object reciprocal(final Object x) {
      return MultiplicativeGroup.reciprocal$(this, x);
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

   public Complex minus(final Complex a, final Complex b) {
      return ComplexOnCRing.minus$(this, a, b);
   }

   public Complex minus$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.minus$mcD$sp$(this, a, b);
   }

   public Complex minus$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.minus$mcF$sp$(this, a, b);
   }

   public Complex negate(final Complex a) {
      return ComplexOnCRing.negate$(this, a);
   }

   public Complex negate$mcD$sp(final Complex a) {
      return ComplexOnCRing.negate$mcD$sp$(this, a);
   }

   public Complex negate$mcF$sp(final Complex a) {
      return ComplexOnCRing.negate$mcF$sp$(this, a);
   }

   public Complex one() {
      return ComplexOnCRing.one$(this);
   }

   public Complex one$mcD$sp() {
      return ComplexOnCRing.one$mcD$sp$(this);
   }

   public Complex one$mcF$sp() {
      return ComplexOnCRing.one$mcF$sp$(this);
   }

   public Complex plus(final Complex a, final Complex b) {
      return ComplexOnCRing.plus$(this, a, b);
   }

   public Complex plus$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.plus$mcD$sp$(this, a, b);
   }

   public Complex plus$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.plus$mcF$sp$(this, a, b);
   }

   public Complex times(final Complex a, final Complex b) {
      return ComplexOnCRing.times$(this, a, b);
   }

   public Complex times$mcD$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.times$mcD$sp$(this, a, b);
   }

   public Complex times$mcF$sp(final Complex a, final Complex b) {
      return ComplexOnCRing.times$mcF$sp$(this, a, b);
   }

   public Complex timesl(final Object a, final Complex v) {
      return ComplexOnCRing.timesl$(this, a, v);
   }

   public Complex timesl$mcD$sp(final double a, final Complex v) {
      return ComplexOnCRing.timesl$mcD$sp$(this, a, v);
   }

   public Complex timesl$mcF$sp(final float a, final Complex v) {
      return ComplexOnCRing.timesl$mcF$sp$(this, a, v);
   }

   public Complex zero() {
      return ComplexOnCRing.zero$(this);
   }

   public Complex zero$mcD$sp() {
      return ComplexOnCRing.zero$mcD$sp$(this);
   }

   public Complex zero$mcF$sp() {
      return ComplexOnCRing.zero$mcF$sp$(this);
   }

   public Complex adjoint(final Complex a) {
      return ComplexOnCRing.adjoint$(this, a);
   }

   public Complex adjoint$mcD$sp(final Complex a) {
      return ComplexOnCRing.adjoint$mcD$sp$(this, a);
   }

   public Complex adjoint$mcF$sp(final Complex a) {
      return ComplexOnCRing.adjoint$mcF$sp$(this, a);
   }

   public Complex fromInt(final int n) {
      return ComplexOnCRing.fromInt$(this, n);
   }

   public Complex fromInt$mcD$sp(final int n) {
      return ComplexOnCRing.fromInt$mcD$sp$(this, n);
   }

   public Complex fromInt$mcF$sp(final int n) {
      return ComplexOnCRing.fromInt$mcF$sp$(this, n);
   }

   public double adjoint$mcD$sp(final double a) {
      return Involution.adjoint$mcD$sp$(this, a);
   }

   public float adjoint$mcF$sp(final float a) {
      return Involution.adjoint$mcF$sp$(this, a);
   }

   public int adjoint$mcI$sp(final int a) {
      return Involution.adjoint$mcI$sp$(this, a);
   }

   public long adjoint$mcJ$sp(final long a) {
      return Involution.adjoint$mcJ$sp$(this, a);
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

   public Order order() {
      return this.order;
   }

   public Signed signed() {
      return this.signed;
   }

   public Field scalar$mcD$sp() {
      return this.scalar();
   }

   public Field scalar$mcF$sp() {
      return this.scalar();
   }

   public Order order$mcD$sp() {
      return this.order();
   }

   public Order order$mcF$sp() {
      return this.order();
   }

   public Signed signed$mcD$sp() {
      return this.signed();
   }

   public Signed signed$mcF$sp() {
      return this.signed();
   }

   public boolean specInstance$() {
      return false;
   }

   public ComplexOnFieldImpl(final Field scalar, final Order order, final Signed signed) {
      this.scalar = scalar;
      this.order = order;
      this.signed = signed;
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
      CModule.$init$(this);
      ComplexOnCRing.$init$(this);
      EuclideanRing.$init$(this);
      MultiplicativeGroup.$init$(this);
      DivisionRing.$init$(this);
      MultiplicativeCommutativeGroup.$init$(this);
      Field.$init$(this);
      VectorSpace.$init$(this);
      ComplexOnField.$init$(this);
   }
}
