package org.apache.spark.deploy.master;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcEnv$;
import org.apache.spark.util.SparkUncaughtExceptionHandler;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple3;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

public final class Master$ implements Logging {
   public static final Master$ MODULE$ = new Master$();
   private static final String SYSTEM_NAME;
   private static final String ENDPOINT_NAME;
   private static final DateTimeFormatter org$apache$spark$deploy$master$Master$$DATE_TIME_FORMATTER;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      SYSTEM_NAME = "sparkMaster";
      ENDPOINT_NAME = "Master";
      org$apache$spark$deploy$master$Master$$DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.US).withZone(ZoneId.systemDefault());
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

   public String SYSTEM_NAME() {
      return SYSTEM_NAME;
   }

   public String ENDPOINT_NAME() {
      return ENDPOINT_NAME;
   }

   public DateTimeFormatter org$apache$spark$deploy$master$Master$$DATE_TIME_FORMATTER() {
      return org$apache$spark$deploy$master$Master$$DATE_TIME_FORMATTER;
   }

   public void main(final String[] argStrings) {
      Thread.setDefaultUncaughtExceptionHandler(new SparkUncaughtExceptionHandler(false));
      Utils$.MODULE$.resetStructuredLogging();
      Utils$.MODULE$.initDaemon(this.log());
      SparkConf conf = new SparkConf();
      MasterArguments args = new MasterArguments(argStrings, conf);
      Tuple3 var6 = this.startRpcEnvAndEndpoint(args.host(), args.port(), args.webUiPort(), conf);
      if (var6 != null) {
         RpcEnv rpcEnv = (RpcEnv)var6._1();
         rpcEnv.awaitTermination();
      } else {
         throw new MatchError(var6);
      }
   }

   public Tuple3 startRpcEnvAndEndpoint(final String host, final int port, final int webUiPort, final SparkConf conf) {
      SecurityManager securityMgr = new SecurityManager(conf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3());
      RpcEnv rpcEnv = RpcEnv$.MODULE$.create(this.SYSTEM_NAME(), host, port, conf, securityMgr, RpcEnv$.MODULE$.create$default$6());
      RpcEndpointRef masterEndpoint = rpcEnv.setupEndpoint(this.ENDPOINT_NAME(), new Master(rpcEnv, rpcEnv.address(), webUiPort, securityMgr, conf));
      MasterMessages.BoundPortsResponse portsResponse = (MasterMessages.BoundPortsResponse)masterEndpoint.askSync(MasterMessages.BoundPortsRequest$.MODULE$, .MODULE$.apply(MasterMessages.BoundPortsResponse.class));
      return new Tuple3(rpcEnv, BoxesRunTime.boxToInteger(portsResponse.webUIPort()), portsResponse.restPort());
   }

   private Master$() {
   }
}
