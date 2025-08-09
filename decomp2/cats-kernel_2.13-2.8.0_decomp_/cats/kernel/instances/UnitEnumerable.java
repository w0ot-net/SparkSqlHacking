package cats.kernel.instances;

import cats.kernel.BoundedEnumerable$mcV$sp;
import scala.Option;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003\u001d\u0001\u0011\u0005S\u0004C\u0003$\u0001\u0011\u0005CE\u0001\bV]&$XI\\;nKJ\f'\r\\3\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001\u00192\u0001A\u0007\u0014!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#F\f\u000e\u0003\u001dI!AF\u0004\u0003#\t{WO\u001c3fI\u0016sW/\\3sC\ndW\r\u0005\u0002\u000f1%\u0011\u0011d\u0004\u0002\u0005+:LG/\u0001\u0004%S:LG\u000f\n\u000b\u0002/\u0005Y\u0001/\u0019:uS\u0006dg*\u001a=u)\tq\u0012\u0005E\u0002\u000f?]I!\u0001I\b\u0003\r=\u0003H/[8o\u0011\u0015\u0011#\u00011\u0001\u0018\u0003\u0005A\u0018a\u00049beRL\u0017\r\u001c)sKZLw.^:\u0015\u0005y)\u0003\"\u0002\u0012\u0004\u0001\u00049\u0002"
)
public interface UnitEnumerable extends BoundedEnumerable$mcV$sp {
   // $FF: synthetic method
   static Option partialNext$(final UnitEnumerable $this, final BoxedUnit x) {
      return $this.partialNext(x);
   }

   default Option partialNext(final BoxedUnit x) {
      return this.partialNext$mcV$sp(x);
   }

   // $FF: synthetic method
   static Option partialPrevious$(final UnitEnumerable $this, final BoxedUnit x) {
      return $this.partialPrevious(x);
   }

   default Option partialPrevious(final BoxedUnit x) {
      return this.partialPrevious$mcV$sp(x);
   }

   // $FF: synthetic method
   static Option partialNext$mcV$sp$(final UnitEnumerable $this, final BoxedUnit x) {
      return $this.partialNext$mcV$sp(x);
   }

   default Option partialNext$mcV$sp(final BoxedUnit x) {
      return .MODULE$;
   }

   // $FF: synthetic method
   static Option partialPrevious$mcV$sp$(final UnitEnumerable $this, final BoxedUnit x) {
      return $this.partialPrevious$mcV$sp(x);
   }

   default Option partialPrevious$mcV$sp(final BoxedUnit x) {
      return .MODULE$;
   }

   static void $init$(final UnitEnumerable $this) {
   }
}
