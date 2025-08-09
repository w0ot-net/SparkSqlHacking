package org.apache.spark.deploy.k8s;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
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
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dc!\u0002\u000e\u001c\u0001})\u0003\"\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a7\u0011!9\u0004A!b\u0001\n\u0003A\u0004\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011\u001d\u0003!Q1A\u0005\u0002aB\u0001\u0002\u0013\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\t\u0013\u0002\u0011)\u0019!C\u0001\u0015\"AQ\f\u0001B\u0001B\u0003%1\n\u0003\u0005_\u0001\t\u0015\r\u0011\"\u0001`\u0011!\u0019\u0007A!A!\u0002\u0013\u0001\u0007\"\u00023\u0001\t\u0003)\u0007\"\u00027\u0001\t\u0003i\u0007bB9\u0001\u0005\u0004%\t\u0005\u000f\u0005\u0007e\u0002\u0001\u000b\u0011B\u001d\t\u000bM\u0004A\u0011I7\t\u000bQ\u0004A\u0011I7\t\u000bU\u0004A\u0011I7\t\u000bY\u0004A\u0011I7\t\u000b]\u0004A\u0011I7\t\u000ba\u0004A\u0011I=\t\u000f\u00055\u0001\u0001\"\u0011\u0002\u0010!9\u00111\u0003\u0001\u0005\n\u0005UqACA\u00117\u0005\u0005\t\u0012A\u0010\u0002$\u0019I!dGA\u0001\u0012\u0003y\u0012Q\u0005\u0005\u0007I^!\t!!\f\t\u0013\u0005=r#%A\u0005\u0002\u0005E\"AF&vE\u0016\u0014h.\u001a;fg\u0016CXmY;u_J\u001cuN\u001c4\u000b\u0005qi\u0012aA69g*\u0011adH\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u001c2\u0001\u0001\u0014+!\t9\u0003&D\u0001\u001c\u0013\tI3D\u0001\bLk\n,'O\\3uKN\u001cuN\u001c4\u0011\u0005-rS\"\u0001\u0017\u000b\u00055z\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005=b#a\u0002'pO\u001eLgnZ\u0001\ngB\f'o[\"p]\u001a\u001c\u0001\u0001\u0005\u00024i5\tq$\u0003\u00026?\tI1\u000b]1sW\u000e{gNZ\u0005\u0003a!\nQ!\u00199q\u0013\u0012,\u0012!\u000f\t\u0003u\rs!aO!\u0011\u0005qzT\"A\u001f\u000b\u0005y\n\u0014A\u0002\u001fs_>$hHC\u0001A\u0003\u0015\u00198-\u00197b\u0013\t\u0011u(\u0001\u0004Qe\u0016$WMZ\u0005\u0003\t\u0016\u0013aa\u0015;sS:<'B\u0001\"@\u0003\u0019\t\u0007\u000f]%eA\u0005QQ\r_3dkR|'/\u00133\u0002\u0017\u0015DXmY;u_JLE\rI\u0001\nIJLg/\u001a:Q_\u0012,\u0012a\u0013\t\u0004\u00196{U\"A \n\u00059{$AB(qi&|g\u000e\u0005\u0002Q76\t\u0011K\u0003\u0002S'\u0006)Qn\u001c3fY*\u0011A+V\u0001\u0004CBL'B\u0001,X\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u00031f\u000bqAZ1ce&\u001c\u0007HC\u0001[\u0003\tIw.\u0003\u0002]#\n\u0019\u0001k\u001c3\u0002\u0015\u0011\u0014\u0018N^3s!>$\u0007%A\tsKN|WO]2f!J|g-\u001b7f\u0013\u0012,\u0012\u0001\u0019\t\u0003\u0019\u0006L!AY \u0003\u0007%sG/\u0001\nsKN|WO]2f!J|g-\u001b7f\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0004gO\"L'n\u001b\t\u0003O\u0001AQ\u0001\r\u0006A\u0002IBQa\u000e\u0006A\u0002eBQa\u0012\u0006A\u0002eBQ!\u0013\u0006A\u0002-CqA\u0018\u0006\u0011\u0002\u0003\u0007\u0001-\u0001\u000bfq\u0016\u001cW\u000f^8s\u001d>$WmU3mK\u000e$xN]\u000b\u0002]B!!h\\\u001d:\u0013\t\u0001XIA\u0002NCB\f!C]3t_V\u00148-\u001a(b[\u0016\u0004&/\u001a4jq\u0006\u0019\"/Z:pkJ\u001cWMT1nKB\u0013XMZ5yA\u00051A.\u00192fYN\f1\"\u001a8wSJ|g.\\3oi\u0006Y\u0011M\u001c8pi\u0006$\u0018n\u001c8t\u0003]\u0019Xm\u0019:fi:\u000bW.Z:U_6{WO\u001c;QCRD7/A\ftK\u000e\u0014X\r^#om:\u000bW.Z:U_.+\u0017PU3gg\u00069ao\u001c7v[\u0016\u001cX#\u0001>\u0011\u000bm\f\t!a\u0002\u000f\u0005qthB\u0001\u001f~\u0013\u0005\u0001\u0015BA@@\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u0001\u0002\u0006\t\u00191+Z9\u000b\u0005}|\u0004cA\u0014\u0002\n%\u0019\u00111B\u000e\u0003)-+(-\u001a:oKR,7OV8mk6,7\u000b]3d\u00035\u00198\r[3ek2,'OT1nKV\u0011\u0011\u0011\u0003\t\u0004\u00196K\u0014aE2iK\u000e\\W\t_3dkR|'/\u00128w\u0017\u0016LH\u0003BA\f\u0003;\u00012\u0001TA\r\u0013\r\tYb\u0010\u0002\b\u0005>|G.Z1o\u0011\u0019\ty\"\u0006a\u0001s\u0005\u00191.Z=\u0002--+(-\u001a:oKR,7/\u0012=fGV$xN]\"p]\u001a\u0004\"aJ\f\u0014\u0007]\t9\u0003E\u0002M\u0003SI1!a\u000b@\u0005\u0019\te.\u001f*fMR\u0011\u00111E\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005M\"f\u00011\u00026-\u0012\u0011q\u0007\t\u0005\u0003s\t\u0019%\u0004\u0002\u0002<)!\u0011QHA \u0003%)hn\u00195fG.,GMC\u0002\u0002B}\n!\"\u00198o_R\fG/[8o\u0013\u0011\t)%a\u000f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public class KubernetesExecutorConf extends KubernetesConf implements Logging {
   private final String appId;
   private final String executorId;
   private final Option driverPod;
   private final int resourceProfileId;
   private final String resourceNamePrefix;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static int $lessinit$greater$default$5() {
      return KubernetesExecutorConf$.MODULE$.$lessinit$greater$default$5();
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

   public String executorId() {
      return this.executorId;
   }

   public Option driverPod() {
      return this.driverPod;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public scala.collection.immutable.Map executorNodeSelector() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_EXECUTOR_NODE_SELECTOR_PREFIX());
   }

   public String resourceNamePrefix() {
      return this.resourceNamePrefix;
   }

   public scala.collection.immutable.Map labels() {
      scala.collection.immutable.Map presetLabels = (scala.collection.immutable.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_VERSION_LABEL()), org.apache.spark.package..MODULE$.SPARK_VERSION()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_EXECUTOR_ID_LABEL()), this.executorId()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_APP_ID_LABEL()), this.appId()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_APP_NAME_LABEL()), KubernetesConf$.MODULE$.getAppNameLabel(this.appName())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_ROLE_LABEL()), Constants$.MODULE$.SPARK_POD_EXECUTOR_ROLE()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_RESOURCE_PROFILE_ID_LABEL()), Integer.toString(this.resourceProfileId()))})));
      scala.collection.immutable.Map executorCustomLabels = (scala.collection.immutable.Map)KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_EXECUTOR_LABEL_PREFIX()).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return new Tuple2(k, org.apache.spark.util.Utils..MODULE$.substituteAppNExecIds(v, this.appId(), this.executorId()));
         } else {
            throw new MatchError(x0$1);
         }
      });
      presetLabels.keys().foreach((key) -> {
         $anonfun$labels$5(executorCustomLabels, key);
         return BoxedUnit.UNIT;
      });
      return (scala.collection.immutable.Map)executorCustomLabels.$plus$plus(presetLabels);
   }

   public scala.collection.immutable.Map environment() {
      return ((IterableOnceOps)super.sparkConf().getExecutorEnv().filter((p) -> BoxesRunTime.boxToBoolean($anonfun$environment$1(this, p)))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public scala.collection.immutable.Map annotations() {
      return (scala.collection.immutable.Map)KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_EXECUTOR_ANNOTATION_PREFIX()).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return new Tuple2(k, org.apache.spark.util.Utils..MODULE$.substituteAppNExecIds(v, this.appId(), this.executorId()));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public scala.collection.immutable.Map secretNamesToMountPaths() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_EXECUTOR_SECRETS_PREFIX());
   }

   public scala.collection.immutable.Map secretEnvNamesToKeyRefs() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(super.sparkConf(), Config$.MODULE$.KUBERNETES_EXECUTOR_SECRET_KEY_REF_PREFIX());
   }

   public Seq volumes() {
      return KubernetesVolumeUtils$.MODULE$.parseVolumesWithPrefix(super.sparkConf(), Config$.MODULE$.KUBERNETES_EXECUTOR_VOLUMES_PREFIX());
   }

   public Option schedulerName() {
      return scala.Option..MODULE$.apply(((Option)this.get(Config$.MODULE$.KUBERNETES_EXECUTOR_SCHEDULER_NAME())).getOrElse(() -> (String)((Option)this.get(Config$.MODULE$.KUBERNETES_SCHEDULER_NAME())).orNull(scala..less.colon.less..MODULE$.refl())));
   }

   private boolean checkExecutorEnvKey(final String key) {
      Regex executorEnvRegex = scala.collection.StringOps..MODULE$.r$extension(.MODULE$.augmentString("[-._a-zA-Z][-._a-zA-Z0-9]*"));
      if (executorEnvRegex.pattern().matcher(key).matches()) {
         return true;
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Invalid key: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, key)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"a valid environment variable name must consist of alphabetic characters, "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"digits, '_', '-', or '.', and must not start with a digit. "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Regex used for validation is '", "'"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ENV_REGEX..MODULE$, executorEnvRegex)}))))));
         return false;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$labels$5(final scala.collection.immutable.Map executorCustomLabels$1, final String key) {
      .MODULE$.require(!executorCustomLabels$1.contains(key), () -> "Custom executor labels cannot contain " + key + " as it is reserved for Spark.");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$environment$1(final KubernetesExecutorConf $this, final Tuple2 p) {
      return $this.checkExecutorEnvKey((String)p._1());
   }

   public KubernetesExecutorConf(final SparkConf sparkConf, final String appId, final String executorId, final Option driverPod, final int resourceProfileId) {
      super(sparkConf);
      this.appId = appId;
      this.executorId = executorId;
      this.driverPod = driverPod;
      this.resourceProfileId = resourceProfileId;
      Logging.$init$(this);
      this.resourceNamePrefix = (String)((Option)this.get(Config$.MODULE$.KUBERNETES_EXECUTOR_POD_NAME_PREFIX())).getOrElse(() -> KubernetesConf$.MODULE$.getResourceNamePrefix(this.appName()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
