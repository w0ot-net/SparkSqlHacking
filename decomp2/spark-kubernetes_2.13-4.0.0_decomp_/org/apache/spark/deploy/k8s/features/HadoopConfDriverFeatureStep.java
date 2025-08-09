package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ConfigMapFluent;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.KeyToPathBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.api.model.VolumeBuilder;
import io.fabric8.kubernetes.api.model.VolumeFluent;
import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.util.ArrayImplicits.;
import org.sparkproject.guava.io.Files;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005a4Q!\u0004\b\u0001)iA\u0001\"\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\u0006W\u0001!\t\u0001\f\u0005\b_\u0001\u0011\r\u0011\"\u00031\u0011\u0019y\u0004\u0001)A\u0005c!9\u0001\t\u0001b\u0001\n\u0013\u0001\u0004BB!\u0001A\u0003%\u0011\u0007\u0003\u0005C\u0001!\u0015\r\u0011\"\u0003D\u0011\u0015)\u0006\u0001\"\u0003W\u0011\u00159\u0006\u0001\"\u0003Y\u0011\u0015a\u0006\u0001\"\u0011^\u0011\u0015\u0019\u0007\u0001\"\u0011e\u0011\u0015A\u0007\u0001\"\u0011j\u0005mA\u0015\rZ8pa\u000e{gN\u001a#sSZ,'OR3biV\u0014Xm\u0015;fa*\u0011q\u0002E\u0001\tM\u0016\fG/\u001e:fg*\u0011\u0011CE\u0001\u0004Wb\u001a(BA\n\u0015\u0003\u0019!W\r\u001d7ps*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014xmE\u0002\u00017\u0005\u0002\"\u0001H\u0010\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011a!\u00118z%\u00164\u0007C\u0001\u0012$\u001b\u0005q\u0011B\u0001\u0013\u000f\u0005mYUOY3s]\u0016$Xm\u001d$fCR,(/Z\"p]\u001aLwm\u0015;fa\u0006!1m\u001c8g\u0007\u0001\u0001\"\u0001K\u0015\u000e\u0003AI!A\u000b\t\u0003\u001d-+(-\u001a:oKR,7oQ8oM\u00061A(\u001b8jiz\"\"!\f\u0018\u0011\u0005\t\u0002\u0001\"B\u0013\u0003\u0001\u00049\u0013aB2p]\u001a$\u0015N]\u000b\u0002cA\u0019AD\r\u001b\n\u0005Mj\"AB(qi&|g\u000e\u0005\u00026y9\u0011aG\u000f\t\u0003oui\u0011\u0001\u000f\u0006\u0003s\u0019\na\u0001\u0010:p_Rt\u0014BA\u001e\u001e\u0003\u0019\u0001&/\u001a3fM&\u0011QH\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005mj\u0012\u0001C2p]\u001a$\u0015N\u001d\u0011\u0002\u001f\u0015D\u0018n\u001d;j]\u001e\u001cuN\u001c4NCB\f\u0001#\u001a=jgRLgnZ\"p]\u001al\u0015\r\u001d\u0011\u0002\u0013\r|gN\u001a$jY\u0016\u001cX#\u0001#\u0011\u0007\u0015SUJ\u0004\u0002G\u0011:\u0011qgR\u0005\u0002=%\u0011\u0011*H\u0001\ba\u0006\u001c7.Y4f\u0013\tYEJA\u0002TKFT!!S\u000f\u0011\u00059\u001bV\"A(\u000b\u0005A\u000b\u0016AA5p\u0015\u0005\u0011\u0016\u0001\u00026bm\u0006L!\u0001V(\u0003\t\u0019KG.Z\u0001\u0011]\u0016<8i\u001c8gS\u001el\u0015\r\u001d(b[\u0016,\u0012\u0001N\u0001\u000eQ\u0006\u001c\b*\u00193p_B\u001cuN\u001c4\u0016\u0003e\u0003\"\u0001\b.\n\u0005mk\"a\u0002\"p_2,\u0017M\\\u0001\rG>tg-[4ve\u0016\u0004v\u000e\u001a\u000b\u0003=\u0006\u0004\"\u0001K0\n\u0005\u0001\u0004\"\u0001C*qCJ\\\u0007k\u001c3\t\u000b\tT\u0001\u0019\u00010\u0002\u0011=\u0014\u0018nZ5oC2\f\u0001eZ3u\u0003\u0012$\u0017\u000e^5p]\u0006d\u0007k\u001c3TsN$X-\u001c)s_B,'\u000f^5fgR\tQ\r\u0005\u00036MR\"\u0014BA4?\u0005\ri\u0015\r]\u0001!O\u0016$\u0018\t\u001a3ji&|g.\u00197Lk\n,'O\\3uKN\u0014Vm]8ve\u000e,7\u000fF\u0001k!\r)%j\u001b\t\u0003YZl\u0011!\u001c\u0006\u0003]>\fQ!\\8eK2T!\u0001]9\u0002\u0007\u0005\u0004\u0018N\u0003\u0002sg\u0006Q1.\u001e2fe:,G/Z:\u000b\u0005Q,\u0018a\u00024bEJL7\r\u000f\u0006\u0002!&\u0011q/\u001c\u0002\f\u0011\u0006\u001cX*\u001a;bI\u0006$\u0018\r"
)
public class HadoopConfDriverFeatureStep implements KubernetesFeatureConfigStep {
   private Seq org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confFiles;
   private final KubernetesConf conf;
   private final Option org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir;
   private final Option org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$existingConfMap;
   private volatile boolean bitmap$0;

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public Option org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir() {
      return this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir;
   }

   public Option org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$existingConfMap() {
      return this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$existingConfMap;
   }

   private Seq confFiles$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            File dir = new File((String)this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir().get());
            this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confFiles = (Seq)(dir.isDirectory() ? .MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])dir.listFiles()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$confFiles$1(x$1)))).toImmutableArraySeq() : scala.collection.immutable.Nil..MODULE$);
            this.bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confFiles;
   }

   public Seq org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confFiles() {
      return !this.bitmap$0 ? this.confFiles$lzycompute() : this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confFiles;
   }

   public String org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$newConfigMapName() {
      return this.conf.resourceNamePrefix() + "-hadoop-config";
   }

   public boolean org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$hasHadoopConf() {
      return this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir().isDefined() || this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$existingConfMap().isDefined();
   }

   public SparkPod configurePod(final SparkPod original) {
      return original.transform(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final HadoopConfDriverFeatureStep $outer;

         public final Object applyOrElse(final SparkPod x1, final Function1 default) {
            if (this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$hasHadoopConf()) {
               Volume var10000;
               if (this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir().isDefined()) {
                  Seq keyPaths = (Seq)this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confFiles().map((file) -> ((KeyToPathBuilder)(new KeyToPathBuilder()).withKey(file.getName()).withPath(file.getName())).build());
                  var10000 = ((VolumeBuilder)((VolumeFluent.ConfigMapNested)(new VolumeBuilder()).withName(Constants$.MODULE$.HADOOP_CONF_VOLUME()).withNewConfigMap().withName(this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$newConfigMapName()).withItems(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(keyPaths).asJava())).endConfigMap()).build();
               } else {
                  var10000 = ((VolumeBuilder)((VolumeFluent.ConfigMapNested)(new VolumeBuilder()).withName(Constants$.MODULE$.HADOOP_CONF_VOLUME()).withNewConfigMap().withName((String)this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$existingConfMap().get())).endConfigMap()).build();
               }

               Volume confVolume = var10000;
               Pod podWithConf = ((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(x1.pod())).editSpec().addNewVolumeLike(confVolume).endVolume()).endSpec()).build();
               Container containerWithMount = ((ContainerBuilder)((ContainerFluent.EnvNested)((ContainerFluent)((ContainerFluent.VolumeMountsNested)(new ContainerBuilder(x1.container())).addNewVolumeMount().withName(Constants$.MODULE$.HADOOP_CONF_VOLUME()).withMountPath(Constants$.MODULE$.HADOOP_CONF_DIR_PATH())).endVolumeMount()).addNewEnv().withName(Constants$.MODULE$.ENV_HADOOP_CONF_DIR()).withValue(Constants$.MODULE$.HADOOP_CONF_DIR_PATH())).endEnv()).build();
               return new SparkPod(podWithConf, containerWithMount);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final SparkPod x1) {
            return this.$outer.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$hasHadoopConf();
         }

         public {
            if (HadoopConfDriverFeatureStep.this == null) {
               throw null;
            } else {
               this.$outer = HadoopConfDriverFeatureStep.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   public Map getAdditionalPodSystemProperties() {
      return this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$hasHadoopConf() ? (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.HADOOP_CONFIG_MAP_NAME()), this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$existingConfMap().getOrElse(() -> this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$newConfigMapName()))}))) : scala.Predef..MODULE$.Map().empty();
   }

   public Seq getAdditionalKubernetesResources() {
      if (this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir().isDefined()) {
         java.util.Map fileMap = scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(((IterableOnceOps)this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confFiles().map((file) -> new Tuple2(file.getName(), Files.asCharSource(file, StandardCharsets.UTF_8).read()))).toMap(scala..less.colon.less..MODULE$.refl())).asJava();
         return new scala.collection.immutable..colon.colon(((ConfigMapBuilder)((ConfigMapFluent)((ConfigMapFluent.MetadataNested)(new ConfigMapBuilder()).withNewMetadata().withName(this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$newConfigMapName())).endMetadata()).withImmutable(scala.Predef..MODULE$.boolean2Boolean(true)).addToData(fileMap)).build(), scala.collection.immutable.Nil..MODULE$);
      } else {
         return scala.collection.immutable.Nil..MODULE$;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$confFiles$1(final File x$1) {
      return x$1.isFile();
   }

   public HadoopConfDriverFeatureStep(final KubernetesConf conf) {
      this.conf = conf;
      KubernetesFeatureConfigStep.$init$(this);
      this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir = scala.Option..MODULE$.apply(conf.sparkConf().getenv(Constants$.MODULE$.ENV_HADOOP_CONF_DIR()));
      this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$existingConfMap = (Option)conf.get((ConfigEntry)Config$.MODULE$.KUBERNETES_HADOOP_CONF_CONFIG_MAP());
      KubernetesUtils$.MODULE$.requireNandDefined(this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$confDir(), this.org$apache$spark$deploy$k8s$features$HadoopConfDriverFeatureStep$$existingConfMap(), "Do not specify both the `HADOOP_CONF_DIR` in your ENV and the ConfigMap as the creation of an additional ConfigMap, when one is already specified is extraneous");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
