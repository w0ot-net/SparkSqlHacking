package cats.kernel.instances;

import cats.kernel.BoundedEnumerable$mcS$sp;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\u0005\u0003\u0005C\u0003'\u0001\u0011\u0005sEA\bTQ>\u0014H/\u00128v[\u0016\u0014\u0018M\u00197f\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u00011c\u0001\u0001\u000e'A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u00042\u0001F\u000b\u0018\u001b\u00059\u0011B\u0001\f\b\u0005E\u0011u.\u001e8eK\u0012,e.^7fe\u0006\u0014G.\u001a\t\u0003\u001daI!!G\b\u0003\u000bMCwN\u001d;\u0002\r\u0011Jg.\u001b;%)\u0005a\u0002C\u0001\b\u001e\u0013\tqrB\u0001\u0003V]&$\u0018a\u00039beRL\u0017\r\u001c(fqR$\"!\t\u0013\u0011\u00079\u0011s#\u0003\u0002$\u001f\t1q\n\u001d;j_:DQ!\n\u0002A\u0002]\t\u0011!Y\u0001\u0010a\u0006\u0014H/[1m!J,g/[8vgR\u0011\u0011\u0005\u000b\u0005\u0006K\r\u0001\ra\u0006"
)
public interface ShortEnumerable extends BoundedEnumerable$mcS$sp {
   // $FF: synthetic method
   static Option partialNext$(final ShortEnumerable $this, final short a) {
      return $this.partialNext(a);
   }

   default Option partialNext(final short a) {
      return this.partialNext$mcS$sp(a);
   }

   // $FF: synthetic method
   static Option partialPrevious$(final ShortEnumerable $this, final short a) {
      return $this.partialPrevious(a);
   }

   default Option partialPrevious(final short a) {
      return this.partialPrevious$mcS$sp(a);
   }

   // $FF: synthetic method
   static Option partialNext$mcS$sp$(final ShortEnumerable $this, final short a) {
      return $this.partialNext$mcS$sp(a);
   }

   default Option partialNext$mcS$sp(final short a) {
      return (Option)(this.order$mcS$sp().eqv$mcS$sp(a, this.maxBound$mcS$sp()) ? .MODULE$ : new Some(BoxesRunTime.boxToShort((short)(a + 1))));
   }

   // $FF: synthetic method
   static Option partialPrevious$mcS$sp$(final ShortEnumerable $this, final short a) {
      return $this.partialPrevious$mcS$sp(a);
   }

   default Option partialPrevious$mcS$sp(final short a) {
      return (Option)(this.order$mcS$sp().eqv$mcS$sp(a, this.minBound$mcS$sp()) ? .MODULE$ : new Some(BoxesRunTime.boxToShort((short)(a - 1))));
   }

   static void $init$(final ShortEnumerable $this) {
   }
}
