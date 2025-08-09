package org.sparkproject.jetty.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class TopologicalSort {
   private final Map _dependencies = new HashMap();

   public void addDependency(Object dependent, Object... dependency) {
      Set<T> set = (Set)this._dependencies.get(dependent);
      if (set == null) {
         set = new HashSet();
         this._dependencies.put(dependent, set);
      }

      for(Object d : dependency) {
         set.add(d);
      }

   }

   public void addBeforeAfter(Object before, Object after) {
      this.addDependency(after, before);
   }

   public void sort(Object[] array) {
      List<T> sorted = new ArrayList();
      Set<T> visited = new HashSet();
      Comparator<T> comparator = new InitialOrderComparator(array);

      for(Object t : array) {
         this.visit(t, visited, sorted, comparator);
      }

      sorted.toArray(array);
   }

   public void sort(Collection list) {
      List<T> sorted = new ArrayList();
      Set<T> visited = new HashSet();
      Comparator<T> comparator = new InitialOrderComparator(list);

      for(Object t : list) {
         this.visit(t, visited, sorted, comparator);
      }

      list.clear();
      list.addAll(sorted);
   }

   private void visit(Object item, Set visited, List sorted, Comparator comparator) {
      if (!visited.contains(item)) {
         visited.add(item);
         Set<T> dependencies = (Set)this._dependencies.get(item);
         if (dependencies != null) {
            SortedSet<T> orderedDeps = new TreeSet(comparator);
            orderedDeps.addAll(dependencies);

            try {
               for(Object d : orderedDeps) {
                  this.visit(d, visited, sorted, comparator);
               }
            } catch (CyclicException e) {
               throw new CyclicException(item, e);
            }
         }

         sorted.add(item);
      } else if (!sorted.contains(item)) {
         throw new CyclicException(item);
      }

   }

   public String toString() {
      return "TopologicalSort " + String.valueOf(this._dependencies);
   }

   private static class InitialOrderComparator implements Comparator {
      private final Map _indexes = new HashMap();

      InitialOrderComparator(Object[] initial) {
         int i = 0;

         for(Object t : initial) {
            this._indexes.put(t, i++);
         }

      }

      InitialOrderComparator(Collection initial) {
         int i = 0;

         for(Object t : initial) {
            this._indexes.put(t, i++);
         }

      }

      public int compare(Object o1, Object o2) {
         Integer i1 = (Integer)this._indexes.get(o1);
         Integer i2 = (Integer)this._indexes.get(o2);
         if (i1 != null && i2 != null && !i1.equals(o2)) {
            return i1 < i2 ? -1 : 1;
         } else {
            return 0;
         }
      }
   }

   private static class CyclicException extends IllegalStateException {
      CyclicException(Object item) {
         super("cyclic at " + String.valueOf(item));
      }

      CyclicException(Object item, CyclicException e) {
         super("cyclic at " + String.valueOf(item), e);
      }
   }
}
