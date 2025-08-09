package org.sparkproject.guava.graph;

import java.util.Set;
import org.sparkproject.guava.annotations.Beta;

@ElementTypesAreNonnullByDefault
@Beta
abstract class GraphsBridgeMethods {
   public static Graph transitiveClosure(Graph graph) {
      return Graphs.transitiveClosure(graph);
   }

   public static Set reachableNodes(Graph graph, Object node) {
      return Graphs.reachableNodes(graph, node);
   }
}
