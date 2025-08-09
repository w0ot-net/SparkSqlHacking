package cats.kernel;

public interface UnboundedEnumerable$mcJ$sp extends UnboundedEnumerable, Previous$mcJ$sp, Next$mcJ$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcJ$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcJ$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final UnboundedEnumerable$mcJ$sp $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.order$mcJ$sp();
   }
}
