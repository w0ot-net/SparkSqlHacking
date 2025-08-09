package cats.kernel.instances;

import cats.kernel.LowerBounded;
import cats.kernel.UpperBounded;
import java.util.UUID;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0005\u0003\u0006C\u0003*\u0001\u0011\u0005\u0003FA\u0006V+&#%i\\;oI\u0016$'B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0014\t\u0001i1c\b\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Q)r#D\u0001\b\u0013\t1rA\u0001\u0007M_^,'OQ8v]\u0012,G\r\u0005\u0002\u0019;5\t\u0011D\u0003\u0002\u001b7\u0005!Q\u000f^5m\u0015\u0005a\u0012\u0001\u00026bm\u0006L!AH\r\u0003\tU+\u0016\n\u0012\t\u0004)\u0001:\u0012BA\u0011\b\u00051)\u0006\u000f]3s\u0005>,h\u000eZ3e\u0003\u0019!\u0013N\\5uIQ\tA\u0005\u0005\u0002\u000fK%\u0011ae\u0004\u0002\u0005+:LG/\u0001\u0005nS:\u0014u.\u001e8e+\u00059\u0012\u0001C7bq\n{WO\u001c3"
)
public interface UUIDBounded extends LowerBounded, UpperBounded {
   // $FF: synthetic method
   static UUID minBound$(final UUIDBounded $this) {
      return $this.minBound();
   }

   default UUID minBound() {
      return new UUID(Long.MIN_VALUE, Long.MIN_VALUE);
   }

   // $FF: synthetic method
   static UUID maxBound$(final UUIDBounded $this) {
      return $this.maxBound();
   }

   default UUID maxBound() {
      return new UUID(Long.MAX_VALUE, Long.MAX_VALUE);
   }

   static void $init$(final UUIDBounded $this) {
   }
}
