package org.apache.spark.scheduler;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q1QAA\u0002\u0001\u000b-AQ\u0001\u0005\u0001\u0005\u0002I\u0011aEQ1se&,'OS8c+:\u001cX\u000f\u001d9peR,GM\u0015#E\u0007\"\f\u0017N\\#yG\u0016\u0004H/[8o\u0015\t!Q!A\u0005tG\",G-\u001e7fe*\u0011aaB\u0001\u0006gB\f'o\u001b\u0006\u0003\u0011%\ta!\u00199bG\",'\"\u0001\u0006\u0002\u0007=\u0014xm\u0005\u0002\u0001\u0019A\u0011QBD\u0007\u0002\u0007%\u0011qb\u0001\u0002\u001b\u0005\u0006\u0014(/[3s\u0015>\u0014\u0017\t\u001c7pG\u0006$\u0018n\u001c8GC&dW\rZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1\u0003\u0005\u0002\u000e\u0001\u0001"
)
public class BarrierJobUnsupportedRDDChainException extends BarrierJobAllocationFailed {
   public BarrierJobUnsupportedRDDChainException() {
      super(BarrierJobAllocationFailed$.MODULE$.ERROR_MESSAGE_RUN_BARRIER_WITH_UNSUPPORTED_RDD_CHAIN_PATTERN());
   }
}
