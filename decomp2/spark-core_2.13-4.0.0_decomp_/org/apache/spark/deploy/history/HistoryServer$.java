package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.History$;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.util.ShutdownHookManager$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class HistoryServer$ implements Logging {
   public static final HistoryServer$ MODULE$ = new HistoryServer$();
   private static SparkConf conf;
   private static final String UI_PATH_PREFIX;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

   static {
      Logging.$init$(MODULE$);
      UI_PATH_PREFIX = "/history";
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

   private SparkConf conf$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            conf = new SparkConf();
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return conf;
   }

   private SparkConf conf() {
      return !bitmap$0 ? this.conf$lzycompute() : conf;
   }

   public String UI_PATH_PREFIX() {
      return UI_PATH_PREFIX;
   }

   public void main(final String[] argStrings) {
      Utils$.MODULE$.resetStructuredLogging();
      Utils$.MODULE$.initDaemon(this.log());
      new HistoryServerArguments(this.conf(), argStrings);
      this.initSecurity();
      SecurityManager securityManager = this.createSecurityManager(this.conf());
      String providerName = (String)this.conf().get(History$.MODULE$.PROVIDER());
      ApplicationHistoryProvider provider = (ApplicationHistoryProvider)Utils$.MODULE$.classForName(providerName, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3()).getConstructor(SparkConf.class).newInstance(this.conf());
      int port = BoxesRunTime.unboxToInt(this.conf().get(History$.MODULE$.HISTORY_SERVER_UI_PORT()));
      HistoryServer server = new HistoryServer(this.conf(), provider, securityManager, port);
      server.bind();
      provider.start();
      ShutdownHookManager$.MODULE$.addShutdownHook((JFunction0.mcV.sp)() -> server.stop());

      while(true) {
         Thread.sleep(2147483647L);
      }
   }

   public SecurityManager createSecurityManager(final SparkConf config) {
      if (config.getBoolean(SecurityManager$.MODULE$.SPARK_AUTH_CONF(), false)) {
         this.logDebug((Function0)(() -> "Clearing " + SecurityManager$.MODULE$.SPARK_AUTH_CONF()));
         config.set(SecurityManager$.MODULE$.SPARK_AUTH_CONF(), "false");
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (BoxesRunTime.unboxToBoolean(config.get(UI$.MODULE$.ACLS_ENABLE()))) {
         this.logInfo(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is configured, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KEY..MODULE$, UI$.MODULE$.ACLS_ENABLE().key())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"clearing it and only using ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KEY2..MODULE$, History$.MODULE$.HISTORY_SERVER_UI_ACLS_ENABLE().key())}))))));
         config.set((ConfigEntry)UI$.MODULE$.ACLS_ENABLE(), (Object)BoxesRunTime.boxToBoolean(false));
      } else {
         BoxedUnit var2 = BoxedUnit.UNIT;
      }

      return new SecurityManager(config, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3());
   }

   public void initSecurity() {
      if (BoxesRunTime.unboxToBoolean(this.conf().get(History$.MODULE$.KERBEROS_ENABLED()))) {
         String principalName = (String)((Option)this.conf().get((ConfigEntry)History$.MODULE$.KERBEROS_PRINCIPAL())).getOrElse(() -> {
            throw new NoSuchElementException(History$.MODULE$.KERBEROS_PRINCIPAL().key());
         });
         String keytabFilename = (String)((Option)this.conf().get((ConfigEntry)History$.MODULE$.KERBEROS_KEYTAB())).getOrElse(() -> {
            throw new NoSuchElementException(History$.MODULE$.KERBEROS_KEYTAB().key());
         });
         SparkHadoopUtil$.MODULE$.get().loginUserFromKeytab(principalName, keytabFilename);
      }
   }

   public String getAttemptURI(final String appId, final Option attemptId) {
      String attemptSuffix = (String)attemptId.map((id) -> "/" + id).getOrElse(() -> "");
      return this.UI_PATH_PREFIX() + "/" + appId + attemptSuffix;
   }

   private HistoryServer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
