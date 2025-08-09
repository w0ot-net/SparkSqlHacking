package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.api.model.VolumeBuilder;
import io.fabric8.kubernetes.api.model.VolumeFluent;
import java.io.Serializable;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.SparkPod;
import scala.Function1;
import scala.Option;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013QAB\u0004\u0001\u001bMA\u0001B\b\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\u0006I\u0001!\t!\n\u0005\bQ\u0001\u0011\r\u0011\"\u0003*\u0011\u0019A\u0004\u0001)A\u0005U!)\u0011\b\u0001C!u\ti\u0002*\u00193p_B\u001cuN\u001c4Fq\u0016\u001cW\u000f^8s\r\u0016\fG/\u001e:f'R,\u0007O\u0003\u0002\t\u0013\u0005Aa-Z1ukJ,7O\u0003\u0002\u000b\u0017\u0005\u00191\u000eO:\u000b\u00051i\u0011A\u00023fa2|\u0017P\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h'\r\u0001AC\u0007\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005maR\"A\u0004\n\u0005u9!aG&vE\u0016\u0014h.\u001a;fg\u001a+\u0017\r^;sK\u000e{gNZ5h'R,\u0007/\u0001\u0003d_:47\u0001\u0001\t\u0003C\tj\u0011!C\u0005\u0003G%\u0011abS;cKJtW\r^3t\u0007>tg-\u0001\u0004=S:LGO\u0010\u000b\u0003M\u001d\u0002\"a\u0007\u0001\t\u000by\u0011\u0001\u0019\u0001\u0011\u0002'!\fGm\\8q\u0007>tg-[4NCBt\u0015-\\3\u0016\u0003)\u00022!F\u0016.\u0013\tacC\u0001\u0004PaRLwN\u001c\t\u0003]Ur!aL\u001a\u0011\u0005A2R\"A\u0019\u000b\u0005Iz\u0012A\u0002\u001fs_>$h(\u0003\u00025-\u00051\u0001K]3eK\u001aL!AN\u001c\u0003\rM#(/\u001b8h\u0015\t!d#\u0001\u000biC\u0012|w\u000e]\"p]\u001aLw-T1q\u001d\u0006lW\rI\u0001\rG>tg-[4ve\u0016\u0004v\u000e\u001a\u000b\u0003wy\u0002\"!\t\u001f\n\u0005uJ!\u0001C*qCJ\\\u0007k\u001c3\t\u000b}*\u0001\u0019A\u001e\u0002\u0011=\u0014\u0018nZ5oC2\u0004"
)
public class HadoopConfExecutorFeatureStep implements KubernetesFeatureConfigStep {
   private final Option org$apache$spark$deploy$k8s$features$HadoopConfExecutorFeatureStep$$hadoopConfigMapName;

   public Map getAdditionalPodSystemProperties() {
      return KubernetesFeatureConfigStep.getAdditionalPodSystemProperties$(this);
   }

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public Seq getAdditionalKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalKubernetesResources$(this);
   }

   public Option org$apache$spark$deploy$k8s$features$HadoopConfExecutorFeatureStep$$hadoopConfigMapName() {
      return this.org$apache$spark$deploy$k8s$features$HadoopConfExecutorFeatureStep$$hadoopConfigMapName;
   }

   public SparkPod configurePod(final SparkPod original) {
      return original.transform(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final HadoopConfExecutorFeatureStep $outer;

         public final Object applyOrElse(final SparkPod x1, final Function1 default) {
            if (this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfExecutorFeatureStep$$hadoopConfigMapName().isDefined()) {
               Volume confVolume = ((VolumeBuilder)((VolumeFluent.ConfigMapNested)(new VolumeBuilder()).withName(Constants$.MODULE$.HADOOP_CONF_VOLUME()).withNewConfigMap().withName((String)this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfExecutorFeatureStep$$hadoopConfigMapName().get())).endConfigMap()).build();
               Pod podWithConf = ((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(x1.pod())).editSpec().addNewVolumeLike(confVolume).endVolume()).endSpec()).build();
               Container containerWithMount = ((ContainerBuilder)((ContainerFluent.EnvNested)((ContainerFluent)((ContainerFluent.VolumeMountsNested)(new ContainerBuilder(x1.container())).addNewVolumeMount().withName(Constants$.MODULE$.HADOOP_CONF_VOLUME()).withMountPath(Constants$.MODULE$.HADOOP_CONF_DIR_PATH())).endVolumeMount()).addNewEnv().withName(Constants$.MODULE$.ENV_HADOOP_CONF_DIR()).withValue(Constants$.MODULE$.HADOOP_CONF_DIR_PATH())).endEnv()).build();
               return new SparkPod(podWithConf, containerWithMount);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final SparkPod x1) {
            return this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfExecutorFeatureStep$$hadoopConfigMapName().isDefined();
         }

         public {
            if (HadoopConfExecutorFeatureStep.this == null) {
               throw null;
            } else {
               this.$outer = HadoopConfExecutorFeatureStep.this;
            }
         }
      });
   }

   public HadoopConfExecutorFeatureStep(final KubernetesConf conf) {
      KubernetesFeatureConfigStep.$init$(this);
      this.org$apache$spark$deploy$k8s$features$HadoopConfExecutorFeatureStep$$hadoopConfigMapName = conf.getOption(Constants$.MODULE$.HADOOP_CONFIG_MAP_NAME());
   }
}
