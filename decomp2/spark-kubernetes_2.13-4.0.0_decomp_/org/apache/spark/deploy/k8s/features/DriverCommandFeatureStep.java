package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.deploy.k8s.submit.JavaMainAppResource;
import org.apache.spark.deploy.k8s.submit.MainAppResource;
import org.apache.spark.deploy.k8s.submit.PythonMainAppResource;
import org.apache.spark.deploy.k8s.submit.RMainAppResource;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4QAC\u0006\u0001#]A\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\u0006]\u0001!\ta\f\u0005\u0006e\u0001!\te\r\u0005\u0006s\u0001!\tE\u000f\u0005\u0006\u0013\u0002!IA\u0013\u0005\u0007\u001d\u0002!\t!E(\t\u000bA\u0003A\u0011B)\t\u000bQ\u0003A\u0011B+\t\u000ba\u0003A\u0011B-\u00031\u0011\u0013\u0018N^3s\u0007>lW.\u00198e\r\u0016\fG/\u001e:f'R,\u0007O\u0003\u0002\r\u001b\u0005Aa-Z1ukJ,7O\u0003\u0002\u000f\u001f\u0005\u00191\u000eO:\u000b\u0005A\t\u0012A\u00023fa2|\u0017P\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h'\u0011\u0001\u0001D\b\u0012\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g!\ty\u0002%D\u0001\f\u0013\t\t3BA\u000eLk\n,'O\\3uKN4U-\u0019;ve\u0016\u001cuN\u001c4jON#X\r\u001d\t\u0003G\u0019j\u0011\u0001\n\u0006\u0003KE\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003O\u0011\u0012q\u0001T8hO&tw-\u0001\u0003d_:47\u0001\u0001\t\u0003W1j\u0011!D\u0005\u0003[5\u0011AcS;cKJtW\r^3t\tJLg/\u001a:D_:4\u0017A\u0002\u001fj]&$h\b\u0006\u00021cA\u0011q\u0004\u0001\u0005\u0006Q\t\u0001\rAK\u0001\rG>tg-[4ve\u0016\u0004v\u000e\u001a\u000b\u0003i]\u0002\"aK\u001b\n\u0005Yj!\u0001C*qCJ\\\u0007k\u001c3\t\u000ba\u001a\u0001\u0019\u0001\u001b\u0002\u0007A|G-\u0001\u0011hKR\fE\rZ5uS>t\u0017\r\u001c)pINK8\u000f^3n!J|\u0007/\u001a:uS\u0016\u001cH#A\u001e\u0011\tq\u001aeI\u0012\b\u0003{\u0005\u0003\"A\u0010\u000e\u000e\u0003}R!\u0001Q\u0015\u0002\rq\u0012xn\u001c;?\u0013\t\u0011%$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\t\u0016\u00131!T1q\u0015\t\u0011%\u0004\u0005\u0002=\u000f&\u0011\u0001*\u0012\u0002\u0007'R\u0014\u0018N\\4\u0002!\r|gNZ5hkJ,gi\u001c:KCZ\fGc\u0001\u001bL\u0019\")\u0001(\u0002a\u0001i!)Q*\u0002a\u0001\r\u0006\u0019!/Z:\u0002)\u0015tg/\u001b:p]6,g\u000e\u001e,be&\f'\r\\3t+\u0005Y\u0014AE2p]\u001aLw-\u001e:f\r>\u0014\b+\u001f;i_:$2\u0001\u000e*T\u0011\u0015At\u00011\u00015\u0011\u0015iu\u00011\u0001G\u00035\u0019wN\u001c4jOV\u0014XMR8s%R\u0019AGV,\t\u000baB\u0001\u0019\u0001\u001b\t\u000b5C\u0001\u0019\u0001$\u0002'\t\f7/\u001a#sSZ,'oQ8oi\u0006Lg.\u001a:\u0015\u0007iC\u0017\u000e\u0005\u0002\\M6\tAL\u0003\u0002^=\u0006)Qn\u001c3fY*\u0011q\fY\u0001\u0004CBL'BA1c\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u0003G\u0012\fqAZ1ce&\u001c\u0007HC\u0001f\u0003\tIw.\u0003\u0002h9\n\u00012i\u001c8uC&tWM\u001d\"vS2$WM\u001d\u0005\u0006q%\u0001\r\u0001\u000e\u0005\u0006U&\u0001\rAR\u0001\te\u0016\u001cx.\u001e:dK\u0002"
)
public class DriverCommandFeatureStep implements KubernetesFeatureConfigStep, Logging {
   private final KubernetesDriverConf conf;
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

   public SparkPod configurePod(final SparkPod pod) {
      MainAppResource var3 = this.conf.mainAppResource();
      if (var3 instanceof JavaMainAppResource var4) {
         Option res = var4.primaryResource();
         return this.configureForJava(pod, (String)res.getOrElse(() -> "spark-internal"));
      } else if (var3 instanceof PythonMainAppResource var6) {
         String res = var6.primaryResource();
         return this.configureForPython(pod, res);
      } else if (var3 instanceof RMainAppResource var8) {
         String res = var8.primaryResource();
         return this.configureForR(pod, res);
      } else {
         throw new MatchError(var3);
      }
   }

   public scala.collection.immutable.Map getAdditionalPodSystemProperties() {
      MainAppResource var3 = this.conf.mainAppResource();
      String var10000;
      if (var3 instanceof JavaMainAppResource) {
         var10000 = Constants$.MODULE$.APP_RESOURCE_TYPE_JAVA();
      } else if (var3 instanceof PythonMainAppResource) {
         var10000 = Constants$.MODULE$.APP_RESOURCE_TYPE_PYTHON();
      } else {
         if (!(var3 instanceof RMainAppResource)) {
            throw new MatchError(var3);
         }

         var10000 = Constants$.MODULE$.APP_RESOURCE_TYPE_R();
      }

      String appType = var10000;
      return (scala.collection.immutable.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Config$.MODULE$.APP_RESOURCE_TYPE().key()), appType)})));
   }

   private SparkPod configureForJava(final SparkPod pod, final String res) {
      boolean x$2 = false;
      Option x$3 = KubernetesUtils$.MODULE$.renameMainAppResource$default$2();
      String newResName = KubernetesUtils$.MODULE$.renameMainAppResource(res, x$3, false);
      Container driverContainer = this.baseDriverContainer(pod, newResName).build();
      return new SparkPod(pod.pod(), driverContainer);
   }

   public scala.collection.immutable.Map environmentVariables() {
      return scala.sys.package..MODULE$.env();
   }

   private SparkPod configureForPython(final SparkPod pod, final String res) {
      if (((Option)this.conf.get(Config$.MODULE$.PYSPARK_MAJOR_PYTHON_VERSION())).isDefined()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " was deprecated in Spark 3.1. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, Config$.MODULE$.PYSPARK_MAJOR_PYTHON_VERSION().key())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Please set '", "' and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, org.apache.spark.internal.config.package..MODULE$.PYSPARK_PYTHON().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"'", "' "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG3..MODULE$, org.apache.spark.internal.config.package..MODULE$.PYSPARK_DRIVER_PYTHON().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"configurations or ", " and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG4..MODULE$, Constants$.MODULE$.ENV_PYSPARK_PYTHON())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " environment variables instead."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG5..MODULE$, Constants$.MODULE$.ENV_PYSPARK_DRIVER_PYTHON())}))))));
      }

      Seq pythonEnvs = KubernetesUtils$.MODULE$.buildEnvVars(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_PYSPARK_PYTHON()), ((Option)this.conf.get(org.apache.spark.internal.config.package..MODULE$.PYSPARK_PYTHON())).orElse(() -> this.environmentVariables().get(Constants$.MODULE$.ENV_PYSPARK_PYTHON())).orNull(scala..less.colon.less..MODULE$.refl())), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_PYSPARK_DRIVER_PYTHON()), ((Option)this.conf.get(org.apache.spark.internal.config.package..MODULE$.PYSPARK_DRIVER_PYTHON())).orElse(() -> (Option)this.conf.get(org.apache.spark.internal.config.package..MODULE$.PYSPARK_PYTHON())).orElse(() -> this.environmentVariables().get(Constants$.MODULE$.ENV_PYSPARK_DRIVER_PYTHON())).orElse(() -> this.environmentVariables().get(Constants$.MODULE$.ENV_PYSPARK_PYTHON())).orNull(scala..less.colon.less..MODULE$.refl())), scala.collection.immutable.Nil..MODULE$)));
      String newResName = KubernetesUtils$.MODULE$.renameMainAppResource(res, scala.Option..MODULE$.apply(this.conf.sparkConf()), true);
      Container pythonContainer = ((ContainerBuilder)this.baseDriverContainer(pod, newResName).addAllToEnv(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(pythonEnvs).asJava())).build();
      return new SparkPod(pod.pod(), pythonContainer);
   }

   private SparkPod configureForR(final SparkPod pod, final String res) {
      Container rContainer = this.baseDriverContainer(pod, res).build();
      return new SparkPod(pod.pod(), rContainer);
   }

   private ContainerBuilder baseDriverContainer(final SparkPod pod, final String resource) {
      Seq proxyUserArgs = (Seq)scala.collection.immutable.Nil..MODULE$;
      if (!this.conf.proxyUser().isEmpty()) {
         proxyUserArgs = (Seq)proxyUserArgs.$colon$plus("--proxy-user");
         proxyUserArgs = (Seq)proxyUserArgs.$colon$plus(this.conf.proxyUser().get());
      }

      return (ContainerBuilder)(new ContainerBuilder(pod.container())).addToArgs(new String[]{"driver"}).addToArgs((String[])proxyUserArgs.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))).addToArgs(new String[]{"--properties-file", Constants$.MODULE$.SPARK_CONF_PATH()}).addToArgs(new String[]{"--class", this.conf.mainClass()}).addToArgs(new String[]{resource}).addToArgs(this.conf.appArgs());
   }

   public DriverCommandFeatureStep(final KubernetesDriverConf conf) {
      this.conf = conf;
      KubernetesFeatureConfigStep.$init$(this);
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
