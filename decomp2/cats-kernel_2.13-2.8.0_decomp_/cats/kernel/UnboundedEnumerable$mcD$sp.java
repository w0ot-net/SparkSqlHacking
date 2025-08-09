package cats.kernel;

public interface UnboundedEnumerable$mcD$sp extends UnboundedEnumerable, Previous$mcD$sp, Next$mcD$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcD$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcD$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final UnboundedEnumerable$mcD$sp $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.order$mcD$sp();
   }
}
