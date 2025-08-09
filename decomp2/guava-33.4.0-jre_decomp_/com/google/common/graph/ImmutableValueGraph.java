package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.util.Objects;

@Immutable(
   containerOf = {"N", "V"}
)
@ElementTypesAreNonnullByDefault
@Beta
public final class ImmutableValueGraph extends StandardValueGraph {
   private ImmutableValueGraph(ValueGraph graph) {
      super(ValueGraphBuilder.from(graph), getNodeConnections(graph), (long)graph.edges().size());
   }

   public static ImmutableValueGraph copyOf(ValueGraph graph) {
      return graph instanceof ImmutableValueGraph ? (ImmutableValueGraph)graph : new ImmutableValueGraph(graph);
   }

   /** @deprecated */
   @Deprecated
   public static ImmutableValueGraph copyOf(ImmutableValueGraph graph) {
      return (ImmutableValueGraph)Preconditions.checkNotNull(graph);
   }

   public ElementOrder incidentEdgeOrder() {
      return ElementOrder.stable();
   }

   public ImmutableGraph asGraph() {
      return new ImmutableGraph(this);
   }

   private static ImmutableMap getNodeConnections(ValueGraph graph) {
      ImmutableMap.Builder<N, GraphConnections<N, V>> nodeConnections = ImmutableMap.builder();

      for(Object node : graph.nodes()) {
         nodeConnections.put(node, connectionsOf(graph, node));
      }

      return nodeConnections.buildOrThrow();
   }

   private static GraphConnections connectionsOf(ValueGraph graph, Object node) {
      Function<N, V> successorNodeToValueFn = (successorNode) -> Objects.requireNonNull(graph.edgeValueOrDefault(node, successorNode, (Object)null));
      return (GraphConnections)(graph.isDirected() ? DirectedGraphConnections.ofImmutable(node, graph.incidentEdges(node), successorNodeToValueFn) : UndirectedGraphConnections.ofImmutable(Maps.asMap(graph.adjacentNodes(node), successorNodeToValueFn)));
   }

   public static class Builder {
      private final MutableValueGraph mutableValueGraph;

      Builder(ValueGraphBuilder graphBuilder) {
         this.mutableValueGraph = graphBuilder.copy().incidentEdgeOrder(ElementOrder.stable()).build();
      }

      @CanIgnoreReturnValue
      public Builder addNode(Object node) {
         this.mutableValueGraph.addNode(node);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putEdgeValue(Object nodeU, Object nodeV, Object value) {
         this.mutableValueGraph.putEdgeValue(nodeU, nodeV, value);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder putEdgeValue(EndpointPair endpoints, Object value) {
         this.mutableValueGraph.putEdgeValue(endpoints, value);
         return this;
      }

      public ImmutableValueGraph build() {
         return ImmutableValueGraph.copyOf((ValueGraph)this.mutableValueGraph);
      }
   }
}
