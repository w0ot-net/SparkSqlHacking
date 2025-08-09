package org.codehaus.commons.compiler.util.iterator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.codehaus.commons.compiler.util.Predicate;
import org.codehaus.commons.nullanalysis.Nullable;

public final class Iterables {
   private Iterables() {
   }

   public static Iterable filterByClass(Object[] delegate, Class qualifyingClass) {
      return filterByClass((Iterable)Arrays.asList(delegate), qualifyingClass);
   }

   public static Iterable filterByClass(final Iterable delegate, final Class qualifyingClass) {
      return new Iterable() {
         public Iterator iterator() {
            return Iterables.filterByClass(delegate.iterator(), qualifyingClass);
         }
      };
   }

   public static Iterator filterByClass(Iterator delegate, final Class qualifyingClass) {
      Iterator<T> result = filter(delegate, new Predicate() {
         public boolean evaluate(@Nullable Object o) {
            return o != null && qualifyingClass.isAssignableFrom(o.getClass());
         }
      });
      return result;
   }

   public static Iterable filter(Object[] delegate, Predicate predicate) {
      return filter((Iterable)Arrays.asList(delegate), predicate);
   }

   public static Iterable filter(final Iterable delegate, final Predicate predicate) {
      return new Iterable() {
         public Iterator iterator() {
            return Iterables.filter(delegate.iterator(), predicate);
         }
      };
   }

   public static Iterator filter(final Iterator delegate, final Predicate predicate) {
      return new Iterator() {
         State state;
         @Nullable
         Object nextElement;

         {
            this.state = Iterables.State.DEFAULT;
         }

         public boolean hasNext() {
            switch (this.state) {
               case DEFAULT:
                  while(delegate.hasNext()) {
                     T ne = (T)delegate.next();
                     if (predicate.evaluate(ne)) {
                        this.nextElement = ne;
                        this.state = Iterables.State.READ_AHEAD;
                        return true;
                     }
                  }

                  this.state = Iterables.State.AT_END;
                  return false;
               case READ_AHEAD:
                  return true;
               case AT_END:
                  return false;
               default:
                  throw new AssertionError(this.state);
            }
         }

         @Nullable
         public Object next() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               this.state = Iterables.State.DEFAULT;
               T result = (T)this.nextElement;
               this.nextElement = null;
               return result;
            }
         }

         public void remove() {
            delegate.remove();
         }
      };
   }

   public static Object[] toArray(Iterable delegate, Class elementType) {
      return toArray(delegate.iterator(), elementType);
   }

   public static Object[] toArray(Iterator delegate, Class componentType) {
      List<T> l = new ArrayList();

      while(delegate.hasNext()) {
         l.add(delegate.next());
      }

      T[] array = (T[])((Object[])Array.newInstance(componentType, l.size()));
      return l.toArray(array);
   }

   private static enum State {
      DEFAULT,
      READ_AHEAD,
      AT_END;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{DEFAULT, READ_AHEAD, AT_END};
      }
   }
}
