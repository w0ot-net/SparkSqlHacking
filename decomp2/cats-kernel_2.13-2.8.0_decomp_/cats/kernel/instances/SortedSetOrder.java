package cats.kernel.instances;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.instances.int.package$;
import scala.Option;
import scala.collection.immutable.SortedSet;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193A!\u0002\u0004\u0001\u001b!AA\u0006\u0001B\u0002B\u0003-Q\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u00035\u0001\u0011\u0005Q\u0007C\u0003>\u0001\u0011\u0005cH\u0001\bT_J$X\rZ*fi>\u0013H-\u001a:\u000b\u0005\u001dA\u0011!C5ogR\fgnY3t\u0015\tI!\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0017\u0005!1-\u0019;t\u0007\u0001)\"AD\u0012\u0014\u0007\u0001yQ\u0003\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VM\u001a\t\u0004-]IR\"\u0001\u0005\n\u0005aA!!B(sI\u0016\u0014\bc\u0001\u000e C5\t1D\u0003\u0002\u001d;\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003=E\t!bY8mY\u0016\u001cG/[8o\u0013\t\u00013DA\u0005T_J$X\rZ*fiB\u0011!e\t\u0007\u0001\t\u0015!\u0003A1\u0001&\u0005\u0005\t\u0015C\u0001\u0014*!\t\u0001r%\u0003\u0002)#\t9aj\u001c;iS:<\u0007C\u0001\t+\u0013\tY\u0013CA\u0002B]f\f!\"\u001a<jI\u0016t7-\u001a\u00137!\r1r#I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003A\"\"!M\u001a\u0011\u0007I\u0002\u0011%D\u0001\u0007\u0011\u0015a#\u0001q\u0001.\u0003\u001d\u0019w.\u001c9be\u0016$2AN\u001d<!\t\u0001r'\u0003\u00029#\t\u0019\u0011J\u001c;\t\u000bi\u001a\u0001\u0019A\r\u0002\u0005\u0005\f\u0004\"\u0002\u001f\u0004\u0001\u0004I\u0012AA13\u0003\r)\u0017O\u001e\u000b\u0004\u007f\t#\u0005C\u0001\tA\u0013\t\t\u0015CA\u0004C_>dW-\u00198\t\u000b\r#\u0001\u0019A\r\u0002\u0005M\f\u0004\"B#\u0005\u0001\u0004I\u0012AA:3\u0001"
)
public class SortedSetOrder implements Order {
   private final Order evidence$6;

   public int compare$mcZ$sp(final boolean x, final boolean y) {
      return Order.compare$mcZ$sp$(this, x, y);
   }

   public int compare$mcB$sp(final byte x, final byte y) {
      return Order.compare$mcB$sp$(this, x, y);
   }

   public int compare$mcC$sp(final char x, final char y) {
      return Order.compare$mcC$sp$(this, x, y);
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

   public Comparison comparison(final Object x, final Object y) {
      return Order.comparison$(this, x, y);
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

   public double partialCompare(final Object x, final Object y) {
      return Order.partialCompare$(this, x, y);
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

   public Object min(final Object x, final Object y) {
      return Order.min$(this, x, y);
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

   public Object max(final Object x, final Object y) {
      return Order.max$(this, x, y);
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

   public boolean eqv$mcC$sp(final char x, final char y) {
      return Order.eqv$mcC$sp$(this, x, y);
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

   public boolean neqv(final Object x, final Object y) {
      return Order.neqv$(this, x, y);
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

   public boolean lteqv(final Object x, final Object y) {
      return Order.lteqv$(this, x, y);
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

   public boolean lt(final Object x, final Object y) {
      return Order.lt$(this, x, y);
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

   public boolean gteqv(final Object x, final Object y) {
      return Order.gteqv$(this, x, y);
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

   public boolean gt(final Object x, final Object y) {
      return Order.gt$(this, x, y);
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

   public int compare(final SortedSet a1, final SortedSet a2) {
      int var3 = package$.MODULE$.catsKernelStdOrderForInt().compare$mcI$sp(a1.size(), a2.size());
      int var10000;
      switch (var3) {
         case 0:
            var10000 = StaticMethods$.MODULE$.iteratorCompare(a1.iterator(), a2.iterator(), this.evidence$6);
            break;
         default:
            var10000 = var3;
      }

      return var10000;
   }

   public boolean eqv(final SortedSet s1, final SortedSet s2) {
      return StaticMethods$.MODULE$.iteratorEq(s1.iterator(), s2.iterator(), this.evidence$6);
   }

   public SortedSetOrder(final Order evidence$6) {
      this.evidence$6 = evidence$6;
      Eq.$init$(this);
      PartialOrder.$init$(this);
      Order.$init$(this);
   }
}
