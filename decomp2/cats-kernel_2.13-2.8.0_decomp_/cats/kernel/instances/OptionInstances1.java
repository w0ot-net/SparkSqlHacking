package cats.kernel.instances;

import cats.kernel.Hash;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0002\u0011\u001fB$\u0018n\u001c8J]N$\u0018M\\2fgFR!!\u0002\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8c\u0001\u0001\f#A\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\u0004\"AE\n\u000e\u0003\u0011I!\u0001\u0006\u0003\u0003!=\u0003H/[8o\u0013:\u001cH/\u00198dKN\u0014\u0014A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003a\u0001\"\u0001D\r\n\u0005ii!\u0001B+oSR\f!dY1ug.+'O\\3m'R$\u0007*Y:i\r>\u0014x\n\u001d;j_:,\"!H\u0014\u0015\u0005y\u0001\u0004cA\u0010!E5\ta!\u0003\u0002\"\r\t!\u0001*Y:i!\ra1%J\u0005\u0003I5\u0011aa\u00149uS>t\u0007C\u0001\u0014(\u0019\u0001!Q\u0001\u000b\u0002C\u0002%\u0012\u0011!Q\t\u0003U5\u0002\"\u0001D\u0016\n\u00051j!a\u0002(pi\"Lgn\u001a\t\u0003\u00199J!aL\u0007\u0003\u0007\u0005s\u0017\u0010C\u00042\u0005\u0005\u0005\t9\u0001\u001a\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u0002 A\u0015\u0002"
)
public interface OptionInstances1 extends OptionInstances2 {
   // $FF: synthetic method
   static Hash catsKernelStdHashForOption$(final OptionInstances1 $this, final Hash evidence$5) {
      return $this.catsKernelStdHashForOption(evidence$5);
   }

   default Hash catsKernelStdHashForOption(final Hash evidence$5) {
      return new OptionHash(evidence$5);
   }

   static void $init$(final OptionInstances1 $this) {
   }
}
