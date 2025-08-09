package cats.kernel.instances.all;

import cats.kernel.Band;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.EqToEquivConversion;
import cats.kernel.Group;
import cats.kernel.Hash;
import cats.kernel.HashToHashingConversion;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.OrderToOrderingConversion;
import cats.kernel.PartialOrder;
import cats.kernel.PartialOrderToPartialOrderingConversion;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import cats.kernel.instances.AllInstances;
import cats.kernel.instances.AllInstancesBinCompat0;
import cats.kernel.instances.AllInstancesBinCompat1;
import cats.kernel.instances.AllInstancesBinCompat2;
import cats.kernel.instances.ArraySeqInstances;
import cats.kernel.instances.BigDecimalInstances;
import cats.kernel.instances.BigIntInstances;
import cats.kernel.instances.BitSetInstances;
import cats.kernel.instances.BooleanInstances;
import cats.kernel.instances.ByteInstances;
import cats.kernel.instances.CharInstances;
import cats.kernel.instances.CharOrder;
import cats.kernel.instances.DeadlineInstances;
import cats.kernel.instances.DoubleInstances;
import cats.kernel.instances.DurationInstances;
import cats.kernel.instances.EitherInstances;
import cats.kernel.instances.EitherInstances0;
import cats.kernel.instances.EitherInstances1;
import cats.kernel.instances.FiniteDurationInstances;
import cats.kernel.instances.FloatInstances;
import cats.kernel.instances.FunctionInstances;
import cats.kernel.instances.FunctionInstances0;
import cats.kernel.instances.FunctionInstances1;
import cats.kernel.instances.FunctionInstances2;
import cats.kernel.instances.FunctionInstances3;
import cats.kernel.instances.FunctionInstances4;
import cats.kernel.instances.IntInstances;
import cats.kernel.instances.LazyListInstances;
import cats.kernel.instances.LazyListInstances1;
import cats.kernel.instances.LazyListInstances2;
import cats.kernel.instances.ListInstances;
import cats.kernel.instances.ListInstances1;
import cats.kernel.instances.ListInstances2;
import cats.kernel.instances.LongInstances;
import cats.kernel.instances.MapInstances;
import cats.kernel.instances.MapInstances1;
import cats.kernel.instances.OptionInstances;
import cats.kernel.instances.OptionInstances0;
import cats.kernel.instances.OptionInstances1;
import cats.kernel.instances.OptionInstances2;
import cats.kernel.instances.QueueInstances;
import cats.kernel.instances.QueueInstances1;
import cats.kernel.instances.QueueInstances2;
import cats.kernel.instances.SeqInstances;
import cats.kernel.instances.SeqInstances1;
import cats.kernel.instances.SeqInstances2;
import cats.kernel.instances.SetInstances;
import cats.kernel.instances.SetInstances1;
import cats.kernel.instances.ShortInstances;
import cats.kernel.instances.SortedMapInstances;
import cats.kernel.instances.SortedMapInstances1;
import cats.kernel.instances.SortedMapInstances2;
import cats.kernel.instances.SortedMapInstances3;
import cats.kernel.instances.SortedSetInstances;
import cats.kernel.instances.SortedSetInstances1;
import cats.kernel.instances.StreamInstances;
import cats.kernel.instances.StreamInstances1;
import cats.kernel.instances.StreamInstances2;
import cats.kernel.instances.StringInstances;
import cats.kernel.instances.SymbolInstances;
import cats.kernel.instances.TupleInstances;
import cats.kernel.instances.TupleInstances1;
import cats.kernel.instances.TupleInstances2;
import cats.kernel.instances.TupleInstances3;
import cats.kernel.instances.UUIDInstances;
import cats.kernel.instances.UnitInstances;
import cats.kernel.instances.VectorInstances;
import cats.kernel.instances.VectorInstances1;
import cats.kernel.instances.VectorInstances2;
import scala.math.Equiv;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.util.hashing.Hashing;

public final class package$ implements AllInstances, AllInstancesBinCompat0, AllInstancesBinCompat1, AllInstancesBinCompat2 {
   public static final package$ MODULE$ = new package$();
   private static Order catsKernelStdOrderForDeadline;
   private static Order catsKernelStdOrderForFiniteDuration;
   private static CommutativeGroup catsKernelStdGroupForFiniteDuration;
   private static Order catsKernelStdOrderForUUID;
   private static Order catsKernelStdOrderForUnit;
   private static BoundedSemilattice catsKernelStdAlgebraForUnit;
   private static Order catsKernelStdOrderForSymbol;
   private static Order catsKernelStdOrderForString;
   private static Monoid catsKernelStdMonoidForString;
   private static Order catsKernelStdOrderForShort;
   private static CommutativeGroup catsKernelStdGroupForShort;
   private static Order catsKernelStdOrderForLong;
   private static CommutativeGroup catsKernelStdGroupForLong;
   private static Order catsKernelStdOrderForInt;
   private static CommutativeGroup catsKernelStdGroupForInt;
   private static Order catsKernelStdOrderForFloat;
   private static CommutativeGroup catsKernelStdGroupForFloat;
   private static Order catsKernelStdOrderForDuration;
   private static CommutativeGroup catsKernelStdGroupForDuration;
   private static Order catsKernelStdOrderForDouble;
   private static CommutativeGroup catsKernelStdGroupForDouble;
   private static CharOrder catsKernelStdOrderForChar;
   private static Order catsKernelStdOrderForByte;
   private static CommutativeGroup catsKernelStdGroupForByte;
   private static Order catsKernelStdOrderForBoolean;
   private static PartialOrder catsKernelStdOrderForBitSet;
   private static BoundedSemilattice catsKernelStdSemilatticeForBitSet;
   private static Order catsKernelStdOrderForBigInt;
   private static CommutativeGroup catsKernelStdGroupForBigInt;
   private static Order catsKernelStdOrderForBigDecimal;
   private static CommutativeGroup catsKernelStdGroupForBigDecimal;

   static {
      ArraySeqInstances.ArraySeqInstances2.$init$(MODULE$);
      ArraySeqInstances.ArraySeqInstances1.$init$(MODULE$);
      ArraySeqInstances.$init$(MODULE$);
      BigDecimalInstances.$init$(MODULE$);
      BigIntInstances.$init$(MODULE$);
      BitSetInstances.$init$(MODULE$);
      BooleanInstances.$init$(MODULE$);
      ByteInstances.$init$(MODULE$);
      CharInstances.$init$(MODULE$);
      DoubleInstances.$init$(MODULE$);
      EqToEquivConversion.$init$(MODULE$);
      EitherInstances1.$init$(MODULE$);
      EitherInstances0.$init$(MODULE$);
      EitherInstances.$init$(MODULE$);
      DurationInstances.$init$(MODULE$);
      FloatInstances.$init$(MODULE$);
      FunctionInstances4.$init$(MODULE$);
      FunctionInstances3.$init$(MODULE$);
      FunctionInstances2.$init$(MODULE$);
      FunctionInstances1.$init$(MODULE$);
      FunctionInstances0.$init$(MODULE$);
      FunctionInstances.$init$(MODULE$);
      HashToHashingConversion.$init$(MODULE$);
      IntInstances.$init$(MODULE$);
      LazyListInstances2.$init$(MODULE$);
      LazyListInstances1.$init$(MODULE$);
      LazyListInstances.$init$(MODULE$);
      ListInstances2.$init$(MODULE$);
      ListInstances1.$init$(MODULE$);
      ListInstances.$init$(MODULE$);
      LongInstances.$init$(MODULE$);
      MapInstances1.$init$(MODULE$);
      MapInstances.$init$(MODULE$);
      OptionInstances2.$init$(MODULE$);
      OptionInstances1.$init$(MODULE$);
      OptionInstances0.$init$(MODULE$);
      OptionInstances.$init$(MODULE$);
      OrderToOrderingConversion.$init$(MODULE$);
      PartialOrderToPartialOrderingConversion.$init$(MODULE$);
      QueueInstances2.$init$(MODULE$);
      QueueInstances1.$init$(MODULE$);
      QueueInstances.$init$(MODULE$);
      SetInstances1.$init$(MODULE$);
      SetInstances.$init$(MODULE$);
      SeqInstances2.$init$(MODULE$);
      SeqInstances1.$init$(MODULE$);
      SeqInstances.$init$(MODULE$);
      ShortInstances.$init$(MODULE$);
      StreamInstances2.$init$(MODULE$);
      StreamInstances1.$init$(MODULE$);
      StreamInstances.$init$(MODULE$);
      StringInstances.$init$(MODULE$);
      SymbolInstances.$init$(MODULE$);
      TupleInstances3.$init$(MODULE$);
      TupleInstances2.$init$(MODULE$);
      TupleInstances1.$init$(MODULE$);
      TupleInstances.$init$(MODULE$);
      UnitInstances.$init$(MODULE$);
      UUIDInstances.$init$(MODULE$);
      VectorInstances2.$init$(MODULE$);
      VectorInstances1.$init$(MODULE$);
      VectorInstances.$init$(MODULE$);
      FiniteDurationInstances.$init$(MODULE$);
      SortedMapInstances1.$init$(MODULE$);
      SortedMapInstances2.$init$(MODULE$);
      SortedMapInstances3.$init$(MODULE$);
      SortedMapInstances.$init$(MODULE$);
      SortedSetInstances1.$init$(MODULE$);
      SortedSetInstances.$init$(MODULE$);
      DeadlineInstances.$init$(MODULE$);
   }

   /** @deprecated */
   public Hash catsKernelStdHashForSortedSet(final Order evidence$1, final Hash evidence$2) {
      return SortedSetInstances.catsKernelStdHashForSortedSet$(this, evidence$1, evidence$2);
   }

   public Hash catsKernelStdHashForSortedSet(final Hash evidence$3) {
      return SortedSetInstances.catsKernelStdHashForSortedSet$(this, evidence$3);
   }

   public Order catsKernelStdOrderForSortedSet(final Order evidence$4) {
      return SortedSetInstances1.catsKernelStdOrderForSortedSet$(this, evidence$4);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForSortedSet(final Order evidence$5) {
      return SortedSetInstances1.catsKernelStdBoundedSemilatticeForSortedSet$(this, evidence$5);
   }

   public Hash catsKernelStdHashForSortedMap(final Hash evidence$1, final Hash evidence$2) {
      return SortedMapInstances.catsKernelStdHashForSortedMap$(this, evidence$1, evidence$2);
   }

   /** @deprecated */
   public Hash catsKernelStdHashForSortedMap(final Hash hashK, final Order orderK, final Hash hashV) {
      return SortedMapInstances.catsKernelStdHashForSortedMap$(this, hashK, orderK, hashV);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForSortedMap(final CommutativeSemigroup evidence$3) {
      return SortedMapInstances.catsKernelStdCommutativeSemigroupForSortedMap$(this, evidence$3);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForSortedMap(final Order evidence$4, final CommutativeSemigroup evidence$5) {
      return SortedMapInstances.catsKernelStdCommutativeMonoidForSortedMap$(this, evidence$4, evidence$5);
   }

   public Order catsKernelStdOrderForSortedMap(final Order evidence$11) {
      return SortedMapInstances3.catsKernelStdOrderForSortedMap$(this, evidence$11);
   }

   public Semigroup catsKernelStdSemigroupForSortedMap(final Semigroup evidence$7) {
      return SortedMapInstances2.catsKernelStdSemigroupForSortedMap$(this, evidence$7);
   }

   public Monoid catsKernelStdMonoidForSortedMap(final Order evidence$8, final Semigroup evidence$9) {
      return SortedMapInstances2.catsKernelStdMonoidForSortedMap$(this, evidence$8, evidence$9);
   }

   public PartialOrder catsKernelStdPartialOrderForSortedMap(final PartialOrder evidence$10) {
      return SortedMapInstances2.catsKernelStdPartialOrderForSortedMap$(this, evidence$10);
   }

   public Eq catsKernelStdEqForSortedMap(final Eq evidence$6) {
      return SortedMapInstances1.catsKernelStdEqForSortedMap$(this, evidence$6);
   }

   /** @deprecated */
   public Eq catsKernelStdEqForSortedMap(final Order orderK, final Eq eqV) {
      return SortedMapInstances1.catsKernelStdEqForSortedMap$(this, orderK, eqV);
   }

   public Order catsKernelStdOrderForVector(final Order evidence$1) {
      return VectorInstances.catsKernelStdOrderForVector$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForVector() {
      return VectorInstances.catsKernelStdMonoidForVector$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForVector(final PartialOrder evidence$2) {
      return VectorInstances1.catsKernelStdPartialOrderForVector$(this, evidence$2);
   }

   public Hash catsKernelStdHashForVector(final Hash evidence$3) {
      return VectorInstances1.catsKernelStdHashForVector$(this, evidence$3);
   }

   public Eq catsKernelStdEqForVector(final Eq evidence$4) {
      return VectorInstances2.catsKernelStdEqForVector$(this, evidence$4);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple1(final CommutativeGroup A0) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple1$(this, A0);
   }

   public Order catsKernelStdOrderForTuple1(final Order A0) {
      return TupleInstances.catsKernelStdOrderForTuple1$(this, A0);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple1(final BoundedSemilattice A0) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple1$(this, A0);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple2(final CommutativeGroup A0, final CommutativeGroup A1) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple2$(this, A0, A1);
   }

   public Order catsKernelStdOrderForTuple2(final Order A0, final Order A1) {
      return TupleInstances.catsKernelStdOrderForTuple2$(this, A0, A1);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple2(final BoundedSemilattice A0, final BoundedSemilattice A1) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple2$(this, A0, A1);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple3(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple3$(this, A0, A1, A2);
   }

   public Order catsKernelStdOrderForTuple3(final Order A0, final Order A1, final Order A2) {
      return TupleInstances.catsKernelStdOrderForTuple3$(this, A0, A1, A2);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple3(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple3$(this, A0, A1, A2);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple4(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple4$(this, A0, A1, A2, A3);
   }

   public Order catsKernelStdOrderForTuple4(final Order A0, final Order A1, final Order A2, final Order A3) {
      return TupleInstances.catsKernelStdOrderForTuple4$(this, A0, A1, A2, A3);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple4(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple4$(this, A0, A1, A2, A3);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple5(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Order catsKernelStdOrderForTuple5(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4) {
      return TupleInstances.catsKernelStdOrderForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple5(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple6(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Order catsKernelStdOrderForTuple6(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5) {
      return TupleInstances.catsKernelStdOrderForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple6(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple7(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Order catsKernelStdOrderForTuple7(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6) {
      return TupleInstances.catsKernelStdOrderForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple7(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple8(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Order catsKernelStdOrderForTuple8(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7) {
      return TupleInstances.catsKernelStdOrderForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple8(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple9(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Order catsKernelStdOrderForTuple9(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8) {
      return TupleInstances.catsKernelStdOrderForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple9(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple10(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Order catsKernelStdOrderForTuple10(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9) {
      return TupleInstances.catsKernelStdOrderForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple10(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple11(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Order catsKernelStdOrderForTuple11(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10) {
      return TupleInstances.catsKernelStdOrderForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple11(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple12(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Order catsKernelStdOrderForTuple12(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11) {
      return TupleInstances.catsKernelStdOrderForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple12(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple13(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Order catsKernelStdOrderForTuple13(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12) {
      return TupleInstances.catsKernelStdOrderForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple13(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple14(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Order catsKernelStdOrderForTuple14(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13) {
      return TupleInstances.catsKernelStdOrderForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple14(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple15(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Order catsKernelStdOrderForTuple15(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14) {
      return TupleInstances.catsKernelStdOrderForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple15(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple16(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Order catsKernelStdOrderForTuple16(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15) {
      return TupleInstances.catsKernelStdOrderForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple16(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple17(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Order catsKernelStdOrderForTuple17(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16) {
      return TupleInstances.catsKernelStdOrderForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple17(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple18(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Order catsKernelStdOrderForTuple18(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17) {
      return TupleInstances.catsKernelStdOrderForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple18(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple19(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Order catsKernelStdOrderForTuple19(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18) {
      return TupleInstances.catsKernelStdOrderForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple19(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple20(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Order catsKernelStdOrderForTuple20(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19) {
      return TupleInstances.catsKernelStdOrderForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple20(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple21(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19, final CommutativeGroup A20) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Order catsKernelStdOrderForTuple21(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20) {
      return TupleInstances.catsKernelStdOrderForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple21(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19, final BoundedSemilattice A20) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public CommutativeGroup catsKernelStdCommutativeGroupForTuple22(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19, final CommutativeGroup A20, final CommutativeGroup A21) {
      return TupleInstances.catsKernelStdCommutativeGroupForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Order catsKernelStdOrderForTuple22(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20, final Order A21) {
      return TupleInstances.catsKernelStdOrderForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple22(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19, final BoundedSemilattice A20, final BoundedSemilattice A21) {
      return TupleInstances.catsKernelStdBoundedSemilatticeForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Semilattice catsKernelStdSemilatticeForTuple1(final Semilattice A0) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple1$(this, A0);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple1(final CommutativeMonoid A0) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple1$(this, A0);
   }

   public Group catsKernelStdGroupForTuple1(final Group A0) {
      return TupleInstances1.catsKernelStdGroupForTuple1$(this, A0);
   }

   public Hash catsKernelStdHashForTuple1(final Hash A0) {
      return TupleInstances1.catsKernelStdHashForTuple1$(this, A0);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple1(final PartialOrder A0) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple1$(this, A0);
   }

   public Semilattice catsKernelStdSemilatticeForTuple2(final Semilattice A0, final Semilattice A1) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple2$(this, A0, A1);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple2(final CommutativeMonoid A0, final CommutativeMonoid A1) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple2$(this, A0, A1);
   }

   public Group catsKernelStdGroupForTuple2(final Group A0, final Group A1) {
      return TupleInstances1.catsKernelStdGroupForTuple2$(this, A0, A1);
   }

   public Hash catsKernelStdHashForTuple2(final Hash A0, final Hash A1) {
      return TupleInstances1.catsKernelStdHashForTuple2$(this, A0, A1);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple2(final PartialOrder A0, final PartialOrder A1) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple2$(this, A0, A1);
   }

   public Semilattice catsKernelStdSemilatticeForTuple3(final Semilattice A0, final Semilattice A1, final Semilattice A2) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple3$(this, A0, A1, A2);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple3(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple3$(this, A0, A1, A2);
   }

   public Group catsKernelStdGroupForTuple3(final Group A0, final Group A1, final Group A2) {
      return TupleInstances1.catsKernelStdGroupForTuple3$(this, A0, A1, A2);
   }

   public Hash catsKernelStdHashForTuple3(final Hash A0, final Hash A1, final Hash A2) {
      return TupleInstances1.catsKernelStdHashForTuple3$(this, A0, A1, A2);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple3(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple3$(this, A0, A1, A2);
   }

   public Semilattice catsKernelStdSemilatticeForTuple4(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple4$(this, A0, A1, A2, A3);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple4(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple4$(this, A0, A1, A2, A3);
   }

   public Group catsKernelStdGroupForTuple4(final Group A0, final Group A1, final Group A2, final Group A3) {
      return TupleInstances1.catsKernelStdGroupForTuple4$(this, A0, A1, A2, A3);
   }

   public Hash catsKernelStdHashForTuple4(final Hash A0, final Hash A1, final Hash A2, final Hash A3) {
      return TupleInstances1.catsKernelStdHashForTuple4$(this, A0, A1, A2, A3);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple4(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple4$(this, A0, A1, A2, A3);
   }

   public Semilattice catsKernelStdSemilatticeForTuple5(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple5(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Group catsKernelStdGroupForTuple5(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4) {
      return TupleInstances1.catsKernelStdGroupForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Hash catsKernelStdHashForTuple5(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4) {
      return TupleInstances1.catsKernelStdHashForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple5(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Semilattice catsKernelStdSemilatticeForTuple6(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple6(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Group catsKernelStdGroupForTuple6(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5) {
      return TupleInstances1.catsKernelStdGroupForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Hash catsKernelStdHashForTuple6(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5) {
      return TupleInstances1.catsKernelStdHashForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple6(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Semilattice catsKernelStdSemilatticeForTuple7(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple7(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Group catsKernelStdGroupForTuple7(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6) {
      return TupleInstances1.catsKernelStdGroupForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Hash catsKernelStdHashForTuple7(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6) {
      return TupleInstances1.catsKernelStdHashForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple7(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Semilattice catsKernelStdSemilatticeForTuple8(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple8(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Group catsKernelStdGroupForTuple8(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7) {
      return TupleInstances1.catsKernelStdGroupForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Hash catsKernelStdHashForTuple8(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7) {
      return TupleInstances1.catsKernelStdHashForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple8(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Semilattice catsKernelStdSemilatticeForTuple9(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple9(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Group catsKernelStdGroupForTuple9(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8) {
      return TupleInstances1.catsKernelStdGroupForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Hash catsKernelStdHashForTuple9(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8) {
      return TupleInstances1.catsKernelStdHashForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple9(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Semilattice catsKernelStdSemilatticeForTuple10(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple10(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Group catsKernelStdGroupForTuple10(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9) {
      return TupleInstances1.catsKernelStdGroupForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Hash catsKernelStdHashForTuple10(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9) {
      return TupleInstances1.catsKernelStdHashForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple10(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Semilattice catsKernelStdSemilatticeForTuple11(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple11(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Group catsKernelStdGroupForTuple11(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10) {
      return TupleInstances1.catsKernelStdGroupForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Hash catsKernelStdHashForTuple11(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10) {
      return TupleInstances1.catsKernelStdHashForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple11(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Semilattice catsKernelStdSemilatticeForTuple12(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple12(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Group catsKernelStdGroupForTuple12(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11) {
      return TupleInstances1.catsKernelStdGroupForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Hash catsKernelStdHashForTuple12(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11) {
      return TupleInstances1.catsKernelStdHashForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple12(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Semilattice catsKernelStdSemilatticeForTuple13(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple13(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Group catsKernelStdGroupForTuple13(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12) {
      return TupleInstances1.catsKernelStdGroupForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Hash catsKernelStdHashForTuple13(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12) {
      return TupleInstances1.catsKernelStdHashForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple13(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Semilattice catsKernelStdSemilatticeForTuple14(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple14(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Group catsKernelStdGroupForTuple14(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13) {
      return TupleInstances1.catsKernelStdGroupForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Hash catsKernelStdHashForTuple14(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13) {
      return TupleInstances1.catsKernelStdHashForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple14(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Semilattice catsKernelStdSemilatticeForTuple15(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple15(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Group catsKernelStdGroupForTuple15(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14) {
      return TupleInstances1.catsKernelStdGroupForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Hash catsKernelStdHashForTuple15(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14) {
      return TupleInstances1.catsKernelStdHashForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple15(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Semilattice catsKernelStdSemilatticeForTuple16(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple16(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Group catsKernelStdGroupForTuple16(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15) {
      return TupleInstances1.catsKernelStdGroupForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Hash catsKernelStdHashForTuple16(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15) {
      return TupleInstances1.catsKernelStdHashForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple16(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Semilattice catsKernelStdSemilatticeForTuple17(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple17(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Group catsKernelStdGroupForTuple17(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16) {
      return TupleInstances1.catsKernelStdGroupForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Hash catsKernelStdHashForTuple17(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16) {
      return TupleInstances1.catsKernelStdHashForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple17(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Semilattice catsKernelStdSemilatticeForTuple18(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple18(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Group catsKernelStdGroupForTuple18(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17) {
      return TupleInstances1.catsKernelStdGroupForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Hash catsKernelStdHashForTuple18(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17) {
      return TupleInstances1.catsKernelStdHashForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple18(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Semilattice catsKernelStdSemilatticeForTuple19(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple19(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Group catsKernelStdGroupForTuple19(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18) {
      return TupleInstances1.catsKernelStdGroupForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Hash catsKernelStdHashForTuple19(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18) {
      return TupleInstances1.catsKernelStdHashForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple19(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Semilattice catsKernelStdSemilatticeForTuple20(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple20(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Group catsKernelStdGroupForTuple20(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19) {
      return TupleInstances1.catsKernelStdGroupForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Hash catsKernelStdHashForTuple20(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19) {
      return TupleInstances1.catsKernelStdHashForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple20(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Semilattice catsKernelStdSemilatticeForTuple21(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple21(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19, final CommutativeMonoid A20) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Group catsKernelStdGroupForTuple21(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20) {
      return TupleInstances1.catsKernelStdGroupForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Hash catsKernelStdHashForTuple21(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19, final Hash A20) {
      return TupleInstances1.catsKernelStdHashForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple21(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19, final PartialOrder A20) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Semilattice catsKernelStdSemilatticeForTuple22(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20, final Semilattice A21) {
      return TupleInstances1.catsKernelStdSemilatticeForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForTuple22(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19, final CommutativeMonoid A20, final CommutativeMonoid A21) {
      return TupleInstances1.catsKernelStdCommutativeMonoidForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Group catsKernelStdGroupForTuple22(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20, final Group A21) {
      return TupleInstances1.catsKernelStdGroupForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Hash catsKernelStdHashForTuple22(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19, final Hash A20, final Hash A21) {
      return TupleInstances1.catsKernelStdHashForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public PartialOrder catsKernelStdPartialOrderForTuple22(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19, final PartialOrder A20, final PartialOrder A21) {
      return TupleInstances1.catsKernelStdPartialOrderForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Band catsKernelStdBandForTuple1(final Band A0) {
      return TupleInstances2.catsKernelStdBandForTuple1$(this, A0);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple1(final CommutativeSemigroup A0) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple1$(this, A0);
   }

   public Monoid catsKernelStdMonoidForTuple1(final Monoid A0) {
      return TupleInstances2.catsKernelStdMonoidForTuple1$(this, A0);
   }

   public Band catsKernelStdBandForTuple2(final Band A0, final Band A1) {
      return TupleInstances2.catsKernelStdBandForTuple2$(this, A0, A1);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple2(final CommutativeSemigroup A0, final CommutativeSemigroup A1) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple2$(this, A0, A1);
   }

   public Monoid catsKernelStdMonoidForTuple2(final Monoid A0, final Monoid A1) {
      return TupleInstances2.catsKernelStdMonoidForTuple2$(this, A0, A1);
   }

   public Band catsKernelStdBandForTuple3(final Band A0, final Band A1, final Band A2) {
      return TupleInstances2.catsKernelStdBandForTuple3$(this, A0, A1, A2);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple3(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple3$(this, A0, A1, A2);
   }

   public Monoid catsKernelStdMonoidForTuple3(final Monoid A0, final Monoid A1, final Monoid A2) {
      return TupleInstances2.catsKernelStdMonoidForTuple3$(this, A0, A1, A2);
   }

   public Band catsKernelStdBandForTuple4(final Band A0, final Band A1, final Band A2, final Band A3) {
      return TupleInstances2.catsKernelStdBandForTuple4$(this, A0, A1, A2, A3);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple4(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple4$(this, A0, A1, A2, A3);
   }

   public Monoid catsKernelStdMonoidForTuple4(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3) {
      return TupleInstances2.catsKernelStdMonoidForTuple4$(this, A0, A1, A2, A3);
   }

   public Band catsKernelStdBandForTuple5(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4) {
      return TupleInstances2.catsKernelStdBandForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple5(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Monoid catsKernelStdMonoidForTuple5(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4) {
      return TupleInstances2.catsKernelStdMonoidForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Band catsKernelStdBandForTuple6(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5) {
      return TupleInstances2.catsKernelStdBandForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple6(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Monoid catsKernelStdMonoidForTuple6(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5) {
      return TupleInstances2.catsKernelStdMonoidForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Band catsKernelStdBandForTuple7(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6) {
      return TupleInstances2.catsKernelStdBandForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple7(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Monoid catsKernelStdMonoidForTuple7(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6) {
      return TupleInstances2.catsKernelStdMonoidForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Band catsKernelStdBandForTuple8(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7) {
      return TupleInstances2.catsKernelStdBandForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple8(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Monoid catsKernelStdMonoidForTuple8(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7) {
      return TupleInstances2.catsKernelStdMonoidForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Band catsKernelStdBandForTuple9(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8) {
      return TupleInstances2.catsKernelStdBandForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple9(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Monoid catsKernelStdMonoidForTuple9(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8) {
      return TupleInstances2.catsKernelStdMonoidForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Band catsKernelStdBandForTuple10(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9) {
      return TupleInstances2.catsKernelStdBandForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple10(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Monoid catsKernelStdMonoidForTuple10(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9) {
      return TupleInstances2.catsKernelStdMonoidForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Band catsKernelStdBandForTuple11(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10) {
      return TupleInstances2.catsKernelStdBandForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple11(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Monoid catsKernelStdMonoidForTuple11(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10) {
      return TupleInstances2.catsKernelStdMonoidForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Band catsKernelStdBandForTuple12(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11) {
      return TupleInstances2.catsKernelStdBandForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple12(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Monoid catsKernelStdMonoidForTuple12(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11) {
      return TupleInstances2.catsKernelStdMonoidForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Band catsKernelStdBandForTuple13(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12) {
      return TupleInstances2.catsKernelStdBandForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple13(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Monoid catsKernelStdMonoidForTuple13(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12) {
      return TupleInstances2.catsKernelStdMonoidForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Band catsKernelStdBandForTuple14(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13) {
      return TupleInstances2.catsKernelStdBandForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple14(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Monoid catsKernelStdMonoidForTuple14(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13) {
      return TupleInstances2.catsKernelStdMonoidForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Band catsKernelStdBandForTuple15(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14) {
      return TupleInstances2.catsKernelStdBandForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple15(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Monoid catsKernelStdMonoidForTuple15(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14) {
      return TupleInstances2.catsKernelStdMonoidForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Band catsKernelStdBandForTuple16(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15) {
      return TupleInstances2.catsKernelStdBandForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple16(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Monoid catsKernelStdMonoidForTuple16(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15) {
      return TupleInstances2.catsKernelStdMonoidForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Band catsKernelStdBandForTuple17(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16) {
      return TupleInstances2.catsKernelStdBandForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple17(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Monoid catsKernelStdMonoidForTuple17(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16) {
      return TupleInstances2.catsKernelStdMonoidForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Band catsKernelStdBandForTuple18(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17) {
      return TupleInstances2.catsKernelStdBandForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple18(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Monoid catsKernelStdMonoidForTuple18(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17) {
      return TupleInstances2.catsKernelStdMonoidForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Band catsKernelStdBandForTuple19(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18) {
      return TupleInstances2.catsKernelStdBandForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple19(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Monoid catsKernelStdMonoidForTuple19(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18) {
      return TupleInstances2.catsKernelStdMonoidForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Band catsKernelStdBandForTuple20(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19) {
      return TupleInstances2.catsKernelStdBandForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple20(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Monoid catsKernelStdMonoidForTuple20(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19) {
      return TupleInstances2.catsKernelStdMonoidForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Band catsKernelStdBandForTuple21(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19, final Band A20) {
      return TupleInstances2.catsKernelStdBandForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple21(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19, final CommutativeSemigroup A20) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Monoid catsKernelStdMonoidForTuple21(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19, final Monoid A20) {
      return TupleInstances2.catsKernelStdMonoidForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Band catsKernelStdBandForTuple22(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19, final Band A20, final Band A21) {
      return TupleInstances2.catsKernelStdBandForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple22(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19, final CommutativeSemigroup A20, final CommutativeSemigroup A21) {
      return TupleInstances2.catsKernelStdCommutativeSemigroupForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Monoid catsKernelStdMonoidForTuple22(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19, final Monoid A20, final Monoid A21) {
      return TupleInstances2.catsKernelStdMonoidForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Semigroup catsKernelStdSemigroupForTuple1(final Semigroup A0) {
      return TupleInstances3.catsKernelStdSemigroupForTuple1$(this, A0);
   }

   public Eq catsKernelStdEqForTuple1(final Eq A0) {
      return TupleInstances3.catsKernelStdEqForTuple1$(this, A0);
   }

   public Semigroup catsKernelStdSemigroupForTuple2(final Semigroup A0, final Semigroup A1) {
      return TupleInstances3.catsKernelStdSemigroupForTuple2$(this, A0, A1);
   }

   public Eq catsKernelStdEqForTuple2(final Eq A0, final Eq A1) {
      return TupleInstances3.catsKernelStdEqForTuple2$(this, A0, A1);
   }

   public Semigroup catsKernelStdSemigroupForTuple3(final Semigroup A0, final Semigroup A1, final Semigroup A2) {
      return TupleInstances3.catsKernelStdSemigroupForTuple3$(this, A0, A1, A2);
   }

   public Eq catsKernelStdEqForTuple3(final Eq A0, final Eq A1, final Eq A2) {
      return TupleInstances3.catsKernelStdEqForTuple3$(this, A0, A1, A2);
   }

   public Semigroup catsKernelStdSemigroupForTuple4(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3) {
      return TupleInstances3.catsKernelStdSemigroupForTuple4$(this, A0, A1, A2, A3);
   }

   public Eq catsKernelStdEqForTuple4(final Eq A0, final Eq A1, final Eq A2, final Eq A3) {
      return TupleInstances3.catsKernelStdEqForTuple4$(this, A0, A1, A2, A3);
   }

   public Semigroup catsKernelStdSemigroupForTuple5(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4) {
      return TupleInstances3.catsKernelStdSemigroupForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Eq catsKernelStdEqForTuple5(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4) {
      return TupleInstances3.catsKernelStdEqForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Semigroup catsKernelStdSemigroupForTuple6(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5) {
      return TupleInstances3.catsKernelStdSemigroupForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Eq catsKernelStdEqForTuple6(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5) {
      return TupleInstances3.catsKernelStdEqForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Semigroup catsKernelStdSemigroupForTuple7(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6) {
      return TupleInstances3.catsKernelStdSemigroupForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Eq catsKernelStdEqForTuple7(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6) {
      return TupleInstances3.catsKernelStdEqForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Semigroup catsKernelStdSemigroupForTuple8(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7) {
      return TupleInstances3.catsKernelStdSemigroupForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Eq catsKernelStdEqForTuple8(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7) {
      return TupleInstances3.catsKernelStdEqForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Semigroup catsKernelStdSemigroupForTuple9(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8) {
      return TupleInstances3.catsKernelStdSemigroupForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Eq catsKernelStdEqForTuple9(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8) {
      return TupleInstances3.catsKernelStdEqForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Semigroup catsKernelStdSemigroupForTuple10(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9) {
      return TupleInstances3.catsKernelStdSemigroupForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Eq catsKernelStdEqForTuple10(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9) {
      return TupleInstances3.catsKernelStdEqForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Semigroup catsKernelStdSemigroupForTuple11(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10) {
      return TupleInstances3.catsKernelStdSemigroupForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Eq catsKernelStdEqForTuple11(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10) {
      return TupleInstances3.catsKernelStdEqForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Semigroup catsKernelStdSemigroupForTuple12(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11) {
      return TupleInstances3.catsKernelStdSemigroupForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Eq catsKernelStdEqForTuple12(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11) {
      return TupleInstances3.catsKernelStdEqForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Semigroup catsKernelStdSemigroupForTuple13(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12) {
      return TupleInstances3.catsKernelStdSemigroupForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Eq catsKernelStdEqForTuple13(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12) {
      return TupleInstances3.catsKernelStdEqForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Semigroup catsKernelStdSemigroupForTuple14(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13) {
      return TupleInstances3.catsKernelStdSemigroupForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Eq catsKernelStdEqForTuple14(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13) {
      return TupleInstances3.catsKernelStdEqForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Semigroup catsKernelStdSemigroupForTuple15(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14) {
      return TupleInstances3.catsKernelStdSemigroupForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Eq catsKernelStdEqForTuple15(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14) {
      return TupleInstances3.catsKernelStdEqForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Semigroup catsKernelStdSemigroupForTuple16(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15) {
      return TupleInstances3.catsKernelStdSemigroupForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Eq catsKernelStdEqForTuple16(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15) {
      return TupleInstances3.catsKernelStdEqForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Semigroup catsKernelStdSemigroupForTuple17(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16) {
      return TupleInstances3.catsKernelStdSemigroupForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Eq catsKernelStdEqForTuple17(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16) {
      return TupleInstances3.catsKernelStdEqForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Semigroup catsKernelStdSemigroupForTuple18(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17) {
      return TupleInstances3.catsKernelStdSemigroupForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Eq catsKernelStdEqForTuple18(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17) {
      return TupleInstances3.catsKernelStdEqForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Semigroup catsKernelStdSemigroupForTuple19(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18) {
      return TupleInstances3.catsKernelStdSemigroupForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Eq catsKernelStdEqForTuple19(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18) {
      return TupleInstances3.catsKernelStdEqForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Semigroup catsKernelStdSemigroupForTuple20(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19) {
      return TupleInstances3.catsKernelStdSemigroupForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Eq catsKernelStdEqForTuple20(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19) {
      return TupleInstances3.catsKernelStdEqForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Semigroup catsKernelStdSemigroupForTuple21(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19, final Semigroup A20) {
      return TupleInstances3.catsKernelStdSemigroupForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Eq catsKernelStdEqForTuple21(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19, final Eq A20) {
      return TupleInstances3.catsKernelStdEqForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Semigroup catsKernelStdSemigroupForTuple22(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19, final Semigroup A20, final Semigroup A21) {
      return TupleInstances3.catsKernelStdSemigroupForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Eq catsKernelStdEqForTuple22(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19, final Eq A20, final Eq A21) {
      return TupleInstances3.catsKernelStdEqForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   /** @deprecated */
   public Order catsKernelStdOrderForStream(final Order evidence$1) {
      return StreamInstances.catsKernelStdOrderForStream$(this, evidence$1);
   }

   /** @deprecated */
   public Monoid catsKernelStdMonoidForStream() {
      return StreamInstances.catsKernelStdMonoidForStream$(this);
   }

   /** @deprecated */
   public PartialOrder catsKernelStdPartialOrderForStream(final PartialOrder evidence$2) {
      return StreamInstances1.catsKernelStdPartialOrderForStream$(this, evidence$2);
   }

   /** @deprecated */
   public Hash catsKernelStdHashForStream(final Hash evidence$3) {
      return StreamInstances1.catsKernelStdHashForStream$(this, evidence$3);
   }

   /** @deprecated */
   public Eq catsKernelStdEqForStream(final Eq evidence$4) {
      return StreamInstances2.catsKernelStdEqForStream$(this, evidence$4);
   }

   public Order catsKernelStdOrderForSeq(final Order evidence$1) {
      return SeqInstances.catsKernelStdOrderForSeq$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForSeq() {
      return SeqInstances.catsKernelStdMonoidForSeq$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForSeq(final PartialOrder evidence$2) {
      return SeqInstances1.catsKernelStdPartialOrderForSeq$(this, evidence$2);
   }

   public Hash catsKernelStdHashForSeq(final Hash evidence$3) {
      return SeqInstances1.catsKernelStdHashForSeq$(this, evidence$3);
   }

   public Eq catsKernelStdEqForSeq(final Eq evidence$4) {
      return SeqInstances2.catsKernelStdEqForSeq$(this, evidence$4);
   }

   public Hash catsKernelStdHashForSet() {
      return SetInstances.catsKernelStdHashForSet$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForSet() {
      return SetInstances1.catsKernelStdPartialOrderForSet$(this);
   }

   public BoundedSemilattice catsKernelStdSemilatticeForSet() {
      return SetInstances1.catsKernelStdSemilatticeForSet$(this);
   }

   public Order catsKernelStdOrderForQueue(final Order evidence$1) {
      return QueueInstances.catsKernelStdOrderForQueue$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForQueue() {
      return QueueInstances.catsKernelStdMonoidForQueue$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForQueue(final PartialOrder evidence$2) {
      return QueueInstances1.catsKernelStdPartialOrderForQueue$(this, evidence$2);
   }

   public Hash catsKernelStdHashForQueue(final Hash evidence$3) {
      return QueueInstances1.catsKernelStdHashForQueue$(this, evidence$3);
   }

   public Eq catsKernelStdEqForQueue(final Eq evidence$4) {
      return QueueInstances2.catsKernelStdEqForQueue$(this, evidence$4);
   }

   public PartialOrdering catsKernelPartialOrderingForPartialOrder(final PartialOrder ev) {
      return PartialOrderToPartialOrderingConversion.catsKernelPartialOrderingForPartialOrder$(this, ev);
   }

   public Ordering catsKernelOrderingForOrder(final Order ev) {
      return OrderToOrderingConversion.catsKernelOrderingForOrder$(this, ev);
   }

   public Order catsKernelStdOrderForOption(final Order evidence$1) {
      return OptionInstances.catsKernelStdOrderForOption$(this, evidence$1);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForOption(final CommutativeSemigroup evidence$2) {
      return OptionInstances.catsKernelStdCommutativeMonoidForOption$(this, evidence$2);
   }

   public Monoid catsKernelStdMonoidForOption(final Semigroup evidence$3) {
      return OptionInstances.catsKernelStdMonoidForOption$(this, evidence$3);
   }

   public PartialOrder catsKernelStdPartialOrderForOption(final PartialOrder evidence$4) {
      return OptionInstances0.catsKernelStdPartialOrderForOption$(this, evidence$4);
   }

   public Hash catsKernelStdHashForOption(final Hash evidence$5) {
      return OptionInstances1.catsKernelStdHashForOption$(this, evidence$5);
   }

   public Eq catsKernelStdEqForOption(final Eq evidence$6) {
      return OptionInstances2.catsKernelStdEqForOption$(this, evidence$6);
   }

   public Hash catsKernelStdHashForMap(final Hash evidence$1, final Hash evidence$2) {
      return MapInstances.catsKernelStdHashForMap$(this, evidence$1, evidence$2);
   }

   public CommutativeMonoid catsKernelStdCommutativeMonoidForMap(final CommutativeSemigroup evidence$3) {
      return MapInstances.catsKernelStdCommutativeMonoidForMap$(this, evidence$3);
   }

   public Eq catsKernelStdEqForMap(final Eq evidence$4) {
      return MapInstances1.catsKernelStdEqForMap$(this, evidence$4);
   }

   public Monoid catsKernelStdMonoidForMap(final Semigroup evidence$5) {
      return MapInstances1.catsKernelStdMonoidForMap$(this, evidence$5);
   }

   public Order catsKernelStdOrderForList(final Order evidence$1) {
      return ListInstances.catsKernelStdOrderForList$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForList() {
      return ListInstances.catsKernelStdMonoidForList$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForList(final PartialOrder evidence$2) {
      return ListInstances1.catsKernelStdPartialOrderForList$(this, evidence$2);
   }

   public Hash catsKernelStdHashForList(final Hash evidence$3) {
      return ListInstances1.catsKernelStdHashForList$(this, evidence$3);
   }

   public Eq catsKernelStdEqForList(final Eq evidence$4) {
      return ListInstances2.catsKernelStdEqForList$(this, evidence$4);
   }

   public Order catsKernelStdOrderForLazyList(final Order evidence$1) {
      return LazyListInstances.catsKernelStdOrderForLazyList$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForLazyList() {
      return LazyListInstances.catsKernelStdMonoidForLazyList$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForLazyList(final PartialOrder evidence$2) {
      return LazyListInstances1.catsKernelStdPartialOrderForLazyList$(this, evidence$2);
   }

   public Hash catsKernelStdHashForLazyList(final Hash evidence$3) {
      return LazyListInstances1.catsKernelStdHashForLazyList$(this, evidence$3);
   }

   public Eq catsKernelStdEqForLazyList(final Eq evidence$4) {
      return LazyListInstances2.catsKernelStdEqForLazyList$(this, evidence$4);
   }

   public Hashing catsKernelHashToHashing(final Hash ev) {
      return HashToHashingConversion.catsKernelHashToHashing$(this, ev);
   }

   public Order catsKernelOrderForFunction0(final Order ev) {
      return FunctionInstances.catsKernelOrderForFunction0$(this, ev);
   }

   public CommutativeGroup catsKernelCommutativeGroupForFunction0(final CommutativeGroup G) {
      return FunctionInstances.catsKernelCommutativeGroupForFunction0$(this, G);
   }

   public CommutativeGroup catsKernelCommutativeGroupForFunction1(final CommutativeGroup G) {
      return FunctionInstances.catsKernelCommutativeGroupForFunction1$(this, G);
   }

   public Hash catsKernelHashForFunction0(final Hash ev) {
      return FunctionInstances0.catsKernelHashForFunction0$(this, ev);
   }

   public PartialOrder catsKernelPartialOrderForFunction0(final PartialOrder ev) {
      return FunctionInstances0.catsKernelPartialOrderForFunction0$(this, ev);
   }

   public Group catsKernelGroupForFunction0(final Group G) {
      return FunctionInstances0.catsKernelGroupForFunction0$(this, G);
   }

   public Group catsKernelGroupForFunction1(final Group G) {
      return FunctionInstances0.catsKernelGroupForFunction1$(this, G);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForFunction0(final BoundedSemilattice G) {
      return FunctionInstances0.catsKernelBoundedSemilatticeForFunction0$(this, G);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForFunction1(final BoundedSemilattice G) {
      return FunctionInstances0.catsKernelBoundedSemilatticeForFunction1$(this, G);
   }

   public Eq catsKernelEqForFunction0(final Eq ev) {
      return FunctionInstances1.catsKernelEqForFunction0$(this, ev);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForFunction0(final CommutativeMonoid M) {
      return FunctionInstances1.catsKernelCommutativeMonoidForFunction0$(this, M);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForFunction1(final CommutativeMonoid M) {
      return FunctionInstances1.catsKernelCommutativeMonoidForFunction1$(this, M);
   }

   public Semilattice catsKernelSemilatticeForFunction0(final Semilattice M) {
      return FunctionInstances1.catsKernelSemilatticeForFunction0$(this, M);
   }

   public Semilattice catsKernelSemilatticeForFunction1(final Semilattice M) {
      return FunctionInstances1.catsKernelSemilatticeForFunction1$(this, M);
   }

   public Monoid catsKernelMonoidForFunction0(final Monoid M) {
      return FunctionInstances2.catsKernelMonoidForFunction0$(this, M);
   }

   public Monoid catsKernelMonoidForFunction1(final Monoid M) {
      return FunctionInstances2.catsKernelMonoidForFunction1$(this, M);
   }

   public Band catsKernelBandForFunction0(final Band S) {
      return FunctionInstances2.catsKernelBandForFunction0$(this, S);
   }

   public Band catsKernelBandForFunction1(final Band S) {
      return FunctionInstances2.catsKernelBandForFunction1$(this, S);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForFunction0(final CommutativeSemigroup S) {
      return FunctionInstances3.catsKernelCommutativeSemigroupForFunction0$(this, S);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForFunction1(final CommutativeSemigroup S) {
      return FunctionInstances3.catsKernelCommutativeSemigroupForFunction1$(this, S);
   }

   public Semigroup catsKernelSemigroupForFunction0(final Semigroup S) {
      return FunctionInstances4.catsKernelSemigroupForFunction0$(this, S);
   }

   public Semigroup catsKernelSemigroupForFunction1(final Semigroup S) {
      return FunctionInstances4.catsKernelSemigroupForFunction1$(this, S);
   }

   public Order catsStdOrderForEither(final Order A, final Order B) {
      return EitherInstances.catsStdOrderForEither$(this, A, B);
   }

   public Monoid catsDataMonoidForEither(final Monoid B) {
      return EitherInstances.catsDataMonoidForEither$(this, B);
   }

   public Semigroup catsDataSemigroupForEither(final Semigroup B) {
      return EitherInstances0.catsDataSemigroupForEither$(this, B);
   }

   public PartialOrder catsStdPartialOrderForEither(final PartialOrder A, final PartialOrder B) {
      return EitherInstances0.catsStdPartialOrderForEither$(this, A, B);
   }

   public Hash catsStdHashForEither(final Hash A, final Hash B) {
      return EitherInstances0.catsStdHashForEither$(this, A, B);
   }

   public Eq catsStdEqForEither(final Eq A, final Eq B) {
      return EitherInstances1.catsStdEqForEither$(this, A, B);
   }

   public Equiv catsKernelEquivForEq(final Eq ev) {
      return EqToEquivConversion.catsKernelEquivForEq$(this, ev);
   }

   public Order catsKernelStdOrderForArraySeq(final Order evidence$1) {
      return ArraySeqInstances.catsKernelStdOrderForArraySeq$(this, evidence$1);
   }

   public Monoid catsKernelStdMonoidForArraySeq() {
      return ArraySeqInstances.catsKernelStdMonoidForArraySeq$(this);
   }

   public PartialOrder catsKernelStdPartialOrderForArraySeq(final PartialOrder evidence$2) {
      return ArraySeqInstances.ArraySeqInstances1.catsKernelStdPartialOrderForArraySeq$(this, evidence$2);
   }

   public Hash catsKernelStdHashForArraySeq(final Hash evidence$3) {
      return ArraySeqInstances.ArraySeqInstances1.catsKernelStdHashForArraySeq$(this, evidence$3);
   }

   public Eq catsKernelStdEqForArraySeq(final Eq evidence$4) {
      return ArraySeqInstances.ArraySeqInstances2.catsKernelStdEqForArraySeq$(this, evidence$4);
   }

   public Order catsKernelStdOrderForDeadline() {
      return catsKernelStdOrderForDeadline;
   }

   public void cats$kernel$instances$DeadlineInstances$_setter_$catsKernelStdOrderForDeadline_$eq(final Order x$1) {
      catsKernelStdOrderForDeadline = x$1;
   }

   public Order catsKernelStdOrderForFiniteDuration() {
      return catsKernelStdOrderForFiniteDuration;
   }

   public CommutativeGroup catsKernelStdGroupForFiniteDuration() {
      return catsKernelStdGroupForFiniteDuration;
   }

   public void cats$kernel$instances$FiniteDurationInstances$_setter_$catsKernelStdOrderForFiniteDuration_$eq(final Order x$1) {
      catsKernelStdOrderForFiniteDuration = x$1;
   }

   public void cats$kernel$instances$FiniteDurationInstances$_setter_$catsKernelStdGroupForFiniteDuration_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForFiniteDuration = x$1;
   }

   public Order catsKernelStdOrderForUUID() {
      return catsKernelStdOrderForUUID;
   }

   public void cats$kernel$instances$UUIDInstances$_setter_$catsKernelStdOrderForUUID_$eq(final Order x$1) {
      catsKernelStdOrderForUUID = x$1;
   }

   public Order catsKernelStdOrderForUnit() {
      return catsKernelStdOrderForUnit;
   }

   public BoundedSemilattice catsKernelStdAlgebraForUnit() {
      return catsKernelStdAlgebraForUnit;
   }

   public void cats$kernel$instances$UnitInstances$_setter_$catsKernelStdOrderForUnit_$eq(final Order x$1) {
      catsKernelStdOrderForUnit = x$1;
   }

   public void cats$kernel$instances$UnitInstances$_setter_$catsKernelStdAlgebraForUnit_$eq(final BoundedSemilattice x$1) {
      catsKernelStdAlgebraForUnit = x$1;
   }

   public Order catsKernelStdOrderForSymbol() {
      return catsKernelStdOrderForSymbol;
   }

   public void cats$kernel$instances$SymbolInstances$_setter_$catsKernelStdOrderForSymbol_$eq(final Order x$1) {
      catsKernelStdOrderForSymbol = x$1;
   }

   public Order catsKernelStdOrderForString() {
      return catsKernelStdOrderForString;
   }

   public Monoid catsKernelStdMonoidForString() {
      return catsKernelStdMonoidForString;
   }

   public void cats$kernel$instances$StringInstances$_setter_$catsKernelStdOrderForString_$eq(final Order x$1) {
      catsKernelStdOrderForString = x$1;
   }

   public void cats$kernel$instances$StringInstances$_setter_$catsKernelStdMonoidForString_$eq(final Monoid x$1) {
      catsKernelStdMonoidForString = x$1;
   }

   public Order catsKernelStdOrderForShort() {
      return catsKernelStdOrderForShort;
   }

   public CommutativeGroup catsKernelStdGroupForShort() {
      return catsKernelStdGroupForShort;
   }

   public void cats$kernel$instances$ShortInstances$_setter_$catsKernelStdOrderForShort_$eq(final Order x$1) {
      catsKernelStdOrderForShort = x$1;
   }

   public void cats$kernel$instances$ShortInstances$_setter_$catsKernelStdGroupForShort_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForShort = x$1;
   }

   public Order catsKernelStdOrderForLong() {
      return catsKernelStdOrderForLong;
   }

   public CommutativeGroup catsKernelStdGroupForLong() {
      return catsKernelStdGroupForLong;
   }

   public void cats$kernel$instances$LongInstances$_setter_$catsKernelStdOrderForLong_$eq(final Order x$1) {
      catsKernelStdOrderForLong = x$1;
   }

   public void cats$kernel$instances$LongInstances$_setter_$catsKernelStdGroupForLong_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForLong = x$1;
   }

   public Order catsKernelStdOrderForInt() {
      return catsKernelStdOrderForInt;
   }

   public CommutativeGroup catsKernelStdGroupForInt() {
      return catsKernelStdGroupForInt;
   }

   public void cats$kernel$instances$IntInstances$_setter_$catsKernelStdOrderForInt_$eq(final Order x$1) {
      catsKernelStdOrderForInt = x$1;
   }

   public void cats$kernel$instances$IntInstances$_setter_$catsKernelStdGroupForInt_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForInt = x$1;
   }

   public Order catsKernelStdOrderForFloat() {
      return catsKernelStdOrderForFloat;
   }

   public CommutativeGroup catsKernelStdGroupForFloat() {
      return catsKernelStdGroupForFloat;
   }

   public void cats$kernel$instances$FloatInstances$_setter_$catsKernelStdOrderForFloat_$eq(final Order x$1) {
      catsKernelStdOrderForFloat = x$1;
   }

   public void cats$kernel$instances$FloatInstances$_setter_$catsKernelStdGroupForFloat_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForFloat = x$1;
   }

   public Order catsKernelStdOrderForDuration() {
      return catsKernelStdOrderForDuration;
   }

   public CommutativeGroup catsKernelStdGroupForDuration() {
      return catsKernelStdGroupForDuration;
   }

   public void cats$kernel$instances$DurationInstances$_setter_$catsKernelStdOrderForDuration_$eq(final Order x$1) {
      catsKernelStdOrderForDuration = x$1;
   }

   public void cats$kernel$instances$DurationInstances$_setter_$catsKernelStdGroupForDuration_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForDuration = x$1;
   }

   public Order catsKernelStdOrderForDouble() {
      return catsKernelStdOrderForDouble;
   }

   public CommutativeGroup catsKernelStdGroupForDouble() {
      return catsKernelStdGroupForDouble;
   }

   public void cats$kernel$instances$DoubleInstances$_setter_$catsKernelStdOrderForDouble_$eq(final Order x$1) {
      catsKernelStdOrderForDouble = x$1;
   }

   public void cats$kernel$instances$DoubleInstances$_setter_$catsKernelStdGroupForDouble_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForDouble = x$1;
   }

   public CharOrder catsKernelStdOrderForChar() {
      return catsKernelStdOrderForChar;
   }

   public void cats$kernel$instances$CharInstances$_setter_$catsKernelStdOrderForChar_$eq(final CharOrder x$1) {
      catsKernelStdOrderForChar = x$1;
   }

   public Order catsKernelStdOrderForByte() {
      return catsKernelStdOrderForByte;
   }

   public CommutativeGroup catsKernelStdGroupForByte() {
      return catsKernelStdGroupForByte;
   }

   public void cats$kernel$instances$ByteInstances$_setter_$catsKernelStdOrderForByte_$eq(final Order x$1) {
      catsKernelStdOrderForByte = x$1;
   }

   public void cats$kernel$instances$ByteInstances$_setter_$catsKernelStdGroupForByte_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForByte = x$1;
   }

   public Order catsKernelStdOrderForBoolean() {
      return catsKernelStdOrderForBoolean;
   }

   public void cats$kernel$instances$BooleanInstances$_setter_$catsKernelStdOrderForBoolean_$eq(final Order x$1) {
      catsKernelStdOrderForBoolean = x$1;
   }

   public PartialOrder catsKernelStdOrderForBitSet() {
      return catsKernelStdOrderForBitSet;
   }

   public BoundedSemilattice catsKernelStdSemilatticeForBitSet() {
      return catsKernelStdSemilatticeForBitSet;
   }

   public void cats$kernel$instances$BitSetInstances$_setter_$catsKernelStdOrderForBitSet_$eq(final PartialOrder x$1) {
      catsKernelStdOrderForBitSet = x$1;
   }

   public void cats$kernel$instances$BitSetInstances$_setter_$catsKernelStdSemilatticeForBitSet_$eq(final BoundedSemilattice x$1) {
      catsKernelStdSemilatticeForBitSet = x$1;
   }

   public Order catsKernelStdOrderForBigInt() {
      return catsKernelStdOrderForBigInt;
   }

   public CommutativeGroup catsKernelStdGroupForBigInt() {
      return catsKernelStdGroupForBigInt;
   }

   public void cats$kernel$instances$BigIntInstances$_setter_$catsKernelStdOrderForBigInt_$eq(final Order x$1) {
      catsKernelStdOrderForBigInt = x$1;
   }

   public void cats$kernel$instances$BigIntInstances$_setter_$catsKernelStdGroupForBigInt_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForBigInt = x$1;
   }

   public Order catsKernelStdOrderForBigDecimal() {
      return catsKernelStdOrderForBigDecimal;
   }

   public CommutativeGroup catsKernelStdGroupForBigDecimal() {
      return catsKernelStdGroupForBigDecimal;
   }

   public void cats$kernel$instances$BigDecimalInstances$_setter_$catsKernelStdOrderForBigDecimal_$eq(final Order x$1) {
      catsKernelStdOrderForBigDecimal = x$1;
   }

   public void cats$kernel$instances$BigDecimalInstances$_setter_$catsKernelStdGroupForBigDecimal_$eq(final CommutativeGroup x$1) {
      catsKernelStdGroupForBigDecimal = x$1;
   }

   private package$() {
   }
}
