package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.MatchError;
import scala.Option;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013A\u0001B\u0003\u0001\u0019!Aq\u0006\u0001B\u0001B\u0003-\u0001\u0007C\u00032\u0001\u0011\u0005!\u0007C\u00038\u0001\u0011\u0005\u0001H\u0001\tMSN$\b+\u0019:uS\u0006dwJ\u001d3fe*\u0011aaB\u0001\nS:\u001cH/\u00198dKNT!\u0001C\u0005\u0002\r-,'O\\3m\u0015\u0005Q\u0011\u0001B2biN\u001c\u0001!\u0006\u0002\u000eMM\u0019\u0001A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\r)b\u0003G\u0007\u0002\u000f%\u0011qc\u0002\u0002\r!\u0006\u0014H/[1m\u001fJ$WM\u001d\t\u00043\u0005\"cB\u0001\u000e \u001d\tYb$D\u0001\u001d\u0015\ti2\"\u0001\u0004=e>|GOP\u0005\u0002#%\u0011\u0001\u0005E\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00113E\u0001\u0003MSN$(B\u0001\u0011\u0011!\t)c\u0005\u0004\u0001\u0005\u000b\u001d\u0002!\u0019\u0001\u0015\u0003\u0003\u0005\u000b\"!\u000b\u0017\u0011\u0005=Q\u0013BA\u0016\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u0017\n\u00059\u0002\"aA!os\u0006\u0011QM\u001e\t\u0004+Y!\u0013A\u0002\u001fj]&$h\bF\u00014)\t!d\u0007E\u00026\u0001\u0011j\u0011!\u0002\u0005\u0006_\t\u0001\u001d\u0001M\u0001\u000fa\u0006\u0014H/[1m\u0007>l\u0007/\u0019:f)\rIDH\u0010\t\u0003\u001fiJ!a\u000f\t\u0003\r\u0011{WO\u00197f\u0011\u0015i4\u00011\u0001\u0019\u0003\tA8\u000fC\u0003@\u0007\u0001\u0007\u0001$\u0001\u0002zg\u0002"
)
public class ListPartialOrder implements PartialOrder {
   private final PartialOrder ev;

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

   public boolean eqv(final Object x, final Object y) {
      return PartialOrder.eqv$(this, x, y);
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

   public double partialCompare(final List xs, final List ys) {
      return xs == ys ? (double)0.0F : this.loop$2(xs, ys);
   }

   private final double loop$2(final List xs, final List ys) {
      double var4;
      while(true) {
         Nil var10000 = .MODULE$.Nil();
         if (var10000 == null) {
            if (xs == null) {
               break;
            }
         } else if (var10000.equals(xs)) {
            break;
         }

         if (!(xs instanceof scala.collection.immutable..colon.colon)) {
            throw new MatchError(xs);
         }

         double var6;
         label44: {
            label58: {
               scala.collection.immutable..colon.colon var10 = (scala.collection.immutable..colon.colon)xs;
               Object x = var10.head();
               List xs = var10.next$access$1();
               var10000 = .MODULE$.Nil();
               if (var10000 == null) {
                  if (ys == null) {
                     break label58;
                  }
               } else if (var10000.equals(ys)) {
                  break label58;
               }

               if (!(ys instanceof scala.collection.immutable..colon.colon)) {
                  throw new MatchError(ys);
               }

               scala.collection.immutable..colon.colon var15 = (scala.collection.immutable..colon.colon)ys;
               Object y = var15.head();
               List ys = var15.next$access$1();
               double n = this.ev.partialCompare(x, y);
               if (n == (double)0.0F) {
                  ys = ys;
                  xs = xs;
                  continue;
               }

               var6 = n;
               break label44;
            }

            var6 = (double)1.0F;
         }

         var4 = var6;
         return var4;
      }

      var4 = ys.isEmpty() ? (double)0.0F : (double)-1.0F;
      return var4;
   }

   public ListPartialOrder(final PartialOrder ev) {
      this.ev = ev;
      Eq.$init$(this);
      PartialOrder.$init$(this);
   }
}
