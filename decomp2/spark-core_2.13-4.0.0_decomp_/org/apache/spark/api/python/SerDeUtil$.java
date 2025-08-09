package org.apache.spark.api.python;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Map;
import net.razorvine.pickle.Pickler;
import net.razorvine.pickle.Unpickler;
import org.apache.spark.SparkException;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Array;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Try;

public final class SerDeUtil$ implements Logging {
   public static final SerDeUtil$ MODULE$ = new SerDeUtil$();
   private static boolean initialized;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      initialized = false;
      MODULE$.initialize();
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

   private boolean initialized() {
      return initialized;
   }

   private void initialized_$eq(final boolean x$1) {
      initialized = x$1;
   }

   public synchronized void initialize() {
      if (!this.initialized()) {
         Unpickler.registerConstructor("__builtin__", "bytearray", new SerDeUtil.ByteArrayConstructor());
         Unpickler.registerConstructor("builtins", "bytearray", new SerDeUtil.ByteArrayConstructor());
         Unpickler.registerConstructor("__builtin__", "bytes", new SerDeUtil.ByteArrayConstructor());
         Unpickler.registerConstructor("_codecs", "encode", new SerDeUtil.ByteArrayConstructor());
         this.initialized_$eq(true);
      }
   }

   public JavaRDD toJavaArray(final JavaRDD jrdd) {
      return jrdd.rdd().map((x0$1) -> {
         if (x0$1 instanceof ArrayList var3) {
            return var3.toArray();
         } else if (x0$1.getClass().isArray()) {
            return .MODULE$.toArray$extension(scala.Predef..MODULE$.genericArrayOps(x0$1), scala.reflect.ClassTag..MODULE$.apply(Object.class));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Object.class)).toJavaRDD();
   }

   public JavaRDD javaToPython(final JavaRDD jRDD) {
      return JavaRDD$.MODULE$.fromRDD(jRDD.rdd().mapPartitions((iter) -> new SerDeUtil.AutoBatchedPickler(iter), jRDD.rdd().mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE))), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
   }

   public JavaRDD pythonToJava(final JavaRDD pyRDD, final boolean batched) {
      return pyRDD.rdd().mapPartitions((iter) -> {
         MODULE$.initialize();
         Unpickler unpickle = new Unpickler();
         return iter.flatMap((row) -> {
            Object obj = unpickle.loads(row);
            if (batched) {
               if (obj instanceof Object[]) {
                  Object[] var6 = obj;
                  return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(var6).toImmutableArraySeq();
               } else {
                  return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala((ArrayList)obj).asScala();
               }
            } else {
               return new scala.collection.immutable..colon.colon(obj, scala.collection.immutable.Nil..MODULE$);
            }
         });
      }, pyRDD.rdd().mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.Any()).toJavaRDD();
   }

   private Tuple2 checkPickle(final Tuple2 t) {
      Pickler pickle = new Pickler(true, false);
      Try kt = scala.util.Try..MODULE$.apply(() -> pickle.dumps(t._1()));
      Try vt = scala.util.Try..MODULE$.apply(() -> pickle.dumps(t._2()));
      Tuple2 var6 = new Tuple2(kt, vt);
      if (var6 != null) {
         Try var7 = (Try)var6._1();
         Try var8 = (Try)var6._2();
         if (var7 instanceof Failure) {
            Failure var9 = (Failure)var7;
            Throwable kf = var9.exception();
            if (var8 instanceof Failure) {
               Failure var11 = (Failure)var8;
               Throwable vf = var11.exception();
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n               |Failed to pickle Java object as key:\n               |", ", falling back\n               |to 'toString'. Error: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, t._1().getClass().getSimpleName()), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, kf.getMessage())}))).stripMargin()));
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n               |Failed to pickle Java object as value:\n               |", ", falling back\n               |to 'toString'. Error: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, t._2().getClass().getSimpleName()), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, vf.getMessage())}))).stripMargin()));
               return new Tuple2.mcZZ.sp(true, true);
            }
         }
      }

      if (var6 != null) {
         Try var13 = (Try)var6._1();
         if (var13 instanceof Failure) {
            Failure var14 = (Failure)var13;
            Throwable kf = var14.exception();
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n               |Failed to pickle Java object as key:\n               |", ", falling back\n               |to 'toString'. Error: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, t._1().getClass().getSimpleName()), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, kf.getMessage())}))).stripMargin()));
            return new Tuple2.mcZZ.sp(true, false);
         }
      }

      if (var6 != null) {
         Try var16 = (Try)var6._2();
         if (var16 instanceof Failure) {
            Failure var17 = (Failure)var16;
            Throwable vf = var17.exception();
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n               |Failed to pickle Java object as value:\n               |", ", falling back\n               |to 'toString'. Error: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, t._2().getClass().getSimpleName()), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, vf.getMessage())}))).stripMargin()));
            return new Tuple2.mcZZ.sp(false, true);
         }
      }

      return new Tuple2.mcZZ.sp(false, false);
   }

   public RDD pairRDDToPython(final RDD rdd, final int batchSize) {
      Object var10000;
      label43: {
         Tuple2[] var7 = (Tuple2[])rdd.take(1);
         if (var7 != null) {
            Object var8 = scala.Array..MODULE$.unapplySeq(var7);
            if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var8) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var8)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var8), 0) == 0) {
               var10000 = new Tuple2.mcZZ.sp(false, false);
               break label43;
            }
         }

         if (var7 == null) {
            throw new MatchError(var7);
         }

         Object var9 = scala.Array..MODULE$.unapplySeq(var7);
         if (scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var9) || new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var9)) == null || scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var9), 1) != 0) {
            throw new MatchError(var7);
         }

         Tuple2 first = (Tuple2)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var9), 0);
         var10000 = this.checkPickle(first);
      }

      Object var6 = var10000;
      if (var6 != null) {
         boolean keyFailed = ((Tuple2)var6)._1$mcZ$sp();
         boolean valueFailed = ((Tuple2)var6)._2$mcZ$sp();
         Tuple2.mcZZ.sp var5 = new Tuple2.mcZZ.sp(keyFailed, valueFailed);
         boolean keyFailed = ((Tuple2)var5)._1$mcZ$sp();
         boolean valueFailed = ((Tuple2)var5)._2$mcZ$sp();
         return rdd.mapPartitions((iter) -> {
            Iterator cleaned = iter.map((x0$1) -> {
               if (x0$1 != null) {
                  Object k = x0$1._1();
                  Object v = x0$1._2();
                  Object key = keyFailed ? k.toString() : k;
                  Object value = valueFailed ? v.toString() : v;
                  return new Object[]{key, value};
               } else {
                  throw new MatchError(x0$1);
               }
            });
            if (batchSize == 0) {
               return new SerDeUtil.AutoBatchedPickler(cleaned);
            } else {
               Pickler pickle = new Pickler(true, false);
               return cleaned.grouped(batchSize).map((batched) -> pickle.dumps(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(batched).asJava()));
            }
         }, rdd.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
      } else {
         throw new MatchError(var6);
      }
   }

   public RDD pythonToPairRDD(final RDD pyRDD, final boolean batched) {
      RDD rdd = this.pythonToJava(JavaRDD$.MODULE$.fromRDD(pyRDD, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE))), batched).rdd();
      Object[] var5 = rdd.take(1);
      if (var5 != null) {
         Object var6 = scala.Array..MODULE$.unapplySeq(var5);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var6) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var6)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var6), 1) == 0) {
            Object obj = scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var6), 0);
            if (isPair$1(obj)) {
               BoxedUnit var11 = BoxedUnit.UNIT;
               return rdd.map((objx) -> new Tuple2(.MODULE$.head$extension(scala.Predef..MODULE$.genericArrayOps(objx)), .MODULE$.last$extension(scala.Predef..MODULE$.genericArrayOps(objx))), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            }
         }
      }

      if (var5 != null) {
         Object var8 = scala.Array..MODULE$.unapplySeq(var5);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var8) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var8)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var8), 0) == 0) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return rdd.map((objx) -> new Tuple2(.MODULE$.head$extension(scala.Predef..MODULE$.genericArrayOps(objx)), .MODULE$.last$extension(scala.Predef..MODULE$.genericArrayOps(objx))), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
         }
      }

      if (var5 != null) {
         Object var9 = scala.Array..MODULE$.unapplySeq(var5);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var9) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var9)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var9), 1) == 0) {
            Object other = scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var9), 0);
            throw new SparkException("RDD element of type " + other.getClass().getName() + " cannot be used");
         }
      }

      throw new MatchError(var5);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$pythonToPairRDD$1(final Class x$2) {
      return !x$2.isPrimitive();
   }

   private static final boolean isPair$1(final Object obj) {
      return scala.Option..MODULE$.apply(obj.getClass().getComponentType()).exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$pythonToPairRDD$1(x$2))) && scala.runtime.ScalaRunTime..MODULE$.array_length(obj) == 2;
   }

   private SerDeUtil$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
