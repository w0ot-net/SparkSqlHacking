package algebra.lattice;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;

public class MinMaxLattice$mcF$sp extends MinMaxLattice implements DistributiveLattice$mcF$sp {
   public final Order order$mcF$sp;

   public Lattice dual() {
      return Lattice$mcF$sp.dual$(this);
   }

   public Lattice dual$mcF$sp() {
      return Lattice$mcF$sp.dual$mcF$sp$(this);
   }

   public Semilattice joinSemilattice() {
      return JoinSemilattice$mcF$sp.joinSemilattice$(this);
   }

   public Semilattice joinSemilattice$mcF$sp() {
      return JoinSemilattice$mcF$sp.joinSemilattice$mcF$sp$(this);
   }

   public PartialOrder joinPartialOrder(final Eq ev) {
      return JoinSemilattice$mcF$sp.joinPartialOrder$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
      return JoinSemilattice$mcF$sp.joinPartialOrder$mcF$sp$(this, ev);
   }

   public Semilattice meetSemilattice() {
      return MeetSemilattice$mcF$sp.meetSemilattice$(this);
   }

   public Semilattice meetSemilattice$mcF$sp() {
      return MeetSemilattice$mcF$sp.meetSemilattice$mcF$sp$(this);
   }

   public PartialOrder meetPartialOrder(final Eq ev) {
      return MeetSemilattice$mcF$sp.meetPartialOrder$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
      return MeetSemilattice$mcF$sp.meetPartialOrder$mcF$sp$(this, ev);
   }

   public float join(final float x, final float y) {
      return this.join$mcF$sp(x, y);
   }

   public float join$mcF$sp(final float x, final float y) {
      return this.order$mcF$sp.max$mcF$sp(x, y);
   }

   public float meet(final float x, final float y) {
      return this.meet$mcF$sp(x, y);
   }

   public float meet$mcF$sp(final float x, final float y) {
      return this.order$mcF$sp.min$mcF$sp(x, y);
   }

   public MinMaxLattice$mcF$sp(final Order order$mcF$sp) {
      super(order$mcF$sp);
      this.order$mcF$sp = order$mcF$sp;
   }
}
