package org.apache.spark.rpc;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e1QAA\u0002\u0001\u0007-AQ\u0001\u0006\u0001\u0005\u0002Y\u0011aC\u00159d\u000b:48\u000b^8qa\u0016$W\t_2faRLwN\u001c\u0006\u0003\t\u0015\t1A\u001d9d\u0015\t1q!A\u0003ta\u0006\u00148N\u0003\u0002\t\u0013\u00051\u0011\r]1dQ\u0016T\u0011AC\u0001\u0004_J<7C\u0001\u0001\r!\ti!#D\u0001\u000f\u0015\ty\u0001#\u0001\u0003mC:<'\"A\t\u0002\t)\fg/Y\u0005\u0003'9\u0011Q#\u00137mK\u001e\fGn\u0015;bi\u0016,\u0005pY3qi&|g.\u0001\u0004=S:LGOP\u0002\u0001)\u00059\u0002C\u0001\r\u0001\u001b\u0005\u0019\u0001"
)
public class RpcEnvStoppedException extends IllegalStateException {
   public RpcEnvStoppedException() {
      super("RpcEnv already stopped.");
   }
}
