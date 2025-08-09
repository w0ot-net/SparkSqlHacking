package org.codehaus.commons.compiler.util.iterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MultiDimensionalIterator implements Iterator {
   private final Iterator[] nest;
   private static final Iterator EMPTY_ITERATOR = new Iterator() {
      public boolean hasNext() {
         return false;
      }

      public Object next() {
         throw new NoSuchElementException();
      }

      public void remove() {
         throw new UnsupportedOperationException("remove");
      }
   };

   public MultiDimensionalIterator(Iterator delegate, int dimensionCount) {
      this.nest = new Iterator[dimensionCount];
      this.nest[0] = delegate;

      for(int i = 1; i < dimensionCount; ++i) {
         this.nest[i] = EMPTY_ITERATOR;
      }

   }

   public boolean hasNext() {
      if (this.nest[this.nest.length - 1].hasNext()) {
         return true;
      } else {
         int i = this.nest.length - 2;
         if (i < 0) {
            return false;
         } else {
            while(true) {
               while(this.nest[i].hasNext()) {
                  if (i == this.nest.length - 1) {
                     return true;
                  }

                  Object o = this.nest[i].next();
                  if (o instanceof Iterator) {
                     ++i;
                     this.nest[i] = (Iterator)o;
                  } else if (o instanceof Object[]) {
                     ++i;
                     this.nest[i] = Arrays.asList(o).iterator();
                  } else if (o instanceof Collection) {
                     ++i;
                     this.nest[i] = ((Collection)o).iterator();
                  } else {
                     if (!(o instanceof Enumeration)) {
                        throw new UniterableElementException();
                     }

                     ++i;
                     this.nest[i] = new EnumerationIterator((Enumeration)o);
                  }
               }

               if (i == 0) {
                  return false;
               }

               --i;
            }
         }
      }
   }

   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         T result = (T)this.nest[this.nest.length - 1].next();
         return result;
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("remove");
   }
}
