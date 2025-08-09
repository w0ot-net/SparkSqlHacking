package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;

@DoNotMock
@ElementTypesAreNonnullByDefault
@Beta
public final class GraphBuilder extends AbstractGraphBuilder {
   private GraphBuilder(boolean directed) {
      super(directed);
   }

   public static GraphBuilder directed() {
      return new GraphBuilder(true);
   }

   public static GraphBuilder undirected() {
      return new GraphBuilder(false);
   }

   public static GraphBuilder from(Graph graph) {
      return (new GraphBuilder(graph.isDirected())).allowsSelfLoops(graph.allowsSelfLoops()).nodeOrder(graph.nodeOrder()).incidentEdgeOrder(graph.incidentEdgeOrder());
   }

   public ImmutableGraph.Builder immutable() {
      GraphBuilder<N1> castBuilder = this.cast();
      return new ImmutableGraph.Builder(castBuilder);
   }

   @CanIgnoreReturnValue
   public GraphBuilder allowsSelfLoops(boolean allowsSelfLoops) {
      this.allowsSelfLoops = allowsSelfLoops;
      return this;
   }

   @CanIgnoreReturnValue
   public GraphBuilder expectedNodeCount(int expectedNodeCount) {
      this.expectedNodeCount = Optional.of(Graphs.checkNonNegative(expectedNodeCount));
      return this;
   }

   public GraphBuilder nodeOrder(ElementOrder nodeOrder) {
      GraphBuilder<N1> newBuilder = this.cast();
      newBuilder.nodeOrder = (ElementOrder)Preconditions.checkNotNull(nodeOrder);
      return newBuilder;
   }

   public GraphBuilder incidentEdgeOrder(ElementOrder incidentEdgeOrder) {
      Preconditions.checkArgument(incidentEdgeOrder.type() == ElementOrder.Type.UNORDERED || incidentEdgeOrder.type() == ElementOrder.Type.STABLE, "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().", (Object)incidentEdgeOrder);
      GraphBuilder<N1> newBuilder = this.cast();
      newBuilder.incidentEdgeOrder = (ElementOrder)Preconditions.checkNotNull(incidentEdgeOrder);
      return newBuilder;
   }

   public MutableGraph build() {
      return new StandardMutableGraph(this);
   }

   GraphBuilder copy() {
      GraphBuilder<N> newBuilder = new GraphBuilder(this.directed);
      newBuilder.allowsSelfLoops = this.allowsSelfLoops;
      newBuilder.nodeOrder = this.nodeOrder;
      newBuilder.expectedNodeCount = this.expectedNodeCount;
      newBuilder.incidentEdgeOrder = this.incidentEdgeOrder;
      return newBuilder;
   }

   private GraphBuilder cast() {
      return this;
   }
}
