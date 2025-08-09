package org.apache.spark.mllib.feature;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!:a\u0001B\u0003\t\u0002\u0015yaAB\t\u0006\u0011\u0003)!\u0003C\u0003\u001a\u0003\u0011\u00051\u0004C\u0003\u001d\u0003\u0011\u0005Q$A\u0004Q\u0007\u0006+F/\u001b7\u000b\u0005\u00199\u0011a\u00024fCR,(/\u001a\u0006\u0003\u0011%\tQ!\u001c7mS\nT!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'o\u001a\t\u0003!\u0005i\u0011!\u0002\u0002\b!\u000e\u000bU\u000b^5m'\t\t1\u0003\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq\"\u0001\u0006nK6|'/_\"pgR$2AH\u0011'!\t!r$\u0003\u0002!+\t!Aj\u001c8h\u0011\u0015\u00113\u00011\u0001$\u0003\u0005Y\u0007C\u0001\u000b%\u0013\t)SCA\u0002J]RDQaJ\u0002A\u0002\r\n1B\\;n\r\u0016\fG/\u001e:fg\u0002"
)
public final class PCAUtil {
   public static long memoryCost(final int k, final int numFeatures) {
      return PCAUtil$.MODULE$.memoryCost(k, numFeatures);
   }
}
