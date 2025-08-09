package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.mutable.Map;
import scala.collection.mutable.Map.;
import scala.runtime.ModuleSerializationProxy;

@DeveloperApi
public final class UDTRegistration$ implements Serializable, Logging {
   public static final UDTRegistration$ MODULE$ = new UDTRegistration$();
   private static Map udtMap;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile boolean bitmap$0;

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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   private Map udtMap$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            udtMap = (Map).MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2("org.apache.spark.ml.linalg.Vector", "org.apache.spark.ml.linalg.VectorUDT"), new Tuple2("org.apache.spark.ml.linalg.DenseVector", "org.apache.spark.ml.linalg.VectorUDT"), new Tuple2("org.apache.spark.ml.linalg.SparseVector", "org.apache.spark.ml.linalg.VectorUDT"), new Tuple2("org.apache.spark.ml.linalg.Matrix", "org.apache.spark.ml.linalg.MatrixUDT"), new Tuple2("org.apache.spark.ml.linalg.DenseMatrix", "org.apache.spark.ml.linalg.MatrixUDT"), new Tuple2("org.apache.spark.ml.linalg.SparseMatrix", "org.apache.spark.ml.linalg.MatrixUDT")})));
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return udtMap;
   }

   private Map udtMap() {
      return !bitmap$0 ? this.udtMap$lzycompute() : udtMap;
   }

   public boolean exists(final String userClassName) {
      return this.udtMap().contains(userClassName);
   }

   public void register(final String userClass, final String udtClass) {
      if (this.udtMap().contains(userClass)) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot register UDT for ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, userClass)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"which is already registered."})))).log(scala.collection.immutable.Nil..MODULE$))));
      } else {
         this.udtMap().$plus$eq(new Tuple2(userClass, udtClass));
      }
   }

   public Option getUDTFor(final String userClass) {
      return this.udtMap().get(userClass).map((udtClassName) -> {
         if (org.apache.spark.util.SparkClassUtils..MODULE$.classIsLoadable(udtClassName)) {
            Class udtClass = org.apache.spark.util.SparkClassUtils..MODULE$.classForName(udtClassName, org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$2(), org.apache.spark.util.SparkClassUtils..MODULE$.classForName$default$3());
            if (UserDefinedType.class.isAssignableFrom(udtClass)) {
               return udtClass;
            } else {
               throw DataTypeErrors$.MODULE$.notUserDefinedTypeError(udtClass.getName(), userClass);
            }
         } else {
            throw DataTypeErrors$.MODULE$.cannotLoadUserDefinedTypeError(udtClassName, userClass);
         }
      });
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UDTRegistration$.class);
   }

   private UDTRegistration$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
