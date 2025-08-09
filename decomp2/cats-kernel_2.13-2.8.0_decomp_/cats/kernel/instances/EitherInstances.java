package cats.kernel.instances;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.MatchError;
import scala.Option;
import scala.collection.IterableOnce;
import scala.math.Ordering;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;

@ScalaSignature(
   bytes = "\u0006\u0005A3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003D\u0001\u0011\rAIA\bFSRDWM]%ogR\fgnY3t\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u00011c\u0001\u0001\u000e'A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u0004\"\u0001F\u000b\u000e\u0003\u0015I!AF\u0003\u0003!\u0015KG\u000f[3s\u0013:\u001cH/\u00198dKN\u0004\u0014A\u0002\u0013j]&$H\u0005F\u0001\u001a!\tq!$\u0003\u0002\u001c\u001f\t!QK\\5u\u0003U\u0019\u0017\r^:Ti\u0012|%\u000fZ3s\r>\u0014X)\u001b;iKJ,2AH\u0019<)\ryR\b\u0011\t\u0004A\u0005\u001aS\"A\u0004\n\u0005\t:!!B(sI\u0016\u0014\b\u0003\u0002\u0013-_ir!!\n\u0016\u000f\u0005\u0019JS\"A\u0014\u000b\u0005!Z\u0011A\u0002\u001fs_>$h(C\u0001\u0011\u0013\tYs\"A\u0004qC\u000e\\\u0017mZ3\n\u00055r#AB#ji\",'O\u0003\u0002,\u001fA\u0011\u0001'\r\u0007\u0001\t\u0015\u0011$A1\u00014\u0005\u0005\t\u0015C\u0001\u001b8!\tqQ'\u0003\u00027\u001f\t9aj\u001c;iS:<\u0007C\u0001\b9\u0013\tItBA\u0002B]f\u0004\"\u0001M\u001e\u0005\u000bq\u0012!\u0019A\u001a\u0003\u0003\tCQA\u0010\u0002A\u0004}\n\u0011!\u0011\t\u0004A\u0005z\u0003\"B!\u0003\u0001\b\u0011\u0015!\u0001\"\u0011\u0007\u0001\n#(A\fdCR\u001cH)\u0019;b\u001b>tw.\u001b3G_J,\u0015\u000e\u001e5feV\u0019QiS'\u0015\u0005\u0019s\u0005c\u0001\u0011H\u0013&\u0011\u0001j\u0002\u0002\u0007\u001b>tw.\u001b3\u0011\t\u0011b#\n\u0014\t\u0003a-#QAM\u0002C\u0002M\u0002\"\u0001M'\u0005\u000bq\u001a!\u0019A\u001a\t\u000b\u0005\u001b\u00019A(\u0011\u0007\u0001:E\n"
)
public interface EitherInstances extends EitherInstances0 {
   // $FF: synthetic method
   static Order catsStdOrderForEither$(final EitherInstances $this, final Order A, final Order B) {
      return $this.catsStdOrderForEither(A, B);
   }

   default Order catsStdOrderForEither(final Order A, final Order B) {
      return new Order(A, B) {
         private final Order A$1;
         private final Order B$1;

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

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
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

         public int compare(final Either x, final Either y) {
            int var3;
            if (x instanceof Left) {
               Left var7 = (Left)x;
               Object xx = var7.value();
               int var5;
               if (y instanceof Left) {
                  Left var10 = (Left)y;
                  Object yy = var10.value();
                  var5 = this.A$1.compare(xx, yy);
               } else {
                  if (!(y instanceof Right)) {
                     throw new MatchError(y);
                  }

                  var5 = -1;
               }

               var3 = var5;
            } else {
               if (!(x instanceof Right)) {
                  throw new MatchError(x);
               }

               Right var12 = (Right)x;
               Object xx = var12.value();
               int var4;
               if (y instanceof Left) {
                  var4 = 1;
               } else {
                  if (!(y instanceof Right)) {
                     throw new MatchError(y);
                  }

                  Right var15 = (Right)y;
                  Object yy = var15.value();
                  var4 = this.B$1.compare(xx, yy);
               }

               var3 = var4;
            }

            return var3;
         }

         public {
            this.A$1 = A$1;
            this.B$1 = B$1;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Monoid catsDataMonoidForEither$(final EitherInstances $this, final Monoid B) {
      return $this.catsDataMonoidForEither(B);
   }

   default Monoid catsDataMonoidForEither(final Monoid B) {
      return new Monoid(B) {
         private final Monoid B$2;

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineN(final Object a, final int n) {
            return Monoid.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Monoid.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Monoid.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Monoid.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Monoid.combineN$mcJ$sp$(this, a, n);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Either empty() {
            return .MODULE$.Right().apply(this.B$2.empty());
         }

         public Either combine(final Either x, final Either y) {
            Object var3;
            if (x instanceof Left) {
               Left var6 = (Left)x;
               var3 = var6;
            } else {
               if (!(x instanceof Right)) {
                  throw new MatchError(x);
               }

               Right var7 = (Right)x;
               Object xx = var7.value();
               Object var4;
               if (y instanceof Left) {
                  Left var10 = (Left)y;
                  var4 = var10;
               } else {
                  if (!(y instanceof Right)) {
                     throw new MatchError(y);
                  }

                  Right var11 = (Right)y;
                  Object yy = var11.value();
                  var4 = .MODULE$.Right().apply(this.B$2.combine(xx, yy));
               }

               var3 = var4;
            }

            return (Either)var3;
         }

         public {
            this.B$2 = B$2;
            Semigroup.$init$(this);
            Monoid.$init$(this);
         }
      };
   }

   static void $init$(final EitherInstances $this) {
   }
}
