package org.apache.spark.ml.evaluation;

import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import scala.Function1;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dqAB\u0004\t\u0011\u0003A!C\u0002\u0004\u0015\u0011!\u0005\u0001\"\u0006\u0005\u00063\u0005!\ta\u0007\u0005\u00079\u0005\u0001\u000b\u0011B\u000f\t\u000b\u0015\nA\u0011\u0001\u0014\t\u000bq\u000bA\u0011A/\t\u000b1\fA\u0011A7\u0002!\r{7/\u001b8f'&d\u0007n\\;fiR,'BA\u0005\u000b\u0003))g/\u00197vCRLwN\u001c\u0006\u0003\u00171\t!!\u001c7\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u0004\"aE\u0001\u000e\u0003!\u0011\u0001cQ8tS:,7+\u001b7i_V,G\u000f^3\u0014\u0005\u00051\u0002CA\n\u0018\u0013\tA\u0002B\u0001\u0006TS2Dw.^3ui\u0016\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002%\u0005Ibn\u001c:nC2L'0\u001a3GK\u0006$XO]3t\u0007>dg*Y7f!\tq2%D\u0001 \u0015\t\u0001\u0013%\u0001\u0003mC:<'\"\u0001\u0012\u0002\t)\fg/Y\u0005\u0003I}\u0011aa\u0015;sS:<\u0017aE2p[B,H/Z\"mkN$XM]*uCR\u001cH#B\u0014B)bS\u0006\u0003\u0002\u00152iar!!K\u0018\u0011\u0005)jS\"A\u0016\u000b\u00051R\u0012A\u0002\u001fs_>$hHC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001T&\u0001\u0004Qe\u0016$WMZ\u0005\u0003eM\u00121!T1q\u0015\t\u0001T\u0006\u0005\u00026m5\tQ&\u0003\u00028[\t1Ai\\;cY\u0016\u0004B!N\u001d<i%\u0011!(\f\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005qzT\"A\u001f\u000b\u0005yR\u0011A\u00027j]\u0006dw-\u0003\u0002A{\t1a+Z2u_JDQA\u0011\u0003A\u0002\r\u000b!\u0001\u001a4\u0011\u0005\u0011\u000bfBA#O\u001d\t1EJ\u0004\u0002H\u0017:\u0011\u0001J\u0013\b\u0003U%K\u0011!E\u0005\u0003\u001fAI!!\u0004\b\n\u00055c\u0011aA:rY&\u0011q\nU\u0001\ba\u0006\u001c7.Y4f\u0015\tiE\"\u0003\u0002S'\nIA)\u0019;b\rJ\fW.\u001a\u0006\u0003\u001fBCQ!\u0016\u0003A\u0002Y\u000b1BZ3biV\u0014Xm]\"pYB\u0011\u0001fV\u0005\u0003IMBQ!\u0017\u0003A\u0002Y\u000bQ\u0002\u001d:fI&\u001cG/[8o\u0007>d\u0007\"B.\u0005\u0001\u00041\u0016!C<fS\u001eDGoQ8m\u0003q\u0019w.\u001c9vi\u0016\u001c\u0016\u000e\u001c5pk\u0016$H/Z\"pK\u001a4\u0017nY5f]R$R\u0001\u000e0gQ*DQaX\u0003A\u0002\u0001\faC\u0019:pC\u0012\u001c\u0017m\u001d;fI\u000ecWo\u001d;feNl\u0015\r\u001d\t\u0004C\u0012<S\"\u00012\u000b\u0005\rd\u0011!\u00032s_\u0006$7-Y:u\u0013\t)'MA\u0005Ce>\fGmY1ti\")q-\u0002a\u0001w\u0005\u0011bn\u001c:nC2L'0\u001a3GK\u0006$XO]3t\u0011\u0015IW\u00011\u00015\u0003%\u0019G.^:uKJLE\rC\u0003l\u000b\u0001\u0007A'\u0001\u0004xK&<\u0007\u000e^\u0001\u0017G>l\u0007/\u001e;f'&d\u0007n\\;fiR,7kY8sKRAAG\\A\u0001\u0003\u0007\t)\u0001C\u0003p\r\u0001\u0007\u0001/A\u0004eCR\f7/\u001a;1\u0005E<\bc\u0001:tk6\t\u0001+\u0003\u0002u!\n9A)\u0019;bg\u0016$\bC\u0001<x\u0019\u0001!\u0011\u0002\u001f8\u0002\u0002\u0003\u0005)\u0011A=\u0003\u0007}#3'\u0005\u0002{{B\u0011Qg_\u0005\u0003y6\u0012qAT8uQ&tw\r\u0005\u00026}&\u0011q0\f\u0002\u0004\u0003:L\b\"B-\u0007\u0001\u00041\u0006\"B+\u0007\u0001\u00041\u0006\"B.\u0007\u0001\u00041\u0006"
)
public final class CosineSilhouette {
   public static double computeSilhouetteScore(final Dataset dataset, final String predictionCol, final String featuresCol, final String weightCol) {
      return CosineSilhouette$.MODULE$.computeSilhouetteScore(dataset, predictionCol, featuresCol, weightCol);
   }

   public static double computeSilhouetteCoefficient(final Broadcast broadcastedClustersMap, final Vector normalizedFeatures, final double clusterId, final double weight) {
      return CosineSilhouette$.MODULE$.computeSilhouetteCoefficient(broadcastedClustersMap, normalizedFeatures, clusterId, weight);
   }

   public static Map computeClusterStats(final Dataset df, final String featuresCol, final String predictionCol, final String weightCol) {
      return CosineSilhouette$.MODULE$.computeClusterStats(df, featuresCol, predictionCol, weightCol);
   }

   public static double overallScore(final Dataset df, final Column scoreColumn, final Column weightColumn) {
      return CosineSilhouette$.MODULE$.overallScore(df, scoreColumn, weightColumn);
   }

   public static double pointSilhouetteCoefficient(final Set clusterIds, final double pointClusterId, final double weightSum, final double weight, final Function1 averageDistanceToCluster) {
      return CosineSilhouette$.MODULE$.pointSilhouetteCoefficient(clusterIds, pointClusterId, weightSum, weight, averageDistanceToCluster);
   }
}
