package cats.kernel;

public interface UpperBoundedEnumerable$mcB$sp extends UpperBoundedEnumerable, Previous$mcB$sp, PartialPreviousUpperBounded$mcB$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcB$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcB$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final UpperBoundedEnumerable$mcB$sp $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.order$mcB$sp();
   }
}
