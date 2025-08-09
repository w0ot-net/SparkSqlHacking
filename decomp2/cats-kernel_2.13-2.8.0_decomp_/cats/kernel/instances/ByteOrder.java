package cats.kernel.instances;

import cats.kernel.BoundedEnumerable;
import cats.kernel.BoundedEnumerable$mcB$sp;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Hash$mcB$sp;
import cats.kernel.LowerBounded;
import cats.kernel.Order;
import cats.kernel.Order$mcB$sp;
import cats.kernel.PartialNext;
import cats.kernel.PartialNextLowerBounded;
import cats.kernel.PartialOrder;
import cats.kernel.PartialOrder$mcB$sp;
import cats.kernel.PartialPrevious;
import cats.kernel.PartialPreviousUpperBounded;
import cats.kernel.UpperBounded;
import scala.Option;
import scala.collection.immutable.LazyList;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\r4AAD\b\u0001-!)a\u0006\u0001C\u0001_!)\u0011\u0007\u0001C\u0001e!)\u0001\b\u0001C\u0001s!)Q\b\u0001C!}!)A\t\u0001C!\u000b\")\u0001\n\u0001C!\u0013\")A\n\u0001C!\u001b\")\u0001\u000b\u0001C!#\")A\u000b\u0001C!+\")\u0001\f\u0001C!3\")A\f\u0001C!;\"9\u0001\r\u0001b\u0001\n\u0003\n\u0007B\u00022\u0001A\u0003%QDA\u0005CsR,wJ\u001d3fe*\u0011\u0001#E\u0001\nS:\u001cH/\u00198dKNT!AE\n\u0002\r-,'O\\3m\u0015\u0005!\u0012\u0001B2biN\u001c\u0001a\u0005\u0004\u0001/u!se\u000b\t\u00031mi\u0011!\u0007\u0006\u00025\u0005)1oY1mC&\u0011A$\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0007yy\u0012%D\u0001\u0012\u0013\t\u0001\u0013CA\u0003Pe\u0012,'\u000f\u0005\u0002\u0019E%\u00111%\u0007\u0002\u0005\u0005f$X\rE\u0002\u001fK\u0005J!AJ\t\u0003\t!\u000b7\u000f\u001b\t\u0003Q%j\u0011aD\u0005\u0003U=\u00111BQ=uK\n{WO\u001c3fIB\u0011\u0001\u0006L\u0005\u0003[=\u0011aBQ=uK\u0016sW/\\3sC\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002aA\u0011\u0001\u0006A\u0001\u0005Q\u0006\u001c\b\u000e\u0006\u00024mA\u0011\u0001\u0004N\u0005\u0003ke\u00111!\u00138u\u0011\u00159$\u00011\u0001\"\u0003\u0005A\u0018aB2p[B\f'/\u001a\u000b\u0004giZ\u0004\"B\u001c\u0004\u0001\u0004\t\u0003\"\u0002\u001f\u0004\u0001\u0004\t\u0013!A=\u0002\u0007\u0015\fh\u000fF\u0002@\u0005\u000e\u0003\"\u0001\u0007!\n\u0005\u0005K\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006o\u0011\u0001\r!\t\u0005\u0006y\u0011\u0001\r!I\u0001\u0005]\u0016\fh\u000fF\u0002@\r\u001eCQaN\u0003A\u0002\u0005BQ\u0001P\u0003A\u0002\u0005\n!a\u001a;\u0015\u0007}R5\nC\u00038\r\u0001\u0007\u0011\u0005C\u0003=\r\u0001\u0007\u0011%A\u0003hi\u0016\fh\u000fF\u0002@\u001d>CQaN\u0004A\u0002\u0005BQ\u0001P\u0004A\u0002\u0005\n!\u0001\u001c;\u0015\u0007}\u00126\u000bC\u00038\u0011\u0001\u0007\u0011\u0005C\u0003=\u0011\u0001\u0007\u0011%A\u0003mi\u0016\fh\u000fF\u0002@-^CQaN\u0005A\u0002\u0005BQ\u0001P\u0005A\u0002\u0005\n1!\\5o)\r\t#l\u0017\u0005\u0006o)\u0001\r!\t\u0005\u0006y)\u0001\r!I\u0001\u0004[\u0006DHcA\u0011_?\")qg\u0003a\u0001C!)Ah\u0003a\u0001C\u0005)qN\u001d3feV\tQ$\u0001\u0004pe\u0012,'\u000f\t"
)
public class ByteOrder implements Order$mcB$sp, Hash$mcB$sp, ByteBounded, ByteEnumerable {
   private final Order order;

   public Option partialNext(final byte a) {
      return ByteEnumerable.partialNext$(this, a);
   }

   public Option partialPrevious(final byte a) {
      return ByteEnumerable.partialPrevious$(this, a);
   }

   public Option partialNext$mcB$sp(final byte a) {
      return ByteEnumerable.partialNext$mcB$sp$(this, a);
   }

   public Option partialPrevious$mcB$sp(final byte a) {
      return ByteEnumerable.partialPrevious$mcB$sp$(this, a);
   }

   public PartialOrder partialOrder() {
      return BoundedEnumerable$mcB$sp.partialOrder$(this);
   }

   public PartialOrder partialOrder$mcB$sp() {
      return BoundedEnumerable$mcB$sp.partialOrder$mcB$sp$(this);
   }

   public byte cycleNext(final byte a) {
      return BoundedEnumerable$mcB$sp.cycleNext$(this, a);
   }

   public byte cycleNext$mcB$sp(final byte a) {
      return BoundedEnumerable$mcB$sp.cycleNext$mcB$sp$(this, a);
   }

   public byte cyclePrevious(final byte a) {
      return BoundedEnumerable$mcB$sp.cyclePrevious$(this, a);
   }

   public byte cyclePrevious$mcB$sp(final byte a) {
      return BoundedEnumerable$mcB$sp.cyclePrevious$mcB$sp$(this, a);
   }

   public Order order$mcZ$sp() {
      return BoundedEnumerable.order$mcZ$sp$(this);
   }

   public Order order$mcC$sp() {
      return BoundedEnumerable.order$mcC$sp$(this);
   }

   public Order order$mcD$sp() {
      return BoundedEnumerable.order$mcD$sp$(this);
   }

   public Order order$mcF$sp() {
      return BoundedEnumerable.order$mcF$sp$(this);
   }

   public Order order$mcI$sp() {
      return BoundedEnumerable.order$mcI$sp$(this);
   }

   public Order order$mcJ$sp() {
      return BoundedEnumerable.order$mcJ$sp$(this);
   }

   public Order order$mcS$sp() {
      return BoundedEnumerable.order$mcS$sp$(this);
   }

   public Order order$mcV$sp() {
      return BoundedEnumerable.order$mcV$sp$(this);
   }

   public PartialOrder partialOrder$mcZ$sp() {
      return BoundedEnumerable.partialOrder$mcZ$sp$(this);
   }

   public PartialOrder partialOrder$mcC$sp() {
      return BoundedEnumerable.partialOrder$mcC$sp$(this);
   }

   public PartialOrder partialOrder$mcD$sp() {
      return BoundedEnumerable.partialOrder$mcD$sp$(this);
   }

   public PartialOrder partialOrder$mcF$sp() {
      return BoundedEnumerable.partialOrder$mcF$sp$(this);
   }

   public PartialOrder partialOrder$mcI$sp() {
      return BoundedEnumerable.partialOrder$mcI$sp$(this);
   }

   public PartialOrder partialOrder$mcJ$sp() {
      return BoundedEnumerable.partialOrder$mcJ$sp$(this);
   }

   public PartialOrder partialOrder$mcS$sp() {
      return BoundedEnumerable.partialOrder$mcS$sp$(this);
   }

   public PartialOrder partialOrder$mcV$sp() {
      return BoundedEnumerable.partialOrder$mcV$sp$(this);
   }

   public boolean cycleNext$mcZ$sp(final boolean a) {
      return BoundedEnumerable.cycleNext$mcZ$sp$(this, a);
   }

   public char cycleNext$mcC$sp(final char a) {
      return BoundedEnumerable.cycleNext$mcC$sp$(this, a);
   }

   public double cycleNext$mcD$sp(final double a) {
      return BoundedEnumerable.cycleNext$mcD$sp$(this, a);
   }

   public float cycleNext$mcF$sp(final float a) {
      return BoundedEnumerable.cycleNext$mcF$sp$(this, a);
   }

   public int cycleNext$mcI$sp(final int a) {
      return BoundedEnumerable.cycleNext$mcI$sp$(this, a);
   }

   public long cycleNext$mcJ$sp(final long a) {
      return BoundedEnumerable.cycleNext$mcJ$sp$(this, a);
   }

   public short cycleNext$mcS$sp(final short a) {
      return BoundedEnumerable.cycleNext$mcS$sp$(this, a);
   }

   public void cycleNext$mcV$sp(final BoxedUnit a) {
      BoundedEnumerable.cycleNext$mcV$sp$(this, a);
   }

   public boolean cyclePrevious$mcZ$sp(final boolean a) {
      return BoundedEnumerable.cyclePrevious$mcZ$sp$(this, a);
   }

   public char cyclePrevious$mcC$sp(final char a) {
      return BoundedEnumerable.cyclePrevious$mcC$sp$(this, a);
   }

   public double cyclePrevious$mcD$sp(final double a) {
      return BoundedEnumerable.cyclePrevious$mcD$sp$(this, a);
   }

   public float cyclePrevious$mcF$sp(final float a) {
      return BoundedEnumerable.cyclePrevious$mcF$sp$(this, a);
   }

   public int cyclePrevious$mcI$sp(final int a) {
      return BoundedEnumerable.cyclePrevious$mcI$sp$(this, a);
   }

   public long cyclePrevious$mcJ$sp(final long a) {
      return BoundedEnumerable.cyclePrevious$mcJ$sp$(this, a);
   }

   public short cyclePrevious$mcS$sp(final short a) {
      return BoundedEnumerable.cyclePrevious$mcS$sp$(this, a);
   }

   public void cyclePrevious$mcV$sp(final BoxedUnit a) {
      BoundedEnumerable.cyclePrevious$mcV$sp$(this, a);
   }

   public LazyList membersAscending() {
      return PartialNextLowerBounded.membersAscending$(this);
   }

   public LazyList membersDescending() {
      return PartialPreviousUpperBounded.membersDescending$(this);
   }

   public Option partialNext$mcZ$sp(final boolean a) {
      return PartialNext.partialNext$mcZ$sp$(this, a);
   }

   public Option partialNext$mcC$sp(final char a) {
      return PartialNext.partialNext$mcC$sp$(this, a);
   }

   public Option partialNext$mcD$sp(final double a) {
      return PartialNext.partialNext$mcD$sp$(this, a);
   }

   public Option partialNext$mcF$sp(final float a) {
      return PartialNext.partialNext$mcF$sp$(this, a);
   }

   public Option partialNext$mcI$sp(final int a) {
      return PartialNext.partialNext$mcI$sp$(this, a);
   }

   public Option partialNext$mcJ$sp(final long a) {
      return PartialNext.partialNext$mcJ$sp$(this, a);
   }

   public Option partialNext$mcS$sp(final short a) {
      return PartialNext.partialNext$mcS$sp$(this, a);
   }

   public Option partialNext$mcV$sp(final BoxedUnit a) {
      return PartialNext.partialNext$mcV$sp$(this, a);
   }

   public Option partialPrevious$mcZ$sp(final boolean a) {
      return PartialPrevious.partialPrevious$mcZ$sp$(this, a);
   }

   public Option partialPrevious$mcC$sp(final char a) {
      return PartialPrevious.partialPrevious$mcC$sp$(this, a);
   }

   public Option partialPrevious$mcD$sp(final double a) {
      return PartialPrevious.partialPrevious$mcD$sp$(this, a);
   }

   public Option partialPrevious$mcF$sp(final float a) {
      return PartialPrevious.partialPrevious$mcF$sp$(this, a);
   }

   public Option partialPrevious$mcI$sp(final int a) {
      return PartialPrevious.partialPrevious$mcI$sp$(this, a);
   }

   public Option partialPrevious$mcJ$sp(final long a) {
      return PartialPrevious.partialPrevious$mcJ$sp$(this, a);
   }

   public Option partialPrevious$mcS$sp(final short a) {
      return PartialPrevious.partialPrevious$mcS$sp$(this, a);
   }

   public Option partialPrevious$mcV$sp(final BoxedUnit a) {
      return PartialPrevious.partialPrevious$mcV$sp$(this, a);
   }

   public byte minBound() {
      return ByteBounded.minBound$(this);
   }

   public byte maxBound() {
      return ByteBounded.maxBound$(this);
   }

   public byte minBound$mcB$sp() {
      return ByteBounded.minBound$mcB$sp$(this);
   }

   public byte maxBound$mcB$sp() {
      return ByteBounded.maxBound$mcB$sp$(this);
   }

   public boolean maxBound$mcZ$sp() {
      return UpperBounded.maxBound$mcZ$sp$(this);
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

   public Comparison comparison(final byte x, final byte y) {
      return Order$mcB$sp.comparison$(this, x, y);
   }

   public Comparison comparison$mcB$sp(final byte x, final byte y) {
      return Order$mcB$sp.comparison$mcB$sp$(this, x, y);
   }

   public double partialCompare(final byte x, final byte y) {
      return Order$mcB$sp.partialCompare$(this, x, y);
   }

   public double partialCompare$mcB$sp(final byte x, final byte y) {
      return Order$mcB$sp.partialCompare$mcB$sp$(this, x, y);
   }

   public Option partialComparison(final byte x, final byte y) {
      return PartialOrder$mcB$sp.partialComparison$(this, x, y);
   }

   public Option partialComparison$mcB$sp(final byte x, final byte y) {
      return PartialOrder$mcB$sp.partialComparison$mcB$sp$(this, x, y);
   }

   public Option tryCompare(final byte x, final byte y) {
      return PartialOrder$mcB$sp.tryCompare$(this, x, y);
   }

   public Option tryCompare$mcB$sp(final byte x, final byte y) {
      return PartialOrder$mcB$sp.tryCompare$mcB$sp$(this, x, y);
   }

   public Option pmin(final byte x, final byte y) {
      return PartialOrder$mcB$sp.pmin$(this, x, y);
   }

   public Option pmin$mcB$sp(final byte x, final byte y) {
      return PartialOrder$mcB$sp.pmin$mcB$sp$(this, x, y);
   }

   public Option pmax(final byte x, final byte y) {
      return PartialOrder$mcB$sp.pmax$(this, x, y);
   }

   public Option pmax$mcB$sp(final byte x, final byte y) {
      return PartialOrder$mcB$sp.pmax$mcB$sp$(this, x, y);
   }

   public int compare$mcZ$sp(final boolean x, final boolean y) {
      return Order.compare$mcZ$sp$(this, x, y);
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

   public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
      return Order.comparison$mcZ$sp$(this, x, y);
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

   public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
      return Order.partialCompare$mcZ$sp$(this, x, y);
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

   public boolean min$mcZ$sp(final boolean x, final boolean y) {
      return Order.min$mcZ$sp$(this, x, y);
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

   public boolean max$mcZ$sp(final boolean x, final boolean y) {
      return Order.max$mcZ$sp$(this, x, y);
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

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.neqv$mcZ$sp$(this, x, y);
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

   public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.lteqv$mcZ$sp$(this, x, y);
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

   public boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return Order.lt$mcZ$sp$(this, x, y);
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

   public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return Order.gteqv$mcZ$sp$(this, x, y);
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

   public boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return Order.gt$mcZ$sp$(this, x, y);
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

   public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
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

   public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
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

   public Option pmin$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmin$mcZ$sp$(this, x, y);
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

   public Option pmax$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder.pmax$mcZ$sp$(this, x, y);
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

   public int hash(final byte x) {
      return this.hash$mcB$sp(x);
   }

   public int compare(final byte x, final byte y) {
      return this.compare$mcB$sp(x, y);
   }

   public boolean eqv(final byte x, final byte y) {
      return this.eqv$mcB$sp(x, y);
   }

   public boolean neqv(final byte x, final byte y) {
      return this.neqv$mcB$sp(x, y);
   }

   public boolean gt(final byte x, final byte y) {
      return this.gt$mcB$sp(x, y);
   }

   public boolean gteqv(final byte x, final byte y) {
      return this.gteqv$mcB$sp(x, y);
   }

   public boolean lt(final byte x, final byte y) {
      return this.lt$mcB$sp(x, y);
   }

   public boolean lteqv(final byte x, final byte y) {
      return this.lteqv$mcB$sp(x, y);
   }

   public byte min(final byte x, final byte y) {
      return this.min$mcB$sp(x, y);
   }

   public byte max(final byte x, final byte y) {
      return this.max$mcB$sp(x, y);
   }

   public Order order() {
      return this.order$mcB$sp();
   }

   public int hash$mcB$sp(final byte x) {
      return Byte.hashCode(x);
   }

   public int compare$mcB$sp(final byte x, final byte y) {
      return x < y ? -1 : (x > y ? 1 : 0);
   }

   public boolean eqv$mcB$sp(final byte x, final byte y) {
      return x == y;
   }

   public boolean neqv$mcB$sp(final byte x, final byte y) {
      return x != y;
   }

   public boolean gt$mcB$sp(final byte x, final byte y) {
      return x > y;
   }

   public boolean gteqv$mcB$sp(final byte x, final byte y) {
      return x >= y;
   }

   public boolean lt$mcB$sp(final byte x, final byte y) {
      return x < y;
   }

   public boolean lteqv$mcB$sp(final byte x, final byte y) {
      return x <= y;
   }

   public byte min$mcB$sp(final byte x, final byte y) {
      return (byte)Math.min(x, y);
   }

   public byte max$mcB$sp(final byte x, final byte y) {
      return (byte)Math.max(x, y);
   }

   public Order order$mcB$sp() {
      return this.order;
   }

   public boolean specInstance$() {
      return true;
   }

   public ByteOrder() {
      Eq.$init$(this);
      PartialOrder.$init$(this);
      Order.$init$(this);
      ByteBounded.$init$(this);
      PartialPreviousUpperBounded.$init$(this);
      PartialNextLowerBounded.$init$(this);
      BoundedEnumerable.$init$(this);
      ByteEnumerable.$init$(this);
      this.order = this;
   }
}
