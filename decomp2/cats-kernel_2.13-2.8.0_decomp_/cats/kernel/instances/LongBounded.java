package cats.kernel.instances;

import cats.kernel.LowerBounded$mcJ$sp;
import cats.kernel.UpperBounded$mcJ$sp;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003#\u0001\u0011\u00053\u0005C\u0003%\u0001\u0011\u00053EA\u0006M_:<'i\\;oI\u0016$'B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0014\t\u0001i1C\u0007\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Q)r#D\u0001\b\u0013\t1rA\u0001\u0007VaB,'OQ8v]\u0012,G\r\u0005\u0002\u000f1%\u0011\u0011d\u0004\u0002\u0005\u0019>tw\rE\u0002\u00157]I!\u0001H\u0004\u0003\u00191{w/\u001a:C_VtG-\u001a3\u0002\r\u0011Jg.\u001b;%)\u0005y\u0002C\u0001\b!\u0013\t\tsB\u0001\u0003V]&$\u0018\u0001C7j]\n{WO\u001c3\u0016\u0003]\t\u0001\"\\1y\u0005>,h\u000e\u001a"
)
public interface LongBounded extends UpperBounded$mcJ$sp, LowerBounded$mcJ$sp {
   // $FF: synthetic method
   static long minBound$(final LongBounded $this) {
      return $this.minBound();
   }

   default long minBound() {
      return this.minBound$mcJ$sp();
   }

   // $FF: synthetic method
   static long maxBound$(final LongBounded $this) {
      return $this.maxBound();
   }

   default long maxBound() {
      return this.maxBound$mcJ$sp();
   }

   // $FF: synthetic method
   static long minBound$mcJ$sp$(final LongBounded $this) {
      return $this.minBound$mcJ$sp();
   }

   default long minBound$mcJ$sp() {
      return Long.MIN_VALUE;
   }

   // $FF: synthetic method
   static long maxBound$mcJ$sp$(final LongBounded $this) {
      return $this.maxBound$mcJ$sp();
   }

   default long maxBound$mcJ$sp() {
      return Long.MAX_VALUE;
   }

   static void $init$(final LongBounded $this) {
   }
}
