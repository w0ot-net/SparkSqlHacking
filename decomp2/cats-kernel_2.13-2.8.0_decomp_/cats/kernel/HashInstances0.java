package cats.kernel;

import cats.kernel.instances.seq.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006'\u0001!\t!\u0006\u0005\u00063\u0001!\u0019A\u0007\u0002\u000f\u0011\u0006\u001c\b.\u00138ti\u0006t7-Z:1\u0015\t)a!\u0001\u0004lKJtW\r\u001c\u0006\u0002\u000f\u0005!1-\u0019;t'\r\u0001\u0011b\u0004\t\u0003\u00155i\u0011a\u0003\u0006\u0002\u0019\u0005)1oY1mC&\u0011ab\u0003\u0002\u0007\u0003:L(+\u001a4\u0011\u0005A\tR\"\u0001\u0003\n\u0005I!!aC#r\u0013:\u001cH/\u00198dKN\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002-A\u0011!bF\u0005\u00031-\u0011A!\u00168ji\u0006!2-\u0019;t\u0017\u0016\u0014h.\u001a7ICNDgi\u001c:TKF,\"aG\u0015\u0015\u0005q\u0011\u0004c\u0001\t\u001e?%\u0011a\u0004\u0002\u0002\u0005\u0011\u0006\u001c\b\u000eE\u0002!K\u001dj\u0011!\t\u0006\u0003E\r\n\u0011\"[7nkR\f'\r\\3\u000b\u0005\u0011Z\u0011AC2pY2,7\r^5p]&\u0011a%\t\u0002\u0004'\u0016\f\bC\u0001\u0015*\u0019\u0001!QA\u000b\u0002C\u0002-\u0012\u0011!Q\t\u0003Y=\u0002\"AC\u0017\n\u00059Z!a\u0002(pi\"Lgn\u001a\t\u0003\u0015AJ!!M\u0006\u0003\u0007\u0005s\u0017\u0010C\u00044\u0005\u0005\u0005\t9\u0001\u001b\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$3\u0007\r\t\u0004!u9\u0003"
)
public interface HashInstances0 extends EqInstances {
   // $FF: synthetic method
   static Hash catsKernelHashForSeq$(final HashInstances0 $this, final Hash evidence$30) {
      return $this.catsKernelHashForSeq(evidence$30);
   }

   default Hash catsKernelHashForSeq(final Hash evidence$30) {
      return package$.MODULE$.catsKernelStdHashForSeq(evidence$30);
   }

   static void $init$(final HashInstances0 $this) {
   }
}
