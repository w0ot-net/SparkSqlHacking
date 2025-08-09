package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Optional;
import java.util.Set;
import javax.annotation.CheckForNull;

@DoNotMock("Use NetworkBuilder to create a real instance")
@ElementTypesAreNonnullByDefault
@Beta
public interface Network extends SuccessorsFunction, PredecessorsFunction {
   Set nodes();

   Set edges();

   Graph asGraph();

   boolean isDirected();

   boolean allowsParallelEdges();

   boolean allowsSelfLoops();

   ElementOrder nodeOrder();

   ElementOrder edgeOrder();

   Set adjacentNodes(Object node);

   Set predecessors(Object node);

   Set successors(Object node);

   Set incidentEdges(Object node);

   Set inEdges(Object node);

   Set outEdges(Object node);

   int degree(Object node);

   int inDegree(Object node);

   int outDegree(Object node);

   EndpointPair incidentNodes(Object edge);

   Set adjacentEdges(Object edge);

   Set edgesConnecting(Object nodeU, Object nodeV);

   Set edgesConnecting(EndpointPair endpoints);

   Optional edgeConnecting(Object nodeU, Object nodeV);

   Optional edgeConnecting(EndpointPair endpoints);

   @CheckForNull
   Object edgeConnectingOrNull(Object nodeU, Object nodeV);

   @CheckForNull
   Object edgeConnectingOrNull(EndpointPair endpoints);

   boolean hasEdgeConnecting(Object nodeU, Object nodeV);

   boolean hasEdgeConnecting(EndpointPair endpoints);

   boolean equals(@CheckForNull Object object);

   int hashCode();
}
