package algebra.instances;

import algebra.lattice.BoundedJoinSemilattice;
import algebra.lattice.GenBool;
import algebra.lattice.JoinSemilattice;
import algebra.lattice.Lattice;
import algebra.lattice.MeetSemilattice;
import algebra.ring.BoolRng;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113AAB\u0004\u0001\u0019!)\u0001\u0007\u0001C\u0001c!)A\u0007\u0001C\u0001k!)a\u0007\u0001C\u0001o!)A\b\u0001C\u0001{!)\u0001\t\u0001C\u0001\u0003\nQ1+\u001a;MCR$\u0018nY3\u000b\u0005!I\u0011!C5ogR\fgnY3t\u0015\u0005Q\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\tiqeE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007cA\u000b\u001955\taC\u0003\u0002\u0018\u0013\u00059A.\u0019;uS\u000e,\u0017BA\r\u0017\u0005\u001d9UM\u001c\"p_2\u00042a\u0007\u0012&\u001d\ta\u0002\u0005\u0005\u0002\u001e!5\taD\u0003\u0002 \u0017\u00051AH]8pizJ!!\t\t\u0002\rA\u0013X\rZ3g\u0013\t\u0019CEA\u0002TKRT!!\t\t\u0011\u0005\u0019:C\u0002\u0001\u0003\u0006Q\u0001\u0011\r!\u000b\u0002\u0002\u0003F\u0011!&\f\t\u0003\u001f-J!\u0001\f\t\u0003\u000f9{G\u000f[5oOB\u0011qBL\u0005\u0003_A\u00111!\u00118z\u0003\u0019a\u0014N\\5u}Q\t!\u0007E\u00024\u0001\u0015j\u0011aB\u0001\u0005u\u0016\u0014x.F\u0001\u001b\u0003\ty'\u000fF\u0002\u001bqiBQ!O\u0002A\u0002i\t1\u0001\u001c5t\u0011\u0015Y4\u00011\u0001\u001b\u0003\r\u0011\bn]\u0001\u0004C:$Gc\u0001\u000e?\u007f!)\u0011\b\u0002a\u00015!)1\b\u0002a\u00015\u00059q/\u001b;i_V$Hc\u0001\u000eC\u0007\")\u0011(\u0002a\u00015!)1(\u0002a\u00015\u0001"
)
public class SetLattice implements GenBool {
   public int and$mcI$sp(final int a, final int b) {
      return GenBool.and$mcI$sp$(this, a, b);
   }

   public long and$mcJ$sp(final long a, final long b) {
      return GenBool.and$mcJ$sp$(this, a, b);
   }

   public Object meet(final Object a, final Object b) {
      return GenBool.meet$(this, a, b);
   }

   public int meet$mcI$sp(final int a, final int b) {
      return GenBool.meet$mcI$sp$(this, a, b);
   }

   public long meet$mcJ$sp(final long a, final long b) {
      return GenBool.meet$mcJ$sp$(this, a, b);
   }

   public int or$mcI$sp(final int a, final int b) {
      return GenBool.or$mcI$sp$(this, a, b);
   }

   public long or$mcJ$sp(final long a, final long b) {
      return GenBool.or$mcJ$sp$(this, a, b);
   }

   public Object join(final Object a, final Object b) {
      return GenBool.join$(this, a, b);
   }

   public int join$mcI$sp(final int a, final int b) {
      return GenBool.join$mcI$sp$(this, a, b);
   }

   public long join$mcJ$sp(final long a, final long b) {
      return GenBool.join$mcJ$sp$(this, a, b);
   }

   public int without$mcI$sp(final int a, final int b) {
      return GenBool.without$mcI$sp$(this, a, b);
   }

   public long without$mcJ$sp(final long a, final long b) {
      return GenBool.without$mcJ$sp$(this, a, b);
   }

   public Object xor(final Object a, final Object b) {
      return GenBool.xor$(this, a, b);
   }

   public int xor$mcI$sp(final int a, final int b) {
      return GenBool.xor$mcI$sp$(this, a, b);
   }

   public long xor$mcJ$sp(final long a, final long b) {
      return GenBool.xor$mcJ$sp$(this, a, b);
   }

   /** @deprecated */
   public BoolRng asBoolRing() {
      return GenBool.asBoolRing$(this);
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

   public Lattice dual() {
      return Lattice.dual$(this);
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

   public Lattice dual$mcJ$sp() {
      return Lattice.dual$mcJ$sp$(this);
   }

   public double meet$mcD$sp(final double lhs, final double rhs) {
      return MeetSemilattice.meet$mcD$sp$(this, lhs, rhs);
   }

   public float meet$mcF$sp(final float lhs, final float rhs) {
      return MeetSemilattice.meet$mcF$sp$(this, lhs, rhs);
   }

   public Semilattice meetSemilattice() {
      return MeetSemilattice.meetSemilattice$(this);
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

   public Semilattice meetSemilattice$mcJ$sp() {
      return MeetSemilattice.meetSemilattice$mcJ$sp$(this);
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

   public Set zero() {
      return .MODULE$.Set().empty();
   }

   public Set or(final Set lhs, final Set rhs) {
      return (Set)lhs.$bar(rhs);
   }

   public Set and(final Set lhs, final Set rhs) {
      return (Set)lhs.$amp(rhs);
   }

   public Set without(final Set lhs, final Set rhs) {
      return (Set)lhs.$minus$minus(rhs);
   }

   public SetLattice() {
      JoinSemilattice.$init$(this);
      MeetSemilattice.$init$(this);
      Lattice.$init$(this);
      BoundedJoinSemilattice.$init$(this);
      GenBool.$init$(this);
   }
}
