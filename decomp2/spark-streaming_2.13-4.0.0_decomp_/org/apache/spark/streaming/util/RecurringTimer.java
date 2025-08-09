package org.apache.spark.streaming.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.StringContext;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb!\u0002\u000f\u001e\u0001}9\u0003\u0002\u0003\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001c\t\u0011m\u0002!\u0011!Q\u0001\nqB\u0001b\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\u0011\u0005\t\r\u0002\u0011\t\u0011)A\u0005\u000f\")!\u000b\u0001C\u0001'\"9!\f\u0001b\u0001\n\u0013Y\u0006B\u00023\u0001A\u0003%A\fC\u0004f\u0001\u0001\u0007I\u0011\u00024\t\u000f\u001d\u0004\u0001\u0019!C\u0005Q\"11\u000e\u0001Q!\nqBq\u0001\u001d\u0001A\u0002\u0013%a\rC\u0004r\u0001\u0001\u0007I\u0011\u0002:\t\rQ\u0004\u0001\u0015)\u0003=\u0011\u001d1\b\u00011A\u0005\n]Dqa\u001f\u0001A\u0002\u0013%A\u0010\u0003\u0004\u007f\u0001\u0001\u0006K\u0001\u001f\u0005\b\u0003\u0003\u0001A\u0011AA\u0002\u0011\u001d\t)\u0001\u0001C\u0001\u0003\u000fAq!!\u0004\u0001\t\u0003\ty\u0001C\u0004\u0002\u000e\u0001!\t!a\u0001\t\u000f\u0005U\u0001\u0001\"\u0001\u0002\u0018!9\u0011Q\u0004\u0001\u0005\n\u0005}\u0001bBA\u0011\u0001\u0011%\u0011qD\u0004\t\u0003Gi\u0002\u0012A\u0010\u0002&\u00199A$\bE\u0001?\u0005\u001d\u0002B\u0002*\u001a\t\u0003\tI\u0003C\u0004\u0002,e!\t!!\f\u0003\u001dI+7-\u001e:sS:<G+[7fe*\u0011adH\u0001\u0005kRLGN\u0003\u0002!C\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003E\r\nQa\u001d9be.T!\u0001J\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0013aA8sON\u0019\u0001\u0001\u000b\u0018\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r\u0005s\u0017PU3g!\ty#'D\u00011\u0015\t\t\u0014%\u0001\u0005j]R,'O\\1m\u0013\t\u0019\u0004GA\u0004M_\u001e<\u0017N\\4\u0002\u000b\rdwnY6\u0004\u0001A\u0011q'O\u0007\u0002q)\u0011a$I\u0005\u0003ua\u0012Qa\u00117pG.\fa\u0001]3sS>$\u0007CA\u0015>\u0013\tq$F\u0001\u0003M_:<\u0017\u0001C2bY2\u0014\u0017mY6\u0011\t%\nEhQ\u0005\u0003\u0005*\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0005%\"\u0015BA#+\u0005\u0011)f.\u001b;\u0002\t9\fW.\u001a\t\u0003\u0011>s!!S'\u0011\u0005)SS\"A&\u000b\u00051+\u0014A\u0002\u001fs_>$h(\u0003\u0002OU\u00051\u0001K]3eK\u001aL!\u0001U)\u0003\rM#(/\u001b8h\u0015\tq%&\u0001\u0004=S:LGO\u0010\u000b\u0006)Z;\u0006,\u0017\t\u0003+\u0002i\u0011!\b\u0005\u0006i\u0015\u0001\rA\u000e\u0005\u0006w\u0015\u0001\r\u0001\u0010\u0005\u0006\u007f\u0015\u0001\r\u0001\u0011\u0005\u0006\r\u0016\u0001\raR\u0001\u0007i\"\u0014X-\u00193\u0016\u0003q\u0003\"!\u00182\u000e\u0003yS!a\u00181\u0002\t1\fgn\u001a\u0006\u0002C\u0006!!.\u0019<b\u0013\t\u0019gL\u0001\u0004UQJ,\u0017\rZ\u0001\bi\"\u0014X-\u00193!\u0003!\u0001(/\u001a<US6,W#\u0001\u001f\u0002\u0019A\u0014XM\u001e+j[\u0016|F%Z9\u0015\u0005\rK\u0007b\u00026\n\u0003\u0003\u0005\r\u0001P\u0001\u0004q\u0012\n\u0014!\u00039sKZ$\u0016.\\3!Q\tQQ\u000e\u0005\u0002*]&\u0011qN\u000b\u0002\tm>d\u0017\r^5mK\u0006Aa.\u001a=u)&lW-\u0001\u0007oKb$H+[7f?\u0012*\u0017\u000f\u0006\u0002Dg\"9!\u000eDA\u0001\u0002\u0004a\u0014!\u00038fqR$\u0016.\\3!Q\tiQ.A\u0004ti>\u0004\b/\u001a3\u0016\u0003a\u0004\"!K=\n\u0005iT#a\u0002\"p_2,\u0017M\\\u0001\fgR|\u0007\u000f]3e?\u0012*\u0017\u000f\u0006\u0002D{\"9!nDA\u0001\u0002\u0004A\u0018\u0001C:u_B\u0004X\r\u001a\u0011)\u0005Ai\u0017\u0001D4fiN#\u0018M\u001d;US6,G#\u0001\u001f\u0002\u001d\u001d,GOU3ti\u0006\u0014H\u000fV5nKR\u0019A(!\u0003\t\r\u0005-!\u00031\u0001=\u0003Ey'/[4j]\u0006d7\u000b^1siRKW.Z\u0001\u0006gR\f'\u000f\u001e\u000b\u0004y\u0005E\u0001BBA\n'\u0001\u0007A(A\u0005ti\u0006\u0014H\u000fV5nK\u0006!1\u000f^8q)\ra\u0014\u0011\u0004\u0005\u0007\u00037)\u0002\u0019\u0001=\u0002\u001d%tG/\u001a:skB$H+[7fe\u0006aBO]5hO\u0016\u0014\u0018i\u0019;j_:4uN\u001d(fqRLe\u000e^3sm\u0006dG#A\"\u0002\t1|w\u000e]\u0001\u000f%\u0016\u001cWO\u001d:j]\u001e$\u0016.\\3s!\t)\u0016dE\u0002\u001aQ9\"\"!!\n\u0002\t5\f\u0017N\u001c\u000b\u0004\u0007\u0006=\u0002bBA\u00197\u0001\u0007\u00111G\u0001\u0005CJ<7\u000f\u0005\u0003*\u0003k9\u0015bAA\u001cU\t)\u0011I\u001d:bs\u0002"
)
public class RecurringTimer implements Logging {
   private final Clock clock;
   private final long period;
   private final Function1 callback;
   public final String org$apache$spark$streaming$util$RecurringTimer$$name;
   private final Thread thread;
   private volatile long prevTime;
   private volatile long nextTime;
   private volatile boolean stopped;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static void main(final String[] args) {
      RecurringTimer$.MODULE$.main(args);
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

   private Thread thread() {
      return this.thread;
   }

   private long prevTime() {
      return this.prevTime;
   }

   private void prevTime_$eq(final long x$1) {
      this.prevTime = x$1;
   }

   private long nextTime() {
      return this.nextTime;
   }

   private void nextTime_$eq(final long x$1) {
      this.nextTime = x$1;
   }

   private boolean stopped() {
      return this.stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.stopped = x$1;
   }

   public long getStartTime() {
      return (long)(.MODULE$.floor((double)this.clock.getTimeMillis() / (double)this.period) + (double)1) * this.period;
   }

   public long getRestartTime(final long originalStartTime) {
      long gap = this.clock.getTimeMillis() - originalStartTime;
      return ((long).MODULE$.floor((double)gap / (double)this.period) + 1L) * this.period + originalStartTime;
   }

   public synchronized long start(final long startTime) {
      this.nextTime_$eq(startTime);
      this.thread().start();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Started timer for ", " at time "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NAME..MODULE$, this.org$apache$spark$streaming$util$RecurringTimer$$name)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, BoxesRunTime.boxToLong(this.nextTime()))}))))));
      return this.nextTime();
   }

   public long start() {
      return this.start(this.getStartTime());
   }

   public synchronized long stop(final boolean interruptTimer) {
      if (!this.stopped()) {
         this.stopped_$eq(true);
         if (interruptTimer) {
            this.thread().interrupt();
         }

         this.thread().join();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Stopped timer for "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMER..MODULE$, this.org$apache$spark$streaming$util$RecurringTimer$$name)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"after time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, BoxesRunTime.boxToLong(this.prevTime()))}))))));
      }

      return this.prevTime();
   }

   private void triggerActionForNextInterval() {
      this.clock.waitTillTime(this.nextTime());
      this.callback.apply$mcVJ$sp(this.nextTime());
      this.prevTime_$eq(this.nextTime());
      this.nextTime_$eq(this.nextTime() + this.period);
      this.logDebug((Function0)(() -> {
         String var10000 = this.org$apache$spark$streaming$util$RecurringTimer$$name;
         return "Callback for " + var10000 + " called at time " + this.prevTime();
      }));
   }

   public void org$apache$spark$streaming$util$RecurringTimer$$loop() {
      try {
         while(true) {
            if (!this.stopped()) {
               this.triggerActionForNextInterval();
            } else {
               this.triggerActionForNextInterval();
               break;
            }
         }
      } catch (InterruptedException var2) {
      }

   }

   public RecurringTimer(final Clock clock, final long period, final Function1 callback, final String name) {
      this.clock = clock;
      this.period = period;
      this.callback = callback;
      this.org$apache$spark$streaming$util$RecurringTimer$$name = name;
      Logging.$init$(this);
      this.thread = new Thread() {
         // $FF: synthetic field
         private final RecurringTimer $outer;

         public void run() {
            this.$outer.org$apache$spark$streaming$util$RecurringTimer$$loop();
         }

         public {
            if (RecurringTimer.this == null) {
               throw null;
            } else {
               this.$outer = RecurringTimer.this;
               this.setDaemon(true);
            }
         }
      };
      this.prevTime = -1L;
      this.nextTime = -1L;
      this.stopped = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
