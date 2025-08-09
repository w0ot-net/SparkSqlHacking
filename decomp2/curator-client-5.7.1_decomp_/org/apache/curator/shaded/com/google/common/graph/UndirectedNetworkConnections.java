package org.apache.curator.shaded.com.google.common.graph;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.apache.curator.shaded.com.google.common.collect.BiMap;
import org.apache.curator.shaded.com.google.common.collect.HashBiMap;
import org.apache.curator.shaded.com.google.common.collect.ImmutableBiMap;

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
