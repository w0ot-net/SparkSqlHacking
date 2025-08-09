package org.apache.spark.deploy.yarn;

import java.io.File;
import java.io.OutputStreamWriter;
import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.mapreduce.MRJobConfig;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import org.sparkproject.guava.base.Objects;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class Client$ implements Logging {
   public static final Client$ MODULE$ = new Client$();
   private static final String APP_JAR_NAME;
   private static final String SPARK_STAGING;
   private static final FsPermission STAGING_DIR_PERMISSION;
   private static final FsPermission APP_FILE_PERMISSION;
   private static final String ENV_DIST_CLASSPATH;
   private static final String LOCALIZED_CONF_DIR;
   private static final String LOCALIZED_HADOOP_CONF_DIR;
   private static final String LOCALIZED_CONF_ARCHIVE;
   private static final String SPARK_CONF_FILE;
   private static final String DIST_CACHE_CONF_FILE;
   private static final String LOCALIZED_PYTHON_DIR;
   private static final String LOCALIZED_LIB_DIR;
   private static final String SPARK_TESTING;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      APP_JAR_NAME = "__app__.jar";
      SPARK_STAGING = ".sparkStaging";
      STAGING_DIR_PERMISSION = FsPermission.createImmutable((short)Integer.parseInt("700", 8));
      APP_FILE_PERMISSION = FsPermission.createImmutable((short)Integer.parseInt("644", 8));
      ENV_DIST_CLASSPATH = "SPARK_DIST_CLASSPATH";
      LOCALIZED_CONF_DIR = "__spark_conf__";
      LOCALIZED_HADOOP_CONF_DIR = "__hadoop_conf__";
      LOCALIZED_CONF_ARCHIVE = MODULE$.LOCALIZED_CONF_DIR() + ".zip";
      SPARK_CONF_FILE = "__spark_conf__.properties";
      DIST_CACHE_CONF_FILE = "__spark_dist_cache__.properties";
      LOCALIZED_PYTHON_DIR = "__pyfiles__";
      LOCALIZED_LIB_DIR = "__spark_libs__";
      SPARK_TESTING = "SPARK_TESTING";
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

   public String APP_JAR_NAME() {
      return APP_JAR_NAME;
   }

   public String SPARK_STAGING() {
      return SPARK_STAGING;
   }

   public FsPermission STAGING_DIR_PERMISSION() {
      return STAGING_DIR_PERMISSION;
   }

   public FsPermission APP_FILE_PERMISSION() {
      return APP_FILE_PERMISSION;
   }

   public String ENV_DIST_CLASSPATH() {
      return ENV_DIST_CLASSPATH;
   }

   public String LOCALIZED_CONF_DIR() {
      return LOCALIZED_CONF_DIR;
   }

   public String LOCALIZED_HADOOP_CONF_DIR() {
      return LOCALIZED_HADOOP_CONF_DIR;
   }

   public String LOCALIZED_CONF_ARCHIVE() {
      return LOCALIZED_CONF_ARCHIVE;
   }

   public String SPARK_CONF_FILE() {
      return SPARK_CONF_FILE;
   }

   public String DIST_CACHE_CONF_FILE() {
      return DIST_CACHE_CONF_FILE;
   }

   public String LOCALIZED_PYTHON_DIR() {
      return LOCALIZED_PYTHON_DIR;
   }

   public String LOCALIZED_LIB_DIR() {
      return LOCALIZED_LIB_DIR;
   }

   public String SPARK_TESTING() {
      return SPARK_TESTING;
   }

   public String org$apache$spark$deploy$yarn$Client$$getAppStagingDir(final ApplicationId appId) {
      return this.buildPath(.MODULE$.wrapRefArray((Object[])(new String[]{this.SPARK_STAGING(), appId.toString()})));
   }

   public void populateHadoopClasspath(final Configuration conf, final HashMap env) {
      Seq classPathElementsToAdd = (Seq)this.getYarnAppClasspath(conf).$plus$plus(this.getMRAppClasspath(conf));
      classPathElementsToAdd.foreach((c) -> {
         $anonfun$populateHadoopClasspath$1(env, c);
         return BoxedUnit.UNIT;
      });
   }

   private Seq getYarnAppClasspath(final Configuration conf) {
      Option var3 = scala.Option..MODULE$.apply(conf.getStrings("yarn.application.classpath"));
      if (var3 instanceof Some var4) {
         String[] s = (String[])var4.value();
         return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(s).toImmutableArraySeq();
      } else if (scala.None..MODULE$.equals(var3)) {
         return this.getDefaultYarnApplicationClasspath();
      } else {
         throw new MatchError(var3);
      }
   }

   private Seq getMRAppClasspath(final Configuration conf) {
      Option var3 = scala.Option..MODULE$.apply(conf.getStrings("mapreduce.application.classpath"));
      if (var3 instanceof Some var4) {
         String[] s = (String[])var4.value();
         return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(s).toImmutableArraySeq();
      } else if (scala.None..MODULE$.equals(var3)) {
         return this.getDefaultMRApplicationClasspath();
      } else {
         throw new MatchError(var3);
      }
   }

   public Seq getDefaultYarnApplicationClasspath() {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(YarnConfiguration.DEFAULT_YARN_APPLICATION_CLASSPATH).toImmutableArraySeq();
   }

   public Seq getDefaultMRApplicationClasspath() {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(StringUtils.getStrings(MRJobConfig.DEFAULT_MAPREDUCE_APPLICATION_CLASSPATH)).toImmutableArraySeq();
   }

   public void populateClasspath(final ClientArguments args, final Configuration conf, final SparkConf sparkConf, final HashMap env, final Option extraClassPath) {
      Set var10000;
      label36: {
         extraClassPath.foreach((cp) -> {
            $anonfun$populateClasspath$1(sparkConf, env, cp);
            return BoxedUnit.UNIT;
         });
         if (extraClassPath instanceof Some var9) {
            String classPath = (String)var9.value();
            if (org.apache.spark.util.Utils..MODULE$.isTesting()) {
               var10000 = scala.Predef..MODULE$.wrapRefArray((Object[])classPath.split(File.pathSeparator)).toSet();
               break label36;
            }
         }

         var10000 = scala.Predef..MODULE$.Set().empty();
      }

      Set cpSet = var10000;
      this.addClasspathEntry(Environment.PWD.$$(), env);
      this.addClasspathEntry(Environment.PWD.$$() + "/" + this.LOCALIZED_CONF_DIR(), env);
      if (BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.USER_CLASS_PATH_FIRST()))) {
         Option mainJar = args != null ? this.getMainJarUri(scala.Option..MODULE$.apply(args.userJar())) : this.getMainJarUri((Option)sparkConf.get(package$.MODULE$.APP_JAR()));
         mainJar.foreach((x$23) -> {
            $anonfun$populateClasspath$2(sparkConf, conf, env, x$23);
            return BoxedUnit.UNIT;
         });
         Seq secondaryJars = args != null ? this.getSecondaryJarUris(scala.Option..MODULE$.apply(sparkConf.get(package$.MODULE$.JARS_TO_DISTRIBUTE()))) : this.getSecondaryJarUris((Option)sparkConf.get(package$.MODULE$.SECONDARY_JARS()));
         secondaryJars.foreach((x) -> {
            $anonfun$populateClasspath$3(sparkConf, conf, env, x);
            return BoxedUnit.UNIT;
         });
      }

      this.addClasspathEntry(this.buildPath(.MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), this.LOCALIZED_LIB_DIR(), "*"}))), env);
      if (((Option)sparkConf.get(package$.MODULE$.SPARK_ARCHIVE())).isEmpty()) {
         ((Option)sparkConf.get(package$.MODULE$.SPARK_JARS())).foreach((jars) -> {
            $anonfun$populateClasspath$4(sparkConf, env, jars);
            return BoxedUnit.UNIT;
         });
      }

      if (BoxesRunTime.unboxToBoolean(sparkConf.get(package$.MODULE$.POPULATE_HADOOP_CLASSPATH()))) {
         this.populateHadoopClasspath(conf, env);
      }

      scala.sys.package..MODULE$.env().get(this.ENV_DIST_CLASSPATH()).foreach((cp) -> {
         $anonfun$populateClasspath$7(cpSet, sparkConf, env, cp);
         return BoxedUnit.UNIT;
      });
      this.addClasspathEntry(this.buildPath(.MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), this.LOCALIZED_CONF_DIR(), this.LOCALIZED_HADOOP_CONF_DIR()}))), env);
   }

   public Option populateClasspath$default$5() {
      return scala.None..MODULE$;
   }

   public URI[] getUserClasspath(final SparkConf conf) {
      Option mainUri = this.getMainJarUri((Option)conf.get(package$.MODULE$.APP_JAR()));
      Seq secondaryUris = this.getSecondaryJarUris((Option)conf.get(package$.MODULE$.SECONDARY_JARS()));
      return (URI[])((IterableOnceOps)scala.Option..MODULE$.option2Iterable(mainUri).$plus$plus(secondaryUris)).toArray(scala.reflect.ClassTag..MODULE$.apply(URI.class));
   }

   public URL[] getUserClasspathUrls(final SparkConf conf, final boolean useClusterPath) {
      return (URL[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.getUserClasspath(conf)), (uri) -> {
         String inputPath = uri.getPath();
         String var7;
         if (org.apache.spark.util.Utils..MODULE$.isLocalUri(uri.toString()) && useClusterPath) {
            var7 = MODULE$.getClusterPath(conf, inputPath);
         } else {
            boolean var8;
            label25: {
               var10000 = scala.Predef..MODULE$;
               if (uri.getScheme() != null) {
                  label23: {
                     String var10001 = uri.getScheme();
                     String var5 = "file";
                     if (var10001 == null) {
                        if (var5 == null) {
                           break label23;
                        }
                     } else if (var10001.equals(var5)) {
                        break label23;
                     }

                     if (!org.apache.spark.util.Utils..MODULE$.isLocalUri(uri.toString())) {
                        var8 = false;
                        break label25;
                     }
                  }
               }

               var8 = true;
            }

            var10000.assert(var8, () -> "getUserClasspath should only return 'file' or 'local' URIs but found: " + uri);
            var7 = inputPath;
         }

         String replacedFilePath = var7;
         String envVarResolvedFilePath = YarnSparkHadoopUtil$.MODULE$.replaceEnvVars(replacedFilePath, scala.sys.package..MODULE$.env(), YarnSparkHadoopUtil$.MODULE$.replaceEnvVars$default$3());
         return Paths.get(envVarResolvedFilePath).toAbsolutePath().toUri().toURL();
      }, scala.reflect.ClassTag..MODULE$.apply(URL.class));
   }

   private Option getMainJarUri(final Option mainJar) {
      return mainJar.flatMap((path) -> {
         URI uri = org.apache.spark.util.Utils..MODULE$.resolveURI(path);
         String var10000 = uri.getScheme();
         String var2 = org.apache.spark.util.Utils..MODULE$.LOCAL_SCHEME();
         if (var10000 == null) {
            if (var2 == null) {
               return new Some(uri);
            }
         } else if (var10000.equals(var2)) {
            return new Some(uri);
         }

         return scala.None..MODULE$;
      }).orElse(() -> new Some(new URI(MODULE$.APP_JAR_NAME())));
   }

   private Seq getSecondaryJarUris(final Option secondaryJars) {
      return (Seq)((IterableOps)secondaryJars.getOrElse(() -> scala.collection.immutable.Nil..MODULE$)).map((x$24) -> new URI(x$24));
   }

   private void addFileToClasspath(final SparkConf conf, final Configuration hadoopConf, final URI uri, final String fileName, final HashMap env) {
      label26: {
         if (uri != null) {
            String var10000 = uri.getScheme();
            String var6 = org.apache.spark.util.Utils..MODULE$.LOCAL_SCHEME();
            if (var10000 == null) {
               if (var6 == null) {
                  break label26;
               }
            } else if (var10000.equals(var6)) {
               break label26;
            }
         }

         if (fileName != null) {
            this.addClasspathEntry(this.buildPath(.MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), fileName}))), env);
            return;
         }

         if (uri != null) {
            Path localPath = this.org$apache$spark$deploy$yarn$Client$$getQualifiedLocalPath(uri, hadoopConf);
            String linkName = (String)scala.Option..MODULE$.apply(uri.getFragment()).getOrElse(() -> localPath.getName());
            this.addClasspathEntry(this.buildPath(.MODULE$.wrapRefArray((Object[])(new String[]{Environment.PWD.$$(), linkName}))), env);
            return;
         }

         return;
      }

      this.addClasspathEntry(this.getClusterPath(conf, uri.getPath()), env);
   }

   private void addClasspathEntry(final String path, final HashMap env) {
      YarnSparkHadoopUtil$.MODULE$.addPathToEnvironment(env, Environment.CLASSPATH.name(), path);
   }

   public String getClusterPath(final SparkConf conf, final String path) {
      Option localPath = (Option)conf.get(package$.MODULE$.GATEWAY_ROOT_PATH());
      Option clusterPath = (Option)conf.get(package$.MODULE$.REPLACEMENT_ROOT_PATH());
      return localPath.isDefined() && clusterPath.isDefined() ? path.replace((CharSequence)localPath.get(), (CharSequence)clusterPath.get()) : path;
   }

   public boolean compareUri(final URI srcUri, final URI dstUri) {
      if (srcUri.getScheme() != null) {
         String var10000 = srcUri.getScheme();
         String var3 = dstUri.getScheme();
         if (var10000 == null) {
            if (var3 != null) {
               return false;
            }
         } else if (!var10000.equals(var3)) {
            return false;
         }

         String srcUserInfo = (String)scala.Option..MODULE$.apply(srcUri.getUserInfo()).getOrElse(() -> "");
         String dstUserInfo = (String)scala.Option..MODULE$.apply(dstUri.getUserInfo()).getOrElse(() -> "");
         if (!srcUserInfo.equals(dstUserInfo)) {
            return false;
         } else {
            String srcHost = srcUri.getHost();
            String dstHost = dstUri.getHost();
            if (srcHost != null && dstHost != null) {
               if (srcHost == null) {
                  if (dstHost == null) {
                     return Objects.equal(srcHost, dstHost) && srcUri.getPort() == dstUri.getPort();
                  }
               } else if (srcHost.equals(dstHost)) {
                  return Objects.equal(srcHost, dstHost) && srcUri.getPort() == dstUri.getPort();
               }

               try {
                  srcHost = InetAddress.getByName(srcHost).getCanonicalHostName();
                  dstHost = InetAddress.getByName(dstHost).getCanonicalHostName();
               } catch (UnknownHostException var10) {
                  return false;
               }
            }

            return Objects.equal(srcHost, dstHost) && srcUri.getPort() == dstUri.getPort();
         }
      } else {
         return false;
      }
   }

   public boolean compareFs(final FileSystem srcFs, final FileSystem destFs) {
      URI srcUri = srcFs.getUri();
      URI dstUri = destFs.getUri();
      return this.compareUri(srcUri, dstUri);
   }

   public Path org$apache$spark$deploy$yarn$Client$$getQualifiedLocalPath(final URI localURI, final Configuration hadoopConf) {
      URI qualifiedURI = localURI.getScheme() == null ? new URI(FileSystem.getLocal(hadoopConf).makeQualified(new Path(localURI)).toString()) : localURI;
      return new Path(qualifiedURI);
   }

   public boolean isUserClassPathFirst(final SparkConf conf, final boolean isDriver) {
      return isDriver ? BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_USER_CLASS_PATH_FIRST())) : BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_USER_CLASS_PATH_FIRST()));
   }

   public String buildPath(final Seq components) {
      return components.mkString("/");
   }

   public YarnAppReport createAppReport(final ApplicationReport report) {
      String diags = report.getDiagnostics();
      Option diagsOpt = (Option)(diags != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(diags)) ? new Some(diags) : scala.None..MODULE$);
      return new YarnAppReport(report.getYarnApplicationState(), report.getFinalApplicationStatus(), diagsOpt);
   }

   public String createLibraryPathPrefix(final String libpath, final SparkConf conf) {
      String var10000;
      if (org.apache.spark.util.Utils..MODULE$.isWindows()) {
         var10000 = org.apache.spark.util.Utils..MODULE$.libraryPathEnvPrefix(new scala.collection.immutable..colon.colon(libpath, scala.collection.immutable.Nil..MODULE$));
      } else {
         String envName = org.apache.spark.util.Utils..MODULE$.libraryPathEnvName();
         String quoted = libpath.replace("\"", "\\\\\\\"");
         var10000 = envName + "=\\\"" + quoted + File.pathSeparator + "$" + envName + "\\\"";
      }

      String cmdPrefix = var10000;
      return this.getClusterPath(conf, cmdPrefix);
   }

   public Properties confToProperties(final SparkConf conf) {
      Properties props = new Properties();
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])conf.getAll()), (x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return props.setProperty(k, v);
         } else {
            throw new MatchError(x0$1);
         }
      });
      return props;
   }

   public void writePropertiesToArchive(final Properties props, final String name, final ZipOutputStream out) {
      out.putNextEntry(new ZipEntry(name));
      OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
      props.store(writer, "Spark configuration.");
      writer.flush();
      out.closeEntry();
   }

   // $FF: synthetic method
   public static final void $anonfun$populateHadoopClasspath$1(final HashMap env$2, final String c) {
      YarnSparkHadoopUtil$.MODULE$.addPathToEnvironment(env$2, Environment.CLASSPATH.name(), c.trim());
   }

   // $FF: synthetic method
   public static final void $anonfun$populateClasspath$1(final SparkConf sparkConf$1, final HashMap env$3, final String cp) {
      MODULE$.addClasspathEntry(MODULE$.getClusterPath(sparkConf$1, cp), env$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$populateClasspath$2(final SparkConf sparkConf$1, final Configuration conf$1, final HashMap env$3, final URI x$23) {
      MODULE$.addFileToClasspath(sparkConf$1, conf$1, x$23, MODULE$.APP_JAR_NAME(), env$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$populateClasspath$3(final SparkConf sparkConf$1, final Configuration conf$1, final HashMap env$3, final URI x) {
      MODULE$.addFileToClasspath(sparkConf$1, conf$1, x, (String)null, env$3);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$populateClasspath$5(final String uri) {
      return org.apache.spark.util.Utils..MODULE$.isLocalUri(uri);
   }

   // $FF: synthetic method
   public static final void $anonfun$populateClasspath$6(final SparkConf sparkConf$1, final HashMap env$3, final String jar) {
      URI uri = new URI(jar);
      MODULE$.addClasspathEntry(MODULE$.getClusterPath(sparkConf$1, uri.getPath()), env$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$populateClasspath$4(final SparkConf sparkConf$1, final HashMap env$3, final Seq jars) {
      ((IterableOnceOps)jars.filter((uri) -> BoxesRunTime.boxToBoolean($anonfun$populateClasspath$5(uri)))).foreach((jar) -> {
         $anonfun$populateClasspath$6(sparkConf$1, env$3, jar);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$populateClasspath$8(final Set cpSet$1, final String elem) {
      return cpSet$1.contains(elem);
   }

   // $FF: synthetic method
   public static final void $anonfun$populateClasspath$7(final Set cpSet$1, final SparkConf sparkConf$1, final HashMap env$3, final String cp) {
      String newCp = org.apache.spark.util.Utils..MODULE$.isTesting() ? scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps((Object[])cp.split(File.pathSeparator)), (elem) -> BoxesRunTime.boxToBoolean($anonfun$populateClasspath$8(cpSet$1, elem)))).mkString(File.pathSeparator) : cp;
      MODULE$.addClasspathEntry(MODULE$.getClusterPath(sparkConf$1, newCp), env$3);
   }

   private Client$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
