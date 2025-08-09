package org.apache.spark.sql.hive;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.util.VersionInfo;
import org.apache.hive.common.util.HiveVersionInfo;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigBuilder;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.hive.client.HiveClient;
import org.apache.spark.sql.hive.client.HiveClientImpl;
import org.apache.spark.sql.hive.client.HiveClientImpl$;
import org.apache.spark.sql.hive.client.IsolatedClientLoader;
import org.apache.spark.sql.hive.client.IsolatedClientLoader$;
import org.apache.spark.sql.internal.SQLConf;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps;
import scala.collection.StringOps.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.HashMap;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

public final class HiveUtils$ implements Logging {
   public static final HiveUtils$ MODULE$ = new HiveUtils$();
   private static final Regex PATTERN_FOR_KEY_EQ_VAL;
   private static final String builtinHiveVersion;
   private static final ConfigEntry BUILTIN_HIVE_VERSION;
   private static final ConfigEntry HIVE_METASTORE_VERSION;
   private static final ConfigEntry HIVE_METASTORE_JARS;
   private static final ConfigEntry HIVE_METASTORE_JARS_PATH;
   private static final ConfigEntry CONVERT_METASTORE_PARQUET;
   private static final ConfigEntry CONVERT_METASTORE_PARQUET_WITH_SCHEMA_MERGING;
   private static final ConfigEntry CONVERT_METASTORE_ORC;
   private static final ConfigEntry CONVERT_INSERTING_PARTITIONED_TABLE;
   private static final ConfigEntry CONVERT_INSERTING_UNPARTITIONED_TABLE;
   private static final ConfigEntry CONVERT_METASTORE_CTAS;
   private static final ConfigEntry CONVERT_METASTORE_INSERT_DIR;
   private static final ConfigEntry HIVE_METASTORE_SHARED_PREFIXES;
   private static final ConfigEntry HIVE_METASTORE_BARRIER_PREFIXES;
   private static final ConfigEntry HIVE_THRIFT_SERVER_ASYNC;
   private static final ConfigEntry USE_DELEGATE_FOR_SYMLINK_TEXT_INPUT_FORMAT;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      PATTERN_FOR_KEY_EQ_VAL = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(.+)=(.+)"));
      builtinHiveVersion = HiveVersionInfo.getVersion();
      BUILTIN_HIVE_VERSION = org.apache.spark.sql.internal.SQLConf..MODULE$.buildStaticConf("spark.sql.hive.version").doc("The compiled, a.k.a, builtin Hive version of the Spark distribution bundled with. Note that, this a read-only conf and only used to report the built-in hive version. If you want a different metastore client for Spark to call, please refer to spark.sql.hive.metastore.version.").version("1.1.1").stringConf().checkValue((x$1) -> BoxesRunTime.boxToBoolean($anonfun$BUILTIN_HIVE_VERSION$1(x$1)), "The builtin Hive version is read-only, please use spark.sql.hive.metastore.version").createWithDefault(MODULE$.builtinHiveVersion());
      HIVE_METASTORE_VERSION = org.apache.spark.sql.internal.SQLConf..MODULE$.buildStaticConf("spark.sql.hive.metastore.version").doc("Version of the Hive metastore. Available options are <code>2.0.0</code> through <code>2.3.10</code>, <code>3.0.0</code> through <code>3.1.3</code> and <code>4.0.0</code> through <code>4.0.1</code>.").version("1.4.0").stringConf().checkValue((hiveVersionStr) -> BoxesRunTime.boxToBoolean($anonfun$HIVE_METASTORE_VERSION$1(hiveVersionStr)), "Unsupported Hive Metastore version").createWithDefault(MODULE$.builtinHiveVersion());
      ConfigBuilder var10000 = org.apache.spark.sql.internal.SQLConf..MODULE$.buildStaticConf("spark.sql.hive.metastore.jars");
      StringOps var10001 = .MODULE$;
      Predef var10002 = scala.Predef..MODULE$;
      String var10003 = MODULE$.builtinHiveVersion();
      HIVE_METASTORE_JARS = var10000.doc(var10001.stripMargin$extension(var10002.augmentString("\n      | Location of the jars that should be used to instantiate the HiveMetastoreClient.\n      | This property can be one of four options:\n      | 1. \"builtin\"\n      |   Use Hive " + var10003 + ", which is bundled with the Spark assembly when\n      |   <code>-Phive</code> is enabled. When this option is chosen,\n      |   <code>spark.sql.hive.metastore.version</code> must be either\n      |   <code>" + MODULE$.builtinHiveVersion() + "</code> or not defined.\n      | 2. \"maven\"\n      |   Use Hive jars of specified version downloaded from Maven repositories.\n      | 3. \"path\"\n      |   Use Hive jars configured by `spark.sql.hive.metastore.jars.path`\n      |   in comma separated format. Support both local or remote paths.The provided jars\n      |   should be the same version as `" + MODULE$.HIVE_METASTORE_VERSION().key() + "`.\n      | 4. A classpath in the standard format for both Hive and Hadoop. The provided jars\n      |   should be the same version as `" + MODULE$.HIVE_METASTORE_VERSION().key() + "`.\n      "))).version("1.4.0").stringConf().createWithDefault("builtin");
      HIVE_METASTORE_JARS_PATH = org.apache.spark.sql.internal.SQLConf..MODULE$.buildStaticConf("spark.sql.hive.metastore.jars.path").doc(.MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n      | Comma-separated paths of the jars that used to instantiate the HiveMetastoreClient.\n      | This configuration is useful only when `" + MODULE$.HIVE_METASTORE_JARS().key() + "` is set as `path`.\n      | The paths can be any of the following format:\n      | 1. file://path/to/jar/foo.jar\n      | 2. hdfs://nameservice/path/to/jar/foo.jar\n      | 3. /path/to/jar/ (path without URI scheme follow conf `fs.defaultFS`'s URI schema)\n      | 4. [http/https/ftp]://path/to/jar/foo.jar\n      | Note that 1, 2, and 3 support wildcard. For example:\n      | 1. file://path/to/jar/*,file://path2/to/jar/*/*.jar\n      | 2. hdfs://nameservice/path/to/jar/*,hdfs://nameservice2/path/to/jar/*/*.jar\n      "))).version("3.1.0").stringConf().toSequence().createWithDefault(scala.collection.immutable.Nil..MODULE$);
      CONVERT_METASTORE_PARQUET = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.convertMetastoreParquet").doc("When set to true, the built-in Parquet reader and writer are used to process parquet tables created by using the HiveQL syntax, instead of Hive serde.").version("1.1.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      CONVERT_METASTORE_PARQUET_WITH_SCHEMA_MERGING = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.convertMetastoreParquet.mergeSchema").doc("When true, also tries to merge possibly different but compatible Parquet schemas in different Parquet data files. This configuration is only effective when \"spark.sql.hive.convertMetastoreParquet\" is true.").version("1.3.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      CONVERT_METASTORE_ORC = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.convertMetastoreOrc").doc("When set to true, the built-in ORC reader and writer are used to process ORC tables created by using the HiveQL syntax, instead of Hive serde.").version("2.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      CONVERT_INSERTING_PARTITIONED_TABLE = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.convertInsertingPartitionedTable").doc("When set to true, and `spark.sql.hive.convertMetastoreParquet` or `spark.sql.hive.convertMetastoreOrc` is true, the built-in ORC/Parquet writer is usedto process inserting into partitioned ORC/Parquet tables created by using the HiveSQL syntax.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      CONVERT_INSERTING_UNPARTITIONED_TABLE = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.convertInsertingUnpartitionedTable").doc("When set to true, and `spark.sql.hive.convertMetastoreParquet` or `spark.sql.hive.convertMetastoreOrc` is true, the built-in ORC/Parquet writer is usedto process inserting into unpartitioned ORC/Parquet tables created by using the HiveSQL syntax.").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      CONVERT_METASTORE_CTAS = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.convertMetastoreCtas").doc("When set to true,  Spark will try to use built-in data source writer instead of Hive serde in CTAS. This flag is effective only if `spark.sql.hive.convertMetastoreParquet` or `spark.sql.hive.convertMetastoreOrc` is enabled respectively for Parquet and ORC formats").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      CONVERT_METASTORE_INSERT_DIR = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.convertMetastoreInsertDir").doc("When set to true,  Spark will try to use built-in data source writer instead of Hive serde in INSERT OVERWRITE DIRECTORY. This flag is effective only if `spark.sql.hive.convertMetastoreParquet` or `spark.sql.hive.convertMetastoreOrc` is enabled respectively for Parquet and ORC formats").version("3.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      HIVE_METASTORE_SHARED_PREFIXES = org.apache.spark.sql.internal.SQLConf..MODULE$.buildStaticConf("spark.sql.hive.metastore.sharedPrefixes").doc("A comma separated list of class prefixes that should be loaded using the classloader that is shared between Spark SQL and a specific version of Hive. An example of classes that should be shared is JDBC drivers that are needed to talk to the metastore. Other classes that need to be shared are those that interact with classes that are already shared. For example, custom appenders that are used by log4j.").version("1.4.0").stringConf().toSequence().createWithDefault(MODULE$.jdbcPrefixes());
      HIVE_METASTORE_BARRIER_PREFIXES = org.apache.spark.sql.internal.SQLConf..MODULE$.buildStaticConf("spark.sql.hive.metastore.barrierPrefixes").doc("A comma separated list of class prefixes that should explicitly be reloaded for each version of Hive that Spark SQL is communicating with. For example, Hive UDFs that are declared in a prefix that typically would be shared (i.e. <code>org.apache.spark.*</code>).").version("1.4.0").stringConf().toSequence().createWithDefault(scala.collection.immutable.Nil..MODULE$);
      HIVE_THRIFT_SERVER_ASYNC = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.thriftServer.async").doc("When set to true, Hive Thrift server executes SQL queries in an asynchronous way.").version("1.5.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      USE_DELEGATE_FOR_SYMLINK_TEXT_INPUT_FORMAT = org.apache.spark.sql.internal.SQLConf..MODULE$.buildConf("spark.sql.hive.useDelegateForSymlinkTextInputFormat").internal().doc("When true, SymlinkTextInputFormat is replaced with a similar delegate class during table scan in order to fix the issue of empty splits").version("3.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
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

   private Regex PATTERN_FOR_KEY_EQ_VAL() {
      return PATTERN_FOR_KEY_EQ_VAL;
   }

   public String builtinHiveVersion() {
      return builtinHiveVersion;
   }

   public ConfigEntry BUILTIN_HIVE_VERSION() {
      return BUILTIN_HIVE_VERSION;
   }

   private boolean isCompatibleHiveVersion(final String hiveVersionStr) {
      return scala.util.Try..MODULE$.apply(() -> IsolatedClientLoader$.MODULE$.hiveVersion(hiveVersionStr)).isSuccess();
   }

   public ConfigEntry HIVE_METASTORE_VERSION() {
      return HIVE_METASTORE_VERSION;
   }

   public ConfigEntry HIVE_METASTORE_JARS() {
      return HIVE_METASTORE_JARS;
   }

   public ConfigEntry HIVE_METASTORE_JARS_PATH() {
      return HIVE_METASTORE_JARS_PATH;
   }

   public ConfigEntry CONVERT_METASTORE_PARQUET() {
      return CONVERT_METASTORE_PARQUET;
   }

   public ConfigEntry CONVERT_METASTORE_PARQUET_WITH_SCHEMA_MERGING() {
      return CONVERT_METASTORE_PARQUET_WITH_SCHEMA_MERGING;
   }

   public ConfigEntry CONVERT_METASTORE_ORC() {
      return CONVERT_METASTORE_ORC;
   }

   public ConfigEntry CONVERT_INSERTING_PARTITIONED_TABLE() {
      return CONVERT_INSERTING_PARTITIONED_TABLE;
   }

   public ConfigEntry CONVERT_INSERTING_UNPARTITIONED_TABLE() {
      return CONVERT_INSERTING_UNPARTITIONED_TABLE;
   }

   public ConfigEntry CONVERT_METASTORE_CTAS() {
      return CONVERT_METASTORE_CTAS;
   }

   public ConfigEntry CONVERT_METASTORE_INSERT_DIR() {
      return CONVERT_METASTORE_INSERT_DIR;
   }

   public ConfigEntry HIVE_METASTORE_SHARED_PREFIXES() {
      return HIVE_METASTORE_SHARED_PREFIXES;
   }

   private Seq jdbcPrefixes() {
      return new scala.collection.immutable..colon.colon("com.mysql.jdbc", new scala.collection.immutable..colon.colon("org.postgresql", new scala.collection.immutable..colon.colon("com.microsoft.sqlserver", new scala.collection.immutable..colon.colon("oracle.jdbc", scala.collection.immutable.Nil..MODULE$))));
   }

   public ConfigEntry HIVE_METASTORE_BARRIER_PREFIXES() {
      return HIVE_METASTORE_BARRIER_PREFIXES;
   }

   public ConfigEntry HIVE_THRIFT_SERVER_ASYNC() {
      return HIVE_THRIFT_SERVER_ASYNC;
   }

   public ConfigEntry USE_DELEGATE_FOR_SYMLINK_TEXT_INPUT_FORMAT() {
      return USE_DELEGATE_FOR_SYMLINK_TEXT_INPUT_FORMAT;
   }

   private String hiveMetastoreVersion(final SQLConf conf) {
      return (String)conf.getConf(this.HIVE_METASTORE_VERSION());
   }

   private String hiveMetastoreJars(final SQLConf conf) {
      return (String)conf.getConf(this.HIVE_METASTORE_JARS());
   }

   private Seq hiveMetastoreJarsPath(final SQLConf conf) {
      return (Seq)conf.getConf(this.HIVE_METASTORE_JARS_PATH());
   }

   private Seq hiveMetastoreSharedPrefixes(final SQLConf conf) {
      return (Seq)((IterableOps)conf.getConf(this.HIVE_METASTORE_SHARED_PREFIXES())).filterNot((x$2) -> BoxesRunTime.boxToBoolean($anonfun$hiveMetastoreSharedPrefixes$1(x$2)));
   }

   private Seq hiveMetastoreBarrierPrefixes(final SQLConf conf) {
      return (Seq)((IterableOps)conf.getConf(this.HIVE_METASTORE_BARRIER_PREFIXES())).filterNot((x$3) -> BoxesRunTime.boxToBoolean($anonfun$hiveMetastoreBarrierPrefixes$1(x$3)));
   }

   public boolean isCliSessionState() {
      SessionState state = SessionState.get();
      Class temp = state != null ? state.getClass() : null;

      boolean found;
      for(found = false; temp != null && !found; temp = temp.getSuperclass()) {
         boolean var5;
         label30: {
            label29: {
               String var10000 = temp.getName();
               String var4 = "org.apache.hadoop.hive.cli.CliSessionState";
               if (var10000 == null) {
                  if (var4 == null) {
                     break label29;
                  }
               } else if (var10000.equals(var4)) {
                  break label29;
               }

               var5 = false;
               break label30;
            }

            var5 = true;
         }

         found = var5;
      }

      return found;
   }

   public HiveClientImpl newClientForExecution(final SparkConf conf, final Configuration hadoopConf) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initializing execution hive, version "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HIVE_METASTORE_VERSION..MODULE$, MODULE$.builtinHiveVersion())}))))));
      org.apache.spark.sql.hive.client.package.HiveVersion x$1 = IsolatedClientLoader$.MODULE$.hiveVersion(this.builtinHiveVersion());
      Seq x$3 = (Seq)scala.package..MODULE$.Seq().empty();
      scala.collection.immutable.Map x$5 = this.newTemporaryConfiguration(true);
      boolean x$6 = false;
      ClassLoader x$7 = org.apache.spark.util.Utils..MODULE$.getContextOrSparkClassLoader();
      Option x$8 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$7();
      Seq x$9 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$9();
      Seq x$10 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$10();
      IsolatedClientLoader loader = new IsolatedClientLoader(x$1, conf, hadoopConf, x$3, x$5, false, x$8, x$7, x$9, x$10);
      return (HiveClientImpl)loader.createClient();
   }

   public HiveClient newClientForMetadata(final SparkConf conf, final Configuration hadoopConf, final scala.collection.immutable.Map configurations) {
      String hiveMetastoreVersion;
      label57: {
         IsolatedClientLoader var55;
         label59: {
            SQLConf sqlConf;
            String hiveMetastoreJars;
            Seq hiveMetastoreSharedPrefixes;
            Seq hiveMetastoreBarrierPrefixes;
            org.apache.spark.sql.hive.client.package.HiveVersion metaVersion;
            label60: {
               sqlConf = new SQLConf();
               sqlConf.setConf(org.apache.spark.sql.classic.SQLContext..MODULE$.getSQLProperties(conf));
               hiveMetastoreVersion = this.hiveMetastoreVersion(sqlConf);
               hiveMetastoreJars = this.hiveMetastoreJars(sqlConf);
               hiveMetastoreSharedPrefixes = this.hiveMetastoreSharedPrefixes(sqlConf);
               hiveMetastoreBarrierPrefixes = this.hiveMetastoreBarrierPrefixes(sqlConf);
               metaVersion = IsolatedClientLoader$.MODULE$.hiveVersion(hiveMetastoreVersion);
               String var11 = "builtin";
               if (hiveMetastoreJars == null) {
                  if (var11 != null) {
                     break label60;
                  }
               } else if (!hiveMetastoreJars.equals(var11)) {
                  break label60;
               }

               String var10000 = this.builtinHiveVersion();
               if (var10000 == null) {
                  if (hiveMetastoreVersion != null) {
                     break label57;
                  }
               } else if (!var10000.equals(hiveMetastoreVersion)) {
                  break label57;
               }

               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initializing HiveMetastoreConnection version "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " using Spark classes."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HIVE_METASTORE_VERSION..MODULE$, hiveMetastoreVersion)}))))));
               boolean x$5 = false;
               Some x$6 = new Some(BoxesRunTime.boxToBoolean(!this.isCliSessionState()));
               Seq x$9 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$4();
               ClassLoader x$10 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$8();
               var55 = new IsolatedClientLoader(metaVersion, conf, hadoopConf, x$9, configurations, false, x$6, x$10, hiveMetastoreSharedPrefixes, hiveMetastoreBarrierPrefixes);
               break label59;
            }

            label61: {
               String var23 = "maven";
               if (hiveMetastoreJars == null) {
                  if (var23 == null) {
                     break label61;
                  }
               } else if (hiveMetastoreJars.equals(var23)) {
                  break label61;
               }

               label36: {
                  String var32 = "path";
                  if (hiveMetastoreJars == null) {
                     if (var32 == null) {
                        break label36;
                     }
                  } else if (hiveMetastoreJars.equals(var32)) {
                     break label36;
                  }

                  URL[] jars = (URL[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])hiveMetastoreJars.split(File.pathSeparator)), (path) -> this.addLocalHiveJars$1(new File(path)), scala.reflect.ClassTag..MODULE$.apply(URL.class));
                  this.logInitWithPath$1(scala.collection.ArrayOps..MODULE$.toSeq$extension(scala.Predef..MODULE$.refArrayOps((Object[])jars)), hiveMetastoreVersion);
                  ArraySeq x$38 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(jars).toImmutableArraySeq();
                  boolean x$40 = true;
                  Option x$43 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$7();
                  ClassLoader x$44 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$8();
                  var55 = new IsolatedClientLoader(metaVersion, conf, hadoopConf, x$38, configurations, true, x$43, x$44, hiveMetastoreSharedPrefixes, hiveMetastoreBarrierPrefixes);
                  break label59;
               }

               Seq jars = (Seq)this.hiveMetastoreJarsPath(sqlConf).flatMap((x0$1) -> {
                  switch (x0$1 == null ? 0 : x0$1.hashCode()) {
                     default:
                        if (x0$1.contains("\\") && org.apache.spark.util.Utils..MODULE$.isWindows()) {
                           return this.addLocalHiveJars$1(new File(x0$1));
                        } else {
                           Seq x$19 = new scala.collection.immutable..colon.colon(x0$1, scala.collection.immutable.Nil..MODULE$);
                           boolean x$21 = true;
                           boolean x$22 = false;
                           boolean x$23 = true;
                           Integer x$24 = org.apache.spark.sql.execution.datasources.DataSource..MODULE$.checkAndGlobPathIfNecessary$default$5();
                           return (Seq)org.apache.spark.sql.execution.datasources.DataSource..MODULE$.checkAndGlobPathIfNecessary(x$19, hadoopConf, true, false, x$24, true).map((x$6) -> x$6.toUri().toURL());
                        }
                  }
               });
               this.logInitWithPath$1(jars, hiveMetastoreVersion);
               boolean x$30 = true;
               Option x$33 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$7();
               ClassLoader x$34 = IsolatedClientLoader$.MODULE$.$lessinit$greater$default$8();
               var55 = new IsolatedClientLoader(metaVersion, conf, hadoopConf, jars, configurations, true, x$33, x$34, hiveMetastoreSharedPrefixes, hiveMetastoreBarrierPrefixes);
               break label59;
            }

            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initializing HiveMetastoreConnection version "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " using maven."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HIVE_METASTORE_VERSION..MODULE$, hiveMetastoreVersion)}))))));
            String x$12 = VersionInfo.getVersion();
            Option x$18 = IsolatedClientLoader$.MODULE$.forVersion$default$6();
            var55 = IsolatedClientLoader$.MODULE$.forVersion(hiveMetastoreVersion, x$12, conf, hadoopConf, configurations, x$18, hiveMetastoreSharedPrefixes, hiveMetastoreBarrierPrefixes);
         }

         IsolatedClientLoader isolatedLoader = var55;
         return isolatedLoader.createClient();
      }

      String var10002 = this.builtinHiveVersion();
      throw new IllegalArgumentException("Builtin jars can only be used when hive execution version == hive metastore version. Execution: " + var10002 + " != Metastore: " + hiveMetastoreVersion + ". Specify a valid path to the correct hive jars using " + this.HIVE_METASTORE_JARS().key() + " or change " + this.HIVE_METASTORE_VERSION().key() + " to " + this.builtinHiveVersion() + ".");
   }

   public scala.collection.immutable.Map newClientForMetadata$default$3() {
      return scala.Predef..MODULE$.Map().empty();
   }

   public scala.collection.immutable.Map newTemporaryConfiguration(final boolean useInMemoryDerby) {
      String withInMemoryMode = useInMemoryDerby ? "memory:" : "";
      File tempDir = org.apache.spark.util.Utils..MODULE$.createTempDir();
      File localMetastore = new File(tempDir, "metastore");
      HashMap propMap = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])ConfVars.values()), (confvar) -> !confvar.varname.contains("datanucleus") && !confvar.varname.contains("jdo") && !confvar.varname.contains("hive.metastore.rawstore.impl") ? BoxedUnit.UNIT : propMap.put(confvar.varname, confvar.getDefaultExpr()));
      propMap.put(org.apache.spark.sql.internal.StaticSQLConf..MODULE$.WAREHOUSE_PATH().key(), localMetastore.toURI().toString());
      propMap.put("javax.jdo.option.ConnectionURL", "jdbc:derby:" + withInMemoryMode + ";databaseName=" + localMetastore.getAbsolutePath() + ";create=true");
      propMap.put("datanucleus.rdbms.datastoreAdapterClassName", "org.datanucleus.store.rdbms.adapter.DerbyAdapter");
      propMap.put("hive.metastore.schema.verification", "false");
      propMap.put("datanucleus.schema.autoCreateAll", "true");
      propMap.put("hive.metastore.uris", "");
      propMap.put(ConfVars.METASTORE_PRE_EVENT_LISTENERS.varname, "");
      propMap.put(ConfVars.METASTORE_EVENT_LISTENERS.varname, "");
      propMap.put(ConfVars.METASTORE_END_FUNCTION_LISTENERS.varname, "");
      org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().appendSparkHadoopConfigs(scala.sys.package..MODULE$.props().toMap(scala..less.colon.less..MODULE$.refl()), propMap);
      org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().appendSparkHiveConfigs(scala.sys.package..MODULE$.props().toMap(scala..less.colon.less..MODULE$.refl()), propMap);
      return propMap.toMap(scala..less.colon.less..MODULE$.refl());
   }

   public CatalogTable inferSchema(final CatalogTable table) {
      if (!org.apache.spark.sql.execution.command.DDLUtils..MODULE$.isDatasourceTable(table) && !table.dataSchema().nonEmpty()) {
         Table hiveTable = HiveClientImpl$.MODULE$.toHiveTable(table, HiveClientImpl$.MODULE$.toHiveTable$default$2());
         Buffer partCols = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hiveTable.getPartCols()).asScala().map((hc) -> HiveClientImpl$.MODULE$.fromHiveColumn(hc));
         Buffer dataCols = (Buffer)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(hiveTable.getCols()).asScala().map((hc) -> HiveClientImpl$.MODULE$.fromHiveColumn(hc));
         StructType x$1 = new StructType((StructField[])((IterableOnceOps)dataCols.$plus$plus(partCols)).toArray(scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
         TableIdentifier x$2 = table.copy$default$1();
         CatalogTableType x$3 = table.copy$default$2();
         CatalogStorageFormat x$4 = table.copy$default$3();
         Option x$5 = table.copy$default$5();
         Seq x$6 = table.copy$default$6();
         Option x$7 = table.copy$default$7();
         String x$8 = table.copy$default$8();
         long x$9 = table.copy$default$9();
         long x$10 = table.copy$default$10();
         String x$11 = table.copy$default$11();
         scala.collection.immutable.Map x$12 = table.copy$default$12();
         Option x$13 = table.copy$default$13();
         Option x$14 = table.copy$default$14();
         Option x$15 = table.copy$default$15();
         Option x$16 = table.copy$default$16();
         Seq x$17 = table.copy$default$17();
         boolean x$18 = table.copy$default$18();
         boolean x$19 = table.copy$default$19();
         scala.collection.immutable.Map x$20 = table.copy$default$20();
         Option x$21 = table.copy$default$21();
         return table.copy(x$2, x$3, x$4, x$1, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21);
      } else {
         return table;
      }
   }

   public String[] partitionNameToValues(final String name) {
      return (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])name.split("/")), (x0$1) -> {
         if (x0$1 != null) {
            Option var3 = MODULE$.PATTERN_FOR_KEY_EQ_VAL().unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((List)var3.get()).lengthCompare(2) == 0) {
               String v = (String)((LinearSeqOps)var3.get()).apply(1);
               return FileUtils.unescapePathName(v);
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$BUILTIN_HIVE_VERSION$1(final String x$1) {
      boolean var10000;
      label23: {
         String var1 = MODULE$.builtinHiveVersion();
         if (x$1 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$1.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$HIVE_METASTORE_VERSION$1(final String hiveVersionStr) {
      return MODULE$.isCompatibleHiveVersion(hiveVersionStr);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hiveMetastoreSharedPrefixes$1(final String x$2) {
      boolean var10000;
      label23: {
         String var1 = "";
         if (x$2 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$2.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hiveMetastoreBarrierPrefixes$1(final String x$3) {
      boolean var10000;
      label23: {
         String var1 = "";
         if (x$3 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (x$3.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$newClientForMetadata$2(final File x$4) {
      return x$4.getName().toLowerCase(Locale.ROOT).endsWith(".jar");
   }

   private final Seq addLocalHiveJars$1(final File file) {
      label20: {
         String var10000 = file.getName();
         String var2 = "*";
         if (var10000 == null) {
            if (var2 != null) {
               break label20;
            }
         } else if (!var10000.equals(var2)) {
            break label20;
         }

         File[] files = file.getParentFile().listFiles();
         if (files == null) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Hive jar path '", "' does not exist."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file.getPath())})))));
            return scala.collection.immutable.Nil..MODULE$;
         }

         return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])files), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$newClientForMetadata$2(x$4)))), (x$5) -> x$5.toURI().toURL(), scala.reflect.ClassTag..MODULE$.apply(URL.class))).toImmutableArraySeq();
      }

      URL var4 = file.toURI().toURL();
      return scala.collection.immutable.Nil..MODULE$.$colon$colon(var4);
   }

   private final void logInitWithPath$1(final Seq jars, final String hiveMetastoreVersion$1) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initializing HiveMetastoreConnection version "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " using paths: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HIVE_METASTORE_VERSION..MODULE$, hiveMetastoreVersion$1)})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, jars.mkString(", "))}))))));
   }

   private HiveUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
