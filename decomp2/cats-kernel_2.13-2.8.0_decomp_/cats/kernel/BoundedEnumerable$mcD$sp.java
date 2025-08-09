package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcD$sp extends BoundedEnumerable, PartialNextLowerBounded$mcD$sp, PartialPreviousUpperBounded$mcD$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcD$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcD$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final BoundedEnumerable$mcD$sp $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.order$mcD$sp();
   }

   // $FF: synthetic method
   static double cycleNext$(final BoundedEnumerable$mcD$sp $this, final double a) {
      return $this.cycleNext(a);
   }

   default double cycleNext(final double a) {
      return this.cycleNext$mcD$sp(a);
   }

   // $FF: synthetic method
   static double cycleNext$mcD$sp$(final BoundedEnumerable$mcD$sp $this, final double a) {
      return $this.cycleNext$mcD$sp(a);
   }

   default double cycleNext$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.partialNext$mcD$sp(a).getOrElse((JFunction0.mcD.sp)() -> this.minBound$mcD$sp()));
   }

   // $FF: synthetic method
   static double cyclePrevious$(final BoundedEnumerable$mcD$sp $this, final double a) {
      return $this.cyclePrevious(a);
   }

   default double cyclePrevious(final double a) {
      return this.cyclePrevious$mcD$sp(a);
   }

   // $FF: synthetic method
   static double cyclePrevious$mcD$sp$(final BoundedEnumerable$mcD$sp $this, final double a) {
      return $this.cyclePrevious$mcD$sp(a);
   }

   default double cyclePrevious$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.partialPrevious$mcD$sp(a).getOrElse((JFunction0.mcD.sp)() -> this.maxBound$mcD$sp()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
