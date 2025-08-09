package org.apache.spark.ml.classification;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3A\u0001C\u0005\u0005)!IA\u0004\u0001B\u0001B\u0003%Q$\r\u0005\ne\u0001\u0011\t\u0011)A\u0005guB\u0011B\u0010\u0001\u0003\u0002\u0003\u0006IaM \t\u0013\u0001\u0003!\u0011!Q\u0001\nM\n\u0005\u0002\u0003\"\u0001\u0005\u000b\u0007I\u0011I\"\t\u0011-\u0003!\u0011!Q\u0001\n\u0011CQ\u0001\u0014\u0001\u0005\u00025\u0013Q'T;mi&d\u0017-_3s!\u0016\u00148-\u001a9ue>t7\t\\1tg&4\u0017nY1uS>tGK]1j]&twmU;n[\u0006\u0014\u00180S7qY*\u0011!bC\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\taQ\"\u0001\u0002nY*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001)\u0012\u0004\u0005\u0002\u0017/5\t\u0011\"\u0003\u0002\u0019\u0013\tiS*\u001e7uS2\f\u00170\u001a:QKJ\u001cW\r\u001d;s_:\u001cE.Y:tS\u001aL7-\u0019;j_:\u001cV/\\7befLU\u000e\u001d7\u0011\u0005YQ\u0012BA\u000e\n\u0005EjU\u000f\u001c;jY\u0006LXM\u001d)fe\u000e,\u0007\u000f\u001e:p]\u000ec\u0017m]:jM&\u001c\u0017\r^5p]R\u0013\u0018-\u001b8j]\u001e\u001cV/\\7bef\f1\u0002\u001d:fI&\u001cG/[8ogB\u0011aD\f\b\u0003?-r!\u0001I\u0015\u000f\u0005\u0005BcB\u0001\u0012(\u001d\t\u0019c%D\u0001%\u0015\t)3#\u0001\u0004=e>|GOP\u0005\u0002%%\u0011\u0001#E\u0005\u0003\u001d=I!AK\u0007\u0002\u0007M\fH.\u0003\u0002-[\u00059\u0001/Y2lC\u001e,'B\u0001\u0016\u000e\u0013\ty\u0003GA\u0005ECR\fgI]1nK*\u0011A&L\u0005\u00039]\tQ\u0002\u001d:fI&\u001cG/[8o\u0007>d\u0007C\u0001\u001b;\u001d\t)\u0004\b\u0005\u0002$m)\tq'A\u0003tG\u0006d\u0017-\u0003\u0002:m\u00051\u0001K]3eK\u001aL!a\u000f\u001f\u0003\rM#(/\u001b8h\u0015\tId'\u0003\u00023/\u0005AA.\u00192fY\u000e{G.\u0003\u0002?/\u0005Iq/Z5hQR\u001cu\u000e\\\u0005\u0003\u0001^\t\u0001c\u001c2kK\u000e$\u0018N^3ISN$xN]=\u0016\u0003\u0011\u00032!\u0012$I\u001b\u00051\u0014BA$7\u0005\u0015\t%O]1z!\t)\u0015*\u0003\u0002Km\t1Ai\\;cY\u0016\f\u0011c\u001c2kK\u000e$\u0018N^3ISN$xN]=!\u0003\u0019a\u0014N\\5u}Q1aj\u0014)R%N\u0003\"A\u0006\u0001\t\u000bq9\u0001\u0019A\u000f\t\u000bI:\u0001\u0019A\u001a\t\u000by:\u0001\u0019A\u001a\t\u000b\u0001;\u0001\u0019A\u001a\t\u000b\t;\u0001\u0019\u0001#"
)
public class MultilayerPerceptronClassificationTrainingSummaryImpl extends MultilayerPerceptronClassificationSummaryImpl implements MultilayerPerceptronClassificationTrainingSummary {
   private final double[] objectiveHistory;

   public int totalIterations() {
      return TrainingSummary.totalIterations$(this);
   }

   public double[] objectiveHistory() {
      return this.objectiveHistory;
   }

   public MultilayerPerceptronClassificationTrainingSummaryImpl(final Dataset predictions, final String predictionCol, final String labelCol, final String weightCol, final double[] objectiveHistory) {
      super(predictions, predictionCol, labelCol, weightCol);
      this.objectiveHistory = objectiveHistory;
      TrainingSummary.$init$(this);
   }
}
