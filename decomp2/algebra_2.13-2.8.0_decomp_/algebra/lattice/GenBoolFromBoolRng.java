package algebra.lattice;

import algebra.ring.BoolRng;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semilattice;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3A\u0001C\u0005\u0001\u001d!AQ\u0005\u0001B\u0001B\u0003%a\u0005C\u0003-\u0001\u0011\u0005Q\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00033\u0001\u0011\u00051\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0003=\u0001\u0011\u0005Q\bC\u0003A\u0001\u0011\u0005\u0013I\u0001\nHK:\u0014un\u001c7Ge>l'i\\8m%:<'B\u0001\u0006\f\u0003\u001da\u0017\r\u001e;jG\u0016T\u0011\u0001D\u0001\bC2<WM\u0019:b\u0007\u0001)\"a\u0004\u000f\u0014\u0007\u0001\u0001b\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0004/aQR\"A\u0005\n\u0005eI!aB$f]\n{w\u000e\u001c\t\u00037qa\u0001\u0001B\u0003\u001e\u0001\t\u0007aDA\u0001B#\ty\"\u0005\u0005\u0002\u0012A%\u0011\u0011E\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t2%\u0003\u0002%%\t\u0019\u0011I\\=\u0002\t=\u0014\u0018n\u001a\t\u0004O)RR\"\u0001\u0015\u000b\u0005%Z\u0011\u0001\u0002:j]\u001eL!a\u000b\u0015\u0003\u000f\t{w\u000e\u001c*oO\u00061A(\u001b8jiz\"\"AL\u0018\u0011\u0007]\u0001!\u0004C\u0003&\u0005\u0001\u0007a%\u0001\u0003{KJ|W#\u0001\u000e\u0002\u0007\u0005tG\rF\u0002\u001biYBQ!\u000e\u0003A\u0002i\t\u0011!\u0019\u0005\u0006o\u0011\u0001\rAG\u0001\u0002E\u0006\u0011qN\u001d\u000b\u00045iZ\u0004\"B\u001b\u0006\u0001\u0004Q\u0002\"B\u001c\u0006\u0001\u0004Q\u0012aB<ji\"|W\u000f\u001e\u000b\u00045yz\u0004\"B\u001b\u0007\u0001\u0004Q\u0002\"B\u001c\u0007\u0001\u0004Q\u0012AC1t\u0005>|GNU5oOV\ta\u0005"
)
public class GenBoolFromBoolRng implements GenBool {
   private final BoolRng orig;

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

   public Object zero() {
      return this.orig.zero();
   }

   public Object and(final Object a, final Object b) {
      return this.orig.times(a, b);
   }

   public Object or(final Object a, final Object b) {
      return this.orig.plus(this.orig.plus(a, b), this.orig.times(a, b));
   }

   public Object without(final Object a, final Object b) {
      return this.orig.plus(a, this.orig.times(a, b));
   }

   public BoolRng asBoolRing() {
      return this.orig;
   }

   public GenBoolFromBoolRng(final BoolRng orig) {
      this.orig = orig;
      JoinSemilattice.$init$(this);
      MeetSemilattice.$init$(this);
      Lattice.$init$(this);
      BoundedJoinSemilattice.$init$(this);
      GenBool.$init$(this);
   }
}
