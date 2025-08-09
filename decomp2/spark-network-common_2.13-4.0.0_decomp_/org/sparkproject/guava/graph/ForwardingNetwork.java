package org.sparkproject.guava.graph;

import java.util.Optional;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class ForwardingNetwork extends AbstractNetwork {
   abstract Network delegate();

   public Set nodes() {
      return this.delegate().nodes();
   }

   public Set edges() {
      return this.delegate().edges();
   }

   public boolean isDirected() {
      return this.delegate().isDirected();
   }

   public boolean allowsParallelEdges() {
      return this.delegate().allowsParallelEdges();
   }

   public boolean allowsSelfLoops() {
      return this.delegate().allowsSelfLoops();
   }

   public ElementOrder nodeOrder() {
      return this.delegate().nodeOrder();
   }

   public ElementOrder edgeOrder() {
      return this.delegate().edgeOrder();
   }

   public Set adjacentNodes(Object node) {
      return this.delegate().adjacentNodes(node);
   }

   public Set predecessors(Object node) {
      return this.delegate().predecessors(node);
   }

   public Set successors(Object node) {
      return this.delegate().successors(node);
   }

   public Set incidentEdges(Object node) {
      return this.delegate().incidentEdges(node);
   }

   public Set inEdges(Object node) {
      return this.delegate().inEdges(node);
   }

   public Set outEdges(Object node) {
      return this.delegate().outEdges(node);
   }

   public EndpointPair incidentNodes(Object edge) {
      return this.delegate().incidentNodes(edge);
   }

   public Set adjacentEdges(Object edge) {
      return this.delegate().adjacentEdges(edge);
   }

   public int degree(Object node) {
      return this.delegate().degree(node);
   }

   public int inDegree(Object node) {
      return this.delegate().inDegree(node);
   }

   public int outDegree(Object node) {
      return this.delegate().outDegree(node);
   }

   public Set edgesConnecting(Object nodeU, Object nodeV) {
      return this.delegate().edgesConnecting(nodeU, nodeV);
   }

   public Set edgesConnecting(EndpointPair endpoints) {
      return this.delegate().edgesConnecting(endpoints);
   }

   public Optional edgeConnecting(Object nodeU, Object nodeV) {
      return this.delegate().edgeConnecting(nodeU, nodeV);
   }

   public Optional edgeConnecting(EndpointPair endpoints) {
      return this.delegate().edgeConnecting(endpoints);
   }

   @CheckForNull
   public Object edgeConnectingOrNull(Object nodeU, Object nodeV) {
      return this.delegate().edgeConnectingOrNull(nodeU, nodeV);
   }

   @CheckForNull
   public Object edgeConnectingOrNull(EndpointPair endpoints) {
      return this.delegate().edgeConnectingOrNull(endpoints);
   }

   public boolean hasEdgeConnecting(Object nodeU, Object nodeV) {
      return this.delegate().hasEdgeConnecting(nodeU, nodeV);
   }

   public boolean hasEdgeConnecting(EndpointPair endpoints) {
      return this.delegate().hasEdgeConnecting(endpoints);
   }
}
