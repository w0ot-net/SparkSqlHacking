package org.apache.spark.streaming.api.python;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.StreamingContext$;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.slf4j.Logger;
import py4j.Py4JException;
import scala.Function0;
import scala.Function2;
import scala.Some;
import scala.StringContext;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class PythonDStream$ implements Serializable {
   public static final PythonDStream$ MODULE$ = new PythonDStream$();

   public void registerSerializer(final PythonTransformFunctionSerializer ser) {
      PythonTransformFunctionSerializer$.MODULE$.register(ser);
   }

   public void callForeachRDD(final JavaDStream jdstream, final PythonTransformFunction pfunc) {
      TransformFunction func = new TransformFunction(pfunc);
      jdstream.dstream().foreachRDD((Function2)((rdd, time) -> {
         $anonfun$callForeachRDD$1(func, rdd, time);
         return BoxedUnit.UNIT;
      }));
   }

   public Queue toRDDQueue(final ArrayList rdds) {
      LinkedList queue = new LinkedList();
      .MODULE$.ListHasAsScala(rdds).asScala().foreach((x$1) -> BoxesRunTime.boxToBoolean($anonfun$toRDDQueue$1(queue, x$1)));
      return queue;
   }

   public void stopStreamingContextIfPythonProcessIsDead(final Throwable e) {
      if (e instanceof Py4JException && ("Cannot obtain a new communication channel".equals(e.getMessage()) || "Error while obtaining a new communication channel".equals(e.getMessage()))) {
         (new Logging(e) {
            private transient Logger org$apache$spark$internal$Logging$$log_;
            private final Throwable e$1;

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
               return this.org$apache$spark$internal$Logging$$log_;
            }

            public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
               this.org$apache$spark$internal$Logging$$log_ = x$1;
            }

            public void run() {
               this.logError((Function0)(() -> "Cannot connect to Python process. It's probably dead. Stopping StreamingContext."), this.e$1);
               StreamingContext$.MODULE$.getActive().foreach((x$7) -> {
                  $anonfun$run$2(x$7);
                  return BoxedUnit.UNIT;
               });
            }

            // $FF: synthetic method
            public static final void $anonfun$run$2(final StreamingContext x$7) {
               x$7.stop(false);
            }

            public {
               this.e$1 = e$1;
               Logging.$init$(this);
               this.setDaemon(true);
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }).start();
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PythonDStream$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$callForeachRDD$1(final TransformFunction func$1, final RDD rdd, final Time time) {
      func$1.apply(new Some(rdd), time);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toRDDQueue$1(final LinkedList queue$1, final JavaRDD x$1) {
      return queue$1.add(x$1);
   }

   private PythonDStream$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
