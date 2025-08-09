package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.security.HadoopDelegationTokenManager;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.util.SparkExitCode.;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

public final class ApplicationMaster$ implements Logging {
   public static final ApplicationMaster$ MODULE$ = new ApplicationMaster$();
   private static final int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SUCCESS;
   private static final int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_UNCAUGHT_EXCEPTION;
   private static final int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_MAX_EXECUTOR_FAILURES;
   private static final int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_REPORTER_FAILURE;
   private static final int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SC_NOT_INITED;
   private static final int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EXCEPTION_USER_CLASS;
   private static final int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EARLY;
   private static final int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_DISCONNECTED;
   private static ApplicationMaster org$apache$spark$deploy$yarn$ApplicationMaster$$master;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SUCCESS = .MODULE$.EXIT_SUCCESS();
      org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_UNCAUGHT_EXCEPTION = 10;
      org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_MAX_EXECUTOR_FAILURES = .MODULE$.EXCEED_MAX_EXECUTOR_FAILURES();
      org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_REPORTER_FAILURE = 12;
      org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SC_NOT_INITED = 13;
      org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EXCEPTION_USER_CLASS = 15;
      org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EARLY = 16;
      org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_DISCONNECTED = 17;
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

   public int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SUCCESS() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SUCCESS;
   }

   public int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_UNCAUGHT_EXCEPTION() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_UNCAUGHT_EXCEPTION;
   }

   public int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_MAX_EXECUTOR_FAILURES() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_MAX_EXECUTOR_FAILURES;
   }

   public int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_REPORTER_FAILURE() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_REPORTER_FAILURE;
   }

   public int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SC_NOT_INITED() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SC_NOT_INITED;
   }

   public int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EXCEPTION_USER_CLASS() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EXCEPTION_USER_CLASS;
   }

   public int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EARLY() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EARLY;
   }

   public int org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_DISCONNECTED() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_DISCONNECTED;
   }

   public ApplicationMaster org$apache$spark$deploy$yarn$ApplicationMaster$$master() {
      return org$apache$spark$deploy$yarn$ApplicationMaster$$master;
   }

   private void master_$eq(final ApplicationMaster x$1) {
      org$apache$spark$deploy$yarn$ApplicationMaster$$master = x$1;
   }

   public void main(final String[] args) {
      org.apache.spark.util.SignalUtils..MODULE$.registerLogger(this.log());
      ApplicationMasterArguments amArgs = new ApplicationMasterArguments(args);
      SparkConf sparkConf = new SparkConf();
      if (amArgs.propertiesFile() != null) {
         org.apache.spark.util.Utils..MODULE$.getPropertiesFromFile(amArgs.propertiesFile()).foreach((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               String v = (String)x0$1._2();
               return sparkConf.set(k, v);
            } else {
               throw new MatchError(x0$1);
            }
         });
      }

      UserGroupInformation var10000;
      label26: {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])sparkConf.getAll()), (x0$2) -> {
            $anonfun$main$2(x0$2);
            return BoxedUnit.UNIT;
         });
         YarnConfiguration yarnConf = new YarnConfiguration(org.apache.spark.deploy.SparkHadoopUtil..MODULE$.newConfiguration(sparkConf));
         this.master_$eq(new ApplicationMaster(amArgs, sparkConf, yarnConf));
         Option var7 = (Option)sparkConf.get(org.apache.spark.internal.config.package..MODULE$.PRINCIPAL());
         if (var7 instanceof Some var8) {
            String principal = (String)var8.value();
            if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$master().org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode()) {
               Credentials originalCreds = UserGroupInformation.getCurrentUser().getCredentials();
               org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().loginUserFromKeytab(principal, (String)((Option)sparkConf.get(org.apache.spark.internal.config.package..MODULE$.KEYTAB())).orNull(scala..less.colon.less..MODULE$.refl()));
               UserGroupInformation newUGI = UserGroupInformation.getCurrentUser();
               if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$master().org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId() != null && this.org$apache$spark$deploy$yarn$ApplicationMaster$$master().org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId().getAttemptId() <= 1) {
                  BoxedUnit var12 = BoxedUnit.UNIT;
               } else {
                  org.apache.spark.util.Utils..MODULE$.withContextClassLoader(this.org$apache$spark$deploy$yarn$ApplicationMaster$$master().org$apache$spark$deploy$yarn$ApplicationMaster$$userClassLoader(), (JFunction0.mcV.sp)() -> {
                     HadoopDelegationTokenManager credentialManager = new HadoopDelegationTokenManager(sparkConf, yarnConf, (RpcEndpointRef)null);
                     credentialManager.obtainDelegationTokens(originalCreds);
                  });
               }

               newUGI.addCredentials(originalCreds);
               var10000 = newUGI;
               break label26;
            }
         }

         var10000 = org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().createSparkUser();
      }

      UserGroupInformation ugi = var10000;
      ugi.doAs(new PrivilegedExceptionAction() {
         public void run() {
            System.exit(ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$master().run());
         }
      });
   }

   public void sparkContextInitialized(final SparkContext sc) {
      this.org$apache$spark$deploy$yarn$ApplicationMaster$$master().org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextInitialized(sc);
   }

   public ApplicationAttemptId getAttemptId() {
      return this.org$apache$spark$deploy$yarn$ApplicationMaster$$master().org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId();
   }

   public String getHistoryServerAddress(final SparkConf sparkConf, final YarnConfiguration yarnConf, final String appId, final String attemptId) {
      return (String)((Option)sparkConf.get(package$.MODULE$.HISTORY_SERVER_ADDRESS())).map((text) -> org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().substituteHadoopVariables(text, yarnConf)).map((address) -> address + org.apache.spark.deploy.history.HistoryServer..MODULE$.UI_PATH_PREFIX() + "/" + appId + "/" + attemptId).getOrElse(() -> "");
   }

   // $FF: synthetic method
   public static final void $anonfun$main$2(final Tuple2 x0$2) {
      if (x0$2 != null) {
         String k = (String)x0$2._1();
         String v = (String)x0$2._2();
         scala.sys.package..MODULE$.props().update(k, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   private ApplicationMaster$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
