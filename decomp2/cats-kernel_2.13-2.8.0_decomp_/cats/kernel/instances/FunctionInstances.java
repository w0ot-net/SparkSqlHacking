package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.IterableOnce;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t4q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\ra\u0004C\u00036\u0001\u0011\ra\u0007C\u0003B\u0001\u0011\r!IA\tGk:\u001cG/[8o\u0013:\u001cH/\u00198dKNT!a\u0002\u0005\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0005\u000b\u0003\u0019YWM\u001d8fY*\t1\"\u0001\u0003dCR\u001c8\u0001A\n\u0004\u00019!\u0002CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\r\u0005\u0002\u0016-5\ta!\u0003\u0002\u0018\r\t\u0011b)\u001e8di&|g.\u00138ti\u0006t7-Z:1\u0003\u0019!\u0013N\\5uIQ\t!\u0004\u0005\u0002\u00107%\u0011A\u0004\u0005\u0002\u0005+:LG/A\u000edCR\u001c8*\u001a:oK2|%\u000fZ3s\r>\u0014h)\u001e8di&|g\u000eM\u000b\u0003?%\"\"\u0001\t\u001a\u0011\u0007\u0005\u0012C%D\u0001\t\u0013\t\u0019\u0003BA\u0003Pe\u0012,'\u000fE\u0002\u0010K\u001dJ!A\n\t\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004C\u0001\u0015*\u0019\u0001!QA\u000b\u0002C\u0002-\u0012\u0011!Q\t\u0003Y=\u0002\"aD\u0017\n\u00059\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001fAJ!!\r\t\u0003\u0007\u0005s\u0017\u0010C\u00034\u0005\u0001\u000fA'\u0001\u0002fmB\u0019\u0011EI\u0014\u0002M\r\fGo]&fe:,GnQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9G_J4UO\\2uS>t\u0007'\u0006\u00028{Q\u0011\u0001H\u0010\t\u0004CeZ\u0014B\u0001\u001e\t\u0005A\u0019u.\\7vi\u0006$\u0018N^3He>,\b\u000fE\u0002\u0010Kq\u0002\"\u0001K\u001f\u0005\u000b)\u001a!\u0019A\u0016\t\u000b}\u001a\u00019\u0001!\u0002\u0003\u001d\u00032!I\u001d=\u0003\u0019\u001a\u0017\r^:LKJtW\r\\\"p[6,H/\u0019;jm\u0016<%o\\;q\r>\u0014h)\u001e8di&|g.M\u000b\u0004\u0007&[EC\u0001#N!\r\t\u0013(\u0012\t\u0005\u001f\u0019C%*\u0003\u0002H!\tIa)\u001e8di&|g.\r\t\u0003Q%#QA\u000b\u0003C\u0002-\u0002\"\u0001K&\u0005\u000b1#!\u0019A\u0016\u0003\u0003\tCQa\u0010\u0003A\u00049\u00032!I\u001dKQ\t\u0001\u0001\u000b\u0005\u0002R?:\u0011!\u000b\u0018\b\u0003'js!\u0001V-\u000f\u0005UCV\"\u0001,\u000b\u0005]c\u0011A\u0002\u001fs_>$h(C\u0001\f\u0013\tI!\"\u0003\u0002\\\u0011\u000511m\\7qCRL!!\u00180\u0002)M\u001c\u0017\r\\1WKJ\u001c\u0018n\u001c8Ta\u0016\u001c\u0017NZ5d\u0015\tY\u0006\"\u0003\u0002aC\n\u00114/\u001e9qe\u0016\u001c8/\u00168vg\u0016$\u0017*\u001c9peR<\u0016M\u001d8j]\u001e4uN]*dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7M\u0003\u0002^=\u0002"
)
public interface FunctionInstances extends FunctionInstances0 {
   // $FF: synthetic method
   static Order catsKernelOrderForFunction0$(final FunctionInstances $this, final Order ev) {
      return $this.catsKernelOrderForFunction0(ev);
   }

   default Order catsKernelOrderForFunction0(final Order ev) {
      return new Order(ev) {
         private final Order ev$1;

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

         public int compare(final Function0 x, final Function0 y) {
            return this.ev$1.compare(x.apply(), y.apply());
         }

         public {
            this.ev$1 = ev$1;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CommutativeGroup catsKernelCommutativeGroupForFunction0$(final FunctionInstances $this, final CommutativeGroup G) {
      return $this.catsKernelCommutativeGroupForFunction0(G);
   }

   default CommutativeGroup catsKernelCommutativeGroupForFunction0(final CommutativeGroup G) {
      return new Function0Group(G) {
         private Function0 empty;
         private final CommutativeGroup G$1;

         public CommutativeMonoid reverse() {
            return CommutativeMonoid.reverse$(this);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Function0 inverse(final Function0 x) {
            return Function0Group.inverse$(this, x);
         }

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

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

         public Function0 combine(final Function0 x, final Function0 y) {
            return Function0Semigroup.combine$(this, x, y);
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

         public Function0 empty() {
            return this.empty;
         }

         public void cats$kernel$instances$Function0Monoid$_setter_$empty_$eq(final Function0 x$1) {
            this.empty = x$1;
         }

         public Group A() {
            return this.G$1;
         }

         public {
            this.G$1 = G$1;
            Semigroup.$init$(this);
            Function0Semigroup.$init$(this);
            Monoid.$init$(this);
            Function0Monoid.$init$(this);
            Group.$init$(this);
            Function0Group.$init$(this);
            CommutativeSemigroup.$init$(this);
            CommutativeMonoid.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   // $FF: synthetic method
   static CommutativeGroup catsKernelCommutativeGroupForFunction1$(final FunctionInstances $this, final CommutativeGroup G) {
      return $this.catsKernelCommutativeGroupForFunction1(G);
   }

   default CommutativeGroup catsKernelCommutativeGroupForFunction1(final CommutativeGroup G) {
      return new Function1Group(G) {
         private Function1 empty;
         private final CommutativeGroup G$2;

         public CommutativeMonoid reverse() {
            return CommutativeMonoid.reverse$(this);
         }

         public CommutativeMonoid reverse$mcD$sp() {
            return CommutativeMonoid.reverse$mcD$sp$(this);
         }

         public CommutativeMonoid reverse$mcF$sp() {
            return CommutativeMonoid.reverse$mcF$sp$(this);
         }

         public CommutativeMonoid reverse$mcI$sp() {
            return CommutativeMonoid.reverse$mcI$sp$(this);
         }

         public CommutativeMonoid reverse$mcJ$sp() {
            return CommutativeMonoid.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Function1 inverse(final Function1 x) {
            return Function1Group.inverse$(this, x);
         }

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

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

         public Function1 combine(final Function1 x, final Function1 y) {
            return Function1Semigroup.combine$(this, x, y);
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

         public Function1 empty() {
            return this.empty;
         }

         public void cats$kernel$instances$Function1Monoid$_setter_$empty_$eq(final Function1 x$1) {
            this.empty = x$1;
         }

         public Group B() {
            return this.G$2;
         }

         public {
            this.G$2 = G$2;
            Semigroup.$init$(this);
            Function1Semigroup.$init$(this);
            Monoid.$init$(this);
            Function1Monoid.$init$(this);
            Group.$init$(this);
            Function1Group.$init$(this);
            CommutativeSemigroup.$init$(this);
            CommutativeMonoid.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   static void $init$(final FunctionInstances $this) {
   }
}
