package org.apache.spark.scheduler;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.metrics.source.Source;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e4Q\u0001D\u0007\u0001\u001fUA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\u0006a\u0001!\t!\r\u0005\bk\u0001\u0011\r\u0011\"\u00117\u0011\u0019\u0011\u0005\u0001)A\u0005o!91\t\u0001b\u0001\n\u0003\"\u0005B\u0002(\u0001A\u0003%Q\tC\u0004P\u0001\t\u0007I\u0011\u0001)\t\rQ\u0003\u0001\u0015!\u0003R\u0011\u001d)\u0006A1A\u0005\nYCaA\u0019\u0001!\u0002\u00139\u0006\"B2\u0001\t\u0003!'A\u0006'jm\u0016d\u0015n\u001d;f]\u0016\u0014()^:NKR\u0014\u0018nY:\u000b\u00059y\u0011!C:dQ\u0016$W\u000f\\3s\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7\u0003\u0002\u0001\u00179\u0011\u0002\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0007CA\u000f#\u001b\u0005q\"BA\u0010!\u0003\u0019\u0019x.\u001e:dK*\u0011\u0011eD\u0001\b[\u0016$(/[2t\u0013\t\u0019cD\u0001\u0004T_V\u00148-\u001a\t\u0003K!j\u0011A\n\u0006\u0003O=\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003S\u0019\u0012q\u0001T8hO&tw-\u0001\u0003d_:47\u0001\u0001\t\u0003[9j\u0011aD\u0005\u0003_=\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\t\u0011D\u0007\u0005\u00024\u00015\tQ\u0002C\u0003+\u0005\u0001\u0007A&\u0001\u0006t_V\u00148-\u001a(b[\u0016,\u0012a\u000e\t\u0003q}r!!O\u001f\u0011\u0005iBR\"A\u001e\u000b\u0005qZ\u0013A\u0002\u001fs_>$h(\u0003\u0002?1\u00051\u0001K]3eK\u001aL!\u0001Q!\u0003\rM#(/\u001b8h\u0015\tq\u0004$A\u0006t_V\u00148-\u001a(b[\u0016\u0004\u0013AD7fiJL7MU3hSN$(/_\u000b\u0002\u000bB\u0011a\tT\u0007\u0002\u000f*\u0011\u0011\u0005\u0013\u0006\u0003\u0013*\u000b\u0001bY8eC\"\fG.\u001a\u0006\u0002\u0017\u0006\u00191m\\7\n\u00055;%AD'fiJL7MU3hSN$(/_\u0001\u0010[\u0016$(/[2SK\u001eL7\u000f\u001e:zA\u0005ya.^7Fm\u0016tGo\u001d)pgR,G-F\u0001R!\t1%+\u0003\u0002T\u000f\n91i\\;oi\u0016\u0014\u0018\u0001\u00058v[\u00163XM\u001c;t!>\u001cH/\u001a3!\u0003Y\u0001XM\u001d'jgR,g.\u001a:DY\u0006\u001c8\u000fV5nKJ\u001cX#A,\u0011\takvgX\u0007\u00023*\u0011!lW\u0001\b[V$\u0018M\u00197f\u0015\ta\u0006$\u0001\u0006d_2dWm\u0019;j_:L!AX-\u0003\u00075\u000b\u0007\u000f\u0005\u0002GA&\u0011\u0011m\u0012\u0002\u0006)&lWM]\u0001\u0018a\u0016\u0014H*[:uK:,'o\u00117bgN$\u0016.\\3sg\u0002\n\u0001dZ3u)&lWM\u001d$pe2K7\u000f^3oKJ\u001cE.Y:t)\t)\u0007\u000eE\u0002\u0018M~K!a\u001a\r\u0003\r=\u0003H/[8o\u0011\u0015I7\u00021\u0001k\u0003\r\u0019Gn\u001d\u0019\u0003WB\u00042\u0001\u000f7o\u0013\ti\u0017IA\u0003DY\u0006\u001c8\u000f\u0005\u0002pa2\u0001A!C9i\u0003\u0003\u0005\tQ!\u0001s\u0005\ryF%M\t\u0003gZ\u0004\"a\u0006;\n\u0005UD\"a\u0002(pi\"Lgn\u001a\t\u0003g]L!\u0001_\u0007\u0003-M\u0003\u0018M]6MSN$XM\\3s\u0013:$XM\u001d4bG\u0016\u0004"
)
public class LiveListenerBusMetrics implements Source, Logging {
   private final SparkConf conf;
   private final String sourceName;
   private final MetricRegistry metricRegistry;
   private final Counter numEventsPosted;
   private final Map perListenerClassTimers;
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public String sourceName() {
      return this.sourceName;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public Counter numEventsPosted() {
      return this.numEventsPosted;
   }

   private Map perListenerClassTimers() {
      return this.perListenerClassTimers;
   }

   public synchronized Option getTimerForListenerClass(final Class cls) {
      String className = cls.getName();
      int maxTimed = BoxesRunTime.unboxToInt(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.LISTENER_BUS_METRICS_MAX_LISTENER_CLASSES_TIMED()));
      return this.perListenerClassTimers().get(className).orElse(() -> {
         if (this.perListenerClassTimers().size() == maxTimed) {
            if (maxTimed != 0) {
               this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not measuring processing time for listener class "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " because a "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, className)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"maximum of ", " listener classes are already timed."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_SIZE..MODULE$, BoxesRunTime.boxToInteger(maxTimed))}))))));
            }

            return scala.None..MODULE$;
         } else {
            this.perListenerClassTimers().update(className, this.metricRegistry().timer(MetricRegistry.name("listenerProcessingTime", new String[]{className})));
            return this.perListenerClassTimers().get(className);
         }
      });
   }

   public LiveListenerBusMetrics(final SparkConf conf) {
      this.conf = conf;
      Logging.$init$(this);
      this.sourceName = "LiveListenerBus";
      this.metricRegistry = new MetricRegistry();
      this.numEventsPosted = this.metricRegistry().counter(MetricRegistry.name("numEventsPosted", new String[0]));
      this.perListenerClassTimers = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
