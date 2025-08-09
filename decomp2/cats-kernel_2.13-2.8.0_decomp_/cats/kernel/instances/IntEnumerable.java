package cats.kernel.instances;

import cats.kernel.BoundedEnumerable$mcI$sp;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\u0005\u0003\u0005C\u0003'\u0001\u0011\u0005sEA\u0007J]R,e.^7fe\u0006\u0014G.\u001a\u0006\u0003\r\u001d\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005!I\u0011AB6fe:,GNC\u0001\u000b\u0003\u0011\u0019\u0017\r^:\u0004\u0001M\u0019\u0001!D\n\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\r!RcF\u0007\u0002\u000f%\u0011ac\u0002\u0002\u0012\u0005>,h\u000eZ3e\u000b:,X.\u001a:bE2,\u0007C\u0001\b\u0019\u0013\tIrBA\u0002J]R\fa\u0001J5oSR$C#\u0001\u000f\u0011\u00059i\u0012B\u0001\u0010\u0010\u0005\u0011)f.\u001b;\u0002\u0017A\f'\u000f^5bY:+\u0007\u0010\u001e\u000b\u0003C\u0011\u00022A\u0004\u0012\u0018\u0013\t\u0019sB\u0001\u0004PaRLwN\u001c\u0005\u0006K\t\u0001\raF\u0001\u0002C\u0006y\u0001/\u0019:uS\u0006d\u0007K]3wS>,8\u000f\u0006\u0002\"Q!)Qe\u0001a\u0001/\u0001"
)
public interface IntEnumerable extends BoundedEnumerable$mcI$sp {
   // $FF: synthetic method
   static Option partialNext$(final IntEnumerable $this, final int a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final int a) {
      return this.partialNext$mcI$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$(final IntEnumerable $this, final int a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final int a) {
      return this.partialPrevious$mcI$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcI$sp$(final IntEnumerable $this, final int a) {
      return $this.partialNext$mcI$sp(a);
   }

   default Option partialNext$mcI$sp(final int a) {
      return (Option)(this.order$mcI$sp().eqv$mcI$sp(a, this.maxBound$mcI$sp()) ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(a + 1)));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcI$sp$(final IntEnumerable $this, final int a) {
      return $this.partialPrevious$mcI$sp(a);
   }

   default Option partialPrevious$mcI$sp(final int a) {
      return (Option)(this.order$mcI$sp().eqv$mcI$sp(a, this.minBound$mcI$sp()) ? .MODULE$ : new Some(BoxesRunTime.boxToInteger(a - 1)));
   }

   static void $init$(final IntEnumerable $this) {
   }
}
