package spire.algebra;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.Field;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;

public interface InnerProductSpace$mcF$sp extends InnerProductSpace, VectorSpace$mcF$sp {
   // $FF: synthetic method
   static NormedVectorSpace normed$(final InnerProductSpace$mcF$sp $this, final NRoot ev) {
      return $this.normed(ev);
   }

   default NormedVectorSpace normed(final NRoot ev) {
      return this.normed$mcF$sp(ev);
   }

   // $FF: synthetic method
   static NormedVectorSpace normed$mcF$sp$(final InnerProductSpace$mcF$sp $this, final NRoot ev) {
      return $this.normed$mcF$sp(ev);
   }

   default NormedVectorSpace normed$mcF$sp(final NRoot ev) {
      return new NormedInnerProductSpace$mcF$sp(ev) {
         // $FF: synthetic field
         private final InnerProductSpace$mcF$sp $outer;
         private final NRoot ev$3;

         public Field scalar() {
            return NormedInnerProductSpace$mcF$sp.scalar$(this);
         }

         public Field scalar$mcF$sp() {
            return NormedInnerProductSpace$mcF$sp.scalar$mcF$sp$(this);
         }

         public Object timesl(final float f, final Object v) {
            return NormedInnerProductSpace$mcF$sp.timesl$(this, f, v);
         }

         public Object timesl$mcF$sp(final float f, final Object v) {
            return NormedInnerProductSpace$mcF$sp.timesl$mcF$sp$(this, f, v);
         }

         public Object divr(final Object v, final float f) {
            return NormedInnerProductSpace$mcF$sp.divr$(this, v, f);
         }

         public Object divr$mcF$sp(final Object v, final float f) {
            return NormedInnerProductSpace$mcF$sp.divr$mcF$sp$(this, v, f);
         }

         public float norm(final Object v) {
            return NormedInnerProductSpace$mcF$sp.norm$(this, v);
         }

         public float norm$mcF$sp(final Object v) {
            return NormedInnerProductSpace$mcF$sp.norm$mcF$sp$(this, v);
         }

         public float distance(final Object v, final Object w) {
            return NormedVectorSpace$mcF$sp.distance$(this, v, w);
         }

         public float distance$mcF$sp(final Object v, final Object w) {
            return NormedVectorSpace$mcF$sp.distance$mcF$sp$(this, v, w);
         }

         public Object timesr(final Object v, final float r) {
            return CModule$mcF$sp.timesr$(this, v, r);
         }

         public Object timesr$mcF$sp(final Object v, final float r) {
            return CModule$mcF$sp.timesr$mcF$sp$(this, v, r);
         }

         public InnerProductSpace space$mcD$sp() {
            return NormedInnerProductSpace.space$mcD$sp$(this);
         }

         public Field scalar$mcD$sp() {
            return NormedInnerProductSpace.scalar$mcD$sp$(this);
         }

         public NRoot nroot$mcD$sp() {
            return NormedInnerProductSpace.nroot$mcD$sp$(this);
         }

         public Object zero() {
            return NormedInnerProductSpace.zero$(this);
         }

         public Object plus(final Object v, final Object w) {
            return NormedInnerProductSpace.plus$(this, v, w);
         }

         public Object negate(final Object v) {
            return NormedInnerProductSpace.negate$(this, v);
         }

         public Object minus(final Object v, final Object w) {
            return NormedInnerProductSpace.minus$(this, v, w);
         }

         public Object timesl$mcD$sp(final double f, final Object v) {
            return NormedInnerProductSpace.timesl$mcD$sp$(this, f, v);
         }

         public Object divr$mcD$sp(final Object v, final double f) {
            return NormedInnerProductSpace.divr$mcD$sp$(this, v, f);
         }

         public double norm$mcD$sp(final Object v) {
            return NormedInnerProductSpace.norm$mcD$sp$(this, v);
         }

         public int norm$mcI$sp(final Object v) {
            return NormedVectorSpace.norm$mcI$sp$(this, v);
         }

         public long norm$mcJ$sp(final Object v) {
            return NormedVectorSpace.norm$mcJ$sp$(this, v);
         }

         public Object normalize(final Object v) {
            return NormedVectorSpace.normalize$(this, v);
         }

         public double distance$mcD$sp(final Object v, final Object w) {
            return NormedVectorSpace.distance$mcD$sp$(this, v, w);
         }

         public int distance$mcI$sp(final Object v, final Object w) {
            return NormedVectorSpace.distance$mcI$sp$(this, v, w);
         }

         public long distance$mcJ$sp(final Object v, final Object w) {
            return NormedVectorSpace.distance$mcJ$sp$(this, v, w);
         }

         public Field scalar$mcI$sp() {
            return VectorSpace.scalar$mcI$sp$(this);
         }

         public Field scalar$mcJ$sp() {
            return VectorSpace.scalar$mcJ$sp$(this);
         }

         public Object divr$mcI$sp(final Object v, final int f) {
            return VectorSpace.divr$mcI$sp$(this, v, f);
         }

         public Object divr$mcJ$sp(final Object v, final long f) {
            return VectorSpace.divr$mcJ$sp$(this, v, f);
         }

         public Object timesr$mcD$sp(final Object v, final double r) {
            return CModule.timesr$mcD$sp$(this, v, r);
         }

         public Object timesr$mcI$sp(final Object v, final int r) {
            return CModule.timesr$mcI$sp$(this, v, r);
         }

         public Object timesr$mcJ$sp(final Object v, final long r) {
            return CModule.timesr$mcJ$sp$(this, v, r);
         }

         public Object timesl$mcI$sp(final int r, final Object v) {
            return LeftModule.timesl$mcI$sp$(this, r, v);
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

         public InnerProductSpace space() {
            return this.space$mcF$sp();
         }

         public NRoot nroot() {
            return this.nroot$mcF$sp();
         }

         public InnerProductSpace space$mcF$sp() {
            return this.$outer;
         }

         public NRoot nroot$mcF$sp() {
            return this.ev$3;
         }

         public {
            if (InnerProductSpace$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = InnerProductSpace$mcF$sp.this;
               this.ev$3 = ev$3;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveGroup.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
               AdditiveCommutativeMonoid.$init$(this);
               AdditiveCommutativeGroup.$init$(this);
               CModule.$init$(this);
               VectorSpace.$init$(this);
               NormedVectorSpace.$init$(this);
               NormedInnerProductSpace.$init$(this);
            }
         }
      };
   }
}
