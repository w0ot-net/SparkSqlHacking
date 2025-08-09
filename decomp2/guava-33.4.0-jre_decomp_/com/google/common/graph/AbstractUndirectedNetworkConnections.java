package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class AbstractUndirectedNetworkConnections implements NetworkConnections {
   final Map incidentEdgeMap;

   AbstractUndirectedNetworkConnections(Map incidentEdgeMap) {
      this.incidentEdgeMap = (Map)Preconditions.checkNotNull(incidentEdgeMap);
   }

   public Set predecessors() {
      return this.adjacentNodes();
   }

   public Set successors() {
      return this.adjacentNodes();
   }

   public Set incidentEdges() {
      return Collections.unmodifiableSet(this.incidentEdgeMap.keySet());
   }

   public Set inEdges() {
      return this.incidentEdges();
   }

   public Set outEdges() {
      return this.incidentEdges();
   }

   public Object adjacentNode(Object edge) {
      return Objects.requireNonNull(this.incidentEdgeMap.get(edge));
   }

   @CheckForNull
   public Object removeInEdge(Object edge, boolean isSelfLoop) {
      return !isSelfLoop ? this.removeOutEdge(edge) : null;
   }

   public Object removeOutEdge(Object edge) {
      N previousNode = (N)this.incidentEdgeMap.remove(edge);
      return Objects.requireNonNull(previousNode);
   }

   public void addInEdge(Object edge, Object node, boolean isSelfLoop) {
      if (!isSelfLoop) {
         this.addOutEdge(edge, node);
      }

   }

   public void addOutEdge(Object edge, Object node) {
      N previousNode = (N)this.incidentEdgeMap.put(edge, node);
      Preconditions.checkState(previousNode == null);
   }
}
