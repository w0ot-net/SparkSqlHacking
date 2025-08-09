package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.collection.immutable.Queue;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005q2A\u0001B\u0003\u0001\u0019!A1\u0006\u0001B\u0001B\u0003-A\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u00034\u0001\u0011\u0005AGA\tRk\u0016,X\rU1si&\fGn\u0014:eKJT!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u001b\t\u001a2\u0001\u0001\b\u0015!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fMB\u0019QC\u0006\r\u000e\u0003\u001dI!aF\u0004\u0003\u0019A\u000b'\u000f^5bY>\u0013H-\u001a:\u0011\u0007eq\u0002%D\u0001\u001b\u0015\tYB$A\u0005j[6,H/\u00192mK*\u0011Q\u0004E\u0001\u000bG>dG.Z2uS>t\u0017BA\u0010\u001b\u0005\u0015\tV/Z;f!\t\t#\u0005\u0004\u0001\u0005\u000b\r\u0002!\u0019\u0001\u0013\u0003\u0003\u0005\u000b\"!\n\u0015\u0011\u0005=1\u0013BA\u0014\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u0015\n\u0005)\u0002\"aA!os\u0006\u0011QM\u001e\t\u0004+Y\u0001\u0013A\u0002\u001fj]&$h\bF\u00010)\t\u0001$\u0007E\u00022\u0001\u0001j\u0011!\u0002\u0005\u0006W\t\u0001\u001d\u0001L\u0001\u000fa\u0006\u0014H/[1m\u0007>l\u0007/\u0019:f)\r)\u0004H\u000f\t\u0003\u001fYJ!a\u000e\t\u0003\r\u0011{WO\u00197f\u0011\u0015I4\u00011\u0001\u0019\u0003\tA8\u000fC\u0003<\u0007\u0001\u0007\u0001$\u0001\u0002zg\u0002"
)
public class QueuePartialOrder implements PartialOrder {
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

   public double partialCompare(final Queue xs, final Queue ys) {
      return xs == ys ? (double)0.0F : StaticMethods$.MODULE$.iteratorPartialCompare(xs.iterator(), ys.iterator(), this.ev);
   }

   public QueuePartialOrder(final PartialOrder ev) {
      this.ev = ev;
      Eq.$init$(this);
      PartialOrder.$init$(this);
   }
}
