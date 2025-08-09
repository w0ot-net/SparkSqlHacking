package org.apache.curator.shaded.com.google.common.graph;

import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

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
