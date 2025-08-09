package org.apache.spark.deploy.worker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.spark.SSLOptions$;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.deploy.Command;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.launcher.WorkerCommandBuilder;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

public final class CommandUtils$ implements Logging {
   public static final CommandUtils$ MODULE$ = new CommandUtils$();
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

   public ProcessBuilder buildProcessBuilder(final Command command, final SecurityManager securityMgr, final int memory, final String sparkHome, final Function1 substituteArguments, final Seq classPaths, final scala.collection.Map env) {
      Command localCommand = this.buildLocalCommand(command, securityMgr, substituteArguments, classPaths, env);
      Seq commandSeq = this.buildCommandSeq(localCommand, memory, sparkHome);
      ProcessBuilder builder = new ProcessBuilder((String[])commandSeq.toArray(.MODULE$.apply(String.class)));
      Map environment = builder.environment();
      localCommand.environment().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$buildProcessBuilder$1(check$ifrefutable$1))).foreach((x$1) -> {
         if (x$1 != null) {
            String key = (String)x$1._1();
            String value = (String)x$1._2();
            return (String)environment.put(key, value);
         } else {
            throw new MatchError(x$1);
         }
      });
      return builder;
   }

   public Seq buildProcessBuilder$default$6() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public scala.collection.Map buildProcessBuilder$default$7() {
      return scala.sys.package..MODULE$.env();
   }

   private Seq buildCommandSeq(final Command command, final int memory, final String sparkHome) {
      List cmd = (new WorkerCommandBuilder(sparkHome, memory, command)).buildCommand();
      return ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(cmd).asScala().$plus$plus(new scala.collection.immutable..colon.colon(command.mainClass(), scala.collection.immutable.Nil..MODULE$))).$plus$plus(command.arguments())).toSeq();
   }

   private Command buildLocalCommand(final Command command, final SecurityManager securityMgr, final Function1 substituteArguments, final Seq classPath, final scala.collection.Map env) {
      String libraryPathName = Utils$.MODULE$.libraryPathEnvName();
      Seq libraryPathEntries = command.libraryPathEntries();
      Option cmdLibraryPath = command.environment().get(libraryPathName);
      scala.collection.Map var10000;
      if (libraryPathEntries.nonEmpty() && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(libraryPathName))) {
         Seq libraryPaths = (Seq)((IterableOps)libraryPathEntries.$plus$plus(cmdLibraryPath)).$plus$plus(env.get(libraryPathName));
         var10000 = (scala.collection.Map)command.environment().$plus$plus((IterableOnce)scala.collection.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(libraryPathName), libraryPaths.mkString(File.pathSeparator))}))));
      } else {
         var10000 = command.environment();
      }

      scala.collection.Map newEnvironment = var10000;
      if (securityMgr.isAuthenticationEnabled()) {
         newEnvironment = (scala.collection.Map)newEnvironment.$plus$plus((IterableOnce)scala.collection.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(SecurityManager$.MODULE$.ENV_AUTH_SECRET()), securityMgr.getSecretKey())}))));
      }

      newEnvironment = (scala.collection.Map)newEnvironment.$plus$plus(securityMgr.getEnvironmentForSslRpcPasswords());
      return new Command(command.mainClass(), (Seq)command.arguments().map(substituteArguments), newEnvironment, (Seq)command.classPathEntries().$plus$plus(classPath), (Seq)scala.package..MODULE$.Seq().empty(), (Seq)command.javaOpts().filterNot((opts) -> BoxesRunTime.boxToBoolean($anonfun$buildLocalCommand$1(opts))));
   }

   private Seq buildLocalCommand$default$4() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   public void redirectStream(final InputStream in, final File file) {
      FileOutputStream out = new FileOutputStream(file, true);
      (new Thread(file, in, out) {
         private final InputStream in$1;
         private final FileOutputStream out$1;
         private final File file$1;

         public void run() {
            try {
               Utils$.MODULE$.copyStream(this.in$1, this.out$1, true, Utils$.MODULE$.copyStream$default$4());
            } catch (IOException var2) {
               CommandUtils$.MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> CommandUtils$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Redirection to ", " closed: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, this.file$1)}))).$plus(CommandUtils$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var2.getMessage())}))))));
            }

         }

         public {
            this.in$1 = in$1;
            this.out$1 = out$1;
            this.file$1 = file$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      }).start();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildProcessBuilder$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildLocalCommand$2(final String opts$1, final String field) {
      return opts$1.startsWith("-D" + field);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$buildLocalCommand$1(final String opts) {
      return opts.startsWith("-D" + SecurityManager$.MODULE$.SPARK_AUTH_SECRET_CONF()) || SSLOptions$.MODULE$.SPARK_RPC_SSL_PASSWORD_FIELDS().exists((field) -> BoxesRunTime.boxToBoolean($anonfun$buildLocalCommand$2(opts, field)));
   }

   private CommandUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
