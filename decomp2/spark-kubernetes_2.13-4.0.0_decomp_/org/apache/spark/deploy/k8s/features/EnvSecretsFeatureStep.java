package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.EnvVarBuilder;
import io.fabric8.kubernetes.api.model.EnvVarFluent;
import io.fabric8.kubernetes.api.model.EnvVarSourceFluent;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.SparkPod;
import scala.MatchError;
import scala.Predef.;
import scala.collection.immutable.Iterable;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000552Q\u0001B\u0003\u0001\u0017EA\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\u0006E\u0001!\ta\t\u0005\u0006M\u0001!\te\n\u0002\u0016\u000b:48+Z2sKR\u001ch)Z1ukJ,7\u000b^3q\u0015\t1q!\u0001\u0005gK\u0006$XO]3t\u0015\tA\u0011\"A\u0002lqMT!AC\u0006\u0002\r\u0011,\u0007\u000f\\8z\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000e\u000e\u0003\u0015I!aG\u0003\u00037-+(-\u001a:oKR,7OR3biV\u0014XmQ8oM&<7\u000b^3q\u00039YWOY3s]\u0016$Xm]\"p]\u001a\u001c\u0001\u0001\u0005\u0002 A5\tq!\u0003\u0002\"\u000f\tq1*\u001e2fe:,G/Z:D_:4\u0017A\u0002\u001fj]&$h\b\u0006\u0002%KA\u0011\u0011\u0004\u0001\u0005\u00069\t\u0001\rAH\u0001\rG>tg-[4ve\u0016\u0004v\u000e\u001a\u000b\u0003Q-\u0002\"aH\u0015\n\u0005):!\u0001C*qCJ\\\u0007k\u001c3\t\u000b1\u001a\u0001\u0019\u0001\u0015\u0002\u0007A|G\r"
)
public class EnvSecretsFeatureStep implements KubernetesFeatureConfigStep {
   private final KubernetesConf kubernetesConf;

   public Map getAdditionalPodSystemProperties() {
      return KubernetesFeatureConfigStep.getAdditionalPodSystemProperties$(this);
   }

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public Seq getAdditionalKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalKubernetesResources$(this);
   }

   public SparkPod configurePod(final SparkPod pod) {
      Iterable addedEnvSecrets = (Iterable)this.kubernetesConf.secretEnvNamesToKeyRefs().map((x0$1) -> {
         if (x0$1 != null) {
            String envName = (String)x0$1._1();
            String keyRef = (String)x0$1._2();
            String[] keyRefParts = keyRef.split(":");
            .MODULE$.require(scala.collection.ArrayOps..MODULE$.size$extension(.MODULE$.refArrayOps((Object[])keyRefParts)) == 2, () -> "SecretKeyRef must be in the form name:key.");
            String name = keyRefParts[0];
            String key = keyRefParts[1];
            return ((EnvVarBuilder)((EnvVarFluent.ValueFromNested)((EnvVarSourceFluent.SecretKeyRefNested)(new EnvVarBuilder()).withName(envName).withNewValueFrom().withNewSecretKeyRef().withKey(key).withName(name)).endSecretKeyRef()).endValueFrom()).build();
         } else {
            throw new MatchError(x0$1);
         }
      });
      Container containerWithEnvVars = ((ContainerBuilder)(new ContainerBuilder(pod.container())).addAllToEnv(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(addedEnvSecrets.toSeq()).asJava())).build();
      return new SparkPod(pod.pod(), containerWithEnvVars);
   }

   public EnvSecretsFeatureStep(final KubernetesConf kubernetesConf) {
      this.kubernetesConf = kubernetesConf;
      KubernetesFeatureConfigStep.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
