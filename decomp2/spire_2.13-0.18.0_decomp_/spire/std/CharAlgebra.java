package spire.std;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Order.mcC.sp;
import scala.Option;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005!2AAA\u0002\u0001\u0011!)q\u0004\u0001C\u0001A\tY1\t[1s\u00032<WM\u0019:b\u0015\t!Q!A\u0002ti\u0012T\u0011AB\u0001\u0006gBL'/Z\u0002\u0001'\u0011\u0001\u0011bD\n\u0011\u0005)iQ\"A\u0006\u000b\u00031\tQa]2bY\u0006L!AD\u0006\u0003\r\u0005s\u0017PU3g!\t\u0001\u0012#D\u0001\u0004\u0013\t\u00112AA\u0005DQ\u0006\u0014xJ\u001d3feB\u0011A\u0003\b\b\u0003+iq!AF\r\u000e\u0003]Q!\u0001G\u0004\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0011BA\u000e\f\u0003\u001d\u0001\u0018mY6bO\u0016L!!\b\u0010\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mY\u0011A\u0002\u001fj]&$h\bF\u0001\"!\t\u0001\u0002\u0001\u000b\u0003\u0001G\u0019:\u0003C\u0001\u0006%\u0013\t)3B\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0001\u0001"
)
public class CharAlgebra implements CharOrder {
   private static final long serialVersionUID = 0L;

   public boolean eqv(final char x, final char y) {
      return CharOrder.eqv$(this, x, y);
   }

   public boolean neqv(final char x, final char y) {
      return CharOrder.neqv$(this, x, y);
   }

   public boolean gt(final char x, final char y) {
      return CharOrder.gt$(this, x, y);
   }

   public boolean gteqv(final char x, final char y) {
      return CharOrder.gteqv$(this, x, y);
   }

   public boolean lt(final char x, final char y) {
      return CharOrder.lt$(this, x, y);
   }

   public boolean lteqv(final char x, final char y) {
      return CharOrder.lteqv$(this, x, y);
   }

   public int compare(final char x, final char y) {
      return CharOrder.compare$(this, x, y);
   }

   public boolean eqv$mcC$sp(final char x, final char y) {
      return CharOrder.eqv$mcC$sp$(this, x, y);
   }

   public boolean neqv$mcC$sp(final char x, final char y) {
      return CharOrder.neqv$mcC$sp$(this, x, y);
   }

   public boolean gt$mcC$sp(final char x, final char y) {
      return CharOrder.gt$mcC$sp$(this, x, y);
   }

   public boolean gteqv$mcC$sp(final char x, final char y) {
      return CharOrder.gteqv$mcC$sp$(this, x, y);
   }

   public boolean lt$mcC$sp(final char x, final char y) {
      return CharOrder.lt$mcC$sp$(this, x, y);
   }

   public boolean lteqv$mcC$sp(final char x, final char y) {
      return CharOrder.lteqv$mcC$sp$(this, x, y);
   }

   public int compare$mcC$sp(final char x, final char y) {
      return CharOrder.compare$mcC$sp$(this, x, y);
   }

   public Comparison comparison(final char x, final char y) {
      return sp.comparison$(this, x, y);
   }

   public Comparison comparison$mcC$sp(final char x, final char y) {
      return sp.comparison$mcC$sp$(this, x, y);
   }

   public double partialCompare(final char x, final char y) {
      return sp.partialCompare$(this, x, y);
   }

   public double partialCompare$mcC$sp(final char x, final char y) {
      return sp.partialCompare$mcC$sp$(this, x, y);
   }

   public char min(final char x, final char y) {
      return sp.min$(this, x, y);
   }

   public char min$mcC$sp(final char x, final char y) {
      return sp.min$mcC$sp$(this, x, y);
   }

   public char max(final char x, final char y) {
      return sp.max$(this, x, y);
   }

   public char max$mcC$sp(final char x, final char y) {
      return sp.max$mcC$sp$(this, x, y);
   }

   public Option partialComparison(final char x, final char y) {
      return cats.kernel.PartialOrder.mcC.sp.partialComparison$(this, x, y);
   }

   public Option partialComparison$mcC$sp(final char x, final char y) {
      return cats.kernel.PartialOrder.mcC.sp.partialComparison$mcC$sp$(this, x, y);
   }

   public Option tryCompare(final char x, final char y) {
      return cats.kernel.PartialOrder.mcC.sp.tryCompare$(this, x, y);
   }

   public Option tryCompare$mcC$sp(final char x, final char y) {
      return cats.kernel.PartialOrder.mcC.sp.tryCompare$mcC$sp$(this, x, y);
   }

   public Option pmin(final char x, final char y) {
      return cats.kernel.PartialOrder.mcC.sp.pmin$(this, x, y);
   }

   public Option pmin$mcC$sp(final char x, final char y) {
      return cats.kernel.PartialOrder.mcC.sp.pmin$mcC$sp$(this, x, y);
   }

   public Option pmax(final char x, final char y) {
      return cats.kernel.PartialOrder.mcC.sp.pmax$(this, x, y);
   }

   public Option pmax$mcC$sp(final char x, final char y) {
      return cats.kernel.PartialOrder.mcC.sp.pmax$mcC$sp$(this, x, y);
   }

   public int compare$mcZ$sp(final boolean x, final boolean y) {
      return Order.compare$mcZ$sp$(this, x, y);
   }

   public int compare$mcB$sp(final byte x, final byte y) {
      return Order.compare$mcB$sp$(this, x, y);
   }

   public int compare$mcD$sp(final double x, final double y) {
      return Order.compare$mcD$sp$(this, x, y);
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

   public Comparison comparison$mcD$sp(final double x, final double y) {
      return Order.comparison$mcD$sp$(this, x, y);
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

   public double partialCompare$mcD$sp(final double x, final double y) {
      return Order.partialCompare$mcD$sp$(this, x, y);
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

   public double min$mcD$sp(final double x, final double y) {
      return Order.min$mcD$sp$(this, x, y);
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

   public double max$mcD$sp(final double x, final double y) {
      return Order.max$mcD$sp$(this, x, y);
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

   public boolean eqv$mcD$sp(final double x, final double y) {
      return Order.eqv$mcD$sp$(this, x, y);
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

   public boolean neqv$mcD$sp(final double x, final double y) {
      return Order.neqv$mcD$sp$(this, x, y);
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

   public boolean lteqv$mcD$sp(final double x, final double y) {
      return Order.lteqv$mcD$sp$(this, x, y);
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

   public boolean lt$mcD$sp(final double x, final double y) {
      return Order.lt$mcD$sp$(this, x, y);
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

   public boolean gteqv$mcD$sp(final double x, final double y) {
      return Order.gteqv$mcD$sp$(this, x, y);
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

   public boolean gt$mcD$sp(final double x, final double y) {
      return Order.gt$mcD$sp$(this, x, y);
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

   public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
   }

   public Option tryCompare$mcB$sp(final byte x, final byte y) {
      return PartialOrder.tryCompare$mcB$sp$(this, x, y);
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

   public Option pmin$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmin$mcZ$sp$(this, x, y);
   }

   public Option pmin$mcB$sp(final byte x, final byte y) {
      return PartialOrder.pmin$mcB$sp$(this, x, y);
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

   public Option pmax$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmax$mcZ$sp$(this, x, y);
   }

   public Option pmax$mcB$sp(final byte x, final byte y) {
      return PartialOrder.pmax$mcB$sp$(this, x, y);
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

   public CharAlgebra() {
      Eq.$init$(this);
      PartialOrder.$init$(this);
      Order.$init$(this);
      CharOrder.$init$(this);
   }
}
