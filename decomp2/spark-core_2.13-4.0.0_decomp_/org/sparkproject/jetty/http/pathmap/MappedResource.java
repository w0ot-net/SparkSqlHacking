package org.sparkproject.jetty.http.pathmap;

import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject("Mapped Resource")
public class MappedResource implements Comparable {
   private final PathSpec pathSpec;
   private final Object resource;
   private final MatchedResource preMatched;

   public MappedResource(PathSpec pathSpec, Object resource) {
      this.pathSpec = pathSpec;
      this.resource = resource;
      MatchedResource<E> matched;
      switch (pathSpec.getGroup()) {
         case ROOT:
            matched = new MatchedResource(resource, pathSpec, pathSpec.matched("/"));
            break;
         case EXACT:
            matched = new MatchedResource(resource, pathSpec, pathSpec.matched(pathSpec.getDeclaration()));
            break;
         default:
            matched = null;
      }

      this.preMatched = matched;
   }

   public MatchedResource getPreMatched() {
      return this.preMatched;
   }

   public int compareTo(MappedResource other) {
      return this.pathSpec.compareTo(other.pathSpec);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         MappedResource<?> other = (MappedResource)obj;
         if (this.pathSpec == null) {
            if (other.pathSpec != null) {
               return false;
            }
         } else if (!this.pathSpec.equals(other.pathSpec)) {
            return false;
         }

         return true;
      }
   }

   @ManagedAttribute(
      value = "path spec",
      readonly = true
   )
   public PathSpec getPathSpec() {
      return this.pathSpec;
   }

   @ManagedAttribute(
      value = "resource",
      readonly = true
   )
   public Object getResource() {
      return this.resource;
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.pathSpec == null ? 0 : this.pathSpec.hashCode());
      return result;
   }

   public String toString() {
      return String.format("MappedResource[pathSpec=%s,resource=%s]", this.pathSpec, this.resource);
   }
}
