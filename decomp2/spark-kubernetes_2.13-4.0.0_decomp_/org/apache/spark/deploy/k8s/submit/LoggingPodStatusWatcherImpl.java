package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.fabric8.kubernetes.client.Watcher.Action;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c!B\f\u0019\u0001i!\u0003\u0002C\u001c\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u000bu\u0002A\u0011\u0001 \t\u000f\u0005\u0003!\u0019!C\u0005\u0005\"1\u0001\u000b\u0001Q\u0001\n\rCq!\u0015\u0001A\u0002\u0013%!\u000bC\u0004X\u0001\u0001\u0007I\u0011\u0002-\t\ry\u0003\u0001\u0015)\u0003T\u0011\u001dy\u0006\u00011A\u0005\nICq\u0001\u0019\u0001A\u0002\u0013%\u0011\r\u0003\u0004d\u0001\u0001\u0006Ka\u0015\u0005\bI\u0002\u0001\r\u0011\"\u0003f\u0011\u001d9\b\u00011A\u0005\naDaA\u001f\u0001!B\u00131\u0007\"B>\u0001\t\u0013\u0011\u0005\"\u0002?\u0001\t\u0003j\b\"\u0002@\u0001\t\u0003z\bbBA\u0015\u0001\u0011\u0005\u00131\u0006\u0005\u0007\u0003S\u0001A\u0011I?\t\r\u0005e\u0002\u0001\"\u0003~\u0011\u001d\tY\u0004\u0001C\u0005\u0003{Aa!a\u0010\u0001\t\u0013i\bbBA!\u0001\u0011\u0005\u00131\t\u0002\u001c\u0019><w-\u001b8h!>$7\u000b^1ukN<\u0016\r^2iKJLU\u000e\u001d7\u000b\u0005eQ\u0012AB:vE6LGO\u0003\u0002\u001c9\u0005\u00191\u000eO:\u000b\u0005uq\u0012A\u00023fa2|\u0017P\u0003\u0002 A\u0005)1\u000f]1sW*\u0011\u0011EI\u0001\u0007CB\f7\r[3\u000b\u0003\r\n1a\u001c:h'\u0011\u0001Q%L\u0019\u0011\u0005\u0019ZS\"A\u0014\u000b\u0005!J\u0013\u0001\u00027b]\u001eT\u0011AK\u0001\u0005U\u00064\u0018-\u0003\u0002-O\t1qJ\u00196fGR\u0004\"AL\u0018\u000e\u0003aI!\u0001\r\r\u0003/1{wmZ5oOB{Gm\u0015;biV\u001cx+\u0019;dQ\u0016\u0014\bC\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b\u001f\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u001c4\u0005\u001daunZ4j]\u001e\fAaY8oM\u000e\u0001\u0001C\u0001\u001e<\u001b\u0005Q\u0012B\u0001\u001f\u001b\u0005QYUOY3s]\u0016$Xm\u001d#sSZ,'oQ8oM\u00061A(\u001b8jiz\"\"a\u0010!\u0011\u00059\u0002\u0001\"B\u001c\u0003\u0001\u0004I\u0014!B1qa&#W#A\"\u0011\u0005\u0011keBA#L!\t1\u0015*D\u0001H\u0015\tA\u0005(\u0001\u0004=e>|GO\u0010\u0006\u0002\u0015\u0006)1oY1mC&\u0011A*S\u0001\u0007!J,G-\u001a4\n\u00059{%AB*ue&twM\u0003\u0002M\u0013\u00061\u0011\r\u001d9JI\u0002\nA\u0002]8e\u0007>l\u0007\u000f\\3uK\u0012,\u0012a\u0015\t\u0003)Vk\u0011!S\u0005\u0003-&\u0013qAQ8pY\u0016\fg.\u0001\tq_\u0012\u001cu.\u001c9mKR,Gm\u0018\u0013fcR\u0011\u0011\f\u0018\t\u0003)jK!aW%\u0003\tUs\u0017\u000e\u001e\u0005\b;\u001a\t\t\u00111\u0001T\u0003\rAH%M\u0001\u000ea>$7i\\7qY\u0016$X\r\u001a\u0011\u0002-I,7o\\;sG\u0016$vn\\(mIJ+7-Z5wK\u0012\f!D]3t_V\u00148-\u001a+p_>cGMU3dK&4X\rZ0%KF$\"!\u00172\t\u000fuK\u0011\u0011!a\u0001'\u00069\"/Z:pkJ\u001cW\rV8p\u001f2$'+Z2fSZ,G\rI\u0001\u0004a>$W#\u00014\u0011\u0007Q;\u0017.\u0003\u0002i\u0013\n1q\n\u001d;j_:\u0004\"A[;\u000e\u0003-T!\u0001\\7\u0002\u000b5|G-\u001a7\u000b\u00059|\u0017aA1qS*\u0011\u0001/]\u0001\u000bWV\u0014WM\u001d8fi\u0016\u001c(B\u0001:t\u0003\u001d1\u0017M\u0019:jGbR\u0011\u0001^\u0001\u0003S>L!A^6\u0003\u0007A{G-A\u0004q_\u0012|F%Z9\u0015\u0005eK\bbB/\r\u0003\u0003\u0005\rAZ\u0001\u0005a>$\u0007%A\u0003qQ\u0006\u001cX-A\u0003sKN,G\u000fF\u0001Z\u00035)g/\u001a8u%\u0016\u001cW-\u001b<fIR)\u0011,!\u0001\u0002(!9\u00111\u0001\tA\u0002\u0005\u0015\u0011AB1di&|g\u000e\u0005\u0003\u0002\b\u0005\u0005b\u0002BA\u0005\u00037qA!a\u0003\u0002\u00189!\u0011QBA\u000b\u001d\u0011\ty!a\u0005\u000f\u0007\u0019\u000b\t\"C\u0001u\u0013\t\u00118/\u0003\u0002qc&\u0019\u0011\u0011D8\u0002\r\rd\u0017.\u001a8u\u0013\u0011\ti\"a\b\u0002\u000f]\u000bGo\u00195fe*\u0019\u0011\u0011D8\n\t\u0005\r\u0012Q\u0005\u0002\u0007\u0003\u000e$\u0018n\u001c8\u000b\t\u0005u\u0011q\u0004\u0005\u0006IB\u0001\r![\u0001\b_:\u001cEn\\:f)\rI\u0016Q\u0006\u0005\b\u0003_\t\u0002\u0019AA\u0019\u0003\u0005)\u0007\u0003BA\u001a\u0003ki!!a\b\n\t\u0005]\u0012q\u0004\u0002\u0011/\u0006$8\r[3s\u000bb\u001cW\r\u001d;j_:\fQ\u0002\\8h\u0019>twm\u0015;biV\u001c\u0018\u0001\u00045bg\u000e{W\u000e\u001d7fi\u0016$G#A*\u0002\u0015\rdwn]3XCR\u001c\u0007.A\u0006xCR\u001c\u0007n\u0014:Ti>\u0004HcA*\u0002F!1\u0011q\t\fA\u0002\r\u000b1a]%e\u0001"
)
public class LoggingPodStatusWatcherImpl implements LoggingPodStatusWatcher, Logging {
   private final KubernetesDriverConf conf;
   private final String appId;
   private boolean podCompleted;
   private boolean resourceTooOldReceived;
   private Option pod;
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

   private String appId() {
      return this.appId;
   }

   private boolean podCompleted() {
      return this.podCompleted;
   }

   private void podCompleted_$eq(final boolean x$1) {
      this.podCompleted = x$1;
   }

   private boolean resourceTooOldReceived() {
      return this.resourceTooOldReceived;
   }

   private void resourceTooOldReceived_$eq(final boolean x$1) {
      this.resourceTooOldReceived = x$1;
   }

   private Option pod() {
      return this.pod;
   }

   private void pod_$eq(final Option x$1) {
      this.pod = x$1;
   }

   private String phase() {
      return (String)this.pod().map((x$1) -> x$1.getStatus().getPhase()).getOrElse(() -> "unknown");
   }

   public void reset() {
      this.resourceTooOldReceived_$eq(false);
   }

   public void eventReceived(final Watcher.Action action, final Pod pod) {
      this.pod_$eq(.MODULE$.apply(pod));
      if (Action.DELETED.equals(action) ? true : Action.ERROR.equals(action)) {
         this.closeWatch();
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         this.logLongStatus();
         if (this.hasCompleted()) {
            this.closeWatch();
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }
   }

   public void onClose(final WatcherException e) {
      this.logDebug((Function0)(() -> {
         String var10000 = this.appId();
         return "Stopping watching application " + var10000 + " with last-observed phase " + this.phase();
      }));
      if (e != null && e.isHttpGone()) {
         this.resourceTooOldReceived_$eq(true);
         this.logDebug((Function0)(() -> "Got HTTP Gone code, resource version changed in k8s api: " + e));
      } else {
         this.closeWatch();
      }
   }

   public void onClose() {
      this.logDebug((Function0)(() -> {
         String var10000 = this.appId();
         return "Stopping watching application " + var10000 + " with last-observed phase " + this.phase();
      }));
      this.closeWatch();
   }

   private void logLongStatus() {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"State changed, new state: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POD_STATE..MODULE$, this.pod().map((pod) -> KubernetesUtils$.MODULE$.formatPodState(pod)).getOrElse(() -> "unknown"))}))))));
   }

   private boolean hasCompleted() {
      boolean var4;
      label32: {
         String var10000 = this.phase();
         String var1 = "Succeeded";
         if (var10000 == null) {
            if (var1 == null) {
               break label32;
            }
         } else if (var10000.equals(var1)) {
            break label32;
         }

         var10000 = this.phase();
         String var2 = "Failed";
         if (var10000 == null) {
            if (var2 == null) {
               break label32;
            }
         } else if (var10000.equals(var2)) {
            break label32;
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   private synchronized void closeWatch() {
      this.podCompleted_$eq(true);
      this.notifyAll();
   }

   public boolean watchOrStop(final String sId) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Waiting for application ", "} with application ID "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_NAME..MODULE$, this.conf.appName())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " and submission ID ", " to finish..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.appId()), new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, sId)}))))));
      long interval = BoxesRunTime.unboxToLong(this.conf.get(Config$.MODULE$.REPORT_INTERVAL()));
      synchronized(this){}

      try {
         while(!this.podCompleted() && !this.resourceTooOldReceived()) {
            this.wait(interval);
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application status for ", " (phase: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.appId()), new MDC(org.apache.spark.internal.LogKeys.POD_PHASE..MODULE$, this.phase())})))));
         }
      } catch (Throwable var6) {
         throw var6;
      }

      if (this.podCompleted()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> (MessageWithContext)this.pod().map((p) -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Container final statuses:\\n\\n", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STATUS..MODULE$, KubernetesUtils$.MODULE$.containersDescription(p, KubernetesUtils$.MODULE$.containersDescription$default$2()))})))).getOrElse(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No containers were found in the driver pod."})))).log(scala.collection.immutable.Nil..MODULE$))));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application ", " with application ID "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_NAME..MODULE$, this.conf.appName())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " and submission ID ", " finished"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.appId()), new MDC(org.apache.spark.internal.LogKeys.SUBMISSION_ID..MODULE$, sId)}))))));
      }

      return this.podCompleted();
   }

   public LoggingPodStatusWatcherImpl(final KubernetesDriverConf conf) {
      this.conf = conf;
      Logging.$init$(this);
      this.appId = conf.appId();
      this.podCompleted = false;
      this.resourceTooOldReceived = false;
      this.pod = .MODULE$.empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
