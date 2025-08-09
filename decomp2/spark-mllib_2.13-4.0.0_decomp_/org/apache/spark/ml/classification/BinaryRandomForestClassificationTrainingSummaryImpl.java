package org.apache.spark.ml.classification;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3A!\u0003\u0006\u0005+!IQ\u0004\u0001B\u0001B\u0003%aD\r\u0005\nk\u0001\u0011\t\u0011)A\u0005m\u0001C\u0011\"\u0011\u0001\u0003\u0002\u0003\u0006IA\u000e\"\t\u0013\r\u0003!\u0011!Q\u0001\nY\"\u0005\"C#\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001cG\u0011!9\u0005A!b\u0001\n\u0003B\u0005\u0002\u0003)\u0001\u0005\u0003\u0005\u000b\u0011B%\t\u000bE\u0003A\u0011\u0001*\u0003g\tKg.\u0019:z%\u0006tGm\\7G_J,7\u000f^\"mCN\u001c\u0018NZ5dCRLwN\u001c+sC&t\u0017N\\4Tk6l\u0017M]=J[Bd'BA\u0006\r\u00039\u0019G.Y:tS\u001aL7-\u0019;j_:T!!\u0004\b\u0002\u00055d'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0004\u0001M\u0019\u0001A\u0006\u000e\u0011\u0005]AR\"\u0001\u0006\n\u0005eQ!a\u000b\"j]\u0006\u0014\u0018PU1oI>lgi\u001c:fgR\u001cE.Y:tS\u001aL7-\u0019;j_:\u001cV/\\7befLU\u000e\u001d7\u0011\u0005]Y\u0012B\u0001\u000f\u000b\u0005=\u0012\u0015N\\1ssJ\u000bg\u000eZ8n\r>\u0014Xm\u001d;DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8Ue\u0006Lg.\u001b8h'VlW.\u0019:z\u0003-\u0001(/\u001a3jGRLwN\\:\u0011\u0005}ycB\u0001\u0011-\u001d\t\t#F\u0004\u0002#S9\u00111\u0005\u000b\b\u0003I\u001dj\u0011!\n\u0006\u0003MQ\ta\u0001\u0010:p_Rt\u0014\"A\n\n\u0005E\u0011\u0012BA\b\u0011\u0013\tYc\"A\u0002tc2L!!\f\u0018\u0002\u000fA\f7m[1hK*\u00111FD\u0005\u0003aE\u0012\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u00055r\u0013BA\u000f4\u0013\t!$BA\u0013SC:$w.\u001c$pe\u0016\u001cHo\u00117bgNLg-[2bi&|gnU;n[\u0006\u0014\u00180S7qY\u0006A1oY8sK\u000e{G\u000e\u0005\u00028{9\u0011\u0001h\u000f\t\u0003IeR\u0011AO\u0001\u0006g\u000e\fG.Y\u0005\u0003ye\na\u0001\u0015:fI\u00164\u0017B\u0001 @\u0005\u0019\u0019FO]5oO*\u0011A(O\u0005\u0003ka\tQ\u0002\u001d:fI&\u001cG/[8o\u0007>d\u0017BA!4\u0003!a\u0017MY3m\u0007>d\u0017BA\"4\u0003%9X-[4ii\u000e{G.\u0003\u0002Fg\u0005\u0001rN\u00196fGRLg/\u001a%jgR|'/_\u000b\u0002\u0013B\u0019!jS'\u000e\u0003eJ!\u0001T\u001d\u0003\u000b\u0005\u0013(/Y=\u0011\u0005)s\u0015BA(:\u0005\u0019!u.\u001e2mK\u0006\trN\u00196fGRLg/\u001a%jgR|'/\u001f\u0011\u0002\rqJg.\u001b;?)\u001d\u0019F+\u0016,X1f\u0003\"a\u0006\u0001\t\u000buA\u0001\u0019\u0001\u0010\t\u000bUB\u0001\u0019\u0001\u001c\t\u000b\u0005C\u0001\u0019\u0001\u001c\t\u000b\rC\u0001\u0019\u0001\u001c\t\u000b\u0015C\u0001\u0019\u0001\u001c\t\u000b\u001dC\u0001\u0019A%"
)
public class BinaryRandomForestClassificationTrainingSummaryImpl extends BinaryRandomForestClassificationSummaryImpl implements BinaryRandomForestClassificationTrainingSummary {
   private final double[] objectiveHistory;

   public int totalIterations() {
      return TrainingSummary.totalIterations$(this);
   }

   public double[] objectiveHistory() {
      return this.objectiveHistory;
   }

   public BinaryRandomForestClassificationTrainingSummaryImpl(final Dataset predictions, final String scoreCol, final String predictionCol, final String labelCol, final String weightCol, final double[] objectiveHistory) {
      super(predictions, scoreCol, predictionCol, labelCol, weightCol);
      this.objectiveHistory = objectiveHistory;
      TrainingSummary.$init$(this);
   }
}
