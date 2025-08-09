package cats.kernel;

public interface LowerBoundedEnumerable$mcZ$sp extends LowerBoundedEnumerable, Next$mcZ$sp, PartialNextLowerBounded$mcZ$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcZ$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcZ$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final LowerBoundedEnumerable$mcZ$sp $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.order$mcZ$sp();
   }
}
