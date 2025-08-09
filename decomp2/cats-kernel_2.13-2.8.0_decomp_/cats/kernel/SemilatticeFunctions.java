package cats.kernel;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Q\u0001B\u0003\u0002\u0002)AQA\n\u0001\u0005\u0002\u001dBQ!\u000b\u0001\u0005\u0002)BQA\u000f\u0001\u0005\u0002m\u0012AcU3nS2\fG\u000f^5dK\u001a+hn\u0019;j_:\u001c(B\u0001\u0004\b\u0003\u0019YWM\u001d8fY*\t\u0001\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u0017I\u0019\"\u0001\u0001\u0007\u0011\u00075q\u0001#D\u0001\u0006\u0013\tyQA\u0001\nTK6LwM]8va\u001a+hn\u0019;j_:\u001c\bCA\t\u0013\u0019\u0001!Qa\u0005\u0001C\u0002Q\u0011\u0011aU\u000b\u0003+\u0001\n\"A\u0006\u000f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\u000f9{G\u000f[5oOB\u0019Q\"H\u0010\n\u0005y)!aC*f[&d\u0017\r\u001e;jG\u0016\u0004\"!\u0005\u0011\u0005\u000b\u0005\u0012\"\u0019\u0001\u0012\u0003\u0003Q\u000b\"AF\u0012\u0011\u0005]!\u0013BA\u0013\u0019\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003!\u00022!\u0004\u0001\u0011\u0003I\t7/T3fiB\u000b'\u000f^5bY>\u0013H-\u001a:\u0016\u0005-\u0002Dc\u0001\u00173kA\u0019Q\"L\u0018\n\u00059*!\u0001\u0004)beRL\u0017\r\\(sI\u0016\u0014\bCA\t1\t\u0015\t$A1\u0001#\u0005\u0005\t\u0005\"B\u001a\u0003\u0001\b!\u0014!A:\u0011\u0007E\u0011r\u0006C\u00037\u0005\u0001\u000fq'\u0001\u0002fmB\u0019Q\u0002O\u0018\n\u0005e*!AA#r\u0003I\t7OS8j]B\u000b'\u000f^5bY>\u0013H-\u001a:\u0016\u0005qzDcA\u001fA\u0005B\u0019Q\"\f \u0011\u0005EyD!B\u0019\u0004\u0005\u0004\u0011\u0003\"B\u001a\u0004\u0001\b\t\u0005cA\t\u0013}!)ag\u0001a\u0002\u0007B\u0019Q\u0002\u000f "
)
public abstract class SemilatticeFunctions extends SemigroupFunctions {
   public PartialOrder asMeetPartialOrder(final Semilattice s, final Eq ev) {
      return s.asMeetPartialOrder(ev);
   }

   public PartialOrder asJoinPartialOrder(final Semilattice s, final Eq ev) {
      return s.asJoinPartialOrder(ev);
   }
}
