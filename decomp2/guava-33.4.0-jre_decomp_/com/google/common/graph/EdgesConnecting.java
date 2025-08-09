package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class EdgesConnecting extends AbstractSet {
   private final Map nodeToOutEdge;
   private final Object targetNode;

   EdgesConnecting(Map nodeToEdgeMap, Object targetNode) {
      this.nodeToOutEdge = (Map)Preconditions.checkNotNull(nodeToEdgeMap);
      this.targetNode = Preconditions.checkNotNull(targetNode);
   }

   public UnmodifiableIterator iterator() {
      E connectingEdge = (E)this.getConnectingEdge();
      return connectingEdge == null ? ImmutableSet.of().iterator() : Iterators.singletonIterator(connectingEdge);
   }

   public int size() {
      return this.getConnectingEdge() == null ? 0 : 1;
   }

   public boolean contains(@CheckForNull Object edge) {
      E connectingEdge = (E)this.getConnectingEdge();
      return connectingEdge != null && connectingEdge.equals(edge);
   }

   @CheckForNull
   private Object getConnectingEdge() {
      return this.nodeToOutEdge.get(this.targetNode);
   }
}
