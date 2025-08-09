package cats.kernel;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0019\u0005a\u0006C\u00033\u0001\u0011\u00053G\u0001\fM_^,'OQ8v]\u0012,G-\u00128v[\u0016\u0014\u0018M\u00197f\u0015\t1q!\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0011\u0005!1-\u0019;t\u0007\u0001)\"a\u0003\r\u0014\t\u0001a!#\n\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007M!b#D\u0001\u0006\u0013\t)RAA\fQCJ$\u0018.\u00197OKb$Hj\\<fe\n{WO\u001c3fIB\u0011q\u0003\u0007\u0007\u0001\t%I\u0002\u0001)A\u0001\u0002\u000b\u0007!DA\u0001B#\tYb\u0004\u0005\u0002\u000e9%\u0011QD\u0004\u0002\b\u001d>$\b.\u001b8h!\tiq$\u0003\u0002!\u001d\t\u0019\u0011I\\=)\u0005a\u0011\u0003CA\u0007$\u0013\t!cBA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0007cA\n'-%\u0011q%\u0002\u0002\u0005\u001d\u0016DH/\u0001\u0004%S:LG\u000f\n\u000b\u0002UA\u0011QbK\u0005\u0003Y9\u0011A!\u00168ji\u0006)qN\u001d3feV\tq\u0006E\u0002\u0014aYI!!M\u0003\u0003\u000b=\u0013H-\u001a:\u0002\u0019A\f'\u000f^5bY>\u0013H-\u001a:\u0016\u0003Q\u00022aE\u001b\u0017\u0013\t1TA\u0001\u0007QCJ$\u0018.\u00197Pe\u0012,'\u000f"
)
public interface LowerBoundedEnumerable extends PartialNextLowerBounded, Next {
   Order order();

   // $FF: synthetic method
   static PartialOrder partialOrder$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder();
   }

   default PartialOrder partialOrder() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcZ$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcZ$sp();
   }

   default Order order$mcZ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcB$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcB$sp();
   }

   default Order order$mcB$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcC$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcC$sp();
   }

   default Order order$mcC$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcD$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcD$sp();
   }

   default Order order$mcD$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcF$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcF$sp();
   }

   default Order order$mcF$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcI$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcI$sp();
   }

   default Order order$mcI$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcJ$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcJ$sp();
   }

   default Order order$mcJ$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcS$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcS$sp();
   }

   default Order order$mcS$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static Order order$mcV$sp$(final LowerBoundedEnumerable $this) {
      return $this.order$mcV$sp();
   }

   default Order order$mcV$sp() {
      return this.order();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcZ$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcZ$sp();
   }

   default PartialOrder partialOrder$mcZ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcB$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcB$sp();
   }

   default PartialOrder partialOrder$mcB$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcC$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcC$sp();
   }

   default PartialOrder partialOrder$mcC$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcD$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcD$sp();
   }

   default PartialOrder partialOrder$mcD$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcF$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcF$sp();
   }

   default PartialOrder partialOrder$mcF$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcI$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcI$sp();
   }

   default PartialOrder partialOrder$mcI$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcJ$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcJ$sp();
   }

   default PartialOrder partialOrder$mcJ$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcS$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcS$sp();
   }

   default PartialOrder partialOrder$mcS$sp() {
      return this.partialOrder();
   }

   // $FF: synthetic method
   static PartialOrder partialOrder$mcV$sp$(final LowerBoundedEnumerable $this) {
      return $this.partialOrder$mcV$sp();
   }

   default PartialOrder partialOrder$mcV$sp() {
      return this.partialOrder();
   }

   static void $init$(final LowerBoundedEnumerable $this) {
   }
}
