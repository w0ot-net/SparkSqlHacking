package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.EmptyDirVolumeSource;
import io.fabric8.kubernetes.api.model.HostPathVolumeSource;
import io.fabric8.kubernetes.api.model.NFSVolumeSource;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimBuilder;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimFluent;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimVolumeSource;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.api.model.VolumeBuilder;
import io.fabric8.kubernetes.api.model.VolumeMount;
import io.fabric8.kubernetes.api.model.VolumeMountBuilder;
import io.fabric8.kubernetes.api.model.VolumeResourceRequirementsBuilder;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.KubernetesEmptyDirVolumeConf;
import org.apache.spark.deploy.k8s.KubernetesExecutorConf;
import org.apache.spark.deploy.k8s.KubernetesHostPathVolumeConf;
import org.apache.spark.deploy.k8s.KubernetesNFSVolumeConf;
import org.apache.spark.deploy.k8s.KubernetesPVCVolumeConf;
import org.apache.spark.deploy.k8s.KubernetesVolumeSpec;
import org.apache.spark.deploy.k8s.KubernetesVolumeSpecificConf;
import org.apache.spark.deploy.k8s.SparkPod;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b!B\u000b\u0017\u0001q\u0011\u0003\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\t\u000bM\u0002A\u0011\u0001\u001b\t\u000f]\u0002!\u0019!C\u0001q!1q\n\u0001Q\u0001\neBq\u0001\u0015\u0001C\u0002\u0013\u0005\u0011\u000b\u0003\u0004[\u0001\u0001\u0006IA\u0015\u0005\u00067\u0002!\t\u0005\u0018\u0005\u0006E\u0002!Ia\u0019\u0005\u0007\u007f\u0002!\t%!\u0001\b\u0011\u0005%a\u0003#\u0001\u001d\u0003\u00171q!\u0006\f\t\u0002q\ti\u0001\u0003\u00044\u0017\u0011\u0005\u0011q\u0002\u0005\t\u0003#Y!\u0019!C\u0001#\"9\u00111C\u0006!\u0002\u0013\u0011\u0006\u0002CA\u000b\u0017\t\u0007I\u0011A)\t\u000f\u0005]1\u0002)A\u0005%\"A\u0011\u0011D\u0006C\u0002\u0013\u0005\u0011\u000bC\u0004\u0002\u001c-\u0001\u000b\u0011\u0002*\t\u0011\u0005u1B1A\u0005\u0002ECq!a\b\fA\u0003%!KA\fN_VtGOV8mk6,7OR3biV\u0014Xm\u0015;fa*\u0011q\u0003G\u0001\tM\u0016\fG/\u001e:fg*\u0011\u0011DG\u0001\u0004Wb\u001a(BA\u000e\u001d\u0003\u0019!W\r\u001d7ps*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014xmE\u0002\u0001G%\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0007C\u0001\u0016,\u001b\u00051\u0012B\u0001\u0017\u0017\u0005mYUOY3s]\u0016$Xm\u001d$fCR,(/Z\"p]\u001aLwm\u0015;fa\u0006!1m\u001c8g\u0007\u0001\u0001\"\u0001M\u0019\u000e\u0003aI!A\r\r\u0003\u001d-+(-\u001a:oKR,7oQ8oM\u00061A(\u001b8jiz\"\"!\u000e\u001c\u0011\u0005)\u0002\u0001\"B\u0017\u0003\u0001\u0004y\u0013aE1eI&$\u0018n\u001c8bYJ+7o\\;sG\u0016\u001cX#A\u001d\u0011\u0007iz\u0014)D\u0001<\u0015\taT(A\u0004nkR\f'\r\\3\u000b\u0005y*\u0013AC2pY2,7\r^5p]&\u0011\u0001i\u000f\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000f\u0005\u0002C\u001b6\t1I\u0003\u0002E\u000b\u0006)Qn\u001c3fY*\u0011aiR\u0001\u0004CBL'B\u0001%J\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u0003\u0015.\u000bqAZ1ce&\u001c\u0007HC\u0001M\u0003\tIw.\u0003\u0002O\u0007\nY\u0001*Y:NKR\fG-\u0019;b\u0003Q\tG\rZ5uS>t\u0017\r\u001c*fg>,(oY3tA\u0005Q\u0011mY2fgNlu\u000eZ3\u0016\u0003I\u0003\"a\u0015-\u000e\u0003QS!!\u0016,\u0002\t1\fgn\u001a\u0006\u0002/\u0006!!.\u0019<b\u0013\tIFK\u0001\u0004TiJLgnZ\u0001\fC\u000e\u001cWm]:N_\u0012,\u0007%\u0001\u0007d_:4\u0017nZ;sKB{G\r\u0006\u0002^AB\u0011\u0001GX\u0005\u0003?b\u0011\u0001b\u00159be.\u0004v\u000e\u001a\u0005\u0006C\u001e\u0001\r!X\u0001\u0004a>$\u0017\u0001E2p]N$(/^2u->dW/\\3t)\t!\u0017\u0010E\u0002f[Bt!AZ6\u000f\u0005\u001dTW\"\u00015\u000b\u0005%t\u0013A\u0002\u001fs_>$h(C\u0001'\u0013\taW%A\u0004qC\u000e\\\u0017mZ3\n\u00059|'\u0001C%uKJ\f'\r\\3\u000b\u00051,\u0003\u0003\u0002\u0013rgZL!A]\u0013\u0003\rQ+\b\u000f\\33!\t\u0011E/\u0003\u0002v\u0007\nYak\u001c7v[\u0016lu.\u001e8u!\t\u0011u/\u0003\u0002y\u0007\n1ak\u001c7v[\u0016DQA\u001f\u0005A\u0002m\f1B^8mk6,7\u000b]3dgB\u0019Q-\u001c?\u0011\u0005Aj\u0018B\u0001@\u0019\u0005QYUOY3s]\u0016$Xm\u001d,pYVlWm\u00159fG\u0006\u0001s-\u001a;BI\u0012LG/[8oC2\\UOY3s]\u0016$Xm\u001d*fg>,(oY3t)\t\t\u0019\u0001\u0005\u0003f\u0003\u000b\t\u0015bAA\u0004_\n\u00191+Z9\u0002/5{WO\u001c;W_2,X.Z:GK\u0006$XO]3Ti\u0016\u0004\bC\u0001\u0016\f'\tY1\u0005\u0006\u0002\u0002\f\u0005i\u0001KV\"`\u001f:{F)R'B\u001d\u0012\u000ba\u0002\u0015,D?>su\fR#N\u0003:#\u0005%A\u0002Q-\u000e\u000bA\u0001\u0015,DA\u0005Y\u0001KV\"`!>\u001bFKR%Y\u00031\u0001fkQ0Q\u001fN#f)\u0013-!\u0003=\u0001fkQ0B\u0007\u000e+5kU0N\u001f\u0012+\u0015\u0001\u0005)W\u0007~\u000b5iQ#T'~ku\nR#!\u0001"
)
public class MountVolumesFeatureStep implements KubernetesFeatureConfigStep {
   private final KubernetesConf conf;
   private final ArrayBuffer additionalResources;
   private final String accessMode;

   public static String PVC_ACCESS_MODE() {
      return MountVolumesFeatureStep$.MODULE$.PVC_ACCESS_MODE();
   }

   public static String PVC_POSTFIX() {
      return MountVolumesFeatureStep$.MODULE$.PVC_POSTFIX();
   }

   public static String PVC() {
      return MountVolumesFeatureStep$.MODULE$.PVC();
   }

   public static String PVC_ON_DEMAND() {
      return MountVolumesFeatureStep$.MODULE$.PVC_ON_DEMAND();
   }

   public Map getAdditionalPodSystemProperties() {
      return KubernetesFeatureConfigStep.getAdditionalPodSystemProperties$(this);
   }

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public ArrayBuffer additionalResources() {
      return this.additionalResources;
   }

   public String accessMode() {
      return this.accessMode;
   }

   public SparkPod configurePod(final SparkPod pod) {
      Tuple2 var4 = this.constructVolumes(this.conf.volumes()).unzip(.MODULE$.$conforms());
      if (var4 != null) {
         Iterable volumeMounts = (Iterable)var4._1();
         Iterable volumes = (Iterable)var4._2();
         Tuple2 var3 = new Tuple2(volumeMounts, volumes);
         Iterable volumeMounts = (Iterable)var3._1();
         Iterable volumes = (Iterable)var3._2();
         Pod podWithVolumes = ((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(pod.pod())).editSpec().addToVolumes((Volume[])volumes.toSeq().toArray(scala.reflect.ClassTag..MODULE$.apply(Volume.class)))).endSpec()).build();
         Container containerWithVolumeMounts = ((ContainerBuilder)(new ContainerBuilder(pod.container())).addToVolumeMounts((VolumeMount[])volumeMounts.toSeq().toArray(scala.reflect.ClassTag..MODULE$.apply(VolumeMount.class)))).build();
         return new SparkPod(podWithVolumes, containerWithVolumeMounts);
      } else {
         throw new MatchError(var4);
      }
   }

   private Iterable constructVolumes(final Iterable volumeSpecs) {
      scala.collection.immutable.Iterable duplicateMountPaths = (scala.collection.immutable.Iterable)((IterableOnceOps)volumeSpecs.map((x$2) -> x$2.mountPath())).toSeq().groupBy((x) -> (String).MODULE$.identity(x)).collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            if (x1 != null) {
               String x = (String)x1._1();
               Seq ys = (Seq)x1._2();
               if (ys.length() > 1) {
                  return "'" + x + "'";
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            if (x1 != null) {
               Seq ys = (Seq)x1._2();
               if (ys.length() > 1) {
                  return true;
               }
            }

            return false;
         }
      });
      .MODULE$.require(duplicateMountPaths.isEmpty(), () -> "Found duplicated mountPath: " + duplicateMountPaths.mkString(", "));
      return (Iterable)((IterableOps)volumeSpecs.zipWithIndex()).map((x0$1) -> {
         if (x0$1 == null) {
            throw new MatchError(x0$1);
         } else {
            KubernetesVolumeSpec spec = (KubernetesVolumeSpec)x0$1._1();
            int i = x0$1._2$mcI$sp();
            VolumeMount volumeMount = ((VolumeMountBuilder)(new VolumeMountBuilder()).withMountPath(spec.mountPath()).withReadOnly(.MODULE$.boolean2Boolean(spec.mountReadOnly())).withSubPath(spec.mountSubPath()).withSubPathExpr(spec.mountSubPathExpr()).withName(spec.volumeName())).build();
            KubernetesVolumeSpecificConf var12 = spec.volumeConf();
            VolumeBuilder var10000;
            if (var12 instanceof KubernetesHostPathVolumeConf) {
               KubernetesHostPathVolumeConf var13 = (KubernetesHostPathVolumeConf)var12;
               String hostPath = var13.hostPath();
               String volumeType = var13.volumeType();
               var10000 = (VolumeBuilder)(new VolumeBuilder()).withHostPath(new HostPathVolumeSource(hostPath, volumeType));
            } else if (var12 instanceof KubernetesPVCVolumeConf) {
               KubernetesPVCVolumeConf var16 = (KubernetesPVCVolumeConf)var12;
               String claimNameTemplate = var16.claimName();
               Option storageClass = var16.storageClass();
               Option size = var16.size();
               Option labels = var16.labels();
               Option annotations = var16.annotations();
               KubernetesConf var23 = this.conf;
               String var41;
               if (var23 instanceof KubernetesExecutorConf) {
                  KubernetesExecutorConf var24 = (KubernetesExecutorConf)var23;
                  String var10001 = MountVolumesFeatureStep$.MODULE$.PVC_ON_DEMAND();
                  String var10002 = this.conf.resourceNamePrefix();
                  var41 = claimNameTemplate.replaceAll(var10001, var10002 + "-exec-" + var24.executorId() + MountVolumesFeatureStep$.MODULE$.PVC_POSTFIX() + "-" + i).replaceAll(Constants$.MODULE$.ENV_EXECUTOR_ID(), var24.executorId());
               } else {
                  String var45 = MountVolumesFeatureStep$.MODULE$.PVC_ON_DEMAND();
                  String var46 = this.conf.resourceNamePrefix();
                  var41 = claimNameTemplate.replaceAll(var45, var46 + "-driver" + MountVolumesFeatureStep$.MODULE$.PVC_POSTFIX() + "-" + i);
               }

               String claimName = var41;
               if (storageClass.isDefined() && size.isDefined()) {
                  Map defaultVolumeLabels = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_APP_ID_LABEL()), this.conf.appId())})));
                  java.util.Map var43;
                  if (labels instanceof Some) {
                     Some var28 = (Some)labels;
                     Map customLabelsMap = (Map)var28.value();
                     var43 = scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map)customLabelsMap.$plus$plus(defaultVolumeLabels)).asJava();
                  } else {
                     if (!scala.None..MODULE$.equals(labels)) {
                        throw new MatchError(labels);
                     }

                     var43 = scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(defaultVolumeLabels).asJava();
                  }

                  java.util.Map volumeLabels = var43;
                  if (annotations instanceof Some) {
                     Some var32 = (Some)annotations;
                     Map value = (Map)var32.value();
                     var43 = scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(value).asJava();
                  } else {
                     if (!scala.None..MODULE$.equals(annotations)) {
                        throw new MatchError(annotations);
                     }

                     var43 = scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map).MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$)).asJava();
                  }

                  java.util.Map volumeAnnotations = var43;
                  this.additionalResources().append(((PersistentVolumeClaimBuilder)((PersistentVolumeClaimFluent.SpecNested)((PersistentVolumeClaimFluent)((PersistentVolumeClaimFluent.MetadataNested)(new PersistentVolumeClaimBuilder()).withKind(MountVolumesFeatureStep$.MODULE$.PVC()).withApiVersion("v1").withNewMetadata().withName(claimName).addToLabels(volumeLabels).addToAnnotations(volumeAnnotations)).endMetadata()).withNewSpec().withStorageClassName((String)storageClass.get()).withAccessModes(new String[]{this.accessMode()}).withResources(((VolumeResourceRequirementsBuilder)(new VolumeResourceRequirementsBuilder()).withRequests(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("storage"), new Quantity((String)size.get()))})))).asJava())).build())).endSpec()).build());
               } else {
                  BoxedUnit var42 = BoxedUnit.UNIT;
               }

               var10000 = (VolumeBuilder)(new VolumeBuilder()).withPersistentVolumeClaim(new PersistentVolumeClaimVolumeSource(claimName, .MODULE$.boolean2Boolean(spec.mountReadOnly())));
            } else if (var12 instanceof KubernetesEmptyDirVolumeConf) {
               KubernetesEmptyDirVolumeConf var34 = (KubernetesEmptyDirVolumeConf)var12;
               Option medium = var34.medium();
               Option sizeLimit = var34.sizeLimit();
               var10000 = (VolumeBuilder)(new VolumeBuilder()).withEmptyDir(new EmptyDirVolumeSource((String)medium.getOrElse(() -> ""), (Quantity)sizeLimit.map((x$3) -> new Quantity(x$3)).orNull(scala..less.colon.less..MODULE$.refl())));
            } else {
               if (!(var12 instanceof KubernetesNFSVolumeConf)) {
                  throw new MatchError(var12);
               }

               KubernetesNFSVolumeConf var37 = (KubernetesNFSVolumeConf)var12;
               String path = var37.path();
               String server = var37.server();
               var10000 = (VolumeBuilder)(new VolumeBuilder()).withNfs(new NFSVolumeSource(path, (Boolean)null, server));
            }

            VolumeBuilder volumeBuilder = var10000;
            Volume volume = ((VolumeBuilder)volumeBuilder.withName(spec.volumeName())).build();
            return new Tuple2(volumeMount, volume);
         }
      });
   }

   public Seq getAdditionalKubernetesResources() {
      return this.additionalResources().toSeq();
   }

   public MountVolumesFeatureStep(final KubernetesConf conf) {
      this.conf = conf;
      KubernetesFeatureConfigStep.$init$(this);
      this.additionalResources = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
      this.accessMode = BoxesRunTime.unboxToBoolean(conf.get(Config$.MODULE$.KUBERNETES_USE_LEGACY_PVC_ACCESS_MODE())) ? "ReadWriteOnce" : MountVolumesFeatureStep$.MODULE$.PVC_ACCESS_MODE();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
