package org.sparkproject.guava.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import org.sparkproject.guava.annotations.Beta;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Functions;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ImmutableMap;
import org.sparkproject.guava.collect.Maps;

@Immutable(
   containerOf = {"N"}
)
@ElementTypesAreNonnullByDefault
@Beta
public class ImmutableGraph extends ForwardingGraph {
   private final BaseGraph backingGraph;

   ImmutableGraph(BaseGraph backingGraph) {
      this.backingGraph = backingGraph;
   }

   public static ImmutableGraph copyOf(Graph graph) {
      return graph instanceof ImmutableGraph ? (ImmutableGraph)graph : new ImmutableGraph(new StandardValueGraph(GraphBuilder.from(graph), getNodeConnections(graph), (long)graph.edges().size()));
   }

   /** @deprecated */
   @Deprecated
   public static ImmutableGraph copyOf(ImmutableGraph graph) {
      return (ImmutableGraph)Preconditions.checkNotNull(graph);
   }

   public ElementOrder incidentEdgeOrder() {
      return ElementOrder.stable();
   }

   private static ImmutableMap getNodeConnections(Graph graph) {
      ImmutableMap.Builder<N, GraphConnections<N, GraphConstants.Presence>> nodeConnections = ImmutableMap.builder();

      for(Object node : graph.nodes()) {
         nodeConnections.put(node, connectionsOf(graph, node));
      }

      return nodeConnections.buildOrThrow();
   }

   private static GraphConnections connectionsOf(Graph graph, Object node) {
      Function<N, GraphConstants.Presence> edgeValueFn = Functions.constant(GraphConstants.Presence.EDGE_EXISTS);
      return (GraphConnections)(graph.isDirected() ? DirectedGraphConnections.ofImmutable(node, graph.incidentEdges(node), edgeValueFn) : UndirectedGraphConnections.ofImmutable(Maps.asMap(graph.adjacentNodes(node), edgeValueFn)));
   }

   BaseGraph delegate() {
      return this.backingGraph;
   }

   public static class Builder {
      private final MutableGraph mutableGraph;

      Builder(GraphBuilder graphBuilder) {
         this.mutableGraph = graphBuilder.copy().incidentEdgeOrder(ElementOrder.stable()).build();
      }

      @CanIgnoreReturnValue
      public Builder addNode(Object node) {
         this.mutableGraph.addNode(node);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putEdge(Object nodeU, Object nodeV) {
         this.mutableGraph.putEdge(nodeU, nodeV);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putEdge(EndpointPair endpoints) {
         this.mutableGraph.putEdge(endpoints);
         return this;
      }

      public ImmutableGraph build() {
         return ImmutableGraph.copyOf((Graph)this.mutableGraph);
      }
   }
}
