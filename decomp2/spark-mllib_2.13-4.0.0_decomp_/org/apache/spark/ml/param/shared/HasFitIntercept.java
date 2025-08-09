package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003\u001f!\u000b7OR5u\u0013:$XM]2faRT!AB\u0004\u0002\rMD\u0017M]3e\u0015\tA\u0011\"A\u0003qCJ\fWN\u0003\u0002\u000b\u0017\u0005\u0011Q\u000e\u001c\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sO\u000e\u00011c\u0001\u0001\u00143A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\u0004\"AG\u000e\u000e\u0003\u001dI!\u0001H\u0004\u0003\rA\u000b'/Y7t\u0003\u0019!\u0013N\\5uIQ\tq\u0004\u0005\u0002\u0015A%\u0011\u0011%\u0006\u0002\u0005+:LG/\u0001\u0007gSRLe\u000e^3sG\u0016\u0004H/F\u0001%!\tQR%\u0003\u0002'\u000f\ta!i\\8mK\u0006t\u0007+\u0019:b[\u0006yq-\u001a;GSRLe\u000e^3sG\u0016\u0004H/F\u0001*!\t!\"&\u0003\u0002,+\t9!i\\8mK\u0006t\u0007"
)
public interface HasFitIntercept extends Params {
   void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1);

   BooleanParam fitIntercept();

   // $FF: synthetic method
   static boolean getFitIntercept$(final HasFitIntercept $this) {
      return $this.getFitIntercept();
   }

   default boolean getFitIntercept() {
      return BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()));
   }

   static void $init$(final HasFitIntercept $this) {
      $this.org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(new BooleanParam($this, "fitIntercept", "whether to fit an intercept term"));
      $this.setDefault($this.fitIntercept(), BoxesRunTime.boxToBoolean(true));
   }
}
