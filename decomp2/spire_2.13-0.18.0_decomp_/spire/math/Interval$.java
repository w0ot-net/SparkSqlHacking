package spire.math;

import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Ring;
import algebra.ring.Semiring;
import cats.kernel.CommutativeMonoid;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.Semigroup;
import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.IterableOnce;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.util.matching.Regex;
import spire.math.interval.Bound;
import spire.math.interval.Closed;
import spire.math.interval.EmptyBound;
import spire.math.interval.Open;
import spire.math.interval.Unbound;

public final class Interval$ implements Serializable {
   public static final Interval$ MODULE$ = new Interval$();
   private static final Regex NullRe;
   private static final Regex SingleRe;
   private static final Regex PairRe;

   static {
      NullRe = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^ *\\( *Ø *\\) *$"));
      SingleRe = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^ *\\[ *([^,]+) *\\] *$"));
      PairRe = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^ *(\\[|\\() *(.+?) *, *(.+?) *(\\]|\\)) *$"));
   }

   public Interval withFlags(final Object lower, final Object upper, final int flags, final Order evidence$2) {
      return (Interval)(evidence$2.lt(lower, upper) ? Bounded$.MODULE$.apply(lower, upper, flags) : (evidence$2.eqv(lower, upper) && flags == 0 ? Point$.MODULE$.apply(lower) : this.empty(evidence$2)));
   }

   public Interval empty(final Order evidence$3) {
      return Empty$.MODULE$.apply();
   }

   public Interval point(final Object a, final Order evidence$4) {
      return Point$.MODULE$.apply(a);
   }

   public Interval zero(final Order evidence$5, final Semiring r) {
      return Point$.MODULE$.apply(r.zero());
   }

   public Interval all(final Order evidence$6) {
      return All$.MODULE$.apply();
   }

   public Interval apply(final Object lower, final Object upper, final Order evidence$7) {
      return this.closed(lower, upper, evidence$7);
   }

   public Interval errorBounds(final double d) {
      Interval var10000;
      if (d == Double.POSITIVE_INFINITY) {
         var10000 = this.above(Rational$.MODULE$.apply(Double.MAX_VALUE), (Order)Rational$.MODULE$.RationalAlgebra());
      } else if (d == Double.NEGATIVE_INFINITY) {
         var10000 = this.below(Rational$.MODULE$.apply(scala.Double..MODULE$.MinValue()), (Order)Rational$.MODULE$.RationalAlgebra());
      } else if (Double.isNaN(d)) {
         var10000 = this.empty((Order)Rational$.MODULE$.RationalAlgebra());
      } else {
         Rational n0 = Rational$.MODULE$.apply(Math.nextAfter(d, (double)-1.0F));
         Rational n1 = Rational$.MODULE$.apply(d);
         Rational n2 = Rational$.MODULE$.apply(Math.nextUp(d));
         var10000 = this.apply(n1.$minus(n0).$div(Rational$.MODULE$.apply(2)).$plus(n0), n2.$minus(n1).$div(Rational$.MODULE$.apply(2)).$plus(n1), (Order)Rational$.MODULE$.RationalAlgebra());
      }

      return var10000;
   }

   public final int closedLowerFlags() {
      return 0;
   }

   public final int openLowerFlags() {
      return 1;
   }

   public final int closedUpperFlags() {
      return 0;
   }

   public final int openUpperFlags() {
      return 2;
   }

   public Interval fromOrderedBounds(final Bound lower, final Bound upper, final Order evidence$8) {
      Tuple2 var6 = new Tuple2(lower, upper);
      Object var4;
      if (var6 != null) {
         Bound var7 = (Bound)var6._1();
         Bound var8 = (Bound)var6._2();
         if (var7 instanceof EmptyBound && var8 instanceof EmptyBound) {
            var4 = this.empty(evidence$8);
            return (Interval)var4;
         }
      }

      if (var6 != null) {
         Bound var9 = (Bound)var6._1();
         Bound var10 = (Bound)var6._2();
         if (var9 instanceof Closed) {
            Closed var11 = (Closed)var9;
            Object x = var11.a();
            if (var10 instanceof Closed) {
               Closed var13 = (Closed)var10;
               Object y = var13.a();
               var4 = Bounded$.MODULE$.apply(x, y, this.closedLowerFlags() | this.closedUpperFlags());
               return (Interval)var4;
            }
         }
      }

      if (var6 != null) {
         Bound var15 = (Bound)var6._1();
         Bound var16 = (Bound)var6._2();
         if (var15 instanceof Open) {
            Open var17 = (Open)var15;
            Object x = var17.a();
            if (var16 instanceof Open) {
               Open var19 = (Open)var16;
               Object y = var19.a();
               var4 = Bounded$.MODULE$.apply(x, y, this.openLowerFlags() | this.openUpperFlags());
               return (Interval)var4;
            }
         }
      }

      if (var6 != null) {
         Bound var21 = (Bound)var6._1();
         Bound var22 = (Bound)var6._2();
         if (var21 instanceof Unbound && var22 instanceof Open) {
            Open var23 = (Open)var22;
            Object y = var23.a();
            var4 = this.below(y, evidence$8);
            return (Interval)var4;
         }
      }

      if (var6 != null) {
         Bound var25 = (Bound)var6._1();
         Bound var26 = (Bound)var6._2();
         if (var25 instanceof Open) {
            Open var27 = (Open)var25;
            Object x = var27.a();
            if (var26 instanceof Unbound) {
               var4 = this.above(x, evidence$8);
               return (Interval)var4;
            }
         }
      }

      if (var6 != null) {
         Bound var29 = (Bound)var6._1();
         Bound var30 = (Bound)var6._2();
         if (var29 instanceof Unbound && var30 instanceof Closed) {
            Closed var31 = (Closed)var30;
            Object y = var31.a();
            var4 = this.atOrBelow(y, evidence$8);
            return (Interval)var4;
         }
      }

      if (var6 != null) {
         Bound var33 = (Bound)var6._1();
         Bound var34 = (Bound)var6._2();
         if (var33 instanceof Closed) {
            Closed var35 = (Closed)var33;
            Object x = var35.a();
            if (var34 instanceof Unbound) {
               var4 = this.atOrAbove(x, evidence$8);
               return (Interval)var4;
            }
         }
      }

      if (var6 != null) {
         Bound var37 = (Bound)var6._1();
         Bound var38 = (Bound)var6._2();
         if (var37 instanceof Closed) {
            Closed var39 = (Closed)var37;
            Object x = var39.a();
            if (var38 instanceof Open) {
               Open var41 = (Open)var38;
               Object y = var41.a();
               var4 = Bounded$.MODULE$.apply(x, y, this.closedLowerFlags() | this.openUpperFlags());
               return (Interval)var4;
            }
         }
      }

      if (var6 != null) {
         Bound var43 = (Bound)var6._1();
         Bound var44 = (Bound)var6._2();
         if (var43 instanceof Open) {
            Open var45 = (Open)var43;
            Object x = var45.a();
            if (var44 instanceof Closed) {
               Closed var47 = (Closed)var44;
               Object y = var47.a();
               var4 = Bounded$.MODULE$.apply(x, y, this.openLowerFlags() | this.closedUpperFlags());
               return (Interval)var4;
            }
         }
      }

      if (var6 != null) {
         Bound var49 = (Bound)var6._1();
         Bound var50 = (Bound)var6._2();
         if (var49 instanceof Unbound && var50 instanceof Unbound) {
            var4 = this.all(evidence$8);
            return (Interval)var4;
         }
      }

      boolean var5;
      label140: {
         if (var6 != null) {
            Bound var51 = (Bound)var6._1();
            if (var51 instanceof EmptyBound) {
               var5 = true;
               break label140;
            }
         }

         if (var6 != null) {
            Bound var52 = (Bound)var6._2();
            if (var52 instanceof EmptyBound) {
               var5 = true;
               break label140;
            }
         }

         var5 = false;
      }

      if (var5) {
         throw new IllegalArgumentException("invalid empty bound");
      } else {
         throw new MatchError(var6);
      }
   }

   public Interval fromBounds(final Bound lower, final Bound upper, final Order evidence$9) {
      Tuple2 var6 = new Tuple2(lower, upper);
      Interval var4;
      if (var6 != null) {
         Bound var7 = (Bound)var6._1();
         Bound var8 = (Bound)var6._2();
         if (var7 instanceof EmptyBound && var8 instanceof EmptyBound) {
            var4 = this.empty(evidence$9);
            return var4;
         }
      }

      if (var6 != null) {
         Bound var9 = (Bound)var6._1();
         Bound var10 = (Bound)var6._2();
         if (var9 instanceof Closed) {
            Closed var11 = (Closed)var9;
            Object x = var11.a();
            if (var10 instanceof Closed) {
               Closed var13 = (Closed)var10;
               Object y = var13.a();
               var4 = this.closed(x, y, evidence$9);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Bound var15 = (Bound)var6._1();
         Bound var16 = (Bound)var6._2();
         if (var15 instanceof Open) {
            Open var17 = (Open)var15;
            Object x = var17.a();
            if (var16 instanceof Open) {
               Open var19 = (Open)var16;
               Object y = var19.a();
               var4 = this.open(x, y, evidence$9);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Bound var21 = (Bound)var6._1();
         Bound var22 = (Bound)var6._2();
         if (var21 instanceof Unbound && var22 instanceof Open) {
            Open var23 = (Open)var22;
            Object y = var23.a();
            var4 = this.below(y, evidence$9);
            return var4;
         }
      }

      if (var6 != null) {
         Bound var25 = (Bound)var6._1();
         Bound var26 = (Bound)var6._2();
         if (var25 instanceof Open) {
            Open var27 = (Open)var25;
            Object x = var27.a();
            if (var26 instanceof Unbound) {
               var4 = this.above(x, evidence$9);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Bound var29 = (Bound)var6._1();
         Bound var30 = (Bound)var6._2();
         if (var29 instanceof Unbound && var30 instanceof Closed) {
            Closed var31 = (Closed)var30;
            Object y = var31.a();
            var4 = this.atOrBelow(y, evidence$9);
            return var4;
         }
      }

      if (var6 != null) {
         Bound var33 = (Bound)var6._1();
         Bound var34 = (Bound)var6._2();
         if (var33 instanceof Closed) {
            Closed var35 = (Closed)var33;
            Object x = var35.a();
            if (var34 instanceof Unbound) {
               var4 = this.atOrAbove(x, evidence$9);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Bound var37 = (Bound)var6._1();
         Bound var38 = (Bound)var6._2();
         if (var37 instanceof Closed) {
            Closed var39 = (Closed)var37;
            Object x = var39.a();
            if (var38 instanceof Open) {
               Open var41 = (Open)var38;
               Object y = var41.a();
               var4 = this.openUpper(x, y, evidence$9);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Bound var43 = (Bound)var6._1();
         Bound var44 = (Bound)var6._2();
         if (var43 instanceof Open) {
            Open var45 = (Open)var43;
            Object x = var45.a();
            if (var44 instanceof Closed) {
               Closed var47 = (Closed)var44;
               Object y = var47.a();
               var4 = this.openLower(x, y, evidence$9);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Bound var49 = (Bound)var6._1();
         Bound var50 = (Bound)var6._2();
         if (var49 instanceof Unbound && var50 instanceof Unbound) {
            var4 = this.all(evidence$9);
            return var4;
         }
      }

      boolean var5;
      label140: {
         if (var6 != null) {
            Bound var51 = (Bound)var6._1();
            if (var51 instanceof EmptyBound) {
               var5 = true;
               break label140;
            }
         }

         if (var6 != null) {
            Bound var52 = (Bound)var6._2();
            if (var52 instanceof EmptyBound) {
               var5 = true;
               break label140;
            }
         }

         var5 = false;
      }

      if (var5) {
         throw new IllegalArgumentException("invalid empty bound");
      } else {
         throw new MatchError(var6);
      }
   }

   public Interval closed(final Object lower, final Object upper, final Order evidence$10) {
      int c = evidence$10.compare(lower, upper);
      return (Interval)(c < 0 ? Bounded$.MODULE$.apply(lower, upper, 0) : (c == 0 ? Point$.MODULE$.apply(lower) : this.empty(evidence$10)));
   }

   public Interval open(final Object lower, final Object upper, final Order evidence$11) {
      return (Interval)(evidence$11.lt(lower, upper) ? Bounded$.MODULE$.apply(lower, upper, 3) : this.empty(evidence$11));
   }

   public Interval openLower(final Object lower, final Object upper, final Order evidence$12) {
      return (Interval)(evidence$12.lt(lower, upper) ? Bounded$.MODULE$.apply(lower, upper, 1) : this.empty(evidence$12));
   }

   public Interval openUpper(final Object lower, final Object upper, final Order evidence$13) {
      return (Interval)(evidence$13.lt(lower, upper) ? Bounded$.MODULE$.apply(lower, upper, 2) : this.empty(evidence$13));
   }

   public Interval above(final Object a, final Order evidence$14) {
      return Above$.MODULE$.apply(a, 1);
   }

   public Interval below(final Object a, final Order evidence$15) {
      return Below$.MODULE$.apply(a, 2);
   }

   public Interval atOrAbove(final Object a, final Order evidence$16) {
      return Above$.MODULE$.apply(a, 0);
   }

   public Interval atOrBelow(final Object a, final Order evidence$17) {
      return Below$.MODULE$.apply(a, 0);
   }

   private Regex NullRe() {
      return NullRe;
   }

   private Regex SingleRe() {
      return SingleRe;
   }

   private Regex PairRe() {
      return PairRe;
   }

   public Interval apply(final String s) {
      Interval var2;
      if (s != null) {
         Option var5 = this.NullRe().unapplySeq(s);
         if (!var5.isEmpty() && var5.get() != null && ((List)var5.get()).lengthCompare(0) == 0) {
            var2 = this.empty((Order)Rational$.MODULE$.RationalAlgebra());
            return var2;
         }
      }

      if (s != null) {
         Option var6 = this.SingleRe().unapplySeq(s);
         if (!var6.isEmpty() && var6.get() != null && ((List)var6.get()).lengthCompare(1) == 0) {
            String x = (String)((LinearSeqOps)var6.get()).apply(0);
            var2 = this.point(Rational$.MODULE$.apply(x), (Order)Rational$.MODULE$.RationalAlgebra());
            return var2;
         }
      }

      if (s == null) {
         throw new NumberFormatException((new StringBuilder(18)).append("For input string: ").append(s).toString());
      } else {
         Option var8 = this.PairRe().unapplySeq(s);
         if (var8.isEmpty() || var8.get() == null || ((List)var8.get()).lengthCompare(4) != 0) {
            throw new NumberFormatException((new StringBuilder(18)).append("For input string: ").append(s).toString());
         } else {
            Interval var3;
            label167: {
               String left = (String)((LinearSeqOps)var8.get()).apply(0);
               String x = (String)((LinearSeqOps)var8.get()).apply(1);
               String y = (String)((LinearSeqOps)var8.get()).apply(2);
               String right = (String)((LinearSeqOps)var8.get()).apply(3);
               Tuple4 var13 = new Tuple4(left, x, y, right);
               if (var13 != null) {
                  String var14 = (String)var13._1();
                  String var15 = (String)var13._2();
                  String var16 = (String)var13._3();
                  String var17 = (String)var13._4();
                  if ("(".equals(var14) && "-∞".equals(var15) && "∞".equals(var16) && ")".equals(var17)) {
                     var3 = this.all((Order)Rational$.MODULE$.RationalAlgebra());
                     break label167;
                  }
               }

               if (var13 != null) {
                  String var18 = (String)var13._1();
                  String var19 = (String)var13._2();
                  String y = (String)var13._3();
                  String var21 = (String)var13._4();
                  if ("(".equals(var18) && "-∞".equals(var19) && ")".equals(var21)) {
                     var3 = this.below(Rational$.MODULE$.apply(y), (Order)Rational$.MODULE$.RationalAlgebra());
                     break label167;
                  }
               }

               if (var13 != null) {
                  String var22 = (String)var13._1();
                  String var23 = (String)var13._2();
                  String y = (String)var13._3();
                  String var25 = (String)var13._4();
                  if ("(".equals(var22) && "-∞".equals(var23) && "]".equals(var25)) {
                     var3 = this.atOrBelow(Rational$.MODULE$.apply(y), (Order)Rational$.MODULE$.RationalAlgebra());
                     break label167;
                  }
               }

               if (var13 != null) {
                  String var26 = (String)var13._1();
                  String x = (String)var13._2();
                  String var28 = (String)var13._3();
                  String var29 = (String)var13._4();
                  if ("(".equals(var26) && "∞".equals(var28) && ")".equals(var29)) {
                     var3 = this.above(Rational$.MODULE$.apply(x), (Order)Rational$.MODULE$.RationalAlgebra());
                     break label167;
                  }
               }

               if (var13 != null) {
                  String var30 = (String)var13._1();
                  String x = (String)var13._2();
                  String var32 = (String)var13._3();
                  String var33 = (String)var13._4();
                  if ("[".equals(var30) && "∞".equals(var32) && ")".equals(var33)) {
                     var3 = this.atOrAbove(Rational$.MODULE$.apply(x), (Order)Rational$.MODULE$.RationalAlgebra());
                     break label167;
                  }
               }

               if (var13 != null) {
                  String var34 = (String)var13._1();
                  String x = (String)var13._2();
                  String y = (String)var13._3();
                  String var37 = (String)var13._4();
                  if ("[".equals(var34) && "]".equals(var37)) {
                     var3 = this.closed(Rational$.MODULE$.apply(x), Rational$.MODULE$.apply(y), (Order)Rational$.MODULE$.RationalAlgebra());
                     break label167;
                  }
               }

               if (var13 != null) {
                  String var38 = (String)var13._1();
                  String x = (String)var13._2();
                  String y = (String)var13._3();
                  String var41 = (String)var13._4();
                  if ("(".equals(var38) && ")".equals(var41)) {
                     var3 = this.open(Rational$.MODULE$.apply(x), Rational$.MODULE$.apply(y), (Order)Rational$.MODULE$.RationalAlgebra());
                     break label167;
                  }
               }

               if (var13 != null) {
                  String var42 = (String)var13._1();
                  String x = (String)var13._2();
                  String y = (String)var13._3();
                  String var45 = (String)var13._4();
                  if ("[".equals(var42) && ")".equals(var45)) {
                     var3 = this.openUpper(Rational$.MODULE$.apply(x), Rational$.MODULE$.apply(y), (Order)Rational$.MODULE$.RationalAlgebra());
                     break label167;
                  }
               }

               if (var13 == null) {
                  throw new NumberFormatException((new StringBuilder(12)).append("Impossible: ").append(s).toString());
               }

               String var46 = (String)var13._1();
               String x = (String)var13._2();
               String y = (String)var13._3();
               String var49 = (String)var13._4();
               if (!"(".equals(var46) || !"]".equals(var49)) {
                  throw new NumberFormatException((new StringBuilder(12)).append("Impossible: ").append(s).toString());
               }

               var3 = this.openLower(Rational$.MODULE$.apply(x), Rational$.MODULE$.apply(y), (Order)Rational$.MODULE$.RationalAlgebra());
            }

            var2 = var3;
            return var2;
         }
      }
   }

   public Eq eq(final Eq evidence$18) {
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

         public boolean eqv(final Interval x, final Interval y) {
            boolean var10000;
            label23: {
               if (x == null) {
                  if (y == null) {
                     break label23;
                  }
               } else if (x.equals(y)) {
                  break label23;
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }

         public {
            Eq.$init$(this);
         }
      };
   }

   public Semiring semiring(final Ring ev, final Order o) {
      return new Semiring(ev, o) {
         private final Ring ev$4;
         private final Order o$6;

         public Semigroup multiplicative() {
            return MultiplicativeSemigroup.multiplicative$(this);
         }

         public Semigroup multiplicative$mcD$sp() {
            return MultiplicativeSemigroup.multiplicative$mcD$sp$(this);
         }

         public Semigroup multiplicative$mcF$sp() {
            return MultiplicativeSemigroup.multiplicative$mcF$sp$(this);
         }

         public Semigroup multiplicative$mcI$sp() {
            return MultiplicativeSemigroup.multiplicative$mcI$sp$(this);
         }

         public Semigroup multiplicative$mcJ$sp() {
            return MultiplicativeSemigroup.multiplicative$mcJ$sp$(this);
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

         public double pow$mcD$sp(final double a, final int n) {
            return MultiplicativeSemigroup.pow$mcD$sp$(this, a, n);
         }

         public float pow$mcF$sp(final float a, final int n) {
            return MultiplicativeSemigroup.pow$mcF$sp$(this, a, n);
         }

         public int pow$mcI$sp(final int a, final int n) {
            return MultiplicativeSemigroup.pow$mcI$sp$(this, a, n);
         }

         public long pow$mcJ$sp(final long a, final int n) {
            return MultiplicativeSemigroup.pow$mcJ$sp$(this, a, n);
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

         public Option tryProduct(final IterableOnce as) {
            return MultiplicativeSemigroup.tryProduct$(this, as);
         }

         public CommutativeMonoid additive() {
            return AdditiveCommutativeMonoid.additive$(this);
         }

         public CommutativeMonoid additive$mcD$sp() {
            return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
         }

         public CommutativeMonoid additive$mcF$sp() {
            return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
         }

         public CommutativeMonoid additive$mcI$sp() {
            return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
         }

         public CommutativeMonoid additive$mcJ$sp() {
            return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
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

         public Object sumN(final Object a, final int n) {
            return AdditiveMonoid.sumN$(this, a, n);
         }

         public double sumN$mcD$sp(final double a, final int n) {
            return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
         }

         public float sumN$mcF$sp(final float a, final int n) {
            return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
         }

         public int sumN$mcI$sp(final int a, final int n) {
            return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
         }

         public long sumN$mcJ$sp(final long a, final int n) {
            return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
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

         public Interval zero() {
            return Interval$.MODULE$.point(this.ev$4.zero(), this.o$6);
         }

         public Interval plus(final Interval x, final Interval y) {
            return x.$plus(y, this.o$6, this.ev$4);
         }

         public Interval times(final Interval x, final Interval y) {
            return x.$times((Interval)y, this.o$6, this.ev$4);
         }

         public Interval pow(final Interval x, final int k) {
            return x.pow(k, this.o$6, this.ev$4);
         }

         public {
            this.ev$4 = ev$4;
            this.o$6 = o$6;
            AdditiveSemigroup.$init$(this);
            AdditiveMonoid.$init$(this);
            AdditiveCommutativeSemigroup.$init$(this);
            AdditiveCommutativeMonoid.$init$(this);
            MultiplicativeSemigroup.$init$(this);
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Interval$.class);
   }

   private Interval$() {
   }
}
