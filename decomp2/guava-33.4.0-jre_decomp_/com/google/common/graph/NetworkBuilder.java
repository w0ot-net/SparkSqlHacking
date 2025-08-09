package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@Beta
public final class NetworkBuilder extends AbstractGraphBuilder {
   boolean allowsParallelEdges = false;
   ElementOrder edgeOrder = ElementOrder.insertion();
   Optional expectedEdgeCount = Optional.absent();

   private NetworkBuilder(boolean directed) {
      super(directed);
   }

   public static NetworkBuilder directed() {
      return new NetworkBuilder(true);
   }

   public static NetworkBuilder undirected() {
      return new NetworkBuilder(false);
   }

   public static NetworkBuilder from(Network network) {
      return (new NetworkBuilder(network.isDirected())).allowsParallelEdges(network.allowsParallelEdges()).allowsSelfLoops(network.allowsSelfLoops()).nodeOrder(network.nodeOrder()).edgeOrder(network.edgeOrder());
   }

   public ImmutableNetwork.Builder immutable() {
      NetworkBuilder<N1, E1> castBuilder = this.cast();
      return new ImmutableNetwork.Builder(castBuilder);
   }

   @CanIgnoreReturnValue
   public NetworkBuilder allowsParallelEdges(boolean allowsParallelEdges) {
      this.allowsParallelEdges = allowsParallelEdges;
      return this;
   }

   @CanIgnoreReturnValue
   public NetworkBuilder allowsSelfLoops(boolean allowsSelfLoops) {
      this.allowsSelfLoops = allowsSelfLoops;
      return this;
   }

   @CanIgnoreReturnValue
   public NetworkBuilder expectedNodeCount(int expectedNodeCount) {
      this.expectedNodeCount = Optional.of(Graphs.checkNonNegative(expectedNodeCount));
      return this;
   }

   @CanIgnoreReturnValue
   public NetworkBuilder expectedEdgeCount(int expectedEdgeCount) {
      this.expectedEdgeCount = Optional.of(Graphs.checkNonNegative(expectedEdgeCount));
      return this;
   }

   public NetworkBuilder nodeOrder(ElementOrder nodeOrder) {
      NetworkBuilder<N1, E> newBuilder = this.cast();
      newBuilder.nodeOrder = (ElementOrder)Preconditions.checkNotNull(nodeOrder);
      return newBuilder;
   }

   public NetworkBuilder edgeOrder(ElementOrder edgeOrder) {
      NetworkBuilder<N, E1> newBuilder = this.cast();
      newBuilder.edgeOrder = (ElementOrder)Preconditions.checkNotNull(edgeOrder);
      return newBuilder;
   }

   public MutableNetwork build() {
      return new StandardMutableNetwork(this);
   }

   private NetworkBuilder cast() {
      return this;
   }
}
