package cats.kernel.instances;

import cats.kernel.CommutativeSemigroup;
import cats.kernel.Semigroup;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\u0019!\b\u0005\u0006i\u0001!\u0019!\u000e\u0002\u0013\rVt7\r^5p]&s7\u000f^1oG\u0016\u001c8G\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\n\u0004\u00011\u0011\u0002CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\r\u0005\u0002\u0014)5\tQ!\u0003\u0002\u0016\u000b\t\u0011b)\u001e8di&|g.\u00138ti\u0006t7-Z:5\u0003\u0019!\u0013N\\5uI\r\u0001A#A\r\u0011\u00055Q\u0012BA\u000e\u000f\u0005\u0011)f.\u001b;\u0002U\r\fGo]&fe:,GnQ8n[V$\u0018\r^5wKN+W.[4s_V\u0004hi\u001c:Gk:\u001cG/[8oaU\u0011a\u0004\u000b\u000b\u0003?E\u00022\u0001I\u0011$\u001b\u00059\u0011B\u0001\u0012\b\u0005Q\u0019u.\\7vi\u0006$\u0018N^3TK6LwM]8vaB\u0019Q\u0002\n\u0014\n\u0005\u0015r!!\u0003$v]\u000e$\u0018n\u001c81!\t9\u0003\u0006\u0004\u0001\u0005\u000b%\u0012!\u0019\u0001\u0016\u0003\u0003\u0005\u000b\"a\u000b\u0018\u0011\u00055a\u0013BA\u0017\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!D\u0018\n\u0005Ar!aA!os\")!G\u0001a\u0002g\u0005\t1\u000bE\u0002!C\u0019\n!fY1ug.+'O\\3m\u0007>lW.\u001e;bi&4XmU3nS\u001e\u0014x.\u001e9G_J4UO\\2uS>t\u0017'F\u00027yy\"\"a\u000e!\u0011\u0007\u0001\n\u0003\b\u0005\u0003\u000esmj\u0014B\u0001\u001e\u000f\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002(y\u0011)\u0011f\u0001b\u0001UA\u0011qE\u0010\u0003\u0006\u007f\r\u0011\rA\u000b\u0002\u0002\u0005\")!g\u0001a\u0002\u0003B\u0019\u0001%I\u001f"
)
public interface FunctionInstances3 extends FunctionInstances4 {
   // $FF: synthetic method
   static CommutativeSemigroup catsKernelCommutativeSemigroupForFunction0$(final FunctionInstances3 $this, final CommutativeSemigroup S) {
      return $this.catsKernelCommutativeSemigroupForFunction0(S);
   }

   default CommutativeSemigroup catsKernelCommutativeSemigroupForFunction0(final CommutativeSemigroup S) {
      return new Function0Semigroup(S) {
         private final CommutativeSemigroup S$3;

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
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

         public Semigroup A() {
            return this.S$3;
         }

         public {
            this.S$3 = S$3;
            Semigroup.$init$(this);
            Function0Semigroup.$init$(this);
            CommutativeSemigroup.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static CommutativeSemigroup catsKernelCommutativeSemigroupForFunction1$(final FunctionInstances3 $this, final CommutativeSemigroup S) {
      return $this.catsKernelCommutativeSemigroupForFunction1(S);
   }

   default CommutativeSemigroup catsKernelCommutativeSemigroupForFunction1(final CommutativeSemigroup S) {
      return new Function1Semigroup(S) {
         private final CommutativeSemigroup S$4;

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
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

         public Semigroup B() {
            return this.S$4;
         }

         public {
            this.S$4 = S$4;
            Semigroup.$init$(this);
            Function1Semigroup.$init$(this);
            CommutativeSemigroup.$init$(this);
         }
      };
   }

   static void $init$(final FunctionInstances3 $this) {
   }
}
