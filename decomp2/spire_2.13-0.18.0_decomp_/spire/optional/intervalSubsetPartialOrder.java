package spire.optional;

import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import spire.math.Interval;

@ScalaSignature(
   bytes = "\u0006\u0005U<Q!\u0004\b\t\u0002M1Q!\u0006\b\t\u0002YAQ!H\u0001\u0005\u0002y1AaH\u0001\u0001A!A1i\u0001B\u0002B\u0003-A\tC\u0003\u001e\u0007\u0011\u0005q\tC\u0003M\u0007\u0011\u0005S\nC\u0003V\u0007\u0011\u0005c\u000bC\u0003Z\u0007\u0011\u0005#\fC\u0003^\u0007\u0011\u0005c\fC\u0003b\u0007\u0011\u0005#\rC\u0003f\u0007\u0011\u0005a\rC\u0003\u000e\u0003\u0011\rA.\u0001\u000ej]R,'O^1m'V\u00147/\u001a;QCJ$\u0018.\u00197Pe\u0012,'O\u0003\u0002\u0010!\u0005Aq\u000e\u001d;j_:\fGNC\u0001\u0012\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0001\"\u0001F\u0001\u000e\u00039\u0011!$\u001b8uKJ4\u0018\r\\*vEN,G\u000fU1si&\fGn\u0014:eKJ\u001c\"!A\f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t1C\u0001\u000eJ]R,'O^1m'V\u00147/\u001a;QCJ$\u0018.\u00197Pe\u0012,'/\u0006\u0002\"uM\u00191a\u0006\u0012\u0011\u0007\rz#G\u0004\u0002%Y9\u0011QE\u000b\b\u0003M%j\u0011a\n\u0006\u0003QI\ta\u0001\u0010:p_Rt\u0014\"A\t\n\u0005-\u0002\u0012aB1mO\u0016\u0014'/Y\u0005\u0003[9\nq\u0001]1dW\u0006<WM\u0003\u0002,!%\u0011\u0001'\r\u0002\r!\u0006\u0014H/[1m\u001fJ$WM\u001d\u0006\u0003[9\u00022a\r\u001c9\u001b\u0005!$BA\u001b\u0011\u0003\u0011i\u0017\r\u001e5\n\u0005]\"$\u0001C%oi\u0016\u0014h/\u00197\u0011\u0005eRD\u0002\u0001\u0003\u0006w\r\u0011\r\u0001\u0010\u0002\u0002\u0003F\u0011Q\b\u0011\t\u00031yJ!aP\r\u0003\u000f9{G\u000f[5oOB\u0011\u0001$Q\u0005\u0003\u0005f\u00111!\u00118z\u0003))g/\u001b3f]\u000e,G%\r\t\u0004G\u0015C\u0014B\u0001$2\u0005\u0015y%\u000fZ3s)\u0005AECA%L!\rQ5\u0001O\u0007\u0002\u0003!)1)\u0002a\u0002\t\u0006\u0019Q-\u001d<\u0015\u00079\u000b6\u000b\u0005\u0002\u0019\u001f&\u0011\u0001+\u0007\u0002\b\u0005>|G.Z1o\u0011\u0015\u0011f\u00011\u00013\u0003\u0005A\b\"\u0002+\u0007\u0001\u0004\u0011\u0014!A=\u0002\u000b1$X-\u001d<\u0015\u00079;\u0006\fC\u0003S\u000f\u0001\u0007!\u0007C\u0003U\u000f\u0001\u0007!'\u0001\u0002miR\u0019aj\u0017/\t\u000bIC\u0001\u0019\u0001\u001a\t\u000bQC\u0001\u0019\u0001\u001a\u0002\u000b\u001d$X-\u001d<\u0015\u00079{\u0006\rC\u0003S\u0013\u0001\u0007!\u0007C\u0003U\u0013\u0001\u0007!'\u0001\u0002hiR\u0019aj\u00193\t\u000bIS\u0001\u0019\u0001\u001a\t\u000bQS\u0001\u0019\u0001\u001a\u0002\u001dA\f'\u000f^5bY\u000e{W\u000e]1sKR\u0019qM[6\u0011\u0005aA\u0017BA5\u001a\u0005\u0019!u.\u001e2mK\")!k\u0003a\u0001e!)Ak\u0003a\u0001eU\u0011Q.\u001d\u000b\u0003]J\u00042aI\u0018p!\r\u0019d\u0007\u001d\t\u0003sE$Qa\u000f\u0007C\u0002qBqa\u001d\u0007\u0002\u0002\u0003\u000fA/\u0001\u0006fm&$WM\\2fII\u00022aI#q\u0001"
)
public final class intervalSubsetPartialOrder {
   public static PartialOrder intervalSubsetPartialOrder(final Order evidence$2) {
      return intervalSubsetPartialOrder$.MODULE$.intervalSubsetPartialOrder(evidence$2);
   }

   public static class IntervalSubsetPartialOrder implements PartialOrder {
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

      public boolean lteqv(final Interval x, final Interval y) {
         return x.isSubsetOf(y, this.evidence$1);
      }

      public boolean lt(final Interval x, final Interval y) {
         return x.isProperSubsetOf(y, this.evidence$1);
      }

      public boolean gteqv(final Interval x, final Interval y) {
         return x.isSupersetOf(y, this.evidence$1);
      }

      public boolean gt(final Interval x, final Interval y) {
         return x.isProperSupersetOf(y, this.evidence$1);
      }

      public double partialCompare(final Interval x, final Interval y) {
         return this.eqv(x, y) ? (double)0.0F : (this.lt(x, y) ? (double)-1.0F : (this.gt(x, y) ? (double)1.0F : Double.NaN));
      }

      public IntervalSubsetPartialOrder(final Order evidence$1) {
         this.evidence$1 = evidence$1;
         Eq.$init$(this);
         PartialOrder.$init$(this);
      }
   }
}
