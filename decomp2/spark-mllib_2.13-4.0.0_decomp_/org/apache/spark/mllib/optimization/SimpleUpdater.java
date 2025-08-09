package org.apache.spark.mllib.optimization;

import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.Tuple2;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U2Aa\u0001\u0003\u0001\u001f!)A\u0003\u0001C\u0001+!)q\u0003\u0001C!1\ti1+[7qY\u0016,\u0006\u000fZ1uKJT!!\u0002\u0004\u0002\u0019=\u0004H/[7ju\u0006$\u0018n\u001c8\u000b\u0005\u001dA\u0011!B7mY&\u0014'BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0005\t\u0003#Ii\u0011\u0001B\u0005\u0003'\u0011\u0011q!\u00169eCR,'/\u0001\u0004=S:LGO\u0010\u000b\u0002-A\u0011\u0011\u0003A\u0001\bG>l\u0007/\u001e;f)\u0019I\u0002F\u000b\u0017/gA!!$H\u0010&\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"A\u0002+va2,'\u0007\u0005\u0002!G5\t\u0011E\u0003\u0002#\r\u00051A.\u001b8bY\u001eL!\u0001J\u0011\u0003\rY+7\r^8s!\tQb%\u0003\u0002(7\t1Ai\\;cY\u0016DQ!\u000b\u0002A\u0002}\t!b^3jO\"$8o\u00147e\u0011\u0015Y#\u00011\u0001 \u0003!9'/\u00193jK:$\b\"B\u0017\u0003\u0001\u0004)\u0013\u0001C:uKB\u001c\u0016N_3\t\u000b=\u0012\u0001\u0019\u0001\u0019\u0002\t%$XM\u001d\t\u00035EJ!AM\u000e\u0003\u0007%sG\u000fC\u00035\u0005\u0001\u0007Q%\u0001\u0005sK\u001e\u0004\u0016M]1n\u0001"
)
public class SimpleUpdater extends Updater {
   public Tuple2 compute(final Vector weightsOld, final Vector gradient, final double stepSize, final int iter, final double regParam) {
      double thisIterStepSize = stepSize / .MODULE$.sqrt((double)iter);
      breeze.linalg.Vector brzWeights = weightsOld.asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      breeze.linalg.package..MODULE$.axpy(BoxesRunTime.boxToDouble(-thisIterStepSize), gradient.asBreeze(), brzWeights, breeze.linalg.operators.HasOps..MODULE$.impl_scaleAdd_InPlace_V_S_V_Double());
      return new Tuple2(Vectors$.MODULE$.fromBreeze(brzWeights), BoxesRunTime.boxToDouble((double)0.0F));
   }
}
