package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@ElementTypesAreNonnullByDefault
final class DirectedNetworkConnections extends AbstractDirectedNetworkConnections {
   DirectedNetworkConnections(Map inEdgeMap, Map outEdgeMap, int selfLoopCount) {
      super(inEdgeMap, outEdgeMap, selfLoopCount);
   }

   static DirectedNetworkConnections of() {
      return new DirectedNetworkConnections(HashBiMap.create(2), HashBiMap.create(2), 0);
   }

   static DirectedNetworkConnections ofImmutable(Map inEdges, Map outEdges, int selfLoopCount) {
      return new DirectedNetworkConnections(ImmutableBiMap.copyOf(inEdges), ImmutableBiMap.copyOf(outEdges), selfLoopCount);
   }

   public Set predecessors() {
      return Collections.unmodifiableSet(((BiMap)this.inEdgeMap).values());
   }

   public Set successors() {
      return Collections.unmodifiableSet(((BiMap)this.outEdgeMap).values());
   }

   public Set edgesConnecting(Object node) {
      return new EdgesConnecting(((BiMap)this.outEdgeMap).inverse(), node);
   }
}
