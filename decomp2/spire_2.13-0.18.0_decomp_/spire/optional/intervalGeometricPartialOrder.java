package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import spire.math.Interval;
import spire.math.interval.Bound;
import spire.math.interval.Closed;
import spire.math.interval.Open;

@ScalaSignature(
   bytes = "\u0006\u0005\r<Q!\u0003\u0006\t\u0002=1Q!\u0005\u0006\t\u0002IAQ!G\u0001\u0005\u0002i1AaG\u0001\u00019!Aqh\u0001B\u0002B\u0003-\u0001\tC\u0003\u001a\u0007\u0011\u00051\tC\u0003I\u0007\u0011\u0005\u0013\nC\u0003R\u0007\u0011\u0005!\u000bC\u0003\n\u0003\u0011\r!,A\u000fj]R,'O^1m\u000f\u0016|W.\u001a;sS\u000e\u0004\u0016M\u001d;jC2|%\u000fZ3s\u0015\tYA\"\u0001\u0005paRLwN\\1m\u0015\u0005i\u0011!B:qSJ,7\u0001\u0001\t\u0003!\u0005i\u0011A\u0003\u0002\u001eS:$XM\u001d<bY\u001e+w.\\3ue&\u001c\u0007+\u0019:uS\u0006dwJ\u001d3feN\u0011\u0011a\u0005\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005y!!H%oi\u0016\u0014h/\u00197HK>lW\r\u001e:jGB\u000b'\u000f^5bY>\u0013H-\u001a:\u0016\u0005u14cA\u0002\u0014=A\u0019qd\u000b\u0018\u000f\u0005\u0001BcBA\u0011'\u001d\t\u0011S%D\u0001$\u0015\t!c\"\u0001\u0004=e>|GOP\u0005\u0002\u001b%\u0011q\u0005D\u0001\bC2<WM\u0019:b\u0013\tI#&A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u001db\u0011B\u0001\u0017.\u00051\u0001\u0016M\u001d;jC2|%\u000fZ3s\u0015\tI#\u0006E\u00020eQj\u0011\u0001\r\u0006\u0003c1\tA!\\1uQ&\u00111\u0007\r\u0002\t\u0013:$XM\u001d<bYB\u0011QG\u000e\u0007\u0001\t\u001594A1\u00019\u0005\u0005\t\u0015CA\u001d=!\t!\"(\u0003\u0002<+\t9aj\u001c;iS:<\u0007C\u0001\u000b>\u0013\tqTCA\u0002B]f\f!\"\u001a<jI\u0016t7-\u001a\u00132!\ry\u0012\tN\u0005\u0003\u00056\u0012Qa\u0014:eKJ$\u0012\u0001\u0012\u000b\u0003\u000b\u001e\u00032AR\u00025\u001b\u0005\t\u0001\"B \u0006\u0001\b\u0001\u0015aA3rmR\u0019!*T(\u0011\u0005QY\u0015B\u0001'\u0016\u0005\u001d\u0011un\u001c7fC:DQA\u0014\u0004A\u00029\n\u0011\u0001\u001f\u0005\u0006!\u001a\u0001\rAL\u0001\u0002s\u0006q\u0001/\u0019:uS\u0006d7i\\7qCJ,GcA*W1B\u0011A\u0003V\u0005\u0003+V\u0011a\u0001R8vE2,\u0007\"B,\b\u0001\u0004q\u0013!A5\t\u000be;\u0001\u0019\u0001\u0018\u0002\u0003),\"aW0\u0015\u0005q\u0003\u0007cA\u0010,;B\u0019qF\r0\u0011\u0005UzF!B\u001c\t\u0005\u0004A\u0004bB1\t\u0003\u0003\u0005\u001dAY\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004cA\u0010B=\u0002"
)
public final class intervalGeometricPartialOrder {
   public static PartialOrder intervalGeometricPartialOrder(final Order evidence$2) {
      return intervalGeometricPartialOrder$.MODULE$.intervalGeometricPartialOrder(evidence$2);
   }

   public static class IntervalGeometricPartialOrder implements PartialOrder {
      private final Order evidence$1;

      public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.partialCompare$mcZ$sp$(this, x, y);
      }

      public double partialCompare$mcB$sp(final byte x, final byte y) {
         return PartialOrder.partialCompare$mcB$sp$(this, x, y);
      }

      public double partialCompare$mcC$sp(final char x, final char y) {
         return PartialOrder.partialCompare$mcC$sp$(this, x, y);
      }

      public double partialCompare$mcD$sp(final double x, final double y) {
         return PartialOrder.partialCompare$mcD$sp$(this, x, y);
      }

      public double partialCompare$mcF$sp(final float x, final float y) {
         return PartialOrder.partialCompare$mcF$sp$(this, x, y);
      }

      public double partialCompare$mcI$sp(final int x, final int y) {
         return PartialOrder.partialCompare$mcI$sp$(this, x, y);
      }

      public double partialCompare$mcJ$sp(final long x, final long y) {
         return PartialOrder.partialCompare$mcJ$sp$(this, x, y);
      }

      public double partialCompare$mcS$sp(final short x, final short y) {
         return PartialOrder.partialCompare$mcS$sp$(this, x, y);
      }

      public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.partialCompare$mcV$sp$(this, x, y);
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

      public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.eqv$mcZ$sp$(this, x, y);
      }

      public boolean eqv$mcB$sp(final byte x, final byte y) {
         return PartialOrder.eqv$mcB$sp$(this, x, y);
      }

      public boolean eqv$mcC$sp(final char x, final char y) {
         return PartialOrder.eqv$mcC$sp$(this, x, y);
      }

      public boolean eqv$mcD$sp(final double x, final double y) {
         return PartialOrder.eqv$mcD$sp$(this, x, y);
      }

      public boolean eqv$mcF$sp(final float x, final float y) {
         return PartialOrder.eqv$mcF$sp$(this, x, y);
      }

      public boolean eqv$mcI$sp(final int x, final int y) {
         return PartialOrder.eqv$mcI$sp$(this, x, y);
      }

      public boolean eqv$mcJ$sp(final long x, final long y) {
         return PartialOrder.eqv$mcJ$sp$(this, x, y);
      }

      public boolean eqv$mcS$sp(final short x, final short y) {
         return PartialOrder.eqv$mcS$sp$(this, x, y);
      }

      public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.eqv$mcV$sp$(this, x, y);
      }

      public boolean lteqv(final Object x, final Object y) {
         return PartialOrder.lteqv$(this, x, y);
      }

      public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.lteqv$mcZ$sp$(this, x, y);
      }

      public boolean lteqv$mcB$sp(final byte x, final byte y) {
         return PartialOrder.lteqv$mcB$sp$(this, x, y);
      }

      public boolean lteqv$mcC$sp(final char x, final char y) {
         return PartialOrder.lteqv$mcC$sp$(this, x, y);
      }

      public boolean lteqv$mcD$sp(final double x, final double y) {
         return PartialOrder.lteqv$mcD$sp$(this, x, y);
      }

      public boolean lteqv$mcF$sp(final float x, final float y) {
         return PartialOrder.lteqv$mcF$sp$(this, x, y);
      }

      public boolean lteqv$mcI$sp(final int x, final int y) {
         return PartialOrder.lteqv$mcI$sp$(this, x, y);
      }

      public boolean lteqv$mcJ$sp(final long x, final long y) {
         return PartialOrder.lteqv$mcJ$sp$(this, x, y);
      }

      public boolean lteqv$mcS$sp(final short x, final short y) {
         return PartialOrder.lteqv$mcS$sp$(this, x, y);
      }

      public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.lteqv$mcV$sp$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return PartialOrder.lt$(this, x, y);
      }

      public boolean lt$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.lt$mcZ$sp$(this, x, y);
      }

      public boolean lt$mcB$sp(final byte x, final byte y) {
         return PartialOrder.lt$mcB$sp$(this, x, y);
      }

      public boolean lt$mcC$sp(final char x, final char y) {
         return PartialOrder.lt$mcC$sp$(this, x, y);
      }

      public boolean lt$mcD$sp(final double x, final double y) {
         return PartialOrder.lt$mcD$sp$(this, x, y);
      }

      public boolean lt$mcF$sp(final float x, final float y) {
         return PartialOrder.lt$mcF$sp$(this, x, y);
      }

      public boolean lt$mcI$sp(final int x, final int y) {
         return PartialOrder.lt$mcI$sp$(this, x, y);
      }

      public boolean lt$mcJ$sp(final long x, final long y) {
         return PartialOrder.lt$mcJ$sp$(this, x, y);
      }

      public boolean lt$mcS$sp(final short x, final short y) {
         return PartialOrder.lt$mcS$sp$(this, x, y);
      }

      public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.lt$mcV$sp$(this, x, y);
      }

      public boolean gteqv(final Object x, final Object y) {
         return PartialOrder.gteqv$(this, x, y);
      }

      public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.gteqv$mcZ$sp$(this, x, y);
      }

      public boolean gteqv$mcB$sp(final byte x, final byte y) {
         return PartialOrder.gteqv$mcB$sp$(this, x, y);
      }

      public boolean gteqv$mcC$sp(final char x, final char y) {
         return PartialOrder.gteqv$mcC$sp$(this, x, y);
      }

      public boolean gteqv$mcD$sp(final double x, final double y) {
         return PartialOrder.gteqv$mcD$sp$(this, x, y);
      }

      public boolean gteqv$mcF$sp(final float x, final float y) {
         return PartialOrder.gteqv$mcF$sp$(this, x, y);
      }

      public boolean gteqv$mcI$sp(final int x, final int y) {
         return PartialOrder.gteqv$mcI$sp$(this, x, y);
      }

      public boolean gteqv$mcJ$sp(final long x, final long y) {
         return PartialOrder.gteqv$mcJ$sp$(this, x, y);
      }

      public boolean gteqv$mcS$sp(final short x, final short y) {
         return PartialOrder.gteqv$mcS$sp$(this, x, y);
      }

      public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.gteqv$mcV$sp$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return PartialOrder.gt$(this, x, y);
      }

      public boolean gt$mcZ$sp(final boolean x, final boolean y) {
         return PartialOrder.gt$mcZ$sp$(this, x, y);
      }

      public boolean gt$mcB$sp(final byte x, final byte y) {
         return PartialOrder.gt$mcB$sp$(this, x, y);
      }

      public boolean gt$mcC$sp(final char x, final char y) {
         return PartialOrder.gt$mcC$sp$(this, x, y);
      }

      public boolean gt$mcD$sp(final double x, final double y) {
         return PartialOrder.gt$mcD$sp$(this, x, y);
      }

      public boolean gt$mcF$sp(final float x, final float y) {
         return PartialOrder.gt$mcF$sp$(this, x, y);
      }

      public boolean gt$mcI$sp(final int x, final int y) {
         return PartialOrder.gt$mcI$sp$(this, x, y);
      }

      public boolean gt$mcJ$sp(final long x, final long y) {
         return PartialOrder.gt$mcJ$sp$(this, x, y);
      }

      public boolean gt$mcS$sp(final short x, final short y) {
         return PartialOrder.gt$mcS$sp$(this, x, y);
      }

      public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
         return PartialOrder.gt$mcV$sp$(this, x, y);
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

      public double partialCompare(final Interval i, final Interval j) {
         if (this.eqv(i, j)) {
            return (double)0.0F;
         } else if (!i.isEmpty() && !j.isEmpty()) {
            Tuple2 var5 = new Tuple2(i.upperBound(), j.lowerBound());
            if (var5 != null) {
               Bound var6 = (Bound)var5._1();
               Bound var7 = (Bound)var5._2();
               if (var6 instanceof Open) {
                  Open var8 = (Open)var6;
                  Object x = var8.a();
                  if (var7 instanceof Open) {
                     Open var10 = (Open)var7;
                     Object y = var10.a();
                     if (this.evidence$1.lteqv(x, y)) {
                        return (double)-1.0F;
                     }
                  }
               }
            }

            if (var5 != null) {
               Bound var12 = (Bound)var5._1();
               Bound var13 = (Bound)var5._2();
               if (var12 instanceof Open) {
                  Open var14 = (Open)var12;
                  Object x = var14.a();
                  if (var13 instanceof Closed) {
                     Closed var16 = (Closed)var13;
                     Object y = var16.a();
                     if (this.evidence$1.lteqv(x, y)) {
                        return (double)-1.0F;
                     }
                  }
               }
            }

            if (var5 != null) {
               Bound var18 = (Bound)var5._1();
               Bound var19 = (Bound)var5._2();
               if (var18 instanceof Closed) {
                  Closed var20 = (Closed)var18;
                  Object x = var20.a();
                  if (var19 instanceof Open) {
                     Open var22 = (Open)var19;
                     Object y = var22.a();
                     if (this.evidence$1.lteqv(x, y)) {
                        return (double)-1.0F;
                     }
                  }
               }
            }

            if (var5 != null) {
               Bound var24 = (Bound)var5._1();
               Bound var25 = (Bound)var5._2();
               if (var24 instanceof Closed) {
                  Closed var26 = (Closed)var24;
                  Object x = var26.a();
                  if (var25 instanceof Closed) {
                     Closed var28 = (Closed)var25;
                     Object y = var28.a();
                     if (this.evidence$1.lt(x, y)) {
                        return (double)-1.0F;
                     }
                  }
               }
            }

            BoxedUnit var4 = BoxedUnit.UNIT;
            Tuple2 var30 = new Tuple2(i.lowerBound(), j.upperBound());
            if (var30 != null) {
               Bound var31 = (Bound)var30._1();
               Bound var32 = (Bound)var30._2();
               if (var31 instanceof Open) {
                  Open var33 = (Open)var31;
                  Object x = var33.a();
                  if (var32 instanceof Open) {
                     Open var35 = (Open)var32;
                     Object y = var35.a();
                     if (this.evidence$1.gteqv(x, y)) {
                        return (double)1.0F;
                     }
                  }
               }
            }

            if (var30 != null) {
               Bound var37 = (Bound)var30._1();
               Bound var38 = (Bound)var30._2();
               if (var37 instanceof Open) {
                  Open var39 = (Open)var37;
                  Object x = var39.a();
                  if (var38 instanceof Closed) {
                     Closed var41 = (Closed)var38;
                     Object y = var41.a();
                     if (this.evidence$1.gteqv(x, y)) {
                        return (double)1.0F;
                     }
                  }
               }
            }

            if (var30 != null) {
               Bound var43 = (Bound)var30._1();
               Bound var44 = (Bound)var30._2();
               if (var43 instanceof Closed) {
                  Closed var45 = (Closed)var43;
                  Object x = var45.a();
                  if (var44 instanceof Open) {
                     Open var47 = (Open)var44;
                     Object y = var47.a();
                     if (this.evidence$1.gteqv(x, y)) {
                        return (double)1.0F;
                     }
                  }
               }
            }

            if (var30 != null) {
               Bound var49 = (Bound)var30._1();
               Bound var50 = (Bound)var30._2();
               if (var49 instanceof Closed) {
                  Closed var51 = (Closed)var49;
                  Object x = var51.a();
                  if (var50 instanceof Closed) {
                     Closed var53 = (Closed)var50;
                     Object y = var53.a();
                     if (this.evidence$1.gt(x, y)) {
                        return (double)1.0F;
                     }
                  }
               }
            }

            BoxedUnit var3 = BoxedUnit.UNIT;
            return Double.NaN;
         } else {
            return Double.NaN;
         }
      }

      public IntervalGeometricPartialOrder(final Order evidence$1) {
         this.evidence$1 = evidence$1;
         Eq.$init$(this);
         PartialOrder.$init$(this);
      }
   }
}
