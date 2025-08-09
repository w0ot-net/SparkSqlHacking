package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaim;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimBuilder;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimFluent;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimVolumeSource;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.PodSpecBuilder;
import io.fabric8.kubernetes.api.model.PodTemplateSpec;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import io.fabric8.kubernetes.api.model.apps.StatefulSetBuilder;
import io.fabric8.kubernetes.api.model.apps.StatefulSetFluent;
import io.fabric8.kubernetes.api.model.apps.StatefulSetSpecFluent;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.AnyNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.CreateOrReplaceable;
import io.fabric8.kubernetes.client.dsl.Deletable;
import io.fabric8.kubernetes.client.dsl.Gettable;
import io.fabric8.kubernetes.client.dsl.Nameable;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import io.fabric8.kubernetes.client.dsl.Waitable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf$;
import org.apache.spark.deploy.k8s.KubernetesExecutorConf;
import org.apache.spark.deploy.k8s.KubernetesExecutorSpec;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055f\u0001B\u000f\u001f\u0001-B\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\tw\u0001\u0011\t\u0011)A\u0005y!Aq\b\u0001B\u0001B\u0003%\u0001\t\u0003\u0005D\u0001\t\u0005\t\u0015!\u0003E\u0011!\u0001\u0006A!A!\u0002\u0013\t\u0006\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011B+\t\u000bm\u0003A\u0011\u0001/\t\u000f\u0011\u0004!\u0019!C\tK\"1!\u0010\u0001Q\u0001\n\u0019Dqa\u001f\u0001C\u0002\u0013EA\u0010C\u0004\u0002\u0002\u0001\u0001\u000b\u0011B?\t\u0013\u0005\r\u0001A1A\u0005\u0012\u0005\u0015\u0001\u0002CA\u000f\u0001\u0001\u0006I!a\u0002\t\u0013\u0005}\u0001A1A\u0005\u0012\u0005\u0005\u0002\u0002CA\u0015\u0001\u0001\u0006I!a\t\t\u0013\u0005-\u0002A1A\u0005\u0002\u00055\u0002\u0002CA!\u0001\u0001\u0006I!a\f\t\u0017\u0005\r\u0003\u00011AA\u0002\u0013E\u0011Q\u0001\u0005\f\u0003\u000b\u0002\u0001\u0019!a\u0001\n#\t9\u0005C\u0006\u0002T\u0001\u0001\r\u0011!Q!\n\u0005\u001d\u0001bBA+\u0001\u0011\u0005\u0011q\u000b\u0005\b\u0003O\u0002A\u0011AA5\u0011\u001d\t)\b\u0001C\u0001\u0003oB\u0011\"a!\u0001\u0005\u0004%\t!!\"\t\u0011\u00055\u0005\u0001)A\u0005\u0003\u000fCq!a$\u0001\t#\t\t\nC\u0004\u0002\u001a\u0002!\t\"a'\t\u000f\u0005\u001d\u0006\u0001\"\u0011\u0002*\nA2\u000b^1uK\u001a,HnU3u!>$7/\u00117m_\u000e\fGo\u001c:\u000b\u0005}\u0001\u0013aA69g*\u0011\u0011EI\u0001\bG2,8\u000f^3s\u0015\t\u0019C%A\u0005tG\",G-\u001e7fe*\u0011QEJ\u0001\u0006gB\f'o\u001b\u0006\u0003O!\na!\u00199bG\",'\"A\u0015\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001a\u0003\u0007\u0005\u0002.]5\ta$\u0003\u00020=\t)\u0012IY:ue\u0006\u001cG\u000fU8eg\u0006cGn\\2bi>\u0014\bCA\u00195\u001b\u0005\u0011$BA\u001a%\u0003!Ig\u000e^3s]\u0006d\u0017BA\u001b3\u0005\u001daunZ4j]\u001e\fAaY8oMB\u0011\u0001(O\u0007\u0002I%\u0011!\b\n\u0002\n'B\f'o[\"p]\u001a\faa]3d\u001b\u001e\u0014\bC\u0001\u001d>\u0013\tqDEA\bTK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s\u0003=)\u00070Z2vi>\u0014()^5mI\u0016\u0014\bCA\u0017B\u0013\t\u0011eDA\rLk\n,'O\\3uKN,\u00050Z2vi>\u0014()^5mI\u0016\u0014\u0018\u0001E6vE\u0016\u0014h.\u001a;fg\u000ec\u0017.\u001a8u!\t)e*D\u0001G\u0015\t9\u0005*\u0001\u0004dY&,g\u000e\u001e\u0006\u0003\u0013*\u000b!b[;cKJtW\r^3t\u0015\tYE*A\u0004gC\n\u0014\u0018n\u0019\u001d\u000b\u00035\u000b!![8\n\u0005=3%\u0001E&vE\u0016\u0014h.\u001a;fg\u000ec\u0017.\u001a8u\u00039\u0019h.\u00199tQ>$8o\u0015;pe\u0016\u0004\"!\f*\n\u0005Ms\"AG#yK\u000e,Ho\u001c:Q_\u0012\u001c8K\\1qg\"|Go]*u_J,\u0017!B2m_\u000e\\\u0007C\u0001,Z\u001b\u00059&B\u0001-%\u0003\u0011)H/\u001b7\n\u0005i;&!B\"m_\u000e\\\u0017A\u0002\u001fj]&$h\bF\u0004^=~\u0003\u0017MY2\u0011\u00055\u0002\u0001\"\u0002\u001c\b\u0001\u00049\u0004\"B\u001e\b\u0001\u0004a\u0004\"B \b\u0001\u0004\u0001\u0005\"B\"\b\u0001\u0004!\u0005\"\u0002)\b\u0001\u0004\t\u0006\"\u0002+\b\u0001\u0004)\u0016!\u0006:q\u0013\u0012$vNU3t_V\u00148-\u001a)s_\u001aLG.Z\u000b\u0002MB!qM\u001c9u\u001b\u0005A'BA5k\u0003\u001diW\u000f^1cY\u0016T!a\u001b7\u0002\u0015\r|G\u000e\\3di&|gNC\u0001n\u0003\u0015\u00198-\u00197b\u0013\ty\u0007NA\u0004ICNDW*\u00199\u0011\u0005E\u0014X\"\u00017\n\u0005Md'aA%oiB\u0011Q\u000f_\u0007\u0002m*\u0011q\u000fJ\u0001\te\u0016\u001cx.\u001e:dK&\u0011\u0011P\u001e\u0002\u0010%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK\u00061\"\u000f]%e)>\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0007%A\ree&4XM\u001d)pIJ+\u0017\rZ5oKN\u001cH+[7f_V$X#A?\u0011\u0005Et\u0018BA@m\u0005\u0011auN\\4\u00025\u0011\u0014\u0018N^3s!>$'+Z1eS:,7o\u001d+j[\u0016|W\u000f\u001e\u0011\u0002\u00139\fW.Z:qC\u000e,WCAA\u0004!\u0011\tI!a\u0006\u000f\t\u0005-\u00111\u0003\t\u0004\u0003\u001baWBAA\b\u0015\r\t\tBK\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005UA.\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u00033\tYB\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003+a\u0017A\u00038b[\u0016\u001c\b/Y2fA\u000592.\u001e2fe:,G/Z:Ee&4XM\u001d)pI:\u000bW.Z\u000b\u0003\u0003G\u0001R!]A\u0013\u0003\u000fI1!a\nm\u0005\u0019y\u0005\u000f^5p]\u0006A2.\u001e2fe:,G/Z:Ee&4XM\u001d)pI:\u000bW.\u001a\u0011\u0002\u0013\u0011\u0014\u0018N^3s!>$WCAA\u0018!\u0015\t\u0018QEA\u0019!\u0011\t\u0019$!\u0010\u000e\u0005\u0005U\"\u0002BA\u001c\u0003s\tQ!\\8eK2T1!a\u000fI\u0003\r\t\u0007/[\u0005\u0005\u0003\u007f\t)DA\u0002Q_\u0012\f!\u0002\u001a:jm\u0016\u0014\bk\u001c3!\u0003\u0015\t\u0007\u000f]%e\u0003%\t\u0007\u000f]%e?\u0012*\u0017\u000f\u0006\u0003\u0002J\u0005=\u0003cA9\u0002L%\u0019\u0011Q\n7\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003#\u001a\u0012\u0011!a\u0001\u0003\u000f\t1\u0001\u001f\u00132\u0003\u0019\t\u0007\u000f]%eA\u0005)1\u000f^1siR1\u0011\u0011JA-\u0003;Bq!a\u0017\u0016\u0001\u0004\t9!A\u0007baBd\u0017nY1uS>t\u0017\n\u001a\u0005\b\u0003?*\u0002\u0019AA1\u0003A\u00198\r[3ek2,'OQ1dW\u0016tG\rE\u0002.\u0003GJ1!!\u001a\u001f\u0005\u0005ZUOY3s]\u0016$Xm]\"mkN$XM]*dQ\u0016$W\u000f\\3s\u0005\u0006\u001c7.\u001a8e\u0003e\u0019X\r\u001e+pi\u0006dW\t\u001f9fGR,G-\u0012=fGV$xN]:\u0015\t\u0005%\u00131\u000e\u0005\b\u0003[2\u0002\u0019AA8\u0003m\u0011Xm]8ve\u000e,\u0007K]8gS2,Gk\u001c+pi\u0006dW\t_3dgB1\u0011\u0011BA9iBLA!a\u001d\u0002\u001c\t\u0019Q*\u00199\u0002\u0013%\u001cH)\u001a7fi\u0016$G\u0003BA=\u0003\u007f\u00022!]A>\u0013\r\ti\b\u001c\u0002\b\u0005>|G.Z1o\u0011\u001d\t\ti\u0006a\u0001\u0003\u000f\t!\"\u001a=fGV$xN]%e\u0003-\u0019X\r^:De\u0016\fG/\u001a3\u0016\u0005\u0005\u001d\u0005\u0003B4\u0002\nBL1!a#i\u0005\u001dA\u0015m\u001d5TKR\fAb]3ug\u000e\u0013X-\u0019;fI\u0002\nqa]3u\u001d\u0006lW\r\u0006\u0004\u0002\b\u0005M\u0015Q\u0013\u0005\b\u00037R\u0002\u0019AA\u0004\u0011\u0019\t9J\u0007a\u0001a\u0006!!\u000f]5e\u0003q\u0019X\r\u001e+be\u001e,G/\u0012=fGV$xN]:SKBd\u0017nY1tKR$\u0002\"!\u0013\u0002\u001e\u0006\u0005\u00161\u0015\u0005\u0007\u0003?[\u0002\u0019\u00019\u0002\u0011\u0015D\b/Z2uK\u0012Dq!a\u0017\u001c\u0001\u0004\t9\u0001\u0003\u0004\u0002&n\u0001\r\u0001]\u0001\u0012e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#\u0017\u0001B:u_B$B!!\u0013\u0002,\"9\u00111\f\u000fA\u0002\u0005\u001d\u0001"
)
public class StatefulSetPodsAllocator extends AbstractPodsAllocator implements Logging {
   private final SparkConf conf;
   private final SecurityManager secMgr;
   private final KubernetesExecutorBuilder executorBuilder;
   private final KubernetesClient kubernetesClient;
   private final HashMap rpIdToResourceProfile;
   private final long driverPodReadinessTimeout;
   private final String namespace;
   private final Option kubernetesDriverPodName;
   private final Option driverPod;
   private String appId;
   private final HashSet setsCreated;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public HashMap rpIdToResourceProfile() {
      return this.rpIdToResourceProfile;
   }

   public long driverPodReadinessTimeout() {
      return this.driverPodReadinessTimeout;
   }

   public String namespace() {
      return this.namespace;
   }

   public Option kubernetesDriverPodName() {
      return this.kubernetesDriverPodName;
   }

   public Option driverPod() {
      return this.driverPod;
   }

   public String appId() {
      return this.appId;
   }

   public void appId_$eq(final String x$1) {
      this.appId = x$1;
   }

   public void start(final String applicationId, final KubernetesClusterSchedulerBackend schedulerBackend) {
      this.appId_$eq(applicationId);
      this.driverPod().foreach((pod) -> {
         $anonfun$start$1(this, pod);
         return BoxedUnit.UNIT;
      });
   }

   public void setTotalExpectedExecutors(final scala.collection.immutable.Map resourceProfileToTotalExecs) {
      if (this.appId() == null) {
         throw new SparkException("setTotalExpectedExecutors called before start of allocator.");
      } else {
         resourceProfileToTotalExecs.foreach((x0$1) -> {
            $anonfun$setTotalExpectedExecutors$1(this, x0$1);
            return BoxedUnit.UNIT;
         });
      }
   }

   public boolean isDeleted(final String executorId) {
      return false;
   }

   public HashSet setsCreated() {
      return this.setsCreated;
   }

   public String setName(final String applicationId, final int rpid) {
      return "spark-s-" + applicationId + "-" + rpid;
   }

   public void setTargetExecutorsReplicaset(final int expected, final String applicationId, final int resourceProfileId) {
      if (this.setsCreated().contains(BoxesRunTime.boxToInteger(resourceProfileId))) {
         RollableScalableResource statefulset = (RollableScalableResource)((Nameable)this.kubernetesClient.apps().statefulSets().inNamespace(this.namespace())).withName(this.setName(applicationId, resourceProfileId));
         statefulset.scale(expected, false);
      } else {
         KubernetesExecutorConf executorConf = KubernetesConf$.MODULE$.createExecutorConf(this.conf, "EXECID", applicationId, this.driverPod(), resourceProfileId);
         KubernetesExecutorSpec resolvedExecutorSpec = this.executorBuilder.buildFromFeatures(executorConf, this.secMgr, this.kubernetesClient, (ResourceProfile)this.rpIdToResourceProfile().apply(BoxesRunTime.boxToInteger(resourceProfileId)));
         SparkPod executorPod = resolvedExecutorSpec.pod();
         PodSpec var10 = executorPod.pod().getSpec();
         PodSpecBuilder podSpecBuilder = var10 == null ? new PodSpecBuilder() : new PodSpecBuilder(var10);
         PodSpec podWithAttachedContainer = ((PodSpecBuilder)podSpecBuilder.addToContainers(new Container[]{executorPod.container()})).build();
         ObjectMeta meta = executorPod.pod().getMetadata();
         Seq resources = resolvedExecutorSpec.executorKubernetesResources();
         Seq dynamicVolumeClaims = (Seq)((IterableOps)resources.filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$setTargetExecutorsReplicaset$1(x$2)))).map((x$3) -> (PersistentVolumeClaim)x$3);
         Set dynamicVolumeClaimNames = ((IterableOnceOps)dynamicVolumeClaims.map((x$4) -> x$4.getMetadata().getName())).toSet();
         Buffer podVolumes = .MODULE$.ListHasAsScala(podWithAttachedContainer.getVolumes()).asScala();
         Buffer staticVolumes = (Buffer)podVolumes.filter((v) -> BoxesRunTime.boxToBoolean($anonfun$setTargetExecutorsReplicaset$4(dynamicVolumeClaimNames, v)));
         scala.collection.immutable.Map dynamicClaimToVolumeName = ((IterableOnceOps)((IterableOps)podVolumes.filter((v) -> BoxesRunTime.boxToBoolean($anonfun$setTargetExecutorsReplicaset$5(dynamicVolumeClaimNames, v)))).map((v) -> new Tuple2(v.getPersistentVolumeClaim().getClaimName(), v.getName()))).toMap(scala..less.colon.less..MODULE$.refl());
         podWithAttachedContainer.setVolumes(.MODULE$.BufferHasAsJava(staticVolumes).asJava());
         Seq newNamedVolumes = (Seq)((IterableOps)dynamicVolumeClaims.zipWithIndex()).map((x0$1) -> {
            if (x0$1 != null) {
               PersistentVolumeClaim v = (PersistentVolumeClaim)x0$1._1();
               return ((PersistentVolumeClaimBuilder)((PersistentVolumeClaimFluent.MetadataNested)(new PersistentVolumeClaimBuilder(v)).editMetadata().withName((String)dynamicClaimToVolumeName.get(v.getMetadata().getName()).get())).endMetadata()).build();
            } else {
               throw new MatchError(x0$1);
            }
         });
         PodTemplateSpec podTemplateSpec = new PodTemplateSpec(meta, podWithAttachedContainer);
         StatefulSet statefulSet = ((StatefulSetBuilder)((StatefulSetFluent.SpecNested)((StatefulSetSpecFluent)((StatefulSetSpecFluent.SelectorNested)((StatefulSetFluent)((StatefulSetFluent.MetadataNested)(new StatefulSetBuilder()).withNewMetadata().withName(this.setName(applicationId, resourceProfileId)).withNamespace(this.namespace())).endMetadata()).withNewSpec().withPodManagementPolicy("Parallel").withReplicas(scala.Predef..MODULE$.int2Integer(expected)).withNewSelector().addToMatchLabels(Constants$.MODULE$.SPARK_APP_ID_LABEL(), applicationId).addToMatchLabels(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE()).addToMatchLabels(Constants$.MODULE$.SPARK_RESOURCE_PROFILE_ID_LABEL(), Integer.toString(resourceProfileId))).endSelector()).withTemplate(podTemplateSpec).addAllToVolumeClaimTemplates(.MODULE$.SeqHasAsJava(newNamedVolumes).asJava())).endSpec()).build();
         KubernetesUtils$.MODULE$.addOwnerReference((Pod)this.driverPod().get(), new scala.collection.immutable..colon.colon(statefulSet, scala.collection.immutable.Nil..MODULE$));
         ((CreateOrReplaceable)((AnyNamespaceOperation)this.kubernetesClient.apps().statefulSets().inNamespace(this.namespace())).resource(statefulSet)).create();
         this.setsCreated().$plus$eq(BoxesRunTime.boxToInteger(resourceProfileId));
      }
   }

   public void stop(final String applicationId) {
      this.setsCreated().foreach((JFunction1.mcVI.sp)(rpid) -> org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((Deletable)((Nameable)this.kubernetesClient.apps().statefulSets().inNamespace(this.namespace())).withName(this.setName(applicationId, rpid))).delete()));
   }

   // $FF: synthetic method
   public static final void $anonfun$start$1(final StatefulSetPodsAllocator $this, final Pod pod) {
      org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> ((Waitable)((Nameable)$this.kubernetesClient.pods().inNamespace($this.namespace())).withName(pod.getMetadata().getName())).waitUntilReady($this.driverPodReadinessTimeout(), TimeUnit.SECONDS));
   }

   // $FF: synthetic method
   public static final void $anonfun$setTotalExpectedExecutors$1(final StatefulSetPodsAllocator $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         ResourceProfile rp = (ResourceProfile)x0$1._1();
         int numExecs = x0$1._2$mcI$sp();
         $this.rpIdToResourceProfile().getOrElseUpdate(BoxesRunTime.boxToInteger(rp.id()), () -> rp);
         $this.setTargetExecutorsReplicaset(numExecs, $this.appId(), rp.id());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$setTargetExecutorsReplicaset$1(final HasMetadata x$2) {
      boolean var2;
      label23: {
         String var10000 = x$2.getKind();
         String var1 = "PersistentVolumeClaim";
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$setTargetExecutorsReplicaset$4(final Set dynamicVolumeClaimNames$1, final Volume v) {
      PersistentVolumeClaimVolumeSource pvc = v.getPersistentVolumeClaim();
      if (pvc == null) {
         return true;
      } else {
         return !dynamicVolumeClaimNames$1.contains(pvc.getClaimName());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$setTargetExecutorsReplicaset$5(final Set dynamicVolumeClaimNames$1, final Volume v) {
      PersistentVolumeClaimVolumeSource pvc = v.getPersistentVolumeClaim();
      return pvc == null ? false : dynamicVolumeClaimNames$1.contains(pvc.getClaimName());
   }

   public StatefulSetPodsAllocator(final SparkConf conf, final SecurityManager secMgr, final KubernetesExecutorBuilder executorBuilder, final KubernetesClient kubernetesClient, final ExecutorPodsSnapshotsStore snapshotsStore, final Clock clock) {
      this.conf = conf;
      this.secMgr = secMgr;
      this.executorBuilder = executorBuilder;
      this.kubernetesClient = kubernetesClient;
      Logging.$init$(this);
      this.rpIdToResourceProfile = new HashMap();
      this.driverPodReadinessTimeout = BoxesRunTime.unboxToLong(conf.get(Config$.MODULE$.KUBERNETES_ALLOCATION_DRIVER_READINESS_TIMEOUT()));
      this.namespace = (String)conf.get(Config$.MODULE$.KUBERNETES_NAMESPACE());
      this.kubernetesDriverPodName = (Option)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_POD_NAME());
      this.driverPod = this.kubernetesDriverPodName().map((name) -> (Pod)scala.Option..MODULE$.apply(((Gettable)((Nameable)this.kubernetesClient.pods().inNamespace(this.namespace())).withName(name)).get()).getOrElse(() -> {
            throw new SparkException("No pod was found named " + name + " in the cluster in the namespace " + this.namespace() + " (this was supposed to be the driver pod.).");
         }));
      this.setsCreated = new HashSet();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
