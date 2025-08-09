package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@Beta
public interface MutableGraph extends Graph {
   @CanIgnoreReturnValue
   boolean addNode(Object node);

   @CanIgnoreReturnValue
   boolean putEdge(Object nodeU, Object nodeV);

   @CanIgnoreReturnValue
   boolean putEdge(EndpointPair endpoints);

   @CanIgnoreReturnValue
   boolean removeNode(Object node);

   @CanIgnoreReturnValue
   boolean removeEdge(Object nodeU, Object nodeV);

   @CanIgnoreReturnValue
   boolean removeEdge(EndpointPair endpoints);
}
