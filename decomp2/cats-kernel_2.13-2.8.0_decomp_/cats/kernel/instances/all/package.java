package cats.kernel.instances.all;

import cats.kernel.Band;
import cats.kernel.BoundedSemilattice;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Hash;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
import cats.kernel.instances.CharOrder;
import scala.math.Equiv;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;
import scala.util.hashing.Hashing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019:Qa\u0001\u0003\t\u000251Qa\u0004\u0003\t\u0002AAQ\u0001J\u0001\u0005\u0002\u0015\nq\u0001]1dW\u0006<WM\u0003\u0002\u0006\r\u0005\u0019\u0011\r\u001c7\u000b\u0005\u001dA\u0011!C5ogR\fgnY3t\u0015\tI!\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0017\u0005!1-\u0019;t\u0007\u0001\u0001\"AD\u0001\u000e\u0003\u0011\u0011q\u0001]1dW\u0006<Wm\u0005\u0004\u0002#]Yb$\t\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005aIR\"\u0001\u0004\n\u0005i1!\u0001D!mY&s7\u000f^1oG\u0016\u001c\bC\u0001\r\u001d\u0013\tibA\u0001\fBY2Len\u001d;b]\u000e,7OQ5o\u0007>l\u0007/\u0019;1!\tAr$\u0003\u0002!\r\t1\u0012\t\u001c7J]N$\u0018M\\2fg\nKgnQ8na\u0006$\u0018\u0007\u0005\u0002\u0019E%\u00111E\u0002\u0002\u0017\u00032d\u0017J\\:uC:\u001cWm\u001d\"j]\u000e{W\u000e]1ue\u00051A(\u001b8jiz\"\u0012!\u0004"
)
public final class package {
   public static Order catsKernelStdOrderForDeadline() {
      return package$.MODULE$.catsKernelStdOrderForDeadline();
   }

   public static Hash catsKernelStdHashForSortedSet(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForSortedSet(evidence$3);
   }

   /** @deprecated */
   public static Hash catsKernelStdHashForSortedSet(final Order evidence$1, final Hash evidence$2) {
      return package$.MODULE$.catsKernelStdHashForSortedSet(evidence$1, evidence$2);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForSortedSet(final Order evidence$5) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForSortedSet(evidence$5);
   }

   public static Order catsKernelStdOrderForSortedSet(final Order evidence$4) {
      return package$.MODULE$.catsKernelStdOrderForSortedSet(evidence$4);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForSortedMap(final Order evidence$4, final CommutativeSemigroup evidence$5) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForSortedMap(evidence$4, evidence$5);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForSortedMap(final CommutativeSemigroup evidence$3) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForSortedMap(evidence$3);
   }

   /** @deprecated */
   public static Hash catsKernelStdHashForSortedMap(final Hash hashK, final Order orderK, final Hash hashV) {
      return package$.MODULE$.catsKernelStdHashForSortedMap(hashK, orderK, hashV);
   }

   public static Hash catsKernelStdHashForSortedMap(final Hash evidence$1, final Hash evidence$2) {
      return package$.MODULE$.catsKernelStdHashForSortedMap(evidence$1, evidence$2);
   }

   public static Order catsKernelStdOrderForSortedMap(final Order evidence$11) {
      return package$.MODULE$.catsKernelStdOrderForSortedMap(evidence$11);
   }

   public static PartialOrder catsKernelStdPartialOrderForSortedMap(final PartialOrder evidence$10) {
      return package$.MODULE$.catsKernelStdPartialOrderForSortedMap(evidence$10);
   }

   public static Monoid catsKernelStdMonoidForSortedMap(final Order evidence$8, final Semigroup evidence$9) {
      return package$.MODULE$.catsKernelStdMonoidForSortedMap(evidence$8, evidence$9);
   }

   public static Semigroup catsKernelStdSemigroupForSortedMap(final Semigroup evidence$7) {
      return package$.MODULE$.catsKernelStdSemigroupForSortedMap(evidence$7);
   }

   /** @deprecated */
   public static Eq catsKernelStdEqForSortedMap(final Order orderK, final Eq eqV) {
      return package$.MODULE$.catsKernelStdEqForSortedMap(orderK, eqV);
   }

   public static Eq catsKernelStdEqForSortedMap(final Eq evidence$6) {
      return package$.MODULE$.catsKernelStdEqForSortedMap(evidence$6);
   }

   public static CommutativeGroup catsKernelStdGroupForFiniteDuration() {
      return package$.MODULE$.catsKernelStdGroupForFiniteDuration();
   }

   public static Order catsKernelStdOrderForFiniteDuration() {
      return package$.MODULE$.catsKernelStdOrderForFiniteDuration();
   }

   public static Monoid catsKernelStdMonoidForVector() {
      return package$.MODULE$.catsKernelStdMonoidForVector();
   }

   public static Order catsKernelStdOrderForVector(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForVector(evidence$1);
   }

   public static Hash catsKernelStdHashForVector(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForVector(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForVector(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForVector(evidence$2);
   }

   public static Eq catsKernelStdEqForVector(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForVector(evidence$4);
   }

   public static Order catsKernelStdOrderForUUID() {
      return package$.MODULE$.catsKernelStdOrderForUUID();
   }

   public static BoundedSemilattice catsKernelStdAlgebraForUnit() {
      return package$.MODULE$.catsKernelStdAlgebraForUnit();
   }

   public static Order catsKernelStdOrderForUnit() {
      return package$.MODULE$.catsKernelStdOrderForUnit();
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple22(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19, final BoundedSemilattice A20, final BoundedSemilattice A21) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static Order catsKernelStdOrderForTuple22(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20, final Order A21) {
      return package$.MODULE$.catsKernelStdOrderForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple22(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19, final CommutativeGroup A20, final CommutativeGroup A21) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple21(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19, final BoundedSemilattice A20) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static Order catsKernelStdOrderForTuple21(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20) {
      return package$.MODULE$.catsKernelStdOrderForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple21(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19, final CommutativeGroup A20) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple20(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18, final BoundedSemilattice A19) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static Order catsKernelStdOrderForTuple20(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19) {
      return package$.MODULE$.catsKernelStdOrderForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple20(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18, final CommutativeGroup A19) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple19(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17, final BoundedSemilattice A18) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static Order catsKernelStdOrderForTuple19(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18) {
      return package$.MODULE$.catsKernelStdOrderForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple19(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17, final CommutativeGroup A18) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple18(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16, final BoundedSemilattice A17) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static Order catsKernelStdOrderForTuple18(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17) {
      return package$.MODULE$.catsKernelStdOrderForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple18(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16, final CommutativeGroup A17) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple17(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15, final BoundedSemilattice A16) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static Order catsKernelStdOrderForTuple17(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16) {
      return package$.MODULE$.catsKernelStdOrderForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple17(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15, final CommutativeGroup A16) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple16(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14, final BoundedSemilattice A15) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static Order catsKernelStdOrderForTuple16(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15) {
      return package$.MODULE$.catsKernelStdOrderForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple16(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14, final CommutativeGroup A15) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple15(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13, final BoundedSemilattice A14) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static Order catsKernelStdOrderForTuple15(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14) {
      return package$.MODULE$.catsKernelStdOrderForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple15(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13, final CommutativeGroup A14) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple14(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12, final BoundedSemilattice A13) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static Order catsKernelStdOrderForTuple14(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13) {
      return package$.MODULE$.catsKernelStdOrderForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple14(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12, final CommutativeGroup A13) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple13(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11, final BoundedSemilattice A12) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static Order catsKernelStdOrderForTuple13(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12) {
      return package$.MODULE$.catsKernelStdOrderForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple13(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11, final CommutativeGroup A12) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple12(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10, final BoundedSemilattice A11) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static Order catsKernelStdOrderForTuple12(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11) {
      return package$.MODULE$.catsKernelStdOrderForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple12(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10, final CommutativeGroup A11) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple11(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9, final BoundedSemilattice A10) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static Order catsKernelStdOrderForTuple11(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10) {
      return package$.MODULE$.catsKernelStdOrderForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple11(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9, final CommutativeGroup A10) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple10(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8, final BoundedSemilattice A9) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static Order catsKernelStdOrderForTuple10(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9) {
      return package$.MODULE$.catsKernelStdOrderForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple10(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8, final CommutativeGroup A9) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple9(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7, final BoundedSemilattice A8) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static Order catsKernelStdOrderForTuple9(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8) {
      return package$.MODULE$.catsKernelStdOrderForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple9(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7, final CommutativeGroup A8) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple8(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6, final BoundedSemilattice A7) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static Order catsKernelStdOrderForTuple8(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7) {
      return package$.MODULE$.catsKernelStdOrderForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple8(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6, final CommutativeGroup A7) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple7(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5, final BoundedSemilattice A6) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static Order catsKernelStdOrderForTuple7(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6) {
      return package$.MODULE$.catsKernelStdOrderForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple7(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5, final CommutativeGroup A6) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple6(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4, final BoundedSemilattice A5) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static Order catsKernelStdOrderForTuple6(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5) {
      return package$.MODULE$.catsKernelStdOrderForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple6(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4, final CommutativeGroup A5) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple5(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3, final BoundedSemilattice A4) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple5(A0, A1, A2, A3, A4);
   }

   public static Order catsKernelStdOrderForTuple5(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4) {
      return package$.MODULE$.catsKernelStdOrderForTuple5(A0, A1, A2, A3, A4);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple5(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3, final CommutativeGroup A4) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple5(A0, A1, A2, A3, A4);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple4(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2, final BoundedSemilattice A3) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple4(A0, A1, A2, A3);
   }

   public static Order catsKernelStdOrderForTuple4(final Order A0, final Order A1, final Order A2, final Order A3) {
      return package$.MODULE$.catsKernelStdOrderForTuple4(A0, A1, A2, A3);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple4(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2, final CommutativeGroup A3) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple4(A0, A1, A2, A3);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple3(final BoundedSemilattice A0, final BoundedSemilattice A1, final BoundedSemilattice A2) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple3(A0, A1, A2);
   }

   public static Order catsKernelStdOrderForTuple3(final Order A0, final Order A1, final Order A2) {
      return package$.MODULE$.catsKernelStdOrderForTuple3(A0, A1, A2);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple3(final CommutativeGroup A0, final CommutativeGroup A1, final CommutativeGroup A2) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple3(A0, A1, A2);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple2(final BoundedSemilattice A0, final BoundedSemilattice A1) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple2(A0, A1);
   }

   public static Order catsKernelStdOrderForTuple2(final Order A0, final Order A1) {
      return package$.MODULE$.catsKernelStdOrderForTuple2(A0, A1);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple2(final CommutativeGroup A0, final CommutativeGroup A1) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple2(A0, A1);
   }

   public static BoundedSemilattice catsKernelStdBoundedSemilatticeForTuple1(final BoundedSemilattice A0) {
      return package$.MODULE$.catsKernelStdBoundedSemilatticeForTuple1(A0);
   }

   public static Order catsKernelStdOrderForTuple1(final Order A0) {
      return package$.MODULE$.catsKernelStdOrderForTuple1(A0);
   }

   public static CommutativeGroup catsKernelStdCommutativeGroupForTuple1(final CommutativeGroup A0) {
      return package$.MODULE$.catsKernelStdCommutativeGroupForTuple1(A0);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple22(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19, final PartialOrder A20, final PartialOrder A21) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static Hash catsKernelStdHashForTuple22(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19, final Hash A20, final Hash A21) {
      return package$.MODULE$.catsKernelStdHashForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static Group catsKernelStdGroupForTuple22(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20, final Group A21) {
      return package$.MODULE$.catsKernelStdGroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple22(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19, final CommutativeMonoid A20, final CommutativeMonoid A21) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple22(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20, final Semilattice A21) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple21(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19, final PartialOrder A20) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static Hash catsKernelStdHashForTuple21(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19, final Hash A20) {
      return package$.MODULE$.catsKernelStdHashForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static Group catsKernelStdGroupForTuple21(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20) {
      return package$.MODULE$.catsKernelStdGroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple21(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19, final CommutativeMonoid A20) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple21(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple20(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static Hash catsKernelStdHashForTuple20(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19) {
      return package$.MODULE$.catsKernelStdHashForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static Group catsKernelStdGroupForTuple20(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19) {
      return package$.MODULE$.catsKernelStdGroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple20(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18, final CommutativeMonoid A19) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple20(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple19(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static Hash catsKernelStdHashForTuple19(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18) {
      return package$.MODULE$.catsKernelStdHashForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static Group catsKernelStdGroupForTuple19(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18) {
      return package$.MODULE$.catsKernelStdGroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple19(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17, final CommutativeMonoid A18) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple19(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple18(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static Hash catsKernelStdHashForTuple18(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17) {
      return package$.MODULE$.catsKernelStdHashForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static Group catsKernelStdGroupForTuple18(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17) {
      return package$.MODULE$.catsKernelStdGroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple18(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16, final CommutativeMonoid A17) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple18(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple17(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static Hash catsKernelStdHashForTuple17(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16) {
      return package$.MODULE$.catsKernelStdHashForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static Group catsKernelStdGroupForTuple17(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16) {
      return package$.MODULE$.catsKernelStdGroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple17(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15, final CommutativeMonoid A16) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple17(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple16(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static Hash catsKernelStdHashForTuple16(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15) {
      return package$.MODULE$.catsKernelStdHashForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static Group catsKernelStdGroupForTuple16(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15) {
      return package$.MODULE$.catsKernelStdGroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple16(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14, final CommutativeMonoid A15) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple16(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple15(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static Hash catsKernelStdHashForTuple15(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14) {
      return package$.MODULE$.catsKernelStdHashForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static Group catsKernelStdGroupForTuple15(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14) {
      return package$.MODULE$.catsKernelStdGroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple15(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13, final CommutativeMonoid A14) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple15(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple14(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static Hash catsKernelStdHashForTuple14(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13) {
      return package$.MODULE$.catsKernelStdHashForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static Group catsKernelStdGroupForTuple14(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13) {
      return package$.MODULE$.catsKernelStdGroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple14(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12, final CommutativeMonoid A13) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple14(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple13(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static Hash catsKernelStdHashForTuple13(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12) {
      return package$.MODULE$.catsKernelStdHashForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static Group catsKernelStdGroupForTuple13(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12) {
      return package$.MODULE$.catsKernelStdGroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple13(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11, final CommutativeMonoid A12) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple13(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple12(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static Hash catsKernelStdHashForTuple12(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11) {
      return package$.MODULE$.catsKernelStdHashForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static Group catsKernelStdGroupForTuple12(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11) {
      return package$.MODULE$.catsKernelStdGroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple12(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10, final CommutativeMonoid A11) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple12(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple11(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static Hash catsKernelStdHashForTuple11(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10) {
      return package$.MODULE$.catsKernelStdHashForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static Group catsKernelStdGroupForTuple11(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10) {
      return package$.MODULE$.catsKernelStdGroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple11(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9, final CommutativeMonoid A10) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple11(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple10(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static Hash catsKernelStdHashForTuple10(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9) {
      return package$.MODULE$.catsKernelStdHashForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static Group catsKernelStdGroupForTuple10(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9) {
      return package$.MODULE$.catsKernelStdGroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple10(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8, final CommutativeMonoid A9) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple10(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple9(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static Hash catsKernelStdHashForTuple9(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8) {
      return package$.MODULE$.catsKernelStdHashForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static Group catsKernelStdGroupForTuple9(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8) {
      return package$.MODULE$.catsKernelStdGroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple9(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7, final CommutativeMonoid A8) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple9(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple8(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static Hash catsKernelStdHashForTuple8(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7) {
      return package$.MODULE$.catsKernelStdHashForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static Group catsKernelStdGroupForTuple8(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7) {
      return package$.MODULE$.catsKernelStdGroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple8(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6, final CommutativeMonoid A7) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple8(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple7(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static Hash catsKernelStdHashForTuple7(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6) {
      return package$.MODULE$.catsKernelStdHashForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static Group catsKernelStdGroupForTuple7(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6) {
      return package$.MODULE$.catsKernelStdGroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple7(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5, final CommutativeMonoid A6) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple7(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple6(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static Hash catsKernelStdHashForTuple6(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5) {
      return package$.MODULE$.catsKernelStdHashForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static Group catsKernelStdGroupForTuple6(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5) {
      return package$.MODULE$.catsKernelStdGroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple6(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4, final CommutativeMonoid A5) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple6(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple5(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple5(A0, A1, A2, A3, A4);
   }

   public static Hash catsKernelStdHashForTuple5(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4) {
      return package$.MODULE$.catsKernelStdHashForTuple5(A0, A1, A2, A3, A4);
   }

   public static Group catsKernelStdGroupForTuple5(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4) {
      return package$.MODULE$.catsKernelStdGroupForTuple5(A0, A1, A2, A3, A4);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple5(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3, final CommutativeMonoid A4) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple5(A0, A1, A2, A3, A4);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple5(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple5(A0, A1, A2, A3, A4);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple4(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple4(A0, A1, A2, A3);
   }

   public static Hash catsKernelStdHashForTuple4(final Hash A0, final Hash A1, final Hash A2, final Hash A3) {
      return package$.MODULE$.catsKernelStdHashForTuple4(A0, A1, A2, A3);
   }

   public static Group catsKernelStdGroupForTuple4(final Group A0, final Group A1, final Group A2, final Group A3) {
      return package$.MODULE$.catsKernelStdGroupForTuple4(A0, A1, A2, A3);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple4(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2, final CommutativeMonoid A3) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple4(A0, A1, A2, A3);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple4(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple4(A0, A1, A2, A3);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple3(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple3(A0, A1, A2);
   }

   public static Hash catsKernelStdHashForTuple3(final Hash A0, final Hash A1, final Hash A2) {
      return package$.MODULE$.catsKernelStdHashForTuple3(A0, A1, A2);
   }

   public static Group catsKernelStdGroupForTuple3(final Group A0, final Group A1, final Group A2) {
      return package$.MODULE$.catsKernelStdGroupForTuple3(A0, A1, A2);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple3(final CommutativeMonoid A0, final CommutativeMonoid A1, final CommutativeMonoid A2) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple3(A0, A1, A2);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple3(final Semilattice A0, final Semilattice A1, final Semilattice A2) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple3(A0, A1, A2);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple2(final PartialOrder A0, final PartialOrder A1) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple2(A0, A1);
   }

   public static Hash catsKernelStdHashForTuple2(final Hash A0, final Hash A1) {
      return package$.MODULE$.catsKernelStdHashForTuple2(A0, A1);
   }

   public static Group catsKernelStdGroupForTuple2(final Group A0, final Group A1) {
      return package$.MODULE$.catsKernelStdGroupForTuple2(A0, A1);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple2(final CommutativeMonoid A0, final CommutativeMonoid A1) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple2(A0, A1);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple2(final Semilattice A0, final Semilattice A1) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple2(A0, A1);
   }

   public static PartialOrder catsKernelStdPartialOrderForTuple1(final PartialOrder A0) {
      return package$.MODULE$.catsKernelStdPartialOrderForTuple1(A0);
   }

   public static Hash catsKernelStdHashForTuple1(final Hash A0) {
      return package$.MODULE$.catsKernelStdHashForTuple1(A0);
   }

   public static Group catsKernelStdGroupForTuple1(final Group A0) {
      return package$.MODULE$.catsKernelStdGroupForTuple1(A0);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForTuple1(final CommutativeMonoid A0) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForTuple1(A0);
   }

   public static Semilattice catsKernelStdSemilatticeForTuple1(final Semilattice A0) {
      return package$.MODULE$.catsKernelStdSemilatticeForTuple1(A0);
   }

   public static Monoid catsKernelStdMonoidForTuple22(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19, final Monoid A20, final Monoid A21) {
      return package$.MODULE$.catsKernelStdMonoidForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple22(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19, final CommutativeSemigroup A20, final CommutativeSemigroup A21) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static Band catsKernelStdBandForTuple22(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19, final Band A20, final Band A21) {
      return package$.MODULE$.catsKernelStdBandForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static Monoid catsKernelStdMonoidForTuple21(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19, final Monoid A20) {
      return package$.MODULE$.catsKernelStdMonoidForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple21(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19, final CommutativeSemigroup A20) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static Band catsKernelStdBandForTuple21(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19, final Band A20) {
      return package$.MODULE$.catsKernelStdBandForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static Monoid catsKernelStdMonoidForTuple20(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18, final Monoid A19) {
      return package$.MODULE$.catsKernelStdMonoidForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple20(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18, final CommutativeSemigroup A19) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static Band catsKernelStdBandForTuple20(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18, final Band A19) {
      return package$.MODULE$.catsKernelStdBandForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static Monoid catsKernelStdMonoidForTuple19(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17, final Monoid A18) {
      return package$.MODULE$.catsKernelStdMonoidForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple19(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17, final CommutativeSemigroup A18) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static Band catsKernelStdBandForTuple19(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17, final Band A18) {
      return package$.MODULE$.catsKernelStdBandForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static Monoid catsKernelStdMonoidForTuple18(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16, final Monoid A17) {
      return package$.MODULE$.catsKernelStdMonoidForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple18(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16, final CommutativeSemigroup A17) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static Band catsKernelStdBandForTuple18(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16, final Band A17) {
      return package$.MODULE$.catsKernelStdBandForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static Monoid catsKernelStdMonoidForTuple17(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15, final Monoid A16) {
      return package$.MODULE$.catsKernelStdMonoidForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple17(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15, final CommutativeSemigroup A16) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static Band catsKernelStdBandForTuple17(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15, final Band A16) {
      return package$.MODULE$.catsKernelStdBandForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static Monoid catsKernelStdMonoidForTuple16(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14, final Monoid A15) {
      return package$.MODULE$.catsKernelStdMonoidForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple16(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14, final CommutativeSemigroup A15) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static Band catsKernelStdBandForTuple16(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14, final Band A15) {
      return package$.MODULE$.catsKernelStdBandForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static Monoid catsKernelStdMonoidForTuple15(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13, final Monoid A14) {
      return package$.MODULE$.catsKernelStdMonoidForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple15(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13, final CommutativeSemigroup A14) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static Band catsKernelStdBandForTuple15(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13, final Band A14) {
      return package$.MODULE$.catsKernelStdBandForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static Monoid catsKernelStdMonoidForTuple14(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12, final Monoid A13) {
      return package$.MODULE$.catsKernelStdMonoidForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple14(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12, final CommutativeSemigroup A13) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static Band catsKernelStdBandForTuple14(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12, final Band A13) {
      return package$.MODULE$.catsKernelStdBandForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static Monoid catsKernelStdMonoidForTuple13(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11, final Monoid A12) {
      return package$.MODULE$.catsKernelStdMonoidForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple13(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11, final CommutativeSemigroup A12) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static Band catsKernelStdBandForTuple13(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11, final Band A12) {
      return package$.MODULE$.catsKernelStdBandForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static Monoid catsKernelStdMonoidForTuple12(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10, final Monoid A11) {
      return package$.MODULE$.catsKernelStdMonoidForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple12(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10, final CommutativeSemigroup A11) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static Band catsKernelStdBandForTuple12(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10, final Band A11) {
      return package$.MODULE$.catsKernelStdBandForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static Monoid catsKernelStdMonoidForTuple11(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9, final Monoid A10) {
      return package$.MODULE$.catsKernelStdMonoidForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple11(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9, final CommutativeSemigroup A10) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static Band catsKernelStdBandForTuple11(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9, final Band A10) {
      return package$.MODULE$.catsKernelStdBandForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static Monoid catsKernelStdMonoidForTuple10(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8, final Monoid A9) {
      return package$.MODULE$.catsKernelStdMonoidForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple10(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8, final CommutativeSemigroup A9) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static Band catsKernelStdBandForTuple10(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8, final Band A9) {
      return package$.MODULE$.catsKernelStdBandForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static Monoid catsKernelStdMonoidForTuple9(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7, final Monoid A8) {
      return package$.MODULE$.catsKernelStdMonoidForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple9(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7, final CommutativeSemigroup A8) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static Band catsKernelStdBandForTuple9(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7, final Band A8) {
      return package$.MODULE$.catsKernelStdBandForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static Monoid catsKernelStdMonoidForTuple8(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6, final Monoid A7) {
      return package$.MODULE$.catsKernelStdMonoidForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple8(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6, final CommutativeSemigroup A7) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static Band catsKernelStdBandForTuple8(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6, final Band A7) {
      return package$.MODULE$.catsKernelStdBandForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static Monoid catsKernelStdMonoidForTuple7(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5, final Monoid A6) {
      return package$.MODULE$.catsKernelStdMonoidForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple7(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5, final CommutativeSemigroup A6) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static Band catsKernelStdBandForTuple7(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5, final Band A6) {
      return package$.MODULE$.catsKernelStdBandForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static Monoid catsKernelStdMonoidForTuple6(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4, final Monoid A5) {
      return package$.MODULE$.catsKernelStdMonoidForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple6(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4, final CommutativeSemigroup A5) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static Band catsKernelStdBandForTuple6(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4, final Band A5) {
      return package$.MODULE$.catsKernelStdBandForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static Monoid catsKernelStdMonoidForTuple5(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3, final Monoid A4) {
      return package$.MODULE$.catsKernelStdMonoidForTuple5(A0, A1, A2, A3, A4);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple5(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3, final CommutativeSemigroup A4) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple5(A0, A1, A2, A3, A4);
   }

   public static Band catsKernelStdBandForTuple5(final Band A0, final Band A1, final Band A2, final Band A3, final Band A4) {
      return package$.MODULE$.catsKernelStdBandForTuple5(A0, A1, A2, A3, A4);
   }

   public static Monoid catsKernelStdMonoidForTuple4(final Monoid A0, final Monoid A1, final Monoid A2, final Monoid A3) {
      return package$.MODULE$.catsKernelStdMonoidForTuple4(A0, A1, A2, A3);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple4(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2, final CommutativeSemigroup A3) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple4(A0, A1, A2, A3);
   }

   public static Band catsKernelStdBandForTuple4(final Band A0, final Band A1, final Band A2, final Band A3) {
      return package$.MODULE$.catsKernelStdBandForTuple4(A0, A1, A2, A3);
   }

   public static Monoid catsKernelStdMonoidForTuple3(final Monoid A0, final Monoid A1, final Monoid A2) {
      return package$.MODULE$.catsKernelStdMonoidForTuple3(A0, A1, A2);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple3(final CommutativeSemigroup A0, final CommutativeSemigroup A1, final CommutativeSemigroup A2) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple3(A0, A1, A2);
   }

   public static Band catsKernelStdBandForTuple3(final Band A0, final Band A1, final Band A2) {
      return package$.MODULE$.catsKernelStdBandForTuple3(A0, A1, A2);
   }

   public static Monoid catsKernelStdMonoidForTuple2(final Monoid A0, final Monoid A1) {
      return package$.MODULE$.catsKernelStdMonoidForTuple2(A0, A1);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple2(final CommutativeSemigroup A0, final CommutativeSemigroup A1) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple2(A0, A1);
   }

   public static Band catsKernelStdBandForTuple2(final Band A0, final Band A1) {
      return package$.MODULE$.catsKernelStdBandForTuple2(A0, A1);
   }

   public static Monoid catsKernelStdMonoidForTuple1(final Monoid A0) {
      return package$.MODULE$.catsKernelStdMonoidForTuple1(A0);
   }

   public static CommutativeSemigroup catsKernelStdCommutativeSemigroupForTuple1(final CommutativeSemigroup A0) {
      return package$.MODULE$.catsKernelStdCommutativeSemigroupForTuple1(A0);
   }

   public static Band catsKernelStdBandForTuple1(final Band A0) {
      return package$.MODULE$.catsKernelStdBandForTuple1(A0);
   }

   public static Eq catsKernelStdEqForTuple22(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19, final Eq A20, final Eq A21) {
      return package$.MODULE$.catsKernelStdEqForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static Semigroup catsKernelStdSemigroupForTuple22(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19, final Semigroup A20, final Semigroup A21) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public static Eq catsKernelStdEqForTuple21(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19, final Eq A20) {
      return package$.MODULE$.catsKernelStdEqForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static Semigroup catsKernelStdSemigroupForTuple21(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19, final Semigroup A20) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public static Eq catsKernelStdEqForTuple20(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19) {
      return package$.MODULE$.catsKernelStdEqForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static Semigroup catsKernelStdSemigroupForTuple20(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18, final Semigroup A19) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public static Eq catsKernelStdEqForTuple19(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18) {
      return package$.MODULE$.catsKernelStdEqForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static Semigroup catsKernelStdSemigroupForTuple19(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17, final Semigroup A18) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public static Eq catsKernelStdEqForTuple18(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17) {
      return package$.MODULE$.catsKernelStdEqForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static Semigroup catsKernelStdSemigroupForTuple18(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16, final Semigroup A17) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public static Eq catsKernelStdEqForTuple17(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16) {
      return package$.MODULE$.catsKernelStdEqForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static Semigroup catsKernelStdSemigroupForTuple17(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15, final Semigroup A16) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public static Eq catsKernelStdEqForTuple16(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15) {
      return package$.MODULE$.catsKernelStdEqForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static Semigroup catsKernelStdSemigroupForTuple16(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14, final Semigroup A15) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public static Eq catsKernelStdEqForTuple15(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14) {
      return package$.MODULE$.catsKernelStdEqForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static Semigroup catsKernelStdSemigroupForTuple15(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13, final Semigroup A14) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public static Eq catsKernelStdEqForTuple14(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13) {
      return package$.MODULE$.catsKernelStdEqForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static Semigroup catsKernelStdSemigroupForTuple14(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12, final Semigroup A13) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public static Eq catsKernelStdEqForTuple13(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12) {
      return package$.MODULE$.catsKernelStdEqForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static Semigroup catsKernelStdSemigroupForTuple13(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11, final Semigroup A12) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public static Eq catsKernelStdEqForTuple12(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11) {
      return package$.MODULE$.catsKernelStdEqForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static Semigroup catsKernelStdSemigroupForTuple12(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10, final Semigroup A11) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public static Eq catsKernelStdEqForTuple11(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10) {
      return package$.MODULE$.catsKernelStdEqForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static Semigroup catsKernelStdSemigroupForTuple11(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9, final Semigroup A10) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public static Eq catsKernelStdEqForTuple10(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9) {
      return package$.MODULE$.catsKernelStdEqForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static Semigroup catsKernelStdSemigroupForTuple10(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8, final Semigroup A9) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public static Eq catsKernelStdEqForTuple9(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8) {
      return package$.MODULE$.catsKernelStdEqForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static Semigroup catsKernelStdSemigroupForTuple9(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7, final Semigroup A8) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public static Eq catsKernelStdEqForTuple8(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7) {
      return package$.MODULE$.catsKernelStdEqForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static Semigroup catsKernelStdSemigroupForTuple8(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6, final Semigroup A7) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public static Eq catsKernelStdEqForTuple7(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6) {
      return package$.MODULE$.catsKernelStdEqForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static Semigroup catsKernelStdSemigroupForTuple7(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5, final Semigroup A6) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   public static Eq catsKernelStdEqForTuple6(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5) {
      return package$.MODULE$.catsKernelStdEqForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static Semigroup catsKernelStdSemigroupForTuple6(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4, final Semigroup A5) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   public static Eq catsKernelStdEqForTuple5(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4) {
      return package$.MODULE$.catsKernelStdEqForTuple5(A0, A1, A2, A3, A4);
   }

   public static Semigroup catsKernelStdSemigroupForTuple5(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3, final Semigroup A4) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple5(A0, A1, A2, A3, A4);
   }

   public static Eq catsKernelStdEqForTuple4(final Eq A0, final Eq A1, final Eq A2, final Eq A3) {
      return package$.MODULE$.catsKernelStdEqForTuple4(A0, A1, A2, A3);
   }

   public static Semigroup catsKernelStdSemigroupForTuple4(final Semigroup A0, final Semigroup A1, final Semigroup A2, final Semigroup A3) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple4(A0, A1, A2, A3);
   }

   public static Eq catsKernelStdEqForTuple3(final Eq A0, final Eq A1, final Eq A2) {
      return package$.MODULE$.catsKernelStdEqForTuple3(A0, A1, A2);
   }

   public static Semigroup catsKernelStdSemigroupForTuple3(final Semigroup A0, final Semigroup A1, final Semigroup A2) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple3(A0, A1, A2);
   }

   public static Eq catsKernelStdEqForTuple2(final Eq A0, final Eq A1) {
      return package$.MODULE$.catsKernelStdEqForTuple2(A0, A1);
   }

   public static Semigroup catsKernelStdSemigroupForTuple2(final Semigroup A0, final Semigroup A1) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple2(A0, A1);
   }

   public static Eq catsKernelStdEqForTuple1(final Eq A0) {
      return package$.MODULE$.catsKernelStdEqForTuple1(A0);
   }

   public static Semigroup catsKernelStdSemigroupForTuple1(final Semigroup A0) {
      return package$.MODULE$.catsKernelStdSemigroupForTuple1(A0);
   }

   public static Order catsKernelStdOrderForSymbol() {
      return package$.MODULE$.catsKernelStdOrderForSymbol();
   }

   public static Monoid catsKernelStdMonoidForString() {
      return package$.MODULE$.catsKernelStdMonoidForString();
   }

   public static Order catsKernelStdOrderForString() {
      return package$.MODULE$.catsKernelStdOrderForString();
   }

   /** @deprecated */
   public static Monoid catsKernelStdMonoidForStream() {
      return package$.MODULE$.catsKernelStdMonoidForStream();
   }

   /** @deprecated */
   public static Order catsKernelStdOrderForStream(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForStream(evidence$1);
   }

   /** @deprecated */
   public static Hash catsKernelStdHashForStream(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForStream(evidence$3);
   }

   /** @deprecated */
   public static PartialOrder catsKernelStdPartialOrderForStream(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForStream(evidence$2);
   }

   /** @deprecated */
   public static Eq catsKernelStdEqForStream(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForStream(evidence$4);
   }

   public static CommutativeGroup catsKernelStdGroupForShort() {
      return package$.MODULE$.catsKernelStdGroupForShort();
   }

   public static Order catsKernelStdOrderForShort() {
      return package$.MODULE$.catsKernelStdOrderForShort();
   }

   public static Monoid catsKernelStdMonoidForSeq() {
      return package$.MODULE$.catsKernelStdMonoidForSeq();
   }

   public static Order catsKernelStdOrderForSeq(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForSeq(evidence$1);
   }

   public static Hash catsKernelStdHashForSeq(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForSeq(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForSeq(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForSeq(evidence$2);
   }

   public static Eq catsKernelStdEqForSeq(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForSeq(evidence$4);
   }

   public static Hash catsKernelStdHashForSet() {
      return package$.MODULE$.catsKernelStdHashForSet();
   }

   public static BoundedSemilattice catsKernelStdSemilatticeForSet() {
      return package$.MODULE$.catsKernelStdSemilatticeForSet();
   }

   public static PartialOrder catsKernelStdPartialOrderForSet() {
      return package$.MODULE$.catsKernelStdPartialOrderForSet();
   }

   public static Monoid catsKernelStdMonoidForQueue() {
      return package$.MODULE$.catsKernelStdMonoidForQueue();
   }

   public static Order catsKernelStdOrderForQueue(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForQueue(evidence$1);
   }

   public static Hash catsKernelStdHashForQueue(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForQueue(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForQueue(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForQueue(evidence$2);
   }

   public static Eq catsKernelStdEqForQueue(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForQueue(evidence$4);
   }

   public static PartialOrdering catsKernelPartialOrderingForPartialOrder(final PartialOrder ev) {
      return package$.MODULE$.catsKernelPartialOrderingForPartialOrder(ev);
   }

   public static Ordering catsKernelOrderingForOrder(final Order ev) {
      return package$.MODULE$.catsKernelOrderingForOrder(ev);
   }

   public static Monoid catsKernelStdMonoidForOption(final Semigroup evidence$3) {
      return package$.MODULE$.catsKernelStdMonoidForOption(evidence$3);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForOption(final CommutativeSemigroup evidence$2) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForOption(evidence$2);
   }

   public static Order catsKernelStdOrderForOption(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForOption(evidence$1);
   }

   public static PartialOrder catsKernelStdPartialOrderForOption(final PartialOrder evidence$4) {
      return package$.MODULE$.catsKernelStdPartialOrderForOption(evidence$4);
   }

   public static Hash catsKernelStdHashForOption(final Hash evidence$5) {
      return package$.MODULE$.catsKernelStdHashForOption(evidence$5);
   }

   public static Eq catsKernelStdEqForOption(final Eq evidence$6) {
      return package$.MODULE$.catsKernelStdEqForOption(evidence$6);
   }

   public static CommutativeMonoid catsKernelStdCommutativeMonoidForMap(final CommutativeSemigroup evidence$3) {
      return package$.MODULE$.catsKernelStdCommutativeMonoidForMap(evidence$3);
   }

   public static Hash catsKernelStdHashForMap(final Hash evidence$1, final Hash evidence$2) {
      return package$.MODULE$.catsKernelStdHashForMap(evidence$1, evidence$2);
   }

   public static Monoid catsKernelStdMonoidForMap(final Semigroup evidence$5) {
      return package$.MODULE$.catsKernelStdMonoidForMap(evidence$5);
   }

   public static Eq catsKernelStdEqForMap(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForMap(evidence$4);
   }

   public static CommutativeGroup catsKernelStdGroupForLong() {
      return package$.MODULE$.catsKernelStdGroupForLong();
   }

   public static Order catsKernelStdOrderForLong() {
      return package$.MODULE$.catsKernelStdOrderForLong();
   }

   public static Monoid catsKernelStdMonoidForList() {
      return package$.MODULE$.catsKernelStdMonoidForList();
   }

   public static Order catsKernelStdOrderForList(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForList(evidence$1);
   }

   public static Hash catsKernelStdHashForList(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForList(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForList(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForList(evidence$2);
   }

   public static Eq catsKernelStdEqForList(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForList(evidence$4);
   }

   public static Monoid catsKernelStdMonoidForLazyList() {
      return package$.MODULE$.catsKernelStdMonoidForLazyList();
   }

   public static Order catsKernelStdOrderForLazyList(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForLazyList(evidence$1);
   }

   public static Hash catsKernelStdHashForLazyList(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForLazyList(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForLazyList(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForLazyList(evidence$2);
   }

   public static Eq catsKernelStdEqForLazyList(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForLazyList(evidence$4);
   }

   public static CommutativeGroup catsKernelStdGroupForInt() {
      return package$.MODULE$.catsKernelStdGroupForInt();
   }

   public static Order catsKernelStdOrderForInt() {
      return package$.MODULE$.catsKernelStdOrderForInt();
   }

   public static Hashing catsKernelHashToHashing(final Hash ev) {
      return package$.MODULE$.catsKernelHashToHashing(ev);
   }

   public static CommutativeGroup catsKernelCommutativeGroupForFunction1(final CommutativeGroup G) {
      return package$.MODULE$.catsKernelCommutativeGroupForFunction1(G);
   }

   public static CommutativeGroup catsKernelCommutativeGroupForFunction0(final CommutativeGroup G) {
      return package$.MODULE$.catsKernelCommutativeGroupForFunction0(G);
   }

   public static Order catsKernelOrderForFunction0(final Order ev) {
      return package$.MODULE$.catsKernelOrderForFunction0(ev);
   }

   public static BoundedSemilattice catsKernelBoundedSemilatticeForFunction1(final BoundedSemilattice G) {
      return package$.MODULE$.catsKernelBoundedSemilatticeForFunction1(G);
   }

   public static BoundedSemilattice catsKernelBoundedSemilatticeForFunction0(final BoundedSemilattice G) {
      return package$.MODULE$.catsKernelBoundedSemilatticeForFunction0(G);
   }

   public static Group catsKernelGroupForFunction1(final Group G) {
      return package$.MODULE$.catsKernelGroupForFunction1(G);
   }

   public static Group catsKernelGroupForFunction0(final Group G) {
      return package$.MODULE$.catsKernelGroupForFunction0(G);
   }

   public static PartialOrder catsKernelPartialOrderForFunction0(final PartialOrder ev) {
      return package$.MODULE$.catsKernelPartialOrderForFunction0(ev);
   }

   public static Hash catsKernelHashForFunction0(final Hash ev) {
      return package$.MODULE$.catsKernelHashForFunction0(ev);
   }

   public static Semilattice catsKernelSemilatticeForFunction1(final Semilattice M) {
      return package$.MODULE$.catsKernelSemilatticeForFunction1(M);
   }

   public static Semilattice catsKernelSemilatticeForFunction0(final Semilattice M) {
      return package$.MODULE$.catsKernelSemilatticeForFunction0(M);
   }

   public static CommutativeMonoid catsKernelCommutativeMonoidForFunction1(final CommutativeMonoid M) {
      return package$.MODULE$.catsKernelCommutativeMonoidForFunction1(M);
   }

   public static CommutativeMonoid catsKernelCommutativeMonoidForFunction0(final CommutativeMonoid M) {
      return package$.MODULE$.catsKernelCommutativeMonoidForFunction0(M);
   }

   public static Eq catsKernelEqForFunction0(final Eq ev) {
      return package$.MODULE$.catsKernelEqForFunction0(ev);
   }

   public static Band catsKernelBandForFunction1(final Band S) {
      return package$.MODULE$.catsKernelBandForFunction1(S);
   }

   public static Band catsKernelBandForFunction0(final Band S) {
      return package$.MODULE$.catsKernelBandForFunction0(S);
   }

   public static Monoid catsKernelMonoidForFunction1(final Monoid M) {
      return package$.MODULE$.catsKernelMonoidForFunction1(M);
   }

   public static Monoid catsKernelMonoidForFunction0(final Monoid M) {
      return package$.MODULE$.catsKernelMonoidForFunction0(M);
   }

   public static CommutativeSemigroup catsKernelCommutativeSemigroupForFunction1(final CommutativeSemigroup S) {
      return package$.MODULE$.catsKernelCommutativeSemigroupForFunction1(S);
   }

   public static CommutativeSemigroup catsKernelCommutativeSemigroupForFunction0(final CommutativeSemigroup S) {
      return package$.MODULE$.catsKernelCommutativeSemigroupForFunction0(S);
   }

   public static Semigroup catsKernelSemigroupForFunction1(final Semigroup S) {
      return package$.MODULE$.catsKernelSemigroupForFunction1(S);
   }

   public static Semigroup catsKernelSemigroupForFunction0(final Semigroup S) {
      return package$.MODULE$.catsKernelSemigroupForFunction0(S);
   }

   public static CommutativeGroup catsKernelStdGroupForFloat() {
      return package$.MODULE$.catsKernelStdGroupForFloat();
   }

   public static Order catsKernelStdOrderForFloat() {
      return package$.MODULE$.catsKernelStdOrderForFloat();
   }

   public static CommutativeGroup catsKernelStdGroupForDuration() {
      return package$.MODULE$.catsKernelStdGroupForDuration();
   }

   public static Order catsKernelStdOrderForDuration() {
      return package$.MODULE$.catsKernelStdOrderForDuration();
   }

   public static Monoid catsDataMonoidForEither(final Monoid B) {
      return package$.MODULE$.catsDataMonoidForEither(B);
   }

   public static Order catsStdOrderForEither(final Order A, final Order B) {
      return package$.MODULE$.catsStdOrderForEither(A, B);
   }

   public static Hash catsStdHashForEither(final Hash A, final Hash B) {
      return package$.MODULE$.catsStdHashForEither(A, B);
   }

   public static PartialOrder catsStdPartialOrderForEither(final PartialOrder A, final PartialOrder B) {
      return package$.MODULE$.catsStdPartialOrderForEither(A, B);
   }

   public static Semigroup catsDataSemigroupForEither(final Semigroup B) {
      return package$.MODULE$.catsDataSemigroupForEither(B);
   }

   public static Eq catsStdEqForEither(final Eq A, final Eq B) {
      return package$.MODULE$.catsStdEqForEither(A, B);
   }

   public static Equiv catsKernelEquivForEq(final Eq ev) {
      return package$.MODULE$.catsKernelEquivForEq(ev);
   }

   public static CommutativeGroup catsKernelStdGroupForDouble() {
      return package$.MODULE$.catsKernelStdGroupForDouble();
   }

   public static Order catsKernelStdOrderForDouble() {
      return package$.MODULE$.catsKernelStdOrderForDouble();
   }

   public static CharOrder catsKernelStdOrderForChar() {
      return package$.MODULE$.catsKernelStdOrderForChar();
   }

   public static CommutativeGroup catsKernelStdGroupForByte() {
      return package$.MODULE$.catsKernelStdGroupForByte();
   }

   public static Order catsKernelStdOrderForByte() {
      return package$.MODULE$.catsKernelStdOrderForByte();
   }

   public static Order catsKernelStdOrderForBoolean() {
      return package$.MODULE$.catsKernelStdOrderForBoolean();
   }

   public static BoundedSemilattice catsKernelStdSemilatticeForBitSet() {
      return package$.MODULE$.catsKernelStdSemilatticeForBitSet();
   }

   public static PartialOrder catsKernelStdOrderForBitSet() {
      return package$.MODULE$.catsKernelStdOrderForBitSet();
   }

   public static CommutativeGroup catsKernelStdGroupForBigInt() {
      return package$.MODULE$.catsKernelStdGroupForBigInt();
   }

   public static Order catsKernelStdOrderForBigInt() {
      return package$.MODULE$.catsKernelStdOrderForBigInt();
   }

   public static CommutativeGroup catsKernelStdGroupForBigDecimal() {
      return package$.MODULE$.catsKernelStdGroupForBigDecimal();
   }

   public static Order catsKernelStdOrderForBigDecimal() {
      return package$.MODULE$.catsKernelStdOrderForBigDecimal();
   }

   public static Monoid catsKernelStdMonoidForArraySeq() {
      return package$.MODULE$.catsKernelStdMonoidForArraySeq();
   }

   public static Order catsKernelStdOrderForArraySeq(final Order evidence$1) {
      return package$.MODULE$.catsKernelStdOrderForArraySeq(evidence$1);
   }

   public static Hash catsKernelStdHashForArraySeq(final Hash evidence$3) {
      return package$.MODULE$.catsKernelStdHashForArraySeq(evidence$3);
   }

   public static PartialOrder catsKernelStdPartialOrderForArraySeq(final PartialOrder evidence$2) {
      return package$.MODULE$.catsKernelStdPartialOrderForArraySeq(evidence$2);
   }

   public static Eq catsKernelStdEqForArraySeq(final Eq evidence$4) {
      return package$.MODULE$.catsKernelStdEqForArraySeq(evidence$4);
   }
}
