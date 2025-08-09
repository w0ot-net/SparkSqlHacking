package cats.kernel;

public interface LowerBoundedEnumerable$mcF$sp extends LowerBoundedEnumerable, Next$mcF$sp, PartialNextLowerBounded$mcF$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcF$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcF$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final LowerBoundedEnumerable$mcF$sp $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.order$mcF$sp();
   }
}
