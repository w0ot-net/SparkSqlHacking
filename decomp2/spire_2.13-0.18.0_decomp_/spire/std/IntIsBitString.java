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
import algebra.lattice.Bool.mcI.sp;
import algebra.ring.BoolRing;
import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;
import spire.math.BitString;
import spire.math.BitString$mcI$sp;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001\u0002\u000b\u0016\u0001iAQA\u000e\u0001\u0005\u0002]BQA\u000f\u0001\u0005\u0002mBQ\u0001\u0010\u0001\u0005\u0002mBQ!\u0010\u0001\u0005\u0002yBQa\u0011\u0001\u0005\u0002\u0011CQa\u0012\u0001\u0005\u0002!CQA\u0013\u0001\u0005\u0002-CQa\u0014\u0001\u0005\u0002mBQ\u0001\u0015\u0001\u0005\u0002ECQ\u0001\u0018\u0001\u0005\u0002uCQa\u0018\u0001\u0005\u0002\u0001DQA\u0019\u0001\u0005\u0002\rDQ!\u001a\u0001\u0005\u0002\u0019DQ\u0001\u001b\u0001\u0005\u0002%DQa\u001b\u0001\u0005\u00021DQ\u0001\u001d\u0001\u0005\u0002EDQ\u0001\u001e\u0001\u0005\u0002UDQ\u0001\u001f\u0001\u0005\u0002eDQ\u0001 \u0001\u0005\u0002u\u0014a\"\u00138u\u0013N\u0014\u0015\u000e^*ue&twM\u0003\u0002\u0017/\u0005\u00191\u000f\u001e3\u000b\u0003a\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0003\u00017\u0005R\u0003C\u0001\u000f \u001b\u0005i\"\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\"AB!osJ+g\rE\u0002#K\u001dj\u0011a\t\u0006\u0003I]\tA!\\1uQ&\u0011ae\t\u0002\n\u0005&$8\u000b\u001e:j]\u001e\u0004\"\u0001\b\u0015\n\u0005%j\"aA%oiB\u00111f\r\b\u0003YEr!!\f\u0019\u000e\u00039R!aL\r\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0012B\u0001\u001a\u001e\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001N\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Ij\u0012A\u0002\u001fj]&$h\bF\u00019!\tI\u0004!D\u0001\u0016\u0003\ryg.Z\u000b\u0002O\u0005!!0\u001a:p\u0003\r\tg\u000e\u001a\u000b\u0004O}\n\u0005\"\u0002!\u0005\u0001\u00049\u0013!A1\t\u000b\t#\u0001\u0019A\u0014\u0002\u0003\t\f!a\u001c:\u0015\u0007\u001d*e\tC\u0003A\u000b\u0001\u0007q\u0005C\u0003C\u000b\u0001\u0007q%\u0001\u0006d_6\u0004H.Z7f]R$\"aJ%\t\u000b\u00013\u0001\u0019A\u0014\u0002\rMLwM\\3e+\u0005a\u0005C\u0001\u000fN\u0013\tqUDA\u0004C_>dW-\u00198\u0002\u000b]LG\r\u001e5\u0002\u0017Q|\u0007*\u001a=TiJLgn\u001a\u000b\u0003%j\u0003\"aU,\u000f\u0005Q+\u0006CA\u0017\u001e\u0013\t1V$\u0001\u0004Qe\u0016$WMZ\u0005\u00031f\u0013aa\u0015;sS:<'B\u0001,\u001e\u0011\u0015Y\u0016\u00021\u0001(\u0003\u0005q\u0017\u0001\u00032ji\u000e{WO\u001c;\u0015\u0005\u001dr\u0006\"B.\u000b\u0001\u00049\u0013!\u00045jO\",7\u000f^(oK\nKG\u000f\u0006\u0002(C\")1l\u0003a\u0001O\u0005aAn\\<fgR|e.\u001a\"jiR\u0011q\u0005\u001a\u0005\u000672\u0001\raJ\u0001\u0015]Vl'-\u001a:PM2+\u0017\rZ5oOj+'o\\:\u0015\u0005\u001d:\u0007\"B.\u000e\u0001\u00049\u0013!\u00068v[\n,'o\u00144Ue\u0006LG.\u001b8h5\u0016\u0014xn\u001d\u000b\u0003O)DQa\u0017\bA\u0002\u001d\n\u0011\u0002\\3giNC\u0017N\u001a;\u0015\u0007\u001djg\u000eC\u0003\\\u001f\u0001\u0007q\u0005C\u0003p\u001f\u0001\u0007q%A\u0001j\u0003)\u0011\u0018n\u001a5u'\"Lg\r\u001e\u000b\u0004OI\u001c\b\"B.\u0011\u0001\u00049\u0003\"B8\u0011\u0001\u00049\u0013\u0001E:jO:,GMU5hQR\u001c\u0006.\u001b4u)\r9co\u001e\u0005\u00067F\u0001\ra\n\u0005\u0006_F\u0001\raJ\u0001\u000be>$\u0018\r^3MK\u001a$HcA\u0014{w\")1L\u0005a\u0001O!)qN\u0005a\u0001O\u0005Y!o\u001c;bi\u0016\u0014\u0016n\u001a5u)\r9cp \u0005\u00067N\u0001\ra\n\u0005\u0006_N\u0001\ra\n\u0015\b\u0001\u0005\r\u0011\u0011BA\u0006!\ra\u0012QA\u0005\u0004\u0003\u000fi\"\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0001\u0001"
)
public class IntIsBitString implements BitString$mcI$sp {
   private static final long serialVersionUID = 0L;

   public int imp(final int a, final int b) {
      return sp.imp$(this, a, b);
   }

   public int imp$mcI$sp(final int a, final int b) {
      return sp.imp$mcI$sp$(this, a, b);
   }

   public int without(final int a, final int b) {
      return sp.without$(this, a, b);
   }

   public int without$mcI$sp(final int a, final int b) {
      return sp.without$mcI$sp$(this, a, b);
   }

   public int xor(final int a, final int b) {
      return sp.xor$(this, a, b);
   }

   public int xor$mcI$sp(final int a, final int b) {
      return sp.xor$mcI$sp$(this, a, b);
   }

   public Bool dual() {
      return sp.dual$(this);
   }

   public Bool dual$mcI$sp() {
      return sp.dual$mcI$sp$(this);
   }

   public int meet(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.meet$(this, a, b);
   }

   public int meet$mcI$sp(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.meet$mcI$sp$(this, a, b);
   }

   public int join(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.join$(this, a, b);
   }

   public int join$mcI$sp(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.join$mcI$sp$(this, a, b);
   }

   public int nand(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.nand$(this, a, b);
   }

   public int nand$mcI$sp(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.nand$mcI$sp$(this, a, b);
   }

   public int nor(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.nor$(this, a, b);
   }

   public int nor$mcI$sp(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.nor$mcI$sp$(this, a, b);
   }

   public int nxor(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.nxor$(this, a, b);
   }

   public int nxor$mcI$sp(final int a, final int b) {
      return algebra.lattice.Heyting.mcI.sp.nxor$mcI$sp$(this, a, b);
   }

   public CommutativeRig asCommutativeRig() {
      return algebra.lattice.BoundedDistributiveLattice.mcI.sp.asCommutativeRig$(this);
   }

   public CommutativeRig asCommutativeRig$mcI$sp() {
      return algebra.lattice.BoundedDistributiveLattice.mcI.sp.asCommutativeRig$mcI$sp$(this);
   }

   public boolean isOne(final int a, final Eq ev) {
      return algebra.lattice.BoundedMeetSemilattice.mcI.sp.isOne$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return algebra.lattice.BoundedMeetSemilattice.mcI.sp.isOne$mcI$sp$(this, a, ev);
   }

   public BoundedSemilattice meetSemilattice() {
      return algebra.lattice.BoundedMeetSemilattice.mcI.sp.meetSemilattice$(this);
   }

   public BoundedSemilattice meetSemilattice$mcI$sp() {
      return algebra.lattice.BoundedMeetSemilattice.mcI.sp.meetSemilattice$mcI$sp$(this);
   }

   public PartialOrder meetPartialOrder(final Eq ev) {
      return algebra.lattice.MeetSemilattice.mcI.sp.meetPartialOrder$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
      return algebra.lattice.MeetSemilattice.mcI.sp.meetPartialOrder$mcI$sp$(this, ev);
   }

   public boolean isZero(final int a, final Eq ev) {
      return algebra.lattice.BoundedJoinSemilattice.mcI.sp.isZero$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return algebra.lattice.BoundedJoinSemilattice.mcI.sp.isZero$mcI$sp$(this, a, ev);
   }

   public BoundedSemilattice joinSemilattice() {
      return algebra.lattice.BoundedJoinSemilattice.mcI.sp.joinSemilattice$(this);
   }

   public BoundedSemilattice joinSemilattice$mcI$sp() {
      return algebra.lattice.BoundedJoinSemilattice.mcI.sp.joinSemilattice$mcI$sp$(this);
   }

   public PartialOrder joinPartialOrder(final Eq ev) {
      return algebra.lattice.JoinSemilattice.mcI.sp.joinPartialOrder$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
      return algebra.lattice.JoinSemilattice.mcI.sp.joinPartialOrder$mcI$sp$(this, ev);
   }

   public String toHexString$mcB$sp(final byte n) {
      return BitString.toHexString$mcB$sp$(this, n);
   }

   public String toHexString$mcJ$sp(final long n) {
      return BitString.toHexString$mcJ$sp$(this, n);
   }

   public String toHexString$mcS$sp(final short n) {
      return BitString.toHexString$mcS$sp$(this, n);
   }

   public int bitCount$mcB$sp(final byte n) {
      return BitString.bitCount$mcB$sp$(this, n);
   }

   public int bitCount$mcJ$sp(final long n) {
      return BitString.bitCount$mcJ$sp$(this, n);
   }

   public int bitCount$mcS$sp(final short n) {
      return BitString.bitCount$mcS$sp$(this, n);
   }

   public byte highestOneBit$mcB$sp(final byte n) {
      return BitString.highestOneBit$mcB$sp$(this, n);
   }

   public long highestOneBit$mcJ$sp(final long n) {
      return BitString.highestOneBit$mcJ$sp$(this, n);
   }

   public short highestOneBit$mcS$sp(final short n) {
      return BitString.highestOneBit$mcS$sp$(this, n);
   }

   public byte lowestOneBit$mcB$sp(final byte n) {
      return BitString.lowestOneBit$mcB$sp$(this, n);
   }

   public long lowestOneBit$mcJ$sp(final long n) {
      return BitString.lowestOneBit$mcJ$sp$(this, n);
   }

   public short lowestOneBit$mcS$sp(final short n) {
      return BitString.lowestOneBit$mcS$sp$(this, n);
   }

   public int numberOfLeadingZeros$mcB$sp(final byte n) {
      return BitString.numberOfLeadingZeros$mcB$sp$(this, n);
   }

   public int numberOfLeadingZeros$mcJ$sp(final long n) {
      return BitString.numberOfLeadingZeros$mcJ$sp$(this, n);
   }

   public int numberOfLeadingZeros$mcS$sp(final short n) {
      return BitString.numberOfLeadingZeros$mcS$sp$(this, n);
   }

   public int numberOfTrailingZeros$mcB$sp(final byte n) {
      return BitString.numberOfTrailingZeros$mcB$sp$(this, n);
   }

   public int numberOfTrailingZeros$mcJ$sp(final long n) {
      return BitString.numberOfTrailingZeros$mcJ$sp$(this, n);
   }

   public int numberOfTrailingZeros$mcS$sp(final short n) {
      return BitString.numberOfTrailingZeros$mcS$sp$(this, n);
   }

   public byte leftShift$mcB$sp(final byte n, final int i) {
      return BitString.leftShift$mcB$sp$(this, n, i);
   }

   public long leftShift$mcJ$sp(final long n, final int i) {
      return BitString.leftShift$mcJ$sp$(this, n, i);
   }

   public short leftShift$mcS$sp(final short n, final int i) {
      return BitString.leftShift$mcS$sp$(this, n, i);
   }

   public byte rightShift$mcB$sp(final byte n, final int i) {
      return BitString.rightShift$mcB$sp$(this, n, i);
   }

   public long rightShift$mcJ$sp(final long n, final int i) {
      return BitString.rightShift$mcJ$sp$(this, n, i);
   }

   public short rightShift$mcS$sp(final short n, final int i) {
      return BitString.rightShift$mcS$sp$(this, n, i);
   }

   public byte signedRightShift$mcB$sp(final byte n, final int i) {
      return BitString.signedRightShift$mcB$sp$(this, n, i);
   }

   public long signedRightShift$mcJ$sp(final long n, final int i) {
      return BitString.signedRightShift$mcJ$sp$(this, n, i);
   }

   public short signedRightShift$mcS$sp(final short n, final int i) {
      return BitString.signedRightShift$mcS$sp$(this, n, i);
   }

   public byte rotateLeft$mcB$sp(final byte n, final int i) {
      return BitString.rotateLeft$mcB$sp$(this, n, i);
   }

   public long rotateLeft$mcJ$sp(final long n, final int i) {
      return BitString.rotateLeft$mcJ$sp$(this, n, i);
   }

   public short rotateLeft$mcS$sp(final short n, final int i) {
      return BitString.rotateLeft$mcS$sp$(this, n, i);
   }

   public byte rotateRight$mcB$sp(final byte n, final int i) {
      return BitString.rotateRight$mcB$sp$(this, n, i);
   }

   public long rotateRight$mcJ$sp(final long n, final int i) {
      return BitString.rotateRight$mcJ$sp$(this, n, i);
   }

   public short rotateRight$mcS$sp(final short n, final int i) {
      return BitString.rotateRight$mcS$sp$(this, n, i);
   }

   public long imp$mcJ$sp(final long a, final long b) {
      return Bool.imp$mcJ$sp$(this, a, b);
   }

   public long without$mcJ$sp(final long a, final long b) {
      return Bool.without$mcJ$sp$(this, a, b);
   }

   public long xor$mcJ$sp(final long a, final long b) {
      return Bool.xor$mcJ$sp$(this, a, b);
   }

   public Bool dual$mcJ$sp() {
      return Bool.dual$mcJ$sp$(this);
   }

   public BoolRing asBoolRing() {
      return Bool.asBoolRing$(this);
   }

   public long and$mcJ$sp(final long a, final long b) {
      return GenBool.and$mcJ$sp$(this, a, b);
   }

   public long meet$mcJ$sp(final long a, final long b) {
      return GenBool.meet$mcJ$sp$(this, a, b);
   }

   public long or$mcJ$sp(final long a, final long b) {
      return GenBool.or$mcJ$sp$(this, a, b);
   }

   public long join$mcJ$sp(final long a, final long b) {
      return GenBool.join$mcJ$sp$(this, a, b);
   }

   public long complement$mcJ$sp(final long a) {
      return Heyting.complement$mcJ$sp$(this, a);
   }

   public long nand$mcJ$sp(final long a, final long b) {
      return Heyting.nand$mcJ$sp$(this, a, b);
   }

   public long nor$mcJ$sp(final long a, final long b) {
      return Heyting.nor$mcJ$sp$(this, a, b);
   }

   public long nxor$mcJ$sp(final long a, final long b) {
      return Heyting.nxor$mcJ$sp$(this, a, b);
   }

   public CommutativeRig asCommutativeRig$mcD$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcD$sp$(this);
   }

   public CommutativeRig asCommutativeRig$mcF$sp() {
      return BoundedDistributiveLattice.asCommutativeRig$mcF$sp$(this);
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

   public long zero$mcJ$sp() {
      return BoundedJoinSemilattice.zero$mcJ$sp$(this);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcJ$sp$(this, a, ev);
   }

   public BoundedSemilattice joinSemilattice$mcD$sp() {
      return BoundedJoinSemilattice.joinSemilattice$mcD$sp$(this);
   }

   public BoundedSemilattice joinSemilattice$mcF$sp() {
      return BoundedJoinSemilattice.joinSemilattice$mcF$sp$(this);
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

   public long one$mcJ$sp() {
      return BoundedMeetSemilattice.one$mcJ$sp$(this);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return BoundedMeetSemilattice.isOne$mcD$sp$(this, a, ev);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return BoundedMeetSemilattice.isOne$mcF$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return BoundedMeetSemilattice.isOne$mcJ$sp$(this, a, ev);
   }

   public BoundedSemilattice meetSemilattice$mcD$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
   }

   public BoundedSemilattice meetSemilattice$mcF$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
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

   public PartialOrder meetPartialOrder$mcD$sp(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$mcD$sp$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcF$sp(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$mcF$sp$(this, ev);
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

   public PartialOrder joinPartialOrder$mcD$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcD$sp$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcF$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcF$sp$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcJ$sp$(this, ev);
   }

   public int one() {
      return this.one$mcI$sp();
   }

   public int zero() {
      return this.zero$mcI$sp();
   }

   public int and(final int a, final int b) {
      return this.and$mcI$sp(a, b);
   }

   public int or(final int a, final int b) {
      return this.or$mcI$sp(a, b);
   }

   public int complement(final int a) {
      return this.complement$mcI$sp(a);
   }

   public boolean signed() {
      return true;
   }

   public int width() {
      return 32;
   }

   public String toHexString(final int n) {
      return this.toHexString$mcI$sp(n);
   }

   public int bitCount(final int n) {
      return this.bitCount$mcI$sp(n);
   }

   public int highestOneBit(final int n) {
      return this.highestOneBit$mcI$sp(n);
   }

   public int lowestOneBit(final int n) {
      return this.lowestOneBit$mcI$sp(n);
   }

   public int numberOfLeadingZeros(final int n) {
      return this.numberOfLeadingZeros$mcI$sp(n);
   }

   public int numberOfTrailingZeros(final int n) {
      return this.numberOfTrailingZeros$mcI$sp(n);
   }

   public int leftShift(final int n, final int i) {
      return this.leftShift$mcI$sp(n, i);
   }

   public int rightShift(final int n, final int i) {
      return this.rightShift$mcI$sp(n, i);
   }

   public int signedRightShift(final int n, final int i) {
      return this.signedRightShift$mcI$sp(n, i);
   }

   public int rotateLeft(final int n, final int i) {
      return this.rotateLeft$mcI$sp(n, i);
   }

   public int rotateRight(final int n, final int i) {
      return this.rotateRight$mcI$sp(n, i);
   }

   public int one$mcI$sp() {
      return -1;
   }

   public int zero$mcI$sp() {
      return 0;
   }

   public int and$mcI$sp(final int a, final int b) {
      return a & b;
   }

   public int or$mcI$sp(final int a, final int b) {
      return a | b;
   }

   public int complement$mcI$sp(final int a) {
      return ~a;
   }

   public String toHexString$mcI$sp(final int n) {
      return Integer.toHexString(n);
   }

   public int bitCount$mcI$sp(final int n) {
      return Integer.bitCount(n);
   }

   public int highestOneBit$mcI$sp(final int n) {
      return Integer.highestOneBit(n);
   }

   public int lowestOneBit$mcI$sp(final int n) {
      return Integer.lowestOneBit(n);
   }

   public int numberOfLeadingZeros$mcI$sp(final int n) {
      return Integer.numberOfLeadingZeros(n);
   }

   public int numberOfTrailingZeros$mcI$sp(final int n) {
      return Integer.numberOfTrailingZeros(n);
   }

   public int leftShift$mcI$sp(final int n, final int i) {
      return n << i;
   }

   public int rightShift$mcI$sp(final int n, final int i) {
      return n >>> i;
   }

   public int signedRightShift$mcI$sp(final int n, final int i) {
      return n >> i;
   }

   public int rotateLeft$mcI$sp(final int n, final int i) {
      return Integer.rotateLeft(n, i);
   }

   public int rotateRight$mcI$sp(final int n, final int i) {
      return Integer.rotateRight(n, i);
   }

   public IntIsBitString() {
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
