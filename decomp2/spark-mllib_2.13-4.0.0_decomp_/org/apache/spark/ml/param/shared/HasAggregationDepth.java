package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003'!\u000b7/Q4he\u0016<\u0017\r^5p]\u0012+\u0007\u000f\u001e5\u000b\u0005\u00199\u0011AB:iCJ,GM\u0003\u0002\t\u0013\u0005)\u0001/\u0019:b[*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0004\n\u0005q9!A\u0002)be\u0006l7/\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011A\u0003I\u0005\u0003CU\u0011A!\u00168ji\u0006\u0001\u0012mZ4sK\u001e\fG/[8o\t\u0016\u0004H\u000f[\u000b\u0002IA\u0011!$J\u0005\u0003M\u001d\u0011\u0001\"\u00138u!\u0006\u0014\u0018-\\\u0001\u0014O\u0016$\u0018iZ4sK\u001e\fG/[8o\t\u0016\u0004H\u000f[\u000b\u0002SA\u0011ACK\u0005\u0003WU\u00111!\u00138u\u0001"
)
public interface HasAggregationDepth extends Params {
   void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1);

   IntParam aggregationDepth();

   // $FF: synthetic method
   static int getAggregationDepth$(final HasAggregationDepth $this) {
      return $this.getAggregationDepth();
   }

   default int getAggregationDepth() {
      return BoxesRunTime.unboxToInt(this.$(this.aggregationDepth()));
   }

   static void $init$(final HasAggregationDepth $this) {
      $this.org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(new IntParam($this, "aggregationDepth", "suggested depth for treeAggregate (>= 2)", ParamValidators$.MODULE$.gtEq((double)2.0F)));
      $this.setDefault($this.aggregationDepth(), BoxesRunTime.boxToInteger(2));
   }
}
