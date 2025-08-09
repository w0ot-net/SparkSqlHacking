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
import scala.runtime.BoxedUnit;
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
import spire.std.AbGroupProductInstances;
import spire.std.AnyInstances;
import spire.std.ArrayInstances;
import spire.std.ArrayInstances0;
import spire.std.ArrayInstances1;
import spire.std.ArrayInstances2;
import spire.std.ArrayInstances3;
import spire.std.BigDecimalInstances;
import spire.std.BigDecimalIsTrig;
import spire.std.BigIntInstances;
import spire.std.BigIntegerInstances;
import spire.std.BooleanInstances;
import spire.std.ByteInstances;
import spire.std.CharInstances;
import spire.std.DoubleInstances;
import spire.std.EqProductInstances;
import spire.std.FloatInstances;
import spire.std.GroupProductInstances;
import spire.std.IntInstances;
import spire.std.IterableInstances;
import spire.std.LongInstances;
import spire.std.MapCRng;
import spire.std.MapCSemiring;
import spire.std.MapEq;
import spire.std.MapGroup;
import spire.std.MapInnerProductSpace;
import spire.std.MapInstances0;
import spire.std.MapInstances1;
import spire.std.MapInstances2;
import spire.std.MapInstances3;
import spire.std.MapMonoid;
import spire.std.MapVectorSpace;
import spire.std.MonoidProductInstances;
import spire.std.OptionAdditiveMonoid;
import spire.std.OptionCMonoid;
import spire.std.OptionEq;
import spire.std.OptionInstances;
import spire.std.OptionInstances0;
import spire.std.OptionMonoid;
import spire.std.OptionMultiplicativeMonoid;
import spire.std.OptionOrder;
import spire.std.OrderProductInstances;
import spire.std.RigProductInstances;
import spire.std.RingProductInstances;
import spire.std.RngProductInstances;
import spire.std.SemigroupProductInstances;
import spire.std.SemiringProductInstances;
import spire.std.SeqCModule;
import spire.std.SeqEq;
import spire.std.SeqInnerProductSpace;
import spire.std.SeqInstances0;
import spire.std.SeqInstances1;
import spire.std.SeqInstances2;
import spire.std.SeqInstances3;
import spire.std.SeqOrder;
import spire.std.SeqVectorSpace;
import spire.std.ShortInstances;
import spire.std.StringInstances;
import spire.std.StringInstances0;
import spire.std.UnitInstances;
import spire.syntax.ActionSyntax;
import spire.syntax.AdditiveGroupOps;
import spire.syntax.AdditiveGroupSyntax;
import spire.syntax.AdditiveMonoidOps;
import spire.syntax.AdditiveMonoidSyntax;
import spire.syntax.AdditiveSemigroupOps;
import spire.syntax.AdditiveSemigroupSyntax;
import spire.syntax.AllSyntax;
import spire.syntax.BitStringOps;
import spire.syntax.BitStringSyntax;
import spire.syntax.BoolOps;
import spire.syntax.BoolSyntax;
import spire.syntax.CforSyntax;
import spire.syntax.ConvertableFromOps;
import spire.syntax.ConvertableFromSyntax;
import spire.syntax.CoordinateSpaceOps;
import spire.syntax.CoordinateSpaceSyntax;
import spire.syntax.EqOps;
import spire.syntax.EqSyntax;
import spire.syntax.EuclideanRingOps;
import spire.syntax.EuclideanRingSyntax;
import spire.syntax.GCDRingOps;
import spire.syntax.GCDRingSyntax;
import spire.syntax.GroupOps;
import spire.syntax.GroupSyntax;
import spire.syntax.GroupoidCommonOps;
import spire.syntax.GroupoidOps;
import spire.syntax.GroupoidSyntax;
import spire.syntax.HeytingOps;
import spire.syntax.HeytingSyntax;
import spire.syntax.InnerProductSpaceOps;
import spire.syntax.InnerProductSpaceSyntax;
import spire.syntax.IntegralSyntax;
import spire.syntax.IntervalPointOps;
import spire.syntax.IntervalSyntax;
import spire.syntax.InvolutionOps;
import spire.syntax.InvolutionSyntax;
import spire.syntax.IsRealOps;
import spire.syntax.IsRealSyntax;
import spire.syntax.JoinOps;
import spire.syntax.LatticeSyntax;
import spire.syntax.LeftActionOps;
import spire.syntax.LeftModuleOps;
import spire.syntax.LeftModuleSyntax;
import spire.syntax.LeftPartialActionOps;
import spire.syntax.LiteralsSyntax;
import spire.syntax.LogicOps;
import spire.syntax.LogicSyntax;
import spire.syntax.MeetOps;
import spire.syntax.MetricSpaceOps;
import spire.syntax.MetricSpaceSyntax;
import spire.syntax.MonoidOps;
import spire.syntax.MonoidSyntax;
import spire.syntax.MultiplicativeGroupOps;
import spire.syntax.MultiplicativeGroupSyntax;
import spire.syntax.MultiplicativeMonoidOps;
import spire.syntax.MultiplicativeMonoidSyntax;
import spire.syntax.MultiplicativeSemigroupOps;
import spire.syntax.MultiplicativeSemigroupSyntax;
import spire.syntax.NRootOps;
import spire.syntax.NRootSyntax;
import spire.syntax.NormedVectorSpaceOps;
import spire.syntax.NormedVectorSpaceSyntax;
import spire.syntax.OrderOps;
import spire.syntax.OrderSyntax;
import spire.syntax.PartialActionSyntax;
import spire.syntax.PartialOrderOps;
import spire.syntax.PartialOrderSyntax;
import spire.syntax.RightActionOps;
import spire.syntax.RightModuleOps;
import spire.syntax.RightModuleSyntax;
import spire.syntax.RightPartialActionOps;
import spire.syntax.SemigroupOps;
import spire.syntax.SemigroupSyntax;
import spire.syntax.SemigroupoidOps;
import spire.syntax.SemigroupoidSyntax;
import spire.syntax.SemiringOps;
import spire.syntax.SemiringSyntax;
import spire.syntax.SignedOps;
import spire.syntax.SignedSyntax;
import spire.syntax.TorsorPointOps;
import spire.syntax.TorsorSyntax;
import spire.syntax.TrigOps;
import spire.syntax.TrigSyntax;
import spire.syntax.TruncatedDivisionOps;
import spire.syntax.TruncatedDivisionSyntax;
import spire.syntax.VectorSpaceOps;
import spire.syntax.VectorSpaceSyntax;
import spire.syntax.std.ArrayOps;
import spire.syntax.std.ArraySyntax;
import spire.syntax.std.BigIntSyntax;
import spire.syntax.std.DoubleSyntax;
import spire.syntax.std.IndexedSeqOps;
import spire.syntax.std.IntSyntax;
import spire.syntax.std.LongSyntax;
import spire.syntax.std.SeqOps;
import spire.syntax.std.SeqSyntax;

public final class implicits$ implements AnyInstances, AllSyntax {
   public static final implicits$ MODULE$ = new implicits$();
   private static volatile LiteralsSyntax.radix$ radix$module;
   private static volatile LiteralsSyntax.si$ si$module;
   private static volatile LiteralsSyntax.us$ us$module;
   private static volatile LiteralsSyntax.eu$ eu$module;
   private static CommutativeGroup UnitAlgebra;
   private static Monoid StringAlgebra;
   private static Order StringOrder;
   private static Field BigDecimalAlgebra;
   private static NumberTag BigDecimalTag;
   private static EuclideanRing BigIntegerAlgebra;
   private static NumberTag BigIntegerTag;
   private static EuclideanRing BigIntAlgebra;
   private static NumberTag BigIntTag;
   private static Field DoubleAlgebra;
   private static NumberTag DoubleTag;
   private static Field FloatAlgebra;
   private static NumberTag FloatTag;
   private static BitString LongBitString;
   private static EuclideanRing LongAlgebra;
   private static NumberTag LongTag;
   private static BitString IntBitString;
   private static EuclideanRing IntAlgebra;
   private static NumberTag IntTag;
   private static BitString ShortBitString;
   private static EuclideanRing ShortAlgebra;
   private static NumberTag ShortTag;
   private static BitString ByteBitString;
   private static EuclideanRing ByteAlgebra;
   private static NumberTag ByteTag;
   private static Order CharAlgebra;
   private static Bool BooleanStructure;

   static {
      BooleanInstances.$init$(MODULE$);
      CharInstances.$init$(MODULE$);
      ByteInstances.$init$(MODULE$);
      ShortInstances.$init$(MODULE$);
      IntInstances.$init$(MODULE$);
      LongInstances.$init$(MODULE$);
      FloatInstances.$init$(MODULE$);
      DoubleInstances.$init$(MODULE$);
      BigIntInstances.$init$(MODULE$);
      BigIntegerInstances.$init$(MODULE$);
      BigDecimalInstances.$init$(MODULE$);
      StringInstances0.$init$(MODULE$);
      StringInstances.$init$(MODULE$);
      IterableInstances.$init$(MODULE$);
      ArrayInstances0.$init$(MODULE$);
      ArrayInstances1.$init$(MODULE$);
      ArrayInstances2.$init$(MODULE$);
      ArrayInstances3.$init$(MODULE$);
      ArrayInstances.$init$(MODULE$);
      SeqInstances0.$init$(MODULE$);
      SeqInstances1.$init$(MODULE$);
      SeqInstances2.$init$(MODULE$);
      SeqInstances3.$init$(MODULE$);
      MapInstances0.$init$(MODULE$);
      MapInstances1.$init$(MODULE$);
      MapInstances2.$init$(MODULE$);
      MapInstances3.$init$(MODULE$);
      SemigroupProductInstances.$init$(MODULE$);
      MonoidProductInstances.$init$(MODULE$);
      GroupProductInstances.$init$(MODULE$);
      AbGroupProductInstances.$init$(MODULE$);
      SemiringProductInstances.$init$(MODULE$);
      RngProductInstances.$init$(MODULE$);
      RigProductInstances.$init$(MODULE$);
      RingProductInstances.$init$(MODULE$);
      EqProductInstances.$init$(MODULE$);
      OrderProductInstances.$init$(MODULE$);
      OptionInstances0.$init$(MODULE$);
      OptionInstances.$init$(MODULE$);
      UnitInstances.$init$(MODULE$);
      LiteralsSyntax.$init$(MODULE$);
      CforSyntax.$init$(MODULE$);
      EqSyntax.$init$(MODULE$);
      PartialOrderSyntax.$init$(MODULE$);
      OrderSyntax.$init$(MODULE$);
      SignedSyntax.$init$(MODULE$);
      TruncatedDivisionSyntax.$init$(MODULE$);
      InvolutionSyntax.$init$(MODULE$);
      IsRealSyntax.$init$(MODULE$);
      ConvertableFromSyntax.$init$(MODULE$);
      SemigroupoidSyntax.$init$(MODULE$);
      GroupoidSyntax.$init$(MODULE$);
      AdditiveSemigroupSyntax.$init$(MODULE$);
      AdditiveMonoidSyntax.$init$(MODULE$);
      AdditiveGroupSyntax.$init$(MODULE$);
      MultiplicativeSemigroupSyntax.$init$(MODULE$);
      MultiplicativeMonoidSyntax.$init$(MODULE$);
      MultiplicativeGroupSyntax.$init$(MODULE$);
      SemiringSyntax.$init$(MODULE$);
      GCDRingSyntax.$init$(MODULE$);
      EuclideanRingSyntax.$init$(MODULE$);
      NRootSyntax.$init$(MODULE$);
      TrigSyntax.$init$(MODULE$);
      IntervalSyntax.$init$(MODULE$);
      LeftModuleSyntax.$init$(MODULE$);
      RightModuleSyntax.$init$(MODULE$);
      VectorSpaceSyntax.$init$(MODULE$);
      MetricSpaceSyntax.$init$(MODULE$);
      NormedVectorSpaceSyntax.$init$(MODULE$);
      InnerProductSpaceSyntax.$init$(MODULE$);
      CoordinateSpaceSyntax.$init$(MODULE$);
      LatticeSyntax.$init$(MODULE$);
      LogicSyntax.$init$(MODULE$);
      HeytingSyntax.$init$(MODULE$);
      BoolSyntax.$init$(MODULE$);
      BitStringSyntax.$init$(MODULE$);
      PartialActionSyntax.$init$(MODULE$);
      ActionSyntax.$init$(MODULE$);
      TorsorSyntax.$init$(MODULE$);
      IntegralSyntax.$init$(MODULE$);
      IntSyntax.$init$(MODULE$);
      LongSyntax.$init$(MODULE$);
      DoubleSyntax.$init$(MODULE$);
      BigIntSyntax.$init$(MODULE$);
      ArraySyntax.$init$(MODULE$);
      SeqSyntax.$init$(MODULE$);
      SemigroupSyntax.$init$(MODULE$);
      MonoidSyntax.$init$(MODULE$);
      GroupSyntax.$init$(MODULE$);
   }

   public GroupOps groupOps(final Object a, final Group evidence$10) {
      return GroupSyntax.groupOps$(this, a, evidence$10);
   }

   public MonoidOps monoidOps(final Object a, final Monoid ev) {
      return MonoidSyntax.monoidOps$(this, a, ev);
   }

   public SemigroupOps semigroupOps(final Object a, final Semigroup evidence$9) {
      return SemigroupSyntax.semigroupOps$(this, a, evidence$9);
   }

   public SeqOps seqOps(final Iterable lhs) {
      return SeqSyntax.seqOps$(this, lhs);
   }

   public IndexedSeqOps indexedSeqOps(final IndexedSeq lhs) {
      return SeqSyntax.indexedSeqOps$(this, lhs);
   }

   public ArrayOps arrayOps(final Object lhs) {
      return ArraySyntax.arrayOps$(this, lhs);
   }

   public ArrayOps arrayOps$mZc$sp(final boolean[] lhs) {
      return ArraySyntax.arrayOps$mZc$sp$(this, lhs);
   }

   public ArrayOps arrayOps$mBc$sp(final byte[] lhs) {
      return ArraySyntax.arrayOps$mBc$sp$(this, lhs);
   }

   public ArrayOps arrayOps$mCc$sp(final char[] lhs) {
      return ArraySyntax.arrayOps$mCc$sp$(this, lhs);
   }

   public ArrayOps arrayOps$mDc$sp(final double[] lhs) {
      return ArraySyntax.arrayOps$mDc$sp$(this, lhs);
   }

   public ArrayOps arrayOps$mFc$sp(final float[] lhs) {
      return ArraySyntax.arrayOps$mFc$sp$(this, lhs);
   }

   public ArrayOps arrayOps$mIc$sp(final int[] lhs) {
      return ArraySyntax.arrayOps$mIc$sp$(this, lhs);
   }

   public ArrayOps arrayOps$mJc$sp(final long[] lhs) {
      return ArraySyntax.arrayOps$mJc$sp$(this, lhs);
   }

   public ArrayOps arrayOps$mSc$sp(final short[] lhs) {
      return ArraySyntax.arrayOps$mSc$sp$(this, lhs);
   }

   public ArrayOps arrayOps$mVc$sp(final BoxedUnit[] lhs) {
      return ArraySyntax.arrayOps$mVc$sp$(this, lhs);
   }

   public BigInt literalBigIntOps(final BigInt b) {
      return BigIntSyntax.literalBigIntOps$(this, b);
   }

   public double literalDoubleOps(final double n) {
      return DoubleSyntax.literalDoubleOps$(this, n);
   }

   public long literalLongOps(final long n) {
      return LongSyntax.literalLongOps$(this, n);
   }

   public int literalIntOps(final int n) {
      return IntSyntax.literalIntOps$(this, n);
   }

   public Object intToA(final int n, final ConvertableTo c) {
      return IntSyntax.intToA$(this, n, c);
   }

   public IntegralOps integralOps(final Object a, final Integral evidence$28) {
      return IntegralSyntax.integralOps$(this, a, evidence$28);
   }

   public TorsorPointOps torsorPointOps(final Object p) {
      return TorsorSyntax.torsorPointOps$(this, p);
   }

   public LeftActionOps leftActionOps(final Object g) {
      return ActionSyntax.leftActionOps$(this, g);
   }

   public RightActionOps rightActionOps(final Object p) {
      return ActionSyntax.rightActionOps$(this, p);
   }

   public LeftPartialActionOps leftPartialActionOps(final Object g) {
      return PartialActionSyntax.leftPartialActionOps$(this, g);
   }

   public RightPartialActionOps rightPartialActionOps(final Object p) {
      return PartialActionSyntax.rightPartialActionOps$(this, p);
   }

   public BitStringOps bitStringOps(final Object a, final BitString evidence$25) {
      return BitStringSyntax.bitStringOps$(this, a, evidence$25);
   }

   public BoolOps boolOps(final Object a, final Bool evidence$24) {
      return BoolSyntax.boolOps$(this, a, evidence$24);
   }

   public HeytingOps heytingOps(final Object a, final Heyting evidence$22) {
      return HeytingSyntax.heytingOps$(this, a, evidence$22);
   }

   public LogicOps logicOps(final Object a, final Logic evidence$23) {
      return LogicSyntax.logicOps$(this, a, evidence$23);
   }

   public MeetOps meetOps(final Object a, final MeetSemilattice evidence$20) {
      return LatticeSyntax.meetOps$(this, a, evidence$20);
   }

   public JoinOps joinOps(final Object a, final JoinSemilattice evidence$21) {
      return LatticeSyntax.joinOps$(this, a, evidence$21);
   }

   public CoordinateSpaceOps coordinateSpaceOps(final Object v) {
      return CoordinateSpaceSyntax.coordinateSpaceOps$(this, v);
   }

   public InnerProductSpaceOps innerProductSpaceOps(final Object v) {
      return InnerProductSpaceSyntax.innerProductSpaceOps$(this, v);
   }

   public NormedVectorSpaceOps normedVectorSpaceOps(final Object v) {
      return NormedVectorSpaceSyntax.normedVectorSpaceOps$(this, v);
   }

   public MetricSpaceOps metricSpaceOps(final Object v) {
      return MetricSpaceSyntax.metricSpaceOps$(this, v);
   }

   public VectorSpaceOps vectorSpaceOps(final Object v) {
      return VectorSpaceSyntax.vectorSpaceOps$(this, v);
   }

   public RightModuleOps rightModuleOps(final Object v) {
      return RightModuleSyntax.rightModuleOps$(this, v);
   }

   public LeftModuleOps leftModuleOps(final Object v) {
      return LeftModuleSyntax.leftModuleOps$(this, v);
   }

   public IntervalPointOps intervalOps(final Object a, final Order evidence$26, final AdditiveGroup evidence$27) {
      return IntervalSyntax.intervalOps$(this, a, evidence$26, evidence$27);
   }

   public TrigOps trigOps(final Object a, final Trig evidence$19) {
      return TrigSyntax.trigOps$(this, a, evidence$19);
   }

   public NRootOps nrootOps(final Object a, final NRoot evidence$18) {
      return NRootSyntax.nrootOps$(this, a, evidence$18);
   }

   public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
      return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
   }

   public int literalIntEuclideanRingOps(final int lhs) {
      return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
   }

   public long literalLongEuclideanRingOps(final long lhs) {
      return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
   }

   public double literalDoubleEuclideanRingOps(final double lhs) {
      return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
   }

   public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
      return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
   }

   public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
      return SemiringSyntax.semiringOps$(this, a, evidence$15);
   }

   public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
      return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
   }

   public int literalIntMultiplicativeGroupOps(final int lhs) {
      return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
   }

   public long literalLongMultiplicativeGroupOps(final long lhs) {
      return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
   }

   public double literalDoubleMultiplicativeGroupOps(final double lhs) {
      return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
   }

   public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
      return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
   }

   public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
      return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
   }

   public int literalIntMultiplicativeSemigroupOps(final int lhs) {
      return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
   }

   public long literalLongMultiplicativeSemigroupOps(final long lhs) {
      return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
   }

   public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
      return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
   }

   public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
      return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
   }

   public int literalIntAdditiveGroupOps(final int lhs) {
      return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
   }

   public long literalLongAdditiveGroupOps(final long lhs) {
      return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
   }

   public double literalDoubleAdditiveGroupOps(final double lhs) {
      return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
   }

   public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
      return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
   }

   public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
      return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
   }

   public int literalIntAdditiveSemigroupOps(final int lhs) {
      return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
   }

   public long literalLongAdditiveSemigroupOps(final long lhs) {
      return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
   }

   public double literalDoubleAdditiveSemigroupOps(final double lhs) {
      return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
   }

   public GroupoidCommonOps groupoidCommonOps(final Object a, final Groupoid ev, final NotGiven ni) {
      return GroupoidSyntax.groupoidCommonOps$(this, a, ev, ni);
   }

   public GroupoidOps groupoidOps(final Object a, final Groupoid ev) {
      return GroupoidSyntax.groupoidOps$(this, a, ev);
   }

   public SemigroupoidOps semigroupoidOps(final Object a, final Semigroupoid evidence$8) {
      return SemigroupoidSyntax.semigroupoidOps$(this, a, evidence$8);
   }

   public ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
      return ConvertableFromSyntax.convertableOps$(this, a, evidence$29);
   }

   public IsRealOps isRealOps(final Object a, final IsReal evidence$7) {
      return IsRealSyntax.isRealOps$(this, a, evidence$7);
   }

   public InvolutionOps involutionOps(final Object lhs, final Involution evidence$6) {
      return InvolutionSyntax.involutionOps$(this, lhs, evidence$6);
   }

   public TruncatedDivisionOps truncatedDivisionOps(final Object a, final TruncatedDivision evidence$5) {
      return TruncatedDivisionSyntax.truncatedDivisionOps$(this, a, evidence$5);
   }

   public int literalIntTruncatedDivisionOps(final int lhs) {
      return TruncatedDivisionSyntax.literalIntTruncatedDivisionOps$(this, lhs);
   }

   public long literalLongTruncatedDivisionOps(final long lhs) {
      return TruncatedDivisionSyntax.literalLongTruncatedDivisionOps$(this, lhs);
   }

   public double literalDoubleTruncatedDivisionOps(final double lhs) {
      return TruncatedDivisionSyntax.literalDoubleTruncatedDivisionOps$(this, lhs);
   }

   public SignedOps signedOps(final Object a, final Signed evidence$4) {
      return SignedSyntax.signedOps$(this, a, evidence$4);
   }

   public OrderOps orderOps(final Object a, final Order evidence$3) {
      return OrderSyntax.orderOps$(this, a, evidence$3);
   }

   public int literalIntOrderOps(final int lhs) {
      return OrderSyntax.literalIntOrderOps$(this, lhs);
   }

   public long literalLongOrderOps(final long lhs) {
      return OrderSyntax.literalLongOrderOps$(this, lhs);
   }

   public double literalDoubleOrderOps(final double lhs) {
      return OrderSyntax.literalDoubleOrderOps$(this, lhs);
   }

   public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
      return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
   }

   public EqOps eqOps(final Object a, final Eq evidence$1) {
      return EqSyntax.eqOps$(this, a, evidence$1);
   }

   public StringContext literals(final StringContext s) {
      return LiteralsSyntax.literals$(this, s);
   }

   public OptionCMonoid OptionCMonoid(final CommutativeSemigroup evidence$9) {
      return OptionInstances.OptionCMonoid$(this, evidence$9);
   }

   public OptionAdditiveMonoid OptionAdditiveMonoid(final AdditiveSemigroup evidence$10) {
      return OptionInstances.OptionAdditiveMonoid$(this, evidence$10);
   }

   public OptionMultiplicativeMonoid OptionMultiplicativeMonoid(final MultiplicativeSemigroup evidence$11) {
      return OptionInstances.OptionMultiplicativeMonoid$(this, evidence$11);
   }

   public OptionOrder OptionOrder(final Order evidence$12) {
      return OptionInstances.OptionOrder$(this, evidence$12);
   }

   public OptionEq OptionEq(final Eq evidence$7) {
      return OptionInstances0.OptionEq$(this, evidence$7);
   }

   public OptionMonoid OptionMonoid(final Semigroup evidence$8) {
      return OptionInstances0.OptionMonoid$(this, evidence$8);
   }

   public Order OrderProduct2(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mDDc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mDFc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mDIc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mDJc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mFDc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mFFc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mFIc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mFJc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mIDc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mIFc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mIIc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mIJc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mJDc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mJFc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mJIc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct2$mJJc$sp(final Order _structure1, final Order _structure2) {
      return OrderProductInstances.OrderProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Order OrderProduct3(final Order _structure1, final Order _structure2, final Order _structure3) {
      return OrderProductInstances.OrderProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Order OrderProduct4(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4) {
      return OrderProductInstances.OrderProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Order OrderProduct5(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5) {
      return OrderProductInstances.OrderProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Order OrderProduct6(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6) {
      return OrderProductInstances.OrderProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Order OrderProduct7(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7) {
      return OrderProductInstances.OrderProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Order OrderProduct8(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8) {
      return OrderProductInstances.OrderProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Order OrderProduct9(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9) {
      return OrderProductInstances.OrderProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Order OrderProduct10(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10) {
      return OrderProductInstances.OrderProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Order OrderProduct11(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11) {
      return OrderProductInstances.OrderProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Order OrderProduct12(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12) {
      return OrderProductInstances.OrderProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Order OrderProduct13(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13) {
      return OrderProductInstances.OrderProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Order OrderProduct14(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14) {
      return OrderProductInstances.OrderProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Order OrderProduct15(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15) {
      return OrderProductInstances.OrderProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Order OrderProduct16(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16) {
      return OrderProductInstances.OrderProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Order OrderProduct17(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17) {
      return OrderProductInstances.OrderProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Order OrderProduct18(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18) {
      return OrderProductInstances.OrderProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Order OrderProduct19(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19) {
      return OrderProductInstances.OrderProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Order OrderProduct20(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20) {
      return OrderProductInstances.OrderProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Order OrderProduct21(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20, final Order _structure21) {
      return OrderProductInstances.OrderProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Order OrderProduct22(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20, final Order _structure21, final Order _structure22) {
      return OrderProductInstances.OrderProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public Eq EqProduct2(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mDDc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mDFc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mDIc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mDJc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mFDc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mFFc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mFIc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mFJc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mIDc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mIFc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mIIc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mIJc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mJDc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mJFc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mJIc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct2$mJJc$sp(final Eq _structure1, final Eq _structure2) {
      return EqProductInstances.EqProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Eq EqProduct3(final Eq _structure1, final Eq _structure2, final Eq _structure3) {
      return EqProductInstances.EqProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Eq EqProduct4(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4) {
      return EqProductInstances.EqProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Eq EqProduct5(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5) {
      return EqProductInstances.EqProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Eq EqProduct6(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6) {
      return EqProductInstances.EqProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Eq EqProduct7(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7) {
      return EqProductInstances.EqProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Eq EqProduct8(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8) {
      return EqProductInstances.EqProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Eq EqProduct9(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9) {
      return EqProductInstances.EqProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Eq EqProduct10(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10) {
      return EqProductInstances.EqProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Eq EqProduct11(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11) {
      return EqProductInstances.EqProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Eq EqProduct12(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12) {
      return EqProductInstances.EqProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Eq EqProduct13(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13) {
      return EqProductInstances.EqProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Eq EqProduct14(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14) {
      return EqProductInstances.EqProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Eq EqProduct15(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15) {
      return EqProductInstances.EqProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Eq EqProduct16(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16) {
      return EqProductInstances.EqProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Eq EqProduct17(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17) {
      return EqProductInstances.EqProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Eq EqProduct18(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18) {
      return EqProductInstances.EqProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Eq EqProduct19(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19) {
      return EqProductInstances.EqProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Eq EqProduct20(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20) {
      return EqProductInstances.EqProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Eq EqProduct21(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20, final Eq _structure21) {
      return EqProductInstances.EqProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Eq EqProduct22(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20, final Eq _structure21, final Eq _structure22) {
      return EqProductInstances.EqProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public Ring RingProduct2(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mDDc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mDFc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mDIc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mDJc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mFDc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mFFc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mFIc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mFJc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mIDc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mIFc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mIIc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mIJc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mJDc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mJFc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mJIc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct2$mJJc$sp(final Ring _structure1, final Ring _structure2) {
      return RingProductInstances.RingProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Ring RingProduct3(final Ring _structure1, final Ring _structure2, final Ring _structure3) {
      return RingProductInstances.RingProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Ring RingProduct4(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4) {
      return RingProductInstances.RingProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Ring RingProduct5(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5) {
      return RingProductInstances.RingProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Ring RingProduct6(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6) {
      return RingProductInstances.RingProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Ring RingProduct7(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7) {
      return RingProductInstances.RingProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Ring RingProduct8(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8) {
      return RingProductInstances.RingProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Ring RingProduct9(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9) {
      return RingProductInstances.RingProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Ring RingProduct10(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10) {
      return RingProductInstances.RingProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Ring RingProduct11(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11) {
      return RingProductInstances.RingProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Ring RingProduct12(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12) {
      return RingProductInstances.RingProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Ring RingProduct13(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13) {
      return RingProductInstances.RingProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Ring RingProduct14(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14) {
      return RingProductInstances.RingProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Ring RingProduct15(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15) {
      return RingProductInstances.RingProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Ring RingProduct16(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16) {
      return RingProductInstances.RingProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Ring RingProduct17(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17) {
      return RingProductInstances.RingProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Ring RingProduct18(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18) {
      return RingProductInstances.RingProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Ring RingProduct19(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19) {
      return RingProductInstances.RingProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Ring RingProduct20(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20) {
      return RingProductInstances.RingProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Ring RingProduct21(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20, final Ring _structure21) {
      return RingProductInstances.RingProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Ring RingProduct22(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20, final Ring _structure21, final Ring _structure22) {
      return RingProductInstances.RingProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public Rig RigProduct2(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mDDc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mDFc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mDIc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mDJc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mFDc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mFFc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mFIc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mFJc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mIDc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mIFc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mIIc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mIJc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mJDc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mJFc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mJIc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct2$mJJc$sp(final Rig _structure1, final Rig _structure2) {
      return RigProductInstances.RigProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Rig RigProduct3(final Rig _structure1, final Rig _structure2, final Rig _structure3) {
      return RigProductInstances.RigProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Rig RigProduct4(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4) {
      return RigProductInstances.RigProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Rig RigProduct5(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5) {
      return RigProductInstances.RigProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Rig RigProduct6(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6) {
      return RigProductInstances.RigProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Rig RigProduct7(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7) {
      return RigProductInstances.RigProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Rig RigProduct8(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8) {
      return RigProductInstances.RigProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Rig RigProduct9(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9) {
      return RigProductInstances.RigProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Rig RigProduct10(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10) {
      return RigProductInstances.RigProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Rig RigProduct11(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11) {
      return RigProductInstances.RigProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Rig RigProduct12(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12) {
      return RigProductInstances.RigProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Rig RigProduct13(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13) {
      return RigProductInstances.RigProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Rig RigProduct14(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14) {
      return RigProductInstances.RigProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Rig RigProduct15(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15) {
      return RigProductInstances.RigProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Rig RigProduct16(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16) {
      return RigProductInstances.RigProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Rig RigProduct17(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17) {
      return RigProductInstances.RigProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Rig RigProduct18(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18) {
      return RigProductInstances.RigProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Rig RigProduct19(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19) {
      return RigProductInstances.RigProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Rig RigProduct20(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20) {
      return RigProductInstances.RigProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Rig RigProduct21(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20, final Rig _structure21) {
      return RigProductInstances.RigProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Rig RigProduct22(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20, final Rig _structure21, final Rig _structure22) {
      return RigProductInstances.RigProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public Rng RngProduct2(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mDDc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mDFc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mDIc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mDJc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mFDc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mFFc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mFIc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mFJc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mIDc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mIFc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mIIc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mIJc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mJDc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mJFc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mJIc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct2$mJJc$sp(final Rng _structure1, final Rng _structure2) {
      return RngProductInstances.RngProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Rng RngProduct3(final Rng _structure1, final Rng _structure2, final Rng _structure3) {
      return RngProductInstances.RngProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Rng RngProduct4(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4) {
      return RngProductInstances.RngProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Rng RngProduct5(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5) {
      return RngProductInstances.RngProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Rng RngProduct6(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6) {
      return RngProductInstances.RngProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Rng RngProduct7(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7) {
      return RngProductInstances.RngProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Rng RngProduct8(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8) {
      return RngProductInstances.RngProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Rng RngProduct9(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9) {
      return RngProductInstances.RngProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Rng RngProduct10(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10) {
      return RngProductInstances.RngProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Rng RngProduct11(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11) {
      return RngProductInstances.RngProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Rng RngProduct12(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12) {
      return RngProductInstances.RngProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Rng RngProduct13(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13) {
      return RngProductInstances.RngProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Rng RngProduct14(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14) {
      return RngProductInstances.RngProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Rng RngProduct15(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15) {
      return RngProductInstances.RngProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Rng RngProduct16(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16) {
      return RngProductInstances.RngProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Rng RngProduct17(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17) {
      return RngProductInstances.RngProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Rng RngProduct18(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18) {
      return RngProductInstances.RngProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Rng RngProduct19(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19) {
      return RngProductInstances.RngProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Rng RngProduct20(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20) {
      return RngProductInstances.RngProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Rng RngProduct21(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20, final Rng _structure21) {
      return RngProductInstances.RngProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Rng RngProduct22(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20, final Rng _structure21, final Rng _structure22) {
      return RngProductInstances.RngProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public Semiring SemiringProduct2(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mDDc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mDFc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mDIc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mDJc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mFDc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mFFc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mFIc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mFJc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mIDc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mIFc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mIIc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mIJc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mJDc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mJFc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mJIc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct2$mJJc$sp(final Semiring _structure1, final Semiring _structure2) {
      return SemiringProductInstances.SemiringProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Semiring SemiringProduct3(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3) {
      return SemiringProductInstances.SemiringProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Semiring SemiringProduct4(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4) {
      return SemiringProductInstances.SemiringProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Semiring SemiringProduct5(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5) {
      return SemiringProductInstances.SemiringProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Semiring SemiringProduct6(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6) {
      return SemiringProductInstances.SemiringProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Semiring SemiringProduct7(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7) {
      return SemiringProductInstances.SemiringProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Semiring SemiringProduct8(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8) {
      return SemiringProductInstances.SemiringProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Semiring SemiringProduct9(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9) {
      return SemiringProductInstances.SemiringProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Semiring SemiringProduct10(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10) {
      return SemiringProductInstances.SemiringProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Semiring SemiringProduct11(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11) {
      return SemiringProductInstances.SemiringProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Semiring SemiringProduct12(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12) {
      return SemiringProductInstances.SemiringProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Semiring SemiringProduct13(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13) {
      return SemiringProductInstances.SemiringProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Semiring SemiringProduct14(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14) {
      return SemiringProductInstances.SemiringProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Semiring SemiringProduct15(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15) {
      return SemiringProductInstances.SemiringProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Semiring SemiringProduct16(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16) {
      return SemiringProductInstances.SemiringProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Semiring SemiringProduct17(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17) {
      return SemiringProductInstances.SemiringProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Semiring SemiringProduct18(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18) {
      return SemiringProductInstances.SemiringProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Semiring SemiringProduct19(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19) {
      return SemiringProductInstances.SemiringProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Semiring SemiringProduct20(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20) {
      return SemiringProductInstances.SemiringProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Semiring SemiringProduct21(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20, final Semiring _structure21) {
      return SemiringProductInstances.SemiringProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Semiring SemiringProduct22(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20, final Semiring _structure21, final Semiring _structure22) {
      return SemiringProductInstances.SemiringProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public CommutativeGroup AbGroupProduct2(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mDDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mDFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mDIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mDJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mFDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mFFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mFIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mFJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mIDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mIFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mIIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mIJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mJDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mJFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mJIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct2$mJJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
      return AbGroupProductInstances.AbGroupProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public CommutativeGroup AbGroupProduct3(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3) {
      return AbGroupProductInstances.AbGroupProduct3$(this, _structure1, _structure2, _structure3);
   }

   public CommutativeGroup AbGroupProduct4(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4) {
      return AbGroupProductInstances.AbGroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public CommutativeGroup AbGroupProduct5(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5) {
      return AbGroupProductInstances.AbGroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public CommutativeGroup AbGroupProduct6(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6) {
      return AbGroupProductInstances.AbGroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public CommutativeGroup AbGroupProduct7(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7) {
      return AbGroupProductInstances.AbGroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public CommutativeGroup AbGroupProduct8(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8) {
      return AbGroupProductInstances.AbGroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public CommutativeGroup AbGroupProduct9(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9) {
      return AbGroupProductInstances.AbGroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public CommutativeGroup AbGroupProduct10(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10) {
      return AbGroupProductInstances.AbGroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public CommutativeGroup AbGroupProduct11(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11) {
      return AbGroupProductInstances.AbGroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public CommutativeGroup AbGroupProduct12(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12) {
      return AbGroupProductInstances.AbGroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public CommutativeGroup AbGroupProduct13(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13) {
      return AbGroupProductInstances.AbGroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public CommutativeGroup AbGroupProduct14(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14) {
      return AbGroupProductInstances.AbGroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public CommutativeGroup AbGroupProduct15(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15) {
      return AbGroupProductInstances.AbGroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public CommutativeGroup AbGroupProduct16(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16) {
      return AbGroupProductInstances.AbGroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public CommutativeGroup AbGroupProduct17(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17) {
      return AbGroupProductInstances.AbGroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public CommutativeGroup AbGroupProduct18(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18) {
      return AbGroupProductInstances.AbGroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public CommutativeGroup AbGroupProduct19(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19) {
      return AbGroupProductInstances.AbGroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public CommutativeGroup AbGroupProduct20(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20) {
      return AbGroupProductInstances.AbGroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public CommutativeGroup AbGroupProduct21(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20, final CommutativeGroup _structure21) {
      return AbGroupProductInstances.AbGroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public CommutativeGroup AbGroupProduct22(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20, final CommutativeGroup _structure21, final CommutativeGroup _structure22) {
      return AbGroupProductInstances.AbGroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public Group GroupProduct2(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mDDc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mDFc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mDIc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mDJc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mFDc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mFFc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mFIc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mFJc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mIDc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mIFc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mIIc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mIJc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mJDc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mJFc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mJIc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct2$mJJc$sp(final Group _structure1, final Group _structure2) {
      return GroupProductInstances.GroupProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Group GroupProduct3(final Group _structure1, final Group _structure2, final Group _structure3) {
      return GroupProductInstances.GroupProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Group GroupProduct4(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4) {
      return GroupProductInstances.GroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Group GroupProduct5(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5) {
      return GroupProductInstances.GroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Group GroupProduct6(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6) {
      return GroupProductInstances.GroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Group GroupProduct7(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7) {
      return GroupProductInstances.GroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Group GroupProduct8(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8) {
      return GroupProductInstances.GroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Group GroupProduct9(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9) {
      return GroupProductInstances.GroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Group GroupProduct10(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10) {
      return GroupProductInstances.GroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Group GroupProduct11(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11) {
      return GroupProductInstances.GroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Group GroupProduct12(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12) {
      return GroupProductInstances.GroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Group GroupProduct13(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13) {
      return GroupProductInstances.GroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Group GroupProduct14(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14) {
      return GroupProductInstances.GroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Group GroupProduct15(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15) {
      return GroupProductInstances.GroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Group GroupProduct16(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16) {
      return GroupProductInstances.GroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Group GroupProduct17(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17) {
      return GroupProductInstances.GroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Group GroupProduct18(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18) {
      return GroupProductInstances.GroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Group GroupProduct19(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19) {
      return GroupProductInstances.GroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Group GroupProduct20(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20) {
      return GroupProductInstances.GroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Group GroupProduct21(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20, final Group _structure21) {
      return GroupProductInstances.GroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Group GroupProduct22(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20, final Group _structure21, final Group _structure22) {
      return GroupProductInstances.GroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public Monoid MonoidProduct2(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mDDc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mDFc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mDIc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mDJc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mFDc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mFFc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mFIc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mFJc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mIDc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mIFc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mIIc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mIJc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mJDc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mJFc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mJIc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct2$mJJc$sp(final Monoid _structure1, final Monoid _structure2) {
      return MonoidProductInstances.MonoidProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Monoid MonoidProduct3(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3) {
      return MonoidProductInstances.MonoidProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Monoid MonoidProduct4(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4) {
      return MonoidProductInstances.MonoidProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Monoid MonoidProduct5(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5) {
      return MonoidProductInstances.MonoidProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Monoid MonoidProduct6(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6) {
      return MonoidProductInstances.MonoidProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Monoid MonoidProduct7(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7) {
      return MonoidProductInstances.MonoidProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Monoid MonoidProduct8(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8) {
      return MonoidProductInstances.MonoidProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Monoid MonoidProduct9(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9) {
      return MonoidProductInstances.MonoidProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Monoid MonoidProduct10(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10) {
      return MonoidProductInstances.MonoidProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Monoid MonoidProduct11(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11) {
      return MonoidProductInstances.MonoidProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Monoid MonoidProduct12(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12) {
      return MonoidProductInstances.MonoidProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Monoid MonoidProduct13(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13) {
      return MonoidProductInstances.MonoidProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Monoid MonoidProduct14(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14) {
      return MonoidProductInstances.MonoidProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Monoid MonoidProduct15(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15) {
      return MonoidProductInstances.MonoidProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Monoid MonoidProduct16(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16) {
      return MonoidProductInstances.MonoidProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Monoid MonoidProduct17(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17) {
      return MonoidProductInstances.MonoidProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Monoid MonoidProduct18(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18) {
      return MonoidProductInstances.MonoidProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Monoid MonoidProduct19(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19) {
      return MonoidProductInstances.MonoidProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Monoid MonoidProduct20(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20) {
      return MonoidProductInstances.MonoidProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Monoid MonoidProduct21(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20, final Monoid _structure21) {
      return MonoidProductInstances.MonoidProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Monoid MonoidProduct22(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20, final Monoid _structure21, final Monoid _structure22) {
      return MonoidProductInstances.MonoidProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public Semigroup SemigroupProduct2(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mDDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mDDc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mDFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mDFc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mDIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mDIc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mDJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mDJc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mFDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mFDc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mFFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mFFc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mFIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mFIc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mFJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mFJc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mIDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mIDc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mIFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mIFc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mIIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mIIc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mIJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mIJc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mJDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mJDc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mJFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mJFc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mJIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mJIc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct2$mJJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
      return SemigroupProductInstances.SemigroupProduct2$mJJc$sp$(this, _structure1, _structure2);
   }

   public Semigroup SemigroupProduct3(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3) {
      return SemigroupProductInstances.SemigroupProduct3$(this, _structure1, _structure2, _structure3);
   }

   public Semigroup SemigroupProduct4(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4) {
      return SemigroupProductInstances.SemigroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
   }

   public Semigroup SemigroupProduct5(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5) {
      return SemigroupProductInstances.SemigroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
   }

   public Semigroup SemigroupProduct6(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6) {
      return SemigroupProductInstances.SemigroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
   }

   public Semigroup SemigroupProduct7(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7) {
      return SemigroupProductInstances.SemigroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
   }

   public Semigroup SemigroupProduct8(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8) {
      return SemigroupProductInstances.SemigroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
   }

   public Semigroup SemigroupProduct9(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9) {
      return SemigroupProductInstances.SemigroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
   }

   public Semigroup SemigroupProduct10(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10) {
      return SemigroupProductInstances.SemigroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
   }

   public Semigroup SemigroupProduct11(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11) {
      return SemigroupProductInstances.SemigroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
   }

   public Semigroup SemigroupProduct12(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12) {
      return SemigroupProductInstances.SemigroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
   }

   public Semigroup SemigroupProduct13(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13) {
      return SemigroupProductInstances.SemigroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
   }

   public Semigroup SemigroupProduct14(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14) {
      return SemigroupProductInstances.SemigroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
   }

   public Semigroup SemigroupProduct15(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15) {
      return SemigroupProductInstances.SemigroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
   }

   public Semigroup SemigroupProduct16(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16) {
      return SemigroupProductInstances.SemigroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
   }

   public Semigroup SemigroupProduct17(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17) {
      return SemigroupProductInstances.SemigroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
   }

   public Semigroup SemigroupProduct18(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18) {
      return SemigroupProductInstances.SemigroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
   }

   public Semigroup SemigroupProduct19(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19) {
      return SemigroupProductInstances.SemigroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
   }

   public Semigroup SemigroupProduct20(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20) {
      return SemigroupProductInstances.SemigroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
   }

   public Semigroup SemigroupProduct21(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20, final Semigroup _structure21) {
      return SemigroupProductInstances.SemigroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
   }

   public Semigroup SemigroupProduct22(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20, final Semigroup _structure21, final Semigroup _structure22) {
      return SemigroupProductInstances.SemigroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
   }

   public MapInnerProductSpace MapInnerProductSpace(final Field evidence$7) {
      return MapInstances3.MapInnerProductSpace$(this, evidence$7);
   }

   public MapEq MapEq(final Eq V0) {
      return MapInstances3.MapEq$(this, V0);
   }

   public MapGroup MapGroup(final Group evidence$5) {
      return MapInstances2.MapGroup$(this, evidence$5);
   }

   public MapVectorSpace MapVectorSpace(final Field evidence$6) {
      return MapInstances2.MapVectorSpace$(this, evidence$6);
   }

   public MapCRng MapCRng(final CommutativeRing evidence$4) {
      return MapInstances1.MapCRng$(this, evidence$4);
   }

   public MapMonoid MapMonoid(final Semigroup evidence$2) {
      return MapInstances0.MapMonoid$(this, evidence$2);
   }

   public MapCSemiring MapCSemiring(final CommutativeSemiring evidence$3) {
      return MapInstances0.MapCSemiring$(this, evidence$3);
   }

   public NormedVectorSpace SeqNormedVectorSpace(final Field field0, final NRoot nroot0, final Factory cbf0) {
      return SeqInstances3.SeqNormedVectorSpace$(this, field0, nroot0, cbf0);
   }

   public SeqInnerProductSpace SeqInnerProductSpace(final Field field0, final Factory cbf0) {
      return SeqInstances2.SeqInnerProductSpace$(this, field0, cbf0);
   }

   public SeqOrder SeqOrder(final Order A0) {
      return SeqInstances2.SeqOrder$(this, A0);
   }

   public SeqVectorSpace SeqVectorSpace(final Field field0, final Factory cbf0, final NotGiven ev) {
      return SeqInstances1.SeqVectorSpace$(this, field0, cbf0, ev);
   }

   public SeqEq SeqEq(final Eq A0) {
      return SeqInstances1.SeqEq$(this, A0);
   }

   public SeqCModule SeqCModule(final CommutativeRing ring0, final Factory cbf0, final NotGiven ev) {
      return SeqInstances0.SeqCModule$(this, ring0, cbf0, ev);
   }

   public Monoid ArrayMonoid(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mZc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mZc$sp$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mBc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mBc$sp$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mCc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mCc$sp$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mDc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mDc$sp$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mFc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mFc$sp$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mIc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mIc$sp$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mJc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mJc$sp$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mSc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mSc$sp$(this, evidence$25);
   }

   public Monoid ArrayMonoid$mVc$sp(final ClassTag evidence$25) {
      return ArrayInstances.ArrayMonoid$mVc$sp$(this, evidence$25);
   }

   public NormedVectorSpace ArrayNormedVectorSpace(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return ArrayInstances3.ArrayNormedVectorSpace$(this, evidence$22, evidence$23, evidence$24);
   }

   public NormedVectorSpace ArrayNormedVectorSpace$mDc$sp(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return ArrayInstances3.ArrayNormedVectorSpace$mDc$sp$(this, evidence$22, evidence$23, evidence$24);
   }

   public NormedVectorSpace ArrayNormedVectorSpace$mFc$sp(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return ArrayInstances3.ArrayNormedVectorSpace$mFc$sp$(this, evidence$22, evidence$23, evidence$24);
   }

   public InnerProductSpace ArrayInnerProductSpace(final Field evidence$19, final ClassTag evidence$20) {
      return ArrayInstances2.ArrayInnerProductSpace$(this, evidence$19, evidence$20);
   }

   public InnerProductSpace ArrayInnerProductSpace$mDc$sp(final Field evidence$19, final ClassTag evidence$20) {
      return ArrayInstances2.ArrayInnerProductSpace$mDc$sp$(this, evidence$19, evidence$20);
   }

   public InnerProductSpace ArrayInnerProductSpace$mFc$sp(final Field evidence$19, final ClassTag evidence$20) {
      return ArrayInstances2.ArrayInnerProductSpace$mFc$sp$(this, evidence$19, evidence$20);
   }

   public Order ArrayOrder(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$(this, evidence$21);
   }

   public Order ArrayOrder$mZc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mZc$sp$(this, evidence$21);
   }

   public Order ArrayOrder$mBc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mBc$sp$(this, evidence$21);
   }

   public Order ArrayOrder$mCc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mCc$sp$(this, evidence$21);
   }

   public Order ArrayOrder$mDc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mDc$sp$(this, evidence$21);
   }

   public Order ArrayOrder$mFc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mFc$sp$(this, evidence$21);
   }

   public Order ArrayOrder$mIc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mIc$sp$(this, evidence$21);
   }

   public Order ArrayOrder$mJc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mJc$sp$(this, evidence$21);
   }

   public Order ArrayOrder$mSc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mSc$sp$(this, evidence$21);
   }

   public Order ArrayOrder$mVc$sp(final Order evidence$21) {
      return ArrayInstances2.ArrayOrder$mVc$sp$(this, evidence$21);
   }

   public VectorSpace ArrayVectorSpace(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return ArrayInstances1.ArrayVectorSpace$(this, evidence$15, evidence$16, evidence$17);
   }

   public VectorSpace ArrayVectorSpace$mDc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return ArrayInstances1.ArrayVectorSpace$mDc$sp$(this, evidence$15, evidence$16, evidence$17);
   }

   public VectorSpace ArrayVectorSpace$mFc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return ArrayInstances1.ArrayVectorSpace$mFc$sp$(this, evidence$15, evidence$16, evidence$17);
   }

   public VectorSpace ArrayVectorSpace$mIc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return ArrayInstances1.ArrayVectorSpace$mIc$sp$(this, evidence$15, evidence$16, evidence$17);
   }

   public VectorSpace ArrayVectorSpace$mJc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
      return ArrayInstances1.ArrayVectorSpace$mJc$sp$(this, evidence$15, evidence$16, evidence$17);
   }

   public Eq ArrayEq(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$(this, evidence$18);
   }

   public Eq ArrayEq$mZc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mZc$sp$(this, evidence$18);
   }

   public Eq ArrayEq$mBc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mBc$sp$(this, evidence$18);
   }

   public Eq ArrayEq$mCc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mCc$sp$(this, evidence$18);
   }

   public Eq ArrayEq$mDc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mDc$sp$(this, evidence$18);
   }

   public Eq ArrayEq$mFc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mFc$sp$(this, evidence$18);
   }

   public Eq ArrayEq$mIc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mIc$sp$(this, evidence$18);
   }

   public Eq ArrayEq$mJc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mJc$sp$(this, evidence$18);
   }

   public Eq ArrayEq$mSc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mSc$sp$(this, evidence$18);
   }

   public Eq ArrayEq$mVc$sp(final Eq evidence$18) {
      return ArrayInstances1.ArrayEq$mVc$sp$(this, evidence$18);
   }

   public CModule ArrayCModule(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return ArrayInstances0.ArrayCModule$(this, evidence$12, evidence$13, evidence$14);
   }

   public CModule ArrayCModule$mDc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return ArrayInstances0.ArrayCModule$mDc$sp$(this, evidence$12, evidence$13, evidence$14);
   }

   public CModule ArrayCModule$mFc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return ArrayInstances0.ArrayCModule$mFc$sp$(this, evidence$12, evidence$13, evidence$14);
   }

   public CModule ArrayCModule$mIc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return ArrayInstances0.ArrayCModule$mIc$sp$(this, evidence$12, evidence$13, evidence$14);
   }

   public CModule ArrayCModule$mJc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
      return ArrayInstances0.ArrayCModule$mJc$sp$(this, evidence$12, evidence$13, evidence$14);
   }

   public Monoid IterableMonoid(final Factory cbf) {
      return IterableInstances.IterableMonoid$(this, cbf);
   }

   public MetricSpace levenshteinDistance() {
      return StringInstances0.levenshteinDistance$(this);
   }

   public BigDecimalIsTrig BigDecimalIsTrig(final MathContext mc) {
      return BigDecimalInstances.BigDecimalIsTrig$(this, mc);
   }

   public MathContext BigDecimalIsTrig$default$1() {
      return BigDecimalInstances.BigDecimalIsTrig$default$1$(this);
   }

   public LiteralsSyntax.radix$ radix() {
      if (radix$module == null) {
         this.radix$lzycompute$1();
      }

      return radix$module;
   }

   public LiteralsSyntax.si$ si() {
      if (si$module == null) {
         this.si$lzycompute$1();
      }

      return si$module;
   }

   public LiteralsSyntax.us$ us() {
      if (us$module == null) {
         this.us$lzycompute$1();
      }

      return us$module;
   }

   public LiteralsSyntax.eu$ eu() {
      if (eu$module == null) {
         this.eu$lzycompute$1();
      }

      return eu$module;
   }

   public final CommutativeGroup UnitAlgebra() {
      return UnitAlgebra;
   }

   public final void spire$std$UnitInstances$_setter_$UnitAlgebra_$eq(final CommutativeGroup x$1) {
      UnitAlgebra = x$1;
   }

   public final Monoid StringAlgebra() {
      return StringAlgebra;
   }

   public final Order StringOrder() {
      return StringOrder;
   }

   public final void spire$std$StringInstances$_setter_$StringAlgebra_$eq(final Monoid x$1) {
      StringAlgebra = x$1;
   }

   public final void spire$std$StringInstances$_setter_$StringOrder_$eq(final Order x$1) {
      StringOrder = x$1;
   }

   public final Field BigDecimalAlgebra() {
      return BigDecimalAlgebra;
   }

   public final NumberTag BigDecimalTag() {
      return BigDecimalTag;
   }

   public final void spire$std$BigDecimalInstances$_setter_$BigDecimalAlgebra_$eq(final Field x$1) {
      BigDecimalAlgebra = x$1;
   }

   public final void spire$std$BigDecimalInstances$_setter_$BigDecimalTag_$eq(final NumberTag x$1) {
      BigDecimalTag = x$1;
   }

   public final EuclideanRing BigIntegerAlgebra() {
      return BigIntegerAlgebra;
   }

   public final NumberTag BigIntegerTag() {
      return BigIntegerTag;
   }

   public final void spire$std$BigIntegerInstances$_setter_$BigIntegerAlgebra_$eq(final EuclideanRing x$1) {
      BigIntegerAlgebra = x$1;
   }

   public final void spire$std$BigIntegerInstances$_setter_$BigIntegerTag_$eq(final NumberTag x$1) {
      BigIntegerTag = x$1;
   }

   public final EuclideanRing BigIntAlgebra() {
      return BigIntAlgebra;
   }

   public final NumberTag BigIntTag() {
      return BigIntTag;
   }

   public final void spire$std$BigIntInstances$_setter_$BigIntAlgebra_$eq(final EuclideanRing x$1) {
      BigIntAlgebra = x$1;
   }

   public final void spire$std$BigIntInstances$_setter_$BigIntTag_$eq(final NumberTag x$1) {
      BigIntTag = x$1;
   }

   public final Field DoubleAlgebra() {
      return DoubleAlgebra;
   }

   public final NumberTag DoubleTag() {
      return DoubleTag;
   }

   public final void spire$std$DoubleInstances$_setter_$DoubleAlgebra_$eq(final Field x$1) {
      DoubleAlgebra = x$1;
   }

   public final void spire$std$DoubleInstances$_setter_$DoubleTag_$eq(final NumberTag x$1) {
      DoubleTag = x$1;
   }

   public final Field FloatAlgebra() {
      return FloatAlgebra;
   }

   public final NumberTag FloatTag() {
      return FloatTag;
   }

   public final void spire$std$FloatInstances$_setter_$FloatAlgebra_$eq(final Field x$1) {
      FloatAlgebra = x$1;
   }

   public final void spire$std$FloatInstances$_setter_$FloatTag_$eq(final NumberTag x$1) {
      FloatTag = x$1;
   }

   public final BitString LongBitString() {
      return LongBitString;
   }

   public final EuclideanRing LongAlgebra() {
      return LongAlgebra;
   }

   public final NumberTag LongTag() {
      return LongTag;
   }

   public final void spire$std$LongInstances$_setter_$LongBitString_$eq(final BitString x$1) {
      LongBitString = x$1;
   }

   public final void spire$std$LongInstances$_setter_$LongAlgebra_$eq(final EuclideanRing x$1) {
      LongAlgebra = x$1;
   }

   public final void spire$std$LongInstances$_setter_$LongTag_$eq(final NumberTag x$1) {
      LongTag = x$1;
   }

   public final BitString IntBitString() {
      return IntBitString;
   }

   public final EuclideanRing IntAlgebra() {
      return IntAlgebra;
   }

   public final NumberTag IntTag() {
      return IntTag;
   }

   public final void spire$std$IntInstances$_setter_$IntBitString_$eq(final BitString x$1) {
      IntBitString = x$1;
   }

   public final void spire$std$IntInstances$_setter_$IntAlgebra_$eq(final EuclideanRing x$1) {
      IntAlgebra = x$1;
   }

   public final void spire$std$IntInstances$_setter_$IntTag_$eq(final NumberTag x$1) {
      IntTag = x$1;
   }

   public final BitString ShortBitString() {
      return ShortBitString;
   }

   public final EuclideanRing ShortAlgebra() {
      return ShortAlgebra;
   }

   public final NumberTag ShortTag() {
      return ShortTag;
   }

   public final void spire$std$ShortInstances$_setter_$ShortBitString_$eq(final BitString x$1) {
      ShortBitString = x$1;
   }

   public final void spire$std$ShortInstances$_setter_$ShortAlgebra_$eq(final EuclideanRing x$1) {
      ShortAlgebra = x$1;
   }

   public final void spire$std$ShortInstances$_setter_$ShortTag_$eq(final NumberTag x$1) {
      ShortTag = x$1;
   }

   public final BitString ByteBitString() {
      return ByteBitString;
   }

   public final EuclideanRing ByteAlgebra() {
      return ByteAlgebra;
   }

   public final NumberTag ByteTag() {
      return ByteTag;
   }

   public final void spire$std$ByteInstances$_setter_$ByteBitString_$eq(final BitString x$1) {
      ByteBitString = x$1;
   }

   public final void spire$std$ByteInstances$_setter_$ByteAlgebra_$eq(final EuclideanRing x$1) {
      ByteAlgebra = x$1;
   }

   public final void spire$std$ByteInstances$_setter_$ByteTag_$eq(final NumberTag x$1) {
      ByteTag = x$1;
   }

   public final Order CharAlgebra() {
      return CharAlgebra;
   }

   public final void spire$std$CharInstances$_setter_$CharAlgebra_$eq(final Order x$1) {
      CharAlgebra = x$1;
   }

   public final Bool BooleanStructure() {
      return BooleanStructure;
   }

   public final void spire$std$BooleanInstances$_setter_$BooleanStructure_$eq(final Bool x$1) {
      BooleanStructure = x$1;
   }

   private final void radix$lzycompute$1() {
      synchronized(this){}

      try {
         if (radix$module == null) {
            radix$module = new LiteralsSyntax.radix$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void si$lzycompute$1() {
      synchronized(this){}

      try {
         if (si$module == null) {
            si$module = new LiteralsSyntax.si$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void us$lzycompute$1() {
      synchronized(this){}

      try {
         if (us$module == null) {
            us$module = new LiteralsSyntax.us$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void eu$lzycompute$1() {
      synchronized(this){}

      try {
         if (eu$module == null) {
            eu$module = new LiteralsSyntax.eu$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private implicits$() {
   }
}
