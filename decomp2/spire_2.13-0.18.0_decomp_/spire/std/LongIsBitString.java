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
import algebra.lattice.Bool.mcJ.sp;
import algebra.ring.BoolRing;
import algebra.ring.CommutativeRig;
import cats.kernel.BoundedSemilattice;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;
import spire.math.BitString;
import spire.math.BitString$mcJ$sp;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001B\u000b\u0017\u0001mAQa\u000e\u0001\u0005\u0002aBQa\u000f\u0001\u0005\u0002qBQ!\u0010\u0001\u0005\u0002qBQA\u0010\u0001\u0005\u0002}BQ\u0001\u0012\u0001\u0005\u0002\u0015CQ\u0001\u0013\u0001\u0005\u0002%CQa\u0013\u0001\u0005B1CQa\u0014\u0001\u0005\u0002ACQ\u0001\u0016\u0001\u0005\u0002UCQ!\u0017\u0001\u0005\u0002iCQ!\u001a\u0001\u0005\u0002\u0019DQ\u0001\u001b\u0001\u0005\u0002%DQa\u001b\u0001\u0005\u00021DQA\u001c\u0001\u0005\u0002=DQ!\u001d\u0001\u0005\u0002IDQ\u0001\u001e\u0001\u0005\u0002UDQ!\u001f\u0001\u0005\u0002iDQ! \u0001\u0005\u0002yDq!a\u0001\u0001\t\u0003\t)\u0001C\u0004\u0002\f\u0001!\t!!\u0004\u0003\u001f1{gnZ%t\u0005&$8\u000b\u001e:j]\u001eT!a\u0006\r\u0002\u0007M$HMC\u0001\u001a\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019B\u0001\u0001\u000f#WA\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\t1\u0011I\\=SK\u001a\u00042a\t\u0014)\u001b\u0005!#BA\u0013\u0019\u0003\u0011i\u0017\r\u001e5\n\u0005\u001d\"#!\u0003\"jiN#(/\u001b8h!\ti\u0012&\u0003\u0002+=\t!Aj\u001c8h!\taCG\u0004\u0002.e9\u0011a&M\u0007\u0002_)\u0011\u0001GG\u0001\u0007yI|w\u000e\u001e \n\u0003}I!a\r\u0010\u0002\u000fA\f7m[1hK&\u0011QG\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003gy\ta\u0001P5oSRtD#A\u001d\u0011\u0005i\u0002Q\"\u0001\f\u0002\u0007=tW-F\u0001)\u0003\u0011QXM]8\u0002\u0007\u0005tG\rF\u0002)\u0001\nCQ!\u0011\u0003A\u0002!\n\u0011!\u0019\u0005\u0006\u0007\u0012\u0001\r\u0001K\u0001\u0002E\u0006\u0011qN\u001d\u000b\u0004Q\u0019;\u0005\"B!\u0006\u0001\u0004A\u0003\"B\"\u0006\u0001\u0004A\u0013AC2p[BdW-\\3oiR\u0011\u0001F\u0013\u0005\u0006\u0003\u001a\u0001\r\u0001K\u0001\u0004q>\u0014Hc\u0001\u0015N\u001d\")\u0011i\u0002a\u0001Q!)1i\u0002a\u0001Q\u000511/[4oK\u0012,\u0012!\u0015\t\u0003;IK!a\u0015\u0010\u0003\u000f\t{w\u000e\\3b]\u0006)q/\u001b3uQV\ta\u000b\u0005\u0002\u001e/&\u0011\u0001L\b\u0002\u0004\u0013:$\u0018a\u0003;p\u0011\u0016D8\u000b\u001e:j]\u001e$\"aW2\u0011\u0005q\u0003gBA/_!\tqc$\u0003\u0002`=\u00051\u0001K]3eK\u001aL!!\u00192\u0003\rM#(/\u001b8h\u0015\tyf\u0004C\u0003e\u0015\u0001\u0007\u0001&A\u0001o\u0003!\u0011\u0017\u000e^\"pk:$HC\u0001,h\u0011\u0015!7\u00021\u0001)\u00035A\u0017n\u001a5fgR|e.\u001a\"jiR\u0011\u0001F\u001b\u0005\u0006I2\u0001\r\u0001K\u0001\rY><Xm\u001d;P]\u0016\u0014\u0015\u000e\u001e\u000b\u0003Q5DQ\u0001Z\u0007A\u0002!\nAC\\;nE\u0016\u0014xJ\u001a'fC\u0012Lgn\u001a.fe>\u001cHC\u0001,q\u0011\u0015!g\u00021\u0001)\u0003UqW/\u001c2fe>3GK]1jY&twMW3s_N$\"AV:\t\u000b\u0011|\u0001\u0019\u0001\u0015\u0002\u00131,g\r^*iS\u001a$Hc\u0001\u0015wo\")A\r\u0005a\u0001Q!)\u0001\u0010\u0005a\u0001-\u0006\t\u0011.\u0001\u0006sS\u001eDGo\u00155jMR$2\u0001K>}\u0011\u0015!\u0017\u00031\u0001)\u0011\u0015A\u0018\u00031\u0001W\u0003A\u0019\u0018n\u001a8fIJKw\r\u001b;TQ&4G\u000f\u0006\u0003)\u007f\u0006\u0005\u0001\"\u00023\u0013\u0001\u0004A\u0003\"\u0002=\u0013\u0001\u00041\u0016A\u0003:pi\u0006$X\rT3giR)\u0001&a\u0002\u0002\n!)Am\u0005a\u0001Q!)\u0001p\u0005a\u0001-\u0006Y!o\u001c;bi\u0016\u0014\u0016n\u001a5u)\u0015A\u0013qBA\t\u0011\u0015!G\u00031\u0001)\u0011\u0015AH\u00031\u0001WQ\u001d\u0001\u0011QCA\u000e\u0003;\u00012!HA\f\u0013\r\tIB\b\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001"
)
public class LongIsBitString implements BitString$mcJ$sp {
   private static final long serialVersionUID = 0L;

   public long imp(final long a, final long b) {
      return sp.imp$(this, a, b);
   }

   public long imp$mcJ$sp(final long a, final long b) {
      return sp.imp$mcJ$sp$(this, a, b);
   }

   public long without(final long a, final long b) {
      return sp.without$(this, a, b);
   }

   public long without$mcJ$sp(final long a, final long b) {
      return sp.without$mcJ$sp$(this, a, b);
   }

   public Bool dual() {
      return sp.dual$(this);
   }

   public Bool dual$mcJ$sp() {
      return sp.dual$mcJ$sp$(this);
   }

   public long meet(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.meet$(this, a, b);
   }

   public long meet$mcJ$sp(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.meet$mcJ$sp$(this, a, b);
   }

   public long join(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.join$(this, a, b);
   }

   public long join$mcJ$sp(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.join$mcJ$sp$(this, a, b);
   }

   public long nand(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.nand$(this, a, b);
   }

   public long nand$mcJ$sp(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.nand$mcJ$sp$(this, a, b);
   }

   public long nor(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.nor$(this, a, b);
   }

   public long nor$mcJ$sp(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.nor$mcJ$sp$(this, a, b);
   }

   public long nxor(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.nxor$(this, a, b);
   }

   public long nxor$mcJ$sp(final long a, final long b) {
      return algebra.lattice.Heyting.mcJ.sp.nxor$mcJ$sp$(this, a, b);
   }

   public CommutativeRig asCommutativeRig() {
      return algebra.lattice.BoundedDistributiveLattice.mcJ.sp.asCommutativeRig$(this);
   }

   public CommutativeRig asCommutativeRig$mcJ$sp() {
      return algebra.lattice.BoundedDistributiveLattice.mcJ.sp.asCommutativeRig$mcJ$sp$(this);
   }

   public boolean isOne(final long a, final Eq ev) {
      return algebra.lattice.BoundedMeetSemilattice.mcJ.sp.isOne$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return algebra.lattice.BoundedMeetSemilattice.mcJ.sp.isOne$mcJ$sp$(this, a, ev);
   }

   public BoundedSemilattice meetSemilattice() {
      return algebra.lattice.BoundedMeetSemilattice.mcJ.sp.meetSemilattice$(this);
   }

   public BoundedSemilattice meetSemilattice$mcJ$sp() {
      return algebra.lattice.BoundedMeetSemilattice.mcJ.sp.meetSemilattice$mcJ$sp$(this);
   }

   public PartialOrder meetPartialOrder(final Eq ev) {
      return algebra.lattice.MeetSemilattice.mcJ.sp.meetPartialOrder$(this, ev);
   }

   public PartialOrder meetPartialOrder$mcJ$sp(final Eq ev) {
      return algebra.lattice.MeetSemilattice.mcJ.sp.meetPartialOrder$mcJ$sp$(this, ev);
   }

   public boolean isZero(final long a, final Eq ev) {
      return algebra.lattice.BoundedJoinSemilattice.mcJ.sp.isZero$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return algebra.lattice.BoundedJoinSemilattice.mcJ.sp.isZero$mcJ$sp$(this, a, ev);
   }

   public BoundedSemilattice joinSemilattice() {
      return algebra.lattice.BoundedJoinSemilattice.mcJ.sp.joinSemilattice$(this);
   }

   public BoundedSemilattice joinSemilattice$mcJ$sp() {
      return algebra.lattice.BoundedJoinSemilattice.mcJ.sp.joinSemilattice$mcJ$sp$(this);
   }

   public PartialOrder joinPartialOrder(final Eq ev) {
      return algebra.lattice.JoinSemilattice.mcJ.sp.joinPartialOrder$(this, ev);
   }

   public PartialOrder joinPartialOrder$mcJ$sp(final Eq ev) {
      return algebra.lattice.JoinSemilattice.mcJ.sp.joinPartialOrder$mcJ$sp$(this, ev);
   }

   public String toHexString$mcB$sp(final byte n) {
      return BitString.toHexString$mcB$sp$(this, n);
   }

   public String toHexString$mcI$sp(final int n) {
      return BitString.toHexString$mcI$sp$(this, n);
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

   public int bitCount$mcS$sp(final short n) {
      return BitString.bitCount$mcS$sp$(this, n);
   }

   public byte highestOneBit$mcB$sp(final byte n) {
      return BitString.highestOneBit$mcB$sp$(this, n);
   }

   public int highestOneBit$mcI$sp(final int n) {
      return BitString.highestOneBit$mcI$sp$(this, n);
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

   public short lowestOneBit$mcS$sp(final short n) {
      return BitString.lowestOneBit$mcS$sp$(this, n);
   }

   public int numberOfLeadingZeros$mcB$sp(final byte n) {
      return BitString.numberOfLeadingZeros$mcB$sp$(this, n);
   }

   public int numberOfLeadingZeros$mcI$sp(final int n) {
      return BitString.numberOfLeadingZeros$mcI$sp$(this, n);
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

   public int numberOfTrailingZeros$mcS$sp(final short n) {
      return BitString.numberOfTrailingZeros$mcS$sp$(this, n);
   }

   public byte leftShift$mcB$sp(final byte n, final int i) {
      return BitString.leftShift$mcB$sp$(this, n, i);
   }

   public int leftShift$mcI$sp(final int n, final int i) {
      return BitString.leftShift$mcI$sp$(this, n, i);
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

   public short rightShift$mcS$sp(final short n, final int i) {
      return BitString.rightShift$mcS$sp$(this, n, i);
   }

   public byte signedRightShift$mcB$sp(final byte n, final int i) {
      return BitString.signedRightShift$mcB$sp$(this, n, i);
   }

   public int signedRightShift$mcI$sp(final int n, final int i) {
      return BitString.signedRightShift$mcI$sp$(this, n, i);
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

   public short rotateLeft$mcS$sp(final short n, final int i) {
      return BitString.rotateLeft$mcS$sp$(this, n, i);
   }

   public byte rotateRight$mcB$sp(final byte n, final int i) {
      return BitString.rotateRight$mcB$sp$(this, n, i);
   }

   public int rotateRight$mcI$sp(final int n, final int i) {
      return BitString.rotateRight$mcI$sp$(this, n, i);
   }

   public short rotateRight$mcS$sp(final short n, final int i) {
      return BitString.rotateRight$mcS$sp$(this, n, i);
   }

   public int imp$mcI$sp(final int a, final int b) {
      return Bool.imp$mcI$sp$(this, a, b);
   }

   public int without$mcI$sp(final int a, final int b) {
      return Bool.without$mcI$sp$(this, a, b);
   }

   public int xor$mcI$sp(final int a, final int b) {
      return Bool.xor$mcI$sp$(this, a, b);
   }

   public Bool dual$mcI$sp() {
      return Bool.dual$mcI$sp$(this);
   }

   public BoolRing asBoolRing() {
      return Bool.asBoolRing$(this);
   }

   public int and$mcI$sp(final int a, final int b) {
      return GenBool.and$mcI$sp$(this, a, b);
   }

   public int meet$mcI$sp(final int a, final int b) {
      return GenBool.meet$mcI$sp$(this, a, b);
   }

   public int or$mcI$sp(final int a, final int b) {
      return GenBool.or$mcI$sp$(this, a, b);
   }

   public int join$mcI$sp(final int a, final int b) {
      return GenBool.join$mcI$sp$(this, a, b);
   }

   public int complement$mcI$sp(final int a) {
      return Heyting.complement$mcI$sp$(this, a);
   }

   public int nand$mcI$sp(final int a, final int b) {
      return Heyting.nand$mcI$sp$(this, a, b);
   }

   public int nor$mcI$sp(final int a, final int b) {
      return Heyting.nor$mcI$sp$(this, a, b);
   }

   public int nxor$mcI$sp(final int a, final int b) {
      return Heyting.nxor$mcI$sp$(this, a, b);
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

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return BoundedJoinSemilattice.isZero$mcI$sp$(this, a, ev);
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

   public double one$mcD$sp() {
      return BoundedMeetSemilattice.one$mcD$sp$(this);
   }

   public float one$mcF$sp() {
      return BoundedMeetSemilattice.one$mcF$sp$(this);
   }

   public int one$mcI$sp() {
      return BoundedMeetSemilattice.one$mcI$sp$(this);
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

   public BoundedSemilattice meetSemilattice$mcD$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcD$sp$(this);
   }

   public BoundedSemilattice meetSemilattice$mcF$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcF$sp$(this);
   }

   public BoundedSemilattice meetSemilattice$mcI$sp() {
      return BoundedMeetSemilattice.meetSemilattice$mcI$sp$(this);
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

   public PartialOrder meetPartialOrder$mcI$sp(final Eq ev) {
      return MeetSemilattice.meetPartialOrder$mcI$sp$(this, ev);
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

   public PartialOrder joinPartialOrder$mcI$sp(final Eq ev) {
      return JoinSemilattice.joinPartialOrder$mcI$sp$(this, ev);
   }

   public long one() {
      return this.one$mcJ$sp();
   }

   public long zero() {
      return this.zero$mcJ$sp();
   }

   public long and(final long a, final long b) {
      return this.and$mcJ$sp(a, b);
   }

   public long or(final long a, final long b) {
      return this.or$mcJ$sp(a, b);
   }

   public long complement(final long a) {
      return this.complement$mcJ$sp(a);
   }

   public long xor(final long a, final long b) {
      return this.xor$mcJ$sp(a, b);
   }

   public boolean signed() {
      return true;
   }

   public int width() {
      return 64;
   }

   public String toHexString(final long n) {
      return this.toHexString$mcJ$sp(n);
   }

   public int bitCount(final long n) {
      return this.bitCount$mcJ$sp(n);
   }

   public long highestOneBit(final long n) {
      return this.highestOneBit$mcJ$sp(n);
   }

   public long lowestOneBit(final long n) {
      return this.lowestOneBit$mcJ$sp(n);
   }

   public int numberOfLeadingZeros(final long n) {
      return this.numberOfLeadingZeros$mcJ$sp(n);
   }

   public int numberOfTrailingZeros(final long n) {
      return this.numberOfTrailingZeros$mcJ$sp(n);
   }

   public long leftShift(final long n, final int i) {
      return this.leftShift$mcJ$sp(n, i);
   }

   public long rightShift(final long n, final int i) {
      return this.rightShift$mcJ$sp(n, i);
   }

   public long signedRightShift(final long n, final int i) {
      return this.signedRightShift$mcJ$sp(n, i);
   }

   public long rotateLeft(final long n, final int i) {
      return this.rotateLeft$mcJ$sp(n, i);
   }

   public long rotateRight(final long n, final int i) {
      return this.rotateRight$mcJ$sp(n, i);
   }

   public long one$mcJ$sp() {
      return -1L;
   }

   public long zero$mcJ$sp() {
      return 0L;
   }

   public long and$mcJ$sp(final long a, final long b) {
      return a & b;
   }

   public long or$mcJ$sp(final long a, final long b) {
      return a | b;
   }

   public long complement$mcJ$sp(final long a) {
      return ~a;
   }

   public long xor$mcJ$sp(final long a, final long b) {
      return a ^ b;
   }

   public String toHexString$mcJ$sp(final long n) {
      return Long.toHexString(n);
   }

   public int bitCount$mcJ$sp(final long n) {
      return Long.bitCount(n);
   }

   public long highestOneBit$mcJ$sp(final long n) {
      return Long.highestOneBit(n);
   }

   public long lowestOneBit$mcJ$sp(final long n) {
      return Long.lowestOneBit(n);
   }

   public int numberOfLeadingZeros$mcJ$sp(final long n) {
      return Long.numberOfLeadingZeros(n);
   }

   public int numberOfTrailingZeros$mcJ$sp(final long n) {
      return Long.numberOfTrailingZeros(n);
   }

   public long leftShift$mcJ$sp(final long n, final int i) {
      return n << i;
   }

   public long rightShift$mcJ$sp(final long n, final int i) {
      return n >> i;
   }

   public long signedRightShift$mcJ$sp(final long n, final int i) {
      return n >>> i;
   }

   public long rotateLeft$mcJ$sp(final long n, final int i) {
      return Long.rotateLeft(n, i);
   }

   public long rotateRight$mcJ$sp(final long n, final int i) {
      return Long.rotateRight(n, i);
   }

   public LongIsBitString() {
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
