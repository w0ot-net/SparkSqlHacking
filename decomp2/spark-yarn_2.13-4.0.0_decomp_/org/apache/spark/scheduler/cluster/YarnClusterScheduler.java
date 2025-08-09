package org.apache.spark.scheduler.cluster;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.yarn.ApplicationMaster$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00192Q\u0001B\u0003\u0001\u0013=A\u0001\u0002\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\u00065\u0001!\ta\u0007\u0005\u0006=\u0001!\te\b\u0002\u00153\u0006\u0014hn\u00117vgR,'oU2iK\u0012,H.\u001a:\u000b\u0005\u00199\u0011aB2mkN$XM\u001d\u0006\u0003\u0011%\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\"\u0001\u0001\t\u0011\u0005E\u0011R\"A\u0003\n\u0005M)!!D-be:\u001c6\r[3ek2,'/\u0001\u0002tG\u000e\u0001\u0001CA\f\u0019\u001b\u0005I\u0011BA\r\n\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\u0019a\u0014N\\5u}Q\u0011A$\b\t\u0003#\u0001AQ\u0001\u0006\u0002A\u0002Y\tQ\u0002]8tiN#\u0018M\u001d;I_>\\G#\u0001\u0011\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\tUs\u0017\u000e\u001e"
)
public class YarnClusterScheduler extends YarnScheduler {
   private final SparkContext sc;

   public void postStartHook() {
      ApplicationMaster$.MODULE$.sparkContextInitialized(this.sc);
      super.postStartHook();
      this.logInfo(() -> "YarnClusterScheduler.postStartHook done");
   }

   public YarnClusterScheduler(final SparkContext sc) {
      super(sc);
      this.sc = sc;
      this.logInfo(() -> "Created YarnClusterScheduler");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
