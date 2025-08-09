package algebra.lattice;

import algebra.ring.BoolRing;
import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193A!\u0003\u0006\u0001\u001f!Aa\u0005\u0001B\u0001B\u0003%q\u0005C\u0003.\u0001\u0011\u0005a\u0006C\u00032\u0001\u0011\u0005!\u0007C\u00034\u0001\u0011\u0005A\u0007C\u00038\u0001\u0011\u0005\u0003\bC\u0003=\u0001\u0011\u0005S\bC\u0003?\u0001\u0011\u0005s\bC\u0003C\u0001\u0011\u00053I\u0001\tC_>dgI]8n\u0005>|GNU5oO*\u00111\u0002D\u0001\bY\u0006$H/[2f\u0015\u0005i\u0011aB1mO\u0016\u0014'/Y\u0002\u0001+\t\u0001rcE\u0002\u0001#\r\u00022AE\n\u0016\u001b\u0005Q\u0011B\u0001\u000b\u000b\u0005I9UM\u001c\"p_24%o\\7C_>d'K\\4\u0011\u0005Y9B\u0002\u0001\u0003\u00061\u0001\u0011\r!\u0007\u0002\u0002\u0003F\u0011!\u0004\t\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\b\u001d>$\b.\u001b8h!\tY\u0012%\u0003\u0002#9\t\u0019\u0011I\\=\u0011\u0007I!S#\u0003\u0002&\u0015\t!!i\\8m\u0003\u0011y'/[4\u0011\u0007!ZS#D\u0001*\u0015\tQC\"\u0001\u0003sS:<\u0017B\u0001\u0017*\u0005!\u0011un\u001c7SS:<\u0017A\u0002\u001fj]&$h\b\u0006\u00020aA\u0019!\u0003A\u000b\t\u000b\u0019\u0012\u0001\u0019A\u0014\u0002\u0007=tW-F\u0001\u0016\u0003)\u0019w.\u001c9mK6,g\u000e\u001e\u000b\u0003+UBQA\u000e\u0003A\u0002U\t\u0011!Y\u0001\bo&$\bn\\;u)\r)\u0012H\u000f\u0005\u0006m\u0015\u0001\r!\u0006\u0005\u0006w\u0015\u0001\r!F\u0001\u0002E\u0006Q\u0011m\u001d\"p_2\u0014\u0016N\\4\u0016\u0003\u001d\nA!\\3fiR\u0019Q\u0003Q!\t\u000bY:\u0001\u0019A\u000b\t\u000bm:\u0001\u0019A\u000b\u0002\t)|\u0017N\u001c\u000b\u0004+\u0011+\u0005\"\u0002\u001c\t\u0001\u0004)\u0002\"B\u001e\t\u0001\u0004)\u0002"
)
public class BoolFromBoolRing extends GenBoolFromBoolRng implements Bool {
   private final BoolRing orig;

   public Object imp(final Object a, final Object b) {
      return Bool.imp$(this, a, b);
   }

   public int imp$mcI$sp(final int a, final int b) {
      return Bool.imp$mcI$sp$(this, a, b);
   }

   public long imp$mcJ$sp(final long a, final long b) {
      return Bool.imp$mcJ$sp$(this, a, b);
   }

   public int without$mcI$sp(final int a, final int b) {
      return Bool.without$mcI$sp$(this, a, b);
   }

   public long without$mcJ$sp(final long a, final long b) {
      return Bool.without$mcJ$sp$(this, a, b);
   }

   public Object xor(final Object a, final Object b) {
      return Bool.xor$(this, a, b);
   }

   public int xor$mcI$sp(final int a, final int b) {
      return Bool.xor$mcI$sp$(this, a, b);
   }

   public long xor$mcJ$sp(final long a, final long b) {
      return Bool.xor$mcJ$sp$(this, a, b);
   }

   public Bool dual() {
      return Bool.dual$(this);
   }

   public Bool dual$mcI$sp() {
      return Bool.dual$mcI$sp$(this);
   }

   public Bool dual$mcJ$sp() {
      return Bool.dual$mcJ$sp$(this);
   }

   public int and$mcI$sp(final int a, final int b) {
      return Heyting.and$mcI$sp$(this, a, b);
   }

   public long and$mcJ$sp(final long a, final long b) {
      return Heyting.and$mcJ$sp$(this, a, b);
   }

   public int meet$mcI$sp(final int a, final int b) {
      return Heyting.meet$mcI$sp$(this, a, b);
   }

   public long meet$mcJ$sp(final long a, final long b) {
      return Heyting.meet$mcJ$sp$(this, a, b);
   }

   public int or$mcI$sp(final int a, final int b) {
      return Heyting.or$mcI$sp$(this, a, b);
   }

   public long or$mcJ$sp(final long a, final long b) {
      return Heyting.or$mcJ$sp$(this, a, b);
   }

   public int join$mcI$sp(final int a, final int b) {
      return Heyting.join$mcI$sp$(this, a, b);
   }

   public long join$mcJ$sp(final long a, final long b) {
      return Heyting.join$mcJ$sp$(this, a, b);
   }

   public int complement$mcI$sp(final int a) {
      return Heyting.complement$mcI$sp$(this, a);
   }

   public long complement$mcJ$sp(final long a) {
      return Heyting.complement$mcJ$sp$(this, a);
   }

   public Object nand(final Object a, final Object b) {
      return Heyting.nand$(this, a, b);
   }

   public int nand$mcI$sp(final int a, final int b) {
      return Heyting.nand$mcI$sp$(this, a, b);
   }

   public long nand$mcJ$sp(final long a, final long b) {
      return Heyting.nand$mcJ$sp$(this, a, b);
   }

   public Object nor(final Object a, final Object b) {
      return Heyting.nor$(this, a, b);
   }

   public int nor$mcI$sp(final int a, final int b) {
      return Heyting.nor$mcI$sp$(this, a, b);
   }

   public long nor$mcJ$sp(final long a, final long b) {
      return Heyting.nor$mcJ$sp$(this, a, b);
   }

   public Object nxor(final Object a, final Object b) {
      return Heyting.nxor$(this, a, b);
   }

   public int nxor$mcI$sp(final int a, final int b) {
      return Heyting.nxor$mcI$sp$(this, a, b);
   }

   public long nxor$mcJ$sp(final long a, final long b) {
      return Heyting.nxor$mcJ$sp$(this, a, b);
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

   public Object one() {
      return this.orig.one();
   }

   public Object complement(final Object a) {
      return this.orig.plus(this.orig.one(), a);
   }

   public Object without(final Object a, final Object b) {
      return super.without(a, b);
   }

   public BoolRing asBoolRing() {
      return this.orig;
   }

   public Object meet(final Object a, final Object b) {
      return GenBool.meet$(this, a, b);
   }

   public Object join(final Object a, final Object b) {
      return GenBool.join$(this, a, b);
   }

   public BoolFromBoolRing(final BoolRing orig) {
      super(orig);
      this.orig = orig;
      BoundedMeetSemilattice.$init$(this);
      BoundedLattice.$init$(this);
      BoundedDistributiveLattice.$init$(this);
      Heyting.$init$(this);
      Bool.$init$(this);
   }
}
