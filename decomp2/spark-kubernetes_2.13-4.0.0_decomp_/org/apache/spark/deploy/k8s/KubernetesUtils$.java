package org.apache.spark.deploy.k8s;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerState;
import io.fabric8.kubernetes.api.model.ContainerStateRunning;
import io.fabric8.kubernetes.api.model.ContainerStateTerminated;
import io.fabric8.kubernetes.api.model.ContainerStateWaiting;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.EnvVarBuilder;
import io.fabric8.kubernetes.api.model.EnvVarSourceBuilder;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.OwnerReference;
import io.fabric8.kubernetes.api.model.OwnerReferenceBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.codec.binary.Hex;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.util.Clock;
import org.apache.spark.util.SystemClock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@Unstable
@DeveloperApi
public final class KubernetesUtils$ implements Logging {
   public static final KubernetesUtils$ MODULE$ = new KubernetesUtils$();
   private static SecureRandom RNG;
   private static final SystemClock systemClock;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

   static {
      Logging.$init$(MODULE$);
      systemClock = new SystemClock();
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private SystemClock systemClock() {
      return systemClock;
   }

   private SecureRandom RNG$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            RNG = new SecureRandom();
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return RNG;
   }

   private SecureRandom RNG() {
      return !bitmap$0 ? this.RNG$lzycompute() : RNG;
   }

   public scala.collection.immutable.Map parsePrefixedKeyValuePairs(final SparkConf sparkConf, final String prefix) {
      return .MODULE$.wrapRefArray((Object[])sparkConf.getAllWithPrefix(prefix)).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public void requireBothOrNeitherDefined(final Option opt1, final Option opt2, final String errMessageWhenFirstIsMissing, final String errMessageWhenSecondIsMissing) {
      this.requireSecondIfFirstIsDefined(opt1, opt2, errMessageWhenSecondIsMissing);
      this.requireSecondIfFirstIsDefined(opt2, opt1, errMessageWhenFirstIsMissing);
   }

   public void requireSecondIfFirstIsDefined(final Option opt1, final Option opt2, final String errMessageWhenSecondIsMissing) {
      opt1.foreach((x$1) -> {
         $anonfun$requireSecondIfFirstIsDefined$1(opt2, errMessageWhenSecondIsMissing, x$1);
         return BoxedUnit.UNIT;
      });
   }

   public void requireNandDefined(final Option opt1, final Option opt2, final String errMessage) {
      opt1.foreach((x$2) -> {
         $anonfun$requireNandDefined$1(opt2, errMessage, x$2);
         return BoxedUnit.UNIT;
      });
      opt2.foreach((x$3) -> {
         $anonfun$requireNandDefined$3(opt1, errMessage, x$3);
         return BoxedUnit.UNIT;
      });
   }

   public SparkPod loadPodFromTemplate(final KubernetesClient kubernetesClient, final String templateFileName, final Option containerName, final SparkConf conf) {
      try {
         Configuration hadoopConf = org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().newConfiguration(conf);
         String localFile = org.apache.spark.util.DependencyUtils..MODULE$.downloadFile(templateFileName, org.apache.spark.util.Utils..MODULE$.createTempDir(), conf, hadoopConf);
         File templateFile = new File((new URI(localFile)).getPath());
         Pod pod = (Pod)((Resource)kubernetesClient.pods().load(templateFile)).item();
         return this.selectSparkContainer(pod, containerName);
      } catch (Exception var10) {
         this.logError((Function0)(() -> "Encountered exception while attempting to load initial pod spec from file"), var10);
         throw new SparkException("Could not load pod from template file.", var10);
      }
   }

   public SparkPod selectSparkContainer(final Pod pod, final Option containerName) {
      List containers = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(pod.getSpec().getContainers()).asScala().toList();
      return (SparkPod)containerName.flatMap((x$5) -> this.selectNamedContainer$1(containers, x$5)).orElse(() -> containers.headOption().map((x$6) -> new Tuple2(x$6, containers.tail()))).map((x0$1) -> {
         if (x0$1 != null) {
            Container sparkContainer = (Container)x0$1._1();
            List rest = (List)x0$1._2();
            if (sparkContainer != null && rest != null) {
               return new SparkPod(((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(pod)).editSpec().withContainers(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(rest).asJava())).endSpec()).build(), sparkContainer);
            }
         }

         throw new MatchError(x0$1);
      }).getOrElse(() -> new SparkPod(pod, (new ContainerBuilder()).build()));
   }

   public String parseMasterUrl(final String url) {
      return url.substring("k8s://".length());
   }

   public String formatPairsBundle(final Seq pairs, final int indent) {
      String indentStr = scala.collection.StringOps..MODULE$.$times$extension(.MODULE$.augmentString("\t"), indent);
      return ((IterableOnceOps)pairs.map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return "\n" + indentStr + " " + k + ": " + scala.Option..MODULE$.apply(v).filter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$formatPairsBundle$2(x$7))).getOrElse(() -> "N/A");
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("");
   }

   public int formatPairsBundle$default$2() {
      return 1;
   }

   public String formatPodState(final Pod pod) {
      Seq details = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2("pod name", pod.getMetadata().getName()), new Tuple2("namespace", pod.getMetadata().getNamespace()), new Tuple2("labels", scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(pod.getMetadata().getLabels()).asScala().mkString(", ")), new Tuple2("pod uid", pod.getMetadata().getUid()), new Tuple2("creation time", this.formatTime(pod.getMetadata().getCreationTimestamp())), new Tuple2("service account name", pod.getSpec().getServiceAccountName()), new Tuple2("volumes", ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(pod.getSpec().getVolumes()).asScala().map((x$8) -> x$8.getName())).mkString(", ")), new Tuple2("node name", pod.getSpec().getNodeName()), new Tuple2("start time", this.formatTime(pod.getStatus().getStartTime())), new Tuple2("phase", pod.getStatus().getPhase()), new Tuple2("container status", this.containersDescription(pod, 2))})));
      return this.formatPairsBundle(details, this.formatPairsBundle$default$2());
   }

   public String containersDescription(final Pod p, final int indent) {
      return ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(p.getStatus().getContainerStatuses()).asScala().map((status) -> (Seq)(new scala.collection.immutable..colon.colon(new Tuple2("container name", status.getName()), new scala.collection.immutable..colon.colon(new Tuple2("container image", status.getImage()), scala.collection.immutable.Nil..MODULE$))).$plus$plus(MODULE$.containerStatusDescription(status)))).map((px) -> MODULE$.formatPairsBundle(px, indent))).mkString("\n\n");
   }

   public int containersDescription$default$2() {
      return 1;
   }

   public Seq containerStatusDescription(final ContainerStatus containerStatus) {
      ContainerState state = containerStatus.getState();
      return (Seq)scala.Option..MODULE$.apply(state.getRunning()).orElse(() -> scala.Option..MODULE$.apply(state.getTerminated())).orElse(() -> scala.Option..MODULE$.apply(state.getWaiting())).map((x0$1) -> {
         if (x0$1 instanceof ContainerStateRunning var3) {
            return new scala.collection.immutable..colon.colon(new Tuple2("container state", "running"), new scala.collection.immutable..colon.colon(new Tuple2("container started at", MODULE$.formatTime(var3.getStartedAt())), scala.collection.immutable.Nil..MODULE$));
         } else if (x0$1 instanceof ContainerStateWaiting var4) {
            return new scala.collection.immutable..colon.colon(new Tuple2("container state", "waiting"), new scala.collection.immutable..colon.colon(new Tuple2("pending reason", var4.getReason()), scala.collection.immutable.Nil..MODULE$));
         } else if (x0$1 instanceof ContainerStateTerminated var5) {
            return new scala.collection.immutable..colon.colon(new Tuple2("container state", "terminated"), new scala.collection.immutable..colon.colon(new Tuple2("container started at", MODULE$.formatTime(var5.getStartedAt())), new scala.collection.immutable..colon.colon(new Tuple2("container finished at", MODULE$.formatTime(var5.getFinishedAt())), new scala.collection.immutable..colon.colon(new Tuple2("exit code", var5.getExitCode().toString()), new scala.collection.immutable..colon.colon(new Tuple2("termination reason", var5.getReason()), scala.collection.immutable.Nil..MODULE$)))));
         } else {
            throw new SparkException("Unexpected container status type " + x0$1.getClass() + ".");
         }
      }).getOrElse(() -> new scala.collection.immutable..colon.colon(new Tuple2("container state", "N/A"), scala.collection.immutable.Nil..MODULE$));
   }

   public String formatTime(final String time) {
      return time != null ? time : "N/A";
   }

   public String uniqueID(final Clock clock) {
      byte[] random = new byte[3];
      synchronized(this){}

      try {
         this.RNG().nextBytes(random);
      } catch (Throwable var6) {
         throw var6;
      }

      String time = Long.toHexString(clock.getTimeMillis() & 1099511627775L);
      String var10000 = Hex.encodeHexString(random);
      return var10000 + time;
   }

   public Clock uniqueID$default$1() {
      return this.systemClock();
   }

   public scala.collection.immutable.Map buildResourcesQuantities(final String componentName, final SparkConf sparkConf) {
      Seq requests = org.apache.spark.resource.ResourceUtils..MODULE$.parseAllResourceRequests(sparkConf, componentName);
      return ((IterableOnceOps)requests.map((request) -> {
         if (request.vendor().isPresent()) {
            String vendorDomain = (String)request.vendor().get();
            Quantity quantity = new Quantity(Long.toString(request.amount()));
            return new Tuple2(KubernetesConf$.MODULE$.buildKubernetesResourceName(vendorDomain, request.id().resourceName()), quantity);
         } else {
            throw new SparkException("Resource: " + request.id().resourceName() + " was requested, but vendor was not specified.");
         }
      })).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Iterable uploadAndTransformFileUris(final Iterable fileUris, final Option conf) {
      return (Iterable)fileUris.map((uri) -> MODULE$.uploadFileUri(uri, conf));
   }

   public Option uploadAndTransformFileUris$default$2() {
      return scala.None..MODULE$;
   }

   private boolean isLocalDependency(final URI uri) {
      String var3 = uri.getScheme();
      switch (var3 == null ? 0 : var3.hashCode()) {
         case 0:
            if (var3 == null) {
               return true;
            }
            break;
         case 3143036:
            if ("file".equals(var3)) {
               return true;
            }
      }

      return false;
   }

   public boolean isLocalAndResolvable(final String resource) {
      boolean var10000;
      label18: {
         String var2 = "spark-internal";
         if (resource == null) {
            if (var2 == null) {
               break label18;
            }
         } else if (resource.equals(var2)) {
            break label18;
         }

         if (this.isLocalDependency(org.apache.spark.util.Utils..MODULE$.resolveURI(resource))) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   public String renameMainAppResource(final String resource, final Option conf, final boolean shouldUploadLocal) {
      if (this.isLocalAndResolvable(resource)) {
         return shouldUploadLocal ? this.uploadFileUri(resource, conf) : "spark-internal";
      } else {
         return resource;
      }
   }

   public Option renameMainAppResource$default$2() {
      return scala.None..MODULE$;
   }

   public String uploadFileUri(final String uri, final Option conf) {
      if (conf instanceof Some var5) {
         SparkConf sConf = (SparkConf)var5.value();
         if (((Option)sConf.get(Config$.MODULE$.KUBERNETES_FILE_UPLOAD_PATH())).isDefined()) {
            URI fileUri = org.apache.spark.util.Utils..MODULE$.resolveURI(uri);

            try {
               Configuration hadoopConf = org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().newConfiguration(sConf);
               String uploadPath = (String)((Option)sConf.get(Config$.MODULE$.KUBERNETES_FILE_UPLOAD_PATH())).get();
               FileSystem fs = org.apache.spark.util.Utils..MODULE$.getHadoopFileSystem(org.apache.spark.util.Utils..MODULE$.resolveURI(uploadPath), hadoopConf);
               String randomDirName = "spark-upload-" + UUID.randomUUID();
               fs.mkdirs(new Path(uploadPath + "/" + randomDirName));
               String targetUri = uploadPath + "/" + randomDirName + "/" + scala.collection.ArrayOps..MODULE$.last$extension(.MODULE$.refArrayOps((Object[])fileUri.getPath().split("/")));
               Logger var10000 = this.log();
               String var10001 = fileUri.getPath();
               var10000.info("Uploading file: " + var10001 + " to dest: " + targetUri + "...");
               this.uploadFileToHadoopCompatibleFS(new Path(fileUri), new Path(targetUri), fs, this.uploadFileToHadoopCompatibleFS$default$4(), this.uploadFileToHadoopCompatibleFS$default$5());
               return targetUri;
            } catch (Exception var14) {
               throw new SparkException("Uploading file " + fileUri.getPath() + " failed...", var14);
            }
         } else {
            throw new SparkException("Please specify spark.kubernetes.file.upload.path property.");
         }
      } else {
         throw new SparkException("Spark configuration is missing...");
      }
   }

   public Option uploadFileUri$default$2() {
      return scala.None..MODULE$;
   }

   private void uploadFileToHadoopCompatibleFS(final Path src, final Path dest, final FileSystem fs, final boolean delSrc, final boolean overwrite) {
      try {
         fs.copyFromLocalFile(delSrc, overwrite, src, dest);
      } catch (IOException var7) {
         throw new SparkException("Error uploading file " + src.getName(), var7);
      }
   }

   private boolean uploadFileToHadoopCompatibleFS$default$4() {
      return false;
   }

   private boolean uploadFileToHadoopCompatibleFS$default$5() {
      return true;
   }

   public Option buildPodWithServiceAccount(final Option serviceAccount, final SparkPod pod) {
      return serviceAccount.map((account) -> ((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(pod.pod())).editOrNewSpec().withServiceAccount(account).withServiceAccountName(account)).endSpec()).build());
   }

   public void addOwnerReference(final Pod pod, final Seq resources) {
      if (pod != null) {
         OwnerReference reference = ((OwnerReferenceBuilder)(new OwnerReferenceBuilder()).withName(pod.getMetadata().getName()).withApiVersion(pod.getApiVersion()).withUid(pod.getMetadata().getUid()).withKind(pod.getKind()).withController(.MODULE$.boolean2Boolean(true))).build();
         resources.foreach((resource) -> {
            $anonfun$addOwnerReference$1(reference, resource);
            return BoxedUnit.UNIT;
         });
      }
   }

   public Seq buildEnvVars(final Seq env) {
      return (Seq)((IterableOps)env.filterNot((x$9) -> BoxesRunTime.boxToBoolean($anonfun$buildEnvVars$1(x$9)))).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return ((EnvVarBuilder)(new EnvVarBuilder()).withName(k).withValue(v)).build();
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Seq buildEnvVarsWithFieldRef(final Seq env) {
      return (Seq)((IterableOps)((IterableOps)env.filterNot((x$10) -> BoxesRunTime.boxToBoolean($anonfun$buildEnvVarsWithFieldRef$1(x$10)))).filterNot((x$11) -> BoxesRunTime.boxToBoolean($anonfun$buildEnvVarsWithFieldRef$2(x$11)))).map((x0$1) -> {
         if (x0$1 != null) {
            String key = (String)x0$1._1();
            String apiVersion = (String)x0$1._2();
            String fieldPath = (String)x0$1._3();
            return ((EnvVarBuilder)(new EnvVarBuilder()).withName(key).withValueFrom(((EnvVarSourceBuilder)(new EnvVarSourceBuilder()).withNewFieldRef(apiVersion, fieldPath)).build())).build();
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$requireSecondIfFirstIsDefined$1(final Option opt2$1, final String errMessageWhenSecondIsMissing$1, final Object x$1) {
      .MODULE$.require(opt2$1.isDefined(), () -> errMessageWhenSecondIsMissing$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$requireNandDefined$1(final Option opt2$2, final String errMessage$1, final Object x$2) {
      .MODULE$.require(opt2$2.isEmpty(), () -> errMessage$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$requireNandDefined$3(final Option opt1$1, final String errMessage$1, final Object x$3) {
      .MODULE$.require(opt1$1.isEmpty(), () -> errMessage$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$selectSparkContainer$1(final String name$1, final Container x$4) {
      boolean var3;
      label23: {
         String var10000 = x$4.getName();
         if (var10000 == null) {
            if (name$1 == null) {
               break label23;
            }
         } else if (var10000.equals(name$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   private final Option selectNamedContainer$1(final List containers, final String name) {
      Tuple2 var4 = containers.partition((x$4) -> BoxesRunTime.boxToBoolean($anonfun$selectSparkContainer$1(name, x$4)));
      if (var4 != null) {
         List var5 = (List)var4._1();
         List rest = (List)var4._2();
         if (var5 instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)var5;
            Container sparkContainer = (Container)var7.head();
            List var9 = var7.next$access$1();
            if (scala.collection.immutable.Nil..MODULE$.equals(var9)) {
               return new Some(new Tuple2(sparkContainer, rest));
            }
         }
      }

      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"specified container ", " not found on pod template, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POD_ID..MODULE$, name)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"falling back to taking the first container"})))).log(scala.collection.immutable.Nil..MODULE$))));
      return scala.Option..MODULE$.empty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$formatPairsBundle$2(final String x$7) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(x$7));
   }

   // $FF: synthetic method
   public static final void $anonfun$addOwnerReference$1(final OwnerReference reference$1, final HasMetadata resource) {
      ObjectMeta originalMetadata = resource.getMetadata();
      originalMetadata.setOwnerReferences(Collections.singletonList(reference$1));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildEnvVars$1(final Tuple2 x$9) {
      return x$9._2() == null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildEnvVarsWithFieldRef$1(final Tuple3 x$10) {
      return x$10._2() == null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildEnvVarsWithFieldRef$2(final Tuple3 x$11) {
      return x$11._3() == null;
   }

   private KubernetesUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
