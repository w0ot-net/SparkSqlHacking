package algebra.lattice;

import cats.kernel.Band;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import cats.kernel.Semilattice.mcD.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface MeetSemilattice$mcD$sp extends MeetSemilattice {
   // $FF: synthetic method
   static Semilattice meetSemilattice$(final MeetSemilattice$mcD$sp $this) {
      return $this.meetSemilattice();
   }

   default Semilattice meetSemilattice() {
      return this.meetSemilattice$mcD$sp();
   }

   // $FF: synthetic method
   static Semilattice meetSemilattice$mcD$sp$(final MeetSemilattice$mcD$sp $this) {
      return $this.meetSemilattice$mcD$sp();
   }

   default Semilattice meetSemilattice$mcD$sp() {
      return new Semilattice.mcD.sp() {
         // $FF: synthetic field
         private final MeetSemilattice$mcD$sp $outer;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return sp.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return sp.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return sp.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return sp.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public double repeatedCombineN(final double a, final int n) {
            return cats.kernel.Band.mcD.sp.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return cats.kernel.Band.mcD.sp.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public CommutativeSemigroup reverse() {
            return cats.kernel.CommutativeSemigroup.mcD.sp.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return cats.kernel.CommutativeSemigroup.mcD.sp.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup intercalate(final double middle) {
            return cats.kernel.CommutativeSemigroup.mcD.sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return cats.kernel.CommutativeSemigroup.mcD.sp.intercalate$mcD$sp$(this, middle);
         }

         public double combineN(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return cats.kernel.Semigroup.mcD.sp.combineN$mcD$sp$(this, a, n);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
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

         public double combine(final double x, final double y) {
            return this.combine$mcD$sp(x, y);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return this.$outer.meet$mcD$sp(x, y);
         }

         public {
            if (MeetSemilattice$mcD$sp.this == null) {
               throw null;
            } else {
               this.$outer = MeetSemilattice$mcD$sp.this;
               Semigroup.$init$(this);
               Band.$init$(this);
               CommutativeSemigroup.$init$(this);
               Semilattice.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialOrder meetPartialOrder$(final MeetSemilattice$mcD$sp $this, final Eq ev) {
      return $this.meetPartialOrder(ev);
   }

   default PartialOrder meetPartialOrder(final Eq ev) {
      return this.meetPartialOrder$mcD$sp(ev);
   }

   // $FF: synthetic method
   static PartialOrder meetPartialOrder$mcD$sp$(final MeetSemilattice$mcD$sp $this, final Eq ev) {
      return $this.meetPartialOrder$mcD$sp(ev);
   }

   default PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
      return this.meetSemilattice$mcD$sp().asMeetPartialOrder$mcD$sp(ev);
   }
}
