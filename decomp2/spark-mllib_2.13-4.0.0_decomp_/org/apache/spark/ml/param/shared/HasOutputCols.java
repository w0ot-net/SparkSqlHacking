package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.param.StringArrayParam;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003\u001b!\u000b7oT;uaV$8i\u001c7t\u0015\t1q!\u0001\u0004tQ\u0006\u0014X\r\u001a\u0006\u0003\u0011%\tQ\u0001]1sC6T!AC\u0006\u0002\u00055d'B\u0001\u0007\u000e\u0003\u0015\u0019\b/\u0019:l\u0015\tqq\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002!\u0005\u0019qN]4\u0004\u0001M\u0019\u0001aE\r\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\tQ2$D\u0001\b\u0013\tarA\u0001\u0004QCJ\fWn]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"\u0001\u0006\u0011\n\u0005\u0005*\"\u0001B+oSR\f!b\\;uaV$8i\u001c7t+\u0005!\u0003C\u0001\u000e&\u0013\t1sA\u0001\tTiJLgnZ!se\u0006L\b+\u0019:b[\u0006iq-\u001a;PkR\u0004X\u000f^\"pYN,\u0012!\u000b\t\u0004))b\u0013BA\u0016\u0016\u0005\u0015\t%O]1z!\tiCG\u0004\u0002/eA\u0011q&F\u0007\u0002a)\u0011\u0011'E\u0001\u0007yI|w\u000e\u001e \n\u0005M*\u0012A\u0002)sK\u0012,g-\u0003\u00026m\t11\u000b\u001e:j]\u001eT!aM\u000b"
)
public interface HasOutputCols extends Params {
   void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1);

   StringArrayParam outputCols();

   // $FF: synthetic method
   static String[] getOutputCols$(final HasOutputCols $this) {
      return $this.getOutputCols();
   }

   default String[] getOutputCols() {
      return (String[])this.$(this.outputCols());
   }

   static void $init$(final HasOutputCols $this) {
      $this.org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(new StringArrayParam($this, "outputCols", "output column names"));
   }
}
