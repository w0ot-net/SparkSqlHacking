package cats.kernel;

public interface UpperBoundedEnumerable$mcS$sp extends UpperBoundedEnumerable, Previous$mcS$sp, PartialPreviousUpperBounded$mcS$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcS$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcS$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final UpperBoundedEnumerable$mcS$sp $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.order$mcS$sp();
   }
}
