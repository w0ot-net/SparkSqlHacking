package algebra.instances;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeGroup$mcF$sp;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveGroup$mcF$sp;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveMonoid$mcF$sp;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.AdditiveSemigroup$mcF$sp;
import algebra.ring.DivisionRing;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.Field$mcF$sp;
import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeGroup$mcF$sp;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeMonoid$mcF$sp;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.MultiplicativeSemigroup$mcF$sp;
import algebra.ring.Ring;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194AAD\b\u0001)!)\u0001\u0007\u0001C\u0001c!)A\u0007\u0001C\u0001k!)a\u0007\u0001C\u0001k!)q\u0007\u0001C\u0001q!)Q\b\u0001C\u0001}!)\u0001\t\u0001C!\u0003\")A\t\u0001C\u0001\u000b\")\u0001\n\u0001C\u0001\u0013\")A\n\u0001C!\u001b\")q\n\u0001C!!\")a\u000b\u0001C!/\")\u0011\f\u0001C!5\")\u0001\r\u0001C!C\naa\t\\8bi\u0006cw-\u001a2sC*\u0011\u0001#E\u0001\nS:\u001cH/\u00198dKNT\u0011AE\u0001\bC2<WM\u0019:b\u0007\u0001\u0019B\u0001A\u000b\u001cIA\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\u00042\u0001H\u0010\"\u001b\u0005i\"B\u0001\u0010\u0012\u0003\u0011\u0011\u0018N\\4\n\u0005\u0001j\"!\u0002$jK2$\u0007C\u0001\f#\u0013\t\u0019sCA\u0003GY>\fG\u000f\u0005\u0002&[9\u0011ae\u000b\b\u0003O)j\u0011\u0001\u000b\u0006\u0003SM\ta\u0001\u0010:p_Rt\u0014\"\u0001\r\n\u00051:\u0012a\u00029bG.\fw-Z\u0005\u0003]=\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001L\f\u0002\rqJg.\u001b;?)\u0005\u0011\u0004CA\u001a\u0001\u001b\u0005y\u0011\u0001\u0002>fe>,\u0012!I\u0001\u0004_:,\u0017\u0001\u00029mkN$2!I\u001d<\u0011\u0015QD\u00011\u0001\"\u0003\u0005A\b\"\u0002\u001f\u0005\u0001\u0004\t\u0013!A=\u0002\r9,w-\u0019;f)\t\ts\bC\u0003;\u000b\u0001\u0007\u0011%A\u0003nS:,8\u000fF\u0002\"\u0005\u000eCQA\u000f\u0004A\u0002\u0005BQ\u0001\u0010\u0004A\u0002\u0005\nQ\u0001^5nKN$2!\t$H\u0011\u0015Qt\u00011\u0001\"\u0011\u0015at\u00011\u0001\"\u0003\r!\u0017N\u001e\u000b\u0004C)[\u0005\"\u0002\u001e\t\u0001\u0004\t\u0003\"\u0002\u001f\t\u0001\u0004\t\u0013A\u0003:fG&\u0004(o\\2bYR\u0011\u0011E\u0014\u0005\u0006u%\u0001\r!I\u0001\u0004a><HcA\u0011R%\")!H\u0003a\u0001C!)AH\u0003a\u0001'B\u0011a\u0003V\u0005\u0003+^\u00111!\u00138u\u0003\u001d1'o\\7J]R$\"!\t-\t\u000biZ\u0001\u0019A*\u0002\u0015\u0019\u0014x.\u001c\"jO&sG\u000f\u0006\u0002\"7\")A\f\u0004a\u0001;\u0006\ta\u000e\u0005\u0002&=&\u0011ql\f\u0002\u0007\u0005&<\u0017J\u001c;\u0002\u0015\u0019\u0014x.\u001c#pk\ndW\r\u0006\u0002\"E\")!(\u0004a\u0001GB\u0011a\u0003Z\u0005\u0003K^\u0011a\u0001R8vE2,\u0007"
)
public class FloatAlgebra implements Field$mcF$sp {
   public float gcd(final float a, final float b, final Eq eqA) {
      return Field$mcF$sp.gcd$(this, a, b, eqA);
   }

   public float gcd$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field$mcF$sp.gcd$mcF$sp$(this, a, b, eqA);
   }

   public float lcm(final float a, final float b, final Eq eqA) {
      return Field$mcF$sp.lcm$(this, a, b, eqA);
   }

   public float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field$mcF$sp.lcm$mcF$sp$(this, a, b, eqA);
   }

   public BigInt euclideanFunction(final float a) {
      return Field$mcF$sp.euclideanFunction$(this, a);
   }

   public BigInt euclideanFunction$mcF$sp(final float a) {
      return Field$mcF$sp.euclideanFunction$mcF$sp$(this, a);
   }

   public float equot(final float a, final float b) {
      return Field$mcF$sp.equot$(this, a, b);
   }

   public float equot$mcF$sp(final float a, final float b) {
      return Field$mcF$sp.equot$mcF$sp$(this, a, b);
   }

   public float emod(final float a, final float b) {
      return Field$mcF$sp.emod$(this, a, b);
   }

   public float emod$mcF$sp(final float a, final float b) {
      return Field$mcF$sp.emod$mcF$sp$(this, a, b);
   }

   public Tuple2 equotmod(final float a, final float b) {
      return Field$mcF$sp.equotmod$(this, a, b);
   }

   public Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return Field$mcF$sp.equotmod$mcF$sp$(this, a, b);
   }

   public CommutativeGroup additive() {
      return AdditiveCommutativeGroup$mcF$sp.additive$(this);
   }

   public CommutativeGroup additive$mcF$sp() {
      return AdditiveCommutativeGroup$mcF$sp.additive$mcF$sp$(this);
   }

   public float sumN(final float a, final int n) {
      return AdditiveGroup$mcF$sp.sumN$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveGroup$mcF$sp.sumN$mcF$sp$(this, a, n);
   }

   public boolean isZero(final float a, final Eq ev) {
      return AdditiveMonoid$mcF$sp.isZero$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid$mcF$sp.isZero$mcF$sp$(this, a, ev);
   }

   public float sum(final IterableOnce as) {
      return AdditiveMonoid$mcF$sp.sum$(this, as);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return AdditiveMonoid$mcF$sp.sum$mcF$sp$(this, as);
   }

   public float positiveSumN(final float a, final int n) {
      return AdditiveSemigroup$mcF$sp.positiveSumN$(this, a, n);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return AdditiveSemigroup$mcF$sp.positiveSumN$mcF$sp$(this, a, n);
   }

   public CommutativeGroup multiplicative() {
      return MultiplicativeCommutativeGroup$mcF$sp.multiplicative$(this);
   }

   public CommutativeGroup multiplicative$mcF$sp() {
      return MultiplicativeCommutativeGroup$mcF$sp.multiplicative$mcF$sp$(this);
   }

   public boolean isOne(final float a, final Eq ev) {
      return MultiplicativeMonoid$mcF$sp.isOne$(this, a, ev);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return MultiplicativeMonoid$mcF$sp.isOne$mcF$sp$(this, a, ev);
   }

   public float product(final IterableOnce as) {
      return MultiplicativeMonoid$mcF$sp.product$(this, as);
   }

   public float product$mcF$sp(final IterableOnce as) {
      return MultiplicativeMonoid$mcF$sp.product$mcF$sp$(this, as);
   }

   public float positivePow(final float a, final int n) {
      return MultiplicativeSemigroup$mcF$sp.positivePow$(this, a, n);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup$mcF$sp.positivePow$mcF$sp$(this, a, n);
   }

   public double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field.gcd$mcD$sp$(this, a, b, eqA);
   }

   public int gcd$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.gcd$mcI$sp$(this, a, b, eqA);
   }

   public long gcd$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.gcd$mcJ$sp$(this, a, b, eqA);
   }

   public double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field.lcm$mcD$sp$(this, a, b, eqA);
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

   public BigInt euclideanFunction$mcI$sp(final int a) {
      return Field.euclideanFunction$mcI$sp$(this, a);
   }

   public BigInt euclideanFunction$mcJ$sp(final long a) {
      return Field.euclideanFunction$mcJ$sp$(this, a);
   }

   public double equot$mcD$sp(final double a, final double b) {
      return Field.equot$mcD$sp$(this, a, b);
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

   public int emod$mcI$sp(final int a, final int b) {
      return Field.emod$mcI$sp$(this, a, b);
   }

   public long emod$mcJ$sp(final long a, final long b) {
      return Field.emod$mcJ$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return Field.equotmod$mcD$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return Field.equotmod$mcI$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return Field.equotmod$mcJ$sp$(this, a, b);
   }

   public double fromDouble$mcD$sp(final double a) {
      return Field.fromDouble$mcD$sp$(this, a);
   }

   public int fromDouble$mcI$sp(final double a) {
      return Field.fromDouble$mcI$sp$(this, a);
   }

   public long fromDouble$mcJ$sp(final double a) {
      return Field.fromDouble$mcJ$sp$(this, a);
   }

   public CommutativeGroup multiplicative$mcD$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcD$sp$(this);
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

   public int reciprocal$mcI$sp(final int x) {
      return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
   }

   public long reciprocal$mcJ$sp(final long x) {
      return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
   }

   public double div$mcD$sp(final double x, final double y) {
      return MultiplicativeGroup.div$mcD$sp$(this, x, y);
   }

   public int div$mcI$sp(final int x, final int y) {
      return MultiplicativeGroup.div$mcI$sp$(this, x, y);
   }

   public long div$mcJ$sp(final long x, final long y) {
      return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
   }

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
   }

   public double fromInt$mcD$sp(final int n) {
      return Ring.fromInt$mcD$sp$(this, n);
   }

   public int fromInt$mcI$sp(final int n) {
      return Ring.fromInt$mcI$sp$(this, n);
   }

   public long fromInt$mcJ$sp(final int n) {
      return Ring.fromInt$mcJ$sp$(this, n);
   }

   public double fromBigInt$mcD$sp(final BigInt n) {
      return Ring.fromBigInt$mcD$sp$(this, n);
   }

   public int fromBigInt$mcI$sp(final BigInt n) {
      return Ring.fromBigInt$mcI$sp$(this, n);
   }

   public long fromBigInt$mcJ$sp(final BigInt n) {
      return Ring.fromBigInt$mcJ$sp$(this, n);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup.additive$mcD$sp$(this);
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

   public int negate$mcI$sp(final int x) {
      return AdditiveGroup.negate$mcI$sp$(this, x);
   }

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
   }

   public double minus$mcD$sp(final double x, final double y) {
      return AdditiveGroup.minus$mcD$sp$(this, x, y);
   }

   public int minus$mcI$sp(final int x, final int y) {
      return AdditiveGroup.minus$mcI$sp$(this, x, y);
   }

   public long minus$mcJ$sp(final long x, final long y) {
      return AdditiveGroup.minus$mcJ$sp$(this, x, y);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup.sumN$mcD$sp$(this, a, n);
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

   public int one$mcI$sp() {
      return MultiplicativeMonoid.one$mcI$sp$(this);
   }

   public long one$mcJ$sp() {
      return MultiplicativeMonoid.one$mcJ$sp$(this);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcD$sp$(this, as);
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

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
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

   public int zero$mcI$sp() {
      return AdditiveMonoid.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
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

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public float zero() {
      return this.zero$mcF$sp();
   }

   public float one() {
      return this.one$mcF$sp();
   }

   public float plus(final float x, final float y) {
      return this.plus$mcF$sp(x, y);
   }

   public float negate(final float x) {
      return this.negate$mcF$sp(x);
   }

   public float minus(final float x, final float y) {
      return this.minus$mcF$sp(x, y);
   }

   public float times(final float x, final float y) {
      return this.times$mcF$sp(x, y);
   }

   public float div(final float x, final float y) {
      return this.div$mcF$sp(x, y);
   }

   public float reciprocal(final float x) {
      return this.reciprocal$mcF$sp(x);
   }

   public float pow(final float x, final int y) {
      return this.pow$mcF$sp(x, y);
   }

   public float fromInt(final int x) {
      return this.fromInt$mcF$sp(x);
   }

   public float fromBigInt(final BigInt n) {
      return this.fromBigInt$mcF$sp(n);
   }

   public float fromDouble(final double x) {
      return this.fromDouble$mcF$sp(x);
   }

   public float zero$mcF$sp() {
      return 0.0F;
   }

   public float one$mcF$sp() {
      return 1.0F;
   }

   public float plus$mcF$sp(final float x, final float y) {
      return x + y;
   }

   public float negate$mcF$sp(final float x) {
      return -x;
   }

   public float minus$mcF$sp(final float x, final float y) {
      return x - y;
   }

   public float times$mcF$sp(final float x, final float y) {
      return x * y;
   }

   public float div$mcF$sp(final float x, final float y) {
      return x / y;
   }

   public float reciprocal$mcF$sp(final float x) {
      return 1.0F / x;
   }

   public float pow$mcF$sp(final float x, final int y) {
      return (float)Math.pow((double)x, (double)y);
   }

   public float fromInt$mcF$sp(final int x) {
      return (float)x;
   }

   public float fromBigInt$mcF$sp(final BigInt n) {
      return n.toFloat();
   }

   public float fromDouble$mcF$sp(final double x) {
      return (float)x;
   }

   public FloatAlgebra() {
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
