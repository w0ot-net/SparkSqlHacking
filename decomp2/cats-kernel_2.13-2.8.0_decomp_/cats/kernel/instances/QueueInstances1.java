package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\u0019!\b\u0005\u0006s\u0001!\u0019A\u000f\u0002\u0010#V,W/Z%ogR\fgnY3tc)\u0011aaB\u0001\nS:\u001cH/\u00198dKNT!\u0001C\u0005\u0002\r-,'O\\3m\u0015\u0005Q\u0011\u0001B2biN\u001c2\u0001\u0001\u0007\u0013!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00111\u0003F\u0007\u0002\u000b%\u0011Q#\u0002\u0002\u0010#V,W/Z%ogR\fgnY3te\u00051A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u001a!\ti!$\u0003\u0002\u001c\u001d\t!QK\\5u\u0003\u0005\u001a\u0017\r^:LKJtW\r\\*uIB\u000b'\u000f^5bY>\u0013H-\u001a:G_J\fV/Z;f+\tqR\u0006\u0006\u0002 mA\u0019\u0001%I\u0012\u000e\u0003\u001dI!AI\u0004\u0003\u0019A\u000b'\u000f^5bY>\u0013H-\u001a:\u0011\u0007\u0011J3&D\u0001&\u0015\t1s%A\u0005j[6,H/\u00192mK*\u0011\u0001FD\u0001\u000bG>dG.Z2uS>t\u0017B\u0001\u0016&\u0005\u0015\tV/Z;f!\taS\u0006\u0004\u0001\u0005\u000b9\u0012!\u0019A\u0018\u0003\u0003\u0005\u000b\"\u0001M\u001a\u0011\u00055\t\u0014B\u0001\u001a\u000f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0004\u001b\n\u0005Ur!aA!os\"9qGAA\u0001\u0002\bA\u0014AC3wS\u0012,gnY3%eA\u0019\u0001%I\u0016\u00023\r\fGo]&fe:,Gn\u0015;e\u0011\u0006\u001c\bNR8s#V,W/Z\u000b\u0003w\u0005#\"\u0001\u0010\"\u0011\u0007\u0001jt(\u0003\u0002?\u000f\t!\u0001*Y:i!\r!\u0013\u0006\u0011\t\u0003Y\u0005#QAL\u0002C\u0002=BqaQ\u0002\u0002\u0002\u0003\u000fA)\u0001\u0006fm&$WM\\2fIM\u00022\u0001I\u001fA\u0001"
)
public interface QueueInstances1 extends QueueInstances2 {
   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForQueue$(final QueueInstances1 $this, final PartialOrder evidence$2) {
      return $this.catsKernelStdPartialOrderForQueue(evidence$2);
   }

   default PartialOrder catsKernelStdPartialOrderForQueue(final PartialOrder evidence$2) {
      return new QueuePartialOrder(evidence$2);
   }

   // $FF: synthetic method
   static Hash catsKernelStdHashForQueue$(final QueueInstances1 $this, final Hash evidence$3) {
      return $this.catsKernelStdHashForQueue(evidence$3);
   }

   default Hash catsKernelStdHashForQueue(final Hash evidence$3) {
      return new QueueHash(evidence$3);
   }

   static void $init$(final QueueInstances1 $this) {
   }
}
