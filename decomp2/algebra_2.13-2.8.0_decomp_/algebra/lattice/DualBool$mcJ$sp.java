package algebra.lattice;

import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;

public class DualBool$mcJ$sp extends DualBool implements Bool$mcJ$sp {
   public final Bool orig$mcJ$sp;

   public long without(final long a, final long b) {
      return Bool$mcJ$sp.without$(this, a, b);
   }

   public long without$mcJ$sp(final long a, final long b) {
      return Bool$mcJ$sp.without$mcJ$sp$(this, a, b);
   }

   public long meet(final long a, final long b) {
      return Heyting$mcJ$sp.meet$(this, a, b);
   }

   public long meet$mcJ$sp(final long a, final long b) {
      return Heyting$mcJ$sp.meet$mcJ$sp$(this, a, b);
   }

   public long join(final long a, final long b) {
      return Heyting$mcJ$sp.join$(this, a, b);
   }

   public long join$mcJ$sp(final long a, final long b) {
      return Heyting$mcJ$sp.join$mcJ$sp$(this, a, b);
   }

   public CommutativeRig asCommutativeRig() {
      return BoundedDistributiveLattice$mcJ$sp.asCommutativeRig$(this);
   }

   public CommutativeRig asCommutativeRig$mcJ$sp() {
      return BoundedDistributiveLattice$mcJ$sp.asCommutativeRig$mcJ$sp$(this);
   }

   public boolean isOne(final long a, final Eq ev) {
      return BoundedMeetSemilattice$mcJ$sp.isOne$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return BoundedMeetSemilattice$mcJ$sp.isOne$mcJ$sp$(this, a, ev);
   }

   public BoundedSemilattice meetSemilattice() {
      return BoundedMeetSemilattice$mcJ$sp.meetSemilattice$(this);
   }

   public BoundedSemilattice meetSemilattice$mcJ$sp() {
      return BoundedMeetSemilattice$mcJ$sp.meetSemilattice$mcJ$sp$(this);
   }

   public PartialOrder meetPartialOrder(final Eq ev) {
      return MeetSemilattice$mcJ$sp.meetPartialOrder$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
      return MeetSemilattice$mcJ$sp.meetPartialOrder$mcJ$sp$(this, ev);
   }

   public boolean isZero(final long a, final Eq ev) {
      return BoundedJoinSemilattice$mcJ$sp.isZero$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return BoundedJoinSemilattice$mcJ$sp.isZero$mcJ$sp$(this, a, ev);
   }

   public BoundedSemilattice joinSemilattice() {
      return BoundedJoinSemilattice$mcJ$sp.joinSemilattice$(this);
   }

   public BoundedSemilattice joinSemilattice$mcJ$sp() {
      return BoundedJoinSemilattice$mcJ$sp.joinSemilattice$mcJ$sp$(this);
   }

   public PartialOrder joinPartialOrder(final Eq ev) {
      return JoinSemilattice$mcJ$sp.joinPartialOrder$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
      return JoinSemilattice$mcJ$sp.joinPartialOrder$mcJ$sp$(this, ev);
   }

   public long one() {
      return this.one$mcJ$sp();
   }

   public long one$mcJ$sp() {
      return this.orig$mcJ$sp.zero$mcJ$sp();
   }

   public long zero() {
      return this.zero$mcJ$sp();
   }

   public long zero$mcJ$sp() {
      return this.orig$mcJ$sp.one$mcJ$sp();
   }

   public long and(final long a, final long b) {
      return this.and$mcJ$sp(a, b);
   }

   public long and$mcJ$sp(final long a, final long b) {
      return this.orig$mcJ$sp.or$mcJ$sp(a, b);
   }

   public long or(final long a, final long b) {
      return this.or$mcJ$sp(a, b);
   }

   public long or$mcJ$sp(final long a, final long b) {
      return this.orig$mcJ$sp.and$mcJ$sp(a, b);
   }

   public long complement(final long a) {
      return this.complement$mcJ$sp(a);
   }

   public long complement$mcJ$sp(final long a) {
      return this.orig$mcJ$sp.complement$mcJ$sp(a);
   }

   public long xor(final long a, final long b) {
      return this.xor$mcJ$sp(a, b);
   }

   public long xor$mcJ$sp(final long a, final long b) {
      return this.orig$mcJ$sp.complement$mcJ$sp(this.orig$mcJ$sp.xor$mcJ$sp(a, b));
   }

   public long imp(final long a, final long b) {
      return this.imp$mcJ$sp(a, b);
   }

   public long imp$mcJ$sp(final long a, final long b) {
      return this.orig$mcJ$sp.and$mcJ$sp(this.orig$mcJ$sp.complement$mcJ$sp(a), b);
   }

   public long nand(final long a, final long b) {
      return this.nand$mcJ$sp(a, b);
   }

   public long nand$mcJ$sp(final long a, final long b) {
      return this.orig$mcJ$sp.nor$mcJ$sp(a, b);
   }

   public long nor(final long a, final long b) {
      return this.nor$mcJ$sp(a, b);
   }

   public long nor$mcJ$sp(final long a, final long b) {
      return this.orig$mcJ$sp.nand$mcJ$sp(a, b);
   }

   public long nxor(final long a, final long b) {
      return this.nxor$mcJ$sp(a, b);
   }

   public long nxor$mcJ$sp(final long a, final long b) {
      return this.orig$mcJ$sp.xor$mcJ$sp(a, b);
   }

   public Bool dual() {
      return this.dual$mcJ$sp();
   }

   public Bool dual$mcJ$sp() {
      return this.orig$mcJ$sp;
   }

   public DualBool$mcJ$sp(final Bool orig$mcJ$sp) {
      super(orig$mcJ$sp);
      this.orig$mcJ$sp = orig$mcJ$sp;
   }
}
