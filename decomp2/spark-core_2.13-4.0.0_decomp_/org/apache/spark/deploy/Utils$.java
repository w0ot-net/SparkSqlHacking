package org.apache.spark.deploy;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ui.JettyUtils$;
import org.apache.spark.ui.WebUI;
import org.apache.spark.util.logging.RollingFileAppender$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple4;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class Utils$ implements Logging {
   public static final Utils$ MODULE$ = new Utils$();
   private static final int DEFAULT_BYTES;
   private static final Set SUPPORTED_LOG_TYPES;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      DEFAULT_BYTES = 102400;
      SUPPORTED_LOG_TYPES = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"stderr", "stdout", "out"})));
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

   public int DEFAULT_BYTES() {
      return DEFAULT_BYTES;
   }

   public Set SUPPORTED_LOG_TYPES() {
      return SUPPORTED_LOG_TYPES;
   }

   public void addRenderLogHandler(final WebUI page, final SparkConf conf) {
      page.attachHandler(JettyUtils$.MODULE$.createServletHandler("/log", JettyUtils$.MODULE$.textResponderToServlet((request) -> MODULE$.renderLog(request, conf)), conf, JettyUtils$.MODULE$.createServletHandler$default$4()));
   }

   private String renderLog(final HttpServletRequest request, final SparkConf conf) {
      String logDir = (String)scala.sys.package..MODULE$.env().getOrElse("SPARK_LOG_DIR", () -> "logs/");
      String logType = request.getParameter("logType");
      Option offset = scala.Option..MODULE$.apply(request.getParameter("offset")).map((x$1) -> BoxesRunTime.boxToLong($anonfun$renderLog$2(x$1)));
      int byteLength = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter("byteLength")).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$renderLog$3(x$2))).getOrElse((JFunction0.mcI.sp)() -> MODULE$.DEFAULT_BYTES()));
      Tuple4 var9 = this.getLog(conf, logDir, logType, offset, byteLength);
      if (var9 != null) {
         String logText = (String)var9._1();
         long startByte = BoxesRunTime.unboxToLong(var9._2());
         long endByte = BoxesRunTime.unboxToLong(var9._3());
         long logLength = BoxesRunTime.unboxToLong(var9._4());
         Tuple4 var8 = new Tuple4(logText, BoxesRunTime.boxToLong(startByte), BoxesRunTime.boxToLong(endByte), BoxesRunTime.boxToLong(logLength));
         String logText = (String)var8._1();
         long startByte = BoxesRunTime.unboxToLong(var8._2());
         long endByte = BoxesRunTime.unboxToLong(var8._3());
         long logLength = BoxesRunTime.unboxToLong(var8._4());
         String pre = "==== Bytes " + startByte + "-" + endByte + " of " + logLength + " of " + logDir + logType + " ====\n";
         return pre + logText;
      } else {
         throw new MatchError(var9);
      }
   }

   public Tuple4 getLog(final SparkConf conf, final String logDirectory, final String logType, final Option offsetOption, final int byteLength) {
      if (!this.SUPPORTED_LOG_TYPES().contains(logType)) {
         return new Tuple4("Error: Log type must be one of " + this.SUPPORTED_LOG_TYPES().mkString(", "), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L));
      } else {
         Tuple4 var10000;
         try {
            String var22;
            if (logType.equals("out")) {
               URI normalizedUri = (new File(logDirectory)).toURI().normalize();
               File normalizedLogDir = new File(normalizedUri.getPath());
               var22 = (String)scala.collection.ArrayOps..MODULE$.headOption$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])normalizedLogDir.listFiles()), (x$4) -> x$4.getName(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$getLog$2(x$5))))).getOrElse(() -> logType);
            } else {
               var22 = logType;
            }

            String fileName = var22;
            Seq files = RollingFileAppender$.MODULE$.getSortedRolledOverFiles(logDirectory, fileName);
            this.logDebug((Function0)(() -> "Sorted log files of type " + logType + " in " + logDirectory + ":\n" + files.mkString("\n")));
            Seq fileLengths = (Seq)files.map((x$6) -> BoxesRunTime.boxToLong($anonfun$getLog$5(conf, x$6)));
            long totalLength = BoxesRunTime.unboxToLong(fileLengths.sum(scala.math.Numeric.LongIsIntegral..MODULE$));
            long offset = BoxesRunTime.unboxToLong(offsetOption.getOrElse((JFunction0.mcJ.sp)() -> totalLength - (long)byteLength));
            long startIndex = offset < 0L ? 0L : (offset > totalLength ? totalLength : offset);
            long endIndex = scala.math.package..MODULE$.min(startIndex + (long)byteLength, totalLength);
            this.logDebug((Function0)(() -> "Getting log from " + startIndex + " to " + endIndex));
            String logText = org.apache.spark.util.Utils$.MODULE$.offsetBytes(files, fileLengths, startIndex, endIndex);
            this.logDebug((Function0)(() -> "Got log of length " + logText.length() + " bytes"));
            var10000 = new Tuple4(logText, BoxesRunTime.boxToLong(startIndex), BoxesRunTime.boxToLong(endIndex), BoxesRunTime.boxToLong(totalLength));
         } catch (Exception var21) {
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error getting ", " logs from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LOG_TYPE..MODULE$, logType)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logDirectory)}))))), var21);
            var10000 = new Tuple4("Error getting logs due to exception: " + var21.getMessage(), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L), BoxesRunTime.boxToLong(0L));
         }

         return var10000;
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$renderLog$2(final String x$1) {
      return scala.collection.StringOps..MODULE$.toLong$extension(.MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final int $anonfun$renderLog$3(final String x$2) {
      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLog$2(final String x$5) {
      return x$5.endsWith(".out");
   }

   // $FF: synthetic method
   public static final long $anonfun$getLog$5(final SparkConf conf$2, final File x$6) {
      return org.apache.spark.util.Utils$.MODULE$.getFileLength(x$6, conf$2);
   }

   private Utils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
