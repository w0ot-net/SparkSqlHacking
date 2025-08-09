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
import cats.kernel.BoundedSemilattice.mcI.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface BoundedJoinSemilattice$mcI$sp extends BoundedJoinSemilattice, JoinSemilattice$mcI$sp {
   // $FF: synthetic method
   static boolean isZero$(final BoundedJoinSemilattice$mcI$sp $this, final int a, final Eq ev) {
      return $this.isZero(a, ev);
   }

   default boolean isZero(final int a, final Eq ev) {
      return this.isZero$mcI$sp(a, ev);
   }

   // $FF: synthetic method
   static boolean isZero$mcI$sp$(final BoundedJoinSemilattice$mcI$sp $this, final int a, final Eq ev) {
      return $this.isZero$mcI$sp(a, ev);
   }

   default boolean isZero$mcI$sp(final int a, final Eq ev) {
      return ev.eqv$mcI$sp(a, this.zero$mcI$sp());
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$(final BoundedJoinSemilattice$mcI$sp $this) {
      return $this.joinSemilattice();
   }

   default BoundedSemilattice joinSemilattice() {
      return this.joinSemilattice$mcI$sp();
   }

   // $FF: synthetic method
   static BoundedSemilattice joinSemilattice$mcI$sp$(final BoundedJoinSemilattice$mcI$sp $this) {
      return $this.joinSemilattice$mcI$sp();
   }

   default BoundedSemilattice joinSemilattice$mcI$sp() {
      return new BoundedSemilattice.mcI.sp() {
         // $FF: synthetic field
         private final BoundedJoinSemilattice$mcI$sp $outer;

         public int combineN(final int a, final int n) {
            return sp.combineN$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return sp.combineN$mcI$sp$(this, a, n);
         }

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return cats.kernel.Semilattice.mcI.sp.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return cats.kernel.Semilattice.mcI.sp.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return cats.kernel.Semilattice.mcI.sp.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return cats.kernel.Semilattice.mcI.sp.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public int repeatedCombineN(final int a, final int n) {
            return cats.kernel.Band.mcI.sp.repeatedCombineN$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return cats.kernel.Band.mcI.sp.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public CommutativeMonoid reverse() {
            return cats.kernel.CommutativeMonoid.mcI.sp.reverse$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return cats.kernel.CommutativeMonoid.mcI.sp.reverse$mcI$sp$(this);
         }

         public boolean isEmpty(final int a, final Eq ev) {
            return cats.kernel.Monoid.mcI.sp.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return cats.kernel.Monoid.mcI.sp.isEmpty$mcI$sp$(this, a, ev);
         }

         public int combineAll(final IterableOnce as) {
            return cats.kernel.Monoid.mcI.sp.combineAll$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return cats.kernel.Monoid.mcI.sp.combineAll$mcI$sp$(this, as);
         }

         public CommutativeSemigroup intercalate(final int middle) {
            return cats.kernel.CommutativeSemigroup.mcI.sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return cats.kernel.CommutativeSemigroup.mcI.sp.intercalate$mcI$sp$(this, middle);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return BoundedSemilattice.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return BoundedSemilattice.combineN$mcF$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return BoundedSemilattice.combineN$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
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

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public int empty() {
            return this.empty$mcI$sp();
         }

         public int combine(final int x, final int y) {
            return this.combine$mcI$sp(x, y);
         }

         public int empty$mcI$sp() {
            return this.$outer.zero$mcI$sp();
         }

         public int combine$mcI$sp(final int x, final int y) {
            return this.$outer.join$mcI$sp(x, y);
         }

         public {
            if (BoundedJoinSemilattice$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = BoundedJoinSemilattice$mcI$sp.this;
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
