package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcS$sp extends BoundedEnumerable, PartialNextLowerBounded$mcS$sp, PartialPreviousUpperBounded$mcS$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcS$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcS$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final BoundedEnumerable$mcS$sp $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.order$mcS$sp();
   }

   // $FF: synthetic method
   static short cycleNext$(final BoundedEnumerable$mcS$sp $this, final short a) {
      return $this.cycleNext(a);
   }

   default short cycleNext(final short a) {
      return this.cycleNext$mcS$sp(a);
   }

   // $FF: synthetic method
   static short cycleNext$mcS$sp$(final BoundedEnumerable$mcS$sp $this, final short a) {
      return $this.cycleNext$mcS$sp(a);
   }

   default short cycleNext$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.partialNext$mcS$sp(a).getOrElse((JFunction0.mcS.sp)() -> this.minBound$mcS$sp()));
   }

   // $FF: synthetic method
   static short cyclePrevious$(final BoundedEnumerable$mcS$sp $this, final short a) {
      return $this.cyclePrevious(a);
   }

   default short cyclePrevious(final short a) {
      return this.cyclePrevious$mcS$sp(a);
   }

   // $FF: synthetic method
   static short cyclePrevious$mcS$sp$(final BoundedEnumerable$mcS$sp $this, final short a) {
      return $this.cyclePrevious$mcS$sp(a);
   }

   default short cyclePrevious$mcS$sp(final short a) {
      return BoxesRunTime.unboxToShort(this.partialPrevious$mcS$sp(a).getOrElse((JFunction0.mcS.sp)() -> this.maxBound$mcS$sp()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
