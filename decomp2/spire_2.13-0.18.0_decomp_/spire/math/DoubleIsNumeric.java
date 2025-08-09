package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.DivisionRing;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import algebra.ring.Signed.forAdditiveCommutativeGroup;
import algebra.ring.Signed.forAdditiveCommutativeMonoid;
import algebra.ring.TruncatedDivision.forCommutativeRing;
import algebra.ring.TruncatedDivision.forCommutativeRing.mcD.sp;
import cats.kernel.CommutativeGroup;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import spire.algebra.IsAlgebraic;
import spire.algebra.IsRational;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.std.DoubleIsField;
import spire.std.DoubleIsNRoot;
import spire.std.DoubleIsReal;
import spire.std.DoubleOrder;
import spire.std.DoubleSigned;
import spire.std.DoubleTruncatedDivision;

@ScalaSignature(
   bytes = "\u0006\u0005%4Q!\u0003\u0006\u0001\u00159AQa\u000f\u0001\u0005\u0002qBQA\u0010\u0001\u0005B}BQ!\u0012\u0001\u0005B\u0019CQ\u0001\u0013\u0001\u0005B%CQA\u0014\u0001\u0005B=CQ!\u0015\u0001\u0005BICQa\u0016\u0001\u0005BaCQ!\u0018\u0001\u0005By\u0013q\u0002R8vE2,\u0017j\u001d(v[\u0016\u0014\u0018n\u0019\u0006\u0003\u00171\tA!\\1uQ*\tQ\"A\u0003ta&\u0014XmE\u0005\u0001\u001fUa\"%\n\u0015,]A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u00042AF\f\u001a\u001b\u0005Q\u0011B\u0001\r\u000b\u0005\u001dqU/\\3sS\u000e\u0004\"\u0001\u0005\u000e\n\u0005m\t\"A\u0002#pk\ndW\r\u0005\u0002\u001eA5\taD\u0003\u0002 \u0019\u0005\u00191\u000f\u001e3\n\u0005\u0005r\"!\u0004#pk\ndW-S:GS\u0016dG\r\u0005\u0002\u001eG%\u0011AE\b\u0002\u000e\t>,(\r\\3Jg:\u0013vn\u001c;\u0011\u0005Y1\u0013BA\u0014\u000b\u0005U\u0019uN\u001c<feR\f'\r\\3Ge>lGi\\;cY\u0016\u0004\"AF\u0015\n\u0005)R!aE\"p]Z,'\u000f^1cY\u0016$v\u000eR8vE2,\u0007CA\u000f-\u0013\ticD\u0001\u0007E_V\u0014G.Z%t%\u0016\fG\u000e\u0005\u00020q9\u0011\u0001G\u000e\b\u0003cUj\u0011A\r\u0006\u0003gQ\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002%%\u0011q'E\u0001\ba\u0006\u001c7.Y4f\u0013\tI$H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00028#\u00051A(\u001b8jiz\"\u0012!\u0010\t\u0003-\u0001\tqA\u001a:p[&sG\u000f\u0006\u0002\u001a\u0001\")\u0011I\u0001a\u0001\u0005\u0006\ta\u000e\u0005\u0002\u0011\u0007&\u0011A)\u0005\u0002\u0004\u0013:$\u0018A\u00034s_6$u.\u001e2mKR\u0011\u0011d\u0012\u0005\u0006\u0003\u000e\u0001\r!G\u0001\u000bMJ|WNQ5h\u0013:$HCA\rK\u0011\u0015\tE\u00011\u0001L!\tyC*\u0003\u0002Nu\t1!)[4J]R\f\u0001\u0002^8E_V\u0014G.\u001a\u000b\u00033ACQ!Q\u0003A\u0002e\t!\u0002^8SCRLwN\\1m)\t\u0019f\u000b\u0005\u0002\u0017)&\u0011QK\u0003\u0002\t%\u0006$\u0018n\u001c8bY\")\u0011I\u0002a\u00013\u0005YAo\\!mO\u0016\u0014'/Y5d)\tIF\f\u0005\u0002\u00175&\u00111L\u0003\u0002\n\u00032<WM\u0019:bS\u000eDQ!Q\u0004A\u0002e\ta\u0001^8SK\u0006dGCA0c!\t1\u0002-\u0003\u0002b\u0015\t!!+Z1m\u0011\u0015\t\u0005\u00021\u0001\u001aQ\u0011\u0001Am\u001a5\u0011\u0005A)\u0017B\u00014\u0012\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0001\u0001"
)
public class DoubleIsNumeric implements Numeric$mcD$sp, DoubleIsField, DoubleIsNRoot, ConvertableFromDouble, ConvertableToDouble, DoubleIsReal {
   private static final long serialVersionUID = 0L;

   public double ceil(final double a) {
      return DoubleIsReal.ceil$(this, a);
   }

   public double floor(final double a) {
      return DoubleIsReal.floor$(this, a);
   }

   public double round(final double a) {
      return DoubleIsReal.round$(this, a);
   }

   public boolean isWhole(final double a) {
      return DoubleIsReal.isWhole$(this, a);
   }

   public double ceil$mcD$sp(final double a) {
      return DoubleIsReal.ceil$mcD$sp$(this, a);
   }

   public double floor$mcD$sp(final double a) {
      return DoubleIsReal.floor$mcD$sp$(this, a);
   }

   public double round$mcD$sp(final double a) {
      return DoubleIsReal.round$mcD$sp$(this, a);
   }

   public boolean isWhole$mcD$sp(final double a) {
      return DoubleIsReal.isWhole$mcD$sp$(this, a);
   }

   public BigInt toBigIntOpt(final double a) {
      return DoubleTruncatedDivision.toBigIntOpt$(this, a);
   }

   public double tquot(final double a, final double b) {
      return DoubleTruncatedDivision.tquot$(this, a, b);
   }

   public double tmod(final double a, final double b) {
      return DoubleTruncatedDivision.tmod$(this, a, b);
   }

   public double tquot$mcD$sp(final double a, final double b) {
      return DoubleTruncatedDivision.tquot$mcD$sp$(this, a, b);
   }

   public double tmod$mcD$sp(final double a, final double b) {
      return DoubleTruncatedDivision.tmod$mcD$sp$(this, a, b);
   }

   public DoubleSigned order() {
      return DoubleSigned.order$(this);
   }

   public int signum(final double a) {
      return DoubleSigned.signum$(this, a);
   }

   public double abs(final double a) {
      return DoubleSigned.abs$(this, a);
   }

   public int signum$mcD$sp(final double a) {
      return DoubleSigned.signum$mcD$sp$(this, a);
   }

   public double abs$mcD$sp(final double a) {
      return DoubleSigned.abs$mcD$sp$(this, a);
   }

   public boolean eqv(final double x, final double y) {
      return DoubleOrder.eqv$(this, x, y);
   }

   public boolean neqv(final double x, final double y) {
      return DoubleOrder.neqv$(this, x, y);
   }

   public boolean gt(final double x, final double y) {
      return DoubleOrder.gt$(this, x, y);
   }

   public boolean gteqv(final double x, final double y) {
      return DoubleOrder.gteqv$(this, x, y);
   }

   public boolean lt(final double x, final double y) {
      return DoubleOrder.lt$(this, x, y);
   }

   public boolean lteqv(final double x, final double y) {
      return DoubleOrder.lteqv$(this, x, y);
   }

   public double min(final double x, final double y) {
      return DoubleOrder.min$(this, x, y);
   }

   public double max(final double x, final double y) {
      return DoubleOrder.max$(this, x, y);
   }

   public int compare(final double x, final double y) {
      return DoubleOrder.compare$(this, x, y);
   }

   public boolean eqv$mcD$sp(final double x, final double y) {
      return DoubleOrder.eqv$mcD$sp$(this, x, y);
   }

   public boolean neqv$mcD$sp(final double x, final double y) {
      return DoubleOrder.neqv$mcD$sp$(this, x, y);
   }

   public boolean gt$mcD$sp(final double x, final double y) {
      return DoubleOrder.gt$mcD$sp$(this, x, y);
   }

   public boolean gteqv$mcD$sp(final double x, final double y) {
      return DoubleOrder.gteqv$mcD$sp$(this, x, y);
   }

   public boolean lt$mcD$sp(final double x, final double y) {
      return DoubleOrder.lt$mcD$sp$(this, x, y);
   }

   public boolean lteqv$mcD$sp(final double x, final double y) {
      return DoubleOrder.lteqv$mcD$sp$(this, x, y);
   }

   public double min$mcD$sp(final double x, final double y) {
      return DoubleOrder.min$mcD$sp$(this, x, y);
   }

   public double max$mcD$sp(final double x, final double y) {
      return DoubleOrder.max$mcD$sp$(this, x, y);
   }

   public int compare$mcD$sp(final double x, final double y) {
      return DoubleOrder.compare$mcD$sp$(this, x, y);
   }

   public double fmod(final double x, final double y) {
      return sp.fmod$(this, x, y);
   }

   public double fmod$mcD$sp(final double x, final double y) {
      return sp.fmod$mcD$sp$(this, x, y);
   }

   public double fquot(final double x, final double y) {
      return sp.fquot$(this, x, y);
   }

   public double fquot$mcD$sp(final double x, final double y) {
      return sp.fquot$mcD$sp$(this, x, y);
   }

   public Tuple2 fquotmod(final double x, final double y) {
      return sp.fquotmod$(this, x, y);
   }

   public Tuple2 fquotmod$mcD$sp(final double x, final double y) {
      return sp.fquotmod$mcD$sp$(this, x, y);
   }

   public Tuple2 tquotmod(final double x, final double y) {
      return algebra.ring.TruncatedDivision.mcD.sp.tquotmod$(this, x, y);
   }

   public Tuple2 tquotmod$mcD$sp(final double x, final double y) {
      return algebra.ring.TruncatedDivision.mcD.sp.tquotmod$mcD$sp$(this, x, y);
   }

   public byte fmod$mcB$sp(final byte x, final byte y) {
      return forCommutativeRing.fmod$mcB$sp$(this, x, y);
   }

   public float fmod$mcF$sp(final float x, final float y) {
      return forCommutativeRing.fmod$mcF$sp$(this, x, y);
   }

   public int fmod$mcI$sp(final int x, final int y) {
      return forCommutativeRing.fmod$mcI$sp$(this, x, y);
   }

   public long fmod$mcJ$sp(final long x, final long y) {
      return forCommutativeRing.fmod$mcJ$sp$(this, x, y);
   }

   public short fmod$mcS$sp(final short x, final short y) {
      return forCommutativeRing.fmod$mcS$sp$(this, x, y);
   }

   public byte fquot$mcB$sp(final byte x, final byte y) {
      return forCommutativeRing.fquot$mcB$sp$(this, x, y);
   }

   public float fquot$mcF$sp(final float x, final float y) {
      return forCommutativeRing.fquot$mcF$sp$(this, x, y);
   }

   public int fquot$mcI$sp(final int x, final int y) {
      return forCommutativeRing.fquot$mcI$sp$(this, x, y);
   }

   public long fquot$mcJ$sp(final long x, final long y) {
      return forCommutativeRing.fquot$mcJ$sp$(this, x, y);
   }

   public short fquot$mcS$sp(final short x, final short y) {
      return forCommutativeRing.fquot$mcS$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcB$sp(final byte x, final byte y) {
      return forCommutativeRing.fquotmod$mcB$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcF$sp(final float x, final float y) {
      return forCommutativeRing.fquotmod$mcF$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcI$sp(final int x, final int y) {
      return forCommutativeRing.fquotmod$mcI$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcJ$sp(final long x, final long y) {
      return forCommutativeRing.fquotmod$mcJ$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcS$sp(final short x, final short y) {
      return forCommutativeRing.fquotmod$mcS$sp$(this, x, y);
   }

   public final Signed.forAdditiveCommutativeMonoid additiveCommutativeMonoid() {
      return forAdditiveCommutativeMonoid.additiveCommutativeMonoid$(this);
   }

   public byte tquot$mcB$sp(final byte x, final byte y) {
      return TruncatedDivision.tquot$mcB$sp$(this, x, y);
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

   public Tuple2 tquotmod$mcB$sp(final byte x, final byte y) {
      return TruncatedDivision.tquotmod$mcB$sp$(this, x, y);
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

   public Algebraic toAlgebraic$mcZ$sp(final boolean a) {
      return IsAlgebraic.toAlgebraic$mcZ$sp$(this, a);
   }

   public Algebraic toAlgebraic$mcB$sp(final byte a) {
      return IsAlgebraic.toAlgebraic$mcB$sp$(this, a);
   }

   public Algebraic toAlgebraic$mcC$sp(final char a) {
      return IsAlgebraic.toAlgebraic$mcC$sp$(this, a);
   }

   public Algebraic toAlgebraic$mcF$sp(final float a) {
      return IsAlgebraic.toAlgebraic$mcF$sp$(this, a);
   }

   public Algebraic toAlgebraic$mcI$sp(final int a) {
      return IsAlgebraic.toAlgebraic$mcI$sp$(this, a);
   }

   public Algebraic toAlgebraic$mcJ$sp(final long a) {
      return IsAlgebraic.toAlgebraic$mcJ$sp$(this, a);
   }

   public Algebraic toAlgebraic$mcS$sp(final short a) {
      return IsAlgebraic.toAlgebraic$mcS$sp$(this, a);
   }

   public Algebraic toAlgebraic$mcV$sp(final BoxedUnit a) {
      return IsAlgebraic.toAlgebraic$mcV$sp$(this, a);
   }

   public Real toReal$mcZ$sp(final boolean a) {
      return IsAlgebraic.toReal$mcZ$sp$(this, a);
   }

   public Real toReal$mcB$sp(final byte a) {
      return IsAlgebraic.toReal$mcB$sp$(this, a);
   }

   public Real toReal$mcC$sp(final char a) {
      return IsAlgebraic.toReal$mcC$sp$(this, a);
   }

   public Real toReal$mcF$sp(final float a) {
      return IsAlgebraic.toReal$mcF$sp$(this, a);
   }

   public Real toReal$mcI$sp(final int a) {
      return IsAlgebraic.toReal$mcI$sp$(this, a);
   }

   public Real toReal$mcJ$sp(final long a) {
      return IsAlgebraic.toReal$mcJ$sp$(this, a);
   }

   public Real toReal$mcS$sp(final short a) {
      return IsAlgebraic.toReal$mcS$sp$(this, a);
   }

   public Real toReal$mcV$sp(final BoxedUnit a) {
      return IsAlgebraic.toReal$mcV$sp$(this, a);
   }

   public double fromByte(final byte a) {
      return ConvertableToDouble.fromByte$(this, a);
   }

   public double fromShort(final short a) {
      return ConvertableToDouble.fromShort$(this, a);
   }

   public double fromLong(final long a) {
      return ConvertableToDouble.fromLong$(this, a);
   }

   public double fromFloat(final float a) {
      return ConvertableToDouble.fromFloat$(this, a);
   }

   public double fromBigDecimal(final BigDecimal a) {
      return ConvertableToDouble.fromBigDecimal$(this, a);
   }

   public double fromRational(final Rational a) {
      return ConvertableToDouble.fromRational$(this, a);
   }

   public double fromAlgebraic(final Algebraic a) {
      return ConvertableToDouble.fromAlgebraic$(this, a);
   }

   public double fromReal(final Real a) {
      return ConvertableToDouble.fromReal$(this, a);
   }

   public double fromType(final Object b, final ConvertableFrom evidence$7) {
      return ConvertableToDouble.fromType$(this, b, evidence$7);
   }

   public double fromByte$mcD$sp(final byte a) {
      return ConvertableToDouble.fromByte$mcD$sp$(this, a);
   }

   public double fromShort$mcD$sp(final short a) {
      return ConvertableToDouble.fromShort$mcD$sp$(this, a);
   }

   public double fromLong$mcD$sp(final long a) {
      return ConvertableToDouble.fromLong$mcD$sp$(this, a);
   }

   public double fromFloat$mcD$sp(final float a) {
      return ConvertableToDouble.fromFloat$mcD$sp$(this, a);
   }

   public double fromBigDecimal$mcD$sp(final BigDecimal a) {
      return ConvertableToDouble.fromBigDecimal$mcD$sp$(this, a);
   }

   public double fromRational$mcD$sp(final Rational a) {
      return ConvertableToDouble.fromRational$mcD$sp$(this, a);
   }

   public double fromAlgebraic$mcD$sp(final Algebraic a) {
      return ConvertableToDouble.fromAlgebraic$mcD$sp$(this, a);
   }

   public double fromReal$mcD$sp(final Real a) {
      return ConvertableToDouble.fromReal$mcD$sp$(this, a);
   }

   public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$7) {
      return ConvertableToDouble.fromType$mcD$sp$(this, b, evidence$7);
   }

   public byte toByte(final double a) {
      return ConvertableFromDouble.toByte$(this, a);
   }

   public short toShort(final double a) {
      return ConvertableFromDouble.toShort$(this, a);
   }

   public int toInt(final double a) {
      return ConvertableFromDouble.toInt$(this, a);
   }

   public long toLong(final double a) {
      return ConvertableFromDouble.toLong$(this, a);
   }

   public float toFloat(final double a) {
      return ConvertableFromDouble.toFloat$(this, a);
   }

   public BigInt toBigInt(final double a) {
      return ConvertableFromDouble.toBigInt$(this, a);
   }

   public BigDecimal toBigDecimal(final double a) {
      return ConvertableFromDouble.toBigDecimal$(this, a);
   }

   public Number toNumber(final double a) {
      return ConvertableFromDouble.toNumber$(this, a);
   }

   public Object toType(final double a, final ConvertableTo evidence$23) {
      return ConvertableFromDouble.toType$(this, a, evidence$23);
   }

   public String toString(final double a) {
      return ConvertableFromDouble.toString$(this, a);
   }

   public byte toByte$mcD$sp(final double a) {
      return ConvertableFromDouble.toByte$mcD$sp$(this, a);
   }

   public short toShort$mcD$sp(final double a) {
      return ConvertableFromDouble.toShort$mcD$sp$(this, a);
   }

   public int toInt$mcD$sp(final double a) {
      return ConvertableFromDouble.toInt$mcD$sp$(this, a);
   }

   public long toLong$mcD$sp(final double a) {
      return ConvertableFromDouble.toLong$mcD$sp$(this, a);
   }

   public float toFloat$mcD$sp(final double a) {
      return ConvertableFromDouble.toFloat$mcD$sp$(this, a);
   }

   public BigInt toBigInt$mcD$sp(final double a) {
      return ConvertableFromDouble.toBigInt$mcD$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcD$sp(final double a) {
      return ConvertableFromDouble.toBigDecimal$mcD$sp$(this, a);
   }

   public Number toNumber$mcD$sp(final double a) {
      return ConvertableFromDouble.toNumber$mcD$sp$(this, a);
   }

   public Object toType$mcD$sp(final double a, final ConvertableTo evidence$23) {
      return ConvertableFromDouble.toType$mcD$sp$(this, a, evidence$23);
   }

   public String toString$mcD$sp(final double a) {
      return ConvertableFromDouble.toString$mcD$sp$(this, a);
   }

   public double nroot(final double a, final int k) {
      return DoubleIsNRoot.nroot$(this, a, k);
   }

   public double sqrt(final double a) {
      return DoubleIsNRoot.sqrt$(this, a);
   }

   public double fpow(final double a, final double b) {
      return DoubleIsNRoot.fpow$(this, a, b);
   }

   public double nroot$mcD$sp(final double a, final int k) {
      return DoubleIsNRoot.nroot$mcD$sp$(this, a, k);
   }

   public double sqrt$mcD$sp(final double a) {
      return DoubleIsNRoot.sqrt$mcD$sp$(this, a);
   }

   public double fpow$mcD$sp(final double a, final double b) {
      return DoubleIsNRoot.fpow$mcD$sp$(this, a, b);
   }

   public double minus(final double a, final double b) {
      return DoubleIsField.minus$(this, a, b);
   }

   public double negate(final double a) {
      return DoubleIsField.negate$(this, a);
   }

   public double one() {
      return DoubleIsField.one$(this);
   }

   public double plus(final double a, final double b) {
      return DoubleIsField.plus$(this, a, b);
   }

   public double pow(final double a, final int b) {
      return DoubleIsField.pow$(this, a, b);
   }

   public double times(final double a, final double b) {
      return DoubleIsField.times$(this, a, b);
   }

   public double zero() {
      return DoubleIsField.zero$(this);
   }

   public double div(final double a, final double b) {
      return DoubleIsField.div$(this, a, b);
   }

   public double minus$mcD$sp(final double a, final double b) {
      return DoubleIsField.minus$mcD$sp$(this, a, b);
   }

   public double negate$mcD$sp(final double a) {
      return DoubleIsField.negate$mcD$sp$(this, a);
   }

   public double one$mcD$sp() {
      return DoubleIsField.one$mcD$sp$(this);
   }

   public double plus$mcD$sp(final double a, final double b) {
      return DoubleIsField.plus$mcD$sp$(this, a, b);
   }

   public double pow$mcD$sp(final double a, final int b) {
      return DoubleIsField.pow$mcD$sp$(this, a, b);
   }

   public double times$mcD$sp(final double a, final double b) {
      return DoubleIsField.times$mcD$sp$(this, a, b);
   }

   public double zero$mcD$sp() {
      return DoubleIsField.zero$mcD$sp$(this);
   }

   public double div$mcD$sp(final double a, final double b) {
      return DoubleIsField.div$mcD$sp$(this, a, b);
   }

   public double gcd(final double a, final double b, final Eq eqA) {
      return algebra.ring.Field.mcD.sp.gcd$(this, a, b, eqA);
   }

   public double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
      return algebra.ring.Field.mcD.sp.gcd$mcD$sp$(this, a, b, eqA);
   }

   public double lcm(final double a, final double b, final Eq eqA) {
      return algebra.ring.Field.mcD.sp.lcm$(this, a, b, eqA);
   }

   public double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
      return algebra.ring.Field.mcD.sp.lcm$mcD$sp$(this, a, b, eqA);
   }

   public BigInt euclideanFunction(final double a) {
      return algebra.ring.Field.mcD.sp.euclideanFunction$(this, a);
   }

   public BigInt euclideanFunction$mcD$sp(final double a) {
      return algebra.ring.Field.mcD.sp.euclideanFunction$mcD$sp$(this, a);
   }

   public double equot(final double a, final double b) {
      return algebra.ring.Field.mcD.sp.equot$(this, a, b);
   }

   public double equot$mcD$sp(final double a, final double b) {
      return algebra.ring.Field.mcD.sp.equot$mcD$sp$(this, a, b);
   }

   public double emod(final double a, final double b) {
      return algebra.ring.Field.mcD.sp.emod$(this, a, b);
   }

   public double emod$mcD$sp(final double a, final double b) {
      return algebra.ring.Field.mcD.sp.emod$mcD$sp$(this, a, b);
   }

   public Tuple2 equotmod(final double a, final double b) {
      return algebra.ring.Field.mcD.sp.equotmod$(this, a, b);
   }

   public Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return algebra.ring.Field.mcD.sp.equotmod$mcD$sp$(this, a, b);
   }

   public float gcd$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field.gcd$mcF$sp$(this, a, b, eqA);
   }

   public int gcd$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.gcd$mcI$sp$(this, a, b, eqA);
   }

   public long gcd$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.gcd$mcJ$sp$(this, a, b, eqA);
   }

   public float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
      return Field.lcm$mcF$sp$(this, a, b, eqA);
   }

   public int lcm$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.lcm$mcI$sp$(this, a, b, eqA);
   }

   public long lcm$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.lcm$mcJ$sp$(this, a, b, eqA);
   }

   public BigInt euclideanFunction$mcF$sp(final float a) {
      return Field.euclideanFunction$mcF$sp$(this, a);
   }

   public BigInt euclideanFunction$mcI$sp(final int a) {
      return Field.euclideanFunction$mcI$sp$(this, a);
   }

   public BigInt euclideanFunction$mcJ$sp(final long a) {
      return Field.euclideanFunction$mcJ$sp$(this, a);
   }

   public float equot$mcF$sp(final float a, final float b) {
      return Field.equot$mcF$sp$(this, a, b);
   }

   public int equot$mcI$sp(final int a, final int b) {
      return Field.equot$mcI$sp$(this, a, b);
   }

   public long equot$mcJ$sp(final long a, final long b) {
      return Field.equot$mcJ$sp$(this, a, b);
   }

   public float emod$mcF$sp(final float a, final float b) {
      return Field.emod$mcF$sp$(this, a, b);
   }

   public int emod$mcI$sp(final int a, final int b) {
      return Field.emod$mcI$sp$(this, a, b);
   }

   public long emod$mcJ$sp(final long a, final long b) {
      return Field.emod$mcJ$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return Field.equotmod$mcF$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return Field.equotmod$mcI$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return Field.equotmod$mcJ$sp$(this, a, b);
   }

   public float fromDouble$mcF$sp(final double a) {
      return Field.fromDouble$mcF$sp$(this, a);
   }

   public int fromDouble$mcI$sp(final double a) {
      return Field.fromDouble$mcI$sp$(this, a);
   }

   public long fromDouble$mcJ$sp(final double a) {
      return Field.fromDouble$mcJ$sp$(this, a);
   }

   public byte fromDouble$mcB$sp(final double a) {
      return DivisionRing.fromDouble$mcB$sp$(this, a);
   }

   public short fromDouble$mcS$sp(final double a) {
      return DivisionRing.fromDouble$mcS$sp$(this, a);
   }

   public CommutativeGroup additive() {
      return algebra.ring.AdditiveCommutativeGroup.mcD.sp.additive$(this);
   }

   public CommutativeGroup additive$mcD$sp() {
      return algebra.ring.AdditiveCommutativeGroup.mcD.sp.additive$mcD$sp$(this);
   }

   public double sumN(final double a, final int n) {
      return algebra.ring.AdditiveGroup.mcD.sp.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return algebra.ring.AdditiveGroup.mcD.sp.sumN$mcD$sp$(this, a, n);
   }

   public boolean isZero(final double a, final Eq ev) {
      return algebra.ring.AdditiveMonoid.mcD.sp.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return algebra.ring.AdditiveMonoid.mcD.sp.isZero$mcD$sp$(this, a, ev);
   }

   public double sum(final IterableOnce as) {
      return algebra.ring.AdditiveMonoid.mcD.sp.sum$(this, as);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return algebra.ring.AdditiveMonoid.mcD.sp.sum$mcD$sp$(this, as);
   }

   public double positiveSumN(final double a, final int n) {
      return algebra.ring.AdditiveSemigroup.mcD.sp.positiveSumN$(this, a, n);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return algebra.ring.AdditiveSemigroup.mcD.sp.positiveSumN$mcD$sp$(this, a, n);
   }

   public CommutativeGroup multiplicative() {
      return algebra.ring.MultiplicativeCommutativeGroup.mcD.sp.multiplicative$(this);
   }

   public CommutativeGroup multiplicative$mcD$sp() {
      return algebra.ring.MultiplicativeCommutativeGroup.mcD.sp.multiplicative$mcD$sp$(this);
   }

   public double reciprocal(final double x) {
      return algebra.ring.MultiplicativeGroup.mcD.sp.reciprocal$(this, x);
   }

   public double reciprocal$mcD$sp(final double x) {
      return algebra.ring.MultiplicativeGroup.mcD.sp.reciprocal$mcD$sp$(this, x);
   }

   public boolean isOne(final double a, final Eq ev) {
      return algebra.ring.MultiplicativeMonoid.mcD.sp.isOne$(this, a, ev);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return algebra.ring.MultiplicativeMonoid.mcD.sp.isOne$mcD$sp$(this, a, ev);
   }

   public double product(final IterableOnce as) {
      return algebra.ring.MultiplicativeMonoid.mcD.sp.product$(this, as);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return algebra.ring.MultiplicativeMonoid.mcD.sp.product$mcD$sp$(this, as);
   }

   public double positivePow(final double a, final int n) {
      return algebra.ring.MultiplicativeSemigroup.mcD.sp.positivePow$(this, a, n);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return algebra.ring.MultiplicativeSemigroup.mcD.sp.positivePow$mcD$sp$(this, a, n);
   }

   public Comparison comparison(final double x, final double y) {
      return cats.kernel.Order.mcD.sp.comparison$(this, x, y);
   }

   public Comparison comparison$mcD$sp(final double x, final double y) {
      return cats.kernel.Order.mcD.sp.comparison$mcD$sp$(this, x, y);
   }

   public double partialCompare(final double x, final double y) {
      return cats.kernel.Order.mcD.sp.partialCompare$(this, x, y);
   }

   public double partialCompare$mcD$sp(final double x, final double y) {
      return cats.kernel.Order.mcD.sp.partialCompare$mcD$sp$(this, x, y);
   }

   public Option partialComparison(final double x, final double y) {
      return cats.kernel.PartialOrder.mcD.sp.partialComparison$(this, x, y);
   }

   public Option partialComparison$mcD$sp(final double x, final double y) {
      return cats.kernel.PartialOrder.mcD.sp.partialComparison$mcD$sp$(this, x, y);
   }

   public Option tryCompare(final double x, final double y) {
      return cats.kernel.PartialOrder.mcD.sp.tryCompare$(this, x, y);
   }

   public Option tryCompare$mcD$sp(final double x, final double y) {
      return cats.kernel.PartialOrder.mcD.sp.tryCompare$mcD$sp$(this, x, y);
   }

   public Option pmin(final double x, final double y) {
      return cats.kernel.PartialOrder.mcD.sp.pmin$(this, x, y);
   }

   public Option pmin$mcD$sp(final double x, final double y) {
      return cats.kernel.PartialOrder.mcD.sp.pmin$mcD$sp$(this, x, y);
   }

   public Option pmax(final double x, final double y) {
      return cats.kernel.PartialOrder.mcD.sp.pmax$(this, x, y);
   }

   public Option pmax$mcD$sp(final double x, final double y) {
      return cats.kernel.PartialOrder.mcD.sp.pmax$mcD$sp$(this, x, y);
   }

   public Signed.Sign sign(final double a) {
      return algebra.ring.Signed.mcD.sp.sign$(this, a);
   }

   public Signed.Sign sign$mcD$sp(final double a) {
      return algebra.ring.Signed.mcD.sp.sign$mcD$sp$(this, a);
   }

   public boolean isSignZero(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignZero$(this, a);
   }

   public boolean isSignZero$mcD$sp(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignZero$mcD$sp$(this, a);
   }

   public boolean isSignPositive(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignPositive$(this, a);
   }

   public boolean isSignPositive$mcD$sp(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignPositive$mcD$sp$(this, a);
   }

   public boolean isSignNegative(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignNegative$(this, a);
   }

   public boolean isSignNegative$mcD$sp(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignNegative$mcD$sp$(this, a);
   }

   public boolean isSignNonZero(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignNonZero$(this, a);
   }

   public boolean isSignNonZero$mcD$sp(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignNonZero$mcD$sp$(this, a);
   }

   public boolean isSignNonPositive(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignNonPositive$(this, a);
   }

   public boolean isSignNonPositive$mcD$sp(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignNonPositive$mcD$sp$(this, a);
   }

   public boolean isSignNonNegative(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignNonNegative$(this, a);
   }

   public boolean isSignNonNegative$mcD$sp(final double a) {
      return algebra.ring.Signed.mcD.sp.isSignNonNegative$mcD$sp$(this, a);
   }

   public boolean ceil$mcZ$sp(final boolean a) {
      return IsReal.ceil$mcZ$sp$(this, a);
   }

   public byte ceil$mcB$sp(final byte a) {
      return IsReal.ceil$mcB$sp$(this, a);
   }

   public char ceil$mcC$sp(final char a) {
      return IsReal.ceil$mcC$sp$(this, a);
   }

   public float ceil$mcF$sp(final float a) {
      return IsReal.ceil$mcF$sp$(this, a);
   }

   public int ceil$mcI$sp(final int a) {
      return IsReal.ceil$mcI$sp$(this, a);
   }

   public long ceil$mcJ$sp(final long a) {
      return IsReal.ceil$mcJ$sp$(this, a);
   }

   public short ceil$mcS$sp(final short a) {
      return IsReal.ceil$mcS$sp$(this, a);
   }

   public void ceil$mcV$sp(final BoxedUnit a) {
      IsReal.ceil$mcV$sp$(this, a);
   }

   public boolean floor$mcZ$sp(final boolean a) {
      return IsReal.floor$mcZ$sp$(this, a);
   }

   public byte floor$mcB$sp(final byte a) {
      return IsReal.floor$mcB$sp$(this, a);
   }

   public char floor$mcC$sp(final char a) {
      return IsReal.floor$mcC$sp$(this, a);
   }

   public float floor$mcF$sp(final float a) {
      return IsReal.floor$mcF$sp$(this, a);
   }

   public int floor$mcI$sp(final int a) {
      return IsReal.floor$mcI$sp$(this, a);
   }

   public long floor$mcJ$sp(final long a) {
      return IsReal.floor$mcJ$sp$(this, a);
   }

   public short floor$mcS$sp(final short a) {
      return IsReal.floor$mcS$sp$(this, a);
   }

   public void floor$mcV$sp(final BoxedUnit a) {
      IsReal.floor$mcV$sp$(this, a);
   }

   public boolean round$mcZ$sp(final boolean a) {
      return IsReal.round$mcZ$sp$(this, a);
   }

   public byte round$mcB$sp(final byte a) {
      return IsReal.round$mcB$sp$(this, a);
   }

   public char round$mcC$sp(final char a) {
      return IsReal.round$mcC$sp$(this, a);
   }

   public float round$mcF$sp(final float a) {
      return IsReal.round$mcF$sp$(this, a);
   }

   public int round$mcI$sp(final int a) {
      return IsReal.round$mcI$sp$(this, a);
   }

   public long round$mcJ$sp(final long a) {
      return IsReal.round$mcJ$sp$(this, a);
   }

   public short round$mcS$sp(final short a) {
      return IsReal.round$mcS$sp$(this, a);
   }

   public void round$mcV$sp(final BoxedUnit a) {
      IsReal.round$mcV$sp$(this, a);
   }

   public boolean isWhole$mcZ$sp(final boolean a) {
      return IsReal.isWhole$mcZ$sp$(this, a);
   }

   public boolean isWhole$mcB$sp(final byte a) {
      return IsReal.isWhole$mcB$sp$(this, a);
   }

   public boolean isWhole$mcC$sp(final char a) {
      return IsReal.isWhole$mcC$sp$(this, a);
   }

   public boolean isWhole$mcF$sp(final float a) {
      return IsReal.isWhole$mcF$sp$(this, a);
   }

   public boolean isWhole$mcI$sp(final int a) {
      return IsReal.isWhole$mcI$sp$(this, a);
   }

   public boolean isWhole$mcJ$sp(final long a) {
      return IsReal.isWhole$mcJ$sp$(this, a);
   }

   public boolean isWhole$mcS$sp(final short a) {
      return IsReal.isWhole$mcS$sp$(this, a);
   }

   public boolean isWhole$mcV$sp(final BoxedUnit a) {
      return IsReal.isWhole$mcV$sp$(this, a);
   }

   public double toDouble$mcZ$sp(final boolean a) {
      return IsReal.toDouble$mcZ$sp$(this, a);
   }

   public double toDouble$mcB$sp(final byte a) {
      return IsReal.toDouble$mcB$sp$(this, a);
   }

   public double toDouble$mcC$sp(final char a) {
      return IsReal.toDouble$mcC$sp$(this, a);
   }

   public double toDouble$mcF$sp(final float a) {
      return IsReal.toDouble$mcF$sp$(this, a);
   }

   public double toDouble$mcI$sp(final int a) {
      return IsReal.toDouble$mcI$sp$(this, a);
   }

   public double toDouble$mcJ$sp(final long a) {
      return IsReal.toDouble$mcJ$sp$(this, a);
   }

   public double toDouble$mcS$sp(final short a) {
      return IsReal.toDouble$mcS$sp$(this, a);
   }

   public double toDouble$mcV$sp(final BoxedUnit a) {
      return IsReal.toDouble$mcV$sp$(this, a);
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

   public Signed.Sign sign$mcB$sp(final byte a) {
      return Signed.sign$mcB$sp$(this, a);
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

   public boolean isSignZero$mcB$sp(final byte a) {
      return Signed.isSignZero$mcB$sp$(this, a);
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

   public boolean isSignPositive$mcB$sp(final byte a) {
      return Signed.isSignPositive$mcB$sp$(this, a);
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

   public boolean isSignNegative$mcB$sp(final byte a) {
      return Signed.isSignNegative$mcB$sp$(this, a);
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

   public boolean isSignNonZero$mcB$sp(final byte a) {
      return Signed.isSignNonZero$mcB$sp$(this, a);
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

   public boolean isSignNonPositive$mcB$sp(final byte a) {
      return Signed.isSignNonPositive$mcB$sp$(this, a);
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

   public boolean isSignNonNegative$mcB$sp(final byte a) {
      return Signed.isSignNonNegative$mcB$sp$(this, a);
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

   public int compare$mcZ$sp(final boolean x, final boolean y) {
      return Order.compare$mcZ$sp$(this, x, y);
   }

   public int compare$mcB$sp(final byte x, final byte y) {
      return Order.compare$mcB$sp$(this, x, y);
   }

   public int compare$mcC$sp(final char x, final char y) {
      return Order.compare$mcC$sp$(this, x, y);
   }

   public int compare$mcF$sp(final float x, final float y) {
      return Order.compare$mcF$sp$(this, x, y);
   }

   public int compare$mcI$sp(final int x, final int y) {
      return Order.compare$mcI$sp$(this, x, y);
   }

   public int compare$mcJ$sp(final long x, final long y) {
      return Order.compare$mcJ$sp$(this, x, y);
   }

   public int compare$mcS$sp(final short x, final short y) {
      return Order.compare$mcS$sp$(this, x, y);
   }

   public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.compare$mcV$sp$(this, x, y);
   }

   public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
      return Order.comparison$mcZ$sp$(this, x, y);
   }

   public Comparison comparison$mcB$sp(final byte x, final byte y) {
      return Order.comparison$mcB$sp$(this, x, y);
   }

   public Comparison comparison$mcC$sp(final char x, final char y) {
      return Order.comparison$mcC$sp$(this, x, y);
   }

   public Comparison comparison$mcF$sp(final float x, final float y) {
      return Order.comparison$mcF$sp$(this, x, y);
   }

   public Comparison comparison$mcI$sp(final int x, final int y) {
      return Order.comparison$mcI$sp$(this, x, y);
   }

   public Comparison comparison$mcJ$sp(final long x, final long y) {
      return Order.comparison$mcJ$sp$(this, x, y);
   }

   public Comparison comparison$mcS$sp(final short x, final short y) {
      return Order.comparison$mcS$sp$(this, x, y);
   }

   public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.comparison$mcV$sp$(this, x, y);
   }

   public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
      return Order.partialCompare$mcZ$sp$(this, x, y);
   }

   public double partialCompare$mcB$sp(final byte x, final byte y) {
      return Order.partialCompare$mcB$sp$(this, x, y);
   }

   public double partialCompare$mcC$sp(final char x, final char y) {
      return Order.partialCompare$mcC$sp$(this, x, y);
   }

   public double partialCompare$mcF$sp(final float x, final float y) {
      return Order.partialCompare$mcF$sp$(this, x, y);
   }

   public double partialCompare$mcI$sp(final int x, final int y) {
      return Order.partialCompare$mcI$sp$(this, x, y);
   }

   public double partialCompare$mcJ$sp(final long x, final long y) {
      return Order.partialCompare$mcJ$sp$(this, x, y);
   }

   public double partialCompare$mcS$sp(final short x, final short y) {
      return Order.partialCompare$mcS$sp$(this, x, y);
   }

   public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.partialCompare$mcV$sp$(this, x, y);
   }

   public boolean min$mcZ$sp(final boolean x, final boolean y) {
      return Order.min$mcZ$sp$(this, x, y);
   }

   public byte min$mcB$sp(final byte x, final byte y) {
      return Order.min$mcB$sp$(this, x, y);
   }

   public char min$mcC$sp(final char x, final char y) {
      return Order.min$mcC$sp$(this, x, y);
   }

   public float min$mcF$sp(final float x, final float y) {
      return Order.min$mcF$sp$(this, x, y);
   }

   public int min$mcI$sp(final int x, final int y) {
      return Order.min$mcI$sp$(this, x, y);
   }

   public long min$mcJ$sp(final long x, final long y) {
      return Order.min$mcJ$sp$(this, x, y);
   }

   public short min$mcS$sp(final short x, final short y) {
      return Order.min$mcS$sp$(this, x, y);
   }

   public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      Order.min$mcV$sp$(this, x, y);
   }

   public boolean max$mcZ$sp(final boolean x, final boolean y) {
      return Order.max$mcZ$sp$(this, x, y);
   }

   public byte max$mcB$sp(final byte x, final byte y) {
      return Order.max$mcB$sp$(this, x, y);
   }

   public char max$mcC$sp(final char x, final char y) {
      return Order.max$mcC$sp$(this, x, y);
   }

   public float max$mcF$sp(final float x, final float y) {
      return Order.max$mcF$sp$(this, x, y);
   }

   public int max$mcI$sp(final int x, final int y) {
      return Order.max$mcI$sp$(this, x, y);
   }

   public long max$mcJ$sp(final long x, final long y) {
      return Order.max$mcJ$sp$(this, x, y);
   }

   public short max$mcS$sp(final short x, final short y) {
      return Order.max$mcS$sp$(this, x, y);
   }

   public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      Order.max$mcV$sp$(this, x, y);
   }

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.eqv$mcZ$sp$(this, x, y);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return Order.eqv$mcB$sp$(this, x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return Order.eqv$mcC$sp$(this, x, y);
   }

   public boolean eqv$mcF$sp(final float x, final float y) {
      return Order.eqv$mcF$sp$(this, x, y);
   }

   public boolean eqv$mcI$sp(final int x, final int y) {
      return Order.eqv$mcI$sp$(this, x, y);
   }

   public boolean eqv$mcJ$sp(final long x, final long y) {
      return Order.eqv$mcJ$sp$(this, x, y);
   }

   public boolean eqv$mcS$sp(final short x, final short y) {
      return Order.eqv$mcS$sp$(this, x, y);
   }

   public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.eqv$mcV$sp$(this, x, y);
   }

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.neqv$mcZ$sp$(this, x, y);
   }

   public boolean neqv$mcB$sp(final byte x, final byte y) {
      return Order.neqv$mcB$sp$(this, x, y);
   }

   public boolean neqv$mcC$sp(final char x, final char y) {
      return Order.neqv$mcC$sp$(this, x, y);
   }

   public boolean neqv$mcF$sp(final float x, final float y) {
      return Order.neqv$mcF$sp$(this, x, y);
   }

   public boolean neqv$mcI$sp(final int x, final int y) {
      return Order.neqv$mcI$sp$(this, x, y);
   }

   public boolean neqv$mcJ$sp(final long x, final long y) {
      return Order.neqv$mcJ$sp$(this, x, y);
   }

   public boolean neqv$mcS$sp(final short x, final short y) {
      return Order.neqv$mcS$sp$(this, x, y);
   }

   public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.neqv$mcV$sp$(this, x, y);
   }

   public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.lteqv$mcZ$sp$(this, x, y);
   }

   public boolean lteqv$mcB$sp(final byte x, final byte y) {
      return Order.lteqv$mcB$sp$(this, x, y);
   }

   public boolean lteqv$mcC$sp(final char x, final char y) {
      return Order.lteqv$mcC$sp$(this, x, y);
   }

   public boolean lteqv$mcF$sp(final float x, final float y) {
      return Order.lteqv$mcF$sp$(this, x, y);
   }

   public boolean lteqv$mcI$sp(final int x, final int y) {
      return Order.lteqv$mcI$sp$(this, x, y);
   }

   public boolean lteqv$mcJ$sp(final long x, final long y) {
      return Order.lteqv$mcJ$sp$(this, x, y);
   }

   public boolean lteqv$mcS$sp(final short x, final short y) {
      return Order.lteqv$mcS$sp$(this, x, y);
   }

   public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.lteqv$mcV$sp$(this, x, y);
   }

   public boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return Order.lt$mcZ$sp$(this, x, y);
   }

   public boolean lt$mcB$sp(final byte x, final byte y) {
      return Order.lt$mcB$sp$(this, x, y);
   }

   public boolean lt$mcC$sp(final char x, final char y) {
      return Order.lt$mcC$sp$(this, x, y);
   }

   public boolean lt$mcF$sp(final float x, final float y) {
      return Order.lt$mcF$sp$(this, x, y);
   }

   public boolean lt$mcI$sp(final int x, final int y) {
      return Order.lt$mcI$sp$(this, x, y);
   }

   public boolean lt$mcJ$sp(final long x, final long y) {
      return Order.lt$mcJ$sp$(this, x, y);
   }

   public boolean lt$mcS$sp(final short x, final short y) {
      return Order.lt$mcS$sp$(this, x, y);
   }

   public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.lt$mcV$sp$(this, x, y);
   }

   public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.gteqv$mcZ$sp$(this, x, y);
   }

   public boolean gteqv$mcB$sp(final byte x, final byte y) {
      return Order.gteqv$mcB$sp$(this, x, y);
   }

   public boolean gteqv$mcC$sp(final char x, final char y) {
      return Order.gteqv$mcC$sp$(this, x, y);
   }

   public boolean gteqv$mcF$sp(final float x, final float y) {
      return Order.gteqv$mcF$sp$(this, x, y);
   }

   public boolean gteqv$mcI$sp(final int x, final int y) {
      return Order.gteqv$mcI$sp$(this, x, y);
   }

   public boolean gteqv$mcJ$sp(final long x, final long y) {
      return Order.gteqv$mcJ$sp$(this, x, y);
   }

   public boolean gteqv$mcS$sp(final short x, final short y) {
      return Order.gteqv$mcS$sp$(this, x, y);
   }

   public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.gteqv$mcV$sp$(this, x, y);
   }

   public boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return Order.gt$mcZ$sp$(this, x, y);
   }

   public boolean gt$mcB$sp(final byte x, final byte y) {
      return Order.gt$mcB$sp$(this, x, y);
   }

   public boolean gt$mcC$sp(final char x, final char y) {
      return Order.gt$mcC$sp$(this, x, y);
   }

   public boolean gt$mcF$sp(final float x, final float y) {
      return Order.gt$mcF$sp$(this, x, y);
   }

   public boolean gt$mcI$sp(final int x, final int y) {
      return Order.gt$mcI$sp$(this, x, y);
   }

   public boolean gt$mcJ$sp(final long x, final long y) {
      return Order.gt$mcJ$sp$(this, x, y);
   }

   public boolean gt$mcS$sp(final short x, final short y) {
      return Order.gt$mcS$sp$(this, x, y);
   }

   public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return Order.gt$mcV$sp$(this, x, y);
   }

   public Ordering toOrdering() {
      return Order.toOrdering$(this);
   }

   public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
   }

   public Option partialComparison$mcB$sp(final byte x, final byte y) {
      return PartialOrder.partialComparison$mcB$sp$(this, x, y);
   }

   public Option partialComparison$mcC$sp(final char x, final char y) {
      return PartialOrder.partialComparison$mcC$sp$(this, x, y);
   }

   public Option partialComparison$mcF$sp(final float x, final float y) {
      return PartialOrder.partialComparison$mcF$sp$(this, x, y);
   }

   public Option partialComparison$mcI$sp(final int x, final int y) {
      return PartialOrder.partialComparison$mcI$sp$(this, x, y);
   }

   public Option partialComparison$mcJ$sp(final long x, final long y) {
      return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
   }

   public Option partialComparison$mcS$sp(final short x, final short y) {
      return PartialOrder.partialComparison$mcS$sp$(this, x, y);
   }

   public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return PartialOrder.partialComparison$mcV$sp$(this, x, y);
   }

   public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
   }

   public Option tryCompare$mcB$sp(final byte x, final byte y) {
      return PartialOrder.tryCompare$mcB$sp$(this, x, y);
   }

   public Option tryCompare$mcC$sp(final char x, final char y) {
      return PartialOrder.tryCompare$mcC$sp$(this, x, y);
   }

   public Option tryCompare$mcF$sp(final float x, final float y) {
      return PartialOrder.tryCompare$mcF$sp$(this, x, y);
   }

   public Option tryCompare$mcI$sp(final int x, final int y) {
      return PartialOrder.tryCompare$mcI$sp$(this, x, y);
   }

   public Option tryCompare$mcJ$sp(final long x, final long y) {
      return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
   }

   public Option tryCompare$mcS$sp(final short x, final short y) {
      return PartialOrder.tryCompare$mcS$sp$(this, x, y);
   }

   public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return PartialOrder.tryCompare$mcV$sp$(this, x, y);
   }

   public Option pmin$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmin$mcZ$sp$(this, x, y);
   }

   public Option pmin$mcB$sp(final byte x, final byte y) {
      return PartialOrder.pmin$mcB$sp$(this, x, y);
   }

   public Option pmin$mcC$sp(final char x, final char y) {
      return PartialOrder.pmin$mcC$sp$(this, x, y);
   }

   public Option pmin$mcF$sp(final float x, final float y) {
      return PartialOrder.pmin$mcF$sp$(this, x, y);
   }

   public Option pmin$mcI$sp(final int x, final int y) {
      return PartialOrder.pmin$mcI$sp$(this, x, y);
   }

   public Option pmin$mcJ$sp(final long x, final long y) {
      return PartialOrder.pmin$mcJ$sp$(this, x, y);
   }

   public Option pmin$mcS$sp(final short x, final short y) {
      return PartialOrder.pmin$mcS$sp$(this, x, y);
   }

   public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return PartialOrder.pmin$mcV$sp$(this, x, y);
   }

   public Option pmax$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmax$mcZ$sp$(this, x, y);
   }

   public Option pmax$mcB$sp(final byte x, final byte y) {
      return PartialOrder.pmax$mcB$sp$(this, x, y);
   }

   public Option pmax$mcC$sp(final char x, final char y) {
      return PartialOrder.pmax$mcC$sp$(this, x, y);
   }

   public Option pmax$mcF$sp(final float x, final float y) {
      return PartialOrder.pmax$mcF$sp$(this, x, y);
   }

   public Option pmax$mcI$sp(final int x, final int y) {
      return PartialOrder.pmax$mcI$sp$(this, x, y);
   }

   public Option pmax$mcJ$sp(final long x, final long y) {
      return PartialOrder.pmax$mcJ$sp$(this, x, y);
   }

   public Option pmax$mcS$sp(final short x, final short y) {
      return PartialOrder.pmax$mcS$sp$(this, x, y);
   }

   public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return PartialOrder.pmax$mcV$sp$(this, x, y);
   }

   public boolean fromByte$mcZ$sp(final byte n) {
      return ConvertableTo.fromByte$mcZ$sp$(this, n);
   }

   public byte fromByte$mcB$sp(final byte n) {
      return ConvertableTo.fromByte$mcB$sp$(this, n);
   }

   public char fromByte$mcC$sp(final byte n) {
      return ConvertableTo.fromByte$mcC$sp$(this, n);
   }

   public float fromByte$mcF$sp(final byte n) {
      return ConvertableTo.fromByte$mcF$sp$(this, n);
   }

   public int fromByte$mcI$sp(final byte n) {
      return ConvertableTo.fromByte$mcI$sp$(this, n);
   }

   public long fromByte$mcJ$sp(final byte n) {
      return ConvertableTo.fromByte$mcJ$sp$(this, n);
   }

   public short fromByte$mcS$sp(final byte n) {
      return ConvertableTo.fromByte$mcS$sp$(this, n);
   }

   public void fromByte$mcV$sp(final byte n) {
      ConvertableTo.fromByte$mcV$sp$(this, n);
   }

   public boolean fromShort$mcZ$sp(final short n) {
      return ConvertableTo.fromShort$mcZ$sp$(this, n);
   }

   public byte fromShort$mcB$sp(final short n) {
      return ConvertableTo.fromShort$mcB$sp$(this, n);
   }

   public char fromShort$mcC$sp(final short n) {
      return ConvertableTo.fromShort$mcC$sp$(this, n);
   }

   public float fromShort$mcF$sp(final short n) {
      return ConvertableTo.fromShort$mcF$sp$(this, n);
   }

   public int fromShort$mcI$sp(final short n) {
      return ConvertableTo.fromShort$mcI$sp$(this, n);
   }

   public long fromShort$mcJ$sp(final short n) {
      return ConvertableTo.fromShort$mcJ$sp$(this, n);
   }

   public short fromShort$mcS$sp(final short n) {
      return ConvertableTo.fromShort$mcS$sp$(this, n);
   }

   public void fromShort$mcV$sp(final short n) {
      ConvertableTo.fromShort$mcV$sp$(this, n);
   }

   public boolean fromInt$mcZ$sp(final int n) {
      return ConvertableTo.fromInt$mcZ$sp$(this, n);
   }

   public byte fromInt$mcB$sp(final int n) {
      return ConvertableTo.fromInt$mcB$sp$(this, n);
   }

   public char fromInt$mcC$sp(final int n) {
      return ConvertableTo.fromInt$mcC$sp$(this, n);
   }

   public float fromInt$mcF$sp(final int n) {
      return ConvertableTo.fromInt$mcF$sp$(this, n);
   }

   public int fromInt$mcI$sp(final int n) {
      return ConvertableTo.fromInt$mcI$sp$(this, n);
   }

   public long fromInt$mcJ$sp(final int n) {
      return ConvertableTo.fromInt$mcJ$sp$(this, n);
   }

   public short fromInt$mcS$sp(final int n) {
      return ConvertableTo.fromInt$mcS$sp$(this, n);
   }

   public void fromInt$mcV$sp(final int n) {
      ConvertableTo.fromInt$mcV$sp$(this, n);
   }

   public boolean fromLong$mcZ$sp(final long n) {
      return ConvertableTo.fromLong$mcZ$sp$(this, n);
   }

   public byte fromLong$mcB$sp(final long n) {
      return ConvertableTo.fromLong$mcB$sp$(this, n);
   }

   public char fromLong$mcC$sp(final long n) {
      return ConvertableTo.fromLong$mcC$sp$(this, n);
   }

   public float fromLong$mcF$sp(final long n) {
      return ConvertableTo.fromLong$mcF$sp$(this, n);
   }

   public int fromLong$mcI$sp(final long n) {
      return ConvertableTo.fromLong$mcI$sp$(this, n);
   }

   public long fromLong$mcJ$sp(final long n) {
      return ConvertableTo.fromLong$mcJ$sp$(this, n);
   }

   public short fromLong$mcS$sp(final long n) {
      return ConvertableTo.fromLong$mcS$sp$(this, n);
   }

   public void fromLong$mcV$sp(final long n) {
      ConvertableTo.fromLong$mcV$sp$(this, n);
   }

   public boolean fromFloat$mcZ$sp(final float n) {
      return ConvertableTo.fromFloat$mcZ$sp$(this, n);
   }

   public byte fromFloat$mcB$sp(final float n) {
      return ConvertableTo.fromFloat$mcB$sp$(this, n);
   }

   public char fromFloat$mcC$sp(final float n) {
      return ConvertableTo.fromFloat$mcC$sp$(this, n);
   }

   public float fromFloat$mcF$sp(final float n) {
      return ConvertableTo.fromFloat$mcF$sp$(this, n);
   }

   public int fromFloat$mcI$sp(final float n) {
      return ConvertableTo.fromFloat$mcI$sp$(this, n);
   }

   public long fromFloat$mcJ$sp(final float n) {
      return ConvertableTo.fromFloat$mcJ$sp$(this, n);
   }

   public short fromFloat$mcS$sp(final float n) {
      return ConvertableTo.fromFloat$mcS$sp$(this, n);
   }

   public void fromFloat$mcV$sp(final float n) {
      ConvertableTo.fromFloat$mcV$sp$(this, n);
   }

   public boolean fromDouble$mcZ$sp(final double n) {
      return ConvertableTo.fromDouble$mcZ$sp$(this, n);
   }

   public char fromDouble$mcC$sp(final double n) {
      return ConvertableTo.fromDouble$mcC$sp$(this, n);
   }

   public void fromDouble$mcV$sp(final double n) {
      ConvertableTo.fromDouble$mcV$sp$(this, n);
   }

   public boolean fromBigInt$mcZ$sp(final BigInt n) {
      return ConvertableTo.fromBigInt$mcZ$sp$(this, n);
   }

   public byte fromBigInt$mcB$sp(final BigInt n) {
      return ConvertableTo.fromBigInt$mcB$sp$(this, n);
   }

   public char fromBigInt$mcC$sp(final BigInt n) {
      return ConvertableTo.fromBigInt$mcC$sp$(this, n);
   }

   public float fromBigInt$mcF$sp(final BigInt n) {
      return ConvertableTo.fromBigInt$mcF$sp$(this, n);
   }

   public int fromBigInt$mcI$sp(final BigInt n) {
      return ConvertableTo.fromBigInt$mcI$sp$(this, n);
   }

   public long fromBigInt$mcJ$sp(final BigInt n) {
      return ConvertableTo.fromBigInt$mcJ$sp$(this, n);
   }

   public short fromBigInt$mcS$sp(final BigInt n) {
      return ConvertableTo.fromBigInt$mcS$sp$(this, n);
   }

   public void fromBigInt$mcV$sp(final BigInt n) {
      ConvertableTo.fromBigInt$mcV$sp$(this, n);
   }

   public boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
      return ConvertableTo.fromBigDecimal$mcZ$sp$(this, n);
   }

   public byte fromBigDecimal$mcB$sp(final BigDecimal n) {
      return ConvertableTo.fromBigDecimal$mcB$sp$(this, n);
   }

   public char fromBigDecimal$mcC$sp(final BigDecimal n) {
      return ConvertableTo.fromBigDecimal$mcC$sp$(this, n);
   }

   public float fromBigDecimal$mcF$sp(final BigDecimal n) {
      return ConvertableTo.fromBigDecimal$mcF$sp$(this, n);
   }

   public int fromBigDecimal$mcI$sp(final BigDecimal n) {
      return ConvertableTo.fromBigDecimal$mcI$sp$(this, n);
   }

   public long fromBigDecimal$mcJ$sp(final BigDecimal n) {
      return ConvertableTo.fromBigDecimal$mcJ$sp$(this, n);
   }

   public short fromBigDecimal$mcS$sp(final BigDecimal n) {
      return ConvertableTo.fromBigDecimal$mcS$sp$(this, n);
   }

   public void fromBigDecimal$mcV$sp(final BigDecimal n) {
      ConvertableTo.fromBigDecimal$mcV$sp$(this, n);
   }

   public boolean fromRational$mcZ$sp(final Rational n) {
      return ConvertableTo.fromRational$mcZ$sp$(this, n);
   }

   public byte fromRational$mcB$sp(final Rational n) {
      return ConvertableTo.fromRational$mcB$sp$(this, n);
   }

   public char fromRational$mcC$sp(final Rational n) {
      return ConvertableTo.fromRational$mcC$sp$(this, n);
   }

   public float fromRational$mcF$sp(final Rational n) {
      return ConvertableTo.fromRational$mcF$sp$(this, n);
   }

   public int fromRational$mcI$sp(final Rational n) {
      return ConvertableTo.fromRational$mcI$sp$(this, n);
   }

   public long fromRational$mcJ$sp(final Rational n) {
      return ConvertableTo.fromRational$mcJ$sp$(this, n);
   }

   public short fromRational$mcS$sp(final Rational n) {
      return ConvertableTo.fromRational$mcS$sp$(this, n);
   }

   public void fromRational$mcV$sp(final Rational n) {
      ConvertableTo.fromRational$mcV$sp$(this, n);
   }

   public boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
      return ConvertableTo.fromAlgebraic$mcZ$sp$(this, n);
   }

   public byte fromAlgebraic$mcB$sp(final Algebraic n) {
      return ConvertableTo.fromAlgebraic$mcB$sp$(this, n);
   }

   public char fromAlgebraic$mcC$sp(final Algebraic n) {
      return ConvertableTo.fromAlgebraic$mcC$sp$(this, n);
   }

   public float fromAlgebraic$mcF$sp(final Algebraic n) {
      return ConvertableTo.fromAlgebraic$mcF$sp$(this, n);
   }

   public int fromAlgebraic$mcI$sp(final Algebraic n) {
      return ConvertableTo.fromAlgebraic$mcI$sp$(this, n);
   }

   public long fromAlgebraic$mcJ$sp(final Algebraic n) {
      return ConvertableTo.fromAlgebraic$mcJ$sp$(this, n);
   }

   public short fromAlgebraic$mcS$sp(final Algebraic n) {
      return ConvertableTo.fromAlgebraic$mcS$sp$(this, n);
   }

   public void fromAlgebraic$mcV$sp(final Algebraic n) {
      ConvertableTo.fromAlgebraic$mcV$sp$(this, n);
   }

   public boolean fromReal$mcZ$sp(final Real n) {
      return ConvertableTo.fromReal$mcZ$sp$(this, n);
   }

   public byte fromReal$mcB$sp(final Real n) {
      return ConvertableTo.fromReal$mcB$sp$(this, n);
   }

   public char fromReal$mcC$sp(final Real n) {
      return ConvertableTo.fromReal$mcC$sp$(this, n);
   }

   public float fromReal$mcF$sp(final Real n) {
      return ConvertableTo.fromReal$mcF$sp$(this, n);
   }

   public int fromReal$mcI$sp(final Real n) {
      return ConvertableTo.fromReal$mcI$sp$(this, n);
   }

   public long fromReal$mcJ$sp(final Real n) {
      return ConvertableTo.fromReal$mcJ$sp$(this, n);
   }

   public short fromReal$mcS$sp(final Real n) {
      return ConvertableTo.fromReal$mcS$sp$(this, n);
   }

   public void fromReal$mcV$sp(final Real n) {
      ConvertableTo.fromReal$mcV$sp$(this, n);
   }

   public boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
      return ConvertableTo.fromType$mcZ$sp$(this, b, evidence$1);
   }

   public byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
      return ConvertableTo.fromType$mcB$sp$(this, b, evidence$1);
   }

   public char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
      return ConvertableTo.fromType$mcC$sp$(this, b, evidence$1);
   }

   public float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
      return ConvertableTo.fromType$mcF$sp$(this, b, evidence$1);
   }

   public int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
      return ConvertableTo.fromType$mcI$sp$(this, b, evidence$1);
   }

   public long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
      return ConvertableTo.fromType$mcJ$sp$(this, b, evidence$1);
   }

   public short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
      return ConvertableTo.fromType$mcS$sp$(this, b, evidence$1);
   }

   public void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
      ConvertableTo.fromType$mcV$sp$(this, b, evidence$1);
   }

   public byte toByte$mcZ$sp(final boolean a) {
      return ConvertableFrom.toByte$mcZ$sp$(this, a);
   }

   public byte toByte$mcB$sp(final byte a) {
      return ConvertableFrom.toByte$mcB$sp$(this, a);
   }

   public byte toByte$mcC$sp(final char a) {
      return ConvertableFrom.toByte$mcC$sp$(this, a);
   }

   public byte toByte$mcF$sp(final float a) {
      return ConvertableFrom.toByte$mcF$sp$(this, a);
   }

   public byte toByte$mcI$sp(final int a) {
      return ConvertableFrom.toByte$mcI$sp$(this, a);
   }

   public byte toByte$mcJ$sp(final long a) {
      return ConvertableFrom.toByte$mcJ$sp$(this, a);
   }

   public byte toByte$mcS$sp(final short a) {
      return ConvertableFrom.toByte$mcS$sp$(this, a);
   }

   public byte toByte$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toByte$mcV$sp$(this, a);
   }

   public short toShort$mcZ$sp(final boolean a) {
      return ConvertableFrom.toShort$mcZ$sp$(this, a);
   }

   public short toShort$mcB$sp(final byte a) {
      return ConvertableFrom.toShort$mcB$sp$(this, a);
   }

   public short toShort$mcC$sp(final char a) {
      return ConvertableFrom.toShort$mcC$sp$(this, a);
   }

   public short toShort$mcF$sp(final float a) {
      return ConvertableFrom.toShort$mcF$sp$(this, a);
   }

   public short toShort$mcI$sp(final int a) {
      return ConvertableFrom.toShort$mcI$sp$(this, a);
   }

   public short toShort$mcJ$sp(final long a) {
      return ConvertableFrom.toShort$mcJ$sp$(this, a);
   }

   public short toShort$mcS$sp(final short a) {
      return ConvertableFrom.toShort$mcS$sp$(this, a);
   }

   public short toShort$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toShort$mcV$sp$(this, a);
   }

   public int toInt$mcZ$sp(final boolean a) {
      return ConvertableFrom.toInt$mcZ$sp$(this, a);
   }

   public int toInt$mcB$sp(final byte a) {
      return ConvertableFrom.toInt$mcB$sp$(this, a);
   }

   public int toInt$mcC$sp(final char a) {
      return ConvertableFrom.toInt$mcC$sp$(this, a);
   }

   public int toInt$mcF$sp(final float a) {
      return ConvertableFrom.toInt$mcF$sp$(this, a);
   }

   public int toInt$mcI$sp(final int a) {
      return ConvertableFrom.toInt$mcI$sp$(this, a);
   }

   public int toInt$mcJ$sp(final long a) {
      return ConvertableFrom.toInt$mcJ$sp$(this, a);
   }

   public int toInt$mcS$sp(final short a) {
      return ConvertableFrom.toInt$mcS$sp$(this, a);
   }

   public int toInt$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toInt$mcV$sp$(this, a);
   }

   public long toLong$mcZ$sp(final boolean a) {
      return ConvertableFrom.toLong$mcZ$sp$(this, a);
   }

   public long toLong$mcB$sp(final byte a) {
      return ConvertableFrom.toLong$mcB$sp$(this, a);
   }

   public long toLong$mcC$sp(final char a) {
      return ConvertableFrom.toLong$mcC$sp$(this, a);
   }

   public long toLong$mcF$sp(final float a) {
      return ConvertableFrom.toLong$mcF$sp$(this, a);
   }

   public long toLong$mcI$sp(final int a) {
      return ConvertableFrom.toLong$mcI$sp$(this, a);
   }

   public long toLong$mcJ$sp(final long a) {
      return ConvertableFrom.toLong$mcJ$sp$(this, a);
   }

   public long toLong$mcS$sp(final short a) {
      return ConvertableFrom.toLong$mcS$sp$(this, a);
   }

   public long toLong$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toLong$mcV$sp$(this, a);
   }

   public float toFloat$mcZ$sp(final boolean a) {
      return ConvertableFrom.toFloat$mcZ$sp$(this, a);
   }

   public float toFloat$mcB$sp(final byte a) {
      return ConvertableFrom.toFloat$mcB$sp$(this, a);
   }

   public float toFloat$mcC$sp(final char a) {
      return ConvertableFrom.toFloat$mcC$sp$(this, a);
   }

   public float toFloat$mcF$sp(final float a) {
      return ConvertableFrom.toFloat$mcF$sp$(this, a);
   }

   public float toFloat$mcI$sp(final int a) {
      return ConvertableFrom.toFloat$mcI$sp$(this, a);
   }

   public float toFloat$mcJ$sp(final long a) {
      return ConvertableFrom.toFloat$mcJ$sp$(this, a);
   }

   public float toFloat$mcS$sp(final short a) {
      return ConvertableFrom.toFloat$mcS$sp$(this, a);
   }

   public float toFloat$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toFloat$mcV$sp$(this, a);
   }

   public BigInt toBigInt$mcZ$sp(final boolean a) {
      return ConvertableFrom.toBigInt$mcZ$sp$(this, a);
   }

   public BigInt toBigInt$mcB$sp(final byte a) {
      return ConvertableFrom.toBigInt$mcB$sp$(this, a);
   }

   public BigInt toBigInt$mcC$sp(final char a) {
      return ConvertableFrom.toBigInt$mcC$sp$(this, a);
   }

   public BigInt toBigInt$mcF$sp(final float a) {
      return ConvertableFrom.toBigInt$mcF$sp$(this, a);
   }

   public BigInt toBigInt$mcI$sp(final int a) {
      return ConvertableFrom.toBigInt$mcI$sp$(this, a);
   }

   public BigInt toBigInt$mcJ$sp(final long a) {
      return ConvertableFrom.toBigInt$mcJ$sp$(this, a);
   }

   public BigInt toBigInt$mcS$sp(final short a) {
      return ConvertableFrom.toBigInt$mcS$sp$(this, a);
   }

   public BigInt toBigInt$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toBigInt$mcV$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcZ$sp(final boolean a) {
      return ConvertableFrom.toBigDecimal$mcZ$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcB$sp(final byte a) {
      return ConvertableFrom.toBigDecimal$mcB$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcC$sp(final char a) {
      return ConvertableFrom.toBigDecimal$mcC$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcF$sp(final float a) {
      return ConvertableFrom.toBigDecimal$mcF$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcI$sp(final int a) {
      return ConvertableFrom.toBigDecimal$mcI$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcJ$sp(final long a) {
      return ConvertableFrom.toBigDecimal$mcJ$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcS$sp(final short a) {
      return ConvertableFrom.toBigDecimal$mcS$sp$(this, a);
   }

   public BigDecimal toBigDecimal$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toBigDecimal$mcV$sp$(this, a);
   }

   public Rational toRational$mcZ$sp(final boolean a) {
      return ConvertableFrom.toRational$mcZ$sp$(this, a);
   }

   public Rational toRational$mcB$sp(final byte a) {
      return ConvertableFrom.toRational$mcB$sp$(this, a);
   }

   public Rational toRational$mcC$sp(final char a) {
      return ConvertableFrom.toRational$mcC$sp$(this, a);
   }

   public Rational toRational$mcF$sp(final float a) {
      return ConvertableFrom.toRational$mcF$sp$(this, a);
   }

   public Rational toRational$mcI$sp(final int a) {
      return ConvertableFrom.toRational$mcI$sp$(this, a);
   }

   public Rational toRational$mcJ$sp(final long a) {
      return ConvertableFrom.toRational$mcJ$sp$(this, a);
   }

   public Rational toRational$mcS$sp(final short a) {
      return ConvertableFrom.toRational$mcS$sp$(this, a);
   }

   public Rational toRational$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toRational$mcV$sp$(this, a);
   }

   public Number toNumber$mcZ$sp(final boolean a) {
      return ConvertableFrom.toNumber$mcZ$sp$(this, a);
   }

   public Number toNumber$mcB$sp(final byte a) {
      return ConvertableFrom.toNumber$mcB$sp$(this, a);
   }

   public Number toNumber$mcC$sp(final char a) {
      return ConvertableFrom.toNumber$mcC$sp$(this, a);
   }

   public Number toNumber$mcF$sp(final float a) {
      return ConvertableFrom.toNumber$mcF$sp$(this, a);
   }

   public Number toNumber$mcI$sp(final int a) {
      return ConvertableFrom.toNumber$mcI$sp$(this, a);
   }

   public Number toNumber$mcJ$sp(final long a) {
      return ConvertableFrom.toNumber$mcJ$sp$(this, a);
   }

   public Number toNumber$mcS$sp(final short a) {
      return ConvertableFrom.toNumber$mcS$sp$(this, a);
   }

   public Number toNumber$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toNumber$mcV$sp$(this, a);
   }

   public Object toType$mcZ$sp(final boolean a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcZ$sp$(this, a, evidence$17);
   }

   public Object toType$mcB$sp(final byte a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcB$sp$(this, a, evidence$17);
   }

   public Object toType$mcC$sp(final char a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcC$sp$(this, a, evidence$17);
   }

   public Object toType$mcF$sp(final float a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcF$sp$(this, a, evidence$17);
   }

   public Object toType$mcI$sp(final int a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcI$sp$(this, a, evidence$17);
   }

   public Object toType$mcJ$sp(final long a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcJ$sp$(this, a, evidence$17);
   }

   public Object toType$mcS$sp(final short a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcS$sp$(this, a, evidence$17);
   }

   public Object toType$mcV$sp(final BoxedUnit a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcV$sp$(this, a, evidence$17);
   }

   public String toString$mcZ$sp(final boolean a) {
      return ConvertableFrom.toString$mcZ$sp$(this, a);
   }

   public String toString$mcB$sp(final byte a) {
      return ConvertableFrom.toString$mcB$sp$(this, a);
   }

   public String toString$mcC$sp(final char a) {
      return ConvertableFrom.toString$mcC$sp$(this, a);
   }

   public String toString$mcF$sp(final float a) {
      return ConvertableFrom.toString$mcF$sp$(this, a);
   }

   public String toString$mcI$sp(final int a) {
      return ConvertableFrom.toString$mcI$sp$(this, a);
   }

   public String toString$mcJ$sp(final long a) {
      return ConvertableFrom.toString$mcJ$sp$(this, a);
   }

   public String toString$mcS$sp(final short a) {
      return ConvertableFrom.toString$mcS$sp$(this, a);
   }

   public String toString$mcV$sp(final BoxedUnit a) {
      return ConvertableFrom.toString$mcV$sp$(this, a);
   }

   public float nroot$mcF$sp(final float a, final int n) {
      return NRoot.nroot$mcF$sp$(this, a, n);
   }

   public int nroot$mcI$sp(final int a, final int n) {
      return NRoot.nroot$mcI$sp$(this, a, n);
   }

   public long nroot$mcJ$sp(final long a, final int n) {
      return NRoot.nroot$mcJ$sp$(this, a, n);
   }

   public float sqrt$mcF$sp(final float a) {
      return NRoot.sqrt$mcF$sp$(this, a);
   }

   public int sqrt$mcI$sp(final int a) {
      return NRoot.sqrt$mcI$sp$(this, a);
   }

   public long sqrt$mcJ$sp(final long a) {
      return NRoot.sqrt$mcJ$sp$(this, a);
   }

   public float fpow$mcF$sp(final float a, final float b) {
      return NRoot.fpow$mcF$sp$(this, a, b);
   }

   public int fpow$mcI$sp(final int a, final int b) {
      return NRoot.fpow$mcI$sp$(this, a, b);
   }

   public long fpow$mcJ$sp(final long a, final long b) {
      return NRoot.fpow$mcJ$sp$(this, a, b);
   }

   public CommutativeGroup multiplicative$mcF$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcF$sp$(this);
   }

   public CommutativeGroup multiplicative$mcI$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcI$sp$(this);
   }

   public CommutativeGroup multiplicative$mcJ$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcJ$sp$(this);
   }

   public float reciprocal$mcF$sp(final float x) {
      return MultiplicativeGroup.reciprocal$mcF$sp$(this, x);
   }

   public int reciprocal$mcI$sp(final int x) {
      return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
   }

   public long reciprocal$mcJ$sp(final long x) {
      return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
   }

   public float div$mcF$sp(final float x, final float y) {
      return MultiplicativeGroup.div$mcF$sp$(this, x, y);
   }

   public int div$mcI$sp(final int x, final int y) {
      return MultiplicativeGroup.div$mcI$sp$(this, x, y);
   }

   public long div$mcJ$sp(final long x, final long y) {
      return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
   }

   public float pow$mcF$sp(final float a, final int n) {
      return MultiplicativeGroup.pow$mcF$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
   }

   public CommutativeGroup additive$mcF$sp() {
      return AdditiveCommutativeGroup.additive$mcF$sp$(this);
   }

   public CommutativeGroup additive$mcI$sp() {
      return AdditiveCommutativeGroup.additive$mcI$sp$(this);
   }

   public CommutativeGroup additive$mcJ$sp() {
      return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
   }

   public float negate$mcF$sp(final float x) {
      return AdditiveGroup.negate$mcF$sp$(this, x);
   }

   public int negate$mcI$sp(final int x) {
      return AdditiveGroup.negate$mcI$sp$(this, x);
   }

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
   }

   public float minus$mcF$sp(final float x, final float y) {
      return AdditiveGroup.minus$mcF$sp$(this, x, y);
   }

   public int minus$mcI$sp(final int x, final int y) {
      return AdditiveGroup.minus$mcI$sp$(this, x, y);
   }

   public long minus$mcJ$sp(final long x, final long y) {
      return AdditiveGroup.minus$mcJ$sp$(this, x, y);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveGroup.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveGroup.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
   }

   public float one$mcF$sp() {
      return MultiplicativeMonoid.one$mcF$sp$(this);
   }

   public int one$mcI$sp() {
      return MultiplicativeMonoid.one$mcI$sp$(this);
   }

   public long one$mcJ$sp() {
      return MultiplicativeMonoid.one$mcJ$sp$(this);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
   }

   public float product$mcF$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcF$sp$(this, as);
   }

   public int product$mcI$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcI$sp$(this, as);
   }

   public long product$mcJ$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcJ$sp$(this, as);
   }

   public Option tryProduct(final IterableOnce as) {
      return MultiplicativeMonoid.tryProduct$(this, as);
   }

   public float times$mcF$sp(final float x, final float y) {
      return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
   }

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
   }

   public int positivePow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
   }

   public long positivePow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
   }

   public float zero$mcF$sp() {
      return AdditiveMonoid.zero$mcF$sp$(this);
   }

   public int zero$mcI$sp() {
      return AdditiveMonoid.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcF$sp$(this, as);
   }

   public int sum$mcI$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcI$sp$(this, as);
   }

   public long sum$mcJ$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcJ$sp$(this, as);
   }

   public Option trySum(final IterableOnce as) {
      return AdditiveMonoid.trySum$(this, as);
   }

   public float plus$mcF$sp(final float x, final float y) {
      return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
   }

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public double fromInt(final int n) {
      return this.fromInt$mcD$sp(n);
   }

   public double fromDouble(final double n) {
      return this.fromDouble$mcD$sp(n);
   }

   public double fromBigInt(final BigInt n) {
      return this.fromBigInt$mcD$sp(n);
   }

   public double toDouble(final double n) {
      return this.toDouble$mcD$sp(n);
   }

   public Rational toRational(final double n) {
      return this.toRational$mcD$sp(n);
   }

   public Algebraic toAlgebraic(final double n) {
      return this.toAlgebraic$mcD$sp(n);
   }

   public Real toReal(final double n) {
      return this.toReal$mcD$sp(n);
   }

   public double fromInt$mcD$sp(final int n) {
      return (double)n;
   }

   public double fromDouble$mcD$sp(final double n) {
      return n;
   }

   public double fromBigInt$mcD$sp(final BigInt n) {
      return n.toDouble();
   }

   public double toDouble$mcD$sp(final double n) {
      return n;
   }

   public Rational toRational$mcD$sp(final double n) {
      return DoubleIsReal.toRational$(this, n);
   }

   public Algebraic toAlgebraic$mcD$sp(final double n) {
      return IsRational.toAlgebraic$(this, BoxesRunTime.boxToDouble(n));
   }

   public Real toReal$mcD$sp(final double n) {
      return IsAlgebraic.toReal$(this, BoxesRunTime.boxToDouble(n));
   }

   public DoubleIsNumeric() {
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      MultiplicativeSemigroup.$init$(this);
      MultiplicativeMonoid.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      Ring.$init$(this);
      MultiplicativeGroup.$init$(this);
      MultiplicativeCommutativeSemigroup.$init$(this);
      MultiplicativeCommutativeMonoid.$init$(this);
      MultiplicativeCommutativeGroup.$init$(this);
      NRoot.$init$(this);
      Eq.$init$(this);
      PartialOrder.$init$(this);
      Order.$init$(this);
      Signed.$init$(this);
      EuclideanRing.$init$(this);
      DivisionRing.$init$(this);
      Field.$init$(this);
      DoubleIsField.$init$(this);
      DoubleIsNRoot.$init$(this);
      ConvertableFromDouble.$init$(this);
      ConvertableToDouble.$init$(this);
      IsAlgebraic.$init$(this);
      IsRational.$init$(this);
      TruncatedDivision.$init$(this);
      forAdditiveCommutativeMonoid.$init$(this);
      forAdditiveCommutativeGroup.$init$(this);
      forCommutativeRing.$init$(this);
      DoubleOrder.$init$(this);
      DoubleSigned.$init$(this);
      DoubleTruncatedDivision.$init$(this);
      DoubleIsReal.$init$(this);
   }
}
