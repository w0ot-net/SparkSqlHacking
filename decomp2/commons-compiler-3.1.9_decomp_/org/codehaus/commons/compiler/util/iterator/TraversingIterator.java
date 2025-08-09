package org.codehaus.commons.compiler.util.iterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import org.codehaus.commons.nullanalysis.Nullable;

public class TraversingIterator implements Iterator {
   private final Stack nest = new Stack();
   @Nullable
   private Object nextElement;
   private boolean nextElementRead;

   public TraversingIterator(Iterator delegate) {
      this.nest.push(delegate);
   }

   public boolean hasNext() {
      return this.nextElementRead || this.readNext();
   }

   public Object next() {
      if (!this.nextElementRead && !this.readNext()) {
         throw new NoSuchElementException();
      } else {
         this.nextElementRead = false;

         assert this.nextElement != null;

         return this.nextElement;
      }
   }

   private boolean readNext() {
      while(true) {
         if (!this.nest.empty()) {
            Iterator<?> it = (Iterator)this.nest.peek();
            if (!it.hasNext()) {
               this.nest.pop();
               continue;
            }

            Object o = it.next();
            if (o instanceof Iterator) {
               this.nest.push((Iterator)o);
               continue;
            }

            if (o instanceof Object[]) {
               this.nest.push(Arrays.asList(o).iterator());
               continue;
            }

            if (o instanceof Collection) {
               this.nest.push(((Collection)o).iterator());
               continue;
            }

            if (o instanceof Enumeration) {
               this.nest.push(new EnumerationIterator((Enumeration)o));
               continue;
            }

            this.nextElement = o;
            this.nextElementRead = true;
            return true;
         }

         return false;
      }
   }

   public void remove() {
      ((Iterator)this.nest.peek()).remove();
   }
}
