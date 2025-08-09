package cats.kernel.instances;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.LowerBounded;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.UpperBounded;
import java.util.UUID;
import scala.Option;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005E2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007I1\u0001\r\u0003\u001bU+\u0016\nR%ogR\fgnY3t\u0015\t)a!A\u0005j]N$\u0018M\\2fg*\u0011q\u0001C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003%\tAaY1ug\u000e\u00011C\u0001\u0001\r!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012\u0001\u0006\t\u0003\u001bUI!A\u0006\b\u0003\tUs\u0017\u000e^\u0001\u001aG\u0006$8oS3s]\u0016d7\u000b\u001e3Pe\u0012,'OR8s+VKE)F\u0001\u001a%\u0015QB\u0004K\u0016/\r\u0011Y\u0002\u0001A\r\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u0007uq\u0002%D\u0001\u0007\u0013\tybAA\u0003Pe\u0012,'\u000f\u0005\u0002\"M5\t!E\u0003\u0002$I\u0005!Q\u000f^5m\u0015\u0005)\u0013\u0001\u00026bm\u0006L!a\n\u0012\u0003\tU+\u0016\n\u0012\t\u0004;%\u0002\u0013B\u0001\u0016\u0007\u0005\u0011A\u0015m\u001d5\u0011\u0007ua\u0003%\u0003\u0002.\r\taAj\\<fe\n{WO\u001c3fIB\u0019Qd\f\u0011\n\u0005A2!\u0001D+qa\u0016\u0014(i\\;oI\u0016$\u0007"
)
public interface UUIDInstances {
   void cats$kernel$instances$UUIDInstances$_setter_$catsKernelStdOrderForUUID_$eq(final Order x$1);

   Order catsKernelStdOrderForUUID();

   static void $init$(final UUIDInstances $this) {
      $this.cats$kernel$instances$UUIDInstances$_setter_$catsKernelStdOrderForUUID_$eq(new Order() {
         private final PartialOrder partialOrder;

         public UUID minBound() {
            return UUIDBounded.minBound$(this);
         }

         public UUID maxBound() {
            return UUIDBounded.maxBound$(this);
         }

         public PartialOrder partialOrder$mcZ$sp() {
            return UpperBounded.partialOrder$mcZ$sp$(this);
         }

         public PartialOrder partialOrder$mcB$sp() {
            return UpperBounded.partialOrder$mcB$sp$(this);
         }

         public PartialOrder partialOrder$mcC$sp() {
            return UpperBounded.partialOrder$mcC$sp$(this);
         }

         public PartialOrder partialOrder$mcD$sp() {
            return UpperBounded.partialOrder$mcD$sp$(this);
         }

         public PartialOrder partialOrder$mcF$sp() {
            return UpperBounded.partialOrder$mcF$sp$(this);
         }

         public PartialOrder partialOrder$mcI$sp() {
            return UpperBounded.partialOrder$mcI$sp$(this);
         }

         public PartialOrder partialOrder$mcJ$sp() {
            return UpperBounded.partialOrder$mcJ$sp$(this);
         }

         public PartialOrder partialOrder$mcS$sp() {
            return UpperBounded.partialOrder$mcS$sp$(this);
         }

         public PartialOrder partialOrder$mcV$sp() {
            return UpperBounded.partialOrder$mcV$sp$(this);
         }

         public boolean maxBound$mcZ$sp() {
            return UpperBounded.maxBound$mcZ$sp$(this);
         }

         public byte maxBound$mcB$sp() {
            return UpperBounded.maxBound$mcB$sp$(this);
         }

         public char maxBound$mcC$sp() {
            return UpperBounded.maxBound$mcC$sp$(this);
         }

         public double maxBound$mcD$sp() {
            return UpperBounded.maxBound$mcD$sp$(this);
         }

         public float maxBound$mcF$sp() {
            return UpperBounded.maxBound$mcF$sp$(this);
         }

         public int maxBound$mcI$sp() {
            return UpperBounded.maxBound$mcI$sp$(this);
         }

         public long maxBound$mcJ$sp() {
            return UpperBounded.maxBound$mcJ$sp$(this);
         }

         public short maxBound$mcS$sp() {
            return UpperBounded.maxBound$mcS$sp$(this);
         }

         public void maxBound$mcV$sp() {
            UpperBounded.maxBound$mcV$sp$(this);
         }

         public boolean minBound$mcZ$sp() {
            return LowerBounded.minBound$mcZ$sp$(this);
         }

         public byte minBound$mcB$sp() {
            return LowerBounded.minBound$mcB$sp$(this);
         }

         public char minBound$mcC$sp() {
            return LowerBounded.minBound$mcC$sp$(this);
         }

         public double minBound$mcD$sp() {
            return LowerBounded.minBound$mcD$sp$(this);
         }

         public float minBound$mcF$sp() {
            return LowerBounded.minBound$mcF$sp$(this);
         }

         public int minBound$mcI$sp() {
            return LowerBounded.minBound$mcI$sp$(this);
         }

         public long minBound$mcJ$sp() {
            return LowerBounded.minBound$mcJ$sp$(this);
         }

         public short minBound$mcS$sp() {
            return LowerBounded.minBound$mcS$sp$(this);
         }

         public void minBound$mcV$sp() {
            LowerBounded.minBound$mcV$sp$(this);
         }

         public int hash$mcZ$sp(final boolean x) {
            return Hash.hash$mcZ$sp$(this, x);
         }

         public int hash$mcB$sp(final byte x) {
            return Hash.hash$mcB$sp$(this, x);
         }

         public int hash$mcC$sp(final char x) {
            return Hash.hash$mcC$sp$(this, x);
         }

         public int hash$mcD$sp(final double x) {
            return Hash.hash$mcD$sp$(this, x);
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

         public int compare(final UUID x, final UUID y) {
            return x.compareTo(y);
         }

         public int hash(final UUID x) {
            return x.hashCode();
         }

         public PartialOrder partialOrder() {
            return this.partialOrder;
         }

         public {
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
            UUIDBounded.$init$(this);
            this.partialOrder = this;
         }
      });
   }
}
