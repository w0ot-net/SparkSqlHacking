package cats.kernel.instances;

import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0011\rQ\u0004C\u0003>\u0001\u0011\raHA\bWK\u000e$xN]%ogR\fgnY3t\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u00011c\u0001\u0001\u000e'A\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u0004\"\u0001F\u000b\u000e\u0003\u0015I!AF\u0003\u0003!Y+7\r^8s\u0013:\u001cH/\u00198dKN\f\u0014A\u0002\u0013j]&$H\u0005F\u0001\u001a!\tq!$\u0003\u0002\u001c\u001f\t!QK\\5u\u0003m\u0019\u0017\r^:LKJtW\r\\*uI>\u0013H-\u001a:G_J4Vm\u0019;peV\u0011a$\r\u000b\u0003?i\u00022\u0001I\u0011$\u001b\u00059\u0011B\u0001\u0012\b\u0005\u0015y%\u000fZ3s!\r!Cf\f\b\u0003K)r!AJ\u0015\u000e\u0003\u001dR!\u0001K\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0012BA\u0016\u0010\u0003\u001d\u0001\u0018mY6bO\u0016L!!\f\u0018\u0003\rY+7\r^8s\u0015\tYs\u0002\u0005\u00021c1\u0001A!\u0002\u001a\u0003\u0005\u0004\u0019$!A!\u0012\u0005Q:\u0004C\u0001\b6\u0013\t1tBA\u0004O_RD\u0017N\\4\u0011\u00059A\u0014BA\u001d\u0010\u0005\r\te.\u001f\u0005\bw\t\t\t\u0011q\u0001=\u0003))g/\u001b3f]\u000e,G%\r\t\u0004A\u0005z\u0013\u0001H2biN\\UM\u001d8fYN#H-T8o_&$gi\u001c:WK\u000e$xN]\u000b\u0003\u007f\u0015+\u0012\u0001\u0011\t\u0004A\u0005\u001b\u0015B\u0001\"\b\u0005\u0019iuN\\8jIB\u0019A\u0005\f#\u0011\u0005A*E!\u0002\u001a\u0004\u0005\u0004\u0019\u0004F\u0001\u0001H!\tAuJ\u0004\u0002J\u0019:\u0011\u0001ES\u0005\u0003\u0017\u001e\taaY8na\u0006$\u0018BA'O\u0003Q\u00198-\u00197b-\u0016\u00148/[8o'B,7-\u001b4jG*\u00111jB\u0005\u0003!F\u0013!g];qaJ,7o]+okN,G-S7q_J$x+\u0019:oS:<gi\u001c:TG\u0006d\u0017MV3sg&|gn\u00159fG&4\u0017n\u0019\u0006\u0003\u001b:\u0003"
)
public interface VectorInstances extends VectorInstances1 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForVector$(final VectorInstances $this, final Order evidence$1) {
      return $this.catsKernelStdOrderForVector(evidence$1);
   }

   default Order catsKernelStdOrderForVector(final Order evidence$1) {
      return new VectorOrder(evidence$1);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForVector$(final VectorInstances $this) {
      return $this.catsKernelStdMonoidForVector();
   }

   default Monoid catsKernelStdMonoidForVector() {
      return new VectorMonoid();
   }

   static void $init$(final VectorInstances $this) {
   }
}
