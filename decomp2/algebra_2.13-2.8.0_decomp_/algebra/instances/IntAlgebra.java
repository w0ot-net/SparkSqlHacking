package algebra.instances;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeGroup$mcI$sp;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveGroup$mcI$sp;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveMonoid$mcI$sp;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.AdditiveSemigroup$mcI$sp;
import algebra.ring.CommutativeRing$mcI$sp;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeMonoid$mcI$sp;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeMonoid$mcI$sp;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.MultiplicativeSemigroup$mcI$sp;
import algebra.ring.Ring;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3Aa\u0003\u0007\u0001#!)Q\u0006\u0001C\u0001]!)\u0011\u0007\u0001C\u0001e!)1\u0007\u0001C\u0001e!)A\u0007\u0001C\u0001k!)!\b\u0001C\u0001w!)Q\b\u0001C!}!)\u0011\t\u0001C\u0001\u0005\")Q\t\u0001C!\r\")\u0011\n\u0001C!\u0015\")Q\n\u0001C!\u001d\nQ\u0011J\u001c;BY\u001e,'M]1\u000b\u00055q\u0011!C5ogR\fgnY3t\u0015\u0005y\u0011aB1mO\u0016\u0014'/Y\u0002\u0001'\u0011\u0001!\u0003G\u0011\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\rIBDH\u0007\u00025)\u00111DD\u0001\u0005e&tw-\u0003\u0002\u001e5\ty1i\\7nkR\fG/\u001b<f%&tw\r\u0005\u0002\u0014?%\u0011\u0001\u0005\u0006\u0002\u0004\u0013:$\bC\u0001\u0012+\u001d\t\u0019\u0003F\u0004\u0002%O5\tQE\u0003\u0002'!\u00051AH]8pizJ\u0011!F\u0005\u0003SQ\tq\u0001]1dW\u0006<W-\u0003\u0002,Y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011\u0006F\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003=\u0002\"\u0001\r\u0001\u000e\u00031\tAA_3s_V\ta$A\u0002p]\u0016\fA\u0001\u001d7vgR\u0019aD\u000e\u001d\t\u000b]\"\u0001\u0019\u0001\u0010\u0002\u0003aDQ!\u000f\u0003A\u0002y\t\u0011!_\u0001\u0007]\u0016<\u0017\r^3\u0015\u0005ya\u0004\"B\u001c\u0006\u0001\u0004q\u0012!B7j]V\u001cHc\u0001\u0010@\u0001\")qG\u0002a\u0001=!)\u0011H\u0002a\u0001=\u0005)A/[7fgR\u0019ad\u0011#\t\u000b]:\u0001\u0019\u0001\u0010\t\u000be:\u0001\u0019\u0001\u0010\u0002\u0007A|w\u000fF\u0002\u001f\u000f\"CQa\u000e\u0005A\u0002yAQ!\u000f\u0005A\u0002y\tqA\u001a:p[&sG\u000f\u0006\u0002\u001f\u0017\")A*\u0003a\u0001=\u0005\ta.\u0001\u0006ge>l')[4J]R$\"AH(\t\u000b1S\u0001\u0019\u0001)\u0011\u0005\t\n\u0016B\u0001*-\u0005\u0019\u0011\u0015nZ%oi\u0002"
)
public class IntAlgebra implements CommutativeRing$mcI$sp {
   public CommutativeMonoid multiplicative() {
      return MultiplicativeCommutativeMonoid$mcI$sp.multiplicative$(this);
   }

   public CommutativeMonoid multiplicative$mcI$sp() {
      return MultiplicativeCommutativeMonoid$mcI$sp.multiplicative$mcI$sp$(this);
   }

   public boolean isOne(final int a, final Eq ev) {
      return MultiplicativeMonoid$mcI$sp.isOne$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return MultiplicativeMonoid$mcI$sp.isOne$mcI$sp$(this, a, ev);
   }

   public int product(final IterableOnce as) {
      return MultiplicativeMonoid$mcI$sp.product$(this, as);
   }

   public int product$mcI$sp(final IterableOnce as) {
      return MultiplicativeMonoid$mcI$sp.product$mcI$sp$(this, as);
   }

   public CommutativeGroup additive() {
      return AdditiveCommutativeGroup$mcI$sp.additive$(this);
   }

   public CommutativeGroup additive$mcI$sp() {
      return AdditiveCommutativeGroup$mcI$sp.additive$mcI$sp$(this);
   }

   public int sumN(final int a, final int n) {
      return AdditiveGroup$mcI$sp.sumN$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveGroup$mcI$sp.sumN$mcI$sp$(this, a, n);
   }

   public boolean isZero(final int a, final Eq ev) {
      return AdditiveMonoid$mcI$sp.isZero$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid$mcI$sp.isZero$mcI$sp$(this, a, ev);
   }

   public int sum(final IterableOnce as) {
      return AdditiveMonoid$mcI$sp.sum$(this, as);
   }

   public int sum$mcI$sp(final IterableOnce as) {
      return AdditiveMonoid$mcI$sp.sum$mcI$sp$(this, as);
   }

   public int positiveSumN(final int a, final int n) {
      return AdditiveSemigroup$mcI$sp.positiveSumN$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup$mcI$sp.positiveSumN$mcI$sp$(this, a, n);
   }

   public int positivePow(final int a, final int n) {
      return MultiplicativeSemigroup$mcI$sp.positivePow$(this, a, n);
   }

   public int positivePow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup$mcI$sp.positivePow$mcI$sp$(this, a, n);
   }

   public CommutativeMonoid multiplicative$mcD$sp() {
      return MultiplicativeCommutativeMonoid.multiplicative$mcD$sp$(this);
   }

   public CommutativeMonoid multiplicative$mcF$sp() {
      return MultiplicativeCommutativeMonoid.multiplicative$mcF$sp$(this);
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

   public long fromInt$mcJ$sp(final int n) {
      return Ring.fromInt$mcJ$sp$(this, n);
   }

   public double fromBigInt$mcD$sp(final BigInt n) {
      return Ring.fromBigInt$mcD$sp$(this, n);
   }

   public float fromBigInt$mcF$sp(final BigInt n) {
      return Ring.fromBigInt$mcF$sp$(this, n);
   }

   public long fromBigInt$mcJ$sp(final BigInt n) {
      return Ring.fromBigInt$mcJ$sp$(this, n);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup.additive$mcD$sp$(this);
   }

   public CommutativeGroup additive$mcF$sp() {
      return AdditiveCommutativeGroup.additive$mcF$sp$(this);
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

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
   }

   public double minus$mcD$sp(final double x, final double y) {
      return AdditiveGroup.minus$mcD$sp$(this, x, y);
   }

   public float minus$mcF$sp(final float x, final float y) {
      return AdditiveGroup.minus$mcF$sp$(this, x, y);
   }

   public long minus$mcJ$sp(final long x, final long y) {
      return AdditiveGroup.minus$mcJ$sp$(this, x, y);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveGroup.sumN$mcF$sp$(this, a, n);
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

   public long one$mcJ$sp() {
      return MultiplicativeMonoid.one$mcJ$sp$(this);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
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

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcD$sp$(this, as);
   }

   public float product$mcF$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcF$sp$(this, as);
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

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
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

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcF$sp$(this, as);
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

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public int zero() {
      return this.zero$mcI$sp();
   }

   public int one() {
      return this.one$mcI$sp();
   }

   public int plus(final int x, final int y) {
      return this.plus$mcI$sp(x, y);
   }

   public int negate(final int x) {
      return this.negate$mcI$sp(x);
   }

   public int minus(final int x, final int y) {
      return this.minus$mcI$sp(x, y);
   }

   public int times(final int x, final int y) {
      return this.times$mcI$sp(x, y);
   }

   public int pow(final int x, final int y) {
      return this.pow$mcI$sp(x, y);
   }

   public int fromInt(final int n) {
      return this.fromInt$mcI$sp(n);
   }

   public int fromBigInt(final BigInt n) {
      return this.fromBigInt$mcI$sp(n);
   }

   public int zero$mcI$sp() {
      return 0;
   }

   public int one$mcI$sp() {
      return 1;
   }

   public int plus$mcI$sp(final int x, final int y) {
      return x + y;
   }

   public int negate$mcI$sp(final int x) {
      return -x;
   }

   public int minus$mcI$sp(final int x, final int y) {
      return x - y;
   }

   public int times$mcI$sp(final int x, final int y) {
      return x * y;
   }

   public int pow$mcI$sp(final int x, final int y) {
      return (int)StaticMethods$.MODULE$.pow((long)x, (long)y);
   }

   public int fromInt$mcI$sp(final int n) {
      return n;
   }

   public int fromBigInt$mcI$sp(final BigInt n) {
      return n.toInt();
   }

   public IntAlgebra() {
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
   }
}
