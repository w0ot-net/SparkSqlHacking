package org.sparkproject.jetty.http.pathmap;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public class PathSpecSet extends AbstractSet implements Predicate {
   private final PathMappings specs = new PathMappings();

   public boolean test(String s) {
      return this.specs.getMatched(s) != null;
   }

   public int size() {
      return this.specs.size();
   }

   private PathSpec asPathSpec(Object o) {
      if (o == null) {
         return null;
      } else {
         return o instanceof PathSpec ? (PathSpec)o : PathSpec.from(Objects.toString(o));
      }
   }

   public boolean add(String s) {
      return this.specs.put((PathSpec)PathSpec.from(s), Boolean.TRUE);
   }

   public boolean remove(Object o) {
      return this.specs.remove(this.asPathSpec(o));
   }

   public void clear() {
      this.specs.reset();
   }

   public Iterator iterator() {
      final Iterator<MappedResource<Boolean>> iterator = this.specs.iterator();
      return new Iterator() {
         public boolean hasNext() {
            return iterator.hasNext();
         }

         public String next() {
            return ((MappedResource)iterator.next()).getPathSpec().getDeclaration();
         }
      };
   }
}
