package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
class StandardValueGraph extends AbstractValueGraph {
   private final boolean isDirected;
   private final boolean allowsSelfLoops;
   private final ElementOrder nodeOrder;
   final MapIteratorCache nodeConnections;
   long edgeCount;

   StandardValueGraph(AbstractGraphBuilder builder) {
      this(builder, builder.nodeOrder.createMap((Integer)builder.expectedNodeCount.or((int)10)), 0L);
   }

   StandardValueGraph(AbstractGraphBuilder builder, Map nodeConnections, long edgeCount) {
      this.isDirected = builder.directed;
      this.allowsSelfLoops = builder.allowsSelfLoops;
      this.nodeOrder = builder.nodeOrder.cast();
      this.nodeConnections = (MapIteratorCache)(nodeConnections instanceof TreeMap ? new MapRetrievalCache(nodeConnections) : new MapIteratorCache(nodeConnections));
      this.edgeCount = Graphs.checkNonNegative(edgeCount);
   }

   public Set nodes() {
      return this.nodeConnections.unmodifiableKeySet();
   }

   public boolean isDirected() {
      return this.isDirected;
   }

   public boolean allowsSelfLoops() {
      return this.allowsSelfLoops;
   }

   public ElementOrder nodeOrder() {
      return this.nodeOrder;
   }

   public Set adjacentNodes(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).adjacentNodes(), node);
   }

   public Set predecessors(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).predecessors(), node);
   }

   public Set successors(Object node) {
      return this.nodeInvalidatableSet(this.checkedConnections(node).successors(), node);
   }

   public Set incidentEdges(Object node) {
      final GraphConnections<N, V> connections = this.checkedConnections(node);
      IncidentEdgeSet<N> incident = new IncidentEdgeSet(this, node) {
         public Iterator iterator() {
            return connections.incidentEdgeIterator(this.node);
         }
      };
      return this.nodeInvalidatableSet(incident, node);
   }

   public boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
      return this.hasEdgeConnectingInternal(Preconditions.checkNotNull(nodeU), Preconditions.checkNotNull(nodeV));
   }

   public boolean hasEdgeConnecting(EndpointPair endpoints) {
      Preconditions.checkNotNull(endpoints);
      return this.isOrderingCompatible(endpoints) && this.hasEdgeConnectingInternal(endpoints.nodeU(), endpoints.nodeV());
   }

   @CheckForNull
   public Object edgeValueOrDefault(Object nodeU, Object nodeV, @CheckForNull Object defaultValue) {
      return this.edgeValueOrDefaultInternal(Preconditions.checkNotNull(nodeU), Preconditions.checkNotNull(nodeV), defaultValue);
   }

   @CheckForNull
   public Object edgeValueOrDefault(EndpointPair endpoints, @CheckForNull Object defaultValue) {
      this.validateEndpoints(endpoints);
      return this.edgeValueOrDefaultInternal(endpoints.nodeU(), endpoints.nodeV(), defaultValue);
   }

   protected long edgeCount() {
      return this.edgeCount;
   }

   private final GraphConnections checkedConnections(Object node) {
      GraphConnections<N, V> connections = (GraphConnections)this.nodeConnections.get(node);
      if (connections == null) {
         Preconditions.checkNotNull(node);
         throw new IllegalArgumentException("Node " + node + " is not an element of this graph.");
      } else {
         return connections;
      }
   }

   final boolean containsNode(@CheckForNull Object node) {
      return this.nodeConnections.containsKey(node);
   }

   private final boolean hasEdgeConnectingInternal(Object nodeU, Object nodeV) {
      GraphConnections<N, V> connectionsU = (GraphConnections)this.nodeConnections.get(nodeU);
      return connectionsU != null && connectionsU.successors().contains(nodeV);
   }

   @CheckForNull
   private final Object edgeValueOrDefaultInternal(Object nodeU, Object nodeV, @CheckForNull Object defaultValue) {
      GraphConnections<N, V> connectionsU = (GraphConnections)this.nodeConnections.get(nodeU);
      V value = (V)(connectionsU == null ? null : connectionsU.value(nodeV));
      return value == null ? defaultValue : value;
   }
}
