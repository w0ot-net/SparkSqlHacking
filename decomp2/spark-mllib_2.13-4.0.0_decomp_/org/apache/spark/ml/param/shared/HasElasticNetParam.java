package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003%!\u000b7/\u00127bgRL7MT3u!\u0006\u0014\u0018-\u001c\u0006\u0003\r\u001d\taa\u001d5be\u0016$'B\u0001\u0005\n\u0003\u0015\u0001\u0018M]1n\u0015\tQ1\"\u0001\u0002nY*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035mi\u0011aB\u0005\u00039\u001d\u0011a\u0001U1sC6\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001 !\t!\u0002%\u0003\u0002\"+\t!QK\\5u\u0003=)G.Y:uS\u000etU\r\u001e)be\u0006lW#\u0001\u0013\u0011\u0005i)\u0013B\u0001\u0014\b\u0005-!u.\u001e2mKB\u000b'/Y7\u0002%\u001d,G/\u00127bgRL7MT3u!\u0006\u0014\u0018-\\\u000b\u0002SA\u0011ACK\u0005\u0003WU\u0011a\u0001R8vE2,\u0007"
)
public interface HasElasticNetParam extends Params {
   void org$apache$spark$ml$param$shared$HasElasticNetParam$_setter_$elasticNetParam_$eq(final DoubleParam x$1);

   DoubleParam elasticNetParam();

   // $FF: synthetic method
   static double getElasticNetParam$(final HasElasticNetParam $this) {
      return $this.getElasticNetParam();
   }

   default double getElasticNetParam() {
      return BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam()));
   }

   static void $init$(final HasElasticNetParam $this) {
      $this.org$apache$spark$ml$param$shared$HasElasticNetParam$_setter_$elasticNetParam_$eq(new DoubleParam($this, "elasticNetParam", "the ElasticNet mixing parameter, in range [0, 1]. For alpha = 0, the penalty is an L2 penalty. For alpha = 1, it is an L1 penalty", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
   }
}
