package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.api.model.VolumeBuilder;
import io.fabric8.kubernetes.api.model.VolumeFluent;
import io.fabric8.kubernetes.api.model.VolumeMount;
import io.fabric8.kubernetes.api.model.VolumeMountBuilder;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.SparkPod;
import scala.MatchError;
import scala.collection.Iterable;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005u2Q!\u0002\u0004\u0001\u0019IA\u0001\"\b\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006O\u0001!\t\u0005\u000b\u0005\u0006]\u0001!Ia\f\u0002\u0018\u001b>,h\u000e^*fGJ,Go\u001d$fCR,(/Z*uKBT!a\u0002\u0005\u0002\u0011\u0019,\u0017\r^;sKNT!!\u0003\u0006\u0002\u0007-D4O\u0003\u0002\f\u0019\u00051A-\u001a9m_fT!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'oZ\n\u0004\u0001MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\r\u0005\u0002\u001b75\ta!\u0003\u0002\u001d\r\tY2*\u001e2fe:,G/Z:GK\u0006$XO]3D_:4\u0017nZ*uKB\fab[;cKJtW\r^3t\u0007>tgm\u0001\u0001\u0011\u0005\u0001\nS\"\u0001\u0005\n\u0005\tB!AD&vE\u0016\u0014h.\u001a;fg\u000e{gNZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u00152\u0003C\u0001\u000e\u0001\u0011\u0015i\"\u00011\u0001 \u00031\u0019wN\u001c4jOV\u0014X\rU8e)\tIC\u0006\u0005\u0002!U%\u00111\u0006\u0003\u0002\t'B\f'o\u001b)pI\")Qf\u0001a\u0001S\u0005\u0019\u0001o\u001c3\u0002!M,7M]3u->dW/\\3OC6,GC\u0001\u0019<!\t\t\u0004H\u0004\u00023mA\u00111'F\u0007\u0002i)\u0011QGH\u0001\u0007yI|w\u000e\u001e \n\u0005]*\u0012A\u0002)sK\u0012,g-\u0003\u0002:u\t11\u000b\u001e:j]\u001eT!aN\u000b\t\u000bq\"\u0001\u0019\u0001\u0019\u0002\u0015M,7M]3u\u001d\u0006lW\r"
)
public class MountSecretsFeatureStep implements KubernetesFeatureConfigStep {
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
      Iterable addedVolumes = (Iterable)this.kubernetesConf.secretNamesToMountPaths().keys().map((secretName) -> ((VolumeBuilder)((VolumeFluent.SecretNested)(new VolumeBuilder()).withName(this.secretVolumeName(secretName)).withNewSecret().withSecretName(secretName)).endSecret()).build());
      Pod podWithVolumes = ((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(pod.pod())).editOrNewSpec().addToVolumes((Volume[])addedVolumes.toSeq().toArray(.MODULE$.apply(Volume.class)))).endSpec()).build();
      scala.collection.immutable.Iterable addedVolumeMounts = (scala.collection.immutable.Iterable)this.kubernetesConf.secretNamesToMountPaths().map((x0$1) -> {
         if (x0$1 != null) {
            String secretName = (String)x0$1._1();
            String mountPath = (String)x0$1._2();
            return ((VolumeMountBuilder)(new VolumeMountBuilder()).withName(this.secretVolumeName(secretName)).withMountPath(mountPath)).build();
         } else {
            throw new MatchError(x0$1);
         }
      });
      Container containerWithMounts = ((ContainerBuilder)(new ContainerBuilder(pod.container())).addToVolumeMounts((VolumeMount[])addedVolumeMounts.toSeq().toArray(.MODULE$.apply(VolumeMount.class)))).build();
      return new SparkPod(podWithVolumes, containerWithMounts);
   }

   private String secretVolumeName(final String secretName) {
      return secretName + "-volume";
   }

   public MountSecretsFeatureStep(final KubernetesConf kubernetesConf) {
      this.kubernetesConf = kubernetesConf;
      KubernetesFeatureConfigStep.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
