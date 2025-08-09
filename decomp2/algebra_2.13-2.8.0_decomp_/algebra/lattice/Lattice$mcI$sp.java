package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;

public interface Lattice$mcI$sp extends Lattice, MeetSemilattice$mcI$sp, JoinSemilattice$mcI$sp {
   // $FF: synthetic method
   static Lattice dual$(final Lattice$mcI$sp $this) {
      return $this.dual();
   }

   default Lattice dual() {
      return this.dual$mcI$sp();
   }

   // $FF: synthetic method
   static Lattice dual$mcI$sp$(final Lattice$mcI$sp $this) {
      return $this.dual$mcI$sp();
   }

   default Lattice dual$mcI$sp() {
      return new Lattice$mcI$sp() {
         // $FF: synthetic field
         private final Lattice$mcI$sp $outer;

         public Semilattice joinSemilattice() {
            return JoinSemilattice$mcI$sp.joinSemilattice$(this);
         }

         public Semilattice joinSemilattice$mcI$sp() {
            return JoinSemilattice$mcI$sp.joinSemilattice$mcI$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcI$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice$mcI$sp.joinPartialOrder$mcI$sp$(this, ev);
         }

         public Semilattice meetSemilattice() {
            return MeetSemilattice$mcI$sp.meetSemilattice$(this);
         }

         public Semilattice meetSemilattice$mcI$sp() {
            return MeetSemilattice$mcI$sp.meetSemilattice$mcI$sp$(this);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcI$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice$mcI$sp.meetPartialOrder$mcI$sp$(this, ev);
         }

         public Lattice dual$mcD$sp() {
            return Lattice.dual$mcD$sp$(this);
         }

         public Lattice dual$mcF$sp() {
            return Lattice.dual$mcF$sp$(this);
         }

         public Lattice dual$mcJ$sp() {
            return Lattice.dual$mcJ$sp$(this);
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

         public Semilattice meetSemilattice$mcD$sp() {
            return MeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public Semilattice meetSemilattice$mcF$sp() {
            return MeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public Semilattice meetSemilattice$mcJ$sp() {
            return MeetSemilattice.meetSemilattice$mcJ$sp$(this);
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

         public Semilattice joinSemilattice$mcD$sp() {
            return JoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public Semilattice joinSemilattice$mcF$sp() {
            return JoinSemilattice.joinSemilattice$mcF$sp$(this);
         }

         public Semilattice joinSemilattice$mcJ$sp() {
            return JoinSemilattice.joinSemilattice$mcJ$sp$(this);
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

         public Lattice dual() {
            return this.dual$mcI$sp();
         }

         public int meet$mcI$sp(final int a, final int b) {
            return this.$outer.join$mcI$sp(a, b);
         }

         public int join$mcI$sp(final int a, final int b) {
            return this.$outer.meet$mcI$sp(a, b);
         }

         public Lattice dual$mcI$sp() {
            return this.$outer;
         }

         public {
            if (Lattice$mcI$sp.this == null) {
               throw null;
            } else {
               this.$outer = Lattice$mcI$sp.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
            }
         }
      };
   }
}
