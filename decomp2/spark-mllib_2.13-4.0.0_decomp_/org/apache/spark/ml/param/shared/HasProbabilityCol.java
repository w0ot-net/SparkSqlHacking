package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005Q2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000bI\u0002AQA\u001a\u0003#!\u000b7\u000f\u0015:pE\u0006\u0014\u0017\u000e\\5us\u000e{GN\u0003\u0002\u0007\u000f\u000511\u000f[1sK\u0012T!\u0001C\u0005\u0002\u000bA\f'/Y7\u000b\u0005)Y\u0011AA7m\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7\u0001A\n\u0004\u0001MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\r\u0005\u0002\u001b75\tq!\u0003\u0002\u001d\u000f\t1\u0001+\u0019:b[N\fa\u0001J5oSR$C#A\u0010\u0011\u0005Q\u0001\u0013BA\u0011\u0016\u0005\u0011)f.\u001b;\u0002\u001dA\u0014xNY1cS2LG/_\"pYV\tA\u0005E\u0002\u001bK\u001dJ!AJ\u0004\u0003\u000bA\u000b'/Y7\u0011\u0005!zcBA\u0015.!\tQS#D\u0001,\u0015\ta\u0013#\u0001\u0004=e>|GOP\u0005\u0003]U\ta\u0001\u0015:fI\u00164\u0017B\u0001\u00192\u0005\u0019\u0019FO]5oO*\u0011a&F\u0001\u0012O\u0016$\bK]8cC\nLG.\u001b;z\u0007>dW#A\u0014"
)
public interface HasProbabilityCol extends Params {
   void org$apache$spark$ml$param$shared$HasProbabilityCol$_setter_$probabilityCol_$eq(final Param x$1);

   Param probabilityCol();

   // $FF: synthetic method
   static String getProbabilityCol$(final HasProbabilityCol $this) {
      return $this.getProbabilityCol();
   }

   default String getProbabilityCol() {
      return (String)this.$(this.probabilityCol());
   }

   static void $init$(final HasProbabilityCol $this) {
      $this.org$apache$spark$ml$param$shared$HasProbabilityCol$_setter_$probabilityCol_$eq(new Param($this, "probabilityCol", "Column name for predicted class conditional probabilities. Note: Not all models output well-calibrated probability estimates! These probabilities should be treated as confidences, not precise probabilities", .MODULE$.apply(String.class)));
      $this.setDefault($this.probabilityCol(), "probability");
   }
}
