package org.apache.spark.ml.clustering;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4A!\u0003\u0006\u0001+!I!\u0004\u0001B\u0001B\u0003%1d\f\u0005\na\u0001\u0011\t\u0011)A\u0005cmB\u0011\u0002\u0010\u0001\u0003\u0002\u0003\u0006I!M\u001f\t\u0013y\u0002!\u0011!Q\u0001\n}\u001a\u0005\"\u0003#\u0001\u0005\u0003\u0005\u000b\u0011B F\u0011!1\u0005A!b\u0001\n\u00039\u0005\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\rY\u0003A\u0011\u0001\u0006X\u00055YU*Z1ogN+X.\\1ss*\u00111\u0002D\u0001\u000bG2,8\u000f^3sS:<'BA\u0007\u000f\u0003\tiGN\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\f\u0011\u0005]AR\"\u0001\u0006\n\u0005eQ!!E\"mkN$XM]5oON+X.\\1ss\u0006Y\u0001O]3eS\u000e$\u0018n\u001c8t!\taBF\u0004\u0002\u001eS9\u0011ad\n\b\u0003?\u0019r!\u0001I\u0013\u000f\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\r\"\u0012A\u0002\u001fs_>$h(C\u0001\u0014\u0013\t\t\"#\u0003\u0002\u0010!%\u0011\u0001FD\u0001\u0004gFd\u0017B\u0001\u0016,\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\u000b\b\n\u00055r#!\u0003#bi\u00064%/Y7f\u0015\tQ3&\u0003\u0002\u001b1\u0005i\u0001O]3eS\u000e$\u0018n\u001c8D_2\u0004\"A\r\u001d\u000f\u0005M2\u0004CA\u00115\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011H\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005]\"\u0014B\u0001\u0019\u0019\u0003-1W-\u0019;ve\u0016\u001c8i\u001c7\n\u0005qB\u0012!A6\u0011\u0005\u0001\u000bU\"\u0001\u001b\n\u0005\t#$aA%oi&\u0011a\bG\u0001\b]Vl\u0017\n^3s\u0013\t!\u0005$\u0001\u0007ue\u0006Lg.\u001b8h\u0007>\u001cH/F\u0001I!\t\u0001\u0015*\u0003\u0002Ki\t1Ai\\;cY\u0016D3A\u0002'S!\ti\u0005+D\u0001O\u0015\tye\"\u0001\u0006b]:|G/\u0019;j_:L!!\u0015(\u0003\u000bMKgnY3\"\u0003M\u000bQA\r\u00185]A\nQ\u0002\u001e:bS:LgnZ\"pgR\u0004\u0003fA\u0004M%\u00061A(\u001b8jiz\"r\u0001W-[7rkf\f\u0005\u0002\u0018\u0001!)!\u0004\u0003a\u00017!)\u0001\u0007\u0003a\u0001c!)A\b\u0003a\u0001c!)a\b\u0003a\u0001\u007f!)A\t\u0003a\u0001\u007f!)a\t\u0003a\u0001\u0011\"\u001aa\f\u0014*)\u0007\u0001a\u0015-I\u0001c\u0003\u0015\u0011d\u0006\r\u00181\u0001"
)
public class KMeansSummary extends ClusteringSummary {
   private final double trainingCost;

   public double trainingCost() {
      return this.trainingCost;
   }

   public KMeansSummary(final Dataset predictions, final String predictionCol, final String featuresCol, final int k, final int numIter, final double trainingCost) {
      super(predictions, predictionCol, featuresCol, k, numIter);
      this.trainingCost = trainingCost;
   }
}
