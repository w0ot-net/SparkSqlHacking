package org.apache.spark.ml.classification;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3A\u0001C\u0005\u0005)!IA\u0004\u0001B\u0001B\u0003%Q$\r\u0005\ne\u0001\u0011\t\u0011)A\u0005guB\u0011B\u0010\u0001\u0003\u0002\u0003\u0006IaM \t\u0013\u0001\u0003!\u0011!Q\u0001\nM\n\u0005\u0002\u0003\"\u0001\u0005\u000b\u0007I\u0011I\"\t\u0011-\u0003!\u0011!Q\u0001\n\u0011CQ\u0001\u0014\u0001\u0005\u00025\u0013QFU1oI>lgi\u001c:fgR\u001cE.Y:tS\u001aL7-\u0019;j_:$&/Y5oS:<7+^7nCJL\u0018*\u001c9m\u0015\tQ1\"\u0001\bdY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\u000b\u00051i\u0011AA7m\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7\u0001A\n\u0004\u0001UI\u0002C\u0001\f\u0018\u001b\u0005I\u0011B\u0001\r\n\u0005\u0015\u0012\u0016M\u001c3p[\u001a{'/Z:u\u00072\f7o]5gS\u000e\fG/[8o'VlW.\u0019:z\u00136\u0004H\u000e\u0005\u0002\u00175%\u00111$\u0003\u0002*%\u0006tGm\\7G_J,7\u000f^\"mCN\u001c\u0018NZ5dCRLwN\u001c+sC&t\u0017N\\4Tk6l\u0017M]=\u0002\u0017A\u0014X\rZ5di&|gn\u001d\t\u0003=9r!aH\u0016\u000f\u0005\u0001JcBA\u0011)\u001d\t\u0011sE\u0004\u0002$M5\tAE\u0003\u0002&'\u00051AH]8pizJ\u0011AE\u0005\u0003!EI!AD\b\n\u0005)j\u0011aA:rY&\u0011A&L\u0001\ba\u0006\u001c7.Y4f\u0015\tQS\"\u0003\u00020a\tIA)\u0019;b\rJ\fW.\u001a\u0006\u0003Y5J!\u0001H\f\u0002\u001bA\u0014X\rZ5di&|gnQ8m!\t!$H\u0004\u00026qA\u00111E\u000e\u0006\u0002o\u0005)1oY1mC&\u0011\u0011HN\u0001\u0007!J,G-\u001a4\n\u0005mb$AB*ue&twM\u0003\u0002:m%\u0011!gF\u0001\tY\u0006\u0014W\r\\\"pY&\u0011ahF\u0001\no\u0016Lw\r\u001b;D_2L!\u0001Q\f\u0002!=\u0014'.Z2uSZ,\u0007*[:u_JLX#\u0001#\u0011\u0007\u00153\u0005*D\u00017\u0013\t9eGA\u0003BeJ\f\u0017\u0010\u0005\u0002F\u0013&\u0011!J\u000e\u0002\u0007\t>,(\r\\3\u0002#=\u0014'.Z2uSZ,\u0007*[:u_JL\b%\u0001\u0004=S:LGO\u0010\u000b\u0007\u001d>\u0003\u0016KU*\u0011\u0005Y\u0001\u0001\"\u0002\u000f\b\u0001\u0004i\u0002\"\u0002\u001a\b\u0001\u0004\u0019\u0004\"\u0002 \b\u0001\u0004\u0019\u0004\"\u0002!\b\u0001\u0004\u0019\u0004\"\u0002\"\b\u0001\u0004!\u0005"
)
public class RandomForestClassificationTrainingSummaryImpl extends RandomForestClassificationSummaryImpl implements RandomForestClassificationTrainingSummary {
   private final double[] objectiveHistory;

   public int totalIterations() {
      return TrainingSummary.totalIterations$(this);
   }

   public double[] objectiveHistory() {
      return this.objectiveHistory;
   }

   public RandomForestClassificationTrainingSummaryImpl(final Dataset predictions, final String predictionCol, final String labelCol, final String weightCol, final double[] objectiveHistory) {
      super(predictions, predictionCol, labelCol, weightCol);
      this.objectiveHistory = objectiveHistory;
      TrainingSummary.$init$(this);
   }
}
