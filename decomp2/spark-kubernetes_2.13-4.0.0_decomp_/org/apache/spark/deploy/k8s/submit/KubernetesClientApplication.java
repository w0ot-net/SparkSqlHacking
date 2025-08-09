package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.client.KubernetesClient;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkApplication;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.KubernetesConf$;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkKubernetesClientFactory;
import org.apache.spark.deploy.k8s.SparkKubernetesClientFactory$;
import org.apache.spark.util.Utils.;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153Q\u0001B\u0003\u0001\u0017EAQ\u0001\b\u0001\u0005\u0002yAQ!\t\u0001\u0005B\tBQ\u0001\u0010\u0001\u0005\nu\u00121dS;cKJtW\r^3t\u00072LWM\u001c;BaBd\u0017nY1uS>t'B\u0001\u0004\b\u0003\u0019\u0019XOY7ji*\u0011\u0001\"C\u0001\u0004Wb\u001a(B\u0001\u0006\f\u0003\u0019!W\r\u001d7ps*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xmE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007CA\r\u001b\u001b\u0005I\u0011BA\u000e\n\u0005A\u0019\u0006/\u0019:l\u0003B\u0004H.[2bi&|g.\u0001\u0004=S:LGOP\u0002\u0001)\u0005y\u0002C\u0001\u0011\u0001\u001b\u0005)\u0011!B:uCJ$HcA\u0012'mA\u00111\u0003J\u0005\u0003KQ\u0011A!\u00168ji\")qE\u0001a\u0001Q\u0005!\u0011M]4t!\r\u0019\u0012fK\u0005\u0003UQ\u0011Q!\u0011:sCf\u0004\"\u0001L\u001a\u000f\u00055\n\u0004C\u0001\u0018\u0015\u001b\u0005y#B\u0001\u0019\u001e\u0003\u0019a$o\\8u}%\u0011!\u0007F\u0001\u0007!J,G-\u001a4\n\u0005Q*$AB*ue&twM\u0003\u00023)!)qG\u0001a\u0001q\u0005!1m\u001c8g!\tI$(D\u0001\f\u0013\tY4BA\u0005Ta\u0006\u00148nQ8oM\u0006\u0019!/\u001e8\u0015\u0007\rr4\tC\u0003@\u0007\u0001\u0007\u0001)A\bdY&,g\u000e^!sOVlWM\u001c;t!\t\u0001\u0013)\u0003\u0002C\u000b\ty1\t\\5f]R\f%oZ;nK:$8\u000fC\u0003E\u0007\u0001\u0007\u0001(A\u0005ta\u0006\u00148nQ8oM\u0002"
)
public class KubernetesClientApplication implements SparkApplication {
   public void start(final String[] args, final SparkConf conf) {
      ClientArguments parsedArguments = ClientArguments$.MODULE$.fromCommandLineArgs(args);
      this.run(parsedArguments, conf);
   }

   private void run(final ClientArguments clientArguments, final SparkConf sparkConf) {
      String kubernetesAppId = KubernetesConf$.MODULE$.getKubernetesAppId();
      KubernetesDriverConf kubernetesConf = KubernetesConf$.MODULE$.createDriverConf(sparkConf, kubernetesAppId, clientArguments.mainAppResource(), clientArguments.mainClass(), clientArguments.driverArgs(), clientArguments.proxyUser());
      String master = KubernetesUtils$.MODULE$.parseMasterUrl(sparkConf.get("spark.master"));
      LoggingPodStatusWatcherImpl watcher = new LoggingPodStatusWatcherImpl(kubernetesConf);
      .MODULE$.tryWithResource(() -> SparkKubernetesClientFactory$.MODULE$.createKubernetesClient(master, new Some(kubernetesConf.namespace()), Config$.MODULE$.KUBERNETES_AUTH_SUBMISSION_CONF_PREFIX(), SparkKubernetesClientFactory.ClientType$.MODULE$.Submission(), sparkConf, scala.None..MODULE$), (kubernetesClient) -> {
         $anonfun$run$6(kubernetesConf, watcher, kubernetesClient);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$run$6(final KubernetesDriverConf kubernetesConf$1, final LoggingPodStatusWatcherImpl watcher$1, final KubernetesClient kubernetesClient) {
      Client client = new Client(kubernetesConf$1, new KubernetesDriverBuilder(), kubernetesClient, watcher$1);
      client.run();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
