package algebra.lattice;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeMonoid$mcI$sp;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveMonoid$mcI$sp;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.AdditiveSemigroup$mcI$sp;
import algebra.ring.CommutativeRig;
import algebra.ring.CommutativeRig$mcI$sp;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeMonoid$mcI$sp;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeMonoid$mcI$sp;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.MultiplicativeSemigroup$mcI$sp;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.collection.IterableOnce;

public interface BoundedDistributiveLattice$mcI$sp extends BoundedDistributiveLattice, DistributiveLattice$mcI$sp, BoundedLattice$mcI$sp {
   default CommutativeRig asCommutativeRig() {
      return this.asCommutativeRig$mcI$sp();
   }

   default CommutativeRig asCommutativeRig$mcI$sp() {
      return new CommutativeRig$mcI$sp() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice$mcI$sp $outer;

         public CommutativeMonoid additive() {
            return AdditiveCommutativeMonoid$mcI$sp.additive$(this);
         }

         public CommutativeMonoid additive$mcI$sp() {
            return AdditiveCommutativeMonoid$mcI$sp.additive$mcI$sp$(this);
         }

         public boolean isZero(final int a, final Eq ev) {
            return AdditiveMonoid$mcI$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return AdditiveMonoid$mcI$sp.isZero$mcI$sp$(this, a, ev);
         }

         public int sumN(final int a, final int n) {
            return AdditiveMonoid$mcI$sp.sumN$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveMonoid$mcI$sp.sumN$mcI$sp$(this, a, n);
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

         public int pow(final int a, final int n) {
            return MultiplicativeMonoid$mcI$sp.pow$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeMonoid$mcI$sp.pow$mcI$sp$(this, a, n);
         }

         public int product(final IterableOnce as) {
            return MultiplicativeMonoid$mcI$sp.product$(this, as);
         }

         public int product$mcI$sp(final IterableOnce as) {
            return MultiplicativeMonoid$mcI$sp.product$mcI$sp$(this, as);
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

         public CommutativeMonoid additive$mcD$sp() {
            return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
         }

         public CommutativeMonoid additive$mcF$sp() {
            return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
         }

         public CommutativeMonoid additive$mcJ$sp() {
            return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
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

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
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

         public int times(final int x, final int y) {
            return this.times$mcI$sp(x, y);
         }

         public int zero$mcI$sp() {
            return this.$outer.zero$mcI$sp();
         }

         public int one$mcI$sp() {
            return this.$outer.one$mcI$sp();
         }

         public int plus$mcI$sp(final int x, final int y) {
            return this.$outer.join$mcI$sp(x, y);
         }

         public int times$mcI$sp(final int x, final int y) {
            return this.$outer.meet$mcI$sp(x, y);
         }

         public {
            if (BoundedDistributiveLattice$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice$mcI$sp.this;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
               AdditiveCommutativeMonoid.$init$(this);
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeMonoid.$init$(this);
               MultiplicativeCommutativeSemigroup.$init$(this);
               MultiplicativeCommutativeMonoid.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static BoundedDistributiveLattice dual$(final BoundedDistributiveLattice$mcI$sp $this) {
      return $this.dual();
   }

   default BoundedDistributiveLattice dual() {
      return this.dual$mcI$sp();
   }

   // $FF: synthetic method
   static BoundedDistributiveLattice dual$mcI$sp$(final BoundedDistributiveLattice$mcI$sp $this) {
      return $this.dual$mcI$sp();
   }

   default BoundedDistributiveLattice dual$mcI$sp() {
      return new BoundedDistributiveLattice$mcI$sp() {
         // $FF: synthetic field
         private final BoundedDistributiveLattice$mcI$sp $outer;

         public CommutativeRig asCommutativeRig() {
            return BoundedDistributiveLattice$mcI$sp.super.asCommutativeRig();
         }

         public CommutativeRig asCommutativeRig$mcI$sp() {
            return BoundedDistributiveLattice$mcI$sp.super.asCommutativeRig$mcI$sp();
         }

         public boolean isOne(final int a, final Eq ev) {
            return BoundedMeetSemilattice$mcI$sp.isOne$(this, a, ev);
         }

         public boolean isOne$mcI$sp(final int a, final Eq ev) {
            return BoundedMeetSemilattice$mcI$sp.isOne$mcI$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice() {
            return BoundedMeetSemilattice$mcI$sp.meetSemilattice$(this);
         }

         public BoundedSemilattice meetSemilattice$mcI$sp() {
            return BoundedMeetSemilattice$mcI$sp.meetSemilattice$mcI$sp$(this);
         }

         public boolean isZero(final int a, final Eq ev) {
            return BoundedJoinSemilattice$mcI$sp.isZero$(this, a, ev);
         }

         public boolean isZero$mcI$sp(final int a, final Eq ev) {
            return BoundedJoinSemilattice$mcI$sp.isZero$mcI$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice() {
            return BoundedJoinSemilattice$mcI$sp.joinSemilattice$(this);
         }

         public BoundedSemilattice joinSemilattice$mcI$sp() {
            return BoundedJoinSemilattice$mcI$sp.joinSemilattice$mcI$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcI$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice$mcI$sp.joinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcI$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice$mcI$sp.meetPartialOrder$mcI$sp$(this, ev);
         }

         public CommutativeRig asCommutativeRig$mcD$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcD$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcF$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcF$sp$(this);
         }

         public CommutativeRig asCommutativeRig$mcJ$sp() {
            return BoundedDistributiveLattice.asCommutativeRig$mcJ$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcD$sp() {
            return BoundedDistributiveLattice.dual$mcD$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcF$sp() {
            return BoundedDistributiveLattice.dual$mcF$sp$(this);
         }

         public BoundedDistributiveLattice dual$mcJ$sp() {
            return BoundedDistributiveLattice.dual$mcJ$sp$(this);
         }

         public double zero$mcD$sp() {
            return BoundedJoinSemilattice.zero$mcD$sp$(this);
         }

         public float zero$mcF$sp() {
            return BoundedJoinSemilattice.zero$mcF$sp$(this);
         }

         public long zero$mcJ$sp() {
            return BoundedJoinSemilattice.zero$mcJ$sp$(this);
         }

         public boolean isZero$mcD$sp(final double a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
         }

         public boolean isZero$mcF$sp(final float a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
         }

         public boolean isZero$mcJ$sp(final long a, final Eq ev) {
            return BoundedJoinSemilattice.isZero$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice joinSemilattice$mcD$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcF$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice joinSemilattice$mcJ$sp() {
            return BoundedJoinSemilattice.joinSemilattice$mcJ$sp$(this);
         }

         public double one$mcD$sp() {
            return BoundedMeetSemilattice.one$mcD$sp$(this);
         }

         public float one$mcF$sp() {
            return BoundedMeetSemilattice.one$mcF$sp$(this);
         }

         public long one$mcJ$sp() {
            return BoundedMeetSemilattice.one$mcJ$sp$(this);
         }

         public boolean isOne$mcD$sp(final double a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcD$sp$(this, a, ev);
         }

         public boolean isOne$mcF$sp(final float a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcF$sp$(this, a, ev);
         }

         public boolean isOne$mcJ$sp(final long a, final Eq ev) {
            return BoundedMeetSemilattice.isOne$mcJ$sp$(this, a, ev);
         }

         public BoundedSemilattice meetSemilattice$mcD$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcF$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public BoundedSemilattice meetSemilattice$mcJ$sp() {
            return BoundedMeetSemilattice.meetSemilattice$mcJ$sp$(this);
         }

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
         }

         public long meet$mcJ$sp(final long lhs, final long rhs) {
            return MeetSemilattice.meet$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcJ$sp$(this, ev);
         }

         public double join$mcD$sp(final double lhs, final double rhs) {
            return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
         }

         public float join$mcF$sp(final float lhs, final float rhs) {
            return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
         }

         public long join$mcJ$sp(final long lhs, final long rhs) {
            return JoinSemilattice.join$mcJ$sp$(this, lhs, rhs);
         }

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public int meet(final int a, final int b) {
            return this.meet$mcI$sp(a, b);
         }

         public int join(final int a, final int b) {
            return this.join$mcI$sp(a, b);
         }

         public int one() {
            return this.one$mcI$sp();
         }

         public int zero() {
            return this.zero$mcI$sp();
         }

         public BoundedDistributiveLattice dual() {
            return this.dual$mcI$sp();
         }

         public int meet$mcI$sp(final int a, final int b) {
            return this.$outer.join$mcI$sp(a, b);
         }

         public int join$mcI$sp(final int a, final int b) {
            return this.$outer.meet$mcI$sp(a, b);
         }

         public int one$mcI$sp() {
            return this.$outer.zero$mcI$sp();
         }

         public int zero$mcI$sp() {
            return this.$outer.one$mcI$sp();
         }

         public BoundedDistributiveLattice dual$mcI$sp() {
            return this.$outer;
         }

         public {
            if (BoundedDistributiveLattice$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedDistributiveLattice$mcI$sp.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
               BoundedMeetSemilattice.$init$(this);
               BoundedJoinSemilattice.$init$(this);
               BoundedLattice.$init$(this);
               BoundedDistributiveLattice.$init$(this);
            }
         }
      };
   }
}
