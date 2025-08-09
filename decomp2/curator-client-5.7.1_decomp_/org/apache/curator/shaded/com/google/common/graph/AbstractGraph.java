package org.apache.curator.shaded.com.google.common.graph;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.Beta;

@ElementTypesAreNonnullByDefault
@Beta
public abstract class AbstractGraph extends AbstractBaseGraph implements Graph {
   public final boolean equals(@CheckForNull Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Graph)) {
         return false;
      } else {
         Graph<?> other = (Graph)obj;
         return this.isDirected() == other.isDirected() && this.nodes().equals(other.nodes()) && this.edges().equals(other.edges());
      }
   }

   public final int hashCode() {
      return this.edges().hashCode();
   }

   public String toString() {
      return "isDirected: " + this.isDirected() + ", allowsSelfLoops: " + this.allowsSelfLoops() + ", nodes: " + this.nodes() + ", edges: " + this.edges();
   }
}
