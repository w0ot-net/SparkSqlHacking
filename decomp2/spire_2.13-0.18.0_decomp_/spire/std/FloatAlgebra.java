package spire.std;

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
import cats.kernel.CommutativeGroup;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Order.mcF.sp;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.math.BigInt;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import spire.algebra.IsAlgebraic;
import spire.algebra.IsRational;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.math.Algebraic;
import spire.math.Rational;
import spire.math.Real;

@ScalaSignature(
   bytes = "\u0006\u0005E2AAA\u0002\u0001\u0011!)\u0001\u0006\u0001C\u0001S\taa\t\\8bi\u0006cw-\u001a2sC*\u0011A!B\u0001\u0004gR$'\"\u0001\u0004\u0002\u000bM\u0004\u0018N]3\u0004\u0001M9\u0001!C\b\u0014-ea\u0002C\u0001\u0006\u000e\u001b\u0005Y!\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u00059Y!AB!osJ+g\r\u0005\u0002\u0011#5\t1!\u0003\u0002\u0013\u0007\taa\t\\8bi&\u001bh)[3mIB\u0011\u0001\u0003F\u0005\u0003+\r\u0011AB\u00127pCRL5O\u0014*p_R\u0004\"\u0001E\f\n\u0005a\u0019!a\u0003$m_\u0006$\u0018j\u001d+sS\u001e\u0004\"\u0001\u0005\u000e\n\u0005m\u0019!a\u0003$m_\u0006$\u0018j\u001d*fC2\u0004\"!H\u0013\u000f\u0005y\u0019cBA\u0010#\u001b\u0005\u0001#BA\u0011\b\u0003\u0019a$o\\8u}%\tA\"\u0003\u0002%\u0017\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0014(\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t!3\"\u0001\u0004=S:LGO\u0010\u000b\u0002UA\u0011\u0001\u0003\u0001\u0015\u0005\u00011z\u0003\u0007\u0005\u0002\u000b[%\u0011af\u0003\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001"
)
public class FloatAlgebra implements FloatIsField, FloatIsNRoot, FloatIsTrig, FloatIsReal {
   private static final long serialVersionUID = 0L;

   public double toDouble(final float x) {
      return FloatIsReal.toDouble$(this, x);
   }

   public float ceil(final float a) {
      return FloatIsReal.ceil$(this, a);
   }

   public float floor(final float a) {
      return FloatIsReal.floor$(this, a);
   }

   public float round(final float a) {
      return FloatIsReal.round$(this, a);
   }

   public boolean isWhole(final float a) {
      return FloatIsReal.isWhole$(this, a);
   }

   public Rational toRational(final float a) {
      return FloatIsReal.toRational$(this, a);
   }

   public double toDouble$mcF$sp(final float x) {
      return FloatIsReal.toDouble$mcF$sp$(this, x);
   }

   public float ceil$mcF$sp(final float a) {
      return FloatIsReal.ceil$mcF$sp$(this, a);
   }

   public float floor$mcF$sp(final float a) {
      return FloatIsReal.floor$mcF$sp$(this, a);
   }

   public float round$mcF$sp(final float a) {
      return FloatIsReal.round$mcF$sp$(this, a);
   }

   public boolean isWhole$mcF$sp(final float a) {
      return FloatIsReal.isWhole$mcF$sp$(this, a);
   }

   public FloatTruncatedDivision order() {
      return FloatTruncatedDivision.order$(this);
   }

   public BigInt toBigIntOpt(final float a) {
      return FloatTruncatedDivision.toBigIntOpt$(this, a);
   }

   public float tquot(final float a, final float b) {
      return FloatTruncatedDivision.tquot$(this, a, b);
   }

   public float tmod(final float a, final float b) {
      return FloatTruncatedDivision.tmod$(this, a, b);
   }

   public float tquot$mcF$sp(final float a, final float b) {
      return FloatTruncatedDivision.tquot$mcF$sp$(this, a, b);
   }

   public float tmod$mcF$sp(final float a, final float b) {
      return FloatTruncatedDivision.tmod$mcF$sp$(this, a, b);
   }

   public int signum(final float a) {
      return FloatSigned.signum$(this, a);
   }

   public float abs(final float a) {
      return FloatSigned.abs$(this, a);
   }

   public int signum$mcF$sp(final float a) {
      return FloatSigned.signum$mcF$sp$(this, a);
   }

   public float abs$mcF$sp(final float a) {
      return FloatSigned.abs$mcF$sp$(this, a);
   }

   public boolean eqv(final float x, final float y) {
      return FloatOrder.eqv$(this, x, y);
   }

   public boolean neqv(final float x, final float y) {
      return FloatOrder.neqv$(this, x, y);
   }

   public boolean gt(final float x, final float y) {
      return FloatOrder.gt$(this, x, y);
   }

   public boolean gteqv(final float x, final float y) {
      return FloatOrder.gteqv$(this, x, y);
   }

   public boolean lt(final float x, final float y) {
      return FloatOrder.lt$(this, x, y);
   }

   public boolean lteqv(final float x, final float y) {
      return FloatOrder.lteqv$(this, x, y);
   }

   public float min(final float x, final float y) {
      return FloatOrder.min$(this, x, y);
   }

   public float max(final float x, final float y) {
      return FloatOrder.max$(this, x, y);
   }

   public int compare(final float x, final float y) {
      return FloatOrder.compare$(this, x, y);
   }

   public boolean eqv$mcF$sp(final float x, final float y) {
      return FloatOrder.eqv$mcF$sp$(this, x, y);
   }

   public boolean neqv$mcF$sp(final float x, final float y) {
      return FloatOrder.neqv$mcF$sp$(this, x, y);
   }

   public boolean gt$mcF$sp(final float x, final float y) {
      return FloatOrder.gt$mcF$sp$(this, x, y);
   }

   public boolean gteqv$mcF$sp(final float x, final float y) {
      return FloatOrder.gteqv$mcF$sp$(this, x, y);
   }

   public boolean lt$mcF$sp(final float x, final float y) {
      return FloatOrder.lt$mcF$sp$(this, x, y);
   }

   public boolean lteqv$mcF$sp(final float x, final float y) {
      return FloatOrder.lteqv$mcF$sp$(this, x, y);
   }

   public float min$mcF$sp(final float x, final float y) {
      return FloatOrder.min$mcF$sp$(this, x, y);
   }

   public float max$mcF$sp(final float x, final float y) {
      return FloatOrder.max$mcF$sp$(this, x, y);
   }

   public int compare$mcF$sp(final float x, final float y) {
      return FloatOrder.compare$mcF$sp$(this, x, y);
   }

   public Comparison comparison(final float x, final float y) {
      return sp.comparison$(this, x, y);
   }

   public Comparison comparison$mcF$sp(final float x, final float y) {
      return sp.comparison$mcF$sp$(this, x, y);
   }

   public double partialCompare(final float x, final float y) {
      return sp.partialCompare$(this, x, y);
   }

   public double partialCompare$mcF$sp(final float x, final float y) {
      return sp.partialCompare$mcF$sp$(this, x, y);
   }

   public Option partialComparison(final float x, final float y) {
      return cats.kernel.PartialOrder.mcF.sp.partialComparison$(this, x, y);
   }

   public Option partialComparison$mcF$sp(final float x, final float y) {
      return cats.kernel.PartialOrder.mcF.sp.partialComparison$mcF$sp$(this, x, y);
   }

   public Option tryCompare(final float x, final float y) {
      return cats.kernel.PartialOrder.mcF.sp.tryCompare$(this, x, y);
   }

   public Option tryCompare$mcF$sp(final float x, final float y) {
      return cats.kernel.PartialOrder.mcF.sp.tryCompare$mcF$sp$(this, x, y);
   }

   public Option pmin(final float x, final float y) {
      return cats.kernel.PartialOrder.mcF.sp.pmin$(this, x, y);
   }

   public Option pmin$mcF$sp(final float x, final float y) {
      return cats.kernel.PartialOrder.mcF.sp.pmin$mcF$sp$(this, x, y);
   }

   public Option pmax(final float x, final float y) {
      return cats.kernel.PartialOrder.mcF.sp.pmax$(this, x, y);
   }

   public Option pmax$mcF$sp(final float x, final float y) {
      return cats.kernel.PartialOrder.mcF.sp.pmax$mcF$sp$(this, x, y);
   }

   public float fmod(final float x, final float y) {
      return algebra.ring.TruncatedDivision.forCommutativeRing.mcF.sp.fmod$(this, x, y);
   }

   public float fmod$mcF$sp(final float x, final float y) {
      return algebra.ring.TruncatedDivision.forCommutativeRing.mcF.sp.fmod$mcF$sp$(this, x, y);
   }

   public float fquot(final float x, final float y) {
      return algebra.ring.TruncatedDivision.forCommutativeRing.mcF.sp.fquot$(this, x, y);
   }

   public float fquot$mcF$sp(final float x, final float y) {
      return algebra.ring.TruncatedDivision.forCommutativeRing.mcF.sp.fquot$mcF$sp$(this, x, y);
   }

   public Tuple2 fquotmod(final float x, final float y) {
      return algebra.ring.TruncatedDivision.forCommutativeRing.mcF.sp.fquotmod$(this, x, y);
   }

   public Tuple2 fquotmod$mcF$sp(final float x, final float y) {
      return algebra.ring.TruncatedDivision.forCommutativeRing.mcF.sp.fquotmod$mcF$sp$(this, x, y);
   }

   public Tuple2 tquotmod(final float x, final float y) {
      return algebra.ring.TruncatedDivision.mcF.sp.tquotmod$(this, x, y);
   }

   public Tuple2 tquotmod$mcF$sp(final float x, final float y) {
      return algebra.ring.TruncatedDivision.mcF.sp.tquotmod$mcF$sp$(this, x, y);
   }

   public Signed.Sign sign(final float a) {
      return algebra.ring.Signed.mcF.sp.sign$(this, a);
   }

   public Signed.Sign sign$mcF$sp(final float a) {
      return algebra.ring.Signed.mcF.sp.sign$mcF$sp$(this, a);
   }

   public boolean isSignZero(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignZero$(this, a);
   }

   public boolean isSignZero$mcF$sp(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignZero$mcF$sp$(this, a);
   }

   public boolean isSignPositive(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignPositive$(this, a);
   }

   public boolean isSignPositive$mcF$sp(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignPositive$mcF$sp$(this, a);
   }

   public boolean isSignNegative(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignNegative$(this, a);
   }

   public boolean isSignNegative$mcF$sp(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignNegative$mcF$sp$(this, a);
   }

   public boolean isSignNonZero(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignNonZero$(this, a);
   }

   public boolean isSignNonZero$mcF$sp(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignNonZero$mcF$sp$(this, a);
   }

   public boolean isSignNonPositive(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignNonPositive$(this, a);
   }

   public boolean isSignNonPositive$mcF$sp(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignNonPositive$mcF$sp$(this, a);
   }

   public boolean isSignNonNegative(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignNonNegative$(this, a);
   }

   public boolean isSignNonNegative$mcF$sp(final float a) {
      return algebra.ring.Signed.mcF.sp.isSignNonNegative$mcF$sp$(this, a);
   }

   public byte fmod$mcB$sp(final byte x, final byte y) {
      return forCommutativeRing.fmod$mcB$sp$(this, x, y);
   }

   public double fmod$mcD$sp(final double x, final double y) {
      return forCommutativeRing.fmod$mcD$sp$(this, x, y);
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

   public double fquot$mcD$sp(final double x, final double y) {
      return forCommutativeRing.fquot$mcD$sp$(this, x, y);
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

   public Tuple2 fquotmod$mcD$sp(final double x, final double y) {
      return forCommutativeRing.fquotmod$mcD$sp$(this, x, y);
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

   public Tuple2 tquotmod$mcI$sp(final int x, final int y) {
      return TruncatedDivision.tquotmod$mcI$sp$(this, x, y);
   }

   public Tuple2 tquotmod$mcJ$sp(final long x, final long y) {
      return TruncatedDivision.tquotmod$mcJ$sp$(this, x, y);
   }

   public Tuple2 tquotmod$mcS$sp(final short x, final short y) {
      return TruncatedDivision.tquotmod$mcS$sp$(this, x, y);
   }

   public Algebraic toAlgebraic(final Object a) {
      return IsRational.toAlgebraic$(this, a);
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

   public Real toReal(final Object a) {
      return IsAlgebraic.toReal$(this, a);
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

   public Signed.Sign sign$mcD$sp(final double a) {
      return Signed.sign$mcD$sp$(this, a);
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

   public boolean isSignZero$mcD$sp(final double a) {
      return Signed.isSignZero$mcD$sp$(this, a);
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

   public boolean isSignPositive$mcD$sp(final double a) {
      return Signed.isSignPositive$mcD$sp$(this, a);
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

   public boolean isSignNegative$mcD$sp(final double a) {
      return Signed.isSignNegative$mcD$sp$(this, a);
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

   public boolean isSignNonZero$mcD$sp(final double a) {
      return Signed.isSignNonZero$mcD$sp$(this, a);
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

   public boolean isSignNonPositive$mcD$sp(final double a) {
      return Signed.isSignNonPositive$mcD$sp$(this, a);
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

   public boolean isSignNonNegative$mcD$sp(final double a) {
      return Signed.isSignNonNegative$mcD$sp$(this, a);
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

   public Comparison comparison$mcD$sp(final double x, final double y) {
      return Order.comparison$mcD$sp$(this, x, y);
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

   public double partialCompare$mcD$sp(final double x, final double y) {
      return Order.partialCompare$mcD$sp$(this, x, y);
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

   public Option partialComparison$mcD$sp(final double x, final double y) {
      return PartialOrder.partialComparison$mcD$sp$(this, x, y);
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

   public Option tryCompare$mcD$sp(final double x, final double y) {
      return PartialOrder.tryCompare$mcD$sp$(this, x, y);
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

   public Option pmin$mcD$sp(final double x, final double y) {
      return PartialOrder.pmin$mcD$sp$(this, x, y);
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

   public Option pmax$mcD$sp(final double x, final double y) {
      return PartialOrder.pmax$mcD$sp$(this, x, y);
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

   public float e() {
      return FloatIsTrig.e$(this);
   }

   public float pi() {
      return FloatIsTrig.pi$(this);
   }

   public float exp(final float a) {
      return FloatIsTrig.exp$(this, a);
   }

   public float expm1(final float a) {
      return FloatIsTrig.expm1$(this, a);
   }

   public float log(final float a) {
      return FloatIsTrig.log$(this, a);
   }

   public float log1p(final float a) {
      return FloatIsTrig.log1p$(this, a);
   }

   public float sin(final float a) {
      return FloatIsTrig.sin$(this, a);
   }

   public float cos(final float a) {
      return FloatIsTrig.cos$(this, a);
   }

   public float tan(final float a) {
      return FloatIsTrig.tan$(this, a);
   }

   public float asin(final float a) {
      return FloatIsTrig.asin$(this, a);
   }

   public float acos(final float a) {
      return FloatIsTrig.acos$(this, a);
   }

   public float atan(final float a) {
      return FloatIsTrig.atan$(this, a);
   }

   public float atan2(final float y, final float x) {
      return FloatIsTrig.atan2$(this, y, x);
   }

   public float sinh(final float x) {
      return FloatIsTrig.sinh$(this, x);
   }

   public float cosh(final float x) {
      return FloatIsTrig.cosh$(this, x);
   }

   public float tanh(final float x) {
      return FloatIsTrig.tanh$(this, x);
   }

   public float toRadians(final float a) {
      return FloatIsTrig.toRadians$(this, a);
   }

   public float toDegrees(final float a) {
      return FloatIsTrig.toDegrees$(this, a);
   }

   public float e$mcF$sp() {
      return FloatIsTrig.e$mcF$sp$(this);
   }

   public float pi$mcF$sp() {
      return FloatIsTrig.pi$mcF$sp$(this);
   }

   public float exp$mcF$sp(final float a) {
      return FloatIsTrig.exp$mcF$sp$(this, a);
   }

   public float expm1$mcF$sp(final float a) {
      return FloatIsTrig.expm1$mcF$sp$(this, a);
   }

   public float log$mcF$sp(final float a) {
      return FloatIsTrig.log$mcF$sp$(this, a);
   }

   public float log1p$mcF$sp(final float a) {
      return FloatIsTrig.log1p$mcF$sp$(this, a);
   }

   public float sin$mcF$sp(final float a) {
      return FloatIsTrig.sin$mcF$sp$(this, a);
   }

   public float cos$mcF$sp(final float a) {
      return FloatIsTrig.cos$mcF$sp$(this, a);
   }

   public float tan$mcF$sp(final float a) {
      return FloatIsTrig.tan$mcF$sp$(this, a);
   }

   public float asin$mcF$sp(final float a) {
      return FloatIsTrig.asin$mcF$sp$(this, a);
   }

   public float acos$mcF$sp(final float a) {
      return FloatIsTrig.acos$mcF$sp$(this, a);
   }

   public float atan$mcF$sp(final float a) {
      return FloatIsTrig.atan$mcF$sp$(this, a);
   }

   public float atan2$mcF$sp(final float y, final float x) {
      return FloatIsTrig.atan2$mcF$sp$(this, y, x);
   }

   public float sinh$mcF$sp(final float x) {
      return FloatIsTrig.sinh$mcF$sp$(this, x);
   }

   public float cosh$mcF$sp(final float x) {
      return FloatIsTrig.cosh$mcF$sp$(this, x);
   }

   public float tanh$mcF$sp(final float x) {
      return FloatIsTrig.tanh$mcF$sp$(this, x);
   }

   public float toRadians$mcF$sp(final float a) {
      return FloatIsTrig.toRadians$mcF$sp$(this, a);
   }

   public float toDegrees$mcF$sp(final float a) {
      return FloatIsTrig.toDegrees$mcF$sp$(this, a);
   }

   public double e$mcD$sp() {
      return Trig.e$mcD$sp$(this);
   }

   public double pi$mcD$sp() {
      return Trig.pi$mcD$sp$(this);
   }

   public double exp$mcD$sp(final double a) {
      return Trig.exp$mcD$sp$(this, a);
   }

   public double expm1$mcD$sp(final double a) {
      return Trig.expm1$mcD$sp$(this, a);
   }

   public double log$mcD$sp(final double a) {
      return Trig.log$mcD$sp$(this, a);
   }

   public double log1p$mcD$sp(final double a) {
      return Trig.log1p$mcD$sp$(this, a);
   }

   public double sin$mcD$sp(final double a) {
      return Trig.sin$mcD$sp$(this, a);
   }

   public double cos$mcD$sp(final double a) {
      return Trig.cos$mcD$sp$(this, a);
   }

   public double tan$mcD$sp(final double a) {
      return Trig.tan$mcD$sp$(this, a);
   }

   public double asin$mcD$sp(final double a) {
      return Trig.asin$mcD$sp$(this, a);
   }

   public double acos$mcD$sp(final double a) {
      return Trig.acos$mcD$sp$(this, a);
   }

   public double atan$mcD$sp(final double a) {
      return Trig.atan$mcD$sp$(this, a);
   }

   public double atan2$mcD$sp(final double y, final double x) {
      return Trig.atan2$mcD$sp$(this, y, x);
   }

   public double sinh$mcD$sp(final double x) {
      return Trig.sinh$mcD$sp$(this, x);
   }

   public double cosh$mcD$sp(final double x) {
      return Trig.cosh$mcD$sp$(this, x);
   }

   public double tanh$mcD$sp(final double x) {
      return Trig.tanh$mcD$sp$(this, x);
   }

   public double toRadians$mcD$sp(final double a) {
      return Trig.toRadians$mcD$sp$(this, a);
   }

   public double toDegrees$mcD$sp(final double a) {
      return Trig.toDegrees$mcD$sp$(this, a);
   }

   public float nroot(final float a, final int k) {
      return FloatIsNRoot.nroot$(this, a, k);
   }

   public float sqrt(final float a) {
      return FloatIsNRoot.sqrt$(this, a);
   }

   public float fpow(final float a, final float b) {
      return FloatIsNRoot.fpow$(this, a, b);
   }

   public float nroot$mcF$sp(final float a, final int k) {
      return FloatIsNRoot.nroot$mcF$sp$(this, a, k);
   }

   public float sqrt$mcF$sp(final float a) {
      return FloatIsNRoot.sqrt$mcF$sp$(this, a);
   }

   public float fpow$mcF$sp(final float a, final float b) {
      return FloatIsNRoot.fpow$mcF$sp$(this, a, b);
   }

   public double nroot$mcD$sp(final double a, final int n) {
      return NRoot.nroot$mcD$sp$(this, a, n);
   }

   public int nroot$mcI$sp(final int a, final int n) {
      return NRoot.nroot$mcI$sp$(this, a, n);
   }

   public long nroot$mcJ$sp(final long a, final int n) {
      return NRoot.nroot$mcJ$sp$(this, a, n);
   }

   public double sqrt$mcD$sp(final double a) {
      return NRoot.sqrt$mcD$sp$(this, a);
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

   public int fpow$mcI$sp(final int a, final int b) {
      return NRoot.fpow$mcI$sp$(this, a, b);
   }

   public long fpow$mcJ$sp(final long a, final long b) {
      return NRoot.fpow$mcJ$sp$(this, a, b);
   }

   public float minus(final float a, final float b) {
      return FloatIsField.minus$(this, a, b);
   }

   public float negate(final float a) {
      return FloatIsField.negate$(this, a);
   }

   public float one() {
      return FloatIsField.one$(this);
   }

   public float plus(final float a, final float b) {
      return FloatIsField.plus$(this, a, b);
   }

   public float pow(final float a, final int b) {
      return FloatIsField.pow$(this, a, b);
   }

   public float times(final float a, final float b) {
      return FloatIsField.times$(this, a, b);
   }

   public float zero() {
      return FloatIsField.zero$(this);
   }

   public float fromInt(final int n) {
      return FloatIsField.fromInt$(this, n);
   }

   public float div(final float a, final float b) {
      return FloatIsField.div$(this, a, b);
   }

   public float fromDouble(final double n) {
      return FloatIsField.fromDouble$(this, n);
   }

   public float minus$mcF$sp(final float a, final float b) {
      return FloatIsField.minus$mcF$sp$(this, a, b);
   }

   public float negate$mcF$sp(final float a) {
      return FloatIsField.negate$mcF$sp$(this, a);
   }

   public float one$mcF$sp() {
      return FloatIsField.one$mcF$sp$(this);
   }

   public float plus$mcF$sp(final float a, final float b) {
      return FloatIsField.plus$mcF$sp$(this, a, b);
   }

   public float pow$mcF$sp(final float a, final int b) {
      return FloatIsField.pow$mcF$sp$(this, a, b);
   }

   public float times$mcF$sp(final float a, final float b) {
      return FloatIsField.times$mcF$sp$(this, a, b);
   }

   public float zero$mcF$sp() {
      return FloatIsField.zero$mcF$sp$(this);
   }

   public float fromInt$mcF$sp(final int n) {
      return FloatIsField.fromInt$mcF$sp$(this, n);
   }

   public float div$mcF$sp(final float a, final float b) {
      return FloatIsField.div$mcF$sp$(this, a, b);
   }

   public float fromDouble$mcF$sp(final double n) {
      return FloatIsField.fromDouble$mcF$sp$(this, n);
   }

   public float gcd(final float a, final float b, final Eq eqA) {
      return algebra.ring.Field.mcF.sp.gcd$(this, a, b, eqA);
   }

   public float gcd$mcF$sp(final float a, final float b, final Eq eqA) {
      return algebra.ring.Field.mcF.sp.gcd$mcF$sp$(this, a, b, eqA);
   }

   public float lcm(final float a, final float b, final Eq eqA) {
      return algebra.ring.Field.mcF.sp.lcm$(this, a, b, eqA);
   }

   public float lcm$mcF$sp(final float a, final float b, final Eq eqA) {
      return algebra.ring.Field.mcF.sp.lcm$mcF$sp$(this, a, b, eqA);
   }

   public BigInt euclideanFunction(final float a) {
      return algebra.ring.Field.mcF.sp.euclideanFunction$(this, a);
   }

   public BigInt euclideanFunction$mcF$sp(final float a) {
      return algebra.ring.Field.mcF.sp.euclideanFunction$mcF$sp$(this, a);
   }

   public float equot(final float a, final float b) {
      return algebra.ring.Field.mcF.sp.equot$(this, a, b);
   }

   public float equot$mcF$sp(final float a, final float b) {
      return algebra.ring.Field.mcF.sp.equot$mcF$sp$(this, a, b);
   }

   public float emod(final float a, final float b) {
      return algebra.ring.Field.mcF.sp.emod$(this, a, b);
   }

   public float emod$mcF$sp(final float a, final float b) {
      return algebra.ring.Field.mcF.sp.emod$mcF$sp$(this, a, b);
   }

   public Tuple2 equotmod(final float a, final float b) {
      return algebra.ring.Field.mcF.sp.equotmod$(this, a, b);
   }

   public Tuple2 equotmod$mcF$sp(final float a, final float b) {
      return algebra.ring.Field.mcF.sp.equotmod$mcF$sp$(this, a, b);
   }

   public float fromBigInt(final BigInt n) {
      return algebra.ring.Ring.mcF.sp.fromBigInt$(this, n);
   }

   public float fromBigInt$mcF$sp(final BigInt n) {
      return algebra.ring.Ring.mcF.sp.fromBigInt$mcF$sp$(this, n);
   }

   public CommutativeGroup additive() {
      return algebra.ring.AdditiveCommutativeGroup.mcF.sp.additive$(this);
   }

   public CommutativeGroup additive$mcF$sp() {
      return algebra.ring.AdditiveCommutativeGroup.mcF.sp.additive$mcF$sp$(this);
   }

   public float sumN(final float a, final int n) {
      return algebra.ring.AdditiveGroup.mcF.sp.sumN$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return algebra.ring.AdditiveGroup.mcF.sp.sumN$mcF$sp$(this, a, n);
   }

   public boolean isZero(final float a, final Eq ev) {
      return algebra.ring.AdditiveMonoid.mcF.sp.isZero$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return algebra.ring.AdditiveMonoid.mcF.sp.isZero$mcF$sp$(this, a, ev);
   }

   public float sum(final IterableOnce as) {
      return algebra.ring.AdditiveMonoid.mcF.sp.sum$(this, as);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return algebra.ring.AdditiveMonoid.mcF.sp.sum$mcF$sp$(this, as);
   }

   public float positiveSumN(final float a, final int n) {
      return algebra.ring.AdditiveSemigroup.mcF.sp.positiveSumN$(this, a, n);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return algebra.ring.AdditiveSemigroup.mcF.sp.positiveSumN$mcF$sp$(this, a, n);
   }

   public CommutativeGroup multiplicative() {
      return algebra.ring.MultiplicativeCommutativeGroup.mcF.sp.multiplicative$(this);
   }

   public CommutativeGroup multiplicative$mcF$sp() {
      return algebra.ring.MultiplicativeCommutativeGroup.mcF.sp.multiplicative$mcF$sp$(this);
   }

   public float reciprocal(final float x) {
      return algebra.ring.MultiplicativeGroup.mcF.sp.reciprocal$(this, x);
   }

   public float reciprocal$mcF$sp(final float x) {
      return algebra.ring.MultiplicativeGroup.mcF.sp.reciprocal$mcF$sp$(this, x);
   }

   public boolean isOne(final float a, final Eq ev) {
      return algebra.ring.MultiplicativeMonoid.mcF.sp.isOne$(this, a, ev);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return algebra.ring.MultiplicativeMonoid.mcF.sp.isOne$mcF$sp$(this, a, ev);
   }

   public float product(final IterableOnce as) {
      return algebra.ring.MultiplicativeMonoid.mcF.sp.product$(this, as);
   }

   public float product$mcF$sp(final IterableOnce as) {
      return algebra.ring.MultiplicativeMonoid.mcF.sp.product$mcF$sp$(this, as);
   }

   public float positivePow(final float a, final int n) {
      return algebra.ring.MultiplicativeSemigroup.mcF.sp.positivePow$(this, a, n);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return algebra.ring.MultiplicativeSemigroup.mcF.sp.positivePow$mcF$sp$(this, a, n);
   }

   public double gcd$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field.gcd$mcD$sp$(this, a, b, eqA);
   }

   public int gcd$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.gcd$mcI$sp$(this, a, b, eqA);
   }

   public long gcd$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.gcd$mcJ$sp$(this, a, b, eqA);
   }

   public double lcm$mcD$sp(final double a, final double b, final Eq eqA) {
      return Field.lcm$mcD$sp$(this, a, b, eqA);
   }

   public int lcm$mcI$sp(final int a, final int b, final Eq eqA) {
      return Field.lcm$mcI$sp$(this, a, b, eqA);
   }

   public long lcm$mcJ$sp(final long a, final long b, final Eq eqA) {
      return Field.lcm$mcJ$sp$(this, a, b, eqA);
   }

   public BigInt euclideanFunction$mcD$sp(final double a) {
      return Field.euclideanFunction$mcD$sp$(this, a);
   }

   public BigInt euclideanFunction$mcI$sp(final int a) {
      return Field.euclideanFunction$mcI$sp$(this, a);
   }

   public BigInt euclideanFunction$mcJ$sp(final long a) {
      return Field.euclideanFunction$mcJ$sp$(this, a);
   }

   public double equot$mcD$sp(final double a, final double b) {
      return Field.equot$mcD$sp$(this, a, b);
   }

   public int equot$mcI$sp(final int a, final int b) {
      return Field.equot$mcI$sp$(this, a, b);
   }

   public long equot$mcJ$sp(final long a, final long b) {
      return Field.equot$mcJ$sp$(this, a, b);
   }

   public double emod$mcD$sp(final double a, final double b) {
      return Field.emod$mcD$sp$(this, a, b);
   }

   public int emod$mcI$sp(final int a, final int b) {
      return Field.emod$mcI$sp$(this, a, b);
   }

   public long emod$mcJ$sp(final long a, final long b) {
      return Field.emod$mcJ$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcD$sp(final double a, final double b) {
      return Field.equotmod$mcD$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcI$sp(final int a, final int b) {
      return Field.equotmod$mcI$sp$(this, a, b);
   }

   public Tuple2 equotmod$mcJ$sp(final long a, final long b) {
      return Field.equotmod$mcJ$sp$(this, a, b);
   }

   public double fromDouble$mcD$sp(final double a) {
      return Field.fromDouble$mcD$sp$(this, a);
   }

   public int fromDouble$mcI$sp(final double a) {
      return Field.fromDouble$mcI$sp$(this, a);
   }

   public long fromDouble$mcJ$sp(final double a) {
      return Field.fromDouble$mcJ$sp$(this, a);
   }

   public CommutativeGroup multiplicative$mcD$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcD$sp$(this);
   }

   public CommutativeGroup multiplicative$mcI$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcI$sp$(this);
   }

   public CommutativeGroup multiplicative$mcJ$sp() {
      return MultiplicativeCommutativeGroup.multiplicative$mcJ$sp$(this);
   }

   public byte fromDouble$mcB$sp(final double a) {
      return DivisionRing.fromDouble$mcB$sp$(this, a);
   }

   public short fromDouble$mcS$sp(final double a) {
      return DivisionRing.fromDouble$mcS$sp$(this, a);
   }

   public double reciprocal$mcD$sp(final double x) {
      return MultiplicativeGroup.reciprocal$mcD$sp$(this, x);
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

   public int div$mcI$sp(final int x, final int y) {
      return MultiplicativeGroup.div$mcI$sp$(this, x, y);
   }

   public long div$mcJ$sp(final long x, final long y) {
      return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
   }

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
   }

   public double fromInt$mcD$sp(final int n) {
      return Ring.fromInt$mcD$sp$(this, n);
   }

   public int fromInt$mcI$sp(final int n) {
      return Ring.fromInt$mcI$sp$(this, n);
   }

   public long fromInt$mcJ$sp(final int n) {
      return Ring.fromInt$mcJ$sp$(this, n);
   }

   public double fromBigInt$mcD$sp(final BigInt n) {
      return Ring.fromBigInt$mcD$sp$(this, n);
   }

   public int fromBigInt$mcI$sp(final BigInt n) {
      return Ring.fromBigInt$mcI$sp$(this, n);
   }

   public long fromBigInt$mcJ$sp(final BigInt n) {
      return Ring.fromBigInt$mcJ$sp$(this, n);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup.additive$mcD$sp$(this);
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

   public int negate$mcI$sp(final int x) {
      return AdditiveGroup.negate$mcI$sp$(this, x);
   }

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
   }

   public double minus$mcD$sp(final double x, final double y) {
      return AdditiveGroup.minus$mcD$sp$(this, x, y);
   }

   public int minus$mcI$sp(final int x, final int y) {
      return AdditiveGroup.minus$mcI$sp$(this, x, y);
   }

   public long minus$mcJ$sp(final long x, final long y) {
      return AdditiveGroup.minus$mcJ$sp$(this, x, y);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup.sumN$mcD$sp$(this, a, n);
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

   public int one$mcI$sp() {
      return MultiplicativeMonoid.one$mcI$sp$(this);
   }

   public long one$mcJ$sp() {
      return MultiplicativeMonoid.one$mcJ$sp$(this);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcD$sp$(this, as);
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

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
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

   public int zero$mcI$sp() {
      return AdditiveMonoid.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
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

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public FloatAlgebra() {
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      MultiplicativeSemigroup.$init$(this);
      MultiplicativeMonoid.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      Ring.$init$(this);
      MultiplicativeCommutativeSemigroup.$init$(this);
      MultiplicativeCommutativeMonoid.$init$(this);
      EuclideanRing.$init$(this);
      MultiplicativeGroup.$init$(this);
      DivisionRing.$init$(this);
      MultiplicativeCommutativeGroup.$init$(this);
      Field.$init$(this);
      FloatIsField.$init$(this);
      NRoot.$init$(this);
      FloatIsNRoot.$init$(this);
      FloatIsTrig.$init$(this);
      Eq.$init$(this);
      PartialOrder.$init$(this);
      Order.$init$(this);
      Signed.$init$(this);
      IsAlgebraic.$init$(this);
      IsRational.$init$(this);
      TruncatedDivision.$init$(this);
      forAdditiveCommutativeMonoid.$init$(this);
      forAdditiveCommutativeGroup.$init$(this);
      forCommutativeRing.$init$(this);
      FloatOrder.$init$(this);
      FloatSigned.$init$(this);
      FloatTruncatedDivision.$init$(this);
      FloatIsReal.$init$(this);
   }
}
