package org.apache.spark.ml.classification;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3AAC\u0006\u0005-!Ia\u0004\u0001B\u0001B\u0003%qd\r\u0005\ni\u0001\u0011\t\u0011)A\u0005k}B\u0011\u0002\u0011\u0001\u0003\u0002\u0003\u0006I!N!\t\u0013\t\u0003!\u0011!Q\u0001\nU\u001a\u0005\"\u0003#\u0001\u0005\u0003\u0005\u000b\u0011B\u001bF\u0011%1\u0005A!A!\u0002\u0013)t\t\u0003\u0005I\u0001\t\u0015\r\u0011\"\u0011J\u0011!\t\u0006A!A!\u0002\u0013Q\u0005\"\u0002*\u0001\t\u0003\u0019&!\n'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:$&/Y5oS:<7+^7nCJL\u0018*\u001c9m\u0015\taQ\"\u0001\bdY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\u000b\u00059y\u0011AA7m\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7\u0001A\n\u0004\u0001]Y\u0002C\u0001\r\u001a\u001b\u0005Y\u0011B\u0001\u000e\f\u0005uaunZ5ti&\u001c'+Z4sKN\u001c\u0018n\u001c8Tk6l\u0017M]=J[Bd\u0007C\u0001\r\u001d\u0013\ti2BA\u0011M_\u001eL7\u000f^5d%\u0016<'/Z:tS>tGK]1j]&twmU;n[\u0006\u0014\u00180A\u0006qe\u0016$\u0017n\u0019;j_:\u001c\bC\u0001\u00111\u001d\t\tSF\u0004\u0002#W9\u00111E\u000b\b\u0003I%r!!\n\u0015\u000e\u0003\u0019R!aJ\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0012B\u0001\n\u0014\u0013\t\u0001\u0012#\u0003\u0002-\u001f\u0005\u00191/\u001d7\n\u00059z\u0013a\u00029bG.\fw-\u001a\u0006\u0003Y=I!!\r\u001a\u0003\u0013\u0011\u000bG/\u0019$sC6,'B\u0001\u00180\u0013\tq\u0012$\u0001\bqe>\u0014\u0017MY5mSRL8i\u001c7\u0011\u0005YbdBA\u001c;!\t)\u0003HC\u0001:\u0003\u0015\u00198-\u00197b\u0013\tY\u0004(\u0001\u0004Qe\u0016$WMZ\u0005\u0003{y\u0012aa\u0015;sS:<'BA\u001e9\u0013\t!\u0014$A\u0007qe\u0016$\u0017n\u0019;j_:\u001cu\u000e\\\u0005\u0003\u0001f\t\u0001\u0002\\1cK2\u001cu\u000e\\\u0005\u0003\u0005f\t1BZ3biV\u0014Xm]\"pY&\u0011A)G\u0001\no\u0016Lw\r\u001b;D_2L!AR\r\u0002!=\u0014'.Z2uSZ,\u0007*[:u_JLX#\u0001&\u0011\u0007-ce*D\u00019\u0013\ti\u0005HA\u0003BeJ\f\u0017\u0010\u0005\u0002L\u001f&\u0011\u0001\u000b\u000f\u0002\u0007\t>,(\r\\3\u0002#=\u0014'.Z2uSZ,\u0007*[:u_JL\b%\u0001\u0004=S:LGO\u0010\u000b\t)V3v\u000bW-[7B\u0011\u0001\u0004\u0001\u0005\u0006=%\u0001\ra\b\u0005\u0006i%\u0001\r!\u000e\u0005\u0006\u0001&\u0001\r!\u000e\u0005\u0006\u0005&\u0001\r!\u000e\u0005\u0006\t&\u0001\r!\u000e\u0005\u0006\r&\u0001\r!\u000e\u0005\u0006\u0011&\u0001\rA\u0013"
)
public class LogisticRegressionTrainingSummaryImpl extends LogisticRegressionSummaryImpl implements LogisticRegressionTrainingSummary {
   private final double[] objectiveHistory;

   public int totalIterations() {
      return TrainingSummary.totalIterations$(this);
   }

   public double[] objectiveHistory() {
      return this.objectiveHistory;
   }

   public LogisticRegressionTrainingSummaryImpl(final Dataset predictions, final String probabilityCol, final String predictionCol, final String labelCol, final String featuresCol, final String weightCol, final double[] objectiveHistory) {
      super(predictions, probabilityCol, predictionCol, labelCol, featuresCol, weightCol);
      this.objectiveHistory = objectiveHistory;
      TrainingSummary.$init$(this);
   }
}
