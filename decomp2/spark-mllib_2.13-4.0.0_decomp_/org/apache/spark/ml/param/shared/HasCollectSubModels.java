package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003'!\u000b7oQ8mY\u0016\u001cGoU;c\u001b>$W\r\\:\u000b\u0005\u00199\u0011AB:iCJ,GM\u0003\u0002\t\u0013\u0005)\u0001/\u0019:b[*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0004\n\u0005q9!A\u0002)be\u0006l7/\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011A\u0003I\u0005\u0003CU\u0011A!\u00168ji\u0006\u00012m\u001c7mK\u000e$8+\u001e2N_\u0012,Gn]\u000b\u0002IA\u0011!$J\u0005\u0003M\u001d\u0011ABQ8pY\u0016\fg\u000eU1sC6\f1cZ3u\u0007>dG.Z2u'V\u0014Wj\u001c3fYN,\u0012!\u000b\t\u0003))J!aK\u000b\u0003\u000f\t{w\u000e\\3b]\u0002"
)
public interface HasCollectSubModels extends Params {
   void org$apache$spark$ml$param$shared$HasCollectSubModels$_setter_$collectSubModels_$eq(final BooleanParam x$1);

   BooleanParam collectSubModels();

   // $FF: synthetic method
   static boolean getCollectSubModels$(final HasCollectSubModels $this) {
      return $this.getCollectSubModels();
   }

   default boolean getCollectSubModels() {
      return BoxesRunTime.unboxToBoolean(this.$(this.collectSubModels()));
   }

   static void $init$(final HasCollectSubModels $this) {
      $this.org$apache$spark$ml$param$shared$HasCollectSubModels$_setter_$collectSubModels_$eq(new BooleanParam($this, "collectSubModels", "whether to collect a list of sub-models trained during tuning. If set to false, then only the single best sub-model will be available after fitting. If set to true, then all sub-models will be available. Warning: For large models, collecting all sub-models can cause OOMs on the Spark driver"));
      $this.setDefault($this.collectSubModels(), BoxesRunTime.boxToBoolean(false));
   }
}
