package algebra.lattice;

import cats.kernel.Band;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import cats.kernel.Semilattice.mcF.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface JoinSemilattice$mcF$sp extends JoinSemilattice {
   // $FF: synthetic method
   static Semilattice joinSemilattice$(final JoinSemilattice$mcF$sp $this) {
      return $this.joinSemilattice();
   }

   default Semilattice joinSemilattice() {
      return this.joinSemilattice$mcF$sp();
   }

   // $FF: synthetic method
   static Semilattice joinSemilattice$mcF$sp$(final JoinSemilattice$mcF$sp $this) {
      return $this.joinSemilattice$mcF$sp();
   }

   default Semilattice joinSemilattice$mcF$sp() {
      return new Semilattice.mcF.sp() {
         // $FF: synthetic field
         private final JoinSemilattice$mcF$sp $outer;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return sp.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return sp.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return sp.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return sp.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public float repeatedCombineN(final float a, final int n) {
            return cats.kernel.Band.mcF.sp.repeatedCombineN$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return cats.kernel.Band.mcF.sp.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public CommutativeSemigroup reverse() {
            return cats.kernel.CommutativeSemigroup.mcF.sp.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return cats.kernel.CommutativeSemigroup.mcF.sp.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup intercalate(final float middle) {
            return cats.kernel.CommutativeSemigroup.mcF.sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return cats.kernel.CommutativeSemigroup.mcF.sp.intercalate$mcF$sp$(this, middle);
         }

         public float combineN(final float a, final int n) {
            return cats.kernel.Semigroup.mcF.sp.combineN$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return cats.kernel.Semigroup.mcF.sp.combineN$mcF$sp$(this, a, n);
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

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
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

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public float combine(final float x, final float y) {
            return this.combine$mcF$sp(x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return this.$outer.join$mcF$sp(x, y);
         }

         public {
            if (JoinSemilattice$mcF$sp.this == null) {
               throw null;
            } else {
               this.$outer = JoinSemilattice$mcF$sp.this;
               Semigroup.$init$(this);
               Band.$init$(this);
               CommutativeSemigroup.$init$(this);
               Semilattice.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialOrder joinPartialOrder$(final JoinSemilattice$mcF$sp $this, final Eq ev) {
      return $this.joinPartialOrder(ev);
   }

   default PartialOrder joinPartialOrder(final Eq ev) {
      return this.joinPartialOrder$mcF$sp(ev);
   }

   // $FF: synthetic method
   static PartialOrder joinPartialOrder$mcF$sp$(final JoinSemilattice$mcF$sp $this, final Eq ev) {
      return $this.joinPartialOrder$mcF$sp(ev);
   }

   default PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
      return this.joinSemilattice$mcF$sp().asJoinPartialOrder$mcF$sp(ev);
   }
}
