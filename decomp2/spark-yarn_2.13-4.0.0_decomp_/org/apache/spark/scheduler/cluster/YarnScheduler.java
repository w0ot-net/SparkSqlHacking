package org.apache.spark.scheduler.cluster;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.yarn.SparkRackResolver;
import org.apache.spark.deploy.yarn.SparkRackResolver$;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.util.Utils.;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3Q\u0001C\u0005\u0001\u001bMA\u0001\u0002\u0007\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\u0006=\u0001!\ta\b\u0005\bG\u0001\u0011\r\u0011\"\u0011%\u0011\u00191\u0004\u0001)A\u0005K!Aq\u0007\u0001b\u0001\n\u0003i\u0001\b\u0003\u0004B\u0001\u0001\u0006I!\u000f\u0005\u0006\u0005\u0002!\te\u0011\u0002\u000e3\u0006\u0014hnU2iK\u0012,H.\u001a:\u000b\u0005)Y\u0011aB2mkN$XM\u001d\u0006\u0003\u00195\t\u0011b]2iK\u0012,H.\u001a:\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\"\u0001\u0001\u000b\u0011\u0005U1R\"A\u0006\n\u0005]Y!!\u0005+bg.\u001c6\r[3ek2,'/S7qY\u0006\u00111oY\u0002\u0001!\tYB$D\u0001\u000e\u0013\tiRB\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/\u0001\u0004=S:LGO\u0010\u000b\u0003A\t\u0002\"!\t\u0001\u000e\u0003%AQ\u0001\u0007\u0002A\u0002i\t\u0001\u0003Z3gCVdGOU1dWZ\u000bG.^3\u0016\u0003\u0015\u00022AJ\u0015,\u001b\u00059#\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):#AB(qi&|g\u000e\u0005\u0002-g9\u0011Q&\r\t\u0003]\u001dj\u0011a\f\u0006\u0003ae\ta\u0001\u0010:p_Rt\u0014B\u0001\u001a(\u0003\u0019\u0001&/\u001a3fM&\u0011A'\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005I:\u0013!\u00053fM\u0006,H\u000e\u001e*bG.4\u0016\r\\;fA\u0005A!/Z:pYZ,'/F\u0001:!\tQt(D\u0001<\u0015\taT(\u0001\u0003zCJt'B\u0001 \u000e\u0003\u0019!W\r\u001d7ps&\u0011\u0001i\u000f\u0002\u0012'B\f'o\u001b*bG.\u0014Vm]8mm\u0016\u0014\u0018!\u0003:fg>dg/\u001a:!\u0003A9W\r\u001e*bG.\u001chi\u001c:I_N$8\u000f\u0006\u0002E\u001bB\u0019QIS\u0013\u000f\u0005\u0019CeB\u0001\u0018H\u0013\u0005A\u0013BA%(\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0013'\u0003\u0007M+\u0017O\u0003\u0002JO!)aj\u0002a\u0001\u001f\u0006I\u0001n\\:u!>\u0014Ho\u001d\t\u0004\u000b*[\u0003"
)
public class YarnScheduler extends TaskSchedulerImpl {
   private final Option defaultRackValue = new Some("/default-rack");
   private final SparkRackResolver resolver;

   public Option defaultRackValue() {
      return this.defaultRackValue;
   }

   public SparkRackResolver resolver() {
      return this.resolver;
   }

   public Seq getRacksForHosts(final Seq hostPorts) {
      Seq hosts = (Seq)hostPorts.map((x$1) -> (String).MODULE$.parseHostPort(x$1)._1());
      return (Seq)this.resolver().resolve(hosts).map((node) -> scala.Option..MODULE$.apply(node.getNetworkLocation()));
   }

   public YarnScheduler(final SparkContext sc) {
      super(sc);
      this.resolver = SparkRackResolver$.MODULE$.get(sc.hadoopConfiguration());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
