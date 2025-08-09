package cats.kernel;

public interface LowerBoundedEnumerable$mcB$sp extends LowerBoundedEnumerable, Next$mcB$sp, PartialNextLowerBounded$mcB$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcB$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcB$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final LowerBoundedEnumerable$mcB$sp $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.order$mcB$sp();
   }
}
