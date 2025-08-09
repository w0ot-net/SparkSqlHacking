package cats.kernel.instances;

import cats.kernel.Band;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005m3\u0001BB\u0004\u0011\u0002\u0007\u0005q!\u0004\u0005\u00061\u0001!\tA\u0007\u0005\u0006=\u0001!\u0019a\b\u0005\u0006m\u0001!\u0019a\u000e\u0005\u0006\t\u0002!\u0019!\u0012\u0005\u0006!\u0002!\u0019!\u0015\u0002\u0013\rVt7\r^5p]&s7\u000f^1oG\u0016\u001c(G\u0003\u0002\t\u0013\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0015-\taa[3s]\u0016d'\"\u0001\u0007\u0002\t\r\fGo]\n\u0004\u00019!\u0002CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\r\u0005\u0002\u0016-5\tq!\u0003\u0002\u0018\u000f\t\u0011b)\u001e8di&|g.\u00138ti\u0006t7-Z:4\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u000e\u0011\u0005=a\u0012BA\u000f\u0011\u0005\u0011)f.\u001b;\u00029\r\fGo]&fe:,G.T8o_&$gi\u001c:Gk:\u001cG/[8oaU\u0011\u0001E\u000b\u000b\u0003CM\u00022AI\u0012&\u001b\u0005I\u0011B\u0001\u0013\n\u0005\u0019iuN\\8jIB\u0019qB\n\u0015\n\u0005\u001d\u0002\"!\u0003$v]\u000e$\u0018n\u001c81!\tI#\u0006\u0004\u0001\u0005\u000b-\u0012!\u0019\u0001\u0017\u0003\u0003\u0005\u000b\"!\f\u0019\u0011\u0005=q\u0013BA\u0018\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aD\u0019\n\u0005I\u0002\"aA!os\")AG\u0001a\u0002k\u0005\tQ\nE\u0002#G!\nAdY1ug.+'O\\3m\u001b>tw.\u001b3G_J4UO\\2uS>t\u0017'F\u00029}\u0001#\"!\u000f\"\u0011\u0007\t\u001a#\b\u0005\u0003\u0010wuz\u0014B\u0001\u001f\u0011\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002*}\u0011)1f\u0001b\u0001YA\u0011\u0011\u0006\u0011\u0003\u0006\u0003\u000e\u0011\r\u0001\f\u0002\u0002\u0005\")Ag\u0001a\u0002\u0007B\u0019!eI \u00025\r\fGo]&fe:,GNQ1oI\u001a{'OR;oGRLwN\u001c\u0019\u0016\u0005\u0019cECA$N!\r\u0011\u0003JS\u0005\u0003\u0013&\u0011AAQ1oIB\u0019qBJ&\u0011\u0005%bE!B\u0016\u0005\u0005\u0004a\u0003\"\u0002(\u0005\u0001\by\u0015!A*\u0011\u0007\tB5*\u0001\u000edCR\u001c8*\u001a:oK2\u0014\u0015M\u001c3G_J4UO\\2uS>t\u0017'F\u0002S-b#\"aU-\u0011\u0007\tBE\u000b\u0005\u0003\u0010wU;\u0006CA\u0015W\t\u0015YSA1\u0001-!\tI\u0003\fB\u0003B\u000b\t\u0007A\u0006C\u0003O\u000b\u0001\u000f!\fE\u0002#\u0011^\u0003"
)
public interface FunctionInstances2 extends FunctionInstances3 {
   // $FF: synthetic method
   static Monoid catsKernelMonoidForFunction0$(final FunctionInstances2 $this, final Monoid M) {
      return $this.catsKernelMonoidForFunction0(M);
   }

   default Monoid catsKernelMonoidForFunction0(final Monoid M) {
      return new Function0Monoid(M) {
         private Function0 empty;
         private final Monoid M$5;

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

         public Function0 empty() {
            return this.empty;
         }

         public void cats$kernel$instances$Function0Monoid$_setter_$empty_$eq(final Function0 x$1) {
            this.empty = x$1;
         }

         public Monoid A() {
            return this.M$5;
         }

         public {
            this.M$5 = M$5;
            Semigroup.$init$(this);
            Function0Semigroup.$init$(this);
            Monoid.$init$(this);
            Function0Monoid.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForFunction1$(final FunctionInstances2 $this, final Monoid M) {
      return $this.catsKernelMonoidForFunction1(M);
   }

   default Monoid catsKernelMonoidForFunction1(final Monoid M) {
      return new Function1Monoid(M) {
         private Function1 empty;
         private final Monoid M$6;

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

         public Function1 empty() {
            return this.empty;
         }

         public void cats$kernel$instances$Function1Monoid$_setter_$empty_$eq(final Function1 x$1) {
            this.empty = x$1;
         }

         public Monoid B() {
            return this.M$6;
         }

         public {
            this.M$6 = M$6;
            Semigroup.$init$(this);
            Function1Semigroup.$init$(this);
            Monoid.$init$(this);
            Function1Monoid.$init$(this);
            Statics.releaseFence();
         }
      };
   }

   // $FF: synthetic method
   static Band catsKernelBandForFunction0$(final FunctionInstances2 $this, final Band S) {
      return $this.catsKernelBandForFunction0(S);
   }

   default Band catsKernelBandForFunction0(final Band S) {
      return new Function0Semigroup(S) {
         private final Band S$1;

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Function0 combine(final Function0 x, final Function0 y) {
            return Function0Semigroup.combine$(this, x, y);
         }

         public Option combineAllOption(final IterableOnce fns) {
            return Function0Semigroup.combineAllOption$(this, fns);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Semigroup reverse() {
            return Semigroup.reverse$(this);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
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

         public Semigroup A() {
            return this.S$1;
         }

         public {
            this.S$1 = S$1;
            Semigroup.$init$(this);
            Function0Semigroup.$init$(this);
            Band.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Band catsKernelBandForFunction1$(final FunctionInstances2 $this, final Band S) {
      return $this.catsKernelBandForFunction1(S);
   }

   default Band catsKernelBandForFunction1(final Band S) {
      return new Function1Semigroup(S) {
         private final Band S$2;

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Function1 combine(final Function1 x, final Function1 y) {
            return Function1Semigroup.combine$(this, x, y);
         }

         public Option combineAllOption(final IterableOnce fns) {
            return Function1Semigroup.combineAllOption$(this, fns);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Semigroup reverse() {
            return Semigroup.reverse$(this);
         }

         public Semigroup reverse$mcD$sp() {
            return Semigroup.reverse$mcD$sp$(this);
         }

         public Semigroup reverse$mcF$sp() {
            return Semigroup.reverse$mcF$sp$(this);
         }

         public Semigroup reverse$mcI$sp() {
            return Semigroup.reverse$mcI$sp$(this);
         }

         public Semigroup reverse$mcJ$sp() {
            return Semigroup.reverse$mcJ$sp$(this);
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

         public Semigroup B() {
            return this.S$2;
         }

         public {
            this.S$2 = S$2;
            Semigroup.$init$(this);
            Function1Semigroup.$init$(this);
            Band.$init$(this);
         }
      };
   }

   static void $init$(final FunctionInstances2 $this) {
   }
}
