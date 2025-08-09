package com.google.common.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
interface NetworkConnections {
   Set adjacentNodes();

   Set predecessors();

   Set successors();

   Set incidentEdges();

   Set inEdges();

   Set outEdges();

   Set edgesConnecting(Object node);

   Object adjacentNode(Object edge);

   @CheckForNull
   @CanIgnoreReturnValue
   Object removeInEdge(Object edge, boolean isSelfLoop);

   @CanIgnoreReturnValue
   Object removeOutEdge(Object edge);

   void addInEdge(Object edge, Object node, boolean isSelfLoop);

   void addOutEdge(Object edge, Object node);
}
