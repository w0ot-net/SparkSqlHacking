package org.sparkproject.guava.graph;

import java.util.Set;

@ElementTypesAreNonnullByDefault
interface BaseGraph extends SuccessorsFunction, PredecessorsFunction {
   Set nodes();

   Set edges();

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
}
