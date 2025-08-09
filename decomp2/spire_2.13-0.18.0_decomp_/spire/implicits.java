package spire;

import algebra.lattice.Bool;
import algebra.lattice.Heyting;
import algebra.lattice.JoinSemilattice;
import algebra.lattice.Logic;
import algebra.lattice.MeetSemilattice;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRing;
import algebra.ring.CommutativeSemiring;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.GCDRing;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import java.math.MathContext;
import scala.StringContext;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.immutable.IndexedSeq;
import scala.math.BigInt;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.InnerProductSpace;
import spire.algebra.Involution;
import spire.algebra.IsReal;
import spire.algebra.MetricSpace;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;
import spire.algebra.partial.Groupoid;
import spire.algebra.partial.Semigroupoid;
import spire.math.BitString;
import spire.math.ConvertableFrom;
import spire.math.ConvertableTo;
import spire.math.Integral;
import spire.math.IntegralOps;
import spire.math.NumberTag;
import spire.std.BigDecimalIsTrig;
import spire.std.MapCRng;
import spire.std.MapCSemiring;
import spire.std.MapEq;
import spire.std.MapGroup;
import spire.std.MapInnerProductSpace;
import spire.std.MapMonoid;
import spire.std.MapVectorSpace;
import spire.std.OptionAdditiveMonoid;
import spire.std.OptionCMonoid;
import spire.std.OptionEq;
import spire.std.OptionMonoid;
import spire.std.OptionMultiplicativeMonoid;
import spire.std.OptionOrder;
import spire.std.SeqCModule;
import spire.std.SeqEq;
import spire.std.SeqInnerProductSpace;
import spire.std.SeqOrder;
import spire.std.SeqVectorSpace;
import spire.syntax.AdditiveGroupOps;
import spire.syntax.AdditiveMonoidOps;
import spire.syntax.AdditiveSemigroupOps;
import spire.syntax.BitStringOps;
import spire.syntax.BoolOps;
import spire.syntax.ConvertableFromOps;
import spire.syntax.CoordinateSpaceOps;
import spire.syntax.EqOps;
import spire.syntax.EuclideanRingOps;
import spire.syntax.GCDRingOps;
import spire.syntax.GroupOps;
import spire.syntax.GroupoidCommonOps;
import spire.syntax.GroupoidOps;
import spire.syntax.HeytingOps;
import spire.syntax.InnerProductSpaceOps;
import spire.syntax.IntervalPointOps;
import spire.syntax.InvolutionOps;
import spire.syntax.IsRealOps;
import spire.syntax.JoinOps;
import spire.syntax.LeftActionOps;
import spire.syntax.LeftModuleOps;
import spire.syntax.LeftPartialActionOps;
import spire.syntax.LiteralsSyntax;
import spire.syntax.LogicOps;
import spire.syntax.MeetOps;
import spire.syntax.MetricSpaceOps;
import spire.syntax.MonoidOps;
import spire.syntax.MultiplicativeGroupOps;
import spire.syntax.MultiplicativeMonoidOps;
import spire.syntax.MultiplicativeSemigroupOps;
import spire.syntax.NRootOps;
import spire.syntax.NormedVectorSpaceOps;
import spire.syntax.OrderOps;
import spire.syntax.PartialOrderOps;
import spire.syntax.RightActionOps;
import spire.syntax.RightModuleOps;
import spire.syntax.RightPartialActionOps;
import spire.syntax.SemigroupOps;
import spire.syntax.SemigroupoidOps;
import spire.syntax.SemiringOps;
import spire.syntax.SignedOps;
import spire.syntax.TorsorPointOps;
import spire.syntax.TrigOps;
import spire.syntax.TruncatedDivisionOps;
import spire.syntax.VectorSpaceOps;
import spire.syntax.std.ArrayOps;
import spire.syntax.std.IndexedSeqOps;
import spire.syntax.std.SeqOps;

@ScalaSignature(
   bytes = "\u0006\u0005}9Qa\u0001\u0003\t\u0002\u001d1Q!\u0003\u0003\t\u0002)AQ!H\u0001\u0005\u0002y\t\u0011\"[7qY&\u001c\u0017\u000e^:\u000b\u0003\u0015\tQa\u001d9je\u0016\u001c\u0001\u0001\u0005\u0002\t\u00035\tAAA\u0005j[Bd\u0017nY5ugN!\u0011aC\t\u0018!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fMB\u0011!#F\u0007\u0002')\u0011A\u0003B\u0001\u0004gR$\u0017B\u0001\f\u0014\u00051\te._%ogR\fgnY3t!\tA2$D\u0001\u001a\u0015\tQB!\u0001\u0004ts:$\u0018\r_\u0005\u00039e\u0011\u0011\"\u00117m'ftG/\u0019=\u0002\rqJg.\u001b;?)\u00059\u0001"
)
public final class implicits {
   public static GroupOps groupOps(final Object a, final Group evidence$10) {
      return implicits$.MODULE$.groupOps(a, evidence$10);
   }

   public static MonoidOps monoidOps(final Object a, final Monoid ev) {
      return implicits$.MODULE$.monoidOps(a, ev);
   }

   public static SemigroupOps semigroupOps(final Object a, final Semigroup evidence$9) {
      return implicits$.MODULE$.semigroupOps(a, evidence$9);
   }

   public static IndexedSeqOps indexedSeqOps(final IndexedSeq lhs) {
      return implicits$.MODULE$.indexedSeqOps(lhs);
   }

   public static SeqOps seqOps(final Iterable lhs) {
      return implicits$.MODULE$.seqOps(lhs);
   }

   public static ArrayOps arrayOps(final Object lhs) {
      return implicits$.MODULE$.arrayOps(lhs);
   }

   public static BigInt literalBigIntOps(final BigInt b) {
      return implicits$.MODULE$.literalBigIntOps(b);
   }

   public static double literalDoubleOps(final double n) {
      return implicits$.MODULE$.literalDoubleOps(n);
   }

   public static long literalLongOps(final long n) {
      return implicits$.MODULE$.literalLongOps(n);
   }

   public static Object intToA(final int n, final ConvertableTo c) {
      return implicits$.MODULE$.intToA(n, c);
   }

   public static int literalIntOps(final int n) {
      return implicits$.MODULE$.literalIntOps(n);
   }

   public static IntegralOps integralOps(final Object a, final Integral evidence$28) {
      return implicits$.MODULE$.integralOps(a, evidence$28);
   }

   public static TorsorPointOps torsorPointOps(final Object p) {
      return implicits$.MODULE$.torsorPointOps(p);
   }

   public static RightActionOps rightActionOps(final Object p) {
      return implicits$.MODULE$.rightActionOps(p);
   }

   public static LeftActionOps leftActionOps(final Object g) {
      return implicits$.MODULE$.leftActionOps(g);
   }

   public static RightPartialActionOps rightPartialActionOps(final Object p) {
      return implicits$.MODULE$.rightPartialActionOps(p);
   }

   public static LeftPartialActionOps leftPartialActionOps(final Object g) {
      return implicits$.MODULE$.leftPartialActionOps(g);
   }

   public static BitStringOps bitStringOps(final Object a, final BitString evidence$25) {
      return implicits$.MODULE$.bitStringOps(a, evidence$25);
   }

   public static BoolOps boolOps(final Object a, final Bool evidence$24) {
      return implicits$.MODULE$.boolOps(a, evidence$24);
   }

   public static HeytingOps heytingOps(final Object a, final Heyting evidence$22) {
      return implicits$.MODULE$.heytingOps(a, evidence$22);
   }

   public static LogicOps logicOps(final Object a, final Logic evidence$23) {
      return implicits$.MODULE$.logicOps(a, evidence$23);
   }

   public static JoinOps joinOps(final Object a, final JoinSemilattice evidence$21) {
      return implicits$.MODULE$.joinOps(a, evidence$21);
   }

   public static MeetOps meetOps(final Object a, final MeetSemilattice evidence$20) {
      return implicits$.MODULE$.meetOps(a, evidence$20);
   }

   public static CoordinateSpaceOps coordinateSpaceOps(final Object v) {
      return implicits$.MODULE$.coordinateSpaceOps(v);
   }

   public static InnerProductSpaceOps innerProductSpaceOps(final Object v) {
      return implicits$.MODULE$.innerProductSpaceOps(v);
   }

   public static NormedVectorSpaceOps normedVectorSpaceOps(final Object v) {
      return implicits$.MODULE$.normedVectorSpaceOps(v);
   }

   public static MetricSpaceOps metricSpaceOps(final Object v) {
      return implicits$.MODULE$.metricSpaceOps(v);
   }

   public static VectorSpaceOps vectorSpaceOps(final Object v) {
      return implicits$.MODULE$.vectorSpaceOps(v);
   }

   public static RightModuleOps rightModuleOps(final Object v) {
      return implicits$.MODULE$.rightModuleOps(v);
   }

   public static LeftModuleOps leftModuleOps(final Object v) {
      return implicits$.MODULE$.leftModuleOps(v);
   }

   public static IntervalPointOps intervalOps(final Object a, final Order evidence$26, final AdditiveGroup evidence$27) {
      return implicits$.MODULE$.intervalOps(a, evidence$26, evidence$27);
   }

   public static TrigOps trigOps(final Object a, final Trig evidence$19) {
      return implicits$.MODULE$.trigOps(a, evidence$19);
   }

   public static NRootOps nrootOps(final Object a, final NRoot evidence$18) {
      return implicits$.MODULE$.nrootOps(a, evidence$18);
   }

   public static double literalDoubleEuclideanRingOps(final double lhs) {
      return implicits$.MODULE$.literalDoubleEuclideanRingOps(lhs);
   }

   public static long literalLongEuclideanRingOps(final long lhs) {
      return implicits$.MODULE$.literalLongEuclideanRingOps(lhs);
   }

   public static int literalIntEuclideanRingOps(final int lhs) {
      return implicits$.MODULE$.literalIntEuclideanRingOps(lhs);
   }

   public static EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
      return implicits$.MODULE$.euclideanRingOps(a, evidence$17);
   }

   public static GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
      return implicits$.MODULE$.gcdRingOps(a, evidence$16);
   }

   public static SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
      return implicits$.MODULE$.semiringOps(a, evidence$15);
   }

   public static double literalDoubleMultiplicativeGroupOps(final double lhs) {
      return implicits$.MODULE$.literalDoubleMultiplicativeGroupOps(lhs);
   }

   public static long literalLongMultiplicativeGroupOps(final long lhs) {
      return implicits$.MODULE$.literalLongMultiplicativeGroupOps(lhs);
   }

   public static int literalIntMultiplicativeGroupOps(final int lhs) {
      return implicits$.MODULE$.literalIntMultiplicativeGroupOps(lhs);
   }

   public static MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
      return implicits$.MODULE$.multiplicativeGroupOps(a, evidence$14);
   }

   public static MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
      return implicits$.MODULE$.multiplicativeMonoidOps(a, ev);
   }

   public static double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
      return implicits$.MODULE$.literalDoubleMultiplicativeSemigroupOps(lhs);
   }

   public static long literalLongMultiplicativeSemigroupOps(final long lhs) {
      return implicits$.MODULE$.literalLongMultiplicativeSemigroupOps(lhs);
   }

   public static int literalIntMultiplicativeSemigroupOps(final int lhs) {
      return implicits$.MODULE$.literalIntMultiplicativeSemigroupOps(lhs);
   }

   public static MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
      return implicits$.MODULE$.multiplicativeSemigroupOps(a, evidence$13);
   }

   public static double literalDoubleAdditiveGroupOps(final double lhs) {
      return implicits$.MODULE$.literalDoubleAdditiveGroupOps(lhs);
   }

   public static long literalLongAdditiveGroupOps(final long lhs) {
      return implicits$.MODULE$.literalLongAdditiveGroupOps(lhs);
   }

   public static int literalIntAdditiveGroupOps(final int lhs) {
      return implicits$.MODULE$.literalIntAdditiveGroupOps(lhs);
   }

   public static AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
      return implicits$.MODULE$.additiveGroupOps(a, evidence$12);
   }

   public static AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
      return implicits$.MODULE$.additiveMonoidOps(a, ev);
   }

   public static double literalDoubleAdditiveSemigroupOps(final double lhs) {
      return implicits$.MODULE$.literalDoubleAdditiveSemigroupOps(lhs);
   }

   public static long literalLongAdditiveSemigroupOps(final long lhs) {
      return implicits$.MODULE$.literalLongAdditiveSemigroupOps(lhs);
   }

   public static int literalIntAdditiveSemigroupOps(final int lhs) {
      return implicits$.MODULE$.literalIntAdditiveSemigroupOps(lhs);
   }

   public static AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
      return implicits$.MODULE$.additiveSemigroupOps(a, evidence$11);
   }

   public static GroupoidOps groupoidOps(final Object a, final Groupoid ev) {
      return implicits$.MODULE$.groupoidOps(a, ev);
   }

   public static GroupoidCommonOps groupoidCommonOps(final Object a, final Groupoid ev, final NotGiven ni) {
      return implicits$.MODULE$.groupoidCommonOps(a, ev, ni);
   }

   public static SemigroupoidOps semigroupoidOps(final Object a, final Semigroupoid evidence$8) {
      return implicits$.MODULE$.semigroupoidOps(a, evidence$8);
   }

   public static ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
      return implicits$.MODULE$.convertableOps(a, evidence$29);
   }

   public static IsRealOps isRealOps(final Object a, final IsReal evidence$7) {
      return implicits$.MODULE$.isRealOps(a, evidence$7);
   }

   public static InvolutionOps involutionOps(final Object lhs, final Involution evidence$6) {
      return implicits$.MODULE$.involutionOps(lhs, evidence$6);
   }

   public static double literalDoubleTruncatedDivisionOps(final double lhs) {
      return implicits$.MODULE$.literalDoubleTruncatedDivisionOps(lhs);
   }

   public static long literalLongTruncatedDivisionOps(final long lhs) {
      return implicits$.MODULE$.literalLongTruncatedDivisionOps(lhs);
   }

   public static int literalIntTruncatedDivisionOps(final int lhs) {
      return implicits$.MODULE$.literalIntTruncatedDivisionOps(lhs);
   }

   public static TruncatedDivisionOps truncatedDivisionOps(final Object a, final TruncatedDivision evidence$5) {
      return implicits$.MODULE$.truncatedDivisionOps(a, evidence$5);
   }

   public static SignedOps signedOps(final Object a, final Signed evidence$4) {
      return implicits$.MODULE$.signedOps(a, evidence$4);
   }

   public static double literalDoubleOrderOps(final double lhs) {
      return implicits$.MODULE$.literalDoubleOrderOps(lhs);
   }

   public static long literalLongOrderOps(final long lhs) {
      return implicits$.MODULE$.literalLongOrderOps(lhs);
   }

   public static int literalIntOrderOps(final int lhs) {
      return implicits$.MODULE$.literalIntOrderOps(lhs);
   }

   public static OrderOps orderOps(final Object a, final Order evidence$3) {
      return implicits$.MODULE$.orderOps(a, evidence$3);
   }

   public static PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
      return implicits$.MODULE$.partialOrderOps(a, evidence$2);
   }

   public static EqOps eqOps(final Object a, final Eq evidence$1) {
      return implicits$.MODULE$.eqOps(a, evidence$1);
   }

   public static LiteralsSyntax.eu$ eu() {
      return implicits$.MODULE$.eu();
   }

   public static LiteralsSyntax.us$ us() {
      return implicits$.MODULE$.us();
   }

   public static LiteralsSyntax.si$ si() {
      return implicits$.MODULE$.si();
   }

   public static LiteralsSyntax.radix$ radix() {
      return implicits$.MODULE$.radix();
   }

   public static StringContext literals(final StringContext s) {
      return implicits$.MODULE$.literals(s);
   }

   public static CommutativeGroup UnitAlgebra() {
      return implicits$.MODULE$.UnitAlgebra();
   }

   public static OptionOrder OptionOrder(final Order evidence$12) {
      return implicits$.MODULE$.OptionOrder(evidence$12);
   }

   public static OptionMultiplicativeMonoid OptionMultiplicativeMonoid(final MultiplicativeSemigroup evidence$11) {
      return implicits$.MODULE$.OptionMultiplicativeMonoid(evidence$11);
   }

   public static OptionAdditiveMonoid OptionAdditiveMonoid(final AdditiveSemigroup evidence$10) {
      return implicits$.MODULE$.OptionAdditiveMonoid(evidence$10);
   }

   public static OptionCMonoid OptionCMonoid(final CommutativeSemigroup evidence$9) {
      return implicits$.MODULE$.OptionCMonoid(evidence$9);
   }

   public static OptionMonoid OptionMonoid(final Semigroup evidence$8) {
      return implicits$.MODULE$.OptionMonoid(evidence$8);
   }

   public static OptionEq OptionEq(final Eq evidence$7) {
      return implicits$.MODULE$.OptionEq(evidence$7);
   }

   public static Order OrderProduct22(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20, final Order _structure21, final Order _structure22) {
      return implicits$.MODULE$.OrderProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Order OrderProduct21(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20, final Order _structure21) {
      return implicits$.MODULE$.OrderProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Order OrderProduct20(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20) {
      return implicits$.MODULE$.OrderProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Order OrderProduct19(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19) {
      return implicits$.MODULE$.OrderProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Order OrderProduct18(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18) {
      return implicits$.MODULE$.OrderProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Order OrderProduct17(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17) {
      return implicits$.MODULE$.OrderProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Order OrderProduct16(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16) {
      return implicits$.MODULE$.OrderProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Order OrderProduct15(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15) {
      return implicits$.MODULE$.OrderProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Order OrderProduct14(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14) {
      return implicits$.MODULE$.OrderProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Order OrderProduct13(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13) {
      return implicits$.MODULE$.OrderProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Order OrderProduct12(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12) {
      return implicits$.MODULE$.OrderProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Order OrderProduct11(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11) {
      return implicits$.MODULE$.OrderProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Order OrderProduct10(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10) {
      return implicits$.MODULE$.OrderProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Order OrderProduct9(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9) {
      return implicits$.MODULE$.OrderProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Order OrderProduct8(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8) {
      return implicits$.MODULE$.OrderProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Order OrderProduct7(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7) {
      return implicits$.MODULE$.OrderProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Order OrderProduct6(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6) {
      return implicits$.MODULE$.OrderProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Order OrderProduct5(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5) {
      return implicits$.MODULE$.OrderProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Order OrderProduct4(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4) {
      return implicits$.MODULE$.OrderProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Order OrderProduct3(final Order _structure1, final Order _structure2, final Order _structure3) {
      return implicits$.MODULE$.OrderProduct3(_structure1, _structure2, _structure3);
   }

   public static Order OrderProduct2(final Order _structure1, final Order _structure2) {
      return implicits$.MODULE$.OrderProduct2(_structure1, _structure2);
   }

   public static Eq EqProduct22(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20, final Eq _structure21, final Eq _structure22) {
      return implicits$.MODULE$.EqProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Eq EqProduct21(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20, final Eq _structure21) {
      return implicits$.MODULE$.EqProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Eq EqProduct20(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20) {
      return implicits$.MODULE$.EqProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Eq EqProduct19(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19) {
      return implicits$.MODULE$.EqProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Eq EqProduct18(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18) {
      return implicits$.MODULE$.EqProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Eq EqProduct17(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17) {
      return implicits$.MODULE$.EqProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Eq EqProduct16(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16) {
      return implicits$.MODULE$.EqProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Eq EqProduct15(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15) {
      return implicits$.MODULE$.EqProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Eq EqProduct14(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14) {
      return implicits$.MODULE$.EqProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Eq EqProduct13(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13) {
      return implicits$.MODULE$.EqProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Eq EqProduct12(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12) {
      return implicits$.MODULE$.EqProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Eq EqProduct11(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11) {
      return implicits$.MODULE$.EqProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Eq EqProduct10(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10) {
      return implicits$.MODULE$.EqProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Eq EqProduct9(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9) {
      return implicits$.MODULE$.EqProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Eq EqProduct8(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8) {
      return implicits$.MODULE$.EqProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Eq EqProduct7(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7) {
      return implicits$.MODULE$.EqProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Eq EqProduct6(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6) {
      return implicits$.MODULE$.EqProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Eq EqProduct5(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5) {
      return implicits$.MODULE$.EqProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Eq EqProduct4(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4) {
      return implicits$.MODULE$.EqProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Eq EqProduct3(final Eq _structure1, final Eq _structure2, final Eq _structure3) {
      return implicits$.MODULE$.EqProduct3(_structure1, _structure2, _structure3);
   }

   public static Eq EqProduct2(final Eq _structure1, final Eq _structure2) {
      return implicits$.MODULE$.EqProduct2(_structure1, _structure2);
   }

   public static Ring RingProduct22(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20, final Ring _structure21, final Ring _structure22) {
      return implicits$.MODULE$.RingProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Ring RingProduct21(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20, final Ring _structure21) {
      return implicits$.MODULE$.RingProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Ring RingProduct20(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20) {
      return implicits$.MODULE$.RingProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Ring RingProduct19(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19) {
      return implicits$.MODULE$.RingProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Ring RingProduct18(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18) {
      return implicits$.MODULE$.RingProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Ring RingProduct17(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17) {
      return implicits$.MODULE$.RingProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Ring RingProduct16(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16) {
      return implicits$.MODULE$.RingProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Ring RingProduct15(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15) {
      return implicits$.MODULE$.RingProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Ring RingProduct14(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14) {
      return implicits$.MODULE$.RingProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Ring RingProduct13(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13) {
      return implicits$.MODULE$.RingProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Ring RingProduct12(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12) {
      return implicits$.MODULE$.RingProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Ring RingProduct11(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11) {
      return implicits$.MODULE$.RingProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Ring RingProduct10(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10) {
      return implicits$.MODULE$.RingProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Ring RingProduct9(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9) {
      return implicits$.MODULE$.RingProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Ring RingProduct8(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8) {
      return implicits$.MODULE$.RingProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Ring RingProduct7(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7) {
      return implicits$.MODULE$.RingProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Ring RingProduct6(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6) {
      return implicits$.MODULE$.RingProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Ring RingProduct5(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5) {
      return implicits$.MODULE$.RingProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Ring RingProduct4(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4) {
      return implicits$.MODULE$.RingProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Ring RingProduct3(final Ring _structure1, final Ring _structure2, final Ring _structure3) {
      return implicits$.MODULE$.RingProduct3(_structure1, _structure2, _structure3);
   }

   public static Ring RingProduct2(final Ring _structure1, final Ring _structure2) {
      return implicits$.MODULE$.RingProduct2(_structure1, _structure2);
   }

   public static Rig RigProduct22(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20, final Rig _structure21, final Rig _structure22) {
      return implicits$.MODULE$.RigProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Rig RigProduct21(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20, final Rig _structure21) {
      return implicits$.MODULE$.RigProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Rig RigProduct20(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20) {
      return implicits$.MODULE$.RigProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Rig RigProduct19(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19) {
      return implicits$.MODULE$.RigProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Rig RigProduct18(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18) {
      return implicits$.MODULE$.RigProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Rig RigProduct17(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17) {
      return implicits$.MODULE$.RigProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Rig RigProduct16(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16) {
      return implicits$.MODULE$.RigProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Rig RigProduct15(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15) {
      return implicits$.MODULE$.RigProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Rig RigProduct14(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14) {
      return implicits$.MODULE$.RigProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Rig RigProduct13(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13) {
      return implicits$.MODULE$.RigProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Rig RigProduct12(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12) {
      return implicits$.MODULE$.RigProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Rig RigProduct11(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11) {
      return implicits$.MODULE$.RigProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Rig RigProduct10(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10) {
      return implicits$.MODULE$.RigProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Rig RigProduct9(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9) {
      return implicits$.MODULE$.RigProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Rig RigProduct8(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8) {
      return implicits$.MODULE$.RigProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Rig RigProduct7(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7) {
      return implicits$.MODULE$.RigProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Rig RigProduct6(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6) {
      return implicits$.MODULE$.RigProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Rig RigProduct5(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5) {
      return implicits$.MODULE$.RigProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Rig RigProduct4(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4) {
      return implicits$.MODULE$.RigProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Rig RigProduct3(final Rig _structure1, final Rig _structure2, final Rig _structure3) {
      return implicits$.MODULE$.RigProduct3(_structure1, _structure2, _structure3);
   }

   public static Rig RigProduct2(final Rig _structure1, final Rig _structure2) {
      return implicits$.MODULE$.RigProduct2(_structure1, _structure2);
   }

   public static Rng RngProduct22(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20, final Rng _structure21, final Rng _structure22) {
      return implicits$.MODULE$.RngProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Rng RngProduct21(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20, final Rng _structure21) {
      return implicits$.MODULE$.RngProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Rng RngProduct20(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20) {
      return implicits$.MODULE$.RngProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Rng RngProduct19(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19) {
      return implicits$.MODULE$.RngProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Rng RngProduct18(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18) {
      return implicits$.MODULE$.RngProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Rng RngProduct17(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17) {
      return implicits$.MODULE$.RngProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Rng RngProduct16(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16) {
      return implicits$.MODULE$.RngProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Rng RngProduct15(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15) {
      return implicits$.MODULE$.RngProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Rng RngProduct14(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14) {
      return implicits$.MODULE$.RngProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Rng RngProduct13(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13) {
      return implicits$.MODULE$.RngProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Rng RngProduct12(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12) {
      return implicits$.MODULE$.RngProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Rng RngProduct11(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11) {
      return implicits$.MODULE$.RngProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Rng RngProduct10(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10) {
      return implicits$.MODULE$.RngProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Rng RngProduct9(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9) {
      return implicits$.MODULE$.RngProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Rng RngProduct8(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8) {
      return implicits$.MODULE$.RngProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Rng RngProduct7(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7) {
      return implicits$.MODULE$.RngProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Rng RngProduct6(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6) {
      return implicits$.MODULE$.RngProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Rng RngProduct5(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5) {
      return implicits$.MODULE$.RngProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Rng RngProduct4(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4) {
      return implicits$.MODULE$.RngProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Rng RngProduct3(final Rng _structure1, final Rng _structure2, final Rng _structure3) {
      return implicits$.MODULE$.RngProduct3(_structure1, _structure2, _structure3);
   }

   public static Rng RngProduct2(final Rng _structure1, final Rng _structure2) {
      return implicits$.MODULE$.RngProduct2(_structure1, _structure2);
   }

   public static Semiring SemiringProduct22(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20, final Semiring _structure21, final Semiring _structure22) {
      return implicits$.MODULE$.SemiringProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Semiring SemiringProduct21(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20, final Semiring _structure21) {
      return implicits$.MODULE$.SemiringProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Semiring SemiringProduct20(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20) {
      return implicits$.MODULE$.SemiringProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Semiring SemiringProduct19(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19) {
      return implicits$.MODULE$.SemiringProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Semiring SemiringProduct18(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18) {
      return implicits$.MODULE$.SemiringProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Semiring SemiringProduct17(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17) {
      return implicits$.MODULE$.SemiringProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Semiring SemiringProduct16(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16) {
      return implicits$.MODULE$.SemiringProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Semiring SemiringProduct15(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15) {
      return implicits$.MODULE$.SemiringProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Semiring SemiringProduct14(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14) {
      return implicits$.MODULE$.SemiringProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Semiring SemiringProduct13(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13) {
      return implicits$.MODULE$.SemiringProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Semiring SemiringProduct12(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12) {
      return implicits$.MODULE$.SemiringProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Semiring SemiringProduct11(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11) {
      return implicits$.MODULE$.SemiringProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Semiring SemiringProduct10(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10) {
      return implicits$.MODULE$.SemiringProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Semiring SemiringProduct9(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9) {
      return implicits$.MODULE$.SemiringProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Semiring SemiringProduct8(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8) {
      return implicits$.MODULE$.SemiringProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Semiring SemiringProduct7(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7) {
      return implicits$.MODULE$.SemiringProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Semiring SemiringProduct6(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6) {
      return implicits$.MODULE$.SemiringProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Semiring SemiringProduct5(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5) {
      return implicits$.MODULE$.SemiringProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Semiring SemiringProduct4(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4) {
      return implicits$.MODULE$.SemiringProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Semiring SemiringProduct3(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3) {
      return implicits$.MODULE$.SemiringProduct3(_structure1, _structure2, _structure3);
   }

   public static Semiring SemiringProduct2(final Semiring _structure1, final Semiring _structure2) {
      return implicits$.MODULE$.SemiringProduct2(_structure1, _structure2);
   }

   public static CommutativeGroup AbGroupProduct22(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20, final CommutativeGroup _structure21, final CommutativeGroup _structure22) {
      return implicits$.MODULE$.AbGroupProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static CommutativeGroup AbGroupProduct21(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20, final CommutativeGroup _structure21) {
      return implicits$.MODULE$.AbGroupProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static CommutativeGroup AbGroupProduct20(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20) {
      return implicits$.MODULE$.AbGroupProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static CommutativeGroup AbGroupProduct19(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19) {
      return implicits$.MODULE$.AbGroupProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static CommutativeGroup AbGroupProduct18(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18) {
      return implicits$.MODULE$.AbGroupProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static CommutativeGroup AbGroupProduct17(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17) {
      return implicits$.MODULE$.AbGroupProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static CommutativeGroup AbGroupProduct16(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16) {
      return implicits$.MODULE$.AbGroupProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static CommutativeGroup AbGroupProduct15(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15) {
      return implicits$.MODULE$.AbGroupProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static CommutativeGroup AbGroupProduct14(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14) {
      return implicits$.MODULE$.AbGroupProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static CommutativeGroup AbGroupProduct13(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13) {
      return implicits$.MODULE$.AbGroupProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static CommutativeGroup AbGroupProduct12(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12) {
      return implicits$.MODULE$.AbGroupProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static CommutativeGroup AbGroupProduct11(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11) {
      return implicits$.MODULE$.AbGroupProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static CommutativeGroup AbGroupProduct10(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10) {
      return implicits$.MODULE$.AbGroupProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static CommutativeGroup AbGroupProduct9(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9) {
      return implicits$.MODULE$.AbGroupProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static CommutativeGroup AbGroupProduct8(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8) {
      return implicits$.MODULE$.AbGroupProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static CommutativeGroup AbGroupProduct7(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7) {
      return implicits$.MODULE$.AbGroupProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static CommutativeGroup AbGroupProduct6(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6) {
      return implicits$.MODULE$.AbGroupProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static CommutativeGroup AbGroupProduct5(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5) {
      return implicits$.MODULE$.AbGroupProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static CommutativeGroup AbGroupProduct4(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4) {
      return implicits$.MODULE$.AbGroupProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static CommutativeGroup AbGroupProduct3(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3) {
      return implicits$.MODULE$.AbGroupProduct3(_structure1, _structure2, _structure3);
   }

   public static CommutativeGroup AbGroupProduct2(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return implicits$.MODULE$.AbGroupProduct2(_structure1, _structure2);
   }

   public static Group GroupProduct22(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20, final Group _structure21, final Group _structure22) {
      return implicits$.MODULE$.GroupProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Group GroupProduct21(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20, final Group _structure21) {
      return implicits$.MODULE$.GroupProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Group GroupProduct20(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20) {
      return implicits$.MODULE$.GroupProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Group GroupProduct19(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19) {
      return implicits$.MODULE$.GroupProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Group GroupProduct18(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18) {
      return implicits$.MODULE$.GroupProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Group GroupProduct17(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17) {
      return implicits$.MODULE$.GroupProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Group GroupProduct16(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16) {
      return implicits$.MODULE$.GroupProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Group GroupProduct15(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15) {
      return implicits$.MODULE$.GroupProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Group GroupProduct14(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14) {
      return implicits$.MODULE$.GroupProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Group GroupProduct13(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13) {
      return implicits$.MODULE$.GroupProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Group GroupProduct12(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12) {
      return implicits$.MODULE$.GroupProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Group GroupProduct11(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11) {
      return implicits$.MODULE$.GroupProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Group GroupProduct10(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10) {
      return implicits$.MODULE$.GroupProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Group GroupProduct9(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9) {
      return implicits$.MODULE$.GroupProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Group GroupProduct8(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8) {
      return implicits$.MODULE$.GroupProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Group GroupProduct7(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7) {
      return implicits$.MODULE$.GroupProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Group GroupProduct6(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6) {
      return implicits$.MODULE$.GroupProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Group GroupProduct5(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5) {
      return implicits$.MODULE$.GroupProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Group GroupProduct4(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4) {
      return implicits$.MODULE$.GroupProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Group GroupProduct3(final Group _structure1, final Group _structure2, final Group _structure3) {
      return implicits$.MODULE$.GroupProduct3(_structure1, _structure2, _structure3);
   }

   public static Group GroupProduct2(final Group _structure1, final Group _structure2) {
      return implicits$.MODULE$.GroupProduct2(_structure1, _structure2);
   }

   public static Monoid MonoidProduct22(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20, final Monoid _structure21, final Monoid _structure22) {
      return implicits$.MODULE$.MonoidProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Monoid MonoidProduct21(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20, final Monoid _structure21) {
      return implicits$.MODULE$.MonoidProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Monoid MonoidProduct20(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20) {
      return implicits$.MODULE$.MonoidProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Monoid MonoidProduct19(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19) {
      return implicits$.MODULE$.MonoidProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Monoid MonoidProduct18(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18) {
      return implicits$.MODULE$.MonoidProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Monoid MonoidProduct17(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17) {
      return implicits$.MODULE$.MonoidProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Monoid MonoidProduct16(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16) {
      return implicits$.MODULE$.MonoidProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Monoid MonoidProduct15(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15) {
      return implicits$.MODULE$.MonoidProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Monoid MonoidProduct14(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14) {
      return implicits$.MODULE$.MonoidProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Monoid MonoidProduct13(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13) {
      return implicits$.MODULE$.MonoidProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Monoid MonoidProduct12(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12) {
      return implicits$.MODULE$.MonoidProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Monoid MonoidProduct11(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11) {
      return implicits$.MODULE$.MonoidProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Monoid MonoidProduct10(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10) {
      return implicits$.MODULE$.MonoidProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Monoid MonoidProduct9(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9) {
      return implicits$.MODULE$.MonoidProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Monoid MonoidProduct8(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8) {
      return implicits$.MODULE$.MonoidProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Monoid MonoidProduct7(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7) {
      return implicits$.MODULE$.MonoidProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Monoid MonoidProduct6(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6) {
      return implicits$.MODULE$.MonoidProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Monoid MonoidProduct5(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5) {
      return implicits$.MODULE$.MonoidProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Monoid MonoidProduct4(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4) {
      return implicits$.MODULE$.MonoidProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Monoid MonoidProduct3(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3) {
      return implicits$.MODULE$.MonoidProduct3(_structure1, _structure2, _structure3);
   }

   public static Monoid MonoidProduct2(final Monoid _structure1, final Monoid _structure2) {
      return implicits$.MODULE$.MonoidProduct2(_structure1, _structure2);
   }

   public static Semigroup SemigroupProduct22(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20, final Semigroup _structure21, final Semigroup _structure22) {
      return implicits$.MODULE$.SemigroupProduct22(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public static Semigroup SemigroupProduct21(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20, final Semigroup _structure21) {
      return implicits$.MODULE$.SemigroupProduct21(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public static Semigroup SemigroupProduct20(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20) {
      return implicits$.MODULE$.SemigroupProduct20(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public static Semigroup SemigroupProduct19(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19) {
      return implicits$.MODULE$.SemigroupProduct19(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public static Semigroup SemigroupProduct18(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18) {
      return implicits$.MODULE$.SemigroupProduct18(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public static Semigroup SemigroupProduct17(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17) {
      return implicits$.MODULE$.SemigroupProduct17(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public static Semigroup SemigroupProduct16(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16) {
      return implicits$.MODULE$.SemigroupProduct16(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public static Semigroup SemigroupProduct15(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15) {
      return implicits$.MODULE$.SemigroupProduct15(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public static Semigroup SemigroupProduct14(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14) {
      return implicits$.MODULE$.SemigroupProduct14(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public static Semigroup SemigroupProduct13(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13) {
      return implicits$.MODULE$.SemigroupProduct13(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public static Semigroup SemigroupProduct12(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12) {
      return implicits$.MODULE$.SemigroupProduct12(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public static Semigroup SemigroupProduct11(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11) {
      return implicits$.MODULE$.SemigroupProduct11(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public static Semigroup SemigroupProduct10(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10) {
      return implicits$.MODULE$.SemigroupProduct10(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public static Semigroup SemigroupProduct9(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9) {
      return implicits$.MODULE$.SemigroupProduct9(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public static Semigroup SemigroupProduct8(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8) {
      return implicits$.MODULE$.SemigroupProduct8(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public static Semigroup SemigroupProduct7(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7) {
      return implicits$.MODULE$.SemigroupProduct7(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public static Semigroup SemigroupProduct6(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6) {
      return implicits$.MODULE$.SemigroupProduct6(_structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public static Semigroup SemigroupProduct5(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5) {
      return implicits$.MODULE$.SemigroupProduct5(_structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public static Semigroup SemigroupProduct4(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4) {
      return implicits$.MODULE$.SemigroupProduct4(_structure1, _structure2, _structure3, _structure4);
   }

   public static Semigroup SemigroupProduct3(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3) {
      return implicits$.MODULE$.SemigroupProduct3(_structure1, _structure2, _structure3);
   }

   public static Semigroup SemigroupProduct2(final Semigroup _structure1, final Semigroup _structure2) {
      return implicits$.MODULE$.SemigroupProduct2(_structure1, _structure2);
   }

   public static MapEq MapEq(final Eq V0) {
      return implicits$.MODULE$.MapEq(V0);
   }

   public static MapInnerProductSpace MapInnerProductSpace(final Field evidence$7) {
      return implicits$.MODULE$.MapInnerProductSpace(evidence$7);
   }

   public static MapVectorSpace MapVectorSpace(final Field evidence$6) {
      return implicits$.MODULE$.MapVectorSpace(evidence$6);
   }

   public static MapGroup MapGroup(final Group evidence$5) {
      return implicits$.MODULE$.MapGroup(evidence$5);
   }

   public static MapCRng MapCRng(final CommutativeRing evidence$4) {
      return implicits$.MODULE$.MapCRng(evidence$4);
   }

   public static MapCSemiring MapCSemiring(final CommutativeSemiring evidence$3) {
      return implicits$.MODULE$.MapCSemiring(evidence$3);
   }

   public static MapMonoid MapMonoid(final Semigroup evidence$2) {
      return implicits$.MODULE$.MapMonoid(evidence$2);
   }

   public static NormedVectorSpace SeqNormedVectorSpace(final Field field0, final NRoot nroot0, final Factory cbf0) {
      return implicits$.MODULE$.SeqNormedVectorSpace(field0, nroot0, cbf0);
   }

   public static SeqOrder SeqOrder(final Order A0) {
      return implicits$.MODULE$.SeqOrder(A0);
   }

   public static SeqInnerProductSpace SeqInnerProductSpace(final Field field0, final Factory cbf0) {
      return implicits$.MODULE$.SeqInnerProductSpace(field0, cbf0);
   }

   public static SeqEq SeqEq(final Eq A0) {
      return implicits$.MODULE$.SeqEq(A0);
   }

   public static SeqVectorSpace SeqVectorSpace(final Field field0, final Factory cbf0, final NotGiven ev) {
      return implicits$.MODULE$.SeqVectorSpace(field0, cbf0, ev);
   }

   public static SeqCModule SeqCModule(final CommutativeRing ring0, final Factory cbf0, final NotGiven ev) {
      return implicits$.MODULE$.SeqCModule(ring0, cbf0, ev);
   }

   public static Monoid ArrayMonoid(final ClassTag evidence$25) {
      return implicits$.MODULE$.ArrayMonoid(evidence$25);
   }

   public static NormedVectorSpace ArrayNormedVectorSpace(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return implicits$.MODULE$.ArrayNormedVectorSpace(evidence$22, evidence$23, evidence$24);
   }

   public static Order ArrayOrder(final Order evidence$21) {
      return implicits$.MODULE$.ArrayOrder(evidence$21);
   }

   public static InnerProductSpace ArrayInnerProductSpace(final Field evidence$19, final ClassTag evidence$20) {
      return implicits$.MODULE$.ArrayInnerProductSpace(evidence$19, evidence$20);
   }

   public static Eq ArrayEq(final Eq evidence$18) {
      return implicits$.MODULE$.ArrayEq(evidence$18);
   }

   public static VectorSpace ArrayVectorSpace(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return implicits$.MODULE$.ArrayVectorSpace(evidence$15, evidence$16, evidence$17);
   }

   public static CModule ArrayCModule(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return implicits$.MODULE$.ArrayCModule(evidence$12, evidence$13, evidence$14);
   }

   public static Monoid IterableMonoid(final Factory cbf) {
      return implicits$.MODULE$.IterableMonoid(cbf);
   }

   public static Order StringOrder() {
      return implicits$.MODULE$.StringOrder();
   }

   public static Monoid StringAlgebra() {
      return implicits$.MODULE$.StringAlgebra();
   }

   public static MetricSpace levenshteinDistance() {
      return implicits$.MODULE$.levenshteinDistance();
   }

   public static NumberTag BigDecimalTag() {
      return implicits$.MODULE$.BigDecimalTag();
   }

   public static MathContext BigDecimalIsTrig$default$1() {
      return implicits$.MODULE$.BigDecimalIsTrig$default$1();
   }

   public static BigDecimalIsTrig BigDecimalIsTrig(final MathContext mc) {
      return implicits$.MODULE$.BigDecimalIsTrig(mc);
   }

   public static Field BigDecimalAlgebra() {
      return implicits$.MODULE$.BigDecimalAlgebra();
   }

   public static NumberTag BigIntegerTag() {
      return implicits$.MODULE$.BigIntegerTag();
   }

   public static EuclideanRing BigIntegerAlgebra() {
      return implicits$.MODULE$.BigIntegerAlgebra();
   }

   public static NumberTag BigIntTag() {
      return implicits$.MODULE$.BigIntTag();
   }

   public static EuclideanRing BigIntAlgebra() {
      return implicits$.MODULE$.BigIntAlgebra();
   }

   public static NumberTag DoubleTag() {
      return implicits$.MODULE$.DoubleTag();
   }

   public static Field DoubleAlgebra() {
      return implicits$.MODULE$.DoubleAlgebra();
   }

   public static NumberTag FloatTag() {
      return implicits$.MODULE$.FloatTag();
   }

   public static Field FloatAlgebra() {
      return implicits$.MODULE$.FloatAlgebra();
   }

   public static NumberTag LongTag() {
      return implicits$.MODULE$.LongTag();
   }

   public static EuclideanRing LongAlgebra() {
      return implicits$.MODULE$.LongAlgebra();
   }

   public static BitString LongBitString() {
      return implicits$.MODULE$.LongBitString();
   }

   public static NumberTag IntTag() {
      return implicits$.MODULE$.IntTag();
   }

   public static EuclideanRing IntAlgebra() {
      return implicits$.MODULE$.IntAlgebra();
   }

   public static BitString IntBitString() {
      return implicits$.MODULE$.IntBitString();
   }

   public static NumberTag ShortTag() {
      return implicits$.MODULE$.ShortTag();
   }

   public static EuclideanRing ShortAlgebra() {
      return implicits$.MODULE$.ShortAlgebra();
   }

   public static BitString ShortBitString() {
      return implicits$.MODULE$.ShortBitString();
   }

   public static NumberTag ByteTag() {
      return implicits$.MODULE$.ByteTag();
   }

   public static EuclideanRing ByteAlgebra() {
      return implicits$.MODULE$.ByteAlgebra();
   }

   public static BitString ByteBitString() {
      return implicits$.MODULE$.ByteBitString();
   }

   public static Order CharAlgebra() {
      return implicits$.MODULE$.CharAlgebra();
   }

   public static Bool BooleanStructure() {
      return implicits$.MODULE$.BooleanStructure();
   }
}
