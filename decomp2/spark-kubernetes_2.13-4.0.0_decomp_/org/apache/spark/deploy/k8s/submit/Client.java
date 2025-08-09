package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodSpecFluent;
import io.fabric8.kubernetes.api.model.VolumeFluent;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher.Action;
import io.fabric8.kubernetes.client.dsl.AnyNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.CreateOrReplaceable;
import io.fabric8.kubernetes.client.dsl.Deletable;
import io.fabric8.kubernetes.client.dsl.Nameable;
import io.fabric8.kubernetes.client.dsl.PodResource;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import org.apache.spark.deploy.k8s.KubernetesDriverSpec;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.ArrowAssoc.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t4Qa\u0003\u0007\u0001%aA\u0001\"\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Y!A\u0001\u0007\u0001B\u0001B\u0003%\u0011\u0007\u0003\u0005>\u0001\t\u0005\t\u0015!\u0003?\u0011\u0015\t\u0005\u0001\"\u0001C\u0011\u0015A\u0005\u0001\"\u0001J\u000f\u0019iE\u0002#\u0001\u0013\u001d\u001a11\u0002\u0004E\u0001%=CQ!\u0011\u0005\u0005\u0002ACQ!\u0015\u0005\u0005\u0002I\u0013aa\u00117jK:$(BA\u0007\u000f\u0003\u0019\u0019XOY7ji*\u0011q\u0002E\u0001\u0004Wb\u001a(BA\t\u0013\u0003\u0019!W\r\u001d7ps*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xmE\u0002\u00013}\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0007C\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u0013\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0013\"\u0005\u001daunZ4j]\u001e\fAaY8oM\u000e\u0001\u0001C\u0001\u0015*\u001b\u0005q\u0011B\u0001\u0016\u000f\u0005QYUOY3s]\u0016$Xm\u001d#sSZ,'oQ8oM\u00069!-^5mI\u0016\u0014\bCA\u0017/\u001b\u0005a\u0011BA\u0018\r\u0005]YUOY3s]\u0016$Xm\u001d#sSZ,'OQ;jY\u0012,'/\u0001\tlk\n,'O\\3uKN\u001cE.[3oiB\u0011!gO\u0007\u0002g)\u0011A'N\u0001\u0007G2LWM\u001c;\u000b\u0005Y:\u0014AC6vE\u0016\u0014h.\u001a;fg*\u0011\u0001(O\u0001\bM\u0006\u0014'/[29\u0015\u0005Q\u0014AA5p\u0013\ta4G\u0001\tLk\n,'O\\3uKN\u001cE.[3oi\u00069q/\u0019;dQ\u0016\u0014\bCA\u0017@\u0013\t\u0001EBA\fM_\u001e<\u0017N\\4Q_\u0012\u001cF/\u0019;vg^\u000bGo\u00195fe\u00061A(\u001b8jiz\"Ra\u0011#F\r\u001e\u0003\"!\f\u0001\t\u000b\u0015*\u0001\u0019A\u0014\t\u000b-*\u0001\u0019\u0001\u0017\t\u000bA*\u0001\u0019A\u0019\t\u000bu*\u0001\u0019\u0001 \u0002\u0007I,h\u000eF\u0001K!\tQ2*\u0003\u0002M7\t!QK\\5u\u0003\u0019\u0019E.[3oiB\u0011Q\u0006C\n\u0003\u0011e!\u0012AT\u0001\rgV\u0014W.[:tS>t\u0017\n\u001a\u000b\u0004'z\u0003\u0007C\u0001+\\\u001d\t)\u0016\f\u0005\u0002W75\tqK\u0003\u0002YM\u00051AH]8pizJ!AW\u000e\u0002\rA\u0013X\rZ3g\u0013\taVL\u0001\u0004TiJLgn\u001a\u0006\u00035nAQa\u0018\u0006A\u0002M\u000b\u0011B\\1nKN\u0004\u0018mY3\t\u000b\u0005T\u0001\u0019A*\u0002\u001b\u0011\u0014\u0018N^3s!>$g*Y7f\u0001"
)
public class Client implements Logging {
   private final KubernetesDriverConf conf;
   private final KubernetesDriverBuilder builder;
   private final KubernetesClient kubernetesClient;
   private final LoggingPodStatusWatcher watcher;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String submissionId(final String namespace, final String driverPodName) {
      return Client$.MODULE$.submissionId(namespace, driverPodName);
   }

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

   public void run() {
      KubernetesDriverSpec resolvedDriverSpec = this.builder.buildFromFeatures(this.conf, this.kubernetesClient);
      String configMapName = KubernetesClientUtils$.MODULE$.configMapNameDriver();
      scala.collection.immutable.Map confFilesMap = KubernetesClientUtils$.MODULE$.buildSparkConfDirFilesMap(configMapName, this.conf.sparkConf(), resolvedDriverSpec.systemProperties());
      ConfigMap configMap = KubernetesClientUtils$.MODULE$.buildConfigMap(configMapName, (scala.collection.immutable.Map)confFilesMap.$plus(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Config$.MODULE$.KUBERNETES_NAMESPACE().key()), this.conf.namespace())), KubernetesClientUtils$.MODULE$.buildConfigMap$default$3());
      Container resolvedDriverContainer = ((ContainerBuilder)((ContainerFluent.VolumeMountsNested)((ContainerFluent)((ContainerFluent.EnvNested)(new ContainerBuilder(resolvedDriverSpec.pod().container())).addNewEnv().withName(Constants$.MODULE$.ENV_SPARK_CONF_DIR()).withValue(Constants$.MODULE$.SPARK_CONF_DIR_INTERNAL())).endEnv()).addNewVolumeMount().withName(Constants$.MODULE$.SPARK_CONF_VOLUME_DRIVER()).withMountPath(Constants$.MODULE$.SPARK_CONF_DIR_INTERNAL())).endVolumeMount()).build();
      Pod resolvedDriverPod = ((PodBuilder)((PodFluent.SpecNested)((PodSpecFluent.VolumesNested)((VolumeFluent.ConfigMapNested)(new PodBuilder(resolvedDriverSpec.pod().pod())).editSpec().addToContainers(new Container[]{resolvedDriverContainer}).addNewVolume().withName(Constants$.MODULE$.SPARK_CONF_VOLUME_DRIVER()).withNewConfigMap().withItems(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(KubernetesClientUtils$.MODULE$.buildKeyToPathObjects(confFilesMap)).asJava()).withName(configMapName)).endConfigMap()).endVolume()).endSpec()).build();
      String driverPodName = resolvedDriverPod.getMetadata().getName();
      Seq preKubernetesResources = resolvedDriverSpec.driverPreKubernetesResources();

      try {
         this.kubernetesClient.resourceList((HasMetadata[])preKubernetesResources.toArray(scala.reflect.ClassTag..MODULE$.apply(HasMetadata.class))).forceConflicts().serverSideApply();
      } catch (Throwable var32) {
         if (var32 != null && scala.util.control.NonFatal..MODULE$.apply(var32)) {
            this.logError((Function0)(() -> "Please check \"kubectl auth can-i create [resource]\" first. It should be yes. And please also check your feature step implementation."));
            this.kubernetesClient.resourceList((HasMetadata[])preKubernetesResources.toArray(scala.reflect.ClassTag..MODULE$.apply(HasMetadata.class))).delete();
            throw var32;
         }

         throw var32;
      }

      ObjectRef watch = ObjectRef.create((Object)null);
      Pod createdDriverPod = null;

      try {
         createdDriverPod = (Pod)((CreateOrReplaceable)((AnyNamespaceOperation)this.kubernetesClient.pods().inNamespace(this.conf.namespace())).resource(resolvedDriverPod)).create();
      } catch (Throwable var31) {
         if (var31 != null && scala.util.control.NonFatal..MODULE$.apply(var31)) {
            this.kubernetesClient.resourceList((HasMetadata[])preKubernetesResources.toArray(scala.reflect.ClassTag..MODULE$.apply(HasMetadata.class))).delete();
            this.logError((Function0)(() -> "Please check \"kubectl auth can-i create pod\" first. It should be yes."));
            throw var31;
         }

         throw var31;
      }

      try {
         KubernetesUtils$.MODULE$.addOwnerReference(createdDriverPod, preKubernetesResources);
         this.kubernetesClient.resourceList((HasMetadata[])preKubernetesResources.toArray(scala.reflect.ClassTag..MODULE$.apply(HasMetadata.class))).forceConflicts().serverSideApply();
      } catch (Throwable var30) {
         if (var30 != null && scala.util.control.NonFatal..MODULE$.apply(var30)) {
            ((Deletable)this.kubernetesClient.pods().resource(createdDriverPod)).delete();
            this.kubernetesClient.resourceList((HasMetadata[])preKubernetesResources.toArray(scala.reflect.ClassTag..MODULE$.apply(HasMetadata.class))).delete();
            throw var30;
         }

         throw var30;
      }

      try {
         Seq otherKubernetesResources = (Seq)resolvedDriverSpec.driverKubernetesResources().$plus$plus(new scala.collection.immutable..colon.colon(configMap, scala.collection.immutable.Nil..MODULE$));
         KubernetesUtils$.MODULE$.addOwnerReference(createdDriverPod, otherKubernetesResources);
         this.kubernetesClient.resourceList((HasMetadata[])otherKubernetesResources.toArray(scala.reflect.ClassTag..MODULE$.apply(HasMetadata.class))).forceConflicts().serverSideApply();
      } catch (Throwable var29) {
         if (var29 != null && scala.util.control.NonFatal..MODULE$.apply(var29)) {
            ((Deletable)this.kubernetesClient.pods().resource(createdDriverPod)).delete();
            throw var29;
         }

         throw var29;
      }

      String sId = Client$.MODULE$.submissionId(this.conf.namespace(), driverPodName);
      if (BoxesRunTime.unboxToBoolean(this.conf.get(Config$.MODULE$.WAIT_FOR_APP_COMPLETION()))) {
         scala.util.control.Breaks..MODULE$.breakable((JFunction0.mcV.sp)() -> {
            do {
               PodResource podWithName = (PodResource)((Nameable)this.kubernetesClient.pods().inNamespace(this.conf.namespace())).withName(driverPodName);
               this.watcher.reset();
               watch.elem = podWithName.watch(this.watcher);
               this.watcher.eventReceived(Action.MODIFIED, podWithName.get());
            } while(!this.watcher.watchOrStop(sId));

            ((Watch)watch.elem).close();
            throw scala.util.control.Breaks..MODULE$.break();
         });
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deployed Spark application ", " with "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_NAME..MODULE$, this.conf.appName())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"application ID ", " and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.conf.appId())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"submission ID ", " into Kubernetes"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, sId)}))))));
      }
   }

   public Client(final KubernetesDriverConf conf, final KubernetesDriverBuilder builder, final KubernetesClient kubernetesClient, final LoggingPodStatusWatcher watcher) {
      this.conf = conf;
      this.builder = builder;
      this.kubernetesClient = kubernetesClient;
      this.watcher = watcher;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
