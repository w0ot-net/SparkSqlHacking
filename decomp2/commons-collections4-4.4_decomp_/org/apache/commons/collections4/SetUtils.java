package org.apache.commons.collections4;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.commons.collections4.set.PredicatedNavigableSet;
import org.apache.commons.collections4.set.PredicatedSet;
import org.apache.commons.collections4.set.PredicatedSortedSet;
import org.apache.commons.collections4.set.TransformedNavigableSet;
import org.apache.commons.collections4.set.TransformedSet;
import org.apache.commons.collections4.set.TransformedSortedSet;
import org.apache.commons.collections4.set.UnmodifiableNavigableSet;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.apache.commons.collections4.set.UnmodifiableSortedSet;

public class SetUtils {
   public static final SortedSet EMPTY_SORTED_SET = UnmodifiableSortedSet.unmodifiableSortedSet(new TreeSet());

   public static SetView difference(final Set a, final Set b) {
      if (a != null && b != null) {
         final Predicate<E> notContainedInB = new Predicate() {
            public boolean evaluate(Object object) {
               return !b.contains(object);
            }
         };
         return new SetView() {
            public boolean contains(Object o) {
               return a.contains(o) && !b.contains(o);
            }

            public Iterator createIterator() {
               return IteratorUtils.filteredIterator(a.iterator(), notContainedInB);
            }
         };
      } else {
         throw new NullPointerException("Sets must not be null.");
      }
   }

   public static SetView disjunction(final Set a, final Set b) {
      if (a != null && b != null) {
         final SetView<E> aMinusB = difference(a, b);
         final SetView<E> bMinusA = difference(b, a);
         return new SetView() {
            public boolean contains(Object o) {
               return a.contains(o) ^ b.contains(o);
            }

            public Iterator createIterator() {
               return IteratorUtils.chainedIterator(aMinusB.iterator(), bMinusA.iterator());
            }

            public boolean isEmpty() {
               return aMinusB.isEmpty() && bMinusA.isEmpty();
            }

            public int size() {
               return aMinusB.size() + bMinusA.size();
            }
         };
      } else {
         throw new NullPointerException("Sets must not be null.");
      }
   }

   public static Set emptyIfNull(Set set) {
      return set == null ? Collections.emptySet() : set;
   }

   public static Set emptySet() {
      return Collections.emptySet();
   }

   public static SortedSet emptySortedSet() {
      return EMPTY_SORTED_SET;
   }

   public static int hashCodeForSet(Collection set) {
      if (set == null) {
         return 0;
      } else {
         int hashCode = 0;

         for(Object obj : set) {
            if (obj != null) {
               hashCode += obj.hashCode();
            }
         }

         return hashCode;
      }
   }

   public static HashSet hashSet(Object... items) {
      return items == null ? null : new HashSet(Arrays.asList(items));
   }

   public static SetView intersection(final Set a, final Set b) {
      if (a != null && b != null) {
         final Predicate<E> containedInB = new Predicate() {
            public boolean evaluate(Object object) {
               return b.contains(object);
            }
         };
         return new SetView() {
            public boolean contains(Object o) {
               return a.contains(o) && b.contains(o);
            }

            public Iterator createIterator() {
               return IteratorUtils.filteredIterator(a.iterator(), containedInB);
            }
         };
      } else {
         throw new NullPointerException("Sets must not be null.");
      }
   }

   public static boolean isEqualSet(Collection set1, Collection set2) {
      if (set1 == set2) {
         return true;
      } else {
         return set1 != null && set2 != null && set1.size() == set2.size() ? set1.containsAll(set2) : false;
      }
   }

   public static Set newIdentityHashSet() {
      return Collections.newSetFromMap(new IdentityHashMap());
   }

   public static Set orderedSet(Set set) {
      return ListOrderedSet.listOrderedSet(set);
   }

   public static SortedSet predicatedNavigableSet(NavigableSet set, Predicate predicate) {
      return PredicatedNavigableSet.predicatedNavigableSet(set, predicate);
   }

   public static Set predicatedSet(Set set, Predicate predicate) {
      return PredicatedSet.predicatedSet(set, predicate);
   }

   public static SortedSet predicatedSortedSet(SortedSet set, Predicate predicate) {
      return PredicatedSortedSet.predicatedSortedSet(set, predicate);
   }

   public static Set synchronizedSet(Set set) {
      return Collections.synchronizedSet(set);
   }

   public static SortedSet synchronizedSortedSet(SortedSet set) {
      return Collections.synchronizedSortedSet(set);
   }

   public static SortedSet transformedNavigableSet(NavigableSet set, Transformer transformer) {
      return TransformedNavigableSet.transformingNavigableSet(set, transformer);
   }

   public static Set transformedSet(Set set, Transformer transformer) {
      return TransformedSet.transformingSet(set, transformer);
   }

   public static SortedSet transformedSortedSet(SortedSet set, Transformer transformer) {
      return TransformedSortedSet.transformingSortedSet(set, transformer);
   }

   public static SetView union(final Set a, final Set b) {
      if (a != null && b != null) {
         final SetView<E> bMinusA = difference(b, a);
         return new SetView() {
            public boolean contains(Object o) {
               return a.contains(o) || b.contains(o);
            }

            public Iterator createIterator() {
               return IteratorUtils.chainedIterator(a.iterator(), bMinusA.iterator());
            }

            public boolean isEmpty() {
               return a.isEmpty() && b.isEmpty();
            }

            public int size() {
               return a.size() + bMinusA.size();
            }
         };
      } else {
         throw new NullPointerException("Sets must not be null.");
      }
   }

   public static SortedSet unmodifiableNavigableSet(NavigableSet set) {
      return UnmodifiableNavigableSet.unmodifiableNavigableSet(set);
   }

   public static Set unmodifiableSet(Object... items) {
      return items == null ? null : UnmodifiableSet.unmodifiableSet(hashSet(items));
   }

   public static Set unmodifiableSet(Set set) {
      return UnmodifiableSet.unmodifiableSet(set);
   }

   public static SortedSet unmodifiableSortedSet(SortedSet set) {
      return UnmodifiableSortedSet.unmodifiableSortedSet(set);
   }

   private SetUtils() {
   }

   public abstract static class SetView extends AbstractSet {
      public void copyInto(Set set) {
         CollectionUtils.addAll(set, (Iterable)this);
      }

      protected abstract Iterator createIterator();

      public Iterator iterator() {
         return IteratorUtils.unmodifiableIterator(this.createIterator());
      }

      public int size() {
         return IteratorUtils.size(this.iterator());
      }

      public Set toSet() {
         Set<E> set = new HashSet(this.size());
         this.copyInto(set);
         return set;
      }
   }
}
