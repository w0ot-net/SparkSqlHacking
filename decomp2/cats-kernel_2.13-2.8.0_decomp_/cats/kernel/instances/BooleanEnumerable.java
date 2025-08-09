package cats.kernel.instances;

import cats.kernel.BoundedEnumerable$mcZ$sp;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\u0005\u0003\u0005C\u0003'\u0001\u0011\u0005sEA\tC_>dW-\u00198F]VlWM]1cY\u0016T!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\n\u0004\u00015\u0019\u0002C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\rE\u0002\u0015+]i\u0011aB\u0005\u0003-\u001d\u0011\u0011CQ8v]\u0012,G-\u00128v[\u0016\u0014\u0018M\u00197f!\tq\u0001$\u0003\u0002\u001a\u001f\t9!i\\8mK\u0006t\u0017A\u0002\u0013j]&$H\u0005F\u0001\u001d!\tqQ$\u0003\u0002\u001f\u001f\t!QK\\5u\u0003-\u0001\u0018M\u001d;jC2tU\r\u001f;\u0015\u0005\u0005\"\u0003c\u0001\b#/%\u00111e\u0004\u0002\u0007\u001fB$\u0018n\u001c8\t\u000b\u0015\u0012\u0001\u0019A\f\u0002\u0003\u0005\fq\u0002]1si&\fG\u000e\u0015:fm&|Wo\u001d\u000b\u0003C!BQ!J\u0002A\u0002]\u0001"
)
public interface BooleanEnumerable extends BoundedEnumerable$mcZ$sp {
   // $FF: synthetic method
   static Option partialNext$(final BooleanEnumerable $this, final boolean a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final boolean a) {
      return this.partialNext$mcZ$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$(final BooleanEnumerable $this, final boolean a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final boolean a) {
      return this.partialPrevious$mcZ$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcZ$sp$(final BooleanEnumerable $this, final boolean a) {
      return $this.partialNext$mcZ$sp(a);
   }

   default Option partialNext$mcZ$sp(final boolean a) {
      return (Option)(!a ? new Some(BoxesRunTime.boxToBoolean(true)) : .MODULE$);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcZ$sp$(final BooleanEnumerable $this, final boolean a) {
      return $this.partialPrevious$mcZ$sp(a);
   }

   default Option partialPrevious$mcZ$sp(final boolean a) {
      return (Option)(a ? new Some(BoxesRunTime.boxToBoolean(false)) : .MODULE$);
   }

   static void $init$(final BooleanEnumerable $this) {
   }
}
