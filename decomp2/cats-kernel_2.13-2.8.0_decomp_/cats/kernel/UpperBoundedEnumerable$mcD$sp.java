package cats.kernel;

public interface UpperBoundedEnumerable$mcD$sp extends UpperBoundedEnumerable, Previous$mcD$sp, PartialPreviousUpperBounded$mcD$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcD$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcD$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final UpperBoundedEnumerable$mcD$sp $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.order$mcD$sp();
   }
}
