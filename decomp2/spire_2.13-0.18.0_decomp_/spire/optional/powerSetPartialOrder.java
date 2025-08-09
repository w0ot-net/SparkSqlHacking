package spire.optional;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u00055<Q\u0001D\u0007\t\u0002I1Q\u0001F\u0007\t\u0002UAQ\u0001H\u0001\u0005\u0002u1AAH\u0001\u0001?!)Ad\u0001C\u0001\t\")qi\u0001C!\u0011\")\u0001k\u0001C!#\")Ak\u0001C!+\")\u0001l\u0001C!3\")Al\u0001C!;\")\u0001m\u0001C\u0001C\")A\"\u0001C\u0002O\u0006!\u0002o\\<feN+G\u000fU1si&\fGn\u0014:eKJT!AD\b\u0002\u0011=\u0004H/[8oC2T\u0011\u0001E\u0001\u0006gBL'/Z\u0002\u0001!\t\u0019\u0012!D\u0001\u000e\u0005Q\u0001xn^3s'\u0016$\b+\u0019:uS\u0006dwJ\u001d3feN\u0011\u0011A\u0006\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\u0011\"\u0001\u0006)po\u0016\u00148+\u001a;QCJ$\u0018.\u00197Pe\u0012,'/\u0006\u0002!wM\u00191AF\u0011\u0011\u0007\tr\u0013G\u0004\u0002$W9\u0011A%\u000b\b\u0003K!j\u0011A\n\u0006\u0003OE\ta\u0001\u0010:p_Rt\u0014\"\u0001\t\n\u0005)z\u0011aB1mO\u0016\u0014'/Y\u0005\u0003Y5\nq\u0001]1dW\u0006<WM\u0003\u0002+\u001f%\u0011q\u0006\r\u0002\r!\u0006\u0014H/[1m\u001fJ$WM\u001d\u0006\u0003Y5\u00022A\r\u001c:\u001d\t\u0019D\u0007\u0005\u0002&1%\u0011Q\u0007G\u0001\u0007!J,G-\u001a4\n\u0005]B$aA*fi*\u0011Q\u0007\u0007\t\u0003umb\u0001\u0001B\u0003=\u0007\t\u0007QHA\u0001B#\tq\u0014\t\u0005\u0002\u0018\u007f%\u0011\u0001\t\u0007\u0002\b\u001d>$\b.\u001b8h!\t9\")\u0003\u0002D1\t\u0019\u0011I\\=\u0015\u0003\u0015\u00032AR\u0002:\u001b\u0005\t\u0011aA3rmR\u0019\u0011\n\u0014(\u0011\u0005]Q\u0015BA&\u0019\u0005\u001d\u0011un\u001c7fC:DQ!T\u0003A\u0002E\n\u0011\u0001\u001f\u0005\u0006\u001f\u0016\u0001\r!M\u0001\u0002s\u0006)A\u000e^3rmR\u0019\u0011JU*\t\u000b53\u0001\u0019A\u0019\t\u000b=3\u0001\u0019A\u0019\u0002\u00051$HcA%W/\")Qj\u0002a\u0001c!)qj\u0002a\u0001c\u0005)q\r^3rmR\u0019\u0011JW.\t\u000b5C\u0001\u0019A\u0019\t\u000b=C\u0001\u0019A\u0019\u0002\u0005\u001d$HcA%_?\")Q*\u0003a\u0001c!)q*\u0003a\u0001c\u0005q\u0001/\u0019:uS\u0006d7i\\7qCJ,Gc\u00012fMB\u0011qcY\u0005\u0003Ib\u0011a\u0001R8vE2,\u0007\"B'\u000b\u0001\u0004\t\u0004\"B(\u000b\u0001\u0004\tTC\u00015m+\u0005I\u0007c\u0001\u0012/UB\u0019!GN6\u0011\u0005ibG!\u0002\u001f\f\u0005\u0004i\u0004"
)
public final class powerSetPartialOrder {
   public static PartialOrder powerSetPartialOrder() {
      return powerSetPartialOrder$.MODULE$.powerSetPartialOrder();
   }

   public static class PowerSetPartialOrder implements PartialOrder {
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

      public boolean eqv(final Set x, final Set y) {
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

      public boolean lteqv(final Set x, final Set y) {
         return x.subsetOf(y);
      }

      public boolean lt(final Set x, final Set y) {
         boolean var10000;
         label25: {
            if (x.subsetOf(y)) {
               if (x == null) {
                  if (y != null) {
                     break label25;
                  }
               } else if (!x.equals(y)) {
                  break label25;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public boolean gteqv(final Set x, final Set y) {
         return y.subsetOf(x);
      }

      public boolean gt(final Set x, final Set y) {
         boolean var10000;
         label25: {
            if (y.subsetOf(x)) {
               if (x == null) {
                  if (y != null) {
                     break label25;
                  }
               } else if (!x.equals(y)) {
                  break label25;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public double partialCompare(final Set x, final Set y) {
         return this.eqv(x, y) ? (double)0.0F : (this.lt(x, y) ? (double)-1.0F : (this.gt(x, y) ? (double)1.0F : Double.NaN));
      }

      public PowerSetPartialOrder() {
         Eq.$init$(this);
         PartialOrder.$init$(this);
      }
   }
}
