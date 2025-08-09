package org.sparkproject.guava.graph;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.sparkproject.guava.collect.BiMap;
import org.sparkproject.guava.collect.HashBiMap;
import org.sparkproject.guava.collect.ImmutableBiMap;

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
