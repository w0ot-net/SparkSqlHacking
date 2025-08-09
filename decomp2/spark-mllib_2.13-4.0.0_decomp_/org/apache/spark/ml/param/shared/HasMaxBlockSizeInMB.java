package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003'!\u000b7/T1y\u00052|7m[*ju\u0016Le.\u0014\"\u000b\u0005\u00199\u0011AB:iCJ,GM\u0003\u0002\t\u0013\u0005)\u0001/\u0019:b[*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0004\n\u0005q9!A\u0002)be\u0006l7/\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011A\u0003I\u0005\u0003CU\u0011A!\u00168ji\u0006\u0001R.\u0019=CY>\u001c7nU5{K&sWJQ\u000b\u0002IA\u0011!$J\u0005\u0003M\u001d\u00111\u0002R8vE2,\u0007+\u0019:b[\u0006\u0019r-\u001a;NCb\u0014En\\2l'&TX-\u00138N\u0005V\t\u0011\u0006\u0005\u0002\u0015U%\u00111&\u0006\u0002\u0007\t>,(\r\\3"
)
public interface HasMaxBlockSizeInMB extends Params {
   void org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(final DoubleParam x$1);

   DoubleParam maxBlockSizeInMB();

   // $FF: synthetic method
   static double getMaxBlockSizeInMB$(final HasMaxBlockSizeInMB $this) {
      return $this.getMaxBlockSizeInMB();
   }

   default double getMaxBlockSizeInMB() {
      return BoxesRunTime.unboxToDouble(this.$(this.maxBlockSizeInMB()));
   }

   static void $init$(final HasMaxBlockSizeInMB $this) {
      $this.org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(new DoubleParam($this, "maxBlockSizeInMB", "Maximum memory in MB for stacking input data into blocks. Data is stacked within partitions. If more than remaining data size in a partition then it is adjusted to the data size. Default 0.0 represents choosing optimal value, depends on specific algorithm. Must be >= 0.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.setDefault($this.maxBlockSizeInMB(), BoxesRunTime.boxToDouble((double)0.0F));
   }
}
