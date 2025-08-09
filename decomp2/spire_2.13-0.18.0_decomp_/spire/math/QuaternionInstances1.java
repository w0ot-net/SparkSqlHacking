package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.DivisionRing;
import algebra.ring.Field;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import algebra.ring.Signed;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Order;
import scala.Option;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import spire.algebra.CModule;
import spire.algebra.Involution;
import spire.algebra.LeftModule;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\r3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\u000bRk\u0006$XM\u001d8j_:Len\u001d;b]\u000e,7/\r\u0006\u0003\u000b\u0019\tA!\\1uQ*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Q\u0001CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002%A\u00111bE\u0005\u0003)1\u0011A!\u00168ji\u0006\u0019\u0012+^1uKJt\u0017n\u001c8Pm\u0016\u0014h)[3mIV\u0011qC\b\u000b\u00051\u001dJd\bE\u0002\u001a5qi\u0011\u0001B\u0005\u00037\u0011\u00111#U;bi\u0016\u0014h.[8o\u001fZ,'OR5fY\u0012\u0004\"!\b\u0010\r\u0001\u0011)qD\u0001b\u0001A\t\t\u0011)\u0005\u0002\"IA\u00111BI\u0005\u0003G1\u0011qAT8uQ&tw\r\u0005\u0002\fK%\u0011a\u0005\u0004\u0002\u0004\u0003:L\b\"\u0002\u0015\u0003\u0001\bI\u0013A\u000141!\rQc\u0007\b\b\u0003WMr!\u0001L\u0019\u000f\u00055\u0002T\"\u0001\u0018\u000b\u0005=B\u0011A\u0002\u001fs_>$h(C\u0001\b\u0013\t\u0011d!A\u0004bY\u001e,'M]1\n\u0005Q*\u0014a\u00029bG.\fw-\u001a\u0006\u0003e\u0019I!a\u000e\u001d\u0003\u000b\u0019KW\r\u001c3\u000b\u0005Q*\u0004\"\u0002\u001e\u0003\u0001\bY\u0014AA81!\rQC\bH\u0005\u0003{a\u0012Qa\u0014:eKJDQa\u0010\u0002A\u0004\u0001\u000b!a\u001d\u0019\u0011\u0007)\nE$\u0003\u0002Cq\t11+[4oK\u0012\u0004"
)
public interface QuaternionInstances1 {
   // $FF: synthetic method
   static QuaternionOverField QuaternionOverField$(final QuaternionInstances1 $this, final Field f0, final Order o0, final Signed s0) {
      return $this.QuaternionOverField(f0, o0, s0);
   }

   default QuaternionOverField QuaternionOverField(final Field f0, final Order o0, final Signed s0) {
      return new QuaternionOverField(f0, o0, s0) {
         private final Field scalar;
         private final Order o;
         private final Signed s;

         public boolean eqv(final Quaternion x, final Quaternion y) {
            return QuaternionOverField.eqv$(this, x, y);
         }

         public boolean neqv(final Quaternion x, final Quaternion y) {
            return QuaternionOverField.neqv$(this, x, y);
         }

         public Quaternion minus(final Quaternion a, final Quaternion b) {
            return QuaternionOverField.minus$(this, a, b);
         }

         public Quaternion negate(final Quaternion a) {
            return QuaternionOverField.negate$(this, a);
         }

         public Quaternion one() {
            return QuaternionOverField.one$(this);
         }

         public Quaternion plus(final Quaternion a, final Quaternion b) {
            return QuaternionOverField.plus$(this, a, b);
         }

         public Quaternion pow(final Quaternion a, final int b) {
            return QuaternionOverField.pow$(this, a, b);
         }

         public Quaternion times(final Quaternion a, final Quaternion b) {
            return QuaternionOverField.times$(this, a, b);
         }

         public Quaternion zero() {
            return QuaternionOverField.zero$(this);
         }

         public Quaternion div(final Quaternion a, final Quaternion b) {
            return QuaternionOverField.div$(this, a, b);
         }

         public Quaternion timesl(final Object a, final Quaternion q) {
            return QuaternionOverField.timesl$(this, a, q);
         }

         public Object dot(final Quaternion x, final Quaternion y) {
            return QuaternionOverField.dot$(this, x, y);
         }

         public Quaternion adjoint(final Quaternion a) {
            return QuaternionOverField.adjoint$(this, a);
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

         public Field scalar$mcD$sp() {
            return VectorSpace.scalar$mcD$sp$(this);
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

         public Object fromDouble(final double a) {
            return DivisionRing.fromDouble$(this, a);
         }

         public byte fromDouble$mcB$sp(final double a) {
            return DivisionRing.fromDouble$mcB$sp$(this, a);
         }

         public double fromDouble$mcD$sp(final double a) {
            return DivisionRing.fromDouble$mcD$sp$(this, a);
         }

         public float fromDouble$mcF$sp(final double a) {
            return DivisionRing.fromDouble$mcF$sp$(this, a);
         }

         public int fromDouble$mcI$sp(final double a) {
            return DivisionRing.fromDouble$mcI$sp$(this, a);
         }

         public long fromDouble$mcJ$sp(final double a) {
            return DivisionRing.fromDouble$mcJ$sp$(this, a);
         }

         public short fromDouble$mcS$sp(final double a) {
            return DivisionRing.fromDouble$mcS$sp$(this, a);
         }

         public Group multiplicative() {
            return MultiplicativeGroup.multiplicative$(this);
         }

         public Group multiplicative$mcD$sp() {
            return MultiplicativeGroup.multiplicative$mcD$sp$(this);
         }

         public Group multiplicative$mcF$sp() {
            return MultiplicativeGroup.multiplicative$mcF$sp$(this);
         }

         public Group multiplicative$mcI$sp() {
            return MultiplicativeGroup.multiplicative$mcI$sp$(this);
         }

         public Group multiplicative$mcJ$sp() {
            return MultiplicativeGroup.multiplicative$mcJ$sp$(this);
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

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public Field scalar() {
            return this.scalar;
         }

         public Order o() {
            return this.o;
         }

         public Signed s() {
            return this.s;
         }

         public {
            Eq.$init$(this);
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            MultiplicativeMonoid.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            Ring.$init$(this);
            MultiplicativeGroup.$init$(this);
            DivisionRing.$init$(this);
            CModule.$init$(this);
            VectorSpace.$init$(this);
            QuaternionOverField.$init$(this);
            this.scalar = f0$1;
            this.o = o0$1;
            this.s = s0$1;
         }
      };
   }

   static void $init$(final QuaternionInstances1 $this) {
   }
}
