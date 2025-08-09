package org.apache.curator.shaded.com.google.common.graph;

import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@Beta
public interface MutableNetwork extends Network {
   @CanIgnoreReturnValue
   boolean addNode(Object node);

   @CanIgnoreReturnValue
   boolean addEdge(Object nodeU, Object nodeV, Object edge);

   @CanIgnoreReturnValue
   boolean addEdge(EndpointPair endpoints, Object edge);

   @CanIgnoreReturnValue
   boolean removeNode(Object node);

   @CanIgnoreReturnValue
   boolean removeEdge(Object edge);
}
