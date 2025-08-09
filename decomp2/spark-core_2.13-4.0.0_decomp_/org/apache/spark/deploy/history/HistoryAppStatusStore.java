package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.executor.ExecutorLogUrlHandler;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.History$;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.AppStatusStore$;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.status.api.v1.ExecutorSummary;
import org.apache.spark.util.kvstore.KVStore;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a!\u0002\u0007\u000e\u0001E9\u0002\u0002\u0003\u0013\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u0013)\u0002!\u0011!Q\u0001\n-\u001a\u0004\"\u0002\u001b\u0001\t\u0003)\u0004b\u0002\u001e\u0001\u0005\u0004%Ia\u000f\u0005\u0007\u001b\u0002\u0001\u000b\u0011\u0002\u001f\t\u000f9\u0003!\u0019!C\u0005\u001f\"1a\u000b\u0001Q\u0001\nACQa\u0016\u0001\u0005BaCQa\u001c\u0001\u0005BADQa\u001d\u0001\u0005\nQDQa\u001e\u0001\u0005\na\u0014Q\u0003S5ti>\u0014\u00180\u00119q'R\fG/^:Ti>\u0014XM\u0003\u0002\u000f\u001f\u00059\u0001.[:u_JL(B\u0001\t\u0012\u0003\u0019!W\r\u001d7ps*\u0011!cE\u0001\u0006gB\f'o\u001b\u0006\u0003)U\ta!\u00199bG\",'\"\u0001\f\u0002\u0007=\u0014xmE\u0002\u00011y\u0001\"!\u0007\u000f\u000e\u0003iQ!aG\t\u0002\rM$\u0018\r^;t\u0013\ti\"D\u0001\bBaB\u001cF/\u0019;vgN#xN]3\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0005\u0005\n\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005\r\u0002#a\u0002'pO\u001eLgnZ\u0001\u0005G>tgm\u0001\u0001\u0011\u0005\u001dBS\"A\t\n\u0005%\n\"!C*qCJ\\7i\u001c8g\u0003\u0015\u0019Ho\u001c:f!\ta\u0013'D\u0001.\u0015\tqs&A\u0004lmN$xN]3\u000b\u0005A\n\u0012\u0001B;uS2L!AM\u0017\u0003\u000f-36\u000b^8sK&\u0011!\u0006H\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007YB\u0014\b\u0005\u00028\u00015\tQ\u0002C\u0003%\u0007\u0001\u0007a\u0005C\u0003+\u0007\u0001\u00071&A\u0007m_\u001e,&\u000f\u001c)biR,'O\\\u000b\u0002yA\u0019Q\b\u0011\"\u000e\u0003yR\u0011aP\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0003z\u0012aa\u00149uS>t\u0007CA\"K\u001d\t!\u0005\n\u0005\u0002F}5\taI\u0003\u0002HK\u00051AH]8pizJ!!\u0013 \u0002\rA\u0013X\rZ3g\u0013\tYEJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0013z\na\u0002\\8h+Jd\u0007+\u0019;uKJt\u0007%A\u0007m_\u001e,&\u000f\u001c%b]\u0012dWM]\u000b\u0002!B\u0011\u0011\u000bV\u0007\u0002%*\u00111+E\u0001\tKb,7-\u001e;pe&\u0011QK\u0015\u0002\u0016\u000bb,7-\u001e;pe2{w-\u0016:m\u0011\u0006tG\r\\3s\u00039awnZ+sY\"\u000bg\u000e\u001a7fe\u0002\nA\"\u001a=fGV$xN\u001d'jgR$\"!\u00176\u0011\u0007i{&M\u0004\u0002\\;:\u0011Q\tX\u0005\u0002\u007f%\u0011aLP\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0017MA\u0002TKFT!A\u0018 \u0011\u0005\rDW\"\u00013\u000b\u0005\u00154\u0017A\u0001<2\u0015\t9'$A\u0002ba&L!!\u001b3\u0003\u001f\u0015CXmY;u_J\u001cV/\\7befDQa\u001b\u0005A\u00021\f!\"Y2uSZ,wJ\u001c7z!\tiT.\u0003\u0002o}\t9!i\\8mK\u0006t\u0017aD3yK\u000e,Ho\u001c:Tk6l\u0017M]=\u0015\u0005\t\f\b\"\u0002:\n\u0001\u0004\u0011\u0015AC3yK\u000e,Ho\u001c:JI\u0006q!/\u001a9mC\u000e,Gj\\4Ve2\u001cHC\u00012v\u0011\u00151(\u00021\u0001c\u0003\u0011)\u00070Z2\u0002'I,\u0007\u000f\\1dK\u0016CXmY;u_JdunZ:\u0015\u0007\tL8\u0010C\u0003{\u0017\u0001\u0007!-\u0001\u0004t_V\u00148-\u001a\u0005\u0006y.\u0001\r!`\u0001\u0010]\u0016<X\t_3dkR|'\u000fT8hgB!1I \"C\u0013\tyHJA\u0002NCB\u0004"
)
public class HistoryAppStatusStore extends AppStatusStore implements Logging {
   private final Option logUrlPattern;
   private final ExecutorLogUrlHandler logUrlHandler;
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

   private Option logUrlPattern() {
      return this.logUrlPattern;
   }

   private ExecutorLogUrlHandler logUrlHandler() {
      return this.logUrlHandler;
   }

   public Seq executorList(final boolean activeOnly) {
      Seq execList = super.executorList(activeOnly);
      return this.logUrlPattern().nonEmpty() ? (Seq)execList.map((exec) -> this.replaceLogUrls(exec)) : execList;
   }

   public ExecutorSummary executorSummary(final String executorId) {
      ExecutorSummary execSummary = super.executorSummary(executorId);
      return this.logUrlPattern().nonEmpty() ? this.replaceLogUrls(execSummary) : execSummary;
   }

   private ExecutorSummary replaceLogUrls(final ExecutorSummary exec) {
      scala.collection.immutable.Map newLogUrlMap = this.logUrlHandler().applyPattern(exec.executorLogs(), exec.attributes());
      return this.replaceExecutorLogs(exec, newLogUrlMap);
   }

   private ExecutorSummary replaceExecutorLogs(final ExecutorSummary source, final scala.collection.immutable.Map newExecutorLogs) {
      return new ExecutorSummary(source.id(), source.hostPort(), source.isActive(), source.rddBlocks(), source.memoryUsed(), source.diskUsed(), source.totalCores(), source.maxTasks(), source.activeTasks(), source.failedTasks(), source.completedTasks(), source.totalTasks(), source.totalDuration(), source.totalGCTime(), source.totalInputBytes(), source.totalShuffleRead(), source.totalShuffleWrite(), source.isBlacklisted(), source.maxMemory(), source.addTime(), source.removeTime(), source.removeReason(), newExecutorLogs, source.memoryMetrics(), source.blacklistedInStages(), source.peakMemoryMetrics(), source.attributes(), source.resources(), source.resourceProfileId(), source.isExcluded(), source.excludedInStages());
   }

   public HistoryAppStatusStore(final SparkConf conf, final KVStore store) {
      super(store, .MODULE$, AppStatusStore$.MODULE$.$lessinit$greater$default$3());
      Logging.$init$(this);
      ApplicationInfo appInfo = super.applicationInfo();
      boolean applicationCompleted = appInfo.attempts().nonEmpty() && ((ApplicationAttemptInfo)appInfo.attempts().head()).completed();
      this.logUrlPattern = (Option)(!applicationCompleted && !BoxesRunTime.unboxToBoolean(conf.get(History$.MODULE$.APPLY_CUSTOM_EXECUTOR_LOG_URL_TO_INCOMPLETE_APP())) ? .MODULE$ : (Option)conf.get((ConfigEntry)History$.MODULE$.CUSTOM_EXECUTOR_LOG_URL()));
      this.logUrlHandler = new ExecutorLogUrlHandler(this.logUrlPattern());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
