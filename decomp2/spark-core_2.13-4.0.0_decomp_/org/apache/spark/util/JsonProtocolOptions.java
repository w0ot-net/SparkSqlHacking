package org.apache.spark.util;

import org.apache.spark.SparkConf;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192Q!\u0002\u0004\u0001\u00119A\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006Ia\u0006\u0005\u00067\u0001!\t\u0001\b\u0005\bA\u0001\u0011\r\u0011\"\u0001\"\u0011\u0019)\u0003\u0001)A\u0005E\t\u0019\"j]8o!J|Go\\2pY>\u0003H/[8og*\u0011q\u0001C\u0001\u0005kRLGN\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h'\t\u0001q\u0002\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VMZ\u0001\u0005G>tgm\u0001\u0001\u0011\u0005aIR\"\u0001\u0005\n\u0005iA!!C*qCJ\\7i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0011Qd\b\t\u0003=\u0001i\u0011A\u0002\u0005\u0006+\t\u0001\raF\u0001\u001fS:\u001cG.\u001e3f)\u0006\u001c8.T3ue&\u001c7/Q2dk6,H.\u0019;peN,\u0012A\t\t\u0003!\rJ!\u0001J\t\u0003\u000f\t{w\u000e\\3b]\u0006y\u0012N\\2mk\u0012,G+Y:l\u001b\u0016$(/[2t\u0003\u000e\u001cW/\\;mCR|'o\u001d\u0011"
)
public class JsonProtocolOptions {
   private final boolean includeTaskMetricsAccumulators;

   public boolean includeTaskMetricsAccumulators() {
      return this.includeTaskMetricsAccumulators;
   }

   public JsonProtocolOptions(final SparkConf conf) {
      this.includeTaskMetricsAccumulators = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.EVENT_LOG_INCLUDE_TASK_METRICS_ACCUMULATORS()));
   }
}
