package cats.kernel;

public interface UnboundedEnumerable$mcS$sp extends UnboundedEnumerable, Previous$mcS$sp, Next$mcS$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcS$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcS$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final UnboundedEnumerable$mcS$sp $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.order$mcS$sp();
   }
}
