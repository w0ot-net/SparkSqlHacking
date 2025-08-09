package cats.kernel;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0019\u0005a\u0006C\u00033\u0001\u0011\u00053GA\nV]\n|WO\u001c3fI\u0016sW/\\3sC\ndWM\u0003\u0002\u0007\u000f\u000511.\u001a:oK2T\u0011\u0001C\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u0005-A2\u0003\u0002\u0001\r%\u0015\u0002\"!\u0004\t\u000e\u00039Q\u0011aD\u0001\u0006g\u000e\fG.Y\u0005\u0003#9\u0011a!\u00118z%\u00164\u0007cA\n\u0015-5\tQ!\u0003\u0002\u0016\u000b\t!a*\u001a=u!\t9\u0002\u0004\u0004\u0001\u0005\u0013e\u0001\u0001\u0015!A\u0001\u0006\u0004Q\"!A!\u0012\u0005mq\u0002CA\u0007\u001d\u0013\tibBA\u0004O_RD\u0017N\\4\u0011\u00055y\u0012B\u0001\u0011\u000f\u0005\r\te.\u001f\u0015\u00031\t\u0002\"!D\u0012\n\u0005\u0011r!aC:qK\u000eL\u0017\r\\5{K\u0012\u00042a\u0005\u0014\u0017\u0013\t9SA\u0001\u0005Qe\u00164\u0018n\\;t\u0003\u0019!\u0013N\\5uIQ\t!\u0006\u0005\u0002\u000eW%\u0011AF\u0004\u0002\u0005+:LG/A\u0003pe\u0012,'/F\u00010!\r\u0019\u0002GF\u0005\u0003c\u0015\u0011Qa\u0014:eKJ\fA\u0002]1si&\fGn\u0014:eKJ,\u0012\u0001\u000e\t\u0004'U2\u0012B\u0001\u001c\u0006\u00051\u0001\u0016M\u001d;jC2|%\u000fZ3s\u0001"
)
public interface UnboundedEnumerable extends Next, Previous {
   Order order();

   // $FF: synthetic method
   static PartialOrder partialOrder$(final UnboundedEnumerable $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcZ$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcZ$sp();
   }

   default Order order$mcZ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcB$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcB$sp();
   }

   default Order order$mcB$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcC$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcC$sp();
   }

   default Order order$mcC$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcD$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcD$sp();
   }

   default Order order$mcD$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcF$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcF$sp();
   }

   default Order order$mcF$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcI$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcI$sp();
   }

   default Order order$mcI$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcJ$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcJ$sp();
   }

   default Order order$mcJ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcS$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcS$sp();
   }

   default Order order$mcS$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcV$sp$(final UnboundedEnumerable $this) {
      return $this.order$mcV$sp();
   }

   default Order order$mcV$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final UnboundedEnumerable $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.partialOrder();
   }

   static void $init$(final UnboundedEnumerable $this) {
   }
}
