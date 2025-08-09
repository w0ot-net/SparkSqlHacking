package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3AAE\n\u00055!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003(\u0011!Y\u0003A!b\u0001\n\u00031\u0003\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0014\t\u00115\u0002!Q1A\u0005\u00029B\u0001B\r\u0001\u0003\u0002\u0003\u0006Ia\f\u0005\tg\u0001\u0011)\u0019!C\u0001i!A\u0001\t\u0001B\u0001B\u0003%Q\u0007\u0003\u0005B\u0001\t\u0015\r\u0011\"\u0001'\u0011!\u0011\u0005A!A!\u0002\u00139\u0003\u0002C\"\u0001\u0005\u000b\u0007I\u0011\u0001\u0014\t\u0011\u0011\u0003!\u0011!Q\u0001\n\u001dB\u0001\"\u0012\u0001\u0003\u0006\u0004%\tA\n\u0005\t\r\u0002\u0011\t\u0011)A\u0005O!Aq\t\u0001BC\u0002\u0013\u0005a\u0005\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003(\u0011\u0015I\u0005\u0001\"\u0001K\u0005I\u0019\u0006/\u0019:l'R\fw-Z%oM>LU\u000e\u001d7\u000b\u0005Q)\u0012!B:qCJ\\'B\u0001\f\u0018\u0003\u0019\t\u0007/Y2iK*\t\u0001$A\u0002pe\u001e\u001c\u0001aE\u0002\u00017\u0005\u0002\"\u0001H\u0010\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011a!\u00118z%\u00164\u0007C\u0001\u0012$\u001b\u0005\u0019\u0012B\u0001\u0013\u0014\u00059\u0019\u0006/\u0019:l'R\fw-Z%oM>\fqa\u001d;bO\u0016LE-F\u0001(!\ta\u0002&\u0003\u0002*;\t\u0019\u0011J\u001c;\u0002\u0011M$\u0018mZ3JI\u0002\n\u0001cY;se\u0016tG/\u0011;uK6\u0004H/\u00133\u0002#\r,(O]3oi\u0006#H/Z7qi&#\u0007%\u0001\btk\nl\u0017n]:j_:$\u0016.\\3\u0016\u0003=\u0002\"\u0001\b\u0019\n\u0005Ej\"\u0001\u0002'p]\u001e\fqb];c[&\u001c8/[8o)&lW\rI\u0001\u0005]\u0006lW-F\u00016!\t1TH\u0004\u00028wA\u0011\u0001(H\u0007\u0002s)\u0011!(G\u0001\u0007yI|w\u000e\u001e \n\u0005qj\u0012A\u0002)sK\u0012,g-\u0003\u0002?\u007f\t11\u000b\u001e:j]\u001eT!\u0001P\u000f\u0002\u000b9\fW.\u001a\u0011\u0002\u00119,X\u000eV1tWN\f\u0011B\\;n)\u0006\u001c8n\u001d\u0011\u0002\u001d9,X.Q2uSZ,G+Y:lg\u0006ya.^7BGRLg/\u001a+bg.\u001c\b%A\tok6\u001cu.\u001c9mKR,G\rV1tWN\f!C\\;n\u0007>l\u0007\u000f\\3uK\u0012$\u0016m]6tA\u0005qa.^7GC&dW\r\u001a+bg.\u001c\u0018a\u00048v[\u001a\u000b\u0017\u000e\\3e)\u0006\u001c8n\u001d\u0011\u0002\rqJg.\u001b;?)%YE*\u0014(P!F\u00136\u000b\u0005\u0002#\u0001!)Q%\u0005a\u0001O!)1&\u0005a\u0001O!)Q&\u0005a\u0001_!)1'\u0005a\u0001k!)\u0011)\u0005a\u0001O!)1)\u0005a\u0001O!)Q)\u0005a\u0001O!)q)\u0005a\u0001O\u0001"
)
public class SparkStageInfoImpl implements SparkStageInfo {
   private final int stageId;
   private final int currentAttemptId;
   private final long submissionTime;
   private final String name;
   private final int numTasks;
   private final int numActiveTasks;
   private final int numCompletedTasks;
   private final int numFailedTasks;

   public int stageId() {
      return this.stageId;
   }

   public int currentAttemptId() {
      return this.currentAttemptId;
   }

   public long submissionTime() {
      return this.submissionTime;
   }

   public String name() {
      return this.name;
   }

   public int numTasks() {
      return this.numTasks;
   }

   public int numActiveTasks() {
      return this.numActiveTasks;
   }

   public int numCompletedTasks() {
      return this.numCompletedTasks;
   }

   public int numFailedTasks() {
      return this.numFailedTasks;
   }

   public SparkStageInfoImpl(final int stageId, final int currentAttemptId, final long submissionTime, final String name, final int numTasks, final int numActiveTasks, final int numCompletedTasks, final int numFailedTasks) {
      this.stageId = stageId;
      this.currentAttemptId = currentAttemptId;
      this.submissionTime = submissionTime;
      this.name = name;
      this.numTasks = numTasks;
      this.numActiveTasks = numActiveTasks;
      this.numCompletedTasks = numCompletedTasks;
      this.numFailedTasks = numFailedTasks;
   }
}
