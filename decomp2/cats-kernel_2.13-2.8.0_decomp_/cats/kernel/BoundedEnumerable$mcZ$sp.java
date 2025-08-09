package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcZ$sp extends BoundedEnumerable, PartialNextLowerBounded$mcZ$sp, PartialPreviousUpperBounded$mcZ$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcZ$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcZ$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final BoundedEnumerable$mcZ$sp $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.order$mcZ$sp();
   }

   // $FF: synthetic method
   static boolean cycleNext$(final BoundedEnumerable$mcZ$sp $this, final boolean a) {
      return $this.cycleNext(a);
   }

   default boolean cycleNext(final boolean a) {
      return this.cycleNext$mcZ$sp(a);
   }

   // $FF: synthetic method
   static boolean cycleNext$mcZ$sp$(final BoundedEnumerable$mcZ$sp $this, final boolean a) {
      return $this.cycleNext$mcZ$sp(a);
   }

   default boolean cycleNext$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.partialNext$mcZ$sp(a).getOrElse((JFunction0.mcZ.sp)() -> this.minBound$mcZ$sp()));
   }

   // $FF: synthetic method
   static boolean cyclePrevious$(final BoundedEnumerable$mcZ$sp $this, final boolean a) {
      return $this.cyclePrevious(a);
   }

   default boolean cyclePrevious(final boolean a) {
      return this.cyclePrevious$mcZ$sp(a);
   }

   // $FF: synthetic method
   static boolean cyclePrevious$mcZ$sp$(final BoundedEnumerable$mcZ$sp $this, final boolean a) {
      return $this.cyclePrevious$mcZ$sp(a);
   }

   default boolean cyclePrevious$mcZ$sp(final boolean a) {
      return BoxesRunTime.unboxToBoolean(this.partialPrevious$mcZ$sp(a).getOrElse((JFunction0.mcZ.sp)() -> this.maxBound$mcZ$sp()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
