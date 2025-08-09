package algebra.lattice;

import cats.kernel.Band;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import cats.kernel.BoundedSemilattice.mcF.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface BoundedJoinSemilattice$mcF$sp extends BoundedJoinSemilattice, JoinSemilattice$mcF$sp {
   // $FF: synthetic method
   static boolean isZero$(final BoundedJoinSemilattice$mcF$sp $this, final float a, final Eq ev) {
      return $this.isZero(a, ev);
   }

   default boolean isZero(final float a, final Eq ev) {
      return this.isZero$mcF$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcF$sp$(final BoundedJoinSemilattice$mcF$sp $this, final float a, final Eq ev) {
      return $this.isZero$mcF$sp(a, ev);
   }

   default boolean isZero$mcF$sp(final float a, final Eq ev) {
      return ev.eqv$mcF$sp(a, this.zero$mcF$sp());
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$(final BoundedJoinSemilattice$mcF$sp $this) {
      return $this.joinSemilattice();
   }

   default BoundedSemilattice joinSemilattice() {
      return this.joinSemilattice$mcF$sp();
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$mcF$sp$(final BoundedJoinSemilattice$mcF$sp $this) {
      return $this.joinSemilattice$mcF$sp();
   }

   default BoundedSemilattice joinSemilattice$mcF$sp() {
      return new BoundedSemilattice.mcF.sp() {
         // $FF: synthetic field
         private final BoundedJoinSemilattice$mcF$sp $outer;

         public float combineN(final float a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return sp.combineN$mcF$sp$(this, a, n);
         }

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return cats.kernel.Semilattice.mcF.sp.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return cats.kernel.Semilattice.mcF.sp.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return cats.kernel.Semilattice.mcF.sp.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return cats.kernel.Semilattice.mcF.sp.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public float repeatedCombineN(final float a, final int n) {
            return cats.kernel.Band.mcF.sp.repeatedCombineN$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return cats.kernel.Band.mcF.sp.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public CommutativeMonoid reverse() {
            return cats.kernel.CommutativeMonoid.mcF.sp.reverse$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return cats.kernel.CommutativeMonoid.mcF.sp.reverse$mcF$sp$(this);
         }

         public boolean isEmpty(final float a, final Eq ev) {
            return cats.kernel.Monoid.mcF.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return cats.kernel.Monoid.mcF.sp.isEmpty$mcF$sp$(this, a, ev);
         }

         public float combineAll(final IterableOnce as) {
            return cats.kernel.Monoid.mcF.sp.combineAll$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return cats.kernel.Monoid.mcF.sp.combineAll$mcF$sp$(this, as);
         }

         public CommutativeSemigroup intercalate(final float middle) {
            return cats.kernel.CommutativeSemigroup.mcF.sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return cats.kernel.CommutativeSemigroup.mcF.sp.intercalate$mcF$sp$(this, middle);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return BoundedSemilattice.combineN$mcD$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return BoundedSemilattice.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return BoundedSemilattice.combineN$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public float empty() {
            return this.empty$mcF$sp();
         }

         public float combine(final float x, final float y) {
            return this.combine$mcF$sp(x, y);
         }

         public float empty$mcF$sp() {
            return this.$outer.zero$mcF$sp();
         }

         public float combine$mcF$sp(final float x, final float y) {
            return this.$outer.join$mcF$sp(x, y);
         }

         public {
            if (BoundedJoinSemilattice$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedJoinSemilattice$mcF$sp.this;
               Semigroup.$init$(this);
               Band.$init$(this);
               CommutativeSemigroup.$init$(this);
               Semilattice.$init$(this);
               Monoid.$init$(this);
               CommutativeMonoid.$init$(this);
               BoundedSemilattice.$init$(this);
            }
         }
      };
   }
}
