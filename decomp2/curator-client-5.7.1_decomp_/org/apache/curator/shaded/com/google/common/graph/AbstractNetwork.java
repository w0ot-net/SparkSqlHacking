package org.apache.curator.shaded.com.google.common.graph;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSet;
import org.apache.curator.shaded.com.google.common.collect.Iterators;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.collect.Sets;
import org.apache.curator.shaded.com.google.common.math.IntMath;

@ElementTypesAreNonnullByDefault
@Beta
public abstract class AbstractNetwork implements Network {
   public Graph asGraph() {
      return new AbstractGraph() {
         public Set nodes() {
            return AbstractNetwork.this.nodes();
         }

         public Set edges() {
            return (Set)(AbstractNetwork.this.allowsParallelEdges() ? super.edges() : new AbstractSet() {
               public Iterator iterator() {
                  return Iterators.transform(AbstractNetwork.this.edges().iterator(), (edge) -> AbstractNetwork.this.incidentNodes(edge));
               }

               public int size() {
                  return AbstractNetwork.this.edges().size();
               }

               public boolean contains(@CheckForNull Object obj) {
                  if (!(obj instanceof EndpointPair)) {
                     return false;
                  } else {
                     EndpointPair<?> endpointPair = (EndpointPair)obj;
                     return isOrderingCompatible(endpointPair) && nodes().contains(endpointPair.nodeU()) && successors(endpointPair.nodeU()).contains(endpointPair.nodeV());
                  }
               }
            });
         }

         public ElementOrder nodeOrder() {
            return AbstractNetwork.this.nodeOrder();
         }

         public ElementOrder incidentEdgeOrder() {
            return ElementOrder.unordered();
         }

         public boolean isDirected() {
            return AbstractNetwork.this.isDirected();
         }

         public boolean allowsSelfLoops() {
            return AbstractNetwork.this.allowsSelfLoops();
         }

         public Set adjacentNodes(Object node) {
            return AbstractNetwork.this.adjacentNodes(node);
         }

         public Set predecessors(Object node) {
            return AbstractNetwork.this.predecessors(node);
         }

         public Set successors(Object node) {
            return AbstractNetwork.this.successors(node);
         }
      };
   }

   public int degree(Object node) {
      return this.isDirected() ? IntMath.saturatedAdd(this.inEdges(node).size(), this.outEdges(node).size()) : IntMath.saturatedAdd(this.incidentEdges(node).size(), this.edgesConnecting(node, node).size());
   }

   public int inDegree(Object node) {
      return this.isDirected() ? this.inEdges(node).size() : this.degree(node);
   }

   public int outDegree(Object node) {
      return this.isDirected() ? this.outEdges(node).size() : this.degree(node);
   }

   public Set adjacentEdges(Object edge) {
      EndpointPair<N> endpointPair = this.incidentNodes(edge);
      Set<E> endpointPairIncidentEdges = Sets.union(this.incidentEdges(endpointPair.nodeU()), this.incidentEdges(endpointPair.nodeV()));
      return Sets.difference(endpointPairIncidentEdges, ImmutableSet.of(edge));
   }

   public Set edgesConnecting(Object nodeU, Object nodeV) {
      Set<E> outEdgesU = this.outEdges(nodeU);
      Set<E> inEdgesV = this.inEdges(nodeV);
      return outEdgesU.size() <= inEdgesV.size() ? Collections.unmodifiableSet(Sets.filter(outEdgesU, this.connectedPredicate(nodeU, nodeV))) : Collections.unmodifiableSet(Sets.filter(inEdgesV, this.connectedPredicate(nodeV, nodeU)));
   }

   public Set edgesConnecting(EndpointPair endpoints) {
      this.validateEndpoints(endpoints);
      return this.edgesConnecting(endpoints.nodeU(), endpoints.nodeV());
   }

   private Predicate connectedPredicate(final Object nodePresent, final Object nodeToCheck) {
      return new Predicate() {
         public boolean apply(Object edge) {
            return AbstractNetwork.this.incidentNodes(edge).adjacentNode(nodePresent).equals(nodeToCheck);
         }
      };
   }

   public Optional edgeConnecting(Object nodeU, Object nodeV) {
      return Optional.ofNullable(this.edgeConnectingOrNull(nodeU, nodeV));
   }

   public Optional edgeConnecting(EndpointPair endpoints) {
      this.validateEndpoints(endpoints);
      return this.edgeConnecting(endpoints.nodeU(), endpoints.nodeV());
   }

   @CheckForNull
   public Object edgeConnectingOrNull(Object nodeU, Object nodeV) {
      Set<E> edgesConnecting = this.edgesConnecting(nodeU, nodeV);
      switch (edgesConnecting.size()) {
         case 0:
            return null;
         case 1:
            return edgesConnecting.iterator().next();
         default:
            throw new IllegalArgumentException(String.format("Cannot call edgeConnecting() when parallel edges exist between %s and %s. Consider calling edgesConnecting() instead.", nodeU, nodeV));
      }
   }

   @CheckForNull
   public Object edgeConnectingOrNull(EndpointPair endpoints) {
      this.validateEndpoints(endpoints);
      return this.edgeConnectingOrNull(endpoints.nodeU(), endpoints.nodeV());
   }

   public boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
      Preconditions.checkNotNull(nodeU);
      Preconditions.checkNotNull(nodeV);
      return this.nodes().contains(nodeU) && this.successors(nodeU).contains(nodeV);
   }

   public boolean hasEdgeConnecting(EndpointPair endpoints) {
      Preconditions.checkNotNull(endpoints);
      return !this.isOrderingCompatible(endpoints) ? false : this.hasEdgeConnecting(endpoints.nodeU(), endpoints.nodeV());
   }

   protected final void validateEndpoints(EndpointPair endpoints) {
      Preconditions.checkNotNull(endpoints);
      Preconditions.checkArgument(this.isOrderingCompatible(endpoints), "Mismatch: endpoints' ordering is not compatible with directionality of the graph");
   }

   protected final boolean isOrderingCompatible(EndpointPair endpoints) {
      return endpoints.isOrdered() == this.isDirected();
   }

   public final boolean equals(@CheckForNull Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Network)) {
         return false;
      } else {
         Network<?, ?> other = (Network)obj;
         return this.isDirected() == other.isDirected() && this.nodes().equals(other.nodes()) && edgeIncidentNodesMap(this).equals(edgeIncidentNodesMap(other));
      }
   }

   public final int hashCode() {
      return edgeIncidentNodesMap(this).hashCode();
   }

   public String toString() {
      return "isDirected: " + this.isDirected() + ", allowsParallelEdges: " + this.allowsParallelEdges() + ", allowsSelfLoops: " + this.allowsSelfLoops() + ", nodes: " + this.nodes() + ", edges: " + edgeIncidentNodesMap(this);
   }

   private static Map edgeIncidentNodesMap(final Network network) {
      Set var10000 = network.edges();
      Objects.requireNonNull(network);
      return Maps.asMap(var10000, network::incidentNodes);
   }
}
