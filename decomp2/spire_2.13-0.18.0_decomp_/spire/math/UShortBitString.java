package spire.math;

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

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!B\u000b\u0017\u0001YQ\u0002\"B\u001b\u0001\t\u00031\u0004\"\u0002\u001d\u0001\t\u0003I\u0004\"\u0002\u001e\u0001\t\u0003I\u0004\"B\u001e\u0001\t\u0003a\u0004\"B!\u0001\t\u0003\u0011\u0005\"B#\u0001\t\u00031\u0005\"\u0002%\u0001\t\u0003J\u0005\"\u0002'\u0001\t\u0003i\u0005\"B)\u0001\t\u0003\u0011\u0006\"\u0002,\u0001\t\u00039\u0006\"\u00022\u0001\t\u0003\u0019\u0007\"B3\u0001\t\u00031\u0007\"\u00025\u0001\t\u0003I\u0007\"B6\u0001\t\u0003a\u0007\"\u00028\u0001\t\u0003y\u0007\"B9\u0001\t\u0003\u0011\b\"\u0002<\u0001\t\u00039\b\"\u0002>\u0001\t\u0003Y\b\"\u0002@\u0001\t\u0003y\bbBA\u0003\u0001\u0011\u0005\u0011q\u0001\u0002\u0010+NCwN\u001d;CSR\u001cFO]5oO*\u0011q\u0003G\u0001\u0005[\u0006$\bNC\u0001\u001a\u0003\u0015\u0019\b/\u001b:f'\u0011\u00011$\t\u0015\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\r\u0005s\u0017PU3g!\r\u00113%J\u0007\u0002-%\u0011AE\u0006\u0002\n\u0005&$8\u000b\u001e:j]\u001e\u0004\"A\t\u0014\n\u0005\u001d2\"AB+TQ>\u0014H\u000f\u0005\u0002*e9\u0011!\u0006\r\b\u0003W=j\u0011\u0001\f\u0006\u0003[9\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002=%\u0011\u0011'H\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019DG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00022;\u00051A(\u001b8jiz\"\u0012a\u000e\t\u0003E\u0001\t1a\u001c8f+\u0005)\u0013\u0001\u0002>fe>\f1!\u00198e)\r)Sh\u0010\u0005\u0006}\u0011\u0001\r!J\u0001\u0002C\")\u0001\t\u0002a\u0001K\u0005\t!-\u0001\u0002peR\u0019Qe\u0011#\t\u000by*\u0001\u0019A\u0013\t\u000b\u0001+\u0001\u0019A\u0013\u0002\u0015\r|W\u000e\u001d7f[\u0016tG\u000f\u0006\u0002&\u000f\")aH\u0002a\u0001K\u0005\u0019\u0001p\u001c:\u0015\u0007\u0015R5\nC\u0003?\u000f\u0001\u0007Q\u0005C\u0003A\u000f\u0001\u0007Q%\u0001\u0004tS\u001etW\rZ\u000b\u0002\u001dB\u0011AdT\u0005\u0003!v\u0011qAQ8pY\u0016\fg.A\u0003xS\u0012$\b.F\u0001T!\taB+\u0003\u0002V;\t\u0019\u0011J\u001c;\u0002\u0017Q|\u0007*\u001a=TiJLgn\u001a\u000b\u00031\u0002\u0004\"!W/\u000f\u0005i[\u0006CA\u0016\u001e\u0013\taV$\u0001\u0004Qe\u0016$WMZ\u0005\u0003=~\u0013aa\u0015;sS:<'B\u0001/\u001e\u0011\u0015\t'\u00021\u0001&\u0003\u0005q\u0017\u0001\u00032ji\u000e{WO\u001c;\u0015\u0005M#\u0007\"B1\f\u0001\u0004)\u0013!\u00045jO\",7\u000f^(oK\nKG\u000f\u0006\u0002&O\")\u0011\r\u0004a\u0001K\u0005aAn\\<fgR|e.\u001a\"jiR\u0011QE\u001b\u0005\u0006C6\u0001\r!J\u0001\u0015]Vl'-\u001a:PM2+\u0017\rZ5oOj+'o\\:\u0015\u0005Mk\u0007\"B1\u000f\u0001\u0004)\u0013!\u00068v[\n,'o\u00144Ue\u0006LG.\u001b8h5\u0016\u0014xn\u001d\u000b\u0003'BDQ!Y\bA\u0002\u0015\n\u0011\u0002\\3giNC\u0017N\u001a;\u0015\u0007\u0015\u001aH\u000fC\u0003b!\u0001\u0007Q\u0005C\u0003v!\u0001\u00071+A\u0001j\u0003)\u0011\u0018n\u001a5u'\"Lg\r\u001e\u000b\u0004KaL\b\"B1\u0012\u0001\u0004)\u0003\"B;\u0012\u0001\u0004\u0019\u0016\u0001E:jO:,GMU5hQR\u001c\u0006.\u001b4u)\r)C0 \u0005\u0006CJ\u0001\r!\n\u0005\u0006kJ\u0001\raU\u0001\u000be>$\u0018\r^3MK\u001a$H#B\u0013\u0002\u0002\u0005\r\u0001\"B1\u0014\u0001\u0004)\u0003\"B;\u0014\u0001\u0004\u0019\u0016a\u0003:pi\u0006$XMU5hQR$R!JA\u0005\u0003\u0017AQ!\u0019\u000bA\u0002\u0015BQ!\u001e\u000bA\u0002MCs\u0001AA\b\u0003+\t9\u0002E\u0002\u001d\u0003#I1!a\u0005\u001e\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0001\u0001"
)
public class UShortBitString implements BitString {
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

   public String toHexString$mcS$sp(final short n) {
      return BitString.toHexString$mcS$sp$(this, n);
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

   public int bitCount$mcS$sp(final short n) {
      return BitString.bitCount$mcS$sp$(this, n);
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

   public short highestOneBit$mcS$sp(final short n) {
      return BitString.highestOneBit$mcS$sp$(this, n);
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

   public short lowestOneBit$mcS$sp(final short n) {
      return BitString.lowestOneBit$mcS$sp$(this, n);
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

   public int numberOfLeadingZeros$mcS$sp(final short n) {
      return BitString.numberOfLeadingZeros$mcS$sp$(this, n);
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

   public int numberOfTrailingZeros$mcS$sp(final short n) {
      return BitString.numberOfTrailingZeros$mcS$sp$(this, n);
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

   public short leftShift$mcS$sp(final short n, final int i) {
      return BitString.leftShift$mcS$sp$(this, n, i);
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

   public short rightShift$mcS$sp(final short n, final int i) {
      return BitString.rightShift$mcS$sp$(this, n, i);
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

   public short signedRightShift$mcS$sp(final short n, final int i) {
      return BitString.signedRightShift$mcS$sp$(this, n, i);
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

   public short rotateLeft$mcS$sp(final short n, final int i) {
      return BitString.rotateLeft$mcS$sp$(this, n, i);
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

   public short rotateRight$mcS$sp(final short n, final int i) {
      return BitString.rotateRight$mcS$sp$(this, n, i);
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

   public char one() {
      return UShort$.MODULE$.apply((short)-1);
   }

   public char zero() {
      return UShort$.MODULE$.apply((short)0);
   }

   public char and(final char a, final char b) {
      return UShort$.MODULE$.$amp$extension(a, b);
   }

   public char or(final char a, final char b) {
      return UShort$.MODULE$.$bar$extension(a, b);
   }

   public char complement(final char a) {
      return UShort$.MODULE$.unary_$tilde$extension(a);
   }

   public char xor(final char a, final char b) {
      return UShort$.MODULE$.$up$extension(a, b);
   }

   public boolean signed() {
      return false;
   }

   public int width() {
      return 16;
   }

   public String toHexString(final char n) {
      return Integer.toHexString(UShort$.MODULE$.toInt$extension(n));
   }

   public int bitCount(final char n) {
      return Integer.bitCount(UShort$.MODULE$.toInt$extension(n));
   }

   public char highestOneBit(final char n) {
      return UShort$.MODULE$.apply(Integer.highestOneBit(UShort$.MODULE$.toInt$extension(n)));
   }

   public char lowestOneBit(final char n) {
      return UShort$.MODULE$.apply(Integer.lowestOneBit(UShort$.MODULE$.toInt$extension(n)));
   }

   public int numberOfLeadingZeros(final char n) {
      return Integer.numberOfLeadingZeros(UShort$.MODULE$.toInt$extension(n));
   }

   public int numberOfTrailingZeros(final char n) {
      return Integer.numberOfTrailingZeros(UShort$.MODULE$.toInt$extension(n));
   }

   public char leftShift(final char n, final int i) {
      return UShort$.MODULE$.$less$less$extension(n, i);
   }

   public char rightShift(final char n, final int i) {
      return UShort$.MODULE$.$greater$greater$extension(n, i);
   }

   public char signedRightShift(final char n, final int i) {
      return UShort$.MODULE$.$greater$greater$greater$extension(n, i);
   }

   public char rotateLeft(final char n, final int i) {
      int j = i & 15;
      return UShort$.MODULE$.$bar$extension(UShort$.MODULE$.$less$less$extension(n, j), UShort$.MODULE$.$greater$greater$greater$extension(n, 16 - j));
   }

   public char rotateRight(final char n, final int i) {
      int j = i & 15;
      return UShort$.MODULE$.$bar$extension(UShort$.MODULE$.$greater$greater$greater$extension(n, j), UShort$.MODULE$.$less$less$extension(n, 16 - j));
   }

   public UShortBitString() {
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
