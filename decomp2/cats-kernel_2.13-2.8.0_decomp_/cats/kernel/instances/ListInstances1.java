package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\u0019!\b\u0005\u0006{\u0001!\u0019A\u0010\u0002\u000f\u0019&\u001cH/\u00138ti\u0006t7-Z:2\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ugN\u0019\u0001\u0001\u0004\n\u0011\u00055\u0001R\"\u0001\b\u000b\u0003=\tQa]2bY\u0006L!!\u0005\b\u0003\r\u0005s\u0017PU3g!\t\u0019B#D\u0001\u0006\u0013\t)RA\u0001\bMSN$\u0018J\\:uC:\u001cWm\u001d\u001a\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012!\u0007\t\u0003\u001biI!a\u0007\b\u0003\tUs\u0017\u000e^\u0001!G\u0006$8oS3s]\u0016d7\u000b\u001e3QCJ$\u0018.\u00197Pe\u0012,'OR8s\u0019&\u001cH/\u0006\u0002\u001fcQ\u0011qD\u000f\t\u0004A\u0005\u001aS\"A\u0004\n\u0005\t:!\u0001\u0004)beRL\u0017\r\\(sI\u0016\u0014\bc\u0001\u0013-_9\u0011QE\u000b\b\u0003M%j\u0011a\n\u0006\u0003Q]\ta\u0001\u0010:p_Rt\u0014\"A\b\n\u0005-r\u0011a\u00029bG.\fw-Z\u0005\u0003[9\u0012A\u0001T5ti*\u00111F\u0004\t\u0003aEb\u0001\u0001B\u00033\u0005\t\u00071GA\u0001B#\t!t\u0007\u0005\u0002\u000ek%\u0011aG\u0004\u0002\b\u001d>$\b.\u001b8h!\ti\u0001(\u0003\u0002:\u001d\t\u0019\u0011I\\=\t\u000fm\u0012\u0011\u0011!a\u0002y\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\u0007\u0001\ns&\u0001\rdCR\u001c8*\u001a:oK2\u001cF\u000f\u001a%bg\"4uN\u001d'jgR,\"aP#\u0015\u0005\u00013\u0005c\u0001\u0011B\u0007&\u0011!i\u0002\u0002\u0005\u0011\u0006\u001c\b\u000eE\u0002%Y\u0011\u0003\"\u0001M#\u0005\u000bI\u001a!\u0019A\u001a\t\u000f\u001d\u001b\u0011\u0011!a\u0002\u0011\u0006QQM^5eK:\u001cW\rJ\u001a\u0011\u0007\u0001\nE\t"
)
public interface ListInstances1 extends ListInstances2 {
   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForList$(final ListInstances1 $this, final PartialOrder evidence$2) {
      return $this.catsKernelStdPartialOrderForList(evidence$2);
   }

   default PartialOrder catsKernelStdPartialOrderForList(final PartialOrder evidence$2) {
      return new ListPartialOrder(evidence$2);
   }

   // $FF: synthetic method
   static Hash catsKernelStdHashForList$(final ListInstances1 $this, final Hash evidence$3) {
      return $this.catsKernelStdHashForList(evidence$3);
   }

   default Hash catsKernelStdHashForList(final Hash evidence$3) {
      return new ListHash(evidence$3);
   }

   static void $init$(final ListInstances1 $this) {
   }
}
