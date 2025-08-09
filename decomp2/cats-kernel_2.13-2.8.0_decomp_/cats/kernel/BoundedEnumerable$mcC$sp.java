package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public interface BoundedEnumerable$mcC$sp extends BoundedEnumerable, PartialNextLowerBounded$mcC$sp, PartialPreviousUpperBounded$mcC$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final BoundedEnumerable$mcC$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcC$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final BoundedEnumerable$mcC$sp $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.order$mcC$sp();
   }

   // $FF: synthetic method
   static char cycleNext$(final BoundedEnumerable$mcC$sp $this, final char a) {
      return $this.cycleNext(a);
   }

   default char cycleNext(final char a) {
      return this.cycleNext$mcC$sp(a);
   }

   // $FF: synthetic method
   static char cycleNext$mcC$sp$(final BoundedEnumerable$mcC$sp $this, final char a) {
      return $this.cycleNext$mcC$sp(a);
   }

   default char cycleNext$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.partialNext$mcC$sp(a).getOrElse((JFunction0.mcC.sp)() -> this.minBound$mcC$sp()));
   }

   // $FF: synthetic method
   static char cyclePrevious$(final BoundedEnumerable$mcC$sp $this, final char a) {
      return $this.cyclePrevious(a);
   }

   default char cyclePrevious(final char a) {
      return this.cyclePrevious$mcC$sp(a);
   }

   // $FF: synthetic method
   static char cyclePrevious$mcC$sp$(final BoundedEnumerable$mcC$sp $this, final char a) {
      return $this.cyclePrevious$mcC$sp(a);
   }

   default char cyclePrevious$mcC$sp(final char a) {
      return BoxesRunTime.unboxToChar(this.partialPrevious$mcC$sp(a).getOrElse((JFunction0.mcC.sp)() -> this.maxBound$mcC$sp()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
