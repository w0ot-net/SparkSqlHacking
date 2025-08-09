package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.Option;
import scala.StringContext;
import scala.None.;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb!B\t\u0013\u0001QQ\u0002\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u0011E\u0002!Q1A\u0005\u0002IB\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\to\u0001\u0011\t\u0011)A\u0005g!A\u0001\b\u0001B\u0001B\u0003%\u0011\bC\u0003K\u0001\u0011\u00051\nC\u0004R\u0001\t\u0007I\u0011\u0002*\t\r}\u0003\u0001\u0015!\u0003T\u0011\u001d\u0001\u0007A1A\u0005\n\u0005Daa\u001a\u0001!\u0002\u0013\u0011\u0007\"\u00025\u0001\t\u0003I\u0007\"B7\u0001\t\u0003q\u0007\"\u0002:\u0001\t\u0003\u0019\bB\u0002:\u0001\t\u0003\tI\u0001C\u0004\u0002\f\u0001!\t%!\u0004\t\u000f\u0005]\u0001\u0001\"\u0011\u0002\u001a\tI!j\u001c2XC&$XM\u001d\u0006\u0003'Q\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e,\"a\u0007 \u0014\t\u0001a\"E\n\t\u0003;\u0001j\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\r\"S\"\u0001\n\n\u0005\u0015\u0012\"a\u0003&pE2K7\u000f^3oKJ\u0004\"a\n\u0016\u000e\u0003!R!!\u000b\u000b\u0002\u0011%tG/\u001a:oC2L!a\u000b\u0015\u0003\u000f1{wmZ5oO\u0006aA-Y4TG\",G-\u001e7fe\u000e\u0001\u0001CA\u00120\u0013\t\u0001$C\u0001\u0007E\u0003\u001e\u001b6\r[3ek2,'/A\u0003k_\nLE-F\u00014!\tiB'\u0003\u00026=\t\u0019\u0011J\u001c;\u0002\r)|'-\u00133!\u0003)!x\u000e^1m)\u0006\u001c8n]\u0001\u000ee\u0016\u001cX\u000f\u001c;IC:$G.\u001a:\u0011\u000buQ4\u0007P$\n\u0005mr\"!\u0003$v]\u000e$\u0018n\u001c83!\tid\b\u0004\u0001\u0005\u000b}\u0002!\u0019\u0001!\u0003\u0003Q\u000b\"!\u0011#\u0011\u0005u\u0011\u0015BA\"\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!H#\n\u0005\u0019s\"aA!osB\u0011Q\u0004S\u0005\u0003\u0013z\u0011A!\u00168ji\u00061A(\u001b8jiz\"R\u0001T'O\u001fB\u00032a\t\u0001=\u0011\u0015ac\u00011\u0001/\u0011\u0015\td\u00011\u00014\u0011\u00159d\u00011\u00014\u0011\u0015Ad\u00011\u0001:\u000351\u0017N\\5tQ\u0016$G+Y:lgV\t1\u000b\u0005\u0002U;6\tQK\u0003\u0002W/\u00061\u0011\r^8nS\u000eT!\u0001W-\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002[7\u0006!Q\u000f^5m\u0015\u0005a\u0016\u0001\u00026bm\u0006L!AX+\u0003\u001b\u0005#x.\\5d\u0013:$XmZ3s\u000391\u0017N\\5tQ\u0016$G+Y:lg\u0002\n!B[8c!J|W.[:f+\u0005\u0011\u0007cA2f\u000f6\tAM\u0003\u0002Y=%\u0011a\r\u001a\u0002\b!J|W.[:f\u0003-QwN\u0019)s_6L7/\u001a\u0011\u0002\u0017)|'MR5oSNDW\rZ\u000b\u0002UB\u0011Qd[\u0005\u0003Yz\u0011qAQ8pY\u0016\fg.\u0001\td_6\u0004H.\u001a;j_:4U\u000f^;sKV\tq\u000eE\u0002da\u001eK!!\u001d3\u0003\r\u0019+H/\u001e:f\u0003\u0019\u0019\u0017M\\2fYR\u0011q\t\u001e\u0005\u0006k6\u0001\rA^\u0001\u0007e\u0016\f7o\u001c8\u0011\u0007u9\u00180\u0003\u0002y=\t1q\n\u001d;j_:\u00042A_A\u0002\u001d\tYx\u0010\u0005\u0002}=5\tQP\u0003\u0002\u007f[\u00051AH]8pizJ1!!\u0001\u001f\u0003\u0019\u0001&/\u001a3fM&!\u0011QAA\u0004\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011\u0001\u0010\u0015\u0003\u001d\u000bQ\u0002^1tWN+8mY3fI\u0016$G#B$\u0002\u0010\u0005M\u0001BBA\t\u001f\u0001\u00071'A\u0003j]\u0012,\u0007\u0010\u0003\u0004\u0002\u0016=\u0001\r\u0001R\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0002\u0013)|'MR1jY\u0016$GcA$\u0002\u001c!9\u0011Q\u0004\tA\u0002\u0005}\u0011!C3yG\u0016\u0004H/[8o!\u0011\t\t#a\u000b\u000f\t\u0005\r\u0012q\u0005\b\u0004y\u0006\u0015\u0012\"A\u0010\n\u0007\u0005%b$A\u0004qC\u000e\\\u0017mZ3\n\t\u00055\u0012q\u0006\u0002\n\u000bb\u001cW\r\u001d;j_:T1!!\u000b\u001f\u0001"
)
public class JobWaiter implements JobListener, Logging {
   private final DAGScheduler dagScheduler;
   private final int jobId;
   private final int totalTasks;
   private final Function2 resultHandler;
   private final AtomicInteger finishedTasks;
   private final Promise jobPromise;
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

   public int jobId() {
      return this.jobId;
   }

   private AtomicInteger finishedTasks() {
      return this.finishedTasks;
   }

   private Promise jobPromise() {
      return this.jobPromise;
   }

   public boolean jobFinished() {
      return this.jobPromise().isCompleted();
   }

   public Future completionFuture() {
      return this.jobPromise().future();
   }

   public void cancel(final Option reason) {
      this.dagScheduler.cancelJob(this.jobId(), reason);
   }

   public void cancel() {
      this.cancel(.MODULE$);
   }

   public void taskSucceeded(final int index, final Object result) {
      synchronized(this){}

      try {
         this.resultHandler.apply(BoxesRunTime.boxToInteger(index), result);
      } catch (Throwable var5) {
         throw var5;
      }

      if (this.finishedTasks().incrementAndGet() == this.totalTasks) {
         this.jobPromise().success(BoxedUnit.UNIT);
      }
   }

   public void jobFailed(final Exception exception) {
      if (!this.jobPromise().tryFailure(exception)) {
         this.logWarning((Function0)(() -> "Ignore failure"), exception);
      }
   }

   public JobWaiter(final DAGScheduler dagScheduler, final int jobId, final int totalTasks, final Function2 resultHandler) {
      this.dagScheduler = dagScheduler;
      this.jobId = jobId;
      this.totalTasks = totalTasks;
      this.resultHandler = resultHandler;
      Logging.$init$(this);
      this.finishedTasks = new AtomicInteger(0);
      this.jobPromise = totalTasks == 0 ? scala.concurrent.Promise..MODULE$.successful(BoxedUnit.UNIT) : scala.concurrent.Promise..MODULE$.apply();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
