package org.apache.spark;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513Q!\u0003\u0006\u0001\u0015AA\u0001\"\b\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\tK\u0001\u0011\t\u0011)A\u0005M!A\u0011\u0007\u0001B\u0001B\u0003%!\u0007C\u00036\u0001\u0011\u0005a\u0007C\u0004=\u0001\t\u0007I\u0011B\u001f\t\r!\u0003\u0001\u0015!\u0003?\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u0015Y\u0005\u0001\"\u0001K\u0005-AU-\u0019:uE\u0016\fG/\u001a:\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c2\u0001A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011\u0001dG\u0007\u00023)\u0011!DC\u0001\tS:$XM\u001d8bY&\u0011A$\u0007\u0002\b\u0019><w-\u001b8h\u0003=\u0011X\r]8si\"+\u0017M\u001d;cK\u0006$8\u0001\u0001\t\u0004%\u0001\u0012\u0013BA\u0011\u0014\u0005%1UO\\2uS>t\u0007\u0007\u0005\u0002\u0013G%\u0011Ae\u0005\u0002\u0005+:LG/\u0001\u0003oC6,\u0007CA\u0014/\u001d\tAC\u0006\u0005\u0002*'5\t!F\u0003\u0002,=\u00051AH]8pizJ!!L\n\u0002\rA\u0013X\rZ3g\u0013\ty\u0003G\u0001\u0004TiJLgn\u001a\u0006\u0003[M\t!\"\u001b8uKJ4\u0018\r\\'t!\t\u00112'\u0003\u00025'\t!Aj\u001c8h\u0003\u0019a\u0014N\\5u}Q!q'\u000f\u001e<!\tA\u0004!D\u0001\u000b\u0011\u0015iB\u00011\u0001 \u0011\u0015)C\u00011\u0001'\u0011\u0015\tD\u00011\u00013\u0003-AW-\u0019:uE\u0016\fG/\u001a:\u0016\u0003y\u0002\"a\u0010$\u000e\u0003\u0001S!!\u0011\"\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002D\t\u0006!Q\u000f^5m\u0015\u0005)\u0015\u0001\u00026bm\u0006L!a\u0012!\u00031M\u001b\u0007.\u001a3vY\u0016$W\t_3dkR|'oU3sm&\u001cW-\u0001\u0007iK\u0006\u0014HOY3bi\u0016\u0014\b%A\u0003ti\u0006\u0014H\u000fF\u0001#\u0003\u0011\u0019Ho\u001c9"
)
public class Heartbeater implements Logging {
   public final Function0 org$apache$spark$Heartbeater$$reportHeartbeat;
   private final long intervalMs;
   private final ScheduledExecutorService heartbeater;
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

   private ScheduledExecutorService heartbeater() {
      return this.heartbeater;
   }

   public void start() {
      long initialDelay = this.intervalMs + (long)((int)(.MODULE$.random() * (double)this.intervalMs));
      Runnable heartbeatTask = new Runnable() {
         // $FF: synthetic field
         private final Heartbeater $outer;

         public void run() {
            Utils$.MODULE$.logUncaughtExceptions(this.$outer.org$apache$spark$Heartbeater$$reportHeartbeat);
         }

         public {
            if (Heartbeater.this == null) {
               throw null;
            } else {
               this.$outer = Heartbeater.this;
            }
         }
      };
      this.heartbeater().scheduleAtFixedRate(heartbeatTask, initialDelay, this.intervalMs, TimeUnit.MILLISECONDS);
   }

   public void stop() {
      this.heartbeater().shutdown();
      this.heartbeater().awaitTermination(10L, TimeUnit.SECONDS);
   }

   public Heartbeater(final Function0 reportHeartbeat, final String name, final long intervalMs) {
      this.org$apache$spark$Heartbeater$$reportHeartbeat = reportHeartbeat;
      this.intervalMs = intervalMs;
      Logging.$init$(this);
      this.heartbeater = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor(name);
   }
}
