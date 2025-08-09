package org.apache.spark.mllib.tree.loss;

import org.apache.spark.mllib.tree.model.TreeEnsembleModel;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;QAB\u0004\t\u0002Q1QAF\u0004\t\u0002]AQ!I\u0001\u0005\u0002\tBQaI\u0001\u0005B\u0011Ba!N\u0001\u0005B51\u0004bB\u001d\u0002\u0003\u0003%IAO\u0001\u000e\u0003\n\u001cx\u000e\\;uK\u0016\u0013(o\u001c:\u000b\u0005!I\u0011\u0001\u00027pgNT!AC\u0006\u0002\tQ\u0014X-\u001a\u0006\u0003\u00195\tQ!\u001c7mS\nT!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\u0002\u0001!\t)\u0012!D\u0001\b\u00055\t%m]8mkR,WI\u001d:peN\u0019\u0011\u0001\u0007\u0010\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g!\t)r$\u0003\u0002!\u000f\t!Aj\\:t\u0003\u0019a\u0014N\\5u}Q\tA#\u0001\u0005he\u0006$\u0017.\u001a8u)\r)\u0003F\u000b\t\u00033\u0019J!a\n\u000e\u0003\r\u0011{WO\u00197f\u0011\u0015I3\u00011\u0001&\u0003)\u0001(/\u001a3jGRLwN\u001c\u0005\u0006W\r\u0001\r!J\u0001\u0006Y\u0006\u0014W\r\u001c\u0015\u0004\u00075\u001a\u0004C\u0001\u00182\u001b\u0005y#B\u0001\u0019\u000e\u0003)\tgN\\8uCRLwN\\\u0005\u0003e=\u0012QaU5oG\u0016\f\u0013\u0001N\u0001\u0006c9\u0012d\u0006M\u0001\rG>l\u0007/\u001e;f\u000bJ\u0014xN\u001d\u000b\u0004K]B\u0004\"B\u0015\u0005\u0001\u0004)\u0003\"B\u0016\u0005\u0001\u0004)\u0013\u0001D<sSR,'+\u001a9mC\u000e,G#A\u001e\u0011\u0005q\nU\"A\u001f\u000b\u0005yz\u0014\u0001\u00027b]\u001eT\u0011\u0001Q\u0001\u0005U\u00064\u0018-\u0003\u0002C{\t1qJ\u00196fGRD3!A\u00174Q\r\u0001Qf\r"
)
public final class AbsoluteError {
   public static double gradient(final double prediction, final double label) {
      return AbsoluteError$.MODULE$.gradient(prediction, label);
   }

   public static double computeError(final TreeEnsembleModel model, final RDD data) {
      return AbsoluteError$.MODULE$.computeError(model, data);
   }
}
