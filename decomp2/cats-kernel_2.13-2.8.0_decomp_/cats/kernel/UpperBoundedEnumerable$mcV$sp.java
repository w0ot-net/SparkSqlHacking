package cats.kernel;

public interface UpperBoundedEnumerable$mcV$sp extends UpperBoundedEnumerable, Previous$mcV$sp, PartialPreviousUpperBounded$mcV$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcV$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcV$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final UpperBoundedEnumerable$mcV$sp $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.order$mcV$sp();
   }
}
