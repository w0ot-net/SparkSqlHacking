package org.sparkproject.guava.graph;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ImmutableSet;

@ElementTypesAreNonnullByDefault
class StandardNetwork extends AbstractNetwork {
   private final boolean isDirected;
   private final boolean allowsParallelEdges;
   private final boolean allowsSelfLoops;
   private final ElementOrder nodeOrder;
   private final ElementOrder edgeOrder;
   final MapIteratorCache nodeConnections;
   final MapIteratorCache edgeToReferenceNode;

   StandardNetwork(NetworkBuilder builder) {
      this(builder, builder.nodeOrder.createMap((Integer)builder.expectedNodeCount.or((int)10)), builder.edgeOrder.createMap((Integer)builder.expectedEdgeCount.or((int)20)));
   }

   StandardNetwork(NetworkBuilder builder, Map nodeConnections, Map edgeToReferenceNode) {
      this.isDirected = builder.directed;
      this.allowsParallelEdges = builder.allowsParallelEdges;
      this.allowsSelfLoops = builder.allowsSelfLoops;
      this.nodeOrder = builder.nodeOrder.cast();
      this.edgeOrder = builder.edgeOrder.cast();
      this.nodeConnections = (MapIteratorCache)(nodeConnections instanceof TreeMap ? new MapRetrievalCache(nodeConnections) : new MapIteratorCache(nodeConnections));
      this.edgeToReferenceNode = new MapIteratorCache(edgeToReferenceNode);
   }

   public Set nodes() {
      return this.nodeConnections.unmodifiableKeySet();
   }

   public Set edges() {
      return this.edgeToReferenceNode.unmodifiableKeySet();
   }

   public boolean isDirected() {
      return this.isDirected;
   }

   public boolean allowsParallelEdges() {
      return this.allowsParallelEdges;
   }

   public boolean allowsSelfLoops() {
      return this.allowsSelfLoops;
   }

   public ElementOrder nodeOrder() {
      return this.nodeOrder;
   }

   public ElementOrder edgeOrder() {
      return this.edgeOrder;
   }

   public Set incidentEdges(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).incidentEdges(), node);
   }

   public EndpointPair incidentNodes(Object edge) {
      N nodeU = (N)this.checkedReferenceNode(edge);
      N nodeV = (N)((NetworkConnections)Objects.requireNonNull((NetworkConnections)this.nodeConnections.get(nodeU))).adjacentNode(edge);
      return EndpointPair.of((Network)this, nodeU, nodeV);
   }

   public Set adjacentNodes(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).adjacentNodes(), node);
   }

   public Set edgesConnecting(Object nodeU, Object nodeV) {
      NetworkConnections<N, E> connectionsU = this.checkedConnections(nodeU);
      if (!this.allowsSelfLoops && nodeU == nodeV) {
         return ImmutableSet.of();
      } else {
         Preconditions.checkArgument(this.containsNode(nodeV), "Node %s is not an element of this graph.", nodeV);
         return this.nodePairInvalidatableSet(connectionsU.edgesConnecting(nodeV), nodeU, nodeV);
      }
   }

   public Set inEdges(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).inEdges(), node);
   }

   public Set outEdges(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).outEdges(), node);
   }

   public Set predecessors(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).predecessors(), node);
   }

   public Set successors(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).successors(), node);
   }

   final NetworkConnections checkedConnections(Object node) {
      NetworkConnections<N, E> connections = (NetworkConnections)this.nodeConnections.get(node);
      if (connections == null) {
         Preconditions.checkNotNull(node);
         throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", node));
      } else {
         return connections;
      }
   }

   final Object checkedReferenceNode(Object edge) {
      N referenceNode = (N)this.edgeToReferenceNode.get(edge);
      if (referenceNode == null) {
         Preconditions.checkNotNull(edge);
         throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", edge));
      } else {
         return referenceNode;
      }
   }

   final boolean containsNode(Object node) {
      return this.nodeConnections.containsKey(node);
   }

   final boolean containsEdge(Object edge) {
      return this.edgeToReferenceNode.containsKey(edge);
   }
}
