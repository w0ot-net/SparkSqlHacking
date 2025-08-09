package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.set.TransformedSet;

public class TransformedBag extends TransformedCollection implements Bag {
   private static final long serialVersionUID = 5421170911299074185L;

   public static Bag transformingBag(Bag bag, Transformer transformer) {
      return new TransformedBag(bag, transformer);
   }

   public static Bag transformedBag(Bag bag, Transformer transformer) {
      TransformedBag<E> decorated = new TransformedBag(bag, transformer);
      if (bag.size() > 0) {
         E[] values = (E[])((Object[])bag.toArray());
         bag.clear();

         for(Object value : values) {
            decorated.decorated().add(transformer.transform(value));
         }
      }

      return decorated;
   }

   protected TransformedBag(Bag bag, Transformer transformer) {
      super(bag, transformer);
   }

   protected Bag getBag() {
      return (Bag)this.decorated();
   }

   public boolean equals(Object object) {
      return object == this || this.decorated().equals(object);
   }

   public int hashCode() {
      return this.decorated().hashCode();
   }

   public int getCount(Object object) {
      return this.getBag().getCount(object);
   }

   public boolean remove(Object object, int nCopies) {
      return this.getBag().remove(object, nCopies);
   }

   public boolean add(Object object, int nCopies) {
      return this.getBag().add(this.transform(object), nCopies);
   }

   public Set uniqueSet() {
      Set<E> set = this.getBag().uniqueSet();
      return TransformedSet.transformingSet(set, this.transformer);
   }
}
