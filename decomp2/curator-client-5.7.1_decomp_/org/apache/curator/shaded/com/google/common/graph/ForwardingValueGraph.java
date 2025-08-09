package org.apache.curator.shaded.com.google.common.graph;

import java.util.Optional;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class ForwardingValueGraph extends AbstractValueGraph {
   abstract ValueGraph delegate();

   public Set nodes() {
      return this.delegate().nodes();
   }

   protected long edgeCount() {
      return (long)this.delegate().edges().size();
   }

   public boolean isDirected() {
      return this.delegate().isDirected();
   }

   public boolean allowsSelfLoops() {
      return this.delegate().allowsSelfLoops();
   }

   public ElementOrder nodeOrder() {
      return this.delegate().nodeOrder();
   }

   public ElementOrder incidentEdgeOrder() {
      return this.delegate().incidentEdgeOrder();
   }

   public Set adjacentNodes(Object node) {
      return this.delegate().adjacentNodes(node);
   }

   public Set predecessors(Object node) {
      return this.delegate().predecessors(node);
   }

   public Set successors(Object node) {
      return this.delegate().successors(node);
   }

   public int degree(Object node) {
      return this.delegate().degree(node);
   }

   public int inDegree(Object node) {
      return this.delegate().inDegree(node);
   }

   public int outDegree(Object node) {
      return this.delegate().outDegree(node);
   }

   public boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
      return this.delegate().hasEdgeConnecting(nodeU, nodeV);
   }

   public boolean hasEdgeConnecting(EndpointPair endpoints) {
      return this.delegate().hasEdgeConnecting(endpoints);
   }

   public Optional edgeValue(Object nodeU, Object nodeV) {
      return this.delegate().edgeValue(nodeU, nodeV);
   }

   public Optional edgeValue(EndpointPair endpoints) {
      return this.delegate().edgeValue(endpoints);
   }

   @CheckForNull
   public Object edgeValueOrDefault(Object nodeU, Object nodeV, @CheckForNull Object defaultValue) {
      return this.delegate().edgeValueOrDefault(nodeU, nodeV, defaultValue);
   }

   @CheckForNull
   public Object edgeValueOrDefault(EndpointPair endpoints, @CheckForNull Object defaultValue) {
      return this.delegate().edgeValueOrDefault(endpoints, defaultValue);
   }
}
