package cats.kernel;

public interface LowerBoundedEnumerable$mcJ$sp extends LowerBoundedEnumerable, Next$mcJ$sp, PartialNextLowerBounded$mcJ$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcJ$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcJ$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final LowerBoundedEnumerable$mcJ$sp $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.order$mcJ$sp();
   }
}
