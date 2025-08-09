package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRing;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.Involution;
import spire.algebra.LeftModule;

@ScalaSignature(
   bytes = "\u0006\u0005U3Q\u0001B\u0003\u0003\u000b%A\u0001b\u000f\u0001\u0003\u0006\u0004%\u0019\u0001\u0010\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005{!)!\n\u0001C\u0001\u0017\n\u00112i\\7qY\u0016DxJ\\\"SS:<\u0017*\u001c9m\u0015\t1q!\u0001\u0003nCRD'\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0016\u0005)92\u0003\u0002\u0001\f#Q\u0002\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0007c\u0001\n\u0014+5\tQ!\u0003\u0002\u0015\u000b\tq1i\\7qY\u0016DxJ\\\"SS:<\u0007C\u0001\f\u0018\u0019\u0001!\u0011\u0002\u0007\u0001!\u0002\u0003\u0005)\u0019\u0001\u000e\u0003\u0003\u0005\u001b\u0001!\u0005\u0002\u001c=A\u0011A\u0002H\u0005\u0003;5\u0011qAT8uQ&tw\r\u0005\u0002\r?%\u0011\u0001%\u0004\u0002\u0004\u0003:L\b\u0006B\f#K=\u0002\"\u0001D\u0012\n\u0005\u0011j!aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u0014(S!r!\u0001D\u0014\n\u0005!j\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u0013+]9q!a\u000b\u0018\u000e\u00031R!!L\r\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011'B\u00121cM\u0012dB\u0001\u00072\u0013\t\u0011T\"\u0001\u0004E_V\u0014G.Z\u0019\u0005I)rc\u0002\u0005\u00026q9\u0011!FN\u0005\u0003o5\tq\u0001]1dW\u0006<W-\u0003\u0002:u\ta1+\u001a:jC2L'0\u00192mK*\u0011q'D\u0001\u0007g\u000e\fG.\u0019:\u0016\u0003u\u00022A\u0010$\u0016\u001d\tyDI\u0004\u0002A\u0005:\u00111&Q\u0005\u0002\u0011%\u00111iB\u0001\bC2<WM\u0019:b\u0013\t9TI\u0003\u0002D\u000f%\u0011q\t\u0013\u0002\u0006\u0007JKgn\u001a\u0006\u0003o\u0015\u000bqa]2bY\u0006\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0002\u0019R\u0011QJ\u0014\t\u0004%\u0001)\u0002\"B\u001e\u0004\u0001\bi\u0004\u0006\u0002\u0001Q'R\u0003\"\u0001D)\n\u0005Ik!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\t\u0001"
)
public class ComplexOnCRingImpl implements ComplexOnCRing {
   private static final long serialVersionUID = 1L;
   public final CommutativeRing scalar;

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

   public CommutativeRing scalar$mcI$sp() {
      return CModule.scalar$mcI$sp$(this);
   }

   public CommutativeRing scalar$mcJ$sp() {
      return CModule.scalar$mcJ$sp$(this);
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

   public CommutativeRing scalar() {
      return this.scalar;
   }

   public CommutativeRing scalar$mcD$sp() {
      return this.scalar();
   }

   public CommutativeRing scalar$mcF$sp() {
      return this.scalar();
   }

   public boolean specInstance$() {
      return false;
   }

   public ComplexOnCRingImpl(final CommutativeRing scalar) {
      this.scalar = scalar;
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
   }
}
