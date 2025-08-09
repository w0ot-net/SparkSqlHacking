package org.codehaus.commons.compiler.util.iterator;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.commons.nullanalysis.Nullable;

public class IteratorCollection extends AbstractCollection {
   private final Iterator iterator;
   private final List elements = new ArrayList();

   public IteratorCollection(Iterator iterator) {
      this.iterator = iterator;
   }

   public Iterator iterator() {
      return new Iterator() {
         @Nullable
         private Iterator elementsIterator;

         {
            this.elementsIterator = IteratorCollection.this.elements.iterator();
         }

         public Object next() {
            Iterator<T> ei = this.elementsIterator;
            if (ei != null) {
               if (ei.hasNext()) {
                  return ei.next();
               }

               this.elementsIterator = null;
            }

            T o = (T)IteratorCollection.this.iterator.next();
            IteratorCollection.this.elements.add(o);
            return o;
         }

         public boolean hasNext() {
            return this.elementsIterator != null && this.elementsIterator.hasNext() || IteratorCollection.this.iterator.hasNext();
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      };
   }

   public int size() {
      int size = 0;

      for(Object o : this) {
         ++size;
      }

      return size;
   }
}
