package org.apache.curator.shaded.com.google.common.graph;

import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
interface GraphConnections {
   Set adjacentNodes();

   Set predecessors();

   Set successors();

   Iterator incidentEdgeIterator(Object thisNode);

   @CheckForNull
   Object value(Object node);

   void removePredecessor(Object node);

   @CheckForNull
   @CanIgnoreReturnValue
   Object removeSuccessor(Object node);

   void addPredecessor(Object node, Object value);

   @CheckForNull
   @CanIgnoreReturnValue
   Object addSuccessor(Object node, Object value);
}
