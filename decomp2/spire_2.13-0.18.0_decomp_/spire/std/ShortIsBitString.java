package spire.std;

import algebra.lattice.Bool;
import algebra.lattice.BoundedDistributiveLattice;
import algebra.lattice.BoundedJoinSemilattice;
import algebra.lattice.BoundedLattice;
import algebra.lattice.BoundedMeetSemilattice;
import algebra.lattice.GenBool;
import algebra.lattice.Heyting;
import algebra.lattice.JoinSemilattice;
import algebra.lattice.Lattice;
import algebra.lattice.MeetSemilattice;
import algebra.ring.BoolRing;
import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;
import spire.math.BitString;
import spire.math.BitString$mcS$sp;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001B\u000b\u0017\u0001mAQa\u000e\u0001\u0005\u0002aBQa\u000f\u0001\u0005\u0002qBQ!\u0010\u0001\u0005\u0002qBQA\u0010\u0001\u0005\u0002}BQ\u0001\u0012\u0001\u0005\u0002\u0015CQ\u0001\u0013\u0001\u0005\u0002%CQa\u0013\u0001\u0005B1CQa\u0014\u0001\u0005\u0002ACQ\u0001\u0016\u0001\u0005\u0002UCQ!\u0017\u0001\u0005\u0002iCQ!\u001a\u0001\u0005\u0002\u0019DQ\u0001\u001b\u0001\u0005\u0002%DQa\u001b\u0001\u0005\u00021DQA\u001c\u0001\u0005\u0002=DQ!\u001d\u0001\u0005\u0002IDQ\u0001\u001e\u0001\u0005\u0002UDQ!\u001f\u0001\u0005\u0002iDQ! \u0001\u0005\u0002yDq!a\u0001\u0001\t\u0003\t)\u0001C\u0004\u0002\f\u0001!\t!!\u0004\u0003!MCwN\u001d;Jg\nKGo\u0015;sS:<'BA\f\u0019\u0003\r\u0019H\u000f\u001a\u0006\u00023\u0005)1\u000f]5sK\u000e\u00011\u0003\u0002\u0001\u001dE-\u0002\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011a!\u00118z%\u00164\u0007cA\u0012'Q5\tAE\u0003\u0002&1\u0005!Q.\u0019;i\u0013\t9CEA\u0005CSR\u001cFO]5oOB\u0011Q$K\u0005\u0003Uy\u0011Qa\u00155peR\u0004\"\u0001\f\u001b\u000f\u00055\u0012dB\u0001\u00182\u001b\u0005y#B\u0001\u0019\u001b\u0003\u0019a$o\\8u}%\tq$\u0003\u00024=\u00059\u0001/Y2lC\u001e,\u0017BA\u001b7\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019d$\u0001\u0004=S:LGO\u0010\u000b\u0002sA\u0011!\bA\u0007\u0002-\u0005\u0019qN\\3\u0016\u0003!\nAA_3s_\u0006\u0019\u0011M\u001c3\u0015\u0007!\u0002%\tC\u0003B\t\u0001\u0007\u0001&A\u0001b\u0011\u0015\u0019E\u00011\u0001)\u0003\u0005\u0011\u0017AA8s)\rAci\u0012\u0005\u0006\u0003\u0016\u0001\r\u0001\u000b\u0005\u0006\u0007\u0016\u0001\r\u0001K\u0001\u000bG>l\u0007\u000f\\3nK:$HC\u0001\u0015K\u0011\u0015\te\u00011\u0001)\u0003\rAxN\u001d\u000b\u0004Q5s\u0005\"B!\b\u0001\u0004A\u0003\"B\"\b\u0001\u0004A\u0013AB:jO:,G-F\u0001R!\ti\"+\u0003\u0002T=\t9!i\\8mK\u0006t\u0017!B<jIRDW#\u0001,\u0011\u0005u9\u0016B\u0001-\u001f\u0005\rIe\u000e^\u0001\fi>DU\r_*ue&tw\r\u0006\u0002\\GB\u0011A\f\u0019\b\u0003;z\u0003\"A\f\u0010\n\u0005}s\u0012A\u0002)sK\u0012,g-\u0003\u0002bE\n11\u000b\u001e:j]\u001eT!a\u0018\u0010\t\u000b\u0011T\u0001\u0019\u0001\u0015\u0002\u00039\f\u0001BY5u\u0007>,h\u000e\u001e\u000b\u0003-\u001eDQ\u0001Z\u0006A\u0002!\nQ\u0002[5hQ\u0016\u001cHo\u00148f\u0005&$HC\u0001\u0015k\u0011\u0015!G\u00021\u0001)\u00031awn^3ti>sWMQ5u)\tAS\u000eC\u0003e\u001b\u0001\u0007\u0001&\u0001\u000bok6\u0014WM](g\u0019\u0016\fG-\u001b8h5\u0016\u0014xn\u001d\u000b\u0003-BDQ\u0001\u001a\bA\u0002!\nQC\\;nE\u0016\u0014xJ\u001a+sC&d\u0017N\\4[KJ|7\u000f\u0006\u0002Wg\")Am\u0004a\u0001Q\u0005IA.\u001a4u'\"Lg\r\u001e\u000b\u0004QY<\b\"\u00023\u0011\u0001\u0004A\u0003\"\u0002=\u0011\u0001\u00041\u0016!A5\u0002\u0015ILw\r\u001b;TQ&4G\u000fF\u0002)wrDQ\u0001Z\tA\u0002!BQ\u0001_\tA\u0002Y\u000b\u0001c]5h]\u0016$'+[4iiNC\u0017N\u001a;\u0015\t!z\u0018\u0011\u0001\u0005\u0006IJ\u0001\r\u0001\u000b\u0005\u0006qJ\u0001\rAV\u0001\u000be>$\u0018\r^3MK\u001a$H#\u0002\u0015\u0002\b\u0005%\u0001\"\u00023\u0014\u0001\u0004A\u0003\"\u0002=\u0014\u0001\u00041\u0016a\u0003:pi\u0006$XMU5hQR$R\u0001KA\b\u0003#AQ\u0001\u001a\u000bA\u0002!BQ\u0001\u001f\u000bA\u0002YCs\u0001AA\u000b\u00037\ti\u0002E\u0002\u001e\u0003/I1!!\u0007\u001f\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0001\u0001"
)
public class ShortIsBitString implements BitString$mcS$sp {
   private static final long serialVersionUID = 0L;

   public String toHexString$mcB$sp(final byte n) {
      return BitString.toHexString$mcB$sp$(this, n);
   }

   public String toHexString$mcI$sp(final int n) {
      return BitString.toHexString$mcI$sp$(this, n);
   }

   public String toHexString$mcJ$sp(final long n) {
      return BitString.toHexString$mcJ$sp$(this, n);
   }

   public int bitCount$mcB$sp(final byte n) {
      return BitString.bitCount$mcB$sp$(this, n);
   }

   public int bitCount$mcI$sp(final int n) {
      return BitString.bitCount$mcI$sp$(this, n);
   }

   public int bitCount$mcJ$sp(final long n) {
      return BitString.bitCount$mcJ$sp$(this, n);
   }

   public byte highestOneBit$mcB$sp(final byte n) {
      return BitString.highestOneBit$mcB$sp$(this, n);
   }

   public int highestOneBit$mcI$sp(final int n) {
      return BitString.highestOneBit$mcI$sp$(this, n);
   }

   public long highestOneBit$mcJ$sp(final long n) {
      return BitString.highestOneBit$mcJ$sp$(this, n);
   }

   public byte lowestOneBit$mcB$sp(final byte n) {
      return BitString.lowestOneBit$mcB$sp$(this, n);
   }

   public int lowestOneBit$mcI$sp(final int n) {
      return BitString.lowestOneBit$mcI$sp$(this, n);
   }

   public long lowestOneBit$mcJ$sp(final long n) {
      return BitString.lowestOneBit$mcJ$sp$(this, n);
   }

   public int numberOfLeadingZeros$mcB$sp(final byte n) {
      return BitString.numberOfLeadingZeros$mcB$sp$(this, n);
   }

   public int numberOfLeadingZeros$mcI$sp(final int n) {
      return BitString.numberOfLeadingZeros$mcI$sp$(this, n);
   }

   public int numberOfLeadingZeros$mcJ$sp(final long n) {
      return BitString.numberOfLeadingZeros$mcJ$sp$(this, n);
   }

   public int numberOfTrailingZeros$mcB$sp(final byte n) {
      return BitString.numberOfTrailingZeros$mcB$sp$(this, n);
   }

   public int numberOfTrailingZeros$mcI$sp(final int n) {
      return BitString.numberOfTrailingZeros$mcI$sp$(this, n);
   }

   public int numberOfTrailingZeros$mcJ$sp(final long n) {
      return BitString.numberOfTrailingZeros$mcJ$sp$(this, n);
   }

   public byte leftShift$mcB$sp(final byte n, final int i) {
      return BitString.leftShift$mcB$sp$(this, n, i);
   }

   public int leftShift$mcI$sp(final int n, final int i) {
      return BitString.leftShift$mcI$sp$(this, n, i);
   }

   public long leftShift$mcJ$sp(final long n, final int i) {
      return BitString.leftShift$mcJ$sp$(this, n, i);
   }

   public byte rightShift$mcB$sp(final byte n, final int i) {
      return BitString.rightShift$mcB$sp$(this, n, i);
   }

   public int rightShift$mcI$sp(final int n, final int i) {
      return BitString.rightShift$mcI$sp$(this, n, i);
   }

   public long rightShift$mcJ$sp(final long n, final int i) {
      return BitString.rightShift$mcJ$sp$(this, n, i);
   }

   public byte signedRightShift$mcB$sp(final byte n, final int i) {
      return BitString.signedRightShift$mcB$sp$(this, n, i);
   }

   public int signedRightShift$mcI$sp(final int n, final int i) {
      return BitString.signedRightShift$mcI$sp$(this, n, i);
   }

   public long signedRightShift$mcJ$sp(final long n, final int i) {
      return BitString.signedRightShift$mcJ$sp$(this, n, i);
   }

   public byte rotateLeft$mcB$sp(final byte n, final int i) {
      return BitString.rotateLeft$mcB$sp$(this, n, i);
   }

   public int rotateLeft$mcI$sp(final int n, final int i) {
      return BitString.rotateLeft$mcI$sp$(this, n, i);
   }

   public long rotateLeft$mcJ$sp(final long n, final int i) {
      return BitString.rotateLeft$mcJ$sp$(this, n, i);
   }

   public byte rotateRight$mcB$sp(final byte n, final int i) {
      return BitString.rotateRight$mcB$sp$(this, n, i);
   }

   public int rotateRight$mcI$sp(final int n, final int i) {
      return BitString.rotateRight$mcI$sp$(this, n, i);
   }

   public long rotateRight$mcJ$sp(final long n, final int i) {
      return BitString.rotateRight$mcJ$sp$(this, n, i);
   }

   public Object imp(final Object a, final Object b) {
      return Bool.imp$(this, a, b);
   }

   public int imp$mcI$sp(final int a, final int b) {
      return Bool.imp$mcI$sp$(this, a, b);
   }

   public long imp$mcJ$sp(final long a, final long b) {
      return Bool.imp$mcJ$sp$(this, a, b);
   }

   public Object without(final Object a, final Object b) {
      return Bool.without$(this, a, b);
   }

   public int without$mcI$sp(final int a, final int b) {
      return Bool.without$mcI$sp$(this, a, b);
   }

   public long without$mcJ$sp(final long a, final long b) {
      return Bool.without$mcJ$sp$(this, a, b);
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

   public BoolRing asBoolRing() {
      return Bool.asBoolRing$(this);
   }

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

   public short one() {
      return -1;
   }

   public short zero() {
      return 0;
   }

   public short and(final short a, final short b) {
      return (short)((short)(a & b));
   }

   public short or(final short a, final short b) {
      return (short)((short)(a | b));
   }

   public short complement(final short a) {
      return (short)(~a);
   }

   public short xor(final short a, final short b) {
      return (short)((short)(a ^ b));
   }

   public boolean signed() {
      return true;
   }

   public int width() {
      return 16;
   }

   public String toHexString(final short n) {
      return this.toHexString$mcS$sp(n);
   }

   public int bitCount(final short n) {
      return this.bitCount$mcS$sp(n);
   }

   public short highestOneBit(final short n) {
      return this.highestOneBit$mcS$sp(n);
   }

   public short lowestOneBit(final short n) {
      return this.lowestOneBit$mcS$sp(n);
   }

   public int numberOfLeadingZeros(final short n) {
      return this.numberOfLeadingZeros$mcS$sp(n);
   }

   public int numberOfTrailingZeros(final short n) {
      return this.numberOfTrailingZeros$mcS$sp(n);
   }

   public short leftShift(final short n, final int i) {
      return this.leftShift$mcS$sp(n, i);
   }

   public short rightShift(final short n, final int i) {
      return this.rightShift$mcS$sp(n, i);
   }

   public short signedRightShift(final short n, final int i) {
      return this.signedRightShift$mcS$sp(n, i);
   }

   public short rotateLeft(final short n, final int i) {
      return this.rotateLeft$mcS$sp(n, i);
   }

   public short rotateRight(final short n, final int i) {
      return this.rotateRight$mcS$sp(n, i);
   }

   public String toHexString$mcS$sp(final short n) {
      return Integer.toHexString(n & '\uffff');
   }

   public int bitCount$mcS$sp(final short n) {
      return Integer.bitCount(n & '\uffff');
   }

   public short highestOneBit$mcS$sp(final short n) {
      return (short)(Integer.highestOneBit(n & '\uffff') & '\uffff');
   }

   public short lowestOneBit$mcS$sp(final short n) {
      return (short)(Integer.lowestOneBit(n & '\uffff') & '\uffff');
   }

   public int numberOfLeadingZeros$mcS$sp(final short n) {
      return Integer.numberOfLeadingZeros(n & '\uffff') - 16;
   }

   public int numberOfTrailingZeros$mcS$sp(final short n) {
      return n == 0 ? 16 : Integer.numberOfTrailingZeros(n & '\uffff');
   }

   public short leftShift$mcS$sp(final short n, final int i) {
      return (short)((n & '\uffff') << (i & 15) & '\uffff');
   }

   public short rightShift$mcS$sp(final short n, final int i) {
      return (short)((n & '\uffff') >>> (i & 15) & '\uffff');
   }

   public short signedRightShift$mcS$sp(final short n, final int i) {
      return (short)(n >> (i & 15) & '\uffff');
   }

   public short rotateLeft$mcS$sp(final short n, final int i) {
      int j = i & 15;
      return (short)(((n & '\uffff') << j | (n & '\uffff') >>> 16 - j) & '\uffff');
   }

   public short rotateRight$mcS$sp(final short n, final int i) {
      int j = i & 15;
      return (short)(((n & '\uffff') >>> j | (n & '\uffff') << 16 - j) & '\uffff');
   }

   public ShortIsBitString() {
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
