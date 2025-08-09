package org.apache.spark.status.protobuf;

import org.apache.spark.JobExecutionStatus;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q:a!\u0002\u0004\t\u0002\u0019\u0001bA\u0002\n\u0007\u0011\u000311\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0003\u001e\u0003\u0011\u0005a\u0004C\u00039\u0003\u0011\u0005\u0011(\u0001\u000fK_\n,\u00050Z2vi&|gn\u0015;biV\u001c8+\u001a:jC2L'0\u001a:\u000b\u0005\u001dA\u0011\u0001\u00039s_R|'-\u001e4\u000b\u0005%Q\u0011AB:uCR,8O\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h!\t\t\u0012!D\u0001\u0007\u0005qQuNY#yK\u000e,H/[8o'R\fG/^:TKJL\u0017\r\\5{KJ\u001c\"!\u0001\u000b\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\t\u0002\u0013M,'/[1mSj,GCA\u00104!\t\u0001\u0003G\u0004\u0002\"]9\u0011!%\f\b\u0003G1r!\u0001J\u0016\u000f\u0005\u0015RcB\u0001\u0014*\u001b\u00059#B\u0001\u0015\u001c\u0003\u0019a$o\\8u}%\tq\"\u0003\u0002\u000e\u001d%\u00111\u0002D\u0005\u0003\u0013)I!a\u0002\u0005\n\u0005=2\u0011AC*u_J,G+\u001f9fg&\u0011\u0011G\r\u0002\u0013\u0015>\u0014W\t_3dkRLwN\\*uCR,8O\u0003\u00020\r!)Ag\u0001a\u0001k\u0005)\u0011N\u001c9viB\u0011agN\u0007\u0002\u0015%\u0011\u0011GC\u0001\fI\u0016\u001cXM]5bY&TX\r\u0006\u00026u!)1\b\u0002a\u0001?\u00051!-\u001b8bef\u0004"
)
public final class JobExecutionStatusSerializer {
   public static JobExecutionStatus deserialize(final StoreTypes.JobExecutionStatus binary) {
      return JobExecutionStatusSerializer$.MODULE$.deserialize(binary);
   }

   public static StoreTypes.JobExecutionStatus serialize(final JobExecutionStatus input) {
      return JobExecutionStatusSerializer$.MODULE$.serialize(input);
   }
}
