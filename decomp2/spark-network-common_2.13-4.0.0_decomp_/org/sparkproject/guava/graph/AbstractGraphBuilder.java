package org.sparkproject.guava.graph;

import org.sparkproject.guava.base.Optional;

@ElementTypesAreNonnullByDefault
abstract class AbstractGraphBuilder {
   final boolean directed;
   boolean allowsSelfLoops = false;
   ElementOrder nodeOrder = ElementOrder.insertion();
   ElementOrder incidentEdgeOrder = ElementOrder.unordered();
   Optional expectedNodeCount = Optional.absent();

   AbstractGraphBuilder(boolean directed) {
      this.directed = directed;
   }
}
