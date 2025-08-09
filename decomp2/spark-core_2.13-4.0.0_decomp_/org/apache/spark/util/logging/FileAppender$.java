package org.apache.spark.util.logging;

import java.io.File;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.IntParam$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public final class FileAppender$ implements Logging {
   public static final FileAppender$ MODULE$ = new FileAppender$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public int $lessinit$greater$default$3() {
      return 8192;
   }

   public boolean $lessinit$greater$default$4() {
      return false;
   }

   public FileAppender apply(final InputStream inputStream, final File file, final SparkConf conf, final boolean closeStreams) {
      String rollingStrategy = (String)conf.get(package$.MODULE$.EXECUTOR_LOGS_ROLLING_STRATEGY());
      String rollingSizeBytes = (String)conf.get(package$.MODULE$.EXECUTOR_LOGS_ROLLING_MAX_SIZE());
      String rollingInterval = (String)conf.get(package$.MODULE$.EXECUTOR_LOGS_ROLLING_TIME_INTERVAL());
      switch (rollingStrategy == null ? 0 : rollingStrategy.hashCode()) {
         case 0:
            if ("".equals(rollingStrategy)) {
               int x$4 = this.$lessinit$greater$default$3();
               return new FileAppender(inputStream, file, x$4, closeStreams);
            }
            break;
         case 3530753:
            if ("size".equals(rollingStrategy)) {
               return this.createSizeBasedAppender$1(rollingSizeBytes, file, inputStream, conf, closeStreams);
            }
            break;
         case 3560141:
            if ("time".equals(rollingStrategy)) {
               return this.createTimeBasedAppender$1(rollingInterval, file, inputStream, conf, closeStreams);
            }
      }

      this.logWarning(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Illegal strategy [", "] for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STRATEGY..MODULE$, rollingStrategy)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"rolling executor logs, rolling logs not enabled"})))).log(scala.collection.immutable.Nil..MODULE$))));
      int x$8 = this.$lessinit$greater$default$3();
      return new FileAppender(inputStream, file, x$8, closeStreams);
   }

   public boolean apply$default$4() {
      return false;
   }

   private final FileAppender createTimeBasedAppender$1(final String rollingInterval$1, final File file$1, final InputStream inputStream$1, final SparkConf conf$1, final boolean closeStreams$1) {
      Object var10000;
      if ("daily".equals(rollingInterval$1)) {
         this.logInfo(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rolling executor logs enabled for ", " with daily rolling"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file$1)})))));
         var10000 = new Some(new Tuple2(BoxesRunTime.boxToLong(86400000L), "--yyyy-MM-dd"));
      } else if ("hourly".equals(rollingInterval$1)) {
         this.logInfo(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rolling executor logs enabled for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file$1)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with hourly rolling"})))).log(scala.collection.immutable.Nil..MODULE$))));
         var10000 = new Some(new Tuple2(BoxesRunTime.boxToLong(3600000L), "--yyyy-MM-dd--HH"));
      } else if ("minutely".equals(rollingInterval$1)) {
         this.logInfo(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rolling executor logs enabled for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file$1)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with rolling every minute"})))).log(scala.collection.immutable.Nil..MODULE$))));
         var10000 = new Some(new Tuple2(BoxesRunTime.boxToLong(60000L), "--yyyy-MM-dd--HH-mm"));
      } else {
         label23: {
            if (rollingInterval$1 != null) {
               Option var9 = IntParam$.MODULE$.unapply(rollingInterval$1);
               if (!var9.isEmpty()) {
                  int seconds = BoxesRunTime.unboxToInt(var9.get());
                  this.logInfo(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rolling executor logs enabled for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file$1)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with rolling ", " seconds"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToInteger(seconds))}))))));
                  var10000 = new Some(new Tuple2(BoxesRunTime.boxToLong((long)seconds * 1000L), "--yyyy-MM-dd--HH-mm-ss"));
                  break label23;
               }
            }

            this.logWarning(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Illegal interval for rolling executor logs ["})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "], "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, rollingInterval$1)})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"rolling logs not enabled"})))).log(scala.collection.immutable.Nil..MODULE$))));
            var10000 = scala.None..MODULE$;
         }
      }

      Option validatedParams = (Option)var10000;
      return (FileAppender)validatedParams.map((x0$1) -> {
         if (x0$1 != null) {
            long interval = x0$1._1$mcJ$sp();
            String pattern = (String)x0$1._2();
            TimeBasedRollingPolicy x$3 = new TimeBasedRollingPolicy(interval, pattern, TimeBasedRollingPolicy$.MODULE$.$lessinit$greater$default$3());
            int x$6 = RollingFileAppender$.MODULE$.$lessinit$greater$default$5();
            return new RollingFileAppender(inputStream$1, file$1, x$3, conf$1, x$6, closeStreams$1);
         } else {
            throw new MatchError(x0$1);
         }
      }).getOrElse(() -> {
         int x$10 = MODULE$.$lessinit$greater$default$3();
         return new FileAppender(inputStream$1, file$1, x$10, closeStreams$1);
      });
   }

   private final FileAppender createSizeBasedAppender$1(final String rollingSizeBytes$1, final File file$1, final InputStream inputStream$1, final SparkConf conf$1, final boolean closeStreams$1) {
      if (rollingSizeBytes$1 != null) {
         Option var8 = IntParam$.MODULE$.unapply(rollingSizeBytes$1);
         if (!var8.isEmpty()) {
            int bytes = BoxesRunTime.unboxToInt(var8.get());
            this.logInfo(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rolling executor logs enabled for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file$1)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with rolling every ", " bytes"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToInteger(bytes))}))))));
            SizeBasedRollingPolicy x$3 = new SizeBasedRollingPolicy((long)bytes, SizeBasedRollingPolicy$.MODULE$.$lessinit$greater$default$2());
            int x$6 = RollingFileAppender$.MODULE$.$lessinit$greater$default$5();
            return new RollingFileAppender(inputStream$1, file$1, x$3, conf$1, x$6, closeStreams$1);
         }
      }

      this.logWarning(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Illegal size [", "] "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, rollingSizeBytes$1)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for rolling executor logs, rolling logs not enabled"})))).log(scala.collection.immutable.Nil..MODULE$))));
      int x$10 = this.$lessinit$greater$default$3();
      return new FileAppender(inputStream$1, file$1, x$10, closeStreams$1);
   }

   private FileAppender$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
