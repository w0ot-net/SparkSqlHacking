package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003\u0015!\u000b7/T1y\u0013R,'O\u0003\u0002\u0007\u000f\u000511\u000f[1sK\u0012T!\u0001C\u0005\u0002\u000bA\f'/Y7\u000b\u0005)Y\u0011AA7m\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7\u0001A\n\u0004\u0001MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\r\u0005\u0002\u001b75\tq!\u0003\u0002\u001d\u000f\t1\u0001+\u0019:b[N\fa\u0001J5oSR$C#A\u0010\u0011\u0005Q\u0001\u0013BA\u0011\u0016\u0005\u0011)f.\u001b;\u0002\u000f5\f\u00070\u0013;feV\tA\u0005\u0005\u0002\u001bK%\u0011ae\u0002\u0002\t\u0013:$\b+\u0019:b[\u0006Qq-\u001a;NCbLE/\u001a:\u0016\u0003%\u0002\"\u0001\u0006\u0016\n\u0005-*\"aA%oi\u0002"
)
public interface HasMaxIter extends Params {
   void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1);

   IntParam maxIter();

   // $FF: synthetic method
   static int getMaxIter$(final HasMaxIter $this) {
      return $this.getMaxIter();
   }

   default int getMaxIter() {
      return BoxesRunTime.unboxToInt(this.$(this.maxIter()));
   }

   static void $init$(final HasMaxIter $this) {
      $this.org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(new IntParam($this, "maxIter", "maximum number of iterations (>= 0)", ParamValidators$.MODULE$.gtEq((double)0.0F)));
   }
}
