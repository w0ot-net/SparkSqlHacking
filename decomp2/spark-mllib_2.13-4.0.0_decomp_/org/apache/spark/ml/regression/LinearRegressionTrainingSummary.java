package org.apache.spark.ml.regression;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.Dataset;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000554A\u0001D\u0007\u00011!IQ\u0004\u0001B\u0001B\u0003%aD\r\u0005\ng\u0001\u0011\t\u0011)A\u0005iyB\u0011b\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\u000e!\t\u0013\u0005\u0003!\u0011!Q\u0001\nQ\u0012\u0005\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011\u0002#\t\u0011\u001d\u0003!\u0011!Q\u0001\n!C\u0001b\u0014\u0001\u0003\u0006\u0004%\t\u0001\u0015\u0005\t#\u0002\u0011\t\u0011)A\u0005\u0011\"1!\u000b\u0001C\u0001\u001bMCq\u0001\u0018\u0001C\u0002\u0013\u0005Q\f\u0003\u0004k\u0001\u0001\u0006IA\u0018\u0002 \u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:$&/Y5oS:<7+^7nCJL(B\u0001\b\u0010\u0003)\u0011Xm\u001a:fgNLwN\u001c\u0006\u0003!E\t!!\u001c7\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u00013A\u0011!dG\u0007\u0002\u001b%\u0011A$\u0004\u0002\u0018\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:\u001cV/\\7bef\f1\u0002\u001d:fI&\u001cG/[8ogB\u0011qd\f\b\u0003A1r!!\t\u0016\u000f\u0005\tJcBA\u0012)\u001d\t!s%D\u0001&\u0015\t1s#\u0001\u0004=e>|GOP\u0005\u0002-%\u0011A#F\u0005\u0003%MI!aK\t\u0002\u0007M\fH.\u0003\u0002.]\u00059\u0001/Y2lC\u001e,'BA\u0016\u0012\u0013\t\u0001\u0014GA\u0005ECR\fgI]1nK*\u0011QFL\u0005\u0003;m\tQ\u0002\u001d:fI&\u001cG/[8o\u0007>d\u0007CA\u001b<\u001d\t1\u0014\b\u0005\u0002%o)\t\u0001(A\u0003tG\u0006d\u0017-\u0003\u0002;o\u00051\u0001K]3eK\u001aL!\u0001P\u001f\u0003\rM#(/\u001b8h\u0015\tQt'\u0003\u000247\u0005AA.\u00192fY\u000e{G.\u0003\u0002@7\u0005Ya-Z1ukJ,7oQ8m\u0013\t\t5$A\u0003n_\u0012,G\u000e\u0005\u0002\u001b\u000b&\u0011a)\u0004\u0002\u0016\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:lu\u000eZ3m\u0003-!\u0017.Y4J]Z\fEoV!\u0011\u0007%SE*D\u00018\u0013\tYuGA\u0003BeJ\f\u0017\u0010\u0005\u0002J\u001b&\u0011aj\u000e\u0002\u0007\t>,(\r\\3\u0002!=\u0014'.Z2uSZ,\u0007*[:u_JLX#\u0001%\u0002#=\u0014'.Z2uSZ,\u0007*[:u_JL\b%\u0001\u0004=S:LGO\u0010\u000b\t)V3v\u000bW-[7B\u0011!\u0004\u0001\u0005\u0006;%\u0001\rA\b\u0005\u0006g%\u0001\r\u0001\u000e\u0005\u0006\u007f%\u0001\r\u0001\u000e\u0005\u0006\u0003&\u0001\r\u0001\u000e\u0005\u0006\u0007&\u0001\r\u0001\u0012\u0005\u0006\u000f&\u0001\r\u0001\u0013\u0005\u0006\u001f&\u0001\r\u0001S\u0001\u0010i>$\u0018\r\\%uKJ\fG/[8ogV\ta\f\u0005\u0002J?&\u0011\u0001m\u000e\u0002\u0004\u0013:$\bf\u0001\u0006cQB\u00111MZ\u0007\u0002I*\u0011Q-E\u0001\u000bC:tw\u000e^1uS>t\u0017BA4e\u0005\u0015\u0019\u0016N\\2fC\u0005I\u0017!B\u0019/k9\u0002\u0014\u0001\u0005;pi\u0006d\u0017\n^3sCRLwN\\:!Q\rY!\r\u001b\u0015\u0004\u0001\tD\u0007"
)
public class LinearRegressionTrainingSummary extends LinearRegressionSummary {
   private final double[] objectiveHistory;
   private final int totalIterations;

   public double[] objectiveHistory() {
      return this.objectiveHistory;
   }

   public int totalIterations() {
      return this.totalIterations;
   }

   public LinearRegressionTrainingSummary(final Dataset predictions, final String predictionCol, final String labelCol, final String featuresCol, final LinearRegressionModel model, final double[] diagInvAtWA, final double[] objectiveHistory) {
      super(predictions, predictionCol, labelCol, featuresCol, model, diagInvAtWA);
      this.objectiveHistory = objectiveHistory;
      .MODULE$.assert(objectiveHistory.length > 0, () -> "objectiveHistory length should be greater than 1.");
      this.totalIterations = objectiveHistory.length - 1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
