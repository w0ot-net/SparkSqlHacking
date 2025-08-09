package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.yarn.client.api.AMRMClient;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.ExecutorFailureTracker;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Me!\u0002\u0011\"\u0001\u0015Z\u0003\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\u0011y\u0002!\u0011!Q\u0001\n}B\u0001\u0002\u0019\u0001\u0003\u0002\u0003\u0006I!\u0019\u0005\u0006K\u0002!\tA\u001a\u0005\bY\u0002\u0011\r\u0011\"\u0003n\u0011\u0019\t\b\u0001)A\u0005]\"9!\u000f\u0001b\u0001\n\u0013\u0019\bBB<\u0001A\u0003%A\u000fC\u0004y\u0001\t\u0007I\u0011B=\t\ru\u0004\u0001\u0015!\u0003{\u0011\u001dq\bA1A\u0005\n}D\u0001\"!\t\u0001A\u0003%\u0011\u0011\u0001\u0005\n\u0003G\u0001!\u0019!C\u0005\u0003KA\u0001\"a\r\u0001A\u0003%\u0011q\u0005\u0005\t\u0003k\u0001\u0001\u0019!C\u0005\u007f\"I\u0011q\u0007\u0001A\u0002\u0013%\u0011\u0011\b\u0005\t\u0003\u000b\u0002\u0001\u0015)\u0003\u0002\u0002!A\u0011q\t\u0001A\u0002\u0013%q\u0010C\u0005\u0002J\u0001\u0001\r\u0011\"\u0003\u0002L!A\u0011q\n\u0001!B\u0013\t\t\u0001\u0003\u0005\u0002R\u0001\u0001\r\u0011\"\u0003z\u0011%\t\u0019\u0006\u0001a\u0001\n\u0013\t)\u0006C\u0004\u0002Z\u0001\u0001\u000b\u0015\u0002>\t\u000f\u0005m\u0003\u0001\"\u0001\u0002^!9\u0011\u0011\r\u0001\u0005\u0002\u0005\r\u0004bBA8\u0001\u0011%\u0011\u0011\u000f\u0005\b\u0003o\u0002A\u0011AA=\u0011\u0019\t\u0019\t\u0001C\u0001g\"9\u0011Q\u0011\u0001\u0005\n\u0005\u001d\u0005bBAE\u0001\u0011%\u00111\u0012\u0005\b\u0003#\u0003A\u0011BAD\u0005yI\u0016M\u001d8BY2|7-\u0019;pe:{G-\u001a%fC2$\b\u000e\u0016:bG.,'O\u0003\u0002#G\u0005!\u00110\u0019:o\u0015\t!S%\u0001\u0004eKBdw.\u001f\u0006\u0003M\u001d\nQa\u001d9be.T!\u0001K\u0015\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0013aA8sON\u0019\u0001\u0001\f\u001a\u0011\u00055\u0002T\"\u0001\u0018\u000b\u0003=\nQa]2bY\u0006L!!\r\u0018\u0003\r\u0005s\u0017PU3g!\t\u0019d'D\u00015\u0015\t)T%\u0001\u0005j]R,'O\\1m\u0013\t9DGA\u0004M_\u001e<\u0017N\\4\u0002\u0013M\u0004\u0018M]6D_:47\u0001\u0001\t\u0003wqj\u0011!J\u0005\u0003{\u0015\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\u0011\u0005l7\t\\5f]R\u00042\u0001\u0011%K\u001b\u0005\t%B\u0001\"D\u0003\r\t\u0007/\u001b\u0006\u0003\t\u0016\u000baa\u00197jK:$(B\u0001\u0012G\u0015\t9u%\u0001\u0004iC\u0012|w\u000e]\u0005\u0003\u0013\u0006\u0013!\"Q'S\u001b\u000ec\u0017.\u001a8u!\tYUL\u0004\u0002M7:\u0011QJ\u0017\b\u0003\u001dfs!a\u0014-\u000f\u0005A;fBA)W\u001d\t\u0011V+D\u0001T\u0015\t!\u0016(\u0001\u0004=e>|GOP\u0005\u0002U%\u0011\u0001&K\u0005\u0003\u000f\u001eJ!A\t$\n\u0005\u0011+\u0015B\u0001\"D\u0013\ta\u0016)\u0001\u0006B\u001bJk5\t\\5f]RL!AX0\u0003!\r{g\u000e^1j]\u0016\u0014(+Z9vKN$(B\u0001/B\u000391\u0017-\u001b7ve\u0016$&/Y2lKJ\u0004\"AY2\u000e\u0003\rJ!\u0001Z\u0012\u0003-\u0015CXmY;u_J4\u0015-\u001b7ve\u0016$&/Y2lKJ\fa\u0001P5oSRtD\u0003B4jU.\u0004\"\u0001\u001b\u0001\u000e\u0003\u0005BQ\u0001\u000f\u0003A\u0002iBQA\u0010\u0003A\u0002}BQ\u0001\u0019\u0003A\u0002\u0005\fQ$\u001a=dYV$Wm\u00148GC&dWO]3US6,w.\u001e;NS2d\u0017n]\u000b\u0002]B\u0011Qf\\\u0005\u0003a:\u0012A\u0001T8oO\u0006qR\r_2mk\u0012,wJ\u001c$bS2,(/\u001a+j[\u0016|W\u000f^'jY2L7\u000fI\u0001\u001eY\u0006,hn\u00195Fq\u000edW\u000fZ3P]\u001a\u000b\u0017\u000e\\;sK\u0016s\u0017M\u00197fIV\tA\u000f\u0005\u0002.k&\u0011aO\f\u0002\b\u0005>|G.Z1o\u0003ya\u0017-\u001e8dQ\u0016C8\r\\;eK>sg)Y5mkJ,WI\\1cY\u0016$\u0007%\u0001\nnCb4\u0015-\u001b7ve\u0016\u001c\b+\u001a:I_N$X#\u0001>\u0011\u00055Z\u0018B\u0001?/\u0005\rIe\u000e^\u0001\u0014[\u0006Dh)Y5mkJ,7\u000fU3s\u0011>\u001cH\u000fI\u0001\rKb\u001cG.\u001e3f\u001d>$Wm]\u000b\u0003\u0003\u0003\u0001b!a\u0001\u0002\u000e\u0005EQBAA\u0003\u0015\u0011\t9!!\u0003\u0002\u0013%lW.\u001e;bE2,'bAA\u0006]\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005=\u0011Q\u0001\u0002\u0004'\u0016$\b\u0003BA\n\u00037qA!!\u0006\u0002\u0018A\u0011!KL\u0005\u0004\u00033q\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002\u001e\u0005}!AB*ue&twMC\u0002\u0002\u001a9\nQ\"\u001a=dYV$WMT8eKN\u0004\u0013!G1mY>\u001c\u0017\r^8s\u000bb\u001cG.\u001e3fI:{G-\u001a'jgR,\"!a\n\u0011\u000f\u0005%\u0012qFA\t]6\u0011\u00111\u0006\u0006\u0005\u0003[\tI!A\u0004nkR\f'\r\\3\n\t\u0005E\u00121\u0006\u0002\b\u0011\u0006\u001c\b.T1q\u0003i\tG\u000e\\8dCR|'/\u0012=dYV$W\r\u001a(pI\u0016d\u0015n\u001d;!\u0003i\u0019WO\u001d:f]R,\u0005p\u00197vI\u0016$W\rZ-be:tu\u000eZ3t\u0003y\u0019WO\u001d:f]R,\u0005p\u00197vI\u0016$W\rZ-be:tu\u000eZ3t?\u0012*\u0017\u000f\u0006\u0003\u0002<\u0005\u0005\u0003cA\u0017\u0002>%\u0019\u0011q\b\u0018\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003\u0007\u0002\u0012\u0011!a\u0001\u0003\u0003\t1\u0001\u001f\u00132\u0003m\u0019WO\u001d:f]R,\u0005p\u00197vI\u0016$W\rZ-be:tu\u000eZ3tA\u0005I2o\u00195fIVdWM]#yG2,H-\u001a3O_\u0012,G*[:u\u0003u\u00198\r[3ek2,'/\u0012=dYV$W\r\u001a(pI\u0016d\u0015n\u001d;`I\u0015\fH\u0003BA\u001e\u0003\u001bB\u0011\"a\u0011\u0014\u0003\u0003\u0005\r!!\u0001\u00025M\u001c\u0007.\u001a3vY\u0016\u0014X\t_2mk\u0012,GMT8eK2K7\u000f\u001e\u0011\u0002\u001f9,Xn\u00117vgR,'OT8eKN\f1C\\;n\u00072,8\u000f^3s\u001d>$Wm]0%KF$B!a\u000f\u0002X!A\u00111\t\f\u0002\u0002\u0003\u0007!0\u0001\tok6\u001cE.^:uKJtu\u000eZ3tA\u0005\u00112/\u001a;Ok6\u001cE.^:uKJtu\u000eZ3t)\u0011\tY$a\u0018\t\r\u0005E\u0003\u00041\u0001{\u0003}A\u0017M\u001c3mKJ+7o\\;sG\u0016\fE\u000e\\8dCRLwN\u001c$bS2,(/\u001a\u000b\u0005\u0003w\t)\u0007C\u0004\u0002he\u0001\r!!\u001b\u0002\u000f!|7\u000f^(qiB)Q&a\u001b\u0002\u0012%\u0019\u0011Q\u000e\u0018\u0003\r=\u0003H/[8o\u0003u)\b\u000fZ1uK\u0006cGn\\2bi&|g.\u0012=dYV$W\r\u001a(pI\u0016\u001cH\u0003BA\u001e\u0003gBq!!\u001e\u001b\u0001\u0004\t\t\"\u0001\u0005i_N$h.Y7f\u0003e\u0019X\r^*dQ\u0016$W\u000f\\3s\u000bb\u001cG.\u001e3fI:{G-Z:\u0015\t\u0005m\u00121\u0010\u0005\b\u0003{Z\u0002\u0019AA@\u0003\u0001\u001a8\r[3ek2,'/\u0012=dYV$W\r\u001a(pI\u0016\u001cx+\u001b;i\u000bb\u0004\u0018N]=\u0011\r\u0005M\u0011\u0011QA\t\u0013\u0011\ty!a\b\u0002#%\u001c\u0018\t\u001c7O_\u0012,W\t_2mk\u0012,G-\u0001\u000bsK\u001a\u0014Xm\u001d5Fq\u000edW\u000fZ3e\u001d>$Wm\u001d\u000b\u0003\u0003w\t\u0001e]=oG\"\u0014xN\\5{K\u0016C8\r\\;eK\u0012tu\u000eZ3t/&$\b.W1s]R!\u00111HAG\u0011\u001d\tyI\ba\u0001\u0003\u007f\naB\\8eKN$v.\u0012=dYV$W-\u0001\u0010sK6|g/Z#ya&\u0014X\rZ-be:,\u0005p\u00197vI\u0016$gj\u001c3fg\u0002"
)
public class YarnAllocatorNodeHealthTracker implements Logging {
   private final AMRMClient amClient;
   private final ExecutorFailureTracker failureTracker;
   private final long excludeOnFailureTimeoutMillis;
   private final boolean launchExcludeOnFailureEnabled;
   private final int maxFailuresPerHost;
   private final Set excludeNodes;
   private final HashMap allocatorExcludedNodeList;
   private Set currentExcludededYarnNodes;
   private Set schedulerExcludedNodeList;
   private int numClusterNodes;
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

   private long excludeOnFailureTimeoutMillis() {
      return this.excludeOnFailureTimeoutMillis;
   }

   private boolean launchExcludeOnFailureEnabled() {
      return this.launchExcludeOnFailureEnabled;
   }

   private int maxFailuresPerHost() {
      return this.maxFailuresPerHost;
   }

   private Set excludeNodes() {
      return this.excludeNodes;
   }

   private HashMap allocatorExcludedNodeList() {
      return this.allocatorExcludedNodeList;
   }

   private Set currentExcludededYarnNodes() {
      return this.currentExcludededYarnNodes;
   }

   private void currentExcludededYarnNodes_$eq(final Set x$1) {
      this.currentExcludededYarnNodes = x$1;
   }

   private Set schedulerExcludedNodeList() {
      return this.schedulerExcludedNodeList;
   }

   private void schedulerExcludedNodeList_$eq(final Set x$1) {
      this.schedulerExcludedNodeList = x$1;
   }

   private int numClusterNodes() {
      return this.numClusterNodes;
   }

   private void numClusterNodes_$eq(final int x$1) {
      this.numClusterNodes = x$1;
   }

   public void setNumClusterNodes(final int numClusterNodes) {
      this.numClusterNodes_$eq(numClusterNodes);
   }

   public void handleResourceAllocationFailure(final Option hostOpt) {
      if (hostOpt instanceof Some var4) {
         String hostname = (String)var4.value();
         if (this.launchExcludeOnFailureEnabled()) {
            if (!this.schedulerExcludedNodeList().contains(hostname) && !this.allocatorExcludedNodeList().contains(hostname)) {
               this.failureTracker.registerFailureOnHost(hostname);
               this.updateAllocationExcludedNodes(hostname);
               BoxedUnit var7 = BoxedUnit.UNIT;
               return;
            }

            BoxedUnit var6 = BoxedUnit.UNIT;
            return;
         }
      }

      this.failureTracker.registerExecutorFailure();
      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   private void updateAllocationExcludedNodes(final String hostname) {
      int failuresOnHost = this.failureTracker.numFailuresOnHost(hostname);
      if (failuresOnHost > this.maxFailuresPerHost()) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"excluding ", " as YARN allocation failed "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, hostname)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " times"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILURES..MODULE$, BoxesRunTime.boxToInteger(failuresOnHost))}))))));
         this.allocatorExcludedNodeList().put(hostname, BoxesRunTime.boxToLong(this.failureTracker.clock().getTimeMillis() + this.excludeOnFailureTimeoutMillis()));
         this.refreshExcludedNodes();
      }
   }

   public void setSchedulerExcludedNodes(final Set schedulerExcludedNodesWithExpiry) {
      this.schedulerExcludedNodeList_$eq(schedulerExcludedNodesWithExpiry);
      this.refreshExcludedNodes();
   }

   public boolean isAllNodeExcluded() {
      if (this.numClusterNodes() <= 0) {
         this.logWarning((Function0)(() -> "No available nodes reported, please check Resource Manager."));
         return false;
      } else {
         return this.currentExcludededYarnNodes().size() >= this.numClusterNodes();
      }
   }

   private void refreshExcludedNodes() {
      this.removeExpiredYarnExcludedNodes();
      Set allExcludedNodes = (Set)this.excludeNodes().$plus$plus(this.schedulerExcludedNodeList()).$plus$plus(this.allocatorExcludedNodeList().keySet());
      this.synchronizeExcludedNodesWithYarn(allExcludedNodes);
   }

   private void synchronizeExcludedNodesWithYarn(final Set nodesToExclude) {
      List additions = (List)nodesToExclude.$minus$minus(this.currentExcludededYarnNodes()).toList().sorted(scala.math.Ordering.String..MODULE$);
      List removals = (List)this.currentExcludededYarnNodes().$minus$minus(nodesToExclude).toList().sorted(scala.math.Ordering.String..MODULE$);
      if (additions.nonEmpty()) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"adding nodes to YARN application master's "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"excluded node list: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NODES..MODULE$, additions)}))))));
      }

      if (removals.nonEmpty()) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"removing nodes from YARN application master's "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"excluded node list: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NODES..MODULE$, removals)}))))));
      }

      if (additions.nonEmpty() || removals.nonEmpty()) {
         this.amClient.updateBlacklist(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(additions).asJava(), scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(removals).asJava());
      }

      this.currentExcludededYarnNodes_$eq(nodesToExclude);
   }

   private void removeExpiredYarnExcludedNodes() {
      long now = this.failureTracker.clock().getTimeMillis();
      this.allocatorExcludedNodeList().filterInPlace((x$1, expiryTime) -> BoxesRunTime.boxToBoolean($anonfun$removeExpiredYarnExcludedNodes$1(now, x$1, BoxesRunTime.unboxToLong(expiryTime))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removeExpiredYarnExcludedNodes$1(final long now$1, final String x$1, final long expiryTime) {
      return expiryTime > now$1;
   }

   public YarnAllocatorNodeHealthTracker(final SparkConf sparkConf, final AMRMClient amClient, final ExecutorFailureTracker failureTracker) {
      this.amClient = amClient;
      this.failureTracker = failureTracker;
      Logging.$init$(this);
      this.excludeOnFailureTimeoutMillis = org.apache.spark.scheduler.HealthTracker..MODULE$.getExcludeOnFailureTimeout(sparkConf);
      this.launchExcludeOnFailureEnabled = BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.YARN_EXECUTOR_LAUNCH_EXCLUDE_ON_FAILURE_ENABLED()));
      this.maxFailuresPerHost = BoxesRunTime.unboxToInt(sparkConf.get(org.apache.spark.internal.config.package..MODULE$.MAX_FAILED_EXEC_PER_NODE()));
      this.excludeNodes = ((IterableOnceOps)sparkConf.get(package$.MODULE$.YARN_EXCLUDE_NODES())).toSet();
      this.allocatorExcludedNodeList = new HashMap();
      this.currentExcludededYarnNodes = scala.Predef..MODULE$.Set().empty();
      this.schedulerExcludedNodeList = scala.Predef..MODULE$.Set().empty();
      this.numClusterNodes = Integer.MAX_VALUE;
      this.refreshExcludedNodes();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
