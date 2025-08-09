package spire.math;

import algebra.lattice.BoundedDistributiveLattice;
import algebra.lattice.BoundedJoinSemilattice;
import algebra.lattice.BoundedLattice;
import algebra.lattice.BoundedMeetSemilattice;
import algebra.lattice.DeMorgan;
import algebra.lattice.JoinSemilattice;
import algebra.lattice.Lattice;
import algebra.lattice.Logic;
import algebra.lattice.MeetSemilattice;
import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2Aa\u0002\u0005\u0001\u001b!)\u0001\u0006\u0001C\u0001S!)1\u0006\u0001C\u0001Y!)Q\u0006\u0001C\u0001Y!)a\u0006\u0001C\u0001_!)!\u0007\u0001C\u0001g!)q\u0007\u0001C\u0001q\tqAK]5mK\u0006t\u0017\t\\4fEJ\f'BA\u0005\u000b\u0003\u0011i\u0017\r\u001e5\u000b\u0003-\tQa\u001d9je\u0016\u001c\u0001aE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007cA\u000b\"I9\u0011aC\b\b\u0003/mq!\u0001G\r\u000e\u0003)I!A\u0007\u0006\u0002\u000f\u0005dw-\u001a2sC&\u0011A$H\u0001\bY\u0006$H/[2f\u0015\tQ\"\"\u0003\u0002 A\u00059\u0001/Y2lC\u001e,'B\u0001\u000f\u001e\u0013\t\u00113E\u0001\u0005EK6{'oZ1o\u0015\ty\u0002\u0005\u0005\u0002&M5\t\u0001\"\u0003\u0002(\u0011\t9AK]5mK\u0006t\u0017A\u0002\u001fj]&$h\bF\u0001+!\t)\u0003!A\u0002p]\u0016,\u0012\u0001J\u0001\u0005u\u0016\u0014x.A\u0002o_R$\"\u0001\n\u0019\t\u000bE\"\u0001\u0019\u0001\u0013\u0002\u0003\u0005\f1!\u00198e)\r!C'\u000e\u0005\u0006c\u0015\u0001\r\u0001\n\u0005\u0006m\u0015\u0001\r\u0001J\u0001\u0002E\u0006\u0011qN\u001d\u000b\u0004IeR\u0004\"B\u0019\u0007\u0001\u0004!\u0003\"\u0002\u001c\u0007\u0001\u0004!\u0003"
)
public class TrileanAlgebra implements DeMorgan {
   public Object meet(final Object a, final Object b) {
      return DeMorgan.meet$(this, a, b);
   }

   public int meet$mcI$sp(final int a, final int b) {
      return DeMorgan.meet$mcI$sp$(this, a, b);
   }

   public long meet$mcJ$sp(final long a, final long b) {
      return DeMorgan.meet$mcJ$sp$(this, a, b);
   }

   public Object join(final Object a, final Object b) {
      return DeMorgan.join$(this, a, b);
   }

   public int join$mcI$sp(final int a, final int b) {
      return DeMorgan.join$mcI$sp$(this, a, b);
   }

   public long join$mcJ$sp(final long a, final long b) {
      return DeMorgan.join$mcJ$sp$(this, a, b);
   }

   public Object imp(final Object a, final Object b) {
      return DeMorgan.imp$(this, a, b);
   }

   public int imp$mcI$sp(final int a, final int b) {
      return DeMorgan.imp$mcI$sp$(this, a, b);
   }

   public long imp$mcJ$sp(final long a, final long b) {
      return DeMorgan.imp$mcJ$sp$(this, a, b);
   }

   public int and$mcI$sp(final int a, final int b) {
      return Logic.and$mcI$sp$(this, a, b);
   }

   public long and$mcJ$sp(final long a, final long b) {
      return Logic.and$mcJ$sp$(this, a, b);
   }

   public int or$mcI$sp(final int a, final int b) {
      return Logic.or$mcI$sp$(this, a, b);
   }

   public long or$mcJ$sp(final long a, final long b) {
      return Logic.or$mcJ$sp$(this, a, b);
   }

   public int not$mcI$sp(final int a) {
      return Logic.not$mcI$sp$(this, a);
   }

   public long not$mcJ$sp(final long a) {
      return Logic.not$mcJ$sp$(this, a);
   }

   public Object xor(final Object a, final Object b) {
      return Logic.xor$(this, a, b);
   }

   public int xor$mcI$sp(final int a, final int b) {
      return Logic.xor$mcI$sp$(this, a, b);
   }

   public long xor$mcJ$sp(final long a, final long b) {
      return Logic.xor$mcJ$sp$(this, a, b);
   }

   public Object nand(final Object a, final Object b) {
      return Logic.nand$(this, a, b);
   }

   public int nand$mcI$sp(final int a, final int b) {
      return Logic.nand$mcI$sp$(this, a, b);
   }

   public long nand$mcJ$sp(final long a, final long b) {
      return Logic.nand$mcJ$sp$(this, a, b);
   }

   public Object nor(final Object a, final Object b) {
      return Logic.nor$(this, a, b);
   }

   public int nor$mcI$sp(final int a, final int b) {
      return Logic.nor$mcI$sp$(this, a, b);
   }

   public long nor$mcJ$sp(final long a, final long b) {
      return Logic.nor$mcJ$sp$(this, a, b);
   }

   public Object nxor(final Object a, final Object b) {
      return Logic.nxor$(this, a, b);
   }

   public int nxor$mcI$sp(final int a, final int b) {
      return Logic.nxor$mcI$sp$(this, a, b);
   }

   public long nxor$mcJ$sp(final long a, final long b) {
      return Logic.nxor$mcJ$sp$(this, a, b);
   }

   public CommutativeRig asCommutativeRig() {
      return BoundedDistributiveLattice.asCommutativeRig$(this);
   }

   public CommutativeRig asCommutativeRig$mcD$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcD$sp$(this);
   }

   public CommutativeRig asCommutativeRig$mcF$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcF$sp$(this);
   }

   public CommutativeRig asCommutativeRig$mcI$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcI$sp$(this);
   }

   public CommutativeRig asCommutativeRig$mcJ$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcJ$sp$(this);
   }

   public BoundedDistributiveLattice dual() {
      return BoundedDistributiveLattice.dual$(this);
   }

   public BoundedDistributiveLattice dual$mcD$sp() {
      return BoundedDistributiveLattice.dual$mcD$sp$(this);
   }

   public BoundedDistributiveLattice dual$mcF$sp() {
      return BoundedDistributiveLattice.dual$mcF$sp$(this);
   }

   public BoundedDistributiveLattice dual$mcI$sp() {
      return BoundedDistributiveLattice.dual$mcI$sp$(this);
   }

   public BoundedDistributiveLattice dual$mcJ$sp() {
      return BoundedDistributiveLattice.dual$mcJ$sp$(this);
   }

   public double zero$mcD$sp() {
      return BoundedJoinSemilattice.zero$mcD$sp$(this);
   }

   public float zero$mcF$sp() {
      return BoundedJoinSemilattice.zero$mcF$sp$(this);
   }

   public int zero$mcI$sp() {
      return BoundedJoinSemilattice.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return BoundedJoinSemilattice.zero$mcJ$sp$(this);
   }

   public boolean isZero(final Object a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcJ$sp$(this, a, ev);
   }

   public BoundedSemilattice joinSemilattice() {
      return BoundedJoinSemilattice.joinSemilattice$(this);
   }

   public BoundedSemilattice joinSemilattice$mcD$sp() {
      return BoundedJoinSemilattice.joinSemilattice$mcD$sp$(this);
   }

   public BoundedSemilattice joinSemilattice$mcF$sp() {
      return BoundedJoinSemilattice.joinSemilattice$mcF$sp$(this);
   }

   public BoundedSemilattice joinSemilattice$mcI$sp() {
      return BoundedJoinSemilattice.joinSemilattice$mcI$sp$(this);
   }

   public BoundedSemilattice joinSemilattice$mcJ$sp() {
      return BoundedJoinSemilattice.joinSemilattice$mcJ$sp$(this);
   }

   public double one$mcD$sp() {
      return BoundedMeetSemilattice.one$mcD$sp$(this);
   }

   public float one$mcF$sp() {
      return BoundedMeetSemilattice.one$mcF$sp$(this);
   }

   public int one$mcI$sp() {
      return BoundedMeetSemilattice.one$mcI$sp$(this);
   }

   public long one$mcJ$sp() {
      return BoundedMeetSemilattice.one$mcJ$sp$(this);
   }

   public boolean isOne(final Object a, final Eq ev) {
      return BoundedMeetSemilattice.isOne$(this, a, ev);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return BoundedMeetSemilattice.isOne$mcD$sp$(this, a, ev);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return BoundedMeetSemilattice.isOne$mcF$sp$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return BoundedMeetSemilattice.isOne$mcI$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return BoundedMeetSemilattice.isOne$mcJ$sp$(this, a, ev);
   }

   public BoundedSemilattice meetSemilattice() {
      return BoundedMeetSemilattice.meetSemilattice$(this);
   }

   public BoundedSemilattice meetSemilattice$mcD$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
   }

   public BoundedSemilattice meetSemilattice$mcF$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
   }

   public BoundedSemilattice meetSemilattice$mcI$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcI$sp$(this);
   }

   public BoundedSemilattice meetSemilattice$mcJ$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcJ$sp$(this);
   }

   public double meet$mcD$sp(final double lhs, final double rhs) {
      return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
   }

   public float meet$mcF$sp(final float lhs, final float rhs) {
      return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
   }

   public PartialOrder meetPartialOrder(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$(this, ev);
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

   public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$mcJ$sp$(this, ev);
   }

   public double join$mcD$sp(final double lhs, final double rhs) {
      return JoinSemilattice.join$mcD$sp$(this, lhs, rhs);
   }

   public float join$mcF$sp(final float lhs, final float rhs) {
      return JoinSemilattice.join$mcF$sp$(this, lhs, rhs);
   }

   public PartialOrder joinPartialOrder(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$(this, ev);
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

   public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
   }

   public int one() {
      return Trilean$.MODULE$.True();
   }

   public int zero() {
      return Trilean$.MODULE$.False();
   }

   public int not(final int a) {
      return Trilean$.MODULE$.unary_$bang$extension(a);
   }

   public int and(final int a, final int b) {
      return Trilean$.MODULE$.$amp$extension(a, b);
   }

   public int or(final int a, final int b) {
      return Trilean$.MODULE$.$bar$extension(a, b);
   }

   public TrileanAlgebra() {
      JoinSemilattice.$init$(this);
      MeetSemilattice.$init$(this);
      Lattice.$init$(this);
      BoundedMeetSemilattice.$init$(this);
      BoundedJoinSemilattice.$init$(this);
      BoundedLattice.$init$(this);
      BoundedDistributiveLattice.$init$(this);
      Logic.$init$(this);
      DeMorgan.$init$(this);
   }
}
