package org.apache.curator.shaded.com.google.common.graph;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Iterables;
import org.apache.curator.shaded.com.google.common.collect.Iterators;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.apache.curator.shaded.com.google.common.collect.UnmodifiableIterator;
import org.apache.curator.shaded.com.google.common.math.IntMath;

@ElementTypesAreNonnullByDefault
abstract class AbstractDirectedNetworkConnections implements NetworkConnections {
   final Map inEdgeMap;
   final Map outEdgeMap;
   private int selfLoopCount;

   AbstractDirectedNetworkConnections(Map inEdgeMap, Map outEdgeMap, int selfLoopCount) {
      this.inEdgeMap = (Map)Preconditions.checkNotNull(inEdgeMap);
      this.outEdgeMap = (Map)Preconditions.checkNotNull(outEdgeMap);
      this.selfLoopCount = Graphs.checkNonNegative(selfLoopCount);
      Preconditions.checkState(selfLoopCount <= inEdgeMap.size() && selfLoopCount <= outEdgeMap.size());
   }

   public Set adjacentNodes() {
      return Sets.union(this.predecessors(), this.successors());
   }

   public Set incidentEdges() {
      return new AbstractSet() {
         public UnmodifiableIterator iterator() {
            Iterable<E> incidentEdges = (Iterable<E>)(AbstractDirectedNetworkConnections.this.selfLoopCount == 0 ? Iterables.concat(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet()) : Sets.union(AbstractDirectedNetworkConnections.this.inEdgeMap.keySet(), AbstractDirectedNetworkConnections.this.outEdgeMap.keySet()));
            return Iterators.unmodifiableIterator(incidentEdges.iterator());
         }

         public int size() {
            return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.inEdgeMap.size(), AbstractDirectedNetworkConnections.this.outEdgeMap.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
         }

         public boolean contains(@CheckForNull Object obj) {
            return AbstractDirectedNetworkConnections.this.inEdgeMap.containsKey(obj) || AbstractDirectedNetworkConnections.this.outEdgeMap.containsKey(obj);
         }
      };
   }

   public Set inEdges() {
      return Collections.unmodifiableSet(this.inEdgeMap.keySet());
   }

   public Set outEdges() {
      return Collections.unmodifiableSet(this.outEdgeMap.keySet());
   }

   public Object adjacentNode(Object edge) {
      return Objects.requireNonNull(this.outEdgeMap.get(edge));
   }

   public Object removeInEdge(Object edge, boolean isSelfLoop) {
      if (isSelfLoop) {
         Graphs.checkNonNegative(--this.selfLoopCount);
      }

      N previousNode = (N)this.inEdgeMap.remove(edge);
      return Objects.requireNonNull(previousNode);
   }

   public Object removeOutEdge(Object edge) {
      N previousNode = (N)this.outEdgeMap.remove(edge);
      return Objects.requireNonNull(previousNode);
   }

   public void addInEdge(Object edge, Object node, boolean isSelfLoop) {
      Preconditions.checkNotNull(edge);
      Preconditions.checkNotNull(node);
      if (isSelfLoop) {
         Graphs.checkPositive(++this.selfLoopCount);
      }

      N previousNode = (N)this.inEdgeMap.put(edge, node);
      Preconditions.checkState(previousNode == null);
   }

   public void addOutEdge(Object edge, Object node) {
      Preconditions.checkNotNull(edge);
      Preconditions.checkNotNull(node);
      N previousNode = (N)this.outEdgeMap.put(edge, node);
      Preconditions.checkState(previousNode == null);
   }
}
