package org.apache.spark.executor;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkEnv;
import org.apache.spark.executor.CoarseGrainedExecutorBackend.;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.rpc.RpcEnv;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function4;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple4;

public final class YarnCoarseGrainedExecutorBackend$ implements Logging {
   public static final YarnCoarseGrainedExecutorBackend$ MODULE$ = new YarnCoarseGrainedExecutorBackend$();
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

   public void main(final String[] args) {
      Function4 createFn = (x0$1, x1$1, x2$1, x3$1) -> {
         Tuple4 var5 = new Tuple4(x0$1, x1$1, x2$1, x3$1);
         if (var5 != null) {
            RpcEnv rpcEnv = (RpcEnv)var5._1();
            CoarseGrainedExecutorBackend.Arguments arguments = (CoarseGrainedExecutorBackend.Arguments)var5._2();
            SparkEnv env = (SparkEnv)var5._3();
            ResourceProfile resourceProfile = (ResourceProfile)var5._4();
            return new YarnCoarseGrainedExecutorBackend(rpcEnv, arguments.driverUrl(), arguments.executorId(), arguments.bindAddress(), arguments.hostname(), arguments.cores(), env, arguments.resourcesFileOpt(), resourceProfile);
         } else {
            throw new MatchError(var5);
         }
      };
      CoarseGrainedExecutorBackend.Arguments backendArgs = .MODULE$.parseArguments(args, scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.getClass().getCanonicalName()), "$"));
      .MODULE$.run(backendArgs, createFn);
      System.exit(0);
   }

   private YarnCoarseGrainedExecutorBackend$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
