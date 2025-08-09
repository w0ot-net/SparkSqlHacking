package cats.kernel.instances;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u0010#V,W/Z%ogR\fgnY3te)\u0011QAB\u0001\nS:\u001cH/\u00198dKNT!a\u0002\u0005\u0002\r-,'O\\3m\u0015\u0005I\u0011\u0001B2biN\u001c\"\u0001A\u0006\u0011\u00051yQ\"A\u0007\u000b\u00039\tQa]2bY\u0006L!\u0001E\u0007\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u000b\u0011\u00051)\u0012B\u0001\f\u000e\u0005\u0011)f.\u001b;\u0002/\r\fGo]&fe:,Gn\u0015;e\u000bF4uN])vKV,WCA\r))\tQ\u0012\u0007E\u0002\u001c9yi\u0011AB\u0005\u0003;\u0019\u0011!!R9\u0011\u0007}!c%D\u0001!\u0015\t\t#%A\u0005j[6,H/\u00192mK*\u00111%D\u0001\u000bG>dG.Z2uS>t\u0017BA\u0013!\u0005\u0015\tV/Z;f!\t9\u0003\u0006\u0004\u0001\u0005\u000b%\u0012!\u0019\u0001\u0016\u0003\u0003\u0005\u000b\"a\u000b\u0018\u0011\u00051a\u0013BA\u0017\u000e\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001D\u0018\n\u0005Aj!aA!os\"9!GAA\u0001\u0002\b\u0019\u0014AC3wS\u0012,gnY3%iA\u00191\u0004\b\u0014"
)
public interface QueueInstances2 {
   // $FF: synthetic method
   static Eq catsKernelStdEqForQueue$(final QueueInstances2 $this, final Eq evidence$4) {
      return $this.catsKernelStdEqForQueue(evidence$4);
   }

   default Eq catsKernelStdEqForQueue(final Eq evidence$4) {
      return new QueueEq(evidence$4);
   }

   static void $init$(final QueueInstances2 $this) {
   }
}
