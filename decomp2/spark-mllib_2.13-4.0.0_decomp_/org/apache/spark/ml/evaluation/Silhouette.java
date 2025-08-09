package org.apache.spark.ml.evaluation;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import scala.Function1;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Set;
import scala.math.Ordering.DeprecatedDoubleOrdering.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005U3a\u0001B\u0003\u0002\u0002\u0015y\u0001\"\u0002\f\u0001\t\u0003A\u0002\"B\u000e\u0001\t\u0003a\u0002\"\u0002\u001d\u0001\t\u0003I$AC*jY\"|W/\u001a;uK*\u0011aaB\u0001\u000bKZ\fG.^1uS>t'B\u0001\u0005\n\u0003\tiGN\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h'\t\u0001\u0001\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0011\u0004\u0005\u0002\u001b\u00015\tQ!\u0001\u000eq_&tGoU5mQ>,X\r\u001e;f\u0007>,gMZ5dS\u0016tG\u000f\u0006\u0004\u001eA5z\u0013g\r\t\u0003#yI!a\b\n\u0003\r\u0011{WO\u00197f\u0011\u0015\t#\u00011\u0001#\u0003)\u0019G.^:uKJLEm\u001d\t\u0004G)jbB\u0001\u0013)!\t)##D\u0001'\u0015\t9s#\u0001\u0004=e>|GOP\u0005\u0003SI\ta\u0001\u0015:fI\u00164\u0017BA\u0016-\u0005\r\u0019V\r\u001e\u0006\u0003SIAQA\f\u0002A\u0002u\ta\u0002]8j]R\u001cE.^:uKJLE\rC\u00031\u0005\u0001\u0007Q$A\u0005xK&<\u0007\u000e^*v[\")!G\u0001a\u0001;\u00051q/Z5hQRDQ\u0001\u000e\u0002A\u0002U\n\u0001$\u0019<fe\u0006<W\rR5ti\u0006t7-\u001a+p\u00072,8\u000f^3s!\u0011\tb'H\u000f\n\u0005]\u0012\"!\u0003$v]\u000e$\u0018n\u001c82\u00031yg/\u001a:bY2\u001c6m\u001c:f)\u0011i\"(T*\t\u000bm\u001a\u0001\u0019\u0001\u001f\u0002\u0005\u00114\u0007CA\u001fK\u001d\tqtI\u0004\u0002@\u000b:\u0011\u0001\t\u0012\b\u0003\u0003\u000es!!\n\"\n\u00039I!\u0001D\u0007\n\u0005)Y\u0011B\u0001$\n\u0003\r\u0019\u0018\u000f\\\u0005\u0003\u0011&\u000bq\u0001]1dW\u0006<WM\u0003\u0002G\u0013%\u00111\n\u0014\u0002\n\t\u0006$\u0018M\u0012:b[\u0016T!\u0001S%\t\u000b9\u001b\u0001\u0019A(\u0002\u0017M\u001cwN]3D_2,XN\u001c\t\u0003!Fk\u0011!S\u0005\u0003%&\u0013aaQ8mk6t\u0007\"\u0002+\u0004\u0001\u0004y\u0015\u0001D<fS\u001eDGoQ8mk6t\u0007"
)
public abstract class Silhouette {
   public double pointSilhouetteCoefficient(final Set clusterIds, final double pointClusterId, final double weightSum, final double weight, final Function1 averageDistanceToCluster) {
      if (weightSum == weight) {
         return (double)0.0F;
      } else {
         Set otherClusterIds = (Set)clusterIds.filter((JFunction1.mcZD.sp)(x$1) -> x$1 != pointClusterId);
         double neighboringClusterDissimilarity = BoxesRunTime.unboxToDouble(((IterableOnceOps)otherClusterIds.map(averageDistanceToCluster)).min(.MODULE$));
         double currentClusterDissimilarity = averageDistanceToCluster.apply$mcDD$sp(pointClusterId) * weightSum / (weightSum - weight);
         if (currentClusterDissimilarity < neighboringClusterDissimilarity) {
            return (double)1 - currentClusterDissimilarity / neighboringClusterDissimilarity;
         } else {
            return currentClusterDissimilarity > neighboringClusterDissimilarity ? neighboringClusterDissimilarity / currentClusterDissimilarity - (double)1 : (double)0.0F;
         }
      }
   }

   public double overallScore(final Dataset df, final Column scoreColumn, final Column weightColumn) {
      return ((Row[])df.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.sum(scoreColumn.$times(weightColumn)).$div(org.apache.spark.sql.functions..MODULE$.sum(weightColumn))}))).collect())[0].getDouble(0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
