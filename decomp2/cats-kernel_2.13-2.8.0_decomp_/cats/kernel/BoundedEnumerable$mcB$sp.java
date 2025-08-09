package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcB$sp extends BoundedEnumerable, PartialNextLowerBounded$mcB$sp, PartialPreviousUpperBounded$mcB$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcB$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcB$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final BoundedEnumerable$mcB$sp $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.order$mcB$sp();
   }

   // $FF: synthetic method
   static byte cycleNext$(final BoundedEnumerable$mcB$sp $this, final byte a) {
      return $this.cycleNext(a);
   }

   default byte cycleNext(final byte a) {
      return this.cycleNext$mcB$sp(a);
   }

   // $FF: synthetic method
   static byte cycleNext$mcB$sp$(final BoundedEnumerable$mcB$sp $this, final byte a) {
      return $this.cycleNext$mcB$sp(a);
   }

   default byte cycleNext$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.partialNext$mcB$sp(a).getOrElse((JFunction0.mcB.sp)() -> this.minBound$mcB$sp()));
   }

   // $FF: synthetic method
   static byte cyclePrevious$(final BoundedEnumerable$mcB$sp $this, final byte a) {
      return $this.cyclePrevious(a);
   }

   default byte cyclePrevious(final byte a) {
      return this.cyclePrevious$mcB$sp(a);
   }

   // $FF: synthetic method
   static byte cyclePrevious$mcB$sp$(final BoundedEnumerable$mcB$sp $this, final byte a) {
      return $this.cyclePrevious$mcB$sp(a);
   }

   default byte cyclePrevious$mcB$sp(final byte a) {
      return BoxesRunTime.unboxToByte(this.partialPrevious$mcB$sp(a).getOrElse((JFunction0.mcB.sp)() -> this.maxBound$mcB$sp()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
