package org.apache.spark.ui.scope;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.DeterministicLevel$;
import org.apache.spark.scheduler.StageInfo;
import org.apache.spark.storage.RDDInfo;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Ordering.Int.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

public final class RDDOperationGraph$ implements Logging, Serializable {
   public static final RDDOperationGraph$ MODULE$ = new RDDOperationGraph$();
   private static final String STAGE_CLUSTER_PREFIX;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      STAGE_CLUSTER_PREFIX = "stage_";
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

   public String STAGE_CLUSTER_PREFIX() {
      return STAGE_CLUSTER_PREFIX;
   }

   public RDDOperationGraph makeOperationGraph(final StageInfo stage, final int retainedNodes) {
      ListBuffer edges = new ListBuffer();
      HashMap nodes = new HashMap();
      HashMap clusters = new HashMap();
      String var10000 = this.STAGE_CLUSTER_PREFIX();
      String stageClusterId = var10000 + stage.stageId();
      int var15 = stage.stageId();
      String stageClusterName = "Stage " + var15 + (stage.attemptNumber() == 0 ? "" : " (attempt " + stage.attemptNumber() + ")");
      RDDOperationCluster rootCluster = new RDDOperationCluster(stageClusterId, false, stageClusterName);
      IntRef rootNodeCount = IntRef.create(0);
      HashSet addRDDIds = new HashSet();
      HashSet dropRDDIds = new HashSet();
      ((IterableOnceOps)stage.rddInfos().sortBy((x$7) -> BoxesRunTime.boxToInteger($anonfun$makeOperationGraph$1(x$7)), .MODULE$)).foreach((rdd) -> {
         $anonfun$makeOperationGraph$2(rootNodeCount, retainedNodes, addRDDIds, dropRDDIds, edges, nodes, rootCluster, clusters, rdd);
         return BoxedUnit.UNIT;
      });
      ListBuffer internalEdges = new ListBuffer();
      ListBuffer outgoingEdges = new ListBuffer();
      ListBuffer incomingEdges = new ListBuffer();
      edges.foreach((x0$1) -> {
         if (x0$1 != null) {
            boolean fromThisGraph = nodes.contains(BoxesRunTime.boxToInteger(x0$1.fromId()));
            boolean toThisGraph = nodes.contains(BoxesRunTime.boxToInteger(x0$1.toId()));
            Tuple2.mcZZ.sp var12 = new Tuple2.mcZZ.sp(fromThisGraph, toThisGraph);
            if (var12 != null) {
               boolean var13 = ((Tuple2)var12)._1$mcZ$sp();
               boolean var14 = ((Tuple2)var12)._2$mcZ$sp();
               if (var13 && var14) {
                  return internalEdges.$plus$eq(x0$1);
               }
            }

            if (var12 != null) {
               boolean var15 = ((Tuple2)var12)._1$mcZ$sp();
               boolean var16 = ((Tuple2)var12)._2$mcZ$sp();
               if (var15 && !var16) {
                  return outgoingEdges.$plus$eq(x0$1);
               }
            }

            if (var12 != null) {
               boolean var17 = ((Tuple2)var12)._1$mcZ$sp();
               boolean var18 = ((Tuple2)var12)._2$mcZ$sp();
               if (!var17 && var18) {
                  return incomingEdges.$plus$eq(x0$1);
               }
            }

            MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Found an orphan edge in stage "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stage.stageId())), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, x0$1)}))))));
            return BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      });
      return new RDDOperationGraph(internalEdges.toSeq(), outgoingEdges.toSeq(), incomingEdges.toSeq(), rootCluster);
   }

   public String makeDotFile(final RDDOperationGraph graph) {
      StringBuilder dotFile = new StringBuilder();
      dotFile.append("digraph G {\n");
      String indent = "  ";
      String var10000 = graph.rootCluster().id();
      String var10001 = this.STAGE_CLUSTER_PREFIX();
      String graphId = "graph_" + var10000.replaceAll(var10001, "");
      dotFile.append(indent).append("id=\"" + graphId + "\";\n");
      this.makeDotSubgraph(dotFile, graph.rootCluster(), indent, this.makeDotSubgraph$default$4());
      graph.edges().foreach((edge) -> {
         int var10001 = edge.fromId();
         return dotFile.append("  " + var10001 + "->" + edge.toId() + ";\n");
      });
      dotFile.append("}");
      String result = dotFile.toString();
      this.logDebug((Function0)(() -> result));
      return result;
   }

   private String makeDotNode(final RDDOperationNode node) {
      String isCached;
      String isBarrier;
      String var14;
      label53: {
         label57: {
            isCached = node.cached() ? " [Cached]" : "";
            isBarrier = node.barrier() ? " [Barrier]" : "";
            Enumeration.Value var6 = node.outputDeterministicLevel();
            Enumeration.Value var10000 = DeterministicLevel$.MODULE$.DETERMINATE();
            if (var10000 == null) {
               if (var6 == null) {
                  break label57;
               }
            } else if (var10000.equals(var6)) {
               break label57;
            }

            label58: {
               var10000 = DeterministicLevel$.MODULE$.INDETERMINATE();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label58;
                  }
               } else if (var10000.equals(var6)) {
                  break label58;
               }

               label38: {
                  var10000 = DeterministicLevel$.MODULE$.UNORDERED();
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label38;
                     }
                  } else if (var10000.equals(var6)) {
                     break label38;
                  }

                  var14 = "";
                  break label53;
               }

               var14 = " [Unordered]";
               break label53;
            }

            var14 = " [Indeterminate]";
            break label53;
         }

         var14 = "";
      }

      String outputDeterministicLevel = var14;
      String escapedCallsite = scala.xml.Utility..MODULE$.escape(node.callsite());
      var14 = node.name();
      String label = StringEscapeUtils.escapeJava(var14 + " [" + node.id() + "]" + isCached + isBarrier + outputDeterministicLevel + "<br>" + escapedCallsite);
      int var16 = node.id();
      return var16 + " [id=\"node_" + node.id() + "\" labelType=\"html\" label=\"" + label + "}\"]";
   }

   private void makeDotSubgraph(final StringBuilder subgraph, final RDDOperationCluster cluster, final String indent, final String prefix) {
      String clusterId = prefix + cluster.id();
      subgraph.append(indent).append("subgraph " + clusterId + " {\n").append(indent).append("  id=\"" + clusterId + "\";\n").append(indent).append("  isCluster=\"true\";\n").append(indent).append("  label=\"" + StringEscapeUtils.escapeJava(cluster.name()) + "\";\n");
      cluster.childNodes().foreach((node) -> subgraph.append(indent).append("  " + MODULE$.makeDotNode(node) + ";\n"));
      cluster.childClusters().foreach((cscope) -> {
         $anonfun$makeDotSubgraph$2(subgraph, indent, cscope);
         return BoxedUnit.UNIT;
      });
      subgraph.append(indent).append("}\n");
   }

   private String makeDotSubgraph$default$4() {
      return "graph_";
   }

   public RDDOperationGraph apply(final Seq edges, final Seq outgoingEdges, final Seq incomingEdges, final RDDOperationCluster rootCluster) {
      return new RDDOperationGraph(edges, outgoingEdges, incomingEdges, rootCluster);
   }

   public Option unapply(final RDDOperationGraph x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(x$0.edges(), x$0.outgoingEdges(), x$0.incomingEdges(), x$0.rootCluster())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RDDOperationGraph$.class);
   }

   // $FF: synthetic method
   public static final int $anonfun$makeOperationGraph$1(final RDDInfo x$7) {
      return x$7.id();
   }

   // $FF: synthetic method
   public static final RDDOperationEdge $anonfun$makeOperationGraph$5(final RDDInfo rdd$1, final int x$8) {
      return new RDDOperationEdge(x$8, rdd$1.id());
   }

   // $FF: synthetic method
   public static final void $anonfun$makeOperationGraph$11(final scala.collection.immutable.Seq pc) {
      if (pc.size() == 2) {
         RDDOperationCluster parentCluster = (RDDOperationCluster)pc.apply(0);
         RDDOperationCluster childCluster = (RDDOperationCluster)pc.apply(1);
         parentCluster.attachChildCluster(childCluster);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$makeOperationGraph$12(final RDDOperationCluster rootCluster$1, final RDDOperationCluster cluster) {
      if (!rootCluster$1.childClusters().contains(cluster)) {
         rootCluster$1.attachChildCluster(cluster);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$makeOperationGraph$13(final RDDOperationNode node$1, final RDDOperationCluster cluster) {
      cluster.attachChildNode(node$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$makeOperationGraph$2(final IntRef rootNodeCount$1, final int retainedNodes$1, final HashSet addRDDIds$1, final HashSet dropRDDIds$1, final ListBuffer edges$1, final HashMap nodes$1, final RDDOperationCluster rootCluster$1, final HashMap clusters$1, final RDDInfo rdd) {
      scala.collection.immutable.Seq parentIds = rdd.parentIds();
      boolean var10000;
      if (parentIds.isEmpty()) {
         ++rootNodeCount$1.elem;
         var10000 = rootNodeCount$1.elem <= retainedNodes$1;
      } else {
         var10000 = parentIds.exists((JFunction1.mcZI.sp)(id) -> addRDDIds$1.contains(BoxesRunTime.boxToInteger(id)) || !dropRDDIds$1.contains(BoxesRunTime.boxToInteger(id)));
      }

      boolean isAllowed = var10000;
      if (isAllowed) {
         addRDDIds$1.$plus$eq(BoxesRunTime.boxToInteger(rdd.id()));
         edges$1.$plus$plus$eq((IterableOnce)((IterableOps)parentIds.filter((JFunction1.mcZI.sp)(id) -> !dropRDDIds$1.contains(BoxesRunTime.boxToInteger(id)))).map((x$8) -> $anonfun$makeOperationGraph$5(rdd, BoxesRunTime.unboxToInt(x$8))));
      } else {
         dropRDDIds$1.$plus$eq(BoxesRunTime.boxToInteger(rdd.id()));
      }

      RDDOperationNode node = (RDDOperationNode)nodes$1.getOrElseUpdate(BoxesRunTime.boxToInteger(rdd.id()), () -> {
         boolean var2;
         RDDOperationNode var10000;
         int var10002;
         String var10003;
         label17: {
            label16: {
               var10000 = new RDDOperationNode;
               var10002 = rdd.id();
               var10003 = rdd.name();
               StorageLevel var10004 = rdd.storageLevel();
               StorageLevel var1 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10004 == null) {
                  if (var1 != null) {
                     break label16;
                  }
               } else if (!var10004.equals(var1)) {
                  break label16;
               }

               var2 = false;
               break label17;
            }

            var2 = true;
         }

         var10000.<init>(var10002, var10003, var2, rdd.isBarrier(), rdd.callSite(), rdd.outputDeterministicLevel());
         return var10000;
      });
      if (rdd.scope().isEmpty()) {
         if (isAllowed) {
            rootCluster$1.attachChildNode(node);
         }
      } else {
         scala.collection.immutable.Seq rddScopes = (scala.collection.immutable.Seq)rdd.scope().map((scope) -> scope.getAllScopes()).getOrElse(() -> (scala.collection.immutable.Seq)scala.package..MODULE$.Seq().empty());
         scala.collection.immutable.Seq rddClusters = (scala.collection.immutable.Seq)rddScopes.map((scope) -> {
            String clusterId = scope.id();
            String clusterName = scope.name().replaceAll("\\n", "\\\\n");
            return (RDDOperationCluster)clusters$1.getOrElseUpdate(clusterId, () -> new RDDOperationCluster(clusterId, false, clusterName));
         });
         rddClusters.sliding(2).foreach((pc) -> {
            $anonfun$makeOperationGraph$11(pc);
            return BoxedUnit.UNIT;
         });
         rddClusters.headOption().foreach((cluster) -> {
            $anonfun$makeOperationGraph$12(rootCluster$1, cluster);
            return BoxedUnit.UNIT;
         });
         if (isAllowed) {
            rddClusters.lastOption().foreach((cluster) -> {
               $anonfun$makeOperationGraph$13(node, cluster);
               return BoxedUnit.UNIT;
            });
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$makeDotSubgraph$2(final StringBuilder subgraph$1, final String indent$1, final RDDOperationCluster cscope) {
      MODULE$.makeDotSubgraph(subgraph$1, cscope, indent$1 + "  ", "cluster_");
   }

   private RDDOperationGraph$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
