package org.apache.curator.shaded.com.google.common.graph;

import java.util.Objects;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
final class StandardMutableValueGraph extends StandardValueGraph implements MutableValueGraph {
   private final ElementOrder incidentEdgeOrder;

   StandardMutableValueGraph(AbstractGraphBuilder builder) {
      super(builder);
      this.incidentEdgeOrder = builder.incidentEdgeOrder.cast();
   }

   public ElementOrder incidentEdgeOrder() {
      return this.incidentEdgeOrder;
   }

   @CanIgnoreReturnValue
   public boolean addNode(Object node) {
      Preconditions.checkNotNull(node, "node");
      if (this.containsNode(node)) {
         return false;
      } else {
         this.addNodeInternal(node);
         return true;
      }
   }

   @CanIgnoreReturnValue
   private GraphConnections addNodeInternal(Object node) {
      GraphConnections<N, V> connections = this.newConnections();
      Preconditions.checkState(this.nodeConnections.put(node, connections) == null);
      return connections;
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object putEdgeValue(Object nodeU, Object nodeV, Object value) {
      Preconditions.checkNotNull(nodeU, "nodeU");
      Preconditions.checkNotNull(nodeV, "nodeV");
      Preconditions.checkNotNull(value, "value");
      if (!this.allowsSelfLoops()) {
         Preconditions.checkArgument(!nodeU.equals(nodeV), "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", nodeU);
      }

      GraphConnections<N, V> connectionsU = (GraphConnections)this.nodeConnections.get(nodeU);
      if (connectionsU == null) {
         connectionsU = this.addNodeInternal(nodeU);
      }

      V previousValue = (V)connectionsU.addSuccessor(nodeV, value);
      GraphConnections<N, V> connectionsV = (GraphConnections)this.nodeConnections.get(nodeV);
      if (connectionsV == null) {
         connectionsV = this.addNodeInternal(nodeV);
      }

      connectionsV.addPredecessor(nodeU, value);
      if (previousValue == null) {
         Graphs.checkPositive(++this.edgeCount);
      }

      return previousValue;
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object putEdgeValue(EndpointPair endpoints, Object value) {
      this.validateEndpoints(endpoints);
      return this.putEdgeValue(endpoints.nodeU(), endpoints.nodeV(), value);
   }

   @CanIgnoreReturnValue
   public boolean removeNode(Object node) {
      Preconditions.checkNotNull(node, "node");
      GraphConnections<N, V> connections = (GraphConnections)this.nodeConnections.get(node);
      if (connections == null) {
         return false;
      } else {
         if (this.allowsSelfLoops() && connections.removeSuccessor(node) != null) {
            connections.removePredecessor(node);
            --this.edgeCount;
         }

         for(Object successor : connections.successors()) {
            ((GraphConnections)Objects.requireNonNull((GraphConnections)this.nodeConnections.getWithoutCaching(successor))).removePredecessor(node);
            --this.edgeCount;
         }

         if (this.isDirected()) {
            for(Object predecessor : connections.predecessors()) {
               Preconditions.checkState(((GraphConnections)Objects.requireNonNull((GraphConnections)this.nodeConnections.getWithoutCaching(predecessor))).removeSuccessor(node) != null);
               --this.edgeCount;
            }
         }

         this.nodeConnections.remove(node);
         Graphs.checkNonNegative(this.edgeCount);
         return true;
      }
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object removeEdge(Object nodeU, Object nodeV) {
      Preconditions.checkNotNull(nodeU, "nodeU");
      Preconditions.checkNotNull(nodeV, "nodeV");
      GraphConnections<N, V> connectionsU = (GraphConnections)this.nodeConnections.get(nodeU);
      GraphConnections<N, V> connectionsV = (GraphConnections)this.nodeConnections.get(nodeV);
      if (connectionsU != null && connectionsV != null) {
         V previousValue = (V)connectionsU.removeSuccessor(nodeV);
         if (previousValue != null) {
            connectionsV.removePredecessor(nodeU);
            Graphs.checkNonNegative(--this.edgeCount);
         }

         return previousValue;
      } else {
         return null;
      }
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object removeEdge(EndpointPair endpoints) {
      this.validateEndpoints(endpoints);
      return this.removeEdge(endpoints.nodeU(), endpoints.nodeV());
   }

   private GraphConnections newConnections() {
      return (GraphConnections)(this.isDirected() ? DirectedGraphConnections.of(this.incidentEdgeOrder) : UndirectedGraphConnections.of(this.incidentEdgeOrder));
   }
}
