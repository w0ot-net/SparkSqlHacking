package org.apache.spark.scheduler.cluster.k8s;

import org.apache.spark.api.plugin.DriverPlugin;
import org.apache.spark.api.plugin.ExecutorPlugin;
import org.apache.spark.api.plugin.SparkPlugin;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2A\u0001B\u0003\u0001%!)1\u0005\u0001C\u0001I!)q\u0005\u0001C!Q!)A\u0006\u0001C![\t\u0011R\t_3dkR|'OU8mYBcWoZ5o\u0015\t1q!A\u0002lqMT!\u0001C\u0005\u0002\u000f\rdWo\u001d;fe*\u0011!bC\u0001\ng\u000eDW\rZ;mKJT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011c\u0007\t\u0003)ei\u0011!\u0006\u0006\u0003-]\tA\u0001\\1oO*\t\u0001$\u0001\u0003kCZ\f\u0017B\u0001\u000e\u0016\u0005\u0019y%M[3diB\u0011A$I\u0007\u0002;)\u0011adH\u0001\u0007a2,x-\u001b8\u000b\u0005\u0001Z\u0011aA1qS&\u0011!%\b\u0002\f'B\f'o\u001b)mk\u001eLg.\u0001\u0004=S:LGO\u0010\u000b\u0002KA\u0011a\u0005A\u0007\u0002\u000b\u0005aAM]5wKJ\u0004F.^4j]R\t\u0011\u0006\u0005\u0002\u001dU%\u00111&\b\u0002\r\tJLg/\u001a:QYV<\u0017N\\\u0001\u000fKb,7-\u001e;peBcWoZ5o)\u0005q\u0003C\u0001\u000f0\u0013\t\u0001TD\u0001\bFq\u0016\u001cW\u000f^8s!2,x-\u001b8"
)
public class ExecutorRollPlugin implements SparkPlugin {
   public DriverPlugin driverPlugin() {
      return new ExecutorRollDriverPlugin();
   }

   public ExecutorPlugin executorPlugin() {
      return null;
   }
}
