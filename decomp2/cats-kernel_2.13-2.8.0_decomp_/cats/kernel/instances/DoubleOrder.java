package cats.kernel.instances;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Hash$mcD$sp;
import cats.kernel.Order;
import cats.kernel.Order$mcD$sp;
import cats.kernel.PartialOrder;
import cats.kernel.PartialOrder$mcD$sp;
import scala.Option;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005a3A\u0001D\u0007\u0001)!)Q\u0005\u0001C\u0001M!)\u0011\u0006\u0001C\u0001U!)\u0001\u0007\u0001C\u0001c!)Q\u0007\u0001C!m!)A\b\u0001C!{!)\u0001\t\u0001C!\u0003\")A\t\u0001C!\u000b\")\u0001\n\u0001C!\u0013\")A\n\u0001C!\u001b\")\u0001\u000b\u0001C!#\")A\u000b\u0001C!+\nYAi\\;cY\u0016|%\u000fZ3s\u0015\tqq\"A\u0005j]N$\u0018M\\2fg*\u0011\u0001#E\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003I\tAaY1ug\u000e\u00011\u0003\u0002\u0001\u00167\t\u0002\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007c\u0001\u000f\u001e?5\tq\"\u0003\u0002\u001f\u001f\t)qJ\u001d3feB\u0011a\u0003I\u0005\u0003C]\u0011a\u0001R8vE2,\u0007c\u0001\u000f$?%\u0011Ae\u0004\u0002\u0005\u0011\u0006\u001c\b.\u0001\u0004=S:LGO\u0010\u000b\u0002OA\u0011\u0001\u0006A\u0007\u0002\u001b\u0005!\u0001.Y:i)\tYc\u0006\u0005\u0002\u0017Y%\u0011Qf\u0006\u0002\u0004\u0013:$\b\"B\u0018\u0003\u0001\u0004y\u0012!\u0001=\u0002\u000f\r|W\u000e]1sKR\u00191FM\u001a\t\u000b=\u001a\u0001\u0019A\u0010\t\u000bQ\u001a\u0001\u0019A\u0010\u0002\u0003e\f1!Z9w)\r9$h\u000f\t\u0003-aJ!!O\f\u0003\u000f\t{w\u000e\\3b]\")q\u0006\u0002a\u0001?!)A\u0007\u0002a\u0001?\u0005!a.Z9w)\r9dh\u0010\u0005\u0006_\u0015\u0001\ra\b\u0005\u0006i\u0015\u0001\raH\u0001\u0003OR$2a\u000e\"D\u0011\u0015yc\u00011\u0001 \u0011\u0015!d\u00011\u0001 \u0003\u00159G/Z9w)\r9di\u0012\u0005\u0006_\u001d\u0001\ra\b\u0005\u0006i\u001d\u0001\raH\u0001\u0003YR$2a\u000e&L\u0011\u0015y\u0003\u00021\u0001 \u0011\u0015!\u0004\u00021\u0001 \u0003\u0015aG/Z9w)\r9dj\u0014\u0005\u0006_%\u0001\ra\b\u0005\u0006i%\u0001\raH\u0001\u0004[&tGcA\u0010S'\")qF\u0003a\u0001?!)AG\u0003a\u0001?\u0005\u0019Q.\u0019=\u0015\u0007}1v\u000bC\u00030\u0017\u0001\u0007q\u0004C\u00035\u0017\u0001\u0007q\u0004"
)
public class DoubleOrder implements Order$mcD$sp, Hash$mcD$sp {
   public int hash$mcZ$sp(final boolean x) {
      return Hash.hash$mcZ$sp$(this, x);
   }

   public int hash$mcB$sp(final byte x) {
      return Hash.hash$mcB$sp$(this, x);
   }

   public int hash$mcC$sp(final char x) {
      return Hash.hash$mcC$sp$(this, x);
   }

   public int hash$mcF$sp(final float x) {
      return Hash.hash$mcF$sp$(this, x);
   }

   public int hash$mcI$sp(final int x) {
      return Hash.hash$mcI$sp$(this, x);
   }

   public int hash$mcJ$sp(final long x) {
      return Hash.hash$mcJ$sp$(this, x);
   }

   public int hash$mcS$sp(final short x) {
      return Hash.hash$mcS$sp$(this, x);
   }

   public int hash$mcV$sp(final BoxedUnit x) {
      return Hash.hash$mcV$sp$(this, x);
   }

   public Comparison comparison(final double x, final double y) {
      return Order$mcD$sp.comparison$(this, x, y);
   }

   public Comparison comparison$mcD$sp(final double x, final double y) {
      return Order$mcD$sp.comparison$mcD$sp$(this, x, y);
   }

   public double partialCompare(final double x, final double y) {
      return Order$mcD$sp.partialCompare$(this, x, y);
   }

   public double partialCompare$mcD$sp(final double x, final double y) {
      return Order$mcD$sp.partialCompare$mcD$sp$(this, x, y);
   }

   public Option partialComparison(final double x, final double y) {
      return PartialOrder$mcD$sp.partialComparison$(this, x, y);
   }

   public Option partialComparison$mcD$sp(final double x, final double y) {
      return PartialOrder$mcD$sp.partialComparison$mcD$sp$(this, x, y);
   }

   public Option tryCompare(final double x, final double y) {
      return PartialOrder$mcD$sp.tryCompare$(this, x, y);
   }

   public Option tryCompare$mcD$sp(final double x, final double y) {
      return PartialOrder$mcD$sp.tryCompare$mcD$sp$(this, x, y);
   }

   public Option pmin(final double x, final double y) {
      return PartialOrder$mcD$sp.pmin$(this, x, y);
   }

   public Option pmin$mcD$sp(final double x, final double y) {
      return PartialOrder$mcD$sp.pmin$mcD$sp$(this, x, y);
   }

   public Option pmax(final double x, final double y) {
      return PartialOrder$mcD$sp.pmax$(this, x, y);
   }

   public Option pmax$mcD$sp(final double x, final double y) {
      return PartialOrder$mcD$sp.pmax$mcD$sp$(this, x, y);
   }

   public int compare$mcZ$sp(final boolean x, final boolean y) {
      return Order.compare$mcZ$sp$(this, x, y);
   }

   public int compare$mcB$sp(final byte x, final byte y) {
      return Order.compare$mcB$sp$(this, x, y);
   }

   public int compare$mcC$sp(final char x, final char y) {
      return Order.compare$mcC$sp$(this, x, y);
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

   public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
      return Order.comparison$mcZ$sp$(this, x, y);
   }

   public Comparison comparison$mcB$sp(final byte x, final byte y) {
      return Order.comparison$mcB$sp$(this, x, y);
   }

   public Comparison comparison$mcC$sp(final char x, final char y) {
      return Order.comparison$mcC$sp$(this, x, y);
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

   public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
      return Order.partialCompare$mcZ$sp$(this, x, y);
   }

   public double partialCompare$mcB$sp(final byte x, final byte y) {
      return Order.partialCompare$mcB$sp$(this, x, y);
   }

   public double partialCompare$mcC$sp(final char x, final char y) {
      return Order.partialCompare$mcC$sp$(this, x, y);
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

   public boolean min$mcZ$sp(final boolean x, final boolean y) {
      return Order.min$mcZ$sp$(this, x, y);
   }

   public byte min$mcB$sp(final byte x, final byte y) {
      return Order.min$mcB$sp$(this, x, y);
   }

   public char min$mcC$sp(final char x, final char y) {
      return Order.min$mcC$sp$(this, x, y);
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

   public boolean max$mcZ$sp(final boolean x, final boolean y) {
      return Order.max$mcZ$sp$(this, x, y);
   }

   public byte max$mcB$sp(final byte x, final byte y) {
      return Order.max$mcB$sp$(this, x, y);
   }

   public char max$mcC$sp(final char x, final char y) {
      return Order.max$mcC$sp$(this, x, y);
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

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.eqv$mcZ$sp$(this, x, y);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return Order.eqv$mcB$sp$(this, x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return Order.eqv$mcC$sp$(this, x, y);
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

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.neqv$mcZ$sp$(this, x, y);
   }

   public boolean neqv$mcB$sp(final byte x, final byte y) {
      return Order.neqv$mcB$sp$(this, x, y);
   }

   public boolean neqv$mcC$sp(final char x, final char y) {
      return Order.neqv$mcC$sp$(this, x, y);
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

   public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.lteqv$mcZ$sp$(this, x, y);
   }

   public boolean lteqv$mcB$sp(final byte x, final byte y) {
      return Order.lteqv$mcB$sp$(this, x, y);
   }

   public boolean lteqv$mcC$sp(final char x, final char y) {
      return Order.lteqv$mcC$sp$(this, x, y);
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

   public boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return Order.lt$mcZ$sp$(this, x, y);
   }

   public boolean lt$mcB$sp(final byte x, final byte y) {
      return Order.lt$mcB$sp$(this, x, y);
   }

   public boolean lt$mcC$sp(final char x, final char y) {
      return Order.lt$mcC$sp$(this, x, y);
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

   public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.gteqv$mcZ$sp$(this, x, y);
   }

   public boolean gteqv$mcB$sp(final byte x, final byte y) {
      return Order.gteqv$mcB$sp$(this, x, y);
   }

   public boolean gteqv$mcC$sp(final char x, final char y) {
      return Order.gteqv$mcC$sp$(this, x, y);
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

   public boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return Order.gt$mcZ$sp$(this, x, y);
   }

   public boolean gt$mcB$sp(final byte x, final byte y) {
      return Order.gt$mcB$sp$(this, x, y);
   }

   public boolean gt$mcC$sp(final char x, final char y) {
      return Order.gt$mcC$sp$(this, x, y);
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

   public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
   }

   public Option partialComparison$mcB$sp(final byte x, final byte y) {
      return PartialOrder.partialComparison$mcB$sp$(this, x, y);
   }

   public Option partialComparison$mcC$sp(final char x, final char y) {
      return PartialOrder.partialComparison$mcC$sp$(this, x, y);
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

   public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
   }

   public Option tryCompare$mcB$sp(final byte x, final byte y) {
      return PartialOrder.tryCompare$mcB$sp$(this, x, y);
   }

   public Option tryCompare$mcC$sp(final char x, final char y) {
      return PartialOrder.tryCompare$mcC$sp$(this, x, y);
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

   public Option pmin$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmin$mcZ$sp$(this, x, y);
   }

   public Option pmin$mcB$sp(final byte x, final byte y) {
      return PartialOrder.pmin$mcB$sp$(this, x, y);
   }

   public Option pmin$mcC$sp(final char x, final char y) {
      return PartialOrder.pmin$mcC$sp$(this, x, y);
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

   public Option pmax$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmax$mcZ$sp$(this, x, y);
   }

   public Option pmax$mcB$sp(final byte x, final byte y) {
      return PartialOrder.pmax$mcB$sp$(this, x, y);
   }

   public Option pmax$mcC$sp(final char x, final char y) {
      return PartialOrder.pmax$mcC$sp$(this, x, y);
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

   public int hash(final double x) {
      return this.hash$mcD$sp(x);
   }

   public int compare(final double x, final double y) {
      return this.compare$mcD$sp(x, y);
   }

   public boolean eqv(final double x, final double y) {
      return this.eqv$mcD$sp(x, y);
   }

   public boolean neqv(final double x, final double y) {
      return this.neqv$mcD$sp(x, y);
   }

   public boolean gt(final double x, final double y) {
      return this.gt$mcD$sp(x, y);
   }

   public boolean gteqv(final double x, final double y) {
      return this.gteqv$mcD$sp(x, y);
   }

   public boolean lt(final double x, final double y) {
      return this.lt$mcD$sp(x, y);
   }

   public boolean lteqv(final double x, final double y) {
      return this.lteqv$mcD$sp(x, y);
   }

   public double min(final double x, final double y) {
      return this.min$mcD$sp(x, y);
   }

   public double max(final double x, final double y) {
      return this.max$mcD$sp(x, y);
   }

   public int hash$mcD$sp(final double x) {
      return Double.hashCode(x);
   }

   public int compare$mcD$sp(final double x, final double y) {
      return Double.compare(x, y);
   }

   public boolean eqv$mcD$sp(final double x, final double y) {
      return x == y;
   }

   public boolean neqv$mcD$sp(final double x, final double y) {
      return x != y;
   }

   public boolean gt$mcD$sp(final double x, final double y) {
      return x > y;
   }

   public boolean gteqv$mcD$sp(final double x, final double y) {
      return x >= y;
   }

   public boolean lt$mcD$sp(final double x, final double y) {
      return x < y;
   }

   public boolean lteqv$mcD$sp(final double x, final double y) {
      return x <= y;
   }

   public double min$mcD$sp(final double x, final double y) {
      return Math.min(x, y);
   }

   public double max$mcD$sp(final double x, final double y) {
      return Math.max(x, y);
   }

   public DoubleOrder() {
      Eq.$init$(this);
      PartialOrder.$init$(this);
      Order.$init$(this);
   }
}
