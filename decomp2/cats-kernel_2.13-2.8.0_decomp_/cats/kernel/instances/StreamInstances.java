package cats.kernel.instances;

import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003H\u0001\u0011\r\u0001JA\bTiJ,\u0017-\\%ogR\fgnY3t\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u00011c\u0001\u0001\u000e'A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u0004\"\u0001F\u000b\u000e\u0003\u0015I!AF\u0003\u0003!M#(/Z1n\u0013:\u001cH/\u00198dKN\f\u0014A\u0002\u0013j]&$H\u0005F\u0001\u001a!\tq!$\u0003\u0002\u001c\u001f\t!QK\\5u\u0003m\u0019\u0017\r^:LKJtW\r\\*uI>\u0013H-\u001a:G_J\u001cFO]3b[V\u0011a$\r\u000b\u0003?i\u00022\u0001I\u0011$\u001b\u00059\u0011B\u0001\u0012\b\u0005\u0015y%\u000fZ3s!\r!Cf\f\b\u0003K)r!AJ\u0015\u000e\u0003\u001dR!\u0001K\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012BA\u0016\u0010\u0003\u001d\u0001\u0018mY6bO\u0016L!!\f\u0018\u0003\rM#(/Z1n\u0015\tYs\u0002\u0005\u00021c1\u0001A!\u0002\u001a\u0003\u0005\u0004\u0019$!A!\u0012\u0005Q:\u0004C\u0001\b6\u0013\t1tBA\u0004O_RD\u0017N\\4\u0011\u00059A\u0014BA\u001d\u0010\u0005\r\te.\u001f\u0005\bw\t\t\t\u0011q\u0001=\u0003))g/\u001b3f]\u000e,G%\r\t\u0004A\u0005z\u0003F\u0002\u0002?\u0003\n#U\t\u0005\u0002\u000f\u007f%\u0011\u0001i\u0004\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0002\u0007\u0006\u0011Sk]3!G\u0006$8OL6fe:,GNL5ogR\fgnY3t]1\f'0\u001f'jgR\fQa]5oG\u0016\f\u0013AR\u0001\ne9\u0002d\u0006M\u0017S\u0007J\nAdY1ug.+'O\\3m'R$Wj\u001c8pS\u00124uN]*ue\u0016\fW.\u0006\u0002J\u001fV\t!\nE\u0002!\u00176K!\u0001T\u0004\u0003\r5{gn\\5e!\r!CF\u0014\t\u0003a=#QAM\u0002C\u0002MBca\u0001 B\u0005\u0012+\u0005"
)
public interface StreamInstances extends StreamInstances1 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForStream$(final StreamInstances $this, final Order evidence$1) {
      return $this.catsKernelStdOrderForStream(evidence$1);
   }

   /** @deprecated */
   default Order catsKernelStdOrderForStream(final Order evidence$1) {
      return new StreamOrder(evidence$1);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForStream$(final StreamInstances $this) {
      return $this.catsKernelStdMonoidForStream();
   }

   /** @deprecated */
   default Monoid catsKernelStdMonoidForStream() {
      return new StreamMonoid();
   }

   static void $init$(final StreamInstances $this) {
   }
}
