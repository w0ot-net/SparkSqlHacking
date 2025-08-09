package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import spire.math.Interval;
import spire.math.Point;
import spire.math.interval.Bound;
import spire.math.interval.Closed;
import spire.math.interval.Open;
import spire.math.interval.ValueBound;

@ScalaSignature(
   bytes = "\u0006\u0005U<Q!\u0004\b\t\u0002M1Q!\u0006\b\t\u0002YAQ!H\u0001\u0005\u0002y1AaH\u0001\u0001A!A1i\u0001B\u0002B\u0003-A\tC\u0003\u001e\u0007\u0011\u0005q\tC\u0003M\u0007\u0011\u0005S\nC\u0003V\u0007\u0011\u0005c\u000bC\u0003Z\u0007\u0011\u0005#\fC\u0003^\u0007\u0011\u0005c\fC\u0003b\u0007\u0011\u0005#\rC\u0003f\u0007\u0011\u0005a\rC\u0003\u000e\u0003\u0011\rA.A\rj]R,'O^1m-\u0006dW/\u001a)beRL\u0017\r\\(sI\u0016\u0014(BA\b\u0011\u0003!y\u0007\u000f^5p]\u0006d'\"A\t\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011A#A\u0007\u0002\u001d\tI\u0012N\u001c;feZ\fGNV1mk\u0016\u0004\u0016M\u001d;jC2|%\u000fZ3s'\t\tq\u0003\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\u0011\u0011$\u00138uKJ4\u0018\r\u001c,bYV,\u0007+\u0019:uS\u0006dwJ\u001d3feV\u0011\u0011EO\n\u0004\u0007]\u0011\u0003cA\u00120e9\u0011A\u0005\f\b\u0003K)r!AJ\u0015\u000e\u0003\u001dR!\u0001\u000b\n\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012BA\u0016\u0011\u0003\u001d\tGnZ3ce\u0006L!!\f\u0018\u0002\u000fA\f7m[1hK*\u00111\u0006E\u0005\u0003aE\u0012A\u0002U1si&\fGn\u0014:eKJT!!\f\u0018\u0011\u0007M2\u0004(D\u00015\u0015\t)\u0004#\u0001\u0003nCRD\u0017BA\u001c5\u0005!Ie\u000e^3sm\u0006d\u0007CA\u001d;\u0019\u0001!QaO\u0002C\u0002q\u0012\u0011!Q\t\u0003{\u0001\u0003\"\u0001\u0007 \n\u0005}J\"a\u0002(pi\"Lgn\u001a\t\u00031\u0005K!AQ\r\u0003\u0007\u0005s\u00170\u0001\u0006fm&$WM\\2fIE\u00022aI#9\u0013\t1\u0015GA\u0003Pe\u0012,'\u000fF\u0001I)\tI5\nE\u0002K\u0007aj\u0011!\u0001\u0005\u0006\u0007\u0016\u0001\u001d\u0001R\u0001\u0004KF4Hc\u0001(R'B\u0011\u0001dT\u0005\u0003!f\u0011qAQ8pY\u0016\fg\u000eC\u0003S\r\u0001\u0007!'A\u0001y\u0011\u0015!f\u00011\u00013\u0003\u0005I\u0018!\u00027uKF4Hc\u0001(X1\")!k\u0002a\u0001e!)Ak\u0002a\u0001e\u0005\u0011A\u000e\u001e\u000b\u0004\u001dnc\u0006\"\u0002*\t\u0001\u0004\u0011\u0004\"\u0002+\t\u0001\u0004\u0011\u0014!B4uKF4Hc\u0001(`A\")!+\u0003a\u0001e!)A+\u0003a\u0001e\u0005\u0011q\r\u001e\u000b\u0004\u001d\u000e$\u0007\"\u0002*\u000b\u0001\u0004\u0011\u0004\"\u0002+\u000b\u0001\u0004\u0011\u0014A\u00049beRL\u0017\r\\\"p[B\f'/\u001a\u000b\u0004O*\\\u0007C\u0001\ri\u0013\tI\u0017D\u0001\u0004E_V\u0014G.\u001a\u0005\u0006%.\u0001\rA\r\u0005\u0006).\u0001\rAM\u000b\u0003[F$\"A\u001c:\u0011\u0007\rzs\u000eE\u00024mA\u0004\"!O9\u0005\u000bmb!\u0019\u0001\u001f\t\u000fMd\u0011\u0011!a\u0002i\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007\r*\u0005\u000f"
)
public final class intervalValuePartialOrder {
   public static PartialOrder intervalValuePartialOrder(final Order evidence$2) {
      return intervalValuePartialOrder$.MODULE$.intervalValuePartialOrder(evidence$2);
   }

   public static class IntervalValuePartialOrder implements PartialOrder {
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
         Tuple2 var4 = new Tuple2(x, y);
         boolean var3;
         if (var4 != null) {
            Interval var5 = (Interval)var4._1();
            Interval var6 = (Interval)var4._2();
            if (var5 instanceof Point) {
               Point var7 = (Point)var5;
               Object p1 = var7.value();
               if (var6 instanceof Point) {
                  Point var9 = (Point)var6;
                  Object p2 = var9.value();
                  var3 = this.evidence$1.eqv(p1, p2);
                  return var3;
               }
            }
         }

         var3 = false;
         return var3;
      }

      public boolean lteqv(final Interval x, final Interval y) {
         Bound var5 = x.upperBound();
         boolean var3;
         if (var5 instanceof ValueBound) {
            ValueBound var6 = (ValueBound)var5;
            Bound var7 = y.lowerBound();
            boolean var4;
            if (var7 instanceof ValueBound) {
               ValueBound var8 = (ValueBound)var7;
               var4 = this.evidence$1.lteqv(var6.a(), var8.a());
            } else {
               var4 = false;
            }

            var3 = var4;
         } else {
            var3 = false;
         }

         return var3;
      }

      public boolean lt(final Interval x, final Interval y) {
         Bound var6 = x.upperBound();
         boolean var3;
         if (var6 instanceof Open) {
            Open var7 = (Open)var6;
            Object a1 = var7.a();
            Bound var9 = y.lowerBound();
            boolean var5;
            if (var9 instanceof ValueBound) {
               ValueBound var10 = (ValueBound)var9;
               var5 = this.evidence$1.lteqv(a1, var10.a());
            } else {
               var5 = false;
            }

            var3 = var5;
         } else if (var6 instanceof Closed) {
            Closed var11 = (Closed)var6;
            Object a1 = var11.a();
            Bound var13 = y.lowerBound();
            boolean var4;
            if (var13 instanceof Closed) {
               Closed var14 = (Closed)var13;
               Object a2 = var14.a();
               var4 = this.evidence$1.lt(a1, a2);
            } else if (var13 instanceof Open) {
               Open var16 = (Open)var13;
               Object a2 = var16.a();
               var4 = this.evidence$1.lteqv(a1, a2);
            } else {
               var4 = false;
            }

            var3 = var4;
         } else {
            var3 = false;
         }

         return var3;
      }

      public boolean gteqv(final Interval x, final Interval y) {
         return this.lteqv(y, x);
      }

      public boolean gt(final Interval x, final Interval y) {
         return this.lt(y, x);
      }

      public double partialCompare(final Interval x, final Interval y) {
         return this.eqv(x, y) ? (double)0.0F : (this.lt(x, y) ? (double)-1.0F : (this.gt(x, y) ? (double)1.0F : Double.NaN));
      }

      public IntervalValuePartialOrder(final Order evidence$1) {
         this.evidence$1 = evidence$1;
         Eq.$init$(this);
         PartialOrder.$init$(this);
      }
   }
}
