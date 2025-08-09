package org.apache.spark.ml.classification;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasStandardization;
import org.apache.spark.ml.param.shared.HasThreshold;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0004\u0005\u0006s\u0001!\ta\u000f\u0005\b\u007f\u0001\u0011\r\u0011\"\u0012A\u0005=a\u0015N\\3beN36\tU1sC6\u001c(BA\u0003\u0007\u00039\u0019G.Y:tS\u001aL7-\u0019;j_:T!a\u0002\u0005\u0002\u00055d'BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0014\u0019\u0001yQ#G\u0011%O)j\u0003g\r\u001c\u0011\u0005A\u0019R\"A\t\u000b\u0003I\tQa]2bY\u0006L!\u0001F\t\u0003\r\u0005s\u0017PU3g!\t1r#D\u0001\u0005\u0013\tABA\u0001\tDY\u0006\u001c8/\u001b4jKJ\u0004\u0016M]1ngB\u0011!dH\u0007\u00027)\u0011A$H\u0001\u0007g\"\f'/\u001a3\u000b\u0005y1\u0011!\u00029be\u0006l\u0017B\u0001\u0011\u001c\u0005-A\u0015m\u001d*fOB\u000b'/Y7\u0011\u0005i\u0011\u0013BA\u0012\u001c\u0005)A\u0015m]'bq&#XM\u001d\t\u00035\u0015J!AJ\u000e\u0003\u001f!\u000b7OR5u\u0013:$XM]2faR\u0004\"A\u0007\u0015\n\u0005%Z\"A\u0002%bgR{G\u000e\u0005\u0002\u001bW%\u0011Af\u0007\u0002\u0013\u0011\u0006\u001c8\u000b^1oI\u0006\u0014H-\u001b>bi&|g\u000e\u0005\u0002\u001b]%\u0011qf\u0007\u0002\r\u0011\u0006\u001cx+Z5hQR\u001cu\u000e\u001c\t\u00035EJ!AM\u000e\u0003'!\u000b7/Q4he\u0016<\u0017\r^5p]\u0012+\u0007\u000f\u001e5\u0011\u0005i!\u0014BA\u001b\u001c\u00051A\u0015m\u001d+ie\u0016\u001c\bn\u001c7e!\tQr'\u0003\u000297\t\u0019\u0002*Y:NCb\u0014En\\2l'&TX-\u00138N\u0005\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001=!\t\u0001R(\u0003\u0002?#\t!QK\\5u\u0003%!\bN]3tQ>dG-F\u0001B!\t\u00115)D\u0001\u001e\u0013\t!UDA\u0006E_V\u0014G.\u001a)be\u0006l\u0007"
)
public interface LinearSVCParams extends ClassifierParams, HasRegParam, HasMaxIter, HasFitIntercept, HasTol, HasStandardization, HasWeightCol, HasAggregationDepth, HasThreshold, HasMaxBlockSizeInMB {
   void org$apache$spark$ml$classification$LinearSVCParams$_setter_$threshold_$eq(final DoubleParam x$1);

   DoubleParam threshold();

   static void $init$(final LinearSVCParams $this) {
      $this.org$apache$spark$ml$classification$LinearSVCParams$_setter_$threshold_$eq(new DoubleParam($this, "threshold", "threshold in binary classification prediction applied to rawPrediction"));
      $this.setDefault(.MODULE$.wrapRefArray(new ParamPair[]{$this.regParam().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(100)), $this.fitIntercept().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(1.0E-6)), $this.standardization().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.threshold().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.aggregationDepth().$minus$greater(BoxesRunTime.boxToInteger(2)), $this.maxBlockSizeInMB().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F))}));
   }
}
