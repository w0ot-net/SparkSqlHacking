package org.apache.spark.util;

import java.io.File;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.SparkSubmit$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple5;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class DependencyUtils$ implements Logging {
   public static final DependencyUtils$ MODULE$ = new DependencyUtils$();
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

   public IvyProperties getIvyProperties() {
      Seq var3 = (Seq)(new .colon.colon(org.apache.spark.internal.config.package$.MODULE$.JAR_PACKAGES_EXCLUSIONS().key(), new .colon.colon(org.apache.spark.internal.config.package$.MODULE$.JAR_PACKAGES().key(), new .colon.colon(org.apache.spark.internal.config.package$.MODULE$.JAR_REPOSITORIES().key(), new .colon.colon(org.apache.spark.internal.config.package$.MODULE$.JAR_IVY_REPO_PATH().key(), new .colon.colon(org.apache.spark.internal.config.package$.MODULE$.JAR_IVY_SETTING_PATH().key(), scala.collection.immutable.Nil..MODULE$)))))).map((x$1) -> (String)scala.sys.package..MODULE$.props().get(x$1).orNull(scala..less.colon.less..MODULE$.refl()));
      if (var3 != null) {
         SeqOps var4 = scala.package..MODULE$.Seq().unapplySeq(var3);
         if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var4) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 5) == 0) {
            String packagesExclusions = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 0);
            String packages = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 1);
            String repositories = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 2);
            String ivyRepoPath = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 3);
            String ivySettingsPath = (String)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var4), 4);
            Tuple5 var2 = new Tuple5(packagesExclusions, packages, repositories, ivyRepoPath, ivySettingsPath);
            String packagesExclusions = (String)var2._1();
            String packages = (String)var2._2();
            String repositories = (String)var2._3();
            String ivyRepoPath = (String)var2._4();
            String ivySettingsPath = (String)var2._5();
            return new IvyProperties(packagesExclusions, packages, repositories, ivyRepoPath, ivySettingsPath);
         }
      }

      throw new MatchError(var3);
   }

   public Seq resolveMavenDependencies(final URI uri) {
      IvyProperties ivyProperties = this.getIvyProperties();
      String authority = uri.getAuthority();
      if (authority == null) {
         throw new IllegalArgumentException("Invalid Ivy URI authority in uri " + uri.toString() + ": Expected 'org:module:version', found null.");
      } else if (authority.split(":").length != 3) {
         String var10002 = uri.toString();
         throw new IllegalArgumentException("Invalid Ivy URI authority in uri " + var10002 + ": Expected 'org:module:version', found " + authority + ".");
      } else {
         Tuple3 var6 = org.apache.spark.util.MavenUtils..MODULE$.parseQueryParams(uri);
         if (var6 != null) {
            boolean transitive = BoxesRunTime.unboxToBoolean(var6._1());
            String exclusionList = (String)var6._2();
            String repos = (String)var6._3();
            Tuple3 var5 = new Tuple3(BoxesRunTime.boxToBoolean(transitive), exclusionList, repos);
            boolean transitive = BoxesRunTime.unboxToBoolean(var5._1());
            String exclusionList = (String)var5._2();
            String repos = (String)var5._3();
            String fullReposList = ((IterableOnceOps)(new .colon.colon(ivyProperties.repositories(), new .colon.colon(repos, scala.collection.immutable.Nil..MODULE$))).filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$resolveMavenDependencies$1(x$4)))).mkString(",");
            return this.resolveMavenDependencies(transitive, exclusionList, authority, fullReposList, ivyProperties.ivyRepoPath(), scala.Option..MODULE$.apply(ivyProperties.ivySettingsPath()));
         } else {
            throw new MatchError(var6);
         }
      }
   }

   public Seq resolveMavenDependencies(final boolean packagesTransitive, final String packagesExclusions, final String packages, final String repositories, final String ivyRepoPath, final Option ivySettingsPath) {
      Seq exclusions = (Seq)(!StringUtils.isBlank(packagesExclusions) ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(packagesExclusions.split(",")).toImmutableArraySeq() : scala.collection.immutable.Nil..MODULE$);
      PrintStream printStream = SparkSubmit$.MODULE$.printStream();
      IvySettings var10000;
      if (ivySettingsPath instanceof Some var12) {
         String path = (String)var12.value();
         var10000 = org.apache.spark.util.MavenUtils..MODULE$.loadIvySettings(path, scala.Option..MODULE$.apply(repositories), scala.Option..MODULE$.apply(ivyRepoPath), printStream);
      } else {
         if (!scala.None..MODULE$.equals(ivySettingsPath)) {
            throw new MatchError(ivySettingsPath);
         }

         var10000 = org.apache.spark.util.MavenUtils..MODULE$.buildIvySettings(scala.Option..MODULE$.apply(repositories), scala.Option..MODULE$.apply(ivyRepoPath), org.apache.spark.util.MavenUtils..MODULE$.buildIvySettings$default$3(), printStream);
      }

      IvySettings ivySettings = var10000;
      Option x$5 = org.apache.spark.util.MavenUtils..MODULE$.resolveMavenCoordinates$default$3();
      boolean x$6 = org.apache.spark.util.MavenUtils..MODULE$.resolveMavenCoordinates$default$6();
      return org.apache.spark.util.MavenUtils..MODULE$.resolveMavenCoordinates(packages, ivySettings, x$5, packagesTransitive, exclusions, x$6, printStream);
   }

   public String resolveAndDownloadJars(final String jars, final String userJar, final SparkConf sparkConf, final Configuration hadoopConf) {
      File targetDir = Utils$.MODULE$.createTempDir();
      String userJarName = (String)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(userJar), File.separatorChar)));
      return (String)scala.Option..MODULE$.apply(jars).map((x$5) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps((Object[])MODULE$.resolveGlobPaths(x$5, hadoopConf).split(",")), (x$6) -> BoxesRunTime.boxToBoolean($anonfun$resolveAndDownloadJars$2(userJarName, x$6)))).mkString(",")).filterNot((x$7) -> BoxesRunTime.boxToBoolean($anonfun$resolveAndDownloadJars$3(x$7))).map((x$8) -> MODULE$.downloadFileList(x$8, targetDir, sparkConf, hadoopConf)).orNull(scala..less.colon.less..MODULE$.refl());
   }

   public void addJarsToClassPath(final String jars, final MutableURLClassLoader loader) {
      if (jars != null) {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])jars.split(",")), (jar) -> {
            $anonfun$addJarsToClassPath$1(loader, jar);
            return BoxedUnit.UNIT;
         });
      }
   }

   public String downloadFileList(final String fileList, final File targetDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      scala.Predef..MODULE$.require(fileList != null, () -> "fileList cannot be null.");
      return ((IterableOnceOps)Utils$.MODULE$.stringToSeq(fileList).map((x$9) -> MODULE$.downloadFile(x$9, targetDir, sparkConf, hadoopConf))).mkString(",");
   }

   public String downloadFile(final String path, final File targetDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      URI uri;
      label47: {
         scala.Predef..MODULE$.require(path != null, () -> "path cannot be null.");
         uri = Utils$.MODULE$.resolveURI(path);
         String var7 = uri.getScheme();
         switch (var7 == null ? 0 : var7.hashCode()) {
            case 101730:
               if (!"ftp".equals(var7)) {
                  break label47;
               }
               break;
            case 3143036:
               if ("file".equals(var7)) {
                  return path;
               }
               break label47;
            case 3213448:
               if (!"http".equals(var7)) {
                  break label47;
               }
               break;
            case 99617003:
               if (!"https".equals(var7)) {
                  break label47;
               }
               break;
            case 103145323:
               if ("local".equals(var7)) {
                  return path;
               }
            default:
               break label47;
         }

         if (Utils$.MODULE$.isTesting()) {
            File file = new File(uri.getPath());
            return (new File(targetDir, file.getName())).toURI().toString();
         }
      }

      String fname = (new Path(uri)).getName();
      File localFile = Utils$.MODULE$.doFetchFile(uri.toString(), targetDir, fname, sparkConf, hadoopConf);
      return localFile.toURI().toString();
   }

   public String resolveGlobPaths(final String paths, final Configuration hadoopConf) {
      scala.Predef..MODULE$.require(paths != null, () -> "paths cannot be null.");
      return ((IterableOnceOps)Utils$.MODULE$.stringToSeq(paths).flatMap((path) -> {
         Tuple2 var5 = MODULE$.splitOnFragment(path);
         if (var5 != null) {
            URI base = (URI)var5._1();
            Option fragment = (Option)var5._2();
            Tuple2 var4 = new Tuple2(base, fragment);
            URI basex = (URI)var4._1();
            Option fragmentx = (Option)var4._2();
            Tuple2 var10 = new Tuple2(MODULE$.resolveGlobPath(basex, hadoopConf), fragmentx);
            if (var10 != null) {
               String[] resolved = (String[])var10._1();
               Option var12 = (Option)var10._2();
               if (var12 instanceof Some && resolved.length > 1) {
                  String var10002 = basex.toString();
                  throw new SparkException(var10002 + " resolves ambiguously to multiple files: " + scala.Predef..MODULE$.wrapRefArray((Object[])resolved).mkString(","));
               }
            }

            if (var10 != null) {
               String[] resolved = (String[])var10._1();
               Option var14 = (Option)var10._2();
               if (var14 instanceof Some) {
                  Some var15 = (Some)var14;
                  String namedAs = (String)var15.value();
                  return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])resolved), (x$11) -> x$11 + "#" + namedAs, scala.reflect.ClassTag..MODULE$.apply(String.class)));
               }
            }

            if (var10 != null) {
               String[] resolved = (String[])var10._1();
               return scala.Predef..MODULE$.wrapRefArray((Object[])resolved);
            } else {
               throw new MatchError(var10);
            }
         } else {
            throw new MatchError(var5);
         }
      })).mkString(",");
   }

   public void addJarToClasspath(final String localJar, final MutableURLClassLoader loader) {
      URI uri;
      label41: {
         uri = Utils$.MODULE$.resolveURI(localJar);
         String var4 = uri.getScheme();
         switch (var4 == null ? 0 : var4.hashCode()) {
            case 3143036:
               if (!"file".equals(var4)) {
                  break label41;
               }
               break;
            case 103145323:
               if (!"local".equals(var4)) {
                  break label41;
               }
               break;
            default:
               break label41;
         }

         File file = new File(uri.getPath());
         if (file.exists()) {
            loader.addURL(file.toURI().toURL());
         } else {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Local jar ", " does not exist, skipping."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file)})))));
         }

         return;
      }

      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skip remote jar ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, uri)})))));
   }

   public String mergeFileLists(final Seq lists) {
      Seq merged = (Seq)((IterableOps)lists.filterNot((x$1) -> BoxesRunTime.boxToBoolean($anonfun$mergeFileLists$1(x$1)))).flatMap((str) -> Utils$.MODULE$.stringToSeq(str));
      return merged.nonEmpty() ? merged.mkString(",") : null;
   }

   private Tuple2 splitOnFragment(final String path) {
      URI uri = Utils$.MODULE$.resolveURI(path);
      URI withoutFragment = new URI(uri.getScheme(), uri.getSchemeSpecificPart(), (String)null);
      return new Tuple2(withoutFragment, scala.Option..MODULE$.apply(uri.getFragment()));
   }

   private String[] resolveGlobPath(final URI uri, final Configuration hadoopConf) {
      String var4 = uri.getScheme();
      switch (var4 == null ? 0 : var4.hashCode()) {
         case 101730:
            if ("ftp".equals(var4)) {
               return (String[])((Object[])(new String[]{uri.toString()}));
            }
            break;
         case 3213448:
            if ("http".equals(var4)) {
               return (String[])((Object[])(new String[]{uri.toString()}));
            }
            break;
         case 99617003:
            if ("https".equals(var4)) {
               return (String[])((Object[])(new String[]{uri.toString()}));
            }
            break;
         case 103145323:
            if ("local".equals(var4)) {
               return (String[])((Object[])(new String[]{uri.toString()}));
            }
      }

      FileSystem fs = FileSystem.get(uri, hadoopConf);
      return (String[])scala.Option..MODULE$.apply(fs.globStatus(new Path(uri))).map((status) -> (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])status), (x$12) -> BoxesRunTime.boxToBoolean($anonfun$resolveGlobPath$2(x$12)))), (x$13) -> x$13.getPath().toUri().toString(), scala.reflect.ClassTag..MODULE$.apply(String.class))).getOrElse(() -> (String[])((Object[])(new String[]{uri.toString()})));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolveMavenDependencies$1(final String x$4) {
      return !StringUtils.isBlank(x$4);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolveAndDownloadJars$2(final String userJarName$1, final String x$6) {
      return x$6.contains(userJarName$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolveAndDownloadJars$3(final String x$7) {
      boolean var10000;
      label23: {
         String var1 = "";
         if (x$7 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$7.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$addJarsToClassPath$1(final MutableURLClassLoader loader$1, final String jar) {
      MODULE$.addJarToClasspath(jar, loader$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergeFileLists$1(final CharSequence x$1) {
      return StringUtils.isBlank(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolveGlobPath$2(final FileStatus x$12) {
      return x$12.isFile();
   }

   private DependencyUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
