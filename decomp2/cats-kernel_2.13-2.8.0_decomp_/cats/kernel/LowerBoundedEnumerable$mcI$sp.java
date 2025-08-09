package cats.kernel;

public interface LowerBoundedEnumerable$mcI$sp extends LowerBoundedEnumerable, Next$mcI$sp, PartialNextLowerBounded$mcI$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable$mcI$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcI$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final LowerBoundedEnumerable$mcI$sp $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.order$mcI$sp();
   }
}
