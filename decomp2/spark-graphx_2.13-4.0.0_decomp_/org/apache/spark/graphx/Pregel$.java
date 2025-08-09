package org.apache.spark.graphx;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.graphx.util.PeriodicGraphCheckpointer;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.util.PeriodicRDDCheckpointer;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

public final class Pregel$ implements Logging {
   public static final Pregel$ MODULE$ = new Pregel$();
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

   public Graph apply(final Graph graph, final Object initialMsg, final int maxIterations, final EdgeDirection activeDirection, final Function3 vprog, final Function1 sendMsg, final Function2 mergeMsg, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      .MODULE$.require(maxIterations > 0, () -> "Maximum number of iterations must be greater than 0, but got " + maxIterations);
      int checkpointInterval = graph.vertices().sparkContext().getReadOnlyConf().getInt("spark.graphx.pregel.checkpointInterval", -1);
      Graph g = graph.mapVertices((vid, vdata) -> $anonfun$apply$2(vprog, initialMsg, BoxesRunTime.unboxToLong(vid), vdata), evidence$1, scala..less.colon.less..MODULE$.refl());
      PeriodicGraphCheckpointer graphCheckpointer = new PeriodicGraphCheckpointer(checkpointInterval, graph.vertices().sparkContext());
      graphCheckpointer.update(g);
      VertexRDD messages = GraphXUtils$.MODULE$.mapReduceTriplets(g, sendMsg, mergeMsg, GraphXUtils$.MODULE$.mapReduceTriplets$default$4(), evidence$1, evidence$2, evidence$3);
      PeriodicRDDCheckpointer messageCheckpointer = new PeriodicRDDCheckpointer(checkpointInterval, graph.vertices().sparkContext());
      messageCheckpointer.update(messages);
      boolean isActiveMessagesNonEmpty = !messages.isEmpty();
      Graph prevG = null;

      for(IntRef i = IntRef.create(0); isActiveMessagesNonEmpty && i.elem < maxIterations; ++i.elem) {
         prevG = g;
         g = Graph$.MODULE$.graphToGraphOps(g, evidence$1, evidence$2).joinVertices(messages, vprog, evidence$3);
         graphCheckpointer.update(g);
         VertexRDD oldMessages = messages;
         messages = GraphXUtils$.MODULE$.mapReduceTriplets(g, sendMsg, mergeMsg, new Some(new Tuple2(messages, activeDirection)), evidence$1, evidence$2, evidence$3);
         messageCheckpointer.update(messages);
         isActiveMessagesNonEmpty = !messages.isEmpty();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Pregel finished iteration ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(i.elem))})))));
         oldMessages.unpersist(oldMessages.unpersist$default$1());
         boolean x$1 = prevG.unpersistVertices$default$1();
         prevG.unpersistVertices(x$1);
         EdgeRDD qual$2 = prevG.edges();
         boolean x$2 = qual$2.unpersist$default$1();
         qual$2.unpersist(x$2);
      }

      messageCheckpointer.unpersistDataSet();
      graphCheckpointer.deleteAllCheckpoints();
      messageCheckpointer.deleteAllCheckpoints();
      return g;
   }

   public int apply$default$3() {
      return Integer.MAX_VALUE;
   }

   public EdgeDirection apply$default$4() {
      return EdgeDirection$.MODULE$.Either();
   }

   // $FF: synthetic method
   public static final Object $anonfun$apply$2(final Function3 vprog$1, final Object initialMsg$1, final long vid, final Object vdata) {
      return vprog$1.apply(BoxesRunTime.boxToLong(vid), vdata, initialMsg$1);
   }

   private Pregel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
