package cats.kernel;

public interface LowerBoundedEnumerable$mcS$sp extends LowerBoundedEnumerable, Next$mcS$sp, PartialNextLowerBounded$mcS$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcS$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcS$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final LowerBoundedEnumerable$mcS$sp $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.order$mcS$sp();
   }
}
