package org.apache.curator.shaded.com.google.common.graph;

import java.util.Collection;
import java.util.Objects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
final class StandardMutableNetwork extends StandardNetwork implements MutableNetwork {
   StandardMutableNetwork(NetworkBuilder builder) {
      super(builder);
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
   private NetworkConnections addNodeInternal(Object node) {
      NetworkConnections<N, E> connections = this.newConnections();
      Preconditions.checkState(this.nodeConnections.put(node, connections) == null);
      return connections;
   }

   @CanIgnoreReturnValue
   public boolean addEdge(Object nodeU, Object nodeV, Object edge) {
      Preconditions.checkNotNull(nodeU, "nodeU");
      Preconditions.checkNotNull(nodeV, "nodeV");
      Preconditions.checkNotNull(edge, "edge");
      if (this.containsEdge(edge)) {
         EndpointPair<N> existingIncidentNodes = this.incidentNodes(edge);
         EndpointPair<N> newIncidentNodes = EndpointPair.of((Network)this, nodeU, nodeV);
         Preconditions.checkArgument(existingIncidentNodes.equals(newIncidentNodes), "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.", edge, existingIncidentNodes, newIncidentNodes);
         return false;
      } else {
         NetworkConnections<N, E> connectionsU = (NetworkConnections)this.nodeConnections.get(nodeU);
         if (!this.allowsParallelEdges()) {
            Preconditions.checkArgument(connectionsU == null || !connectionsU.successors().contains(nodeV), "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.", nodeU, nodeV);
         }

         boolean isSelfLoop = nodeU.equals(nodeV);
         if (!this.allowsSelfLoops()) {
            Preconditions.checkArgument(!isSelfLoop, "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", nodeU);
         }

         if (connectionsU == null) {
            connectionsU = this.addNodeInternal(nodeU);
         }

         connectionsU.addOutEdge(edge, nodeV);
         NetworkConnections<N, E> connectionsV = (NetworkConnections)this.nodeConnections.get(nodeV);
         if (connectionsV == null) {
            connectionsV = this.addNodeInternal(nodeV);
         }

         connectionsV.addInEdge(edge, nodeU, isSelfLoop);
         this.edgeToReferenceNode.put(edge, nodeU);
         return true;
      }
   }

   @CanIgnoreReturnValue
   public boolean addEdge(EndpointPair endpoints, Object edge) {
      this.validateEndpoints(endpoints);
      return this.addEdge(endpoints.nodeU(), endpoints.nodeV(), edge);
   }

   @CanIgnoreReturnValue
   public boolean removeNode(Object node) {
      Preconditions.checkNotNull(node, "node");
      NetworkConnections<N, E> connections = (NetworkConnections)this.nodeConnections.get(node);
      if (connections == null) {
         return false;
      } else {
         for(Object edge : ImmutableList.copyOf((Collection)connections.incidentEdges())) {
            this.removeEdge(edge);
         }

         this.nodeConnections.remove(node);
         return true;
      }
   }

   @CanIgnoreReturnValue
   public boolean removeEdge(Object edge) {
      Preconditions.checkNotNull(edge, "edge");
      N nodeU = (N)this.edgeToReferenceNode.get(edge);
      if (nodeU == null) {
         return false;
      } else {
         NetworkConnections<N, E> connectionsU = (NetworkConnections)Objects.requireNonNull((NetworkConnections)this.nodeConnections.get(nodeU));
         N nodeV = (N)connectionsU.adjacentNode(edge);
         NetworkConnections<N, E> connectionsV = (NetworkConnections)Objects.requireNonNull((NetworkConnections)this.nodeConnections.get(nodeV));
         connectionsU.removeOutEdge(edge);
         connectionsV.removeInEdge(edge, this.allowsSelfLoops() && nodeU.equals(nodeV));
         this.edgeToReferenceNode.remove(edge);
         return true;
      }
   }

   private NetworkConnections newConnections() {
      return (NetworkConnections)(this.isDirected() ? (this.allowsParallelEdges() ? DirectedMultiNetworkConnections.of() : DirectedNetworkConnections.of()) : (this.allowsParallelEdges() ? UndirectedMultiNetworkConnections.of() : UndirectedNetworkConnections.of()));
   }
}
