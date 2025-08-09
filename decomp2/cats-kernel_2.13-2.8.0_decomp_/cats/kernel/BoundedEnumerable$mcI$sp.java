package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcI$sp extends BoundedEnumerable, PartialNextLowerBounded$mcI$sp, PartialPreviousUpperBounded$mcI$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcI$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcI$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final BoundedEnumerable$mcI$sp $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.order$mcI$sp();
   }

   // $FF: synthetic method
   static int cycleNext$(final BoundedEnumerable$mcI$sp $this, final int a) {
      return $this.cycleNext(a);
   }

   default int cycleNext(final int a) {
      return this.cycleNext$mcI$sp(a);
   }

   // $FF: synthetic method
   static int cycleNext$mcI$sp$(final BoundedEnumerable$mcI$sp $this, final int a) {
      return $this.cycleNext$mcI$sp(a);
   }

   default int cycleNext$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.partialNext$mcI$sp(a).getOrElse((JFunction0.mcI.sp)() -> this.minBound$mcI$sp()));
   }

   // $FF: synthetic method
   static int cyclePrevious$(final BoundedEnumerable$mcI$sp $this, final int a) {
      return $this.cyclePrevious(a);
   }

   default int cyclePrevious(final int a) {
      return this.cyclePrevious$mcI$sp(a);
   }

   // $FF: synthetic method
   static int cyclePrevious$mcI$sp$(final BoundedEnumerable$mcI$sp $this, final int a) {
      return $this.cyclePrevious$mcI$sp(a);
   }

   default int cyclePrevious$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.partialPrevious$mcI$sp(a).getOrElse((JFunction0.mcI.sp)() -> this.maxBound$mcI$sp()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
