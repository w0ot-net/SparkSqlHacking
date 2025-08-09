package org.apache.curator.shaded.com.google.common.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.common.base.Objects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSet;
import org.apache.curator.shaded.com.google.common.collect.Iterables;
import org.apache.curator.shaded.com.google.common.collect.Iterators;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@Beta
public final class Graphs {
   private Graphs() {
   }

   public static boolean hasCycle(Graph graph) {
      int numEdges = graph.edges().size();
      if (numEdges == 0) {
         return false;
      } else if (!graph.isDirected() && numEdges >= graph.nodes().size()) {
         return true;
      } else {
         Map<Object, NodeVisitState> visitedNodes = Maps.newHashMapWithExpectedSize(graph.nodes().size());

         for(Object node : graph.nodes()) {
            if (subgraphHasCycle(graph, visitedNodes, node, (Object)null)) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean hasCycle(Network network) {
      return !network.isDirected() && network.allowsParallelEdges() && network.edges().size() > network.asGraph().edges().size() ? true : hasCycle(network.asGraph());
   }

   private static boolean subgraphHasCycle(Graph graph, Map visitedNodes, Object node, @CheckForNull Object previousNode) {
      NodeVisitState state = (NodeVisitState)visitedNodes.get(node);
      if (state == Graphs.NodeVisitState.COMPLETE) {
         return false;
      } else if (state == Graphs.NodeVisitState.PENDING) {
         return true;
      } else {
         visitedNodes.put(node, Graphs.NodeVisitState.PENDING);

         for(Object nextNode : graph.successors(node)) {
            if (canTraverseWithoutReusingEdge(graph, nextNode, previousNode) && subgraphHasCycle(graph, visitedNodes, nextNode, node)) {
               return true;
            }
         }

         visitedNodes.put(node, Graphs.NodeVisitState.COMPLETE);
         return false;
      }
   }

   private static boolean canTraverseWithoutReusingEdge(Graph graph, Object nextNode, @CheckForNull Object previousNode) {
      return graph.isDirected() || !Objects.equal(previousNode, nextNode);
   }

   public static Graph transitiveClosure(Graph graph) {
      MutableGraph<N> transitiveClosure = GraphBuilder.from(graph).allowsSelfLoops(true).build();
      if (graph.isDirected()) {
         for(Object node : graph.nodes()) {
            for(Object reachableNode : reachableNodes(graph, node)) {
               transitiveClosure.putEdge(node, reachableNode);
            }
         }
      } else {
         Set<N> visitedNodes = new HashSet();

         for(Object node : graph.nodes()) {
            if (!visitedNodes.contains(node)) {
               Set<N> reachableNodes = reachableNodes(graph, node);
               visitedNodes.addAll(reachableNodes);
               int pairwiseMatch = 1;

               for(Object nodeU : reachableNodes) {
                  for(Object nodeV : Iterables.limit(reachableNodes, pairwiseMatch++)) {
                     transitiveClosure.putEdge(nodeU, nodeV);
                  }
               }
            }
         }
      }

      return transitiveClosure;
   }

   public static Set reachableNodes(Graph graph, Object node) {
      Preconditions.checkArgument(graph.nodes().contains(node), "Node %s is not an element of this graph.", node);
      return ImmutableSet.copyOf(Traverser.forGraph(graph).breadthFirst(node));
   }

   public static Graph transpose(Graph graph) {
      if (!graph.isDirected()) {
         return graph;
      } else {
         return (Graph)(graph instanceof TransposedGraph ? ((TransposedGraph)graph).graph : new TransposedGraph(graph));
      }
   }

   public static ValueGraph transpose(ValueGraph graph) {
      if (!graph.isDirected()) {
         return graph;
      } else {
         return (ValueGraph)(graph instanceof TransposedValueGraph ? ((TransposedValueGraph)graph).graph : new TransposedValueGraph(graph));
      }
   }

   public static Network transpose(Network network) {
      if (!network.isDirected()) {
         return network;
      } else {
         return (Network)(network instanceof TransposedNetwork ? ((TransposedNetwork)network).network : new TransposedNetwork(network));
      }
   }

   static EndpointPair transpose(EndpointPair endpoints) {
      return endpoints.isOrdered() ? EndpointPair.ordered(endpoints.target(), endpoints.source()) : endpoints;
   }

   public static MutableGraph inducedSubgraph(Graph graph, Iterable nodes) {
      MutableGraph<N> subgraph = nodes instanceof Collection ? GraphBuilder.from(graph).expectedNodeCount(((Collection)nodes).size()).build() : GraphBuilder.from(graph).build();

      for(Object node : nodes) {
         subgraph.addNode(node);
      }

      for(Object node : subgraph.nodes()) {
         for(Object successorNode : graph.successors(node)) {
            if (subgraph.nodes().contains(successorNode)) {
               subgraph.putEdge(node, successorNode);
            }
         }
      }

      return subgraph;
   }

   public static MutableValueGraph inducedSubgraph(ValueGraph graph, Iterable nodes) {
      MutableValueGraph<N, V> subgraph = nodes instanceof Collection ? ValueGraphBuilder.from(graph).expectedNodeCount(((Collection)nodes).size()).build() : ValueGraphBuilder.from(graph).build();

      for(Object node : nodes) {
         subgraph.addNode(node);
      }

      for(Object node : subgraph.nodes()) {
         for(Object successorNode : graph.successors(node)) {
            if (subgraph.nodes().contains(successorNode)) {
               subgraph.putEdgeValue(node, successorNode, java.util.Objects.requireNonNull(graph.edgeValueOrDefault(node, successorNode, (Object)null)));
            }
         }
      }

      return subgraph;
   }

   public static MutableNetwork inducedSubgraph(Network network, Iterable nodes) {
      MutableNetwork<N, E> subgraph = nodes instanceof Collection ? NetworkBuilder.from(network).expectedNodeCount(((Collection)nodes).size()).build() : NetworkBuilder.from(network).build();

      for(Object node : nodes) {
         subgraph.addNode(node);
      }

      for(Object node : subgraph.nodes()) {
         for(Object edge : network.outEdges(node)) {
            N successorNode = (N)network.incidentNodes(edge).adjacentNode(node);
            if (subgraph.nodes().contains(successorNode)) {
               subgraph.addEdge(node, successorNode, edge);
            }
         }
      }

      return subgraph;
   }

   public static MutableGraph copyOf(Graph graph) {
      MutableGraph<N> copy = GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();

      for(Object node : graph.nodes()) {
         copy.addNode(node);
      }

      for(EndpointPair edge : graph.edges()) {
         copy.putEdge(edge.nodeU(), edge.nodeV());
      }

      return copy;
   }

   public static MutableValueGraph copyOf(ValueGraph graph) {
      MutableValueGraph<N, V> copy = ValueGraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();

      for(Object node : graph.nodes()) {
         copy.addNode(node);
      }

      for(EndpointPair edge : graph.edges()) {
         copy.putEdgeValue(edge.nodeU(), edge.nodeV(), java.util.Objects.requireNonNull(graph.edgeValueOrDefault(edge.nodeU(), edge.nodeV(), (Object)null)));
      }

      return copy;
   }

   public static MutableNetwork copyOf(Network network) {
      MutableNetwork<N, E> copy = NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();

      for(Object node : network.nodes()) {
         copy.addNode(node);
      }

      for(Object edge : network.edges()) {
         EndpointPair<N> endpointPair = network.incidentNodes(edge);
         copy.addEdge(endpointPair.nodeU(), endpointPair.nodeV(), edge);
      }

      return copy;
   }

   @CanIgnoreReturnValue
   static int checkNonNegative(int value) {
      Preconditions.checkArgument(value >= 0, "Not true that %s is non-negative.", value);
      return value;
   }

   @CanIgnoreReturnValue
   static long checkNonNegative(long value) {
      Preconditions.checkArgument(value >= 0L, "Not true that %s is non-negative.", value);
      return value;
   }

   @CanIgnoreReturnValue
   static int checkPositive(int value) {
      Preconditions.checkArgument(value > 0, "Not true that %s is positive.", value);
      return value;
   }

   @CanIgnoreReturnValue
   static long checkPositive(long value) {
      Preconditions.checkArgument(value > 0L, "Not true that %s is positive.", value);
      return value;
   }

   private static class TransposedGraph extends ForwardingGraph {
      private final Graph graph;

      TransposedGraph(Graph graph) {
         this.graph = graph;
      }

      Graph delegate() {
         return this.graph;
      }

      public Set predecessors(Object node) {
         return this.delegate().successors(node);
      }

      public Set successors(Object node) {
         return this.delegate().predecessors(node);
      }

      public Set incidentEdges(Object node) {
         return new IncidentEdgeSet(this, node) {
            public Iterator iterator() {
               return Iterators.transform(TransposedGraph.this.delegate().incidentEdges(this.node).iterator(), (edge) -> EndpointPair.of(TransposedGraph.this.delegate(), edge.nodeV(), edge.nodeU()));
            }
         };
      }

      public int inDegree(Object node) {
         return this.delegate().outDegree(node);
      }

      public int outDegree(Object node) {
         return this.delegate().inDegree(node);
      }

      public boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
         return this.delegate().hasEdgeConnecting(nodeV, nodeU);
      }

      public boolean hasEdgeConnecting(EndpointPair endpoints) {
         return this.delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
      }
   }

   private static class TransposedValueGraph extends ForwardingValueGraph {
      private final ValueGraph graph;

      TransposedValueGraph(ValueGraph graph) {
         this.graph = graph;
      }

      ValueGraph delegate() {
         return this.graph;
      }

      public Set predecessors(Object node) {
         return this.delegate().successors(node);
      }

      public Set successors(Object node) {
         return this.delegate().predecessors(node);
      }

      public int inDegree(Object node) {
         return this.delegate().outDegree(node);
      }

      public int outDegree(Object node) {
         return this.delegate().inDegree(node);
      }

      public boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
         return this.delegate().hasEdgeConnecting(nodeV, nodeU);
      }

      public boolean hasEdgeConnecting(EndpointPair endpoints) {
         return this.delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
      }

      public Optional edgeValue(Object nodeU, Object nodeV) {
         return this.delegate().edgeValue(nodeV, nodeU);
      }

      public Optional edgeValue(EndpointPair endpoints) {
         return this.delegate().edgeValue(Graphs.transpose(endpoints));
      }

      @CheckForNull
      public Object edgeValueOrDefault(Object nodeU, Object nodeV, @CheckForNull Object defaultValue) {
         return this.delegate().edgeValueOrDefault(nodeV, nodeU, defaultValue);
      }

      @CheckForNull
      public Object edgeValueOrDefault(EndpointPair endpoints, @CheckForNull Object defaultValue) {
         return this.delegate().edgeValueOrDefault(Graphs.transpose(endpoints), defaultValue);
      }
   }

   private static class TransposedNetwork extends ForwardingNetwork {
      private final Network network;

      TransposedNetwork(Network network) {
         this.network = network;
      }

      Network delegate() {
         return this.network;
      }

      public Set predecessors(Object node) {
         return this.delegate().successors(node);
      }

      public Set successors(Object node) {
         return this.delegate().predecessors(node);
      }

      public int inDegree(Object node) {
         return this.delegate().outDegree(node);
      }

      public int outDegree(Object node) {
         return this.delegate().inDegree(node);
      }

      public Set inEdges(Object node) {
         return this.delegate().outEdges(node);
      }

      public Set outEdges(Object node) {
         return this.delegate().inEdges(node);
      }

      public EndpointPair incidentNodes(Object edge) {
         EndpointPair<N> endpointPair = this.delegate().incidentNodes(edge);
         return EndpointPair.of(this.network, endpointPair.nodeV(), endpointPair.nodeU());
      }

      public Set edgesConnecting(Object nodeU, Object nodeV) {
         return this.delegate().edgesConnecting(nodeV, nodeU);
      }

      public Set edgesConnecting(EndpointPair endpoints) {
         return this.delegate().edgesConnecting(Graphs.transpose(endpoints));
      }

      public Optional edgeConnecting(Object nodeU, Object nodeV) {
         return this.delegate().edgeConnecting(nodeV, nodeU);
      }

      public Optional edgeConnecting(EndpointPair endpoints) {
         return this.delegate().edgeConnecting(Graphs.transpose(endpoints));
      }

      @CheckForNull
      public Object edgeConnectingOrNull(Object nodeU, Object nodeV) {
         return this.delegate().edgeConnectingOrNull(nodeV, nodeU);
      }

      @CheckForNull
      public Object edgeConnectingOrNull(EndpointPair endpoints) {
         return this.delegate().edgeConnectingOrNull(Graphs.transpose(endpoints));
      }

      public boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
         return this.delegate().hasEdgeConnecting(nodeV, nodeU);
      }

      public boolean hasEdgeConnecting(EndpointPair endpoints) {
         return this.delegate().hasEdgeConnecting(Graphs.transpose(endpoints));
      }
   }

   private static enum NodeVisitState {
      PENDING,
      COMPLETE;

      // $FF: synthetic method
      private static NodeVisitState[] $values() {
         return new NodeVisitState[]{PENDING, COMPLETE};
      }
   }
}
