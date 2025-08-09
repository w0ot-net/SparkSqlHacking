package org.sparkproject.guava.graph;

import java.util.AbstractSet;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class IncidentEdgeSet extends AbstractSet {
   final Object node;
   final BaseGraph graph;

   IncidentEdgeSet(BaseGraph graph, Object node) {
      this.graph = graph;
      this.node = node;
   }

   public boolean remove(@CheckForNull Object o) {
      throw new UnsupportedOperationException();
   }

   public int size() {
      return this.graph.isDirected() ? this.graph.inDegree(this.node) + this.graph.outDegree(this.node) - (this.graph.successors(this.node).contains(this.node) ? 1 : 0) : this.graph.adjacentNodes(this.node).size();
   }

   public boolean contains(@CheckForNull Object obj) {
      if (!(obj instanceof EndpointPair)) {
         return false;
      } else {
         EndpointPair<?> endpointPair = (EndpointPair)obj;
         if (this.graph.isDirected()) {
            if (!endpointPair.isOrdered()) {
               return false;
            } else {
               Object source = endpointPair.source();
               Object target = endpointPair.target();
               return this.node.equals(source) && this.graph.successors(this.node).contains(target) || this.node.equals(target) && this.graph.predecessors(this.node).contains(source);
            }
         } else if (endpointPair.isOrdered()) {
            return false;
         } else {
            Set<N> adjacent = this.graph.adjacentNodes(this.node);
            Object nodeU = endpointPair.nodeU();
            Object nodeV = endpointPair.nodeV();
            return this.node.equals(nodeV) && adjacent.contains(nodeU) || this.node.equals(nodeU) && adjacent.contains(nodeV);
         }
      }
   }
}
