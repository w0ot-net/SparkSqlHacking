package cats.kernel.instances;

import cats.kernel.BoundedEnumerable$mcJ$sp;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\u0005\u0003\u0005C\u0003'\u0001\u0011\u0005sE\u0001\bM_:<WI\\;nKJ\f'\r\\3\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001\u00192\u0001A\u0007\u0014!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#F\f\u000e\u0003\u001dI!AF\u0004\u0003#\t{WO\u001c3fI\u0016sW/\\3sC\ndW\r\u0005\u0002\u000f1%\u0011\u0011d\u0004\u0002\u0005\u0019>tw-\u0001\u0004%S:LG\u000f\n\u000b\u00029A\u0011a\"H\u0005\u0003==\u0011A!\u00168ji\u0006Y\u0001/\u0019:uS\u0006dg*\u001a=u)\t\tC\u0005E\u0002\u000fE]I!aI\b\u0003\r=\u0003H/[8o\u0011\u0015)#\u00011\u0001\u0018\u0003\u0005\t\u0017a\u00049beRL\u0017\r\u001c)sKZLw.^:\u0015\u0005\u0005B\u0003\"B\u0013\u0004\u0001\u00049\u0002"
)
public interface LongEnumerable extends BoundedEnumerable$mcJ$sp {
   // $FF: synthetic method
   static Option partialNext$(final LongEnumerable $this, final long a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final long a) {
      return this.partialNext$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$(final LongEnumerable $this, final long a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final long a) {
      return this.partialPrevious$mcJ$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcJ$sp$(final LongEnumerable $this, final long a) {
      return $this.partialNext$mcJ$sp(a);
   }

   default Option partialNext$mcJ$sp(final long a) {
      return (Option)(this.order$mcJ$sp().neqv$mcJ$sp(a, this.maxBound$mcJ$sp()) ? new Some(BoxesRunTime.boxToLong(a + 1L)) : .MODULE$);
   }

   // $FF: synthetic method
   static Option partialPrevious$mcJ$sp$(final LongEnumerable $this, final long a) {
      return $this.partialPrevious$mcJ$sp(a);
   }

   default Option partialPrevious$mcJ$sp(final long a) {
      return (Option)(this.order$mcJ$sp().neqv$mcJ$sp(a, this.minBound$mcJ$sp()) ? new Some(BoxesRunTime.boxToLong(a - 1L)) : .MODULE$);
   }

   static void $init$(final LongEnumerable $this) {
   }
}
