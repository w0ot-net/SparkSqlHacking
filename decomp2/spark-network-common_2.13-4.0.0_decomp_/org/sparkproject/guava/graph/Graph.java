package org.sparkproject.guava.graph;

import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.Beta;

@DoNotMock("Use GraphBuilder to create a real instance")
@ElementTypesAreNonnullByDefault
@Beta
public interface Graph extends BaseGraph {
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

   boolean equals(@CheckForNull Object object);

   int hashCode();
}
