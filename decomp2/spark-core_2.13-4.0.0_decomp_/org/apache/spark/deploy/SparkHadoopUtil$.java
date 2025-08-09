package org.apache.spark.deploy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.URL;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FSDataOutputStreamBuilder;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.package$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class SparkHadoopUtil$ implements Logging {
   public static final SparkHadoopUtil$ MODULE$ = new SparkHadoopUtil$();
   private static SparkHadoopUtil instance;
   private static Seq hiveConfKeys;
   private static final int UPDATE_INPUT_METRICS_INTERVAL_RECORDS;
   private static final String SPARK_HADOOP_CONF_FILE;
   private static final String SOURCE_HIVE_SITE;
   private static final String SOURCE_SPARK;
   private static final String SOURCE_SPARK_HADOOP;
   private static final String ENV_VAR_AWS_ENDPOINT_URL;
   private static final String ENV_VAR_AWS_ACCESS_KEY;
   private static final String ENV_VAR_AWS_SECRET_KEY;
   private static final String ENV_VAR_AWS_SESSION_TOKEN;
   private static final String SOURCE_SPARK_HIVE;
   private static final String SET_TO_DEFAULT_VALUES;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile byte bitmap$0;

   static {
      Logging.$init$(MODULE$);
      UPDATE_INPUT_METRICS_INTERVAL_RECORDS = 1000;
      SPARK_HADOOP_CONF_FILE = "__spark_hadoop_conf__.xml";
      SOURCE_HIVE_SITE = "Set by Spark from hive-site.xml";
      SOURCE_SPARK = "Set by Spark";
      SOURCE_SPARK_HADOOP = "Set by Spark from keys starting with 'spark.hadoop'";
      ENV_VAR_AWS_ENDPOINT_URL = "AWS_ENDPOINT_URL";
      ENV_VAR_AWS_ACCESS_KEY = "AWS_ACCESS_KEY_ID";
      ENV_VAR_AWS_SECRET_KEY = "AWS_SECRET_ACCESS_KEY";
      ENV_VAR_AWS_SESSION_TOKEN = "AWS_SESSION_TOKEN";
      SOURCE_SPARK_HIVE = "Set by Spark from keys starting with 'spark.hive'";
      SET_TO_DEFAULT_VALUES = "Set by Spark to default values";
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

   private SparkHadoopUtil instance$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            instance = new SparkHadoopUtil();
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return instance;
   }

   private SparkHadoopUtil instance() {
      return (byte)(bitmap$0 & 1) == 0 ? this.instance$lzycompute() : instance;
   }

   public int UPDATE_INPUT_METRICS_INTERVAL_RECORDS() {
      return UPDATE_INPUT_METRICS_INTERVAL_RECORDS;
   }

   public String SPARK_HADOOP_CONF_FILE() {
      return SPARK_HADOOP_CONF_FILE;
   }

   public String SOURCE_HIVE_SITE() {
      return SOURCE_HIVE_SITE;
   }

   public String SOURCE_SPARK() {
      return SOURCE_SPARK;
   }

   public String SOURCE_SPARK_HADOOP() {
      return SOURCE_SPARK_HADOOP;
   }

   public String ENV_VAR_AWS_ENDPOINT_URL() {
      return ENV_VAR_AWS_ENDPOINT_URL;
   }

   public String ENV_VAR_AWS_ACCESS_KEY() {
      return ENV_VAR_AWS_ACCESS_KEY;
   }

   public String ENV_VAR_AWS_SECRET_KEY() {
      return ENV_VAR_AWS_SECRET_KEY;
   }

   public String ENV_VAR_AWS_SESSION_TOKEN() {
      return ENV_VAR_AWS_SESSION_TOKEN;
   }

   public String SOURCE_SPARK_HIVE() {
      return SOURCE_SPARK_HIVE;
   }

   public String SET_TO_DEFAULT_VALUES() {
      return SET_TO_DEFAULT_VALUES;
   }

   public SparkHadoopUtil get() {
      return this.instance();
   }

   public Configuration newConfiguration(final SparkConf conf) {
      Configuration hadoopConf = new Configuration();
      this.org$apache$spark$deploy$SparkHadoopUtil$$appendS3AndSparkHadoopHiveConfigurations(conf, hadoopConf);
      return hadoopConf;
   }

   public void org$apache$spark$deploy$SparkHadoopUtil$$appendS3AndSparkHadoopHiveConfigurations(final SparkConf conf, final Configuration hadoopConf) {
      if (conf != null) {
         this.appendS3CredentialsFromEnvironment(hadoopConf, System.getenv(this.ENV_VAR_AWS_ENDPOINT_URL()), System.getenv(this.ENV_VAR_AWS_ACCESS_KEY()), System.getenv(this.ENV_VAR_AWS_SECRET_KEY()), System.getenv(this.ENV_VAR_AWS_SESSION_TOKEN()));
         this.appendHiveConfigs(hadoopConf);
         this.org$apache$spark$deploy$SparkHadoopUtil$$appendSparkHadoopConfigs(conf, hadoopConf);
         this.appendSparkHiveConfigs(conf, hadoopConf);
         String bufferSize = conf.get(package$.MODULE$.BUFFER_SIZE()).toString();
         hadoopConf.set("io.file.buffer.size", bufferSize, package$.MODULE$.BUFFER_SIZE().key());
      }
   }

   public void appendS3CredentialsFromEnvironment(final Configuration hadoopConf, final String endpointUrl, final String keyId, final String accessKey, final String sessionToken) {
      if (keyId != null && accessKey != null) {
         String var10000 = this.SOURCE_SPARK();
         String source = var10000 + " on " + InetAddress.getLocalHost().toString() + " from ";
         hadoopConf.set("fs.s3.awsAccessKeyId", keyId, source + this.ENV_VAR_AWS_ACCESS_KEY());
         hadoopConf.set("fs.s3n.awsAccessKeyId", keyId, source + this.ENV_VAR_AWS_ACCESS_KEY());
         hadoopConf.set("fs.s3a.access.key", keyId, source + this.ENV_VAR_AWS_ACCESS_KEY());
         hadoopConf.set("fs.s3.awsSecretAccessKey", accessKey, source + this.ENV_VAR_AWS_SECRET_KEY());
         hadoopConf.set("fs.s3n.awsSecretAccessKey", accessKey, source + this.ENV_VAR_AWS_SECRET_KEY());
         hadoopConf.set("fs.s3a.secret.key", accessKey, source + this.ENV_VAR_AWS_SECRET_KEY());
         if (endpointUrl != null) {
            hadoopConf.set("fs.s3a.endpoint", endpointUrl, source + this.ENV_VAR_AWS_ENDPOINT_URL());
         }

         if (sessionToken != null) {
            hadoopConf.set("fs.s3a.session.token", sessionToken, source + this.ENV_VAR_AWS_SESSION_TOKEN());
         }
      }
   }

   private Seq hiveConfKeys$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            URL configFile = org.apache.spark.util.Utils$.MODULE$.getContextOrSparkClassLoader().getResource("hive-site.xml");
            Object var10000;
            if (configFile != null) {
               Configuration conf = new Configuration(false);
               conf.addResource(configFile);
               var10000 = .MODULE$.IteratorHasAsScala(conf.iterator()).asScala().toSeq();
            } else {
               var10000 = scala.collection.immutable.Nil..MODULE$;
            }

            hiveConfKeys = (Seq)var10000;
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return hiveConfKeys;
   }

   private Seq hiveConfKeys() {
      return (byte)(bitmap$0 & 2) == 0 ? this.hiveConfKeys$lzycompute() : hiveConfKeys;
   }

   private void appendHiveConfigs(final Configuration hadoopConf) {
      this.hiveConfKeys().foreach((kv) -> {
         $anonfun$appendHiveConfigs$1(hadoopConf, kv);
         return BoxedUnit.UNIT;
      });
   }

   public void org$apache$spark$deploy$SparkHadoopUtil$$appendSparkHadoopConfigs(final SparkConf conf, final Configuration hadoopConf) {
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])conf.getAll()), (check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$appendSparkHadoopConfigs$4(check$ifrefutable$3))).withFilter((x$11) -> BoxesRunTime.boxToBoolean($anonfun$appendSparkHadoopConfigs$5(x$11))).foreach((x$12) -> {
         $anonfun$appendSparkHadoopConfigs$6(hadoopConf, x$12);
         return BoxedUnit.UNIT;
      });
      String setBySpark = this.SET_TO_DEFAULT_VALUES();
      if (conf.getOption("spark.hadoop.mapreduce.fileoutputcommitter.algorithm.version").isEmpty()) {
         hadoopConf.set("mapreduce.fileoutputcommitter.algorithm.version", "1", setBySpark);
      }

      if (conf.getOption("spark.hadoop.fs.s3a.downgrade.syncable.exceptions").isEmpty()) {
         hadoopConf.set("fs.s3a.downgrade.syncable.exceptions", "true", setBySpark);
      }
   }

   private void appendSparkHiveConfigs(final SparkConf conf, final Configuration hadoopConf) {
      scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])conf.getAll()), (check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$appendSparkHiveConfigs$4(check$ifrefutable$4))).withFilter((x$13) -> BoxesRunTime.boxToBoolean($anonfun$appendSparkHiveConfigs$5(x$13))).foreach((x$14) -> {
         $anonfun$appendSparkHiveConfigs$6(hadoopConf, x$14);
         return BoxedUnit.UNIT;
      });
   }

   public String propertySources(final Configuration hadoopConf, final String key) {
      String[] sources = hadoopConf.getPropertySources(key);
      return sources != null && scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])sources)) ? scala.Predef..MODULE$.wrapRefArray((Object[])sources).mkString(",") : "";
   }

   public FSDataOutputStream createFile(final FileSystem fs, final Path path, final boolean allowEC) {
      if (allowEC) {
         return fs.create(path);
      } else if (!fs.mkdirs(path.getParent())) {
         throw new IOException("Failed to create parents of " + path);
      } else {
         Path qualifiedPath = fs.makeQualified(path);
         FSDataOutputStreamBuilder builder = fs.createFile(qualifiedPath);
         if (builder instanceof DistributedFileSystem.HdfsDataOutputStreamBuilder) {
            DistributedFileSystem.HdfsDataOutputStreamBuilder var8 = (DistributedFileSystem.HdfsDataOutputStreamBuilder)builder;
            return var8.replicate().build();
         } else {
            return fs.create(path);
         }
      }
   }

   public boolean isFile(final FileSystem fs, final Path path) {
      boolean var10000;
      try {
         var10000 = fs.getFileStatus(path).isFile();
      } catch (FileNotFoundException var3) {
         var10000 = false;
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$appendHiveConfigs$1(final Configuration hadoopConf$1, final Map.Entry kv) {
      hadoopConf$1.set((String)kv.getKey(), (String)kv.getValue(), MODULE$.SOURCE_HIVE_SITE());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appendSparkHadoopConfigs$4(final Tuple2 check$ifrefutable$3) {
      return check$ifrefutable$3 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appendSparkHadoopConfigs$5(final Tuple2 x$11) {
      if (x$11 != null) {
         String key = (String)x$11._1();
         return key.startsWith("spark.hadoop.");
      } else {
         throw new MatchError(x$11);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$appendSparkHadoopConfigs$6(final Configuration hadoopConf$2, final Tuple2 x$12) {
      if (x$12 != null) {
         String key = (String)x$12._1();
         String value = (String)x$12._2();
         hadoopConf$2.set(key.substring("spark.hadoop.".length()), value, MODULE$.SOURCE_SPARK_HADOOP());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$12);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appendSparkHiveConfigs$4(final Tuple2 check$ifrefutable$4) {
      return check$ifrefutable$4 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appendSparkHiveConfigs$5(final Tuple2 x$13) {
      if (x$13 != null) {
         String key = (String)x$13._1();
         return key.startsWith("spark.hive.");
      } else {
         throw new MatchError(x$13);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$appendSparkHiveConfigs$6(final Configuration hadoopConf$3, final Tuple2 x$14) {
      if (x$14 != null) {
         String key = (String)x$14._1();
         String value = (String)x$14._2();
         hadoopConf$3.set(key.substring("spark.".length()), value, MODULE$.SOURCE_SPARK_HIVE());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$14);
      }
   }

   private SparkHadoopUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
