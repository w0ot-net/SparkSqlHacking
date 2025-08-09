package cats.kernel.instances;

import cats.kernel.LowerBounded$mcI$sp;
import cats.kernel.UpperBounded$mcI$sp;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003#\u0001\u0011\u00053\u0005C\u0003%\u0001\u0011\u00053E\u0001\u0006J]R\u0014u.\u001e8eK\u0012T!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\n\u0005\u00015\u0019\"\u0004\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0004)U9R\"A\u0004\n\u0005Y9!\u0001\u0004'po\u0016\u0014(i\\;oI\u0016$\u0007C\u0001\b\u0019\u0013\tIrBA\u0002J]R\u00042\u0001F\u000e\u0018\u0013\tarA\u0001\u0007VaB,'OQ8v]\u0012,G-\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011a\u0002I\u0005\u0003C=\u0011A!\u00168ji\u0006AQ.\u001b8C_VtG-F\u0001\u0018\u0003!i\u0017\r\u001f\"pk:$\u0007"
)
public interface IntBounded extends LowerBounded$mcI$sp, UpperBounded$mcI$sp {
   // $FF: synthetic method
   static int minBound$(final IntBounded $this) {
      return $this.minBound();
   }

   default int minBound() {
      return this.minBound$mcI$sp();
   }

   // $FF: synthetic method
   static int maxBound$(final IntBounded $this) {
      return $this.maxBound();
   }

   default int maxBound() {
      return this.maxBound$mcI$sp();
   }

   // $FF: synthetic method
   static int minBound$mcI$sp$(final IntBounded $this) {
      return $this.minBound$mcI$sp();
   }

   default int minBound$mcI$sp() {
      return Integer.MIN_VALUE;
   }

   // $FF: synthetic method
   static int maxBound$mcI$sp$(final IntBounded $this) {
      return $this.maxBound$mcI$sp();
   }

   default int maxBound$mcI$sp() {
      return Integer.MAX_VALUE;
   }

   static void $init$(final IntBounded $this) {
   }
}
