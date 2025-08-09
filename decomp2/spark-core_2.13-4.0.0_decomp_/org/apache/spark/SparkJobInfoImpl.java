package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2A\u0001C\u0005\u0005!!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005!\u0001\t\u0005\t\u0015!\u0003\u001e\u0011!\t\u0003A!b\u0001\n\u0003\u0011\u0003\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011B\u0012\t\u0011\u001d\u0002!Q1A\u0005\u0002!B\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\u0006[\u0001!\tA\f\u0002\u0011'B\f'o\u001b&pE&sgm\\%na2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u0002\u0001'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005aIR\"A\u0005\n\u0005iI!\u0001D*qCJ\\'j\u001c2J]\u001a|\u0017!\u00026pE&#W#A\u000f\u0011\u0005Iq\u0012BA\u0010\u0014\u0005\rIe\u000e^\u0001\u0007U>\u0014\u0017\n\u001a\u0011\u0002\u0011M$\u0018mZ3JIN,\u0012a\t\t\u0004%\u0011j\u0012BA\u0013\u0014\u0005\u0015\t%O]1z\u0003%\u0019H/Y4f\u0013\u0012\u001c\b%\u0001\u0004ti\u0006$Xo]\u000b\u0002SA\u0011\u0001DK\u0005\u0003W%\u0011!CS8c\u000bb,7-\u001e;j_:\u001cF/\u0019;vg\u000691\u000f^1ukN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u00030aE\u0012\u0004C\u0001\r\u0001\u0011\u0015Yr\u00011\u0001\u001e\u0011\u0015\ts\u00011\u0001$\u0011\u00159s\u00011\u0001*\u0001"
)
public class SparkJobInfoImpl implements SparkJobInfo {
   private final int jobId;
   private final int[] stageIds;
   private final JobExecutionStatus status;

   public int jobId() {
      return this.jobId;
   }

   public int[] stageIds() {
      return this.stageIds;
   }

   public JobExecutionStatus status() {
      return this.status;
   }

   public SparkJobInfoImpl(final int jobId, final int[] stageIds, final JobExecutionStatus status) {
      this.jobId = jobId;
      this.stageIds = stageIds;
      this.status = status;
   }
}
