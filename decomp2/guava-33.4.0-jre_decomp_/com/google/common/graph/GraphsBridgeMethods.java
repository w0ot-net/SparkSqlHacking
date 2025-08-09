package com.google.common.graph;

import com.google.common.annotations.Beta;
import java.util.Set;

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
