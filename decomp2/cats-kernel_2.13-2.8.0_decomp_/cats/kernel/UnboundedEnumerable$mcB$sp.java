package cats.kernel;

public interface UnboundedEnumerable$mcB$sp extends UnboundedEnumerable, Previous$mcB$sp, Next$mcB$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcB$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcB$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final UnboundedEnumerable$mcB$sp $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.order$mcB$sp();
   }
}
