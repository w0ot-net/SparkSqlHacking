package cats.kernel;

public interface UpperBoundedEnumerable$mcF$sp extends UpperBoundedEnumerable, Previous$mcF$sp, PartialPreviousUpperBounded$mcF$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcF$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcF$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final UpperBoundedEnumerable$mcF$sp $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.order$mcF$sp();
   }
}
