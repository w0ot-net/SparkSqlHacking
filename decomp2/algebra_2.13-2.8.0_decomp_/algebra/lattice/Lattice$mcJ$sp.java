package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;

public interface Lattice$mcJ$sp extends Lattice, MeetSemilattice$mcJ$sp, JoinSemilattice$mcJ$sp {
   // $FF: synthetic method
   static Lattice dual$(final Lattice$mcJ$sp $this) {
      return $this.dual();
   }

   default Lattice dual() {
      return this.dual$mcJ$sp();
   }

   // $FF: synthetic method
   static Lattice dual$mcJ$sp$(final Lattice$mcJ$sp $this) {
      return $this.dual$mcJ$sp();
   }

   default Lattice dual$mcJ$sp() {
      return new Lattice$mcJ$sp() {
         // $FF: synthetic field
         private final Lattice$mcJ$sp $outer;

         public Semilattice joinSemilattice() {
            return JoinSemilattice$mcJ$sp.joinSemilattice$(this);
         }

         public Semilattice joinSemilattice$mcJ$sp() {
            return JoinSemilattice$mcJ$sp.joinSemilattice$mcJ$sp$(this);
         }

         public PartialOrder joinPartialOrder(final Eq ev) {
            return JoinSemilattice$mcJ$sp.joinPartialOrder$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
            return JoinSemilattice$mcJ$sp.joinPartialOrder$mcJ$sp$(this, ev);
         }

         public Semilattice meetSemilattice() {
            return MeetSemilattice$mcJ$sp.meetSemilattice$(this);
         }

         public Semilattice meetSemilattice$mcJ$sp() {
            return MeetSemilattice$mcJ$sp.meetSemilattice$mcJ$sp$(this);
         }

         public PartialOrder meetPartialOrder(final Eq ev) {
            return MeetSemilattice$mcJ$sp.meetPartialOrder$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
            return MeetSemilattice$mcJ$sp.meetPartialOrder$mcJ$sp$(this, ev);
         }

         public Lattice dual$mcD$sp() {
            return Lattice.dual$mcD$sp$(this);
         }

         public Lattice dual$mcF$sp() {
            return Lattice.dual$mcF$sp$(this);
         }

         public Lattice dual$mcI$sp() {
            return Lattice.dual$mcI$sp$(this);
         }

         public double meet$mcD$sp(final double lhs, final double rhs) {
            return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
         }

         public float meet$mcF$sp(final float lhs, final float rhs) {
            return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
         }

         public int meet$mcI$sp(final int lhs, final int rhs) {
            return MeetSemilattice.meet$mcI$sp$(this, lhs, rhs);
         }

         public Semilattice meetSemilattice$mcD$sp() {
            return MeetSemilattice.meetSemilattice$mcD$sp$(this);
         }

         public Semilattice meetSemilattice$mcF$sp() {
            return MeetSemilattice.meetSemilattice$mcF$sp$(this);
         }

         public Semilattice meetSemilattice$mcI$sp() {
            return MeetSemilattice.meetSemilattice$mcI$sp$(this);
         }

         public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
            return MeetSemilattice.meetPartialOrder$mcI$sp$(this, ev);
         }

         public double join$mcD$sp(final double lhs, final double rhs) {
            return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
         }

         public float join$mcF$sp(final float lhs, final float rhs) {
            return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
         }

         public int join$mcI$sp(final int lhs, final int rhs) {
            return JoinSemilattice.join$mcI$sp$(this, lhs, rhs);
         }

         public Semilattice joinSemilattice$mcD$sp() {
            return JoinSemilattice.joinSemilattice$mcD$sp$(this);
         }

         public Semilattice joinSemilattice$mcF$sp() {
            return JoinSemilattice.joinSemilattice$mcF$sp$(this);
         }

         public Semilattice joinSemilattice$mcI$sp() {
            return JoinSemilattice.joinSemilattice$mcI$sp$(this);
         }

         public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
            return JoinSemilattice.joinPartialOrder$mcI$sp$(this, ev);
         }

         public long meet(final long a, final long b) {
            return this.meet$mcJ$sp(a, b);
         }

         public long join(final long a, final long b) {
            return this.join$mcJ$sp(a, b);
         }

         public Lattice dual() {
            return this.dual$mcJ$sp();
         }

         public long meet$mcJ$sp(final long a, final long b) {
            return this.$outer.join$mcJ$sp(a, b);
         }

         public long join$mcJ$sp(final long a, final long b) {
            return this.$outer.meet$mcJ$sp(a, b);
         }

         public Lattice dual$mcJ$sp() {
            return this.$outer;
         }

         public {
            if (Lattice$mcJ$sp.this == null) {
               throw null;
            } else {
               this.$outer = Lattice$mcJ$sp.this;
               JoinSemilattice.$init$(this);
               MeetSemilattice.$init$(this);
               Lattice.$init$(this);
            }
         }
      };
   }
}
