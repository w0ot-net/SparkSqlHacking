package cats.kernel.instances;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u0011-\u0016\u001cGo\u001c:J]N$\u0018M\\2fgJR!!\u0002\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8C\u0001\u0001\f!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0015!\taQ#\u0003\u0002\u0017\u001b\t!QK\\5u\u0003a\u0019\u0017\r^:LKJtW\r\\*uI\u0016\u000bhi\u001c:WK\u000e$xN]\u000b\u000331\"\"AG\u001b\u0011\u0007mab$D\u0001\u0007\u0013\tibA\u0001\u0002FcB\u0019qd\n\u0016\u000f\u0005\u0001*cBA\u0011%\u001b\u0005\u0011#BA\u0012\u0013\u0003\u0019a$o\\8u}%\ta\"\u0003\u0002'\u001b\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0015*\u0005\u00191Vm\u0019;pe*\u0011a%\u0004\t\u0003W1b\u0001\u0001B\u0003.\u0005\t\u0007aFA\u0001B#\ty#\u0007\u0005\u0002\ra%\u0011\u0011'\u0004\u0002\b\u001d>$\b.\u001b8h!\ta1'\u0003\u00025\u001b\t\u0019\u0011I\\=\t\u000fY\u0012\u0011\u0011!a\u0002o\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\u0007ma\"\u0006"
)
public interface VectorInstances2 {
   // $FF: synthetic method
   static Eq catsKernelStdEqForVector$(final VectorInstances2 $this, final Eq evidence$4) {
      return $this.catsKernelStdEqForVector(evidence$4);
   }

   default Eq catsKernelStdEqForVector(final Eq evidence$4) {
      return new VectorEq(evidence$4);
   }

   static void $init$(final VectorInstances2 $this) {
   }
}
