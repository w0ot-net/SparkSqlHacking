package algebra.lattice;

import algebra.ring.BoolRing;
import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%4AAD\b\u0001)!Aa\b\u0001B\u0001B\u0003%A\u0004C\u0003@\u0001\u0011\u0005\u0001\tC\u0003D\u0001\u0011\u0005A\tC\u0003F\u0001\u0011\u0005A\tC\u0003G\u0001\u0011\u0005q\tC\u0003M\u0001\u0011\u0005Q\nC\u0003Q\u0001\u0011\u0005\u0011\u000bC\u0003T\u0001\u0011\u0005C\u000bC\u0003X\u0001\u0011\u0005\u0003\fC\u0003\\\u0001\u0011\u0005C\fC\u0003`\u0001\u0011\u0005\u0003\rC\u0003d\u0001\u0011\u0005C\rC\u0003h\u0001\u0011\u0005\u0003N\u0001\u0005Ek\u0006d'i\\8m\u0015\t\u0001\u0012#A\u0004mCR$\u0018nY3\u000b\u0003I\tq!\u00197hK\n\u0014\u0018m\u0001\u0001\u0016\u0005U\u00113c\u0001\u0001\u00179A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\u00042!\b\u0010!\u001b\u0005y\u0011BA\u0010\u0010\u0005\u0011\u0011un\u001c7\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\nG\u0001\u0001\u000b\u0011!AC\u0002\u0011\u0012\u0011!Q\t\u0003K!\u0002\"a\u0006\u0014\n\u0005\u001dB\"a\u0002(pi\"Lgn\u001a\t\u0003/%J!A\u000b\r\u0003\u0007\u0005s\u0017\u0010\u000b\u0003#Y=J\u0004CA\f.\u0013\tq\u0003DA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u00121cM\u0012dBA\f2\u0013\t\u0011\u0004$A\u0002J]R\fD\u0001\n\u001b939\u0011Q\u0007O\u0007\u0002m)\u0011qgE\u0001\u0007yI|w\u000e\u001e \n\u0003e\tTa\t\u001e<{qr!aF\u001e\n\u0005qB\u0012\u0001\u0002'p]\u001e\fD\u0001\n\u001b93\u0005!qN]5h\u0003\u0019a\u0014N\\5u}Q\u0011\u0011I\u0011\t\u0004;\u0001\u0001\u0003\"\u0002 \u0003\u0001\u0004a\u0012aA8oKV\t\u0001%\u0001\u0003{KJ|\u0017aA1oIR\u0019\u0001\u0005\u0013&\t\u000b%+\u0001\u0019\u0001\u0011\u0002\u0003\u0005DQaS\u0003A\u0002\u0001\n\u0011AY\u0001\u0003_J$2\u0001\t(P\u0011\u0015Ie\u00011\u0001!\u0011\u0015Ye\u00011\u0001!\u0003)\u0019w.\u001c9mK6,g\u000e\u001e\u000b\u0003AICQ!S\u0004A\u0002\u0001\n1\u0001_8s)\r\u0001SK\u0016\u0005\u0006\u0013\"\u0001\r\u0001\t\u0005\u0006\u0017\"\u0001\r\u0001I\u0001\u0004S6\u0004Hc\u0001\u0011Z5\")\u0011*\u0003a\u0001A!)1*\u0003a\u0001A\u0005!a.\u00198e)\r\u0001SL\u0018\u0005\u0006\u0013*\u0001\r\u0001\t\u0005\u0006\u0017*\u0001\r\u0001I\u0001\u0004]>\u0014Hc\u0001\u0011bE\")\u0011j\u0003a\u0001A!)1j\u0003a\u0001A\u0005!a\u000e_8s)\r\u0001SM\u001a\u0005\u0006\u00132\u0001\r\u0001\t\u0005\u0006\u00172\u0001\r\u0001I\u0001\u0005IV\fG.F\u0001\u001d\u0001"
)
public class DualBool implements Bool {
   public final Bool orig;

   public Object without(final Object a, final Object b) {
      return Bool.without$(this, a, b);
   }

   public int without$mcI$sp(final int a, final int b) {
      return Bool.without$mcI$sp$(this, a, b);
   }

   public long without$mcJ$sp(final long a, final long b) {
      return Bool.without$mcJ$sp$(this, a, b);
   }

   public BoolRing asBoolRing() {
      return Bool.asBoolRing$(this);
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

   public Object join(final Object a, final Object b) {
      return GenBool.join$(this, a, b);
   }

   public int join$mcI$sp(final int a, final int b) {
      return GenBool.join$mcI$sp$(this, a, b);
   }

   public long join$mcJ$sp(final long a, final long b) {
      return GenBool.join$mcJ$sp$(this, a, b);
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

   public BoundedDistributiveLattice dual$mcD$sp() {
      return BoundedDistributiveLattice.dual$mcD$sp$(this);
   }

   public BoundedDistributiveLattice dual$mcF$sp() {
      return BoundedDistributiveLattice.dual$mcF$sp$(this);
   }

   public double zero$mcD$sp() {
      return BoundedJoinSemilattice.zero$mcD$sp$(this);
   }

   public float zero$mcF$sp() {
      return BoundedJoinSemilattice.zero$mcF$sp$(this);
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

   public Object one() {
      return this.orig.zero();
   }

   public Object zero() {
      return this.orig.one();
   }

   public Object and(final Object a, final Object b) {
      return this.orig.or(a, b);
   }

   public Object or(final Object a, final Object b) {
      return this.orig.and(a, b);
   }

   public Object complement(final Object a) {
      return this.orig.complement(a);
   }

   public Object xor(final Object a, final Object b) {
      return this.orig.complement(this.orig.xor(a, b));
   }

   public Object imp(final Object a, final Object b) {
      return this.orig.and(this.orig.complement(a), b);
   }

   public Object nand(final Object a, final Object b) {
      return this.orig.nor(a, b);
   }

   public Object nor(final Object a, final Object b) {
      return this.orig.nand(a, b);
   }

   public Object nxor(final Object a, final Object b) {
      return this.orig.xor(a, b);
   }

   public Bool dual() {
      return this.orig;
   }

   public int one$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.one());
   }

   public long one$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.one());
   }

   public int zero$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.zero());
   }

   public long zero$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.zero());
   }

   public int and$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.and(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   public long and$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.and(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   public int or$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.or(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   public long or$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.or(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   public int complement$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.complement(BoxesRunTime.boxToInteger(a)));
   }

   public long complement$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.complement(BoxesRunTime.boxToLong(a)));
   }

   public int xor$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.xor(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   public long xor$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.xor(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   public int imp$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.imp(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   public long imp$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.imp(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   public int nand$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.nand(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   public long nand$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.nand(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   public int nor$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.nor(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   public long nor$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.nor(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   public int nxor$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.nxor(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   public long nxor$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.nxor(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   public Bool dual$mcI$sp() {
      return this.dual();
   }

   public Bool dual$mcJ$sp() {
      return this.dual();
   }

   public DualBool(final Bool orig) {
      this.orig = orig;
      JoinSemilattice.$init$(this);
      MeetSemilattice.$init$(this);
      Lattice.$init$(this);
      BoundedMeetSemilattice.$init$(this);
      BoundedJoinSemilattice.$init$(this);
      BoundedLattice.$init$(this);
      BoundedDistributiveLattice.$init$(this);
      Heyting.$init$(this);
      GenBool.$init$(this);
      Bool.$init$(this);
   }
}
