package cats.kernel;

public interface UpperBoundedEnumerable$mcJ$sp extends UpperBoundedEnumerable, Previous$mcJ$sp, PartialPreviousUpperBounded$mcJ$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcJ$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcJ$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final UpperBoundedEnumerable$mcJ$sp $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.order$mcJ$sp();
   }
}
