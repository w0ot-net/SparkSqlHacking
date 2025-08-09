package org.sparkproject.guava.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.sparkproject.guava.annotations.Beta;

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
