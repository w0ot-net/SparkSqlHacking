package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\u0019!\b\u0005\u0006{\u0001!\u0019A\u0010\u0002\u0011-\u0016\u001cGo\u001c:J]N$\u0018M\\2fgFR!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u0004\"a\u0005\u000b\u000e\u0003\u0015I!!F\u0003\u0003!Y+7\r^8s\u0013:\u001cH/\u00198dKN\u0014\u0014A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003e\u0001\"!\u0004\u000e\n\u0005mq!\u0001B+oSR\f!eY1ug.+'O\\3m'R$\u0007+\u0019:uS\u0006dwJ\u001d3fe\u001a{'OV3di>\u0014XC\u0001\u00102)\ty\"\bE\u0002!C\rj\u0011aB\u0005\u0003E\u001d\u0011A\u0002U1si&\fGn\u0014:eKJ\u00042\u0001\n\u00170\u001d\t)#F\u0004\u0002'S5\tqE\u0003\u0002)/\u00051AH]8pizJ\u0011aD\u0005\u0003W9\tq\u0001]1dW\u0006<W-\u0003\u0002.]\t1a+Z2u_JT!a\u000b\b\u0011\u0005A\nD\u0002\u0001\u0003\u0006e\t\u0011\ra\r\u0002\u0002\u0003F\u0011Ag\u000e\t\u0003\u001bUJ!A\u000e\b\u0003\u000f9{G\u000f[5oOB\u0011Q\u0002O\u0005\u0003s9\u00111!\u00118z\u0011\u001dY$!!AA\u0004q\n!\"\u001a<jI\u0016t7-\u001a\u00133!\r\u0001\u0013eL\u0001\u001bG\u0006$8oS3s]\u0016d7\u000b\u001e3ICNDgi\u001c:WK\u000e$xN]\u000b\u0003\u007f\u0015#\"\u0001\u0011$\u0011\u0007\u0001\n5)\u0003\u0002C\u000f\t!\u0001*Y:i!\r!C\u0006\u0012\t\u0003a\u0015#QAM\u0002C\u0002MBqaR\u0002\u0002\u0002\u0003\u000f\u0001*\u0001\u0006fm&$WM\\2fIM\u00022\u0001I!E\u0001"
)
public interface VectorInstances1 extends VectorInstances2 {
   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForVector$(final VectorInstances1 $this, final PartialOrder evidence$2) {
      return $this.catsKernelStdPartialOrderForVector(evidence$2);
   }

   default PartialOrder catsKernelStdPartialOrderForVector(final PartialOrder evidence$2) {
      return new VectorPartialOrder(evidence$2);
   }

   // $FF: synthetic method
   static Hash catsKernelStdHashForVector$(final VectorInstances1 $this, final Hash evidence$3) {
      return $this.catsKernelStdHashForVector(evidence$3);
   }

   default Hash catsKernelStdHashForVector(final Hash evidence$3) {
      return new VectorHash(evidence$3);
   }

   static void $init$(final VectorInstances1 $this) {
   }
}
