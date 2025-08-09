package cats.kernel;

public interface UpperBoundedEnumerable$mcI$sp extends UpperBoundedEnumerable, Previous$mcI$sp, PartialPreviousUpperBounded$mcI$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable$mcI$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcI$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final UpperBoundedEnumerable$mcI$sp $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.order$mcI$sp();
   }
}
