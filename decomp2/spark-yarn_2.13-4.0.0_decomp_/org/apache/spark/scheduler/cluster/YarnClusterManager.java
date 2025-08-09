package org.apache.spark.scheduler.cluster;

import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.scheduler.ExternalClusterManager;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.TaskScheduler;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3QAB\u0004\u0001\u0017EAQ\u0001\b\u0001\u0005\u0002yAQ!\t\u0001\u0005B\tBQa\r\u0001\u0005BQBQa\u0010\u0001\u0005B\u0001CQa\u0012\u0001\u0005B!\u0013!#W1s]\u000ecWo\u001d;fe6\u000bg.Y4fe*\u0011\u0001\"C\u0001\bG2,8\u000f^3s\u0015\tQ1\"A\u0005tG\",G-\u001e7fe*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xmE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007CA\r\u001b\u001b\u0005I\u0011BA\u000e\n\u0005Y)\u0005\u0010^3s]\u0006d7\t\\;ti\u0016\u0014X*\u00198bO\u0016\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003}\u0001\"\u0001\t\u0001\u000e\u0003\u001d\t\u0011bY1o\u0007J,\u0017\r^3\u0015\u0005\r2\u0003CA\n%\u0013\t)CCA\u0004C_>dW-\u00198\t\u000b\u001d\u0012\u0001\u0019\u0001\u0015\u0002\u00135\f7\u000f^3s+Jc\u0005CA\u00151\u001d\tQc\u0006\u0005\u0002,)5\tAF\u0003\u0002.;\u00051AH]8pizJ!a\f\u000b\u0002\rA\u0013X\rZ3g\u0013\t\t$G\u0001\u0004TiJLgn\u001a\u0006\u0003_Q\t1c\u0019:fCR,G+Y:l'\u000eDW\rZ;mKJ$2!\u000e\u001d?!\tIb'\u0003\u00028\u0013\tiA+Y:l'\u000eDW\rZ;mKJDQ!O\u0002A\u0002i\n!a]2\u0011\u0005mbT\"A\u0006\n\u0005uZ!\u0001D*qCJ\\7i\u001c8uKb$\b\"B\u0014\u0004\u0001\u0004A\u0013AF2sK\u0006$XmU2iK\u0012,H.\u001a:CC\u000e\\WM\u001c3\u0015\t\u0005#UI\u0012\t\u00033\tK!aQ\u0005\u0003!M\u001b\u0007.\u001a3vY\u0016\u0014()Y2lK:$\u0007\"B\u001d\u0005\u0001\u0004Q\u0004\"B\u0014\u0005\u0001\u0004A\u0003\"\u0002\u0006\u0005\u0001\u0004)\u0014AC5oSRL\u0017\r\\5{KR\u0019\u0011\nT'\u0011\u0005MQ\u0015BA&\u0015\u0005\u0011)f.\u001b;\t\u000b))\u0001\u0019A\u001b\t\u000b9+\u0001\u0019A!\u0002\u000f\t\f7m[3oI\u0002"
)
public class YarnClusterManager implements ExternalClusterManager {
   public boolean canCreate(final String masterURL) {
      boolean var10000;
      label23: {
         String var2 = "yarn";
         if (masterURL == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (masterURL.equals(var2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public TaskScheduler createTaskScheduler(final SparkContext sc, final String masterURL) {
      String var4 = sc.deployMode();
      switch (var4 == null ? 0 : var4.hashCode()) {
         case -1357712437:
            if ("client".equals(var4)) {
               return new YarnScheduler(sc);
            }
            break;
         case 872092154:
            if ("cluster".equals(var4)) {
               return new YarnClusterScheduler(sc);
            }
      }

      throw new SparkException("Unknown deploy mode '" + sc.deployMode() + "' for Yarn");
   }

   public SchedulerBackend createSchedulerBackend(final SparkContext sc, final String masterURL, final TaskScheduler scheduler) {
      String var5 = sc.deployMode();
      switch (var5 == null ? 0 : var5.hashCode()) {
         case -1357712437:
            if ("client".equals(var5)) {
               return new YarnClientSchedulerBackend((TaskSchedulerImpl)scheduler, sc);
            }
            break;
         case 872092154:
            if ("cluster".equals(var5)) {
               return new YarnClusterSchedulerBackend((TaskSchedulerImpl)scheduler, sc);
            }
      }

      throw new SparkException("Unknown deploy mode '" + sc.deployMode() + "' for Yarn");
   }

   public void initialize(final TaskScheduler scheduler, final SchedulerBackend backend) {
      ((TaskSchedulerImpl)scheduler).initialize(backend);
   }
}
