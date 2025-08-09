package cats.kernel;

import cats.kernel.instances.seq.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006'\u0001!\t!\u0006\u0005\u00063\u0001!\u0019A\u0007\u0002\u0010\u001fJ$WM]%ogR\fgnY3ta)\u0011QAB\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003\u001d\tAaY1ugN\u0019\u0001!C\b\u0011\u0005)iQ\"A\u0006\u000b\u00031\tQa]2bY\u0006L!AD\u0006\u0003\r\u0005s\u0017PU3g!\t\u0001\u0012#D\u0001\u0005\u0013\t\u0011BAA\u000bQCJ$\u0018.\u00197Pe\u0012,'/\u00138ti\u0006t7-Z:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012A\u0006\t\u0003\u0015]I!\u0001G\u0006\u0003\tUs\u0017\u000e^\u0001\u0016G\u0006$8oS3s]\u0016dwJ\u001d3fe\u001a{'oU3r+\tY\u0012\u0006\u0006\u0002\u001deA\u0019\u0001#H\u0010\n\u0005y!!!B(sI\u0016\u0014\bc\u0001\u0011&O5\t\u0011E\u0003\u0002#G\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003I-\t!bY8mY\u0016\u001cG/[8o\u0013\t1\u0013EA\u0002TKF\u0004\"\u0001K\u0015\r\u0001\u0011)!F\u0001b\u0001W\t\t\u0011)\u0005\u0002-_A\u0011!\"L\u0005\u0003]-\u0011qAT8uQ&tw\r\u0005\u0002\u000ba%\u0011\u0011g\u0003\u0002\u0004\u0003:L\bbB\u001a\u0003\u0003\u0003\u0005\u001d\u0001N\u0001\u000bKZLG-\u001a8dK\u0012J\u0004c\u0001\t\u001eO\u0001"
)
public interface OrderInstances0 extends PartialOrderInstances {
   // $FF: synthetic method
   static Order catsKernelOrderForSeq$(final OrderInstances0 $this, final Order evidence$9) {
      return $this.catsKernelOrderForSeq(evidence$9);
   }

   default Order catsKernelOrderForSeq(final Order evidence$9) {
      return package$.MODULE$.catsKernelStdOrderForSeq(evidence$9);
   }

   static void $init$(final OrderInstances0 $this) {
   }
}
