package org.apache.spark.deploy;

import org.apache.spark.api.plugin.DriverPlugin;
import org.apache.spark.api.plugin.ExecutorPlugin;
import org.apache.spark.api.plugin.SparkPlugin;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000552A\u0001B\u0003\u0001\u001d!)q\u0004\u0001C\u0001A!)1\u0005\u0001C!I!)\u0001\u0006\u0001C!S\t\u0019BI]5wKJ$\u0016.\\3pkR\u0004F.^4j]*\u0011aaB\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005!I\u0011!B:qCJ\\'B\u0001\u0006\f\u0003\u0019\t\u0007/Y2iK*\tA\"A\u0002pe\u001e\u001c\u0001aE\u0002\u0001\u001f]\u0001\"\u0001E\u000b\u000e\u0003EQ!AE\n\u0002\t1\fgn\u001a\u0006\u0002)\u0005!!.\u0019<b\u0013\t1\u0012C\u0001\u0004PE*,7\r\u001e\t\u00031ui\u0011!\u0007\u0006\u00035m\ta\u0001\u001d7vO&t'B\u0001\u000f\b\u0003\r\t\u0007/[\u0005\u0003=e\u00111b\u00159be.\u0004F.^4j]\u00061A(\u001b8jiz\"\u0012!\t\t\u0003E\u0001i\u0011!B\u0001\rIJLg/\u001a:QYV<\u0017N\u001c\u000b\u0002KA\u0011\u0001DJ\u0005\u0003Oe\u0011A\u0002\u0012:jm\u0016\u0014\b\u000b\\;hS:\fa\"\u001a=fGV$xN\u001d)mk\u001eLg\u000eF\u0001+!\tA2&\u0003\u0002-3\tqQ\t_3dkR|'\u000f\u00157vO&t\u0007"
)
public class DriverTimeoutPlugin implements SparkPlugin {
   public DriverPlugin driverPlugin() {
      return new DriverTimeoutDriverPlugin();
   }

   public ExecutorPlugin executorPlugin() {
      return null;
   }
}
