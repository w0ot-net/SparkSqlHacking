package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.math.Numeric.IntIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mf!\u0002\u00180\u0001=:\u0004\u0002\u0003#\u0001\u0005\u000b\u0007I\u0011\u0002$\t\u0011-\u0003!\u0011!Q\u0001\n\u001dC\u0001\u0002\u0014\u0001\u0003\u0006\u0004%\t!\u0014\u0005\t%\u0002\u0011\t\u0011)A\u0005\u001d\"A1\u000b\u0001BC\u0002\u0013\u0005A\u000b\u0003\u0005Y\u0001\t\u0005\t\u0015!\u0003V\u0011!I\u0006A!b\u0001\n\u0003!\u0006\u0002\u0003.\u0001\u0005\u0003\u0005\u000b\u0011B+\t\u0011m\u0003!Q1A\u0005\u0002qC\u0001b\u0019\u0001\u0003\u0002\u0003\u0006I!\u0018\u0005\tI\u0002\u0011)\u0019!C\u0001K\"A\u0011\u000e\u0001B\u0001B\u0003%a\rC\u0003k\u0001\u0011\u00051\u000eC\u0004t\u0001\t\u0007I\u0011\u0002+\t\rQ\u0004\u0001\u0015!\u0003V\u0011\u001d)\bA1A\u0005\nQCaA\u001e\u0001!\u0002\u0013)\u0006bB<\u0001\u0005\u0004%I\u0001\u0016\u0005\u0007q\u0002\u0001\u000b\u0011B+\t\u000fe\u0004!\u0019!C\u0005)\"1!\u0010\u0001Q\u0001\nUCqa\u001f\u0001C\u0002\u0013\u0005A\u0010C\u0004\u0002(\u0001\u0001\u000b\u0011B?\t\u0013\u0005%\u0002A1A\u0005\n\u0005-\u0002\u0002CA\u001b\u0001\u0001\u0006I!!\f\t\u0013\u0005]\u0002A1A\u0005\n\u0005e\u0002\u0002CA \u0001\u0001\u0006I!a\u000f\t\u0013\u0005\u0005\u0003A1A\u0005\n\u0005\r\u0003\u0002CA#\u0001\u0001\u0006I!a\f\t\u0013\u0005\u001d\u0003A1A\u0005\n\u0005\r\u0003\u0002CA%\u0001\u0001\u0006I!a\f\t\u0013\u0005-\u0003\u00011A\u0005\n\u00055\u0003\"CA(\u0001\u0001\u0007I\u0011BA)\u0011!\ti\u0006\u0001Q!\n\u0005-\u0001bBA0\u0001\u0011\u0005\u0011Q\n\u0005\b\u0003C\u0002A\u0011AA2\u0011\u001d\ti\u0007\u0001C\u0001\u0003_Bq!a\u001e\u0001\t\u0003\tI\bC\u0004\u0002~\u0001!\t!a \t\u0011\u0005\r\u0005\u0001\"\u00010\u0003\u000b;\u0001\"!&0\u0011\u0003y\u0013q\u0013\u0004\b]=B\taLAM\u0011\u0019Q'\u0006\"\u0001\u0002\u001c\"9\u0011Q\u0014\u0016\u0005\u0002\u0005}\u0005\"CARUE\u0005I\u0011AAS\u0005I!\u0016m]6TKR,\u0005p\u00197vI\u0016d\u0017n\u001d;\u000b\u0005A\n\u0014!C:dQ\u0016$W\u000f\\3s\u0015\t\u00114'A\u0003ta\u0006\u00148N\u0003\u00025k\u00051\u0011\r]1dQ\u0016T\u0011AN\u0001\u0004_J<7c\u0001\u00019}A\u0011\u0011\bP\u0007\u0002u)\t1(A\u0003tG\u0006d\u0017-\u0003\u0002>u\t1\u0011I\\=SK\u001a\u0004\"a\u0010\"\u000e\u0003\u0001S!!Q\u0019\u0002\u0011%tG/\u001a:oC2L!a\u0011!\u0003\u000f1{wmZ5oO\u0006YA.[:uK:,'OQ;t\u0007\u0001)\u0012a\u0012\t\u0003\u0011&k\u0011aL\u0005\u0003\u0015>\u0012q\u0002T5wK2K7\u000f^3oKJ\u0014Uo]\u0001\rY&\u001cH/\u001a8fe\n+8\u000fI\u0001\u0005G>tg-F\u0001O!\ty\u0005+D\u00012\u0013\t\t\u0016GA\u0005Ta\u0006\u00148nQ8oM\u0006)1m\u001c8gA\u000591\u000f^1hK&#W#A+\u0011\u0005e2\u0016BA,;\u0005\rIe\u000e^\u0001\tgR\fw-Z%eA\u0005q1\u000f^1hK\u0006#H/Z7qi&#\u0017aD:uC\u001e,\u0017\t\u001e;f[B$\u0018\n\u001a\u0011\u0002\u000b\rdwnY6\u0016\u0003u\u0003\"AX1\u000e\u0003}S!\u0001Y\u0019\u0002\tU$\u0018\u000e\\\u0005\u0003E~\u0013Qa\u00117pG.\faa\u00197pG.\u0004\u0013\u0001C5t\tJL(+\u001e8\u0016\u0003\u0019\u0004\"!O4\n\u0005!T$a\u0002\"p_2,\u0017M\\\u0001\nSN$%/\u001f*v]\u0002\na\u0001P5oSRtDc\u00027n]>\u0004\u0018O\u001d\t\u0003\u0011\u0002AQ\u0001R\u0007A\u0002\u001dCQ\u0001T\u0007A\u00029CQaU\u0007A\u0002UCQ!W\u0007A\u0002UCQaW\u0007A\u0002uCq\u0001Z\u0007\u0011\u0002\u0003\u0007a-\u0001\u0010N\u0003b{F+Q*L?\u0006#F+R'Q)N{\u0006+\u0012*`\u000bb+5)\u0016+P%\u0006yR*\u0011-`)\u0006\u001b6jX!U)\u0016k\u0005\u000bV*`!\u0016\u0013v,\u0012-F\u0007V#vJ\u0015\u0011\u000255\u000b\u0005l\u0018+B'.{\u0016\t\u0016+F\u001bB#6k\u0018)F%~su\nR#\u000275\u000b\u0005l\u0018+B'.{\u0016\t\u0016+F\u001bB#6k\u0018)F%~su\nR#!\u0003mi\u0015\tW0G\u0003&cUKU#T?B+%kX#Y\u000b\u000e{6\u000bV!H\u000b\u0006aR*\u0011-`\r\u0006KE*\u0016*F'~\u0003VIU0F1\u0016\u001bul\u0015+B\u000f\u0016\u0003\u0013AH'B1~3\u0015)\u0013'F\t~+\u0005,R\"`!\u0016\u0013vLT(E\u000b~\u001bF+Q$F\u0003}i\u0015\tW0G\u0003&cU\tR0F1\u0016\u001bu\fU#S?:{E)R0T)\u0006;U\tI\u0001\u000fKb,7\rV8GC&dWO]3t+\u0005i\bc\u0002@\u0002\b\u0005-\u0011\u0011E\u0007\u0002\u007f*!\u0011\u0011AA\u0002\u0003\u001diW\u000f^1cY\u0016T1!!\u0002;\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0004\u0003\u0013y(a\u0002%bg\"l\u0015\r\u001d\t\u0005\u0003\u001b\tYB\u0004\u0003\u0002\u0010\u0005]\u0001cAA\tu5\u0011\u00111\u0003\u0006\u0004\u0003+)\u0015A\u0002\u001fs_>$h(C\u0002\u0002\u001ai\na\u0001\u0015:fI\u00164\u0017\u0002BA\u000f\u0003?\u0011aa\u0015;sS:<'bAA\ruA\u0019\u0001*a\t\n\u0007\u0005\u0015rFA\rFq\u0016\u001cW\u000f^8s\r\u0006LG.\u001e:fg&sG+Y:l'\u0016$\u0018aD3yK\u000e$vNR1jYV\u0014Xm\u001d\u0011\u0002/9|G-\u001a+p\u000bb,7m],ji\"4\u0015-\u001b7ve\u0016\u001cXCAA\u0017!\u001dq\u0018qAA\u0006\u0003_\u0001RA`A\u0019\u0003\u0017I1!a\r\u0000\u0005\u001dA\u0015m\u001d5TKR\f\u0001D\\8eKR{W\t_3dg^KG\u000f\u001b$bS2,(/Z:!\u0003eqw\u000eZ3U_\u0016C8\r\\;eK\u0012$\u0016m]6J]\u0012,\u00070Z:\u0016\u0005\u0005m\u0002c\u0002@\u0002\b\u0005-\u0011Q\b\t\u0005}\u0006ER+\u0001\u000eo_\u0012,Gk\\#yG2,H-\u001a3UCN\\\u0017J\u001c3fq\u0016\u001c\b%A\u0007fq\u000edW\u000fZ3e\u000bb,7m]\u000b\u0003\u0003_\ta\"\u001a=dYV$W\rZ#yK\u000e\u001c\b%A\u0007fq\u000edW\u000fZ3e\u001d>$Wm]\u0001\u000fKb\u001cG.\u001e3fI:{G-Z:!\u0003Ma\u0017\r^3ti\u001a\u000b\u0017\u000e\\;sKJ+\u0017m]8o+\t\tY!A\fmCR,7\u000f\u001e$bS2,(/\u001a*fCN|gn\u0018\u0013fcR!\u00111KA-!\rI\u0014QK\u0005\u0004\u0003/R$\u0001B+oSRD\u0011\"a\u0017\"\u0003\u0003\u0005\r!a\u0003\u0002\u0007a$\u0013'\u0001\u000bmCR,7\u000f\u001e$bS2,(/\u001a*fCN|g\u000eI\u0001\u0017O\u0016$H*\u0019;fgR4\u0015-\u001b7ve\u0016\u0014V-Y:p]\u0006I\u0012n]#yK\u000e,Ho\u001c:Fq\u000edW\u000fZ3e\r>\u0014H+Y:l)\u00151\u0017QMA5\u0011\u001d\t9\u0007\na\u0001\u0003\u0017\t!\"\u001a=fGV$xN]%e\u0011\u0019\tY\u0007\na\u0001+\u0006)\u0011N\u001c3fq\u0006)\u0012n\u001d(pI\u0016,\u0005p\u00197vI\u0016$gi\u001c:UCN\\G#\u00024\u0002r\u0005U\u0004bBA:K\u0001\u0007\u00111B\u0001\u0005]>$W\r\u0003\u0004\u0002l\u0015\u0002\r!V\u0001\u001dSN,\u00050Z2vi>\u0014X\t_2mk\u0012,GMR8s)\u0006\u001c8nU3u)\r1\u00171\u0010\u0005\b\u0003O2\u0003\u0019AA\u0006\u0003aI7OT8eK\u0016C8\r\\;eK\u00124uN\u001d+bg.\u001cV\r\u001e\u000b\u0004M\u0006\u0005\u0005bBA:O\u0001\u0007\u00111B\u0001\u001ckB$\u0017\r^3Fq\u000edW\u000fZ3e\r>\u0014h)Y5mK\u0012$\u0016m]6\u0015\u0015\u0005M\u0013qQAF\u0003\u001f\u000b\t\nC\u0004\u0002\n\"\u0002\r!a\u0003\u0002\t!|7\u000f\u001e\u0005\b\u0003\u001bC\u0003\u0019AA\u0006\u0003\u0011)\u00070Z2\t\r\u0005-\u0004\u00061\u0001V\u0011\u001d\t\u0019\n\u000ba\u0001\u0003\u0017\tQBZ1jYV\u0014XMU3bg>t\u0017A\u0005+bg.\u001cV\r^#yG2,H-\u001a7jgR\u0004\"\u0001\u0013\u0016\u0014\u0005)BDCAAL\u0003eI7/\u0012=dYV$Wm\u00148GC&dWO]3F]\u0006\u0014G.\u001a3\u0015\u0007\u0019\f\t\u000bC\u0003MY\u0001\u0007a*A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEN\u000b\u0003\u0003OS3AZAUW\t\tY\u000b\u0005\u0003\u0002.\u0006]VBAAX\u0015\u0011\t\t,a-\u0002\u0013Ut7\r[3dW\u0016$'bAA[u\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005e\u0016q\u0016\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class TaskSetExcludelist implements Logging {
   private final LiveListenerBus listenerBus;
   private final SparkConf conf;
   private final int stageId;
   private final int stageAttemptId;
   private final Clock clock;
   private final boolean isDryRun;
   private final int MAX_TASK_ATTEMPTS_PER_EXECUTOR;
   private final int MAX_TASK_ATTEMPTS_PER_NODE;
   private final int MAX_FAILURES_PER_EXEC_STAGE;
   private final int MAX_FAILED_EXEC_PER_NODE_STAGE;
   private final HashMap execToFailures;
   private final HashMap nodeToExecsWithFailures;
   private final HashMap nodeToExcludedTaskIndexes;
   private final HashSet excludedExecs;
   private final HashSet excludedNodes;
   private String latestFailureReason;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$6() {
      return TaskSetExcludelist$.MODULE$.$lessinit$greater$default$6();
   }

   public static boolean isExcludeOnFailureEnabled(final SparkConf conf) {
      return TaskSetExcludelist$.MODULE$.isExcludeOnFailureEnabled(conf);
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

   private LiveListenerBus listenerBus() {
      return this.listenerBus;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public Clock clock() {
      return this.clock;
   }

   public boolean isDryRun() {
      return this.isDryRun;
   }

   private int MAX_TASK_ATTEMPTS_PER_EXECUTOR() {
      return this.MAX_TASK_ATTEMPTS_PER_EXECUTOR;
   }

   private int MAX_TASK_ATTEMPTS_PER_NODE() {
      return this.MAX_TASK_ATTEMPTS_PER_NODE;
   }

   private int MAX_FAILURES_PER_EXEC_STAGE() {
      return this.MAX_FAILURES_PER_EXEC_STAGE;
   }

   private int MAX_FAILED_EXEC_PER_NODE_STAGE() {
      return this.MAX_FAILED_EXEC_PER_NODE_STAGE;
   }

   public HashMap execToFailures() {
      return this.execToFailures;
   }

   private HashMap nodeToExecsWithFailures() {
      return this.nodeToExecsWithFailures;
   }

   private HashMap nodeToExcludedTaskIndexes() {
      return this.nodeToExcludedTaskIndexes;
   }

   private HashSet excludedExecs() {
      return this.excludedExecs;
   }

   private HashSet excludedNodes() {
      return this.excludedNodes;
   }

   private String latestFailureReason() {
      return this.latestFailureReason;
   }

   private void latestFailureReason_$eq(final String x$1) {
      this.latestFailureReason = x$1;
   }

   public String getLatestFailureReason() {
      return this.latestFailureReason();
   }

   public boolean isExecutorExcludedForTask(final String executorId, final int index) {
      return !this.isDryRun() && this.execToFailures().get(executorId).exists((execFailures) -> BoxesRunTime.boxToBoolean($anonfun$isExecutorExcludedForTask$1(this, index, execFailures)));
   }

   public boolean isNodeExcludedForTask(final String node, final int index) {
      return !this.isDryRun() && this.nodeToExcludedTaskIndexes().get(node).exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$isNodeExcludedForTask$1(index, x$1)));
   }

   public boolean isExecutorExcludedForTaskSet(final String executorId) {
      return !this.isDryRun() && this.excludedExecs().contains(executorId);
   }

   public boolean isNodeExcludedForTaskSet(final String node) {
      return !this.isDryRun() && this.excludedNodes().contains(node);
   }

   public void updateExcludedForFailedTask(final String host, final String exec, final int index, final String failureReason) {
      this.latestFailureReason_$eq(failureReason);
      ExecutorFailuresInTaskSet execFailures = (ExecutorFailuresInTaskSet)this.execToFailures().getOrElseUpdate(exec, () -> new ExecutorFailuresInTaskSet(host));
      execFailures.updateWithFailure(index, this.clock().getTimeMillis());
      HashSet execsWithFailuresOnNode = (HashSet)this.nodeToExecsWithFailures().getOrElseUpdate(host, () -> new HashSet());
      execsWithFailuresOnNode.$plus$eq(exec);
      int failuresOnHost = BoxesRunTime.unboxToInt(execsWithFailuresOnNode.iterator().flatMap((execx) -> this.execToFailures().get(execx).map((failures) -> BoxesRunTime.boxToInteger($anonfun$updateExcludedForFailedTask$4(index, failures)))).sum(.MODULE$));
      if (failuresOnHost >= this.MAX_TASK_ATTEMPTS_PER_NODE()) {
         ((Growable)this.nodeToExcludedTaskIndexes().getOrElseUpdate(host, () -> new HashSet())).$plus$eq(BoxesRunTime.boxToInteger(index));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      int numFailures = execFailures.numUniqueTasksWithFailures();
      if (numFailures >= this.MAX_FAILURES_PER_EXEC_STAGE()) {
         if (this.excludedExecs().add(exec)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Excluding executor ", " for stage "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, exec)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(this.stageId()))}))))));
            HashSet excludedExecutorsOnNode = (HashSet)execsWithFailuresOnNode.intersect(this.excludedExecs());
            long now = this.clock().getTimeMillis();
            this.listenerBus().post(new SparkListenerExecutorBlacklistedForStage(now, exec, numFailures, this.stageId(), this.stageAttemptId()));
            this.listenerBus().post(new SparkListenerExecutorExcludedForStage(now, exec, numFailures, this.stageId(), this.stageAttemptId()));
            int numFailExec = excludedExecutorsOnNode.size();
            if (numFailExec >= this.MAX_FAILED_EXEC_PER_NODE_STAGE()) {
               if (this.excludedNodes().add(host)) {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Excluding ", " for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, host)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"stage ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(this.stageId()))}))))));
                  this.listenerBus().post(new SparkListenerNodeBlacklistedForStage(now, host, numFailExec, this.stageId(), this.stageAttemptId()));
                  this.listenerBus().post(new SparkListenerNodeExcludedForStage(now, host, numFailExec, this.stageId(), this.stageAttemptId()));
               }
            }
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isExecutorExcludedForTask$1(final TaskSetExcludelist $this, final int index$1, final ExecutorFailuresInTaskSet execFailures) {
      return execFailures.getNumTaskFailures(index$1) >= $this.MAX_TASK_ATTEMPTS_PER_EXECUTOR();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isNodeExcludedForTask$1(final int index$2, final HashSet x$1) {
      return x$1.contains(BoxesRunTime.boxToInteger(index$2));
   }

   // $FF: synthetic method
   public static final int $anonfun$updateExcludedForFailedTask$4(final int index$3, final ExecutorFailuresInTaskSet failures) {
      return failures.getNumTaskFailures(index$3);
   }

   public TaskSetExcludelist(final LiveListenerBus listenerBus, final SparkConf conf, final int stageId, final int stageAttemptId, final Clock clock, final boolean isDryRun) {
      this.listenerBus = listenerBus;
      this.conf = conf;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.clock = clock;
      this.isDryRun = isDryRun;
      Logging.$init$(this);
      this.MAX_TASK_ATTEMPTS_PER_EXECUTOR = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.MAX_TASK_ATTEMPTS_PER_EXECUTOR()));
      this.MAX_TASK_ATTEMPTS_PER_NODE = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.MAX_TASK_ATTEMPTS_PER_NODE()));
      this.MAX_FAILURES_PER_EXEC_STAGE = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.MAX_FAILURES_PER_EXEC_STAGE()));
      this.MAX_FAILED_EXEC_PER_NODE_STAGE = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.MAX_FAILED_EXEC_PER_NODE_STAGE()));
      this.execToFailures = new HashMap();
      this.nodeToExecsWithFailures = new HashMap();
      this.nodeToExcludedTaskIndexes = new HashMap();
      this.excludedExecs = new HashSet();
      this.excludedNodes = new HashSet();
      this.latestFailureReason = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
