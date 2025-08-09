package algebra.instances;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import cats.kernel.Order;
import cats.kernel.instances.bigInt.package.;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2A!\u0002\u0004\u0001\u0017!)\u0001\u0006\u0001C\u0001S!)1\u0006\u0001C!Y!)\u0011\u0007\u0001C!e!)Q\u0007\u0001C!m\t1\")[4J]R$&/\u001e8dCR,G\rR5wSN|gN\u0003\u0002\b\u0011\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0002\u0013\u00059\u0011\r\\4fEJ\f7\u0001A\n\u0004\u00011\u0001\u0002CA\u0007\u000f\u001b\u00051\u0011BA\b\u0007\u00055\u0011\u0015nZ%oi\u0006cw-\u001a2sCB\u0019\u0011c\u0006\u000e\u000f\u0005I)R\"A\n\u000b\u0005QA\u0011\u0001\u0002:j]\u001eL!AF\n\u0002#Q\u0013XO\\2bi\u0016$G)\u001b<jg&|g.\u0003\u0002\u00193\t\u0011bm\u001c:D_6lW\u000f^1uSZ,'+\u001b8h\u0015\t12\u0003\u0005\u0002\u001cK9\u0011AD\t\b\u0003;\u0001j\u0011A\b\u0006\u0003?)\ta\u0001\u0010:p_Rt\u0014\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\"\u0013a\u00029bG.\fw-\u001a\u0006\u0002C%\u0011ae\n\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005\r\"\u0013A\u0002\u001fj]&$h\bF\u0001+!\ti\u0001!A\u0003ucV|G\u000fF\u0002\u001b[=BQA\f\u0002A\u0002i\t\u0011\u0001\u001f\u0005\u0006a\t\u0001\rAG\u0001\u0002s\u0006!A/\\8e)\rQ2\u0007\u000e\u0005\u0006]\r\u0001\rA\u0007\u0005\u0006a\r\u0001\rAG\u0001\u0006_J$WM]\u000b\u0002oA\u0019\u0001h\u000f\u000e\u000f\u0005eRT\"\u0001\u0005\n\u0005\rB\u0011B\u0001\u001f>\u0005\u0015y%\u000fZ3s\u0015\t\u0019\u0003\u0002"
)
public class BigIntTruncatedDivison extends BigIntAlgebra implements TruncatedDivision.forCommutativeRing {
   public Object fmod(final Object x, final Object y) {
      return TruncatedDivision.forCommutativeRing.fmod$(this, x, y);
   }

   public byte fmod$mcB$sp(final byte x, final byte y) {
      return TruncatedDivision.forCommutativeRing.fmod$mcB$sp$(this, x, y);
   }

   public double fmod$mcD$sp(final double x, final double y) {
      return TruncatedDivision.forCommutativeRing.fmod$mcD$sp$(this, x, y);
   }

   public float fmod$mcF$sp(final float x, final float y) {
      return TruncatedDivision.forCommutativeRing.fmod$mcF$sp$(this, x, y);
   }

   public int fmod$mcI$sp(final int x, final int y) {
      return TruncatedDivision.forCommutativeRing.fmod$mcI$sp$(this, x, y);
   }

   public long fmod$mcJ$sp(final long x, final long y) {
      return TruncatedDivision.forCommutativeRing.fmod$mcJ$sp$(this, x, y);
   }

   public short fmod$mcS$sp(final short x, final short y) {
      return TruncatedDivision.forCommutativeRing.fmod$mcS$sp$(this, x, y);
   }

   public Object fquot(final Object x, final Object y) {
      return TruncatedDivision.forCommutativeRing.fquot$(this, x, y);
   }

   public byte fquot$mcB$sp(final byte x, final byte y) {
      return TruncatedDivision.forCommutativeRing.fquot$mcB$sp$(this, x, y);
   }

   public double fquot$mcD$sp(final double x, final double y) {
      return TruncatedDivision.forCommutativeRing.fquot$mcD$sp$(this, x, y);
   }

   public float fquot$mcF$sp(final float x, final float y) {
      return TruncatedDivision.forCommutativeRing.fquot$mcF$sp$(this, x, y);
   }

   public int fquot$mcI$sp(final int x, final int y) {
      return TruncatedDivision.forCommutativeRing.fquot$mcI$sp$(this, x, y);
   }

   public long fquot$mcJ$sp(final long x, final long y) {
      return TruncatedDivision.forCommutativeRing.fquot$mcJ$sp$(this, x, y);
   }

   public short fquot$mcS$sp(final short x, final short y) {
      return TruncatedDivision.forCommutativeRing.fquot$mcS$sp$(this, x, y);
   }

   public Tuple2 fquotmod(final Object x, final Object y) {
      return TruncatedDivision.forCommutativeRing.fquotmod$(this, x, y);
   }

   public Tuple2 fquotmod$mcB$sp(final byte x, final byte y) {
      return TruncatedDivision.forCommutativeRing.fquotmod$mcB$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcD$sp(final double x, final double y) {
      return TruncatedDivision.forCommutativeRing.fquotmod$mcD$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcF$sp(final float x, final float y) {
      return TruncatedDivision.forCommutativeRing.fquotmod$mcF$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcI$sp(final int x, final int y) {
      return TruncatedDivision.forCommutativeRing.fquotmod$mcI$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcJ$sp(final long x, final long y) {
      return TruncatedDivision.forCommutativeRing.fquotmod$mcJ$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcS$sp(final short x, final short y) {
      return TruncatedDivision.forCommutativeRing.fquotmod$mcS$sp$(this, x, y);
   }

   public Object abs(final Object a) {
      return Signed.forAdditiveCommutativeGroup.abs$(this, a);
   }

   public final Signed.forAdditiveCommutativeMonoid additiveCommutativeMonoid() {
      return Signed.forAdditiveCommutativeMonoid.additiveCommutativeMonoid$(this);
   }

   public int signum(final Object a) {
      return Signed.forAdditiveCommutativeMonoid.signum$(this, a);
   }

   public byte tquot$mcB$sp(final byte x, final byte y) {
      return TruncatedDivision.tquot$mcB$sp$(this, x, y);
   }

   public double tquot$mcD$sp(final double x, final double y) {
      return TruncatedDivision.tquot$mcD$sp$(this, x, y);
   }

   public float tquot$mcF$sp(final float x, final float y) {
      return TruncatedDivision.tquot$mcF$sp$(this, x, y);
   }

   public int tquot$mcI$sp(final int x, final int y) {
      return TruncatedDivision.tquot$mcI$sp$(this, x, y);
   }

   public long tquot$mcJ$sp(final long x, final long y) {
      return TruncatedDivision.tquot$mcJ$sp$(this, x, y);
   }

   public short tquot$mcS$sp(final short x, final short y) {
      return TruncatedDivision.tquot$mcS$sp$(this, x, y);
   }

   public byte tmod$mcB$sp(final byte x, final byte y) {
      return TruncatedDivision.tmod$mcB$sp$(this, x, y);
   }

   public double tmod$mcD$sp(final double x, final double y) {
      return TruncatedDivision.tmod$mcD$sp$(this, x, y);
   }

   public float tmod$mcF$sp(final float x, final float y) {
      return TruncatedDivision.tmod$mcF$sp$(this, x, y);
   }

   public int tmod$mcI$sp(final int x, final int y) {
      return TruncatedDivision.tmod$mcI$sp$(this, x, y);
   }

   public long tmod$mcJ$sp(final long x, final long y) {
      return TruncatedDivision.tmod$mcJ$sp$(this, x, y);
   }

   public short tmod$mcS$sp(final short x, final short y) {
      return TruncatedDivision.tmod$mcS$sp$(this, x, y);
   }

   public Tuple2 tquotmod(final Object x, final Object y) {
      return TruncatedDivision.tquotmod$(this, x, y);
   }

   public Tuple2 tquotmod$mcB$sp(final byte x, final byte y) {
      return TruncatedDivision.tquotmod$mcB$sp$(this, x, y);
   }

   public Tuple2 tquotmod$mcD$sp(final double x, final double y) {
      return TruncatedDivision.tquotmod$mcD$sp$(this, x, y);
   }

   public Tuple2 tquotmod$mcF$sp(final float x, final float y) {
      return TruncatedDivision.tquotmod$mcF$sp$(this, x, y);
   }

   public Tuple2 tquotmod$mcI$sp(final int x, final int y) {
      return TruncatedDivision.tquotmod$mcI$sp$(this, x, y);
   }

   public Tuple2 tquotmod$mcJ$sp(final long x, final long y) {
      return TruncatedDivision.tquotmod$mcJ$sp$(this, x, y);
   }

   public Tuple2 tquotmod$mcS$sp(final short x, final short y) {
      return TruncatedDivision.tquotmod$mcS$sp$(this, x, y);
   }

   public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcB$sp() {
      return Signed.additiveCommutativeMonoid$mcB$sp$(this);
   }

   public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcD$sp() {
      return Signed.additiveCommutativeMonoid$mcD$sp$(this);
   }

   public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcF$sp() {
      return Signed.additiveCommutativeMonoid$mcF$sp$(this);
   }

   public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcI$sp() {
      return Signed.additiveCommutativeMonoid$mcI$sp$(this);
   }

   public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcJ$sp() {
      return Signed.additiveCommutativeMonoid$mcJ$sp$(this);
   }

   public AdditiveCommutativeMonoid additiveCommutativeMonoid$mcS$sp() {
      return Signed.additiveCommutativeMonoid$mcS$sp$(this);
   }

   public Order order$mcB$sp() {
      return Signed.order$mcB$sp$(this);
   }

   public Order order$mcD$sp() {
      return Signed.order$mcD$sp$(this);
   }

   public Order order$mcF$sp() {
      return Signed.order$mcF$sp$(this);
   }

   public Order order$mcI$sp() {
      return Signed.order$mcI$sp$(this);
   }

   public Order order$mcJ$sp() {
      return Signed.order$mcJ$sp$(this);
   }

   public Order order$mcS$sp() {
      return Signed.order$mcS$sp$(this);
   }

   public Signed.Sign sign(final Object a) {
      return Signed.sign$(this, a);
   }

   public Signed.Sign sign$mcB$sp(final byte a) {
      return Signed.sign$mcB$sp$(this, a);
   }

   public Signed.Sign sign$mcD$sp(final double a) {
      return Signed.sign$mcD$sp$(this, a);
   }

   public Signed.Sign sign$mcF$sp(final float a) {
      return Signed.sign$mcF$sp$(this, a);
   }

   public Signed.Sign sign$mcI$sp(final int a) {
      return Signed.sign$mcI$sp$(this, a);
   }

   public Signed.Sign sign$mcJ$sp(final long a) {
      return Signed.sign$mcJ$sp$(this, a);
   }

   public Signed.Sign sign$mcS$sp(final short a) {
      return Signed.sign$mcS$sp$(this, a);
   }

   public int signum$mcB$sp(final byte a) {
      return Signed.signum$mcB$sp$(this, a);
   }

   public int signum$mcD$sp(final double a) {
      return Signed.signum$mcD$sp$(this, a);
   }

   public int signum$mcF$sp(final float a) {
      return Signed.signum$mcF$sp$(this, a);
   }

   public int signum$mcI$sp(final int a) {
      return Signed.signum$mcI$sp$(this, a);
   }

   public int signum$mcJ$sp(final long a) {
      return Signed.signum$mcJ$sp$(this, a);
   }

   public int signum$mcS$sp(final short a) {
      return Signed.signum$mcS$sp$(this, a);
   }

   public byte abs$mcB$sp(final byte a) {
      return Signed.abs$mcB$sp$(this, a);
   }

   public double abs$mcD$sp(final double a) {
      return Signed.abs$mcD$sp$(this, a);
   }

   public float abs$mcF$sp(final float a) {
      return Signed.abs$mcF$sp$(this, a);
   }

   public int abs$mcI$sp(final int a) {
      return Signed.abs$mcI$sp$(this, a);
   }

   public long abs$mcJ$sp(final long a) {
      return Signed.abs$mcJ$sp$(this, a);
   }

   public short abs$mcS$sp(final short a) {
      return Signed.abs$mcS$sp$(this, a);
   }

   public boolean isSignZero(final Object a) {
      return Signed.isSignZero$(this, a);
   }

   public boolean isSignZero$mcB$sp(final byte a) {
      return Signed.isSignZero$mcB$sp$(this, a);
   }

   public boolean isSignZero$mcD$sp(final double a) {
      return Signed.isSignZero$mcD$sp$(this, a);
   }

   public boolean isSignZero$mcF$sp(final float a) {
      return Signed.isSignZero$mcF$sp$(this, a);
   }

   public boolean isSignZero$mcI$sp(final int a) {
      return Signed.isSignZero$mcI$sp$(this, a);
   }

   public boolean isSignZero$mcJ$sp(final long a) {
      return Signed.isSignZero$mcJ$sp$(this, a);
   }

   public boolean isSignZero$mcS$sp(final short a) {
      return Signed.isSignZero$mcS$sp$(this, a);
   }

   public boolean isSignPositive(final Object a) {
      return Signed.isSignPositive$(this, a);
   }

   public boolean isSignPositive$mcB$sp(final byte a) {
      return Signed.isSignPositive$mcB$sp$(this, a);
   }

   public boolean isSignPositive$mcD$sp(final double a) {
      return Signed.isSignPositive$mcD$sp$(this, a);
   }

   public boolean isSignPositive$mcF$sp(final float a) {
      return Signed.isSignPositive$mcF$sp$(this, a);
   }

   public boolean isSignPositive$mcI$sp(final int a) {
      return Signed.isSignPositive$mcI$sp$(this, a);
   }

   public boolean isSignPositive$mcJ$sp(final long a) {
      return Signed.isSignPositive$mcJ$sp$(this, a);
   }

   public boolean isSignPositive$mcS$sp(final short a) {
      return Signed.isSignPositive$mcS$sp$(this, a);
   }

   public boolean isSignNegative(final Object a) {
      return Signed.isSignNegative$(this, a);
   }

   public boolean isSignNegative$mcB$sp(final byte a) {
      return Signed.isSignNegative$mcB$sp$(this, a);
   }

   public boolean isSignNegative$mcD$sp(final double a) {
      return Signed.isSignNegative$mcD$sp$(this, a);
   }

   public boolean isSignNegative$mcF$sp(final float a) {
      return Signed.isSignNegative$mcF$sp$(this, a);
   }

   public boolean isSignNegative$mcI$sp(final int a) {
      return Signed.isSignNegative$mcI$sp$(this, a);
   }

   public boolean isSignNegative$mcJ$sp(final long a) {
      return Signed.isSignNegative$mcJ$sp$(this, a);
   }

   public boolean isSignNegative$mcS$sp(final short a) {
      return Signed.isSignNegative$mcS$sp$(this, a);
   }

   public boolean isSignNonZero(final Object a) {
      return Signed.isSignNonZero$(this, a);
   }

   public boolean isSignNonZero$mcB$sp(final byte a) {
      return Signed.isSignNonZero$mcB$sp$(this, a);
   }

   public boolean isSignNonZero$mcD$sp(final double a) {
      return Signed.isSignNonZero$mcD$sp$(this, a);
   }

   public boolean isSignNonZero$mcF$sp(final float a) {
      return Signed.isSignNonZero$mcF$sp$(this, a);
   }

   public boolean isSignNonZero$mcI$sp(final int a) {
      return Signed.isSignNonZero$mcI$sp$(this, a);
   }

   public boolean isSignNonZero$mcJ$sp(final long a) {
      return Signed.isSignNonZero$mcJ$sp$(this, a);
   }

   public boolean isSignNonZero$mcS$sp(final short a) {
      return Signed.isSignNonZero$mcS$sp$(this, a);
   }

   public boolean isSignNonPositive(final Object a) {
      return Signed.isSignNonPositive$(this, a);
   }

   public boolean isSignNonPositive$mcB$sp(final byte a) {
      return Signed.isSignNonPositive$mcB$sp$(this, a);
   }

   public boolean isSignNonPositive$mcD$sp(final double a) {
      return Signed.isSignNonPositive$mcD$sp$(this, a);
   }

   public boolean isSignNonPositive$mcF$sp(final float a) {
      return Signed.isSignNonPositive$mcF$sp$(this, a);
   }

   public boolean isSignNonPositive$mcI$sp(final int a) {
      return Signed.isSignNonPositive$mcI$sp$(this, a);
   }

   public boolean isSignNonPositive$mcJ$sp(final long a) {
      return Signed.isSignNonPositive$mcJ$sp$(this, a);
   }

   public boolean isSignNonPositive$mcS$sp(final short a) {
      return Signed.isSignNonPositive$mcS$sp$(this, a);
   }

   public boolean isSignNonNegative(final Object a) {
      return Signed.isSignNonNegative$(this, a);
   }

   public boolean isSignNonNegative$mcB$sp(final byte a) {
      return Signed.isSignNonNegative$mcB$sp$(this, a);
   }

   public boolean isSignNonNegative$mcD$sp(final double a) {
      return Signed.isSignNonNegative$mcD$sp$(this, a);
   }

   public boolean isSignNonNegative$mcF$sp(final float a) {
      return Signed.isSignNonNegative$mcF$sp$(this, a);
   }

   public boolean isSignNonNegative$mcI$sp(final int a) {
      return Signed.isSignNonNegative$mcI$sp$(this, a);
   }

   public boolean isSignNonNegative$mcJ$sp(final long a) {
      return Signed.isSignNonNegative$mcJ$sp$(this, a);
   }

   public boolean isSignNonNegative$mcS$sp(final short a) {
      return Signed.isSignNonNegative$mcS$sp$(this, a);
   }

   public BigInt tquot(final BigInt x, final BigInt y) {
      return x.$div(y);
   }

   public BigInt tmod(final BigInt x, final BigInt y) {
      return x.$percent(y);
   }

   public Order order() {
      return .MODULE$.catsKernelStdOrderForBigInt();
   }

   public BigIntTruncatedDivison() {
      Signed.$init$(this);
      TruncatedDivision.$init$(this);
      Signed.forAdditiveCommutativeMonoid.$init$(this);
      Signed.forAdditiveCommutativeGroup.$init$(this);
      TruncatedDivision.forCommutativeRing.$init$(this);
   }
}
