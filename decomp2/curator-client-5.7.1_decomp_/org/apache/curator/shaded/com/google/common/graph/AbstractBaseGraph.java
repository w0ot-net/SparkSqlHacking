package org.apache.curator.shaded.com.google.common.graph;

import java.util.AbstractSet;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSet;
import org.apache.curator.shaded.com.google.common.collect.Iterators;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.apache.curator.shaded.com.google.common.collect.UnmodifiableIterator;
import org.apache.curator.shaded.com.google.common.math.IntMath;
import org.apache.curator.shaded.com.google.common.primitives.Ints;

@ElementTypesAreNonnullByDefault
abstract class AbstractBaseGraph implements BaseGraph {
   protected long edgeCount() {
      long degreeSum = 0L;

      for(Object node : this.nodes()) {
         degreeSum += (long)this.degree(node);
      }

      Preconditions.checkState((degreeSum & 1L) == 0L);
      return degreeSum >>> 1;
   }

   public Set edges() {
      return new AbstractSet() {
         public UnmodifiableIterator iterator() {
            return EndpointPairIterator.of(AbstractBaseGraph.this);
         }

         public int size() {
            return Ints.saturatedCast(AbstractBaseGraph.this.edgeCount());
         }

         public boolean remove(@CheckForNull Object o) {
            throw new UnsupportedOperationException();
         }

         public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof EndpointPair)) {
               return false;
            } else {
               EndpointPair<?> endpointPair = (EndpointPair)obj;
               return AbstractBaseGraph.this.isOrderingCompatible(endpointPair) && AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU()) && AbstractBaseGraph.this.successors(endpointPair.nodeU()).contains(endpointPair.nodeV());
            }
         }
      };
   }

   public ElementOrder incidentEdgeOrder() {
      return ElementOrder.unordered();
   }

   public Set incidentEdges(Object node) {
      Preconditions.checkNotNull(node);
      Preconditions.checkArgument(this.nodes().contains(node), "Node %s is not an element of this graph.", node);
      return new IncidentEdgeSet(this, node) {
         public UnmodifiableIterator iterator() {
            return this.graph.isDirected() ? Iterators.unmodifiableIterator(Iterators.concat(Iterators.transform(this.graph.predecessors(this.node).iterator(), (predecessor) -> EndpointPair.ordered(predecessor, this.node)), Iterators.transform(Sets.difference(this.graph.successors(this.node), ImmutableSet.of(this.node)).iterator(), (successor) -> EndpointPair.ordered(this.node, successor)))) : Iterators.unmodifiableIterator(Iterators.transform(this.graph.adjacentNodes(this.node).iterator(), (adjacentNode) -> EndpointPair.unordered(this.node, adjacentNode)));
         }
      };
   }

   public int degree(Object node) {
      if (this.isDirected()) {
         return IntMath.saturatedAdd(this.predecessors(node).size(), this.successors(node).size());
      } else {
         Set<N> neighbors = this.adjacentNodes(node);
         int selfLoopCount = this.allowsSelfLoops() && neighbors.contains(node) ? 1 : 0;
         return IntMath.saturatedAdd(neighbors.size(), selfLoopCount);
      }
   }

   public int inDegree(Object node) {
      return this.isDirected() ? this.predecessors(node).size() : this.degree(node);
   }

   public int outDegree(Object node) {
      return this.isDirected() ? this.successors(node).size() : this.degree(node);
   }

   public boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
      Preconditions.checkNotNull(nodeU);
      Preconditions.checkNotNull(nodeV);
      return this.nodes().contains(nodeU) && this.successors(nodeU).contains(nodeV);
   }

   public boolean hasEdgeConnecting(EndpointPair endpoints) {
      Preconditions.checkNotNull(endpoints);
      if (!this.isOrderingCompatible(endpoints)) {
         return false;
      } else {
         N nodeU = (N)endpoints.nodeU();
         N nodeV = (N)endpoints.nodeV();
         return this.nodes().contains(nodeU) && this.successors(nodeU).contains(nodeV);
      }
   }

   protected final void validateEndpoints(EndpointPair endpoints) {
      Preconditions.checkNotNull(endpoints);
      Preconditions.checkArgument(this.isOrderingCompatible(endpoints), "Mismatch: endpoints' ordering is not compatible with directionality of the graph");
   }

   protected final boolean isOrderingCompatible(EndpointPair endpoints) {
      return endpoints.isOrdered() == this.isDirected();
   }
}
