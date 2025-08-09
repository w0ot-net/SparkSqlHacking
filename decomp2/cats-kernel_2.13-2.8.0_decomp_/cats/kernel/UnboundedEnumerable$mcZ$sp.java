package cats.kernel;

public interface UnboundedEnumerable$mcZ$sp extends UnboundedEnumerable, Previous$mcZ$sp, Next$mcZ$sp {
   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable$mcZ$sp $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.partialOrder$mcZ$sp();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final UnboundedEnumerable$mcZ$sp $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.order$mcZ$sp();
   }
}
