package org.apache.commons.collections4.set;

import java.util.Set;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;

public class TransformedSet extends TransformedCollection implements Set {
   private static final long serialVersionUID = 306127383500410386L;

   public static TransformedSet transformingSet(Set set, Transformer transformer) {
      return new TransformedSet(set, transformer);
   }

   public static Set transformedSet(Set set, Transformer transformer) {
      TransformedSet<E> decorated = new TransformedSet(set, transformer);
      if (set.size() > 0) {
         E[] values = (E[])((Object[])set.toArray());
         set.clear();

         for(Object value : values) {
            decorated.decorated().add(transformer.transform(value));
         }
      }

      return decorated;
   }

   protected TransformedSet(Set set, Transformer transformer) {
      super(set, transformer);
   }

   public boolean equals(Object object) {
      return object == this || this.decorated().equals(object);
   }

   public int hashCode() {
      return this.decorated().hashCode();
   }
}
