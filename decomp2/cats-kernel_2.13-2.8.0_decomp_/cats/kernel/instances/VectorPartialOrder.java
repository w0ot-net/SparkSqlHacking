package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import scala.Option;
import scala.collection.immutable.Vector;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013A\u0001B\u0003\u0001\u0019!Aq\u0006\u0001B\u0001B\u0003-\u0001\u0007C\u00032\u0001\u0011\u0005!\u0007C\u00038\u0001\u0011\u0005\u0001H\u0001\nWK\u000e$xN\u001d)beRL\u0017\r\\(sI\u0016\u0014(B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u0005513c\u0001\u0001\u000f)A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u00042!\u0006\f\u0019\u001b\u00059\u0011BA\f\b\u00051\u0001\u0016M\u001d;jC2|%\u000fZ3s!\rI\u0012\u0005\n\b\u00035}q!a\u0007\u0010\u000e\u0003qQ!!H\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012B\u0001\u0011\u0011\u0003\u001d\u0001\u0018mY6bO\u0016L!AI\u0012\u0003\rY+7\r^8s\u0015\t\u0001\u0003\u0003\u0005\u0002&M1\u0001A!B\u0014\u0001\u0005\u0004A#!A!\u0012\u0005%b\u0003CA\b+\u0013\tY\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=i\u0013B\u0001\u0018\u0011\u0005\r\te._\u0001\u0003KZ\u00042!\u0006\f%\u0003\u0019a\u0014N\\5u}Q\t1\u0007\u0006\u00025mA\u0019Q\u0007\u0001\u0013\u000e\u0003\u0015AQa\f\u0002A\u0004A\na\u0002]1si&\fGnQ8na\u0006\u0014X\rF\u0002:yy\u0002\"a\u0004\u001e\n\u0005m\u0002\"A\u0002#pk\ndW\rC\u0003>\u0007\u0001\u0007\u0001$\u0001\u0002yg\")qh\u0001a\u00011\u0005\u0011\u0011p\u001d"
)
public class VectorPartialOrder implements PartialOrder {
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

   public double partialCompare(final Vector xs, final Vector ys) {
      return xs == ys ? (double)0.0F : StaticMethods$.MODULE$.iteratorPartialCompare(xs.iterator(), ys.iterator(), this.ev);
   }

   public VectorPartialOrder(final PartialOrder ev) {
      this.ev = ev;
      Eq.$init$(this);
      PartialOrder.$init$(this);
   }
}
