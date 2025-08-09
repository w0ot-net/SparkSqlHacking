package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ConfigMapFluent;
import io.fabric8.kubernetes.api.model.ConfigMapVolumeSourceFluent;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodSpecFluent;
import io.fabric8.kubernetes.api.model.VolumeFluent;
import java.io.File;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.internal.config.ConfigEntry;
import org.sparkproject.guava.io.Files;
import scala.Option;
import scala.Predef;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;

@ScalaSignature(
   bytes = "\u0006\u000514QAC\u0006\u0001#]A\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\u0006Q\u0001!\t!\u000b\u0005\bY\u0001\u0011\r\u0011\"\u0003.\u0011\u0019\t\u0004\u0001)A\u0005]!9!\u0007\u0001b\u0001\n\u0013\u0019\u0004B\u0002\u001f\u0001A\u0003%A\u0007C\u0003>\u0001\u0011\u0005a\bC\u0003E\u0001\u0011\u0005S\tC\u0003T\u0001\u0011\u0005CK\u0001\rQ_\u0012$V-\u001c9mCR,7i\u001c8gS\u001el\u0015\r]*uKBT!\u0001D\u0007\u0002\u0011\u0019,\u0017\r^;sKNT!AD\b\u0002\u0007-D4O\u0003\u0002\u0011#\u00051A-\u001a9m_fT!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\n\u0004\u0001aq\u0002CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"AB!osJ+g\r\u0005\u0002 A5\t1\"\u0003\u0002\"\u0017\tY2*\u001e2fe:,G/Z:GK\u0006$XO]3D_:4\u0017nZ*uKB\fAaY8oM\u000e\u0001\u0001CA\u0013'\u001b\u0005i\u0011BA\u0014\u000e\u00059YUOY3s]\u0016$Xm]\"p]\u001a\fa\u0001P5oSRtDC\u0001\u0016,!\ty\u0002\u0001C\u0003#\u0005\u0001\u0007A%A\u0006iCN$V-\u001c9mCR,W#\u0001\u0018\u0011\u0005ey\u0013B\u0001\u0019\u001b\u0005\u001d\u0011un\u001c7fC:\fA\u0002[1t)\u0016l\u0007\u000f\\1uK\u0002\nQbY8oM&<W.\u00199OC6,W#\u0001\u001b\u0011\u0005URT\"\u0001\u001c\u000b\u0005]B\u0014\u0001\u00027b]\u001eT\u0011!O\u0001\u0005U\u00064\u0018-\u0003\u0002<m\t11\u000b\u001e:j]\u001e\fabY8oM&<W.\u00199OC6,\u0007%\u0001\u0007d_:4\u0017nZ;sKB{G\r\u0006\u0002@\u0005B\u0011Q\u0005Q\u0005\u0003\u00036\u0011\u0001b\u00159be.\u0004v\u000e\u001a\u0005\u0006\u0007\u001e\u0001\raP\u0001\u0004a>$\u0017\u0001I4fi\u0006#G-\u001b;j_:\fG\u000eU8e'f\u001cH/Z7Qe>\u0004XM\u001d;jKN$\u0012A\u0012\t\u0005\u000f:\u000b\u0016K\u0004\u0002I\u0019B\u0011\u0011JG\u0007\u0002\u0015*\u00111jI\u0001\u0007yI|w\u000e\u001e \n\u00055S\u0012A\u0002)sK\u0012,g-\u0003\u0002P!\n\u0019Q*\u00199\u000b\u00055S\u0002CA$S\u0013\tY\u0004+\u0001\u0011hKR\fE\rZ5uS>t\u0017\r\\&vE\u0016\u0014h.\u001a;fgJ+7o\\;sG\u0016\u001cH#A+\u0011\u0007Y[fL\u0004\u0002X3:\u0011\u0011\nW\u0005\u00027%\u0011!LG\u0001\ba\u0006\u001c7.Y4f\u0013\taVLA\u0002TKFT!A\u0017\u000e\u0011\u0005}SW\"\u00011\u000b\u0005\u0005\u0014\u0017!B7pI\u0016d'BA2e\u0003\r\t\u0007/\u001b\u0006\u0003K\u001a\f!b[;cKJtW\r^3t\u0015\t9\u0007.A\u0004gC\n\u0014\u0018n\u0019\u001d\u000b\u0003%\f!![8\n\u0005-\u0004'a\u0003%bg6+G/\u00193bi\u0006\u0004"
)
public class PodTemplateConfigMapStep implements KubernetesFeatureConfigStep {
   private final KubernetesConf conf;
   private final boolean hasTemplate;
   private final String configmapName;

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   private boolean hasTemplate() {
      return this.hasTemplate;
   }

   private String configmapName() {
      return this.configmapName;
   }

   public SparkPod configurePod(final SparkPod pod) {
      if (this.hasTemplate()) {
         Pod podWithVolume = ((PodBuilder)((PodFluent.SpecNested)((PodSpecFluent.VolumesNested)((VolumeFluent.ConfigMapNested)((ConfigMapVolumeSourceFluent.ItemsNested)(new PodBuilder(pod.pod())).editSpec().addNewVolume().withName(Constants$.MODULE$.POD_TEMPLATE_VOLUME()).withNewConfigMap().withName(this.configmapName()).addNewItem().withKey(Constants$.MODULE$.POD_TEMPLATE_KEY()).withPath(Constants$.MODULE$.EXECUTOR_POD_SPEC_TEMPLATE_FILE_NAME())).endItem()).endConfigMap()).endVolume()).endSpec()).build();
         Container containerWithVolume = ((ContainerBuilder)((ContainerFluent.VolumeMountsNested)(new ContainerBuilder(pod.container())).addNewVolumeMount().withName(Constants$.MODULE$.POD_TEMPLATE_VOLUME()).withMountPath(Constants$.MODULE$.EXECUTOR_POD_SPEC_TEMPLATE_MOUNTPATH())).endVolumeMount()).build();
         return new SparkPod(podWithVolume, containerWithVolume);
      } else {
         return pod;
      }
   }

   public Map getAdditionalPodSystemProperties() {
      if (this.hasTemplate()) {
         Map var10000 = .MODULE$.Map();
         ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
         Tuple2[] var10002 = new Tuple2[1];
         Predef.ArrowAssoc var10005 = scala.Predef.ArrowAssoc..MODULE$;
         Object var10006 = .MODULE$.ArrowAssoc(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_FILE().key());
         String var10007 = Constants$.MODULE$.EXECUTOR_POD_SPEC_TEMPLATE_MOUNTPATH();
         var10002[0] = var10005.$minus$greater$extension(var10006, var10007 + "/" + Constants$.MODULE$.EXECUTOR_POD_SPEC_TEMPLATE_FILE_NAME());
         return (Map)var10000.apply(var10001.wrapRefArray((Object[])var10002));
      } else {
         return .MODULE$.Map().empty();
      }
   }

   public Seq getAdditionalKubernetesResources() {
      if (this.hasTemplate()) {
         String podTemplateFile = (String)((Option)this.conf.get((ConfigEntry)Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_FILE())).get();
         Configuration hadoopConf = org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().newConfiguration(this.conf.sparkConf());
         String uri = org.apache.spark.util.DependencyUtils..MODULE$.downloadFile(podTemplateFile, org.apache.spark.util.Utils..MODULE$.createTempDir(), this.conf.sparkConf(), hadoopConf);
         String file = (new URI(uri)).getPath();
         String podTemplateString = Files.asCharSource(new File(file), StandardCharsets.UTF_8).read();
         return new scala.collection.immutable..colon.colon(((ConfigMapBuilder)((ConfigMapFluent)((ConfigMapFluent.MetadataNested)(new ConfigMapBuilder()).withNewMetadata().withName(this.configmapName())).endMetadata()).withImmutable(.MODULE$.boolean2Boolean(true)).addToData(Constants$.MODULE$.POD_TEMPLATE_KEY(), podTemplateString)).build(), scala.collection.immutable.Nil..MODULE$);
      } else {
         return scala.collection.immutable.Nil..MODULE$;
      }
   }

   public PodTemplateConfigMapStep(final KubernetesConf conf) {
      this.conf = conf;
      KubernetesFeatureConfigStep.$init$(this);
      this.hasTemplate = conf.contains(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_FILE());
      String var10001 = conf.resourceNamePrefix();
      this.configmapName = var10001 + "-" + Constants$.MODULE$.POD_TEMPLATE_CONFIGMAP();
   }
}
