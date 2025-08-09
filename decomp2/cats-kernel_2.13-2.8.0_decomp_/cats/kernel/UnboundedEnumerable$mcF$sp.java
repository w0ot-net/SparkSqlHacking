package cats.kernel;

public interface UnboundedEnumerable$mcF$sp extends UnboundedEnumerable, Previous$mcF$sp, Next$mcF$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcF$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcF$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final UnboundedEnumerable$mcF$sp $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.order$mcF$sp();
   }
}
