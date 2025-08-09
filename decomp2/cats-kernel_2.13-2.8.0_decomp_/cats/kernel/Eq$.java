package cats.kernel;

import cats.kernel.instances.TupleEqInstances;
import cats.kernel.instances.TupleHashInstances;
import cats.kernel.instances.TupleOrderInstances;
import cats.kernel.instances.TuplePartialOrderInstances;
import cats.kernel.instances.bitSet.package$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Vector;
import scala.math.Equiv;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public final class Eq$ extends EqFunctions implements EqToEquivConversion, ScalaVersionSpecificOrderInstances, TupleOrderInstances, OrderInstances1, Serializable {
   public static final Eq$ MODULE$ = new Eq$();

   static {
      EqToEquivConversion.$init$(MODULE$);
      ScalaVersionSpecificEqInstances.$init$(MODULE$);
      ScalaVersionSpecificHashInstances.$init$(MODULE$);
      ScalaVersionSpecificPartialOrderInstances.$init$(MODULE$);
      ScalaVersionSpecificOrderInstances.$init$(MODULE$);
      TupleEqInstances.$init$(MODULE$);
      TupleHashInstances.$init$(MODULE$);
      TuplePartialOrderInstances.$init$(MODULE$);
      TupleOrderInstances.$init$(MODULE$);
      EqInstances0.$init$(MODULE$);
      EqInstances.$init$(MODULE$);
      HashInstances0.$init$(MODULE$);
      HashInstances.$init$(MODULE$);
      PartialOrderInstances0.$init$(MODULE$);
      PartialOrderInstances1.$init$(MODULE$);
      PartialOrderInstances.$init$(MODULE$);
      OrderInstances0.$init$(MODULE$);
      OrderInstances1.$init$(MODULE$);
   }

   public Order catsKernelOrderForSortedMap(final Order evidence$10) {
      return OrderInstances1.catsKernelOrderForSortedMap$(this, evidence$10);
   }

   public Order catsKernelOrderForSeq(final Order evidence$9) {
      return OrderInstances0.catsKernelOrderForSeq$(this, evidence$9);
   }

   public PartialOrder catsKernelPartialOrderForOption(final PartialOrder evidence$11) {
      return PartialOrderInstances.catsKernelPartialOrderForOption$(this, evidence$11);
   }

   public PartialOrder catsKernelPartialOrderForList(final PartialOrder evidence$12) {
      return PartialOrderInstances.catsKernelPartialOrderForList$(this, evidence$12);
   }

   public PartialOrder catsKernelPartialOrderForVector(final PartialOrder evidence$13) {
      return PartialOrderInstances.catsKernelPartialOrderForVector$(this, evidence$13);
   }

   public PartialOrder catsKernelPartialOrderForQueue(final PartialOrder evidence$14) {
      return PartialOrderInstances.catsKernelPartialOrderForQueue$(this, evidence$14);
   }

   public PartialOrder catsKernelPartialOrderForFunction0(final PartialOrder evidence$15) {
      return PartialOrderInstances.catsKernelPartialOrderForFunction0$(this, evidence$15);
   }

   public PartialOrder catsKernelPartialOrderForSortedMap(final PartialOrder evidence$17) {
      return PartialOrderInstances1.catsKernelPartialOrderForSortedMap$(this, evidence$17);
   }

   public PartialOrder catsKernelPartialOrderForSeq(final PartialOrder evidence$16) {
      return PartialOrderInstances0.catsKernelPartialOrderForSeq$(this, evidence$16);
   }

   public Hash catsKernelHashForSet() {
      return HashInstances.catsKernelHashForSet$(this);
   }

   public Hash catsKernelHashForOption(final Hash evidence$18) {
      return HashInstances.catsKernelHashForOption$(this, evidence$18);
   }

   public Hash catsKernelHashForList(final Hash evidence$19) {
      return HashInstances.catsKernelHashForList$(this, evidence$19);
   }

   public Hash catsKernelHashForVector(final Hash evidence$20) {
      return HashInstances.catsKernelHashForVector$(this, evidence$20);
   }

   public Hash catsKernelHashForQueue(final Hash evidence$21) {
      return HashInstances.catsKernelHashForQueue$(this, evidence$21);
   }

   public Hash catsKernelHashForSortedSet(final Hash evidence$22) {
      return HashInstances.catsKernelHashForSortedSet$(this, evidence$22);
   }

   public Hash catsKernelHashForFunction0(final Hash evidence$23) {
      return HashInstances.catsKernelHashForFunction0$(this, evidence$23);
   }

   public Hash catsKernelHashForMap(final Hash evidence$24, final Hash evidence$25) {
      return HashInstances.catsKernelHashForMap$(this, evidence$24, evidence$25);
   }

   public Hash catsKernelHashForSortedMap(final Hash evidence$26, final Hash evidence$27) {
      return HashInstances.catsKernelHashForSortedMap$(this, evidence$26, evidence$27);
   }

   public Hash catsKernelHashForEither(final Hash evidence$28, final Hash evidence$29) {
      return HashInstances.catsKernelHashForEither$(this, evidence$28, evidence$29);
   }

   public Hash catsKernelHashForSeq(final Hash evidence$30) {
      return HashInstances0.catsKernelHashForSeq$(this, evidence$30);
   }

   public Eq catsKernelEqForOption(final Eq evidence$31) {
      return EqInstances.catsKernelEqForOption$(this, evidence$31);
   }

   public Eq catsKernelEqForList(final Eq evidence$32) {
      return EqInstances.catsKernelEqForList$(this, evidence$32);
   }

   public Eq catsKernelEqForVector(final Eq evidence$33) {
      return EqInstances.catsKernelEqForVector$(this, evidence$33);
   }

   public Eq catsKernelEqForQueue(final Eq evidence$34) {
      return EqInstances.catsKernelEqForQueue$(this, evidence$34);
   }

   public Eq catsKernelEqForFunction0(final Eq evidence$35) {
      return EqInstances.catsKernelEqForFunction0$(this, evidence$35);
   }

   public Eq catsKernelEqForMap(final Eq evidence$36) {
      return EqInstances.catsKernelEqForMap$(this, evidence$36);
   }

   public Eq catsKernelEqForSortedMap(final Eq evidence$37) {
      return EqInstances.catsKernelEqForSortedMap$(this, evidence$37);
   }

   public Eq catsKernelEqForEither(final Eq evidence$38, final Eq evidence$39) {
      return EqInstances.catsKernelEqForEither$(this, evidence$38, evidence$39);
   }

   public Eq catsKernelEqForSeq(final Eq evidence$40) {
      return EqInstances0.catsKernelEqForSeq$(this, evidence$40);
   }

   public Order catsKernelOrderForTuple1(final Order A0) {
      return TupleOrderInstances.catsKernelOrderForTuple1$(this, A0);
   }

   public Order catsKernelOrderForTuple2(final Order A0, final Order A1) {
      return TupleOrderInstances.catsKernelOrderForTuple2$(this, A0, A1);
   }

   public Order catsKernelOrderForTuple3(final Order A0, final Order A1, final Order A2) {
      return TupleOrderInstances.catsKernelOrderForTuple3$(this, A0, A1, A2);
   }

   public Order catsKernelOrderForTuple4(final Order A0, final Order A1, final Order A2, final Order A3) {
      return TupleOrderInstances.catsKernelOrderForTuple4$(this, A0, A1, A2, A3);
   }

   public Order catsKernelOrderForTuple5(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4) {
      return TupleOrderInstances.catsKernelOrderForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Order catsKernelOrderForTuple6(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5) {
      return TupleOrderInstances.catsKernelOrderForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Order catsKernelOrderForTuple7(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6) {
      return TupleOrderInstances.catsKernelOrderForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Order catsKernelOrderForTuple8(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7) {
      return TupleOrderInstances.catsKernelOrderForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Order catsKernelOrderForTuple9(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8) {
      return TupleOrderInstances.catsKernelOrderForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Order catsKernelOrderForTuple10(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9) {
      return TupleOrderInstances.catsKernelOrderForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Order catsKernelOrderForTuple11(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10) {
      return TupleOrderInstances.catsKernelOrderForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Order catsKernelOrderForTuple12(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11) {
      return TupleOrderInstances.catsKernelOrderForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Order catsKernelOrderForTuple13(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12) {
      return TupleOrderInstances.catsKernelOrderForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Order catsKernelOrderForTuple14(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13) {
      return TupleOrderInstances.catsKernelOrderForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Order catsKernelOrderForTuple15(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14) {
      return TupleOrderInstances.catsKernelOrderForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Order catsKernelOrderForTuple16(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15) {
      return TupleOrderInstances.catsKernelOrderForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Order catsKernelOrderForTuple17(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16) {
      return TupleOrderInstances.catsKernelOrderForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Order catsKernelOrderForTuple18(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17) {
      return TupleOrderInstances.catsKernelOrderForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Order catsKernelOrderForTuple19(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18) {
      return TupleOrderInstances.catsKernelOrderForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Order catsKernelOrderForTuple20(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19) {
      return TupleOrderInstances.catsKernelOrderForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Order catsKernelOrderForTuple21(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20) {
      return TupleOrderInstances.catsKernelOrderForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Order catsKernelOrderForTuple22(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20, final Order A21) {
      return TupleOrderInstances.catsKernelOrderForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public PartialOrder catsKernelPartialOrderForTuple1(final PartialOrder A0) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple1$(this, A0);
   }

   public PartialOrder catsKernelPartialOrderForTuple2(final PartialOrder A0, final PartialOrder A1) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple2$(this, A0, A1);
   }

   public PartialOrder catsKernelPartialOrderForTuple3(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple3$(this, A0, A1, A2);
   }

   public PartialOrder catsKernelPartialOrderForTuple4(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple4$(this, A0, A1, A2, A3);
   }

   public PartialOrder catsKernelPartialOrderForTuple5(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public PartialOrder catsKernelPartialOrderForTuple6(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public PartialOrder catsKernelPartialOrderForTuple7(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public PartialOrder catsKernelPartialOrderForTuple8(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public PartialOrder catsKernelPartialOrderForTuple9(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public PartialOrder catsKernelPartialOrderForTuple10(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public PartialOrder catsKernelPartialOrderForTuple11(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public PartialOrder catsKernelPartialOrderForTuple12(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public PartialOrder catsKernelPartialOrderForTuple13(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public PartialOrder catsKernelPartialOrderForTuple14(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public PartialOrder catsKernelPartialOrderForTuple15(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public PartialOrder catsKernelPartialOrderForTuple16(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public PartialOrder catsKernelPartialOrderForTuple17(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public PartialOrder catsKernelPartialOrderForTuple18(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public PartialOrder catsKernelPartialOrderForTuple19(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public PartialOrder catsKernelPartialOrderForTuple20(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public PartialOrder catsKernelPartialOrderForTuple21(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19, final PartialOrder A20) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public PartialOrder catsKernelPartialOrderForTuple22(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19, final PartialOrder A20, final PartialOrder A21) {
      return TuplePartialOrderInstances.catsKernelPartialOrderForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Hash catsKernelHashForTuple1(final Hash A0) {
      return TupleHashInstances.catsKernelHashForTuple1$(this, A0);
   }

   public Hash catsKernelHashForTuple2(final Hash A0, final Hash A1) {
      return TupleHashInstances.catsKernelHashForTuple2$(this, A0, A1);
   }

   public Hash catsKernelHashForTuple3(final Hash A0, final Hash A1, final Hash A2) {
      return TupleHashInstances.catsKernelHashForTuple3$(this, A0, A1, A2);
   }

   public Hash catsKernelHashForTuple4(final Hash A0, final Hash A1, final Hash A2, final Hash A3) {
      return TupleHashInstances.catsKernelHashForTuple4$(this, A0, A1, A2, A3);
   }

   public Hash catsKernelHashForTuple5(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4) {
      return TupleHashInstances.catsKernelHashForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Hash catsKernelHashForTuple6(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5) {
      return TupleHashInstances.catsKernelHashForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Hash catsKernelHashForTuple7(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6) {
      return TupleHashInstances.catsKernelHashForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Hash catsKernelHashForTuple8(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7) {
      return TupleHashInstances.catsKernelHashForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Hash catsKernelHashForTuple9(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8) {
      return TupleHashInstances.catsKernelHashForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Hash catsKernelHashForTuple10(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9) {
      return TupleHashInstances.catsKernelHashForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Hash catsKernelHashForTuple11(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10) {
      return TupleHashInstances.catsKernelHashForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Hash catsKernelHashForTuple12(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11) {
      return TupleHashInstances.catsKernelHashForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Hash catsKernelHashForTuple13(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12) {
      return TupleHashInstances.catsKernelHashForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Hash catsKernelHashForTuple14(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13) {
      return TupleHashInstances.catsKernelHashForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Hash catsKernelHashForTuple15(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14) {
      return TupleHashInstances.catsKernelHashForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Hash catsKernelHashForTuple16(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15) {
      return TupleHashInstances.catsKernelHashForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Hash catsKernelHashForTuple17(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16) {
      return TupleHashInstances.catsKernelHashForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Hash catsKernelHashForTuple18(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17) {
      return TupleHashInstances.catsKernelHashForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Hash catsKernelHashForTuple19(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18) {
      return TupleHashInstances.catsKernelHashForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Hash catsKernelHashForTuple20(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19) {
      return TupleHashInstances.catsKernelHashForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Hash catsKernelHashForTuple21(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19, final Hash A20) {
      return TupleHashInstances.catsKernelHashForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Hash catsKernelHashForTuple22(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19, final Hash A20, final Hash A21) {
      return TupleHashInstances.catsKernelHashForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   public Eq catsKernelEqForTuple1(final Eq A0) {
      return TupleEqInstances.catsKernelEqForTuple1$(this, A0);
   }

   public Eq catsKernelEqForTuple2(final Eq A0, final Eq A1) {
      return TupleEqInstances.catsKernelEqForTuple2$(this, A0, A1);
   }

   public Eq catsKernelEqForTuple3(final Eq A0, final Eq A1, final Eq A2) {
      return TupleEqInstances.catsKernelEqForTuple3$(this, A0, A1, A2);
   }

   public Eq catsKernelEqForTuple4(final Eq A0, final Eq A1, final Eq A2, final Eq A3) {
      return TupleEqInstances.catsKernelEqForTuple4$(this, A0, A1, A2, A3);
   }

   public Eq catsKernelEqForTuple5(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4) {
      return TupleEqInstances.catsKernelEqForTuple5$(this, A0, A1, A2, A3, A4);
   }

   public Eq catsKernelEqForTuple6(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5) {
      return TupleEqInstances.catsKernelEqForTuple6$(this, A0, A1, A2, A3, A4, A5);
   }

   public Eq catsKernelEqForTuple7(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6) {
      return TupleEqInstances.catsKernelEqForTuple7$(this, A0, A1, A2, A3, A4, A5, A6);
   }

   public Eq catsKernelEqForTuple8(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7) {
      return TupleEqInstances.catsKernelEqForTuple8$(this, A0, A1, A2, A3, A4, A5, A6, A7);
   }

   public Eq catsKernelEqForTuple9(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8) {
      return TupleEqInstances.catsKernelEqForTuple9$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   public Eq catsKernelEqForTuple10(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9) {
      return TupleEqInstances.catsKernelEqForTuple10$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   public Eq catsKernelEqForTuple11(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10) {
      return TupleEqInstances.catsKernelEqForTuple11$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   public Eq catsKernelEqForTuple12(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11) {
      return TupleEqInstances.catsKernelEqForTuple12$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   public Eq catsKernelEqForTuple13(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12) {
      return TupleEqInstances.catsKernelEqForTuple13$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   public Eq catsKernelEqForTuple14(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13) {
      return TupleEqInstances.catsKernelEqForTuple14$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   public Eq catsKernelEqForTuple15(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14) {
      return TupleEqInstances.catsKernelEqForTuple15$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   public Eq catsKernelEqForTuple16(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15) {
      return TupleEqInstances.catsKernelEqForTuple16$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   public Eq catsKernelEqForTuple17(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16) {
      return TupleEqInstances.catsKernelEqForTuple17$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   public Eq catsKernelEqForTuple18(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17) {
      return TupleEqInstances.catsKernelEqForTuple18$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   public Eq catsKernelEqForTuple19(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18) {
      return TupleEqInstances.catsKernelEqForTuple19$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   public Eq catsKernelEqForTuple20(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19) {
      return TupleEqInstances.catsKernelEqForTuple20$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   public Eq catsKernelEqForTuple21(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19, final Eq A20) {
      return TupleEqInstances.catsKernelEqForTuple21$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   public Eq catsKernelEqForTuple22(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19, final Eq A20, final Eq A21) {
      return TupleEqInstances.catsKernelEqForTuple22$(this, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   /** @deprecated */
   public Order catsKernelOrderForStream(final Order evidence$1) {
      return ScalaVersionSpecificOrderInstances.catsKernelOrderForStream$(this, evidence$1);
   }

   public Order catsKernelOrderForLazyList(final Order evidence$2) {
      return ScalaVersionSpecificOrderInstances.catsKernelOrderForLazyList$(this, evidence$2);
   }

   public Order catsKernelOrderForArraySeq(final Order evidence$3) {
      return ScalaVersionSpecificOrderInstances.catsKernelOrderForArraySeq$(this, evidence$3);
   }

   /** @deprecated */
   public PartialOrder catsKernelPartialOrderForStream(final PartialOrder evidence$4) {
      return ScalaVersionSpecificPartialOrderInstances.catsKernelPartialOrderForStream$(this, evidence$4);
   }

   public PartialOrder catsKernelPartialOrderForLazyList(final PartialOrder evidence$5) {
      return ScalaVersionSpecificPartialOrderInstances.catsKernelPartialOrderForLazyList$(this, evidence$5);
   }

   public PartialOrder catsKernelPartialOrderForArraySeq(final PartialOrder evidence$6) {
      return ScalaVersionSpecificPartialOrderInstances.catsKernelPartialOrderForArraySeq$(this, evidence$6);
   }

   /** @deprecated */
   public Hash catsKernelHashForStream(final Hash evidence$7) {
      return ScalaVersionSpecificHashInstances.catsKernelHashForStream$(this, evidence$7);
   }

   public Hash catsKernelHashForLazyList(final Hash evidence$8) {
      return ScalaVersionSpecificHashInstances.catsKernelHashForLazyList$(this, evidence$8);
   }

   public Hash catsKernelHashForArraySeq(final Hash evidence$9) {
      return ScalaVersionSpecificHashInstances.catsKernelHashForArraySeq$(this, evidence$9);
   }

   /** @deprecated */
   public Eq catsKernelEqForStream(final Eq evidence$10) {
      return ScalaVersionSpecificEqInstances.catsKernelEqForStream$(this, evidence$10);
   }

   public Eq catsKernelEqForLazyList(final Eq evidence$11) {
      return ScalaVersionSpecificEqInstances.catsKernelEqForLazyList$(this, evidence$11);
   }

   public Eq catsKernelEqForArraySeq(final Eq evidence$12) {
      return ScalaVersionSpecificEqInstances.catsKernelEqForArraySeq$(this, evidence$12);
   }

   public Equiv catsKernelEquivForEq(final Eq ev) {
      return EqToEquivConversion.catsKernelEquivForEq$(this, ev);
   }

   public final Eq apply(final Eq ev) {
      return ev;
   }

   public Eq by(final Function1 f, final Eq ev) {
      return new Eq(ev, f) {
         private final Eq ev$2;
         private final Function1 f$1;

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return this.ev$2.eqv(this.f$1.apply(x), this.f$1.apply(y));
         }

         public {
            this.ev$2 = ev$2;
            this.f$1 = f$1;
            Eq.$init$(this);
         }
      };
   }

   public Eq and(final Eq eq1, final Eq eq2) {
      return new Eq(eq1, eq2) {
         private final Eq eq1$1;
         private final Eq eq2$1;

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return this.eq1$1.eqv(x, y) && this.eq2$1.eqv(x, y);
         }

         public {
            this.eq1$1 = eq1$1;
            this.eq2$1 = eq2$1;
            Eq.$init$(this);
         }
      };
   }

   public Eq or(final Eq eq1, final Eq eq2) {
      return new Eq(eq1, eq2) {
         private final Eq eq1$2;
         private final Eq eq2$2;

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return this.eq1$2.eqv(x, y) || this.eq2$2.eqv(x, y);
         }

         public {
            this.eq1$2 = eq1$2;
            this.eq2$2 = eq2$2;
            Eq.$init$(this);
         }
      };
   }

   public Eq instance(final Function2 f) {
      return new Eq(f) {
         private final Function2 f$2;

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.f$2.apply(x, y));
         }

         public {
            this.f$2 = f$2;
            Eq.$init$(this);
         }
      };
   }

   public Eq fromUniversalEquals() {
      return new Eq() {
         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return BoxesRunTime.equals(x, y);
         }

         public {
            Eq.$init$(this);
         }
      };
   }

   public Eq allEqual() {
      return new Eq() {
         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return true;
         }

         public {
            Eq.$init$(this);
         }
      };
   }

   public BoundedSemilattice allEqualBoundedSemilattice() {
      return new BoundedSemilattice() {
         public Object combineN(final Object a, final int n) {
            return BoundedSemilattice.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return BoundedSemilattice.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return BoundedSemilattice.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return BoundedSemilattice.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return BoundedSemilattice.combineN$mcJ$sp$(this, a, n);
         }

         public CommutativeMonoid reverse() {
            return CommutativeMonoid.reverse$(this);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
         }

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

         public Eq empty() {
            return Eq$.MODULE$.allEqual();
         }

         public Eq combine(final Eq e1, final Eq e2) {
            return Eq$.MODULE$.and(e1, e2);
         }

         public Option combineAllOption(final IterableOnce es) {
            Object var10000;
            if (es.iterator().isEmpty()) {
               var10000 = .MODULE$;
            } else {
               Vector materialized = es.iterator().toVector();
               var10000 = new Some(new Eq(materialized) {
                  private final Vector materialized$1;

                  public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
                     return Eq.eqv$mcZ$sp$(this, x, y);
                  }

                  public boolean eqv$mcB$sp(final byte x, final byte y) {
                     return Eq.eqv$mcB$sp$(this, x, y);
                  }

                  public boolean eqv$mcC$sp(final char x, final char y) {
                     return Eq.eqv$mcC$sp$(this, x, y);
                  }

                  public boolean eqv$mcD$sp(final double x, final double y) {
                     return Eq.eqv$mcD$sp$(this, x, y);
                  }

                  public boolean eqv$mcF$sp(final float x, final float y) {
                     return Eq.eqv$mcF$sp$(this, x, y);
                  }

                  public boolean eqv$mcI$sp(final int x, final int y) {
                     return Eq.eqv$mcI$sp$(this, x, y);
                  }

                  public boolean eqv$mcJ$sp(final long x, final long y) {
                     return Eq.eqv$mcJ$sp$(this, x, y);
                  }

                  public boolean eqv$mcS$sp(final short x, final short y) {
                     return Eq.eqv$mcS$sp$(this, x, y);
                  }

                  public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
                     return Eq.eqv$mcV$sp$(this, x, y);
                  }

                  public boolean neqv(final Object x, final Object y) {
                     return Eq.neqv$(this, x, y);
                  }

                  public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
                     return Eq.neqv$mcZ$sp$(this, x, y);
                  }

                  public boolean neqv$mcB$sp(final byte x, final byte y) {
                     return Eq.neqv$mcB$sp$(this, x, y);
                  }

                  public boolean neqv$mcC$sp(final char x, final char y) {
                     return Eq.neqv$mcC$sp$(this, x, y);
                  }

                  public boolean neqv$mcD$sp(final double x, final double y) {
                     return Eq.neqv$mcD$sp$(this, x, y);
                  }

                  public boolean neqv$mcF$sp(final float x, final float y) {
                     return Eq.neqv$mcF$sp$(this, x, y);
                  }

                  public boolean neqv$mcI$sp(final int x, final int y) {
                     return Eq.neqv$mcI$sp$(this, x, y);
                  }

                  public boolean neqv$mcJ$sp(final long x, final long y) {
                     return Eq.neqv$mcJ$sp$(this, x, y);
                  }

                  public boolean neqv$mcS$sp(final short x, final short y) {
                     return Eq.neqv$mcS$sp$(this, x, y);
                  }

                  public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
                     return Eq.neqv$mcV$sp$(this, x, y);
                  }

                  public boolean eqv(final Object x, final Object y) {
                     return this.materialized$1.forall((x$1) -> BoxesRunTime.boxToBoolean($anonfun$eqv$1(x, y, x$1)));
                  }

                  // $FF: synthetic method
                  public static final boolean $anonfun$eqv$1(final Object x$3, final Object y$1, final Eq x$1) {
                     return x$1.eqv(x$3, y$1);
                  }

                  public {
                     this.materialized$1 = materialized$1;
                     Eq.$init$(this);
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               });
            }

            return (Option)var10000;
         }

         public {
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
            Monoid.$init$(this);
            CommutativeMonoid.$init$(this);
            BoundedSemilattice.$init$(this);
         }
      };
   }

   public Semilattice anyEqualSemilattice() {
      return new Semilattice() {
         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
         }

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

         public Eq combine(final Eq e1, final Eq e2) {
            return Eq$.MODULE$.or(e1, e2);
         }

         public Option combineAllOption(final IterableOnce es) {
            Object var10000;
            if (es.iterator().isEmpty()) {
               var10000 = .MODULE$;
            } else {
               Vector materialized = es.iterator().toVector();
               var10000 = new Some(new Eq(materialized) {
                  private final Vector materialized$2;

                  public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
                     return Eq.eqv$mcZ$sp$(this, x, y);
                  }

                  public boolean eqv$mcB$sp(final byte x, final byte y) {
                     return Eq.eqv$mcB$sp$(this, x, y);
                  }

                  public boolean eqv$mcC$sp(final char x, final char y) {
                     return Eq.eqv$mcC$sp$(this, x, y);
                  }

                  public boolean eqv$mcD$sp(final double x, final double y) {
                     return Eq.eqv$mcD$sp$(this, x, y);
                  }

                  public boolean eqv$mcF$sp(final float x, final float y) {
                     return Eq.eqv$mcF$sp$(this, x, y);
                  }

                  public boolean eqv$mcI$sp(final int x, final int y) {
                     return Eq.eqv$mcI$sp$(this, x, y);
                  }

                  public boolean eqv$mcJ$sp(final long x, final long y) {
                     return Eq.eqv$mcJ$sp$(this, x, y);
                  }

                  public boolean eqv$mcS$sp(final short x, final short y) {
                     return Eq.eqv$mcS$sp$(this, x, y);
                  }

                  public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
                     return Eq.eqv$mcV$sp$(this, x, y);
                  }

                  public boolean neqv(final Object x, final Object y) {
                     return Eq.neqv$(this, x, y);
                  }

                  public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
                     return Eq.neqv$mcZ$sp$(this, x, y);
                  }

                  public boolean neqv$mcB$sp(final byte x, final byte y) {
                     return Eq.neqv$mcB$sp$(this, x, y);
                  }

                  public boolean neqv$mcC$sp(final char x, final char y) {
                     return Eq.neqv$mcC$sp$(this, x, y);
                  }

                  public boolean neqv$mcD$sp(final double x, final double y) {
                     return Eq.neqv$mcD$sp$(this, x, y);
                  }

                  public boolean neqv$mcF$sp(final float x, final float y) {
                     return Eq.neqv$mcF$sp$(this, x, y);
                  }

                  public boolean neqv$mcI$sp(final int x, final int y) {
                     return Eq.neqv$mcI$sp$(this, x, y);
                  }

                  public boolean neqv$mcJ$sp(final long x, final long y) {
                     return Eq.neqv$mcJ$sp$(this, x, y);
                  }

                  public boolean neqv$mcS$sp(final short x, final short y) {
                     return Eq.neqv$mcS$sp$(this, x, y);
                  }

                  public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
                     return Eq.neqv$mcV$sp$(this, x, y);
                  }

                  public boolean eqv(final Object x, final Object y) {
                     return this.materialized$2.exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$eqv$2(x, y, x$2)));
                  }

                  // $FF: synthetic method
                  public static final boolean $anonfun$eqv$2(final Object x$4, final Object y$2, final Eq x$2) {
                     return x$2.eqv(x$4, y$2);
                  }

                  public {
                     this.materialized$2 = materialized$2;
                     Eq.$init$(this);
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               });
            }

            return (Option)var10000;
         }

         public {
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   public PartialOrder catsKernelInstancesForBitSet() {
      return package$.MODULE$.catsKernelStdOrderForBitSet();
   }

   public PartialOrder catsKernelPartialOrderForSet() {
      return cats.kernel.instances.set.package$.MODULE$.catsKernelStdPartialOrderForSet();
   }

   public Order catsKernelOrderForEither(final Order evidence$1, final Order evidence$2) {
      return cats.kernel.instances.either.package$.MODULE$.catsStdOrderForEither(evidence$1, evidence$2);
   }

   public Order catsKernelInstancesForUnit() {
      return cats.kernel.instances.unit.package$.MODULE$.catsKernelStdOrderForUnit();
   }

   public Order catsKernelInstancesForBoolean() {
      return cats.kernel.instances.boolean.package$.MODULE$.catsKernelStdOrderForBoolean();
   }

   public Order catsKernelInstancesForByte() {
      return cats.kernel.instances.byte.package$.MODULE$.catsKernelStdOrderForByte();
   }

   public Order catsKernelInstancesForShort() {
      return cats.kernel.instances.short.package$.MODULE$.catsKernelStdOrderForShort();
   }

   public Order catsKernelInstancesForInt() {
      return cats.kernel.instances.int.package$.MODULE$.catsKernelStdOrderForInt();
   }

   public Order catsKernelInstancesForLong() {
      return cats.kernel.instances.long.package$.MODULE$.catsKernelStdOrderForLong();
   }

   public Order catsKernelInstancesForBigInt() {
      return cats.kernel.instances.bigInt.package$.MODULE$.catsKernelStdOrderForBigInt();
   }

   public Order catsKernelInstancesForBigDecimal() {
      return cats.kernel.instances.bigDecimal.package$.MODULE$.catsKernelStdOrderForBigDecimal();
   }

   public Order catsKernelInstancesForDuration() {
      return cats.kernel.instances.duration.package$.MODULE$.catsKernelStdOrderForDuration();
   }

   public Order catsKernelInstancesForFiniteDuration() {
      return cats.kernel.instances.all.package$.MODULE$.catsKernelStdOrderForFiniteDuration();
   }

   public Order catsKernelInstancesForChar() {
      return cats.kernel.instances.char.package$.MODULE$.catsKernelStdOrderForChar();
   }

   public Order catsKernelInstancesForSymbol() {
      return cats.kernel.instances.symbol.package$.MODULE$.catsKernelStdOrderForSymbol();
   }

   public Order catsKernelInstancesForString() {
      return cats.kernel.instances.string.package$.MODULE$.catsKernelStdOrderForString();
   }

   public Order catsKernelInstancesForUUID() {
      return cats.kernel.instances.uuid.package$.MODULE$.catsKernelStdOrderForUUID();
   }

   public Order catsKernelInstancesForDouble() {
      return cats.kernel.instances.double.package$.MODULE$.catsKernelStdOrderForDouble();
   }

   public Order catsKernelInstancesForFloat() {
      return cats.kernel.instances.float.package$.MODULE$.catsKernelStdOrderForFloat();
   }

   public Order catsKernelOrderForOption(final Order evidence$3) {
      return cats.kernel.instances.option.package$.MODULE$.catsKernelStdOrderForOption(evidence$3);
   }

   public Order catsKernelOrderForList(final Order evidence$4) {
      return cats.kernel.instances.list.package$.MODULE$.catsKernelStdOrderForList(evidence$4);
   }

   public Order catsKernelOrderForVector(final Order evidence$5) {
      return cats.kernel.instances.vector.package$.MODULE$.catsKernelStdOrderForVector(evidence$5);
   }

   public Order catsKernelOrderForQueue(final Order evidence$6) {
      return cats.kernel.instances.queue.package$.MODULE$.catsKernelStdOrderForQueue(evidence$6);
   }

   public Order catsKernelOrderForSortedSet(final Order evidence$7) {
      return cats.kernel.instances.sortedSet.package$.MODULE$.catsKernelStdOrderForSortedSet(evidence$7);
   }

   public Order catsKernelOrderForFunction0(final Order evidence$8) {
      return cats.kernel.instances.function.package$.MODULE$.catsKernelOrderForFunction0(evidence$8);
   }

   public Eq catsStdEqForTry(final Eq A, final Eq T) {
      return new Eq(A, T) {
         private final Eq A$1;
         private final Eq T$1;

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Eq.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Try x, final Try y) {
            Tuple2 var4 = new Tuple2(x, y);
            boolean var3;
            if (var4 != null) {
               Try var5 = (Try)var4._1();
               Try var6 = (Try)var4._2();
               if (var5 instanceof Success) {
                  Success var7 = (Success)var5;
                  Object a = var7.value();
                  if (var6 instanceof Success) {
                     Success var9 = (Success)var6;
                     Object b = var9.value();
                     var3 = this.A$1.eqv(a, b);
                     return var3;
                  }
               }
            }

            if (var4 != null) {
               Try var11 = (Try)var4._1();
               Try var12 = (Try)var4._2();
               if (var11 instanceof Failure) {
                  Failure var13 = (Failure)var11;
                  Throwable a = var13.exception();
                  if (var12 instanceof Failure) {
                     Failure var15 = (Failure)var12;
                     Throwable b = var15.exception();
                     var3 = this.T$1.eqv(a, b);
                     return var3;
                  }
               }
            }

            var3 = false;
            return var3;
         }

         public {
            this.A$1 = A$1;
            this.T$1 = T$1;
            Eq.$init$(this);
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Eq$.class);
   }

   public Eq by$mZZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$3;
         private final Function1 f$3;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$3.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$3.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToBoolean(this.f$3.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$3 = ev$3;
            this.f$3 = f$3;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mZBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$4;
         private final Function1 f$4;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$4.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$4.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToByte(this.f$4.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$4 = ev$4;
            this.f$4 = f$4;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mZCc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$5;
         private final Function1 f$5;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$5.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$5.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToChar(this.f$5.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$5 = ev$5;
            this.f$5 = f$5;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mZDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$6;
         private final Function1 f$6;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$6.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$6.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToDouble(this.f$6.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$6 = ev$6;
            this.f$6 = f$6;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mZFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$7;
         private final Function1 f$7;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$7.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$7.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToFloat(this.f$7.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$7 = ev$7;
            this.f$7 = f$7;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mZIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$8;
         private final Function1 f$8;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$8.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$8.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToInt(this.f$8.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$8 = ev$8;
            this.f$8 = f$8;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mZJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$9;
         private final Function1 f$9;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$9.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$9.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToLong(this.f$9.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$9 = ev$9;
            this.f$9 = f$9;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mZSc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$10;
         private final Function1 f$10;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$10.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$10.apply(BoxesRunTime.boxToBoolean(x))), BoxesRunTime.unboxToShort(this.f$10.apply(BoxesRunTime.boxToBoolean(y))));
         }

         public {
            this.ev$10 = ev$10;
            this.f$10 = f$10;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mZVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcZ$sp(ev, f) {
         private final Eq ev$11;
         private final Function1 f$11;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.ev$11.eqv$mcV$sp((BoxedUnit)this.f$11.apply(BoxesRunTime.boxToBoolean(x)), (BoxedUnit)this.f$11.apply(BoxesRunTime.boxToBoolean(y)));
         }

         public {
            this.ev$11 = ev$11;
            this.f$11 = f$11;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$12;
         private final Function1 f$12;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$12.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$12.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToBoolean(this.f$12.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$12 = ev$12;
            this.f$12 = f$12;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$13;
         private final Function1 f$13;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$13.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$13.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToByte(this.f$13.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$13 = ev$13;
            this.f$13 = f$13;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBCc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$14;
         private final Function1 f$14;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$14.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$14.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToChar(this.f$14.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$14 = ev$14;
            this.f$14 = f$14;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$15;
         private final Function1 f$15;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$15.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$15.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToDouble(this.f$15.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$15 = ev$15;
            this.f$15 = f$15;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$16;
         private final Function1 f$16;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$16.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$16.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToFloat(this.f$16.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$16 = ev$16;
            this.f$16 = f$16;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$17;
         private final Function1 f$17;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$17.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$17.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToInt(this.f$17.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$17 = ev$17;
            this.f$17 = f$17;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$18;
         private final Function1 f$18;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$18.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$18.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToLong(this.f$18.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$18 = ev$18;
            this.f$18 = f$18;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBSc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$19;
         private final Function1 f$19;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$19.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$19.apply(BoxesRunTime.boxToByte(x))), BoxesRunTime.unboxToShort(this.f$19.apply(BoxesRunTime.boxToByte(y))));
         }

         public {
            this.ev$19 = ev$19;
            this.f$19 = f$19;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mBVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcB$sp(ev, f) {
         private final Eq ev$20;
         private final Function1 f$20;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.ev$20.eqv$mcV$sp((BoxedUnit)this.f$20.apply(BoxesRunTime.boxToByte(x)), (BoxedUnit)this.f$20.apply(BoxesRunTime.boxToByte(y)));
         }

         public {
            this.ev$20 = ev$20;
            this.f$20 = f$20;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$21;
         private final Function1 f$21;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$21.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$21.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToBoolean(this.f$21.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$21 = ev$21;
            this.f$21 = f$21;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$22;
         private final Function1 f$22;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$22.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$22.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToByte(this.f$22.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$22 = ev$22;
            this.f$22 = f$22;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCCc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$23;
         private final Function1 f$23;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$23.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$23.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToChar(this.f$23.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$23 = ev$23;
            this.f$23 = f$23;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$24;
         private final Function1 f$24;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$24.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$24.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToDouble(this.f$24.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$24 = ev$24;
            this.f$24 = f$24;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$25;
         private final Function1 f$25;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$25.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$25.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToFloat(this.f$25.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$25 = ev$25;
            this.f$25 = f$25;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$26;
         private final Function1 f$26;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$26.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$26.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToInt(this.f$26.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$26 = ev$26;
            this.f$26 = f$26;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$27;
         private final Function1 f$27;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$27.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$27.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToLong(this.f$27.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$27 = ev$27;
            this.f$27 = f$27;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCSc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$28;
         private final Function1 f$28;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$28.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$28.apply(BoxesRunTime.boxToCharacter(x))), BoxesRunTime.unboxToShort(this.f$28.apply(BoxesRunTime.boxToCharacter(y))));
         }

         public {
            this.ev$28 = ev$28;
            this.f$28 = f$28;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mCVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcC$sp(ev, f) {
         private final Eq ev$29;
         private final Function1 f$29;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.ev$29.eqv$mcV$sp((BoxedUnit)this.f$29.apply(BoxesRunTime.boxToCharacter(x)), (BoxedUnit)this.f$29.apply(BoxesRunTime.boxToCharacter(y)));
         }

         public {
            this.ev$29 = ev$29;
            this.f$29 = f$29;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$30;
         private final Function1 f$30;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$30.eqv$mcZ$sp(this.f$30.apply$mcZD$sp(x), this.f$30.apply$mcZD$sp(y));
         }

         public {
            this.ev$30 = ev$30;
            this.f$30 = f$30;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$31;
         private final Function1 f$31;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$31.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$31.apply(BoxesRunTime.boxToDouble(x))), BoxesRunTime.unboxToByte(this.f$31.apply(BoxesRunTime.boxToDouble(y))));
         }

         public {
            this.ev$31 = ev$31;
            this.f$31 = f$31;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDCc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$32;
         private final Function1 f$32;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$32.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$32.apply(BoxesRunTime.boxToDouble(x))), BoxesRunTime.unboxToChar(this.f$32.apply(BoxesRunTime.boxToDouble(y))));
         }

         public {
            this.ev$32 = ev$32;
            this.f$32 = f$32;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$33;
         private final Function1 f$33;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$33.eqv$mcD$sp(this.f$33.apply$mcDD$sp(x), this.f$33.apply$mcDD$sp(y));
         }

         public {
            this.ev$33 = ev$33;
            this.f$33 = f$33;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$34;
         private final Function1 f$34;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$34.eqv$mcF$sp(this.f$34.apply$mcFD$sp(x), this.f$34.apply$mcFD$sp(y));
         }

         public {
            this.ev$34 = ev$34;
            this.f$34 = f$34;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$35;
         private final Function1 f$35;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$35.eqv$mcI$sp(this.f$35.apply$mcID$sp(x), this.f$35.apply$mcID$sp(y));
         }

         public {
            this.ev$35 = ev$35;
            this.f$35 = f$35;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$36;
         private final Function1 f$36;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$36.eqv$mcJ$sp(this.f$36.apply$mcJD$sp(x), this.f$36.apply$mcJD$sp(y));
         }

         public {
            this.ev$36 = ev$36;
            this.f$36 = f$36;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDSc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$37;
         private final Function1 f$37;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.ev$37.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$37.apply(BoxesRunTime.boxToDouble(x))), BoxesRunTime.unboxToShort(this.f$37.apply(BoxesRunTime.boxToDouble(y))));
         }

         public {
            this.ev$37 = ev$37;
            this.f$37 = f$37;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mDVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcD$sp(ev, f) {
         private final Eq ev$38;
         private final Function1 f$38;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            Eq var10000 = this.ev$38;
            this.f$38.apply$mcVD$sp(x);
            BoxedUnit var10001 = BoxedUnit.UNIT;
            this.f$38.apply$mcVD$sp(y);
            return var10000.eqv$mcV$sp(var10001, BoxedUnit.UNIT);
         }

         public {
            this.ev$38 = ev$38;
            this.f$38 = f$38;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$39;
         private final Function1 f$39;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$39.eqv$mcZ$sp(this.f$39.apply$mcZF$sp(x), this.f$39.apply$mcZF$sp(y));
         }

         public {
            this.ev$39 = ev$39;
            this.f$39 = f$39;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$40;
         private final Function1 f$40;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$40.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$40.apply(BoxesRunTime.boxToFloat(x))), BoxesRunTime.unboxToByte(this.f$40.apply(BoxesRunTime.boxToFloat(y))));
         }

         public {
            this.ev$40 = ev$40;
            this.f$40 = f$40;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFCc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$41;
         private final Function1 f$41;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$41.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$41.apply(BoxesRunTime.boxToFloat(x))), BoxesRunTime.unboxToChar(this.f$41.apply(BoxesRunTime.boxToFloat(y))));
         }

         public {
            this.ev$41 = ev$41;
            this.f$41 = f$41;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$42;
         private final Function1 f$42;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$42.eqv$mcD$sp(this.f$42.apply$mcDF$sp(x), this.f$42.apply$mcDF$sp(y));
         }

         public {
            this.ev$42 = ev$42;
            this.f$42 = f$42;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$43;
         private final Function1 f$43;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$43.eqv$mcF$sp(this.f$43.apply$mcFF$sp(x), this.f$43.apply$mcFF$sp(y));
         }

         public {
            this.ev$43 = ev$43;
            this.f$43 = f$43;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$44;
         private final Function1 f$44;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$44.eqv$mcI$sp(this.f$44.apply$mcIF$sp(x), this.f$44.apply$mcIF$sp(y));
         }

         public {
            this.ev$44 = ev$44;
            this.f$44 = f$44;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$45;
         private final Function1 f$45;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$45.eqv$mcJ$sp(this.f$45.apply$mcJF$sp(x), this.f$45.apply$mcJF$sp(y));
         }

         public {
            this.ev$45 = ev$45;
            this.f$45 = f$45;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFSc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$46;
         private final Function1 f$46;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.ev$46.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$46.apply(BoxesRunTime.boxToFloat(x))), BoxesRunTime.unboxToShort(this.f$46.apply(BoxesRunTime.boxToFloat(y))));
         }

         public {
            this.ev$46 = ev$46;
            this.f$46 = f$46;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mFVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcF$sp(ev, f) {
         private final Eq ev$47;
         private final Function1 f$47;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            Eq var10000 = this.ev$47;
            this.f$47.apply$mcVF$sp(x);
            BoxedUnit var10001 = BoxedUnit.UNIT;
            this.f$47.apply$mcVF$sp(y);
            return var10000.eqv$mcV$sp(var10001, BoxedUnit.UNIT);
         }

         public {
            this.ev$47 = ev$47;
            this.f$47 = f$47;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mIZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$48;
         private final Function1 f$48;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$48.eqv$mcZ$sp(this.f$48.apply$mcZI$sp(x), this.f$48.apply$mcZI$sp(y));
         }

         public {
            this.ev$48 = ev$48;
            this.f$48 = f$48;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mIBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$49;
         private final Function1 f$49;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$49.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$49.apply(BoxesRunTime.boxToInteger(x))), BoxesRunTime.unboxToByte(this.f$49.apply(BoxesRunTime.boxToInteger(y))));
         }

         public {
            this.ev$49 = ev$49;
            this.f$49 = f$49;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mICc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$50;
         private final Function1 f$50;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$50.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$50.apply(BoxesRunTime.boxToInteger(x))), BoxesRunTime.unboxToChar(this.f$50.apply(BoxesRunTime.boxToInteger(y))));
         }

         public {
            this.ev$50 = ev$50;
            this.f$50 = f$50;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mIDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$51;
         private final Function1 f$51;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$51.eqv$mcD$sp(this.f$51.apply$mcDI$sp(x), this.f$51.apply$mcDI$sp(y));
         }

         public {
            this.ev$51 = ev$51;
            this.f$51 = f$51;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mIFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$52;
         private final Function1 f$52;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$52.eqv$mcF$sp(this.f$52.apply$mcFI$sp(x), this.f$52.apply$mcFI$sp(y));
         }

         public {
            this.ev$52 = ev$52;
            this.f$52 = f$52;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mIIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$53;
         private final Function1 f$53;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$53.eqv$mcI$sp(this.f$53.apply$mcII$sp(x), this.f$53.apply$mcII$sp(y));
         }

         public {
            this.ev$53 = ev$53;
            this.f$53 = f$53;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mIJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$54;
         private final Function1 f$54;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$54.eqv$mcJ$sp(this.f$54.apply$mcJI$sp(x), this.f$54.apply$mcJI$sp(y));
         }

         public {
            this.ev$54 = ev$54;
            this.f$54 = f$54;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mISc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$55;
         private final Function1 f$55;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.ev$55.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$55.apply(BoxesRunTime.boxToInteger(x))), BoxesRunTime.unboxToShort(this.f$55.apply(BoxesRunTime.boxToInteger(y))));
         }

         public {
            this.ev$55 = ev$55;
            this.f$55 = f$55;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mIVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcI$sp(ev, f) {
         private final Eq ev$56;
         private final Function1 f$56;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            Eq var10000 = this.ev$56;
            this.f$56.apply$mcVI$sp(x);
            BoxedUnit var10001 = BoxedUnit.UNIT;
            this.f$56.apply$mcVI$sp(y);
            return var10000.eqv$mcV$sp(var10001, BoxedUnit.UNIT);
         }

         public {
            this.ev$56 = ev$56;
            this.f$56 = f$56;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$57;
         private final Function1 f$57;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$57.eqv$mcZ$sp(this.f$57.apply$mcZJ$sp(x), this.f$57.apply$mcZJ$sp(y));
         }

         public {
            this.ev$57 = ev$57;
            this.f$57 = f$57;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$58;
         private final Function1 f$58;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$58.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$58.apply(BoxesRunTime.boxToLong(x))), BoxesRunTime.unboxToByte(this.f$58.apply(BoxesRunTime.boxToLong(y))));
         }

         public {
            this.ev$58 = ev$58;
            this.f$58 = f$58;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJCc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$59;
         private final Function1 f$59;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$59.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$59.apply(BoxesRunTime.boxToLong(x))), BoxesRunTime.unboxToChar(this.f$59.apply(BoxesRunTime.boxToLong(y))));
         }

         public {
            this.ev$59 = ev$59;
            this.f$59 = f$59;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$60;
         private final Function1 f$60;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$60.eqv$mcD$sp(this.f$60.apply$mcDJ$sp(x), this.f$60.apply$mcDJ$sp(y));
         }

         public {
            this.ev$60 = ev$60;
            this.f$60 = f$60;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$61;
         private final Function1 f$61;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$61.eqv$mcF$sp(this.f$61.apply$mcFJ$sp(x), this.f$61.apply$mcFJ$sp(y));
         }

         public {
            this.ev$61 = ev$61;
            this.f$61 = f$61;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$62;
         private final Function1 f$62;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$62.eqv$mcI$sp(this.f$62.apply$mcIJ$sp(x), this.f$62.apply$mcIJ$sp(y));
         }

         public {
            this.ev$62 = ev$62;
            this.f$62 = f$62;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$63;
         private final Function1 f$63;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$63.eqv$mcJ$sp(this.f$63.apply$mcJJ$sp(x), this.f$63.apply$mcJJ$sp(y));
         }

         public {
            this.ev$63 = ev$63;
            this.f$63 = f$63;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJSc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$64;
         private final Function1 f$64;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.ev$64.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$64.apply(BoxesRunTime.boxToLong(x))), BoxesRunTime.unboxToShort(this.f$64.apply(BoxesRunTime.boxToLong(y))));
         }

         public {
            this.ev$64 = ev$64;
            this.f$64 = f$64;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mJVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcJ$sp(ev, f) {
         private final Eq ev$65;
         private final Function1 f$65;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            Eq var10000 = this.ev$65;
            this.f$65.apply$mcVJ$sp(x);
            BoxedUnit var10001 = BoxedUnit.UNIT;
            this.f$65.apply$mcVJ$sp(y);
            return var10000.eqv$mcV$sp(var10001, BoxedUnit.UNIT);
         }

         public {
            this.ev$65 = ev$65;
            this.f$65 = f$65;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$66;
         private final Function1 f$66;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$66.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$66.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToBoolean(this.f$66.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$66 = ev$66;
            this.f$66 = f$66;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$67;
         private final Function1 f$67;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$67.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$67.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToByte(this.f$67.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$67 = ev$67;
            this.f$67 = f$67;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSCc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$68;
         private final Function1 f$68;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$68.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$68.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToChar(this.f$68.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$68 = ev$68;
            this.f$68 = f$68;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$69;
         private final Function1 f$69;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$69.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$69.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToDouble(this.f$69.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$69 = ev$69;
            this.f$69 = f$69;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$70;
         private final Function1 f$70;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$70.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$70.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToFloat(this.f$70.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$70 = ev$70;
            this.f$70 = f$70;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$71;
         private final Function1 f$71;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$71.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$71.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToInt(this.f$71.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$71 = ev$71;
            this.f$71 = f$71;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$72;
         private final Function1 f$72;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$72.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$72.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToLong(this.f$72.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$72 = ev$72;
            this.f$72 = f$72;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSSc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$73;
         private final Function1 f$73;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$73.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$73.apply(BoxesRunTime.boxToShort(x))), BoxesRunTime.unboxToShort(this.f$73.apply(BoxesRunTime.boxToShort(y))));
         }

         public {
            this.ev$73 = ev$73;
            this.f$73 = f$73;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mSVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcS$sp(ev, f) {
         private final Eq ev$74;
         private final Function1 f$74;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.ev$74.eqv$mcV$sp((BoxedUnit)this.f$74.apply(BoxesRunTime.boxToShort(x)), (BoxedUnit)this.f$74.apply(BoxesRunTime.boxToShort(y)));
         }

         public {
            this.ev$74 = ev$74;
            this.f$74 = f$74;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVZc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$75;
         private final Function1 f$75;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$75.eqv$mcZ$sp(BoxesRunTime.unboxToBoolean(this.f$75.apply(x)), BoxesRunTime.unboxToBoolean(this.f$75.apply(y)));
         }

         public {
            this.ev$75 = ev$75;
            this.f$75 = f$75;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVBc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$76;
         private final Function1 f$76;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$76.eqv$mcB$sp(BoxesRunTime.unboxToByte(this.f$76.apply(x)), BoxesRunTime.unboxToByte(this.f$76.apply(y)));
         }

         public {
            this.ev$76 = ev$76;
            this.f$76 = f$76;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVCc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$77;
         private final Function1 f$77;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$77.eqv$mcC$sp(BoxesRunTime.unboxToChar(this.f$77.apply(x)), BoxesRunTime.unboxToChar(this.f$77.apply(y)));
         }

         public {
            this.ev$77 = ev$77;
            this.f$77 = f$77;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVDc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$78;
         private final Function1 f$78;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$78.eqv$mcD$sp(BoxesRunTime.unboxToDouble(this.f$78.apply(x)), BoxesRunTime.unboxToDouble(this.f$78.apply(y)));
         }

         public {
            this.ev$78 = ev$78;
            this.f$78 = f$78;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVFc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$79;
         private final Function1 f$79;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$79.eqv$mcF$sp(BoxesRunTime.unboxToFloat(this.f$79.apply(x)), BoxesRunTime.unboxToFloat(this.f$79.apply(y)));
         }

         public {
            this.ev$79 = ev$79;
            this.f$79 = f$79;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVIc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$80;
         private final Function1 f$80;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$80.eqv$mcI$sp(BoxesRunTime.unboxToInt(this.f$80.apply(x)), BoxesRunTime.unboxToInt(this.f$80.apply(y)));
         }

         public {
            this.ev$80 = ev$80;
            this.f$80 = f$80;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVJc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$81;
         private final Function1 f$81;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$81.eqv$mcJ$sp(BoxesRunTime.unboxToLong(this.f$81.apply(x)), BoxesRunTime.unboxToLong(this.f$81.apply(y)));
         }

         public {
            this.ev$81 = ev$81;
            this.f$81 = f$81;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVSc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$82;
         private final Function1 f$82;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$82.eqv$mcS$sp(BoxesRunTime.unboxToShort(this.f$82.apply(x)), BoxesRunTime.unboxToShort(this.f$82.apply(y)));
         }

         public {
            this.ev$82 = ev$82;
            this.f$82 = f$82;
            Eq.$init$(this);
         }
      };
   }

   public Eq by$mVVc$sp(final Function1 f, final Eq ev) {
      return new Eq$mcV$sp(ev, f) {
         private final Eq ev$83;
         private final Function1 f$83;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.ev$83.eqv$mcV$sp((BoxedUnit)this.f$83.apply(x), (BoxedUnit)this.f$83.apply(y));
         }

         public {
            this.ev$83 = ev$83;
            this.f$83 = f$83;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mZc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcZ$sp(eq1, eq2) {
         private final Eq eq1$3;
         private final Eq eq2$3;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.eq1$3.eqv$mcZ$sp(x, y) && this.eq2$3.eqv$mcZ$sp(x, y);
         }

         public {
            this.eq1$3 = eq1$3;
            this.eq2$3 = eq2$3;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mBc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcB$sp(eq1, eq2) {
         private final Eq eq1$4;
         private final Eq eq2$4;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.eq1$4.eqv$mcB$sp(x, y) && this.eq2$4.eqv$mcB$sp(x, y);
         }

         public {
            this.eq1$4 = eq1$4;
            this.eq2$4 = eq2$4;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mCc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcC$sp(eq1, eq2) {
         private final Eq eq1$5;
         private final Eq eq2$5;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.eq1$5.eqv$mcC$sp(x, y) && this.eq2$5.eqv$mcC$sp(x, y);
         }

         public {
            this.eq1$5 = eq1$5;
            this.eq2$5 = eq2$5;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mDc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcD$sp(eq1, eq2) {
         private final Eq eq1$6;
         private final Eq eq2$6;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.eq1$6.eqv$mcD$sp(x, y) && this.eq2$6.eqv$mcD$sp(x, y);
         }

         public {
            this.eq1$6 = eq1$6;
            this.eq2$6 = eq2$6;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mFc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcF$sp(eq1, eq2) {
         private final Eq eq1$7;
         private final Eq eq2$7;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.eq1$7.eqv$mcF$sp(x, y) && this.eq2$7.eqv$mcF$sp(x, y);
         }

         public {
            this.eq1$7 = eq1$7;
            this.eq2$7 = eq2$7;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mIc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcI$sp(eq1, eq2) {
         private final Eq eq1$8;
         private final Eq eq2$8;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.eq1$8.eqv$mcI$sp(x, y) && this.eq2$8.eqv$mcI$sp(x, y);
         }

         public {
            this.eq1$8 = eq1$8;
            this.eq2$8 = eq2$8;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mJc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcJ$sp(eq1, eq2) {
         private final Eq eq1$9;
         private final Eq eq2$9;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.eq1$9.eqv$mcJ$sp(x, y) && this.eq2$9.eqv$mcJ$sp(x, y);
         }

         public {
            this.eq1$9 = eq1$9;
            this.eq2$9 = eq2$9;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mSc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcS$sp(eq1, eq2) {
         private final Eq eq1$10;
         private final Eq eq2$10;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.eq1$10.eqv$mcS$sp(x, y) && this.eq2$10.eqv$mcS$sp(x, y);
         }

         public {
            this.eq1$10 = eq1$10;
            this.eq2$10 = eq2$10;
            Eq.$init$(this);
         }
      };
   }

   public Eq and$mVc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcV$sp(eq1, eq2) {
         private final Eq eq1$11;
         private final Eq eq2$11;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.eq1$11.eqv$mcV$sp(x, y) && this.eq2$11.eqv$mcV$sp(x, y);
         }

         public {
            this.eq1$11 = eq1$11;
            this.eq2$11 = eq2$11;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mZc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcZ$sp(eq1, eq2) {
         private final Eq eq1$12;
         private final Eq eq2$12;

         public boolean neqv(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq$mcZ$sp.neqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final boolean x, final boolean y) {
            return this.eqv$mcZ$sp(x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return this.eq1$12.eqv$mcZ$sp(x, y) || this.eq2$12.eqv$mcZ$sp(x, y);
         }

         public {
            this.eq1$12 = eq1$12;
            this.eq2$12 = eq2$12;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mBc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcB$sp(eq1, eq2) {
         private final Eq eq1$13;
         private final Eq eq2$13;

         public boolean neqv(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq$mcB$sp.neqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final byte x, final byte y) {
            return this.eqv$mcB$sp(x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return this.eq1$13.eqv$mcB$sp(x, y) || this.eq2$13.eqv$mcB$sp(x, y);
         }

         public {
            this.eq1$13 = eq1$13;
            this.eq2$13 = eq2$13;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mCc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcC$sp(eq1, eq2) {
         private final Eq eq1$14;
         private final Eq eq2$14;

         public boolean neqv(final char x, final char y) {
            return Eq$mcC$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq$mcC$sp.neqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final char x, final char y) {
            return this.eqv$mcC$sp(x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return this.eq1$14.eqv$mcC$sp(x, y) || this.eq2$14.eqv$mcC$sp(x, y);
         }

         public {
            this.eq1$14 = eq1$14;
            this.eq2$14 = eq2$14;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mDc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcD$sp(eq1, eq2) {
         private final Eq eq1$15;
         private final Eq eq2$15;

         public boolean neqv(final double x, final double y) {
            return Eq$mcD$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq$mcD$sp.neqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final double x, final double y) {
            return this.eqv$mcD$sp(x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return this.eq1$15.eqv$mcD$sp(x, y) || this.eq2$15.eqv$mcD$sp(x, y);
         }

         public {
            this.eq1$15 = eq1$15;
            this.eq2$15 = eq2$15;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mFc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcF$sp(eq1, eq2) {
         private final Eq eq1$16;
         private final Eq eq2$16;

         public boolean neqv(final float x, final float y) {
            return Eq$mcF$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq$mcF$sp.neqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final float x, final float y) {
            return this.eqv$mcF$sp(x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return this.eq1$16.eqv$mcF$sp(x, y) || this.eq2$16.eqv$mcF$sp(x, y);
         }

         public {
            this.eq1$16 = eq1$16;
            this.eq2$16 = eq2$16;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mIc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcI$sp(eq1, eq2) {
         private final Eq eq1$17;
         private final Eq eq2$17;

         public boolean neqv(final int x, final int y) {
            return Eq$mcI$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq$mcI$sp.neqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final int x, final int y) {
            return this.eqv$mcI$sp(x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return this.eq1$17.eqv$mcI$sp(x, y) || this.eq2$17.eqv$mcI$sp(x, y);
         }

         public {
            this.eq1$17 = eq1$17;
            this.eq2$17 = eq2$17;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mJc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcJ$sp(eq1, eq2) {
         private final Eq eq1$18;
         private final Eq eq2$18;

         public boolean neqv(final long x, final long y) {
            return Eq$mcJ$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq$mcJ$sp.neqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final long x, final long y) {
            return this.eqv$mcJ$sp(x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return this.eq1$18.eqv$mcJ$sp(x, y) || this.eq2$18.eqv$mcJ$sp(x, y);
         }

         public {
            this.eq1$18 = eq1$18;
            this.eq2$18 = eq2$18;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mSc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcS$sp(eq1, eq2) {
         private final Eq eq1$19;
         private final Eq eq2$19;

         public boolean neqv(final short x, final short y) {
            return Eq$mcS$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq$mcS$sp.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv(final short x, final short y) {
            return this.eqv$mcS$sp(x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return this.eq1$19.eqv$mcS$sp(x, y) || this.eq2$19.eqv$mcS$sp(x, y);
         }

         public {
            this.eq1$19 = eq1$19;
            this.eq2$19 = eq2$19;
            Eq.$init$(this);
         }
      };
   }

   public Eq or$mVc$sp(final Eq eq1, final Eq eq2) {
      return new Eq$mcV$sp(eq1, eq2) {
         private final Eq eq1$20;
         private final Eq eq2$20;

         public boolean neqv(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Eq$mcV$sp.neqv$mcV$sp$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Eq.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Eq.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Eq.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Eq.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Eq.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Eq.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Eq.eqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Eq.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Eq.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Eq.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Eq.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Eq.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Eq.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Eq.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Eq.neqv$mcS$sp$(this, x, y);
         }

         public boolean eqv(final BoxedUnit x, final BoxedUnit y) {
            return this.eqv$mcV$sp(x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return this.eq1$20.eqv$mcV$sp(x, y) || this.eq2$20.eqv$mcV$sp(x, y);
         }

         public {
            this.eq1$20 = eq1$20;
            this.eq2$20 = eq2$20;
            Eq.$init$(this);
         }
      };
   }

   private Eq$() {
   }
}
