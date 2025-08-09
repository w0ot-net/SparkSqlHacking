package org.apache.spark.executor;

import org.apache.spark.TaskCommitDenied;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2Qa\u0002\u0005\u0001\u0015AA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tS\u0001\u0011\t\u0011)A\u0005U!Aa\u0006\u0001B\u0001B\u0003%!\u0006\u0003\u00050\u0001\t\u0005\t\u0015!\u0003+\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u0015A\u0004\u0001\"\u0001:\u0005U\u0019u.\\7ji\u0012+g.[3e\u000bb\u001cW\r\u001d;j_:T!!\u0003\u0006\u0002\u0011\u0015DXmY;u_JT!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0003\u0001E\u0001\"AE\u000f\u000f\u0005MQbB\u0001\u000b\u0019\u001b\u0005)\"B\u0001\f\u0018\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005ma\u0012a\u00029bG.\fw-\u001a\u0006\u00023%\u0011ad\b\u0002\n\u000bb\u001cW\r\u001d;j_:T!a\u0007\u000f\u0002\u00075\u001cx\r\u0005\u0002#M9\u00111\u0005\n\t\u0003)qI!!\n\u000f\u0002\rA\u0013X\rZ3g\u0013\t9\u0003F\u0001\u0004TiJLgn\u001a\u0006\u0003Kq\tQA[8c\u0013\u0012\u0003\"a\u000b\u0017\u000e\u0003qI!!\f\u000f\u0003\u0007%sG/A\u0004ta2LG/\u0013#\u0002\u001b\u0005$H/Z7qi:+XNY3s\u0003\u0019a\u0014N\\5u}Q)!\u0007N\u001b7oA\u00111\u0007A\u0007\u0002\u0011!)\u0001%\u0002a\u0001C!)\u0011&\u0002a\u0001U!)a&\u0002a\u0001U!)q&\u0002a\u0001U\u0005ABo\u001c+bg.\u001cu.\\7ji\u0012+g.[3e%\u0016\f7o\u001c8\u0016\u0003i\u0002\"a\u000f\u001f\u000e\u0003)I!!\u0010\u0006\u0003!Q\u000b7o[\"p[6LG\u000fR3oS\u0016$\u0007"
)
public class CommitDeniedException extends Exception {
   private final int jobID;
   private final int splitID;
   private final int attemptNumber;

   public TaskCommitDenied toTaskCommitDeniedReason() {
      return new TaskCommitDenied(this.jobID, this.splitID, this.attemptNumber);
   }

   public CommitDeniedException(final String msg, final int jobID, final int splitID, final int attemptNumber) {
      super(msg);
      this.jobID = jobID;
      this.splitID = splitID;
      this.attemptNumber = attemptNumber;
   }
}
