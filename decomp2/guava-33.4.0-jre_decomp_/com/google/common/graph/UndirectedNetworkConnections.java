package com.google.common.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@ElementTypesAreNonnullByDefault
final class UndirectedNetworkConnections extends AbstractUndirectedNetworkConnections {
   UndirectedNetworkConnections(Map incidentEdgeMap) {
      super(incidentEdgeMap);
   }

   static UndirectedNetworkConnections of() {
      return new UndirectedNetworkConnections(HashBiMap.create(2));
   }

   static UndirectedNetworkConnections ofImmutable(Map incidentEdges) {
      return new UndirectedNetworkConnections(ImmutableBiMap.copyOf(incidentEdges));
   }

   public Set adjacentNodes() {
      return Collections.unmodifiableSet(((BiMap)this.incidentEdgeMap).values());
   }

   public Set edgesConnecting(Object node) {
      return new EdgesConnecting(((BiMap)this.incidentEdgeMap).inverse(), node);
   }
}
