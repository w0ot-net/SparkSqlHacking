package org.apache.curator.shaded.com.google.common.graph;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.AbstractIterator;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSet;
import org.apache.curator.shaded.com.google.common.collect.Sets;

@ElementTypesAreNonnullByDefault
abstract class EndpointPairIterator extends AbstractIterator {
   private final BaseGraph graph;
   private final Iterator nodeIterator;
   @CheckForNull
   Object node;
   Iterator successorIterator;

   static EndpointPairIterator of(BaseGraph graph) {
      return (EndpointPairIterator)(graph.isDirected() ? new Directed(graph) : new Undirected(graph));
   }

   private EndpointPairIterator(BaseGraph graph) {
      this.node = null;
      this.successorIterator = ImmutableSet.of().iterator();
      this.graph = graph;
      this.nodeIterator = graph.nodes().iterator();
   }

   final boolean advance() {
      Preconditions.checkState(!this.successorIterator.hasNext());
      if (!this.nodeIterator.hasNext()) {
         return false;
      } else {
         this.node = this.nodeIterator.next();
         this.successorIterator = this.graph.successors(this.node).iterator();
         return true;
      }
   }

   private static final class Directed extends EndpointPairIterator {
      private Directed(BaseGraph graph) {
         super(graph, null);
      }

      @CheckForNull
      protected EndpointPair computeNext() {
         while(!this.successorIterator.hasNext()) {
            if (!this.advance()) {
               return (EndpointPair)this.endOfData();
            }
         }

         return EndpointPair.ordered(Objects.requireNonNull(this.node), this.successorIterator.next());
      }
   }

   private static final class Undirected extends EndpointPairIterator {
      @CheckForNull
      private Set visitedNodes;

      private Undirected(BaseGraph graph) {
         super(graph, null);
         this.visitedNodes = Sets.newHashSetWithExpectedSize(graph.nodes().size() + 1);
      }

      @CheckForNull
      protected EndpointPair computeNext() {
         do {
            Objects.requireNonNull(this.visitedNodes);

            while(this.successorIterator.hasNext()) {
               N otherNode = (N)this.successorIterator.next();
               if (!this.visitedNodes.contains(otherNode)) {
                  return EndpointPair.unordered(Objects.requireNonNull(this.node), otherNode);
               }
            }

            this.visitedNodes.add(this.node);
         } while(this.advance());

         this.visitedNodes = null;
         return (EndpointPair)this.endOfData();
      }
   }
}
