package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcF$sp extends BoundedEnumerable, PartialNextLowerBounded$mcF$sp, PartialPreviousUpperBounded$mcF$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcF$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcF$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final BoundedEnumerable$mcF$sp $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.order$mcF$sp();
   }

   // $FF: synthetic method
   static float cycleNext$(final BoundedEnumerable$mcF$sp $this, final float a) {
      return $this.cycleNext(a);
   }

   default float cycleNext(final float a) {
      return this.cycleNext$mcF$sp(a);
   }

   // $FF: synthetic method
   static float cycleNext$mcF$sp$(final BoundedEnumerable$mcF$sp $this, final float a) {
      return $this.cycleNext$mcF$sp(a);
   }

   default float cycleNext$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.partialNext$mcF$sp(a).getOrElse((JFunction0.mcF.sp)() -> this.minBound$mcF$sp()));
   }

   // $FF: synthetic method
   static float cyclePrevious$(final BoundedEnumerable$mcF$sp $this, final float a) {
      return $this.cyclePrevious(a);
   }

   default float cyclePrevious(final float a) {
      return this.cyclePrevious$mcF$sp(a);
   }

   // $FF: synthetic method
   static float cyclePrevious$mcF$sp$(final BoundedEnumerable$mcF$sp $this, final float a) {
      return $this.cyclePrevious$mcF$sp(a);
   }

   default float cyclePrevious$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.partialPrevious$mcF$sp(a).getOrElse((JFunction0.mcF.sp)() -> this.maxBound$mcF$sp()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
