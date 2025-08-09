package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.Immutable;
import javax.annotation.CheckForNull;

@Immutable(
   containerOf = {"N"}
)
@ElementTypesAreNonnullByDefault
@Beta
public abstract class EndpointPair implements Iterable {
   private final Object nodeU;
   private final Object nodeV;

   private EndpointPair(Object nodeU, Object nodeV) {
      this.nodeU = Preconditions.checkNotNull(nodeU);
      this.nodeV = Preconditions.checkNotNull(nodeV);
   }

   public static EndpointPair ordered(Object source, Object target) {
      return new Ordered(source, target);
   }

   public static EndpointPair unordered(Object nodeU, Object nodeV) {
      return new Unordered(nodeV, nodeU);
   }

   static EndpointPair of(Graph graph, Object nodeU, Object nodeV) {
      return graph.isDirected() ? ordered(nodeU, nodeV) : unordered(nodeU, nodeV);
   }

   static EndpointPair of(Network network, Object nodeU, Object nodeV) {
      return network.isDirected() ? ordered(nodeU, nodeV) : unordered(nodeU, nodeV);
   }

   public abstract Object source();

   public abstract Object target();

   public final Object nodeU() {
      return this.nodeU;
   }

   public final Object nodeV() {
      return this.nodeV;
   }

   public final Object adjacentNode(Object node) {
      if (node.equals(this.nodeU)) {
         return this.nodeV;
      } else if (node.equals(this.nodeV)) {
         return this.nodeU;
      } else {
         throw new IllegalArgumentException("EndpointPair " + this + " does not contain node " + node);
      }
   }

   public abstract boolean isOrdered();

   public final UnmodifiableIterator iterator() {
      return Iterators.forArray(this.nodeU, this.nodeV);
   }

   public abstract boolean equals(@CheckForNull Object obj);

   public abstract int hashCode();

   private static final class Ordered extends EndpointPair {
      private Ordered(Object source, Object target) {
         super(source, target, null);
      }

      public Object source() {
         return this.nodeU();
      }

      public Object target() {
         return this.nodeV();
      }

      public boolean isOrdered() {
         return true;
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof EndpointPair)) {
            return false;
         } else {
            EndpointPair<?> other = (EndpointPair)obj;
            if (this.isOrdered() != other.isOrdered()) {
               return false;
            } else {
               return this.source().equals(other.source()) && this.target().equals(other.target());
            }
         }
      }

      public int hashCode() {
         return Objects.hashCode(this.source(), this.target());
      }

      public String toString() {
         return "<" + this.source() + " -> " + this.target() + ">";
      }
   }

   private static final class Unordered extends EndpointPair {
      private Unordered(Object nodeU, Object nodeV) {
         super(nodeU, nodeV, null);
      }

      public Object source() {
         throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
      }

      public Object target() {
         throw new UnsupportedOperationException("Cannot call source()/target() on a EndpointPair from an undirected graph. Consider calling adjacentNode(node) if you already have a node, or nodeU()/nodeV() if you don't.");
      }

      public boolean isOrdered() {
         return false;
      }

      public boolean equals(@CheckForNull Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof EndpointPair)) {
            return false;
         } else {
            EndpointPair<?> other = (EndpointPair)obj;
            if (this.isOrdered() != other.isOrdered()) {
               return false;
            } else if (this.nodeU().equals(other.nodeU())) {
               return this.nodeV().equals(other.nodeV());
            } else {
               return this.nodeU().equals(other.nodeV()) && this.nodeV().equals(other.nodeU());
            }
         }
      }

      public int hashCode() {
         return this.nodeU().hashCode() + this.nodeV().hashCode();
      }

      public String toString() {
         return "[" + this.nodeU() + ", " + this.nodeV() + "]";
      }
   }
}
