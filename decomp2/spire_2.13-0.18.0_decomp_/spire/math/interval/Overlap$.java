package spire.math.interval;

import cats.kernel.Eq;
import cats.kernel.Order;
import java.io.Serializable;
import scala.Tuple2;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import spire.algebra.package$;
import spire.math.Bounded;
import spire.math.Empty;
import spire.math.Interval;
import spire.math.Interval$;
import spire.math.Point;

public final class Overlap$ implements Serializable {
   public static final Overlap$ MODULE$ = new Overlap$();

   public Overlap apply(final Interval lhs, final Interval rhs, final Order evidence$1) {
      Object var10000;
      if (Interval$.MODULE$.eq(evidence$1).eqv(lhs, rhs)) {
         var10000 = Overlap.Equal$.MODULE$.apply();
      } else if (rhs.isSupersetOf(lhs, evidence$1)) {
         var10000 = Overlap.Subset$.MODULE$.apply(lhs, rhs);
      } else if (lhs.isSupersetOf(rhs, evidence$1)) {
         var10000 = Overlap.Subset$.MODULE$.apply(rhs, lhs);
      } else {
         Interval var5 = lhs.intersect(rhs, evidence$1);
         Object var4;
         if (var5 instanceof Bounded) {
            Bounded var6 = (Bounded)var5;
            var4 = lessAndOverlaps$1(var6.lowerBound(), evidence$1, lhs, rhs);
         } else if (var5 instanceof Point) {
            Point var7 = (Point)var5;
            var4 = lessAndOverlaps$1(var7.lowerBound(), evidence$1, lhs, rhs);
         } else {
            if (!(var5 instanceof Empty)) {
               throw new Exception("impossible");
            }

            var4 = Interval$.MODULE$.fromBounds(lhs.lowerBound(), rhs.upperBound(), evidence$1).isEmpty() ? Overlap.Disjoint$.MODULE$.apply(rhs, lhs) : Overlap.Disjoint$.MODULE$.apply(lhs, rhs);
         }

         var10000 = var4;
      }

      return (Overlap)var10000;
   }

   public Eq eqOverlap(final Eq evidence$2) {
      return new Eq(evidence$2) {
         private final Eq eq;

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

         private Eq eq() {
            return this.eq;
         }

         public boolean eqv(final Overlap x, final Overlap y) {
            Tuple2 var4 = new Tuple2(x, y);
            boolean var3;
            if (var4 != null) {
               Overlap var5 = (Overlap)var4._1();
               Overlap var6 = (Overlap)var4._2();
               if (var5 instanceof Overlap.Equal && var6 instanceof Overlap.Equal) {
                  var3 = true;
                  return var3;
               }
            }

            if (var4 != null) {
               Overlap var7 = (Overlap)var4._1();
               Overlap var8 = (Overlap)var4._2();
               if (var7 instanceof Overlap.Disjoint) {
                  Overlap.Disjoint var9 = (Overlap.Disjoint)var7;
                  Interval x1 = var9.lower();
                  Interval y1 = var9.upper();
                  if (var8 instanceof Overlap.Disjoint) {
                     Overlap.Disjoint var12 = (Overlap.Disjoint)var8;
                     Interval x2 = var12.lower();
                     Interval y2 = var12.upper();
                     var3 = this.eq().eqv(x1, x2) && this.eq().eqv(y1, y2);
                     return var3;
                  }
               }
            }

            if (var4 != null) {
               Overlap var15 = (Overlap)var4._1();
               Overlap var16 = (Overlap)var4._2();
               if (var15 instanceof Overlap.PartialOverlap) {
                  Overlap.PartialOverlap var17 = (Overlap.PartialOverlap)var15;
                  Interval x1 = var17.lower();
                  Interval y1 = var17.upper();
                  if (var16 instanceof Overlap.PartialOverlap) {
                     Overlap.PartialOverlap var20 = (Overlap.PartialOverlap)var16;
                     Interval x2 = var20.lower();
                     Interval y2 = var20.upper();
                     var3 = this.eq().eqv(x1, x2) && this.eq().eqv(y1, y2);
                     return var3;
                  }
               }
            }

            if (var4 != null) {
               Overlap var23 = (Overlap)var4._1();
               Overlap var24 = (Overlap)var4._2();
               if (var23 instanceof Overlap.Subset) {
                  Overlap.Subset var25 = (Overlap.Subset)var23;
                  Interval x1 = var25.inner();
                  Interval y1 = var25.outer();
                  if (var24 instanceof Overlap.Subset) {
                     Overlap.Subset var28 = (Overlap.Subset)var24;
                     Interval x2 = var28.inner();
                     Interval y2 = var28.outer();
                     var3 = this.eq().eqv(x1, x2) && this.eq().eqv(y1, y2);
                     return var3;
                  }
               }
            }

            var3 = false;
            return var3;
         }

         public {
            Eq.$init$(this);
            this.eq = package$.MODULE$.Eq().apply(Interval$.MODULE$.eq(evidence$2$1));
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Overlap$.class);
   }

   private static final Overlap lessAndOverlaps$1(final Bound intersectionLowerBound, final Order evidence$1$1, final Interval lhs$1, final Interval rhs$1) {
      return Bound$.MODULE$.eq(evidence$1$1).eqv(lhs$1.lowerBound(), intersectionLowerBound) ? Overlap.PartialOverlap$.MODULE$.apply(lhs$1, rhs$1) : Overlap.PartialOverlap$.MODULE$.apply(rhs$1, lhs$1);
   }

   private Overlap$() {
   }
}
