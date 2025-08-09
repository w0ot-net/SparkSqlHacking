package org.apache.spark.deploy.k8s;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.deploy.k8s.features.DriverServiceFeatureStep$;
import org.apache.spark.deploy.k8s.submit.MainAppResource;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001B\u0010!\u0001-B\u0011B\u000e\u0001\u0003\u0002\u0003\u0006IaN\u001e\t\u0011q\u0002!Q1A\u0005\u0002uB\u0001b\u0013\u0001\u0003\u0002\u0003\u0006IA\u0010\u0005\t\u0019\u0002\u0011)\u0019!C\u0001\u001b\"AA\u000b\u0001B\u0001B\u0003%a\n\u0003\u0005V\u0001\t\u0015\r\u0011\"\u0001>\u0011!1\u0006A!A!\u0002\u0013q\u0004\u0002C,\u0001\u0005\u000b\u0007I\u0011\u0001-\t\u0011u\u0003!\u0011!Q\u0001\neC\u0001B\u0018\u0001\u0003\u0006\u0004%\ta\u0018\u0005\tG\u0002\u0011\t\u0011)A\u0005A\"AA\r\u0001B\u0001B\u0003%Q\rC\u0003l\u0001\u0011\u0005A\u000eC\u0003v\u0001\u0011\u0005a\u000f\u0003\u0005{\u0001!\u0015\r\u0011\"\u0001>\u0011\u001dY\bA1A\u0005BuBa\u0001 \u0001!\u0002\u0013q\u0004\"B?\u0001\t\u00032\b\"\u0002@\u0001\t\u00032\b\"B@\u0001\t\u00032\bBBA\u0001\u0001\u0011\u0005a\u000f\u0003\u0004\u0002\u0004\u0001!\tA\u001e\u0005\u0007\u0003\u000b\u0001A\u0011\t<\t\r\u0005\u001d\u0001\u0001\"\u0011w\u0011\u001d\tI\u0001\u0001C!\u0003\u0017Aa!!\n\u0001\t\u0003zv!CA%A\u0005\u0005\t\u0012AA&\r!y\u0002%!A\t\u0002\u00055\u0003BB6\u001d\t\u0003\t)\u0006C\u0005\u0002Xq\t\n\u0011\"\u0001\u0002Z\t!2*\u001e2fe:,G/Z:Ee&4XM]\"p]\u001aT!!\t\u0012\u0002\u0007-D4O\u0003\u0002$I\u00051A-\u001a9m_fT!!\n\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dB\u0013AB1qC\u000eDWMC\u0001*\u0003\ry'oZ\u0002\u0001'\r\u0001A\u0006\r\t\u0003[9j\u0011\u0001I\u0005\u0003_\u0001\u0012abS;cKJtW\r^3t\u0007>tg\r\u0005\u00022i5\t!G\u0003\u00024I\u0005A\u0011N\u001c;fe:\fG.\u0003\u00026e\t9Aj\\4hS:<\u0017!C:qCJ\\7i\u001c8g!\tA\u0014(D\u0001%\u0013\tQDEA\u0005Ta\u0006\u00148nQ8oM&\u0011aGL\u0001\u0006CB\u0004\u0018\nZ\u000b\u0002}A\u0011q\b\u0013\b\u0003\u0001\u001a\u0003\"!\u0011#\u000e\u0003\tS!a\u0011\u0016\u0002\rq\u0012xn\u001c;?\u0015\u0005)\u0015!B:dC2\f\u0017BA$E\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011J\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u001d#\u0015AB1qa&#\u0007%A\bnC&t\u0017\t\u001d9SKN|WO]2f+\u0005q\u0005CA(S\u001b\u0005\u0001&BA)!\u0003\u0019\u0019XOY7ji&\u00111\u000b\u0015\u0002\u0010\u001b\u0006Lg.\u00119q%\u0016\u001cx.\u001e:dK\u0006\u0001R.Y5o\u0003B\u0004(+Z:pkJ\u001cW\rI\u0001\n[\u0006Lgn\u00117bgN\f!\"\\1j]\u000ec\u0017m]:!\u0003\u001d\t\u0007\u000f]!sON,\u0012!\u0017\t\u00045nsT\"\u0001#\n\u0005q#%!B!se\u0006L\u0018\u0001C1qa\u0006\u0013xm\u001d\u0011\u0002\u0013A\u0014x\u000e_=Vg\u0016\u0014X#\u00011\u0011\u0007i\u000bg(\u0003\u0002c\t\n1q\n\u001d;j_:\f!\u0002\u001d:pqf,6/\u001a:!\u0003\u0015\u0019Gn\\2l!\t1\u0017.D\u0001h\u0015\tAG%\u0001\u0003vi&d\u0017B\u00016h\u0005\u0015\u0019En\\2l\u0003\u0019a\u0014N\\5u}QAQN\\8qcJ\u001cH\u000f\u0005\u0002.\u0001!)a'\u0004a\u0001o!)A(\u0004a\u0001}!)A*\u0004a\u0001\u001d\")Q+\u0004a\u0001}!)q+\u0004a\u00013\")a,\u0004a\u0001A\"9A-\u0004I\u0001\u0002\u0004)\u0017A\u00053sSZ,'OT8eKN+G.Z2u_J,\u0012a\u001e\t\u0005\u007fatd(\u0003\u0002z\u0015\n\u0019Q*\u00199\u0002#\u0011\u0014\u0018N^3s'\u0016\u0014h/[2f\u001d\u0006lW-\u0001\nsKN|WO]2f\u001d\u0006lW\r\u0015:fM&D\u0018a\u0005:fg>,(oY3OC6,\u0007K]3gSb\u0004\u0013A\u00027bE\u0016d7/A\u0006f]ZL'o\u001c8nK:$\u0018aC1o]>$\u0018\r^5p]N\fQb]3sm&\u001cW\rT1cK2\u001c\u0018AE:feZL7-Z!o]>$\u0018\r^5p]N\fqc]3de\u0016$h*Y7fgR{Wj\\;oiB\u000bG\u000f[:\u0002/M,7M]3u\u000b:4h*Y7fgR{7*Z=SK\u001a\u001c\u0018a\u0002<pYVlWm]\u000b\u0003\u0003\u001b\u0001b!a\u0004\u0002\u001a\u0005}a\u0002BA\t\u0003+q1!QA\n\u0013\u0005)\u0015bAA\f\t\u00069\u0001/Y2lC\u001e,\u0017\u0002BA\u000e\u0003;\u00111aU3r\u0015\r\t9\u0002\u0012\t\u0004[\u0005\u0005\u0012bAA\u0012A\t!2*\u001e2fe:,G/Z:W_2,X.Z*qK\u000e\fQb]2iK\u0012,H.\u001a:OC6,\u0007f\u0001\u0001\u0002*A!\u00111FA\u0019\u001b\t\tiCC\u0002\u00020\u0011\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019$!\f\u0003\u0011Us7\u000f^1cY\u0016D3\u0001AA\u001c!\u0011\tY#!\u000f\n\t\u0005m\u0012Q\u0006\u0002\r\t\u00164X\r\\8qKJ\f\u0005/\u001b\u0015\u0006\u0001\u0005}\u0012Q\t\t\u0005\u0003W\t\t%\u0003\u0003\u0002D\u00055\"!B*j]\u000e,\u0017EAA$\u0003\u0015!d\u0006\r\u00181\u0003QYUOY3s]\u0016$Xm\u001d#sSZ,'oQ8oMB\u0011Q\u0006H\n\u00049\u0005=\u0003c\u0001.\u0002R%\u0019\u00111\u000b#\u0003\r\u0005s\u0017PU3g)\t\tY%A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeN\u000b\u0003\u00037R3!ZA/W\t\ty\u0006\u0005\u0003\u0002b\u0005%TBAA2\u0015\u0011\t)'a\u001a\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0018\t&!\u00111NA2\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public class KubernetesDriverConf extends KubernetesConf implements Logging {
   private String driverServiceName;
   private final String appId;
   private final MainAppResource mainAppResource;
   private final String mainClass;
   private final String[] appArgs;
   private final Option proxyUser;
   private final Clock clock;
   private final String resourceNamePrefix;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static Clock $lessinit$greater$default$7() {
      return KubernetesDriverConf$.MODULE$.$lessinit$greater$default$7();
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

   public String appId() {
      return this.appId;
   }

   public MainAppResource mainAppResource() {
      return this.mainAppResource;
   }

   public String mainClass() {
      return this.mainClass;
   }

   public String[] appArgs() {
      return this.appArgs;
   }

   public Option proxyUser() {
      return this.proxyUser;
   }

   public scala.collection.immutable.Map driverNodeSelector() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_NODE_SELECTOR_PREFIX());
   }

   private String driverServiceName$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            String var10001 = this.resourceNamePrefix();
            String preferredServiceName = var10001 + DriverServiceFeatureStep$.MODULE$.DRIVER_SVC_POSTFIX();
            if (preferredServiceName.length() <= DriverServiceFeatureStep$.MODULE$.MAX_SERVICE_NAME_LENGTH()) {
               var10001 = preferredServiceName;
            } else {
               String randomServiceId = KubernetesUtils$.MODULE$.uniqueID(this.clock);
               String shorterServiceName = "spark-" + randomServiceId + DriverServiceFeatureStep$.MODULE$.DRIVER_SVC_POSTFIX();
               this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver's hostname would preferably be "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", but this is too long "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PREFERRED_SERVICE_NAME..MODULE$, preferredServiceName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(must be <= ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_SERVICE_NAME_LENGTH..MODULE$, BoxesRunTime.boxToInteger(DriverServiceFeatureStep$.MODULE$.MAX_SERVICE_NAME_LENGTH()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"characters). Falling back to use "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " as the driver service's name."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHORTER_SERVICE_NAME..MODULE$, shorterServiceName)}))))));
               var10001 = shorterServiceName;
            }

            this.driverServiceName = var10001;
            this.bitmap$0 = true;
         }
      } catch (Throwable var6) {
         throw var6;
      }

      return this.driverServiceName;
   }

   public String driverServiceName() {
      return !this.bitmap$0 ? this.driverServiceName$lzycompute() : this.driverServiceName;
   }

   public String resourceNamePrefix() {
      return this.resourceNamePrefix;
   }

   public scala.collection.immutable.Map labels() {
      scala.collection.immutable.Map presetLabels = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_VERSION_LABEL()), org.apache.spark.package..MODULE$.SPARK_VERSION()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_APP_ID_LABEL()), this.appId()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_APP_NAME_LABEL()), KubernetesConf$.MODULE$.getAppNameLabel(this.appName())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_ROLE_LABEL()), Constants$.MODULE$.SPARK_POD_DRIVER_ROLE())})));
      scala.collection.immutable.Map driverCustomLabels = (scala.collection.immutable.Map)KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_LABEL_PREFIX()).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return new Tuple2(k, org.apache.spark.util.Utils..MODULE$.substituteAppNExecIds(v, this.appId(), ""));
         } else {
            throw new MatchError(x0$1);
         }
      });
      presetLabels.keys().foreach((key) -> {
         $anonfun$labels$2(driverCustomLabels, key);
         return BoxedUnit.UNIT;
      });
      return (scala.collection.immutable.Map)driverCustomLabels.$plus$plus(presetLabels);
   }

   public scala.collection.immutable.Map environment() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_ENV_PREFIX());
   }

   public scala.collection.immutable.Map annotations() {
      return (scala.collection.immutable.Map)KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_ANNOTATION_PREFIX()).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return new Tuple2(k, org.apache.spark.util.Utils..MODULE$.substituteAppNExecIds(v, this.appId(), ""));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public scala.collection.immutable.Map serviceLabels() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_SERVICE_LABEL_PREFIX());
   }

   public scala.collection.immutable.Map serviceAnnotations() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_SERVICE_ANNOTATION_PREFIX());
   }

   public scala.collection.immutable.Map secretNamesToMountPaths() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_SECRETS_PREFIX());
   }

   public scala.collection.immutable.Map secretEnvNamesToKeyRefs() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_SECRET_KEY_REF_PREFIX());
   }

   public Seq volumes() {
      return KubernetesVolumeUtils$.MODULE$.parseVolumesWithPrefix(super.sparkConf(), Config$.MODULE$.KUBERNETES_DRIVER_VOLUMES_PREFIX());
   }

   public Option schedulerName() {
      return scala.Option..MODULE$.apply(((Option)this.get(Config$.MODULE$.KUBERNETES_DRIVER_SCHEDULER_NAME())).getOrElse(() -> (String)((Option)this.get(Config$.MODULE$.KUBERNETES_SCHEDULER_NAME())).orNull(scala..less.colon.less..MODULE$.refl())));
   }

   // $FF: synthetic method
   public static final void $anonfun$labels$2(final scala.collection.immutable.Map driverCustomLabels$1, final String key) {
      scala.Predef..MODULE$.require(!driverCustomLabels$1.contains(key), () -> "Label with key " + key + " is not allowed as it is reserved for Spark bookkeeping operations.");
   }

   public KubernetesDriverConf(final SparkConf sparkConf, final String appId, final MainAppResource mainAppResource, final String mainClass, final String[] appArgs, final Option proxyUser, final Clock clock) {
      super(sparkConf);
      this.appId = appId;
      this.mainAppResource = mainAppResource;
      this.mainClass = mainClass;
      this.appArgs = appArgs;
      this.proxyUser = proxyUser;
      this.clock = clock;
      Logging.$init$(this);
      Option custom = (Option)(org.apache.spark.util.Utils..MODULE$.isTesting() ? (Option)this.get(Config$.MODULE$.KUBERNETES_DRIVER_POD_NAME_PREFIX()) : scala.None..MODULE$);
      this.resourceNamePrefix = (String)custom.getOrElse(() -> KubernetesConf$.MODULE$.getResourceNamePrefix(this.appName()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
