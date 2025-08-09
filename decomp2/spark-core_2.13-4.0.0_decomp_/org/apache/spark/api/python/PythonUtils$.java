package org.apache.spark.api.python;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkEnv$;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaSparkContext$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.Python$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.CollectionAccumulator;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.StringBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.sys.process.ProcessBuilder;
import scala.sys.process.ProcessLogger;

public final class PythonUtils$ implements Logging {
   public static final PythonUtils$ MODULE$ = new PythonUtils$();
   private static final String PY4J_ZIP_NAME;
   private static Option additionalTestingPath;
   private static final String defaultPythonExec;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      PY4J_ZIP_NAME = "py4j-0.10.9.9-src.zip";
      additionalTestingPath = .MODULE$;
      defaultPythonExec = (String)scala.sys.package..MODULE$.env().getOrElse("PYSPARK_DRIVER_PYTHON", () -> (String)scala.sys.package..MODULE$.env().getOrElse("PYSPARK_PYTHON", () -> "python3"));
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

   public String PY4J_ZIP_NAME() {
      return PY4J_ZIP_NAME;
   }

   public String sparkPythonPath() {
      ArrayBuffer pythonPath = new ArrayBuffer();
      scala.sys.package..MODULE$.env().get("SPARK_HOME").foreach((sparkHome) -> {
         pythonPath.$plus$eq((new scala.collection.immutable..colon.colon(sparkHome, new scala.collection.immutable..colon.colon("python", new scala.collection.immutable..colon.colon("lib", new scala.collection.immutable..colon.colon("pyspark.zip", scala.collection.immutable.Nil..MODULE$))))).mkString(File.separator));
         return (ArrayBuffer)pythonPath.$plus$eq((new scala.collection.immutable..colon.colon(sparkHome, new scala.collection.immutable..colon.colon("python", new scala.collection.immutable..colon.colon("lib", new scala.collection.immutable..colon.colon(MODULE$.PY4J_ZIP_NAME(), scala.collection.immutable.Nil..MODULE$))))).mkString(File.separator));
      });
      pythonPath.$plus$plus$eq(SparkContext$.MODULE$.jarOfObject(this));
      return pythonPath.mkString(File.pathSeparator);
   }

   public String mergePythonPaths(final Seq paths) {
      return ((IterableOnceOps)paths.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$mergePythonPaths$1(x$1)))).mkString(File.pathSeparator);
   }

   public JavaRDD generateRDDWithNull(final JavaSparkContext sc) {
      SparkContext qual$1 = JavaSparkContext$.MODULE$.toSparkContext(sc);
      List x$1 = new scala.collection.immutable..colon.colon("a", new scala.collection.immutable..colon.colon((Object)null, new scala.collection.immutable..colon.colon("b", scala.collection.immutable.Nil..MODULE$)));
      int x$2 = qual$1.parallelize$default$2();
      return JavaRDD$.MODULE$.fromRDD(qual$1.parallelize(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(String.class)), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public Seq toSeq(final java.util.List vs) {
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(vs).asScala().toSeq();
   }

   public List toList(final java.util.List vs) {
      return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(vs).asScala().toList();
   }

   public Object toArray(final java.util.List vs) {
      return vs.toArray();
   }

   public scala.collection.immutable.Map toScalaMap(final Map jm) {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(jm).asScala().toMap(scala..less.colon.less..MODULE$.refl());
   }

   public boolean isEncryptionEnabled(final JavaSparkContext sc) {
      return BoxesRunTime.unboxToBoolean(JavaSparkContext$.MODULE$.toSparkContext(sc).conf().get(package$.MODULE$.IO_ENCRYPTION_ENABLED()));
   }

   public long getBroadcastThreshold(final JavaSparkContext sc) {
      return BoxesRunTime.unboxToLong(JavaSparkContext$.MODULE$.toSparkContext(sc).conf().get(package$.MODULE$.BROADCAST_FOR_UDF_COMPRESSION_THRESHOLD()));
   }

   public long getPythonAuthSocketTimeout(final JavaSparkContext sc) {
      return BoxesRunTime.unboxToLong(JavaSparkContext$.MODULE$.toSparkContext(sc).conf().get(Python$.MODULE$.PYTHON_AUTH_SOCKET_TIMEOUT()));
   }

   public int getSparkBufferSize(final JavaSparkContext sc) {
      return BoxesRunTime.unboxToInt(JavaSparkContext$.MODULE$.toSparkContext(sc).conf().get(package$.MODULE$.BUFFER_SIZE()));
   }

   public void logPythonInfo(final String pythonExec) {
      if (BoxesRunTime.unboxToBoolean(SparkEnv$.MODULE$.get().conf().get(Python$.MODULE$.PYTHON_LOG_INFO()))) {
         Seq pythonVersionCMD = new scala.collection.immutable..colon.colon(pythonExec, new scala.collection.immutable..colon.colon("-VV", scala.collection.immutable.Nil..MODULE$));
         String pythonPath = this.mergePythonPaths(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{this.sparkPythonPath(), (String)scala.sys.package..MODULE$.env().getOrElse("PYTHONPATH", () -> "")})));
         scala.collection.immutable.Map environment = (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("PYTHONPATH"), pythonPath)})));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Python path ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, pythonPath)})))));
         ProcessBuilder processPythonVer = scala.sys.process.Process..MODULE$.apply(pythonVersionCMD, .MODULE$, environment.toSeq());
         Option output = runCommand$1(processPythonVer);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Python version: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_VERSION..MODULE$, output.getOrElse(() -> "Unable to determine"))})))));
         String pythonCode = scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n          |import pkg_resources\n          |\n          |installed_packages = pkg_resources.working_set\n          |installed_packages_list = sorted([\"%s:%s\" % (i.key, i.version)\n          |                                 for i in installed_packages])\n          |\n          |for package in installed_packages_list:\n          |    print(package)\n          |"));
         ProcessBuilder listPackagesCMD = scala.sys.process.Process..MODULE$.apply(new scala.collection.immutable..colon.colon(pythonExec, new scala.collection.immutable..colon.colon("-c", new scala.collection.immutable..colon.colon(pythonCode, scala.collection.immutable.Nil..MODULE$))));
         Option listOfPackages = runCommand$1(listPackagesCMD);
         listOfPackages.foreach((x) -> {
            $anonfun$logPythonInfo$7(x);
            return BoxedUnit.UNIT;
         });
      }
   }

   public Option additionalTestingPath() {
      return additionalTestingPath;
   }

   public void additionalTestingPath_$eq(final Option x$1) {
      additionalTestingPath = x$1;
   }

   public String defaultPythonExec() {
      return defaultPythonExec;
   }

   public SimplePythonFunction createPythonFunction(final byte[] command) {
      String var10000;
      if (Utils$.MODULE$.isTesting()) {
         scala.Predef..MODULE$.require(scala.sys.package..MODULE$.props().contains("spark.test.home") || scala.sys.package..MODULE$.env().contains("SPARK_HOME"), () -> "spark.test.home or SPARK_HOME is not set.");
         String sparkHome = (String)scala.sys.package..MODULE$.props().getOrElse("spark.test.home", () -> (String)scala.sys.package..MODULE$.env().apply("SPARK_HOME"));
         Path sourcePath = Paths.get(sparkHome, "python").toAbsolutePath();
         Path py4jPath = Paths.get(sparkHome, "python", "lib", this.PY4J_ZIP_NAME()).toAbsolutePath();
         String merged = this.mergePythonPaths(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{sourcePath.toString(), py4jPath.toString()})));
         var10000 = (String)this.additionalTestingPath().map((x$3) -> MODULE$.mergePythonPaths(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{x$3, merged})))).getOrElse(() -> merged);
      } else {
         var10000 = this.sparkPythonPath();
      }

      String sourcePython = var10000;
      String pythonPath = this.mergePythonPaths(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{sourcePython, (String)scala.sys.package..MODULE$.env().getOrElse("PYTHONPATH", () -> "")})));
      String pythonVer = scala.sys.process.Process..MODULE$.apply(new scala.collection.immutable..colon.colon(this.defaultPythonExec(), new scala.collection.immutable..colon.colon("-c", new scala.collection.immutable..colon.colon("import sys; print('%d.%d' % sys.version_info[:2])", scala.collection.immutable.Nil..MODULE$))), .MODULE$, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("PYTHONPATH"), pythonPath)}))).$bang$bang().trim();
      return new SimplePythonFunction(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(command).toImmutableArraySeq(), scala.jdk.CollectionConverters..MODULE$.MutableMapHasAsJava((scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("PYTHONPATH"), pythonPath)})))).asJava(), scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.package..MODULE$.List().empty()).asJava(), this.defaultPythonExec(), pythonVer, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(scala.package..MODULE$.List().empty()).asJava(), (CollectionAccumulator)null);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$mergePythonPaths$1(final String x$1) {
      boolean var10000;
      label23: {
         String var1 = "";
         if (x$1 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!x$1.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$logPythonInfo$1(final StringBuilder stdout$1, final String line) {
      stdout$1.append(line).append(" ");
   }

   // $FF: synthetic method
   public static final void $anonfun$logPythonInfo$2(final String x$2) {
   }

   private static final Option runCommand$1(final ProcessBuilder process) {
      Object var10000;
      try {
         StringBuilder stdout = new StringBuilder();
         ProcessLogger processLogger = scala.sys.process.ProcessLogger..MODULE$.apply((line) -> {
            $anonfun$logPythonInfo$1(stdout, line);
            return BoxedUnit.UNIT;
         }, (x$2) -> {
            $anonfun$logPythonInfo$2(x$2);
            return BoxedUnit.UNIT;
         });
         var10000 = process.run(processLogger).exitValue() == 0 ? new Some(stdout.toString().trim()) : .MODULE$;
      } catch (Throwable var3) {
         var10000 = .MODULE$;
      }

      return (Option)var10000;
   }

   private static final String formatOutput$1(final String output) {
      return output.replaceAll("\\s+", ", ");
   }

   // $FF: synthetic method
   public static final void $anonfun$logPythonInfo$7(final String x) {
      MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"List of Python packages :-"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_PACKAGES..MODULE$, formatOutput$1(x))}))))));
   }

   private PythonUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
