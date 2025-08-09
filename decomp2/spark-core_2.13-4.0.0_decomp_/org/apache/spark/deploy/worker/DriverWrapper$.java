package org.apache.spark.deploy.worker;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcEnv$;
import org.apache.spark.util.ChildFirstURLClassLoader;
import org.apache.spark.util.DependencyUtils$;
import org.apache.spark.util.IvyProperties;
import org.apache.spark.util.MutableURLClassLoader;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;

public final class DriverWrapper$ implements Logging {
   public static final DriverWrapper$ MODULE$ = new DriverWrapper$();
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
      List var3 = .MODULE$.wrapRefArray((Object[])args).toList();
      if (var3 instanceof scala.collection.immutable..colon.colon var4) {
         String workerUrl = (String)var4.head();
         List var6 = var4.next$access$1();
         if (var6 instanceof scala.collection.immutable..colon.colon var7) {
            String userJar = (String)var7.head();
            List var9 = var7.next$access$1();
            if (var9 instanceof scala.collection.immutable..colon.colon var10) {
               String mainClass = (String)var10.head();
               List extraArgs = var10.next$access$1();
               SparkConf conf = new SparkConf();
               String host = Utils$.MODULE$.localHostName();
               int port = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString((String)scala.sys.package..MODULE$.props().getOrElse(package$.MODULE$.DRIVER_PORT().key(), () -> "0")));
               RpcEnv rpcEnv = RpcEnv$.MODULE$.create("Driver", host, port, conf, new SecurityManager(conf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3()), RpcEnv$.MODULE$.create$default$6());
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver address: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ADDRESS..MODULE$, rpcEnv.address())})))));
               rpcEnv.setupEndpoint("workerWatcher", new WorkerWatcher(rpcEnv, workerUrl, WorkerWatcher$.MODULE$.$lessinit$greater$default$3(), WorkerWatcher$.MODULE$.$lessinit$greater$default$4()));
               ClassLoader currentLoader = Thread.currentThread().getContextClassLoader();
               URL userJarUrl = (new File(userJar)).toURI().toURL();
               MutableURLClassLoader loader = (MutableURLClassLoader)(scala.collection.StringOps..MODULE$.toBoolean$extension(.MODULE$.augmentString((String)scala.sys.package..MODULE$.props().getOrElse(package$.MODULE$.DRIVER_USER_CLASS_PATH_FIRST().key(), () -> "false"))) ? new ChildFirstURLClassLoader((URL[])((Object[])(new URL[]{userJarUrl})), currentLoader) : new MutableURLClassLoader((URL[])((Object[])(new URL[]{userJarUrl})), currentLoader));
               Thread.currentThread().setContextClassLoader(loader);
               this.setupDependencies(loader, userJar);
               Class clazz = Utils$.MODULE$.classForName(mainClass, Utils$.MODULE$.classForName$default$2(), Utils$.MODULE$.classForName$default$3());
               Method mainMethod = clazz.getMethod("main", String[].class);
               mainMethod.invoke((Object)null, extraArgs.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
               rpcEnv.shutdown();
               BoxedUnit var22 = BoxedUnit.UNIT;
               return;
            }
         }
      }

      System.err.println("Usage: DriverWrapper <workerUrl> <userJar> <driverMainClass> [options]");
      System.exit(-1);
      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   private void setupDependencies(final MutableURLClassLoader loader, final String userJar) {
      SparkConf sparkConf = new SparkConf();
      Configuration hadoopConf = SparkHadoopUtil$.MODULE$.newConfiguration(sparkConf);
      IvyProperties ivyProperties = DependencyUtils$.MODULE$.getIvyProperties();
      Seq resolvedMavenCoordinates = DependencyUtils$.MODULE$.resolveMavenDependencies(true, ivyProperties.packagesExclusions(), ivyProperties.packages(), ivyProperties.repositories(), ivyProperties.ivyRepoPath(), scala.Option..MODULE$.apply(ivyProperties.ivySettingsPath()));
      String jarsProp = (String)scala.sys.package..MODULE$.props().get(package$.MODULE$.JARS().key()).orNull(scala..less.colon.less..MODULE$.refl());
      String jars = resolvedMavenCoordinates.nonEmpty() ? DependencyUtils$.MODULE$.mergeFileLists(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{jarsProp, DependencyUtils$.MODULE$.mergeFileLists(resolvedMavenCoordinates)}))) : jarsProp;
      String localJars = DependencyUtils$.MODULE$.resolveAndDownloadJars(jars, userJar, sparkConf, hadoopConf);
      DependencyUtils$.MODULE$.addJarsToClassPath(localJars, loader);
   }

   private DriverWrapper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
