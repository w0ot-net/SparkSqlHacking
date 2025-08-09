package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.ContainerPortBuilder;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.LifecycleFluent;
import io.fabric8.kubernetes.api.model.LifecycleHandlerFluent;
import io.fabric8.kubernetes.api.model.LocalObjectReference;
import io.fabric8.kubernetes.api.model.OwnerReference;
import io.fabric8.kubernetes.api.model.OwnerReferenceBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.PodSpecFluent;
import io.fabric8.kubernetes.api.model.Quantity;
import io.fabric8.kubernetes.api.model.ResourceRequirements;
import io.fabric8.kubernetes.api.model.VolumeFluent;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf$;
import org.apache.spark.deploy.k8s.KubernetesExecutorConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.deploy.k8s.submit.KubernetesClientUtils$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.resource.ResourceProfile;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de!B\u0011#\u0001!r\u0003\u0002C \u0001\u0005\u0003\u0005\u000b\u0011B!\t\u0011\u0015\u0003!\u0011!Q\u0001\n\u0019C\u0001B\u0013\u0001\u0003\u0002\u0003\u0006Ia\u0013\u0005\u0006#\u0002!\tA\u0015\u0005\b/\u0002\u0011\r\u0011\"\u0003Y\u0011\u0019!\u0007\u0001)A\u00053\"9Q\r\u0001b\u0001\n\u00131\u0007B\u00026\u0001A\u0003%q\rC\u0004l\u0001\t\u0007I\u0011\u0002-\t\r1\u0004\u0001\u0015!\u0003Z\u0011\u001di\u0007A1A\u0005\n9DaA\u001e\u0001!\u0002\u0013y\u0007bB<\u0001\u0005\u0004%I\u0001\u001f\u0005\u0007y\u0002\u0001\u000b\u0011B=\t\u000fu\u0004!\u0019!C\u0005q\"1a\u0010\u0001Q\u0001\neDqa \u0001C\u0002\u0013%\u0001\u0010C\u0004\u0002\u0002\u0001\u0001\u000b\u0011B=\t\u0013\u0005\r\u0001A1A\u0005\n\u0005\u0015\u0001\u0002CA\u0007\u0001\u0001\u0006I!a\u0002\t\u0013\u0005=\u0001A1A\u0005\n\u0005E\u0001\u0002CA\r\u0001\u0001\u0006I!a\u0005\t\u0013\u0005m\u0001A1A\u0005\u0002\u0005u\u0001\u0002CA\u0017\u0001\u0001\u0006I!a\b\t\u0011\u0005=\u0002A1A\u0005\n9Dq!!\r\u0001A\u0003%q\u000e\u0003\u0005\u00024\u0001\u0011\r\u0011\"\u0003Y\u0011\u001d\t)\u0004\u0001Q\u0001\neC\u0011\"a\u000e\u0001\u0005\u0004%I!!\u000f\t\u0011\u0005\u0005\u0003\u0001)A\u0005\u0003wAq!a\u0011\u0001\t\u0013\t)\u0005C\u0004\u0002z\u0001!\t%a\u001f\u00031\t\u000b7/[2Fq\u0016\u001cW\u000f^8s\r\u0016\fG/\u001e:f'R,\u0007O\u0003\u0002$I\u0005Aa-Z1ukJ,7O\u0003\u0002&M\u0005\u00191\u000eO:\u000b\u0005\u001dB\u0013A\u00023fa2|\u0017P\u0003\u0002*U\u0005)1\u000f]1sW*\u00111\u0006L\u0001\u0007CB\f7\r[3\u000b\u00035\n1a\u001c:h'\u0011\u0001q&N\u001d\u0011\u0005A\u001aT\"A\u0019\u000b\u0003I\nQa]2bY\u0006L!\u0001N\u0019\u0003\r\u0005s\u0017PU3g!\t1t'D\u0001#\u0013\tA$EA\u000eLk\n,'O\\3uKN4U-\u0019;ve\u0016\u001cuN\u001c4jON#X\r\u001d\t\u0003uuj\u0011a\u000f\u0006\u0003y!\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003}m\u0012q\u0001T8hO&tw-\u0001\blk\n,'O\\3uKN\u001cuN\u001c4\u0004\u0001A\u0011!iQ\u0007\u0002I%\u0011A\t\n\u0002\u0017\u0017V\u0014WM\u001d8fi\u0016\u001cX\t_3dkR|'oQ8oM\u000611/Z2NOJ\u0004\"a\u0012%\u000e\u0003!J!!\u0013\u0015\u0003\u001fM+7-\u001e:jifl\u0015M\\1hKJ\fqB]3t_V\u00148-\u001a)s_\u001aLG.\u001a\t\u0003\u0019>k\u0011!\u0014\u0006\u0003\u001d\"\n\u0001B]3t_V\u00148-Z\u0005\u0003!6\u0013qBU3t_V\u00148-\u001a)s_\u001aLG.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\tM#VK\u0016\t\u0003m\u0001AQa\u0010\u0003A\u0002\u0005CQ!\u0012\u0003A\u0002\u0019CQA\u0013\u0003A\u0002-\u000ba#\u001a=fGV$xN]\"p]R\f\u0017N\\3s\u00136\fw-Z\u000b\u00023B\u0011!,\u0019\b\u00037~\u0003\"\u0001X\u0019\u000e\u0003uS!A\u0018!\u0002\rq\u0012xn\u001c;?\u0013\t\u0001\u0017'\u0001\u0004Qe\u0016$WMZ\u0005\u0003E\u000e\u0014aa\u0015;sS:<'B\u000112\u0003])\u00070Z2vi>\u00148i\u001c8uC&tWM]%nC\u001e,\u0007%\u0001\tcY>\u001c7.T1oC\u001e,'\u000fU8siV\tq\r\u0005\u00021Q&\u0011\u0011.\r\u0002\u0004\u0013:$\u0018!\u00052m_\u000e\\W*\u00198bO\u0016\u0014\bk\u001c:uA\u0005)R\r_3dkR|'\u000fU8e\u001d\u0006lW\r\u0015:fM&D\u0018AF3yK\u000e,Ho\u001c:Q_\u0012t\u0015-\\3Qe\u00164\u0017\u000e\u001f\u0011\u0002\u0013\u0011\u0014\u0018N^3s+JdW#A8\u0011\u0005A,X\"A9\u000b\u0005I\u001c\u0018\u0001\u00027b]\u001eT\u0011\u0001^\u0001\u0005U\u00064\u0018-\u0003\u0002cc\u0006QAM]5wKJ,&\u000f\u001c\u0011\u0002!%\u001cH)\u001a4bk2$\bK]8gS2,W#A=\u0011\u0005AR\u0018BA>2\u0005\u001d\u0011un\u001c7fC:\f\u0011#[:EK\u001a\fW\u000f\u001c;Qe>4\u0017\u000e\\3!\u0003-I7\u000fU=uQ>t\u0017\t\u001d9\u0002\u0019%\u001c\b+\u001f;i_:\f\u0005\u000f\u001d\u0011\u0002!\u0011L7/\u00192mK\u000e{gNZ5h\u001b\u0006\u0004\u0018!\u00053jg\u0006\u0014G.Z\"p]\u001aLw-T1qA\u0005)R.\u001b8j[VlW*Z7pef|e/\u001a:iK\u0006$WCAA\u0004!\r\u0001\u0014\u0011B\u0005\u0004\u0003\u0017\t$\u0001\u0002'p]\u001e\fa#\\5oS6,X.T3n_JLxJ^3sQ\u0016\fG\rI\u0001\u0015[\u0016lwN]=Pm\u0016\u0014\b.Z1e\r\u0006\u001cGo\u001c:\u0016\u0005\u0005M\u0001c\u0001\u0019\u0002\u0016%\u0019\u0011qC\u0019\u0003\r\u0011{WO\u00197f\u0003UiW-\\8ss>3XM\u001d5fC\u00124\u0015m\u0019;pe\u0002\nQ\"\u001a=fGJ+7o\\;sG\u0016\u001cXCAA\u0010!\u0011\t\t#a\n\u000f\u00071\u000b\u0019#C\u0002\u0002&5\u000bqBU3t_V\u00148-\u001a)s_\u001aLG.Z\u0005\u0005\u0003S\tYCA\u000eFq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dKN|%\u000fR3gCVdGo\u001d\u0006\u0004\u0003Ki\u0015AD3yK\u000e\u0014Vm]8ve\u000e,7\u000fI\u0001\u0015Kb,7-\u001e;pe6+Wn\u001c:z'R\u0014\u0018N\\4\u0002+\u0015DXmY;u_JlU-\\8ssN#(/\u001b8hA\u0005!R\r_3dkR|'oQ8sKN\u0014V-];fgR\fQ#\u001a=fGV$xN]\"pe\u0016\u001c(+Z9vKN$\b%\u0001\nfq\u0016\u001cW\u000f^8s\u0019&l\u0017\u000e^\"pe\u0016\u001cXCAA\u001e!\u0011\u0001\u0014QH-\n\u0007\u0005}\u0012G\u0001\u0004PaRLwN\\\u0001\u0014Kb,7-\u001e;pe2KW.\u001b;D_J,7\u000fI\u0001!EVLG\u000eZ#yK\u000e,Ho\u001c:SKN|WO]2fgF+\u0018M\u001c;ji&,7\u000f\u0006\u0003\u0002H\u0005%\u0004C\u0002.\u0002Je\u000bi%C\u0002\u0002L\r\u00141!T1q!\u0011\ty%!\u001a\u000e\u0005\u0005E#\u0002BA*\u0003+\nQ!\\8eK2TA!a\u0016\u0002Z\u0005\u0019\u0011\r]5\u000b\t\u0005m\u0013QL\u0001\u000bWV\u0014WM\u001d8fi\u0016\u001c(\u0002BA0\u0003C\nqAZ1ce&\u001c\u0007H\u0003\u0002\u0002d\u0005\u0011\u0011n\\\u0005\u0005\u0003O\n\tF\u0001\u0005Rk\u0006tG/\u001b;z\u0011\u001d\tYg\ba\u0001\u0003[\nqbY;ti>l'+Z:pkJ\u001cWm\u001d\t\u00065\u0006=\u00141O\u0005\u0004\u0003c\u001a'aA*fiB\u0019A*!\u001e\n\u0007\u0005]TJA\fFq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dKJ+\u0017/^3ti\u0006a1m\u001c8gS\u001e,(/\u001a)pIR!\u0011QPAB!\r\u0011\u0015qP\u0005\u0004\u0003\u0003##\u0001C*qCJ\\\u0007k\u001c3\t\u000f\u0005\u0015\u0005\u00051\u0001\u0002~\u0005\u0019\u0001o\u001c3"
)
public class BasicExecutorFeatureStep implements KubernetesFeatureConfigStep, Logging {
   private final KubernetesExecutorConf kubernetesConf;
   private final SecurityManager secMgr;
   private final ResourceProfile resourceProfile;
   private final String executorContainerImage;
   private final int blockManagerPort;
   private final String executorPodNamePrefix;
   private final String driverUrl;
   private final boolean isDefaultProfile;
   private final boolean isPythonApp;
   private final boolean disableConfigMap;
   private final long minimumMemoryOverhead;
   private final double memoryOverheadFactor;
   private final ResourceProfile.ExecutorResourcesOrDefaults execResources;
   private final String executorMemoryString;
   private final String executorCoresRequest;
   private final Option executorLimitCores;
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

   public scala.collection.immutable.Map getAdditionalPodSystemProperties() {
      return KubernetesFeatureConfigStep.getAdditionalPodSystemProperties$(this);
   }

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public Seq getAdditionalKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalKubernetesResources$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private String executorContainerImage() {
      return this.executorContainerImage;
   }

   private int blockManagerPort() {
      return this.blockManagerPort;
   }

   private String executorPodNamePrefix() {
      return this.executorPodNamePrefix;
   }

   private String driverUrl() {
      return this.driverUrl;
   }

   private boolean isDefaultProfile() {
      return this.isDefaultProfile;
   }

   private boolean isPythonApp() {
      return this.isPythonApp;
   }

   private boolean disableConfigMap() {
      return this.disableConfigMap;
   }

   private long minimumMemoryOverhead() {
      return this.minimumMemoryOverhead;
   }

   private double memoryOverheadFactor() {
      return this.memoryOverheadFactor;
   }

   public ResourceProfile.ExecutorResourcesOrDefaults execResources() {
      return this.execResources;
   }

   private String executorMemoryString() {
      return this.executorMemoryString;
   }

   private String executorCoresRequest() {
      return this.executorCoresRequest;
   }

   private Option executorLimitCores() {
      return this.executorLimitCores;
   }

   private scala.collection.immutable.Map buildExecutorResourcesQuantities(final Set customResources) {
      return ((IterableOnceOps)customResources.map((request) -> {
         if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(request.vendor()))) {
            String vendorDomain = request.vendor();
            Quantity quantity = new Quantity(Long.toString(request.amount()));
            return new Tuple2(KubernetesConf$.MODULE$.buildKubernetesResourceName(vendorDomain, request.resourceName()), quantity);
         } else {
            throw new SparkException("Resource: " + request.resourceName() + " was requested, but vendor was not specified.");
         }
      })).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public SparkPod configurePod(final SparkPod pod) {
      String var10000 = this.executorPodNamePrefix();
      String name = var10000 + "-exec-" + this.kubernetesConf.executorId();
      String configMapName = KubernetesClientUtils$.MODULE$.configMapNameExecutor();
      scala.collection.immutable.Map confFilesMap = KubernetesClientUtils$.MODULE$.buildSparkConfDirFilesMap(configMapName, this.kubernetesConf.sparkConf(), scala.Predef..MODULE$.Map().empty());
      Seq keyToPaths = KubernetesClientUtils$.MODULE$.buildKeyToPathObjects(confFilesMap);
      String hostname = name.substring(Math.max(0, name.length() - Config$.MODULE$.KUBERNETES_DNS_LABEL_NAME_MAX_LENGTH())).replaceAll("^[^\\w]+", "").replaceAll("[^\\w-]+", "_");
      Quantity executorMemoryQuantity = new Quantity(this.execResources().totalMemMiB() + "Mi");
      Quantity executorCpuQuantity = new Quantity(this.executorCoresRequest());
      scala.collection.immutable.Map executorResourceQuantities = this.buildExecutorResourcesQuantities(this.execResources().customResources().values().toSet());
      Seq sparkAuthSecret = (Seq)scala.Option..MODULE$.apply(this.secMgr.getSecretKey()).map((x0$1) -> (Seq)(x0$1 != null && ((Option)this.kubernetesConf.get(org.apache.spark.internal.config.package..MODULE$.AUTH_SECRET_FILE_EXECUTOR())).isEmpty() ? new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.SecurityManager..MODULE$.ENV_AUTH_SECRET()), x0$1), scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$)).getOrElse(() -> scala.collection.immutable.Nil..MODULE$);
      Seq userOpts = (Seq)scala.Option..MODULE$.option2Iterable((Option)this.kubernetesConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_JAVA_OPTIONS())).toSeq().flatMap((opts) -> {
         String subsOpts = org.apache.spark.util.Utils..MODULE$.substituteAppNExecIds(opts, this.kubernetesConf.appId(), this.kubernetesConf.executorId());
         return org.apache.spark.util.Utils..MODULE$.splitCommandString(subsOpts);
      });
      Seq sparkOpts = org.apache.spark.util.Utils..MODULE$.sparkJavaOpts(this.kubernetesConf.sparkConf(), (namex) -> BoxesRunTime.boxToBoolean($anonfun$configurePod$4(namex)));
      scala.collection.immutable.Map allOpts = ((IterableOnceOps)((IterableOps)((IterableOps)userOpts.$plus$plus(sparkOpts)).zipWithIndex()).map((x0$2) -> {
         if (x0$2 != null) {
            String opt = (String)x0$2._1();
            int index = x0$2._2$mcI$sp();
            return new Tuple2(Constants$.MODULE$.ENV_JAVA_OPT_PREFIX() + index, opt);
         } else {
            throw new MatchError(x0$2);
         }
      })).toMap(scala..less.colon.less..MODULE$.refl());
      scala.collection.immutable.Map attributes = ((Option)this.kubernetesConf.get(org.apache.spark.internal.config.UI..MODULE$.CUSTOM_EXECUTOR_LOG_URL())).isDefined() ? (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_EXECUTOR_ATTRIBUTE_APP_ID()), this.kubernetesConf.appId()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_EXECUTOR_ATTRIBUTE_EXECUTOR_ID()), this.kubernetesConf.executorId())}))) : scala.Predef..MODULE$.Map().empty();
      Seq executorEnv = (Seq)KubernetesUtils$.MODULE$.buildEnvVars((Seq)((IterableOps)((IterableOps)((IterableOps)((IterableOps)(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_DRIVER_URL()), this.driverUrl()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_EXECUTOR_CORES()), this.execResources().cores().get().toString()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_EXECUTOR_MEMORY()), this.executorMemoryString()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_APPLICATION_ID()), this.kubernetesConf.appId()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_SPARK_CONF_DIR()), Constants$.MODULE$.SPARK_CONF_DIR_INTERNAL()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_EXECUTOR_ID()), this.kubernetesConf.executorId()), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_RESOURCE_PROFILE_ID()), Integer.toString(this.resourceProfile.id())), scala.collection.immutable.Nil..MODULE$)))))))).$plus$plus(attributes)).$plus$plus(this.kubernetesConf.environment())).$plus$plus(sparkAuthSecret)).$plus$plus(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_CLASSPATH()), ((Option)this.kubernetesConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_CLASS_PATH())).orNull(scala..less.colon.less..MODULE$.refl())), scala.collection.immutable.Nil..MODULE$))).$plus$plus(allOpts)).$plus$plus(KubernetesUtils$.MODULE$.buildEnvVarsWithFieldRef(new scala.collection.immutable..colon.colon(new Tuple3(Constants$.MODULE$.ENV_EXECUTOR_POD_IP(), "v1", "status.podIP"), new scala.collection.immutable..colon.colon(new Tuple3(Constants$.MODULE$.ENV_EXECUTOR_POD_NAME(), "v1", "metadata.name"), scala.collection.immutable.Nil..MODULE$))));
      executorEnv.find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$configurePod$6(x$1))).foreach((e) -> {
         $anonfun$configurePod$7(this, e);
         return BoxedUnit.UNIT;
      });
      Seq requiredPorts = (Seq)(this.blockManagerPort() != 0 ? (Seq)(new scala.collection.immutable..colon.colon(new Tuple2(Constants$.MODULE$.BLOCK_MANAGER_PORT_NAME(), BoxesRunTime.boxToInteger(this.blockManagerPort())), scala.collection.immutable.Nil..MODULE$)).map((x0$3) -> {
         if (x0$3 != null) {
            String name = (String)x0$3._1();
            int port = x0$3._2$mcI$sp();
            return ((ContainerPortBuilder)(new ContainerPortBuilder()).withName(name).withContainerPort(scala.Predef..MODULE$.int2Integer(port))).build();
         } else {
            throw new MatchError(x0$3);
         }
      }) : scala.collection.immutable.Nil..MODULE$);
      if (!this.isDefaultProfile() && pod.container() != null && pod.container().getResources() != null) {
         this.logDebug((Function0)(() -> "NOT using the default profile and removing template resources"));
         pod.container().setResources(new ResourceRequirements());
      }

      Container executorContainer = ((ContainerBuilder)((ContainerFluent)((ContainerFluent.EnvNested)((ContainerFluent)((ContainerFluent.ResourcesNested)(new ContainerBuilder(pod.container())).withName((String)scala.Option..MODULE$.apply(pod.container().getName()).getOrElse(() -> Constants$.MODULE$.DEFAULT_EXECUTOR_CONTAINER_NAME())).withImage(this.executorContainerImage()).withImagePullPolicy(this.kubernetesConf.imagePullPolicy()).editOrNewResources().addToRequests("memory", executorMemoryQuantity).addToLimits("memory", executorMemoryQuantity).addToRequests("cpu", executorCpuQuantity).addToLimits(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(executorResourceQuantities).asJava())).endResources()).addNewEnv().withName(Constants$.MODULE$.ENV_SPARK_USER()).withValue(org.apache.spark.util.Utils..MODULE$.getCurrentUserName())).endEnv()).addAllToEnv(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(executorEnv).asJava()).addAllToPorts(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(requiredPorts).asJava()).addToArgs(new String[]{"executor"})).build();
      Container executorContainerWithConfVolume = this.disableConfigMap() ? executorContainer : ((ContainerBuilder)((ContainerFluent.VolumeMountsNested)(new ContainerBuilder(executorContainer)).addNewVolumeMount().withName(Constants$.MODULE$.SPARK_CONF_VOLUME_EXEC()).withMountPath(Constants$.MODULE$.SPARK_CONF_DIR_INTERNAL())).endVolumeMount()).build();
      Container containerWithLimitCores = this.isDefaultProfile() ? (Container)this.executorLimitCores().map((limitCores) -> {
         Quantity executorCpuLimitQuantity = new Quantity(limitCores);
         return ((ContainerBuilder)((ContainerFluent.ResourcesNested)(new ContainerBuilder(executorContainerWithConfVolume)).editResources().addToLimits("cpu", executorCpuLimitQuantity)).endResources()).build();
      }).getOrElse(() -> executorContainerWithConfVolume) : executorContainerWithConfVolume;
      Container var28;
      if (!this.kubernetesConf.workerDecommissioning()) {
         this.logInfo((Function0)(() -> "Decommissioning not enabled, skipping shutdown script"));
         var28 = containerWithLimitCores;
      } else {
         this.logInfo((Function0)(() -> "Adding decommission script to lifecycle"));
         var28 = ((ContainerBuilder)((ContainerFluent.LifecycleNested)((LifecycleFluent.PreStopNested)((LifecycleHandlerFluent.ExecNested)(new ContainerBuilder(containerWithLimitCores)).withNewLifecycle().withNewPreStop().withNewExec().addToCommand(new String[]{(String)this.kubernetesConf.get(Config$.MODULE$.DECOMMISSION_SCRIPT())})).endExec()).endPreStop()).endLifecycle()).build();
      }

      Container containerWithLifecycle = var28;
      Option ownerReference = this.kubernetesConf.driverPod().map((podx) -> ((OwnerReferenceBuilder)(new OwnerReferenceBuilder()).withController(scala.Predef..MODULE$.boolean2Boolean(true)).withApiVersion(podx.getApiVersion()).withKind(podx.getKind()).withName(podx.getMetadata().getName()).withUid(podx.getMetadata().getUid())).build());
      String var24 = (String)this.kubernetesConf.get(Config$.MODULE$.KUBERNETES_ALLOCATION_PODS_ALLOCATOR());
      String var29;
      switch (var24 == null ? 0 : var24.hashCode()) {
         case 1736521110:
            if ("statefulset".equals(var24)) {
               var29 = "Always";
               break;
            }
         default:
            var29 = "Never";
      }

      String policy = var29;
      PodFluent.SpecNested executorPodBuilder = (PodFluent.SpecNested)((PodFluent)((PodFluent.MetadataNested)(new PodBuilder(pod.pod())).editOrNewMetadata().withName(name).addToLabels(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.kubernetesConf.labels()).asJava()).addToAnnotations(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.kubernetesConf.annotations()).asJava()).addToOwnerReferences((OwnerReference[])scala.Option..MODULE$.option2Iterable(ownerReference).toSeq().toArray(scala.reflect.ClassTag..MODULE$.apply(OwnerReference.class)))).endMetadata()).editOrNewSpec().withHostname(hostname).withRestartPolicy(policy).addToNodeSelector(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.kubernetesConf.nodeSelector()).asJava()).addToNodeSelector(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.kubernetesConf.executorNodeSelector()).asJava()).addToImagePullSecrets((LocalObjectReference[])this.kubernetesConf.imagePullSecrets().toArray(scala.reflect.ClassTag..MODULE$.apply(LocalObjectReference.class)));
      Pod executorPod = this.disableConfigMap() ? ((PodBuilder)executorPodBuilder.endSpec()).build() : ((PodBuilder)((PodFluent.SpecNested)((PodSpecFluent.VolumesNested)((VolumeFluent.ConfigMapNested)executorPodBuilder.addNewVolume().withName(Constants$.MODULE$.SPARK_CONF_VOLUME_EXEC()).withNewConfigMap().withItems(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(keyToPaths).asJava()).withName(configMapName)).endConfigMap()).endVolume()).endSpec()).build();
      Option var30 = this.kubernetesConf.schedulerName();
      PodSpec var27 = executorPod.getSpec();
      var30.foreach((x$1) -> {
         $anonfun$configurePod$16(var27, x$1);
         return BoxedUnit.UNIT;
      });
      return new SparkPod(executorPod, containerWithLifecycle);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$configurePod$4(final String name) {
      return org.apache.spark.SparkConf..MODULE$.isExecutorStartupConf(name);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$configurePod$6(final EnvVar x$1) {
      boolean var2;
      label23: {
         String var10000 = x$1.getName();
         String var1 = Constants$.MODULE$.ENV_EXECUTOR_DIRS();
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
   public static final void $anonfun$configurePod$7(final BasicExecutorFeatureStep $this, final EnvVar e) {
      e.setValue(e.getValue().replaceAll(Constants$.MODULE$.ENV_APPLICATION_ID(), $this.kubernetesConf.appId()).replaceAll(Constants$.MODULE$.ENV_EXECUTOR_ID(), $this.kubernetesConf.executorId()));
   }

   // $FF: synthetic method
   public static final void $anonfun$configurePod$16(final PodSpec eta$0$1$1, final String x$1) {
      eta$0$1$1.setSchedulerName(x$1);
   }

   public BasicExecutorFeatureStep(final KubernetesExecutorConf kubernetesConf, final SecurityManager secMgr, final ResourceProfile resourceProfile) {
      boolean var5;
      label40: {
         label39: {
            this.kubernetesConf = kubernetesConf;
            this.secMgr = secMgr;
            this.resourceProfile = resourceProfile;
            super();
            KubernetesFeatureConfigStep.$init$(this);
            Logging.$init$(this);
            this.executorContainerImage = (String)((Option)kubernetesConf.get(Config$.MODULE$.EXECUTOR_CONTAINER_IMAGE())).getOrElse(() -> {
               throw new SparkException("Must specify the executor container image");
            });
            this.blockManagerPort = kubernetesConf.sparkConf().getInt(org.apache.spark.internal.config.package..MODULE$.BLOCK_MANAGER_PORT().key(), Constants$.MODULE$.DEFAULT_BLOCKMANAGER_PORT());
            scala.Predef..MODULE$.require(this.blockManagerPort() == 0 || 1024 <= this.blockManagerPort() && this.blockManagerPort() < 65536, () -> "port number must be 0 or in [1024, 65535]");
            this.executorPodNamePrefix = kubernetesConf.resourceNamePrefix();
            this.driverUrl = org.apache.spark.rpc.RpcEndpointAddress..MODULE$.apply((String)kubernetesConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_HOST_ADDRESS()), kubernetesConf.sparkConf().getInt(org.apache.spark.internal.config.package..MODULE$.DRIVER_PORT().key(), Constants$.MODULE$.DEFAULT_DRIVER_PORT()), org.apache.spark.scheduler.cluster.CoarseGrainedSchedulerBackend..MODULE$.ENDPOINT_NAME()).toString();
            this.isDefaultProfile = resourceProfile.id() == org.apache.spark.resource.ResourceProfile..MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
            Object var10001 = kubernetesConf.get(Config$.MODULE$.APP_RESOURCE_TYPE());
            Some var4 = new Some(Constants$.MODULE$.APP_RESOURCE_TYPE_PYTHON());
            if (var10001 == null) {
               if (var4 == null) {
                  break label39;
               }
            } else if (var10001.equals(var4)) {
               break label39;
            }

            var5 = false;
            break label40;
         }

         var5 = true;
      }

      this.isPythonApp = var5;
      this.disableConfigMap = BoxesRunTime.unboxToBoolean(kubernetesConf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_DISABLE_CONFIGMAP()));
      this.minimumMemoryOverhead = BoxesRunTime.unboxToLong(kubernetesConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MIN_MEMORY_OVERHEAD()));
      this.memoryOverheadFactor = kubernetesConf.contains(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY_OVERHEAD_FACTOR()) ? BoxesRunTime.unboxToDouble(kubernetesConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY_OVERHEAD_FACTOR())) : BoxesRunTime.unboxToDouble(kubernetesConf.get(Config$.MODULE$.MEMORY_OVERHEAD_FACTOR()));
      this.execResources = org.apache.spark.resource.ResourceProfile..MODULE$.getResourcesForClusterManager(resourceProfile.id(), resourceProfile.executorResources(), this.minimumMemoryOverhead(), this.memoryOverheadFactor(), kubernetesConf.sparkConf(), this.isPythonApp(), scala.Predef..MODULE$.Map().empty());
      scala.Predef..MODULE$.assert(this.execResources().cores().nonEmpty());
      this.executorMemoryString = this.execResources().executorMemoryMiB() + "m";
      this.executorCoresRequest = this.isDefaultProfile() && kubernetesConf.sparkConf().contains(Config$.MODULE$.KUBERNETES_EXECUTOR_REQUEST_CORES()) ? (String)((Option)kubernetesConf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_REQUEST_CORES())).get() : this.execResources().cores().get().toString();
      this.executorLimitCores = (Option)kubernetesConf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_LIMIT_CORES());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
