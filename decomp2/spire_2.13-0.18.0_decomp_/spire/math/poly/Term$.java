package spire.math.poly;

import algebra.ring.Rig;
import algebra.ring.Semiring;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.math.Ordering;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichInt;
import scala.util.matching.Regex;

public final class Term$ implements Serializable {
   public static final Term$ MODULE$ = new Term$();
   private static final Regex spire$math$poly$Term$$IsZero;
   private static final Regex spire$math$poly$Term$$IsNegative;
   private static final Tuple2[] digitToSuperscript;
   private static final Regex superscriptRegex;
   private static final Function1 spire$math$poly$Term$$superscript;
   private static final Function1 removeSuperscript;

   static {
      spire$math$poly$Term$$IsZero = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("0"));
      spire$math$poly$Term$$IsNegative = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("-(.*)"));
      digitToSuperscript = (Tuple2[])((Object[])(new Tuple2[]{new Tuple2.mcCC.sp('0', '⁰'), new Tuple2.mcCC.sp('1', 'ⁱ'), new Tuple2.mcCC.sp('2', '\u2072'), new Tuple2.mcCC.sp('3', '\u2073'), new Tuple2.mcCC.sp('4', '⁴'), new Tuple2.mcCC.sp('5', '⁵'), new Tuple2.mcCC.sp('6', '⁶'), new Tuple2.mcCC.sp('7', '⁷'), new Tuple2.mcCC.sp('8', '⁸'), new Tuple2.mcCC.sp('9', '⁹'), new Tuple2.mcCC.sp('-', '⁻'), new Tuple2.mcCC.sp('1', '¹'), new Tuple2.mcCC.sp('2', '²'), new Tuple2.mcCC.sp('3', '³')}));
      superscriptRegex = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("[⁰¹²³⁴⁵⁶⁷⁸⁹⁻ⁱ]+"));
      spire$math$poly$Term$$superscript = (Function1)scala.Predef..MODULE$.Map().apply(scala.collection.ArrayOps..MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])MODULE$.digitToSuperscript())));
      removeSuperscript = (Function1)scala.Predef..MODULE$.Map().apply(scala.collection.ArrayOps..MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])MODULE$.digitToSuperscript()), (x$2) -> x$2.swap$mcCC$sp(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)))));
   }

   public Order ordering() {
      return new Order() {
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

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
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

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
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

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
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

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
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

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
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

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
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

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
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

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
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

         public int compare(final Term x, final Term y) {
            return (new RichInt(scala.Predef..MODULE$.intWrapper(x.exp()))).compare(BoxesRunTime.boxToInteger(y.exp()));
         }

         public {
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }
      };
   }

   public Term fromTuple(final Tuple2 tpl) {
      return new Term(tpl._2(), tpl._1$mcI$sp());
   }

   public Term zero(final Semiring r) {
      return new Term(r.zero(), 0);
   }

   public Term one(final Rig r) {
      return new Term(r.one(), 0);
   }

   public Regex spire$math$poly$Term$$IsZero() {
      return spire$math$poly$Term$$IsZero;
   }

   public Regex spire$math$poly$Term$$IsNegative() {
      return spire$math$poly$Term$$IsNegative;
   }

   private Tuple2[] digitToSuperscript() {
      return digitToSuperscript;
   }

   private Regex superscriptRegex() {
      return superscriptRegex;
   }

   public String removeSuperscript(final String text) {
      return this.superscriptRegex().replaceAllIn(text, (x$1) -> (new StringBuilder(1)).append("^").append(.MODULE$.map$extension(scala.Predef..MODULE$.augmentString(x$1.group(0)), MODULE$.removeSuperscript())).toString());
   }

   public Function1 spire$math$poly$Term$$superscript() {
      return spire$math$poly$Term$$superscript;
   }

   private Function1 removeSuperscript() {
      return removeSuperscript;
   }

   public Term apply(final Object coeff, final int exp) {
      return new Term(coeff, exp);
   }

   public Option unapply(final Term x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.coeff(), BoxesRunTime.boxToInteger(x$0.exp()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Term$.class);
   }

   public Term fromTuple$mDc$sp(final Tuple2 tpl) {
      return new Term$mcD$sp(tpl._2$mcD$sp(), tpl._1$mcI$sp());
   }

   public Term fromTuple$mFc$sp(final Tuple2 tpl) {
      return new Term$mcF$sp(BoxesRunTime.unboxToFloat(tpl._2()), tpl._1$mcI$sp());
   }

   public Term zero$mDc$sp(final Semiring r) {
      return new Term$mcD$sp(r.zero$mcD$sp(), 0);
   }

   public Term zero$mFc$sp(final Semiring r) {
      return new Term$mcF$sp(r.zero$mcF$sp(), 0);
   }

   public Term one$mDc$sp(final Rig r) {
      return new Term$mcD$sp(r.one$mcD$sp(), 0);
   }

   public Term one$mFc$sp(final Rig r) {
      return new Term$mcF$sp(r.one$mcF$sp(), 0);
   }

   public Term apply$mDc$sp(final double coeff, final int exp) {
      return new Term$mcD$sp(coeff, exp);
   }

   public Term apply$mFc$sp(final float coeff, final int exp) {
      return new Term$mcF$sp(coeff, exp);
   }

   public Option unapply$mDc$sp(final Term x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDI.sp(x$0.coeff$mcD$sp(), x$0.exp())));
   }

   public Option unapply$mFc$sp(final Term x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToFloat(x$0.coeff$mcF$sp()), BoxesRunTime.boxToInteger(x$0.exp()))));
   }

   private Term$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
