package org.apache.spark.repl;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.util.Utils.;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.tools.nsc.GenericRunnerSettings;

public final class Main$ implements Logging {
   public static final Main$ MODULE$ = new Main$();
   private static final SparkConf conf;
   private static final String rootDir;
   private static final File outputDir;
   private static SparkContext sparkContext;
   private static SparkSession sparkSession;
   private static SparkILoop interp;
   private static boolean hasErrors;
   private static boolean isShellSession;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      MODULE$.initializeLogIfNecessary(true);
      Signaling$.MODULE$.cancelOnInterrupt();
      conf = new SparkConf();
      rootDir = (String)MODULE$.conf().getOption("spark.repl.classdir").getOrElse(() -> .MODULE$.getLocalDir(MODULE$.conf()));
      outputDir = .MODULE$.createTempDir(MODULE$.rootDir(), "repl");
      hasErrors = false;
      isShellSession = false;
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

   public SparkConf conf() {
      return conf;
   }

   public String rootDir() {
      return rootDir;
   }

   public File outputDir() {
      return outputDir;
   }

   public SparkContext sparkContext() {
      return sparkContext;
   }

   public void sparkContext_$eq(final SparkContext x$1) {
      sparkContext = x$1;
   }

   public SparkSession sparkSession() {
      return sparkSession;
   }

   public void sparkSession_$eq(final SparkSession x$1) {
      sparkSession = x$1;
   }

   public SparkILoop interp() {
      return interp;
   }

   public void interp_$eq(final SparkILoop x$1) {
      interp = x$1;
   }

   private boolean hasErrors() {
      return hasErrors;
   }

   private void hasErrors_$eq(final boolean x$1) {
      hasErrors = x$1;
   }

   private boolean isShellSession() {
      return isShellSession;
   }

   private void isShellSession_$eq(final boolean x$1) {
      isShellSession = x$1;
   }

   private void scalaOptionError(final String msg) {
      this.hasErrors_$eq(true);
      scala.Console..MODULE$.err().println(msg);
   }

   public void main(final String[] args) {
      this.isShellSession_$eq(true);
      this.doMain(args, new SparkILoop());
   }

   public void doMain(final String[] args, final SparkILoop _interp) {
      this.interp_$eq(_interp);
      String jars = ((IterableOnceOps).MODULE$.getLocalUserJarsForShell(this.conf()).map((x) -> x.startsWith("file:") ? (new File(new URI(x))).getPath() : x)).mkString(File.pathSeparator);
      List interpArguments = (List)(new scala.collection.immutable..colon.colon("-Yrepl-class-based", new scala.collection.immutable..colon.colon("-Yrepl-outdir", new scala.collection.immutable..colon.colon(String.valueOf(this.outputDir().getAbsolutePath()), new scala.collection.immutable..colon.colon("-classpath", new scala.collection.immutable..colon.colon(jars, scala.collection.immutable.Nil..MODULE$)))))).$plus$plus(scala.Predef..MODULE$.wrapRefArray((Object[])args).toList());
      GenericRunnerSettings settings = new GenericRunnerSettings((msg) -> {
         $anonfun$doMain$2(msg);
         return BoxedUnit.UNIT;
      });
      settings.processArguments(interpArguments, true);
      if (!this.hasErrors()) {
         this.interp().run(settings);
         scala.Option..MODULE$.apply(this.sparkContext()).foreach((x$4) -> {
            $anonfun$doMain$3(x$4);
            return BoxedUnit.UNIT;
         });
      }
   }

   public SparkSession createSparkSession() {
      try {
         String execUri = System.getenv("SPARK_EXECUTOR_URI");
         this.conf().setIfMissing("spark.app.name", "Spark shell");
         this.conf().set("spark.repl.class.outputDir", this.outputDir().getAbsolutePath());
         this.conf().set(org.apache.spark.sql.internal.SQLConf..MODULE$.ARTIFACTS_SESSION_ISOLATION_ENABLED(), BoxesRunTime.boxToBoolean(false));
         if (execUri != null) {
            this.conf().set("spark.executor.uri", execUri);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (System.getenv("SPARK_HOME") != null) {
            this.conf().setSparkHome(System.getenv("SPARK_HOME"));
         } else {
            BoxedUnit var9 = BoxedUnit.UNIT;
         }

         label44: {
            SparkSession.Builder builder;
            label43: {
               builder = org.apache.spark.sql.SparkSession..MODULE$.builder().config(this.conf());
               String var10 = this.conf().get(org.apache.spark.sql.internal.StaticSQLConf..MODULE$.CATALOG_IMPLEMENTATION().key(), "hive");
               String var4 = "hive";
               if (var10 == null) {
                  if (var4 != null) {
                     break label43;
                  }
               } else if (!var10.equals(var4)) {
                  break label43;
               }

               if (org.apache.spark.sql.classic.SparkSession..MODULE$.hiveClassesArePresent()) {
                  this.sparkSession_$eq(builder.enableHiveSupport().getOrCreate());
                  this.logInfo((Function0)(() -> "Created Spark session with Hive support"));
               } else {
                  builder.config(org.apache.spark.sql.internal.StaticSQLConf..MODULE$.CATALOG_IMPLEMENTATION().key(), "in-memory");
                  this.sparkSession_$eq(builder.getOrCreate());
                  this.logInfo((Function0)(() -> "Created Spark session"));
               }
               break label44;
            }

            this.sparkSession_$eq(builder.getOrCreate());
            this.logInfo((Function0)(() -> "Created Spark session"));
         }

         this.sparkContext_$eq(this.sparkSession().sparkContext());
         return this.sparkSession();
      } catch (Throwable var8) {
         if (var8 instanceof Exception var7) {
            if (this.isShellSession()) {
               this.logError((Function0)(() -> "Failed to initialize Spark session."), var7);
               throw scala.sys.package..MODULE$.exit(1);
            }
         }

         throw var8;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$doMain$2(final String msg) {
      MODULE$.scalaOptionError(msg);
   }

   // $FF: synthetic method
   public static final void $anonfun$doMain$3(final SparkContext x$4) {
      x$4.stop();
   }

   private Main$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
