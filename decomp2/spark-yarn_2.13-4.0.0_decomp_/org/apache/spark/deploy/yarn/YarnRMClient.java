package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.client.api.AMRMClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.webapp.util.WebAppUtils;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcEndpointRef;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uf!\u0002\n\u0014\u0001]i\u0002\"\u0002\u0016\u0001\t\u0003a\u0003\"C\u0018\u0001\u0001\u0004\u0005\r\u0011\"\u00031\u0011%\u0011\u0006\u00011AA\u0002\u0013%1\u000bC\u0005Z\u0001\u0001\u0007\t\u0011)Q\u0005c!I!\f\u0001a\u0001\u0002\u0004%Ia\u0017\u0005\nI\u0002\u0001\r\u00111A\u0005\n\u0015D\u0011b\u001a\u0001A\u0002\u0003\u0005\u000b\u0015\u0002/\t\u000f!\u0004\u0001\u0019!C\u0005S\"9Q\u000e\u0001a\u0001\n\u0013q\u0007B\u00029\u0001A\u0003&!\u000eC\u0003r\u0001\u0011\u0005!\u000fC\u0004\u0002\u001c\u0001!\t!!\b\t\u000f\u0005%\u0004\u0001\"\u0001\u0002l!I\u00111\u0010\u0001\u0012\u0002\u0013\u0005\u0011Q\u0010\u0005\b\u0003'\u0003A\u0011AAK\u0011\u001d\ty\n\u0001C\u0001\u0003CCq!!+\u0001\t\u0013\tYK\u0001\u0007ZCJt'+T\"mS\u0016tGO\u0003\u0002\u0015+\u0005!\u00110\u0019:o\u0015\t1r#\u0001\u0004eKBdw.\u001f\u0006\u00031e\tQa\u001d9be.T!AG\u000e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0012aA8sON\u0019\u0001A\b\u0013\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0003\u0005\nQa]2bY\u0006L!a\t\u0011\u0003\r\u0005s\u0017PU3g!\t)\u0003&D\u0001'\u0015\t9s#\u0001\u0005j]R,'O\\1m\u0013\tIcEA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!\f\t\u0003]\u0001i\u0011aE\u0001\tC6\u001cE.[3oiV\t\u0011\u0007E\u00023uqj\u0011a\r\u0006\u0003iU\n1!\u00199j\u0015\t1t'\u0001\u0004dY&,g\u000e\u001e\u0006\u0003)aR!!O\r\u0002\r!\fGm\\8q\u0013\tY4G\u0001\u0006B\u001bJk5\t\\5f]R\u0004\"!P(\u000f\u0005yjeBA M\u001d\t\u00015J\u0004\u0002B\u0015:\u0011!)\u0013\b\u0003\u0007\"s!\u0001R$\u000e\u0003\u0015S!AR\u0016\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012B\u0001\u000e\u001c\u0013\tI\u0014$\u0003\u0002\u0015q%\u0011agN\u0005\u0003iUJ!AT\u001a\u0002\u0015\u0005k%+T\"mS\u0016tG/\u0003\u0002Q#\n\u00012i\u001c8uC&tWM\u001d*fcV,7\u000f\u001e\u0006\u0003\u001dN\nA\"Y7DY&,g\u000e^0%KF$\"\u0001V,\u0011\u0005})\u0016B\u0001,!\u0005\u0011)f.\u001b;\t\u000fa\u001b\u0011\u0011!a\u0001c\u0005\u0019\u0001\u0010J\u0019\u0002\u0013\u0005l7\t\\5f]R\u0004\u0013\u0001E;j\u0011&\u001cHo\u001c:z\u0003\u0012$'/Z:t+\u0005a\u0006CA/b\u001d\tqv\f\u0005\u0002EA%\u0011\u0001\rI\u0001\u0007!J,G-\u001a4\n\u0005\t\u001c'AB*ue&twM\u0003\u0002aA\u0005!R/\u001b%jgR|'/_!eIJ,7o]0%KF$\"\u0001\u00164\t\u000fa3\u0011\u0011!a\u00019\u0006\tR/\u001b%jgR|'/_!eIJ,7o\u001d\u0011\u0002\u0015I,w-[:uKJ,G-F\u0001k!\ty2.\u0003\u0002mA\t9!i\\8mK\u0006t\u0017A\u0004:fO&\u001cH/\u001a:fI~#S-\u001d\u000b\u0003)>Dq\u0001W\u0005\u0002\u0002\u0003\u0007!.A\u0006sK\u001eL7\u000f^3sK\u0012\u0004\u0013\u0001\u0003:fO&\u001cH/\u001a:\u0015\u0015Q\u001bXO_A\u0002\u0003\u001f\tI\u0002C\u0003u\u0017\u0001\u0007A,\u0001\u0006ee&4XM\u001d%pgRDQA^\u0006A\u0002]\f!\u0002\u001a:jm\u0016\u0014\bk\u001c:u!\ty\u00020\u0003\u0002zA\t\u0019\u0011J\u001c;\t\u000bm\\\u0001\u0019\u0001?\u0002\t\r|gN\u001a\t\u0003{~l\u0011A \u0006\u0003w^J1!!\u0001\u007f\u0005EI\u0016M\u001d8D_:4\u0017nZ;sCRLwN\u001c\u0005\b\u0003\u000bY\u0001\u0019AA\u0004\u0003%\u0019\b/\u0019:l\u0007>tg\r\u0005\u0003\u0002\n\u0005-Q\"A\f\n\u0007\u00055qCA\u0005Ta\u0006\u00148nQ8oM\"9\u0011\u0011C\u0006A\u0002\u0005M\u0011!C;j\u0003\u0012$'/Z:t!\u0011y\u0012Q\u0003/\n\u0007\u0005]\u0001E\u0001\u0004PaRLwN\u001c\u0005\u00065.\u0001\r\u0001X\u0001\u0010GJ,\u0017\r^3BY2|7-\u0019;peR\u0001\u0012qDA\u0013\u0003O\tI#a\u000f\u0002@\u0005=\u0013\u0011\f\t\u0004]\u0005\u0005\u0012bAA\u0012'\ti\u0011,\u0019:o\u00032dwnY1u_JDQa\u001f\u0007A\u0002qDq!!\u0002\r\u0001\u0004\t9\u0001C\u0004\u0002,1\u0001\r!!\f\u0002\u0019\u0005\u0004\b/\u0011;uK6\u0004H/\u00133\u0011\t\u0005=\u0012qG\u0007\u0003\u0003cQA!a\r\u00026\u00059!/Z2pe\u0012\u001c(B\u0001\u001b8\u0013\u0011\tI$!\r\u0003)\u0005\u0003\b\u000f\\5dCRLwN\\!ui\u0016l\u0007\u000f^%e\u0011\u0019\ti\u0004\u0004a\u00019\u0006IAM]5wKJ,&\u000f\u001c\u0005\b\u0003\u0003b\u0001\u0019AA\"\u0003%!'/\u001b<feJ+g\r\u0005\u0003\u0002F\u0005-SBAA$\u0015\r\tIeF\u0001\u0004eB\u001c\u0017\u0002BA'\u0003\u000f\u0012aB\u00159d\u000b:$\u0007o\\5oiJ+g\rC\u0004\u0002R1\u0001\r!a\u0015\u0002\u0017M,7-\u001e:jifluM\u001d\t\u0005\u0003\u0013\t)&C\u0002\u0002X]\u0011qbU3dkJLG/_'b]\u0006<WM\u001d\u0005\b\u00037b\u0001\u0019AA/\u00039awnY1m%\u0016\u001cx.\u001e:dKN\u0004b!XA09\u0006\r\u0014bAA1G\n\u0019Q*\u00199\u0011\t\u0005=\u0012QM\u0005\u0005\u0003O\n\tDA\u0007M_\u000e\fGNU3t_V\u00148-Z\u0001\u000bk:\u0014XmZ5ti\u0016\u0014H#\u0002+\u0002n\u0005]\u0004bBA8\u001b\u0001\u0007\u0011\u0011O\u0001\u0007gR\fG/^:\u0011\t\u0005=\u00121O\u0005\u0005\u0003k\n\tD\u0001\fGS:\fG.\u00119qY&\u001c\u0017\r^5p]N#\u0018\r^;t\u0011!\tI(\u0004I\u0001\u0002\u0004a\u0016a\u00033jC\u001etwn\u001d;jGN\fA#\u001e8sK\u001eL7\u000f^3sI\u0011,g-Y;mi\u0012\u0012TCAA@U\ra\u0016\u0011Q\u0016\u0003\u0003\u0007\u0003B!!\"\u0002\u00106\u0011\u0011q\u0011\u0006\u0005\u0003\u0013\u000bY)A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\u0012\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0012\u0006\u001d%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u0019r-\u001a;B[&\u0003h)\u001b7uKJ\u0004\u0016M]1ngR1\u0011qSAM\u00037\u0003R!XA09rCQa_\bA\u0002qDa!!(\u0010\u0001\u0004a\u0016!\u00039s_bL()Y:f\u0003E9W\r^'bqJ+w-\u0011;uK6\u0004Ho\u001d\u000b\u0006o\u0006\r\u0016Q\u0015\u0005\b\u0003\u000b\u0001\u0002\u0019AA\u0004\u0011\u0019\t9\u000b\u0005a\u0001y\u0006A\u00110\u0019:o\u0007>tg-\u0001\u0007hKR,&\u000f\u001c\"z%6LE\rF\u0003]\u0003[\u000bI\f\u0003\u0004|#\u0001\u0007\u0011q\u0016\t\u0005\u0003c\u000b),\u0004\u0002\u00024*\u00111\u0010O\u0005\u0005\u0003o\u000b\u0019LA\u0007D_:4\u0017nZ;sCRLwN\u001c\u0005\u0007\u0003w\u000b\u0002\u0019\u0001/\u0002\tIl\u0017\n\u001a"
)
public class YarnRMClient implements Logging {
   private AMRMClient amClient;
   private String uiHistoryAddress;
   private boolean registered;
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

   private AMRMClient amClient() {
      return this.amClient;
   }

   private void amClient_$eq(final AMRMClient x$1) {
      this.amClient = x$1;
   }

   private String uiHistoryAddress() {
      return this.uiHistoryAddress;
   }

   private void uiHistoryAddress_$eq(final String x$1) {
      this.uiHistoryAddress = x$1;
   }

   private boolean registered() {
      return this.registered;
   }

   private void registered_$eq(final boolean x$1) {
      this.registered = x$1;
   }

   public void register(final String driverHost, final int driverPort, final YarnConfiguration conf, final SparkConf sparkConf, final Option uiAddress, final String uiHistoryAddress) {
      this.amClient_$eq(AMRMClient.createAMRMClient());
      this.amClient().init(conf);
      this.amClient().start();
      this.uiHistoryAddress_$eq(uiHistoryAddress);
      String trackingUrl = (String)uiAddress.getOrElse(() -> BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.ALLOW_HISTORY_SERVER_TRACKING_URL())) ? uiHistoryAddress : "");
      this.logInfo((Function0)(() -> "Registering the ApplicationMaster"));
      synchronized(this){}

      try {
         this.amClient().registerApplicationMaster(driverHost, driverPort, trackingUrl);
         this.registered_$eq(true);
      } catch (Throwable var10) {
         throw var10;
      }

   }

   public YarnAllocator createAllocator(final YarnConfiguration conf, final SparkConf sparkConf, final ApplicationAttemptId appAttemptId, final String driverUrl, final RpcEndpointRef driverRef, final SecurityManager securityMgr, final scala.collection.immutable.Map localResources) {
      .MODULE$.require(this.registered(), () -> "Must register AM before creating allocator.");
      return new YarnAllocator(driverUrl, driverRef, conf, sparkConf, this.amClient(), appAttemptId, securityMgr, localResources, SparkRackResolver$.MODULE$.get(conf), YarnAllocator$.MODULE$.$lessinit$greater$default$10());
   }

   public synchronized void unregister(final FinalApplicationStatus status, final String diagnostics) {
      if (this.registered()) {
         this.amClient().unregisterApplicationMaster(status, diagnostics, this.uiHistoryAddress());
      }

      if (this.amClient() != null) {
         this.amClient().stop();
      }
   }

   public String unregister$default$2() {
      return "";
   }

   public scala.collection.immutable.Map getAmIpFilterParams(final YarnConfiguration conf, final String proxyBase) {
      String prefix = WebAppUtils.getHttpSchemePrefix(conf);
      List proxies = WebAppUtils.getProxyHostsAndPortsForAmFilter(conf);
      Buffer hosts = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(proxies).asScala().map((proxy) -> (String)org.apache.spark.util.Utils..MODULE$.parseHostPort(proxy)._1());
      Buffer uriBases = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(proxies).asScala().map((proxy) -> prefix + proxy + proxyBase);
      scala.collection.immutable.Map params = (scala.collection.immutable.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("PROXY_HOSTS"), hosts.mkString(",")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("PROXY_URI_BASES"), uriBases.mkString(","))})));
      Iterable rmIds = scala.jdk.CollectionConverters..MODULE$.CollectionHasAsScala(conf.getStringCollection("yarn.resourcemanager.ha.rm-ids")).asScala();
      return rmIds != null && rmIds.nonEmpty() ? (scala.collection.immutable.Map)params.$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("RM_HA_URLS"), ((IterableOnceOps)rmIds.map((x$3) -> this.getUrlByRmId(conf, x$3))).mkString(","))) : params;
   }

   public int getMaxRegAttempts(final SparkConf sparkConf, final YarnConfiguration yarnConf) {
      Option sparkMaxAttempts = (Option)sparkConf.get(package$.MODULE$.MAX_APP_ATTEMPTS());
      int yarnMaxAttempts = yarnConf.getInt("yarn.resourcemanager.am.max-attempts", 2);
      if (sparkMaxAttempts instanceof Some var7) {
         int x = BoxesRunTime.unboxToInt(var7.value());
         return x <= yarnMaxAttempts ? x : yarnMaxAttempts;
      } else if (scala.None..MODULE$.equals(sparkMaxAttempts)) {
         return yarnMaxAttempts;
      } else {
         throw new MatchError(sparkMaxAttempts);
      }
   }

   private String getUrlByRmId(final Configuration conf, final String rmId) {
      String addressPropertyPrefix = YarnConfiguration.useHttps(conf) ? "yarn.resourcemanager.webapp.https.address" : "yarn.resourcemanager.webapp.address";
      String var10000;
      if (rmId != null && !rmId.isEmpty()) {
         if (rmId.startsWith(".")) {
            throw new IllegalStateException("rmId " + rmId + " should not already have '.' prepended.");
         }

         var10000 = addressPropertyPrefix + "." + rmId;
      } else {
         var10000 = addressPropertyPrefix;
      }

      String addressWithRmId = var10000;
      return conf.get(addressWithRmId);
   }

   public YarnRMClient() {
      Logging.$init$(this);
      this.registered = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
