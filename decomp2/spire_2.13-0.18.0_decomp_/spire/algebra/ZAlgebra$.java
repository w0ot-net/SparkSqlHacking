package spire.algebra;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRing;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import java.io.Serializable;
import scala.Option;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.runtime.ModuleSerializationProxy;

public final class ZAlgebra$ implements Serializable {
   public static final ZAlgebra$ MODULE$ = new ZAlgebra$();

   public ZAlgebra apply(final Ring vector0, final CommutativeRing scalar0) {
      return new ZAlgebra(vector0, scalar0) {
         private final Ring vector;
         private final CommutativeRing scalar;

         public Object zero() {
            return ZAlgebra.zero$(this);
         }

         public Object one() {
            return ZAlgebra.one$(this);
         }

         public Object negate(final Object v) {
            return ZAlgebra.negate$(this, v);
         }

         public Object plus(final Object v, final Object w) {
            return ZAlgebra.plus$(this, v, w);
         }

         public Object minus(final Object v, final Object w) {
            return ZAlgebra.minus$(this, v, w);
         }

         public Object times(final Object v, final Object w) {
            return ZAlgebra.times$(this, v, w);
         }

         public Object timesl(final int r, final Object v) {
            return ZAlgebra.timesl$(this, r, v);
         }

         public Object fromInt(final int n) {
            return ZAlgebra.fromInt$(this, n);
         }

         public Object timesl$mcI$sp(final int r, final Object v) {
            return ZAlgebra.timesl$mcI$sp$(this, r, v);
         }

         public Object timesr(final Object v, final int r) {
            return CModule$mcI$sp.timesr$(this, v, r);
         }

         public Object timesr$mcI$sp(final Object v, final int r) {
            return CModule$mcI$sp.timesr$mcI$sp$(this, v, r);
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

         public Monoid multiplicative() {
            return MultiplicativeMonoid.multiplicative$(this);
         }

         public Monoid multiplicative$mcD$sp() {
            return MultiplicativeMonoid.multiplicative$mcD$sp$(this);
         }

         public Monoid multiplicative$mcF$sp() {
            return MultiplicativeMonoid.multiplicative$mcF$sp$(this);
         }

         public Monoid multiplicative$mcI$sp() {
            return MultiplicativeMonoid.multiplicative$mcI$sp$(this);
         }

         public Monoid multiplicative$mcJ$sp() {
            return MultiplicativeMonoid.multiplicative$mcJ$sp$(this);
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

         public CommutativeRing scalar$mcD$sp() {
            return CModule.scalar$mcD$sp$(this);
         }

         public CommutativeRing scalar$mcF$sp() {
            return CModule.scalar$mcF$sp$(this);
         }

         public CommutativeRing scalar$mcJ$sp() {
            return CModule.scalar$mcJ$sp$(this);
         }

         public Object timesr$mcD$sp(final Object v, final double r) {
            return CModule.timesr$mcD$sp$(this, v, r);
         }

         public Object timesr$mcF$sp(final Object v, final float r) {
            return CModule.timesr$mcF$sp$(this, v, r);
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

         public Object timesl$mcJ$sp(final long r, final Object v) {
            return LeftModule.timesl$mcJ$sp$(this, r, v);
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

         public Ring vector() {
            return this.vector;
         }

         public CommutativeRing scalar() {
            return this.scalar$mcI$sp();
         }

         public CommutativeRing scalar$mcI$sp() {
            return this.scalar;
         }

         public boolean specInstance$() {
            return false;
         }

         public {
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveGroup.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            AdditiveCommutativeGroup.$init$(this);
            CModule.$init$(this);
            MultiplicativeSemigroup.$init$(this);
            MultiplicativeMonoid.$init$(this);
            Ring.$init$(this);
            ZAlgebra.$init$(this);
            this.vector = vector0$1;
            this.scalar = scalar0$1;
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ZAlgebra$.class);
   }

   private ZAlgebra$() {
   }
}
