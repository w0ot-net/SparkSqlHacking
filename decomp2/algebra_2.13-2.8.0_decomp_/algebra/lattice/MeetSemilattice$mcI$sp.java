package algebra.lattice;

import cats.kernel.Band;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import cats.kernel.Semilattice.mcI.sp;
import scala.Option;
import scala.collection.IterableOnce;

public interface MeetSemilattice$mcI$sp extends MeetSemilattice {
   // $FF: synthetic method
   static Semilattice meetSemilattice$(final MeetSemilattice$mcI$sp $this) {
      return $this.meetSemilattice();
   }

   default Semilattice meetSemilattice() {
      return this.meetSemilattice$mcI$sp();
   }

   // $FF: synthetic method
   static Semilattice meetSemilattice$mcI$sp$(final MeetSemilattice$mcI$sp $this) {
      return $this.meetSemilattice$mcI$sp();
   }

   default Semilattice meetSemilattice$mcI$sp() {
      return new Semilattice.mcI.sp() {
         // $FF: synthetic field
         private final MeetSemilattice$mcI$sp $outer;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return sp.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return sp.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return sp.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return sp.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public int repeatedCombineN(final int a, final int n) {
            return cats.kernel.Band.mcI.sp.repeatedCombineN$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return cats.kernel.Band.mcI.sp.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public CommutativeSemigroup reverse() {
            return cats.kernel.CommutativeSemigroup.mcI.sp.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return cats.kernel.CommutativeSemigroup.mcI.sp.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup intercalate(final int middle) {
            return cats.kernel.CommutativeSemigroup.mcI.sp.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return cats.kernel.CommutativeSemigroup.mcI.sp.intercalate$mcI$sp$(this, middle);
         }

         public int combineN(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.combineN$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return cats.kernel.Semigroup.mcI.sp.combineN$mcI$sp$(this, a, n);
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

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
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

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public int combine(final int x, final int y) {
            return this.combine$mcI$sp(x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return this.$outer.meet$mcI$sp(x, y);
         }

         public {
            if (MeetSemilattice$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = MeetSemilattice$mcI$sp.this;
               Semigroup.$init$(this);
               Band.$init$(this);
               CommutativeSemigroup.$init$(this);
               Semilattice.$init$(this);
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialOrder meetPartialOrder$(final MeetSemilattice$mcI$sp $this, final Eq ev) {
      return $this.meetPartialOrder(ev);
   }

   default PartialOrder meetPartialOrder(final Eq ev) {
      return this.meetPartialOrder$mcI$sp(ev);
   }

   // $FF: synthetic method
   static PartialOrder meetPartialOrder$mcI$sp$(final MeetSemilattice$mcI$sp $this, final Eq ev) {
      return $this.meetPartialOrder$mcI$sp(ev);
   }

   default PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
      return this.meetSemilattice$mcI$sp().asMeetPartialOrder$mcI$sp(ev);
   }
}
