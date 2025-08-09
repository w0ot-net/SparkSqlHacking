package org.sparkproject.guava.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.Beta;

@ElementTypesAreNonnullByDefault
@Beta
public interface MutableValueGraph extends ValueGraph {
   @CanIgnoreReturnValue
   boolean addNode(Object node);

   @CheckForNull
   @CanIgnoreReturnValue
   Object putEdgeValue(Object nodeU, Object nodeV, Object value);

   @CheckForNull
   @CanIgnoreReturnValue
   Object putEdgeValue(EndpointPair endpoints, Object value);

   @CanIgnoreReturnValue
   boolean removeNode(Object node);

   @CheckForNull
   @CanIgnoreReturnValue
   Object removeEdge(Object nodeU, Object nodeV);

   @CheckForNull
   @CanIgnoreReturnValue
   Object removeEdge(EndpointPair endpoints);
}
