package cats.kernel.instances;

import cats.kernel.Semigroup;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006%\u0001!\t\u0001\u0006\u0005\u00061\u0001!\u0019!\u0007\u0005\u0006a\u0001!\u0019!\r\u0002\u0013\rVt7\r^5p]&s7\u000f^1oG\u0016\u001cHG\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\n\u0003\u00011\u0001\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003U\u0001\"!\u0004\f\n\u0005]q!\u0001B+oSR\fqdY1ug.+'O\\3m'\u0016l\u0017n\u001a:pkB4uN\u001d$v]\u000e$\u0018n\u001c81+\tQB\u0005\u0006\u0002\u001c[A\u0019A$H\u0010\u000e\u0003\u001dI!AH\u0004\u0003\u0013M+W.[4s_V\u0004\bcA\u0007!E%\u0011\u0011E\u0004\u0002\n\rVt7\r^5p]B\u0002\"a\t\u0013\r\u0001\u0011)QE\u0001b\u0001M\t\t\u0011)\u0005\u0002(UA\u0011Q\u0002K\u0005\u0003S9\u0011qAT8uQ&tw\r\u0005\u0002\u000eW%\u0011AF\u0004\u0002\u0004\u0003:L\b\"\u0002\u0018\u0003\u0001\by\u0013!A*\u0011\u0007qi\"%A\u0010dCR\u001c8*\u001a:oK2\u001cV-\\5he>,\bOR8s\rVt7\r^5p]F*2A\r\u001d;)\t\u0019D\bE\u0002\u001d;Q\u0002B!D\u001b8s%\u0011aG\u0004\u0002\n\rVt7\r^5p]F\u0002\"a\t\u001d\u0005\u000b\u0015\u001a!\u0019\u0001\u0014\u0011\u0005\rRD!B\u001e\u0004\u0005\u00041#!\u0001\"\t\u000b9\u001a\u00019A\u001f\u0011\u0007qi\u0012\b"
)
public interface FunctionInstances4 {
   // $FF: synthetic method
   static Semigroup catsKernelSemigroupForFunction0$(final FunctionInstances4 $this, final Semigroup S) {
      return $this.catsKernelSemigroupForFunction0(S);
   }

   default Semigroup catsKernelSemigroupForFunction0(final Semigroup S) {
      return new Function0Semigroup(S) {
         private final Semigroup S$5;

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
            return this.S$5;
         }

         public {
            this.S$5 = S$5;
            Semigroup.$init$(this);
            Function0Semigroup.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semigroup catsKernelSemigroupForFunction1$(final FunctionInstances4 $this, final Semigroup S) {
      return $this.catsKernelSemigroupForFunction1(S);
   }

   default Semigroup catsKernelSemigroupForFunction1(final Semigroup S) {
      return new Function1Semigroup(S) {
         private final Semigroup S$6;

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
            return this.S$6;
         }

         public {
            this.S$6 = S$6;
            Semigroup.$init$(this);
            Function1Semigroup.$init$(this);
         }
      };
   }

   static void $init$(final FunctionInstances4 $this) {
   }
}
