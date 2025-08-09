package org.apache.curator.shaded.com.google.common.graph;

import org.apache.curator.shaded.com.google.common.base.Optional;

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
