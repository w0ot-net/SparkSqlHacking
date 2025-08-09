package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\u0019!\b\u0005\u0006\u000f\u0002!\u0019\u0001\u0013\u0002\u0011'R\u0014X-Y7J]N$\u0018M\\2fgFR!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u0004\"a\u0005\u000b\u000e\u0003\u0015I!!F\u0003\u0003!M#(/Z1n\u0013:\u001cH/\u00198dKN\u0014\u0014A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003e\u0001\"!\u0004\u000e\n\u0005mq!\u0001B+oSR\f!eY1ug.+'O\\3m'R$\u0007+\u0019:uS\u0006dwJ\u001d3fe\u001a{'o\u0015;sK\u0006lWC\u0001\u00102)\ty\"\bE\u0002!C\rj\u0011aB\u0005\u0003E\u001d\u0011A\u0002U1si&\fGn\u0014:eKJ\u00042\u0001\n\u00170\u001d\t)#F\u0004\u0002'S5\tqE\u0003\u0002)/\u00051AH]8pizJ\u0011aD\u0005\u0003W9\tq\u0001]1dW\u0006<W-\u0003\u0002.]\t11\u000b\u001e:fC6T!a\u000b\b\u0011\u0005A\nD\u0002\u0001\u0003\u0006e\t\u0011\ra\r\u0002\u0002\u0003F\u0011Ag\u000e\t\u0003\u001bUJ!A\u000e\b\u0003\u000f9{G\u000f[5oOB\u0011Q\u0002O\u0005\u0003s9\u00111!\u00118z\u0011\u001dY$!!AA\u0004q\n!\"\u001a<jI\u0016t7-\u001a\u00133!\r\u0001\u0013e\f\u0015\u0007\u0005y\n%\tR#\u0011\u00055y\u0014B\u0001!\u000f\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005\u0019\u0015AI+tK\u0002\u001a\u0017\r^:/W\u0016\u0014h.\u001a7/S:\u001cH/\u00198dKNtC.\u0019>z\u0019&\u001cH/A\u0003tS:\u001cW-I\u0001G\u0003%\u0011d\u0006\r\u00181[I\u001b%'\u0001\u000edCR\u001c8*\u001a:oK2\u001cF\u000f\u001a%bg\"4uN]*ue\u0016\fW.\u0006\u0002J\u001fR\u0011!\n\u0015\t\u0004A-k\u0015B\u0001'\b\u0005\u0011A\u0015m\u001d5\u0011\u0007\u0011bc\n\u0005\u00021\u001f\u0012)!g\u0001b\u0001g!9\u0011kAA\u0001\u0002\b\u0011\u0016AC3wS\u0012,gnY3%gA\u0019\u0001e\u0013()\r\rq\u0014I\u0011#F\u0001"
)
public interface StreamInstances1 extends StreamInstances2 {
   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForStream$(final StreamInstances1 $this, final PartialOrder evidence$2) {
      return $this.catsKernelStdPartialOrderForStream(evidence$2);
   }

   /** @deprecated */
   default PartialOrder catsKernelStdPartialOrderForStream(final PartialOrder evidence$2) {
      return new StreamPartialOrder(evidence$2);
   }

   // $FF: synthetic method
   static Hash catsKernelStdHashForStream$(final StreamInstances1 $this, final Hash evidence$3) {
      return $this.catsKernelStdHashForStream(evidence$3);
   }

   /** @deprecated */
   default Hash catsKernelStdHashForStream(final Hash evidence$3) {
      return new StreamHash(evidence$3);
   }

   static void $init$(final StreamInstances1 $this) {
   }
}
