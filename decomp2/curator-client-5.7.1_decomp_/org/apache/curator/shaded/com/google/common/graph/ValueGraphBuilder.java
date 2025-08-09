package org.apache.curator.shaded.com.google.common.graph;

import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.common.base.Optional;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@Beta
public final class ValueGraphBuilder extends AbstractGraphBuilder {
   private ValueGraphBuilder(boolean directed) {
      super(directed);
   }

   public static ValueGraphBuilder directed() {
      return new ValueGraphBuilder(true);
   }

   public static ValueGraphBuilder undirected() {
      return new ValueGraphBuilder(false);
   }

   public static ValueGraphBuilder from(ValueGraph graph) {
      return (new ValueGraphBuilder(graph.isDirected())).allowsSelfLoops(graph.allowsSelfLoops()).nodeOrder(graph.nodeOrder()).incidentEdgeOrder(graph.incidentEdgeOrder());
   }

   public ImmutableValueGraph.Builder immutable() {
      ValueGraphBuilder<N1, V1> castBuilder = this.cast();
      return new ImmutableValueGraph.Builder(castBuilder);
   }

   @CanIgnoreReturnValue
   public ValueGraphBuilder allowsSelfLoops(boolean allowsSelfLoops) {
      this.allowsSelfLoops = allowsSelfLoops;
      return this;
   }

   @CanIgnoreReturnValue
   public ValueGraphBuilder expectedNodeCount(int expectedNodeCount) {
      this.expectedNodeCount = Optional.of(Graphs.checkNonNegative(expectedNodeCount));
      return this;
   }

   public ValueGraphBuilder nodeOrder(ElementOrder nodeOrder) {
      ValueGraphBuilder<N1, V> newBuilder = this.cast();
      newBuilder.nodeOrder = (ElementOrder)Preconditions.checkNotNull(nodeOrder);
      return newBuilder;
   }

   public ValueGraphBuilder incidentEdgeOrder(ElementOrder incidentEdgeOrder) {
      Preconditions.checkArgument(incidentEdgeOrder.type() == ElementOrder.Type.UNORDERED || incidentEdgeOrder.type() == ElementOrder.Type.STABLE, "The given elementOrder (%s) is unsupported. incidentEdgeOrder() only supports ElementOrder.unordered() and ElementOrder.stable().", (Object)incidentEdgeOrder);
      ValueGraphBuilder<N1, V> newBuilder = this.cast();
      newBuilder.incidentEdgeOrder = (ElementOrder)Preconditions.checkNotNull(incidentEdgeOrder);
      return newBuilder;
   }

   public MutableValueGraph build() {
      return new StandardMutableValueGraph(this);
   }

   ValueGraphBuilder copy() {
      ValueGraphBuilder<N, V> newBuilder = new ValueGraphBuilder(this.directed);
      newBuilder.allowsSelfLoops = this.allowsSelfLoops;
      newBuilder.nodeOrder = this.nodeOrder;
      newBuilder.expectedNodeCount = this.expectedNodeCount;
      newBuilder.incidentEdgeOrder = this.incidentEdgeOrder;
      return newBuilder;
   }

   private ValueGraphBuilder cast() {
      return this;
   }
}
