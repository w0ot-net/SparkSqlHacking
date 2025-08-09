package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
public abstract class AbstractValueGraph extends AbstractBaseGraph implements ValueGraph {
   public Graph asGraph() {
      return new AbstractGraph() {
         public Set nodes() {
            return AbstractValueGraph.this.nodes();
         }

         public Set edges() {
            return AbstractValueGraph.this.edges();
         }

         public boolean isDirected() {
            return AbstractValueGraph.this.isDirected();
         }

         public boolean allowsSelfLoops() {
            return AbstractValueGraph.this.allowsSelfLoops();
         }

         public ElementOrder nodeOrder() {
            return AbstractValueGraph.this.nodeOrder();
         }

         public ElementOrder incidentEdgeOrder() {
            return AbstractValueGraph.this.incidentEdgeOrder();
         }

         public Set adjacentNodes(Object node) {
            return AbstractValueGraph.this.adjacentNodes(node);
         }

         public Set predecessors(Object node) {
            return AbstractValueGraph.this.predecessors(node);
         }

         public Set successors(Object node) {
            return AbstractValueGraph.this.successors(node);
         }

         public int degree(Object node) {
            return AbstractValueGraph.this.degree(node);
         }

         public int inDegree(Object node) {
            return AbstractValueGraph.this.inDegree(node);
         }

         public int outDegree(Object node) {
            return AbstractValueGraph.this.outDegree(node);
         }
      };
   }

   public Optional edgeValue(Object nodeU, Object nodeV) {
      return Optional.ofNullable(this.edgeValueOrDefault(nodeU, nodeV, (Object)null));
   }

   public Optional edgeValue(EndpointPair endpoints) {
      return Optional.ofNullable(this.edgeValueOrDefault(endpoints, (Object)null));
   }

   public final boolean equals(@CheckForNull Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ValueGraph)) {
         return false;
      } else {
         ValueGraph<?, ?> other = (ValueGraph)obj;
         return this.isDirected() == other.isDirected() && this.nodes().equals(other.nodes()) && edgeValueMap(this).equals(edgeValueMap(other));
      }
   }

   public final int hashCode() {
      return edgeValueMap(this).hashCode();
   }

   public String toString() {
      return "isDirected: " + this.isDirected() + ", allowsSelfLoops: " + this.allowsSelfLoops() + ", nodes: " + this.nodes() + ", edges: " + edgeValueMap(this);
   }

   private static Map edgeValueMap(final ValueGraph graph) {
      return Maps.asMap((Set)graph.edges(), (edge) -> Objects.requireNonNull(graph.edgeValueOrDefault(edge.nodeU(), edge.nodeV(), (Object)null)));
   }
}
