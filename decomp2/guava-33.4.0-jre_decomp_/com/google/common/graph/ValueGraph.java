package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Optional;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Beta
public interface ValueGraph extends BaseGraph {
   Set nodes();

   Set edges();

   Graph asGraph();

   boolean isDirected();

   boolean allowsSelfLoops();

   ElementOrder nodeOrder();

   ElementOrder incidentEdgeOrder();

   Set adjacentNodes(Object node);

   Set predecessors(Object node);

   Set successors(Object node);

   Set incidentEdges(Object node);

   int degree(Object node);

   int inDegree(Object node);

   int outDegree(Object node);

   boolean hasEdgeConnecting(Object nodeU, Object nodeV);

   boolean hasEdgeConnecting(EndpointPair endpoints);

   Optional edgeValue(Object nodeU, Object nodeV);

   Optional edgeValue(EndpointPair endpoints);

   @CheckForNull
   Object edgeValueOrDefault(Object nodeU, Object nodeV, @CheckForNull Object defaultValue);

   @CheckForNull
   Object edgeValueOrDefault(EndpointPair endpoints, @CheckForNull Object defaultValue);

   boolean equals(@CheckForNull Object object);

   int hashCode();
}
