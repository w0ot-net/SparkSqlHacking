package org.apache.spark.ml.tuning;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u000552\u0001\u0002B\u0003\u0011\u0002\u0007\u0005qa\u0004\u0005\u00065\u0001!\t\u0001\b\u0005\bA\u0001\u0011\r\u0011\"\u0001\"\u0011\u0015A\u0003\u0001\"\u0001*\u0005i!&/Y5o-\u0006d\u0017\u000eZ1uS>t7\u000b\u001d7jiB\u000b'/Y7t\u0015\t1q!\u0001\u0004uk:Lgn\u001a\u0006\u0003\u0011%\t!!\u001c7\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c2\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0011q\u0003G\u0007\u0002\u000b%\u0011\u0011$\u0002\u0002\u0010-\u0006d\u0017\u000eZ1u_J\u0004\u0016M]1ng\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u001e!\t\tb$\u0003\u0002 %\t!QK\\5u\u0003)!(/Y5o%\u0006$\u0018n\\\u000b\u0002EA\u00111EJ\u0007\u0002I)\u0011QeB\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003O\u0011\u00121\u0002R8vE2,\u0007+\u0019:b[\u0006iq-\u001a;Ue\u0006LgNU1uS>,\u0012A\u000b\t\u0003#-J!\u0001\f\n\u0003\r\u0011{WO\u00197f\u0001"
)
public interface TrainValidationSplitParams extends ValidatorParams {
   void org$apache$spark$ml$tuning$TrainValidationSplitParams$_setter_$trainRatio_$eq(final DoubleParam x$1);

   DoubleParam trainRatio();

   // $FF: synthetic method
   static double getTrainRatio$(final TrainValidationSplitParams $this) {
      return $this.getTrainRatio();
   }

   default double getTrainRatio() {
      return BoxesRunTime.unboxToDouble(this.$(this.trainRatio()));
   }

   static void $init$(final TrainValidationSplitParams $this) {
      $this.org$apache$spark$ml$tuning$TrainValidationSplitParams$_setter_$trainRatio_$eq(new DoubleParam($this, "trainRatio", "ratio between training set and validation set (>= 0 && <= 1)", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
      $this.setDefault(.MODULE$.wrapRefArray(new ParamPair[]{$this.trainRatio().$minus$greater(BoxesRunTime.boxToDouble((double)0.75F))}));
   }
}
