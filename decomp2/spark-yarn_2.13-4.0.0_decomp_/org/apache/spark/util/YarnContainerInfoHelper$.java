package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.spark.deploy.yarn.YarnSparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;

public final class YarnContainerInfoHelper$ implements Logging {
   public static final YarnContainerInfoHelper$ MODULE$ = new YarnContainerInfoHelper$();
   private static final Seq DRIVER_LOG_FILE_NAMES;
   private static final int DRIVER_LOG_START_OFFSET;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      DRIVER_LOG_FILE_NAMES = new .colon.colon("stdout", new .colon.colon("stderr", scala.collection.immutable.Nil..MODULE$));
      DRIVER_LOG_START_OFFSET = -4096;
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

   public scala.collection.immutable.Map getLogUrlsFromBaseUrl(final String baseUrl) {
      return ((IterableOnceOps)DRIVER_LOG_FILE_NAMES.map((fname) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(fname), baseUrl + "/" + fname + "?start=" + DRIVER_LOG_START_OFFSET))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Option getLogUrls(final Configuration conf, final Option container) {
      Object var10000;
      try {
         YarnConfiguration yarnConf = new YarnConfiguration(conf);
         ContainerId containerId = this.getContainerId(container);
         String user = org.apache.spark.util.Utils..MODULE$.getCurrentUserName();
         String httpScheme = this.getYarnHttpScheme(yarnConf);
         String httpAddress = this.getNodeManagerHttpAddress(container);
         String baseUrl = httpScheme + httpAddress + "/node/containerlogs/" + containerId + "/" + user;
         this.logDebug((Function0)(() -> "Base URL for logs: " + baseUrl));
         var10000 = new Some(this.getLogUrlsFromBaseUrl(baseUrl));
      } catch (Exception var10) {
         this.logInfo((Function0)(() -> "Error while building executor logs - executor logs will not be available"), var10);
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public Option getAttributes(final Configuration conf, final Option container) {
      Object var10000;
      try {
         YarnConfiguration yarnConf = new YarnConfiguration(conf);
         var10000 = new Some(scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("HTTP_SCHEME"), this.getYarnHttpScheme(yarnConf)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("NM_HOST"), this.getNodeManagerHost(container)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("NM_PORT"), this.getNodeManagerPort(container)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("NM_HTTP_PORT"), this.getNodeManagerHttpPort(container)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("NM_HTTP_ADDRESS"), this.getNodeManagerHttpAddress(container)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("CLUSTER_ID"), this.getClusterId(yarnConf).getOrElse(() -> "")), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("CONTAINER_ID"), this.convertToString(this.getContainerId(container))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("USER"), org.apache.spark.util.Utils..MODULE$.getCurrentUserName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("LOG_FILES"), "stderr,stdout")}))));
      } catch (Exception var5) {
         this.logInfo((Function0)(() -> "Error while retrieving executor attributes - executor logs will not be replaced with custom log pattern"), var5);
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public String convertToString(final ContainerId containerId) {
      return containerId != null ? containerId.toString() : null;
   }

   public ContainerId getContainerId(final Option container) {
      if (container instanceof Some var4) {
         Container c = (Container)var4.value();
         return c.getId();
      } else if (scala.None..MODULE$.equals(container)) {
         return YarnSparkHadoopUtil$.MODULE$.getContainerId();
      } else {
         throw new MatchError(container);
      }
   }

   public Option getClusterId(final YarnConfiguration yarnConf) {
      Object var10000;
      try {
         var10000 = new Some(YarnConfiguration.getClusterId(yarnConf));
      } catch (HadoopIllegalArgumentException var2) {
         var10000 = scala.None..MODULE$;
      }

      return (Option)var10000;
   }

   public String getYarnHttpScheme(final YarnConfiguration yarnConf) {
      String yarnHttpPolicy = yarnConf.get("yarn.http.policy", YarnConfiguration.YARN_HTTP_POLICY_DEFAULT);
      String var3 = "HTTPS_ONLY";
      if (yarnHttpPolicy == null) {
         if (var3 == null) {
            return "https://";
         }
      } else if (yarnHttpPolicy.equals(var3)) {
         return "https://";
      }

      return "http://";
   }

   public String getNodeManagerHttpAddress(final Option container) {
      if (container instanceof Some var4) {
         Container c = (Container)var4.value();
         return c.getNodeHttpAddress();
      } else if (scala.None..MODULE$.equals(container)) {
         String var10000 = this.getNodeManagerHost(scala.None..MODULE$);
         return var10000 + ":" + this.getNodeManagerHttpPort(scala.None..MODULE$);
      } else {
         throw new MatchError(container);
      }
   }

   public String getNodeManagerHost(final Option container) {
      if (container instanceof Some var4) {
         Container c = (Container)var4.value();
         return c.getNodeHttpAddress().split(":")[0];
      } else if (scala.None..MODULE$.equals(container)) {
         return System.getenv(Environment.NM_HOST.name());
      } else {
         throw new MatchError(container);
      }
   }

   public String getNodeManagerHttpPort(final Option container) {
      if (container instanceof Some var4) {
         Container c = (Container)var4.value();
         return c.getNodeHttpAddress().split(":")[1];
      } else if (scala.None..MODULE$.equals(container)) {
         return System.getenv(Environment.NM_HTTP_PORT.name());
      } else {
         throw new MatchError(container);
      }
   }

   public String getNodeManagerPort(final Option container) {
      if (container instanceof Some) {
         return "-1";
      } else if (scala.None..MODULE$.equals(container)) {
         return System.getenv(Environment.NM_PORT.name());
      } else {
         throw new MatchError(container);
      }
   }

   private YarnContainerInfoHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
