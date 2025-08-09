package cats.kernel.instances;

import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u0001!\u0019\u0001\b\u0002\u0011\u001fB$\u0018n\u001c8J]N$\u0018M\\2fgBR!!\u0002\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8c\u0001\u0001\f#A\u0011AbD\u0007\u0002\u001b)\ta\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0011\u001b\t1\u0011I\\=SK\u001a\u0004\"AE\n\u000e\u0003\u0011I!\u0001\u0006\u0003\u0003!=\u0003H/[8o\u0013:\u001cH/\u00198dKN\f\u0014A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003a\u0001\"\u0001D\r\n\u0005ii!\u0001B+oSR\f!eY1ug.+'O\\3m'R$\u0007+\u0019:uS\u0006dwJ\u001d3fe\u001a{'o\u00149uS>tWCA\u000f()\tq\u0002\u0007E\u0002 A\tj\u0011AB\u0005\u0003C\u0019\u0011A\u0002U1si&\fGn\u0014:eKJ\u00042\u0001D\u0012&\u0013\t!SB\u0001\u0004PaRLwN\u001c\t\u0003M\u001db\u0001\u0001B\u0003)\u0005\t\u0007\u0011FA\u0001B#\tQS\u0006\u0005\u0002\rW%\u0011A&\u0004\u0002\b\u001d>$\b.\u001b8h!\taa&\u0003\u00020\u001b\t\u0019\u0011I\\=\t\u000fE\u0012\u0011\u0011!a\u0002e\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\u0007}\u0001S\u0005"
)
public interface OptionInstances0 extends OptionInstances1 {
   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForOption$(final OptionInstances0 $this, final PartialOrder evidence$4) {
      return $this.catsKernelStdPartialOrderForOption(evidence$4);
   }

   default PartialOrder catsKernelStdPartialOrderForOption(final PartialOrder evidence$4) {
      return new OptionPartialOrder(evidence$4);
   }

   static void $init$(final OptionInstances0 $this) {
   }
}
