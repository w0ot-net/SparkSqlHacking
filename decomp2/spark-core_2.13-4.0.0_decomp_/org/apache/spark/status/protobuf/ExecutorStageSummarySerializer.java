package org.apache.spark.status.protobuf;

import org.apache.spark.status.api.v1.ExecutorStageSummary;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M:a!\u0002\u0004\t\u0002\u0019\u0001bA\u0002\n\u0007\u0011\u000311\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0003\u001e\u0003\u0011\u0005a\u0004C\u00030\u0003\u0011\u0005\u0001'\u0001\u0010Fq\u0016\u001cW\u000f^8s'R\fw-Z*v[6\f'/_*fe&\fG.\u001b>fe*\u0011q\u0001C\u0001\taJ|Go\u001c2vM*\u0011\u0011BC\u0001\u0007gR\fG/^:\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u0004\"!E\u0001\u000e\u0003\u0019\u0011a$\u0012=fGV$xN]*uC\u001e,7+^7nCJL8+\u001a:jC2L'0\u001a:\u0014\u0005\u0005!\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\u0001\u0012!C:fe&\fG.\u001b>f)\tyb\u0005\u0005\u0002!G9\u0011\u0011#I\u0005\u0003E\u0019\t!b\u0015;pe\u0016$\u0016\u0010]3t\u0013\t!SE\u0001\u000bFq\u0016\u001cW\u000f^8s'R\fw-Z*v[6\f'/\u001f\u0006\u0003E\u0019AQaJ\u0002A\u0002!\nQ!\u001b8qkR\u0004\"!\u000b\u0018\u000e\u0003)R!a\u000b\u0017\u0002\u0005Y\f$BA\u0017\t\u0003\r\t\u0007/[\u0005\u0003I)\n1\u0002Z3tKJL\u0017\r\\5{KR\u0011\u0001&\r\u0005\u0006e\u0011\u0001\raH\u0001\u0007E&t\u0017M]="
)
public final class ExecutorStageSummarySerializer {
   public static ExecutorStageSummary deserialize(final StoreTypes.ExecutorStageSummary binary) {
      return ExecutorStageSummarySerializer$.MODULE$.deserialize(binary);
   }

   public static StoreTypes.ExecutorStageSummary serialize(final ExecutorStageSummary input) {
      return ExecutorStageSummarySerializer$.MODULE$.serialize(input);
   }
}
