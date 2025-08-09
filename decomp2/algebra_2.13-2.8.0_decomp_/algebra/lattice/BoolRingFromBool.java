package algebra.lattice;

import algebra.ring.BoolRing;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.Ring;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592A\u0001B\u0003\u0001\u0015!AA\u0005\u0001B\u0001B\u0003%Q\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003-\u0001\u0011\u0005QF\u0001\tC_>d'+\u001b8h\rJ|WNQ8pY*\u0011aaB\u0001\bY\u0006$H/[2f\u0015\u0005A\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\tY!cE\u0002\u0001\u0019y\u00012!\u0004\b\u0011\u001b\u0005)\u0011BA\b\u0006\u0005I\u0011un\u001c7S]\u001e4%o\\7HK:\u0014un\u001c7\u0011\u0005E\u0011B\u0002\u0001\u0003\u0006'\u0001\u0011\r\u0001\u0006\u0002\u0002\u0003F\u0011Qc\u0007\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\b\u001d>$\b.\u001b8h!\t1B$\u0003\u0002\u001e/\t\u0019\u0011I\\=\u0011\u0007}\u0011\u0003#D\u0001!\u0015\t\ts!\u0001\u0003sS:<\u0017BA\u0012!\u0005!\u0011un\u001c7SS:<\u0017\u0001B8sS\u001e\u00042!\u0004\u0014\u0011\u0013\t9SA\u0001\u0003C_>d\u0017A\u0002\u001fj]&$h\b\u0006\u0002+WA\u0019Q\u0002\u0001\t\t\u000b\u0011\u0012\u0001\u0019A\u0013\u0002\u0007=tW-F\u0001\u0011\u0001"
)
public class BoolRingFromBool extends BoolRngFromGenBool implements BoolRing {
   private final Bool orig;

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

   public Object one() {
      return this.orig.one();
   }

   public BoolRingFromBool(final Bool orig) {
      super(orig);
      this.orig = orig;
      MultiplicativeMonoid.$init$(this);
      Ring.$init$(this);
      MultiplicativeCommutativeMonoid.$init$(this);
   }
}
