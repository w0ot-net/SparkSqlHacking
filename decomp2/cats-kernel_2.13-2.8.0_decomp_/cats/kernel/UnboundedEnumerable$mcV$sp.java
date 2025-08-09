package cats.kernel;

public interface UnboundedEnumerable$mcV$sp extends UnboundedEnumerable, Previous$mcV$sp, Next$mcV$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcV$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcV$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final UnboundedEnumerable$mcV$sp $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.order$mcV$sp();
   }
}
