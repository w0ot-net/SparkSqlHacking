package org.sparkproject.guava.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ImmutableMap;
import org.sparkproject.guava.collect.Iterators;

@ElementTypesAreNonnullByDefault
final class UndirectedGraphConnections implements GraphConnections {
   private final Map adjacentNodeValues;

   private UndirectedGraphConnections(Map adjacentNodeValues) {
      this.adjacentNodeValues = (Map)Preconditions.checkNotNull(adjacentNodeValues);
   }

   static UndirectedGraphConnections of(ElementOrder incidentEdgeOrder) {
      switch (incidentEdgeOrder.type()) {
         case UNORDERED:
            return new UndirectedGraphConnections(new HashMap(2, 1.0F));
         case STABLE:
            return new UndirectedGraphConnections(new LinkedHashMap(2, 1.0F));
         default:
            throw new AssertionError(incidentEdgeOrder.type());
      }
   }

   static UndirectedGraphConnections ofImmutable(Map adjacentNodeValues) {
      return new UndirectedGraphConnections(ImmutableMap.copyOf(adjacentNodeValues));
   }

   public Set adjacentNodes() {
      return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
   }

   public Set predecessors() {
      return this.adjacentNodes();
   }

   public Set successors() {
      return this.adjacentNodes();
   }

   public Iterator incidentEdgeIterator(Object thisNode) {
      return Iterators.transform(this.adjacentNodeValues.keySet().iterator(), (incidentNode) -> EndpointPair.unordered(thisNode, incidentNode));
   }

   @CheckForNull
   public Object value(Object node) {
      return this.adjacentNodeValues.get(node);
   }

   public void removePredecessor(Object node) {
      this.removeSuccessor(node);
   }

   @CheckForNull
   public Object removeSuccessor(Object node) {
      return this.adjacentNodeValues.remove(node);
   }

   public void addPredecessor(Object node, Object value) {
      this.addSuccessor(node, value);
   }

   @CheckForNull
   public Object addSuccessor(Object node, Object value) {
      return this.adjacentNodeValues.put(node, value);
   }
}
