package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003%!\u000b7o\u0015;b]\u0012\f'\u000fZ5{CRLwN\u001c\u0006\u0003\r\u001d\taa\u001d5be\u0016$'B\u0001\u0005\n\u0003\u0015\u0001\u0018M]1n\u0015\tQ1\"\u0001\u0002nY*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035mi\u0011aB\u0005\u00039\u001d\u0011a\u0001U1sC6\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001 !\t!\u0002%\u0003\u0002\"+\t!QK\\5u\u0003=\u0019H/\u00198eCJ$\u0017N_1uS>tW#\u0001\u0013\u0011\u0005i)\u0013B\u0001\u0014\b\u00051\u0011un\u001c7fC:\u0004\u0016M]1n\u0003I9W\r^*uC:$\u0017M\u001d3ju\u0006$\u0018n\u001c8\u0016\u0003%\u0002\"\u0001\u0006\u0016\n\u0005-*\"a\u0002\"p_2,\u0017M\u001c"
)
public interface HasStandardization extends Params {
   void org$apache$spark$ml$param$shared$HasStandardization$_setter_$standardization_$eq(final BooleanParam x$1);

   BooleanParam standardization();

   // $FF: synthetic method
   static boolean getStandardization$(final HasStandardization $this) {
      return $this.getStandardization();
   }

   default boolean getStandardization() {
      return BoxesRunTime.unboxToBoolean(this.$(this.standardization()));
   }

   static void $init$(final HasStandardization $this) {
      $this.org$apache$spark$ml$param$shared$HasStandardization$_setter_$standardization_$eq(new BooleanParam($this, "standardization", "whether to standardize the training features before fitting the model"));
      $this.setDefault($this.standardization(), BoxesRunTime.boxToBoolean(true));
   }
}
