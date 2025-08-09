package algebra.instances;

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
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%a\u0001B\n\u0015\u0001eAQ!\u000e\u0001\u0005\u0002YBq!\u000f\u0001C\u0002\u0013\u0005!\b\u0003\u0004<\u0001\u0001\u0006IA\n\u0005\by\u0001\u0011\r\u0011\"\u0001;\u0011\u0019i\u0004\u0001)A\u0005M!)a\b\u0001C\u0001\u007f!)A\t\u0001C\u0001\u000b\")q\t\u0001C!\u0011\")1\n\u0001C\u0001\u0019\")q\n\u0001C!!\")q\u000b\u0001C!1\")1\f\u0001C!9\")a\f\u0001C!?\")A\u000e\u0001C![\")!\u000f\u0001C\u0001g\")Q\u000f\u0001C!m\")A\u0010\u0001C\u0001{\"9\u0011\u0011\u0001\u0001\u0005\u0002\u0005\r!!\u0004\"jO&sG/\u00117hK\n\u0014\u0018M\u0003\u0002\u0016-\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0002/\u00059\u0011\r\\4fEJ\f7\u0001A\n\u0005\u0001i\u0001#\u0007\u0005\u0002\u001c=5\tADC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0013\tyBD\u0001\u0004B]f\u0014VM\u001a\t\u0004C\u00112S\"\u0001\u0012\u000b\u0005\r2\u0012\u0001\u0002:j]\u001eL!!\n\u0012\u0003\u001b\u0015+8\r\\5eK\u0006t'+\u001b8h!\t9sF\u0004\u0002)[9\u0011\u0011\u0006L\u0007\u0002U)\u00111\u0006G\u0001\u0007yI|w\u000e\u001e \n\u0003uI!A\f\u000f\u0002\u000fA\f7m[1hK&\u0011\u0001'\r\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u00059b\u0002CA\u00144\u0013\t!\u0014G\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002oA\u0011\u0001\bA\u0007\u0002)\u0005!!0\u001a:p+\u00051\u0013!\u0002>fe>\u0004\u0013aA8oK\u0006!qN\\3!\u0003\u0011\u0001H.^:\u0015\u0007\u0019\u0002%\tC\u0003B\r\u0001\u0007a%A\u0001b\u0011\u0015\u0019e\u00011\u0001'\u0003\u0005\u0011\u0017A\u00028fO\u0006$X\r\u0006\u0002'\r\")\u0011i\u0002a\u0001M\u0005)Q.\u001b8vgR\u0019a%\u0013&\t\u000b\u0005C\u0001\u0019\u0001\u0014\t\u000b\rC\u0001\u0019\u0001\u0014\u0002\u000bQLW.Z:\u0015\u0007\u0019je\nC\u0003B\u0013\u0001\u0007a\u0005C\u0003D\u0013\u0001\u0007a%A\u0002q_^$2AJ)S\u0011\u0015\t%\u00021\u0001'\u0011\u0015\u0019&\u00021\u0001U\u0003\u0005Y\u0007CA\u000eV\u0013\t1FDA\u0002J]R\fqA\u001a:p[&sG\u000f\u0006\u0002'3\")!l\u0003a\u0001)\u0006\ta.\u0001\u0006ge>l')[4J]R$\"AJ/\t\u000bic\u0001\u0019\u0001\u0014\u0002\u00071\u001cW\u000eF\u0002aU.$\"AJ1\t\u000b\tl\u00019A2\u0002\u0005\u00154\bc\u00013hM9\u0011QMZ\u0007\u0002-%\u0011aFF\u0005\u0003Q&\u0014!!R9\u000b\u000592\u0002\"B!\u000e\u0001\u00041\u0003\"B\"\u000e\u0001\u00041\u0013aA4dIR\u0019a\u000e]9\u0015\u0005\u0019z\u0007\"\u00022\u000f\u0001\b\u0019\u0007\"B!\u000f\u0001\u00041\u0003\"B\"\u000f\u0001\u00041\u0013!E3vG2LG-Z1o\rVt7\r^5p]R\u0011a\u0005\u001e\u0005\u0006\u0003>\u0001\rAJ\u0001\tKF,x\u000e^7pIR\u0019qO_>\u0011\tmAhEJ\u0005\u0003sr\u0011a\u0001V;qY\u0016\u0014\u0004\"B!\u0011\u0001\u00041\u0003\"B\"\u0011\u0001\u00041\u0013!B3rk>$Hc\u0001\u0014\u007f\u007f\")\u0011)\u0005a\u0001M!)1)\u0005a\u0001M\u0005!Q-\\8e)\u00151\u0013QAA\u0004\u0011\u0015\t%\u00031\u0001'\u0011\u0015\u0019%\u00031\u0001'\u0001"
)
public class BigIntAlgebra implements EuclideanRing {
   private final BigInt zero;
   private final BigInt one;

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

   public BigInt zero() {
      return this.zero;
   }

   public BigInt one() {
      return this.one;
   }

   public BigInt plus(final BigInt a, final BigInt b) {
      return a.$plus(b);
   }

   public BigInt negate(final BigInt a) {
      return a.unary_$minus();
   }

   public BigInt minus(final BigInt a, final BigInt b) {
      return a.$minus(b);
   }

   public BigInt times(final BigInt a, final BigInt b) {
      return a.$times(b);
   }

   public BigInt pow(final BigInt a, final int k) {
      return a.pow(k);
   }

   public BigInt fromInt(final int n) {
      return .MODULE$.BigInt().apply(n);
   }

   public BigInt fromBigInt(final BigInt n) {
      return n;
   }

   public BigInt lcm(final BigInt a, final BigInt b, final Eq ev) {
      return a.signum() != 0 && b.signum() != 0 ? a.$div(a.gcd(b)).$times(b) : this.zero();
   }

   public BigInt gcd(final BigInt a, final BigInt b, final Eq ev) {
      return a.gcd(b);
   }

   public BigInt euclideanFunction(final BigInt a) {
      return a.abs();
   }

   public Tuple2 equotmod(final BigInt a, final BigInt b) {
      Tuple2 var5 = a.$div$percent(b);
      if (var5 != null) {
         BigInt qt = (BigInt)var5._1();
         BigInt rt = (BigInt)var5._2();
         Tuple2 var3 = new Tuple2(qt, rt);
         BigInt qt = (BigInt)var3._1();
         BigInt rt = (BigInt)var3._2();
         return rt.signum() >= 0 ? new Tuple2(qt, rt) : (b.signum() > 0 ? new Tuple2(qt.$minus(scala.math.BigInt..MODULE$.int2bigInt(1)), rt.$plus(b)) : new Tuple2(qt.$plus(scala.math.BigInt..MODULE$.int2bigInt(1)), rt.$minus(b)));
      } else {
         throw new MatchError(var5);
      }
   }

   public BigInt equot(final BigInt a, final BigInt b) {
      Tuple2 var5 = a.$div$percent(b);
      if (var5 != null) {
         BigInt qt = (BigInt)var5._1();
         BigInt rt = (BigInt)var5._2();
         Tuple2 var3 = new Tuple2(qt, rt);
         BigInt qt = (BigInt)var3._1();
         BigInt rt = (BigInt)var3._2();
         return rt.signum() >= 0 ? qt : (b.signum() > 0 ? qt.$minus(scala.math.BigInt..MODULE$.int2bigInt(1)) : qt.$plus(scala.math.BigInt..MODULE$.int2bigInt(1)));
      } else {
         throw new MatchError(var5);
      }
   }

   public BigInt emod(final BigInt a, final BigInt b) {
      BigInt rt = a.$percent(b);
      return rt.signum() >= 0 ? rt : (b.$greater(scala.math.BigInt..MODULE$.int2bigInt(0)) ? rt.$plus(b) : rt.$minus(b));
   }

   public BigIntAlgebra() {
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
      this.zero = .MODULE$.BigInt().apply(0);
      this.one = .MODULE$.BigInt().apply(1);
   }
}
