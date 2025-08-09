package org.apache.spark.rpc;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005a\u0001\u0004\u0005\u0006/\u0001!\t!\u0007\u0005\u0006;\u0001!)A\b\u0002\u001e\u0013N|G.\u0019;fIRC'/Z1e'\u00064WM\u00159d\u000b:$\u0007o\\5oi*\u0011QAB\u0001\u0004eB\u001c'BA\u0004\t\u0003\u0015\u0019\b/\u0019:l\u0015\tI!\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0017\u0005\u0019qN]4\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0003)Ui\u0011\u0001B\u0005\u0003-\u0011\u00111#S:pY\u0006$X\r\u001a*qG\u0016sG\r]8j]R\fa\u0001J5oSR$3\u0001\u0001\u000b\u00025A\u0011abG\u0005\u00039=\u0011A!\u00168ji\u0006YA\u000f\u001b:fC\u0012\u001cu.\u001e8u)\u0005y\u0002C\u0001\b!\u0013\t\tsBA\u0002J]R\u0004"
)
public interface IsolatedThreadSafeRpcEndpoint extends IsolatedRpcEndpoint {
   // $FF: synthetic method
   static int threadCount$(final IsolatedThreadSafeRpcEndpoint $this) {
      return $this.threadCount();
   }

   default int threadCount() {
      return 1;
   }

   static void $init$(final IsolatedThreadSafeRpcEndpoint $this) {
   }
}
