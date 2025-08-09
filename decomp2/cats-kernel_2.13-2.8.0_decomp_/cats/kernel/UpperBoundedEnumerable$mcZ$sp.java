package cats.kernel;

public interface UpperBoundedEnumerable$mcZ$sp extends UpperBoundedEnumerable, Previous$mcZ$sp, PartialPreviousUpperBounded$mcZ$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcZ$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcZ$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final UpperBoundedEnumerable$mcZ$sp $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.order$mcZ$sp();
   }
}
