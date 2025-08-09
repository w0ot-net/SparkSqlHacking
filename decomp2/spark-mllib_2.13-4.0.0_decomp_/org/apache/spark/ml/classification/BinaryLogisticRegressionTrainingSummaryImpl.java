package org.apache.spark.ml.classification;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y3AAC\u0006\u0005-!Ia\u0004\u0001B\u0001B\u0003%qd\r\u0005\nm\u0001\u0011\t\u0011)A\u0005o\u0005C\u0011B\u0011\u0001\u0003\u0002\u0003\u0006IaN\"\t\u0013\u0011\u0003!\u0011!Q\u0001\n]*\u0005\"\u0003$\u0001\u0005\u0003\u0005\u000b\u0011B\u001cH\u0011%A\u0005A!A!\u0002\u00139\u0014\n\u0003\u0005K\u0001\t\u0015\r\u0011\"\u0011L\u0011!\u0019\u0006A!A!\u0002\u0013a\u0005\"\u0002+\u0001\t\u0003)&a\u000b\"j]\u0006\u0014\u0018\u0010T8hSN$\u0018n\u0019*fOJ,7o]5p]R\u0013\u0018-\u001b8j]\u001e\u001cV/\\7befLU\u000e\u001d7\u000b\u00051i\u0011AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003\u001d=\t!!\u001c7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001/m\u0001\"\u0001G\r\u000e\u0003-I!AG\u0006\u0003G\tKg.\u0019:z\u0019><\u0017n\u001d;jGJ+wM]3tg&|gnU;n[\u0006\u0014\u00180S7qYB\u0011\u0001\u0004H\u0005\u0003;-\u0011qEQ5oCJLHj\\4jgRL7MU3he\u0016\u001c8/[8o)J\f\u0017N\\5oON+X.\\1ss\u0006Y\u0001O]3eS\u000e$\u0018n\u001c8t!\t\u0001\u0003G\u0004\u0002\"[9\u0011!e\u000b\b\u0003G)r!\u0001J\u0015\u000f\u0005\u0015BS\"\u0001\u0014\u000b\u0005\u001d*\u0012A\u0002\u001fs_>$h(C\u0001\u0015\u0013\t\u00112#\u0003\u0002\u0011#%\u0011AfD\u0001\u0004gFd\u0017B\u0001\u00180\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001L\b\n\u0005E\u0012$!\u0003#bi\u00064%/Y7f\u0015\tqs&\u0003\u0002\u001fi%\u0011Qg\u0003\u0002\u001e\u0019><\u0017n\u001d;jGJ+wM]3tg&|gnU;n[\u0006\u0014\u00180S7qY\u0006q\u0001O]8cC\nLG.\u001b;z\u0007>d\u0007C\u0001\u001d?\u001d\tID\b\u0005\u0002&u)\t1(A\u0003tG\u0006d\u0017-\u0003\u0002>u\u00051\u0001K]3eK\u001aL!a\u0010!\u0003\rM#(/\u001b8h\u0015\ti$(\u0003\u00027i\u0005i\u0001O]3eS\u000e$\u0018n\u001c8D_2L!A\u0011\u001b\u0002\u00111\f'-\u001a7D_2L!\u0001\u0012\u001b\u0002\u0017\u0019,\u0017\r^;sKN\u001cu\u000e\\\u0005\u0003\rR\n\u0011b^3jO\"$8i\u001c7\n\u0005!#\u0014\u0001E8cU\u0016\u001cG/\u001b<f\u0011&\u001cHo\u001c:z+\u0005a\u0005cA'O!6\t!(\u0003\u0002Pu\t)\u0011I\u001d:bsB\u0011Q*U\u0005\u0003%j\u0012a\u0001R8vE2,\u0017!E8cU\u0016\u001cG/\u001b<f\u0011&\u001cHo\u001c:zA\u00051A(\u001b8jiz\"\u0002BV,Y3j[F,\u0018\t\u00031\u0001AQAH\u0005A\u0002}AQAN\u0005A\u0002]BQAQ\u0005A\u0002]BQ\u0001R\u0005A\u0002]BQAR\u0005A\u0002]BQ\u0001S\u0005A\u0002]BQAS\u0005A\u00021\u0003"
)
public class BinaryLogisticRegressionTrainingSummaryImpl extends BinaryLogisticRegressionSummaryImpl implements BinaryLogisticRegressionTrainingSummary {
   private final double[] objectiveHistory;

   public int totalIterations() {
      return TrainingSummary.totalIterations$(this);
   }

   public double[] objectiveHistory() {
      return this.objectiveHistory;
   }

   public BinaryLogisticRegressionTrainingSummaryImpl(final Dataset predictions, final String probabilityCol, final String predictionCol, final String labelCol, final String featuresCol, final String weightCol, final double[] objectiveHistory) {
      super(predictions, probabilityCol, predictionCol, labelCol, featuresCol, weightCol);
      this.objectiveHistory = objectiveHistory;
      TrainingSummary.$init$(this);
   }
}
