package cats.kernel;

public interface UnboundedEnumerable$mcC$sp extends UnboundedEnumerable, Previous$mcC$sp, Next$mcC$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcC$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcC$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final UnboundedEnumerable$mcC$sp $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.order$mcC$sp();
   }
}
