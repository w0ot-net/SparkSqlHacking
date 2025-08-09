package breeze.util;

import scala.Product;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0011B\u0007\u0005\u0006!\u0001!\t!\u0005\u0005\t+\u0001A)\u0019!C!-\tq1)Y2iK\u0012D\u0015m\u001d5D_\u0012,'BA\u0003\u0007\u0003\u0011)H/\u001b7\u000b\u0003\u001d\taA\u0019:fKj,7\u0001A\n\u0003\u0001)\u0001\"a\u0003\b\u000e\u00031Q\u0011!D\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f1\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0013!\tY1#\u0003\u0002\u0015\u0019\t!QK\\5u\u0003!A\u0017m\u001d5D_\u0012,W#A\f\u0011\u0005-A\u0012BA\r\r\u0005\rIe\u000e\u001e\n\u00047}\tc\u0001\u0002\u000f\u0001\u0001i\u0011A\u0002\u0010:fM&tW-\\3oizR!A\b\u0005\u0002\rq\u0012xn\u001c;?!\t\u0001\u0003!D\u0001\u0005!\tY!%\u0003\u0002$\u0019\t9\u0001K]8ek\u000e$\b"
)
public interface CachedHashCode {
   // $FF: synthetic method
   static int hashCode$(final CachedHashCode $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return .MODULE$._hashCode((Product)this);
   }

   static void $init$(final CachedHashCode $this) {
   }
}
