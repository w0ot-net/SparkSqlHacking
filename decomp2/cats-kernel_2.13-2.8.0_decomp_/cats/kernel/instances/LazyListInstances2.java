package cats.kernel.instances;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u0013\u0019\u0006T\u0018\u0010T5ti&s7\u000f^1oG\u0016\u001c(G\u0003\u0002\u0006\r\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u000f!\taa[3s]\u0016d'\"A\u0005\u0002\t\r\fGo]\n\u0003\u0001-\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003Q\u0001\"\u0001D\u000b\n\u0005Yi!\u0001B+oSR\f!dY1ug.+'O\\3m'R$W)\u001d$pe2\u000b'0\u001f'jgR,\"!\u0007\u0017\u0015\u0005i)\u0004cA\u000e\u001d=5\ta!\u0003\u0002\u001e\r\t\u0011Q)\u001d\t\u0004?\u001dRcB\u0001\u0011&\u001d\t\tC%D\u0001#\u0015\t\u0019##\u0001\u0004=e>|GOP\u0005\u0002\u001d%\u0011a%D\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0013F\u0001\u0005MCjLH*[:u\u0015\t1S\u0002\u0005\u0002,Y1\u0001A!B\u0017\u0003\u0005\u0004q#!A!\u0012\u0005=\u0012\u0004C\u0001\u00071\u0013\t\tTBA\u0004O_RD\u0017N\\4\u0011\u00051\u0019\u0014B\u0001\u001b\u000e\u0005\r\te.\u001f\u0005\bm\t\t\t\u0011q\u00018\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u00047qQ\u0003"
)
public interface LazyListInstances2 {
   // $FF: synthetic method
   static Eq catsKernelStdEqForLazyList$(final LazyListInstances2 $this, final Eq evidence$4) {
      return $this.catsKernelStdEqForLazyList(evidence$4);
   }

   default Eq catsKernelStdEqForLazyList(final Eq evidence$4) {
      return new LazyListEq(evidence$4);
   }

   static void $init$(final LazyListInstances2 $this) {
   }
}
