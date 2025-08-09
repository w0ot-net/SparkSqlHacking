package cats.kernel;

public interface LowerBoundedEnumerable$mcD$sp extends LowerBoundedEnumerable, Next$mcD$sp, PartialNextLowerBounded$mcD$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcD$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcD$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final LowerBoundedEnumerable$mcD$sp $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.order$mcD$sp();
   }
}
