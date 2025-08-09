package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.util.Loader;
import org.apache.spark.mllib.util.Loader$;
import org.json4s.Formats;
import org.json4s.JValue;
import org.json4s.DefaultFormats.;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class DecisionTreeModel$ implements Loader, Logging, Serializable {
   public static final DecisionTreeModel$ MODULE$ = new DecisionTreeModel$();
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

   public DecisionTreeModel load(final SparkContext sc, final String path) {
      Formats formats = .MODULE$;
      Tuple3 var7 = Loader$.MODULE$.loadMetadata(sc, path);
      if (var7 == null) {
         throw new MatchError(var7);
      } else {
         String loadedClassName = (String)var7._1();
         String version = (String)var7._2();
         JValue metadata = (JValue)var7._3();
         Tuple3 var6 = new Tuple3(loadedClassName, version, metadata);
         String loadedClassName = (String)var6._1();
         String version = (String)var6._2();
         JValue metadata = (JValue)var6._3();
         String algo = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "algo")), formats, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
         int numNodes = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "numNodes")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
         String classNameV1_0 = DecisionTreeModel.SaveLoadV1_0$.MODULE$.thisClassName();
         Tuple2 var17 = new Tuple2(loadedClassName, version);
         if (var17 != null) {
            String className = (String)var17._1();
            String var19 = (String)var17._2();
            if ("1.0".equals(var19)) {
               if (className == null) {
                  if (classNameV1_0 == null) {
                     return DecisionTreeModel.SaveLoadV1_0$.MODULE$.load(sc, path, algo, numNodes);
                  }
               } else if (className.equals(classNameV1_0)) {
                  return DecisionTreeModel.SaveLoadV1_0$.MODULE$.load(sc, path, algo, numNodes);
               }
            }
         }

         throw new Exception("DecisionTreeModel.load did not recognize model with (className, format version):(" + loadedClassName + ", " + version + ").  Supported:\n  (" + classNameV1_0 + ", 1.0)");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecisionTreeModel$.class);
   }

   private DecisionTreeModel$() {
   }
}
