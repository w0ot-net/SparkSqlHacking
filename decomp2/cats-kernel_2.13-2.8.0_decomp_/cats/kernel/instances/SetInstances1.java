package cats.kernel.instances;

import cats.kernel.BoundedSemilattice;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006%\u0001!\t\u0001\u0006\u0005\u00061\u0001!\u0019!\u0007\u0005\u0006k\u0001!\u0019A\u000e\u0002\u000e'\u0016$\u0018J\\:uC:\u001cWm]\u0019\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t'\t\u0001A\u0002\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tQ\u0003\u0005\u0002\u000e-%\u0011qC\u0004\u0002\u0005+:LG/A\u0010dCR\u001c8*\u001a:oK2\u001cF\u000f\u001a)beRL\u0017\r\\(sI\u0016\u0014hi\u001c:TKR,\"A\u0007\u0017\u0016\u0003m\u00012\u0001H\u000f \u001b\u00059\u0011B\u0001\u0010\b\u00051\u0001\u0016M\u001d;jC2|%\u000fZ3s!\r\u0001sE\u000b\b\u0003C\u0015\u0002\"A\t\b\u000e\u0003\rR!\u0001J\n\u0002\rq\u0012xn\u001c;?\u0013\t1c\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003Q%\u00121aU3u\u0015\t1c\u0002\u0005\u0002,Y1\u0001A!B\u0017\u0003\u0005\u0004q#!A!\u0012\u0005=\u0012\u0004CA\u00071\u0013\t\tdBA\u0004O_RD\u0017N\\4\u0011\u00055\u0019\u0014B\u0001\u001b\u000f\u0005\r\te._\u0001\u001fG\u0006$8oS3s]\u0016d7\u000b\u001e3TK6LG.\u0019;uS\u000e,gi\u001c:TKR,\"aN\u001f\u0016\u0003a\u00022\u0001H\u001d<\u0013\tQtA\u0001\nC_VtG-\u001a3TK6LG.\u0019;uS\u000e,\u0007c\u0001\u0011(yA\u00111&\u0010\u0003\u0006[\r\u0011\rA\f"
)
public interface SetInstances1 {
   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForSet$(final SetInstances1 $this) {
      return $this.catsKernelStdPartialOrderForSet();
   }

   default PartialOrder catsKernelStdPartialOrderForSet() {
      return new SetPartialOrder();
   }

   // $FF: synthetic method
   static BoundedSemilattice catsKernelStdSemilatticeForSet$(final SetInstances1 $this) {
      return $this.catsKernelStdSemilatticeForSet();
   }

   default BoundedSemilattice catsKernelStdSemilatticeForSet() {
      return new SetSemilattice();
   }

   static void $init$(final SetInstances1 $this) {
   }
}
