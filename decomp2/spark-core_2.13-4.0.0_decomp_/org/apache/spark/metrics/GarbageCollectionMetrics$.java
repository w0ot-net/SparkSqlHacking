package org.apache.spark.metrics;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Map;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.memory.MemoryManager;
import org.slf4j.Logger;
import scala.Function0;
import scala.Product;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.Buffer;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

public final class GarbageCollectionMetrics$ implements ExecutorMetricType, Logging, Product, Serializable {
   public static final GarbageCollectionMetrics$ MODULE$ = new GarbageCollectionMetrics$();
   private static Seq youngGenerationGarbageCollector;
   private static Seq oldGenerationGarbageCollector;
   private static Seq nonBuiltInCollectors;
   private static final Seq names;
   private static final Seq YOUNG_GENERATION_BUILTIN_GARBAGE_COLLECTORS;
   private static final Seq OLD_GENERATION_BUILTIN_GARBAGE_COLLECTORS;
   private static final String BUILTIN_CONCURRENT_GARBAGE_COLLECTOR;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile byte bitmap$0;

   static {
      Logging.$init$(MODULE$);
      Product.$init$(MODULE$);
      nonBuiltInCollectors = .MODULE$;
      names = new scala.collection.immutable..colon.colon("MinorGCCount", new scala.collection.immutable..colon.colon("MinorGCTime", new scala.collection.immutable..colon.colon("MajorGCCount", new scala.collection.immutable..colon.colon("MajorGCTime", new scala.collection.immutable..colon.colon("TotalGCTime", new scala.collection.immutable..colon.colon("ConcurrentGCCount", new scala.collection.immutable..colon.colon("ConcurrentGCTime", .MODULE$)))))));
      YOUNG_GENERATION_BUILTIN_GARBAGE_COLLECTORS = new scala.collection.immutable..colon.colon("Copy", new scala.collection.immutable..colon.colon("PS Scavenge", new scala.collection.immutable..colon.colon("ParNew", new scala.collection.immutable..colon.colon("G1 Young Generation", .MODULE$))));
      OLD_GENERATION_BUILTIN_GARBAGE_COLLECTORS = new scala.collection.immutable..colon.colon("MarkSweepCompact", new scala.collection.immutable..colon.colon("PS MarkSweep", new scala.collection.immutable..colon.colon("ConcurrentMarkSweep", new scala.collection.immutable..colon.colon("G1 Old Generation", .MODULE$))));
      BUILTIN_CONCURRENT_GARBAGE_COLLECTOR = "G1 Concurrent GC";
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   private Seq nonBuiltInCollectors() {
      return nonBuiltInCollectors;
   }

   private void nonBuiltInCollectors_$eq(final Seq x$1) {
      nonBuiltInCollectors = x$1;
   }

   public Seq names() {
      return names;
   }

   public Seq YOUNG_GENERATION_BUILTIN_GARBAGE_COLLECTORS() {
      return YOUNG_GENERATION_BUILTIN_GARBAGE_COLLECTORS;
   }

   public Seq OLD_GENERATION_BUILTIN_GARBAGE_COLLECTORS() {
      return OLD_GENERATION_BUILTIN_GARBAGE_COLLECTORS;
   }

   public String BUILTIN_CONCURRENT_GARBAGE_COLLECTOR() {
      return BUILTIN_CONCURRENT_GARBAGE_COLLECTOR;
   }

   private Seq youngGenerationGarbageCollector$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            youngGenerationGarbageCollector = (Seq)SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.EVENT_LOG_GC_METRICS_YOUNG_GENERATION_GARBAGE_COLLECTORS());
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return youngGenerationGarbageCollector;
   }

   private Seq youngGenerationGarbageCollector() {
      return (byte)(bitmap$0 & 1) == 0 ? this.youngGenerationGarbageCollector$lzycompute() : youngGenerationGarbageCollector;
   }

   private Seq oldGenerationGarbageCollector$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            oldGenerationGarbageCollector = (Seq)SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.EVENT_LOG_GC_METRICS_OLD_GENERATION_GARBAGE_COLLECTORS());
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return oldGenerationGarbageCollector;
   }

   private Seq oldGenerationGarbageCollector() {
      return (byte)(bitmap$0 & 2) == 0 ? this.oldGenerationGarbageCollector$lzycompute() : oldGenerationGarbageCollector;
   }

   public long[] getMetricValues(final MemoryManager memoryManager) {
      long[] gcMetrics = new long[this.names().length()];
      Buffer mxBeans = scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(ManagementFactory.getGarbageCollectorMXBeans()).asScala();
      gcMetrics[4] = BoxesRunTime.unboxToLong(((IterableOnceOps)mxBeans.map((x$1) -> BoxesRunTime.boxToLong($anonfun$getMetricValues$1(x$1)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      mxBeans.foreach((mxBean) -> {
         $anonfun$getMetricValues$2(gcMetrics, mxBean);
         return BoxedUnit.UNIT;
      });
      return gcMetrics;
   }

   public String productPrefix() {
      return "GarbageCollectionMetrics";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof GarbageCollectionMetrics$;
   }

   public int hashCode() {
      return 794585264;
   }

   public String toString() {
      return "GarbageCollectionMetrics";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(GarbageCollectionMetrics$.class);
   }

   // $FF: synthetic method
   public static final long $anonfun$getMetricValues$1(final GarbageCollectorMXBean x$1) {
      return x$1.getCollectionTime();
   }

   // $FF: synthetic method
   public static final void $anonfun$getMetricValues$2(final long[] gcMetrics$1, final GarbageCollectorMXBean mxBean) {
      if (MODULE$.youngGenerationGarbageCollector().contains(mxBean.getName())) {
         gcMetrics$1[0] = mxBean.getCollectionCount();
         gcMetrics$1[1] = mxBean.getCollectionTime();
      } else if (MODULE$.oldGenerationGarbageCollector().contains(mxBean.getName())) {
         gcMetrics$1[2] = mxBean.getCollectionCount();
         gcMetrics$1[3] = mxBean.getCollectionTime();
      } else if (MODULE$.BUILTIN_CONCURRENT_GARBAGE_COLLECTOR().equals(mxBean.getName())) {
         gcMetrics$1[5] = mxBean.getCollectionCount();
         gcMetrics$1[6] = mxBean.getCollectionTime();
      } else if (!MODULE$.nonBuiltInCollectors().contains(mxBean.getName())) {
         GarbageCollectionMetrics$ var10000 = MODULE$;
         String var2 = mxBean.getName();
         var10000.nonBuiltInCollectors_$eq((Seq)MODULE$.nonBuiltInCollectors().$plus$colon(var2));
         MDC youngGenerationGc = new MDC(org.apache.spark.internal.LogKeys.YOUNG_GENERATION_GC..MODULE$, package$.MODULE$.EVENT_LOG_GC_METRICS_YOUNG_GENERATION_GARBAGE_COLLECTORS().key());
         MDC oldGenerationGc = new MDC(org.apache.spark.internal.LogKeys.OLD_GENERATION_GC..MODULE$, package$.MODULE$.EVENT_LOG_GC_METRICS_OLD_GENERATION_GARBAGE_COLLECTORS().key());
         MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"To enable non-built-in garbage collector(s) "})))).log(.MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NON_BUILT_IN_CONNECTORS..MODULE$, MODULE$.nonBuiltInCollectors())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"users should configure it(them) to ", " or ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{youngGenerationGc, oldGenerationGc}))))));
      }
   }

   private GarbageCollectionMetrics$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
