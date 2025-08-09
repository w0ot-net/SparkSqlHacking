package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcV$sp extends BoundedEnumerable, PartialNextLowerBounded$mcV$sp, PartialPreviousUpperBounded$mcV$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcV$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcV$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final BoundedEnumerable$mcV$sp $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.order$mcV$sp();
   }

   // $FF: synthetic method
   static void cycleNext$(final BoundedEnumerable$mcV$sp $this, final BoxedUnit a) {
      $this.cycleNext(a);
   }

   default void cycleNext(final BoxedUnit a) {
      this.cycleNext$mcV$sp(a);
   }

   // $FF: synthetic method
   static void cycleNext$mcV$sp$(final BoundedEnumerable$mcV$sp $this, final BoxedUnit a) {
      $this.cycleNext$mcV$sp(a);
   }

   default void cycleNext$mcV$sp(final BoxedUnit a) {
      this.partialNext$mcV$sp(a).getOrElse((JFunction0.mcV.sp)() -> this.minBound$mcV$sp());
   }

   // $FF: synthetic method
   static void cyclePrevious$(final BoundedEnumerable$mcV$sp $this, final BoxedUnit a) {
      $this.cyclePrevious(a);
   }

   default void cyclePrevious(final BoxedUnit a) {
      this.cyclePrevious$mcV$sp(a);
   }

   // $FF: synthetic method
   static void cyclePrevious$mcV$sp$(final BoundedEnumerable$mcV$sp $this, final BoxedUnit a) {
      $this.cyclePrevious$mcV$sp(a);
   }

   default void cyclePrevious$mcV$sp(final BoxedUnit a) {
      this.partialPrevious$mcV$sp(a).getOrElse((JFunction0.mcV.sp)() -> this.maxBound$mcV$sp());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
