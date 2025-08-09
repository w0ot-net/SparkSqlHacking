package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcJ$sp extends BoundedEnumerable, PartialNextLowerBounded$mcJ$sp, PartialPreviousUpperBounded$mcJ$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcJ$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcJ$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final BoundedEnumerable$mcJ$sp $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.order$mcJ$sp();
   }

   // $FF: synthetic method
   static long cycleNext$(final BoundedEnumerable$mcJ$sp $this, final long a) {
      return $this.cycleNext(a);
   }

   default long cycleNext(final long a) {
      return this.cycleNext$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long cycleNext$mcJ$sp$(final BoundedEnumerable$mcJ$sp $this, final long a) {
      return $this.cycleNext$mcJ$sp(a);
   }

   default long cycleNext$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.partialNext$mcJ$sp(a).getOrElse((JFunction0.mcJ.sp)() -> this.minBound$mcJ$sp()));
   }

   // $FF: synthetic method
   static long cyclePrevious$(final BoundedEnumerable$mcJ$sp $this, final long a) {
      return $this.cyclePrevious(a);
   }

   default long cyclePrevious(final long a) {
      return this.cyclePrevious$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long cyclePrevious$mcJ$sp$(final BoundedEnumerable$mcJ$sp $this, final long a) {
      return $this.cyclePrevious$mcJ$sp(a);
   }

   default long cyclePrevious$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.partialPrevious$mcJ$sp(a).getOrElse((JFunction0.mcJ.sp)() -> this.maxBound$mcJ$sp()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
