package cats.kernel;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0019\u0005a\u0006C\u00033\u0001\u0011\u00053G\u0001\fVaB,'OQ8v]\u0012,G-\u00128v[\u0016\u0014\u0018M\u00197f\u0015\t1q!\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0011\u0005!1-\u0019;t\u0007\u0001)\"a\u0003\r\u0014\t\u0001a!#\n\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007M!b#D\u0001\u0006\u0013\t)RAA\u000eQCJ$\u0018.\u00197Qe\u00164\u0018n\\;t+B\u0004XM\u001d\"pk:$W\r\u001a\t\u0003/aa\u0001\u0001B\u0005\u001a\u0001\u0001\u0006\t\u0011!b\u00015\t\t\u0011)\u0005\u0002\u001c=A\u0011Q\u0002H\u0005\u0003;9\u0011qAT8uQ&tw\r\u0005\u0002\u000e?%\u0011\u0001E\u0004\u0002\u0004\u0003:L\bF\u0001\r#!\ti1%\u0003\u0002%\u001d\tY1\u000f]3dS\u0006d\u0017N_3e!\r\u0019bEF\u0005\u0003O\u0015\u0011\u0001\u0002\u0015:fm&|Wo]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003)\u0002\"!D\u0016\n\u00051r!\u0001B+oSR\fQa\u001c:eKJ,\u0012a\f\t\u0004'A2\u0012BA\u0019\u0006\u0005\u0015y%\u000fZ3s\u00031\u0001\u0018M\u001d;jC2|%\u000fZ3s+\u0005!\u0004cA\n6-%\u0011a'\u0002\u0002\r!\u0006\u0014H/[1m\u001fJ$WM\u001d"
)
public interface UpperBoundedEnumerable extends PartialPreviousUpperBounded, Previous {
   Order order();

   // $FF: synthetic method
   static PartialOrder partialOrder$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcZ$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcZ$sp();
   }

   default Order order$mcZ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcB$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcB$sp();
   }

   default Order order$mcB$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcC$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcC$sp();
   }

   default Order order$mcC$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcD$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcD$sp();
   }

   default Order order$mcD$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcF$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcF$sp();
   }

   default Order order$mcF$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcI$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcI$sp();
   }

   default Order order$mcI$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcJ$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcJ$sp();
   }

   default Order order$mcJ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcS$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcS$sp();
   }

   default Order order$mcS$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcV$sp$(final UpperBoundedEnumerable $this) {
      return $this.order$mcV$sp();
   }

   default Order order$mcV$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final UpperBoundedEnumerable $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.partialOrder();
   }

   static void $init$(final UpperBoundedEnumerable $this) {
   }
}
