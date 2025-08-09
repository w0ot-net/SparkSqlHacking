package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007I\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\u0003\u0019!\u000b7\u000f\u00165sKNDw\u000e\u001c3\u000b\u0005\u00199\u0011AB:iCJ,GM\u0003\u0002\t\u0013\u0005)\u0001/\u0019:b[*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0004\n\u0005q9!A\u0002)be\u0006l7/\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011A\u0003I\u0005\u0003CU\u0011A!\u00168ji\u0006IA\u000f\u001b:fg\"|G\u000eZ\u000b\u0002IA\u0011!$J\u0005\u0003M\u001d\u00111\u0002R8vE2,\u0007+\u0019:b[\u0006aq-\u001a;UQJ,7\u000f[8mIV\t\u0011\u0006\u0005\u0002\u0015U%\u00111&\u0006\u0002\u0007\t>,(\r\\3"
)
public interface HasThreshold extends Params {
   void org$apache$spark$ml$param$shared$HasThreshold$_setter_$threshold_$eq(final DoubleParam x$1);

   DoubleParam threshold();

   // $FF: synthetic method
   static double getThreshold$(final HasThreshold $this) {
      return $this.getThreshold();
   }

   default double getThreshold() {
      return BoxesRunTime.unboxToDouble(this.$(this.threshold()));
   }

   static void $init$(final HasThreshold $this) {
      $this.org$apache$spark$ml$param$shared$HasThreshold$_setter_$threshold_$eq(new DoubleParam($this, "threshold", "threshold in binary classification prediction, in range [0, 1]", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
   }
}
