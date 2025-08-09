package cats.kernel.instances;

import cats.kernel.BoundedEnumerable;
import cats.kernel.BoundedEnumerable$mcZ$sp;
import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Hash;
import cats.kernel.Hash$mcZ$sp;
import cats.kernel.LowerBounded;
import cats.kernel.Order;
import cats.kernel.Order$mcZ$sp;
import cats.kernel.PartialNext;
import cats.kernel.PartialNextLowerBounded;
import cats.kernel.PartialOrder;
import cats.kernel.PartialOrder$mcZ$sp;
import cats.kernel.PartialPrevious;
import cats.kernel.PartialPreviousUpperBounded;
import cats.kernel.UpperBounded;
import scala.Option;
import scala.collection.immutable.LazyList;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014AAD\b\u0001-!)a\u0006\u0001C\u0001_!)\u0011\u0007\u0001C\u0001e!)\u0001\b\u0001C\u0001s!)Q\b\u0001C!}!)\u0011\t\u0001C!\u0005\")Q\t\u0001C!\r\")\u0011\n\u0001C!\u0015\")Q\n\u0001C!\u001d\")\u0011\u000b\u0001C!%\")Q\u000b\u0001C!-\")\u0011\f\u0001C!5\"9Q\f\u0001b\u0001\n\u0003r\u0006BB0\u0001A\u0003%QD\u0001\u0007C_>dW-\u00198Pe\u0012,'O\u0003\u0002\u0011#\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003%M\taa[3s]\u0016d'\"\u0001\u000b\u0002\t\r\fGo]\u0002\u0001'\u0019\u0001q#\b\u0013(WA\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u00042AH\u0010\"\u001b\u0005\t\u0012B\u0001\u0011\u0012\u0005\u0015y%\u000fZ3s!\tA\"%\u0003\u0002$3\t9!i\\8mK\u0006t\u0007c\u0001\u0010&C%\u0011a%\u0005\u0002\u0005\u0011\u0006\u001c\b\u000e\u0005\u0002)S5\tq\"\u0003\u0002+\u001f\tq!i\\8mK\u0006t'i\\;oI\u0016$\u0007C\u0001\u0015-\u0013\tisBA\tC_>dW-\u00198F]VlWM]1cY\u0016\fa\u0001P5oSRtD#\u0001\u0019\u0011\u0005!\u0002\u0011\u0001\u00025bg\"$\"a\r\u001c\u0011\u0005a!\u0014BA\u001b\u001a\u0005\rIe\u000e\u001e\u0005\u0006o\t\u0001\r!I\u0001\u0002q\u000691m\\7qCJ,GcA\u001a;w!)qg\u0001a\u0001C!)Ah\u0001a\u0001C\u0005\t\u00110A\u0002fcZ$2!I A\u0011\u00159D\u00011\u0001\"\u0011\u0015aD\u00011\u0001\"\u0003\u0011qW-\u001d<\u0015\u0007\u0005\u001aE\tC\u00038\u000b\u0001\u0007\u0011\u0005C\u0003=\u000b\u0001\u0007\u0011%\u0001\u0002hiR\u0019\u0011e\u0012%\t\u000b]2\u0001\u0019A\u0011\t\u000bq2\u0001\u0019A\u0011\u0002\u00051$HcA\u0011L\u0019\")qg\u0002a\u0001C!)Ah\u0002a\u0001C\u0005)q\r^3rmR\u0019\u0011e\u0014)\t\u000b]B\u0001\u0019A\u0011\t\u000bqB\u0001\u0019A\u0011\u0002\u000b1$X-\u001d<\u0015\u0007\u0005\u001aF\u000bC\u00038\u0013\u0001\u0007\u0011\u0005C\u0003=\u0013\u0001\u0007\u0011%A\u0002nS:$2!I,Y\u0011\u00159$\u00021\u0001\"\u0011\u0015a$\u00021\u0001\"\u0003\ri\u0017\r\u001f\u000b\u0004Cmc\u0006\"B\u001c\f\u0001\u0004\t\u0003\"\u0002\u001f\f\u0001\u0004\t\u0013!B8sI\u0016\u0014X#A\u000f\u0002\r=\u0014H-\u001a:!\u0001"
)
public class BooleanOrder implements Order$mcZ$sp, Hash$mcZ$sp, BooleanBounded, BooleanEnumerable {
   private final Order order;

   public Option partialNext(final boolean a) {
      return BooleanEnumerable.partialNext$(this, a);
   }

   public Option partialPrevious(final boolean a) {
      return BooleanEnumerable.partialPrevious$(this, a);
   }

   public Option partialNext$mcZ$sp(final boolean a) {
      return BooleanEnumerable.partialNext$mcZ$sp$(this, a);
   }

   public Option partialPrevious$mcZ$sp(final boolean a) {
      return BooleanEnumerable.partialPrevious$mcZ$sp$(this, a);
   }

   public PartialOrder partialOrder() {
      return BoundedEnumerable$mcZ$sp.partialOrder$(this);
   }

   public PartialOrder partialOrder$mcZ$sp() {
      return BoundedEnumerable$mcZ$sp.partialOrder$mcZ$sp$(this);
   }

   public boolean cycleNext(final boolean a) {
      return BoundedEnumerable$mcZ$sp.cycleNext$(this, a);
   }

   public boolean cycleNext$mcZ$sp(final boolean a) {
      return BoundedEnumerable$mcZ$sp.cycleNext$mcZ$sp$(this, a);
   }

   public boolean cyclePrevious(final boolean a) {
      return BoundedEnumerable$mcZ$sp.cyclePrevious$(this, a);
   }

   public boolean cyclePrevious$mcZ$sp(final boolean a) {
      return BoundedEnumerable$mcZ$sp.cyclePrevious$mcZ$sp$(this, a);
   }

   public Order order$mcB$sp() {
      return BoundedEnumerable.order$mcB$sp$(this);
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

   public PartialOrder partialOrder$mcB$sp() {
      return BoundedEnumerable.partialOrder$mcB$sp$(this);
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

   public byte cycleNext$mcB$sp(final byte a) {
      return BoundedEnumerable.cycleNext$mcB$sp$(this, a);
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

   public byte cyclePrevious$mcB$sp(final byte a) {
      return BoundedEnumerable.cyclePrevious$mcB$sp$(this, a);
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

   public Option partialNext$mcB$sp(final byte a) {
      return PartialNext.partialNext$mcB$sp$(this, a);
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

   public Option partialPrevious$mcB$sp(final byte a) {
      return PartialPrevious.partialPrevious$mcB$sp$(this, a);
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

   public boolean minBound() {
      return BooleanBounded.minBound$(this);
   }

   public boolean maxBound() {
      return BooleanBounded.maxBound$(this);
   }

   public boolean minBound$mcZ$sp() {
      return BooleanBounded.minBound$mcZ$sp$(this);
   }

   public boolean maxBound$mcZ$sp() {
      return BooleanBounded.maxBound$mcZ$sp$(this);
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

   public Comparison comparison(final boolean x, final boolean y) {
      return Order$mcZ$sp.comparison$(this, x, y);
   }

   public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
      return Order$mcZ$sp.comparison$mcZ$sp$(this, x, y);
   }

   public double partialCompare(final boolean x, final boolean y) {
      return Order$mcZ$sp.partialCompare$(this, x, y);
   }

   public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
      return Order$mcZ$sp.partialCompare$mcZ$sp$(this, x, y);
   }

   public Option partialComparison(final boolean x, final boolean y) {
      return PartialOrder$mcZ$sp.partialComparison$(this, x, y);
   }

   public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder$mcZ$sp.partialComparison$mcZ$sp$(this, x, y);
   }

   public Option tryCompare(final boolean x, final boolean y) {
      return PartialOrder$mcZ$sp.tryCompare$(this, x, y);
   }

   public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder$mcZ$sp.tryCompare$mcZ$sp$(this, x, y);
   }

   public Option pmin(final boolean x, final boolean y) {
      return PartialOrder$mcZ$sp.pmin$(this, x, y);
   }

   public Option pmin$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder$mcZ$sp.pmin$mcZ$sp$(this, x, y);
   }

   public Option pmax(final boolean x, final boolean y) {
      return PartialOrder$mcZ$sp.pmax$(this, x, y);
   }

   public Option pmax$mcZ$sp(final boolean x, final boolean y) {
      return PartialOrder$mcZ$sp.pmax$mcZ$sp$(this, x, y);
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

   public int hash(final boolean x) {
      return this.hash$mcZ$sp(x);
   }

   public int compare(final boolean x, final boolean y) {
      return this.compare$mcZ$sp(x, y);
   }

   public boolean eqv(final boolean x, final boolean y) {
      return this.eqv$mcZ$sp(x, y);
   }

   public boolean neqv(final boolean x, final boolean y) {
      return this.neqv$mcZ$sp(x, y);
   }

   public boolean gt(final boolean x, final boolean y) {
      return this.gt$mcZ$sp(x, y);
   }

   public boolean lt(final boolean x, final boolean y) {
      return this.lt$mcZ$sp(x, y);
   }

   public boolean gteqv(final boolean x, final boolean y) {
      return this.gteqv$mcZ$sp(x, y);
   }

   public boolean lteqv(final boolean x, final boolean y) {
      return this.lteqv$mcZ$sp(x, y);
   }

   public boolean min(final boolean x, final boolean y) {
      return this.min$mcZ$sp(x, y);
   }

   public boolean max(final boolean x, final boolean y) {
      return this.max$mcZ$sp(x, y);
   }

   public Order order() {
      return this.order$mcZ$sp();
   }

   public int hash$mcZ$sp(final boolean x) {
      return Boolean.hashCode(x);
   }

   public int compare$mcZ$sp(final boolean x, final boolean y) {
      return x == y ? 0 : (x ? 1 : -1);
   }

   public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return x == y;
   }

   public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return x != y;
   }

   public boolean gt$mcZ$sp(final boolean x, final boolean y) {
      return x && !y;
   }

   public boolean lt$mcZ$sp(final boolean x, final boolean y) {
      return !x && y;
   }

   public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
      return x == y || x;
   }

   public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
      return x == y || y;
   }

   public boolean min$mcZ$sp(final boolean x, final boolean y) {
      return x && y;
   }

   public boolean max$mcZ$sp(final boolean x, final boolean y) {
      return x || y;
   }

   public Order order$mcZ$sp() {
      return this.order;
   }

   public boolean specInstance$() {
      return true;
   }

   public BooleanOrder() {
      Eq.$init$(this);
      PartialOrder.$init$(this);
      Order.$init$(this);
      BooleanBounded.$init$(this);
      PartialPreviousUpperBounded.$init$(this);
      PartialNextLowerBounded.$init$(this);
      BoundedEnumerable.$init$(this);
      BooleanEnumerable.$init$(this);
      this.order = this;
   }
}
