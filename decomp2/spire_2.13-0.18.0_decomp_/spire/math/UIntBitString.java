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
   bytes = "\u0006\u0005\u0005ea!B\u000b\u0017\u0001YQ\u0002\"B\u001b\u0001\t\u00031\u0004\"\u0002\u001d\u0001\t\u0003I\u0004\"\u0002\u001e\u0001\t\u0003I\u0004\"B\u001e\u0001\t\u0003a\u0004\"B!\u0001\t\u0003\u0011\u0005\"B#\u0001\t\u00031\u0005\"\u0002%\u0001\t\u0003J\u0005\"\u0002'\u0001\t\u0003i\u0005\"B)\u0001\t\u0003\u0011\u0006\"\u0002,\u0001\t\u00039\u0006\"\u00022\u0001\t\u0003\u0019\u0007\"B3\u0001\t\u00031\u0007\"\u00025\u0001\t\u0003I\u0007\"B6\u0001\t\u0003a\u0007\"\u00028\u0001\t\u0003y\u0007\"B9\u0001\t\u0003\u0011\b\"\u0002<\u0001\t\u00039\b\"\u0002>\u0001\t\u0003Y\b\"\u0002@\u0001\t\u0003y\bbBA\u0003\u0001\u0011\u0005\u0011q\u0001\u0002\u000e+&sGOQ5u'R\u0014\u0018N\\4\u000b\u0005]A\u0012\u0001B7bi\"T\u0011!G\u0001\u0006gBL'/Z\n\u0005\u0001m\t\u0003\u0006\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VM\u001a\t\u0004E\r*S\"\u0001\f\n\u0005\u00112\"!\u0003\"jiN#(/\u001b8h!\t\u0011c%\u0003\u0002(-\t!Q+\u00138u!\tI#G\u0004\u0002+a9\u00111fL\u0007\u0002Y)\u0011QFL\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\ta$\u0003\u00022;\u00059\u0001/Y2lC\u001e,\u0017BA\u001a5\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\tT$\u0001\u0004=S:LGO\u0010\u000b\u0002oA\u0011!\u0005A\u0001\u0004_:,W#A\u0013\u0002\ti,'o\\\u0001\u0004C:$GcA\u0013>\u007f!)a\b\u0002a\u0001K\u0005\t\u0011\rC\u0003A\t\u0001\u0007Q%A\u0001c\u0003\ty'\u000fF\u0002&\u0007\u0012CQAP\u0003A\u0002\u0015BQ\u0001Q\u0003A\u0002\u0015\n!bY8na2,W.\u001a8u)\t)s\tC\u0003?\r\u0001\u0007Q%A\u0002y_J$2!\n&L\u0011\u0015qt\u00011\u0001&\u0011\u0015\u0001u\u00011\u0001&\u0003\u0019\u0019\u0018n\u001a8fIV\ta\n\u0005\u0002\u001d\u001f&\u0011\u0001+\b\u0002\b\u0005>|G.Z1o\u0003\u00159\u0018\u000e\u001a;i+\u0005\u0019\u0006C\u0001\u000fU\u0013\t)VDA\u0002J]R\f1\u0002^8IKb\u001cFO]5oOR\u0011\u0001\f\u0019\t\u00033vs!AW.\u0011\u0005-j\u0012B\u0001/\u001e\u0003\u0019\u0001&/\u001a3fM&\u0011al\u0018\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005qk\u0002\"B1\u000b\u0001\u0004)\u0013!\u00018\u0002\u0011\tLGoQ8v]R$\"a\u00153\t\u000b\u0005\\\u0001\u0019A\u0013\u0002\u001b!Lw\r[3ti>sWMQ5u)\t)s\rC\u0003b\u0019\u0001\u0007Q%\u0001\u0007m_^,7\u000f^(oK\nKG\u000f\u0006\u0002&U\")\u0011-\u0004a\u0001K\u0005!b.^7cKJ|e\rT3bI&twMW3s_N$\"aU7\t\u000b\u0005t\u0001\u0019A\u0013\u0002+9,XNY3s\u001f\u001a$&/Y5mS:<',\u001a:pgR\u00111\u000b\u001d\u0005\u0006C>\u0001\r!J\u0001\nY\u00164Go\u00155jMR$2!J:u\u0011\u0015\t\u0007\u00031\u0001&\u0011\u0015)\b\u00031\u0001T\u0003\u0005I\u0017A\u0003:jO\"$8\u000b[5giR\u0019Q\u0005_=\t\u000b\u0005\f\u0002\u0019A\u0013\t\u000bU\f\u0002\u0019A*\u0002!MLwM\\3e%&<\u0007\u000e^*iS\u001a$HcA\u0013}{\")\u0011M\u0005a\u0001K!)QO\u0005a\u0001'\u0006Q!o\u001c;bi\u0016dUM\u001a;\u0015\u000b\u0015\n\t!a\u0001\t\u000b\u0005\u001c\u0002\u0019A\u0013\t\u000bU\u001c\u0002\u0019A*\u0002\u0017I|G/\u0019;f%&<\u0007\u000e\u001e\u000b\u0006K\u0005%\u00111\u0002\u0005\u0006CR\u0001\r!\n\u0005\u0006kR\u0001\ra\u0015\u0015\b\u0001\u0005=\u0011QCA\f!\ra\u0012\u0011C\u0005\u0004\u0003'i\"\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0001\u0001"
)
public class UIntBitString implements BitString {
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

   public int one() {
      return UInt$.MODULE$.apply(-1);
   }

   public int zero() {
      return UInt$.MODULE$.apply(0);
   }

   public int and(final int a, final int b) {
      return UInt$.MODULE$.$amp$extension(a, b);
   }

   public int or(final int a, final int b) {
      return UInt$.MODULE$.$bar$extension(a, b);
   }

   public int complement(final int a) {
      return UInt$.MODULE$.unary_$tilde$extension(a);
   }

   public int xor(final int a, final int b) {
      return UInt$.MODULE$.$up$extension(a, b);
   }

   public boolean signed() {
      return false;
   }

   public int width() {
      return 32;
   }

   public String toHexString(final int n) {
      return Integer.toHexString(n);
   }

   public int bitCount(final int n) {
      return Integer.bitCount(n);
   }

   public int highestOneBit(final int n) {
      return UInt$.MODULE$.apply(Integer.highestOneBit(n));
   }

   public int lowestOneBit(final int n) {
      return UInt$.MODULE$.apply(Integer.lowestOneBit(n));
   }

   public int numberOfLeadingZeros(final int n) {
      return Integer.numberOfLeadingZeros(n);
   }

   public int numberOfTrailingZeros(final int n) {
      return Integer.numberOfTrailingZeros(n);
   }

   public int leftShift(final int n, final int i) {
      return UInt$.MODULE$.$less$less$extension(n, i);
   }

   public int rightShift(final int n, final int i) {
      return UInt$.MODULE$.$greater$greater$extension(n, i);
   }

   public int signedRightShift(final int n, final int i) {
      return UInt$.MODULE$.$greater$greater$greater$extension(n, i);
   }

   public int rotateLeft(final int n, final int i) {
      return UInt$.MODULE$.apply(Integer.rotateLeft(n, i));
   }

   public int rotateRight(final int n, final int i) {
      return UInt$.MODULE$.apply(Integer.rotateRight(n, i));
   }

   public UIntBitString() {
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
