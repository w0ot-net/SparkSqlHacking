package org.apache.spark.status.protobuf;

import org.apache.spark.executor.ExecutorMetrics;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E:a!\u0002\u0004\t\u0002\u0019\u0001bA\u0002\n\u0007\u0011\u000311\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0003\u001e\u0003\u0011\u0005a\u0004C\u0003.\u0003\u0011\u0005a&A\rFq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t'\u0016\u0014\u0018.\u00197ju\u0016\u0014(BA\u0004\t\u0003!\u0001(o\u001c;pEV4'BA\u0005\u000b\u0003\u0019\u0019H/\u0019;vg*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014x\r\u0005\u0002\u0012\u00035\taAA\rFq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t'\u0016\u0014\u0018.\u00197ju\u0016\u00148CA\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0011\u0003%\u0019XM]5bY&TX\r\u0006\u0002 MA\u0011\u0001e\t\b\u0003#\u0005J!A\t\u0004\u0002\u0015M#xN]3UsB,7/\u0003\u0002%K\tyQ\t_3dkR|'/T3ue&\u001c7O\u0003\u0002#\r!)qe\u0001a\u0001Q\u0005\tQ\r\u0005\u0002*Y5\t!F\u0003\u0002,\u0015\u0005AQ\r_3dkR|'/\u0003\u0002%U\u0005YA-Z:fe&\fG.\u001b>f)\tAs\u0006C\u00031\t\u0001\u0007q$\u0001\u0004cS:\f'/\u001f"
)
public final class ExecutorMetricsSerializer {
   public static ExecutorMetrics deserialize(final StoreTypes.ExecutorMetrics binary) {
      return ExecutorMetricsSerializer$.MODULE$.deserialize(binary);
   }

   public static StoreTypes.ExecutorMetrics serialize(final ExecutorMetrics e) {
      return ExecutorMetricsSerializer$.MODULE$.serialize(e);
   }
}
