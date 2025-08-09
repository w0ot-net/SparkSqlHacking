package org.apache.spark.metrics;

import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.metrics.sink.MetricsServlet;
import org.apache.spark.metrics.sink.PrometheusServlet;
import org.apache.spark.metrics.sink.Sink;
import org.apache.spark.metrics.source.Source;
import org.apache.spark.metrics.source.StaticSources$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001db!\u0002\u0017.\u0001=*\u0004\u0002\u0003\"\u0001\u0005\u000b\u0007I\u0011\u0001#\t\u0011A\u0003!\u0011!Q\u0001\n\u0015C\u0001\"\u0015\u0001\u0003\u0002\u0003\u0006IA\u0015\u0005\u0006-\u0002!Ia\u0016\u0005\u00079\u0002\u0001\u000b\u0011B/\t\u000f\u0001\u0004!\u0019!C\u0005C\"1\u0001\u000f\u0001Q\u0001\n\tDq!\u001d\u0001C\u0002\u0013%!\u000f\u0003\u0004{\u0001\u0001\u0006Ia\u001d\u0005\bw\u0002\u0011\r\u0011\"\u0003}\u0011\u001d\ti\u0001\u0001Q\u0001\nuD\u0011\"a\u0004\u0001\u0001\u0004%I!!\u0005\t\u0013\u0005e\u0001\u00011A\u0005\n\u0005m\u0001\u0002CA\u0014\u0001\u0001\u0006K!a\u0005\t\u0013\u0005%\u0002\u00011A\u0005\n\u0005-\u0002\"CA\u001d\u0001\u0001\u0007I\u0011BA\u001e\u0011!\ty\u0004\u0001Q!\n\u00055\u0002\"CA!\u0001\u0001\u0007I\u0011BA\"\u0011%\ti\u0005\u0001a\u0001\n\u0013\ty\u0005\u0003\u0005\u0002T\u0001\u0001\u000b\u0015BA#\u0011\u001d\t)\u0006\u0001C\u0001\u0003/Bq!a\u001d\u0001\t\u0003\t)\bC\u0005\u0002|\u0001\t\n\u0011\"\u0001\u0002~!9\u00111\u0013\u0001\u0005\u0002\u0005U\u0005bBAL\u0001\u0011\u0005\u0011Q\u0013\u0005\t\u00033\u0003A\u0011A\u0018\u0002\u001c\"9\u0011q\u0014\u0001\u0005\u0002\u0005\u0005\u0006bBA]\u0001\u0011\u0005\u00111\u0018\u0005\b\u0003\u007f\u0003A\u0011AAa\u0011\u001d\t)\r\u0001C\u0005\u0003+Cq!a2\u0001\t\u0013\t)\nC\u0004\u0002J\u0002!\t!a3\b\u0011\u0005uW\u0006#\u00010\u0003?4q\u0001L\u0017\t\u0002=\n\t\u000f\u0003\u0004WE\u0011\u0005\u00111\u001d\u0005\n\u0003K\u0014#\u0019!C\u0001\u0003OD\u0001\"a>#A\u0003%\u0011\u0011\u001e\u0005\n\u0003s\u0014#\u0019!C\u0001\u0003OD\u0001\"a?#A\u0003%\u0011\u0011\u001e\u0005\t\u0003{\u0014\u0003\u0015!\u0003\u0002\u0000\"A!1\u0002\u0012!\u0002\u0013\u0011i\u0001C\u0004\u0003\u0014\t\"\tA!\u0006\t\u000f\t}!\u0005\"\u0001\u0003\"\tiQ*\u001a;sS\u000e\u001c8+_:uK6T!AL\u0018\u0002\u000f5,GO]5dg*\u0011\u0001'M\u0001\u0006gB\f'o\u001b\u0006\u0003eM\na!\u00199bG\",'\"\u0001\u001b\u0002\u0007=\u0014xmE\u0002\u0001mq\u0002\"a\u000e\u001e\u000e\u0003aR\u0011!O\u0001\u0006g\u000e\fG.Y\u0005\u0003wa\u0012a!\u00118z%\u00164\u0007CA\u001fA\u001b\u0005q$BA 0\u0003!Ig\u000e^3s]\u0006d\u0017BA!?\u0005\u001daunZ4j]\u001e\f\u0001\"\u001b8ti\u0006t7-Z\u0002\u0001+\u0005)\u0005C\u0001$N\u001d\t95\n\u0005\u0002Iq5\t\u0011J\u0003\u0002K\u0007\u00061AH]8pizJ!\u0001\u0014\u001d\u0002\rA\u0013X\rZ3g\u0013\tquJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0019b\n\u0011\"\u001b8ti\u0006t7-\u001a\u0011\u0002\t\r|gN\u001a\t\u0003'Rk\u0011aL\u0005\u0003+>\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\rA&l\u0017\t\u00033\u0002i\u0011!\f\u0005\u0006\u0005\u0012\u0001\r!\u0012\u0005\u0006#\u0012\u0001\rAU\u0001\u000e[\u0016$(/[2t\u0007>tg-[4\u0011\u0005es\u0016BA0.\u00055iU\r\u001e:jGN\u001cuN\u001c4jO\u0006)1/\u001b8lgV\t!\rE\u0002dQ*l\u0011\u0001\u001a\u0006\u0003K\u001a\fq!\\;uC\ndWM\u0003\u0002hq\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005%$'aC!se\u0006L()\u001e4gKJ\u0004\"a\u001b8\u000e\u00031T!!\\\u0017\u0002\tMLgn[\u0005\u0003_2\u0014AaU5oW\u000611/\u001b8lg\u0002\nqa]8ve\u000e,7/F\u0001t!\r\u0019\u0007\u000e\u001e\t\u0003kbl\u0011A\u001e\u0006\u0003o6\naa]8ve\u000e,\u0017BA=w\u0005\u0019\u0019v.\u001e:dK\u0006A1o\\;sG\u0016\u001c\b%\u0001\u0005sK\u001eL7\u000f\u001e:z+\u0005i\bc\u0001@\u0002\n5\tqPC\u0002/\u0003\u0003QA!a\u0001\u0002\u0006\u0005A1m\u001c3bQ\u0006dWM\u0003\u0002\u0002\b\u0005\u00191m\\7\n\u0007\u0005-qP\u0001\bNKR\u0014\u0018n\u0019*fO&\u001cHO]=\u0002\u0013I,w-[:uef\u0004\u0013a\u0002:v]:LgnZ\u000b\u0003\u0003'\u00012aNA\u000b\u0013\r\t9\u0002\u000f\u0002\b\u0005>|G.Z1o\u0003-\u0011XO\u001c8j]\u001e|F%Z9\u0015\t\u0005u\u00111\u0005\t\u0004o\u0005}\u0011bAA\u0011q\t!QK\\5u\u0011%\t)#DA\u0001\u0002\u0004\t\u0019\"A\u0002yIE\n\u0001B];o]&tw\rI\u0001\u000f[\u0016$(/[2t'\u0016\u0014h\u000f\\3u+\t\ti\u0003E\u00038\u0003_\t\u0019$C\u0002\u00022a\u0012aa\u00149uS>t\u0007cA6\u00026%\u0019\u0011q\u00077\u0003\u001d5+GO]5dgN+'O\u001e7fi\u0006\u0011R.\u001a;sS\u000e\u001c8+\u001a:wY\u0016$x\fJ3r)\u0011\ti\"!\u0010\t\u0013\u0005\u0015\u0002#!AA\u0002\u00055\u0012aD7fiJL7m]*feZdW\r\u001e\u0011\u0002#A\u0014x.\\3uQ\u0016,8oU3sm2,G/\u0006\u0002\u0002FA)q'a\f\u0002HA\u00191.!\u0013\n\u0007\u0005-CNA\tQe>lW\r\u001e5fkN\u001cVM\u001d<mKR\fQ\u0003\u001d:p[\u0016$\b.Z;t'\u0016\u0014h\u000f\\3u?\u0012*\u0017\u000f\u0006\u0003\u0002\u001e\u0005E\u0003\"CA\u0013'\u0005\u0005\t\u0019AA#\u0003I\u0001(o\\7fi\",Wo]*feZdW\r\u001e\u0011\u0002%\u001d,GoU3sm2,G\u000fS1oI2,'o]\u000b\u0003\u00033\u0002RaNA.\u0003?J1!!\u00189\u0005\u0015\t%O]1z!\u0011\t\t'a\u001c\u000e\u0005\u0005\r$\u0002BA3\u0003O\nqa]3sm2,GO\u0003\u0003\u0002j\u0005-\u0014!\u00026fiRL(bAA7g\u00059Qm\u00197jaN,\u0017\u0002BA9\u0003G\u0012QcU3sm2,GoQ8oi\u0016DH\u000fS1oI2,'/A\u0003ti\u0006\u0014H\u000f\u0006\u0003\u0002\u001e\u0005]\u0004\"CA=-A\u0005\t\u0019AA\n\u0003U\u0011XmZ5ti\u0016\u00148\u000b^1uS\u000e\u001cv.\u001e:dKN\fqb\u001d;beR$C-\u001a4bk2$H%M\u000b\u0003\u0003\u007fRC!a\u0005\u0002\u0002.\u0012\u00111\u0011\t\u0005\u0003\u000b\u000by)\u0004\u0002\u0002\b*!\u0011\u0011RAF\u0003%)hn\u00195fG.,GMC\u0002\u0002\u000eb\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t*a\"\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0003ti>\u0004HCAA\u000f\u0003\u0019\u0011X\r]8si\u0006\t\"-^5mIJ+w-[:ueft\u0015-\\3\u0015\u0007\u0015\u000bi\nC\u0003x5\u0001\u0007A/\u0001\thKR\u001cv.\u001e:dKN\u0014\u0015PT1nKR!\u00111UA[!\u0015\t)+a,u\u001d\u0011\t9+a+\u000f\u0007!\u000bI+C\u0001:\u0013\r\ti\u000bO\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t,a-\u0003\u0007M+\u0017OC\u0002\u0002.bBa!a.\u001c\u0001\u0004)\u0015AC:pkJ\u001cWMT1nK\u0006q!/Z4jgR,'oU8ve\u000e,G\u0003BA\u000f\u0003{CQa\u001e\u000fA\u0002Q\fAB]3n_Z,7k\\;sG\u0016$B!!\b\u0002D\")q/\ba\u0001i\u0006y!/Z4jgR,'oU8ve\u000e,7/A\u0007sK\u001eL7\u000f^3s'&t7n]\u0001\u0012[\u0016$(/[2t!J|\u0007/\u001a:uS\u0016\u001cHCAAg!\u0011\ty-!7\u000e\u0005\u0005E'\u0002BAj\u0003+\fA!\u001e;jY*\u0011\u0011q[\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\\\u0006E'A\u0003)s_B,'\u000f^5fg\u0006iQ*\u001a;sS\u000e\u001c8+_:uK6\u0004\"!\u0017\u0012\u0014\u0005\t2DCAAp\u0003)\u0019\u0016JT&`%\u0016;U\tW\u000b\u0003\u0003S\u0004B!a;\u0002t6\u0011\u0011Q\u001e\u0006\u0005\u0003_\f\t0\u0001\u0005nCR\u001c\u0007.\u001b8h\u0015\r\t\u0019\u000eO\u0005\u0005\u0003k\fiOA\u0003SK\u001e,\u00070A\u0006T\u0013:[uLU#H\u000bb\u0003\u0013\u0001D*P+J\u001bUi\u0018*F\u000f\u0016C\u0016!D*P+J\u001bUi\u0018*F\u000f\u0016C\u0006%A\tN\u0013:KU*\u0011'`!>cEjX+O\u0013R\u0003BA!\u0001\u0003\b5\u0011!1\u0001\u0006\u0005\u0005\u000b\t\t.\u0001\u0006d_:\u001cWO\u001d:f]RLAA!\u0003\u0003\u0004\tAA+[7f+:LG/A\nN\u0013:KU*\u0011'`!>cEj\u0018)F%&{E\tE\u00028\u0005\u001fI1A!\u00059\u0005\rIe\u000e^\u0001\u001aG\",7m['j]&l\u0017\r\u001c)pY2Lgn\u001a)fe&|G\r\u0006\u0004\u0002\u001e\t]!1\u0004\u0005\b\u00053Q\u0003\u0019AA\u0000\u0003!\u0001x\u000e\u001c7V]&$\bb\u0002B\u000fU\u0001\u0007!QB\u0001\u000ba>dG\u000eU3sS>$\u0017aE2sK\u0006$X-T3ue&\u001c7oU=ti\u0016lG#\u0002-\u0003$\t\u0015\u0002\"\u0002\",\u0001\u0004)\u0005\"B),\u0001\u0004\u0011\u0006"
)
public class MetricsSystem implements Logging {
   private final String instance;
   private final SparkConf conf;
   private final MetricsConfig metricsConfig;
   private final ArrayBuffer sinks;
   private final ArrayBuffer sources;
   private final MetricRegistry registry;
   private boolean running;
   private Option metricsServlet;
   private Option prometheusServlet;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static MetricsSystem createMetricsSystem(final String instance, final SparkConf conf) {
      return MetricsSystem$.MODULE$.createMetricsSystem(instance, conf);
   }

   public static void checkMinimalPollingPeriod(final TimeUnit pollUnit, final int pollPeriod) {
      MetricsSystem$.MODULE$.checkMinimalPollingPeriod(pollUnit, pollPeriod);
   }

   public static Regex SOURCE_REGEX() {
      return MetricsSystem$.MODULE$.SOURCE_REGEX();
   }

   public static Regex SINK_REGEX() {
      return MetricsSystem$.MODULE$.SINK_REGEX();
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

   public String instance() {
      return this.instance;
   }

   private ArrayBuffer sinks() {
      return this.sinks;
   }

   private ArrayBuffer sources() {
      return this.sources;
   }

   private MetricRegistry registry() {
      return this.registry;
   }

   private boolean running() {
      return this.running;
   }

   private void running_$eq(final boolean x$1) {
      this.running = x$1;
   }

   private Option metricsServlet() {
      return this.metricsServlet;
   }

   private void metricsServlet_$eq(final Option x$1) {
      this.metricsServlet = x$1;
   }

   private Option prometheusServlet() {
      return this.prometheusServlet;
   }

   private void prometheusServlet_$eq(final Option x$1) {
      this.prometheusServlet = x$1;
   }

   public ServletContextHandler[] getServletHandlers() {
      .MODULE$.require(this.running(), () -> "Can only call getServletHandlers on a running MetricsSystem");
      return (ServletContextHandler[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(.MODULE$.refArrayOps(this.metricsServlet().map((x$1) -> x$1.getHandlers(this.conf)).getOrElse(() -> (ServletContextHandler[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(ServletContextHandler.class)))), this.prometheusServlet().map((x$2) -> x$2.getHandlers(this.conf)).getOrElse(() -> (ServletContextHandler[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(ServletContextHandler.class))), scala.reflect.ClassTag..MODULE$.apply(ServletContextHandler.class));
   }

   public void start(final boolean registerStaticSources) {
      .MODULE$.require(!this.running(), () -> "Attempting to start a MetricsSystem that is already running");
      this.running_$eq(true);
      if (registerStaticSources) {
         StaticSources$.MODULE$.allSources().foreach((source) -> {
            $anonfun$start$2(this, source);
            return BoxedUnit.UNIT;
         });
         this.registerSources();
      }

      this.registerSinks();
      this.sinks().foreach((x$3) -> {
         $anonfun$start$3(x$3);
         return BoxedUnit.UNIT;
      });
   }

   public boolean start$default$1() {
      return true;
   }

   public void stop() {
      if (this.running()) {
         this.sinks().foreach((x$4) -> {
            $anonfun$stop$1(x$4);
            return BoxedUnit.UNIT;
         });
         this.registry().removeMatching((x$5, x$6) -> true);
      } else {
         this.logWarning((Function0)(() -> "Stopping a MetricsSystem that is not running"));
      }

      this.running_$eq(false);
   }

   public void report() {
      this.sinks().foreach((x$7) -> {
         $anonfun$report$1(x$7);
         return BoxedUnit.UNIT;
      });
   }

   public String buildRegistryName(final Source source) {
      Option metricsNamespace;
      Option executorId;
      String defaultName;
      label36: {
         metricsNamespace = ((Option)this.conf.get((ConfigEntry)package$.MODULE$.METRICS_NAMESPACE())).orElse(() -> this.conf.getOption("spark.app.id"));
         executorId = (Option)this.conf.get((ConfigEntry)package$.MODULE$.EXECUTOR_ID());
         defaultName = MetricRegistry.name(source.sourceName(), new String[0]);
         String var10000 = this.instance();
         String var5 = "driver";
         if (var10000 == null) {
            if (var5 == null) {
               break label36;
            }
         } else if (var10000.equals(var5)) {
            break label36;
         }

         var10000 = this.instance();
         String var6 = "executor";
         if (var10000 == null) {
            if (var6 != null) {
               return defaultName;
            }
         } else if (!var10000.equals(var6)) {
            return defaultName;
         }
      }

      if (metricsNamespace.isDefined() && executorId.isDefined()) {
         return MetricRegistry.name((String)metricsNamespace.get(), new String[]{(String)executorId.get(), source.sourceName()});
      } else {
         if (metricsNamespace.isEmpty()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using default name ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DEFAULT_NAME..MODULE$, defaultName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for source because neither "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " nor spark.app.id is set."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, package$.MODULE$.METRICS_NAMESPACE().key())}))))));
         }

         if (executorId.isEmpty()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using default name ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DEFAULT_NAME..MODULE$, defaultName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for source because spark.executor.id is not set."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         return defaultName;
      }
   }

   public Seq getSourcesByName(final String sourceName) {
      synchronized(this.sources()){}

      Seq var3;
      try {
         var3 = ((IterableOnceOps)this.sources().filter((x$8) -> BoxesRunTime.boxToBoolean($anonfun$getSourcesByName$1(sourceName, x$8)))).toSeq();
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   public void registerSource(final Source source) {
      synchronized(this.sources()){}

      try {
         ArrayBuffer var10000 = (ArrayBuffer)this.sources().$plus$eq(source);
      } catch (Throwable var8) {
         throw var8;
      }

      try {
         String regName = this.buildRegistryName(source);
         this.registry().register(regName, source.metricRegistry());
      } catch (IllegalArgumentException var7) {
         this.logInfo((Function0)(() -> "Metrics already registered"), var7);
      }

   }

   public void removeSource(final Source source) {
      synchronized(this.sources()){}

      try {
         ArrayBuffer var10000 = (ArrayBuffer)this.sources().$minus$eq(source);
      } catch (Throwable var5) {
         throw var5;
      }

      String regName = this.buildRegistryName(source);
      this.registry().removeMatching((name, x$9) -> name.startsWith(regName));
   }

   private void registerSources() {
      Properties instConfig = this.metricsConfig.getInstance(this.instance());
      HashMap sourceConfigs = this.metricsConfig.subProperties(instConfig, MetricsSystem$.MODULE$.SOURCE_REGEX());
      sourceConfigs.foreach((kv) -> {
         $anonfun$registerSources$1(this, kv);
         return BoxedUnit.UNIT;
      });
   }

   private void registerSinks() {
      Properties instConfig = this.metricsConfig.getInstance(this.instance());
      HashMap sinkConfigs = this.metricsConfig.subProperties(instConfig, MetricsSystem$.MODULE$.SINK_REGEX());
      sinkConfigs.foreach((kv) -> {
         String classPath = ((Properties)kv._2()).getProperty("class");
         if (classPath == null) {
            return BoxedUnit.UNIT;
         } else {
            try {
               Object var13;
               label55: {
                  var13 = kv._1();
                  String var3 = "servlet";
                  if (var13 == null) {
                     if (var3 == null) {
                        break label55;
                     }
                  } else if (var13.equals(var3)) {
                     break label55;
                  }

                  label56: {
                     var13 = kv._1();
                     String var5 = "prometheusServlet";
                     if (var13 == null) {
                        if (var5 == null) {
                           break label56;
                        }
                     } else if (var13.equals(var5)) {
                        break label56;
                     }

                     try {
                        var12 = (Sink)Utils$.MODULE$.classForName(classPath, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()).getConstructor(Properties.class, MetricRegistry.class).newInstance(kv._2(), this.registry());
                     } catch (NoSuchMethodException var9) {
                        var12 = (Sink)Utils$.MODULE$.classForName(classPath, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()).getConstructor(Properties.class, MetricRegistry.class, SecurityManager.class).newInstance(kv._2(), this.registry(), null);
                     }

                     Sink sink = var12;
                     var13 = this.sinks().$plus$eq(sink);
                     return var13;
                  }

                  PrometheusServlet servlet = (PrometheusServlet)Utils$.MODULE$.classForName(classPath, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()).getConstructor(Properties.class, MetricRegistry.class).newInstance(kv._2(), this.registry());
                  this.prometheusServlet_$eq(new Some(servlet));
                  var13 = BoxedUnit.UNIT;
                  return var13;
               }

               MetricsServlet servlet = (MetricsServlet)Utils$.MODULE$.classForName(classPath, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()).getConstructor(Properties.class, MetricRegistry.class).newInstance(kv._2(), this.registry());
               this.metricsServlet_$eq(new Some(servlet));
               var13 = BoxedUnit.UNIT;
               return var13;
            } catch (Exception var10) {
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Sink class ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, classPath)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"cannot be instantiated"})))).log(scala.collection.immutable.Nil..MODULE$))));
               throw var10;
            }
         }
      });
   }

   public Properties metricsProperties() {
      return this.metricsConfig.properties();
   }

   // $FF: synthetic method
   public static final void $anonfun$start$2(final MetricsSystem $this, final Source source) {
      $this.registerSource(source);
   }

   // $FF: synthetic method
   public static final void $anonfun$start$3(final Sink x$3) {
      x$3.start();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final Sink x$4) {
      x$4.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$report$1(final Sink x$7) {
      x$7.report();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getSourcesByName$1(final String sourceName$1, final Source x$8) {
      boolean var3;
      label23: {
         String var10000 = x$8.sourceName();
         if (var10000 == null) {
            if (sourceName$1 == null) {
               break label23;
            }
         } else if (var10000.equals(sourceName$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final void $anonfun$registerSources$1(final MetricsSystem $this, final Tuple2 kv) {
      String classPath = ((Properties)kv._2()).getProperty("class");

      try {
         Source source = (Source)Utils$.MODULE$.classForName(classPath, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()).getConstructor().newInstance();
         $this.registerSource(source);
      } catch (Exception var5) {
         $this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Source class ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, classPath)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"cannot be instantiated"})))).log(scala.collection.immutable.Nil..MODULE$))), var5);
      }

   }

   public MetricsSystem(final String instance, final SparkConf conf) {
      this.instance = instance;
      this.conf = conf;
      Logging.$init$(this);
      this.metricsConfig = new MetricsConfig(conf);
      this.sinks = new ArrayBuffer();
      this.sources = new ArrayBuffer();
      this.registry = new MetricRegistry();
      this.running = false;
      this.metricsServlet = scala.None..MODULE$;
      this.prometheusServlet = scala.None..MODULE$;
      this.metricsConfig.initialize();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
