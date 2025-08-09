package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Pod;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.internal.config.ConfigEntry;
import scala.Option;
import scala.Option.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013QAB\u0004\u0001\u001bMA\u0001B\b\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\u0006I\u0001!\t!\n\u0005\tQ\u0001A)\u0019!C\u0005S!A\u0001\b\u0001EC\u0002\u0013%\u0011\u0006C\u0003:\u0001\u0011\u0005#H\u0001\u0015Fq\u0016\u001cW\u000f^8s\u0017V\u0014WM\u001d8fi\u0016\u001c8I]3eK:$\u0018.\u00197t\r\u0016\fG/\u001e:f'R,\u0007O\u0003\u0002\t\u0013\u0005Aa-Z1ukJ,7O\u0003\u0002\u000b\u0017\u0005\u00191\u000eO:\u000b\u00051i\u0011A\u00023fa2|\u0017P\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h'\r\u0001AC\u0007\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005maR\"A\u0004\n\u0005u9!aG&vE\u0016\u0014h.\u001a;fg\u001a+\u0017\r^;sK\u000e{gNZ5h'R,\u0007/\u0001\blk\n,'O\\3uKN\u001cuN\u001c4\u0004\u0001A\u0011\u0011EI\u0007\u0002\u0013%\u00111%\u0003\u0002\u000f\u0017V\u0014WM\u001d8fi\u0016\u001c8i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0011ae\n\t\u00037\u0001AQA\b\u0002A\u0002\u0001\nA\u0003\u001a:jm\u0016\u00148+\u001a:wS\u000e,\u0017iY2pk:$X#\u0001\u0016\u0011\u0007UYS&\u0003\u0002--\t1q\n\u001d;j_:\u0004\"AL\u001b\u000f\u0005=\u001a\u0004C\u0001\u0019\u0017\u001b\u0005\t$B\u0001\u001a \u0003\u0019a$o\\8u}%\u0011AGF\u0001\u0007!J,G-\u001a4\n\u0005Y:$AB*ue&twM\u0003\u00025-\u00051R\r_3dkR|'oU3sm&\u001cW-Q2d_VtG/\u0001\u0007d_:4\u0017nZ;sKB{G\r\u0006\u0002<}A\u0011\u0011\u0005P\u0005\u0003{%\u0011\u0001b\u00159be.\u0004v\u000e\u001a\u0005\u0006\u007f\u0015\u0001\raO\u0001\u0004a>$\u0007"
)
public class ExecutorKubernetesCredentialsFeatureStep implements KubernetesFeatureConfigStep {
   private Option driverServiceAccount;
   private Option executorServiceAccount;
   private final KubernetesConf kubernetesConf;
   private volatile byte bitmap$0;

   public Map getAdditionalPodSystemProperties() {
      return KubernetesFeatureConfigStep.getAdditionalPodSystemProperties$(this);
   }

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public Seq getAdditionalKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalKubernetesResources$(this);
   }

   private Option driverServiceAccount$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.driverServiceAccount = (Option)this.kubernetesConf.get((ConfigEntry)Config$.MODULE$.KUBERNETES_DRIVER_SERVICE_ACCOUNT_NAME());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.driverServiceAccount;
   }

   private Option driverServiceAccount() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.driverServiceAccount$lzycompute() : this.driverServiceAccount;
   }

   private Option executorServiceAccount$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.executorServiceAccount = (Option)this.kubernetesConf.get((ConfigEntry)Config$.MODULE$.KUBERNETES_EXECUTOR_SERVICE_ACCOUNT_NAME());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.executorServiceAccount;
   }

   private Option executorServiceAccount() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.executorServiceAccount$lzycompute() : this.executorServiceAccount;
   }

   public SparkPod configurePod(final SparkPod pod) {
      return pod.copy(.MODULE$.apply(pod.pod().getSpec().getServiceAccount()).isEmpty() ? (Pod)KubernetesUtils$.MODULE$.buildPodWithServiceAccount(this.executorServiceAccount().orElse(() -> this.driverServiceAccount()), pod).getOrElse(() -> pod.pod()) : pod.pod(), pod.copy$default$2());
   }

   public ExecutorKubernetesCredentialsFeatureStep(final KubernetesConf kubernetesConf) {
      this.kubernetesConf = kubernetesConf;
      KubernetesFeatureConfigStep.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
