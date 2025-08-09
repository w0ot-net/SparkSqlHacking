package org.apache.spark.sql.hive.client;

import java.io.File;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.hive.HiveUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.package.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class IsolatedClientLoader$ implements Logging {
   public static final IsolatedClientLoader$ MODULE$ = new IsolatedClientLoader$();
   private static final HashMap resolvedVersions;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      resolvedVersions = new HashMap();
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

   public Seq $lessinit$greater$default$4() {
      return (Seq).MODULE$.Seq().empty();
   }

   public scala.collection.immutable.Map $lessinit$greater$default$5() {
      return scala.Predef..MODULE$.Map().empty();
   }

   public boolean $lessinit$greater$default$6() {
      return true;
   }

   public Option $lessinit$greater$default$7() {
      return scala.None..MODULE$;
   }

   public ClassLoader $lessinit$greater$default$8() {
      return Thread.currentThread().getContextClassLoader();
   }

   public Seq $lessinit$greater$default$9() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Seq $lessinit$greater$default$10() {
      return (Seq).MODULE$.Seq().empty();
   }

   public synchronized IsolatedClientLoader forVersion(final String hiveMetastoreVersion, final String hadoopVersion, final SparkConf sparkConf, final Configuration hadoopConf, final scala.collection.immutable.Map config, final Option ivyPath, final Seq sharedPrefixes, final Seq barrierPrefixes) {
      package.HiveVersion resolvedVersion = this.hiveVersion(hiveMetastoreVersion);
      Seq var10000;
      if (this.resolvedVersions().contains(new Tuple2(resolvedVersion, hadoopVersion))) {
         var10000 = (Seq)this.resolvedVersions().apply(new Tuple2(resolvedVersion, hadoopVersion));
      } else {
         String remoteRepos = (String)sparkConf.get(org.apache.spark.sql.internal.SQLConf..MODULE$.ADDITIONAL_REMOTE_REPOSITORIES());
         Tuple2 var14 = this.liftedTree1$1(resolvedVersion, hadoopVersion, ivyPath, remoteRepos);
         if (var14 == null) {
            throw new MatchError(var14);
         }

         Seq downloadedFiles = (Seq)var14._1();
         String actualHadoopVersion = (String)var14._2();
         Tuple2 var13 = new Tuple2(downloadedFiles, actualHadoopVersion);
         Seq downloadedFiles = (Seq)var13._1();
         String actualHadoopVersion = (String)var13._2();
         this.resolvedVersions().put(new Tuple2(resolvedVersion, actualHadoopVersion), downloadedFiles);
         var10000 = (Seq)this.resolvedVersions().apply(new Tuple2(resolvedVersion, actualHadoopVersion));
      }

      Seq files = var10000;
      package.HiveVersion x$1 = this.hiveVersion(hiveMetastoreVersion);
      boolean x$8 = this.$lessinit$greater$default$6();
      Option x$9 = this.$lessinit$greater$default$7();
      ClassLoader x$10 = this.$lessinit$greater$default$8();
      return new IsolatedClientLoader(x$1, sparkConf, hadoopConf, files, config, x$8, x$9, x$10, sharedPrefixes, barrierPrefixes);
   }

   public scala.collection.immutable.Map forVersion$default$5() {
      return scala.Predef..MODULE$.Map().empty();
   }

   public Option forVersion$default$6() {
      return scala.None..MODULE$;
   }

   public Seq forVersion$default$7() {
      return (Seq).MODULE$.Seq().empty();
   }

   public Seq forVersion$default$8() {
      return (Seq).MODULE$.Seq().empty();
   }

   public package.HiveVersion hiveVersion(final String version) {
      return (package.HiveVersion)org.apache.spark.util.VersionUtils..MODULE$.majorMinorPatchVersion(version).flatMap((x0$1) -> {
         if (x0$1 != null) {
            int var3 = BoxesRunTime.unboxToInt(x0$1._1());
            int var4 = BoxesRunTime.unboxToInt(x0$1._2());
            if (2 == var3 && 0 == var4) {
               return new Some(package$hive$v2_0$.MODULE$);
            }
         }

         if (x0$1 != null) {
            int var5 = BoxesRunTime.unboxToInt(x0$1._1());
            int var6 = BoxesRunTime.unboxToInt(x0$1._2());
            if (2 == var5 && 1 == var6) {
               return new Some(package$hive$v2_1$.MODULE$);
            }
         }

         if (x0$1 != null) {
            int var7 = BoxesRunTime.unboxToInt(x0$1._1());
            int var8 = BoxesRunTime.unboxToInt(x0$1._2());
            if (2 == var7 && 2 == var8) {
               return new Some(package$hive$v2_2$.MODULE$);
            }
         }

         if (x0$1 != null) {
            int var9 = BoxesRunTime.unboxToInt(x0$1._1());
            int var10 = BoxesRunTime.unboxToInt(x0$1._2());
            if (2 == var9 && 3 == var10) {
               return new Some(package$hive$v2_3$.MODULE$);
            }
         }

         if (x0$1 != null) {
            int var11 = BoxesRunTime.unboxToInt(x0$1._1());
            int var12 = BoxesRunTime.unboxToInt(x0$1._2());
            if (3 == var11 && 0 == var12) {
               return new Some(package$hive$v3_0$.MODULE$);
            }
         }

         if (x0$1 != null) {
            int var13 = BoxesRunTime.unboxToInt(x0$1._1());
            int var14 = BoxesRunTime.unboxToInt(x0$1._2());
            if (3 == var13 && 1 == var14) {
               return new Some(package$hive$v3_1$.MODULE$);
            }
         }

         if (x0$1 != null) {
            int var15 = BoxesRunTime.unboxToInt(x0$1._1());
            int var16 = BoxesRunTime.unboxToInt(x0$1._2());
            if (4 == var15 && 0 == var16) {
               return new Some(package$hive$v4_0$.MODULE$);
            }
         }

         return scala.None..MODULE$;
      }).getOrElse(() -> {
         throw org.apache.spark.sql.errors.QueryExecutionErrors..MODULE$.unsupportedHiveMetastoreVersionError(version, HiveUtils$.MODULE$.HIVE_METASTORE_VERSION().key());
      });
   }

   public boolean supportsHadoopShadedClient(final String hadoopVersion) {
      return org.apache.spark.util.VersionUtils..MODULE$.majorMinorPatchVersion(hadoopVersion).exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$supportsHadoopShadedClient$1(x0$1)));
   }

   private Seq downloadVersion(final package.HiveVersion version, final String hadoopVersion, final Option ivyPath, final String remoteRepos) {
      Seq hadoopJarNames = this.supportsHadoopShadedClient(hadoopVersion) ? new scala.collection.immutable..colon.colon("org.apache.hadoop:hadoop-client-api:" + hadoopVersion, new scala.collection.immutable..colon.colon("org.apache.hadoop:hadoop-client-runtime:" + hadoopVersion, scala.collection.immutable.Nil..MODULE$)) : new scala.collection.immutable..colon.colon("org.apache.hadoop:hadoop-client:" + hadoopVersion, scala.collection.immutable.Nil..MODULE$);
      Seq hiveArtifacts = (Seq)((IterableOps)version.extraDeps().$plus$plus((IterableOnce)(new scala.collection.immutable..colon.colon("hive-metastore", new scala.collection.immutable..colon.colon("hive-exec", new scala.collection.immutable..colon.colon("hive-common", new scala.collection.immutable..colon.colon("hive-serde", scala.collection.immutable.Nil..MODULE$))))).map((a) -> "org.apache.hive:" + a + ":" + version.fullVersion()))).$plus$plus(hadoopJarNames);
      PrintStream printStream = org.apache.spark.deploy.SparkSubmit..MODULE$.printStream();
      Seq classpaths = (Seq)org.apache.spark.sql.catalyst.util.package..MODULE$.quietly(() -> org.apache.spark.util.MavenUtils..MODULE$.resolveMavenCoordinates(hiveArtifacts.mkString(","), org.apache.spark.util.MavenUtils..MODULE$.buildIvySettings(new Some(remoteRepos), ivyPath, org.apache.spark.util.MavenUtils..MODULE$.buildIvySettings$default$3(), printStream), new Some(org.apache.spark.util.MavenUtils..MODULE$.buildIvySettings(new Some(remoteRepos), ivyPath, false, printStream)), true, version.exclusions(), org.apache.spark.util.MavenUtils..MODULE$.resolveMavenCoordinates$default$6(), printStream));
      Set allFiles = ((IterableOnceOps)classpaths.map((x$2x) -> new File(x$2x))).toSet();
      String x$1 = "hive-" + version;
      String x$2 = org.apache.spark.util.Utils..MODULE$.createTempDir$default$1();
      File tempDir = org.apache.spark.util.Utils..MODULE$.createTempDir(x$2, x$1);
      allFiles.foreach((f) -> {
         $anonfun$downloadVersion$4(tempDir, f);
         return BoxedUnit.UNIT;
      });
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Downloaded metastore jars to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, tempDir.getCanonicalPath())})))));
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])tempDir.listFiles()), (x$3) -> x$3.toURI().toURL(), scala.reflect.ClassTag..MODULE$.apply(URL.class))).toImmutableArraySeq();
   }

   private HashMap resolvedVersions() {
      return resolvedVersions;
   }

   // $FF: synthetic method
   private final Tuple2 liftedTree1$1(final package.HiveVersion resolvedVersion$1, final String hadoopVersion$1, final Option ivyPath$1, final String remoteRepos$1) {
      Tuple2 var10000;
      try {
         var10000 = new Tuple2(this.downloadVersion(resolvedVersion$1, hadoopVersion$1, ivyPath$1, remoteRepos$1), hadoopVersion$1);
      } catch (Throwable var10) {
         if (var10 instanceof RuntimeException var8) {
            if (var8.getMessage().contains("hadoop")) {
               String fallbackVersion = "3.4.1";
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to resolve Hadoop artifacts for the version "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". We will change the hadoop version from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HADOOP_VERSION..MODULE$, hadoopVersion$1)})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HADOOP_VERSION..MODULE$, hadoopVersion$1)})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " and try again. It is recommended to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FALLBACK_VERSION..MODULE$, fallbackVersion)})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"set jars used by Hive metastore client through spark.sql.hive.metastore.jars "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"in the production environment."})))).log(scala.collection.immutable.Nil..MODULE$))));
               var10000 = new Tuple2(this.downloadVersion(resolvedVersion$1, fallbackVersion, ivyPath$1, remoteRepos$1), fallbackVersion);
               return var10000;
            }
         }

         throw var10;
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$supportsHadoopShadedClient$1(final Tuple3 x0$1) {
      if (x0$1 != null) {
         int var3 = BoxesRunTime.unboxToInt(x0$1._1());
         int var4 = BoxesRunTime.unboxToInt(x0$1._2());
         int v = BoxesRunTime.unboxToInt(x0$1._3());
         if (3 == var3 && 2 == var4 && v >= 2) {
            return true;
         }
      }

      if (x0$1 != null) {
         int var6 = BoxesRunTime.unboxToInt(x0$1._1());
         int var7 = BoxesRunTime.unboxToInt(x0$1._2());
         int v = BoxesRunTime.unboxToInt(x0$1._3());
         if (3 == var6 && 3 == var7 && v >= 1) {
            return true;
         }
      }

      if (x0$1 != null) {
         int var9 = BoxesRunTime.unboxToInt(x0$1._1());
         int v = BoxesRunTime.unboxToInt(x0$1._2());
         if (3 == var9 && v >= 4) {
            return true;
         }
      }

      if (x0$1 != null) {
         int v = BoxesRunTime.unboxToInt(x0$1._1());
         if (v >= 4) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final void $anonfun$downloadVersion$4(final File tempDir$1, final File f) {
      FileUtils.copyFileToDirectory(f, tempDir$1);
   }

   private IsolatedClientLoader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
