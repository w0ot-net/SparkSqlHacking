package org.apache.spark.metrics.sink;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.metrics.MetricsSystem$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EtA\u0002\u00192\u0011\u0003)4H\u0002\u0004>c!\u0005QG\u0010\u0005\u0006\u000b\u0006!\ta\u0012\u0005\b\u0011\u0006\u0011\r\u0011\"\u0001J\u0011\u0019\u0011\u0016\u0001)A\u0005\u0015\"91+\u0001b\u0001\n\u0003I\u0005B\u0002+\u0002A\u0003%!\nC\u0004V\u0003\t\u0007I\u0011A%\t\rY\u000b\u0001\u0015!\u0003K\u0011\u001d9\u0016A1A\u0005\u0002%Ca\u0001W\u0001!\u0002\u0013Q\u0005bB-\u0002\u0005\u0004%\t!\u0013\u0005\u00075\u0006\u0001\u000b\u0011\u0002&\t\u000fm\u000b!\u0019!C\u0001\u0013\"1A,\u0001Q\u0001\n)Cq!X\u0001C\u0002\u0013\u0005\u0011\n\u0003\u0004_\u0003\u0001\u0006IA\u0013\u0005\b?\u0006\u0011\r\u0011\"\u0001J\u0011\u0019\u0001\u0017\u0001)A\u0005\u0015\"9\u0011-\u0001b\u0001\n\u0003I\u0005B\u00022\u0002A\u0003%!\nC\u0004d\u0003\t\u0007I\u0011A%\t\r\u0011\f\u0001\u0015!\u0003K\u0011\u001d)\u0017A1A\u0005\u0002%CaAZ\u0001!\u0002\u0013Qe!B\u001f2\u0001U:\u0007\u0002C9\u001a\u0005\u000b\u0007I\u0011\u0001:\t\u0011eL\"\u0011!Q\u0001\nMD\u0001B_\r\u0003\u0006\u0004%\ta\u001f\u0005\n\u0003\u0017I\"\u0011!Q\u0001\nqDa!R\r\u0005\u0002\u00055\u0001\u0002CA\u000b3\t\u0007I\u0011A%\t\u000f\u0005]\u0011\u0004)A\u0005\u0015\"I\u0011\u0011D\rC\u0002\u0013\u0005\u00111\u0004\u0005\t\u0003GI\u0002\u0015!\u0003\u0002\u001e!I\u0011QE\rC\u0002\u0013\u0005\u00111\u0004\u0005\t\u0003OI\u0002\u0015!\u0003\u0002\u001e!I\u0011\u0011F\rC\u0002\u0013\u0005\u00111\u0006\u0005\t\u0003sI\u0002\u0015!\u0003\u0002.!A\u00111H\rC\u0002\u0013\u0005\u0011\nC\u0004\u0002>e\u0001\u000b\u0011\u0002&\t\u0013\u0005}\u0012D1A\u0005\u0002\u0005\u0005\u0003\u0002CA%3\u0001\u0006I!a\u0011\t\u0013\u0005]\u0013D1A\u0005\u0002\u0005e\u0003\u0002CA13\u0001\u0006I!a\u0017\t\u000f\u0005\r\u0014\u0004\"\u0011\u0002f!9\u0011QN\r\u0005B\u0005\u0015\u0004bBA83\u0011\u0005\u0013QM\u0001\u000b'R\fGo\u001d3TS:\\'B\u0001\u001a4\u0003\u0011\u0019\u0018N\\6\u000b\u0005Q*\u0014aB7fiJL7m\u001d\u0006\u0003m]\nQa\u001d9be.T!\u0001O\u001d\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0014aA8sOB\u0011A(A\u0007\u0002c\tQ1\u000b^1ug\u0012\u001c\u0016N\\6\u0014\u0005\u0005y\u0004C\u0001!D\u001b\u0005\t%\"\u0001\"\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\u000b%AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005Y\u0014aD*U\u0003R\u001bFiX&F3~Cuj\u0015+\u0016\u0003)\u0003\"a\u0013)\u000e\u00031S!!\u0014(\u0002\t1\fgn\u001a\u0006\u0002\u001f\u0006!!.\u0019<b\u0013\t\tFJ\u0001\u0004TiJLgnZ\u0001\u0011'R\u000bEk\u0015#`\u0017\u0016Kv\fS(T)\u0002\nqb\u0015+B)N#ulS#Z?B{%\u000bV\u0001\u0011'R\u000bEk\u0015#`\u0017\u0016Kv\fU(S)\u0002\n\u0011c\u0015+B)N#ulS#Z?B+%+S(E\u0003I\u0019F+\u0011+T\t~[U)W0Q\u000bJKu\n\u0012\u0011\u0002\u001fM#\u0016\tV*E?.+\u0015lX+O\u0013R\u000b\u0001c\u0015+B)N#ulS#Z?Vs\u0015\n\u0016\u0011\u0002#M#\u0016\tV*E?.+\u0015l\u0018)S\u000b\u001aK\u0005,\u0001\nT)\u0006#6\u000bR0L\u000bf{\u0006KU#G\u0013b\u0003\u0013\u0001E*U\u0003R\u001bFiX&F3~\u0013ViR#Y\u0003E\u0019F+\u0011+T\t~[U)W0S\u000b\u001e+\u0005\fI\u0001\u0014'R\u000bEk\u0015#`\t\u00163\u0015)\u0016'U?\"{5\u000bV\u0001\u0015'R\u000bEk\u0015#`\t\u00163\u0015)\u0016'U?\"{5\u000b\u0016\u0011\u0002'M#\u0016\tV*E?\u0012+e)Q+M)~\u0003vJ\u0015+\u0002)M#\u0016\tV*E?\u0012+e)Q+M)~\u0003vJ\u0015+!\u0003U\u0019F+\u0011+T\t~#UIR!V\u0019R{\u0006+\u0012*J\u001f\u0012\u000bac\u0015+B)N#u\fR#G\u0003VcEk\u0018)F%&{E\tI\u0001\u0014'R\u000bEk\u0015#`\t\u00163\u0015)\u0016'U?Vs\u0015\nV\u0001\u0015'R\u000bEk\u0015#`\t\u00163\u0015)\u0016'U?Vs\u0015\n\u0016\u0011\u0002+M#\u0016\tV*E?\u0012+e)Q+M)~\u0003&+\u0012$J1\u000612\u000bV!U'\u0012{F)\u0012$B+2#v\f\u0015*F\r&C\u0006e\u0005\u0003\u001a\u007f!\\\u0007C\u0001\u001fj\u0013\tQ\u0017G\u0001\u0003TS:\\\u0007C\u00017p\u001b\u0005i'B\u000186\u0003!Ig\u000e^3s]\u0006d\u0017B\u00019n\u0005\u001daunZ4j]\u001e\f\u0001\u0002\u001d:pa\u0016\u0014H/_\u000b\u0002gB\u0011Ao^\u0007\u0002k*\u0011aOT\u0001\u0005kRLG.\u0003\u0002yk\nQ\u0001K]8qKJ$\u0018.Z:\u0002\u0013A\u0014x\u000e]3sif\u0004\u0013\u0001\u0003:fO&\u001cHO]=\u0016\u0003q\u00042!`A\u0004\u001b\u0005q(B\u0001\u001b\u0000\u0015\u0011\t\t!a\u0001\u0002\u0011\r|G-\u00195bY\u0016T!!!\u0002\u0002\u0007\r|W.C\u0002\u0002\ny\u0014a\"T3ue&\u001c'+Z4jgR\u0014\u00180A\u0005sK\u001eL7\u000f\u001e:zAQ1\u0011qBA\t\u0003'\u0001\"\u0001P\r\t\u000bEt\u0002\u0019A:\t\u000bit\u0002\u0019\u0001?\u0002\t!|7\u000f^\u0001\u0006Q>\u001cH\u000fI\u0001\u0005a>\u0014H/\u0006\u0002\u0002\u001eA\u0019\u0001)a\b\n\u0007\u0005\u0005\u0012IA\u0002J]R\fQ\u0001]8si\u0002\n!\u0002]8mYB+'/[8e\u0003-\u0001x\u000e\u001c7QKJLw\u000e\u001a\u0011\u0002\u0011A|G\u000e\\+oSR,\"!!\f\u0011\t\u0005=\u0012QG\u0007\u0003\u0003cQ1!a\rv\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0005\u0003o\t\tD\u0001\u0005US6,WK\\5u\u0003%\u0001x\u000e\u001c7V]&$\b%\u0001\u0004qe\u00164\u0017\u000e_\u0001\baJ,g-\u001b=!\u0003\u00191\u0017\u000e\u001c;feV\u0011\u00111\t\n\u0007\u0003\u000b\nY%!\u0015\u0007\r\u0005\u001d#\u0006AA\"\u00051a$/\u001a4j]\u0016lWM\u001c;?\u0003\u001d1\u0017\u000e\u001c;fe\u0002\u00022aSA'\u0013\r\ty\u0005\u0014\u0002\u0007\u001f\nTWm\u0019;\u0011\u0007u\f\u0019&C\u0002\u0002Vy\u0014A\"T3ue&\u001cg)\u001b7uKJ\f\u0001B]3q_J$XM]\u000b\u0003\u00037\u00022\u0001PA/\u0013\r\ty&\r\u0002\u000f'R\fGo\u001d3SKB|'\u000f^3s\u0003%\u0011X\r]8si\u0016\u0014\b%A\u0003ti\u0006\u0014H\u000f\u0006\u0002\u0002hA\u0019\u0001)!\u001b\n\u0007\u0005-\u0014I\u0001\u0003V]&$\u0018\u0001B:u_B\faA]3q_J$\b"
)
public class StatsdSink implements Sink, Logging {
   private final Properties property;
   private final MetricRegistry registry;
   private final String host;
   private final int port;
   private final int pollPeriod;
   private final TimeUnit pollUnit;
   private final String prefix;
   private final MetricFilter filter;
   private final StatsdReporter reporter;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String STATSD_DEFAULT_PREFIX() {
      return StatsdSink$.MODULE$.STATSD_DEFAULT_PREFIX();
   }

   public static String STATSD_DEFAULT_UNIT() {
      return StatsdSink$.MODULE$.STATSD_DEFAULT_UNIT();
   }

   public static String STATSD_DEFAULT_PERIOD() {
      return StatsdSink$.MODULE$.STATSD_DEFAULT_PERIOD();
   }

   public static String STATSD_DEFAULT_PORT() {
      return StatsdSink$.MODULE$.STATSD_DEFAULT_PORT();
   }

   public static String STATSD_DEFAULT_HOST() {
      return StatsdSink$.MODULE$.STATSD_DEFAULT_HOST();
   }

   public static String STATSD_KEY_REGEX() {
      return StatsdSink$.MODULE$.STATSD_KEY_REGEX();
   }

   public static String STATSD_KEY_PREFIX() {
      return StatsdSink$.MODULE$.STATSD_KEY_PREFIX();
   }

   public static String STATSD_KEY_UNIT() {
      return StatsdSink$.MODULE$.STATSD_KEY_UNIT();
   }

   public static String STATSD_KEY_PERIOD() {
      return StatsdSink$.MODULE$.STATSD_KEY_PERIOD();
   }

   public static String STATSD_KEY_PORT() {
      return StatsdSink$.MODULE$.STATSD_KEY_PORT();
   }

   public static String STATSD_KEY_HOST() {
      return StatsdSink$.MODULE$.STATSD_KEY_HOST();
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

   public Properties property() {
      return this.property;
   }

   public MetricRegistry registry() {
      return this.registry;
   }

   public String host() {
      return this.host;
   }

   public int port() {
      return this.port;
   }

   public int pollPeriod() {
      return this.pollPeriod;
   }

   public TimeUnit pollUnit() {
      return this.pollUnit;
   }

   public String prefix() {
      return this.prefix;
   }

   public MetricFilter filter() {
      return this.filter;
   }

   public StatsdReporter reporter() {
      return this.reporter;
   }

   public void start() {
      this.reporter().start((long)this.pollPeriod(), this.pollUnit());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"StatsdSink started with prefix: '", "'"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PREFIX..MODULE$, this.prefix())})))));
   }

   public void stop() {
      this.reporter().stop();
      this.logInfo((Function0)(() -> "StatsdSink stopped."));
   }

   public void report() {
      this.reporter().report();
   }

   public StatsdSink(final Properties property, final MetricRegistry registry) {
      this.property = property;
      this.registry = registry;
      Logging.$init$(this);
      this.host = property.getProperty(StatsdSink$.MODULE$.STATSD_KEY_HOST(), StatsdSink$.MODULE$.STATSD_DEFAULT_HOST());
      this.port = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(property.getProperty(StatsdSink$.MODULE$.STATSD_KEY_PORT(), StatsdSink$.MODULE$.STATSD_DEFAULT_PORT())));
      this.pollPeriod = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(property.getProperty(StatsdSink$.MODULE$.STATSD_KEY_PERIOD(), StatsdSink$.MODULE$.STATSD_DEFAULT_PERIOD())));
      this.pollUnit = TimeUnit.valueOf(property.getProperty(StatsdSink$.MODULE$.STATSD_KEY_UNIT(), StatsdSink$.MODULE$.STATSD_DEFAULT_UNIT()).toUpperCase(Locale.ROOT));
      this.prefix = property.getProperty(StatsdSink$.MODULE$.STATSD_KEY_PREFIX(), StatsdSink$.MODULE$.STATSD_DEFAULT_PREFIX());
      Option var4 = scala.Option..MODULE$.apply(property.getProperty(StatsdSink$.MODULE$.STATSD_KEY_REGEX()));
      MetricFilter var10001;
      if (var4 instanceof Some var5) {
         String pattern = (String)var5.value();
         var10001 = new MetricFilter(pattern) {
            private final String pattern$1;

            public boolean matches(final String name, final Metric metric) {
               return scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString(this.pattern$1)).findFirstMatchIn(name).isDefined();
            }

            public {
               this.pattern$1 = pattern$1;
            }
         };
      } else {
         if (!scala.None..MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         var10001 = MetricFilter.ALL;
      }

      this.filter = var10001;
      MetricsSystem$.MODULE$.checkMinimalPollingPeriod(this.pollUnit(), this.pollPeriod());
      this.reporter = new StatsdReporter(registry, this.host(), this.port(), this.prefix(), this.filter(), StatsdReporter$.MODULE$.$lessinit$greater$default$6(), StatsdReporter$.MODULE$.$lessinit$greater$default$7());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
