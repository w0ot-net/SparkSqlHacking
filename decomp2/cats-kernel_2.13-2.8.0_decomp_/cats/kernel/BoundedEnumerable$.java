package cats.kernel;

import cats.kernel.instances.unit.package$;
import scala.Option;
import scala.collection.immutable.LazyList;
import scala.runtime.BoxedUnit;

public final class BoundedEnumerable$ {
   public static final BoundedEnumerable$ MODULE$ = new BoundedEnumerable$();

   public BoundedEnumerable catsKernelBoundedEnumerableForUnit() {
      return (BoundedEnumerable)package$.MODULE$.catsKernelStdOrderForUnit();
   }

   public BoundedEnumerable catsKernelBoundedEnumerableForBoolean() {
      return (BoundedEnumerable)cats.kernel.instances.boolean.package$.MODULE$.catsKernelStdOrderForBoolean();
   }

   public BoundedEnumerable catsKernelBoundedEnumerableForByte() {
      return (BoundedEnumerable)cats.kernel.instances.byte.package$.MODULE$.catsKernelStdOrderForByte();
   }

   public BoundedEnumerable catsKernelBoundedEnumerableForInt() {
      return (BoundedEnumerable)cats.kernel.instances.int.package$.MODULE$.catsKernelStdOrderForInt();
   }

   public BoundedEnumerable catsKernelBoundedEnumerableForShort() {
      return (BoundedEnumerable)cats.kernel.instances.short.package$.MODULE$.catsKernelStdOrderForShort();
   }

   public BoundedEnumerable catsKernelBoundedEnumerableForLong() {
      return (BoundedEnumerable)cats.kernel.instances.long.package$.MODULE$.catsKernelStdOrderForLong();
   }

   public BoundedEnumerable catsKernelBoundedEnumerableForChar() {
      return cats.kernel.instances.char.package$.MODULE$.catsKernelStdOrderForChar();
   }

   public BoundedEnumerable apply(final BoundedEnumerable e) {
      return e;
   }

   public BoundedEnumerable reverse(final BoundedEnumerable e) {
      return new BoundedEnumerable(e) {
         private final BoundedEnumerable e$1;

         public Order order$mcZ$sp() {
            return BoundedEnumerable.order$mcZ$sp$(this);
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

         public PartialOrder partialOrder() {
            return BoundedEnumerable.partialOrder$(this);
         }

         public PartialOrder partialOrder$mcZ$sp() {
            return BoundedEnumerable.partialOrder$mcZ$sp$(this);
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

         public Object cycleNext(final Object a) {
            return BoundedEnumerable.cycleNext$(this, a);
         }

         public boolean cycleNext$mcZ$sp(final boolean a) {
            return BoundedEnumerable.cycleNext$mcZ$sp$(this, a);
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

         public Object cyclePrevious(final Object a) {
            return BoundedEnumerable.cyclePrevious$(this, a);
         }

         public boolean cyclePrevious$mcZ$sp(final boolean a) {
            return BoundedEnumerable.cyclePrevious$mcZ$sp$(this, a);
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

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public Option partialNext$mcZ$sp(final boolean a) {
            return PartialNext.partialNext$mcZ$sp$(this, a);
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

         public Option partialPrevious$mcZ$sp(final boolean a) {
            return PartialPrevious.partialPrevious$mcZ$sp$(this, a);
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

         public Order order() {
            return Order$.MODULE$.reverse(this.e$1.order());
         }

         public Option partialNext(final Object a) {
            return this.e$1.partialPrevious(a);
         }

         public Option partialPrevious(final Object a) {
            return this.e$1.partialNext(a);
         }

         public Object minBound() {
            return this.e$1.maxBound();
         }

         public Object maxBound() {
            return this.e$1.minBound();
         }

         public {
            this.e$1 = e$1;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mZc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcZ$sp(e) {
         private final BoundedEnumerable e$2;

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

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public Order order() {
            return this.order$mcZ$sp();
         }

         public Option partialNext(final boolean a) {
            return this.partialNext$mcZ$sp(a);
         }

         public Option partialPrevious(final boolean a) {
            return this.partialPrevious$mcZ$sp(a);
         }

         public boolean minBound() {
            return this.minBound$mcZ$sp();
         }

         public boolean maxBound() {
            return this.maxBound$mcZ$sp();
         }

         public Order order$mcZ$sp() {
            return Order$.MODULE$.reverse$mZc$sp(this.e$2.order$mcZ$sp());
         }

         public Option partialNext$mcZ$sp(final boolean a) {
            return this.e$2.partialPrevious$mcZ$sp(a);
         }

         public Option partialPrevious$mcZ$sp(final boolean a) {
            return this.e$2.partialNext$mcZ$sp(a);
         }

         public boolean minBound$mcZ$sp() {
            return this.e$2.maxBound$mcZ$sp();
         }

         public boolean maxBound$mcZ$sp() {
            return this.e$2.minBound$mcZ$sp();
         }

         public {
            this.e$2 = e$2;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mBc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcB$sp(e) {
         private final BoundedEnumerable e$3;

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

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public Order order() {
            return this.order$mcB$sp();
         }

         public Option partialNext(final byte a) {
            return this.partialNext$mcB$sp(a);
         }

         public Option partialPrevious(final byte a) {
            return this.partialPrevious$mcB$sp(a);
         }

         public byte minBound() {
            return this.minBound$mcB$sp();
         }

         public byte maxBound() {
            return this.maxBound$mcB$sp();
         }

         public Order order$mcB$sp() {
            return Order$.MODULE$.reverse$mBc$sp(this.e$3.order$mcB$sp());
         }

         public Option partialNext$mcB$sp(final byte a) {
            return this.e$3.partialPrevious$mcB$sp(a);
         }

         public Option partialPrevious$mcB$sp(final byte a) {
            return this.e$3.partialNext$mcB$sp(a);
         }

         public byte minBound$mcB$sp() {
            return this.e$3.maxBound$mcB$sp();
         }

         public byte maxBound$mcB$sp() {
            return this.e$3.minBound$mcB$sp();
         }

         public {
            this.e$3 = e$3;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mCc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcC$sp(e) {
         private final BoundedEnumerable e$4;

         public PartialOrder partialOrder() {
            return BoundedEnumerable$mcC$sp.partialOrder$(this);
         }

         public PartialOrder partialOrder$mcC$sp() {
            return BoundedEnumerable$mcC$sp.partialOrder$mcC$sp$(this);
         }

         public char cycleNext(final char a) {
            return BoundedEnumerable$mcC$sp.cycleNext$(this, a);
         }

         public char cycleNext$mcC$sp(final char a) {
            return BoundedEnumerable$mcC$sp.cycleNext$mcC$sp$(this, a);
         }

         public char cyclePrevious(final char a) {
            return BoundedEnumerable$mcC$sp.cyclePrevious$(this, a);
         }

         public char cyclePrevious$mcC$sp(final char a) {
            return BoundedEnumerable$mcC$sp.cyclePrevious$mcC$sp$(this, a);
         }

         public Order order$mcZ$sp() {
            return BoundedEnumerable.order$mcZ$sp$(this);
         }

         public Order order$mcB$sp() {
            return BoundedEnumerable.order$mcB$sp$(this);
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

         public PartialOrder partialOrder$mcB$sp() {
            return BoundedEnumerable.partialOrder$mcB$sp$(this);
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

         public byte cycleNext$mcB$sp(final byte a) {
            return BoundedEnumerable.cycleNext$mcB$sp$(this, a);
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

         public byte cyclePrevious$mcB$sp(final byte a) {
            return BoundedEnumerable.cyclePrevious$mcB$sp$(this, a);
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

         public boolean minBound$mcZ$sp() {
            return LowerBounded.minBound$mcZ$sp$(this);
         }

         public byte minBound$mcB$sp() {
            return LowerBounded.minBound$mcB$sp$(this);
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

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
         }

         public boolean maxBound$mcZ$sp() {
            return UpperBounded.maxBound$mcZ$sp$(this);
         }

         public byte maxBound$mcB$sp() {
            return UpperBounded.maxBound$mcB$sp$(this);
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

         public Option partialNext$mcZ$sp(final boolean a) {
            return PartialNext.partialNext$mcZ$sp$(this, a);
         }

         public Option partialNext$mcB$sp(final byte a) {
            return PartialNext.partialNext$mcB$sp$(this, a);
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

         public Option partialPrevious$mcB$sp(final byte a) {
            return PartialPrevious.partialPrevious$mcB$sp$(this, a);
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

         public Order order() {
            return this.order$mcC$sp();
         }

         public Option partialNext(final char a) {
            return this.partialNext$mcC$sp(a);
         }

         public Option partialPrevious(final char a) {
            return this.partialPrevious$mcC$sp(a);
         }

         public char minBound() {
            return this.minBound$mcC$sp();
         }

         public char maxBound() {
            return this.maxBound$mcC$sp();
         }

         public Order order$mcC$sp() {
            return Order$.MODULE$.reverse$mCc$sp(this.e$4.order$mcC$sp());
         }

         public Option partialNext$mcC$sp(final char a) {
            return this.e$4.partialPrevious$mcC$sp(a);
         }

         public Option partialPrevious$mcC$sp(final char a) {
            return this.e$4.partialNext$mcC$sp(a);
         }

         public char minBound$mcC$sp() {
            return this.e$4.maxBound$mcC$sp();
         }

         public char maxBound$mcC$sp() {
            return this.e$4.minBound$mcC$sp();
         }

         public {
            this.e$4 = e$4;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mDc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcD$sp(e) {
         private final BoundedEnumerable e$5;

         public PartialOrder partialOrder() {
            return BoundedEnumerable$mcD$sp.partialOrder$(this);
         }

         public PartialOrder partialOrder$mcD$sp() {
            return BoundedEnumerable$mcD$sp.partialOrder$mcD$sp$(this);
         }

         public double cycleNext(final double a) {
            return BoundedEnumerable$mcD$sp.cycleNext$(this, a);
         }

         public double cycleNext$mcD$sp(final double a) {
            return BoundedEnumerable$mcD$sp.cycleNext$mcD$sp$(this, a);
         }

         public double cyclePrevious(final double a) {
            return BoundedEnumerable$mcD$sp.cyclePrevious$(this, a);
         }

         public double cyclePrevious$mcD$sp(final double a) {
            return BoundedEnumerable$mcD$sp.cyclePrevious$mcD$sp$(this, a);
         }

         public Order order$mcZ$sp() {
            return BoundedEnumerable.order$mcZ$sp$(this);
         }

         public Order order$mcB$sp() {
            return BoundedEnumerable.order$mcB$sp$(this);
         }

         public Order order$mcC$sp() {
            return BoundedEnumerable.order$mcC$sp$(this);
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

         public PartialOrder partialOrder$mcB$sp() {
            return BoundedEnumerable.partialOrder$mcB$sp$(this);
         }

         public PartialOrder partialOrder$mcC$sp() {
            return BoundedEnumerable.partialOrder$mcC$sp$(this);
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

         public byte cycleNext$mcB$sp(final byte a) {
            return BoundedEnumerable.cycleNext$mcB$sp$(this, a);
         }

         public char cycleNext$mcC$sp(final char a) {
            return BoundedEnumerable.cycleNext$mcC$sp$(this, a);
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

         public byte cyclePrevious$mcB$sp(final byte a) {
            return BoundedEnumerable.cyclePrevious$mcB$sp$(this, a);
         }

         public char cyclePrevious$mcC$sp(final char a) {
            return BoundedEnumerable.cyclePrevious$mcC$sp$(this, a);
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

         public boolean minBound$mcZ$sp() {
            return LowerBounded.minBound$mcZ$sp$(this);
         }

         public byte minBound$mcB$sp() {
            return LowerBounded.minBound$mcB$sp$(this);
         }

         public char minBound$mcC$sp() {
            return LowerBounded.minBound$mcC$sp$(this);
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

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public Option partialNext$mcZ$sp(final boolean a) {
            return PartialNext.partialNext$mcZ$sp$(this, a);
         }

         public Option partialNext$mcB$sp(final byte a) {
            return PartialNext.partialNext$mcB$sp$(this, a);
         }

         public Option partialNext$mcC$sp(final char a) {
            return PartialNext.partialNext$mcC$sp$(this, a);
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

         public Option partialPrevious$mcB$sp(final byte a) {
            return PartialPrevious.partialPrevious$mcB$sp$(this, a);
         }

         public Option partialPrevious$mcC$sp(final char a) {
            return PartialPrevious.partialPrevious$mcC$sp$(this, a);
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

         public Order order() {
            return this.order$mcD$sp();
         }

         public Option partialNext(final double a) {
            return this.partialNext$mcD$sp(a);
         }

         public Option partialPrevious(final double a) {
            return this.partialPrevious$mcD$sp(a);
         }

         public double minBound() {
            return this.minBound$mcD$sp();
         }

         public double maxBound() {
            return this.maxBound$mcD$sp();
         }

         public Order order$mcD$sp() {
            return Order$.MODULE$.reverse$mDc$sp(this.e$5.order$mcD$sp());
         }

         public Option partialNext$mcD$sp(final double a) {
            return this.e$5.partialPrevious$mcD$sp(a);
         }

         public Option partialPrevious$mcD$sp(final double a) {
            return this.e$5.partialNext$mcD$sp(a);
         }

         public double minBound$mcD$sp() {
            return this.e$5.maxBound$mcD$sp();
         }

         public double maxBound$mcD$sp() {
            return this.e$5.minBound$mcD$sp();
         }

         public {
            this.e$5 = e$5;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mFc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcF$sp(e) {
         private final BoundedEnumerable e$6;

         public PartialOrder partialOrder() {
            return BoundedEnumerable$mcF$sp.partialOrder$(this);
         }

         public PartialOrder partialOrder$mcF$sp() {
            return BoundedEnumerable$mcF$sp.partialOrder$mcF$sp$(this);
         }

         public float cycleNext(final float a) {
            return BoundedEnumerable$mcF$sp.cycleNext$(this, a);
         }

         public float cycleNext$mcF$sp(final float a) {
            return BoundedEnumerable$mcF$sp.cycleNext$mcF$sp$(this, a);
         }

         public float cyclePrevious(final float a) {
            return BoundedEnumerable$mcF$sp.cyclePrevious$(this, a);
         }

         public float cyclePrevious$mcF$sp(final float a) {
            return BoundedEnumerable$mcF$sp.cyclePrevious$mcF$sp$(this, a);
         }

         public Order order$mcZ$sp() {
            return BoundedEnumerable.order$mcZ$sp$(this);
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

         public PartialOrder partialOrder$mcB$sp() {
            return BoundedEnumerable.partialOrder$mcB$sp$(this);
         }

         public PartialOrder partialOrder$mcC$sp() {
            return BoundedEnumerable.partialOrder$mcC$sp$(this);
         }

         public PartialOrder partialOrder$mcD$sp() {
            return BoundedEnumerable.partialOrder$mcD$sp$(this);
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

         public byte cycleNext$mcB$sp(final byte a) {
            return BoundedEnumerable.cycleNext$mcB$sp$(this, a);
         }

         public char cycleNext$mcC$sp(final char a) {
            return BoundedEnumerable.cycleNext$mcC$sp$(this, a);
         }

         public double cycleNext$mcD$sp(final double a) {
            return BoundedEnumerable.cycleNext$mcD$sp$(this, a);
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

         public byte cyclePrevious$mcB$sp(final byte a) {
            return BoundedEnumerable.cyclePrevious$mcB$sp$(this, a);
         }

         public char cyclePrevious$mcC$sp(final char a) {
            return BoundedEnumerable.cyclePrevious$mcC$sp$(this, a);
         }

         public double cyclePrevious$mcD$sp(final double a) {
            return BoundedEnumerable.cyclePrevious$mcD$sp$(this, a);
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

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public Option partialNext$mcZ$sp(final boolean a) {
            return PartialNext.partialNext$mcZ$sp$(this, a);
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

         public Option partialPrevious$mcB$sp(final byte a) {
            return PartialPrevious.partialPrevious$mcB$sp$(this, a);
         }

         public Option partialPrevious$mcC$sp(final char a) {
            return PartialPrevious.partialPrevious$mcC$sp$(this, a);
         }

         public Option partialPrevious$mcD$sp(final double a) {
            return PartialPrevious.partialPrevious$mcD$sp$(this, a);
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

         public Order order() {
            return this.order$mcF$sp();
         }

         public Option partialNext(final float a) {
            return this.partialNext$mcF$sp(a);
         }

         public Option partialPrevious(final float a) {
            return this.partialPrevious$mcF$sp(a);
         }

         public float minBound() {
            return this.minBound$mcF$sp();
         }

         public float maxBound() {
            return this.maxBound$mcF$sp();
         }

         public Order order$mcF$sp() {
            return Order$.MODULE$.reverse$mFc$sp(this.e$6.order$mcF$sp());
         }

         public Option partialNext$mcF$sp(final float a) {
            return this.e$6.partialPrevious$mcF$sp(a);
         }

         public Option partialPrevious$mcF$sp(final float a) {
            return this.e$6.partialNext$mcF$sp(a);
         }

         public float minBound$mcF$sp() {
            return this.e$6.maxBound$mcF$sp();
         }

         public float maxBound$mcF$sp() {
            return this.e$6.minBound$mcF$sp();
         }

         public {
            this.e$6 = e$6;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mIc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcI$sp(e) {
         private final BoundedEnumerable e$7;

         public PartialOrder partialOrder() {
            return BoundedEnumerable$mcI$sp.partialOrder$(this);
         }

         public PartialOrder partialOrder$mcI$sp() {
            return BoundedEnumerable$mcI$sp.partialOrder$mcI$sp$(this);
         }

         public int cycleNext(final int a) {
            return BoundedEnumerable$mcI$sp.cycleNext$(this, a);
         }

         public int cycleNext$mcI$sp(final int a) {
            return BoundedEnumerable$mcI$sp.cycleNext$mcI$sp$(this, a);
         }

         public int cyclePrevious(final int a) {
            return BoundedEnumerable$mcI$sp.cyclePrevious$(this, a);
         }

         public int cyclePrevious$mcI$sp(final int a) {
            return BoundedEnumerable$mcI$sp.cyclePrevious$mcI$sp$(this, a);
         }

         public Order order$mcZ$sp() {
            return BoundedEnumerable.order$mcZ$sp$(this);
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

         public long minBound$mcJ$sp() {
            return LowerBounded.minBound$mcJ$sp$(this);
         }

         public short minBound$mcS$sp() {
            return LowerBounded.minBound$mcS$sp$(this);
         }

         public void minBound$mcV$sp() {
            LowerBounded.minBound$mcV$sp$(this);
         }

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public long maxBound$mcJ$sp() {
            return UpperBounded.maxBound$mcJ$sp$(this);
         }

         public short maxBound$mcS$sp() {
            return UpperBounded.maxBound$mcS$sp$(this);
         }

         public void maxBound$mcV$sp() {
            UpperBounded.maxBound$mcV$sp$(this);
         }

         public Option partialNext$mcZ$sp(final boolean a) {
            return PartialNext.partialNext$mcZ$sp$(this, a);
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

         public Option partialPrevious$mcJ$sp(final long a) {
            return PartialPrevious.partialPrevious$mcJ$sp$(this, a);
         }

         public Option partialPrevious$mcS$sp(final short a) {
            return PartialPrevious.partialPrevious$mcS$sp$(this, a);
         }

         public Option partialPrevious$mcV$sp(final BoxedUnit a) {
            return PartialPrevious.partialPrevious$mcV$sp$(this, a);
         }

         public Order order() {
            return this.order$mcI$sp();
         }

         public Option partialNext(final int a) {
            return this.partialNext$mcI$sp(a);
         }

         public Option partialPrevious(final int a) {
            return this.partialPrevious$mcI$sp(a);
         }

         public int minBound() {
            return this.minBound$mcI$sp();
         }

         public int maxBound() {
            return this.maxBound$mcI$sp();
         }

         public Order order$mcI$sp() {
            return Order$.MODULE$.reverse$mIc$sp(this.e$7.order$mcI$sp());
         }

         public Option partialNext$mcI$sp(final int a) {
            return this.e$7.partialPrevious$mcI$sp(a);
         }

         public Option partialPrevious$mcI$sp(final int a) {
            return this.e$7.partialNext$mcI$sp(a);
         }

         public int minBound$mcI$sp() {
            return this.e$7.maxBound$mcI$sp();
         }

         public int maxBound$mcI$sp() {
            return this.e$7.minBound$mcI$sp();
         }

         public {
            this.e$7 = e$7;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mJc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcJ$sp(e) {
         private final BoundedEnumerable e$8;

         public PartialOrder partialOrder() {
            return BoundedEnumerable$mcJ$sp.partialOrder$(this);
         }

         public PartialOrder partialOrder$mcJ$sp() {
            return BoundedEnumerable$mcJ$sp.partialOrder$mcJ$sp$(this);
         }

         public long cycleNext(final long a) {
            return BoundedEnumerable$mcJ$sp.cycleNext$(this, a);
         }

         public long cycleNext$mcJ$sp(final long a) {
            return BoundedEnumerable$mcJ$sp.cycleNext$mcJ$sp$(this, a);
         }

         public long cyclePrevious(final long a) {
            return BoundedEnumerable$mcJ$sp.cyclePrevious$(this, a);
         }

         public long cyclePrevious$mcJ$sp(final long a) {
            return BoundedEnumerable$mcJ$sp.cyclePrevious$mcJ$sp$(this, a);
         }

         public Order order$mcZ$sp() {
            return BoundedEnumerable.order$mcZ$sp$(this);
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

         public Order order$mcS$sp() {
            return BoundedEnumerable.order$mcS$sp$(this);
         }

         public Order order$mcV$sp() {
            return BoundedEnumerable.order$mcV$sp$(this);
         }

         public PartialOrder partialOrder$mcZ$sp() {
            return BoundedEnumerable.partialOrder$mcZ$sp$(this);
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

         public PartialOrder partialOrder$mcS$sp() {
            return BoundedEnumerable.partialOrder$mcS$sp$(this);
         }

         public PartialOrder partialOrder$mcV$sp() {
            return BoundedEnumerable.partialOrder$mcV$sp$(this);
         }

         public boolean cycleNext$mcZ$sp(final boolean a) {
            return BoundedEnumerable.cycleNext$mcZ$sp$(this, a);
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

         public short cycleNext$mcS$sp(final short a) {
            return BoundedEnumerable.cycleNext$mcS$sp$(this, a);
         }

         public void cycleNext$mcV$sp(final BoxedUnit a) {
            BoundedEnumerable.cycleNext$mcV$sp$(this, a);
         }

         public boolean cyclePrevious$mcZ$sp(final boolean a) {
            return BoundedEnumerable.cyclePrevious$mcZ$sp$(this, a);
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

         public short cyclePrevious$mcS$sp(final short a) {
            return BoundedEnumerable.cyclePrevious$mcS$sp$(this, a);
         }

         public void cyclePrevious$mcV$sp(final BoxedUnit a) {
            BoundedEnumerable.cyclePrevious$mcV$sp$(this, a);
         }

         public LazyList membersAscending() {
            return PartialNextLowerBounded.membersAscending$(this);
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

         public short minBound$mcS$sp() {
            return LowerBounded.minBound$mcS$sp$(this);
         }

         public void minBound$mcV$sp() {
            LowerBounded.minBound$mcV$sp$(this);
         }

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public short maxBound$mcS$sp() {
            return UpperBounded.maxBound$mcS$sp$(this);
         }

         public void maxBound$mcV$sp() {
            UpperBounded.maxBound$mcV$sp$(this);
         }

         public Option partialNext$mcZ$sp(final boolean a) {
            return PartialNext.partialNext$mcZ$sp$(this, a);
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

         public Option partialNext$mcS$sp(final short a) {
            return PartialNext.partialNext$mcS$sp$(this, a);
         }

         public Option partialNext$mcV$sp(final BoxedUnit a) {
            return PartialNext.partialNext$mcV$sp$(this, a);
         }

         public Option partialPrevious$mcZ$sp(final boolean a) {
            return PartialPrevious.partialPrevious$mcZ$sp$(this, a);
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

         public Option partialPrevious$mcS$sp(final short a) {
            return PartialPrevious.partialPrevious$mcS$sp$(this, a);
         }

         public Option partialPrevious$mcV$sp(final BoxedUnit a) {
            return PartialPrevious.partialPrevious$mcV$sp$(this, a);
         }

         public Order order() {
            return this.order$mcJ$sp();
         }

         public Option partialNext(final long a) {
            return this.partialNext$mcJ$sp(a);
         }

         public Option partialPrevious(final long a) {
            return this.partialPrevious$mcJ$sp(a);
         }

         public long minBound() {
            return this.minBound$mcJ$sp();
         }

         public long maxBound() {
            return this.maxBound$mcJ$sp();
         }

         public Order order$mcJ$sp() {
            return Order$.MODULE$.reverse$mJc$sp(this.e$8.order$mcJ$sp());
         }

         public Option partialNext$mcJ$sp(final long a) {
            return this.e$8.partialPrevious$mcJ$sp(a);
         }

         public Option partialPrevious$mcJ$sp(final long a) {
            return this.e$8.partialNext$mcJ$sp(a);
         }

         public long minBound$mcJ$sp() {
            return this.e$8.maxBound$mcJ$sp();
         }

         public long maxBound$mcJ$sp() {
            return this.e$8.minBound$mcJ$sp();
         }

         public {
            this.e$8 = e$8;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mSc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcS$sp(e) {
         private final BoundedEnumerable e$9;

         public PartialOrder partialOrder() {
            return BoundedEnumerable$mcS$sp.partialOrder$(this);
         }

         public PartialOrder partialOrder$mcS$sp() {
            return BoundedEnumerable$mcS$sp.partialOrder$mcS$sp$(this);
         }

         public short cycleNext(final short a) {
            return BoundedEnumerable$mcS$sp.cycleNext$(this, a);
         }

         public short cycleNext$mcS$sp(final short a) {
            return BoundedEnumerable$mcS$sp.cycleNext$mcS$sp$(this, a);
         }

         public short cyclePrevious(final short a) {
            return BoundedEnumerable$mcS$sp.cyclePrevious$(this, a);
         }

         public short cyclePrevious$mcS$sp(final short a) {
            return BoundedEnumerable$mcS$sp.cyclePrevious$mcS$sp$(this, a);
         }

         public Order order$mcZ$sp() {
            return BoundedEnumerable.order$mcZ$sp$(this);
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

         public Order order$mcV$sp() {
            return BoundedEnumerable.order$mcV$sp$(this);
         }

         public PartialOrder partialOrder$mcZ$sp() {
            return BoundedEnumerable.partialOrder$mcZ$sp$(this);
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

         public PartialOrder partialOrder$mcV$sp() {
            return BoundedEnumerable.partialOrder$mcV$sp$(this);
         }

         public boolean cycleNext$mcZ$sp(final boolean a) {
            return BoundedEnumerable.cycleNext$mcZ$sp$(this, a);
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

         public void cycleNext$mcV$sp(final BoxedUnit a) {
            BoundedEnumerable.cycleNext$mcV$sp$(this, a);
         }

         public boolean cyclePrevious$mcZ$sp(final boolean a) {
            return BoundedEnumerable.cyclePrevious$mcZ$sp$(this, a);
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

         public void cyclePrevious$mcV$sp(final BoxedUnit a) {
            BoundedEnumerable.cyclePrevious$mcV$sp$(this, a);
         }

         public LazyList membersAscending() {
            return PartialNextLowerBounded.membersAscending$(this);
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

         public void minBound$mcV$sp() {
            LowerBounded.minBound$mcV$sp$(this);
         }

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public void maxBound$mcV$sp() {
            UpperBounded.maxBound$mcV$sp$(this);
         }

         public Option partialNext$mcZ$sp(final boolean a) {
            return PartialNext.partialNext$mcZ$sp$(this, a);
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

         public Option partialNext$mcV$sp(final BoxedUnit a) {
            return PartialNext.partialNext$mcV$sp$(this, a);
         }

         public Option partialPrevious$mcZ$sp(final boolean a) {
            return PartialPrevious.partialPrevious$mcZ$sp$(this, a);
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

         public Option partialPrevious$mcV$sp(final BoxedUnit a) {
            return PartialPrevious.partialPrevious$mcV$sp$(this, a);
         }

         public Order order() {
            return this.order$mcS$sp();
         }

         public Option partialNext(final short a) {
            return this.partialNext$mcS$sp(a);
         }

         public Option partialPrevious(final short a) {
            return this.partialPrevious$mcS$sp(a);
         }

         public short minBound() {
            return this.minBound$mcS$sp();
         }

         public short maxBound() {
            return this.maxBound$mcS$sp();
         }

         public Order order$mcS$sp() {
            return Order$.MODULE$.reverse$mSc$sp(this.e$9.order$mcS$sp());
         }

         public Option partialNext$mcS$sp(final short a) {
            return this.e$9.partialPrevious$mcS$sp(a);
         }

         public Option partialPrevious$mcS$sp(final short a) {
            return this.e$9.partialNext$mcS$sp(a);
         }

         public short minBound$mcS$sp() {
            return this.e$9.maxBound$mcS$sp();
         }

         public short maxBound$mcS$sp() {
            return this.e$9.minBound$mcS$sp();
         }

         public {
            this.e$9 = e$9;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   public BoundedEnumerable reverse$mVc$sp(final BoundedEnumerable e) {
      return new BoundedEnumerable$mcV$sp(e) {
         private final BoundedEnumerable e$10;

         public PartialOrder partialOrder() {
            return BoundedEnumerable$mcV$sp.partialOrder$(this);
         }

         public PartialOrder partialOrder$mcV$sp() {
            return BoundedEnumerable$mcV$sp.partialOrder$mcV$sp$(this);
         }

         public void cycleNext(final BoxedUnit a) {
            BoundedEnumerable$mcV$sp.cycleNext$(this, a);
         }

         public void cycleNext$mcV$sp(final BoxedUnit a) {
            BoundedEnumerable$mcV$sp.cycleNext$mcV$sp$(this, a);
         }

         public void cyclePrevious(final BoxedUnit a) {
            BoundedEnumerable$mcV$sp.cyclePrevious$(this, a);
         }

         public void cyclePrevious$mcV$sp(final BoxedUnit a) {
            BoundedEnumerable$mcV$sp.cyclePrevious$mcV$sp$(this, a);
         }

         public Order order$mcZ$sp() {
            return BoundedEnumerable.order$mcZ$sp$(this);
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

         public PartialOrder partialOrder$mcZ$sp() {
            return BoundedEnumerable.partialOrder$mcZ$sp$(this);
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

         public boolean cycleNext$mcZ$sp(final boolean a) {
            return BoundedEnumerable.cycleNext$mcZ$sp$(this, a);
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

         public boolean cyclePrevious$mcZ$sp(final boolean a) {
            return BoundedEnumerable.cyclePrevious$mcZ$sp$(this, a);
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

         public LazyList membersAscending() {
            return PartialNextLowerBounded.membersAscending$(this);
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

         public LazyList membersDescending() {
            return PartialPreviousUpperBounded.membersDescending$(this);
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

         public Option partialNext$mcZ$sp(final boolean a) {
            return PartialNext.partialNext$mcZ$sp$(this, a);
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

         public Option partialPrevious$mcZ$sp(final boolean a) {
            return PartialPrevious.partialPrevious$mcZ$sp$(this, a);
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

         public Order order() {
            return this.order$mcV$sp();
         }

         public Option partialNext(final BoxedUnit a) {
            return this.partialNext$mcV$sp(a);
         }

         public Option partialPrevious(final BoxedUnit a) {
            return this.partialPrevious$mcV$sp(a);
         }

         public void minBound() {
            this.minBound$mcV$sp();
         }

         public void maxBound() {
            this.maxBound$mcV$sp();
         }

         public Order order$mcV$sp() {
            return Order$.MODULE$.reverse$mVc$sp(this.e$10.order$mcV$sp());
         }

         public Option partialNext$mcV$sp(final BoxedUnit a) {
            return this.e$10.partialPrevious$mcV$sp(a);
         }

         public Option partialPrevious$mcV$sp(final BoxedUnit a) {
            return this.e$10.partialNext$mcV$sp(a);
         }

         public void minBound$mcV$sp() {
            this.e$10.maxBound$mcV$sp();
         }

         public void maxBound$mcV$sp() {
            this.e$10.minBound$mcV$sp();
         }

         public {
            this.e$10 = e$10;
            PartialPreviousUpperBounded.$init$(this);
            PartialNextLowerBounded.$init$(this);
            BoundedEnumerable.$init$(this);
         }
      };
   }

   private BoundedEnumerable$() {
   }
}
