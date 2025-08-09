package cats.kernel;

public interface UnboundedEnumerable$mcI$sp extends UnboundedEnumerable, Previous$mcI$sp, Next$mcI$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcI$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcI$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final UnboundedEnumerable$mcI$sp $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.order$mcI$sp();
   }
}
