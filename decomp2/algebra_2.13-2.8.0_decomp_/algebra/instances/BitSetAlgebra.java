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
import scala.collection.immutable.BitSet;
import scala.collection.immutable.BitSet.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3A\u0001C\u0005\u0001\u001d!)q\u0006\u0001C\u0001a!91\u0007\u0001b\u0001\n\u0003!\u0004BB\u001b\u0001A\u0003%1\u0004C\u00037\u0001\u0011\u0005q\u0007C\u0003=\u0001\u0011\u0005Q\bC\u0003A\u0001\u0011\u0005\u0011\tC\u0003E\u0001\u0011\u0005SIA\u0007CSR\u001cV\r^!mO\u0016\u0014'/\u0019\u0006\u0003\u0015-\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u00031\tq!\u00197hK\n\u0014\u0018m\u0001\u0001\u0014\t\u0001yQc\t\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007YI2$D\u0001\u0018\u0015\tA2\"A\u0004mCR$\u0018nY3\n\u0005i9\"aB$f]\n{w\u000e\u001c\t\u00039\u0005j\u0011!\b\u0006\u0003=}\t\u0011\"[7nkR\f'\r\\3\u000b\u0005\u0001\n\u0012AC2pY2,7\r^5p]&\u0011!%\b\u0002\u0007\u0005&$8+\u001a;\u0011\u0005\u0011bcBA\u0013+\u001d\t1\u0013&D\u0001(\u0015\tAS\"\u0001\u0004=e>|GOP\u0005\u0002%%\u00111&E\u0001\ba\u0006\u001c7.Y4f\u0013\ticF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002,#\u00051A(\u001b8jiz\"\u0012!\r\t\u0003e\u0001i\u0011!C\u0001\u0005u\u0016\u0014x.F\u0001\u001c\u0003\u0015QXM]8!\u0003\r\tg\u000e\u001a\u000b\u00047aR\u0004\"B\u001d\u0005\u0001\u0004Y\u0012!A1\t\u000bm\"\u0001\u0019A\u000e\u0002\u0003\t\f!a\u001c:\u0015\u0007mqt\bC\u0003:\u000b\u0001\u00071\u0004C\u0003<\u000b\u0001\u00071$A\u0004xSRDw.\u001e;\u0015\u0007m\u00115\tC\u0003:\r\u0001\u00071\u0004C\u0003<\r\u0001\u00071$A\u0002y_J$2a\u0007$H\u0011\u0015It\u00011\u0001\u001c\u0011\u0015Yt\u00011\u0001\u001c\u0001"
)
public class BitSetAlgebra implements GenBool {
   private final BitSet zero;

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

   public BitSet zero() {
      return this.zero;
   }

   public BitSet and(final BitSet a, final BitSet b) {
      return (BitSet)a.$amp(b);
   }

   public BitSet or(final BitSet a, final BitSet b) {
      return (BitSet)a.$bar(b);
   }

   public BitSet without(final BitSet a, final BitSet b) {
      return (BitSet)a.$minus$minus(b);
   }

   public BitSet xor(final BitSet a, final BitSet b) {
      return (BitSet)a.$up(b);
   }

   public BitSetAlgebra() {
      JoinSemilattice.$init$(this);
      MeetSemilattice.$init$(this);
      Lattice.$init$(this);
      BoundedJoinSemilattice.$init$(this);
      GenBool.$init$(this);
      this.zero = .MODULE$.empty();
   }
}
