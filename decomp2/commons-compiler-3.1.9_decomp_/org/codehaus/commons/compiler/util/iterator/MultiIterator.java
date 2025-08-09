package org.codehaus.commons.compiler.util.iterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.codehaus.commons.compiler.InternalCompilerException;

public class MultiIterator implements Iterator {
   private final Iterator outer;
   private Iterator inner = Collections.emptyList().iterator();

   public MultiIterator(Iterator[] iterators) {
      this.outer = Arrays.asList(iterators).iterator();
   }

   public MultiIterator(Collection[] collections) {
      this.outer = Arrays.asList(collections).iterator();
   }

   public MultiIterator(Object[][] arrays) {
      this.outer = Arrays.asList(arrays).iterator();
   }

   public MultiIterator(Collection collection) {
      this.outer = collection.iterator();
   }

   public MultiIterator(Iterator iterator) {
      this.outer = iterator;
   }

   public MultiIterator(Object[] array) {
      this.outer = Arrays.asList(array).iterator();
   }

   public MultiIterator(Object object, Collection collection) {
      this.outer = Arrays.asList(new Object[]{object}, collection).iterator();
   }

   public MultiIterator(Collection collection, Object object) {
      this.outer = Arrays.asList(collection, new Object[]{object}).iterator();
   }

   public MultiIterator(Object prefix, Iterator iterator) {
      this.outer = Arrays.asList(new Object[]{prefix}, iterator).iterator();
   }

   public MultiIterator(Iterator iterator, Object suffix) {
      this.outer = Arrays.asList(iterator, new Object[]{suffix}).iterator();
   }

   public boolean hasNext() {
      while(!this.inner.hasNext()) {
         if (!this.outer.hasNext()) {
            return false;
         }

         Object o = this.outer.next();
         if (o instanceof Iterator) {
            Iterator<T> tmp = (Iterator)o;
            this.inner = tmp;
         } else if (o instanceof Collection) {
            Collection<T> tmp = (Collection)o;
            this.inner = tmp.iterator();
         } else {
            if (!(o instanceof Object[])) {
               throw new InternalCompilerException("Unexpected element type \"" + o.getClass().getName() + "\"");
            }

            T[] tmp = (T[])((Object[])o);
            this.inner = Arrays.asList(tmp).iterator();
         }
      }

      return true;
   }

   public Object next() {
      if (this.hasNext()) {
         return this.inner.next();
      } else {
         throw new NoSuchElementException();
      }
   }

   public void remove() {
      this.inner.remove();
   }
}
