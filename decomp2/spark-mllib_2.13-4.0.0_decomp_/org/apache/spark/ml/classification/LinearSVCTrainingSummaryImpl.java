package org.apache.spark.ml.classification;

import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3A!\u0003\u0006\u0005+!IQ\u0004\u0001B\u0001B\u0003%aD\r\u0005\ng\u0001\u0011\t\u0011)A\u0005iyB\u0011b\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\u000e!\t\u0013\u0005\u0003!\u0011!Q\u0001\nQ\u0012\u0005\"C\"\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001bE\u0011!)\u0005A!b\u0001\n\u00032\u0005\u0002\u0003(\u0001\u0005\u0003\u0005\u000b\u0011B$\t\u000b=\u0003A\u0011\u0001)\u000391Kg.Z1s'Z\u001bEK]1j]&twmU;n[\u0006\u0014\u00180S7qY*\u00111\u0002D\u0001\u000fG2\f7o]5gS\u000e\fG/[8o\u0015\tia\"\u0001\u0002nY*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u00011\"\u0004\u0005\u0002\u001815\t!\"\u0003\u0002\u001a\u0015\t!B*\u001b8fCJ\u001cfkQ*v[6\f'/_%na2\u0004\"aF\u000e\n\u0005qQ!\u0001\u0007'j]\u0016\f'o\u0015,D)J\f\u0017N\\5oON+X.\\1ss\u0006Y\u0001O]3eS\u000e$\u0018n\u001c8t!\tyrF\u0004\u0002!Y9\u0011\u0011E\u000b\b\u0003E%r!a\t\u0015\u000f\u0005\u0011:S\"A\u0013\u000b\u0005\u0019\"\u0012A\u0002\u001fs_>$h(C\u0001\u0014\u0013\t\t\"#\u0003\u0002\u0010!%\u00111FD\u0001\u0004gFd\u0017BA\u0017/\u0003\u001d\u0001\u0018mY6bO\u0016T!a\u000b\b\n\u0005A\n$!\u0003#bi\u00064%/Y7f\u0015\tic&\u0003\u0002\u001e1\u0005A1oY8sK\u000e{G\u000e\u0005\u00026w9\u0011a'\u000f\t\u0003I]R\u0011\u0001O\u0001\u0006g\u000e\fG.Y\u0005\u0003u]\na\u0001\u0015:fI\u00164\u0017B\u0001\u001f>\u0005\u0019\u0019FO]5oO*\u0011!hN\u0005\u0003ga\tQ\u0002\u001d:fI&\u001cG/[8o\u0007>d\u0017BA \u0019\u0003!a\u0017MY3m\u0007>d\u0017BA!\u0019\u0003%9X-[4ii\u000e{G.\u0003\u0002D1\u0005\u0001rN\u00196fGRLg/\u001a%jgR|'/_\u000b\u0002\u000fB\u0019\u0001*S&\u000e\u0003]J!AS\u001c\u0003\u000b\u0005\u0013(/Y=\u0011\u0005!c\u0015BA'8\u0005\u0019!u.\u001e2mK\u0006\trN\u00196fGRLg/\u001a%jgR|'/\u001f\u0011\u0002\rqJg.\u001b;?)\u001d\t&k\u0015+V-^\u0003\"a\u0006\u0001\t\u000buA\u0001\u0019\u0001\u0010\t\u000bMB\u0001\u0019\u0001\u001b\t\u000b}B\u0001\u0019\u0001\u001b\t\u000b\u0005C\u0001\u0019\u0001\u001b\t\u000b\rC\u0001\u0019\u0001\u001b\t\u000b\u0015C\u0001\u0019A$"
)
public class LinearSVCTrainingSummaryImpl extends LinearSVCSummaryImpl implements LinearSVCTrainingSummary {
   private final double[] objectiveHistory;

   public int totalIterations() {
      return TrainingSummary.totalIterations$(this);
   }

   public double[] objectiveHistory() {
      return this.objectiveHistory;
   }

   public LinearSVCTrainingSummaryImpl(final Dataset predictions, final String scoreCol, final String predictionCol, final String labelCol, final String weightCol, final double[] objectiveHistory) {
      super(predictions, scoreCol, predictionCol, labelCol, weightCol);
      this.objectiveHistory = objectiveHistory;
      TrainingSummary.$init$(this);
   }
}
