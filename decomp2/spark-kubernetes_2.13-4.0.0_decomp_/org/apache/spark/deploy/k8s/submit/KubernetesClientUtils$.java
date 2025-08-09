package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ConfigMapFluent;
import io.fabric8.kubernetes.api.model.KeyToPath;
import java.io.File;
import java.io.StringWriter;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.MalformedInputException;
import java.util.Map;
import java.util.Properties;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
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
import scala.collection.SeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.io.Source;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;

@Unstable
@DeveloperApi
public final class KubernetesClientUtils$ implements Logging {
   public static final KubernetesClientUtils$ MODULE$ = new KubernetesClientUtils$();
   private static final String configMapNameExecutor;
   private static final String configMapNameDriver;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      KubernetesUtils$ var10001 = KubernetesUtils$.MODULE$;
      configMapNameExecutor = MODULE$.configMapName("spark-exec-" + var10001.uniqueID(KubernetesUtils$.MODULE$.uniqueID$default$1()));
      var10001 = KubernetesUtils$.MODULE$;
      configMapNameDriver = MODULE$.configMapName("spark-drv-" + var10001.uniqueID(KubernetesUtils$.MODULE$.uniqueID$default$1()));
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

   public String configMapName(final String prefix) {
      String suffix = "-conf-map";
      String var10000 = .MODULE$.take$extension(scala.Predef..MODULE$.augmentString(prefix), Config$.MODULE$.KUBERNETES_DNS_SUBDOMAIN_NAME_MAX_LENGTH() - suffix.length());
      return var10000 + suffix;
   }

   public String configMapNameExecutor() {
      return configMapNameExecutor;
   }

   public String configMapNameDriver() {
      return configMapNameDriver;
   }

   private String buildStringFromPropertiesMap(final String configMapName, final scala.collection.immutable.Map propertiesMap) {
      Properties properties = new Properties();
      propertiesMap.foreach((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return properties.setProperty(k, v);
         } else {
            throw new MatchError(x0$1);
         }
      });
      StringWriter propertiesWriter = new StringWriter();
      properties.store(propertiesWriter, "Java properties built from Kubernetes config map with name: " + configMapName);
      return propertiesWriter.toString();
   }

   public synchronized scala.collection.immutable.Map buildSparkConfDirFilesMap(final String configMapName, final SparkConf sparkConf, final scala.collection.immutable.Map resolvedPropertiesMap) {
      scala.collection.immutable.Map loadedConfFilesMap = this.loadSparkConfDirFiles(sparkConf);
      if (resolvedPropertiesMap.nonEmpty()) {
         String resolvedProperties = this.buildStringFromPropertiesMap(configMapName, resolvedPropertiesMap);
         return (scala.collection.immutable.Map)loadedConfFilesMap.$plus$plus((IterableOnce)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.SPARK_CONF_FILE_NAME()), resolvedProperties)}))));
      } else {
         return loadedConfFilesMap;
      }
   }

   public Seq buildKeyToPathObjects(final scala.collection.immutable.Map confFilesMap) {
      return (Seq)((IterableOnceOps)confFilesMap.map((x0$1) -> {
         if (x0$1 != null) {
            String fileName = (String)x0$1._1();
            if (fileName != null && x0$1._2() != null) {
               int filePermissionMode = 420;
               return new KeyToPath(fileName, scala.Predef..MODULE$.int2Integer(filePermissionMode), fileName);
            }
         }

         throw new MatchError(x0$1);
      })).toList().sortBy((x) -> x.getKey(), scala.math.Ordering.String..MODULE$);
   }

   public ConfigMap buildConfigMap(final String configMapName, final scala.collection.immutable.Map confFileMap, final scala.collection.immutable.Map withLabels) {
      String configMapNameSpace = (String)confFileMap.getOrElse(Config$.MODULE$.KUBERNETES_NAMESPACE().key(), () -> Config$.MODULE$.KUBERNETES_NAMESPACE().defaultValueString());
      return ((ConfigMapBuilder)((ConfigMapFluent)((ConfigMapFluent.MetadataNested)(new ConfigMapBuilder()).withNewMetadata().withName(configMapName).withNamespace(configMapNameSpace).withLabels(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(withLabels).asJava())).endMetadata()).withImmutable(scala.Predef..MODULE$.boolean2Boolean(true)).addToData(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(confFileMap).asJava())).build();
   }

   public scala.collection.immutable.Map buildConfigMap$default$3() {
      return (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
   }

   private Seq orderFilesBySize(final Seq confFiles) {
      Seq fileToFileSizePairs = (Seq)confFiles.map((f) -> new Tuple2(f, BoxesRunTime.boxToLong((long)f.getName().length() + f.length())));
      return (Seq)((IterableOps)((SeqOps)fileToFileSizePairs.sortBy((f) -> (File)f._1(), scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms()))).sortBy((f) -> BoxesRunTime.boxToLong($anonfun$orderFilesBySize$3(f)), scala.math.Ordering.Long..MODULE$)).map((x$1) -> (File)x$1._1());
   }

   public scala.collection.immutable.Map loadSparkConfDirFiles(final SparkConf conf) {
      Option confDir = scala.Option..MODULE$.apply(conf.getenv(Constants$.MODULE$.ENV_SPARK_CONF_DIR())).orElse(() -> conf.getOption("spark.home").map((dir) -> dir + "/conf"));
      long maxSize = BoxesRunTime.unboxToLong(conf.get(Config$.MODULE$.CONFIG_MAP_MAXSIZE()));
      if (confDir.isDefined()) {
         Seq confFiles = this.listConfFiles((String)confDir.get(), maxSize);
         Seq orderedConfFiles = this.orderFilesBySize(confFiles);
         LongRef truncatedMapSize = LongRef.create(0L);
         HashMap truncatedMap = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         HashSet skippedFiles = (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         ObjectRef source = ObjectRef.create(scala.io.Source..MODULE$.fromString(""));
         orderedConfFiles.foreach((file) -> {
            Object var10000;
            try {
               source.elem = scala.io.Source..MODULE$.fromFile(file, scala.io.Codec..MODULE$.UTF8());
               Tuple2 var9 = scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(file.getName()), ((Source)source.elem).mkString());
               if (var9 == null) {
                  throw new MatchError(var9);
               }

               String fileName = (String)var9._1();
               String fileContent = (String)var9._2();
               Tuple2 var8 = new Tuple2(fileName, fileContent);
               String fileName = (String)var8._1();
               String fileContentx = (String)var8._2();
               if (truncatedMapSize.elem + (long)fileName.length() + (long)fileContentx.length() < maxSize) {
                  truncatedMap.put(fileName, fileContentx);
                  truncatedMapSize.elem += (long)(fileName.length() + fileContentx.length());
                  var10000 = BoxedUnit.UNIT;
               } else {
                  var10000 = BoxesRunTime.boxToBoolean(skippedFiles.add(fileName));
               }
            } catch (MalformedInputException var18) {
               MODULE$.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unable to read a non UTF-8 encoded file "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Skipping..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file.getAbsolutePath())}))))), var18);
               var10000 = BoxedUnit.UNIT;
            } finally {
               ((Source)source.elem).close();
            }

            return var10000;
         });
         if (truncatedMap.nonEmpty()) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Spark configuration files loaded from ", " : "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, confDir)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATHS..MODULE$, truncatedMap.keys().mkString(","))}))))));
         }

         if (skippedFiles.nonEmpty()) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped conf file(s) ", ", due to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATHS..MODULE$, skippedFiles.mkString(","))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"size constraint. Please see, config: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"`", "` for more details."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, Config$.MODULE$.CONFIG_MAP_MAXSIZE().key())}))))));
         }

         return truncatedMap.toMap(scala..less.colon.less..MODULE$.refl());
      } else {
         return scala.Predef..MODULE$.Map().empty();
      }
   }

   private Seq listConfFiles(final String confDir, final long maxSize) {
      Function1 fileFilter = (f) -> BoxesRunTime.boxToBoolean($anonfun$listConfFiles$1(maxSize, f));
      File dir = new File(confDir);
      Seq confFiles = (Seq)(dir.isDirectory() ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])dir.listFiles()), (x) -> BoxesRunTime.boxToBoolean($anonfun$listConfFiles$2(fileFilter, x)))).toImmutableArraySeq() : scala.collection.immutable.Nil..MODULE$);
      return confFiles;
   }

   // $FF: synthetic method
   public static final long $anonfun$orderFilesBySize$3(final Tuple2 f) {
      return f._2$mcJ$sp();
   }

   private static final boolean testIfTooLargeOrBinary$1(final File f, final long maxSize$2) {
      return f.length() + (long)f.getName().length() > maxSize$2 || f.getName().matches(".*\\.(gz|zip|jar|tar)");
   }

   private static final boolean testIfSparkConfOrTemplates$1(final File f) {
      return f.getName().matches(".*\\.template") || f.getName().matches("spark.*(conf|properties)");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listConfFiles$1(final long maxSize$2, final File f) {
      return f.isFile() && !testIfTooLargeOrBinary$1(f, maxSize$2) && !testIfSparkConfOrTemplates$1(f);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listConfFiles$2(final Function1 fileFilter$1, final File x) {
      return BoxesRunTime.unboxToBoolean(fileFilter$1.apply(x));
   }

   private KubernetesClientUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
