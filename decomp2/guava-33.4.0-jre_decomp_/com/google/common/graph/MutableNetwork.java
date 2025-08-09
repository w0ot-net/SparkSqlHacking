package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

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
