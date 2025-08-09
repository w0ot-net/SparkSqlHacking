package cats.kernel;

public interface LowerBoundedEnumerable$mcV$sp extends LowerBoundedEnumerable, Next$mcV$sp, PartialNextLowerBounded$mcV$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcV$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcV$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final LowerBoundedEnumerable$mcV$sp $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.order$mcV$sp();
   }
}
