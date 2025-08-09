package org.apache.spark.graphx;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkContext;
import org.apache.spark.graphx.impl.EdgePartitionBuilder;
import org.apache.spark.graphx.impl.EdgePartitionBuilder$;
import org.apache.spark.graphx.impl.EdgePartitionBuilder$mcI$sp;
import org.apache.spark.graphx.impl.GraphImpl$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.Ordering.String.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class GraphLoader$ implements Logging {
   public static final GraphLoader$ MODULE$ = new GraphLoader$();
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

   public Graph edgeListFile(final SparkContext sc, final String path, final boolean canonicalOrientation, final int numEdgePartitions, final StorageLevel edgeStorageLevel, final StorageLevel vertexStorageLevel) {
      long startTimeNs = System.nanoTime();
      RDD var10000;
      if (numEdgePartitions > 0) {
         RDD qual$1 = sc.textFile(path, numEdgePartitions);
         boolean x$2 = qual$1.coalesce$default$2();
         Option x$3 = qual$1.coalesce$default$3();
         var10000 = qual$1.coalesce(numEdgePartitions, x$2, x$3, .MODULE$);
      } else {
         var10000 = sc.textFile(path, sc.textFile$default$2());
      }

      RDD lines = var10000;
      RDD edges = lines.mapPartitionsWithIndex((pid, iter) -> $anonfun$edgeListFile$1(canonicalOrientation, BoxesRunTime.unboxToInt(pid), iter), lines.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).persist(edgeStorageLevel).setName(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("GraphLoader.edgeListFile - edges (%s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{path})));
      edges.count();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"It took "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTimeNs)))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to load the edges"})))).log(scala.collection.immutable.Nil..MODULE$))));
      return GraphImpl$.MODULE$.fromEdgePartitions(edges, BoxesRunTime.boxToInteger(1), edgeStorageLevel, vertexStorageLevel, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Int());
   }

   public boolean edgeListFile$default$3() {
      return false;
   }

   public int edgeListFile$default$4() {
      return -1;
   }

   public StorageLevel edgeListFile$default$5() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY();
   }

   public StorageLevel edgeListFile$default$6() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY();
   }

   // $FF: synthetic method
   public static final void $anonfun$edgeListFile$2(final boolean canonicalOrientation$1, final EdgePartitionBuilder builder$1, final String line) {
      if (!line.isEmpty() && scala.collection.StringOps..MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(line), 0) != '#') {
         String[] lineArray = line.split("\\s+");
         if (lineArray.length < 2) {
            throw new IllegalArgumentException("Invalid line: " + line);
         } else {
            long srcId = scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(lineArray[0]));
            long dstId = scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(lineArray[1]));
            if (canonicalOrientation$1 && srcId > dstId) {
               builder$1.add$mcI$sp(dstId, srcId, 1);
            } else {
               builder$1.add$mcI$sp(srcId, dstId, 1);
            }
         }
      }
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$edgeListFile$1(final boolean canonicalOrientation$1, final int pid, final Iterator iter) {
      EdgePartitionBuilder builder = new EdgePartitionBuilder$mcI$sp(EdgePartitionBuilder$.MODULE$.$lessinit$greater$default$1(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Int());
      iter.foreach((line) -> {
         $anonfun$edgeListFile$2(canonicalOrientation$1, builder, line);
         return BoxedUnit.UNIT;
      });
      return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(pid), builder.toEdgePartition$mcI$sp())})));
   }

   private GraphLoader$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
