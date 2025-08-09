package cats.kernel;

import cats.kernel.instances.TupleBandInstances;
import cats.kernel.instances.TupleBoundedSemilatticeInstances;
import cats.kernel.instances.TupleCommutativeGroupInstances;
import cats.kernel.instances.TupleCommutativeMonoidInstances;
import cats.kernel.instances.TupleCommutativeSemigroupInstances;
import cats.kernel.instances.TupleGroupInstances;
import cats.kernel.instances.TupleMonoidInstances;
import cats.kernel.instances.TupleSemigroupInstances;
import cats.kernel.instances.TupleSemilatticeInstances;
import cats.kernel.instances.bitSet.package$;
import java.io.Serializable;
import scala.Function2;
import scala.Option;
import scala.collection.IterableOnce;
import scala.concurrent.ExecutionContext;
import scala.runtime.ModuleSerializationProxy;

public final class Semigroup$ extends SemigroupFunctions implements ScalaVersionSpecificMonoidInstances, TupleCommutativeGroupInstances, GroupInstances, Serializable {
   public static final Semigroup$ MODULE$ = new Semigroup$();

   static {
      ScalaVersionSpecificMonoidInstances.$init$(MODULE$);
      TupleSemigroupInstances.$init$(MODULE$);
      TupleBandInstances.$init$(MODULE$);
      TupleCommutativeSemigroupInstances.$init$(MODULE$);
      TupleMonoidInstances.$init$(MODULE$);
      TupleSemilatticeInstances.$init$(MODULE$);
      TupleCommutativeMonoidInstances.$init$(MODULE$);
      TupleGroupInstances.$init$(MODULE$);
      TupleBoundedSemilatticeInstances.$init$(MODULE$);
      TupleCommutativeGroupInstances.$init$(MODULE$);
      SemigroupInstances.$init$(MODULE$);
      CommutativeSemigroupInstances.$init$(MODULE$);
      BandInstances.$init$(MODULE$);
      MonoidInstances.$init$(MODULE$);
      CommutativeMonoidInstances.$init$(MODULE$);
      SemilatticeInstances.$init$(MODULE$);
      BoundedSemilatticeInstances.$init$(MODULE$);
      GroupInstances.$init$(MODULE$);
   }

   public Group catsKernelGroupForFunction0(final Group evidence$8) {
      return GroupInstances.catsKernelGroupForFunction0$(this, evidence$8);
   }

   public Group catsKernelGroupForFunction1(final Group evidence$9) {
      return GroupInstances.catsKernelGroupForFunction1$(this, evidence$9);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForFunction0(final BoundedSemilattice evidence$10) {
      return BoundedSemilatticeInstances.catsKernelBoundedSemilatticeForFunction0$(this, evidence$10);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForFunction1(final BoundedSemilattice evidence$11) {
      return BoundedSemilatticeInstances.catsKernelBoundedSemilatticeForFunction1$(this, evidence$11);
   }

   public Semilattice catsKernelSemilatticeForFunction0(final Semilattice evidence$12) {
      return SemilatticeInstances.catsKernelSemilatticeForFunction0$(this, evidence$12);
   }

   public Semilattice catsKernelSemilatticeForFunction1(final Semilattice evidence$13) {
      return SemilatticeInstances.catsKernelSemilatticeForFunction1$(this, evidence$13);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForFunction0(final CommutativeMonoid evidence$14) {
      return CommutativeMonoidInstances.catsKernelCommutativeMonoidForFunction0$(this, evidence$14);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForFunction1(final CommutativeMonoid evidence$15) {
      return CommutativeMonoidInstances.catsKernelCommutativeMonoidForFunction1$(this, evidence$15);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForOption(final CommutativeSemigroup evidence$16) {
      return CommutativeMonoidInstances.catsKernelCommutativeMonoidForOption$(this, evidence$16);
   }

   public Monoid catsKernelMonoidForFunction0(final Monoid evidence$17) {
      return MonoidInstances.catsKernelMonoidForFunction0$(this, evidence$17);
   }

   public Monoid catsKernelMonoidForFunction1(final Monoid evidence$18) {
      return MonoidInstances.catsKernelMonoidForFunction1$(this, evidence$18);
   }

   public Monoid catsKernelMonoidForMap(final Semigroup evidence$19) {
      return MonoidInstances.catsKernelMonoidForMap$(this, evidence$19);
   }

   public Semigroup catsKernelSemigroupForSortedMap(final Semigroup evidence$20) {
      return MonoidInstances.catsKernelSemigroupForSortedMap$(this, evidence$20);
   }

   public Monoid catsKernelMonoidForSortedMap(final Order evidence$21, final Semigroup evidence$22) {
      return MonoidInstances.catsKernelMonoidForSortedMap$(this, evidence$21, evidence$22);
   }

   public Monoid catsKernelMonoidForEither(final Monoid evidence$23) {
      return MonoidInstances.catsKernelMonoidForEither$(this, evidence$23);
   }

   public Monoid catsKernelMonoidForTry(final Monoid evidence$24) {
      return MonoidInstances.catsKernelMonoidForTry$(this, evidence$24);
   }

   public Monoid catsKernelMonoidForFuture(final Monoid A, final ExecutionContext ec) {
      return MonoidInstances.catsKernelMonoidForFuture$(this, A, ec);
   }

   public Monoid catsKernelMonoidForOption(final Semigroup evidence$25) {
      return MonoidInstances.catsKernelMonoidForOption$(this, evidence$25);
   }

   public Monoid catsKernelMonoidForSeq() {
      return MonoidInstances.catsKernelMonoidForSeq$(this);
   }

   public Band catsKernelBandForFunction0(final Band evidence$26) {
      return BandInstances.catsKernelBandForFunction0$(this, evidence$26);
   }

   public Band catsKernelBandForFunction1(final Band evidence$27) {
      return BandInstances.catsKernelBandForFunction1$(this, evidence$27);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForFunction0(final CommutativeSemigroup evidence$28) {
      return CommutativeSemigroupInstances.catsKernelCommutativeSemigroupForFunction0$(this, evidence$28);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForFunction1(final CommutativeSemigroup evidence$29) {
      return CommutativeSemigroupInstances.catsKernelCommutativeSemigroupForFunction1$(this, evidence$29);
   }

   public Semigroup catsKernelSemigroupForFunction0(final Semigroup evidence$30) {
      return SemigroupInstances.catsKernelSemigroupForFunction0$(this, evidence$30);
   }

   public Semigroup catsKernelSemigroupForFunction1(final Semigroup evidence$31) {
      return SemigroupInstances.catsKernelSemigroupForFunction1$(this, evidence$31);
   }

   public Semigroup catsKernelSemigroupForEither(final Semigroup evidence$32) {
      return SemigroupInstances.catsKernelSemigroupForEither$(this, evidence$32);
   }

   public Semigroup catsKernelSemigroupForTry(final Semigroup evidence$33) {
      return SemigroupInstances.catsKernelSemigroupForTry$(this, evidence$33);
   }

   public Semigroup catsKernelSemigroupForFuture(final Semigroup A, final ExecutionContext ec) {
      return SemigroupInstances.catsKernelSemigroupForFuture$(this, A, ec);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple1(final CommutativeGroup A0) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple1$(this, A0);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple2(final CommutativeGroup A0, final CommutativeGroup A1) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple2$(this, A0, A1);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple3(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple3$(this, A0, A1, A2);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple4(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple4$(this, A0, A1, A2, A3);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple5(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple6(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple7(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple8(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple9(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple10(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple11(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple12(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple13(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple14(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple15(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple16(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple17(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple18(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple19(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple20(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple21(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19, final CommutativeGroup A20) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public CommutativeGroup catsKernelCommutativeGroupForTuple22(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19, final CommutativeGroup A20, final CommutativeGroup A21) {
      return TupleCommutativeGroupInstances.catsKernelCommutativeGroupForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple1(final BoundedSemilattice A0) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple1$(this, A0);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple2(final BoundedSemilattice A0, final BoundedSemilattice A1) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple2$(this, A0, A1);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple3(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple3$(this, A0, A1, A2);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple4(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple4$(this, A0, A1, A2, A3);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple5(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple6(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple7(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple8(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple9(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple10(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple11(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple12(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple13(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple14(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple15(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple16(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple17(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple18(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple19(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple20(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple21(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19, final BoundedSemilattice A20) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForTuple22(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19, final BoundedSemilattice A20, final BoundedSemilattice A21) {
      return TupleBoundedSemilatticeInstances.catsKernelBoundedSemilatticeForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Group catsKernelGroupForTuple1(final Group A0) {
      return TupleGroupInstances.catsKernelGroupForTuple1$(this, A0);
   }

   public Group catsKernelGroupForTuple2(final Group A0, final Group A1) {
      return TupleGroupInstances.catsKernelGroupForTuple2$(this, A0, A1);
   }

   public Group catsKernelGroupForTuple3(final Group A0, final Group A1, final Group A2) {
      return TupleGroupInstances.catsKernelGroupForTuple3$(this, A0, A1, A2);
   }

   public Group catsKernelGroupForTuple4(final Group A0, final Group A1, final Group A2, final Group A3) {
      return TupleGroupInstances.catsKernelGroupForTuple4$(this, A0, A1, A2, A3);
   }

   public Group catsKernelGroupForTuple5(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4) {
      return TupleGroupInstances.catsKernelGroupForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Group catsKernelGroupForTuple6(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5) {
      return TupleGroupInstances.catsKernelGroupForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Group catsKernelGroupForTuple7(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6) {
      return TupleGroupInstances.catsKernelGroupForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Group catsKernelGroupForTuple8(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7) {
      return TupleGroupInstances.catsKernelGroupForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Group catsKernelGroupForTuple9(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8) {
      return TupleGroupInstances.catsKernelGroupForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Group catsKernelGroupForTuple10(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9) {
      return TupleGroupInstances.catsKernelGroupForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Group catsKernelGroupForTuple11(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10) {
      return TupleGroupInstances.catsKernelGroupForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Group catsKernelGroupForTuple12(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11) {
      return TupleGroupInstances.catsKernelGroupForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Group catsKernelGroupForTuple13(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12) {
      return TupleGroupInstances.catsKernelGroupForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Group catsKernelGroupForTuple14(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13) {
      return TupleGroupInstances.catsKernelGroupForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Group catsKernelGroupForTuple15(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14) {
      return TupleGroupInstances.catsKernelGroupForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Group catsKernelGroupForTuple16(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15) {
      return TupleGroupInstances.catsKernelGroupForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Group catsKernelGroupForTuple17(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16) {
      return TupleGroupInstances.catsKernelGroupForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Group catsKernelGroupForTuple18(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17) {
      return TupleGroupInstances.catsKernelGroupForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Group catsKernelGroupForTuple19(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18) {
      return TupleGroupInstances.catsKernelGroupForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Group catsKernelGroupForTuple20(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19) {
      return TupleGroupInstances.catsKernelGroupForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Group catsKernelGroupForTuple21(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20) {
      return TupleGroupInstances.catsKernelGroupForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Group catsKernelGroupForTuple22(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20, final Group A21) {
      return TupleGroupInstances.catsKernelGroupForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple1(final CommutativeMonoid A0) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple1$(this, A0);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple2(final CommutativeMonoid A0, final CommutativeMonoid A1) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple2$(this, A0, A1);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple3(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple3$(this, A0, A1, A2);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple4(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple4$(this, A0, A1, A2, A3);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple5(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple6(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple7(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple8(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple9(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple10(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple11(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple12(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple13(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple14(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple15(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple16(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple17(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple18(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple19(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple20(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple21(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19, final CommutativeMonoid A20) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForTuple22(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19, final CommutativeMonoid A20, final CommutativeMonoid A21) {
      return TupleCommutativeMonoidInstances.catsKernelCommutativeMonoidForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Semilattice catsKernelSemilatticeForTuple1(final Semilattice A0) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple1$(this, A0);
   }

   public Semilattice catsKernelSemilatticeForTuple2(final Semilattice A0, final Semilattice A1) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple2$(this, A0, A1);
   }

   public Semilattice catsKernelSemilatticeForTuple3(final Semilattice A0, final Semilattice A1, final Semilattice A2) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple3$(this, A0, A1, A2);
   }

   public Semilattice catsKernelSemilatticeForTuple4(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple4$(this, A0, A1, A2, A3);
   }

   public Semilattice catsKernelSemilatticeForTuple5(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Semilattice catsKernelSemilatticeForTuple6(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Semilattice catsKernelSemilatticeForTuple7(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Semilattice catsKernelSemilatticeForTuple8(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Semilattice catsKernelSemilatticeForTuple9(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Semilattice catsKernelSemilatticeForTuple10(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Semilattice catsKernelSemilatticeForTuple11(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Semilattice catsKernelSemilatticeForTuple12(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Semilattice catsKernelSemilatticeForTuple13(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Semilattice catsKernelSemilatticeForTuple14(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Semilattice catsKernelSemilatticeForTuple15(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Semilattice catsKernelSemilatticeForTuple16(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Semilattice catsKernelSemilatticeForTuple17(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Semilattice catsKernelSemilatticeForTuple18(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Semilattice catsKernelSemilatticeForTuple19(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Semilattice catsKernelSemilatticeForTuple20(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Semilattice catsKernelSemilatticeForTuple21(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Semilattice catsKernelSemilatticeForTuple22(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20, final Semilattice A21) {
      return TupleSemilatticeInstances.catsKernelSemilatticeForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Monoid catsKernelMonoidForTuple1(final Monoid A0) {
      return TupleMonoidInstances.catsKernelMonoidForTuple1$(this, A0);
   }

   public Monoid catsKernelMonoidForTuple2(final Monoid A0, final Monoid A1) {
      return TupleMonoidInstances.catsKernelMonoidForTuple2$(this, A0, A1);
   }

   public Monoid catsKernelMonoidForTuple3(final Monoid A0, final Monoid A1, final Monoid A2) {
      return TupleMonoidInstances.catsKernelMonoidForTuple3$(this, A0, A1, A2);
   }

   public Monoid catsKernelMonoidForTuple4(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3) {
      return TupleMonoidInstances.catsKernelMonoidForTuple4$(this, A0, A1, A2, A3);
   }

   public Monoid catsKernelMonoidForTuple5(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4) {
      return TupleMonoidInstances.catsKernelMonoidForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Monoid catsKernelMonoidForTuple6(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5) {
      return TupleMonoidInstances.catsKernelMonoidForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Monoid catsKernelMonoidForTuple7(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6) {
      return TupleMonoidInstances.catsKernelMonoidForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Monoid catsKernelMonoidForTuple8(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7) {
      return TupleMonoidInstances.catsKernelMonoidForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Monoid catsKernelMonoidForTuple9(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8) {
      return TupleMonoidInstances.catsKernelMonoidForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Monoid catsKernelMonoidForTuple10(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9) {
      return TupleMonoidInstances.catsKernelMonoidForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Monoid catsKernelMonoidForTuple11(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10) {
      return TupleMonoidInstances.catsKernelMonoidForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Monoid catsKernelMonoidForTuple12(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11) {
      return TupleMonoidInstances.catsKernelMonoidForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Monoid catsKernelMonoidForTuple13(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12) {
      return TupleMonoidInstances.catsKernelMonoidForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Monoid catsKernelMonoidForTuple14(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13) {
      return TupleMonoidInstances.catsKernelMonoidForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Monoid catsKernelMonoidForTuple15(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14) {
      return TupleMonoidInstances.catsKernelMonoidForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Monoid catsKernelMonoidForTuple16(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15) {
      return TupleMonoidInstances.catsKernelMonoidForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Monoid catsKernelMonoidForTuple17(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16) {
      return TupleMonoidInstances.catsKernelMonoidForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Monoid catsKernelMonoidForTuple18(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17) {
      return TupleMonoidInstances.catsKernelMonoidForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Monoid catsKernelMonoidForTuple19(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18) {
      return TupleMonoidInstances.catsKernelMonoidForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Monoid catsKernelMonoidForTuple20(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19) {
      return TupleMonoidInstances.catsKernelMonoidForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Monoid catsKernelMonoidForTuple21(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19, final Monoid A20) {
      return TupleMonoidInstances.catsKernelMonoidForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Monoid catsKernelMonoidForTuple22(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19, final Monoid A20, final Monoid A21) {
      return TupleMonoidInstances.catsKernelMonoidForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple1(final CommutativeSemigroup A0) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple1$(this, A0);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple2(final CommutativeSemigroup A0, final CommutativeSemigroup A1) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple2$(this, A0, A1);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple3(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple3$(this, A0, A1, A2);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple4(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple4$(this, A0, A1, A2, A3);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple5(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple6(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple7(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple8(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple9(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple10(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple11(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple12(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple13(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple14(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple15(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple16(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple17(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple18(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple19(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple20(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple21(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19, final CommutativeSemigroup A20) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForTuple22(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19, final CommutativeSemigroup A20, final CommutativeSemigroup A21) {
      return TupleCommutativeSemigroupInstances.catsKernelCommutativeSemigroupForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Band catsKernelBandForTuple1(final Band A0) {
      return TupleBandInstances.catsKernelBandForTuple1$(this, A0);
   }

   public Band catsKernelBandForTuple2(final Band A0, final Band A1) {
      return TupleBandInstances.catsKernelBandForTuple2$(this, A0, A1);
   }

   public Band catsKernelBandForTuple3(final Band A0, final Band A1, final Band A2) {
      return TupleBandInstances.catsKernelBandForTuple3$(this, A0, A1, A2);
   }

   public Band catsKernelBandForTuple4(final Band A0, final Band A1, final Band A2, final Band A3) {
      return TupleBandInstances.catsKernelBandForTuple4$(this, A0, A1, A2, A3);
   }

   public Band catsKernelBandForTuple5(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4) {
      return TupleBandInstances.catsKernelBandForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Band catsKernelBandForTuple6(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5) {
      return TupleBandInstances.catsKernelBandForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Band catsKernelBandForTuple7(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6) {
      return TupleBandInstances.catsKernelBandForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Band catsKernelBandForTuple8(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7) {
      return TupleBandInstances.catsKernelBandForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Band catsKernelBandForTuple9(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8) {
      return TupleBandInstances.catsKernelBandForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Band catsKernelBandForTuple10(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9) {
      return TupleBandInstances.catsKernelBandForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Band catsKernelBandForTuple11(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10) {
      return TupleBandInstances.catsKernelBandForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Band catsKernelBandForTuple12(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11) {
      return TupleBandInstances.catsKernelBandForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Band catsKernelBandForTuple13(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12) {
      return TupleBandInstances.catsKernelBandForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Band catsKernelBandForTuple14(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13) {
      return TupleBandInstances.catsKernelBandForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Band catsKernelBandForTuple15(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14) {
      return TupleBandInstances.catsKernelBandForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Band catsKernelBandForTuple16(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15) {
      return TupleBandInstances.catsKernelBandForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Band catsKernelBandForTuple17(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16) {
      return TupleBandInstances.catsKernelBandForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Band catsKernelBandForTuple18(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17) {
      return TupleBandInstances.catsKernelBandForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Band catsKernelBandForTuple19(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18) {
      return TupleBandInstances.catsKernelBandForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Band catsKernelBandForTuple20(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19) {
      return TupleBandInstances.catsKernelBandForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Band catsKernelBandForTuple21(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19, final Band A20) {
      return TupleBandInstances.catsKernelBandForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Band catsKernelBandForTuple22(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19, final Band A20, final Band A21) {
      return TupleBandInstances.catsKernelBandForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Semigroup catsKernelSemigroupForTuple1(final Semigroup A0) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple1$(this, A0);
   }

   public Semigroup catsKernelSemigroupForTuple2(final Semigroup A0, final Semigroup A1) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple2$(this, A0, A1);
   }

   public Semigroup catsKernelSemigroupForTuple3(final Semigroup A0, final Semigroup A1, final Semigroup A2) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple3$(this, A0, A1, A2);
   }

   public Semigroup catsKernelSemigroupForTuple4(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple4$(this, A0, A1, A2, A3);
   }

   public Semigroup catsKernelSemigroupForTuple5(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Semigroup catsKernelSemigroupForTuple6(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Semigroup catsKernelSemigroupForTuple7(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Semigroup catsKernelSemigroupForTuple8(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Semigroup catsKernelSemigroupForTuple9(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Semigroup catsKernelSemigroupForTuple10(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Semigroup catsKernelSemigroupForTuple11(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Semigroup catsKernelSemigroupForTuple12(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Semigroup catsKernelSemigroupForTuple13(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Semigroup catsKernelSemigroupForTuple14(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Semigroup catsKernelSemigroupForTuple15(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Semigroup catsKernelSemigroupForTuple16(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Semigroup catsKernelSemigroupForTuple17(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Semigroup catsKernelSemigroupForTuple18(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Semigroup catsKernelSemigroupForTuple19(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Semigroup catsKernelSemigroupForTuple20(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Semigroup catsKernelSemigroupForTuple21(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19, final Semigroup A20) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Semigroup catsKernelSemigroupForTuple22(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19, final Semigroup A20, final Semigroup A21) {
      return TupleSemigroupInstances.catsKernelSemigroupForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   /** @deprecated */
   public Monoid catsKernelMonoidForStream() {
      return ScalaVersionSpecificMonoidInstances.catsKernelMonoidForStream$(this);
   }

   public Monoid catsKernelMonoidForLazyList() {
      return ScalaVersionSpecificMonoidInstances.catsKernelMonoidForLazyList$(this);
   }

   public Monoid catsKernelMonoidForArraySeq() {
      return ScalaVersionSpecificMonoidInstances.catsKernelMonoidForArraySeq$(this);
   }

   public final Semigroup apply(final Semigroup ev) {
      return ev;
   }

   public Semigroup instance(final Function2 cmb) {
      return new Semigroup(cmb) {
         private final Function2 cmb$1;

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Semigroup reverse() {
            return Semigroup.reverse$(this);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object combine(final Object x, final Object y) {
            return this.cmb$1.apply(x, y);
         }

         public {
            this.cmb$1 = cmb$1;
            Semigroup.$init$(this);
         }
      };
   }

   public Semigroup first() {
      return new Semigroup() {
         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Semigroup reverse() {
            return Semigroup.reverse$(this);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object combine(final Object x, final Object y) {
            return x;
         }

         public {
            Semigroup.$init$(this);
         }
      };
   }

   public Semigroup last() {
      return new Semigroup() {
         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Semigroup reverse() {
            return Semigroup.reverse$(this);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object combine(final Object x, final Object y) {
            return y;
         }

         public {
            Semigroup.$init$(this);
         }
      };
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForBitSet() {
      return package$.MODULE$.catsKernelStdSemilatticeForBitSet();
   }

   public BoundedSemilattice catsKernelInstancesForUnit() {
      return cats.kernel.instances.unit.package$.MODULE$.catsKernelStdAlgebraForUnit();
   }

   public CommutativeGroup catsKernelCommutativeGroupForByte() {
      return cats.kernel.instances.byte.package$.MODULE$.catsKernelStdGroupForByte();
   }

   public CommutativeGroup catsKernelCommutativeGroupForShort() {
      return cats.kernel.instances.short.package$.MODULE$.catsKernelStdGroupForShort();
   }

   public CommutativeGroup catsKernelCommutativeGroupForInt() {
      return cats.kernel.instances.int.package$.MODULE$.catsKernelStdGroupForInt();
   }

   public CommutativeGroup catsKernelCommutativeGroupForLong() {
      return cats.kernel.instances.long.package$.MODULE$.catsKernelStdGroupForLong();
   }

   public CommutativeGroup catsKernelCommutativeGroupForBigInt() {
      return cats.kernel.instances.bigInt.package$.MODULE$.catsKernelStdGroupForBigInt();
   }

   public CommutativeGroup catsKernelCommutativeGroupForBigDecimal() {
      return cats.kernel.instances.bigDecimal.package$.MODULE$.catsKernelStdGroupForBigDecimal();
   }

   public CommutativeGroup catsKernelCommutativeGroupForDuration() {
      return cats.kernel.instances.duration.package$.MODULE$.catsKernelStdGroupForDuration();
   }

   public CommutativeGroup catsKernelCommutativeGroupForFiniteDuration() {
      return cats.kernel.instances.all.package$.MODULE$.catsKernelStdGroupForFiniteDuration();
   }

   public CommutativeGroup catsKernelCommutativeGroupForDouble() {
      return cats.kernel.instances.double.package$.MODULE$.catsKernelStdGroupForDouble();
   }

   public CommutativeGroup catsKernelCommutativeGroupForFloat() {
      return cats.kernel.instances.float.package$.MODULE$.catsKernelStdGroupForFloat();
   }

   public Monoid catsKernelMonoidForString() {
      return cats.kernel.instances.string.package$.MODULE$.catsKernelStdMonoidForString();
   }

   public Monoid catsKernelMonoidForList() {
      return cats.kernel.instances.list.package$.MODULE$.catsKernelStdMonoidForList();
   }

   public Monoid catsKernelMonoidForVector() {
      return cats.kernel.instances.vector.package$.MODULE$.catsKernelStdMonoidForVector();
   }

   public Monoid catsKernelMonoidForQueue() {
      return cats.kernel.instances.queue.package$.MODULE$.catsKernelStdMonoidForQueue();
   }

   public CommutativeGroup catsKernelCommutativeGroupForFunction0(final CommutativeGroup evidence$1) {
      return cats.kernel.instances.function.package$.MODULE$.catsKernelCommutativeGroupForFunction0(evidence$1);
   }

   public CommutativeGroup catsKernelCommutativeGroupForFunction1(final CommutativeGroup evidence$2) {
      return cats.kernel.instances.function.package$.MODULE$.catsKernelCommutativeGroupForFunction1(evidence$2);
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForSet() {
      return cats.kernel.instances.set.package$.MODULE$.catsKernelStdSemilatticeForSet();
   }

   public BoundedSemilattice catsKernelBoundedSemilatticeForSortedSet(final Order evidence$3) {
      return cats.kernel.instances.sortedSet.package$.MODULE$.catsKernelStdBoundedSemilatticeForSortedSet(evidence$3);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForMap(final CommutativeSemigroup evidence$4) {
      return cats.kernel.instances.map.package$.MODULE$.catsKernelStdCommutativeMonoidForMap(evidence$4);
   }

   public CommutativeSemigroup catsKernelCommutativeSemigroupForSortedMap(final CommutativeSemigroup evidence$5) {
      return cats.kernel.instances.sortedMap.package$.MODULE$.catsKernelStdCommutativeSemigroupForSortedMap(evidence$5);
   }

   public CommutativeMonoid catsKernelCommutativeMonoidForSortedMap(final Order evidence$6, final CommutativeSemigroup evidence$7) {
      return cats.kernel.instances.sortedMap.package$.MODULE$.catsKernelStdCommutativeMonoidForSortedMap(evidence$6, evidence$7);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Semigroup$.class);
   }

   private Semigroup$() {
   }
}
