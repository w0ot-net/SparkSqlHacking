package cats.kernel.instances;

import cats.kernel.LowerBounded$mcS$sp;
import cats.kernel.UpperBounded$mcS$sp;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003#\u0001\u0011\u00053\u0005C\u0003%\u0001\u0011\u00053E\u0001\u0007TQ>\u0014HOQ8v]\u0012,GM\u0003\u0002\u0007\u000f\u0005I\u0011N\\:uC:\u001cWm\u001d\u0006\u0003\u0011%\taa[3s]\u0016d'\"\u0001\u0006\u0002\t\r\fGo]\u0002\u0001'\u0011\u0001Qb\u0005\u000e\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g!\r!RcF\u0007\u0002\u000f%\u0011ac\u0002\u0002\r\u0019><XM\u001d\"pk:$W\r\u001a\t\u0003\u001daI!!G\b\u0003\u000bMCwN\u001d;\u0011\u0007QYr#\u0003\u0002\u001d\u000f\taQ\u000b\u001d9fe\n{WO\u001c3fI\u00061A%\u001b8ji\u0012\"\u0012a\b\t\u0003\u001d\u0001J!!I\b\u0003\tUs\u0017\u000e^\u0001\t[&t'i\\;oIV\tq#\u0001\u0005nCb\u0014u.\u001e8e\u0001"
)
public interface ShortBounded extends LowerBounded$mcS$sp, UpperBounded$mcS$sp {
   // $FF: synthetic method
   static short minBound$(final ShortBounded $this) {
      return $this.minBound();
   }

   default short minBound() {
      return this.minBound$mcS$sp();
   }

   // $FF: synthetic method
   static short maxBound$(final ShortBounded $this) {
      return $this.maxBound();
   }

   default short maxBound() {
      return this.maxBound$mcS$sp();
   }

   // $FF: synthetic method
   static short minBound$mcS$sp$(final ShortBounded $this) {
      return $this.minBound$mcS$sp();
   }

   default short minBound$mcS$sp() {
      return Short.MIN_VALUE;
   }

   // $FF: synthetic method
   static short maxBound$mcS$sp$(final ShortBounded $this) {
      return $this.maxBound$mcS$sp();
   }

   default short maxBound$mcS$sp() {
      return 32767;
   }

   static void $init$(final ShortBounded $this) {
   }
}
