package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r2Qa\u0001\u0003\u0001\t)A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006Ia\u0007\u0005\u0006=\u0001!\ta\b\u0002$)\u0006\u001c8nT;uaV$h)\u001b7f\u00032\u0014X-\u00193z\u000bbL7\u000f^#yG\u0016\u0004H/[8o\u0015\t)a!A\u0003ta\u0006\u00148N\u0003\u0002\b\u0011\u00051\u0011\r]1dQ\u0016T\u0011!C\u0001\u0004_J<7C\u0001\u0001\f!\taqC\u0004\u0002\u000e)9\u0011aBE\u0007\u0002\u001f)\u0011\u0001#E\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016-\u00059\u0001/Y2lC\u001e,'\"A\n\n\u0005aI\"!C#yG\u0016\u0004H/[8o\u0015\t)b#A\u0003feJ|'\u000f\u0005\u0002\r9%\u0011Q$\u0007\u0002\n)\"\u0014xn^1cY\u0016\fa\u0001P5oSRtDC\u0001\u0011#!\t\t\u0003!D\u0001\u0005\u0011\u0015Q\"\u00011\u0001\u001c\u0001"
)
public class TaskOutputFileAlreadyExistException extends Exception {
   public TaskOutputFileAlreadyExistException(final Throwable error) {
      super(error);
   }
}
