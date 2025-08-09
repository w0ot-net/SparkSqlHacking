package cats.kernel;

import cats.kernel.instances.sortedMap.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006'\u0001!\t!\u0006\u0005\u00063\u0001!\u0019A\u0007\u0002\u0017!\u0006\u0014H/[1m\u001fJ$WM]%ogR\fgnY3tc)\u0011QAB\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003\u001d\tAaY1ugN\u0019\u0001!C\b\u0011\u0005)iQ\"A\u0006\u000b\u00031\tQa]2bY\u0006L!AD\u0006\u0003\r\u0005s\u0017PU3g!\t\u0001\u0012#D\u0001\u0005\u0013\t\u0011BA\u0001\fQCJ$\u0018.\u00197Pe\u0012,'/\u00138ti\u0006t7-Z:1\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\f\u0011\u0005)9\u0012B\u0001\r\f\u0005\u0011)f.\u001b;\u0002E\r\fGo]&fe:,G\u000eU1si&\fGn\u0014:eKJ4uN]*peR,G-T1q+\rY\u0012f\r\u000b\u00039U\u00022\u0001E\u000f \u0013\tqBA\u0001\u0007QCJ$\u0018.\u00197Pe\u0012,'\u000f\u0005\u0003!K\u001d\u0012T\"A\u0011\u000b\u0005\t\u001a\u0013!C5n[V$\u0018M\u00197f\u0015\t!3\"\u0001\u0006d_2dWm\u0019;j_:L!AJ\u0011\u0003\u0013M{'\u000f^3e\u001b\u0006\u0004\bC\u0001\u0015*\u0019\u0001!QA\u000b\u0002C\u0002-\u0012\u0011aS\t\u0003Y=\u0002\"AC\u0017\n\u00059Z!a\u0002(pi\"Lgn\u001a\t\u0003\u0015AJ!!M\u0006\u0003\u0007\u0005s\u0017\u0010\u0005\u0002)g\u0011)AG\u0001b\u0001W\t\ta\u000bC\u00047\u0005\u0005\u0005\t9A\u001c\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0004!u\u0011\u0004"
)
public interface PartialOrderInstances1 extends PartialOrderInstances0 {
   // $FF: synthetic method
   static PartialOrder catsKernelPartialOrderForSortedMap$(final PartialOrderInstances1 $this, final PartialOrder evidence$17) {
      return $this.catsKernelPartialOrderForSortedMap(evidence$17);
   }

   default PartialOrder catsKernelPartialOrderForSortedMap(final PartialOrder evidence$17) {
      return package$.MODULE$.catsKernelStdPartialOrderForSortedMap(evidence$17);
   }

   static void $init$(final PartialOrderInstances1 $this) {
   }
}
