package org.sparkproject.guava.graph;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.util.Map;
import org.sparkproject.guava.annotations.Beta;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ImmutableMap;
import org.sparkproject.guava.collect.Maps;

@Immutable(
   containerOf = {"N", "E"}
)
@ElementTypesAreNonnullByDefault
@Beta
public final class ImmutableNetwork extends StandardNetwork {
   private ImmutableNetwork(Network network) {
      super(NetworkBuilder.from(network), getNodeConnections(network), getEdgeToReferenceNode(network));
   }

   public static ImmutableNetwork copyOf(Network network) {
      return network instanceof ImmutableNetwork ? (ImmutableNetwork)network : new ImmutableNetwork(network);
   }

   /** @deprecated */
   @Deprecated
   public static ImmutableNetwork copyOf(ImmutableNetwork network) {
      return (ImmutableNetwork)Preconditions.checkNotNull(network);
   }

   public ImmutableGraph asGraph() {
      return new ImmutableGraph(super.asGraph());
   }

   private static Map getNodeConnections(Network network) {
      ImmutableMap.Builder<N, NetworkConnections<N, E>> nodeConnections = ImmutableMap.builder();

      for(Object node : network.nodes()) {
         nodeConnections.put(node, connectionsOf(network, node));
      }

      return nodeConnections.buildOrThrow();
   }

   private static Map getEdgeToReferenceNode(Network network) {
      ImmutableMap.Builder<E, N> edgeToReferenceNode = ImmutableMap.builder();

      for(Object edge : network.edges()) {
         edgeToReferenceNode.put(edge, network.incidentNodes(edge).nodeU());
      }

      return edgeToReferenceNode.buildOrThrow();
   }

   private static NetworkConnections connectionsOf(Network network, Object node) {
      if (network.isDirected()) {
         Map<E, N> inEdgeMap = Maps.asMap(network.inEdges(node), sourceNodeFn(network));
         Map<E, N> outEdgeMap = Maps.asMap(network.outEdges(node), targetNodeFn(network));
         int selfLoopCount = network.edgesConnecting(node, node).size();
         return (NetworkConnections)(network.allowsParallelEdges() ? DirectedMultiNetworkConnections.ofImmutable(inEdgeMap, outEdgeMap, selfLoopCount) : DirectedNetworkConnections.ofImmutable(inEdgeMap, outEdgeMap, selfLoopCount));
      } else {
         Map<E, N> incidentEdgeMap = Maps.asMap(network.incidentEdges(node), adjacentNodeFn(network, node));
         return (NetworkConnections)(network.allowsParallelEdges() ? UndirectedMultiNetworkConnections.ofImmutable(incidentEdgeMap) : UndirectedNetworkConnections.ofImmutable(incidentEdgeMap));
      }
   }

   private static Function sourceNodeFn(Network network) {
      return (edge) -> network.incidentNodes(edge).source();
   }

   private static Function targetNodeFn(Network network) {
      return (edge) -> network.incidentNodes(edge).target();
   }

   private static Function adjacentNodeFn(Network network, Object node) {
      return (edge) -> network.incidentNodes(edge).adjacentNode(node);
   }

   public static class Builder {
      private final MutableNetwork mutableNetwork;

      Builder(NetworkBuilder networkBuilder) {
         this.mutableNetwork = networkBuilder.build();
      }

      @CanIgnoreReturnValue
      public Builder addNode(Object node) {
         this.mutableNetwork.addNode(node);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addEdge(Object nodeU, Object nodeV, Object edge) {
         this.mutableNetwork.addEdge(nodeU, nodeV, edge);
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addEdge(EndpointPair endpoints, Object edge) {
         this.mutableNetwork.addEdge(endpoints, edge);
         return this;
      }

      public ImmutableNetwork build() {
         return ImmutableNetwork.copyOf((Network)this.mutableNetwork);
      }
   }
}
