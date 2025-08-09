package algebra.lattice;

import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;

public class DualBool$mcI$sp extends DualBool implements Bool$mcI$sp {
   public final Bool orig$mcI$sp;

   public int without(final int a, final int b) {
      return Bool$mcI$sp.without$(this, a, b);
   }

   public int without$mcI$sp(final int a, final int b) {
      return Bool$mcI$sp.without$mcI$sp$(this, a, b);
   }

   public int meet(final int a, final int b) {
      return Heyting$mcI$sp.meet$(this, a, b);
   }

   public int meet$mcI$sp(final int a, final int b) {
      return Heyting$mcI$sp.meet$mcI$sp$(this, a, b);
   }

   public int join(final int a, final int b) {
      return Heyting$mcI$sp.join$(this, a, b);
   }

   public int join$mcI$sp(final int a, final int b) {
      return Heyting$mcI$sp.join$mcI$sp$(this, a, b);
   }

   public CommutativeRig asCommutativeRig() {
      return BoundedDistributiveLattice$mcI$sp.asCommutativeRig$(this);
   }

   public CommutativeRig asCommutativeRig$mcI$sp() {
      return BoundedDistributiveLattice$mcI$sp.asCommutativeRig$mcI$sp$(this);
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

   public PartialOrder meetPartialOrder(final Eq ev) {
      return MeetSemilattice$mcI$sp.meetPartialOrder$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
      return MeetSemilattice$mcI$sp.meetPartialOrder$mcI$sp$(this, ev);
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

   public int one() {
      return this.one$mcI$sp();
   }

   public int one$mcI$sp() {
      return this.orig$mcI$sp.zero$mcI$sp();
   }

   public int zero() {
      return this.zero$mcI$sp();
   }

   public int zero$mcI$sp() {
      return this.orig$mcI$sp.one$mcI$sp();
   }

   public int and(final int a, final int b) {
      return this.and$mcI$sp(a, b);
   }

   public int and$mcI$sp(final int a, final int b) {
      return this.orig$mcI$sp.or$mcI$sp(a, b);
   }

   public int or(final int a, final int b) {
      return this.or$mcI$sp(a, b);
   }

   public int or$mcI$sp(final int a, final int b) {
      return this.orig$mcI$sp.and$mcI$sp(a, b);
   }

   public int complement(final int a) {
      return this.complement$mcI$sp(a);
   }

   public int complement$mcI$sp(final int a) {
      return this.orig$mcI$sp.complement$mcI$sp(a);
   }

   public int xor(final int a, final int b) {
      return this.xor$mcI$sp(a, b);
   }

   public int xor$mcI$sp(final int a, final int b) {
      return this.orig$mcI$sp.complement$mcI$sp(this.orig$mcI$sp.xor$mcI$sp(a, b));
   }

   public int imp(final int a, final int b) {
      return this.imp$mcI$sp(a, b);
   }

   public int imp$mcI$sp(final int a, final int b) {
      return this.orig$mcI$sp.and$mcI$sp(this.orig$mcI$sp.complement$mcI$sp(a), b);
   }

   public int nand(final int a, final int b) {
      return this.nand$mcI$sp(a, b);
   }

   public int nand$mcI$sp(final int a, final int b) {
      return this.orig$mcI$sp.nor$mcI$sp(a, b);
   }

   public int nor(final int a, final int b) {
      return this.nor$mcI$sp(a, b);
   }

   public int nor$mcI$sp(final int a, final int b) {
      return this.orig$mcI$sp.nand$mcI$sp(a, b);
   }

   public int nxor(final int a, final int b) {
      return this.nxor$mcI$sp(a, b);
   }

   public int nxor$mcI$sp(final int a, final int b) {
      return this.orig$mcI$sp.xor$mcI$sp(a, b);
   }

   public Bool dual() {
      return this.dual$mcI$sp();
   }

   public Bool dual$mcI$sp() {
      return this.orig$mcI$sp;
   }

   public DualBool$mcI$sp(final Bool orig$mcI$sp) {
      super(orig$mcI$sp);
      this.orig$mcI$sp = orig$mcI$sp;
   }
}
