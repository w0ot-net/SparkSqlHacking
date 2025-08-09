package org.apache.spark.ml.clustering;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4A!\u0003\u0006\u0001+!I!\u0004\u0001B\u0001B\u0003%1d\f\u0005\na\u0001\u0011\t\u0011)A\u0005cmB\u0011\u0002\u0010\u0001\u0003\u0002\u0003\u0006I!M\u001f\t\u0013y\u0002!\u0011!Q\u0001\n}\u001a\u0005\"\u0003#\u0001\u0005\u0003\u0005\u000b\u0011B F\u0011!1\u0005A!b\u0001\n\u00039\u0005\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\rY\u0003A\u0011\u0001\u0006X\u0005Y\u0011\u0015n]3di&twmS'fC:\u001c8+^7nCJL(BA\u0006\r\u0003)\u0019G.^:uKJLgn\u001a\u0006\u0003\u001b9\t!!\u001c7\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001-A\u0011q\u0003G\u0007\u0002\u0015%\u0011\u0011D\u0003\u0002\u0012\u00072,8\u000f^3sS:<7+^7nCJL\u0018a\u00039sK\u0012L7\r^5p]N\u0004\"\u0001\b\u0017\u000f\u0005uIcB\u0001\u0010(\u001d\tybE\u0004\u0002!K9\u0011\u0011\u0005J\u0007\u0002E)\u00111\u0005F\u0001\u0007yI|w\u000e\u001e \n\u0003MI!!\u0005\n\n\u0005=\u0001\u0012B\u0001\u0015\u000f\u0003\r\u0019\u0018\u000f\\\u0005\u0003U-\nq\u0001]1dW\u0006<WM\u0003\u0002)\u001d%\u0011QF\f\u0002\n\t\u0006$\u0018M\u0012:b[\u0016T!AK\u0016\n\u0005iA\u0012!\u00049sK\u0012L7\r^5p]\u000e{G\u000e\u0005\u00023q9\u00111G\u000e\t\u0003CQR\u0011!N\u0001\u0006g\u000e\fG.Y\u0005\u0003oQ\na\u0001\u0015:fI\u00164\u0017BA\u001d;\u0005\u0019\u0019FO]5oO*\u0011q\u0007N\u0005\u0003aa\t1BZ3biV\u0014Xm]\"pY&\u0011A\bG\u0001\u0002WB\u0011\u0001)Q\u0007\u0002i%\u0011!\t\u000e\u0002\u0004\u0013:$\u0018B\u0001 \u0019\u0003\u001dqW/\\%uKJL!\u0001\u0012\r\u0002\u0019Q\u0014\u0018-\u001b8j]\u001e\u001cun\u001d;\u0016\u0003!\u0003\"\u0001Q%\n\u0005)#$A\u0002#pk\ndW\rK\u0002\u0007\u0019J\u0003\"!\u0014)\u000e\u00039S!a\u0014\b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002R\u001d\n)1+\u001b8dK\u0006\n1+A\u00034]Ar\u0003'A\u0007ue\u0006Lg.\u001b8h\u0007>\u001cH\u000f\t\u0015\u0004\u000f1\u0013\u0016A\u0002\u001fj]&$h\bF\u0004Y3j[F,\u00180\u0011\u0005]\u0001\u0001\"\u0002\u000e\t\u0001\u0004Y\u0002\"\u0002\u0019\t\u0001\u0004\t\u0004\"\u0002\u001f\t\u0001\u0004\t\u0004\"\u0002 \t\u0001\u0004y\u0004\"\u0002#\t\u0001\u0004y\u0004\"\u0002$\t\u0001\u0004A\u0005f\u00010M%\"\u001a\u0001\u0001T1\"\u0003\t\fQA\r\u00182]A\u0002"
)
public class BisectingKMeansSummary extends ClusteringSummary {
   private final double trainingCost;

   public double trainingCost() {
      return this.trainingCost;
   }

   public BisectingKMeansSummary(final Dataset predictions, final String predictionCol, final String featuresCol, final int k, final int numIter, final double trainingCost) {
      super(predictions, predictionCol, featuresCol, k, numIter);
      this.trainingCost = trainingCost;
   }
}
