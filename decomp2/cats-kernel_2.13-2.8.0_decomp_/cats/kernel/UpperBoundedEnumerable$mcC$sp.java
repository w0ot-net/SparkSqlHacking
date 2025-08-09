package cats.kernel;

public interface UpperBoundedEnumerable$mcC$sp extends UpperBoundedEnumerable, Previous$mcC$sp, PartialPreviousUpperBounded$mcC$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcC$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcC$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final UpperBoundedEnumerable$mcC$sp $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.order$mcC$sp();
   }
}
