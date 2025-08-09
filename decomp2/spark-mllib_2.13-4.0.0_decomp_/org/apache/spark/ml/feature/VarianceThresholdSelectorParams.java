package org.apache.spark.ml.feature;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005}2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0004\u0005\u0006K\u0001!\ta\n\u0005\bW\u0001\u0011\r\u0011\"\u0002-\u0011\u0015I\u0004\u0001\"\u0001;\u0005}1\u0016M]5b]\u000e,G\u000b\u001b:fg\"|G\u000eZ*fY\u0016\u001cGo\u001c:QCJ\fWn\u001d\u0006\u0003\r\u001d\tqAZ3biV\u0014XM\u0003\u0002\t\u0013\u0005\u0011Q\u000e\u001c\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sON)\u0001\u0001\u0005\f\u001dEA\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u0004\"a\u0006\u000e\u000e\u0003aQ!!G\u0004\u0002\u000bA\f'/Y7\n\u0005mA\"A\u0002)be\u0006l7\u000f\u0005\u0002\u001eA5\taD\u0003\u0002 1\u000511\u000f[1sK\u0012L!!\t\u0010\u0003\u001d!\u000b7OR3biV\u0014Xm]\"pYB\u0011QdI\u0005\u0003Iy\u0011A\u0002S1t\u001fV$\b/\u001e;D_2\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002QA\u0011\u0011#K\u0005\u0003UI\u0011A!\u00168ji\u0006\tb/\u0019:jC:\u001cW\r\u00165sKNDw\u000e\u001c3\u0016\u00035\u0002\"a\u0006\u0018\n\u0005=B\"a\u0003#pk\ndW\rU1sC6D3AA\u00198!\t\u0011T'D\u00014\u0015\t!\u0014\"\u0001\u0006b]:|G/\u0019;j_:L!AN\u001a\u0003\u000bMKgnY3\"\u0003a\nQa\r\u00182]A\nAcZ3u-\u0006\u0014\u0018.\u00198dKRC'/Z:i_2$W#A\u001e\u0011\u0005Ea\u0014BA\u001f\u0013\u0005\u0019!u.\u001e2mK\"\u001a1!M\u001c"
)
public interface VarianceThresholdSelectorParams extends HasFeaturesCol, HasOutputCol {
   void org$apache$spark$ml$feature$VarianceThresholdSelectorParams$_setter_$varianceThreshold_$eq(final DoubleParam x$1);

   DoubleParam varianceThreshold();

   // $FF: synthetic method
   static double getVarianceThreshold$(final VarianceThresholdSelectorParams $this) {
      return $this.getVarianceThreshold();
   }

   default double getVarianceThreshold() {
      return BoxesRunTime.unboxToDouble(this.$(this.varianceThreshold()));
   }

   static void $init$(final VarianceThresholdSelectorParams $this) {
      $this.org$apache$spark$ml$feature$VarianceThresholdSelectorParams$_setter_$varianceThreshold_$eq(new DoubleParam($this, "varianceThreshold", "Param for variance threshold. Features with a variance not greater than this threshold will be removed. The default value is 0.0.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.setDefault(.MODULE$.wrapRefArray(new ParamPair[]{$this.varianceThreshold().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F))}));
   }
}
