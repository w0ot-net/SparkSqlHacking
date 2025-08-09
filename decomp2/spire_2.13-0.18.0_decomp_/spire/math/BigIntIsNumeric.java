package spire.math;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.EuclideanRing;
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
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;
import spire.algebra.IsAlgebraic;
import spire.algebra.IsIntegral;
import spire.algebra.IsRational;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.std.BigIntIsEuclideanRing;
import spire.std.BigIntIsNRoot;
import spire.std.BigIntIsReal;
import spire.std.BigIntOrder;
import spire.std.BigIntSigned;
import spire.std.BigIntTruncatedDivision;

@ScalaSignature(
   bytes = "\u0006\u0005Q4Qa\u0003\u0007\u0001\u0019AAQ!\u0010\u0001\u0005\u0002yBQ\u0001\u0011\u0001\u0005B\u0005CQa\u0012\u0001\u0005B!CQ!\u0014\u0001\u0005B9CQ\u0001\u0015\u0001\u0005BECQa\u0015\u0001\u0005BQCQ!\u0017\u0001\u0005BiCQa\u0018\u0001\u0005B\u0001DQ!\u001a\u0001\u0005B\u0019DQ\u0001\u001b\u0001\u0005\u0002%\u0014qBQ5h\u0013:$\u0018j\u001d(v[\u0016\u0014\u0018n\u0019\u0006\u0003\u001b9\tA!\\1uQ*\tq\"A\u0003ta&\u0014XmE\u0005\u0001#]Ac&\r\u001b8uA\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u00042\u0001G\r\u001c\u001b\u0005a\u0011B\u0001\u000e\r\u0005\u001dqU/\\3sS\u000e\u0004\"\u0001H\u0013\u000f\u0005u\u0019cB\u0001\u0010#\u001b\u0005y\"B\u0001\u0011\"\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u000b\n\u0005\u0011\u001a\u0012a\u00029bG.\fw-Z\u0005\u0003M\u001d\u0012aAQ5h\u0013:$(B\u0001\u0013\u0014!\tIC&D\u0001+\u0015\tYc\"A\u0002ti\u0012L!!\f\u0016\u0003+\tKw-\u00138u\u0013N,Uo\u00197jI\u0016\fgNU5oOB\u0011\u0011fL\u0005\u0003a)\u0012QBQ5h\u0013:$\u0018j\u001d(S_>$\bC\u0001\r3\u0013\t\u0019DBA\u000bD_:4XM\u001d;bE2,gI]8n\u0005&<\u0017J\u001c;\u0011\u0005a)\u0014B\u0001\u001c\r\u0005M\u0019uN\u001c<feR\f'\r\\3U_\nKw-\u00138u!\tI\u0003(\u0003\u0002:U\ta!)[4J]RL5OU3bYB\u0011AdO\u0005\u0003y\u001d\u0012AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#A \u0011\u0005a\u0001\u0011a\u00024s_6Le\u000e\u001e\u000b\u00037\tCQa\u0011\u0002A\u0002\u0011\u000b\u0011A\u001c\t\u0003%\u0015K!AR\n\u0003\u0007%sG/\u0001\u0006ge>lGi\\;cY\u0016$\"aG%\t\u000b\r\u001b\u0001\u0019\u0001&\u0011\u0005IY\u0015B\u0001'\u0014\u0005\u0019!u.\u001e2mK\u0006QaM]8n\u0005&<\u0017J\u001c;\u0015\u0005my\u0005\"B\"\u0005\u0001\u0004Y\u0012\u0001\u0003;p\t>,(\r\\3\u0015\u0005)\u0013\u0006\"B\"\u0006\u0001\u0004Y\u0012A\u0003;p%\u0006$\u0018n\u001c8bYR\u0011Q\u000b\u0017\t\u00031YK!a\u0016\u0007\u0003\u0011I\u000bG/[8oC2DQa\u0011\u0004A\u0002m\t1\u0002^8BY\u001e,'M]1jGR\u00111L\u0018\t\u00031qK!!\u0018\u0007\u0003\u0013\u0005cw-\u001a2sC&\u001c\u0007\"B\"\b\u0001\u0004Y\u0012A\u0002;p%\u0016\fG\u000e\u0006\u0002bIB\u0011\u0001DY\u0005\u0003G2\u0011AAU3bY\")1\t\u0003a\u00017\u0005AAo\u001c\"jO&sG\u000f\u0006\u0002\u001cO\")1)\u0003a\u00017\u0005\u0019A-\u001b<\u0015\u0007mQG\u000eC\u0003l\u0015\u0001\u00071$A\u0001b\u0011\u0015i'\u00021\u0001\u001c\u0003\u0005\u0011\u0007\u0006\u0002\u0001peN\u0004\"A\u00059\n\u0005E\u001c\"\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0001\u0001"
)
public class BigIntIsNumeric implements Numeric, BigIntIsEuclideanRing, BigIntIsNRoot, ConvertableFromBigInt, ConvertableToBigInt, BigIntIsReal {
   private static final long serialVersionUID = 0L;
   private BigInt one;
   private BigInt zero;

   public BigInt toBigIntOpt(final BigInt x) {
      return BigIntTruncatedDivision.toBigIntOpt$(this, x);
   }

   public BigInt tquot(final BigInt a, final BigInt b) {
      return BigIntTruncatedDivision.tquot$(this, a, b);
   }

   public BigInt tmod(final BigInt a, final BigInt b) {
      return BigIntTruncatedDivision.tmod$(this, a, b);
   }

   public Tuple2 tquotmod(final BigInt a, final BigInt b) {
      return BigIntTruncatedDivision.tquotmod$(this, a, b);
   }

   public BigIntSigned order() {
      return BigIntSigned.order$(this);
   }

   public int signum(final BigInt a) {
      return BigIntSigned.signum$(this, a);
   }

   public BigInt abs(final BigInt a) {
      return BigIntSigned.abs$(this, a);
   }

   public boolean eqv(final BigInt x, final BigInt y) {
      return BigIntOrder.eqv$(this, x, y);
   }

   public boolean neqv(final BigInt x, final BigInt y) {
      return BigIntOrder.neqv$(this, x, y);
   }

   public boolean gt(final BigInt x, final BigInt y) {
      return BigIntOrder.gt$(this, x, y);
   }

   public boolean gteqv(final BigInt x, final BigInt y) {
      return BigIntOrder.gteqv$(this, x, y);
   }

   public boolean lt(final BigInt x, final BigInt y) {
      return BigIntOrder.lt$(this, x, y);
   }

   public boolean lteqv(final BigInt x, final BigInt y) {
      return BigIntOrder.lteqv$(this, x, y);
   }

   public BigInt min(final BigInt x, final BigInt y) {
      return BigIntOrder.min$(this, x, y);
   }

   public BigInt max(final BigInt x, final BigInt y) {
      return BigIntOrder.max$(this, x, y);
   }

   public int compare(final BigInt x, final BigInt y) {
      return BigIntOrder.compare$(this, x, y);
   }

   public Object fmod(final Object x, final Object y) {
      return forCommutativeRing.fmod$(this, x, y);
   }

   public byte fmod$mcB$sp(final byte x, final byte y) {
      return forCommutativeRing.fmod$mcB$sp$(this, x, y);
   }

   public double fmod$mcD$sp(final double x, final double y) {
      return forCommutativeRing.fmod$mcD$sp$(this, x, y);
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

   public Object fquot(final Object x, final Object y) {
      return forCommutativeRing.fquot$(this, x, y);
   }

   public byte fquot$mcB$sp(final byte x, final byte y) {
      return forCommutativeRing.fquot$mcB$sp$(this, x, y);
   }

   public double fquot$mcD$sp(final double x, final double y) {
      return forCommutativeRing.fquot$mcD$sp$(this, x, y);
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

   public Tuple2 fquotmod(final Object x, final Object y) {
      return forCommutativeRing.fquotmod$(this, x, y);
   }

   public Tuple2 fquotmod$mcB$sp(final byte x, final byte y) {
      return forCommutativeRing.fquotmod$mcB$sp$(this, x, y);
   }

   public Tuple2 fquotmod$mcD$sp(final double x, final double y) {
      return forCommutativeRing.fquotmod$mcD$sp$(this, x, y);
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

   public Object ceil(final Object a) {
      return IsIntegral.ceil$(this, a);
   }

   public Object floor(final Object a) {
      return IsIntegral.floor$(this, a);
   }

   public Object round(final Object a) {
      return IsIntegral.round$(this, a);
   }

   public boolean isWhole(final Object a) {
      return IsIntegral.isWhole$(this, a);
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

   public Algebraic toAlgebraic$mcD$sp(final double a) {
      return IsAlgebraic.toAlgebraic$mcD$sp$(this, a);
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

   public Real toReal$mcD$sp(final double a) {
      return IsAlgebraic.toReal$mcD$sp$(this, a);
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

   public BigInt fromByte(final byte a) {
      return ConvertableToBigInt.fromByte$(this, a);
   }

   public BigInt fromShort(final short a) {
      return ConvertableToBigInt.fromShort$(this, a);
   }

   public BigInt fromLong(final long a) {
      return ConvertableToBigInt.fromLong$(this, a);
   }

   public BigInt fromFloat(final float a) {
      return ConvertableToBigInt.fromFloat$(this, a);
   }

   public BigInt fromBigDecimal(final BigDecimal a) {
      return ConvertableToBigInt.fromBigDecimal$(this, a);
   }

   public BigInt fromRational(final Rational a) {
      return ConvertableToBigInt.fromRational$(this, a);
   }

   public BigInt fromAlgebraic(final Algebraic a) {
      return ConvertableToBigInt.fromAlgebraic$(this, a);
   }

   public BigInt fromReal(final Real a) {
      return ConvertableToBigInt.fromReal$(this, a);
   }

   public BigInt fromType(final Object b, final ConvertableFrom evidence$8) {
      return ConvertableToBigInt.fromType$(this, b, evidence$8);
   }

   public byte toByte(final BigInt a) {
      return ConvertableFromBigInt.toByte$(this, a);
   }

   public short toShort(final BigInt a) {
      return ConvertableFromBigInt.toShort$(this, a);
   }

   public int toInt(final BigInt a) {
      return ConvertableFromBigInt.toInt$(this, a);
   }

   public long toLong(final BigInt a) {
      return ConvertableFromBigInt.toLong$(this, a);
   }

   public float toFloat(final BigInt a) {
      return ConvertableFromBigInt.toFloat$(this, a);
   }

   public BigDecimal toBigDecimal(final BigInt a) {
      return ConvertableFromBigInt.toBigDecimal$(this, a);
   }

   public Number toNumber(final BigInt a) {
      return ConvertableFromBigInt.toNumber$(this, a);
   }

   public Object toType(final BigInt a, final ConvertableTo evidence$24) {
      return ConvertableFromBigInt.toType$(this, a, evidence$24);
   }

   public String toString(final BigInt a) {
      return ConvertableFromBigInt.toString$(this, a);
   }

   public BigInt nroot(final BigInt a, final int k) {
      return BigIntIsNRoot.nroot$(this, a, k);
   }

   public BigInt fpow(final BigInt a, final BigInt b) {
      return BigIntIsNRoot.fpow$(this, a, b);
   }

   public BigInt minus(final BigInt a, final BigInt b) {
      return BigIntIsEuclideanRing.minus$(this, a, b);
   }

   public BigInt negate(final BigInt a) {
      return BigIntIsEuclideanRing.negate$(this, a);
   }

   public BigInt plus(final BigInt a, final BigInt b) {
      return BigIntIsEuclideanRing.plus$(this, a, b);
   }

   public BigInt pow(final BigInt a, final int b) {
      return BigIntIsEuclideanRing.pow$(this, a, b);
   }

   public BigInt times(final BigInt a, final BigInt b) {
      return BigIntIsEuclideanRing.times$(this, a, b);
   }

   public BigInt euclideanFunction(final BigInt a) {
      return BigIntIsEuclideanRing.euclideanFunction$(this, a);
   }

   public Tuple2 equotmod(final BigInt a, final BigInt b) {
      return BigIntIsEuclideanRing.equotmod$(this, a, b);
   }

   public BigInt equot(final BigInt a, final BigInt b) {
      return BigIntIsEuclideanRing.equot$(this, a, b);
   }

   public BigInt emod(final BigInt a, final BigInt b) {
      return BigIntIsEuclideanRing.emod$(this, a, b);
   }

   public BigInt lcm(final BigInt a, final BigInt b, final Eq ev) {
      return BigIntIsEuclideanRing.lcm$(this, a, b, ev);
   }

   public BigInt gcd(final BigInt a, final BigInt b, final Eq ev) {
      return BigIntIsEuclideanRing.gcd$(this, a, b, ev);
   }

   public BigInt euclideanFunction$mcD$sp(final double a) {
      return EuclideanRing.euclideanFunction$mcD$sp$(this, a);
   }

   public BigInt euclideanFunction$mcF$sp(final float a) {
      return EuclideanRing.euclideanFunction$mcF$sp$(this, a);
   }

   public BigInt euclideanFunction$mcI$sp(final int a) {
      return EuclideanRing.euclideanFunction$mcI$sp$(this, a);
   }

   public BigInt euclideanFunction$mcJ$sp(final long a) {
      return EuclideanRing.euclideanFunction$mcJ$sp$(this, a);
   }

   public double equot$mcD$sp(final double a, final double b) {
      return EuclideanRing.equot$mcD$sp$(this, a, b);
   }

   public float equot$mcF$sp(final float a, final float b) {
      return EuclideanRing.equot$mcF$sp$(this, a, b);
   }

   public int equot$mcI$sp(final int a, final int b) {
      return EuclideanRing.equot$mcI$sp$(this, a, b);
   }

   public long equot$mcJ$sp(final long a, final long b) {
      return EuclideanRing.equot$mcJ$sp$(this, a, b);
   }

   public double emod$mcD$sp(final double a, final double b) {
      return EuclideanRing.emod$mcD$sp$(this, a, b);
   }

   public float emod$mcF$sp(final float a, final float b) {
      return EuclideanRing.emod$mcF$sp$(this, a, b);
   }

   public int emod$mcI$sp(final int a, final int b) {
      return EuclideanRing.emod$mcI$sp$(this, a, b);
   }

   public long emod$mcJ$sp(final long a, final long b) {
      return EuclideanRing.emod$mcJ$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return EuclideanRing.equotmod$mcD$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return EuclideanRing.equotmod$mcF$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return EuclideanRing.equotmod$mcI$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return EuclideanRing.equotmod$mcJ$sp$(this, a, b);
   }

   public double gcd$mcD$sp(final double a, final double b, final Eq ev) {
      return EuclideanRing.gcd$mcD$sp$(this, a, b, ev);
   }

   public float gcd$mcF$sp(final float a, final float b, final Eq ev) {
      return EuclideanRing.gcd$mcF$sp$(this, a, b, ev);
   }

   public int gcd$mcI$sp(final int a, final int b, final Eq ev) {
      return EuclideanRing.gcd$mcI$sp$(this, a, b, ev);
   }

   public long gcd$mcJ$sp(final long a, final long b, final Eq ev) {
      return EuclideanRing.gcd$mcJ$sp$(this, a, b, ev);
   }

   public double lcm$mcD$sp(final double a, final double b, final Eq ev) {
      return EuclideanRing.lcm$mcD$sp$(this, a, b, ev);
   }

   public float lcm$mcF$sp(final float a, final float b, final Eq ev) {
      return EuclideanRing.lcm$mcF$sp$(this, a, b, ev);
   }

   public int lcm$mcI$sp(final int a, final int b, final Eq ev) {
      return EuclideanRing.lcm$mcI$sp$(this, a, b, ev);
   }

   public long lcm$mcJ$sp(final long a, final long b, final Eq ev) {
      return EuclideanRing.lcm$mcJ$sp$(this, a, b, ev);
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

   public double ceil$mcD$sp(final double a) {
      return IsReal.ceil$mcD$sp$(this, a);
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

   public double floor$mcD$sp(final double a) {
      return IsReal.floor$mcD$sp$(this, a);
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

   public double round$mcD$sp(final double a) {
      return IsReal.round$mcD$sp$(this, a);
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

   public boolean isWhole$mcD$sp(final double a) {
      return IsReal.isWhole$mcD$sp$(this, a);
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

   public double toDouble$mcD$sp(final double a) {
      return IsReal.toDouble$mcD$sp$(this, a);
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

   public int compare$mcZ$sp(final boolean x, final boolean y) {
      return Order.compare$mcZ$sp$(this, x, y);
   }

   public int compare$mcB$sp(final byte x, final byte y) {
      return Order.compare$mcB$sp$(this, x, y);
   }

   public int compare$mcC$sp(final char x, final char y) {
      return Order.compare$mcC$sp$(this, x, y);
   }

   public int compare$mcD$sp(final double x, final double y) {
      return Order.compare$mcD$sp$(this, x, y);
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

   public Comparison comparison(final Object x, final Object y) {
      return Order.comparison$(this, x, y);
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

   public Comparison comparison$mcD$sp(final double x, final double y) {
      return Order.comparison$mcD$sp$(this, x, y);
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

   public double partialCompare(final Object x, final Object y) {
      return Order.partialCompare$(this, x, y);
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

   public double partialCompare$mcD$sp(final double x, final double y) {
      return Order.partialCompare$mcD$sp$(this, x, y);
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

   public double min$mcD$sp(final double x, final double y) {
      return Order.min$mcD$sp$(this, x, y);
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

   public double max$mcD$sp(final double x, final double y) {
      return Order.max$mcD$sp$(this, x, y);
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

   public boolean eqv$mcD$sp(final double x, final double y) {
      return Order.eqv$mcD$sp$(this, x, y);
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

   public boolean neqv$mcD$sp(final double x, final double y) {
      return Order.neqv$mcD$sp$(this, x, y);
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

   public boolean lteqv$mcD$sp(final double x, final double y) {
      return Order.lteqv$mcD$sp$(this, x, y);
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

   public boolean lt$mcD$sp(final double x, final double y) {
      return Order.lt$mcD$sp$(this, x, y);
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

   public boolean gteqv$mcD$sp(final double x, final double y) {
      return Order.gteqv$mcD$sp$(this, x, y);
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

   public boolean gt$mcD$sp(final double x, final double y) {
      return Order.gt$mcD$sp$(this, x, y);
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

   public Option partialComparison(final Object x, final Object y) {
      return PartialOrder.partialComparison$(this, x, y);
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

   public Option partialComparison$mcD$sp(final double x, final double y) {
      return PartialOrder.partialComparison$mcD$sp$(this, x, y);
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

   public Option tryCompare(final Object x, final Object y) {
      return PartialOrder.tryCompare$(this, x, y);
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

   public Option tryCompare$mcD$sp(final double x, final double y) {
      return PartialOrder.tryCompare$mcD$sp$(this, x, y);
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

   public Option pmin(final Object x, final Object y) {
      return PartialOrder.pmin$(this, x, y);
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

   public Option pmin$mcD$sp(final double x, final double y) {
      return PartialOrder.pmin$mcD$sp$(this, x, y);
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

   public Option pmax(final Object x, final Object y) {
      return PartialOrder.pmax$(this, x, y);
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

   public Option pmax$mcD$sp(final double x, final double y) {
      return PartialOrder.pmax$mcD$sp$(this, x, y);
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

   public double fromByte$mcD$sp(final byte n) {
      return ConvertableTo.fromByte$mcD$sp$(this, n);
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

   public double fromShort$mcD$sp(final short n) {
      return ConvertableTo.fromShort$mcD$sp$(this, n);
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

   public double fromInt$mcD$sp(final int n) {
      return ConvertableTo.fromInt$mcD$sp$(this, n);
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

   public double fromLong$mcD$sp(final long n) {
      return ConvertableTo.fromLong$mcD$sp$(this, n);
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

   public double fromFloat$mcD$sp(final float n) {
      return ConvertableTo.fromFloat$mcD$sp$(this, n);
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

   public byte fromDouble$mcB$sp(final double n) {
      return ConvertableTo.fromDouble$mcB$sp$(this, n);
   }

   public char fromDouble$mcC$sp(final double n) {
      return ConvertableTo.fromDouble$mcC$sp$(this, n);
   }

   public double fromDouble$mcD$sp(final double n) {
      return ConvertableTo.fromDouble$mcD$sp$(this, n);
   }

   public float fromDouble$mcF$sp(final double n) {
      return ConvertableTo.fromDouble$mcF$sp$(this, n);
   }

   public int fromDouble$mcI$sp(final double n) {
      return ConvertableTo.fromDouble$mcI$sp$(this, n);
   }

   public long fromDouble$mcJ$sp(final double n) {
      return ConvertableTo.fromDouble$mcJ$sp$(this, n);
   }

   public short fromDouble$mcS$sp(final double n) {
      return ConvertableTo.fromDouble$mcS$sp$(this, n);
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

   public double fromBigInt$mcD$sp(final BigInt n) {
      return ConvertableTo.fromBigInt$mcD$sp$(this, n);
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

   public double fromBigDecimal$mcD$sp(final BigDecimal n) {
      return ConvertableTo.fromBigDecimal$mcD$sp$(this, n);
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

   public double fromRational$mcD$sp(final Rational n) {
      return ConvertableTo.fromRational$mcD$sp$(this, n);
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

   public double fromAlgebraic$mcD$sp(final Algebraic n) {
      return ConvertableTo.fromAlgebraic$mcD$sp$(this, n);
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

   public double fromReal$mcD$sp(final Real n) {
      return ConvertableTo.fromReal$mcD$sp$(this, n);
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

   public double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
      return ConvertableTo.fromType$mcD$sp$(this, b, evidence$1);
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

   public byte toByte$mcD$sp(final double a) {
      return ConvertableFrom.toByte$mcD$sp$(this, a);
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

   public short toShort$mcD$sp(final double a) {
      return ConvertableFrom.toShort$mcD$sp$(this, a);
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

   public int toInt$mcD$sp(final double a) {
      return ConvertableFrom.toInt$mcD$sp$(this, a);
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

   public long toLong$mcD$sp(final double a) {
      return ConvertableFrom.toLong$mcD$sp$(this, a);
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

   public float toFloat$mcD$sp(final double a) {
      return ConvertableFrom.toFloat$mcD$sp$(this, a);
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

   public BigInt toBigInt$mcD$sp(final double a) {
      return ConvertableFrom.toBigInt$mcD$sp$(this, a);
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

   public BigDecimal toBigDecimal$mcD$sp(final double a) {
      return ConvertableFrom.toBigDecimal$mcD$sp$(this, a);
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

   public Rational toRational$mcD$sp(final double a) {
      return ConvertableFrom.toRational$mcD$sp$(this, a);
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

   public Number toNumber$mcD$sp(final double a) {
      return ConvertableFrom.toNumber$mcD$sp$(this, a);
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

   public Object toType$mcD$sp(final double a, final ConvertableTo evidence$17) {
      return ConvertableFrom.toType$mcD$sp$(this, a, evidence$17);
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

   public String toString$mcD$sp(final double a) {
      return ConvertableFrom.toString$mcD$sp$(this, a);
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

   public double nroot$mcD$sp(final double a, final int n) {
      return NRoot.nroot$mcD$sp$(this, a, n);
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

   public Object sqrt(final Object a) {
      return NRoot.sqrt$(this, a);
   }

   public double sqrt$mcD$sp(final double a) {
      return NRoot.sqrt$mcD$sp$(this, a);
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

   public double fpow$mcD$sp(final double a, final double b) {
      return NRoot.fpow$mcD$sp$(this, a, b);
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

   public CommutativeGroup multiplicative() {
      return MultiplicativeCommutativeGroup.multiplicative$(this);
   }

   public CommutativeGroup multiplicative$mcD$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcD$sp$(this);
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

   public Object reciprocal(final Object x) {
      return MultiplicativeGroup.reciprocal$(this, x);
   }

   public double reciprocal$mcD$sp(final double x) {
      return MultiplicativeGroup.reciprocal$mcD$sp$(this, x);
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

   public double div$mcD$sp(final double x, final double y) {
      return MultiplicativeGroup.div$mcD$sp$(this, x, y);
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

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
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

   public CommutativeGroup additive() {
      return AdditiveCommutativeGroup.additive$(this);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup.additive$mcD$sp$(this);
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

   public double negate$mcD$sp(final double x) {
      return AdditiveGroup.negate$mcD$sp$(this, x);
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

   public double minus$mcD$sp(final double x, final double y) {
      return AdditiveGroup.minus$mcD$sp$(this, x, y);
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

   public Object sumN(final Object a, final int n) {
      return AdditiveGroup.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup.sumN$mcD$sp$(this, a, n);
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

   public double one$mcD$sp() {
      return MultiplicativeMonoid.one$mcD$sp$(this);
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

   public boolean isOne(final Object a, final Eq ev) {
      return MultiplicativeMonoid.isOne$(this, a, ev);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
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

   public Object product(final IterableOnce as) {
      return MultiplicativeMonoid.product$(this, as);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcD$sp$(this, as);
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

   public double times$mcD$sp(final double x, final double y) {
      return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
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

   public Object positivePow(final Object a, final int n) {
      return MultiplicativeSemigroup.positivePow$(this, a, n);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
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

   public double zero$mcD$sp() {
      return AdditiveMonoid.zero$mcD$sp$(this);
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

   public boolean isZero(final Object a, final Eq ev) {
      return AdditiveMonoid.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
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

   public Object sum(final IterableOnce as) {
      return AdditiveMonoid.sum$(this, as);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
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

   public double plus$mcD$sp(final double x, final double y) {
      return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
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

   public Object positiveSumN(final Object a, final int n) {
      return AdditiveSemigroup.positiveSumN$(this, a, n);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
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

   public BigInt one() {
      return this.one;
   }

   public BigInt zero() {
      return this.zero;
   }

   public void spire$std$BigIntIsEuclideanRing$_setter_$one_$eq(final BigInt x$1) {
      this.one = x$1;
   }

   public void spire$std$BigIntIsEuclideanRing$_setter_$zero_$eq(final BigInt x$1) {
      this.zero = x$1;
   }

   public BigInt fromInt(final int n) {
      return .MODULE$.BigInt().apply(n);
   }

   public BigInt fromDouble(final double n) {
      return .MODULE$.BigDecimal().apply(n).toBigInt();
   }

   public BigInt fromBigInt(final BigInt n) {
      return n;
   }

   public double toDouble(final BigInt n) {
      return n.toDouble();
   }

   public Rational toRational(final BigInt n) {
      return IsIntegral.toRational$(this, n);
   }

   public Algebraic toAlgebraic(final BigInt n) {
      return IsRational.toAlgebraic$(this, n);
   }

   public Real toReal(final BigInt n) {
      return IsAlgebraic.toReal$(this, n);
   }

   public BigInt toBigInt(final BigInt n) {
      return n;
   }

   public BigInt div(final BigInt a, final BigInt b) {
      return a.$div(b);
   }

   public BigIntIsNumeric() {
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
      BigIntIsEuclideanRing.$init$(this);
      BigIntIsNRoot.$init$(this);
      ConvertableFromBigInt.$init$(this);
      ConvertableToBigInt.$init$(this);
      IsAlgebraic.$init$(this);
      IsRational.$init$(this);
      IsIntegral.$init$(this);
      TruncatedDivision.$init$(this);
      forAdditiveCommutativeMonoid.$init$(this);
      forAdditiveCommutativeGroup.$init$(this);
      forCommutativeRing.$init$(this);
      BigIntOrder.$init$(this);
      BigIntSigned.$init$(this);
      BigIntTruncatedDivision.$init$(this);
      BigIntIsReal.$init$(this);
      Statics.releaseFence();
   }
}
