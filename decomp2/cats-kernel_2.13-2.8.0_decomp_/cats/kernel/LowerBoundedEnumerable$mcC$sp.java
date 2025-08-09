package cats.kernel;

public interface LowerBoundedEnumerable$mcC$sp extends LowerBoundedEnumerable, Next$mcC$sp, PartialNextLowerBounded$mcC$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcC$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcC$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final LowerBoundedEnumerable$mcC$sp $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.order$mcC$sp();
   }
}
