package org.sparkproject.guava.graph;

@ElementTypesAreNonnullByDefault
final class StandardMutableGraph extends ForwardingGraph implements MutableGraph {
   private final MutableValueGraph backingValueGraph;

   StandardMutableGraph(AbstractGraphBuilder builder) {
      this.backingValueGraph = new StandardMutableValueGraph(builder);
   }

   BaseGraph delegate() {
      return this.backingValueGraph;
   }

   public boolean addNode(Object node) {
      return this.backingValueGraph.addNode(node);
   }

   public boolean putEdge(Object nodeU, Object nodeV) {
      return this.backingValueGraph.putEdgeValue(nodeU, nodeV, GraphConstants.Presence.EDGE_EXISTS) == null;
   }

   public boolean putEdge(EndpointPair endpoints) {
      this.validateEndpoints(endpoints);
      return this.putEdge(endpoints.nodeU(), endpoints.nodeV());
   }

   public boolean removeNode(Object node) {
      return this.backingValueGraph.removeNode(node);
   }

   public boolean removeEdge(Object nodeU, Object nodeV) {
      return this.backingValueGraph.removeEdge(nodeU, nodeV) != null;
   }

   public boolean removeEdge(EndpointPair endpoints) {
      this.validateEndpoints(endpoints);
      return this.removeEdge(endpoints.nodeU(), endpoints.nodeV());
   }
}
