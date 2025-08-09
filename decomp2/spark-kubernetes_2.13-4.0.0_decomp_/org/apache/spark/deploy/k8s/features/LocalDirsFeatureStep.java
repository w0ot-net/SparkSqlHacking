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
import io.fabric8.kubernetes.api.model.VolumeMount;
import io.fabric8.kubernetes.api.model.VolumeMountBuilder;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.util.Utils.;
import scala.MatchError;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]3Qa\u0003\u0007\u0001%aA\u0001b\t\u0001\u0003\u0002\u0003\u0006I!\n\u0005\tS\u0001\u0011\t\u0011)A\u0005U!)Q\u0007\u0001C\u0001m!9!\b\u0001b\u0001\n\u0013Y\u0004BB \u0001A\u0003%A\bC\u0003A\u0001\u0011\u0005\u0013i\u0002\u0005H\u0019\u0005\u0005\t\u0012\u0001\nI\r!YA\"!A\t\u0002II\u0005\"B\u001b\t\t\u0003Q\u0005bB&\t#\u0003%\t\u0001\u0014\u0002\u0015\u0019>\u001c\u0017\r\u001c#jeN4U-\u0019;ve\u0016\u001cF/\u001a9\u000b\u00055q\u0011\u0001\u00034fCR,(/Z:\u000b\u0005=\u0001\u0012aA69g*\u0011\u0011CE\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005M!\u0012!B:qCJ\\'BA\u000b\u0017\u0003\u0019\t\u0007/Y2iK*\tq#A\u0002pe\u001e\u001c2\u0001A\r !\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fMB\u0011\u0001%I\u0007\u0002\u0019%\u0011!\u0005\u0004\u0002\u001c\u0017V\u0014WM\u001d8fi\u0016\u001ch)Z1ukJ,7i\u001c8gS\u001e\u001cF/\u001a9\u0002\t\r|gNZ\u0002\u0001!\t1s%D\u0001\u000f\u0013\tAcB\u0001\bLk\n,'O\\3uKN\u001cuN\u001c4\u0002\u001f\u0011,g-Y;mi2{7-\u00197ESJ\u0004\"a\u000b\u001a\u000f\u00051\u0002\u0004CA\u0017\u001c\u001b\u0005q#BA\u0018%\u0003\u0019a$o\\8u}%\u0011\u0011gG\u0001\u0007!J,G-\u001a4\n\u0005M\"$AB*ue&twM\u0003\u000227\u00051A(\u001b8jiz\"2a\u000e\u001d:!\t\u0001\u0003\u0001C\u0003$\u0007\u0001\u0007Q\u0005C\u0004*\u0007A\u0005\t\u0019\u0001\u0016\u0002!U\u001cX\rT8dC2$\u0015N\u001d+na\u001a\u001bX#\u0001\u001f\u0011\u0005ii\u0014B\u0001 \u001c\u0005\u001d\u0011un\u001c7fC:\f\u0011#^:f\u0019>\u001c\u0017\r\u001c#jeRk\u0007OR:!\u00031\u0019wN\u001c4jOV\u0014X\rU8e)\t\u0011U\t\u0005\u0002'\u0007&\u0011AI\u0004\u0002\t'B\f'o\u001b)pI\")aI\u0002a\u0001\u0005\u0006\u0019\u0001o\u001c3\u0002)1{7-\u00197ESJ\u001ch)Z1ukJ,7\u000b^3q!\t\u0001\u0003b\u0005\u0002\t3Q\t\u0001*A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0002\u001b*\u0012!FT\u0016\u0002\u001fB\u0011\u0001+V\u0007\u0002#*\u0011!kU\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001V\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002W#\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3"
)
public class LocalDirsFeatureStep implements KubernetesFeatureConfigStep {
   private final KubernetesConf conf;
   private final String defaultLocalDir;
   private final boolean useLocalDirTmpFs;

   public static String $lessinit$greater$default$2() {
      return LocalDirsFeatureStep$.MODULE$.$lessinit$greater$default$2();
   }

   public Map getAdditionalPodSystemProperties() {
      return KubernetesFeatureConfigStep.getAdditionalPodSystemProperties$(this);
   }

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public Seq getAdditionalKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalKubernetesResources$(this);
   }

   private boolean useLocalDirTmpFs() {
      return this.useLocalDirTmpFs;
   }

   public SparkPod configurePod(final SparkPod pod) {
      Seq localDirs = .MODULE$.randomize((IterableOnce)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(pod.container().getVolumeMounts()).asScala().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$configurePod$1(x$1)))).map((x$2) -> x$2.getMountPath()), scala.reflect.ClassTag..MODULE$.apply(String.class));
      Seq localDirVolumes = (Seq)scala.collection.immutable.Nil..MODULE$;
      Seq localDirVolumeMounts = (Seq)scala.collection.immutable.Nil..MODULE$;
      if (localDirs.isEmpty()) {
         String[] resolvedLocalDirs = ((String)scala.Option..MODULE$.apply(this.conf.sparkConf().getenv("SPARK_LOCAL_DIRS")).orElse(() -> this.conf.getOption("spark.local.dir")).getOrElse(() -> this.defaultLocalDir)).split(",");
         .MODULE$.randomize(scala.Predef..MODULE$.wrapRefArray((Object[])resolvedLocalDirs), scala.reflect.ClassTag..MODULE$.apply(String.class));
         localDirs = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(resolvedLocalDirs).toImmutableArraySeq();
         localDirVolumes = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])resolvedLocalDirs))), (x0$1) -> {
            if (x0$1 != null) {
               int index = x0$1._2$mcI$sp();
               return ((VolumeBuilder)((VolumeFluent.EmptyDirNested)(new VolumeBuilder()).withName("spark-local-dir-" + (index + 1)).withNewEmptyDir().withMedium(this.useLocalDirTmpFs() ? "Memory" : null)).endEmptyDir()).build();
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(Volume.class))).toImmutableArraySeq();
         localDirVolumeMounts = (Seq)((IterableOps)localDirVolumes.zip(scala.Predef..MODULE$.wrapRefArray((Object[])resolvedLocalDirs))).map((x0$2) -> {
            if (x0$2 != null) {
               Volume localDirVolume = (Volume)x0$2._1();
               String localDirPath = (String)x0$2._2();
               return ((VolumeMountBuilder)(new VolumeMountBuilder()).withName(localDirVolume.getName()).withMountPath(localDirPath)).build();
            } else {
               throw new MatchError(x0$2);
            }
         });
      }

      Pod podWithLocalDirVolumes = ((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(pod.pod())).editSpec().addToVolumes((Volume[])localDirVolumes.toArray(scala.reflect.ClassTag..MODULE$.apply(Volume.class)))).endSpec()).build();
      Container containerWithLocalDirVolumeMounts = ((ContainerBuilder)((ContainerFluent)((ContainerFluent.EnvNested)(new ContainerBuilder(pod.container())).addNewEnv().withName("SPARK_LOCAL_DIRS").withValue(localDirs.mkString(","))).endEnv()).addToVolumeMounts((VolumeMount[])localDirVolumeMounts.toArray(scala.reflect.ClassTag..MODULE$.apply(VolumeMount.class)))).build();
      return new SparkPod(podWithLocalDirVolumes, containerWithLocalDirVolumeMounts);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$configurePod$1(final VolumeMount x$1) {
      return x$1.getName().startsWith("spark-local-dir-");
   }

   public LocalDirsFeatureStep(final KubernetesConf conf, final String defaultLocalDir) {
      this.conf = conf;
      this.defaultLocalDir = defaultLocalDir;
      KubernetesFeatureConfigStep.$init$(this);
      this.useLocalDirTmpFs = BoxesRunTime.unboxToBoolean(conf.get(Config$.MODULE$.KUBERNETES_LOCAL_DIRS_TMPFS()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
