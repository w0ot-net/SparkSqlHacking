package algebra.instances;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeGroup$mcD$sp;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveGroup$mcD$sp;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveMonoid$mcD$sp;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.AdditiveSemigroup$mcD$sp;
import algebra.ring.DivisionRing;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.Field$mcD$sp;
import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeGroup$mcD$sp;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeMonoid$mcD$sp;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.MultiplicativeSemigroup$mcD$sp;
import algebra.ring.Ring;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4AAD\b\u0001)!)\u0001\u0007\u0001C\u0001c!)A\u0007\u0001C\u0001k!)a\u0007\u0001C\u0001k!)q\u0007\u0001C\u0001q!)Q\b\u0001C\u0001}!)\u0001\t\u0001C!\u0003\")A\t\u0001C\u0001\u000b\")\u0001\n\u0001C\u0001\u0013\")A\n\u0001C!\u001b\")q\n\u0001C!!\")a\u000b\u0001C!/\")\u0011\f\u0001C!5\")\u0001\r\u0001C!C\niAi\\;cY\u0016\fEnZ3ce\u0006T!\u0001E\t\u0002\u0013%t7\u000f^1oG\u0016\u001c(\"\u0001\n\u0002\u000f\u0005dw-\u001a2sC\u000e\u00011\u0003\u0002\u0001\u00167\u0011\u0002\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007c\u0001\u000f C5\tQD\u0003\u0002\u001f#\u0005!!/\u001b8h\u0013\t\u0001SDA\u0003GS\u0016dG\r\u0005\u0002\u0017E%\u00111e\u0006\u0002\u0007\t>,(\r\\3\u0011\u0005\u0015jcB\u0001\u0014,\u001d\t9#&D\u0001)\u0015\tI3#\u0001\u0004=e>|GOP\u0005\u00021%\u0011AfF\u0001\ba\u0006\u001c7.Y4f\u0013\tqsF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002-/\u00051A(\u001b8jiz\"\u0012A\r\t\u0003g\u0001i\u0011aD\u0001\u0005u\u0016\u0014x.F\u0001\"\u0003\ryg.Z\u0001\u0005a2,8\u000fF\u0002\"smBQA\u000f\u0003A\u0002\u0005\n\u0011\u0001\u001f\u0005\u0006y\u0011\u0001\r!I\u0001\u0002s\u00061a.Z4bi\u0016$\"!I \t\u000bi*\u0001\u0019A\u0011\u0002\u000b5Lg.^:\u0015\u0007\u0005\u00125\tC\u0003;\r\u0001\u0007\u0011\u0005C\u0003=\r\u0001\u0007\u0011%A\u0003uS6,7\u000fF\u0002\"\r\u001eCQAO\u0004A\u0002\u0005BQ\u0001P\u0004A\u0002\u0005\n1\u0001Z5w)\r\t#j\u0013\u0005\u0006u!\u0001\r!\t\u0005\u0006y!\u0001\r!I\u0001\u000be\u0016\u001c\u0017\u000e\u001d:pG\u0006dGCA\u0011O\u0011\u0015Q\u0014\u00021\u0001\"\u0003\r\u0001xn\u001e\u000b\u0004CE\u0013\u0006\"\u0002\u001e\u000b\u0001\u0004\t\u0003\"\u0002\u001f\u000b\u0001\u0004\u0019\u0006C\u0001\fU\u0013\t)vCA\u0002J]R\fqA\u001a:p[&sG\u000f\u0006\u0002\"1\")!h\u0003a\u0001'\u0006QaM]8n\u0005&<\u0017J\u001c;\u0015\u0005\u0005Z\u0006\"\u0002/\r\u0001\u0004i\u0016!\u00018\u0011\u0005\u0015r\u0016BA00\u0005\u0019\u0011\u0015nZ%oi\u0006QaM]8n\t>,(\r\\3\u0015\u0005\u0005\u0012\u0007\"\u0002\u001e\u000e\u0001\u0004\t\u0003"
)
public class DoubleAlgebra implements Field$mcD$sp {
   public double gcd(final double a, final double b, final Eq eqA) {
      return Field$mcD$sp.gcd$(this, a, b, eqA);
   }

   public double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field$mcD$sp.gcd$mcD$sp$(this, a, b, eqA);
   }

   public double lcm(final double a, final double b, final Eq eqA) {
      return Field$mcD$sp.lcm$(this, a, b, eqA);
   }

   public double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field$mcD$sp.lcm$mcD$sp$(this, a, b, eqA);
   }

   public BigInt euclideanFunction(final double a) {
      return Field$mcD$sp.euclideanFunction$(this, a);
   }

   public BigInt euclideanFunction$mcD$sp(final double a) {
      return Field$mcD$sp.euclideanFunction$mcD$sp$(this, a);
   }

   public double equot(final double a, final double b) {
      return Field$mcD$sp.equot$(this, a, b);
   }

   public double equot$mcD$sp(final double a, final double b) {
      return Field$mcD$sp.equot$mcD$sp$(this, a, b);
   }

   public double emod(final double a, final double b) {
      return Field$mcD$sp.emod$(this, a, b);
   }

   public double emod$mcD$sp(final double a, final double b) {
      return Field$mcD$sp.emod$mcD$sp$(this, a, b);
   }

   public Tuple2 equotmod(final double a, final double b) {
      return Field$mcD$sp.equotmod$(this, a, b);
   }

   public Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return Field$mcD$sp.equotmod$mcD$sp$(this, a, b);
   }

   public CommutativeGroup additive() {
      return AdditiveCommutativeGroup$mcD$sp.additive$(this);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup$mcD$sp.additive$mcD$sp$(this);
   }

   public double sumN(final double a, final int n) {
      return AdditiveGroup$mcD$sp.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup$mcD$sp.sumN$mcD$sp$(this, a, n);
   }

   public boolean isZero(final double a, final Eq ev) {
      return AdditiveMonoid$mcD$sp.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid$mcD$sp.isZero$mcD$sp$(this, a, ev);
   }

   public double sum(final IterableOnce as) {
      return AdditiveMonoid$mcD$sp.sum$(this, as);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid$mcD$sp.sum$mcD$sp$(this, as);
   }

   public double positiveSumN(final double a, final int n) {
      return AdditiveSemigroup$mcD$sp.positiveSumN$(this, a, n);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup$mcD$sp.positiveSumN$mcD$sp$(this, a, n);
   }

   public CommutativeGroup multiplicative() {
      return MultiplicativeCommutativeGroup$mcD$sp.multiplicative$(this);
   }

   public CommutativeGroup multiplicative$mcD$sp() {
      return MultiplicativeCommutativeGroup$mcD$sp.multiplicative$mcD$sp$(this);
   }

   public boolean isOne(final double a, final Eq ev) {
      return MultiplicativeMonoid$mcD$sp.isOne$(this, a, ev);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return MultiplicativeMonoid$mcD$sp.isOne$mcD$sp$(this, a, ev);
   }

   public double product(final IterableOnce as) {
      return MultiplicativeMonoid$mcD$sp.product$(this, as);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return MultiplicativeMonoid$mcD$sp.product$mcD$sp$(this, as);
   }

   public double positivePow(final double a, final int n) {
      return MultiplicativeSemigroup$mcD$sp.positivePow$(this, a, n);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup$mcD$sp.positivePow$mcD$sp$(this, a, n);
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

   public float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field.lcm$mcF$sp$(this, a, b, eqA);
   }

   public int lcm$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.lcm$mcI$sp$(this, a, b, eqA);
   }

   public long lcm$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.lcm$mcJ$sp$(this, a, b, eqA);
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

   public float equot$mcF$sp(final float a, final float b) {
      return Field.equot$mcF$sp$(this, a, b);
   }

   public int equot$mcI$sp(final int a, final int b) {
      return Field.equot$mcI$sp$(this, a, b);
   }

   public long equot$mcJ$sp(final long a, final long b) {
      return Field.equot$mcJ$sp$(this, a, b);
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

   public Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return Field.equotmod$mcF$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return Field.equotmod$mcI$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return Field.equotmod$mcJ$sp$(this, a, b);
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

   public float reciprocal$mcF$sp(final float x) {
      return MultiplicativeGroup.reciprocal$mcF$sp$(this, x);
   }

   public int reciprocal$mcI$sp(final int x) {
      return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
   }

   public long reciprocal$mcJ$sp(final long x) {
      return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
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

   public float pow$mcF$sp(final float a, final int n) {
      return MultiplicativeGroup.pow$mcF$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
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

   public float fromBigInt$mcF$sp(final BigInt n) {
      return Ring.fromBigInt$mcF$sp$(this, n);
   }

   public int fromBigInt$mcI$sp(final BigInt n) {
      return Ring.fromBigInt$mcI$sp$(this, n);
   }

   public long fromBigInt$mcJ$sp(final BigInt n) {
      return Ring.fromBigInt$mcJ$sp$(this, n);
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

   public float negate$mcF$sp(final float x) {
      return AdditiveGroup.negate$mcF$sp$(this, x);
   }

   public int negate$mcI$sp(final int x) {
      return AdditiveGroup.negate$mcI$sp$(this, x);
   }

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
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

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveGroup.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveGroup.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
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

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
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

   public float times$mcF$sp(final float x, final float y) {
      return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
   }

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
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

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
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

   public float plus$mcF$sp(final float x, final float y) {
      return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
   }

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
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

   public double zero() {
      return this.zero$mcD$sp();
   }

   public double one() {
      return this.one$mcD$sp();
   }

   public double plus(final double x, final double y) {
      return this.plus$mcD$sp(x, y);
   }

   public double negate(final double x) {
      return this.negate$mcD$sp(x);
   }

   public double minus(final double x, final double y) {
      return this.minus$mcD$sp(x, y);
   }

   public double times(final double x, final double y) {
      return this.times$mcD$sp(x, y);
   }

   public double div(final double x, final double y) {
      return this.div$mcD$sp(x, y);
   }

   public double reciprocal(final double x) {
      return this.reciprocal$mcD$sp(x);
   }

   public double pow(final double x, final int y) {
      return this.pow$mcD$sp(x, y);
   }

   public double fromInt(final int x) {
      return this.fromInt$mcD$sp(x);
   }

   public double fromBigInt(final BigInt n) {
      return this.fromBigInt$mcD$sp(n);
   }

   public double fromDouble(final double x) {
      return this.fromDouble$mcD$sp(x);
   }

   public double zero$mcD$sp() {
      return (double)0.0F;
   }

   public double one$mcD$sp() {
      return (double)1.0F;
   }

   public double plus$mcD$sp(final double x, final double y) {
      return x + y;
   }

   public double negate$mcD$sp(final double x) {
      return -x;
   }

   public double minus$mcD$sp(final double x, final double y) {
      return x - y;
   }

   public double times$mcD$sp(final double x, final double y) {
      return x * y;
   }

   public double div$mcD$sp(final double x, final double y) {
      return x / y;
   }

   public double reciprocal$mcD$sp(final double x) {
      return (double)1.0F / x;
   }

   public double pow$mcD$sp(final double x, final int y) {
      return Math.pow(x, (double)y);
   }

   public double fromInt$mcD$sp(final int x) {
      return (double)x;
   }

   public double fromBigInt$mcD$sp(final BigInt n) {
      return n.toDouble();
   }

   public double fromDouble$mcD$sp(final double x) {
      return x;
   }

   public DoubleAlgebra() {
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
      MultiplicativeGroup.$init$(this);
      DivisionRing.$init$(this);
      MultiplicativeCommutativeGroup.$init$(this);
      Field.$init$(this);
   }
}
